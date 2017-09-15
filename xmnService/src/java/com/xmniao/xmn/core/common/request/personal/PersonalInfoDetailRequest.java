package com.xmniao.xmn.core.common.request.personal;

import java.io.Serializable;

import com.xmniao.xmn.core.base.BaseRequest;
import net.sf.oval.constraint.NotNull;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：PersonalInfoDetailRequest   
* 类描述：    用户/主播详情 请求参数
* 创建人：yezhiyong   
* 创建时间：2016年11月16日 下午5:21:41   
* @version    
*
 */
public class PersonalInfoDetailRequest extends BaseRequest {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -335954408768486754L;

	//活动行为类型  1 主播直播动态  2去过的店铺  3粉丝  4关注 5参加的直播活动  6成长足迹
	@NotNull(message="活动行为类型不能为空")
	private Integer type;
	
	//用户的uid
	@NotNull(message="用户的uid不能为空")
	private Integer uid;
	
	//页码
	@NotNull(message="页码不能为空")
	private Integer page;

	private String maxTime; //v3.6版本 查询 主播直播动态 和 参加的直播活动， 需要传
	
	//用户经度
	private Double longitude;
	
	//用户纬度
	private Double latitude;
	
	/**
	 * 会话令牌
	 */
	private String sessiontoken;
	
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

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
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

	public String getMaxTime() {
		return maxTime;
	}

	public void setMaxTime(String maxTime) {
		this.maxTime = maxTime;
	}

	@Override
	public String toString() {
		return "PersonalInfoDetailRequest{" +
				"type=" + type +
				", uid=" + uid +
				", page=" + page +
				", maxTime='" + maxTime + '\'' +
				", longitude=" + longitude +
				", latitude=" + latitude +
				", sessiontoken='" + sessiontoken + '\'' +
				'}';
	}
}
