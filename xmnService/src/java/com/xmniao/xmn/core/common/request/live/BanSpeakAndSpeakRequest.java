package com.xmniao.xmn.core.common.request.live;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * 
*    
* 项目名称：xmnService_3.1.2_zb   
* 类名称：BanSpeakRequest   
* 类描述：   主播禁言观众/主播解除观众禁言接口
* 创建人：yezhiyong   
* 创建时间：2016年8月31日 上午9:10:48   
* @version    
*
 */
public class BanSpeakAndSpeakRequest extends BaseRequest{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -419334692697437120L;

	//会员id不能为空
	@NotNull(message="uid不能为空")
	private Integer uid;
	
	//直播记录id不能为空
	@NotNull(message="直播记录id不能为空")
	private Integer liveRecordId;
	
	//类型 : 1 禁言  2 解除禁言
	@NotNull(message="类型不能为空")
	private Integer type;

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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "BanSpeakAndSpeakRequest [uid=" + uid + ", liveRecordId="
				+ liveRecordId + ", type=" + type + "]";
	}
	
}
