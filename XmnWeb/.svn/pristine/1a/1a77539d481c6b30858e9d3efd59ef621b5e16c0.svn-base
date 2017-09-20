package com.xmniao.xmn.core.base;

import java.io.Serializable;

import com.xmniao.xmn.core.util.PageConstant;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：BaseEntity
 * 
 * 类描述： 基类
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年10月31日 下午2:10:41
 * 
 * Copyright (c) 深圳市XXX有限公司-版权所有
 */
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 4566767234709666470L;
	protected Integer page = PageConstant.PAGE; // 当前页数
	protected Integer start = PageConstant.START;// 起始数目
	protected Integer limit = PageConstant.LIMIT;// 每页显示数
	protected Integer order = PageConstant.ORDER;//是否排序

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	@Override
	public String toString() {
		return "BaseEntity [page=" + page + ", start=" + start + ", limit=" + limit + "]";
	}

}
