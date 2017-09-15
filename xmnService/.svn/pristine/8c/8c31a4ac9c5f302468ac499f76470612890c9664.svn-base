package com.xmniao.xmn.core.common.request.live;

import java.io.Serializable;

import net.sf.oval.constraint.NotNull;

/**
 * 
*    
* 项目名称：xmnService_3.1.2_zb   
* 类名称：LiveRecordRequest   
* 类描述：   预告/直播,回放列表
* 创建人：yezhiyong   
* 创建时间：2016年8月12日 下午4:17:55   
* @version    
*
 */
public class LiveRecordRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4610563493947987284L;

	//页码
	@NotNull(message="页码不能为空")
	private Integer page;
	
	//0:预告列表1:直播/回放列表
	@NotNull(message="预告/直播类型不能为空")
	private Integer type;
	
	//主播id
	private Integer anchorId;
	
	/**
	 * 会话令牌
	 */
	private String sessiontoken;
	
	//用户经度
	private Double longitude;
	
	//用户纬度
	private Double latitude;
	
	public Integer getAnchorId() {
		return anchorId;
	}

	public void setAnchorId(Integer anchorId) {
		this.anchorId = anchorId;
	}

	public String getSessiontoken() {
		return sessiontoken;
	}

	public void setSessiontoken(String sessiontoken) {
		this.sessiontoken = sessiontoken;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
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

	@Override
	public String toString() {
		return "LiveRecordRequest [page=" + page + ", type=" + type
				+ ", anchorId=" + anchorId + ", sessiontoken=" + sessiontoken
				+ ", longitude=" + longitude + ", latitude=" + latitude + "]";
	}

}
