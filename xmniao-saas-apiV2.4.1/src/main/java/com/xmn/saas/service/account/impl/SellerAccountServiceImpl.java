package com.xmn.saas.service.account.impl;

import com.xmn.saas.base.GlobalConfig;
import com.xmn.saas.base.ResponseCode;
import com.xmn.saas.base.ThriftBuilder;
import com.xmn.saas.base.thrift.tbase.MentionAccount;
import com.xmn.saas.dao.common.SellerAccountDao;
import com.xmn.saas.dao.wallet.SellerDao;
import com.xmn.saas.entity.common.SellerAccount;
import com.xmn.saas.entity.wallet.Seller;
import com.xmn.saas.exception.SaasException;
import com.xmn.saas.service.account.SellerAccountService;
import com.xmn.saas.service.base.RedisService;
import com.xmn.saas.service.base.SynthesizeService;
import com.xmn.saas.service.sms.SmsService;
import com.xmn.saas.service.wallet.WithdrawalService;
import com.xmn.saas.utils.CryptoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * create 2016/09/23
 * @author yangQiang
 */

@Service
public class SellerAccountServiceImpl implements SellerAccountService {

    // 注入Redis服务类
    @Autowired
    private RedisService redisService;

    // 短信服务类
    @Autowired
    private SmsService smsService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    // 注入商户账户DAO (t_seller_account)
    @Autowired
    private SellerAccountDao sellerAccountDao;

    @Autowired
    private GlobalConfig globalConfig;

    @Autowired
    private WithdrawalService withdrawalService;

    @Autowired
    private SellerDao sellerDao;


    // 初始化日志类
    private final Logger logger = LoggerFactory.getLogger(SellerAccountServiceImpl.class);

    @Transactional
    @Override
    public HashMap<String, Object> login(SellerAccount sellerAccount) throws Exception {
        // 根据用户名和密码查询t_seller_account

        SellerAccount loggedAccount = sellerAccountDao.login(sellerAccount);

        // 判断是否有查询记录
        if (loggedAccount == null) { throw new SaasException("用户名或密码错误!");}

        // 判断账户是否被冻结
        if (loggedAccount.getUserstatus() != 0) { throw new SaasException("该账号已被冻结或删除!"); }

        // 判断商户是否被冻结
        Seller seller = sellerDao.selectByPrimaryKey(loggedAccount.getSellerid());
        if (seller.getIsonline() != 1) { throw new SaasException("该商户已下线,请联系客服!");}


        // 判断是否完成激活步骤
        String sellerId = String.valueOf(loggedAccount.getSellerid());

        List<Integer> steps = new ArrayList<>();

        // 只有账户类型为 "老板" 才会需要要求验证激活步骤
        if(loggedAccount.getType() == 1) {
            if (checkDefaultPwd(loggedAccount)) {  // 判断用户是否使用的默认密码
                steps.add(1);   // 账户使用的默认的密码
            }
            if (!checkPayPwd(sellerId)) {    // 判断是否设置了支付密码
                steps.add(2);   // 没有修改支付密码
            }
            if (!checkBankCard(sellerId)) {    // 判断是否绑定了银行卡
                steps.add(3);   // 没有绑定银行卡
            }
            // 判断用户是否同意协议:如果Redis中存在该key 说明已同意
            if (!redisService.exsitKey(RedisService.PREFIX_ACCOUNT_AGREEMENT+sellerId)) {
                steps.add(4);
            }

            // 如果不步骤包含 2 3
            ArrayList<Integer> content = new ArrayList<>();
            content.add(2);
            content.add(3);
            if (! steps.containsAll(content)) {
                steps.remove(new Integer(1));  // 删除 步骤1
            }

        }

        // 生成UUID用作sessionToken,并未去除UUID中的中划线("-"). UUID默认32位
        String sessionToken = UUID.randomUUID().toString().replaceAll("-","");

        // 获取该账号的存在Reids aidKey
        String aidRedisKey = RedisService.PREFIX_ACCOUNT_AID + String.valueOf(loggedAccount.getAid());
        String sessionTokenRedisKey = RedisService.PREFIX_ACCOUNT + sessionToken;

        // 如果该账户已登录(在Redis中有其他sessionToken, 则清理掉之前的sessionToken
        if(redisService.exsitKey(aidRedisKey)) {
            // 通过aid获取已登录的sessionToken
            String oldSessionToken = redisService.getString(aidRedisKey);
            // 删除已登录的sessoinToken
            redisService.deleteKey(oldSessionToken);
        }

        // 在Redis存入Aid Value已登录的sessionToken, 用于验证账户是否登陆

        Calendar calendar = Calendar.getInstance();
        // 从配置文件中获取Redis缓存时间
        calendar.add(Calendar.MINUTE,globalConfig.getRedisTimeSessionToken());
        // 存入Redis
        redisService.setString(aidRedisKey ,sessionTokenRedisKey,calendar.getTime());

        // 添加sessionToken
        redisService.setSessionCache(sessionTokenRedisKey,loggedAccount);

        // 修改用户最后一次登陆的时间
        SellerAccount account = new SellerAccount();
        account.setAid(loggedAccount.getAid());
        account.setEdate(new Date());
        sellerAccountDao.updateByPrimaryKeySelective(account);

        // 封装响应数据
        HashMap<String, Object> result = new HashMap<>();
        result.put("sessionToken",sessionToken);
        result.put("type",loggedAccount.getType());
        result.put("steps", steps);

        return result;
    }

