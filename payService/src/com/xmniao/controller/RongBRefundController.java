package com.xmniao.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.xmniao.common.AgentPayRequest;
import com.xmniao.common.PayConstants;
import com.xmniao.common.PayRefundStatus;
import com.xmniao.common.RTSign;
import com.xmniao.common.ReapalUtil;
import com.xmniao.common.UpdateLedgerSystem;
import com.xmniao.dao.UpdateWithdrawalsRecordStateMapper;
import com.xmniao.service.CommonService;
import com.xmniao.service.impl.RongTWithdrawMoneyImpl;
import com.xmniao.service.impl.WithdrawMoneyServiceTools;
import com.xmniao.service.pay.WithdrawMoneyServiceImpl;
import com.xmniao.thrift.ledger.FailureException;

@Controller
public class RongBRefundController {
	
	   // 日志
    private static final Logger log = LoggerFactory.getLogger(RongBRefundController.class);
	@Autowired
	private WithdrawMoneyServiceImpl withdrawMoneyServiceImpl;
	@Autowired
	public UpdateWithdrawalsRecordStateMapper updateWithdrawalsRecordStateMapper;
	@Autowired
	private RongTWithdrawMoneyImpl RongTWithdrawMoneyImpl;
	@Autowired
	private UpdateLedgerSystem updateLedgerSystem;
	@Autowired
	private CommonService commonService;

	@Autowired
	private WithdrawMoneyServiceTools withdrawMoneyServiceTools;

	@Autowired
	private RongTWithdrawMoneyImpl rt;
	
