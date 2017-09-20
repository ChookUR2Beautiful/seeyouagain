package com.xmniao.xmn.core.http.entity;

import java.io.Serializable;

/**
 * 
 * 项目名称：TravelingWeb
 * 
 * 类名称：PhpHttpPageable
 * 
 * 类描述： PHP接口返回的数据结构
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年10月22日 下午2:00:58
 * 
 * Copyright (c) 深圳市XXX有限公司-版权所有
 */
public class PhpHttpPageable<T> implements Serializable {

	private static final long serialVersionUID = 1379074698773632362L;

	private Boolean status;// true = 成功 | false = 失败
	private String msg;// 错误消息
	private String total;// 总条数
	private String page;// 当前页数
	private String pageSize;// 每天显示条数
	private Object data ;// 数据

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "PhpHttpPageable [status=" + status + ", msg=" + msg + ", total=" + total + ", page=" + page + ", pageSize=" + pageSize + ", data=" + data + "]";
	}

}