    @Override
    public void logout(String sessionToken) {
        // 调用RedisServic删除sessionToken
        redisService.deleteSessionCacheObject(sessionToken);
    }

    @Transactional
    @Override
    public void updatePassword(SellerAccount sellerAccount, String oldPassword, String newPassword) throws SaasException {
        Integer aid = sellerAccount.getAid();
        // 判断是否在Redis中获取到主键
        if (aid == null) {
            logger.error("未从Redis获取到aid !");
            throw new SaasException("修改密码发生异常!");
        }

        // 判断原密码是否正确
        sellerAccount.setPassword(oldPassword);
        if(sellerAccountDao.countAccountByAidAndPassword(sellerAccount) < 1){
            throw new SaasException("原密码错误!");
        }

        // 修改商户账户密码
        sellerAccount.setPassword(newPassword);
        sellerAccountDao.updatePassword(sellerAccount);

    }

    @Override
    public Map<String, Object> getAccountDetail(SellerAccount sellerAccount) throws Exception {
        Map<String,Object> result = sellerAccountDao.selectAccountDetail(sellerAccount.getAid());
        if (result.get("isChain") == null) {
            result.put("isChain",0);
        }


        // 调用Thrift 接口查询商户信息
        try {
            SynthesizeService.Client client = ThriftBuilder.build(globalConfig.getThriftPayHost(),
                    Integer.parseInt(globalConfig.getThriftPayPort())
                    , "SynthesizeService", SynthesizeService.Client.class);
            ThriftBuilder.open();
            // 封装调用Thrift接口所需的参数
            Map<String, String> params = new HashMap<>();
            params.put("uId",sellerAccount.getSellerid().toString());
            params.put("userType","2");

            // 调用业务服务查询相关字段;
            MentionAccount mentionAccountList = client.getMentionAccountList(params);
            List<Map<String, String>> list = mentionAccountList.getAccountList();
            for (Map<String, String> maps : list) {
                result.put("identity", maps.get("identity"));    // 身份证号码
                result.put("phoneid", maps.get("mobileid"));     // 银行预留手机号
                result.put("fullname", maps.get("username"));    // 持卡人姓名
                result.put("isPublic", maps.get("ispublic"));    // 是否对公账户
                break;
            }

        } finally {
            ThriftBuilder.close();
        }

        result.put("authentication","已实名"); // 是否实名认证
        result.put("hPoundage",globalConfig.getMoreLimit());    // 提现手续费高于500时的手续费
        result.put("lPoundage",globalConfig.getUnderLimit());   // 提现手续费低于500时的手续费

        return result;
    }

