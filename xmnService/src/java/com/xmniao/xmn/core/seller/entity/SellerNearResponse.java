package com.xmniao.xmn.core.seller.entity;

import java.util.Map;

public class SellerNearResponse {
	
	private String sellername;
	
	private int conCount;//消费人数
    private String range; //距离
    private Double distance;//纯数字的距离用于排序
    private String sellerid;//商家ID
    private String url;
    private String tradename;
    private String zoneName;
    private int lable;
    private Map<String,Object> subSet;
    private String zhibomark ;
    
    
	public String getZhibomark() {
		return zhibomark;
	}
	public void setZhibomark(String zhibomark) {
		this.zhibomark = zhibomark;
	}
	public int getLable() {
		return lable;
	}
	public void setLable(int lable) {
		this.lable = lable;
	}
	public Map<String, Object> getSubSet() {
		return subSet;
	}
	public void setSubSet(Map<String, Object> subSet) {
		this.subSet = subSet;
	}
	private int zhibo_type=5; //默认5 没有直播
    
	public int getZhibo_type() {
		return zhibo_type;
	}
	public void setZhibo_type(int zhibo_type) {
		this.zhibo_type = zhibo_type;
	}
	public String getZoneName() {
		return zoneName;
	}
	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}
	
	
	
	public int getConCount() {
		return conCount;
	}
	public void setConCount(int conCount) {
		this.conCount = conCount;
	}
	public String getSellername() {
		return sellername;
	}
	public void setSellername(String sellername) {
		this.sellername = sellername;
	}
	public String getRange() {
		return range;
	}
	public void setRange(String range) {
		this.range = range;
	}
	public String getSellerid() {
		return sellerid;
	}
	public void setSellerid(String sellerid) {
		this.sellerid = sellerid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTradename() {
		return tradename;
	}
	public void setTradename(String tradename) {
		this.tradename = tradename;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
    
    
    
     

}
