package com.xmniao.xmn.core.util.listener;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.xmniao.xmn.core.base.BaseListener;
import com.xmniao.xmn.core.base.BaseEvent;
import com.xmniao.xmn.core.util.StringUtils;

public class AccessRemarkRecordListener implements BaseListener{
	private static final Log LOG = LogFactory.getLog(AccessRemarkRecordListener.class);
	private static final ThreadLocal<Map<String,Object>> threadLocal = new ThreadLocal<Map<String,Object>>();
	@Override
	public void event(BaseEvent event) {
		if(event.getType()==null){
			String[] remarks = (String[])event.getData();
			Integer state = event.getState();
			boolean isSuccess = state==null || state==1;
			String remark=null;
			Map<String, Object> map= new HashMap<String, Object>(1);
			try {
				remark = StringUtils.requestActiongLogging(remarks,state);
				Map<String, Object> m = get();
				boolean isRecord =  m != null;
				if(isRecord){
					remark = appendInfo(m,"actionRemark",remark);
					/*String actionRemark =(String)m.get("actionRemark");
					if(StringUtils.hasLength(actionRemark)){
						remark=actionRemark+"<br>"+remark;
					}*/	
				}
				if(!isSuccess){
					String errorInfo  = remarks[remarks.length-1];
					if(isRecord && m.containsKey("errorInfo")){
						errorInfo = appendInfo(m,"errorInfo",errorInfo);
					}
					map.put("errorInfo", errorInfo);
					
				}
				map.put("actionRemark", remark);
				map.put("actionResult", isSuccess?1:state);
				put(map);
			} catch (Exception e) {
				LOG.error("日志描述记录失败", e);
			}
		}
	}
	
	private String appendInfo(Map<String, Object> m,String key,String appendValue){
		String value =(String)m.get(key);
		if(StringUtils.hasLength(value)){
			value=value+"<br>"+appendValue;
		}	
		return value;
	}
	
	private void put(Map<String, Object> map){
		threadLocal.set(map);
	}
	
	public static Map<String, Object> get(){
		return threadLocal.get();
	}
	
	public static void clean(){
		threadLocal.remove();
	}
	

}
