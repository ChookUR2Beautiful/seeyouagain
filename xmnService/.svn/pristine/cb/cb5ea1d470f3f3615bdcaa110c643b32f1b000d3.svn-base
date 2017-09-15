package com.xmniao.xmn.core.market.service.activity.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.RedisService;
import com.xmniao.xmn.core.catehome.response.HomeImageResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.live.service.LiveGiftsInfoService;
import com.xmniao.xmn.core.market.base.Page;
import com.xmniao.xmn.core.market.dao.*;
import com.xmniao.xmn.core.market.entity.activity.indiana.*;
import com.xmniao.xmn.core.market.entity.pay.ProductDetails;
import com.xmniao.xmn.core.market.entity.pay.ProductInfo;
import com.xmniao.xmn.core.market.exception.CustomException;
import com.xmniao.xmn.core.market.service.activity.IndianaService;
import com.xmniao.xmn.core.market.service.activity.OrderService;
import com.xmniao.xmn.core.market.service.product.ProductService;
import com.xmniao.xmn.core.seller.dao.BannerDao;
import com.xmniao.xmn.core.util.*;
import com.xmniao.xmn.core.verification.dao.UrsDao;
import com.xmniao.xmn.core.verification.entity.Urs;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by yang.qiang on 2017/2/21.
 */
