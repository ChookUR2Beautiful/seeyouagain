package com.xmn.saas.service.account;

import com.xmn.saas.entity.common.SellerAccount;
import com.xmn.saas.entity.wallet.Seller;
import com.xmn.saas.exception.SaasException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create 2016/09/23
 *
 * @author yangQiang
 */

public interface SellerAccountService {
    /**
     * 粉丝券登录
     * @param sellerAccount
     * @return
     * @throws Exception
     */
    public SellerAccount fansLogin(SellerAccount sellerAccount) throws Exception;
    /**
     * 用户登录,返回sessionToken, sessionToken时效为一个月
     * @param sellerAccount
     * @return
     * @throws Exception
     */
    HashMap<String, Object> login(SellerAccount sellerAccount) throws Exception;

    /**
     * 把Redis中的登陆缓存数据删除
     * @param sessionToken
     */
    void logout(String sessionToken);

    /**
     * 修改密码
     * @param sellerAccount 从Redis中查出的商户账户
     * @param oldPassword   原密码
     * @param newPassword   新密码
     */
    void updatePassword(SellerAccount sellerAccount, String oldPassword, String newPassword) throws SaasException;

    /**
     * 获取账户详情
     * @param sellerAccount
     * @return
     */
    Map<String,Object> getAccountDetail(SellerAccount sellerAccount) throws Exception;

    /**
     * 添加子账户
     * @param token
     * @param subAccount
     */
    void addSubAccount(String token, SellerAccount subAccount) throws SaasException;

    /**
     * 更新子账号
     * @param token
     * @param subAccount
     */
    void updateSubAccount(String token, SellerAccount subAccount) throws SaasException;

    /**
     * 查询出当前登录账号的所有子账号
     *
     * @param parentAccount
     * @return
     */
    List<SellerAccount> querySubAccountList(SellerAccount parentAccount) throws SaasException;

    /**
     * 根据子账号主键(aid) 查询子账号详情
     *
     * @param parentAccount
     * @param subAid
     * @return
     */
    SellerAccount querySubAccountDetail(SellerAccount parentAccount, Integer subAid) throws SaasException;

    /**
     * 修改子账号状态
     * @param parentAccount 父账号
     * @param subAccount    子账号
     */
    void setSubAccountStatus(SellerAccount parentAccount, SellerAccount subAccount) throws SaasException;

    /**
     * 扫码快速登录
     * @param token     // 二维码中的token参数
     * @param type      // 操作类型
     * @param sessionToken  // 会话令牌
     * @throws SaasException
     */
    void quickLogin(String token, Integer type, String sessionToken) throws SaasException;

    /**
     * 忘记登陆密码
     * 0.检测验证码 --> 1.修改登陆密码
     * @param sellerAccount 需要修改密码的账号
     * @param verifyCode    验证码
     * @param operation     操作类型 0.校验验证码 1.修改登陆密码
     */
    void forgetPassword(SellerAccount sellerAccount, String verifyCode, Integer operation) throws SaasException;

    /**
     * 校验忘记手势密码产生的短信验证码
     * @param loggedAccount // 已登录的账户
     * @param verifyCode    // 短信验证码
     */
    boolean checkGestureVerifyCode(SellerAccount loggedAccount, String verifyCode) throws SaasException;

    /**
     * 同意协议
     * @param sellerAccount
     */
    void agreeAgreement(SellerAccount sellerAccount);

    List<Seller> queryShopList(String account);
}
