package com.xmn.saas.service.bill.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.xmn.saas.base.GlobalConfig;
import com.xmn.saas.base.Response;
import com.xmn.saas.base.ResponseCode;
import com.xmn.saas.base.ThriftBuilder;
import com.xmn.saas.base.thrift.common.PageList;
import com.xmn.saas.base.thrift.common.ResponseData;
import com.xmn.saas.base.thrift.common.ResponseList;
import com.xmn.saas.base.thrift.common.ResponseSubList;
import com.xmn.saas.base.thrift.common.SubList;
import com.xmn.saas.dao.bill.BillDao;
import com.xmn.saas.dao.bill.CouponDetailDao;
import com.xmn.saas.dao.bill.CouponRelationDao;
import com.xmn.saas.dao.bill.LivePayOrderDao;
import com.xmn.saas.dao.bill.SellerPackageDao;
import com.xmn.saas.dao.bill.SellerPackageGrantDao;
import com.xmn.saas.dao.bill.SellerPackageOrderDao;
import com.xmn.saas.dao.sellercard.DebitcardSellerDao;
import com.xmn.saas.dao.wallet.SellerDao;
import com.xmn.saas.entity.bill.Bagin;
import com.xmn.saas.entity.bill.Bill;
import com.xmn.saas.entity.bill.BillList;
import com.xmn.saas.entity.bill.BillRecord;
import com.xmn.saas.entity.bill.Coupon;
import com.xmn.saas.entity.bill.CouponDetail;
import com.xmn.saas.entity.bill.CouponRelation;
import com.xmn.saas.entity.bill.LivePayOrder;
import com.xmn.saas.entity.bill.SellerPackage;
import com.xmn.saas.entity.bill.SellerPackageGrant;
import com.xmn.saas.entity.bill.SellerPackageOrder;
import com.xmn.saas.entity.common.SellerAccount;
import com.xmn.saas.entity.sellercard.DebitcardSeller;
import com.xmn.saas.entity.wallet.Seller;
import com.xmn.saas.service.base.OrderService;
import com.xmn.saas.service.base.RedisService;
import com.xmn.saas.service.base.SellerVerifyOrderService;
import com.xmn.saas.service.base.UserService;
import com.xmn.saas.service.base.ValueCardService;
import com.xmn.saas.service.base.XmniaoWalletService;
import com.xmn.saas.service.bill.BillService;
import com.xmn.saas.service.redpacket.RedpacketService;
import com.xmn.saas.utils.CalendarUtil;

@Service
public class BillServiceImpl implements BillService {

    private final Logger log = LoggerFactory.getLogger(BillServiceImpl.class);

    @Autowired
    private GlobalConfig globalConfig;
    
    @Autowired
    private SellerPackageOrderDao sellerPackageOrderDao;
    @Autowired
    private SellerPackageDao sellerPackageDao;
    @Autowired
    private DebitcardSellerDao debitcardSellerDao;
    
    @Autowired
    private SellerPackageGrantDao sellerPackageGrantDao;

    @Autowired
    private BillDao billDao;
    
    @Autowired
    private RedpacketService redpacketService;
    
    @Autowired
    private RedisService redisService;
    
    @Autowired
    private CouponDetailDao couponDetailDao;
    
    @Autowired
    private SellerDao sellerDao;
    
    @Autowired
    private CouponRelationDao  couponRelationDao;
    
    @Autowired
    private LivePayOrderDao livePayOrderDao;
 
    
    
    /**
     * 获取钱包的分账记录列表
     */
    @Override
    public ResponseList getXmnWalletLedgerList(Map<String, String> map) {
        ResponseList responseList = null;
        try {
            XmniaoWalletService.Client client =
                    ThriftBuilder.build(globalConfig.getThriftPayHost(),
                            Integer.parseInt(globalConfig.getThriftPayPort()),
                            "XmniaoWalletService", XmniaoWalletService.Client.class);

            ThriftBuilder.open();
            responseList = client.getXmnWalletLedgerList(map);

        } catch (Exception e) {
            log.error("调用支付系统接口获取钱包的分账记录列表异常！", e);

        } finally {
            ThriftBuilder.close();
        }
        return responseList;

    }
    /**
     * 获取商户历史支出列表
     */
    @Override
    public ResponseList getSellerExpenseList(Map<String, String> map) {
        ResponseList responseList = null;
        try {
            XmniaoWalletService.Client client =
                    ThriftBuilder.build(globalConfig.getThriftPayHost(),
                            Integer.parseInt(globalConfig.getThriftPayPort()),
                            "XmniaoWalletService", XmniaoWalletService.Client.class);

            ThriftBuilder.open();
            responseList = client.getSellerExpenseList(map);

        } catch (Exception e) {
            log.error("调用支付系统接口获取钱包的分账记录列表异常！", e);

        } finally {
            ThriftBuilder.close();
        }
        return responseList;

    }
    
