package com.xmniao.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import com.xmniao.thrift.ledger.CommonAccountService;
import com.xmniao.thrift.ledger.PayAcountService;
import com.xmniao.thrift.ledger.PayAcountService.Client;

/**
 * 修改分账系统
 * 
 * @author YangJing
 * 
 *         2015年1月20日
 */
public class UpdateLedgerSystem {

    /**
     * 日志记录
     */
    private static Logger log = Logger.getLogger(UpdateLedgerSystem.class);

    /**
     * 分账服务ip
     */
    @Resource(name = "IP_NUMBER")
    private String IP_NUMBER;

    /**
     * 分账服务端口
     */
    @Resource(name = "PORT")
    private int PORT;

	/**
	 * 修改分账系统提现状态
	 * 
	 * @param params
	 * @return
	 */
	public int updateWithdrawState(Map<String, Object> params) {
		TTransport transport = null;
		try {

			log.info("updateWithdrawState----->IP_NUMBER：" + IP_NUMBER
					+ "------>PORT：" + PORT);

			//log.info("updateWithdrawState 更新分账系统提现状态参数："+params);
			transport = new TSocket(IP_NUMBER, PORT);
			TFramedTransport frame = new TFramedTransport(transport);
			// 设置传输协议为 TBinaryProtocol
			TProtocol protocol = new TBinaryProtocol(frame);
			TMultiplexedProtocol ManagerProtocol = new TMultiplexedProtocol(
					protocol, "PayAcountService");
			PayAcountService.Client client = new Client(ManagerProtocol);
			// 打开端口,开始调用
			transport.open();

			Map<String, String> map = new HashMap<String, String>();
			map.put("orderNumber", String.valueOf(params.get("orderNumber")));
			map.put("status", String.valueOf(params.get("status")));
			map.put("message", String.valueOf(params.get("Message")));
			map.put("platform_code",
					String.valueOf(params.get("platform_code")));
			log.info("updateWithdrawState 更新分账系统提现状态参数："+map);
			int result = client.updateWithdrawalsRecordState(map);
			log.info("分账服务修改提现订单响应参数：" + result);
			return result;
		} catch (TTransportException e) {
			log.error("设置调用的服务地址及端口异常，修改分账管理系统订单状态", e);
		} catch (Exception e) {
			log.error("设置调用的服务地址及端口异常", e);
		} finally {
			transport.close();
		}

		return 2;
	}
	
	/**
	 * 修改分账系统分账状态
	 * 
	 * @param params
	 * @return
	 */
	public int updateLedgerState(List<Map<String, String>> listMap) {
		TTransport transport = null;
		try {

			log.info("updateLedgerState----->IP_NUMBER：" + IP_NUMBER
					+ "------>PORT：" + PORT);

			transport = new TSocket(IP_NUMBER, PORT);
			TFramedTransport frame = new TFramedTransport(transport);
			// 设置传输协议为 TBinaryProtocol
			TProtocol protocol = new TBinaryProtocol(frame);
			TMultiplexedProtocol ManagerProtocol = new TMultiplexedProtocol(
					protocol, "PayAcountService");
			PayAcountService.Client client = new Client(ManagerProtocol);
			// 打开端口,开始调用
			transport.open();

			int result = client.updateDivideFlowState(listMap);
			
			log.info("分账服务修改分账订单响应参数：" + result);
			return result;
		} catch (TTransportException e) {
			log.error("设置调用的服务地址及端口异常，修改分账管理系统订单状态", e);
		} catch (Exception e) {
			log.error("设置调用的服务地址及端口异常", e);
		} finally {
			transport.close();
		}

		return 2;
	}
	
	//1:成功；0：失败
	public int updateLedgerTipState(Map<String, String> params){
		
		TTransport transport = null;
		try {

			log.info("updateLedgerTipState----->IP_NUMBER：" + IP_NUMBER
					+ "------>PORT：" + PORT);

			log.info("updateLedgerTipState 更新分账系统打赏状态参数："+params);
			List<Map<String,String>> paramsList = new ArrayList<Map<String,String>>();
			paramsList.add(params);
			transport = new TSocket(IP_NUMBER, PORT);
			TFramedTransport frame = new TFramedTransport(transport);
			// 设置传输协议为 TBinaryProtocol
			TProtocol protocol = new TBinaryProtocol(frame);
			TMultiplexedProtocol ManagerProtocol = new TMultiplexedProtocol(
					protocol, "CommonAccountService");
			CommonAccountService.Client client = new CommonAccountService.Client(ManagerProtocol);
			// 打开端口,开始调用
			transport.open();
			Map<String, String> result = client.updateTip(paramsList);
			log.info("分账服务修改打赏状态响应：" + result);
			
			return Integer.valueOf(result.get("code"));
		} catch (TTransportException e) {
			log.error("设置调用的服务地址及端口异常，修改分账管理系统订单状态", e);
		} catch (Exception e) {
			log.error("设置调用的服务地址及端口异常", e);
		} finally {
			transport.close();
		}

		return 0;
		
		
	}
}
