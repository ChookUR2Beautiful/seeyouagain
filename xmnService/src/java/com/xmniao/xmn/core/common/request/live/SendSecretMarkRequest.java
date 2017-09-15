/**
 * 2016年8月18日 下午4:13:44
 */
package com.xmniao.xmn.core.common.request.live;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * @项目名称：xmnService_3.1.2_zb
 * @类名称：SendSecretMarkRequest
 * @类描述：
 * @创建人： yeyu
 * @创建时间 2016年8月18日 下午4:13:44
 * @version
 */
public class SendSecretMarkRequest extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7365265431823478438L;
	
	@NotNull(message="主播手机号码不能为空")
	private String anchorPhone;
	
	private Long record_id;
	
	@NotNull(message="发送消息不能为空")
	private String messager_txt;
	
	public String getAnchorPhone() {
		return anchorPhone;
	}

	public void setAnchorPhone(String anchorPhone) {
		this.anchorPhone = anchorPhone;
	}

	public Long getRecord_id() {
		return record_id;
	}

	public void setRecord_id(Long record_id) {
		this.record_id = record_id;
	}

	public String getMessager_txt() {
		return messager_txt;
	}

	public void setMessager_txt(String messager_txt) {
		this.messager_txt = messager_txt;
	}

	@Override
	public String toString() {
		return "SendSecretMarkRequest [anchorPhone=" + anchorPhone
				+ ", record_id=" + record_id + ", messager_txt=" + messager_txt
				+ "]";
	}

}	
