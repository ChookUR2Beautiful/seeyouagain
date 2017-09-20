package com.xmniao.xmn.core.member.entity;

import com.xmniao.xmn.core.base.BaseEntity;
/**
 * 
 * @author dong'jietao 
 * @date 2015年5月27日 下午4:52:50
 * @TODO 提现流程记录
 */
public class WithdrawalsProcess extends BaseEntity {
	private String state;// 1：提交资料；2：处理中； 3：已到账;4:打款失败；5：写回钱包
	private String submitdate;// 提交资料时间
	private String disposedate;// 处理中时间
	private String receivedate;// 已到账时间
	private String faildate;// 打款账失败时间
	private String failmsg;// 打款账失败原因
	private String backdate;// 写回钱包时间
	private String backmsg;// 写回钱包原因

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getSubmitdate() {
		return submitdate;
	}

	public void setSubmitdate(String submitdate) {
		this.submitdate = submitdate;
	}

	public String getDisposedate() {
		return disposedate;
	}

	public void setDisposedate(String disposedate) {
		this.disposedate = disposedate;
	}

	public String getReceivedate() {
		return receivedate;
	}

	public void setReceivedate(String receivedate) {
		this.receivedate = receivedate;
	}

	public String getFaildate() {
		return faildate;
	}

	public void setFaildate(String faildate) {
		this.faildate = faildate;
	}

	public String getFailmsg() {
		return failmsg;
	}

	public void setFailmsg(String failmsg) {
		this.failmsg = failmsg;
	}

	public String getBackdate() {
		return backdate;
	}

	public void setBackdate(String backdate) {
		this.backdate = backdate;
	}

	public String getBackmsg() {
		return backmsg;
	}

	public void setBackmsg(String backmsg) {
		this.backmsg = backmsg;
	}
}
