package com.xmniao.xmn.core.common.request.live;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * 
*    
* 项目名称：xmnService_3.1.2_zb   
* 类名称：AttentionListRequest   
* 类描述：   关注列表的请求参数
* 创建人：yezhiyong   
* 创建时间：2016年8月18日 上午11:10:06   
* @version    
*
 */
public class AttentionListRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5334898127576380250L;

	//页码
	@NotNull(message="页码不能为空")
	private Integer page;
	
	private Integer uid;
	
	//用户经度
	@NotNull(message="经度不能为空")
	private Double longitude;
	
	//用户纬度
	@NotNull(message="纬度不能为空")
	private Double latitude;

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

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	@Override
	public String toString() {
		return "AttentionListRequest [page=" + page + ", uid=" + uid
				+ ", longitude=" + longitude + ", latitude=" + latitude + "]";
	}


}
