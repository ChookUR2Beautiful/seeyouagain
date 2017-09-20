package com.xmniao.xmn.core.system_settings.entity;

import java.io.Serializable;

public class TAuthorityArea implements  Serializable,Comparable<TAuthorityArea>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4676643274797708980L;
	
	
	private Long id;
	private Long authorityId;
	private String authorityName;
	private Long authorityFatherId;
	private String url;
	private Integer sort;
	private String methodName;
	private String tableName;
	private String preName;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAuthorityId() {
		return authorityId;
	}
	public void setAuthorityId(Long authorityId) {
		this.authorityId = authorityId;
	}
	public String getAuthorityName() {
		return authorityName;
	}
	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}
	public Long getAuthorityFatherId() {
		return authorityFatherId;
	}
	public void setAuthorityFatherId(Long authorityFatherId) {
		this.authorityFatherId = authorityFatherId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getPreName() {
		return preName;
	}
	public void setPreName(String preName) {
		this.preName = preName;
	}
	@Override
	public String toString() {
		return "TAuthorityArea [id=" + id + ", authorityId=" + authorityId
				+ ", authorityName=" + authorityName + ", authorityFatherId="
				+ authorityFatherId + ", url=" + url + ", sort=" + sort + "]";
	}
	
	@Override
	public int compareTo(TAuthorityArea o) {
		if(this.sort!=null && o.sort!=null){
			if(this.sort<o.sort){
				return -1;
			}	
		}else if(this.sort==null){
			return -1;
		}
		return 1;
	}
	
}
