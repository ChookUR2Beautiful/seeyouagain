package com.xmniao.xmn.core.util.helper;

import com.xmniao.xmn.core.base.BaseEvent;
import com.xmniao.xmn.core.base.BaseListener;
import com.xmniao.xmn.core.base.BaseService;


public final class ListenerHelperSupport {
	
	private BaseListener[] listeners = new BaseListener[0];
	 private final Object listenersLock = new Object();
	
	public void addListener(BaseListener listener){
		synchronized (listenersLock) {
			BaseListener[] result =new BaseListener[listeners.length + 1];
			for(int i=0;i<listeners.length;i++){
				result[i]=listeners[i];
			}
			result[listeners.length]=listener;
			listeners = result;
		}
	}
	
	public BaseListener findListener(BaseListener listener){
		for(int i=0;i<listeners.length;i++){
			if(listeners[i]==listener){
				return listener;
			}	
		}
		return null;
	}
	
	public void removeListener(BaseListener listener){
		synchronized (listenersLock) {
			int n=-1;
			for(int i=0;i<listeners.length;i++){
				if(listeners[i]==listener){
					n=i;
					break;
				}
			}
			if(n<0){
				return;
			}
			BaseListener[] result =new BaseListener[listeners.length - 1];
			int j=0;
			for(int i=0;i<listeners.length;i++){
				if(i!=n){
					result[j++]=listeners[i];
				}
			}
			listeners = result;
		}
	}
	
	public void fireEvent(BaseService eventSource,String type,Object data,Integer state){
		BaseEvent event = new BaseEvent(eventSource,type, data,state);
		BaseListener[] interested=listeners;
		for(int i=0;i<interested.length;i++){
			interested[i].event(event);
		}
		
	}

}
