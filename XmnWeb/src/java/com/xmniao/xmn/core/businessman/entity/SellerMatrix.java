package com.xmniao.xmn.core.businessman.entity;

public class SellerMatrix {
	private String sellerid;
	private String sellername;
	private String aid;
	
	public String getSellername() {
		return sellername;
	}
	public void setSellername(String sellername) {
		this.sellername = sellername;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getSellerid() {
		return sellerid;
	}
	public void setSellerid(String sellerid) {
		this.sellerid = sellerid;
	}
	@Override
	public String toString() {
		return "SellerMatrix [sellerid=" + sellerid + ", sellername="
				+ sellername + ", aid=" + aid + "]";
	}
}
