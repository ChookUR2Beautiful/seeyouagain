package com.xmniao.xmn.core.userData_statistics.entity;

import java.io.Serializable;
import java.util.List;
/**
 * 项目名称:XmnWeb
 * 
 * 类名：PUserDataContainer.java
 * 
 * 类描述：用户数据统计-->公用接口返回信息存储类
 * 
 * 创建人：yang'xu
 * 
 * 创建时间：2014-12-22 15：24：53
 * 
 * Copyright © 广东寻蜜鸟网络技术有限公司
 */
public class PUserDataContainer<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3037454705585783899L;
	
	private Boolean status;//状态 
	private String msg;//消息
	private ResponseData<T> data;//列表
	
	

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
	public ResponseData<T> getData() {
		return data;
	}
	public void setData(ResponseData<T> data) {
		this.data = data;
	}

	/**
	 * 项目名称:XmnWeb
	 * 
	 * 类名：ResponseData.java
	 * 
	 * 类描述：用户数据统计-->公用接口返回信息容器类
	 * 
	 * 创建人：yang'xu
	 * 
	 * 创建时间：2014-12-22 15：24：53
	 * 
	 * Copyright © 广东寻蜜鸟网络技术有限公司
	 */
	@SuppressWarnings("hiding")
	public class ResponseData<T> implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1209881875249232091L;
		protected Integer count;//总数
		protected Integer pageCount;//总页数
		protected Integer add_users;//时间段新增用户总数
		protected Integer uvs;//时间段启动用户总数
		protected Integer start_nos;//时间段启动次数总数
		protected Integer valid_users;//时间段新增有效用户总数
		protected Integer login_users;//时间段登录用户数总数
		protected Integer pvs;//时间段用户访问总数
		protected List<T> data;
		public Integer getCount() {
			return count;
		}
		public void setCount(Integer count) {
			this.count = count;
		}
		public Integer getPageCount() {
			return pageCount;
		}
		public void setPageCount(Integer pageCount) {
			this.pageCount = pageCount;
		}
		public Integer getValid_users() {
			return valid_users;
		}
		public void setValid_users(Integer valid_users) {
			this.valid_users = valid_users;
		}
		public Integer getLogin_users() {
			return login_users;
		}
		public void setLogin_users(Integer login_users) {
			this.login_users = login_users;
		}
		public Integer getPvs() {
			return pvs;
		}
		public void setPvs(Integer pvs) {
			this.pvs = pvs;
		}
		public Integer getAdd_users() {
			return add_users;
		}
		public void setAdd_users(Integer add_users) {
			this.add_users = add_users;
		}
		public Integer getUvs() {
			return uvs;
		}
		public void setUvs(Integer uvs) {
			this.uvs = uvs;
		}
		public Integer getStart_nos() {
			return start_nos;
		}
		public void setStart_nos(Integer start_nos) {
			this.start_nos = start_nos;
		}
		public List<T> getData() {
			return data;
		}
		public void setData(List<T> data) {
			this.data = data;
		}
		
		
	}

}
