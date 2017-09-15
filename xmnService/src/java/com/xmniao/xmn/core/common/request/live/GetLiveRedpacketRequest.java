package com.xmniao.xmn.core.common.request.live;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：GetLiveRedpacketRequest   
* 类描述：   领取红包请求参数
* 创建人：yezhiyong   
* 创建时间：2016年12月22日 下午6:02:32   
* @version    
*
 */
public class GetLiveRedpacketRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3007685763082979843L;
	
	/**
	 * 直播红包id
	 */
	@NotNull(message="直播红包id不能为空")
	private Integer liveRedpacketId;
	
	/**
	 * 直播记录id
	 */
	@NotNull(message="直播记录id不能为空")
	private Integer liveRecordId;

	public Integer getLiveRedpacketId() {
		return liveRedpacketId;
	}

	public void setLiveRedpacketId(Integer liveRedpacketId) {
		this.liveRedpacketId = liveRedpacketId;
	}

	public Integer getLiveRecordId() {
		return liveRecordId;
	}

	public void setLiveRecordId(Integer liveRecordId) {
		this.liveRecordId = liveRecordId;
	}

	@Override
	public String toString() {
		return "GetLiveRedpacketRequest [liveRedpacketId=" + liveRedpacketId
				+ ", liveRecordId=" + liveRecordId + "]";
	}
	
}
