package com.xmniao.xmn.core.system_settings.entity;

import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

public class Category extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2722582302592038840L;
	private Integer tid;//类别ID
	private String tradename;//类别名称
	private Integer pid;//父ID
	private Integer status;//状态  默认0开启    1关闭，子集全部关闭
	private Date sdate;//更新时间
	private Integer number;//行业已签约商户数
	private Integer orderNum;//排序,值越大排序越靠前
	private String showBigImg;//首页分类显示的大图（第一层）
	private String showSmallImg;//首页分类显示的小图（第二层）
	private Integer isremove; //是否能删除
	private Integer hot;// 是否为热门分类0=否|1=是
	private Integer type; //跳转类型 1 wap 2 APP
	private String url;//type=1时，输入跳转的wap url地址。type=2时，seller代表跳转到商户列表页；grow代表跳转到成长记列表
	private String website; //type=1时，输入跳转的wap url地址。
	private String redirectType;// type=2时，seller代表跳转到商户列表页；grow代表跳转到成长记列表
	

	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public String getTradename() {
		return tradename;
	}
	public void setTradename(String tradename) {
		this.tradename = tradename;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getSdate() {
		return sdate;
	}
	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getShowBigImg() {
		return showBigImg;
	}
	public void setShowBigImg(String showBigImg) {
		this.showBigImg = showBigImg;
	}
	public String getShowSmallImg() {
		return showSmallImg;
	}
	public void setShowSmallImg(String showSmallImg) {
		this.showSmallImg = showSmallImg;
	}
	
	public Integer getIsremove() {
		return isremove;
	}
	public void setIsremove(Integer isremove) {
		this.isremove = isremove;
	}
	public Integer getHot() {
		return hot;
	}
	public void setHot(Integer hot) {
		this.hot = hot;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getWebsite() {
		return url;
	}
	public void setWebsite(String website) {
		this.url=website;
	}
	public String getRedirectType() {
		return url;
	}
	public void setRedirectType(String redirectType) {
		this.url=redirectType;
	}
	@Override
	public String toString() {
		return "Category [tid=" + tid + ", tradename=" + tradename + ", pid="
				+ pid + ", status=" + status + ", sdate=" + sdate + ", number="
				+ number + ", orderNum=" + orderNum + ", showBigImg="
				+ showBigImg + ", showSmallImg=" + showSmallImg + ", isremove="
				+ isremove + ", hot=" + hot + "]";
	}

	

}
