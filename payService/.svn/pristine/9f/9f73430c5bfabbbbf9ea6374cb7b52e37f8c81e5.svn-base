package com.xmniao.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xmniao.common.PayConstants;
import com.xmniao.common.PayRefundStatus;
import com.xmniao.common.UpdateLedgerSystem;
import com.xmniao.dao.UpdateWithdrawalsRecordStateMapper;
import com.xmniao.service.CommonService;
import com.xmniao.service.pay.WithdrawMoneyServiceImpl;
import com.xmniao.thrift.ledger.SynthesizeService;

/**
 * 汇付天下异步通知数据 处理
 * 
 * @author Dongjietao
 * 
 */
@Controller
public class ChinaPnRRefundController {

    // 初始日志类
    private final Logger log = Logger.getLogger(ChinaPnRRefundController.class);

    @Autowired
    private SynthesizeService.Iface ServiceImpl;
   
    @Autowired
    private WithdrawMoneyServiceImpl withdrawMoneyServiceImpl;
    
    @Autowired
    public UpdateWithdrawalsRecordStateMapper updateWithdrawalsRecordStateMapper;

    @Autowired
    private UpdateLedgerSystem updateLedgerSystem;
    
    @Autowired
    private CommonService commonService;

    @RequestMapping(value = "/ChinaPnRRefoundNotify", method = RequestMethod.POST)
    public void RequestHandle(HttpServletRequest request,
	    HttpServletResponse response) {
	log.info("ChinaPnRRefundController--->RequestHandle(汇付天下异步通知数据 处理)");
	long edate = System.currentTimeMillis();
	log.info("订单号： "+ request.getParameter("OrdId")+",汇付天下代付回调时间-----------："+edate);
	
	
	String sta = commonService.getWithdrawStatus(request.getParameter("MerPriv"),request.getParameter("OrdId"));

	// 验证是否已经修改了支付状态
	if (!PayConstants.WITHDRAW_STATUS_PROCESS.equals(sta)) {
		return;
	}
	if ("-1".equals(sta)) {
		log.error("汇付天下代付回调参数异常----userType:"+request.getParameter("MerPriv")+",orderId:"+request.getParameter("OrdId"));
		return;
	}
	try {
	    request.setCharacterEncoding("GBK");// 设置获取参数编码GBK
	} catch (UnsupportedEncodingException e1) {
	    log.info("汇付天下");
	}

	Map<String, Object> map = new HashMap<String, Object>();

	map.put("CmdId", request.getParameter("CmdId"));

	map.put("RespCode", request.getParameter("RespCode"));

	//System.out.println("RespDesc:" + request.getParameter("RespDesc"));
	try {
	    map.put("RespDesc",
		    URLDecoder.decode(request.getParameter("RespDesc"), "GBK"));// 中文转码
	} catch (UnsupportedEncodingException e) {
	    e.printStackTrace();
	}
	//System.out.println("RespDesc:" + map.get("RespDesc"));

	map.put("CustId", request.getParameter("CustId"));

	map.put("SubAcctId", request.getParameter("SubAcctId"));

	map.put("OrdId", request.getParameter("OrdId"));

	map.put("TransStat", request.getParameter("TransStat"));

	map.put("HandleDate", request.getParameter("HandleDate"));

	map.put("HandleSeqId", request.getParameter("HandleSeqId"));

	map.put("OrdAmt", request.getParameter("OrdAmt"));

	map.put("MerPriv", request.getParameter("MerPriv"));

	try {
	    map.put("AcctName",
		    URLDecoder.decode(request.getParameter("AcctName"), "GBK"));// 中文转码
	} catch (UnsupportedEncodingException e) {
	    e.printStackTrace();
	}
	map.put("BankId", request.getParameter("BankId"));

	map.put("AcctId", request.getParameter("AcctId"));

	map.put("UserMp", request.getParameter("UserMp"));

	map.put("CertType", request.getParameter("CertType"));

	map.put("CertId", request.getParameter("CertId"));

	map.put("PrType", request.getParameter("PrType"));

	map.put("ProvName", request.getParameter("ProvName"));

	map.put("AreaName", request.getParameter("AreaName"));

	map.put("BranchName", request.getParameter("BranchName"));

	map.put("PrPurpose", request.getParameter("PrPurpose"));

	map.put("Charset", request.getParameter("Charset"));

	map.put("ChkType", request.getParameter("ChkType"));

	map.put("ChkValue", request.getParameter("ChkValue"));

	log.info("汇付天下代发回调参数" + map);
	updateState(map); // 代发回调更新状态（异步通知）

    }

    public Map<String, String> updateState(Map<String, Object> map) { // 代发回调更新状态（异步通知）

	Map<String, String> str = new HashMap<String,String>();
	Map<String, String> resultMap = new HashMap<String,String>();
	
    String RespDesc = "";
	String status = PayConstants.WITHDRAW_STATUS_AUDIT;// 财务处理中
	if (map.get("TransStat").equals("I")) { // 交易处理中
	    status = PayConstants.WITHDRAW_STATUS_PROCESS;
	    RespDesc = PayConstants.WITHDRAW_MSG_PROCESS;
	} else if (map.get("TransStat").equals("S")) {// 交易成功
	    status = PayConstants.WITHDRAW_STATUS_SUCCESS;
	    RespDesc = PayConstants.WITHDRAW_MSG_SUCCESS;
	} else {// 交易失败
	    status = PayConstants.WITHDRAW_STATUS_FAIL;
	    RespDesc = PayConstants.WITHDRAW_MSG_FAIL+","+map.get("RespDesc");
	}
	try {
		resultMap.put("orderNumber", String.valueOf(map.get("OrdId")));
		resultMap.put("status", status);
		resultMap.put("Message", RespDesc);
		resultMap.put("platform_code", String.valueOf(map.get("HandleSeqId")));
        //修改提现状态
	    commonService.updateWithdrawState(map.get("OrdId").toString(), map.get("MerPriv").toString(), Integer.valueOf(status), PayConstants.WITHDRAW_TYPE_HF, resultMap);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return str;//
    }
}
