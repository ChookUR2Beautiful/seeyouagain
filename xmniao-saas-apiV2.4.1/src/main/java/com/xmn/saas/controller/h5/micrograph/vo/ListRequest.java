package com.xmn.saas.controller.h5.micrograph.vo;

import com.xmn.saas.entity.micrograph.MicrographSearch;

public class ListRequest {
	private Integer serialType;  //排序类型
	
	private Integer pageSize=5;	
	
	private Integer pageIndex=1;
	
	private Integer tag;	//类型
		
	private String searchName=null;	 //搜索内容
	
	public MicrographSearch convertRequestToBean(){
		MicrographSearch micrographSearch = new MicrographSearch();
		micrographSearch.setPageIndex(pageIndex);
		micrographSearch.setPageSize(pageSize);
		micrographSearch.setTitle(searchName);
		micrographSearch.setSerialType(serialType);
		micrographSearch.setTag(tag);
		return micrographSearch;
	}
	
	public Integer getSerialType() {
		return serialType;
	}

	public void setSerialType(Integer serialType) {
		this.serialType = serialType;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getTag() {
		return tag;
	}

	public void setTag(Integer tag) {
		this.tag = tag;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	
	
}
