/**
 * 2016年9月13日 下午2:33:59
 */
package com.xmniao.xmn.core.common.request.live;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * @项目名称：xmnService_3.1.2_zb
 * @类名称：PushSingleRequest
 * @类描述：
 * @创建人： yeyu
 * @创建时间 2016年9月13日 下午2:33:59
 * @version
 */
public class PushSingleRequest {

	/**
	 *long
	 */
	private static final long serialVersionUID = -8905639062000408105L;
	@NotNull(message="主播ID不能为空")
	private Integer anchorId;
	@NotNull(message="直播记录ID不能为空")
	private Integer recordId;
	@NotNull(message="主播名称不能为空")
	private String anchorName;
	@NotNull(message="商户名称不能为空")
	private String  sellerName;
	@NotNull(message="计划开始时间不能为空")
	private String startTime;
	@NotNull(message="房间号不能为空")
	private String roomNo;
	@NotNull(message="群组号不能为空")
	private String groupId;

//	@NotNull(message="操作类型不能为空")
	private int type=1;
	public Integer getAnchorId() {
		return anchorId;
	}
	public void setAnchorId(Integer anchorId) {
		this.anchorId = anchorId;
	}
	public Integer getRecordId() {
		return recordId;
	}
	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}
	public String getAnchorName() {
		return anchorName;
	}
	public void setAnchorName(String anchorName) {
		this.anchorName = anchorName;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	@Override
	public String toString() {
		return "PushSingleRequest [anchorId=" + anchorId + ", recordId="
				+ recordId + ", anchorName=" + anchorName + ", sellerName="
				+ sellerName + ", startTime=" + startTime + ", roomNo="
				+ roomNo + ", groupId=" + groupId + ", type=" + type + "]";
	}
	
	
	
	
	
}