    @Override
    @Transactional
    public void addSubAccount(String token, SellerAccount subAccount) throws SaasException {
        // 判断该手机号是否已存在
        if(sellerAccountDao.countAccountByPhone(subAccount) > 0){ throw new SaasException(subAccount.getPhone()+"该号码已经存在!");}

        // 获取父账号
        SellerAccount parentAccount = redisService.getSellerAccount(token);

        // 设置子账户账号为子账号的手机号码
        subAccount.setAccount(subAccount.getPhone());
        // 将sellerId设置为父账号的sellerId
        subAccount.setSellerid(parentAccount.getSellerid());
        // 设置主键为空
        subAccount.setAid(null);
        // 设置时间
        subAccount.setSdate(new Date());
        subAccount.setEdate(new Date());

        // 校验父账号权限
        doValidationParent(parentAccount,subAccount);
        // 向数据库插入子账号
        sellerAccountDao.insertSelective(subAccount);

    }

    @Override
    @Transactional
    public void updateSubAccount(String token, SellerAccount subAccount) throws SaasException {
        // 从数据库中查询用户
        SellerAccount sellerAccount = sellerAccountDao.selectByPrimaryKey(subAccount.getAid());
        SellerAccount parentAccount = redisService.getSellerAccount(token);

        if (sellerAccount == null) { throw new SaasException("未找到该账号(aid=" + subAccount.getAid() + ")");}

        // 判断修改的手机号是否已存在
        String account = sellerAccount.getAccount();
        if (!account.equals(subAccount.getPhone()) &&    // 判断是否修改手机号码
            sellerAccountDao.countAccountByPhone(subAccount) > 0    // 判断修改后的手机号码(phone)在数据库中是否存在相同的账号(account)
            ) {
            throw new SaasException(subAccount.getPhone() + "该账号已经存在!");
        }

        // 设置修改时间
        subAccount.setEdate(new Date());
        // 将手机号码(phone)设置为账号(account)
        subAccount.setAccount(subAccount.getPhone());

        // 校验父账号是否有权限操作子账户
        subAccount.setSellerid(parentAccount.getSellerid());
        doValidationParent(parentAccount,subAccount);

        // 更新子账号
        sellerAccountDao.updateByPrimaryKeySelective(subAccount);
    }

    @Override
    public List<SellerAccount> querySubAccountList(SellerAccount parentAccount) throws SaasException {
        // 检查权限
        doValidation(parentAccount);

        // 查询子账款列表,不包含该父账号( aid<>#{aid} )
        // 老板:1,2,3     店长:3,
        List<SellerAccount> accounts = sellerAccountDao.selectSubAccountBySellerId(parentAccount);

        // 如果没有查到,返回空集
        if (accounts == null) {
            return new ArrayList<>();
        }
        return accounts;
    }

    @Override
    public SellerAccount querySubAccountDetail(SellerAccount parentAccount, Integer subAid) throws SaasException {
        // 根据主键获取子账号
        SellerAccount subAccount = sellerAccountDao.selectByPrimaryKey(subAid);

        if (subAccount == null) {
            throw new SaasException("没有查询到该账户!");
        }

        // 验证父账号是否有权限操作操作子账户
        doValidationParent(parentAccount,subAccount);

        return subAccount;

    }

    @Override
    @Transactional
    public void setSubAccountStatus(SellerAccount parentAccount, SellerAccount subAccount) throws SaasException {
        // 验证父账号是否有权限操作该子账号
        SellerAccount sellerAccount = sellerAccountDao.selectByPrimaryKey(subAccount.getAid());
        doValidationParent(parentAccount,sellerAccount);

        // 执行更新语句
        sellerAccountDao.updateByPrimaryKeySelective(subAccount);
    }

