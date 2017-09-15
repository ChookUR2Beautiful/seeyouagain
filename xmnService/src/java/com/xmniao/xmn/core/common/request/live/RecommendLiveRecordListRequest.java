package com.xmniao.xmn.core.common.request.live;

import java.io.Serializable;

import com.xmniao.xmn.core.base.BaseRequest;
import net.sf.oval.constraint.NotNull;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：RecommendLiveRecordListRequest   
* 类描述：   推荐的直播列表/预告列表/精彩时刻列表请求参数
* 创建人：yezhiyong   
* 创建时间：2016年12月3日 下午2:32:11   
* @version    
*
 */
public class RecommendLiveRecordListRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4610563493947987284L;
	
	/**
	 * 会话令牌
	 */
	private String sessiontoken;
	
	/**
	 * 用户经度
	 */
	private Double longitude;
	
	/**
	 * 用户纬度
	 */
	private Double latitude;
	
	/**
	 * 开始时间
	 */
	private String sdate;
	/**
	 * 结束时间
	 */
	private String edate;
	
	/**
	 * 列表类型  0预告列表  1直播列表   2精彩时刻列表
	 */
	@NotNull(message="列表类型不能为空")
	private Integer liveType;
	
	/**
	 * type 预告列表帅选条件 1 我喜欢的主播(关注)   2我感兴趣的店铺(消费,浏览)  3.我附近   4.最多人想去
	 */
	private Integer type;
	
	/**
	 * 页码
	 */
	private Integer page = 1;

	private Integer pageSize = 10;
	
	private Integer anchorId;
	
	public Integer getAnchorId() {
		return anchorId;
	}

	public void setAnchorId(Integer anchorId) {
		this.anchorId = anchorId;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}

	public Integer getLiveType() {
		return liveType;
	}

	public void setLiveType(Integer liveType) {
		this.liveType = liveType;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getSessiontoken() {
		return sessiontoken;
	}

	public void setSessiontoken(String sessiontoken) {
		this.sessiontoken = sessiontoken;
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

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "RecommendLiveRecordListRequest{" +
				"sessiontoken='" + sessiontoken + '\'' +
				", longitude=" + longitude +
				", latitude=" + latitude +
				", sdate='" + sdate + '\'' +
				", edate='" + edate + '\'' +
				", liveType=" + liveType +
				", type=" + type +
				", page=" + page +
				", pageSize=" + pageSize +
				", anchorId=" + anchorId +
				'}';
	}
}
