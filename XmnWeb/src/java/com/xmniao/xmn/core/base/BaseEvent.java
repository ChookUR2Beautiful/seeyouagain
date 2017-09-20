package com.xmniao.xmn.core.base;

import java.util.EventObject;


public final class BaseEvent extends EventObject{
	
	public static final String AREA_EVENT = "area_event";
	public static final String TRADE_EVENT = "trade_event";
	
	public BaseEvent(BaseService eventSource,String type,Object data) {
		this(eventSource,type,data,null);
	}
	
	public BaseEvent(BaseService eventSource,String type,Object data,Integer state) {
		super(eventSource);
		this.type=type;
		this.data=data;
		this.state=state;
	}
	
	
	
	private final String type;
	private final Object data;
	private final Integer state;
	
	public Object getData() {
		return data;
	}
	public String getType() {
		return type;
	}
	public Integer getState() {
		return state;
	}

	@Override
	public BaseService getSource() {
		return (BaseService)super.getSource();
	}



}
