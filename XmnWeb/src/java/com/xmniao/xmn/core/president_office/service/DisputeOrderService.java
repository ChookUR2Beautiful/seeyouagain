package com.xmniao.xmn.core.president_office.service;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.exception.ApplicationException;
import com.xmniao.xmn.core.president_office.util.PresidentConstants;
import com.xmniao.xmn.core.thrift.client.proxy.ThriftClientProxy;
import com.xmniao.xmn.core.thrift.service.ledgerService.BusineAccountService;
import com.xmniao.xmn.core.thrift.service.ledgerService.Result;
import com.xmniao.xmn.core.thrift.util.ThriftClientUtil;
@SuppressWarnings("rawtypes")
@Service
public class DisputeOrderService extends BaseService{
	
	private final Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name = "ledgerServiceClient")
	private ThriftClientProxy ledgerServiceClient;
	@Autowired
	private ThriftClientUtil thriftClientUtil;
	
	private ThriftClientUtil getThriftClienUtil(){
		return thriftClientUtil;
	}
	public void setThriftClientUtil(ThriftClientUtil ThriftClientUtil){
		this.thriftClientUtil=thriftClientUtil;
	}
	@SuppressWarnings({ "unchecked" })
	public Pageable queryOrder(Map<String,String> queryParams) throws Exception {
		Pageable pageable = new Pageable();
		TSocket socket = null;	
		try {
			Map<String,String> mapIp=new HashMap<String,String>();
			
			mapIp=thriftClientUtil.getConfigMapIP("BusineAccountService");
			socket = new TSocket(mapIp.get("ip"), Integer.parseInt(mapIp.get("port")));			
			TFramedTransport frame = new TFramedTransport(socket);
			TProtocol protocol = new TBinaryProtocol(frame);
			TMultiplexedProtocol ManagerProtocol = new TMultiplexedProtocol(protocol, "BusineAccountService");
			BusineAccountService.Client client = new BusineAccountService.Client(ManagerProtocol);
			socket.open();
			queryParams.put("pageSize", queryParams.get("limit"));
			queryParams.remove("limit");
			log.info(queryParams);
			Result result;
			pageable.setPage(Integer.parseInt(String.valueOf(queryParams.get("page"))));
			pageable.setPageSzie(Integer.parseInt(String.valueOf(queryParams.get("pageSize"))));
			try {
				result = client.queryOrder(queryParams);
				pageable.setContent(result.getData());
				pageable.setTotal(result.getTotal());
			} catch (TException e) {
				log.error("查询争议订单失败", e);
				pageable.setTotal(0l);
			} 
		} catch (Exception e) {
			throw new Exception();
		} finally{
			socket.close();  
		}
		return pageable;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public String updateOrderState(Map<String, String> queryParams){
		String res = null;
		try {
			res=getRes(queryParams);//调用接口更新
		} catch (Exception e) {
			this.log.error("更新争议订单状态异常：", e);
			throw new ApplicationException("更新争议订单状态",e,new Object[]{queryParams},new String[]{"争议订单",queryParams.get("id"),"更新状态","更新状态"});
		} finally{
			thriftClientUtil.close(); // 关闭端口
		}
		return res;
	}
    public String getRes(Map<String, String> queryParams){
    	String res=null;
		try {
			BusineAccountService.Client client = new BusineAccountService.Client(
					thriftClientUtil.getThriftClient("BusineAccountService"));
			res = client.updateOrderState(queryParams);//实际调用接口
		}catch (Exception e) {
			this.log.error("更新争议订单状态接口异常：", e);
			throw new ApplicationException("更新争议订单状态接口",e,new Object[]{queryParams});
		}
    	return  res;
    }
	public Map<String,String> getMsg(String res){//参数组装 
		int flag = PresidentConstants.FLAG_0;//0
		Map<String,String> map=new HashMap<String,String>();
		String msg="";
		if(res.equals(String.valueOf(PresidentConstants.FLAG_0))){//0
			msg = "数据提交成功";
			flag = PresidentConstants.FLAG_1;//1
		}else if(res.equals(String.valueOf(PresidentConstants.FLAG_1))){//1
			msg = "数据提交失败或无数据";
		}else if(res.equals(String.valueOf(PresidentConstants.FLAG_2))){//2
			msg = "数据提交异常";
		}
		map.put("msg", msg);
		map.put("flag", String.valueOf(flag));
		return map;
	}
    
	@Override
	protected BaseDao getBaseDao() {
		return null;
	}

}
