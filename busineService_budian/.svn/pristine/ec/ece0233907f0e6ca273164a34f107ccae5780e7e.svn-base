package com.xmniao.service.quartz;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.alibaba.fastjson.JSON;
import com.xmniao.dao.common.CommonServiceDao;
import com.xmniao.dao.order.BillFreshSubDao;
import com.xmniao.dao.seller.SellerDao;
import com.xmniao.domain.ledger.MallLedgerBean;
import com.xmniao.domain.order.BillFreshSub;
import com.xmniao.domain.seller.SellerBean;
import com.xmniao.thrift.ledger.ResponseSplitMap;
import com.xmniao.urs.dao.UrsDao;

/**
 * 
 * 
 * 项目名称：busineService
 * 
 * 类名称：OnlineMallQuertzService
 * 
 * 类描述：线上积分商城订单分账任务 
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年6月22日 下午7:51:03 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class OnlineMallQuertzService {
	
    /*
     * 日志记录
     */
    private final Logger log = Logger.getLogger(OnlineMallQuertzService.class);
    
    @Autowired
    private BillFreshSubDao billFreshSubDao;
    
    @Resource(name="commonServiceImpl")
    private CommonServiceDao commonService;
    
    @Autowired
    private StringRedisTemplate redisTemplate;
    
    @Autowired
    private UrsDao ursDao;
    
    @Autowired
    private SellerDao sellerDao;
    
    @Resource(name="onlineKey")
    private String key;
    
    @Resource(name="lastDay")
    private String lastDay;
    
	/*
	 * 自动扫描已支付订单的分账
	 */
	public void appleyOnlineOrderLedger(){
		log.info("定时扫描未分账的线上积分商城订单开始......");
		int count=0,success=0,fail=0;
		try{
			List<BillFreshSub> subBillList = billFreshSubDao.getLedgerBillList();
			count = subBillList.size();
			if(count!=0){
				for(BillFreshSub subBill:subBillList){
					log.info("开始分账订单【"+subBill.getSubOrderSn()+"】的线上积分商品订单");
					/*
					 * 1. 获取订单中商品的总采购价
					 * 2. 调用分账计算接口
					 * 3. 组装数据
					 * 3.1 获取用户所属商家信息
					 * 3.2 组装其他数据
					 * 4. 写入分账队列
					 * 5. 更新订单状态为分账处理中
					 */
					try{
						if(null != getCashCoupon(subBill.getOrderSn()) || subBill.getCoinAmount().compareTo(BigDecimal.ZERO)!=0){//鸟币支付只有 鸟币全额支付  和鸟币与积分混合支付两种  都不参与分账
							log.info("使用了优惠券的订单或者是鸟币支付");
							//更新该订单
							BillFreshSub mdyBill = new BillFreshSub();
							mdyBill.setOrderSn(subBill.getOrderSn());
							mdyBill.setSubOrderSn(subBill.getSubOrderSn());
							mdyBill.setHstatus(9);
							mdyBill.setCommission(null);
							mdyBill.setModifyTime(new Date());
							billFreshSubDao.modifySubBillInfo(mdyBill);
							success++;
						}else{
							log.info("没使用优惠券的订单");
						Map<String,Object> priceMap = this.getBillPurchasePrice(subBill.getOrderSn(),subBill.getSubOrderSn());
						double allPurchase = ((BigDecimal) priceMap.get("purchasePrice")).doubleValue();
						double allCash = ((BigDecimal) priceMap.get("cash")).doubleValue();
						log.info("获取商品价格成功");
						Map<String,String> selMap = new HashMap<String,String>();
						selMap.put("uid", String.valueOf(subBill.getUid()));
						
						Map<String,Object> ursMap = ursDao.getUrsAscription(selMap); 
						boolean brseller = false;
						if(ursMap!=null && ursMap.get("genussellerid") != null){
							brseller = true;
							if(!isSeller((Integer)ursMap.get("genussellerid"))){
								brseller = false;
							}
						}
						log.info("用户所属信息成功");
						// 根据 采购价	 销售价  用户支付金额计算分账
						//double purchase =  allPurchase.doubleValue();
						//double total = PreciseComputeUtil.mul(purchase, 1.1,2);//subBill.getTotalAmount().doubleValue();
						//double ledger = (subBill.getTotalAmount().subtract(subBill.getIntegralAmount())).doubleValue();
						
						ResponseSplitMap responseSplitMap = commonService.onlineMallLedgerFormula(allPurchase,allCash,allCash,brseller);
						//ResponseSplitMap responseSplitMap = commonService.onlineMallLedgerFormula(8000, 8800, 8800);
						if(responseSplitMap != null){
//							Map<String,String> selMap = new HashMap<String,String>();
//							selMap.put("uid", String.valueOf(subBill.getUid()));
//							Map<String,Object> ursMap = ursDao.getUrsAscription(selMap); 
							
							MallLedgerBean mallLedgerBean = getMallLedgerBean(subBill, responseSplitMap,ursMap);
							
							log.info("发送的请求分账信息为:"+mallLedgerBean);
							redisTemplate.opsForList().leftPush(key, JSON.toJSONString(mallLedgerBean));
							
							//更新该订单
							BillFreshSub mdyBill = new BillFreshSub();
							mdyBill.setOrderSn(subBill.getOrderSn());
							mdyBill.setSubOrderSn(subBill.getSubOrderSn());
							mdyBill.setHstatus(10);
							mdyBill.setCommission(JSON.toJSONString(responseSplitMap.getData()));
							mdyBill.setModifyTime(new Date());
							billFreshSubDao.modifySubBillInfo(mdyBill);
							success++;
						}else{
							log.error("线上积分商城子订单【"+subBill.getSubOrderSn()+"】分账计算明细失败...");
							fail++;
						}
						}
						
					} catch(Exception e){
						log.error("订单号【"+subBill.getOrderSn()+"】的线上积分商城订单申请分账异常",e);
						fail++;
					}
				}
			}else{
				log.info("本次扫描没有未开始分账的线上积分商城订单");
			}
		}catch(Exception e){
			log.error("自动扫描未分账的线上积分商城订单异常",e);
		}
		log.info("定时扫描未分账的线上积分商城订单结束，处理申请分账结果【总数："+count+",成功："+success+",失败："+fail+"】");
	}
	
	/*
	 * 自动扫描已发货，超时未确认收货的订单
	 */
	public void onlineOrderVerify(){
		log.info("定时扫描未验单的线上积分商城订单开始......");
		Map<String,Object> map = new HashMap<>();
		map.put("operator", "(0) 平台自动收货");
		map.put("memo", "平台自动收货");
		map.put("sysdate",new SimpleDateFormat("yyyy-MM-dd HH:mm:dd").format(new Date()));
		map.put("lastDay", lastDay);
		int result = billFreshSubDao.updateOutTimeOrder(map);
		log.info("自动更新"+result+"条订单");
		log.info("定时扫描未验单的线上积分商城订单结束......");
	}
	
	/*
	 * 获取订单采购价
	 */
	private Map<String,Object> getBillPurchasePrice(String ordersn,String subOrdersn) throws Exception{
		Map<String,Object> resultMap = new HashMap<String,Object>();
		BigDecimal purchasePrice = new BigDecimal(0.00);
		BigDecimal cash = new BigDecimal(0.00);
		BigDecimal purtemp=null; 
		BigDecimal cashtemp=null;
		Map<String,Object> parMap = new HashMap<String,Object>();
		parMap.put("bid", ordersn);
		parMap.put("subBid", subOrdersn);
		List<Map<String,Object>> priceList =  billFreshSubDao.getBillPurchasePrice(parMap);
		log.info("订单内商品数量及单品采购价："+priceList);
		for(Map<String,Object> price:priceList){
			BigDecimal t1=(BigDecimal)price.get("purchasePrice");
			BigDecimal c1=(BigDecimal)price.get("cash");
			int m1= (int)price.get("wareNum");
			BigDecimal t2 = new BigDecimal(m1);
			purtemp = t1.multiply(t2);
			purchasePrice = purchasePrice.add(purtemp);
			
			BigDecimal c2 = new BigDecimal(m1);
			cashtemp = c1.multiply(c2);
			cash = cash.add(cashtemp);
		}
		resultMap.put("purchasePrice", purchasePrice);
		resultMap.put("cash", cash);
		return resultMap;
	}
	
	/*
	 * 获取线上积分商城订单的申请分账参数
	 */
	private MallLedgerBean getMallLedgerBean(BillFreshSub subBill,ResponseSplitMap responseSplitMap,Map<String,Object> ursMap){

		MallLedgerBean mallLedgerBean = new MallLedgerBean();
		mallLedgerBean.setbSellerId((Integer)ursMap.get("genussellerid"));
		mallLedgerBean.setbSellerMoney(responseSplitMap.getData().get("bSellerMoney"));
		mallLedgerBean.setbSellerName((String) ursMap.get("genusname"));
		mallLedgerBean.setCreateDate(new Date());
		mallLedgerBean.setDealType(1);//线上
		mallLedgerBean.setIntegral(subBill.getIntegralAmount().doubleValue());
		mallLedgerBean.setMemberBackMoney(0);
		mallLedgerBean.setMemberId(Integer.valueOf((String.valueOf(subBill.getUid()))));
		mallLedgerBean.setMemberName(subBill.getNname());
		mallLedgerBean.setMoney(subBill.getTotalAmount().doubleValue());
		mallLedgerBean.setOrderId(String.valueOf(subBill.getSubOrderSn()));
		mallLedgerBean.setPayCode(String.valueOf(subBill.getNumber()));
		mallLedgerBean.setPayId(String.valueOf(subBill.getPayid()));
		mallLedgerBean.setPayment(subBill.getCashAmount().doubleValue());
		mallLedgerBean.setPayType(subBill.getPaytype());
//		mallLedgerBean.setSellerArea(0);
//		mallLedgerBean.setSellerAreaMoney(responseSplitMap.getData().get("sellerAreaMoney"));
//		mallLedgerBean.setSellerAreaName("");
//		mallLedgerBean.setSellerAreaId(billBargain.getConsumeJointid());
//		mallLedgerBean.setSellerBackMoney(responseSplitMap.getData().get("sellerBackMoney"));
//		mallLedgerBean.setSellerId(billBargain.getSellerid());
//		mallLedgerBean.setSellerIndustry(sellerIndustry);
//		mallLedgerBean.setSellerName(subBill.getSellername());
		mallLedgerBean.setXmniaoMoney(responseSplitMap.getData().get("xmniaoMoney"));
		return mallLedgerBean;
	}
	
	//是否有商家
	boolean isSeller(Integer sellerid){
		//检测商家是否正常
		SellerBean sellerBean = sellerDao.getSellerInfo(sellerid);
		if(sellerBean!=null && sellerBean.getIsonline() !=null && sellerBean.getIsonline()==1){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 是否是优惠券支付的水
	 * @Title: isWaterOrder 
	 * @Description:
	 */
	private Map<String,Object> getCashCoupon(String ordersn){
		return billFreshSubDao.getCashCoupon(ordersn);
	}
}
