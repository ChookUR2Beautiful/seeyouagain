package com.xmniao.service.impl;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.xmniao.common.AgentPayRequest;
import com.xmniao.common.HttpClientUtil;
import com.xmniao.common.HttpRequest;
import com.xmniao.common.PayConstants;
import com.xmniao.common.RTSign;
import com.xmniao.common.ReapalUtil;
import com.xmniao.dao.UpdateWithdrawalsRecordStateMapper;
import com.xmniao.service.RongTWithdrawMoney;
import com.xmniao.service.pay.WithdrawMoneyServiceImpl;

/**
 * 
 * @author DongJieTao
 * 
 */
@Service("RongTWithdrawMoneyImpl")
public class RongTWithdrawMoneyImpl implements RongTWithdrawMoney {

    /**
     * 日志记录
     */
    private final Logger log = Logger.getLogger(RongTWithdrawMoneyImpl.class);
    /** 提现记录Manager */
    @Autowired
    private UpdateWithdrawalsRecordStateMapper updateWithdrawalsRecordStateMapper;
    /** 提现服务 */
    @Autowired
    private WithdrawMoneyServiceImpl withdrawMoneyServiceImpl;

    /**
     * 融宝代发（提现）
     * 
     * @param map
     * @return
     * @throws Exception
     */
    @Override
    public Map<String, String> WithdrawMoney(AgentPayRequest agentPayRequest) throws Exception {// 融宝提现

	log.info("==============融宝提现接口进入=============");
	log.info("AgentPayRequest："+agentPayRequest);
	Map<String, String> result = new HashMap<String, String>();
	result.put("status", "fail");
	result.put("reason", "");
	
	Map<String, String> map = new HashMap<String, String>(0);
	map.put("charset", "UTF-8");
	map.put("trans_time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	map.put("notify_url", ReapalUtil.getNotify_url());
	System.out.println("notify_url:"+ReapalUtil.getNotify_url());
	map.put("batch_no", agentPayRequest.getBatch_no());
	map.put("batch_count", agentPayRequest.getBatch_count());
	map.put("batch_amount", agentPayRequest.getBatch_amount());
	map.put("pay_type", agentPayRequest.getPay_type());
	map.put("content", agentPayRequest.getContent());

	String mysign = ReapalUtil.BuildMysign(map, ReapalUtil.getKey());

	map.put("sign", mysign);
	map.put("sign_type", ReapalUtil.getSign_type());
	log.info("融宝代付申请请求数据:"+map);
	String json = JSON.toJSONString(map);

	Map<String, String> maps = ReapalUtil.addkey(json);
	maps.put("merchant_id", ReapalUtil.getMerchant_id());
	maps.put("version", ReapalUtil.getVersion());
	log.info("融宝代付申请请求加密后数据：" + com.alibaba.fastjson.JSON.toJSONString(maps));
	String post = HttpClientUtil.post(ReapalUtil.getUrl() + "agentpay/pay", maps);
	
	String res = ReapalUtil.pubkey(post);
	Map<String,String> resMap =(Map<String,String>) JSON.parse(res);
    log.info("融宝代付申请同步返回数据为:"+resMap);
    if(resMap==null){
    	log.error("请求失败");
    	result.put("status", "fail");
    	result.put("reason", "");
    	return result;
    }
    if(resMap.get("result_code") != null && "0000".equals(resMap.get("result_code"))){
    	log.info("成功");
    	result.put("status", "succ");
    	result.put("reason", "");
    	return result;
    }else{
    	log.info("失败："+resMap.get("result_msg"));
    	result.put("status", "fail");
    	result.put("reason", resMap.get("result_msg"));
    }
	return result;
    }

    /**
     * 融宝提现查询
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> WithdrawMoneyQuery_Old(Map<String, Object> map) throws Exception {// 融宝提现查询

	List<Map<String, Object>> list = null;
	RTSign.qPaddingData(map);// 基础参数填充

	map.put("sign", RTSign.getSign(map));// 参数签名

	String params = RTSign.CreateLinkString(map);// 参数排序组合

	InputStream input = HttpRequest.sendPostRTQ(RTSign.getQueryUrl(), params);// 提交post提现查询请求，返回字节流

	if (input == null) {

	    throw new Exception("查询返回信息为空");
	}
	String str = HttpRequest.inputStreamToString(input);// 字节流转换为String

	// log.info("融宝查询返回参数：" + str);
	if (str.startsWith("<Resp>")) {
	    try {
		Document doc = DocumentHelper.parseText(str);
		Element root = doc.getRootElement();
		String st = root.elementText("status");
		String reason = root.elementText("reason");

		if (st != null && st.equals("fail")) {
		    list = new ArrayList<Map<String, Object>>();
		    Map<String, Object> mapf = new HashMap<String, Object>();
		    mapf.put("status", st);
		    mapf.put("reason", reason);
		    list.add(mapf);
		}

	    } catch (DocumentException e2) {
		log.error("提交查询失败，请查证！订单号为：" + map.get("batchCurrnum"), e2);
	    }
	} else {
	    try {
		str = RTSign.jim(str);// 解密
	    } catch (Exception e1) {
		log.error("提交查询数据有误，请查证！订单号为：" + map.get("batchCurrnum"), e1);
	    }

	    Map<String, Object> mapParms = new HashMap<String, Object>();
	    try {
		mapParms = RTSign.parseString(str);// 参数解析，返回map
	    } catch (Exception e) {
		log.error("提交查询参数错误，解析为空，请查证！订单号为：" + map.get("batchCurrnum"), e);
	    }

	    list = RTSign.splistParams(mapParms);// 交易明细参数拆分
	}
	// list-->map--->单笔交易参数
	return list;// 返回参数

    }

    public static void main(String[] args) throws Exception {

	FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext(new String[] {
		"WebRoot/WEB-INF/pay-context.xml", "WebRoot/WEB-INF/pay-service.xml", "WebRoot/WEB-INF/pay-rocketmq.xml",
		"WebRoot/WEB-INF/pay-quartz.xml", "WebRoot/WEB-INF/pay-thrift.xml", "WebRoot/WEB-INF/pay-servlet.xml" }, true);
	context.start();

	WithdrawMoneyServiceTools withdrawMoneyServiceTools = context.getBean(WithdrawMoneyServiceTools.class);

	Map<String, Object> mapEntity = withdrawMoneyServiceTools.getOrderData("888150841", 2);

	RongTWithdrawMoneyImpl rt = context.getBean(RongTWithdrawMoneyImpl.class);
//	Map<String,Object> reqMap = new HashMap<String,Object>();
//	reqMap.put("date", "2016-05-14 12:25:25");
//	reqMap.put("batchCurrnum", "888150837_2");
//	List list= rt.WithdrawMoneyQuery2(reqMap);
//	System.out.println(list);
	AgentPayRequest agentPayRequest = WithdrawMoneyServiceTools.putTheDataR(mapEntity,"2");// 参数组装

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

    }
    
    
    /**
     * 融宝提现查询
     * 
     * @param map
     * @return
     * @throws Exception
     */
    @Override
    public List<Map<String, Object>> WithdrawMoneyQuery(Map<String,Object> reqMap) throws Exception {// 融宝提现查询
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	Map<String,Object> resultMap = new HashMap<String,Object>();
	    AgentPayRequest agentPayRequest = new AgentPayRequest();
	    agentPayRequest.setTrans_time(reqMap.get("date").toString()); //申请代付时间
	    agentPayRequest.setBatch_no(reqMap.get("batchCurrnum").toString());
	    agentPayRequest.setDetail_no(reqMap.get("batchCurrnum").toString());
		Map<String, String> map = new HashMap<String, String>(0);
		map.put("charset", ReapalUtil.getCharset());
		map.put("trans_time",
				agentPayRequest.getTrans_time().trim());
		map.put("notify_url", ReapalUtil.getNotify_url());
		map.put("batch_no", agentPayRequest.getBatch_no());
		//map.put("detail_no", "1");//agentPayRequest.getDetail_no()
		map.put("next_tag", "1");
		String mysign = ReapalUtil.BuildMysign(map, ReapalUtil.getKey());
		
		map.put("sign", mysign);
		map.put("sign_type", ReapalUtil.getSign_type());
	
		String json = JSON.toJSONString(map);
		log.info("融宝代付查询数据json:"+json);
		Map<String, String> maps = ReapalUtil.addkey(json);
		maps.put("merchant_id", ReapalUtil.getMerchant_id());
		maps.put("version", ReapalUtil.getVersion());
		log.info("融宝代付查询数据，加密后json:"+maps);

		String post = HttpClientUtil.post(ReapalUtil.getUrl()
				+ "agentpay/batchpayquery", maps);
		String res = ReapalUtil.pubkey(post);
		Map<String,String> resMap =(Map<String,String>) JSON.parse(res);
	    log.info("融宝代付查询同步返回数据为:"+resMap);
	    
	    if(resMap==null){
	    	log.error("请求失败");
	    	resultMap.put("status", "fail");
	    	resultMap.put("reason", "返回结果解析异常");
	    	list.add(resultMap);
	    	return list;
	    }
	    
	    if(resMap.get("result_code") != null && "0001".equals(resMap.get("result_code"))){
	    	log.info("查询状态：成功");
	    	String content = resMap.get("content")==null?"":resMap.get("content");
	    	String[] result = content.split(",");
	    	if(result.length>=12){
	    		if(result[11].equals("成功")){
	        		log.info("代付成功了。。");
	        		resultMap.put("tradeReason", "代付成功");
	        	}else if(result[11].equals("失败")){
	        		log.error("代付失败了。。。"+result[12]);
		        	resultMap.put("tradeReason", result[12]);
	        	}else{
	        		log.error("代付状态："+result[11]);
		        	resultMap.put("tradeReason", result.length>12?result[12]:"");
	        	}
	    		resultMap.put("tradeFeedbackcode", result[11]);
	        	list.add(resultMap);
	        	return list;
	    	} else{
	    		log.error("代付失败了");
	    		resultMap.put("status", "fail");
	        	resultMap.put("reason", "返回结果解析异常");
	        	list.add(resultMap);
	        	return list;
	    	}
	    }else{
	    	log.info("查询状态：失败  "+resMap.get("result_msg"));
	    	resultMap.put("status", "fail");
	    	resultMap.put("reason", resMap.get("result_msg"));
	    	list.add(resultMap);
	    	return list;
	    }
    }
}
