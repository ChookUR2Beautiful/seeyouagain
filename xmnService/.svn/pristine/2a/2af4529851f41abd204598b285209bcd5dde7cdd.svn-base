package com.xmniao.xmn.core.common.request.live;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：CheckRoomPwdRequest   
* 类描述：   验证直播间密码
* 创建人：yezhiyong   
* 创建时间：2017年2月17日 下午2:11:18   
* @version    
*
 */
public class CheckRoomPwdRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6778876969621019526L;
	
	/**
	 * 提现密码
	 */
	@NotNull(message="提现密码不能为空")
	private String password;

	@NotNull(message="直播记录id不能为空")
	private Integer liveRecordId;
	
	public Integer getLiveRecordId() {
		return liveRecordId;
	}

	public void setLiveRecordId(Integer liveRecordId) {
		this.liveRecordId = liveRecordId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "CheckRoomPwdRequest [password=" + password + ", liveRecordId="
				+ liveRecordId + "]";
	}

}
