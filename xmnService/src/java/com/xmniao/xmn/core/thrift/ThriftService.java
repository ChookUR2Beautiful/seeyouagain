package com.xmniao.xmn.core.thrift;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.protocol.ResponseDate;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
* 类名称：ThriftService   
* 类描述：   thrift 接口调用类
* 创建人：xiaoxiong   
* 创建时间：2016年12月21日 下午6:10:58
 */
@Service
public class ThriftService {
	/**
	 * 支付接口地址
	 */
	@Autowired
	private String ip_number_pay;
	
	/**
	 * 支付接口端口号
	 */
	@Autowired
	private String port_pay;
	
	
	/**
	 * 用户鸟豆明细
	 * @author xiaoxiong
	 * @date 2016年12月21日
	 */
	public List<ResponseSubList> getBirdBeansList(int uid,int pageNo,int pageSize,String sdate,String edate){
		TTransport transport = null;
		try {
			 // 设置调用的服务地址为本地，端口为 7911
            transport = new TSocket(ip_number_pay,Integer.valueOf(port_pay));
            TFramedTransport frame = new TFramedTransport(transport);
            // 设置传输协议为 TBinaryProtocol
            TProtocol protocol = new TBinaryProtocol(frame);
            
            //商家服务模块
            TMultiplexedProtocol liveProtocol = new TMultiplexedProtocol(
                    protocol, "LiveWalletService");
            LiveWalletService.Client client = new LiveWalletService.Client(liveProtocol);
            transport.open();
            Map<String, String> params = new HashMap<>();
            params.put("uid", uid+"");
            
            if(sdate!=null && !sdate.equals("")){
            	params.put("sdate", sdate);
            }
            if(edate!=null && !edate.equals("")){
            	params.put("edate", edate);
            }
            
            params.put("uid", uid+"");
            params.put("pageNo2", pageNo+"");
            params.put("pageSize2", pageSize+"");
            ResponseList responseDate = client.getBirdBeansList(params);
            if(responseDate.getDataList()!=null  && responseDate.getDataList().size()>0){
            	return responseDate.getDataList();
            }
            return null;
            
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(transport!=null){
				transport.close();
			}
		}
		return null;
	}
	
	 	/**
	 	 * 获取鸟币收支明细
	 	 * @author xiaoxiong
	 	 * @date 2016年12月23日
	 	 * @param type 0 收入 1 支出
	 	 */
	public List<Map<String, String>> birdCoinDetail(int uid, int pageNo,
			int pageSize, int type) {
		TTransport transport = null;
		try {
			// 设置调用的服务地址为本地，端口为 7911
			transport = new TSocket(ip_number_pay, Integer.valueOf(port_pay));
			TFramedTransport frame = new TFramedTransport(transport);
			// 设置传输协议为 TBinaryProtocol
			TProtocol protocol = new TBinaryProtocol(frame);

			// 商家服务模块
			TMultiplexedProtocol liveProtocol = new TMultiplexedProtocol(
					protocol, "LiveWalletService");
			LiveWalletService.Client client = new LiveWalletService.Client(
					liveProtocol);
			transport.open();
			Map<String, String> params = new HashMap<>();
			params.put("uid", uid + "");
			params.put("pageNo", pageNo + "");
			params.put("pageSize", pageSize + "");
			params.put("type", type + "");

			List<Map<String, String>> resultlist = client
					.birdCoinDetail(params);
			if (resultlist != null && resultlist.size() > 0) {
				return resultlist;
			}

			return null;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (transport != null) {
				transport.close();
			}
		}
		return null;
	}
	
	public Map<String, String> countBirdCoin(int uid) {
		TTransport transport = null;
		try {
			// 设置调用的服务地址为本地，端口为 7911
			transport = new TSocket(ip_number_pay, Integer.valueOf(port_pay));
			TFramedTransport frame = new TFramedTransport(transport);
			// 设置传输协议为 TBinaryProtocol
			TProtocol protocol = new TBinaryProtocol(frame);

			// 商家服务模块
			TMultiplexedProtocol liveProtocol = new TMultiplexedProtocol(protocol, "LiveWalletService");
			LiveWalletService.Client client = new LiveWalletService.Client(liveProtocol);
			transport.open();
			Map<String, String> params = new HashMap<>();
			params.put("uid", uid + "");
			Map<String, String> map = client.countBirdCoin(params);
			if (params != null ) {
				return map;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (transport != null) {
				transport.close();
			}
		}
		return null;
	}
	
	
}