    /**
     * 检查用户是否有权限操作子账户,如果没有会抛出异常
     * 只有老板(1), 店长(2)才能操作子账户
     * @param parentAccount
     * @throws SaasException
     */
    private void doValidation(SellerAccount parentAccount) throws SaasException {
        if (parentAccount.getType() > 2) {
            throw new SaasException("只有老板,店长才能操作子账号");
        }
    }

    @Override
    public void quickLogin(String token, Integer type, String sessionToken) throws SaasException {
        if (!redisService.exsitKey(token)) {

            throw new SaasException("二维码无效,请重新刷新网页!", ResponseCode.FAILURE);
        }

        switch (type){
            case 1:     // 1 检测二维码有效性, 如果token可用, 直接返回成功
                throw new SaasException("token有效",ResponseCode.SUCCESS);
            case 2:     // 2 扫码正确确认登录
                redisTemplate.opsForHash().put(token,"status", "1");
                redisTemplate.opsForHash().put(token,"token",RedisService.PREFIX_ACCOUNT + sessionToken);
                break;
            default:
                throw new SaasException("type参数有误", ResponseCode.DATAERR);
        }
    }

    @Override
    public void forgetPassword(SellerAccount sellerAccount, String verifyCode, Integer operation) throws SaasException {
        String account = sellerAccount.getAccount();
        // 获取Redis中缓存的短信验证码
        String redisCode = smsService.getSmsCode(account, SmsService.CODE_TYPE_LOGIN_PASSWORD);

        // 校验短信验证码
        if (!verifyCode.equals(redisCode)) {
            throw new SaasException("短信验证码无效!",ResponseCode.ACCESSCODE);
        }

        switch (operation){
            case 0 :    // 校验短信验证码
                return;
            case 1 :    // 修改登陆密码
                int count = sellerAccountDao.updatePasswordByAccount(sellerAccount);
                // 校验密码是否为空
                if (sellerAccount.getPassword() == null) {
                    throw new SaasException("密码(password)为空",ResponseCode.PARAM_ERROR);
                }
                if (count < 1){
                    logger.error("修改登陆密码失败!",sellerAccount);
                    throw new SaasException("更改密码失败!", ResponseCode.FAILURE);
                }else if (count > 1){
                    logger.error("内部数据异常,同时修改了两个账户的密码,请检查account是否重复!",sellerAccount);
                }
                smsService.deleteSmsCode(account,SmsService.CODE_TYPE_LOGIN_PASSWORD);
                break;
            default:    //
                throw new SaasException("操作类型(operation)输入错误, 不能为"+operation, ResponseCode.PARAM_ERROR);
        }

    }

    @Override
    public boolean checkGestureVerifyCode(SellerAccount loggedAccount, String verifyCode) throws SaasException {
        boolean result = false;

        // 获取Redis中缓存的短信验证码
        String redisCode = smsService.getSmsCode(loggedAccount.getAccount(), SmsService.CODE_TYPE_GESTURE_PASSWORD);
        if (verifyCode.equals(redisCode)) {
            smsService.deleteSmsCode(loggedAccount.getAccount(), SmsService.CODE_TYPE_GESTURE_PASSWORD);
            result = true;
        }
        return result;
    }

    @Override
    public void agreeAgreement(SellerAccount sellerAccount) {
        Integer sellerid = sellerAccount.getSellerid();
        // 将商户的是否同意协议存入Redis中
        redisService.setString(RedisService.PREFIX_ACCOUNT_AGREEMENT +sellerid,"1");
    }

    /**
     * 查询账户关联的店铺列表
     * @param account
     */
    @Override
    public List<Seller> queryShopList(String account) {
        return sellerDao.selectByAccount(account);
    }

