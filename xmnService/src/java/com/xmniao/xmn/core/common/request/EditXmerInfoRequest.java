/**
 * 
 */
package com.xmniao.xmn.core.common.request;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * 项目名称：xmnService
 * 
 * 类名称：EditXmerInfoRequest
 * 
 * 类描述：寻蜜客资料编辑请求
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016-5-19下午8:58:30
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class EditXmerInfoRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3404219439327572290L;
	
	private Integer uid;//用户ID
	
	@NotNull(message="头像不能为空")
	private String avatar;//用户头像
	
	@NotNull(message="姓名不能为空")
	private String userrname;//用户姓名
	
	@NotNull(message="邮箱地址不能为空")
	private String useremail;//用户邮箱地址
	
	@NotNull(message="微信号不能为空")
	private String wechatnum;//用户微信号
	
	
	/**
	 * @return the uid
	 */
	public Integer getUid() {
		return uid;
	}
	/**
	 * @param uid the uid to set
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	/**
	 * @return the avatar
	 */
	public String getAvatar() {
		return avatar;
	}
	/**
	 * @param avatar the avatar to set
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	/**
	 * @return the userrname
	 */
	public String getUserrname() {
		return userrname;
	}
	/**
	 * @param userrname the userrname to set
	 */
	public void setUserrname(String userrname) {
		this.userrname = userrname;
	}
	/**
	 * @return the useremail
	 */
	public String getUseremail() {
		return useremail;
	}
	/**
	 * @param useremail the useremail to set
	 */
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	/**
	 * @return the wechatnum
	 */
	public String getWechatnum() {
		return wechatnum;
	}
	/**
	 * @param wechatnum the wechatnum to set
	 */
	public void setWechatnum(String wechatnum) {
		this.wechatnum = wechatnum;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EditXmerInfoRequest [uid=" + uid + ", avatar=" + avatar
				+ ", userrname=" + userrname + ", useremail=" + useremail
				+ ", wechatnum=" + wechatnum + "]";
	}

}