    /**
     * 账单列表，根据条件（时间、营收、收益、支出）筛选
     */
    @Override
    public Map<Object, Object> list(Map<String, String> map) {
        log.info("访问getXmnWalletLedgerList,【请求参数】：" +map);
        Map<Object, Object> resultMap = new HashMap<>();
        // 储值卡充值列表
        if (map.get("searchType") != null && map.get("searchType").equals("4")) {
            map.put("rtype", "42");
            ResponseList responseList = this.getSellerRechargeLedgerRecord(map);
            if (responseList.getDataInfo() != null && responseList.getDataInfo().getState() == 0) {
                Map<String, String> dataInfo = responseList.getDataInfo().getResultMap();
                resultMap.put("code", ResponseCode.SUCCESS);
                resultMap.put("msg", "获取数据成功");
                resultMap.put("totalAmount", dataInfo.get("totalAmount"));// 总分账金额
                resultMap.put("count", dataInfo.get("count"));// 总记录数
                resultMap.put("pageCount", dataInfo.get("pageCount"));// 总页数
                resultMap.put("type",map.get("type"));
                
                List<ResponseSubList> subList = responseList.getDataList();
                if(subList!=null && subList.size()>0){
                    for(ResponseSubList vo : subList){
                        List<Map<String, String>> mapList =  vo.getSubList();
                        if(mapList!=null && mapList.size()>0){
                            for(Map<String, String> mapVO : mapList){
                                String orderNo = mapVO.get("orderNo");
                                LivePayOrder livePayOrder = livePayOrderDao.selectByOrderNo(orderNo);
                                Integer uid = livePayOrder.getUid();
                                Map<String,String> userMap= new HashMap<>();
                                userMap.put("uid", uid+"");
                                ResponseData user = this.getUserMsg(userMap);
                                if(user.getState()==0){
                                    String genussellerid = user.getResultMap().get("genussellerid");//所属商家id
                                    //判断是否本店会员
                                    if(StringUtils.isNotBlank(genussellerid) && map.get("uId").equals(genussellerid)){
                                        mapVO.put("isVip", "0");//本店会员
                                    }else{
                                        mapVO.put("isVip", "1");//非本店会员
                                    }
                                    mapVO.put("avatar", user.getResultMap().get("avatar"));
                                    mapVO.put("nname", user.getResultMap().get("nname")==null?user.getResultMap().get("phone"):user.getResultMap().get("nname"));
                                    mapVO.put("payment", livePayOrder.getPayment().toString());
                                    mapVO.put("percent", livePayOrder.getEntrySelleragio()==null?"0.7":livePayOrder.getEntrySelleragio().toString());
                                    
                                }
                            }
                        }
                        
                }
                    
                }
                resultMap.put("dataList", subList);// 数据详情列表

            } else {
                resultMap.put("code", ResponseCode.FAILURE);
                resultMap.put("msg", "获取数据失败");
            }
        }
        
        
        // 收益列表
        if (map.get("searchType") != null && map.get("searchType").equals("2")) {
            ResponseList responseList = this.getXmnWalletLedgerList(map);
            if (responseList.getDataInfo() != null && responseList.getDataInfo().getState() == 0) {
                Map<String, String> dataInfo = responseList.getDataInfo().getResultMap();
                resultMap.put("code", ResponseCode.SUCCESS);
                resultMap.put("msg", "获取数据成功");
                resultMap.put("totalAmount", dataInfo.get("totalAmount"));// 总分账金额
                resultMap.put("count", dataInfo.get("count"));// 总记录数
                resultMap.put("pageCount", dataInfo.get("pageCount"));// 总页数
                resultMap.put("type",map.get("type"));
                resultMap.put("dataList", responseList.getDataList());// 数据详情列表

            } else {
                resultMap.put("code", ResponseCode.FAILURE);
                resultMap.put("msg", "获取数据失败");
            }
        }
        // 支出列表
        if (map.get("searchType") != null && map.get("searchType").equals("3")) {
            ResponseList sellerExpenseList = this.getSellerExpenseList(map);
            if (sellerExpenseList.getDataInfo() != null && sellerExpenseList.getDataInfo().getState() == 0) {
                Map<String, String> dataInfo = sellerExpenseList.getDataInfo().getResultMap();
                resultMap.put("code", ResponseCode.SUCCESS);
                resultMap.put("msg", "获取数据成功");
                resultMap.put("totalAmount", dataInfo.get("totalAmount"));// 总分账金额
                resultMap.put("count", dataInfo.get("count"));// 总记录数
                resultMap.put("pageCount", dataInfo.get("pageCount"));// 总页数
                resultMap.put("type",map.get("type"));
                resultMap.put("dataList", sellerExpenseList.getDataList());// 数据详情列表

            } else {
                resultMap.put("code", ResponseCode.FAILURE);
                resultMap.put("msg", "获取数据失败");
            }
        }
        // 营收列表
        else if (map.get("searchType") != null && map.get("searchType").equals("1")) {

            map.put("sdate", map.get("sdate")+" 00:00:00");
            map.put("edate", map.get("edate")+" 23:59:59");
            List<String> countDateList = billDao.findCountDate(map);//所有统计的日期
            List<BillList> billCount = billDao.findBillCount(map);
            
            resultMap.put("code", ResponseCode.SUCCESS);
            resultMap.put("msg", "获取数据成功");
            resultMap.put("type",map.get("type"));
            Integer count = 0;// 总条数
            List<Map<String,String>> paraList =new  ArrayList<Map<String,String>>();
            for (BillList vo : billCount) {
                paraList.add(getMap(vo.getBidno(), vo.getBillType()+""));//封装参数
            }
            resultMap.put("count", billCount.size());// 总记录数
            resultMap.put("totalAmount", String.format("%.2f",getOrderLedgerInfoListSellerAmount(paraList)));// 总金额
            // 列表详情
            List<Map<String, Object>> dataList = new ArrayList<>();
            for(String countDate : countDateList){
                Map<String,String> params = new HashMap<>();
                Map<String, Object> sMap =new  HashMap<>();
                sMap.put("countDate", countDate);
                params.put("sdate", countDate+" 00:00:00");
                params.put("edate", countDate+" 23:59:59");
                params.put("uId", map.get("uId"));
                List<BillList> list = billDao.findBillList(params);
                sMap.put("countNum", list.size()) ;//统计数量
                Double countSum =0.0;
                List<Map<String, Object>> subList  = new ArrayList<>();//json的当天所有数据list
                List<Map<String,String>> paraListSub =new  ArrayList<Map<String,String>>();
                for(BillList bill :list){
                    paraListSub.add(getMap(bill.getBidno(), bill.getBillType()+""));//封装每天的总额的参数
                }
                Map<String, ResponseData> responseDataMap = getOrderLedgerInfoListMap(paraListSub);
                
                for(BillList bill :list){
                    
                    Map<String, Object> billMap = new HashMap<>();
                    billMap.put("bid", bill.getBidno());//订单号
                    billMap.put("zdate", CalendarUtil.getDateString(bill.getSdate(), "HH:mm:ss"));
                    billMap.put("couponType", bill.getCouponType());//优惠券类型   1:平台 2:商户 3:粉丝 4:套餐',
                    //秒杀记录的支付类型替换成和t_bill一样
                    if(bill.getPaytype().equals("1000014")||bill.getPaytype().equals("1000016") || bill.getPaytype().equals("1000018")){
                        billMap.put("payType", "1000001");//支付宝
                    }else if(bill.getPaytype().equals("1000013")||bill.getPaytype().equals("1000017") || bill.getPaytype().equals("1000019")){
                        billMap.put("payType", "1000003");//微信
                    }else{
                        billMap.put("payType", bill.getPaytype());
                    }
                    if(bill.getBillType()==1){
                        billMap.put("isaccount", getStatusMsg(bill.getStatus(),bill.getHstatus()));
                    }else if(bill.getBillType()==2 || bill.getBillType() == 3){
                        billMap.put("isaccount", "已分账");
                    }
                    //营收金额
                    Double amount =0.00;
                    ResponseData responseData = responseDataMap.get(bill.getBidno());
                    if(responseData.getResultMap()!=null && responseData.getResultMap().size()>0){
                        amount = Double.valueOf(responseData.getResultMap().get("selleramount")) ;
                    }
                    billMap.put("amount", String.format("%.2f",amount));//营收金额
                    countSum +=amount;
                    subList.add(billMap);
                    sMap.put("subList", subList);
                    
                }
                count+=subList.size();
                sMap.put("countSum",String.format("%.2f",countSum));//统计总额
                dataList.add(sMap);
                
            }
            resultMap.put("dataList", dataList);
        }


        return resultMap;
    }

    /**
     * 账单详情
     */
    @Override
    public Map<Object, Object> detail(Map<String, String> map) {
        Map<Object, Object> resultMap = new HashMap();
        // 营收详情
        if (map.get("searchType") != null && map.get("searchType").equals("1")) {
            resultMap.put("code", ResponseCode.SUCCESS);
            resultMap.put("msg", "获取成功");
            int times = billDao.findConsumTimes(map);
            BillList bill = billDao.findBillDetail(map);
            resultMap.put("status", getStatusMsg(bill.getStatus(),bill.getHstatus()));// '0 未到账 1 已到账'
            resultMap.put("bid", bill.getBidno());// 订单号
            resultMap.put("sdate",
                    CalendarUtil.getDateString(bill.getZdate(), "yyyy-MM-dd HH:mm:ss"));// 支付时间
            resultMap.put("codeid", bill.getCodeid());// 消费验证码
            resultMap.put("money", bill.getMoney());// 消费金额
            resultMap.put("nname", bill.getNname());// 用户昵称
            resultMap.put("userType", bill.getSellerid() == bill.getGenussellerid() ? 2 : 1);// 用户类型：1// 普通会员// 2// 绑定会员
            resultMap.put("sellername", bill.getSellername());// 消费商家
            //账单详情：1；2:秒杀详情
            resultMap.put("income", getOderInfoAmount(bill.getBidno(),bill.getBillType()+""));// 实际支付金额
                
            
            resultMap.put("rebate", String.format("%.2f",getOderInfoLedgeramount(bill.getBidno(),bill.getBillType()+"")));// 总分账金额
            resultMap.put("couponsMoney", Double.valueOf(getPreferential(bill.getBidno(),bill.getBillType()+"")));// 优惠券金额 
            resultMap.put("amount",String.format("%.2f",getOderInfoSelleramount(bill)));//实际营收金额
            resultMap.put("payType", bill.getPaytype());//支付方式
            resultMap.put("percent", getLedgerratio(bill.getBidno(),bill.getBillType()+""));// 优惠比例
            
            ResponseData responseData = getOrderLedgerInfo(bill.getBidno(),bill.getBillType()+"");
            if(responseData.getState()==0){
               resultMap.put("liveCoin", responseData.getResultMap().get("liveCoin"));//鸟币支付总额
               resultMap.put("liveCoinRatio", responseData.getResultMap().get("liveCoinRatio"));//鸟币费率
               resultMap.put("codeid", responseData.getResultMap().get("codeid"));//消费验证码
               resultMap.put("expectedselleramount", responseData.getResultMap().get("expectedselleramount"));//预计(理论)商家营收
            }
            
            
            if(bill.getBillType()==2||bill.getBillType()==3){
                resultMap.put("status", "已分账");
            }
            resultMap.put("times", times);// 消费次数
            //扫码的消费次数设置为1
            if(bill.getBillType()==3){
                resultMap.put("times", 1);
            }
            
            map.put("uid", bill.getUid()+"");
            ResponseData user = this.getUserMsg(map);
            if(user!=null && user.getResultMap()!=null){
                resultMap.put("avatar", user.getResultMap().get("avatar")==null?"":user.getResultMap().get("avatar"));// 商户头像
            }

        }
        // 收益详情
        else if (map.get("searchType") != null && map.get("searchType").equals("2")) {
            try{
                resultMap.put("code", ResponseCode.SUCCESS);
                resultMap.put("msg", "获取成功");
                
                BillList bill = billDao.findBillDetail(map);
                @SuppressWarnings( "unchecked" )
                Map<String, Object> commissionMap =
                        (Map<String, Object>) JSON.parse(bill.getCommission());
                map.put("genussellerid", bill.getGenussellerid() + "");
                map.put("sellerid", bill.getSellerid() + "");
                log.info("访问getSellerLedgerInfo,【请求参数】：" +map);
                ResponseData responseData = this.getSellerLedgerInfo(map);
                if (commissionMap!=null && responseData.getState() == 0 && responseData.getResultMap() != null) {
                    resultMap.put("times", responseData.getResultMap().get("count"));// 分账次数
                    resultMap.put("commisonCount", responseData.getResultMap().get("commisonCount"));// 已分账总金额
                    // 参与分账总金额
//                    BigDecimal LedgerAmount =bill.getCouponType() == null ? 
//                            bill.getMoney(): (bill.getCouponType() == 2 ? bill.getMoney().subtract(bill.getCuser()) : bill.getMoney());
                    resultMap.put( "rebate",String.format("%.2f",Double.valueOf(commissionMap.get("seller_money").toString())));// 总分账金额
                    resultMap.put("money", bill.getMoney());// 消费金额
                    resultMap.put("nname", bill.getNname());// 用户昵称
                    resultMap.put("sellername", bill.getSellername());// 消费商家名称
                    resultMap.put("sdate",CalendarUtil.getDateString(bill.getZdate(), "yyyy-MM-dd hh:mm:ss"));// 支付时间
                    if(bill.getIsaccount()!=null && bill.getIsaccount() ==0){
                        resultMap.put("status", "未到账");// '0 未到账 1 已到账'
                    }else if(bill.getIsaccount()!=null && bill.getIsaccount() ==1){
                        resultMap.put("status", "已到账");// '0 未到账 1 已到账'
                    }
                    
                    map.put("uid", bill.getUid()+"");
                    log.info("访问getUserMsg,【请求参数】：" +map);
                    ResponseData user = this.getUserMsg(map);
                    resultMap.put("avatar", user.getResultMap().get("avatar"));// 商户头像
                }
            }
            catch(Exception e){
                e.printStackTrace();
                resultMap.put("code", ResponseCode.FAILURE);
                resultMap.put("msg", "获取失败");
                
            }
        }
        //储值卡详情
        else if (map.get("searchType") != null && map.get("searchType").equals("4")) {
            String bid = map.get("bid").toString();//订单号
            SellerPackageOrder sellerPackageOrder = sellerPackageOrderDao.selectByOrderNo(bid);
            resultMap.put("code", ResponseCode.SUCCESS);
            resultMap.put("msg", "获取成功");
            resultMap.put("status", "1");// '0 未到账 1 已到账'
            resultMap.put("rebate", "0.00");// 总分账金额
            resultMap.put("payType", sellerPackageOrder.getPaymentType());//支付方式
            resultMap.put("liveCoinRatio", "1:1.43");//鸟币费率
            resultMap.put("status", "已分账");
            resultMap.put("expectedselleramount", "0.00");//实际营收
            Seller seller = sellerDao.findBySellerid(Integer.valueOf(map.get("sellerid")));
            resultMap.put("percent",seller.getAgio());
            resultMap.put("cuser",sellerPackageOrder.getCuser().doubleValue());//抵用券优惠减免金额
            
        }
        
        else {
            resultMap.put("code", ResponseCode.FAILURE);
            resultMap.put("msg", "获取失败");
        }


        return resultMap;
    }


