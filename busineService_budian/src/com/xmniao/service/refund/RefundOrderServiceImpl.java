package com.xmniao.service.refund;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.common.ConversionTypeUtil;
import com.xmniao.common.ResponseState;
import com.xmniao.dao.order.OrderServiceDao;
import com.xmniao.dao.refund.RefundOrderServiceDao;
import com.xmniao.domain.ledger.LedgerNewBean;
import com.xmniao.domain.order.BillBean;
import com.xmniao.domain.order.OrdRecordBean;
import com.xmniao.service.ledger.LedgerAlgorithmUtils;
import com.xmniao.service.ledger.LedgerServiceImpl;
import com.xmniao.service.order.OrderServiceImpl;
import com.xmniao.thrift.busine.common.FailureException;
import com.xmniao.thrift.busine.refund.RefundOrderService;
import com.xmniao.urs.dao.UrsDao;
import com.xmniao.urs.dao.XmerDao;

/**
 * 订单退款服务接口实现类
 * @author  LiBingBing
 * @version  [版本号, 2015年8月5日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service
public class RefundOrderServiceImpl implements RefundOrderService.Iface
{
    
    /**
     * 日志记录
     */
    private final Logger log = Logger.getLogger(RefundOrderServiceImpl.class);
    
    /**
     * 订单退款DAO层
     */
    @Autowired
    private RefundOrderServiceDao refundDao;
    
    /**
     * 注入分账系统服务实现类
     */
    @Autowired
    private LedgerServiceImpl ledgerServiceImpl;
    
    @Autowired
    private OrderServiceImpl orderService;
    
    @Autowired
    private XmerDao xmerDao;
    
    @Autowired
    private OrderServiceDao orderDao;
    
    @Autowired
    private UrsDao ursDao;
    
    /**
     * 报障退款订单接口
     * @param bid
     * @return
     * @throws FailureException
     * @throws TException
     */
    @Override
    public Map<String, String> payFailRefundOrder(Map<String,String> paraMap)
            throws FailureException, TException
    {
        log.info("payFailRefundOrder start:" + paraMap);
        Map<String, String> resMap = new HashMap<String, String>();
        
        //根据订单号获取订单信息
        Map<String, Object> resOrderMap = refundDao.queryOrderInfo(paraMap.get("bid"));
        //订单信息为空
        if (null == resOrderMap)
        {
            log.error("没有获取到订单号为:【" + paraMap.get("bid") + "】订单信息");
            resMap.put("bid", paraMap.get("bid"));
            resMap.put("recode", "107");
            resMap.put("remark", "没有获取到订单号为:【" + paraMap.get("bid") + "】订单信息");
            return resMap;
        }
        
        //订单状态
        int status = Integer.valueOf(resOrderMap.get("status").toString());
        //订单已为平台同意退款
        if (status == 14 || status==15)
        {
            log.error("该订单【" + paraMap.get("bid") + "】已重复报障退款操作");
            resMap.put("bid", paraMap.get("bid"));
            resMap.put("recode", "112");
            resMap.put("remark", "该订单【" + paraMap.get("bid") + "】已重复报障退款操作");
            return resMap;
        }
        
        //订单必须为待支付状态0
        if(status!=0)
        {
            log.error("该订单【" + paraMap.get("bid") + "】必须为待支付");
            resMap.put("bid", paraMap.get("bid"));
            resMap.put("recode", "112");
            resMap.put("remark", "该订单【" + paraMap.get("bid") + "】必须为待支付");
            return resMap;
        }
        try
        {
            resOrderMap.put("paytype", paraMap.get("paytype"));
            
            //调用分账系统服务的获取分账信息
            //LedgerBean ledgerBean = ledgerServiceImpl.ledgerInfoProcess(resOrderMap);
            //调用分账系统分配佣金接口
            //Map<String, Object> resCommission = ledgerServiceImpl.getLedgerMoney(ledgerBean);
            String commission=this.getLedgerCommission(paraMap);
            log.info("ledgerSystem commission::" + commission);
            
            //组装更新订单状态和订单佣金JSON
            Map<String, String> reqMap = new HashMap<String, String>();
            reqMap.put("bid", paraMap.get("bid"));
            
            //若请求参数中没有订单类型，则默认为报障退款类型(即为 14)
            if(paraMap.containsKey("bidtype")==false)
            {
                paraMap.put("bidtype", "14");
            }
            
            if(paraMap.get("bidtype").equals("14"))
            {
                reqMap.put("status", "14");
            }
            if(paraMap.get("bidtype").equals("15"))
            {
                reqMap.put("status", "15");
            }
            reqMap.put("source", "2");
            reqMap.put("number", paraMap.get("number"));
            reqMap.put("paytype", paraMap.get("paytype"));
            reqMap.put("payid", paraMap.get("payid"));
            reqMap.put("samount", paraMap.get("samount"));
            reqMap.put("commision", paraMap.get("commision"));
            reqMap.put("profit", paraMap.get("profit"));
            reqMap.put("giveMoney", paraMap.get("giveMoney"));
            reqMap.put("thirdUid", paraMap.get("thirdUid"));
            reqMap.put("hstatus", "0");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            reqMap.put("zdate", dateFormat.format(new Date()));
            
            reqMap.put("commission", commission);
            //更新订单状态
            refundDao.modifyOrderInfo(reqMap);
            
            //订单处理记录接口
            Map<String, String> recordMap = new HashMap<String, String>();
            recordMap.put("bid", paraMap.get("bid"));
            recordMap.put("status", "3");
            if(paraMap.get("bidtype").equals("14"))
            {
                recordMap.put("explains", "用户报障，平台发起退款");
                recordMap.put("remarks", "用户报障，平台发起退款");
            }
            if(paraMap.get("bidtype").equals("15"))
            {
                recordMap.put("explains", "用户使用优惠券刷单，平台发起退款");
                recordMap.put("remarks", "用户使用优惠券刷单，平台发起退款");
            }
            insertBillRecord(recordMap);
            
            //组装插入订单退款记录MAP
            Map<String, String> orderRefundMap = new HashMap<String, String>();
            orderRefundMap.put("bid",paraMap.get("bid"));
            orderRefundMap.put("sellerid", String.valueOf(resOrderMap.get("sellerid")));
            orderRefundMap.put("sellername", String.valueOf(resOrderMap.get("sellername")));
            if(paraMap.get("bidtype").equals("14"))
            {
                orderRefundMap.put("apply", "用户报障，平台发起退款");
                orderRefundMap.put("status", "11");
                orderRefundMap.put("remarks", "用户报障，平台发起退款");
                
            }
            if(paraMap.get("bidtype").equals("15"))
            {
                orderRefundMap.put("apply", "用户使用优惠券刷单，平台发起退款");
                orderRefundMap.put("status", "12");
                orderRefundMap.put("remarks", "用户使用优惠券刷单，平台发起退款");
            }
            orderRefundMap.put("sdate", dateFormat.format(new Date()));
            orderRefundMap.put("source", "2");
            //插入订单退款记录
            refundDao.insertOrderRefundRecord(orderRefundMap);
            
            resMap.put("recode", "100");
            resMap.put("remark", "报障退款订单操作成功");
        }
        catch (Exception e)
        {
            log.error("更新订单数据异常", e);
            throw new FailureException(ResponseState.ORDERFAIL, "更新订单数据异常");
        }
        log.info("payFailRefundOrder end");
        return resMap;
    }
    
    /**
     * 订单处理记录方法
     * @param reqMap [请求参数MAP]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public void insertBillRecord(Map<String,String> reqMap)
    {
        log.info("inserBillRecord param start:"+reqMap);
        try
        {
            OrdRecordBean reqBean = new OrdRecordBean();
            //订单号
            reqBean.setBid(reqMap.get("bid"));
            //订单处理状态
            reqBean.setStatus(Integer.valueOf(reqMap.get("status")));
            //订单处理说明
            reqBean.setExplains(reqMap.get("explains"));
            reqBean.setRemarks(reqMap.get("remarks"));
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            reqBean.setCdate(dateFormat.format(new Date()));
            //开始插入订单处理记录表
            refundDao.insertBillRecord(reqBean);
        }
        catch (Exception e)
        {
            log.error("订单处理记录异常",e);
        }
        log.info("inserBillRecord end");
    }
    
    /*
     * 获取分账明细
     */
    public String getLedgerCommission(Map<String,String> request) throws FailureException{
    	String bid = request.get("bid");
    	BillBean billBean = orderDao.getBillBean(bid);
    	Map<String,String> uMap = new HashMap<String,String>();
    	uMap.put("uid", billBean.getUid()+"");
    	
    	//绑定商家信息
    	Map<String,Object> urs = ursDao.getUrsAscription(uMap);
    	
    	//是否首单信息
        Map<String, String> resMikeMap = new HashMap<String, String>();
        resMikeMap.put("mikeType", "2");
        resMikeMap.put("bfirst", "0");
        
        //首单判断
        log.info("消费会员【"+billBean.getUid()+"】当前的所属信息:"+urs);
        if(urs.get("genussellerid") == null)
        {           
        	log.info("会员消费首单");
            resMikeMap.put("mikeType", "2");
            resMikeMap.put("bfirst", "1");
        }      
        
        //准备最新订单信息
        modifyBillBean(billBean,request,resMikeMap);
        return billBean.getCommission();
        
//        LedgerNewBean ledgerBean = new LedgerNewBean();
//        
//		ledgerBean.setOrderMoney(billBean.getLedgerAmount().doubleValue());
//		ledgerBean.setUserMoney(billBean.getPayment().doubleValue());
//		ledgerBean.setBaseagio(billBean.getBaseagio());
//		ledgerBean.setMikeType(1);
//		ledgerBean.setSellerId(billBean.getSellerid());
//		ledgerBean.setGenusSellerId(billBean.getGenussellerid());
//		ledgerBean.setParentMikeId(billBean.getTwoLevelXmerId());
//		ledgerBean.setTopMikeId(billBean.getOneLevelXmerId());
//		ledgerBean.setConsumeJointid(billBean.getConsumeJointid());
//    	
////        LedgerNewBean bean = ledgerServiceImpl.ledgerInfoProcess2(billBean);
//        log.info("分账初始数据："+ledgerBean);
//        Map<String,Object> resMap = LedgerAlgorithmUtils.getLedgerMoney(ledgerBean);
//        
//        log.info("ledgerSystem resMap::" + resMap);
//        if(null != resMap && !resMap.isEmpty())
//        {
//        	JSONObject json=(JSONObject) JSONObject.toJSON(resMap);
//        	json.put("proportion", json.getString("proportion"));
//        	return json.getString("commission");
//        }else{
//        	return null;
//        }
    }
    
    /**
     * 准备最新订单信息
     * @Title: modifyBillBean 
     * @Description:
     */
   void  modifyBillBean(BillBean billBean,Map<String,String> request,Map<String,String> resMikeMap) throws FailureException{
       log.info("modifyBillBean start:" + billBean);
       try
       {
           billBean.setPayid(request.get("payid"));
           billBean.setPaytype(request.get("paytype"));
           billBean.setPayment(ConversionTypeUtil.conversionToBigDecimal(request.get("samount")));
//           billBean.setStatus(1);
           billBean.setCommision(ConversionTypeUtil.conversionToBigDecimal(request.get("commision")));
           billBean.setProfit(ConversionTypeUtil.conversionToBigDecimal(request.get("profit")));
           billBean.setGiveMoney(ConversionTypeUtil.conversionToBigDecimal(request.get("giveMoney")));
           billBean.setNumber(request.get("number"));
           billBean.setZdate(new Date());
           billBean.setIntegral(billBean.getCalculateRealZInteger().doubleValue());//2016-7-12 Edit By CB 总金额-立减 //billBean.getPayment().doubleValue()
           billBean.setLedgerAmount(billBean.getCalculateRealLedgerAmount());
   		   /* 获取寻蜜客等级信息 */
   		   Integer xmerUid = billBean.getXmerUid();
   		   if(xmerUid != null){
	           	if(xmerUid==0){
	        		log.info("该订单记录商家寻蜜客的uid为无效ID，现将其设置为null");
	        		billBean.setXmerUid(null);
	        	}
   			   Map<String,Object> issuesMap = xmerDao.getXmerIssues(xmerUid);
   			   if(issuesMap == null){	//寻蜜客被解除
   				   billBean.setXmerUid(null);//2016年7月7日 若对应寻蜜客被解除，则将其默认为无寻蜜客
   			   }else{
	   			   orderService.getXmerName(issuesMap);
		           billBean.setOneLevelXmerId(issuesMap.get("oneLevelXmerId")==null?null:(Integer)issuesMap.get("oneLevelXmerId"));
		           billBean.setTwoLevelXmerId(issuesMap.get("twoLevelXmerId")==null?null:(Integer)issuesMap.get("twoLevelXmerId"));
		           billBean.setOneLevelXmerName(issuesMap.get("oneLevelXmerName")==null?null:(String)issuesMap.get("oneLevelXmerName"));
		           billBean.setTwoLevelXmerName(issuesMap.get("twoLevelXmerName")==null?null:(String)issuesMap.get("twoLevelXmerName"));
   			   }
   		   }   
   		   
           LedgerNewBean bean = ledgerServiceImpl.ledgerInfoProcess2(billBean);
           log.info("分账初始数据："+bean);
           Map<String,Object> resMap = LedgerAlgorithmUtils.getLedgerMoney(bean);
           
           log.info("ledgerSystem resMap::" + resMap);
           if(null != resMap && !resMap.isEmpty())
           {
           	JSONObject json=(JSONObject) JSONObject.toJSON(resMap);
           	json.put("proportion", json.getString("proportion"));
           	billBean.setCommission(json.getString("commission"));
           	billBean.setProportion(json.getString("proportion"));;
           }
           
           //若是自动分账
//           if(request.getLedgertype() == 1)
//           {
//        	   billBean.setHstatus(10);
//        	   billBean.setLdate(new Date());
//           }else{
//        	   billBean.setHstatus(0);
//           }
//           
//           if(null!=resMikeMap)
//           {
//        	   billBean.setMikeType(resMikeMap.containsKey("mikeType")?Byte.valueOf(resMikeMap.get("mikeType")):null);
//        	   billBean.setBfirst(resMikeMap.containsKey("bfirst")?Integer.valueOf(resMikeMap.get("bfirst")):null);
//           }
//           
       }
       catch (Exception e)
       {
           log.error("准备订单数据异常", e);
           throw new FailureException(ResponseState.ELSEEROR, "准备订单数据异常");
       }
       log.info("modifyBillBean end......");
    }
}
