/**   
 * 文件名：TSellerPic.java   
 *    
 * 日期：2014年11月11日15时56分48秒  
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司     
 */
package com.xmniao.xmn.core.businessman.entity;

import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：TSellerPic
 * 
 * 类描述：商家环境图片
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月11日15时56分48秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TSellerPic extends BaseEntity {

	private static final long serialVersionUID = -6831661026222043829L;
	private Integer picid;// 图片ID
	private Integer sellerid;// 商家ID
	private Integer pid;// 类型ID
	private String breviary;// 缩略图URL
	private String picurl;// 图片URL
	private String bewrite;// 图片描述
	private Date sdate;// 上传时间
	private String sdateStr;
	private Integer islogo;// 默认0表示环境图 1表示为LOGO 2表示为商家图片

	private String picids;
	/**
	 * 创建一个新的实例 TSellerPic.
	 * 
	 */
	public TSellerPic() {
		super();
	}

	public TSellerPic(Integer picid) {
		this.picid = picid;
	}

	public String getSdateStr() {
		return sdateStr;
	}

	public void setSdateStr(String sdateStr) {
		this.sdateStr = sdateStr;
	}

	/**
	 * picid
	 * 
	 * @return the picid
	 */
	public Integer getPicid() {
		return picid;
	}

	/**
	 * @param picid
	 *            the picid to set
	 */
	public void setPicid(Integer picid) {
		this.picid = picid;
	}

	/**
	 * sellerid
	 * 
	 * @return the sellerid
	 */
	public Integer getSellerid() {
		return sellerid;
	}

	/**
	 * @param sellerid
	 *            the sellerid to set
	 */
	public void setSellerid(Integer sellerid) {
		this.sellerid = sellerid;
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
	 * breviary
	 * 
	 * @return the breviary
	 */
	public String getBreviary() {
		return breviary;
	}

	/**
	 * @param breviary
	 *            the breviary to set
	 */
	public void setBreviary(String breviary) {
		this.breviary = breviary;
	}

	/**
	 * picurl
	 * 
	 * @return the picurl
	 */
	public String getPicurl() {
		return picurl;
	}

	/**
	 * @param picurl
	 *            the picurl to set
	 */
	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	/**
	 * bewrite
	 * 
	 * @return the bewrite
	 */
	public String getBewrite() {
		return bewrite;
	}

	/**
	 * @param bewrite
	 *            the bewrite to set
	 */
	public void setBewrite(String bewrite) {
		this.bewrite = bewrite;
	}

	

	public Date getSdate() {
		return sdate;
	}

	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}

	/**
	 * islogo
	 * 
	 * @return the islogo
	 */
	public Integer getIslogo() {
		return islogo;
	}

	/**
	 * @param islogo
	 *            the islogo to set
	 */
	public void setIslogo(Integer islogo) {
		this.islogo = islogo;
	}

	public String getPicids() {
		return picids;
	}

	public void setPicids(String picids) {
		this.picids = picids;
	}

	@Override
	public String toString() {
		return "TSellerPic [picid=" + picid + ", sellerid=" + sellerid
				+ ", pid=" + pid + ", breviary=" + breviary + ", picurl="
				+ picurl + ", bewrite=" + bewrite + ", sdate=" + sdate
				+ ", islogo=" + islogo + ", picids=" + picids + "]";
	}

}