@Service
public class IndianaServiceImpl implements IndianaService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    public final String INDIANA_INCR_KEY = "xmnservice:market:indiana:bout.incr:";

    @Autowired
    private Rule rule;
    @Autowired
    private FreshActivityIndianaDao freshActivityIndianaDao;
    @Autowired
    private ProductInfoDao productInfoDao;
    @Autowired
    private String fileUrl;
    @Autowired
    private FreshActivityIndianaBoutDao freshActivityIndianaBoutDao;
    @Autowired
    private FreshActivityIndianaDduonumDao freshActivityIndianaDduonumDao;
    @Autowired
    private UrsDao ursDao;
    @Autowired
    private LiveGiftsInfoService liveGiftsInfoService;
    @Autowired
    private BannerDao bannerDao;
    @Autowired
    private ProductDetailsDao productDetailsDao;
    @Autowired
    private PropertiesUtil propertiesUtil;
    @Autowired
    private OrderService orderService;
    @Autowired
    private BillFreshActivityDao billFreshActivityDao;
    @Autowired
    private RedisService redisService;
    @Autowired
    private FreshIndianaRobotDao freshIndianaRobotDao;
    @Autowired
    private ProductService productService;
    /**
     * 获取积分超市一元夺宝规则说明
     * @return
     */
    @Override
    public Rule getRule() {
//        System.out.println(rule.getActivityRule());
        return rule;
    }

    /**
     * 查询夺宝活动列表
     * @param page
     * @return
     */
    @Override
    public List<FreshActivityIndiana> getIndianas(Page page) {
        List<FreshActivityIndiana> indianaList = freshActivityIndianaDao.selectVaildActivity(page);
        for (FreshActivityIndiana indiana : indianaList) {

            // 加载商品信息
            ProductInfo productInfo = productInfoDao.selectByCodeId(indiana.getCodeid().intValue());
            productInfo.setBreviary(fileUrl + productInfo.getBreviary());
            indiana.setProduct(productInfo);
            // 加载当前期次信息
            FreshActivityIndianaBout currentBout = freshActivityIndianaBoutDao.selectByCurrentBout(indiana.getId());
            indiana.setCurrentBout(currentBout);
        }

        // 如果没有当前期次, 则不返回该夺宝活动
        Iterator<FreshActivityIndiana> iterator = indianaList.iterator();
        while (iterator.hasNext()){
            FreshActivityIndiana indiana = iterator.next();
            FreshActivityIndianaBout currentBout = indiana.getCurrentBout();
            if (currentBout == null ){  // 没有当前期次
                iterator.remove();
            }
        }
        return indianaList;
    }

    /**
     * 夺宝信息
     * @param activityId
     * @return
     */
    @Override
    public FreshActivityIndiana getIndiana(Integer activityId) {
        FreshActivityIndiana indiana = freshActivityIndianaDao.selectByPrimaryKey(activityId);
        indiana.setCurrentBout(freshActivityIndianaBoutDao.selectByCurrentBout(activityId));
        ProductInfo productInfo = productInfoDao.selectByCodeId(indiana.getCodeid().intValue());
        loadProductDetail(productInfo);

        indiana.setProduct(productInfo);
        if (indiana.getCurrentBout() == null) {
            indiana.setStatus(1);
        }
        return indiana;
    }

    /**
     * 加载商品 图文详情 banner图列表
     * @param productInfo
     */
    private void loadProductDetail(ProductInfo productInfo) {
        ProductDetails productDetail = productDetailsDao.selectByCodeId(productInfo.getCodeid());
        //图片集合
        List<String> bannerList = new ArrayList<>();
        if(StringUtils.isNotBlank(productDetail.getPic1()))
            bannerList.add(fileUrl+productDetail.getPic1());

        if(StringUtils.isNotBlank(productDetail.getPic2()))
            bannerList.add(fileUrl+productDetail.getPic2());

        if(StringUtils.isNotBlank(productDetail.getPic3()))
            bannerList.add(fileUrl+productDetail.getPic3());

        if(StringUtils.isNotBlank(productDetail.getPic4()))
            bannerList.add(fileUrl+productDetail.getPic4());

        if(StringUtils.isNotBlank(productDetail.getPic5()))
            bannerList.add(fileUrl+productDetail.getPic5());

        if(bannerList.size()==0){
            if(StringUtils.isNotBlank(productDetail.getImg1()))
                bannerList.add(fileUrl+productDetail.getImg1());

            if(StringUtils.isNotBlank(productDetail.getImg2()))
                bannerList.add(fileUrl+productDetail.getImg2());

            if(StringUtils.isNotBlank(productDetail.getImg3()))
                bannerList.add(fileUrl+productDetail.getImg3());

            if(StringUtils.isNotBlank(productDetail.getImg4()))
                bannerList.add(fileUrl+productDetail.getImg4());

            if(StringUtils.isNotBlank(productDetail.getImg5()))
                bannerList.add(fileUrl+productDetail.getImg5());

            if(StringUtils.isNotBlank(productDetail.getImg6()))
                bannerList.add(fileUrl+productDetail.getImg6());

            if(StringUtils.isNotBlank(productDetail.getImg7()))
                bannerList.add(fileUrl+productDetail.getImg7());

            if(StringUtils.isNotBlank(productDetail.getImg8()))
                bannerList.add(fileUrl+productDetail.getImg8());

            if(StringUtils.isNotBlank(productDetail.getImg9()))
                bannerList.add(fileUrl+productDetail.getImg9());

            if(StringUtils.isNotBlank(productDetail.getImg10()))
                bannerList.add(fileUrl+productDetail.getImg10());

            if(StringUtils.isNotBlank(productDetail.getImg11()))
                bannerList.add(fileUrl+productDetail.getImg11());

            if(StringUtils.isNotBlank(productDetail.getImg12()))
                bannerList.add(fileUrl+productDetail.getImg12());
        }

        productInfo.setBanner(bannerList);
        if(StringUtils.isNotBlank(productDetail.getHtml())){
            productInfo.setHtml(productDetail.getHtml());
        }else{
            if(StringUtils.isBlank(productDetail.getHtml()) && bannerList.size()!=0){
                StringBuilder sb= new StringBuilder();
                sb.append("<style type=\"text/css\">*{margin:0;padding:0;}img{width: 100% !important;height: auto !important;display:block;margin:auto;vertical-align: top;}</style>");
                for(String url : bannerList){
                    sb.append("<p><img  src=\""+url+"\"  /></p>");
                }
                productInfo.setHtml(sb.toString());

            }
        }
    }

    /**
     * 获取最近购买的夺宝记录
     * @param boutId
     * @param page
     * @return
     */
    @Override
    public List<FreshActivityIndianaDduonum> getRecentlyRecord(Long boutId, Page page) {
        List<FreshActivityIndianaDduonum> dduonumList = freshActivityIndianaDduonumDao.selectRecentlyRecord(boutId, page);
        for (FreshActivityIndianaDduonum dduonum : dduonumList) {

            try {
                dduonum.setGiveType(dduonum.getType()==1 ? 1:0 );
                dduonum.setWinnerRid(dduonum.getUid());
            } catch (Exception e) {
            }
            String createTime = dduonum.getCreateTime();
            dduonum.setCreateTime(createTime.substring(0,16).replace("-","."));
        }
        return dduonumList;
    }

    /**
     * 用户支付夺宝, 生成夺宝期号
     * @param urs
     * @param boutId
     * @param point
     */
    @Override
    public Integer payIndiana(Integer uid, Integer boutId, Integer point) throws Exception {
        // 查询用户信息, 期次, 夺宝活动
        Urs urs = ursDao.queryUrsByUid(uid);
        FreshActivityIndianaBout indianaBout = freshActivityIndianaBoutDao.selectByPrimaryKey(boutId);
        FreshActivityIndiana indiana = freshActivityIndianaDao.selectByPrimaryKey(indianaBout.getActivityId());

        // 校验是否剩余夺宝次数是否足够
        int residuePoint = indiana.getPoint() - indianaBout.getSaleNum(); // 该期夺宝的剩余次数
        long currentTime = System.currentTimeMillis();

        // 判断活动是否正在进行中
        if(indiana.getEndTime().getTime() < currentTime  ||
            indiana.getBeginTime().getTime() > currentTime ||
            indiana.getStatus() != 0){
            throw new CustomException("夺宝未开始,或已结束!", ResponseCode.INDIANA_ACTIVITY_END);
        }

        // 但剩余夺宝份数 不足时返回错误状态
        if (residuePoint < point) {     // 剩余份数 小于 购买份数
            if (residuePoint <= 0) {
                if (indiana.getBoutResidue() <= 0) {
                    throw new CustomException("夺宝活动已经结束, 请查看其他夺宝活动", ResponseCode.INDIANA_ACTIVITY_END);
                }
                throw new CustomException("该期夺宝已结束,请前往下一期夺宝活动", ResponseCode.INDIANA_BOUT_SALEOUT);
            }
            throw new CustomException("该期夺宝剩余次数不足,请调整份数重新夺宝", ResponseCode.INDIANA_LOW_STOCKS);
        }

//         校验账户余额是否足够
        BigDecimal orderAmount = indiana.getPointPrice().multiply(BigDecimal.valueOf(point));
        Map<String, Object> liveWalletBlance = liveGiftsInfoService.getLiveWalletBlance(uid.toString());
        Object zbalanceCoin = liveWalletBlance.get("zbalanceCoin");
        if (zbalanceCoin != null) {
            BigDecimal zbalanceCoin1 = (BigDecimal) zbalanceCoin;
            if (zbalanceCoin1.compareTo(orderAmount) <= 0) {
                throw new CustomException("可用鸟币不足",ResponseCode.INDIANA_COIN_NOT_ENOUGH);
            }
        }

        // 调用支付接口
        String orderNo = MakeOrderNum.getInstance().makeOrderNum();
        String key = "";
        try {
            key = propertiesUtil.getValue("payBirdKey", "conf_live.properties");
        } catch (IOException e1) {
            logger.error("uid:" + uid + "夺宝支付，获取支付key错误");
            e1.printStackTrace();
        }
        logger.info("鸟币支付的key:" + key);
        HashMap<String, String> payParam = new HashMap<>();
        payParam.put("uid", uid.toString());                 //用户id
        payParam.put("orderSn", orderNo);                    //订单号
        payParam.put("amount", orderAmount.toString());      //订单总额
        payParam.put("paymentType", "1000020");              //支付类型(鸟币支付)
        payParam.put("orderType", "2");                      //订单类型 2消费订单
        payParam.put("source", "013");                       //013 订单来源: 013一元夺宝
        payParam.put("subject", "一元夺宝*" + point);         //订单标题
        payParam.put("liveCoin", orderAmount.toString());    //鸟币
        payParam.put("sign", Signature.sign(payParam, key));//签名
        logger.info("一元夺宝 支付请求数据 :" + payParam);
        //请求支付接口
        String url = HttpConnectionUtil.getUrl(payParam, propertiesUtil.getValue("payBirdurl", "conf_live.properties"));
        logger.info("请求支付接口的拼装url：" + url);
        String resultStr = HttpConnectionUtil.doPost(url, "");// 请求支付接口
        logger.info("支付返回的resultStr:    " + resultStr);
        JSONObject json = JSONObject.parseObject(resultStr);
        logger.info("格式化请求支付接口返回json:" + json);
        Map<String, Object> resultMap = (Map<String, Object>) JSON.parse(json.toString());
        // 支付成功
        if (resultMap.get("state") != null && resultMap.get("state").equals("200")) {
            logger.info("一元夺宝支付成功");
            // 手动开启事务
            WebApplicationContext contextLoader = ContextLoader.getCurrentWebApplicationContext();
            DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
            definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
            definition.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
            DataSourceTransactionManager transactionManager = (DataSourceTransactionManager) contextLoader.getBean("transactionManager");
            TransactionStatus transactionStatus = transactionManager.getTransaction(definition);
            try {
                String toBuyTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date());
                String redisKey = INDIANA_INCR_KEY + indianaBout.getId();
                // 插入用户夺宝记录
                for (int i = 0; i < point; i++) {
                    Long increment = redisService.increment(redisKey);
                    FreshActivityIndianaDduonum freshActivityIndianaDduonum = new FreshActivityIndianaDduonum();
                    freshActivityIndianaDduonum.setId(indianaBout.getId().longValue() * 1000000L + increment);
                    freshActivityIndianaDduonum.setOrderNo(orderNo);
                    freshActivityIndianaDduonum.setCreateTime(toBuyTime);
                    freshActivityIndianaDduonum.setBoutId(indianaBout.getId());
                    freshActivityIndianaDduonum.setNname(urs.getNname());
                    freshActivityIndianaDduonum.setUid(urs.getUid());
                    freshActivityIndianaDduonum.setType(0);
                    freshActivityIndianaDduonum.setPhone(urs.getPhone());
                    freshActivityIndianaDduonumDao.insertSelective(freshActivityIndianaDduonum);
                }
                redisService.expireat(redisKey,new Date(indiana.getEndTime().getTime() + 10000000000L));

                // 更新夺宝活动对应期次 份数
                freshActivityIndianaBoutDao.updatePoint(boutId, point);
            } catch (Exception e) {
                logger.error("扣除鸟币成功,但创建夺宝购买记录失败!", e);
                // 手动回滚事务
                transactionManager.rollback(transactionStatus);
                throw new CustomException("购买失败,请稍后重试!");
            }
            // 提交事务
            transactionManager.commit(transactionStatus);

            // 判断是否夺宝期次已购完
            if ((indianaBout.getSaleNum() + point) >= indiana.getPoint()) {
                // 没有剩余期次, 直接结束掉夺宝活动
                if (indiana.getBoutResidue() <= 0) {
                    logger.info("活动夺宝"+indiana+"已卖完, 且并无剩余期次,更新夺宝活动状态");
                    FreshActivityIndiana record = new FreshActivityIndiana();
                    record.setStatus(3);
                    record.setId(indiana.getId());
                    freshActivityIndianaDao.updateByPrimaryKeySelective(record);
                }else {
                    // 异步加载下一期次夺宝
                    IndianaUpdateRunner runner = new IndianaUpdateRunner();
                    Thread thread = new Thread(runner);
                    thread.start();
                }
            }


            // 支付失败
        } else {
            logger.error("支付失败 支付系统响应数据 : "+resultStr);
            throw new CustomException("支付失败");
        }

        return point;

    }

    @Override
    public Map<String, Object> getUserWallet(String uid) throws Exception {
        return liveGiftsInfoService.getLiveWalletBlance(uid);
    }

    /**
     * 获取用户夺宝记录
     * @param uid
     * @param page
     * @return
     */
    @Override
    public List<IndianaRecord> userRecord(String uid, Page page) {
            //  分页
        List<IndianaRecord> recordList = freshActivityIndianaDduonumDao.selectByUser(uid,page);
        for (IndianaRecord record : recordList) {
            IndianaRecord queryRecord = freshActivityIndianaBoutDao.selectIndianaInfoByBout(record.getBoutId());
            BeanUtils.copyProperties(queryRecord,record,new String[]{
                "firstTime",
                "lastTIme",
                "total",
                "boutId",
            });

            // 查询是否有已退款的夺
            int refundCount = freshActivityIndianaDduonumDao.countRefund(uid,record.getBoutId());
            if (refundCount >0) {
                record.setRefunded(true);
            }


            // 如果获奖的用户类型是机器人, 查询绑定的真实用户id
            if (record.getGiveType() != null && record.getGiveType() == 1) {
                record.setWinnerRid(freshIndianaRobotDao.queryUidById(record.getWinnerUid()));
            }else {
                record.setWinnerRid(record.getWinnerUid());
            }

            // 判断用户是否中奖
            if (record.getStatus() != null && record.getStatus() ==1 ) {       // 夺宝活动是否已开奖
                if (record.getWinnerUid() != null && record.getWinnerUid().toString().equals(uid)){
                    record.setWin(true);
                    record.setOrder(billFreshActivityDao.selectByUidAndActivityId(record.getBoutId(),uid));
                }else {
                    record.setWin(false);
                }
            }



            record.setLastTIme(record.getLastTIme().substring(0,16).replace("-","."));
            record.setFirstTime(record.getFirstTime().substring(0,16).replace("-","."));
            record.setImage(fileUrl + record.getImage());
        }
        return recordList;
    }

    @Override
    public Map<String, Object> banner() {

        List<Map<Object,Object>> banners = new ArrayList<Map<Object,Object>>();
        Map<String,Object> resulMap =new HashMap<>();
        List<HomeImageResponse> list = new ArrayList<>();
        banners = bannerDao.findBidBannerByType(6);
        if(banners != null && banners.size() > 0){
            for(Map<Object,Object> banner : banners){
                try{
                    String objson = Base64.getFromBase64(banner.get("objJson").toString());
                    JSONArray jsonArr = JSON.parseArray(objson);
                    for(int i=0; i<jsonArr.size(); i++){
                        HomeImageResponse image = new HomeImageResponse();
                        image.setId(Integer.parseInt(banner.get("id").toString()));//图片ID
                        image.setSort(Integer.parseInt(banner.get("sort").toString()));//图片排序
                        image.setContent(JSON.parseObject(jsonArr.getString(i)).getString("content"));
                        image.setPicUrl(fileUrl+JSON.parseObject(jsonArr.getString(i)).getString("pic_url"));
                        image.setLogRequired(JSON.parseObject(jsonArr.getString(i)).getString("logRequired"));
                        image.setType(JSON.parseObject(jsonArr.getString(i)).getString("type"));
                        list.add(image);
                    }
                }catch(Exception e){
                    e.printStackTrace();
                    logger.info("轮播图解码json串异常");
                }
            }
            resulMap.put("dataList", list);
        }
        return resulMap;
    }

    /**
     * 获取用户一期次的夺宝号码
     * @param uid
     * @param boutId
     * @return
     */
    @Override
    public List<String> getIndianaNumbers(String uid, Integer boutId) {
        return freshActivityIndianaDduonumDao.selectIndianaNumbers(uid,boutId);
    }

    /**
     * 根据期次ID查询一个夺宝活动
     * @param boutId
     * @return
     */
    @Override
    public FreshActivityIndiana getIndianaByBoutId(Integer boutId) {
        return freshActivityIndianaDao.selectByBoutId(boutId);
    }

    /**
     * 统计用户一期次 的参与次数
     * @param uid
     * @param boutId
     */
    @Override
    public Integer countUserBoutJoins(String uid, Integer boutId) {
        return freshActivityIndianaDduonumDao.countUserBoutJoins(uid,boutId);
    }

    @Override
    public FreshActivityIndianaBout queryBout(Integer boutId) {
        return freshActivityIndianaBoutDao.selectByPrimaryKey(boutId);
    }


    /**
     * 更新已经卖完的夺宝期次, 创建新的夺宝期次
     */
    class IndianaUpdateRunner implements Runnable{
        private FreshActivityIndiana indiana;
        private FreshActivityIndianaBout indianaBout;

        public IndianaUpdateRunner() {
            super();
        }

        public IndianaUpdateRunner(FreshActivityIndiana indiana, FreshActivityIndianaBout indianaBout) {
            this.indiana = indiana;
            this.indianaBout = indianaBout;
        }

        @Override
        public void run() {
            logger.info("夺宝期次可购买份数已销完,准备更新夺宝期次!");
            List<FreshActivityIndianaBout> boutList = freshActivityIndianaBoutDao.selectSaleoutBout();
            for (FreshActivityIndianaBout bout : boutList) {
                // 插入新一期夺宝
                FreshActivityIndianaBout newBout = new FreshActivityIndianaBout();
                newBout.setActivityId(bout.getActivityId());
                newBout.setEndTime(bout.getEndTime());
                newBout.setCodeid(bout.getCodeid());
                newBout.setBoutTh(bout.getBoutTh()+1);
                newBout.setStatus(0);
                newBout.setCreateTime(new Date());
                newBout.setUpdateTime(new Date());
                freshActivityIndianaBoutDao.insertSelective(newBout);
                logger.info("创建了新一期夺宝! 夺宝id : " + newBout.getId());
                // 更新夺宝活动,夺宝期次
                freshActivityIndianaBoutDao.updateIndianaAndOldBout(bout);

            }
        }
    }
}
