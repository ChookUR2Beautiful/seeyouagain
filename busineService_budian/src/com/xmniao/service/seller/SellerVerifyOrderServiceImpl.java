/**    
 * 文件名：SellerVerifyOrderImpl.java    
 *    
 * 版本信息：    
 * 日期：2017年3月7日    
 * Copyright (c) 广东寻蜜鸟网络技术有限公司  2017     
 * 版权所有    
 *    
 */
package com.xmniao.service.seller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.dao.coupon.CouponDao;
import com.xmniao.dao.order.OrderServiceDao;
import com.xmniao.dao.seller.SellerLiveLedgerDao;
import com.xmniao.dao.sellerPackage.SellerPackageDao;
import com.xmniao.domain.coupon.CouponDetail;
import com.xmniao.domain.coupon.CouponRelation;
import com.xmniao.domain.order.BillBean;
import com.xmniao.domain.order.OrdRecordBean;
import com.xmniao.domain.seller.SellerLiveLedger;
import com.xmniao.service.order.OrderServiceImpl;
import com.xmniao.thrift.busine.common.FailureException;
import com.xmniao.thrift.busine.common.ResponseData;
import com.xmniao.thrift.busine.common.XmnOrderParamV2;
import com.xmniao.thrift.busine.seller.SellerVerifyOrderService;
/**
 * 
 * 项目名称：busineService
 * 
 * 类名称：SellerVerifyOrderImpl
 * 
 * 类描述： 
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2017年3月7日 上午11:33:32 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

@Service("sellerVerifyOrderServiceImpl")
public class SellerVerifyOrderServiceImpl implements SellerVerifyOrderService.Iface{
	
	private final Logger log = Logger.getLogger(SellerVerifyOrderServiceImpl.class); 
	
	@Autowired
	private SellerLiveLedgerDao sellerLiveLedgerDao;
	
	@Autowired
	private OrderServiceDao orderDao;
	
    @Autowired
    private CouponDao couponDao;
	
    @Autowired
    private SellerPackageDao sellerPackageDao;
    
	@Autowired
	private OrderServiceImpl orderService;
	
	@Override
	public ResponseData getSellerLiveLedgerMode(int sellerid)
			throws FailureException, TException {
		Map<String,String> resultMap = new HashMap<>();
		try{
		Date now = new Date();
		SellerLiveLedger liveLedger = sellerLiveLedgerDao.getSellerLiveLedger(new SellerLiveLedger(sellerid));
		if(liveLedger==null){
			resultMap.put("liveLedger", "0");
		}else{
			Date sDate = liveLedger.getStartDate();
			Date eDate = liveLedger.getEndDate();
			if(sDate!=null && now.before(sDate)){
				resultMap.put("liveLedger", "0");
			} else if(eDate != null && now.after(eDate)){
				resultMap.put("liveLedger", "0");
			} else{
				resultMap.put("liveLedger", "1");
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			resultMap.put("ledgerStyle", liveLedger.getLedgerStyle()+"");
			resultMap.put("ledgerMode", liveLedger.getLedgerMode()+"");
			resultMap.put("ledgerRatio", liveLedger.getLedgerRatio()+"");
			resultMap.put("startDate", liveLedger.getStartDate()==null?"":sdf.format(liveLedger.getStartDate()));
			resultMap.put("endDate", liveLedger.getEndDate()==null?"":sdf.format(liveLedger.getEndDate()));
		}
		}catch(Exception e){
			log.error("查询商家直播分账设置异常",e);
			return new ResponseData(1, "查询异常", null);
		}
		return new ResponseData(0, "成功", resultMap);
	}

	
	@Override
	public ResponseData verifyConsumeOrder(Map<String, String> paramMap)
			throws FailureException, TException {
		log.info("verifyConsumeOrder:"+paramMap);
		BillBean billBean = orderDao.getBillBean(paramMap.get("bid"));
		if(billBean == null 
			|| !paramMap.get("uid").equals(billBean.getUid()+"")
			|| !paramMap.get("sellerid").equals(billBean.getSellerid()+"")){
			return new ResponseData(1,"找不到对应订单",null);
		}
		if(paramMap.get("zdate")==null){
			paramMap.put("zdate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		}
		
		BigDecimal cuser = new BigDecimal(paramMap.get("cuser"));
		BigDecimal money = new BigDecimal(paramMap.get("money"));
		if(cuser.compareTo(money)!=0){
			return new ResponseData(1,"订单金额错误",null);
		}
		
		try{

			Integer couponType = billBean.getCouponType();
			if(couponType!=null){
				if(couponType==3){
					List<CouponRelation> couponRelationList = couponDao.getCouponRelation(billBean.getBid());
					if(couponRelationList!=null && couponRelationList.size()>0){
						CouponRelation couponRelation = couponRelationList.get(0);
						CouponDetail couponDetail = couponDao.getCouponDetail(couponRelation.getCdid());
						if(couponDetail!=null && couponDetail.getUserStatus()==0){
							if(couponDetail.getDenomination().compareTo(cuser)>=0){	//订单金额没问题
								//do...
								XmnOrderParamV2 param = getXmnOrderParamV2ByMap(paramMap,billBean);
								orderService.updateXmnOrderInfoV2(param);
								sellerVerified(billBean);
								return new ResponseData(0,"成功",null);
							}
						}
					}
				}else if(couponType==4){
					Map<String,Object> reqMap = new HashMap<>();
					reqMap.put("bid", paramMap.get("bid"));
					Map<String,Object> grantMap = sellerPackageDao.getSellerPackageGrantByConsume(reqMap);
					if(grantMap !=null && grantMap.get("userStatus").toString().equals("0")){
						if(((BigDecimal)grantMap.get("ledgerAmount")).compareTo(cuser)>=0){//订单金额没问题
							//do ...
							XmnOrderParamV2 param = getXmnOrderParamV2ByMap(paramMap,billBean);
							orderService.updateXmnOrderInfoV2(param);
							sellerVerified(billBean);
							return new ResponseData(0, "成功", null);
						}
					}
				}
			}
		}catch(Exception e){
			log.error("更新订单失败",e);
		}
		
		return new ResponseData(1,"订单验证失败",null);
	}

	private XmnOrderParamV2 getXmnOrderParamV2ByMap(Map<String,String> map,BillBean bill){
		XmnOrderParamV2 orderParamV2 = new XmnOrderParamV2();
		orderParamV2.setBid(map.get("bid"));
		orderParamV2.setStatus("1");
		orderParamV2.setZdate(map.get("zdate"));
		orderParamV2.setUid(map.get("uid"));
		orderParamV2.setPhoneid(bill.getPhoneid());
		orderParamV2.setPayid("");
		orderParamV2.setNumber("");
		orderParamV2.setThirdUid("");
		orderParamV2.setPaytype(bill.getPaytype());
		orderParamV2.setOrdertype("1");
		orderParamV2.setIsbalance("0");
		orderParamV2.setMoney(map.get("money"));
		orderParamV2.setPreferential(map.get("cuser"));
		orderParamV2.setPayamount("0");
		orderParamV2.setSamount("0");
		orderParamV2.setCommision("0");
		orderParamV2.setProfit("0");
		orderParamV2.setGiveMoney("0");
		orderParamV2.setLiveCoin("0");
		orderParamV2.setLiveCoinArrivedMoney("0");
		orderParamV2.setLiveCoinRatio("0");
		orderParamV2.setIntegral("0");
		orderParamV2.setDiscounts(bill.getBaseagio()+"");//商家折扣
		orderParamV2.setBase("0");//基数
		orderParamV2.setSellerCoin("0");//商家专享鸟币
		orderParamV2.setLedgertype("2");
		return orderParamV2;
	}
	
	public void sellerVerified(BillBean billBean){
		log.info("业务服务模拟商户端进行验单");
		billBean.setYdate(billBean.getZdate());
		billBean.setStatus(9);
		billBean.setLdate(billBean.getZdate());
		orderDao.modifyBillVerify(billBean);
		OrdRecordBean record = new OrdRecordBean();
		record.setBid(billBean.getBid()+"");
		record.setStatus(6);
		record.setRemarks(null);
		record.setExplains("创建并验证订单");
		orderDao.insertBillRecord(record);
	}
}
