package com.xmniao.xmn.core.seller.entity;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;

public class UnsignedSellerRequest extends BaseRequest{

	private static final long serialVersionUID = -7984597785765589873L;
	
	@NotNull(message="商家名称不能为空")
	private String sellername;	//商家名称
	
	@NotNull(message="省编号不能为空")
	private String province;	//省编号
	
	@NotNull(message="市编号不能为空")
	private String city;		//市编号
	
	@NotNull(message="区编号不能为空")
	private String area;		//区编号
	
	@NotNull(message="商圈编号不能为空")
	private Integer zoneid;		//商圈编号
	
	@NotNull(message="二级分类不能为空")
	private String genre;		//二级类别
	
	@NotNull(message="二级分类名称不能为空")
	private String tradename;	//二级分类名称
	
	@NotNull(message="商家地址不能为空")
	private String address;		//商家地址
	
	
	private Double consume;		//double(10,2) 人均消费

	public String getSellername() {
		return sellername;
	}

	public void setSellername(String sellername) {
		this.sellername = sellername;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Integer getZoneid() {
		return zoneid;
	}

	public void setZoneid(Integer zoneid) {
		this.zoneid = zoneid;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getTradename() {
		return tradename;
	}

	public void setTradename(String tradename) {
		this.tradename = tradename;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getConsume() {
		return consume;
	}

	public void setConsume(Double consume) {
		this.consume = consume;
	}

	@Override
	public String toString() {
		return "UnsignedSellerRequest [sellername=" + sellername + ", province=" + province + ", city=" + city
				+ ", area=" + area + ", zoneid=" + zoneid + ", genre=" + genre + ", tradename=" + tradename
				+ ", address=" + address + ", consume=" + consume + ",Base:" + super.toString() + "]";
	}
	
	
}
