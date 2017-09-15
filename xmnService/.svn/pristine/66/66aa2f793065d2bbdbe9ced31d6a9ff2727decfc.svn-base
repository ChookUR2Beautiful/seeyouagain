package com.xmniao.xmn.core.market.service.activity.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.catehome.response.HomeImageResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.entity.LiverInfo;
import com.xmniao.xmn.core.live.service.LiveGiftsInfoService;
import com.xmniao.xmn.core.market.common.MarketConsts;
import com.xmniao.xmn.core.market.dao.*;
import com.xmniao.xmn.core.market.entity.activity.BillFreshActivity;
import com.xmniao.xmn.core.market.entity.activity.FreshActivityAuction;
import com.xmniao.xmn.core.market.entity.activity.FreshActivityAuctionBidding;
import com.xmniao.xmn.core.market.entity.activity.FreshActivityAuctionRecord;
import com.xmniao.xmn.core.market.entity.pay.ProductDetails;
import com.xmniao.xmn.core.market.entity.pay.ProductInfo;
import com.xmniao.xmn.core.market.exception.CustomException;
import com.xmniao.xmn.core.market.service.activity.AuctionService;
import com.xmniao.xmn.core.market.service.activity.IndianaService;
import com.xmniao.xmn.core.seller.dao.BannerDao;
import com.xmniao.xmn.core.util.*;
import com.xmniao.xmn.core.verification.dao.UrsDao;
import com.xmniao.xmn.core.verification.entity.Urs;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@Service
public class AuctionServiceImpl implements AuctionService {
    
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private SessionTokenService sessionTokenService;
    
    @Autowired
    private BillFreshActivityDao billFreshActivityDao ;
    
    @Autowired
    private LiveUserDao liverUserDao;
    
    @Autowired
    private LiveGiftsInfoService liveGiftsInfoService;
    
    @Autowired
    private IndianaService indianaService;
    
    @Autowired
    private ThriftUtil thriftUtil;
    
    @Autowired
    private ProductInfoDao productInfoDao;
    
    @Autowired
    private FreshActivityAuctionRecordDao freshActivityAuctionRecordDao;
    
    @Autowired
    private UrsDao ursDao;
    
    @Autowired
    private BannerDao bannerDao;
    
    @Autowired
    private String fileUrl;
    
    @Autowired
    private FreshActivityAuctionDao freshActivityAuctionDao;
    
    @Autowired
    private FreshActivityAuctionBiddingDao freshActivityAuctionBiddingDao;
    
    @Autowired
    private ProductDetailsDao productDetailsDao;

    @Autowired
    private PropertiesUtil propertiesUtil;

    @Override
    public String text(Integer type) throws IOException {
        String text="";
        if(type==0){
           text = propertiesUtil.getValue("auction.text", "conf_integral_pay.properties");
        }else{
           text = propertiesUtil.getValue("auction.text.detail", "conf_integral_pay.properties");
        }
        return text;
    }
    
    /**
     * 竞拍活动列表
     * @param freshActivityAuction
     * @return
     */
    @Override
    public Map<String, Object> list(FreshActivityAuction freshActivityAuction) {
        List<FreshActivityAuction> list = new ArrayList<>();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg", "请求成功");
        resultMap.put("code", ResponseCode.SUCCESS);
        int rows = 0;
        // 分页
        list = freshActivityAuctionDao.findByPage(freshActivityAuction.getPageOffset(),freshActivityAuction.getPageSize());
        for(FreshActivityAuction vo :list){
            ProductInfo productInfo = productInfoDao.selectByCodeId(vo.getCodeid().intValue());
            //设置缩略图
            vo.setBreviary(fileUrl+productInfo.getBreviary());
            //当前最高价格
            BigDecimal maxPrise = freshActivityAuctionBiddingDao.selectMaxByActivity(vo.getId());
            vo.setNowPrice(maxPrise);
        }
        rows = freshActivityAuctionDao.findByPageCount();
        resultMap.put("dataList", list);
        resultMap.put("rows", rows);
        
        return resultMap;
    }
    