	@RequestMapping(value = "/rongBoRefundC", method = RequestMethod.POST)
	public void rongBoRefundC(HttpServletRequest request,
			HttpServletResponse response) {
		
		long edate = System.currentTimeMillis();
		log.info("融宝代付回调时间-----------："+edate);
		String str=request.getParameter("data");
        try {
        	str = RTSign.jim(str);// 解密
		} catch (Exception e1) {
			log.error("提交查询数据有误，请查证！",e1);
		}
		Map mapParms = new HashMap<String, String>();
		try { 
			mapParms = RTSign.parseString(str);// 参数解析，返回map
		} catch (Exception e) {
			
			log.error("提交查询参数错误，解析为空，请查证！",e);
		}
		List list = RTSign.splistParams(mapParms);// 交易明细参数拆分
													// list-->map--->单笔交易参数
		if(list==null||list.size()<1){
			log.info("rongBoRefundC--------------参数为空---------------");
			return ;
		}
		Map<String, String> paramsMap = (Map<String, String>) list.get(0);
		log.info("订单号： "+ mapParms.get("batchCurrnum"));
		log.info("融宝回调参数map:"+paramsMap);
		String[] orderIdArray = String.valueOf(mapParms.get("batchCurrnum")).split("_");
		String orderId = orderIdArray[0];
		String sty = orderIdArray[1];
		
		
		String sta = commonService.getWithdrawStatus(sty,orderId);

		// 验证是否已经修改了支付状态
		if (!PayConstants.WITHDRAW_STATUS_PROCESS.equals(sta)) {
			return;
		}
		if ("-1".equals(sta)) {
			log.error("汇付天下代付回调参数异常----userType:"+sty+",orderId:"+orderId);
			return;
		}
		
		String status = PayConstants.WITHDRAW_STATUS_AUDIT;// 财务处理中
		if ("成功".equals(paramsMap.get("tradeFeedbackcode").toString())) {
	     status = PayConstants.WITHDRAW_STATUS_SUCCESS;
		} else if ("失败".equals(paramsMap.get("tradeFeedbackcode").toString())) {
			 status = PayConstants.WITHDRAW_STATUS_FAIL;
		} else {
			 status = PayConstants.WITHDRAW_STATUS_PROCESS;
		}
		
			try {
			    //修改分账服务
			    Map<String, String> uwsMap = new HashMap<String, String>();
			    uwsMap.put("orderNumber", orderId);
			    uwsMap.put("status", status);
			    uwsMap.put("Message", status.equals(PayConstants.WITHDRAW_STATUS_SUCCESS)?
			    		PayConstants.WITHDRAW_MSG_SUCCESS:PayConstants.WITHDRAW_MSG_FAIL+","+paramsMap.get("tradeReason"));
			    uwsMap.put("platform_code",  String.valueOf(mapParms.get("batchCurrnum")));
			    
			    //修改提现状态
			    commonService.updateWithdrawState(orderId, sty, Integer.valueOf(status), PayConstants.WITHDRAW_TYPE_RB, uwsMap);
			} catch (Exception e) {
				log.error("更新提现记录状态异常，订单号为："+orderId,e);
			}


	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/rongBaoNotify", method = {RequestMethod.POST,RequestMethod.GET})
	public void rongBaoNotify(HttpServletRequest request,
			HttpServletResponse response) {
		log.info("-----融宝代付回调----- ");
/*
		String merchantId = request.getParameter("merchant_id");				//商户在融宝的用户ID
		String encryptkey = request.getParameter("encryptkey");				//DES密码密文
		String str = request.getParameter("data");			//推送结果
		log.info("merchantId:"+merchantId+",encryptkey:"+encryptkey+",str:"+str);
		String res ="";
        try {
        	res = ReapalUtil.pubkey(encryptkey,str);
        	log.info("推送回调接口结果:"+res);
		} catch (Exception e1) {
			log.error("提交查询数据有误，请查证！",e1);
		}
    	Map<String,String> resMap =(Map<String,String>) JSON.parse(res);
        log.info("异步返回数据为:"+resMap);
 		String notifyBatchNo="";
		String notifyStatus="";
		String notifyReason="";
        if(resMap==null || resMap.get("result_code") == null){
        	log.error("请求失败");
        	return;
        }
        if("0000".equals(resMap.get("result_code"))){
        	log.info("成功,data:"+resMap.get("data"));
        	String notifyData=resMap.get("data")==null?"":resMap.get("data");
        	String[] result=notifyData.split(",");
        	if(result.length>=14){
        		notifyBatchNo=result[1];
        		notifyStatus=result[13];
        		notifyReason=result.length>=15?result[14]:"";
        	}else{
        		log.error("返回数据格式不对:"+notifyData);
        		return ;
        	}
        			
        }else{
        	log.info("失败："+resMap.get("result_code")+"-"+resMap.get("result_msg"));
        	return ;
        }
        
		String[] orderIdArray = String.valueOf(notifyBatchNo).split("_");
		String orderId = orderIdArray[0];
		String sty = orderIdArray[1];
		String sta = commonService.getWithdrawStatus(sty,orderId);

		// 验证是否已经修改了支付状态
		if (!PayConstants.WITHDRAW_STATUS_PROCESS.equals(sta)) {
			return;
		}
		if ("-1".equals(sta)) {
			log.error("汇付天下代付回调参数异常----userType:"+sty+",orderId:"+orderId);
			return;
		}
		
		String status = PayConstants.WITHDRAW_STATUS_AUDIT;// 财务处理中
		if ("成功".equals(notifyStatus)) {
	     status = PayConstants.WITHDRAW_STATUS_SUCCESS;
		} else if ("失败".equals(notifyStatus)) {
			 status = PayConstants.WITHDRAW_STATUS_FAIL;
		} else {
			 status = PayConstants.WITHDRAW_STATUS_PROCESS;
		}
		
		try {
		    //修改分账服务
		    Map<String, String> uwsMap = new HashMap<String, String>();
		    uwsMap.put("orderNumber", orderId);
		    uwsMap.put("status", status);
		    uwsMap.put("Message", status.equals(PayConstants.WITHDRAW_STATUS_SUCCESS)?
		    		PayConstants.WITHDRAW_MSG_SUCCESS:PayConstants.WITHDRAW_MSG_FAIL+","+notifyReason);
		    uwsMap.put("platform_code",  String.valueOf(notifyBatchNo));
		    
		    //修改提现状态
		    commonService.updateWithdrawState(orderId, sty, Integer.valueOf(status), PayConstants.WITHDRAW_TYPE_RB, uwsMap);
		} catch (Exception e) {
			log.error("更新提现记录状态异常，订单号为："+orderId,e);
		}

*/
	}
	
	
//	@RequestMapping(value = "/rongPay", method = {RequestMethod.POST,RequestMethod.GET})
	public void rongBaoPay(HttpServletRequest request,HttpServletResponse response) {

		try{
			int userType=2;
			String orderId = request.getParameter("orderId"); 
			Map<String, Object> mapEntity = withdrawMoneyServiceTools.getOrderData(orderId, userType);

//			Map<String,Object> reqMap = new HashMap<String,Object>();
//			reqMap.put("date", "2016-05-14 12:25:25");
//			reqMap.put("batchCurrnum", "888150837_2");
//			List list= rt.WithdrawMoneyQuery2(reqMap);
//			System.out.println(list);
			
			AgentPayRequest agentPayRequest = WithdrawMoneyServiceTools.putTheDataR(mapEntity,userType+"");// 参数组装
			Map<String, String> resultMap = rt.WithdrawMoney(agentPayRequest);// 融宝代发执行
			System.out.println(resultMap);

			/*
			 * Map<String, Object> map = new HashMap<String,Object>();
			 * 
			 * map.put("batchCurrnum", "8000003833"); map.put("batchDate", "20150202");
			 * 
			 * FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext(new String[]
			 * {"WebRoot/WEB-INF/pay-context.xml","WebRoot/WEB-INF/pay-service.xml"}, true); context.start();
			 * RongTWithdrawMoneyImpl rongTWithdrawMoneyImpl = context.getBean(RongTWithdrawMoneyImpl.class);
			 * rongTWithdrawMoneyImpl.WithdrawMoney(map);
			 */
		}catch(Exception e){
			e.printStackTrace();
		}

	}
}
