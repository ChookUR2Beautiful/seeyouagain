package com.xmniao.xmn.core.common.request.live;


import net.sf.oval.constraint.NotNull;


public class PushUrsRequest{
	
	@NotNull(message="主播uid不能为空")
	private String uid;
	
	@NotNull(message="托送标题不能为空")
	private String title;
	
	@NotNull(message="托送消息不能为空")
	private String msg;
	
	@NotNull(message="直播记录ID不能为空")
	private Integer rid;
	
	
	private int action_type=1; //1activity 打开app本身2打开浏览器 3打开intent
	
	private String activity;
	
	
	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public int getAction_type() {
		return action_type;
	}

	public void setAction_type(int action_type) {
		this.action_type = action_type;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	private String sendtime;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getSendtime() {
		return sendtime;
	}

	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}


	

}
