/**
 * 2016年8月19日 下午2:22:37
 */
package com.xmniao.xmn.core.common.request.live;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * @项目名称：xmnService_3.1.2_zb
 * @类名称：SendBarrageRequest
 * @类描述：
 * @创建人： yeyu
 * @创建时间 2016年8月19日 下午2:22:37
 * @version
 */
public class SendBarrageRequest extends BaseRequest {

	/**
	 *
	 */
	private static final long serialVersionUID = -8241638173839670484L;
	
	//信息内容
	@NotNull(message="发送信息不能为空")
	private String messagerTxt;
	
	//直播记录id
	@NotNull(message="直播记录ID不能为空")
	private Integer liveRecordId;
	
	//群组号
	@NotNull(message="群组号不能为空")
	private String messagerGroupNo;

	public String getMessagerTxt() {
		return messagerTxt;
	}

	public void setMessagerTxt(String messagerTxt) {
		this.messagerTxt = messagerTxt;
	}

	public Integer getLiveRecordId() {
		return liveRecordId;
	}

	public void setLiveRecordId(Integer liveRecordId) {
		this.liveRecordId = liveRecordId;
	}

	public String getMessagerGroupNo() {
		return messagerGroupNo;
	}

	public void setMessagerGroupNo(String messagerGroupNo) {
		this.messagerGroupNo = messagerGroupNo;
	}

	@Override
	public String toString() {
		return "SendBarrageRequest [messagerTxt=" + messagerTxt
				+ ", liveRecordId=" + liveRecordId + ", messagerGroupNo="
				+ messagerGroupNo + "]";
	}
	
	
	/*//应用id
	@NotNull(message = "应用id不能为空")
	private String sdkAppid;
	
	//管理员账号
	@NotNull(message = "管理员账号不能为空")
	private String identifier;
	
	//用户账号
	@NotNull(message = "用户账号不能为空")
	private String account;*/

	

	

	
	
}