    /**
     * 取商户的分账信息
     */
    @Override
    public ResponseData getSellerLedgerInfo(Map<String, String> map) {
        ResponseData responseData = null;
        try {
            XmniaoWalletService.Client client =
                    ThriftBuilder.build(globalConfig.getThriftPayHost(),
                            Integer.parseInt(globalConfig.getThriftPayPort()),
                            "XmniaoWalletService", XmniaoWalletService.Client.class);

            ThriftBuilder.open();
            responseData = client.getSellerLedgerInfo(map);

        } catch (Exception e) {
            log.error("调用支付系统接口获取商户的分账信息异常！", e);

        } finally {
            ThriftBuilder.close();
        }
        return responseData;
    }

    /**
     * 获取用户信息
     * uid  必选  String  用户id
     */
    @Override
    public ResponseData getUserMsg(Map<String, String> paramMap) {
        ResponseData responseData = null;
        try {
            UserService.Client client =
                    ThriftBuilder.build(globalConfig.getThriftBusHost(),
                            Integer.parseInt(globalConfig.getThriftBusPort()), "UserService",
                            UserService.Client.class);

            ThriftBuilder.open();
            responseData = client.getUserMsg(paramMap);

        } catch (Exception e) {
            log.error("调用支付系统接口获取用户信息信息异常！", e);

        } finally {
            ThriftBuilder.close();
        }
        return responseData;
    }
    
    /**
     * 手动验单
     * @throws Exception 
     */
	@Override
	@Transactional
	public void vertify(String bid,Integer type) throws Exception {
		
			//添加验证消费记录
			BillRecord billRecord=new BillRecord();
			billRecord.setBid(Long.valueOf(bid));
			billRecord.setStatus(6);
			billRecord.setCdate(new Date());
			billRecord.setExplains("美食订单验证消费");
			//修改订单状态(如果优惠券类型为3分账状态不修改)
			Bill bill=new Bill();
			bill.setLdate(new Date());
			bill.setBid(Long.valueOf(bid));
			if(type!=null&&type==1){
				bill.setStatus(9);
			}else{
				bill.setStatus(9);
				bill.setHstatus(11);
			}
			billDao.modifyHstatus(bill);
			//添加验证记录
			billDao.inserBillRecord(billRecord);
			
			//调用业务服务分账（如果优惠券类型为3则不进行分账）
			if(type==null||type!=1){
				   try {
			            OrderService.Client client = ThriftBuilder.build(globalConfig.getThriftBusHost(), Integer.parseInt(globalConfig
			                    .getThriftBusPort()), "OrderService", OrderService.Client.class);
			            ThriftBuilder.open();
			            Map<String, String> paramMap=new HashMap<>();
			            paramMap.put("bid",bid);
			            
			            Map<String, String> map= client.handleLedger(paramMap);
			            if(map!=null&&!map.isEmpty()){
			            	if(!map.get("recode").equals("100")){//如果手动分账 失败抛出异常，回滚数据
				            	 throw new RuntimeException();
							}
			            }else{
			            	throw new RuntimeException();
			            }
			            
			        } catch (Exception e) {
			            log.error("调用手动验证分账接口异常", e);
			            throw new RuntimeException();
			            
			        } finally {
			            ThriftBuilder.close();
			        }
			}
		
	}
	
	public Map<String,String> getOrderLedgerInfo(String bid){
		try {
				
			 OrderService.Client client = ThriftBuilder.build(globalConfig.getThriftBusHost(), Integer.parseInt(globalConfig
	                    .getThriftBusPort()), "OrderService", OrderService.Client.class);
	            ThriftBuilder.open();
	            Map<String, String> paramMap=new HashMap<>();
	            paramMap.put("bid",bid);
	            paramMap.put("btype", "1");
	            ResponseData responseDate= client.getOrderLedgerInfo(paramMap);
	            if(responseDate.getState()==0){
	            	return responseDate.getResultMap();
	            }else{
	            	return null;
	            }
	            
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
            ThriftBuilder.close();
        }
		
		return null;
	}
	
	
	/**
	 * 根据订单号查询订单信息
	 */
	@Override
	public Bill selectBillByBid(String bid) {
		
		return billDao.selectBillByBid(bid);
	}
	
	/**
	 * 根据验证消费吗和商家ID获取订单信息
	 */
	@Override
	public Bill selectBillByCodeidAndSellerid(String codeid, int sellerid) {
		
		return billDao.selectBillByCodeidAndSellerid(codeid,sellerid);
	}
	
	/**
	 * 根据验证消费吗和商家ID获取积分订单信息
	 */
	@Override
	public Bagin selectBarginBycodeidAndSellerid(String codeid, int sellerid) {
		
		return billDao.selectBarginBycodeidAndSellerid(codeid,sellerid);
		
	}

	/**
	 * 验证爆品订单
	 */
	@Override
	@Transactional
	public void vertifyBargin(String bid,int sellerid,int bpid) {
		
		billDao.modifyBarginBycodeid(bid,sellerid);//修改积分订单状态
		billDao.modifyBarginCount(bpid);//修改积分订单卖出数量
		//添加验证消费记录
		BillRecord billRecord=new BillRecord();
		billRecord.setBid(Long.valueOf(bid));
		billRecord.setStatus(6);
		billRecord.setCdate(new Date());
		billRecord.setExplains("爆品订单验证消费");
		//添加验证记录
		billDao.inserBillRecord(billRecord);
	}
	
