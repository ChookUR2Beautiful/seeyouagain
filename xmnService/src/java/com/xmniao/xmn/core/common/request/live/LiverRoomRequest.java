package com.xmniao.xmn.core.common.request.live;

import java.io.Serializable;

import com.xmniao.xmn.core.base.BaseRequest;
import net.sf.oval.constraint.Max;
import net.sf.oval.constraint.Min;
import net.sf.oval.constraint.NotNull;

/**
 * 
*    
* 项目名称：xmnService_zb   
* 类描述：  直播房间请求类
* 创建人：yhl   
* 创建时间：2016年8月12日 上午11:14:21   
 */
public class LiverRoomRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 856156629461076765L;

	@NotNull(message="直播ID不能为空")
	private Integer zhiboRecordId;
	
	private Integer uid;
	
	/**
	 * 商家名称
	 */
	private String location;
	
	/**
	 * 商家地址
	 */
	private String address;
	
	/**
	 * 直播标题
	 */
	private String title;
	
	/**
	 * 房间密码锁
	 */
	private String roomLock;
	
	/**
	 * 标签名称
	 */
	private String tagIds;
	
	//开播类型   0:通告开播    1:自定义开播  2自定义续播
	private Integer type;
	
	//自定义开播的封面
	private String cover;
	
	private Double longitude = 0D;
	
	private Double latitude = 0D;
	
	/**
	 * app端系统版本，如android 4.3或ios 7.1 
	 */
	@NotNull(message="App系统版本不能为空！")
	private String systemversion;
	
	/**
	 * API版本，默认版本1 范围1-99
	 */
	@Min(1)
	@Max(99)
	@NotNull(message="apiversion不能为空")
	private Integer apiversion=1;
	
	/**
	 * App端版本号 范围1.0.0-9.9.9
	 */
	@NotNull(message="App版本号错误！")
	private String appversion;	
	
	/**
	 * 回话令牌
	 */
	private String sessiontoken;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getZhiboRecordId() {
		return zhiboRecordId;
	}

	public void setZhiboRecordId(Integer zhiboRecordId) {
		this.zhiboRecordId = zhiboRecordId;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSessiontoken() {
		return sessiontoken;
	}

	public void setSessiontoken(String sessiontoken) {
		this.sessiontoken = sessiontoken;
	}

	public String getSystemversion() {
		return systemversion;
	}

	public void setSystemversion(String systemversion) {
		this.systemversion = systemversion;
	}

	public Integer getApiversion() {
		return apiversion;
	}

	public void setApiversion(Integer apiversion) {
		this.apiversion = apiversion;
	}

	public String getRoomLock() {
		return roomLock;
	}

	public void setRoomLock(String roomLock) {
		this.roomLock = roomLock;
	}

	public String getTagIds() {
		return tagIds;
	}

	public void setTagIds(String tagIds) {
		this.tagIds = tagIds;
	}

	public String getAppversion() {
		return appversion;
	}

	public void setAppversion(String appversion) {
		this.appversion = appversion;
	}

	@Override
	public String toString() {
		return "LiverRoomRequest [zhiboRecordId=" + zhiboRecordId + ", uid="
				+ uid + ", location=" + location + ", address=" + address
				+ ", title=" + title + ", roomLock=" + roomLock + ", tagIds="
				+ tagIds + ", type=" + type + ", cover=" + cover
				+ ", longitude=" + longitude + ", latitude=" + latitude
				+ ", systemversion=" + systemversion + ", apiversion="
				+ apiversion + ", appversion=" + appversion + ", sessiontoken="
				+ sessiontoken + "]";
	}


}
