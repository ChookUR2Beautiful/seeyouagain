package com.xmniao.xmn.core.common.request.live;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * 
*    
* 项目名称：xmnService_3.1.2_zb   
* 类名称：ContributeListRequest   
* 类描述：   房间贡献榜/贡献榜请求参数
* 创建人：yezhiyong   
* 创建时间：2016年8月16日 下午3:10:20   
* @version    
*
 */
public class ContributeListRequest extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6579368338658433593L;

	//页码
	@NotNull(message="页码不能为空")
	private Integer page=1;
	
	//0：土豪榜, 1：房间贡献榜:当前排行,  2：房间排行榜的:总排行, 3贡献榜 4.查看主播个人主页里面的:贡献榜 5 贡献榜-实体礼物 6 守护榜-实体礼物
	@NotNull(message="贡献榜类型不能为空")
	private Integer type=1;
	
	private Integer pageSize=20;
	
	//直播记录id  (类型5为用户uid)
	private Integer	 uid;
	
	//直播记录id
	private Integer	 liveRecordId;
	
	//主播id
	private Integer anchorId;

	private String sessiontoken;
	
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

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getLiveRecordId() {
		return liveRecordId;
	}

	public void setLiveRecordId(Integer liveRecordId) {
		this.liveRecordId = liveRecordId;
	}

	public Integer getAnchorId() {
		return anchorId;
	}

	public void setAnchorId(Integer anchorId) {
		this.anchorId = anchorId;
	}

	@Override
	public String getSessiontoken() {
		return sessiontoken;
	}

	@Override
	public void setSessiontoken(String sessiontoken) {
		this.sessiontoken = sessiontoken;
	}

	@Override
	public String toString() {
		return "ContributeListRequest{" +
				"page=" + page +
				", type=" + type +
				", pageSize=" + pageSize +
				", uid=" + uid +
				", liveRecordId=" + liveRecordId +
				", anchorId=" + anchorId +
				", sessiontoken='" + sessiontoken + '\'' +
				'}';
	}
}
