package com.xmn.designer.controller.api.v1.address.vo;

import com.xmn.designer.base.Request;
import com.xmn.designer.entity.address.DesigberAddress;

public class AddressRequest extends Request {
private Integer id;
	
	private String nname;
	
	private Integer sex;

	private Integer provinceId;

	private String province;

	private String city;

	private Integer cityId;

	private String areaName;

	private Integer areaId;

	private String address;

	private String phone;

	private Integer isDefault;
	
	public DesigberAddress convertRequestToBean(){
		DesigberAddress sellerAddress=new DesigberAddress();
		sellerAddress.setId(id);
		sellerAddress.setAddress(address);
		sellerAddress.setAreaId(areaId);
		sellerAddress.setAreaName(areaName);
		sellerAddress.setSex(sex);
		sellerAddress.setCity(city);
		sellerAddress.setCityId(cityId);
		sellerAddress.setIsDefault(isDefault);
		sellerAddress.setNname(nname);
		sellerAddress.setPhone(phone);
		sellerAddress.setProvince(province);
		sellerAddress.setProvinceId(provinceId);
		return sellerAddress;
	}
	
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNname() {
		return nname;
	}

	public void setNname(String nname) {
		this.nname = nname == null ? null : nname.trim();
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province == null ? null : province.trim();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city == null ? null : city.trim();
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName == null ? null : areaName.trim();
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	
}
