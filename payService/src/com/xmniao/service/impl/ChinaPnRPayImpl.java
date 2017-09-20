package com.xmniao.service.impl;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.common.HttpRequest;
import com.xmniao.common.PayConstants;
import com.xmniao.common.PayDatas;
import com.xmniao.common.PnRSignature;
import com.xmniao.controller.ChinaPnRRefundController;
import com.xmniao.dao.UpdateWithdrawalsRecordStateMapper;
import com.xmniao.service.ChinaPnRPay;

/**
 * 汇付天下支付提现操作
 * 
 * @author Dongjietao
 * 
 */
@Service("ChinaPnRPayImpl")
public class ChinaPnRPayImpl implements ChinaPnRPay {

	private final Logger log = Logger.getLogger(ChinaPnRRefundController.class);
	
	/** 提现记录Mapper */
    @Autowired
    public UpdateWithdrawalsRecordStateMapper updateWithdrawalsRecordStateMapper;
  
    @Autowired
    private PayDatas payDatas;
  
    
    

    /**
     * 汇付天下支付提现接口(汇付天下代发接口)   
     * @param map
     * @return
     * @throws Exception 
     */
	@Override
	public Map<String, String> PnRPay(Map<String, Object> map) throws Exception { // 汇付天下代付（提现）总接口

		log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
				+ "-----------ChinaPnRPayImpl--->PnRPay(汇付天下代付（提现）总接口)--------------------------------map:"
				+ map);

		String ChkValue = null;// 签名变量定义
		try {
			ChkValue = PnRSignature.getSign(map, payDatas);// 获取签名
			//log.info("ChkValue:" + ChkValue);
		} catch (UnsupportedEncodingException e) {
			log.error("PnRSignature.getSign--汇付天下代付签名出错，订单号为："+map.get("OrdId"), e);
		}
		map.put("ChkValue", ChkValue);
		// 生产系统
	
		String str = payDatas.getTheMap(map);// 拼装提交参数
		log.info("汇付天下代付 提交参数 ----" + str);
		long sdate = System.currentTimeMillis();
		Map<String, Object> refundMap = HttpRequest.sendPost(payDatas.getPayUrl(), str);// 发出请求，获取xml文件解析，并装入map中
		long edate = System.currentTimeMillis();
		long ydate = edate - sdate;
		log.info("汇付天下代付发送完时间-----------："+edate);
		log.info("汇付天下代付响应时间----------："+ydate);
		return updateStateS(refundMap); // 更新数据库状态 并返回相应数据接口参数 1
		// 提现成功 2 提现失败 3 银行处理中

	}

	// 汇付天下同步返回数据，修改状态
	private Map<String, String> updateStateS(Map<String, Object> map) {// 汇付天下同步返回数据，修改状态
		log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
				+ "----------------------ChinaPnRRefundController--->updateStateS(汇付天下同步返回数据，修改状态)--------------------------------");
		Map<String, String> m = new HashMap<String, String>();

        String RespDesc = "";
		String status = PayConstants.WITHDRAW_STATUS_AUDIT;// 财务处理中
		if (map.get("TransStat").equals("I")) { // 交易处理中
			status = PayConstants.WITHDRAW_STATUS_PROCESS;
			RespDesc = PayConstants.WITHDRAW_MSG_PROCESS;
		} else if (map.get("TransStat").equals("S")) { // 交易成功
			status = PayConstants.WITHDRAW_STATUS_SUCCESS;
			RespDesc = PayConstants.WITHDRAW_MSG_SUCCESS;
		} else {// 交易失败
			status = PayConstants.WITHDRAW_STATUS_FAIL;
			RespDesc = PayConstants.WITHDRAW_MSG_FAIL+","+ map.get("RespDesc");
		}

		m.put("status", status); // 添加代发状态
		m.put("HandleSeqId", String.valueOf(map.get("HandleSeqId"))); // 代发流水号
		m.put("RespDesc", RespDesc); // 代发处理说明
		m.put("RespCode", String.valueOf(map.get("RespCode"))); // 返回码
		return m;
	}
	
	
	@Override
	public  Map<String,String> queryOneByOrder(String orderId,String ordAmt) {
		Map<String,String> resultMap = new HashMap<String,String>();
		try {
			String chkValue = PnRSignature.getQuerySign(orderId,ordAmt, payDatas);// 获取签名
			String str = payDatas.getQueryOneParam(orderId , ordAmt,chkValue);
			Map<String, Object> refundMap = HttpRequest.sendPost(payDatas.getQueryUrl(), str);// 发出请求，获取xml文件解析，并装入map中

			
			if(refundMap.get("RespCode").equals("000")){
				resultMap.put("platform_code", String.valueOf(refundMap.get("HandleSeqId")));
				resultMap.put("usertype",  refundMap.get("MerPriv").toString());
				if (refundMap.get("TransStat").equals("I")) { // 交易处理中
					resultMap.put("status", PayConstants.WITHDRAW_STATUS_PROCESS);
					resultMap.put("Message", PayConstants.WITHDRAW_MSG_PROCESS);
				} else if (refundMap.get("TransStat").equals("S")) { // 交易成功
					resultMap.put("status", PayConstants.WITHDRAW_STATUS_SUCCESS);
					resultMap.put("Message",PayConstants.WITHDRAW_MSG_SUCCESS);
					resultMap.put("platform_code", String.valueOf(refundMap.get("HandleSeqId")));
					
				} else {// 交易失败
					resultMap.put("status", PayConstants.WITHDRAW_STATUS_FAIL);
					resultMap.put("Message", PayConstants.WITHDRAW_MSG_FAIL);
				}
				
			}else{
				resultMap.put("status", "0");
				resultMap.put("Message", refundMap.get("RespDesc").toString());
				return resultMap;
				
			}
		} catch (Exception e) {
			log.error("汇付代付查询异常");
			resultMap.put("status", "0");
			resultMap.put("Message", "查询异常");
			return resultMap;
		}
		
		return resultMap;
	}
	

}
