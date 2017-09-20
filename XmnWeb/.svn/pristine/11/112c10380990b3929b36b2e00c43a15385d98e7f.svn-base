/**   
 * 文件名：TUser.java   
 *    
 * 日期：2014年11月12日15时07分03秒  
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司     
 */
package com.xmniao.xmn.core.system_settings.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;


/**
 * 项目名称：XmnWeb
 * 
 * 类名称：TUser
 * 
 * 类描述：用户
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月12日15时07分03秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TUser extends BaseEntity  {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -5539066511109586329L;
	private Long userId;// ID
	private Long roleId;// ID
	private String username;// 用户名
	private String password;// 密码
	private String email;// E-mail
	private String name;// 姓名
	private Boolean isEnabled;// 是否启用
	private Boolean isLocked;// 是否锁定
	private Date lockedDate;// 锁定日期
	private Date loginDate;// 最后登录日期
	private String loginIp;// 最后登录IP
	private String roleName;//角色名称
	
	/**   
	 * 创建一个新的实例 TUser.   
	 *      
	 */
	public TUser() {
		super();
	}
	
	public TUser(Long userId) {
		this.userId = userId;
	}

	
	/**
	 * userId
	 * 
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	/**
	 * roleId
	 * 
	 * @return the roleId
	 */
	public Long getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId
	 *            the roleId to set
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
	/**
	 * username
	 * 
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * password
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * email
	 * 
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * name
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * isEnabled
	 * 
	 * @return the isEnabled
	 */
	public Boolean getIsEnabled() {
		return isEnabled;
	}

	/**
	 * @param isEnabled
	 *            the isEnabled to set
	 */
	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	
	/**
	 * isLocked
	 * 
	 * @return the isLocked
	 */
	public Boolean getIsLocked() {
		return isLocked;
	}

	/**
	 * @param isLocked
	 *            the isLocked to set
	 */
	public void setIsLocked(Boolean isLocked) {
		this.isLocked = isLocked;
	}
	
	/**
	 * lockedDate
	 * 
	 * @return the lockedDate
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getLockedDate() {
		return lockedDate;
	}

	/**
	 * @param lockedDate
	 *            the lockedDate to set
	 */
	public void setLockedDate(Date lockedDate) {
		this.lockedDate = lockedDate;
	}
	
	/**
	 * loginDate
	 * 
	 * @return the loginDate
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getLoginDate() {
		return loginDate;
	}

	/**
	 * @param loginDate
	 *            the loginDate to set
	 */
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	
	/**
	 * loginIp
	 * 
	 * @return the loginIp
	 */
	public String getLoginIp() {
		return loginIp;
	}

	/**
	 * @param loginIp
	 *            the loginIp to set
	 */
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName.trim();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TUser [userId=" + userId + ", roleId=" + roleId + ", username=" + username + ", password=" + password + ", email=" + email + ", name=" + name + ", isEnabled=" + isEnabled + ", isLocked=" + isLocked + ", lockedDate=" + lockedDate + ", loginDate=" + loginDate + ", loginIp=" + loginIp + ", ]";
	}
}
