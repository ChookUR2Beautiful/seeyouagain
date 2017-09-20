package com.xmniao.xmn.core.util.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.xmniao.xmn.core.base.BaseEvent;
import com.xmniao.xmn.core.base.BaseListener;
import com.xmniao.xmn.core.common.dao.AreaDao;
import com.xmniao.xmn.core.common.service.AreaService;
import com.xmniao.xmn.core.system_settings.service.CategoryService;
import com.xmniao.xmn.core.util.StringUtils;
import com.xmniao.xmn.core.util.handler.AreaHandler;

public class LdHandlerListener implements BaseListener{

	private static final Log LOG=LogFactory.getLog(LdHandlerListener.class);
	
	
	private Object areaLock  = new Object();
	private Object tradeLock  = new Object();
	
	@Override
	public void event(final BaseEvent event) {
		try{
			String eventType = event.getType();
			Object source = event.getSource();
			 if(StringUtils.hasLength(eventType) ){
				 Object obj = event.getData();
				 if(eventType.equals(BaseEvent.AREA_EVENT)){
					 if(obj instanceof AreaDao){
						 synchronized (areaLock) {
							 if(source instanceof AreaService){
									AreaService areaService = (AreaService)source;
									areaService.updateAreas(null);
							 }
							 AreaHandler.getAreaHandler().areaHanlde((AreaDao)obj); 
						}
					 }
					 
				 }
				 if(eventType.equals(BaseEvent.TRADE_EVENT)){
					if(source instanceof CategoryService){
						synchronized (tradeLock) {
							CategoryService  tradeService= (CategoryService)source;
							tradeService.updateTrades();
						}
					}
				} 
			 }
		}catch(Exception exception){
			LOG.error("级联事件监听异常", exception);
		} 
		
	}
}
