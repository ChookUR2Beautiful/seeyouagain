/**   
 * 文件名：TRoleAuthority.java   
 *    
 * 日期：2014年11月19日10时11分27秒  
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司     
 */
package com.xmniao.xmn.core.system_settings.entity;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：TRoleAuthority
 * 
 * 类描述：角色资源
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月19日10时11分27秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TRoleAuthority extends BaseEntity {

	private static final long serialVersionUID = 4238881190022184947L;
	private Long id;// ID
	private Long authorityId;// 资源ID
	private Long roleId;// ID



	
	public TRoleAuthority(Long id,Long roleId,Long  authorityId) {
		this.id = id;
		this.authorityId =authorityId; 
		this.roleId = roleId;
	}
	
	public TRoleAuthority(Long id,Long roleId) {
		this(id,roleId,null);
	}

	public TRoleAuthority(Long id) {
		this(id,null);
	}
	
	/**
	 * 创建一个新的实例 TRoleAuthority.
	 * 
	 */
	public TRoleAuthority() {
		this(null);
	}

	/**
	 * id
	 * 
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * authorityId
	 * 
	 * @return the authorityId
	 */
	public Long getAuthorityId() {
		return authorityId;
	}

	/**
	 * @param authorityId
	 *            the authorityId to set
	 */
	public void setAuthorityId(Long authorityId) {
		this.authorityId = authorityId;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TRoleAuthority [id=" + id + ", authorityId=" + authorityId + ", roleId=" + roleId + ", ]";
	}
}
