package com.xmniao.xmn.core.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
 * 项目名称：ZHPTShop
 * 
 * 类名称：Page
 * 
 * 类描述： 分页数据
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年8月13日 下午2:37:57
 * 
 * Copyright (c) 深圳市XXX有限公司-版权所有
 */
public class Pageable<T extends BaseEntity> implements Serializable {

	private static final long serialVersionUID = -6194225349369776246L;

	private List<T> content = new ArrayList<T>(); // 数据
	private Long total; // 总条数
	private Integer page;// 当前页数
	private Integer pageSzie;// 每页显示条数
	private String colSum;//某一列的统计值
	private Map<String,Object> titleInfo;//标题栏统计信息
	

	public Pageable() {
		super();
	}

	public Pageable(T t) {
		page = t.getPage();
		pageSzie = t.getLimit();
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSzie() {
		return pageSzie;
	}

	public void setPageSzie(Integer pageSzie) {
		this.pageSzie = pageSzie;
	}
	
	
	/**
	 * @return the colSum
	 */
	public String getColSum() {
		return colSum;
	}

	/**
	 * @param colSum the colSum to set
	 */
	public void setColSum(String colSum) {
		this.colSum = colSum;
	}

	
	/**
	 * @return the titleInfo
	 */
	public Map<String, Object> getTitleInfo() {
		return titleInfo;
	}

	/**
	 * @param titleInfo the titleInfo to set
	 */
	public void setTitleInfo(Map<String, Object> titleInfo) {
		this.titleInfo = titleInfo;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Pageable [content=" + content + ", total=" + total + ", page="
				+ page + ", pageSzie=" + pageSzie + ", colSum=" + colSum
				+ ", titleInfo=" + titleInfo + "]";
	}

}
