package com.xmniao.xmn.core.common.request.live;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * 
*    
* 项目名称：xmnService_3.1.2_zb   
* 类名称：UserInfoRequest   
* 类描述：   获取主播/观众信息请求参数
* 创建人：yezhiyong   
* 创建时间：2016年8月24日 上午9:35:36   
* @version    
*
 */
public class LiverInfoRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5021954835682682972L;

	//会员id
	@NotNull(message="会员id不能为空")
	private Integer uid;
	
	//用户类型: 0 机器人 1主播  2普通用户
	@NotNull(message="用户类型不能为空")
	private Integer type;
	
	//直播记录id
	@NotNull(message="直播记录id不能为空")
	private Integer liveRecordId;
	
	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getLiveRecordId() {
		return liveRecordId;
	}

	public void setLiveRecordId(Integer liveRecordId) {
		this.liveRecordId = liveRecordId;
	}

	@Override
	public String toString() {
		return "LiverInfoRequest [uid=" + uid + ", type=" + type
				+ ", liveRecordId=" + liveRecordId + "]";
	}

}
