package com.xmniao.xmn.core.common.request.live;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
* 类名称：UserInfoRequest   
* 类描述：   获取主播/观众信息请求参数
* 创建时间：2016年8月24日 上午9:35:36   
* @version    
 */
public class LiverInfoByPhoneRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7791924145986622771L;
	
	//直播记录id
	@NotNull(message="直播用户手机号不能为空")
	private String phone;

	private String uid;
	
	public String getPhone() {
		return phone;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "LiverInfoByPhoneRequest [phone=" + phone + "]";
	}

	
	
	
}
