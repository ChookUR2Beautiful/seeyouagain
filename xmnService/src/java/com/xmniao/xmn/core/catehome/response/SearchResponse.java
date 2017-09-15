package com.xmniao.xmn.core.catehome.response;

import java.util.Map;


public class SearchResponse {
	
	private int sellerid;
	
	private String sellerName;
	
	private String pic;
	
	private String zoneName;
	
	private int count;
	
	private String range;
	
	private String typename;
	
	private String tradename;
	
	private int lable;
	
	private Map<String,Object> subSet;
	
	private int zhibo_type=3;
	
	private String zhibomark ;

	public String getZhibomark() {
		return zhibomark;
	}

	public void setZhibomark(String zhibomark) {
		this.zhibomark = zhibomark;
	}

	public int getZhibo_type() {
		return zhibo_type;
	}

	public void setZhibo_type(int zhibo_type) {
		this.zhibo_type = zhibo_type;
	}

	public Map<String, Object> getSubSet() {
		return subSet;
	}

	public void setSubSet(Map<String, Object> subSet) {
		this.subSet = subSet;
	}

	/**
	 * 是否有正在直播，回放，预告记录
	 */
	private int isLive;
	
	private String url;
	

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	public int getIsLive() {
		return isLive;
	}

	public void setIsLive(int isLive) {
		this.isLive = isLive;
	}

	public int getLable() {
		return lable;
	}

	public void setLable(int lable) {
		this.lable = lable;
	}

	public int getSellerid() {
		return sellerid;
	}

	public void setSellerid(int sellerid) {
		this.sellerid = sellerid;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getTradename() {
		return tradename;
	}

	public void setTradename(String tradename) {
		this.tradename = tradename;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}


}
