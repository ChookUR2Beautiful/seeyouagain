/**
 * 2016年9月13日 下午2:33:59
 */
package com.xmniao.xmn.core.common.request.live;

import java.io.Serializable;

import net.sf.oval.constraint.NotNull;


/**
 * @项目名称：xmnService_3.1.2_zb
 * @类名称：PushSingleRequest
 * @类描述：
 * @创建人： yeyu
 * @创建时间 2016年9月13日 下午2:33:59
 * @version
 */
public class PushTodayFirstRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7311694126937752673L;

	@NotNull(message="观众ID不能为空")
	private Integer liverId;
	
	@NotNull(message="礼物ID不能为空")
	private Integer giftId;
	
	@NotNull(message="观众名称不能为空")
	private String liverName;
	
	@NotNull(message="群组号不能为空")
	private String groupId;
	
	@NotNull(message="用户头像不能为空")
	private String avatar;
	
	@NotNull(message="手机号码不能为空")
	private String phone;
	
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public Integer getLiverId() {
		return liverId;
	}
	public void setLiverId(Integer liverId) {
		this.liverId = liverId;
	}
	public Integer getGiftId() {
		return giftId;
	}
	public void setGiftId(Integer giftId) {
		this.giftId = giftId;
	}
	public String getLiverName() {
		return liverName;
	}
	public void setLiverName(String liverName) {
		this.liverName = liverName;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Override
	public String toString() {
		return "PushTodayFirstRequest [liverId=" + liverId + ", giftId="
				+ giftId + ", liverName=" + liverName + ", groupId=" + groupId
				+ ", avatar=" + avatar + ", phone=" + phone + "]";
	}
	
	
}
