package com.xmniao.xmn.core.businessman.entity;

import java.io.Serializable;
/**
 * mongodb 商家图片对象
 * @author Administrator
 *
 */

public class MSellerPic implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8099059765577395348L;
	
	
	private String breviary;// 缩略图URL
	private String picurl;// 图片URL
	private String bewrite;// 图片描述
	public String getBreviary() {
		return breviary;
	}
	public void setBreviary(String breviary) {
		this.breviary = breviary;
	}

	public String getPicurl() {
		return picurl;
	}
	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}
	public String getBewrite() {
		return bewrite;
	}
	public void setBewrite(String bewrite) {
		this.bewrite = bewrite;
	}
	@Override
	public String toString() {
		return "MSellerPic [breviary=" + breviary + ", picurl=" + picurl
				+ ", bewrite=" + bewrite + "]";
	}
}
