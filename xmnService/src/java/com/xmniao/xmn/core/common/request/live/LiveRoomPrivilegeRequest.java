package com.xmniao.xmn.core.common.request.live;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：LiveRoomPrivilegeRequest   
* 类描述：   权限接口
* 创建人：yezhiyong   
* 创建时间：2017年2月17日 上午10:49:26   
* @version    
*
 */
public class LiveRoomPrivilegeRequest extends BaseRequest{

	private static final long serialVersionUID = 1L;

	@NotNull(message="类型不能为空")
	private Integer type;

	//直播记录id
	private Integer liveRecordId;
	
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
		return "TypeRequest [type=" + type + "]";
	}
	
	
}