    /**
     * 竞拍详情
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> detail(Integer id,String uid) {
        FreshActivityAuction freshActivityAuction = freshActivityAuctionDao.selectByPrimaryKey(id);
        ProductDetails productDetail = productDetailsDao.selectByCodeId(freshActivityAuction.getCodeid());
        freshActivityAuction.setHtml(productDetail.getHtml());
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
        
        
        if(StringUtils.isNotBlank(productDetail.getHtml())){
            freshActivityAuction.setHtml(productDetail.getHtml());
        }else{
            if(StringUtils.isBlank(productDetail.getHtml()) && bannerList.size()!=0){
                StringBuilder sb= new StringBuilder();
                sb.append("<style type=\"text/css\">*{margin:0;padding:0;}img{width: 100% !important;height: auto !important;display:block;margin:auto;vertical-align: top;}</style>");
                for(String url : bannerList){
                    sb.append("<p><img  src=\""+url+"\"  /></p>");
                }
                freshActivityAuction.setHtml(sb.toString());
            }
        }
        
        BigDecimal maxPrise = freshActivityAuctionBiddingDao.selectMaxByActivity(id);
        freshActivityAuction.setBannerList(bannerList);
        BigDecimal myPrice = BigDecimal.ZERO;
        if(StringUtils.isNotBlank(uid)){
            myPrice = freshActivityAuctionBiddingDao.selectMaxByUid(id, Integer.valueOf(uid));
        }
        Map<String, Object> resultMap = new HashMap<>();
        freshActivityAuction.setMyPrice(myPrice);
        freshActivityAuction.setNowPrice(maxPrise);
        resultMap.put("dataList", freshActivityAuction);
//        resultMap.put("nowPrice", maxPrise);
//        resultMap.put("myPrice", myPrice);
        return resultMap;
    }
    
    /**
     * 竞拍出价
     * @param freshActivityAuctionBidding
     * @return
     */
    @Override
    public Map<String, Object> bid(FreshActivityAuctionBidding freshActivityAuctionBidding) throws CustomException{
        Map<String, Object> resultMap = new HashMap<>();
        Urs urs  = ursDao.queryUrsByUid(freshActivityAuctionBidding.getUid());
        if(urs==null){
            resultMap.put("msg", "查询用户信息失败");
            resultMap.put("code", ResponseCode.FAILURE);  
            return resultMap;
        }
        //加锁
        String identify =  sessionTokenService.lock(MarketConsts.BIDDING_HEADER+freshActivityAuctionBidding.getActivityId(), 20000);
        resultMap  =  bidding(resultMap,urs,freshActivityAuctionBidding ,identify);
        //删除锁
        sessionTokenService.unlock(MarketConsts.BIDDING_HEADER+freshActivityAuctionBidding.getActivityId(),identify);
        return resultMap;
        
        
    }
    
    
    public   Map<String, Object>  bidding(Map<String, Object> resultMap ,Urs urs,FreshActivityAuctionBidding freshActivityAuctionBidding,String identify){
        if(StringUtils.isNotBlank(identify)){
            //鸟币余额
            BigDecimal zbalanceCoin = BigDecimal.ZERO;
            //鸟豆
            BigDecimal commision = BigDecimal.ZERO;
            try {
                Map<String,Object> userWallet =  (Map<String,Object>) liveGiftsInfoService.getLiveWalletBlance(freshActivityAuctionBidding.getUid().toString());
                
                commision =new  BigDecimal( userWallet.get("commision")==null?"0":userWallet.get("commision").toString());
                zbalanceCoin = new BigDecimal( userWallet.get("zbalanceCoin")==null?"0":userWallet.get("zbalanceCoin").toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            //查询当前竞拍的活动详情
            FreshActivityAuction freshActivityAuction = freshActivityAuctionDao.selectByPrimaryKey(freshActivityAuctionBidding.getActivityId());
            if(freshActivityAuction ==null || freshActivityAuction.getState()!=0){
                resultMap.put("msg", "当前竞拍已结束，出价失败");
                resultMap.put("code", ResponseCode.FAILURE);
                return resultMap;
            }
            //判断时间
            if(setBidTime(freshActivityAuction)){
                resultMap.put("resetTime", true); 
            };
            
            //查询当前最高出价
            BigDecimal max = freshActivityAuctionBiddingDao.selectMaxByActivity(freshActivityAuctionBidding.getActivityId());
            if(max!=null){
                freshActivityAuctionBidding.setPhone(urs.getPhone());
                freshActivityAuctionBidding.setCreateTime(new Date());
                freshActivityAuctionBidding.setUserName(urs.getNname()==null?urs.getUname():urs.getNname());
                //出价大于当前最高价格
                if(max.compareTo(freshActivityAuctionBidding.getRisePrice())<0 ){
                    try {
                        if(bidZbalanceCoin(zbalanceCoin,commision,freshActivityAuctionBidding.getActivityId(),freshActivityAuctionBidding.getUid())==1){
                            resultMap.put("msg", "您账户中鸟币余额不足，充值鸟豆后进行打赏可获得鸟币。");
                            resultMap.put("code", ResponseCode.ZBALANCECOIN_EEROR);
                            return resultMap;
                        }
                        if(bidZbalanceCoin(zbalanceCoin,commision,freshActivityAuctionBidding.getActivityId(),freshActivityAuctionBidding.getUid())==2){
                            resultMap.put("msg", "您账户中鸟币余额不足，打赏鸟豆后可获得鸟币。");
                            resultMap.put("code", ResponseCode.COMMISION_ERROR);
                            return resultMap;
                        }
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    
                    //插入竞拍记录
                    if(!insertRecord(freshActivityAuctionBidding)){
                        resultMap.put("msg", "扣除保证金出错！！");
                        resultMap.put("code", ResponseCode.FAILURE);
                        return resultMap; 
                    };
                    freshActivityAuctionBiddingDao.insert(freshActivityAuctionBidding);
                    resultMap.put("msg", "出价成功，请耐心等待竞拍结束");
                    resultMap.put("code", ResponseCode.SUCCESS);
                    return resultMap;
                }
                //出价小于当前最高价格
                else if(max.compareTo(freshActivityAuctionBidding.getRisePrice())>=0){
                    resultMap.put("msg", "当前竞拍价有变化，出价失败,请重新出价");
                    resultMap.put("code", ResponseCode.FAILURE);
                    return resultMap;
                }
            }
            //正常情况且没有人出价格
            try {
                if(bidZbalanceCoin(zbalanceCoin,commision,freshActivityAuctionBidding.getActivityId(),freshActivityAuctionBidding.getUid())==1){
                    resultMap.put("msg", "您账户中鸟币余额不足，充值鸟豆后进行打赏可获得鸟币。");
                    resultMap.put("code", ResponseCode.ZBALANCECOIN_EEROR);
                    return resultMap;
                }
                if(bidZbalanceCoin(zbalanceCoin,commision,freshActivityAuctionBidding.getActivityId(),freshActivityAuctionBidding.getUid())==2){
                    resultMap.put("msg", "您账户中鸟币余额不足，打赏鸟豆后可获得鸟币。");
                    resultMap.put("code", ResponseCode.COMMISION_ERROR);
                    return resultMap;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            freshActivityAuctionBidding.setPhone(urs.getPhone());
            freshActivityAuctionBidding.setUserName(urs.getNname()==null?urs.getUname():urs.getNname());
            freshActivityAuctionBidding.setCreateTime(new Date());
            //插入竞拍记录
            if(!insertRecord(freshActivityAuctionBidding)){
                resultMap.put("msg", "扣除保证金出错！！");
                resultMap.put("code", ResponseCode.FAILURE);
                return resultMap; 
            };
            freshActivityAuctionBiddingDao.insert(freshActivityAuctionBidding);
            resultMap.put("msg", "出价成功，请耐心等待竞拍结束");
            resultMap.put("code", ResponseCode.SUCCESS);
            return resultMap;
        }else{
            resultMap.put("msg", "当前竞拍价有变化，出价失败,请重新出价");
            resultMap.put("code", ResponseCode.FAILURE);
            return resultMap;
        }
        
    }
    /**
     * 插入竞拍记录,调用支付服务扣除鸟币
     * @param vo
     * @throws IOException 
     */
    public boolean insertRecord(FreshActivityAuctionBidding vo) {
        FreshActivityAuctionRecord record =  freshActivityAuctionRecordDao.selectByUidAndId(vo.getActivityId(), vo.getUid());
        if(record!=null){
            record.setRisedNum(record.getRisedNum()+1);
            record.setRisedPrice(vo.getRisePrice());
            freshActivityAuctionRecordDao.updateByPrimaryKey(record);
        }else{
            FreshActivityAuctionRecord auctionRecord = new FreshActivityAuctionRecord();
            auctionRecord.setActivityId(vo.getActivityId());
            auctionRecord.setCreateTime(new Date());
            auctionRecord.setPhone(vo.getPhone());
            auctionRecord.setRisedNum(1);
            auctionRecord.setRisedPrice(vo.getRisePrice());
            auctionRecord.setState(0);
            auctionRecord.setUid(vo.getUid());
            auctionRecord.setDepositState(1);//已交保证金
            auctionRecord.setDepositPrice(new BigDecimal(100L));//保证金金额
            Urs urs = ursDao.queryUrsByUid(vo.getUid());
            auctionRecord.setUserName(urs.getNname()==null?urs.getUname():urs.getNname());
            //保证金订单号
            String bidOrder = MakeOrderNum.getInstance().makeOrderNum();
            auctionRecord.setDepositOrder(bidOrder);
            
            String key ="";
            try {
                key = propertiesUtil.getValue("payBirdKey", "conf_live.properties");
            } catch (IOException e1) {
                log.error("uid:"+vo.getUid()+"扣取竞拍保证金，获取支付key错误");
                e1.printStackTrace();
            }
            log.info("鸟币支付的key:"+key);
            
            //拼装参数
            String bidPrice ="100.00";
            try {
                bidPrice = propertiesUtil.getValue("market.auction.price", "conf_config.properties");
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            Map<String, String> paramMap =new HashMap<>();
            Map<String, Object> resultMap =new HashMap<>();
            paramMap.put("uid", vo.getUid()+"");// 用户id
            paramMap.put("orderSn", bidOrder);// 竞拍保证金订单号
            paramMap.put("amount", bidPrice);// 支付金额
            paramMap.put("paymentType", 1000020+"");// 支付类型
            paramMap.put("orderType", "2");// 订单类型（1:充值订单 2:消费订单）
            paramMap.put("source", "012");// 订单来源
            paramMap.put("subject", "竞拍订单保证金");// 竞拍订单保证金
            paramMap.put("liveCoin", bidPrice);//鸟币支付金额
            
            log.info("竞拍保证金支付要加密签名的字段："+paramMap.toString());
            paramMap.put("sign", Signature.sign(paramMap, key));// 签名
            
            //请求支付接口
            String url = "";
            try {
                url = HttpConnectionUtil.getUrl(paramMap, propertiesUtil.getValue("payBirdurl", "conf_live.properties"));
            } catch (IOException e) {
                log.error("uid:"+vo.getUid()+"扣取竞拍保证金，获取支付url错误");
                e.printStackTrace();
            }
            log.info("访问支付接口的拼装url：    "+url);
            String resultStr = HttpConnectionUtil.doPost(url, "");// 请求支付接口
            log.info("请求支付接口返回的resultStr:    "+resultStr);
            if (StringUtils.isNotEmpty(resultStr)) {
                JSONObject json = JSONObject.parseObject(resultStr);
                log.info("格式化请求支付接口返回json:"+json);
                resultMap = (Map<String, Object>) JSON.parse(json.toString());
                if(resultMap.get("state")!=null && !resultMap.get("state").equals("200")){
                    return false;
                }
                if(json.containsKey("result")){
                    Object data= json.get("result");
                    resultMap.clear();
                    resultMap.put("data", data);
                }
                log.info("返回客户端数据:"+resultMap);
                
            }
            
            freshActivityAuctionRecordDao.insert(auctionRecord);
        }
        return true;
        
    }
    /**
     * 竞拍排名接口
     */
    @Override
    public Map<String, Object> bidList(String uid ,Integer activityId) {
        Map<String, Object> resultMap = new HashMap<>();
        BigDecimal myPrice = BigDecimal.ZERO;
        if(StringUtils.isNotBlank(uid)){
            myPrice = freshActivityAuctionBiddingDao.selectMaxByUid(activityId, Integer.valueOf(uid));
        }
        List<FreshActivityAuctionBidding> list = freshActivityAuctionBiddingDao.selectListByActivity(activityId);
        int count = freshActivityAuctionBiddingDao.selectCountByActivity(activityId);
        for(FreshActivityAuctionBidding vo : list){
            Urs urs =ursDao.queryUrsByUid(Integer.valueOf(vo.getUid()));
            LiverInfo liverInfo  = liverUserDao.queryLiverByUid(vo.getUid().longValue());
            vo.setUtype(liverInfo.getUtype().intValue());//'直播用户类型： 1 主播 2 普通用户',
            vo.setName(urs.getNname()==null?urs.getPhone():urs.getNname());
        }
        resultMap.put("myPrice", myPrice);
        resultMap.put("count", count);
        resultMap.put("dataList", list);
        return resultMap;
    }
    
    /**
     * 判断时间是否少于5分钟，并设置为5分钟
     * @param freshActivityAuction
     * @return
     */
    
    public boolean setBidTime(FreshActivityAuction freshActivityAuction){
        boolean flag = false;
        Date end_time = freshActivityAuction.getEndTime();
        Date now_time= new Date();
        long cha = end_time.getTime()-now_time.getTime();
        if(cha<300000){
            log.info("活动id："+freshActivityAuction.getId()+"时间少于5分钟");
            flag=true;
            freshActivityAuction.setEndTime(new Date(end_time.getTime()+(300000-cha)));//延时，增加到5分钟
            freshActivityAuctionDao.updateByPrimaryKeySelective(freshActivityAuction);
        }
        return  flag;
     
    }
    
    /**
     * 扣减竞拍100鸟币
     * @param uid
     * @return
     * @throws Exception
     */
    public int bidZbalanceCoin(BigDecimal zbalanceCoin,BigDecimal commision,Integer activityId,Integer uid) throws Exception{
        FreshActivityAuctionRecord record =  freshActivityAuctionRecordDao.selectByUidAndId(activityId, uid);
        //已经交了保证金100鸟币
        if(record!=null){
            return 0;
        }
        //鸟币足够
        if(zbalanceCoin.subtract(BigDecimal.valueOf(100L)).compareTo(BigDecimal.ZERO)>=0){
            return 0;
        };
        //鸟豆不足
        if(commision.compareTo(BigDecimal.valueOf(100L))<=0){
            return 1;
        }
        return 2;
    }

    @Override
    public Map<String, Object> banner() {
        
        List<Map<Object,Object>> banners = new ArrayList<Map<Object,Object>>();
        Map<String,Object> resulMap =new HashMap<>();
        List<HomeImageResponse> list = new ArrayList<>();
        banners = bannerDao.findBidBanner();
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
                    log.info("轮播图解码json串异常");
                }
            }
            resulMap.put("dataList", list);
        }
        return resulMap;
    }

    
    /**
     * 竞拍记录
     */
    @Override
    public Map<String, Object> record(FreshActivityAuctionRecord freshActivityAuctionRecord) {
        List<FreshActivityAuctionRecord> list = freshActivityAuctionRecordDao.selectByPage(freshActivityAuctionRecord.getUid(), freshActivityAuctionRecord.getPageOffset(), freshActivityAuctionRecord.getPageSize());
        if(list!=null && list.size()>=0){
            for(FreshActivityAuctionRecord vo :list){
                
                //查询当前最高出价
                BigDecimal max = freshActivityAuctionBiddingDao.selectMaxByActivity(vo.getActivityId()); 
                //查询当前竞拍的活动详情
                FreshActivityAuction freshActivityAuction = freshActivityAuctionDao.selectByPrimaryKey(vo.getActivityId());
                vo.setNowPrice(max);
                vo.setName(freshActivityAuction.getTitle());
                ProductInfo productInfo = productInfoDao.selectByCodeId(freshActivityAuction.getCodeid().intValue());
                vo.setPicUrl(fileUrl+productInfo.getBreviary());
                vo.setEndTime(freshActivityAuction.getEndTime());
                if(vo.getState()==0){
                   vo.setStateMsg("竞拍中"); 
                }else if(vo.getState()==1){
                   vo.setStateMsg("竞拍成功"); 
                }else if(vo.getState()==2){
                   vo.setStateMsg("不成立：出价人次低于最低人次"); 
                }else if(vo.getState()==3){
                    vo.setStateMsg("失败原因：出价低于最高价"); 
                 }else if(vo.getState()==4){
                    vo.setStateMsg("已支付尾款"); 
                 }
                
            }
        }
        int count = freshActivityAuctionRecordDao.selectByPageCount(freshActivityAuctionRecord.getUid());
        
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("dataList", list);
        resultMap.put("count", count);
        return resultMap;
    }

    /**
     * 支付尾款
     */
    @Override
    public Map<String, Object> pay(BillFreshActivity billFreshActivity) throws CustomException{
        String orderNo = billFreshActivity.getId();
        BillFreshActivity vo = billFreshActivityDao.selectByPrimaryKey(orderNo);
        Map<String, Object> resultMap =new HashMap<>();
        if(vo.getState()!=null && vo.getState()==1){
            //拼装参数
            Map<String, String> paramMap =new HashMap<>();
            
            paramMap.put("uid", billFreshActivity.getUserId());// 用户id
            paramMap.put("orderSn", orderNo);// 竞拍尾款订单号
            paramMap.put("amount", vo.getAuctionBalance().toString());// 支付金额
            paramMap.put("paymentType", 1000020+"");// 支付类型
            paramMap.put("orderType", "2");// 订单类型（1:充值订单 2:消费订单）
            paramMap.put("source", "012");// 订单来源
            paramMap.put("subject", "竞拍订单支付尾款");// 竞拍订单支付尾款
            paramMap.put("liveCoin", vo.getAuctionBalance().toString());//鸟币支付金额
            
            log.info("竞拍保证金支付要加密签名的字段："+paramMap.toString());
            String key ="";
            try {
                key = propertiesUtil.getValue("payBirdKey", "conf_live.properties");
            } catch (IOException e1) {
                log.error("uid:"+vo.getUserId()+"支付尾款，获取支付key错误");
                e1.printStackTrace();
            }
            log.info("鸟币支付的key:"+key);
            paramMap.put("sign", Signature.sign(paramMap, key));// 签名
            
            //请求支付接口
            String url = "";
            try {
                url = HttpConnectionUtil.getUrl(paramMap, propertiesUtil.getValue("payBirdurl", "conf_live.properties"));
            } catch (IOException e) {
                log.error("uid:"+vo.getUserId()+"支付尾款，获取支付url错误");
                e.printStackTrace();
            }
            log.info("访问支付接口的拼装url：    "+url);
            String resultStr = HttpConnectionUtil.doPost(url, "");// 请求支付接口
            log.info("请求支付接口返回的resultStr:    "+resultStr);
            if (StringUtils.isNotEmpty(resultStr)) {
                JSONObject json = JSONObject.parseObject(resultStr);
                log.info("格式化请求支付接口返回json:"+json);
                resultMap = (Map<String, Object>) JSON.parse(json.toString());
                if(json.containsKey("result")){
                    Object data= json.get("result");
                    resultMap.clear();
                    resultMap.put("data", data);
                }
                log.info("返回客户端数据:"+resultMap);
                
            }
        }else{
            resultMap.put("msg", "支付失败！！");
            resultMap.put("code", ResponseCode.FAILURE); 
            throw new CustomException("支付失败！！");
        }
        
        return resultMap;
    }

    /**
     * 查询保证金金额
     * @return
     */
    @Override
    public BigDecimal queryAuctionPirce() throws IOException {
        Properties properties = new Properties();
        ClassPathResource resource = new ClassPathResource("/conf_config.properties");
        properties.load(resource.getInputStream());
        String price = properties.getProperty("market.auction.price");
        return new BigDecimal(price);
    }


}
