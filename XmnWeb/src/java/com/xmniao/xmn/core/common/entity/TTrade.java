/**   
 * 文件名：TTrade.java   
 *    
 * 日期：2014年12月01日11时07分10秒  
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司     
 */
package com.xmniao.xmn.core.common.entity;

import java.util.Date;
import com.xmniao.xmn.core.base.BaseEntity;


/**
 * 项目名称：XmnWeb
 * 
 * 类名称：TTrade
 * 
 * 类描述：经营(行业)类别
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年12月01日11时07分10秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TTrade extends BaseEntity  {
	
	private static final long serialVersionUID = -8882943728365963691L;
	private Integer tid;// 类别ID
	private String tradename;// 类别名称
	private Integer pid;// 父ID
	private Integer status;// 默认0=开启|1=关闭|子集全部关闭
	private Date sdate;// 更新时间
	private Integer number;// 行业已签约商户数
	private Integer orderNum;// 排序|值越大排序越靠前
	private String showBigImg;// 首页分类显示的大图(第一层)
	private String showSmallImg;// 首页分类显示的小图(第二层)
	private Integer hot;// 是否为热门分类0=否|1=是
	
	/**   
	 * 创建一个新的实例 TTrade.   
	 *      
	 */
	public TTrade() {
		super();
	}
	
	public TTrade(Integer tid) {
		this.tid = tid;
	}

	
	/**
	 * tid
	 * 
	 * @return the tid
	 */
	public Integer getTid() {
		return tid;
	}

	/**
	 * @param tid
	 *            the tid to set
	 */
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	
	/**
	 * tradename
	 * 
	 * @return the tradename
	 */
	public String getTradename() {
		return tradename;
	}

	/**
	 * @param tradename
	 *            the tradename to set
	 */
	public void setTradename(String tradename) {
		this.tradename = tradename;
	}
	
	/**
	 * pid
	 * 
	 * @return the pid
	 */
	public Integer getPid() {
		return pid;
	}

	/**
	 * @param pid
	 *            the pid to set
	 */
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	
	/**
	 * status
	 * 
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/**
	 * sdate
	 * 
	 * @return the sdate
	 */
	public Date getSdate() {
		return sdate;
	}

	/**
	 * @param sdate
	 *            the sdate to set
	 */
	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}
	
	/**
	 * number
	 * 
	 * @return the number
	 */
	public Integer getNumber() {
		return number;
	}

	/**
	 * @param number
	 *            the number to set
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	/**
	 * orderNum
	 * 
	 * @return the orderNum
	 */
	public Integer getOrderNum() {
		return orderNum;
	}

	/**
	 * @param orderNum
	 *            the orderNum to set
	 */
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	
	/**
	 * showBigImg
	 * 
	 * @return the showBigImg
	 */
	public String getShowBigImg() {
		return showBigImg;
	}

	/**
	 * @param showBigImg
	 *            the showBigImg to set
	 */
	public void setShowBigImg(String showBigImg) {
		this.showBigImg = showBigImg;
	}
	
	/**
	 * showSmallImg
	 * 
	 * @return the showSmallImg
	 */
	public String getShowSmallImg() {
		return showSmallImg;
	}

	/**
	 * @param showSmallImg
	 *            the showSmallImg to set
	 */
	public void setShowSmallImg(String showSmallImg) {
		this.showSmallImg = showSmallImg;
	}
	
	/**
	 * hot
	 * 
	 * @return the hot
	 */
	public Integer getHot() {
		return hot;
	}

	/**
	 * @param hot
	 *            the hot to set
	 */
	public void setHot(Integer hot) {
		this.hot = hot;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TTrade [tid=" + tid + ", tradename=" + tradename + ", pid=" + pid + ", status=" + status + ", sdate=" + sdate + ", number=" + number + ", orderNum=" + orderNum + ", showBigImg=" + showBigImg + ", showSmallImg=" + showSmallImg + ", hot=" + hot + ", ]";
	}
}
