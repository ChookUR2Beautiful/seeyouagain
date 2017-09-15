package com.xmniao.xmn.core.common.request.live;


import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：SensitiveWordRequest   
* 类描述：    敏感词汇处理请求参数
* 创建人：yezhiyong   
* 创建时间：2016年10月8日 下午4:11:05   
* @version    
*
 */
public class SensitiveWordRequest extends BaseRequest{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2533244793401445775L;

	@NotNull(message="需要过滤的文本不能为空")
	private String text;
	
	private String liveRecordId;

	public String getLiveRecordId() {
		return liveRecordId;
	}

	public void setLiveRecordId(String liveRecordId) {
		this.liveRecordId = liveRecordId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "SensitiveWordRequest [text=" + text + ",liveRecordId=" + liveRecordId + "]" + super.toString();
	}
	
}
