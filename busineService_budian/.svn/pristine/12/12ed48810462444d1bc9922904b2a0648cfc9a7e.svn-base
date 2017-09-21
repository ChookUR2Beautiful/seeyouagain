package com.xmniao.service.quartz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.thrift.pay.ResponseData;
import com.xmniao.thrift.pay.XmerWalletService;
import com.xmniao.urs.dao.XmerDao;
import com.xmniao.util.ArithUtil;
import com.xmniao.util.Constant;

public class HotPersonQuertzServie {

	@Autowired
	private XmerDao xmerDao;
	@Autowired
	private StringRedisTemplate stringredisTemplate;
	
	@Autowired
	private String transLedgerIP;
	@Autowired
	private String transLedgerPort;
	
	
	
	public void execute(){
		
		List<Map<Object,Object>> xmerList=xmerDao.queryXmer();
		stringredisTemplate.opsForZSet().removeRange(Constant.XMER_RANK_KEY,0,-1);
		
		int i=0;
		for(Map<Object,Object> xmerMap:xmerList){
			i++;
			//调用支付服务获取收入总金额
			double allincome=getAllincome(xmerMap.get("uid")+"");
			JSONObject json=new JSONObject();
			json.put("id", xmerMap.get("id"));
			json.put("soldnums", xmerMap.get("sold_nums"));
			json.put("partnernums", xmerMap.get("partner_nums"));			
			json.put("achievement", xmerMap.get("achievement"));
			json.put("achievement", xmerMap.get("achievement"));
			json.put("userpic", xmerMap.get("avatar"));
			json.put("username", xmerMap.get("name"));
			json.put("rankno", i);
			json.put("uid", xmerMap.get("uid"));
			json.put("allincome", allincome);
			stringredisTemplate.opsForZSet().add(Constant.XMER_RANK_KEY,json.toString(),i);
		}
		
		Set<String> set=stringredisTemplate.opsForZSet().range(Constant.XMER_RANK_KEY, 0, -1);
	}



	private double getAllincome(String uid) {
		TTransport transport=null;
		try {
			transport = new TSocket(transLedgerIP, Integer.parseInt(transLedgerPort));
			TFramedTransport frame = new TFramedTransport(transport);
			// 设置传输协议为 TBinaryProtocol
			TProtocol protocol = new TBinaryProtocol(frame);
			TMultiplexedProtocol ManagerProtocol = new TMultiplexedProtocol(
					protocol, "XmerWalletService");
			XmerWalletService.Client client = new XmerWalletService.Client(
					ManagerProtocol);
			// 打开端口,开始调用
			transport.open();
			Map<String, String> paramMap=new HashMap<>();
			paramMap.put("uid",uid);
			ResponseData responseData= client.getXmerWallet(paramMap);
			if(responseData!=null){
				if(responseData.getState()==0){
				Map<String,String> resultMap=responseData.getResultMap();
				if(resultMap!=null){
					double profit=Double.parseDouble(resultMap.get("profit"));
					double trunout=Double.parseDouble(resultMap.get("trunout"));
					return ArithUtil.add(profit, trunout);
				}
				}else{
					return 0;
				}
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("调用支付服务失败");
			return 0;
		}finally{
			transport.close();
		}	
		return 0;
	}

	
	
}
