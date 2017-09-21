package com.xmniao.service.quartz;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.alibaba.fastjson.JSON;
import com.xmniao.common.DateUtil;
import com.xmniao.dao.common.CommonServiceDao;
import com.xmniao.dao.seller.JointDao;
import com.xmniao.dao.seller.SellerDao;
import com.xmniao.dao.xmer.SaasOrderDao;
import com.xmniao.domain.order.RSaasSoldOrderLedger;
import com.xmniao.domain.order.SaasOrderBean;
import com.xmniao.domain.order.SaasSoldOrderBean;
import com.xmniao.domain.seller.JointBean;
import com.xmniao.domain.seller.JointSaasLedgerBean;
import com.xmniao.domain.seller.SellerBean;
import com.xmniao.thrift.ledger.ResponseSplitMap;
import com.xmniao.urs.dao.UrsDao;
import com.xmniao.urs.dao.XmerDao;


/**
 * 
 * 
 * 项目名称：busineService
 * 
 * 类名称：SaasLedgerQuertzService
 * 
 * 类描述：商家saas签约分账失败自动 扫描重新分账任务 
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年7月16日 上午9:51:03 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class SaasLedgerQuertzService {
	
    /*
     * 日志记录
     */
    private final Logger log = Logger.getLogger(SaasLedgerQuertzService.class);
    
    @Autowired
    private SaasOrderDao saasOrderDao;
    
    @Resource(name="commonServiceImpl")
    private CommonServiceDao commonService;
    
    @Autowired
    private StringRedisTemplate redisTemplate;
    
    @Autowired
    private UrsDao ursDao;
    
    @Autowired
    private XmerDao xmerDao;
    
    @Autowired
    private SellerDao sellerDao;
    
    @Autowired
    private JointDao jointDao;
    
    @Resource(name="saasSoldKey")
    private String saasLedgerKey;
	/*
	 * 自动扫描已支付订单的分账
	 */
	public void appleySaasOrderLedger(){
		log.info("定时扫描申请分账失败的saas签约订单开始......");
		int count=0,success=0,fail=0;
		try{
			/*
			 * 获取上审核通过，且尚未开始分账的订单
			 */
			Map<String,String> selMap = new HashMap<String,String>();
			selMap.put("hstatus", "0");
			selMap.put("status", "1");
			List<SaasSoldOrderBean> saasList = saasOrderDao.getResplitSaasSoldOrder(selMap);
			if(saasList!=null && saasList.size()>0){
				for(SaasSoldOrderBean saasOrder : saasList){
					SellerBean sellerBean = sellerDao.getSellerInfo(saasOrder.getSellerid());
					saasOrderLedger(saasOrder, sellerBean);
				}
			}else{
				log.info("本次扫描没有申请分账失败的saas签约订单");
			}
		}catch(Exception e){
			log.error("自动扫描没有申请分账失败的saas签约订单异常",e);
		}
		log.info("定时扫描没有申请分账失败的saas签约订单结束，处理申请分账结果【总数："+count+",成功："+success+",失败："+fail+"】");
	}
	
	/*
	 * 申请分账
	 */
	public void saasOrderLedger(SaasSoldOrderBean saasSoldOrder,SellerBean seller) throws Exception{

		if(saasSoldOrder.getUid()==null){
			log.info("该商家无所属寻蜜客，无需进行分账！");
			return ;
		}
		if(saasSoldOrder.getHstatus()!=null && saasSoldOrder.getHstatus()!=0){
			log.error("该签约订单【"+saasSoldOrder.getOrdersn()+"】已进行过分账处理");
			return ;
		}

		/* 通知分账服务对该签约订单进行分账 */
		notifyLedgerService(saasSoldOrder,seller);
	
	}
	public void notifyLedgerService(SaasSoldOrderBean tSaasSoldOrder,SellerBean tSeller) throws Exception{
		log.info("tSaasSoldOrder:"+tSaasSoldOrder+",tSeller:"+tSeller);

		RSaasSoldOrderLedger ssol = new RSaasSoldOrderLedger();
		int xmerType=1;
		
		/* 获取寻蜜客等级信息 */
		Integer xmerUid = tSeller.getUid();
		Map<String,Object> issuesMap = xmerDao.getXmerIssues(xmerUid);
		
		/* 获取该商家订单所属寻蜜客套餐信息 */
		SaasOrderBean saasOrder = saasOrderDao.getSaasOrderByOrdersn(tSaasSoldOrder.getSaasOrdersn());
		
		//是否有上上级
		boolean isOneLevelXmer = issuesMap.get("oneLevelXmerId")==null?false:true;	
		//是否有上级
		boolean isTwoLevelXmer = issuesMap.get("twoLevelXmerId")==null?false:true;
		boolean isSellerArea = tSeller.getJointid()==null?false:true;
		boolean isReturn = true;//是否返还寻蜜客签约套餐时的单套本金(押金)
		double discount = saasOrder.getAgio().doubleValue();
		boolean isAgentscope = false;	//是否卖光了签约套数
		double purchaseDiscount = 1;	//合作商进购价
		
		JointBean joint= null;
		if(isSellerArea){
			isSellerArea = false;
			joint = jointDao.getJointInfo(tSeller.getJointid());
			//检测该合作商是否已停止合作
			if(joint!=null && joint.getStatus()==1){
				isAgentscope = joint.getStocknum()==0?false:true;
				purchaseDiscount = joint.getSaasagio()==null?1:joint.getSaasagio();
				isSellerArea = true;
			}
		}
		
		/* 
		 * 获取商家签约分账金额计算结果  
		 * */
		 double ledgerAmount = tSaasSoldOrder.getAmount().doubleValue();
//		if(tSaasSoldOrder.getPayType().equals(10000000)){
//			log.info("该SAAS软件为寻蜜客赠送订单，实际参与分账金额为"+saasOrder.getPrice());
//			ledgerAmount = saasOrder.getPrice().doubleValue();
//		}else{
//			ledgerAmount = tSaasSoldOrder.getAmount().doubleValue();
//		}
		
		ResponseSplitMap responseSplitMap = commonService.saasSoldLedgerFormula(ledgerAmount, 
				isReturn,xmerType,discount,isSellerArea,isAgentscope,purchaseDiscount,isOneLevelXmer,isTwoLevelXmer);
		if(responseSplitMap.getCode()!=1){
			log.error("调用分账算法接口失败："+responseSplitMap.getMsg());
			return ;
		}
		if(tSaasSoldOrder.getPayType().equals(10000000)){
			log.info("寻蜜客赠送订单，该寻蜜客将不参与分账");
			responseSplitMap.getData().put("xmerMoney", 0.0);
			responseSplitMap.getData().put("xmerBackMoney", 0.0);
		}
		if(issuesMap==null || issuesMap.size()==0){
			log.info("已解约寻蜜客下的商家上线，该寻蜜客所分账金额将平台");
			double xmerAmount = responseSplitMap.getData().get("xmerMoney");
			double xmerBackMoney = responseSplitMap.getData().get("xmerBackMoney");
			double xmniaoMoney = responseSplitMap.getData().get("xmniaoMoney");
			double xmniaoNewMoney = xmerAmount+xmniaoMoney+xmerBackMoney;
			responseSplitMap.getData().put("xmniaoMoney", xmniaoNewMoney);
			responseSplitMap.getData().put("xmerMoney", 0.0);
			responseSplitMap.getData().put("xmerBackMoney", 0.0);
		}
		
		/* 获取商家经营信息 */
		String areaId= tSeller.getArea();
		Map<String,String> areaMap= sellerDao.getSellerArea(areaId);
		String areaText = areaMap.get("area");
		String categoryText = tSeller.getHyText();
		

		log.info("issuesMap:"+issuesMap);
		if(issuesMap!=null && issuesMap.size()>0){
			getXmerName(issuesMap);
		}
		/* 填充分账信息 */
		ssol.setPayType(getStringValue(tSaasSoldOrder.getPayType()));
		ssol.setPayId(tSaasSoldOrder.getPayId());
		ssol.setPayCode(tSaasSoldOrder.getPayCode());
		ssol.setOrderId(tSaasSoldOrder.getOrdersn());
		ssol.setMoney(getStringValue(tSaasSoldOrder.getAmount()));
		ssol.setPayment(getStringValue(tSaasSoldOrder.getAmount()));
		ssol.setSellerId(getStringValue(tSaasSoldOrder.getSellerid()));
		ssol.setSellerName(tSaasSoldOrder.getSellername());
		
		ssol.setSellerArea(areaId);
		ssol.setSellerAreaName(areaText);
		ssol.setSellerIndustry(categoryText);
		ssol.setXmerId(getStringValue(tSeller.getUid()));
		ssol.setXmerName(getStringValue(issuesMap==null?null:issuesMap.get("uidName")));
		ssol.setOneLevelXmerId(getStringValue(issuesMap==null?null:issuesMap.get("oneLevelXmerId")));
		ssol.setOneLevelXmerName(getStringValue(issuesMap==null?null:issuesMap.get("oneLevelXmerName")));
		ssol.setTwoLevelXmerId(getStringValue(issuesMap==null?null:issuesMap.get("twoLevelXmerId")));
		ssol.setTwoLevelXmerName(getStringValue(issuesMap==null?null:issuesMap.get("twoLevelXmerName")));
		ssol.setSellerAreaId(getStringValue(tSeller.getJointid()));
		ssol.setXmerType(getStringValue(xmerType));
		ssol.setCompanyXmerName(getStringValue(issuesMap==null?null:issuesMap.get("uidName")));
		
		ssol.setXmerMoney(getStringValue(responseSplitMap.getData().get("xmerMoney")));
		ssol.setOneLevelXmerMoney(getStringValue(responseSplitMap.getData().get("oneLevelXmerMoney")));
		ssol.setTwoLevelXmerMoney(getStringValue(responseSplitMap.getData().get("twoLevelXmerMoney")));
		ssol.setXmerBackMoney(getStringValue(responseSplitMap.getData().get("xmerBackMoney")));
		ssol.setSellerAreaMoney(getStringValue(responseSplitMap.getData().get("sellerAreaMoney")));
		ssol.setCompanyXmerMoney(getStringValue(responseSplitMap.getData().get("companyXmerMoney")==null?0:responseSplitMap.getData().get("companyXmerMoney")));
		ssol.setXmniaoMoney(getStringValue(responseSplitMap.getData().get("xmniaoMoney")));

//		SaasSoldOrderBean tsso = new SaasSoldOrderBean();
//		tsso.setCommission(JSON.toJSONString(responseSplitMap.getData()));
//		tsso.setOrdersn(tSaasSoldOrder.getOrdersn());
//		tsso.setHstatus(10);
//		tsso.setUdate(new Date());
		
    	Map<String,String> uMap=new HashMap<String,String>();
    	uMap.put("commission", JSON.toJSONString(responseSplitMap.getData()));//订单编号
    	uMap.put("ordersn", tSaasSoldOrder.getOrdersn());//订单状态
    	uMap.put("hstatus", "10");//支付方式
    	uMap.put("udate", DateUtil.getCurrentTimeStr());//支付流水
		saasOrderDao.updateSaasSoldOrder(uMap);
		
		log.info("Saas签约订单分账队列："+saasLedgerKey);
		log.info("Saas签约订单分账处理："+JSON.toJSONString(ssol));
		redisTemplate.opsForList().leftPush(saasLedgerKey, JSON.toJSONString(ssol));

		/**
		 * 经销商saas库存-1后，新增1条 t_joint_saas_ledger记录
		 * add by lifeng
		 */
		//经销商saas库存-1
		if(isSellerArea && isAgentscope){
			JointBean mdyJoint = new JointBean();
			mdyJoint.setJointid(tSeller.getJointid());
			int mdyCount = jointDao.modifyJonitSaasInfo(mdyJoint);
			if(mdyCount==0){
				log.error("合作商【"+tSeller.getJointid()+"】的套餐已卖光了。。。");
			}
		}
		
		if(isSellerArea){
			JointSaasLedgerBean tJointSaasLedger = new JointSaasLedgerBean();
			tJointSaasLedger.setJointid(tSeller.getJointid());
			tJointSaasLedger.setSellerid(tSeller.getSellerid());
			tJointSaasLedger.setXmerUid(tSaasSoldOrder.getUid());
			tJointSaasLedger.setSaasBid(tSaasSoldOrder.getId() + "");	
			tJointSaasLedger.setDate(new Date());
			tJointSaasLedger.setCommison(new BigDecimal(ssol.getSellerAreaMoney()));
			jointDao.addJointSaasLedger(tJointSaasLedger);//保存一条记录
		}

	}

	private String getStringValue(Object obj){
		if(obj!=null){
			return String.valueOf(obj);
		}
		return null;
	}

    public void getXmerName(Map<String,Object> issuesMap){
    	log.info("当前寻蜜客上级关系："+issuesMap);
    	Set<String> keys = issuesMap.keySet();
   		if(keys.size()>1){
   			String[] array = keys.toArray(new String[]{});
   			List<String> ids = new ArrayList<String>();
   			for(String name:array){
   				ids.add(issuesMap.get(name).toString());
   			}
   			List<Map<String,Object>> nameList = xmerDao.getXmerName(ids.toArray(new String[]{}));
	   		Iterator<String> it =  keys.iterator();
	   		Map<String,Object> nMap = new HashMap<String,Object>();
	   		while(it.hasNext()){
	   			String key1=it.next();
	   			for(Map<String,Object> nameMap:nameList){
	   				if(nameMap.get("uid").toString().equals(issuesMap.get(key1).toString())){
	   					String nameKey = key1.replace("Id", "Name");
	   					nMap.put(nameKey, nameMap.get("name"));
	   				}
	   			}
	   		}
	   		issuesMap.putAll(nMap);
   		}
   		log.info("完善后寻蜜客上级关系："+issuesMap);
    }
}