    /**
     * 判断账户密码是否为默认密码
     * @param sellerAccount
     * @return
     * @throws Exception
     */
    private boolean checkDefaultPwd(SellerAccount sellerAccount) throws Exception {
        boolean result = false;
        String account = sellerAccount.getAccount();
        String password = sellerAccount.getPassword();

        // 账号后6位为默认密码; 截取后账户后6位;
        String defaultPwd = account.substring(account.length() - 6);
        // 获取默认密码 MD5 密文
        String defaultPwdMd5 = CryptoUtil.encryptMd5(defaultPwd, 32);
        // 判断是否为默认密码
        if (defaultPwdMd5.equals(password)) {
            result = true;
        }
        return result;
    }

    /**
     * 判断商户账号是否设置支付密码
     * @param sellerId
     * @return
     */
    private boolean checkPayPwd(String sellerId) throws Exception {
        boolean result = true;
        try {
            SynthesizeService.Client client = ThriftBuilder.build(globalConfig.getThriftPayHost(),
                    Integer.parseInt(globalConfig.getThriftPayPort()), "SynthesizeService", SynthesizeService.Client.class);
            ThriftBuilder.open();

            // checkWallet():验证是否是第一次提现 状态 0是 1否 2验证失败
            if (client.checkWallet(sellerId, "2") == 0 ) {
                // 返回0 表示没有设置密码
                result = false;
            }

        } finally {
            ThriftBuilder.close();
        }

        return result;
    }

    /**
     * 判断商户账户是否绑定银行卡
     * @param sellerId
     * @return
     */
    private boolean checkBankCard(String sellerId) throws Exception {
        boolean result = false;

        try {
            SynthesizeService.Client client = ThriftBuilder.build(globalConfig.getThriftPayHost(),
                    Integer.parseInt(globalConfig.getThriftPayPort()), "SynthesizeService", SynthesizeService.Client.class);
            ThriftBuilder.open();
            // getMentionAccount():查询用户的银行卡
            List<Map<String, String>> mentionAccount = client.getMentionAccount(sellerId, 2);
            if (mentionAccount != null && mentionAccount.size() > 0 ) {
                // 如果返回的集合不为空, 那么认定账户已绑定银行卡
                result = true;
            }
        } finally {
            ThriftBuilder.close();
        }

        return result;
    }

    /**
     * 检查父账号是否有权限操作子账号
     * @param parentAccount
     * @param subAccount
     * @return
     */
    private void doValidationParent(SellerAccount parentAccount, SellerAccount subAccount) throws SaasException {

        // 父账号与子账户不是同一个商户(sellerid), 判定为没有权限操作子账户
        if(!(parentAccount.getSellerid().equals(subAccount.getSellerid()))){
            throw new SaasException("该父账号没有权限操作该子账号");
        }

        switch (parentAccount.getType()) {
            case 1:    // 账户类型为老板, 只能操作 1,2,3 类型账户
                if (!(subAccount.getType() <= 3))
                    throw new SaasException("老板类型账户只能操作,老板,店长,服务员");
                break;
            case 2:    // 账户类型为店长, 只能操作 3 类型账户
                if (!(subAccount.getType() == 3))
                    throw new SaasException("店长类型账户只能操作,服务员");
                break;
            default:
                throw new SaasException("该账户没有权限操作子账户");
        }

    }
    
    /**
     * 粉丝券登录
     * @param sellerAccount
     * @throws Exception
     */
    @Transactional
    @Override
    public SellerAccount fansLogin(SellerAccount sellerAccount) throws Exception {
        // 根据用户名和密码查询t_seller_account

        SellerAccount loggedAccount = sellerAccountDao.login(sellerAccount);

        // 判断是否有查询记录
        if (loggedAccount == null) {
            throw new SaasException("用户名或密码错误!",1);
        }

        // 判断账户是否被冻结
        if (loggedAccount.getUserstatus() != 0) {
            throw new SaasException("该账号已被冻结或删除!",1);
        }
        
        return loggedAccount;
    }
    
}
