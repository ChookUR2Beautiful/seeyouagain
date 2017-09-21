package com.xmniao.service.quartz;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.alibaba.fastjson.JSON;
import com.xmniao.dao.common.CommonServiceDao;
import com.xmniao.dao.order.BillBargainDao;
import com.xmniao.dao.seller.SellerDao;
import com.xmniao.domain.ledger.MallLedgerBean;
import com.xmniao.domain.order.BillBargain;
import com.xmniao.domain.seller.SellerBean;
import com.xmniao.thrift.ledger.ResponseSplitMap;

/**
 * 
 * 
 * 项目名称：busineService
 * 
 * 类名称：OfflineMallQuertzService
 * 
 * 类描述：线下积分商城订单分账任务 
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年6月22日 下午7:50:35 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class OfflineMallQuertzService {
    /*
     * 日志记录
     */
    private final Logger log = Logger.getLogger(OfflineMallQuertzService.class);
    
    @Autowired
    private BillBargainDao billBargainDao;
    
    @Autowired
    private SellerDao sellerDao;
    
    @Resource(name="commonServiceImpl")
    private CommonServiceDao  commonService;
    
    @Autowired
    private StringRedisTemplate redisTemplate;
    
    @Resource(name="offlineKey")
    private String key;
	/*
	 * 自动扫描已支付订单的分账
	 */
	public void appleyOfflineOrderLedger(){
		log.info("定时扫描未分账的线下积分商城订单开始......");
		try{
		//offlineOrderVerify();//更新订单状态，自动验单
		BillBargain selBillBargain = new BillBargain();
		selBillBargain.setBstatus(1);
		selBillBargain.setHstatusStr("0,11");//描述 未分账及手动验单的订单
		List<BillBargain> billBargainList = billBargainDao.getBillBargainList(selBillBargain);
		for(BillBargain billBargain:billBargainList){
			log.info("开始分账订单【"+billBargain.getBid()+"】的线下积分商品订单");
			Map<String,Object> bargainProduct = billBargainDao.getBargainProduct(String.valueOf(billBargain.getBpid()));
			if(bargainProduct==null){
				log.error("找不到订单【"+billBargain.getBid()+"】里编号【"+billBargain.getBpid()+"】的线下积分商品");
				continue;
			}

			boolean brseller = billBargain.getGenussellerid()==null?false:true;
			boolean bsellerArea = billBargain.getConsumeJointid()==null?false:true;
			if(bsellerArea){	
				if(!isJoint(billBargain.getConsumeJointid())){
					bsellerArea=false;	
				}
			}
			if(brseller){	
				if(!isSeller(billBargain.getGenussellerid())){
					brseller=false;	
				}
			}
			log.info("订单【"+billBargain.getBid()+"】分账计算参数："+bargainProduct.get("purchasePrice")+","+bargainProduct.get("cash")+","+brseller+","+bsellerArea);
			double purchaseMoney = ((BigDecimal)bargainProduct.get("purchasePrice")).doubleValue();
			double sellMoney = ((BigDecimal)bargainProduct.get("cash")).doubleValue();
			ResponseSplitMap responseSplitMap = commonService.offlineMallLedgerFormula(purchaseMoney,sellMoney,brseller,bsellerArea);
			if(responseSplitMap!=null){
				MallLedgerBean malllLedgerBean = getMallLedgerBean(billBargain,responseSplitMap);
				log.info("发送的请求分账信息为:"+malllLedgerBean);
				redisTemplate.opsForList().leftPush(key, JSON.toJSONString(malllLedgerBean));
				
				//更新该订单
				selBillBargain = new BillBargain();
				selBillBargain.setHstatus(10);
				selBillBargain.setBid(billBargain.getBid());
				selBillBargain.setCommission(JSON.toJSONString(responseSplitMap.getData()));
				
				billBargainDao.modifyBillBargainInfo(selBillBargain);
			}
		}
		}catch(Exception e){
			log.error("线下积分商品订单定时申请分账异常",e);
		}
		
		log.info("定时扫描未分账的线下积分商城订单结束......");
	}
	
	/*
	 * 自动扫描已支付，商家超时未验单的订单
	 */
	public void offlineOrderVerify(){
		log.info("定时扫描未验单的线下积分商城订单开始......");
		try{
			StringBuilder sb = new StringBuilder();
			BillBargain selBillBargain = new BillBargain();
			selBillBargain.setStatus(1);
			selBillBargain.setBstatus(0);
			List<BillBargain> billBargainList = billBargainDao.getBillBargainList(selBillBargain);
			for(BillBargain billBargain:billBargainList){
				sb.append(billBargain.getBid()).append(",");
			}
			if(sb.length()>0){
				selBillBargain = new BillBargain();
				String bids=sb.substring(0, sb.length()-1);
				selBillBargain.setBids(bids);
				selBillBargain.setBstatus(1);
				log.info("本次扫描到的未验单的线下积分商城订共有"+billBargainList.size()+"条，依次是："+bids);
				billBargainDao.modifyBillBargainInfoByBatch(selBillBargain);
			}else{
				log.info("本次没有未验单的线下积分商城订单");
			}
			
		}catch(Exception e){
			log.error("扫描未验单的线下积分订单异常",e);
		}
		log.info("定时扫描未验单的线下积分商城订单结束......");
	}
	
	private MallLedgerBean getMallLedgerBean(BillBargain billBargain,ResponseSplitMap responseSplitMap){

		MallLedgerBean mallLedgerBean = new MallLedgerBean();
		mallLedgerBean.setbSellerId(billBargain.getGenussellerid());
		mallLedgerBean.setbSellerMoney(responseSplitMap.getData().get("bSellerMoney")==null?0:responseSplitMap.getData().get("bSellerMoney"));
		mallLedgerBean.setbSellerName(billBargain.getGenusname());
		mallLedgerBean.setCreateDate(new Date());
		mallLedgerBean.setDealType(2);//线下
		mallLedgerBean.setIntegral(billBargain.getIntegral());
		mallLedgerBean.setMemberBackMoney(0);
		mallLedgerBean.setMemberId(billBargain.getUid());
		mallLedgerBean.setMemberName(billBargain.getUname());
		mallLedgerBean.setMoney(billBargain.getPrice().doubleValue());
		mallLedgerBean.setOrderId(String.valueOf(billBargain.getBid()));
		mallLedgerBean.setPayCode(String.valueOf(billBargain.getCodeid()));
		mallLedgerBean.setPayId(String.valueOf(billBargain.getPayid()));
		mallLedgerBean.setPayment(billBargain.getAmount()==null?0:billBargain.getAmount().doubleValue());
		mallLedgerBean.setPayType(Integer.parseInt(billBargain.getPaymentType()));
//		mallLedgerBean.setSellerArea(0);
		mallLedgerBean.setSellerAreaMoney(responseSplitMap.getData().get("sellerAreaMoney")==null?0:responseSplitMap.getData().get("sellerAreaMoney"));
//		mallLedgerBean.setSellerAreaName("");
		mallLedgerBean.setSellerAreaId(billBargain.getConsumeJointid());
		mallLedgerBean.setSellerBackMoney(responseSplitMap.getData().get("sellerBackMoney")==null?0:responseSplitMap.getData().get("sellerBackMoney"));
		mallLedgerBean.setSellerId(billBargain.getSellerid());
//		mallLedgerBean.setSellerIndustry(sellerIndustry);
		mallLedgerBean.setSellerName(billBargain.getSellername());
		mallLedgerBean.setXmniaoMoney(responseSplitMap.getData().get("xmniaoMoney"));
		return mallLedgerBean;
	}
	
	//是否有合作商
	boolean isJoint(Integer jointid){
		//检测合作商是否正常
		Map<String,Object> jointInfo = sellerDao.getJointInfo(jointid);
		if(jointInfo!=null && jointInfo.get("status")!=null && (int)jointInfo.get("status")==1){
			return true;
		}else{
			return false;
		}
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
}
