/**   
 * 文件名：TAuthority.java   
 *    
 * 日期：2014年11月19日10时19分59秒  
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司     
 */
package com.xmniao.xmn.core.system_settings.entity;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：TAuthority
 * 
 * 类描述：资源
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月19日10时19分59秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TAuthority extends BaseEntity implements Comparable<TAuthority>{

	private static final long serialVersionUID = -1062320479203555210L;
	private Long id;// 资源ID
	private Long authorityFatherId;// 父节点
	private String authorityName;// 资源名称
	private String authorityExtra;// 资源图标
	private String authorityRefer;// 资源组件别名
	private Integer leaf;// 叶子
	private String authorityUrl;// 路径
	private Integer authoritySort;// 排序
	
	
	private Boolean  check; //复选框 true选中   false 不选

	/**
	 * 创建一个新的实例 TAuthority.
	 * 
	 */
	public TAuthority() {
		super();
	}

	public TAuthority(Long id) {
		this.id = id;
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
	 * authorityFatherId
	 * 
	 * @return the authorityFatherId
	 */
	public Long getAuthorityFatherId() {
		return authorityFatherId;
	}

	/**
	 * @param authorityFatherId
	 *            the authorityFatherId to set
	 */
	public void setAuthorityFatherId(Long authorityFatherId) {
		this.authorityFatherId = authorityFatherId;
	}

	/**
	 * authorityName
	 * 
	 * @return the authorityName
	 */
	public String getAuthorityName() {
		return authorityName;
	}

	/**
	 * @param authorityName
	 *            the authorityName to set
	 */
	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	/**
	 * authorityExtra
	 * 
	 * @return the authorityExtra
	 */
	public String getAuthorityExtra() {
		return authorityExtra;
	}

	/**
	 * @param authorityExtra
	 *            the authorityExtra to set
	 */
	public void setAuthorityExtra(String authorityExtra) {
		this.authorityExtra = authorityExtra;
	}

	/**
	 * authorityRefer
	 * 
	 * @return the authorityRefer
	 */
	public String getAuthorityRefer() {
		return authorityRefer;
	}

	/**
	 * @param authorityRefer
	 *            the authorityRefer to set
	 */
	public void setAuthorityRefer(String authorityRefer) {
		this.authorityRefer = authorityRefer;
	}

	/**
	 * leaf
	 * 
	 * @return the leaf
	 */
	public Integer getLeaf() {
		return leaf;
	}

	/**
	 * @param leaf
	 *            the leaf to set
	 */
	public void setLeaf(Integer leaf) {
		this.leaf = leaf;
	}

	/**
	 * authorityUrl
	 * 
	 * @return the authorityUrl
	 */
	public String getAuthorityUrl() {
		return authorityUrl;
	}

	/**
	 * @param authorityUrl
	 *            the authorityUrl to set
	 */
	public void setAuthorityUrl(String authorityUrl) {
		this.authorityUrl = authorityUrl;
	}

	/**
	 * authoritySort
	 * 
	 * @return the authoritySort
	 */
	public Integer getAuthoritySort() {
		return authoritySort;
	}

	/**
	 * @param authoritySort
	 *            the authoritySort to set
	 */
	public void setAuthoritySort(Integer authoritySort) {
		this.authoritySort = authoritySort;
	}

	public Boolean getCheck() {
		return check;
	}

	public void setCheck(Boolean check) {
		this.check = check;
	}

	@Override
	public String toString() {
		return "TAuthority [id=" + id + ", authorityFatherId="
				+ authorityFatherId + ", authorityName=" + authorityName
				+ ", authorityExtra=" + authorityExtra + ", authorityRefer="
				+ authorityRefer + ", leaf=" + leaf + ", authorityUrl="
				+ authorityUrl + ", authoritySort=" + authoritySort
				+ ", check=" + check + "]";
	}

	@Override
	public int compareTo(TAuthority o) {
		if(this.authoritySort==null&&o.authoritySort==null){
			return 0;
		}else if(this.authoritySort==null){
			return -1;
		}else if(o.authoritySort==null){
			return 1;
		}
		return (this.authoritySort>o.authoritySort?1:-1);
	}


}