	/**
	 * 根据商户ID和验证消费吗查询优惠券信息
	 */
	@Override
	public Coupon selectCouponByCodeidAndSellerid(String codeid, int sellerid) {
		
		return billDao.selectCouponByCodeidAndSellerid(codeid,sellerid);
	}
	/**
	 * 验证商家赠送券
	 */
	@Override
	@Transactional
	public void vertifySellerCoupon(int cuid) {
		//添加验证消费记录
		BillRecord billRecord=new BillRecord();
		billRecord.setBid(Long.valueOf(cuid));
		billRecord.setStatus(6);
		billRecord.setCdate(new Date());
		billRecord.setExplains("商家赠送券验证消费");
	
		//添加验证记录
		billDao.inserBillRecord(billRecord);
		billDao.modifyCouponSellerStatusByCuid(cuid,new Date());
		
	}
	
	/**
	 * 获取分账状态
	 * @param hstatus
	 * @return
	 */
	private String getStatusMsg(Integer status ,Integer hstatus){
	    String statusInfo = "";
        switch (status){
            case 1:
                if(0 == hstatus || 10 == hstatus){
                    statusInfo="待分账";
                }else{
                    statusInfo="分账中";
                }
                break;
            case 2:
                statusInfo = "已分账";
                break;
            case 3:
                if(0 == hstatus || 10 == hstatus){
                    statusInfo="待验证";
                }else{
                    statusInfo="分账中";
                }
                break;
            case 4:
                statusInfo="退款中";  
                break;
            case 5:
                statusInfo="已退款";
                break;
            case 6:
                if(0 == hstatus || 10 == hstatus){
                    statusInfo="待验证";
                }else{
                    statusInfo="分账中";
                }
                break;
            case 7:
                statusInfo="退款中";
                break;
            case 8:
                if(0 == hstatus || 10 == hstatus){
                    statusInfo="待验证";
                }else{
                    statusInfo="分账中";
                }
                break;
            case 9:
                statusInfo="分账中";
                break;
            case 10:
                statusInfo="退款中";
                break;
            case 11:
                statusInfo="退款中";
                break;
            case 12:
                statusInfo="退款中";
                break;
            case 13:
                statusInfo="退款中";
                break;
            case 14:
                statusInfo="退款中";
                break;
            case 15:
                statusInfo="已退款";
        }
        return statusInfo;
	}
    @Override
    public List<Bill> findFansList(Map<String, String> params) {
        
        
        return null;
    }
    @Override
    public List<String> findFansCountDate(Map<String, String> params) {
        List<String> list = this.findFansCountDate(params);
        return list;
    }
    @Override
    public Map<Object, Object> list(int page,int rows,int sellerid,int type) throws Exception{
        Map<Object, Object> resultMap = new HashMap<>();
        Map<String, String> params =new HashMap<>();
        //分页        
        params.put("pageOffset", (page-1)*rows + "");
        params.put("pageSize", rows + "");
        params.put("sellerid", sellerid+"");
        String sdate= "2015-01-01 00:00:00";
        String edate = CalendarUtil.getDateString(new Date(), "yyyy-MM-dd HH:mm:ss");
        params.put("sdate", sdate);
        params.put("edate", edate);
        
        if(type==1){
            log.info("------------访问登录列表粉丝券列表");
            List<String> countDateList = billDao.findFansCountDate(params);//所有统计的日期
            List<Bill> billCount = billDao.findFansList(params);
            Double totalAmount = 0.0;// 总金额
            Integer count = 0;// 总条数
            for (Bill vo : billCount) {
                totalAmount += (vo.getCdenom().doubleValue());// 总金额
            }
            resultMap.put("totalAmount", String.format("%.2f",totalAmount));// 总金额
            // 列表详情
            List<Map<String, Object>> dataList = new ArrayList<>();
            for(String countDate : countDateList){
                Map<String, Object> sMap =new  HashMap<>();
                sMap.put("countDate", countDate +" "+CalendarUtil.getWeek(CalendarUtil.stringToDate(countDate)));
                params.put("sdate", countDate+" 00:00:00");
                params.put("edate", countDate+" 23:59:59");
                List<Bill> list = billDao.findFansList(params);
                sMap.put("countNum", list.size()) ;//统计数量
                Double countSum =0.0;
                List<Map<String, Object>> subList  = new ArrayList<>();//json的当天所有数据list
                for(Bill bill :list){
                    Map<String, Object> billMap = new HashMap<>();
                    billMap.put("bid", bill.getBid());
                    
                    Map<String,String> pars = new HashMap<>();
                    params.put("uid", bill.getUid()+"");
                    ResponseData responseData;
                    try {
                        responseData = redpacketService.getUserMsg(params);
                        if(responseData!=null && responseData.getState()==0){
                            String nname=  responseData.getResultMap().get("nname");
                            String phone=  responseData.getResultMap().get("phone");
                            billMap.put("uid", StringUtils.isNotBlank(nname)?nname:phone);
                        }else{
                            billMap.put("uid", StringUtils.isNotBlank(responseData.getResultMap().get("phone"))?responseData.getResultMap().get("phone").toString():"匿名"); 
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    
                    
                    
                    billMap.put("zdate", CalendarUtil.getDateString(bill.getZdate(), "HH:mm:ss"));
                    billMap.put("payType", bill.getPaytype());
                    billMap.put("cdenom", bill.getCdenom());//优惠券金额
                    subList.add(billMap);
                    countSum +=bill.getCdenom().doubleValue();
                    sMap.put("subList", subList);
                }
                count+=subList.size();
                sMap.put("countSum",String.format("%.2f",countSum));//统计总额
                dataList.add(sMap);
                resultMap.put("count", count);// 总记录数
            }
            resultMap.put("dataList", dataList);
        }else if(type==2){
            log.info("------------访问登录列表营收列表");
            params.put("uId", params.get("sellerid"));
            List<String> countDateList = billDao.findLoginCountDate(params);//所有统计的日期
            //List<String> countDateSum = billDao.findCountDateSum(map);//所有统计的日期
            List<Bill> billCount = billDao.findLoginBillCount(params);
            
            resultMap.put("code", ResponseCode.SUCCESS);
            resultMap.put("msg", "获取数据成功");
            Double totalAmount = 0.0;// 总金额
            Integer count = 0;// 总条数
            for (Bill vo : billCount) {
                //totalAmount += (Double) commissionMap.get("seller_amount");// 总金额
                totalAmount += this.getAmount(vo);// 总金额
            }
            resultMap.put("totalAmount", String.format("%.2f",totalAmount));// 总金额
            // 列表详情
            List<Map<String, Object>> dataList = new ArrayList<>();
            for(String countDate : countDateList){
                Map<String,String> p = new HashMap<>();
                Map<String, Object> sMap =new  HashMap<>();
                sMap.put("countDate", countDate +" "+CalendarUtil.getWeek(CalendarUtil.stringToDate(countDate)));
                p.put("sdate", countDate+" 00:00:00");
                p.put("edate", countDate+" 23:59:59");
                p.put("uId", params.get("uId"));
                List<Bill> list = billDao.findLoginBillList(p);
                sMap.put("countNum", list.size()) ;//统计数量
                Double countSum =0.0;
                List<Map<String, Object>> subList  = new ArrayList<>();//json的当天所有数据list
                for(Bill bill :list){
                    Map<String, Object> billMap = new HashMap<>();
                    billMap.put("bid", bill.getBid());
                    billMap.put("zdate", CalendarUtil.getDateString(bill.getSdate(), "HH:mm:ss"));
                    billMap.put("payType", bill.getPaytype());
                    billMap.put("isaccount", getStatusMsg(bill.getStatus(),bill.getHstatus()));
                    billMap.put("amount", String.format("%.2f",getAmount(bill)));//营收金额
                    
                    params.put("uid", bill.getUid()+"");
                    ResponseData responseData;
                    try {
                        responseData = redpacketService.getUserMsg(params);
                        if(responseData!=null && responseData.getState()==0){
                            String nname=  responseData.getResultMap().get("nname");
                            String phone=  responseData.getResultMap().get("phone");
                            billMap.put("uid", StringUtils.isNotBlank(nname)?nname:phone);
                        }else{
                            billMap.put("uid", StringUtils.isNotBlank(responseData.getResultMap().get("phone"))?responseData.getResultMap().get("phone").toString():"匿名"); 
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    
                    if(bill.getCouponType()!=null && bill.getCouponType()==3){
                        billMap.put("couponType","使用粉丝券");
                    }
                    subList.add(billMap);
                    countSum +=getAmount(bill);
                    sMap.put("subList", subList);
                }
                count+=subList.size();
                sMap.put("countSum",String.format("%.2f",countSum));//统计总额
                dataList.add(sMap);
                resultMap.put("count", count);// 总记录数
            }
            resultMap.put("dataList", dataList);
        }
        
        return resultMap;
    }
    //获取实际支付金额
    public double getSellerAmount(Bill bill){
        
        double sellerAmount = (bill.getMoney()==null?BigDecimal.ZERO:bill.getMoney()).subtract(bill.getCuser()==null?BigDecimal.ZERO:bill.getCuser()).
                subtract(bill.getFullReduction()==null?BigDecimal.ZERO:bill.getFullReduction()).
                subtract(bill.getReduction()==null?BigDecimal.ZERO:bill.getReduction()).doubleValue();
        return sellerAmount;
    }
    
    
    /**
     * 计算商户实际营收金额
     * @param bill
     * @return
     */
    @Override
    public Double getAmount(Bill bill) {
        //秒杀记录营收
        if(bill.getBillType()==2){
            Double amount =bill.getAmount()==null?0.00: bill.getAmount().doubleValue();
            return amount;
        }
        Double money = bill.getMoney().doubleValue()*bill.getBaseagio();
        Double cuser = 0.00;
        if(bill.getCouponType()!=null){
            cuser = bill.getCouponType()==2?bill.getCuser().doubleValue():0.00;
        }
        Double fullReduction = bill.getFullReduction()==null?BigDecimal.ZERO.doubleValue():bill.getFullReduction().doubleValue();
        Double amount = money-cuser-fullReduction<0?0.00:money-cuser-fullReduction;
                
        return amount;
    }
    
    
    /**
     * 获取订单分账及消费信息中的实际支付金额
     * @param bid
     * @return
     */
    @Override
    public Double getOderInfoAmount(String bid,String btype){
        ResponseData OrderLedgerInfo = this.getOrderLedgerInfo(bid,btype);
        Double amount = 0.00;
        if(OrderLedgerInfo.getState()==0){
           amount = Double.valueOf(OrderLedgerInfo.getResultMap().get("payamount"));
        }
        return amount;
    }
    
    /**
     * 获取订单分账及消费信息中的实际营收金额
     * @param bid
     * @return
     */
    @Override
    public Double getOderInfoSelleramount(BillList bill){
        ResponseData OrderLedgerInfo = this.getOrderLedgerInfo(bill.getBidno(),bill.getBillType()+"");
        Double amount = 0.00;
        if(OrderLedgerInfo.getState()==0 ){
            amount = Double.valueOf(OrderLedgerInfo.getResultMap().get("selleramount"));
        }
//        }else if(bill.getBillType()==2){
//            amount = bill.getAmount().doubleValue();
//        }
        return amount;
    }
    
    
    /**
     * 获取订单分账及消费信息中的分账金额
     * @param bid
     * @return
     */
    public Double getOderInfoLedgeramount(String bid,String btype){
        ResponseData OrderLedgerInfo = this.getOrderLedgerInfo(bid,btype);
        Double amount = 0.00;
        if(OrderLedgerInfo.getState()==0){
            amount =Double.valueOf(OrderLedgerInfo.getResultMap().get("ledgeramount"))-Double.valueOf(OrderLedgerInfo.getResultMap().get("selleramount"));
        }
        return amount;
    }
    
    /**
     * 获取订单分账及消费信息中的优惠比例
     * @param bid
     * @return
     */
    public String getLedgerratio(String bid,String btype){
        ResponseData OrderLedgerInfo = this.getOrderLedgerInfo(bid,btype);
        String ledgerratio ="";
        if(OrderLedgerInfo.getState()==0){
            ledgerratio =  OrderLedgerInfo.getResultMap().get("ledgerratio");
        }
        return ledgerratio;
    }
    
    /**
     * 获取订单分账及消费信息中的优惠总额
     * @param bid
     * @return
     */
    public String getPreferential(String bid,String btype){
        ResponseData OrderLedgerInfo = this.getOrderLedgerInfo(bid,btype);
        String preferential ="0.00";
        if(OrderLedgerInfo.getState()==0){
            preferential =  OrderLedgerInfo.getResultMap().get("preferential");
        }
        return preferential;
    }
    
    
    /**
     * 获取订单分账及消费信息
     * 
     */
    @Override
    public ResponseData getOrderLedgerInfo(String id,String btype) {
        Map<String,String> params = new HashMap<>();
        params.put("bid", id);
        params.put("btype", btype);
        ResponseData responseData = null;
        try {
            OrderService.Client client =
                    ThriftBuilder.build(globalConfig.getThriftBusHost(),
                            Integer.parseInt(globalConfig.getThriftBusPort()), "OrderService",
                            OrderService.Client.class);

            ThriftBuilder.open();
            responseData = client.getOrderLedgerInfo(params);

        } catch (Exception e) {
            log.error("调用业务系统接口获取订单分账及消费信息异常！", e);

        } finally {
            ThriftBuilder.close();
        }
        return responseData;
    }
    
    /**
     * 1.11.4.  获取商户储值卡充值消费详细记录
     * 
     */
    public PageList  getValueCardRecord(Map<String,String> paraMap) {
        PageList pageList = null;
        try {
            ValueCardService.Client client =
                    ThriftBuilder.build(globalConfig.getThriftPayHost(),
                            Integer.parseInt(globalConfig.getThriftPayPort()), "ValueCardService",
                            ValueCardService.Client.class);
            
            ThriftBuilder.open();
            pageList = client.getValueCardRecord(paraMap);
            
        } catch (Exception e) {
            log.error("调用获取商户储值卡充值消费详细记录异常！", e);
            
        } finally {
            ThriftBuilder.close();
        }
        return pageList;
    }
    
    /**
     * 批量获取订单分账及消费信息
     * 
     */
    public Map<String,ResponseData>  getOrderLedgerInfoList(List<Map<String,String>> paraList) {
        //log.info("调用getOrderLedgerInfoList 参数为： "+paraList);
        Map<String,ResponseData> resultMap = null;
        try {
            OrderService.Client client =
                    ThriftBuilder.build(globalConfig.getThriftBusHost(),
                            Integer.parseInt(globalConfig.getThriftBusPort()), "OrderService",
                            OrderService.Client.class);

            ThriftBuilder.open();
            resultMap = client.getOrderLedgerInfoList(paraList);

        } catch (Exception e) {
            log.error("调用业务系统接口批量获取订单分账及消费信息异常！", e);

        } finally {
            ThriftBuilder.close();
        }
        //log.info("调用getOrderLedgerInfoList 返回为： "+resultMap);
        return resultMap;
    }

    public  Map<String,String> getMap(String bid,String btype){
         Map<String,String>  map=  new HashMap<>();
         map.put("bid", bid);
         map.put("btype", btype);
         return map;
    }
    
  //获取批量获取订单分账及消费信息中的实际支付金额
    @Override
    public Double getOrderLedgerInfoListSellerAmount(List<Map<String,String>> paraList){
        Double totalAmount = 0.00;
        Map<String, ResponseData> orderLedgerInfoListMap = this.getOrderLedgerInfoList(paraList);
        if(orderLedgerInfoListMap!=null && orderLedgerInfoListMap.size()>0 ){
            for(String bid : orderLedgerInfoListMap.keySet()){
                ResponseData responseData = orderLedgerInfoListMap.get(bid);
                if(responseData.getState()==0 && responseData.getResultMap()!=null){
                    totalAmount += Double.valueOf(responseData.getResultMap().get("selleramount"));
                }
            }
        }
        return totalAmount;
    }
    
    //获取批量获取订单分账及消费信息
    public Map<String, ResponseData> getOrderLedgerInfoListMap(List<Map<String,String>> paraList){
        Map<String, ResponseData> orderLedgerInfoListMap = this.getOrderLedgerInfoList(paraList);
        return orderLedgerInfoListMap;
    }
    
    //获取支付类型字符串
    public String getPayType(String payType){
        String type="组合支付";
        if(payType.equals("1000001")||payType.equals("1000014")||payType.equals("1000016") || payType.equals("1000018")){
            type="支付宝支付";
        }else if(payType.equals("1000003")||payType.equals("1000013")||payType.equals("1000017") || payType.equals("1000019")){
            type="微信支付";
        }
        return type;
        
    }
	@Override
	public Coupon queryCouponByCodeidAndSellerid(String codeid, int sellerid) {
		try {
			return billDao.queryCouponByCodeidAndSellerid(codeid,sellerid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 验证粉丝卷
	 */
	@Override
	public void vertifyCoupon(int cuid) {
		//添加验证消费记录
		BillRecord billRecord=new BillRecord();
		billRecord.setBid(Long.valueOf(cuid));
		billRecord.setStatus(6);
		billRecord.setCdate(new Date());
		billRecord.setExplains("粉丝卷验证消费");
	
		//添加验证记录
		billDao.inserBillRecord(billRecord);
		
		billDao.modifyCouponStatusByCuid(cuid,new Date());
		
	}
	
	/**
	 * 储值消费、充值历史
	 */
    @Override
    public Map<String, Object> getValueCardList(Map<String, String> map) {
        log.info("访问getValueCardRecord,【请求参数】：" +map);
        Map<String, Object> resultMap= new HashMap<>();
        PageList pageList = null;
        DebitcardSeller debitcardSeller = debitcardSellerDao.findBySellerId(map.get("sellerid").toString());
        if(debitcardSeller==null){
            resultMap.put("code", ResponseCode.EEROR);
            resultMap.put("msg", "此商户未开通储值卡功能！");
            return resultMap;
        }
        map.put("sellerid", debitcardSeller.getSellerid().toString());
        map.put("type", debitcardSeller.getSellertype().toString());
        try {
            ValueCardService.Client client =
                    ThriftBuilder.build(globalConfig.getThriftPayHost(),
                            Integer.parseInt(globalConfig.getThriftPayPort()), "ValueCardService",
                            ValueCardService.Client.class);
            ThriftBuilder.open();
            pageList = client.getValueCardRecord(map);
            String sum = pageList.getSum();//人数
            String total =  pageList.getTotal();
            pageList.getPageList();
            resultMap.put("sum", sum);
            resultMap.put("total", total);
            resultMap.put("dataList", pageList.getPageList());
            resultMap.put("code", ResponseCode.SUCCESS);
            resultMap.put("msg", "获取数据成功");
        } catch (Exception e) {
            log.error("调用获取商户储值卡充值消费详细记录异常！", e);
            resultMap.put("code", ResponseCode.FAILURE);
            resultMap.put("msg", "获取数据失败");
        } finally {
            ThriftBuilder.close();
        }
        return resultMap;
    }
    @Override
    public Map<String, Object> getUserList(Map<String, String> map) {
        log.info("访问getUserList,【请求参数】：" +map);
        Map<String, Object> resultMap= new HashMap<>();
        SubList subList = null;
        DebitcardSeller debitcardSeller = debitcardSellerDao.findBySellerId(map.get("sellerid").toString());
        if(debitcardSeller==null){
            resultMap.put("code", ResponseCode.EEROR);
            resultMap.put("msg", "此商户未开通储值卡功能！");
            return resultMap;
        }
        map.put("sellerid", debitcardSeller.getSellerid().toString());
        map.put("type", debitcardSeller.getSellertype().toString());
        try {
            ValueCardService.Client client =
                    ThriftBuilder.build(globalConfig.getThriftPayHost(),
                            Integer.parseInt(globalConfig.getThriftPayPort()), "ValueCardService",
                            ValueCardService.Client.class);
            ThriftBuilder.open();
            subList = client.getUserList(map);
            String sum = subList.getSum();//人数
            String total =  subList.getDealDate();//总额
            resultMap.put("sum", sum);
            resultMap.put("total", total);
            resultMap.put("dataList",  subList.getDataList());
            resultMap.put("code", ResponseCode.SUCCESS);
            resultMap.put("msg", "获取数据成功");
        } catch (Exception e) {
            log.error("调用获取商户充值会员列表异常！", e);
            resultMap.put("code", ResponseCode.FAILURE);
            resultMap.put("msg", "获取数据失败");
        } finally {
            ThriftBuilder.close();
        }
        return resultMap;
    }
    
    @Override
    public Map<String, Object> getUserDetail(Map<String, String> map) {
        log.info("访问getUserDetail,【请求参数】：" +map);
        Map<String, Object> resultMap= new HashMap<>();
        Map<String,String> detailMap = null;
        DebitcardSeller debitcardSeller = debitcardSellerDao.findBySellerId(map.get("sellerid").toString());
        if(debitcardSeller==null){
            resultMap.put("code", ResponseCode.EEROR);
            resultMap.put("msg", "此商户未开通储值卡功能！");
            return resultMap;
        }
        map.put("sellerid", debitcardSeller.getSellerid().toString());
        map.put("type", debitcardSeller.getSellertype().toString());
        try {
            ValueCardService.Client client =
                    ThriftBuilder.build(globalConfig.getThriftPayHost(),
                            Integer.parseInt(globalConfig.getThriftPayPort()), "ValueCardService",
                            ValueCardService.Client.class);
            ThriftBuilder.open();
            detailMap = client.getUserDetail(map);
            resultMap.put("code", ResponseCode.SUCCESS);
            resultMap.put("msg", "获取数据成功");
            resultMap.put("dataList", detailMap);
        } catch (Exception e) {
            log.error("调用获取商户充值会员详情异常！", e);
            resultMap.put("code", ResponseCode.FAILURE);
            resultMap.put("msg", "获取数据失败");
        } finally {
            ThriftBuilder.close();
        }
        return resultMap;
    }
    /**
     * 获取商户累计充值，累计剩余，充值会员
     */
    @Override
    public Map<String, Object> getValueCardBalance(SellerAccount sellerAccount) {
        log.info("访问getValueCardBalance,【请求参数】sellerid:" +sellerAccount.getSellerid());
        Map<String, Object> resultMap= new HashMap<>();
        Map<String,String> detailMap = null;
        DebitcardSeller debitcardSeller = debitcardSellerDao.findBySellerId(sellerAccount.getSellerid().toString());
        if(debitcardSeller==null){
            resultMap.put("code", ResponseCode.EEROR);
            resultMap.put("msg", "此商户未开通储值卡功能！");
            return resultMap;
        }
        try {
            ValueCardService.Client client =
                    ThriftBuilder.build(globalConfig.getThriftPayHost(),
                            Integer.parseInt(globalConfig.getThriftPayPort()), "ValueCardService",
                            ValueCardService.Client.class);
            ThriftBuilder.open();
            Map<String,String> params = new HashMap<>();
            params.put("sellerid", debitcardSeller.getSellerid().toString());
            params.put("type", debitcardSeller.getSellertype().toString());
            detailMap = client.getValueCardBalance(params);
            if(detailMap.size()!=0){
            	for(String key :detailMap.keySet()){
            		String values = detailMap.get(key);
            		String[] arrResult = values.split(",");
            		resultMap.put("code", ResponseCode.SUCCESS);
            		resultMap.put("msg", "获取数据成功");
            		resultMap.put("cumulativeQuota", arrResult[0]);//累积充值,
            		resultMap.put("quota", arrResult[1]);//剩余额度
            		resultMap.put("num", arrResult[2]);//充值会员
            	}
            	
            }else{
                resultMap.put("code", ResponseCode.FAILURE);
                resultMap.put("msg", "获取数据失败");
            }
        } catch (Exception e) {
            log.error("调用获取商户累计充值，累计剩余，充值会员异常！", e);
            resultMap.put("code", ResponseCode.FAILURE);
            resultMap.put("msg", "获取数据失败");
        } finally {
            ThriftBuilder.close();
        }
        return resultMap;
    }
    
    /**
     * 
     * @Description: 处理数据
     * @author xiaoxiong
     * @date 2016年11月17日
     */
    @Override
    public  Map<String, Object> billConverMap(Bill bill) {
        
        Map<String,Object> billMap = new HashMap<>();
        try {
            Map<String,String> result = this.getOrderLedgerInfo(bill.getBid()+"");
            if(result==null){
                result=new HashMap<String,String>();
            }
            
            billMap.put("payment", result.get("payamount")==null?"0":result.get("payamount"));//实际支付00.n
            billMap.put("revenue", result.get("selleramount")==null?"0":result.get("selleramount"));//商家营收
            billMap.put("money", bill.getMoney());//订单金额
            billMap.put("agio", bill.getBaseagio());//商家折扣
            billMap.put("phone",bill.getPhoneid().substring(0,3)+"****"+bill.getPhoneid().substring(7));//手机号码
            billMap.put("nname", bill.getNname());//昵称
//          billMap.put("zdate", bill.getZdate());//支付时间
            billMap.put("bid", bill.getBid());//订单编号
            billMap.put("spitamount", result.get("preferential")==null?"0":result.get("preferential"));//立减
            billMap.put("type", 1);//类型 1 美食  3 赠品卷  4预售卷
            //double ledgeramount =Double.parseDouble(result.get("ledgeramount")==null?"0":result.get("ledgeramount"))-Double.parseDouble(result.get("selleramount")==null?"0":result.get("selleramount"));
            double ledgeramount =0.00;
            double selleramount = 0.00;
            if(result.get("ledgeramount")!=null && result.get("ledgeramount")!= ""){
                ledgeramount = Double.valueOf(result.get("ledgeramount"));
            }
            if(result.get("selleramount")!=null && result.get("selleramount")!= ""){
                selleramount = Double.valueOf(result.get("selleramount"));
            }
            double ledgerMoney = ledgeramount - selleramount;
            
//          BigDecimal bg = new BigDecimal(ledgerMoney);
//          ledgeramount  = bg.setScale(2).doubleValue();
            billMap.put("ledgeramount", String.format("%.2f",ledgerMoney));//系统分账金额
        } catch (Exception e) { 
            e.printStackTrace();
        }
        return billMap;
    }
    
    
   //验证美食订单
    @Override
    public boolean virtifyBill(String codeid,int sellerid){
        try {
            String setDate=globalConfig.getVertifyDate();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
            
            Bill bill=this.selectBillByCodeidAndSellerid(codeid, sellerid);
            if(bill!=null){
                if(bill.getZdate().getTime()>sdf.parse(setDate).getTime()){
                    this.vertify(bill.getBid().toString(),bill.getLiveLedger());
                    Map<String,Object> result=billConverMap(bill);
                    new Response(ResponseCode.SUCCESS, "成功",result).write();
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
        
    }
    
    /**
     * 
     * @Description: 验证优惠券
     * @author xiaoxiong
     * @throws IOException 
     * @throws ParseException 
     * @date 2016年10月21日
     */
    @Override
    public boolean virtifySellercoupon(String codeid, int sellerid){
        try {
            /*有老订单处理时间分界线*/
            String setDate=globalConfig.getVertifyDate();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
            
            Coupon coupon=this.selectCouponByCodeidAndSellerid(codeid,sellerid);
            if(coupon!=null){
                if(sdf.parse(coupon.getGdate()).getTime()>=sdf.parse(setDate).getTime()){
                    if(sdf.parse(coupon.getSdate()).getTime()<=new Date().getTime()&&new Date().getTime()<=sdf.parse(coupon.getEdate()).getTime()){
                        this.vertifySellerCoupon(coupon.getCuid());
                        coupon.setType(3);
                        new Response(ResponseCode.SUCCESS, "成功",coupon).write(new HashMap<Class<?>, String[]>(){{
                            put(Coupon.class, new String[]{"getWay","description","cname","type"});
                        }});
                        return true;
                    }else{
                        new Response(ResponseCode.COUPON_NOT_USE, "优惠使用时间还未开始或已过期！",coupon).write(new HashMap<Class<?>, String[]>(){{
                            put(Coupon.class, new String[]{"sdate","edate"});
                        }});
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    /**
     * 验证粉丝卷
     * @author zhouxiaojian
     * @date 2017年03月08日
     */
    @Override
    public boolean virtifycoupon(SellerAccount sellerAccount, String codeid){
        try {
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy.MM.dd HH:mm");
            /*查询粉丝卷信息*/
            Coupon coupon=this.queryCouponByCodeidAndSellerid(codeid,sellerAccount.getSellerid());
            if(coupon!=null){
                    if(sdf.parse(coupon.getSdate()).getTime()<=new Date().getTime()&&new Date().getTime()<=sdf.parse(coupon.getEdate()).getTime()){
                        coupon.setType(4);
                        /*status：0未使用 1 锁定 2 已使用*/ 
                        if(coupon.getUseStatus()!=0){
                            if(coupon.getUseStatus()==2){
                                new Response(ResponseCode.COUPON_NOT_USE, "粉丝卷已使用！").write();
                                return true;
                            }else{
                                new Response(ResponseCode.COUPON_NOT_USE, "粉丝卷已锁定！").write();
                                return true;
                            }
                        }else{
                            //模拟下单
                            CouponDetail couponDetail = couponDetailDao.selectBySerial(codeid);
                            Bill bill = insertBill(sellerAccount, codeid, 3,couponDetail,null);//粉丝券类型为3
                            if(bill==null){
                                new Response(ResponseCode.COUPON_NOT_USE, "验单模拟下单错误！",coupon).write();
                                return true;
                            }
                            Map<String,String> params = new HashMap<>();
                            params.put("bid", bill.getBid().toString());
                            params.put("uid", bill.getUid()+"");
                            params.put("sellerid", bill.getSellerid()+"");
                            params.put("zdate", CalendarUtil.getDateString(bill.getZdate(), "yyyy-MM-dd HH:mm:ss"));
                            params.put("money", bill.getMoney()+"");
                            params.put("payment", "0");
                            params.put("cuser", bill.getCuser()+"");
                            //调用业务服务验单
                            ResponseData responseData = this.verifyConsumeOrder(params);
                            if(responseData.getState()!=0){
                                new Response(ResponseCode.COUPON_NOT_USE, "调用业务服务验单错误！",coupon).write();
                                return true;
                            }
                            this.vertifyCoupon(coupon.getCuid());
                            new Response(ResponseCode.SUCCESS, "验证成功！",coupon).write(new HashMap<Class<?>, String[]>(){{
                                put(Coupon.class, new String[]{"sellerName","description","type","cname","sdate","edate"});
                            }});
                            return true;
                        }
                    }else{
                        new Response(ResponseCode.COUPON_NOT_USE, "优惠使用时间还未开始或已过期！",coupon).write(new HashMap<Class<?>, String[]>(){{
                            put(Coupon.class, new String[]{"sdate","edate"});
                        }});
                        return true;
                    }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
    
    
    /**
     * 验证储值卡套餐券
     */
    @Override
    public boolean virtifySellerCard(SellerAccount sellerAccount, String codeid){
        try {
//            SimpleDateFormat sdf=new SimpleDateFormat("yyyy.MM.dd HH:mm");
            /*查询套餐信息*/
            SellerPackageGrant sellerPackageGrant = sellerPackageGrantDao.selectBySerial(codeid,sellerAccount.getSellerid());
            if(sellerPackageGrant==null){
                new Response(ResponseCode.FAILURE, "输入验证码错误！").write();
                return true;
            }
            if(sellerPackageGrant!=null){
                if(sellerPackageGrant.getUseStartTime().getTime()<=new Date().getTime()&&new Date().getTime()<=sellerPackageGrant.getUseEndTime().getTime()){
                    sellerPackageGrant.setType(5);//套餐券
                   // 使用状态，0未使用，1已锁定  2已使用
                    if(sellerPackageGrant.getUserStatus()==2){
                        new Response(ResponseCode.COUPON_NOT_USE, "套餐券已使用！").write();
                        return true;
                    }else if(sellerPackageGrant.getUserStatus()==1){
                        new Response(ResponseCode.COUPON_NOT_USE, "套餐券已锁定！").write();
                        return true;
                    }
                    else{
                        //模拟下单
                        
                        Bill bill = insertBill(sellerAccount, codeid, 4,null,sellerPackageGrant);//套餐券类型为4
                        if(bill==null){
                            new Response(ResponseCode.COUPON_NOT_USE, "验单模拟下单错误！",sellerPackageGrant).write();
                            return true;
                        }
                        Map<String,String> params = new HashMap<>();
                        params.put("bid", bill.getBid().toString());
                        params.put("uid", bill.getUid()+"");
                        params.put("sellerid", bill.getSellerid()+"");
                        params.put("zdate", CalendarUtil.getDateString(bill.getZdate(), "yyyy-MM-dd HH:mm:ss"));
                        params.put("money", bill.getMoney()+"");
                        params.put("payment", "0");
                        params.put("cuser", bill.getCuser()+"");
                        //调用业务服务验单
                        ResponseData responseData = this.verifyConsumeOrder(params);
                        if(responseData.getState()!=0){
                            new Response(ResponseCode.COUPON_NOT_USE, "调用业务服务验单错误！",sellerPackageGrant).write();
                            return true;
                        }
                        this.vertifyCoupon(sellerPackageGrant.getPid());
                        new Response(ResponseCode.SUCCESS, "验证成功！",sellerPackageGrant).write(
                                new HashMap<Class<?>, String[]>(){{
                            put(SellerPackageGrant.class, new String[]{"sellerName","description","type","cname","sdate","edate","payment"});
                        }}
                         );
                        return true;
                    }
                }else{
                    new Response(ResponseCode.COUPON_NOT_USE, "套餐券使用时间还未开始或已过期！",sellerPackageGrant).write(new HashMap<Class<?>, String[]>(){{
                        put(SellerPackageGrant.class, new String[]{"sdate","edate"});
                    }});
                    return true;
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
    
    /**
     * 获取商家直播分账配置
     * @param sellerId
     * @return
     */
    public ResponseData getSellerLiveLedgerMode (Integer sellerId) {
        log.info("访问getSellerLiveLedgerMode,【请求参数】：" +sellerId);
        ResponseData   responseData  = null;
        try {
            SellerVerifyOrderService.Client client =
                    ThriftBuilder.build(globalConfig.getThriftBusHost(),
                            Integer.parseInt(globalConfig.getThriftBusPort()), "SellerVerifyOrderService",
                            SellerVerifyOrderService.Client.class);
            ThriftBuilder.open();
            responseData = client.getSellerLiveLedgerMode(sellerId);
        } catch (Exception e) {
            log.error("获取商家直播分账配置异常！", e);
        } finally {
            ThriftBuilder.close();
        }
        return responseData;
    }
    
    
    
    /**
     * 商家扫码下单验单
     * @param params
     * @return
     */
    public ResponseData verifyConsumeOrder  (Map <String,String> params) {
        log.info("访问verifyConsumeOrder,【请求参数】：" +params);
        ResponseData   responseData  = null;
        try {
            SellerVerifyOrderService.Client client =
                    ThriftBuilder.build(globalConfig.getThriftBusHost(),
                            Integer.parseInt(globalConfig.getThriftBusPort()), "SellerVerifyOrderService",
                            SellerVerifyOrderService.Client.class);
            ThriftBuilder.open();
            responseData = client.verifyConsumeOrder(params);
        log.info("访问verifyConsumeOrder,返回参数：" +responseData.getState()+"--------msg---"+responseData.getMsg()+"--map-"+responseData.getResultMap());    
        } catch (Exception e) {
            log.error("访问商家扫码下单验单异常！", e);
        } finally {
            ThriftBuilder.close();
        }
        return responseData;
    }
    
    
    /**
     *  模拟下单插入数据到t_bill
     * @param sellerid
     * @return
     */
    public Bill insertBill(SellerAccount sellerAccount,String codeid,Integer couponType,CouponDetail couponDetail,SellerPackageGrant sellerPackageGrant){
        //模拟下单插入数据到t_bill
        //访问获取商家直播分账配置
        ResponseData responseData = this.getSellerLiveLedgerMode(sellerAccount.getSellerid());
        if(responseData.getState()!=0 || responseData.getResultMapSize()==0){
            return null;
        };
        //券详情
        if(couponDetail==null && sellerPackageGrant==null){
            return null;
        }
        Bill bill =new Bill();
        String orderno = redisService.createOrderNumber();
        bill.setBid(Long.valueOf(orderno));//订单号
        bill.setLiveLedger(responseData.getResultMap().get("liveLedger")==null?0:Integer.valueOf(responseData.getResultMap().get("liveLedger")));//当前是否采用直播分账 0 没采用 1 采用
        bill.setLiveLedgerStyle(responseData.getResultMap().get("ledgerStyle")==null?null:Integer.valueOf(responseData.getResultMap().get("ledgerStyle")));//分账方式 0  自动 1手动
        bill.setLedgerMode(responseData.getResultMap().get("ledgerMode")==null?null:Integer.valueOf(responseData.getResultMap().get("ledgerMode")));//分账模式 1全部金额分账2 仅粉丝券金额分账
        if(responseData.getResultMap().get("ledgerRatio")!=null && responseData.getResultMap().get("ledgerRatio")!=""){
            bill.setLiveLedgerRatio(Double.valueOf(responseData.getResultMap().get("ledgerRatio")));//商家分账比例
        }
//        bill.setUidRelationChain(responseData.getResultMap().get("uidRelationChain"));//用户会员等级关系链
        bill.setCodeid(Integer.valueOf(codeid));
        bill.setSellerid(sellerAccount.getSellerid());
        bill.setStatus(0);
        bill.setReduction(BigDecimal.ZERO);
        Map<String,String> userMap = new HashMap<>();
        Seller seller = sellerDao.findBySellerid(sellerAccount.getSellerid());
        if(couponDetail!=null){
            bill.setMoney(couponDetail.getDenomination());//消费总金额
            bill.setUid(couponDetail.getUid());
            bill.setCuser(couponDetail.getDenomination());//优惠券支付金额
            bill.setCdenom(couponDetail.getDenomination());//优惠卷面额总数
            bill.setPhoneid(couponDetail.getPhone());
            userMap.put("uid", couponDetail.getUid()+"");
        }else{
            bill.setMoney(sellerPackageGrant.getLedgerAmount());//消费总金额
            bill.setUid(sellerPackageGrant.getUid());
            bill.setCuser(sellerPackageGrant.getLedgerAmount());//优惠券支付金额
            bill.setCdenom(sellerPackageGrant.getLedgerAmount());//优惠卷面额总数
            bill.setPhoneid(sellerPackageGrant.getPhone());
            userMap.put("uid", sellerPackageGrant.getUid()+"");
            
            sellerPackageGrant.setSellerName(seller.getSellername());
            sellerPackageGrant.setSdate(CalendarUtil.getDateString(sellerPackageGrant.getUseStartTime(), "yyyy.MM.dd HH:mm")); 
            sellerPackageGrant.setEdate(CalendarUtil.getDateString(sellerPackageGrant.getUseEndTime(), "yyyy.MM.dd HH:mm")); 
            SellerPackage sellerPackage = sellerPackageDao.selectByPrimaryKey(sellerPackageGrant.getPid());
            sellerPackageGrant.setCname(sellerPackage.getTitle());
            sellerPackageGrant.setDescription(sellerPackage.getTitle());
            sellerPackageGrant.setPayment(sellerPackageGrant.getLedgerAmount().doubleValue());
        }
        bill.setAid(sellerAccount.getSellerid());
        bill.setFullname(sellerAccount.getFullname());
        bill.setSdate(new Date());//下单时间
        bill.setPaytype("1000011");
        
        bill.setSellername(seller.getSellername());//商家名称
        bill.setBaseagio(seller.getAgio());//商家折扣
        //查询用户信息
        ResponseData user = this.getUserMsg(userMap);
        if(user.getState()==0){
          bill.setNname(user.getResultMap().get("nname"));
          bill.setPhoneid(user.getResultMap().get("phone"));
          bill.setGenussellerid(Integer.valueOf(user.getResultMap().get("genussellerid")));
          bill.setGenusname(user.getResultMap().get("genusname"));
          bill.setUidRelationChain(user.getResultMap().get("uidRelationChain"));
          if(StringUtils.isNotBlank(user.getResultMap().get("jointid"))){
              bill.setJointid(Integer.valueOf((user.getResultMap().get("jointid"))));
          }
          bill.setCorporate(user.getResultMap().get("corporate"));
        }
        bill.setArea(seller.getArea()); 
        bill.setZoneid(seller.getZoneid());
        bill.setXmerUid(seller.getUid());
        bill.setZdate(new Date());
        bill.setCouponType(couponType);//优惠券类型 1:平台 2:商户 3:粉丝 4:套餐
        int billFlag = billDao.insertSelective(bill);
        if(billFlag==0){
           return null; 
        }
        //插入优惠券关系表
        if(couponDetail!=null){
            CouponRelation couponRelation =new CouponRelation();
            couponRelation.setBid(bill.getBid());
            couponRelation.setCdenom(couponDetail.getDenomination());;
            couponRelation.setCtype(new Byte(couponType.toString()));//优惠券类型
            couponRelation.setCdid(couponDetail.getCdid());
            couponRelation.setSerial(codeid);
            int relationFlag= couponRelationDao.insertSelective(couponRelation);
            if(relationFlag==0){
                return null; 
            }
        }else{
            sellerPackageGrant.setBid(bill.getBid());
            int flag = sellerPackageGrantDao.updateByPrimaryKey(sellerPackageGrant);
            if(flag==0){
                return null;  
            }
        }
        return bill;
    }
    
    
    /**
     * 获取商户充值分账列表
     * @param params
     * @return
     */
    public ResponseList getSellerRechargeLedgerRecord(Map <String,String> params) {
        ResponseList responseList = null;
        try {
            XmniaoWalletService.Client client =
                    ThriftBuilder.build(globalConfig.getThriftPayHost(),
                            Integer.parseInt(globalConfig.getThriftPayPort()),
                            "XmniaoWalletService", XmniaoWalletService.Client.class);
    
            ThriftBuilder.open();
            responseList = client.getSellerRechargeLedgerRecord(params);
    
        } catch (Exception e) {
            log.error("调用支付系统接口获取钱包的分账记录列表异常！", e);
    
        } finally {
            ThriftBuilder.close();
        }
        return responseList;
    }
    
    /**
     * 是否普通商家
     * @param sellerAccount
     * @return
     */
    @Override
    public Map<Object, Object> isNormal(SellerAccount sellerAccount) {
        DebitcardSeller debitcardSeller =  debitcardSellerDao.selectBySellerId(sellerAccount.getSellerid().toString());
        Map<Object, Object> resultMap =new HashMap<>();
        if(debitcardSeller==null){
            resultMap.put("code", ResponseCode.FAILURE);
            resultMap.put("msg", "此商户非普通商户");
            return resultMap;
        }
        resultMap.put("code", ResponseCode.SUCCESS);
        resultMap.put("msg", "普通商户");
        return resultMap;
    }
    
}
