/**
 * 
 */
package com.xmniao.xmn.core.live.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: TMqConsumeRecord
 * @Description: 消息队列消息消费记录表
 * @author hkun
 * @date 2016年9月5日 下午12:06:18
 *
 */
public class TMqConsumeRecord implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 消息唯一标示
	 * 生成规则：1.大礼包领取流程-(uid+"|"+gift_bag_id)
	 */
	private String messageKey;
	
	/**
	 * 消费状态(0：未消费；1：已消费)
	 */
	private int status;
	
	/**
	 * 消费异常
	 */
	private String consumeException;
	
	/**
	 * 消息处理完成时间
	 */
	private Date createTime;
	
	/**
	 * 消息处理更新时间
	 */
	private Date updateTime;

	public String getMessageKey() {
		return messageKey;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getConsumeException() {
		return consumeException;
	}

	public void setConsumeException(String consumeException) {
		this.consumeException = consumeException;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString(){
		StringBuffer sb = new StringBuffer("");
		sb.append("TMqConsumeRecord:{");
		sb.append("messageKey:").append(messageKey).append(",");
		sb.append("status:").append(status).append(",");
		sb.append("consumeException:").append(consumeException).append(",");
		sb.append("createTime:").append(createTime).append(",");
		sb.append("updateTime:").append(updateTime);
		sb.append("}");
		return sb.toString();
	}
	
}
