package com.xmniao.xmn.core.common.request.personal;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：AddReceivingAddressRequest   
* 类描述：   添加/编辑收货地址请求参数
* 创建人：yezhiyong   
* 创建时间：2016年11月11日 下午6:05:53   
* @version    
*
 */
public class AddReceivingAddressRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1523462076730323428L;
	
	//编辑的收货地址id
	private Integer receivingAddressId;
	
	//收货地址
	@NotNull(message="收货人地址不能为空")
	private String userName;
	
	//收货人手机号码
	@NotNull(message="收货人手机号码不能为空")
	private String phoneId;
	
	//收货人省份
	@NotNull(message="收货人省份不能为空")
	private String province;
	
	//收货人省编号
	@NotNull(message="收货人省编号不能为空")
	private Integer provinceId;
	
	//收货人城市
	@NotNull(message="收货人城市不能为空")
	private String city;
	
	//收货人市级编号
	@NotNull(message="收货人市编号不能为空")
	private Integer cityId;
	
	//收货人地区
	@NotNull(message="收货人地区不能为空")
	private String areaName;
	
	//收货人区编号
	@NotNull(message="收货人区编号不能为空")
	private Integer areaId;
	
	//收货人详细地址
	@NotNull(message="收货人详细地址不能为空")
	private String address;
	
	//是否是收货的默认地址
	@NotNull(message="是否是收货人默认地址不能为空")
	private Integer isDefault;
	
	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getPhoneId() {
		return phoneId;
	}

	public void setPhoneId(String phoneId) {
		this.phoneId = phoneId;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	public Integer getReceivingAddressId() {
		return receivingAddressId;
	}

	public void setReceivingAddressId(Integer receivingAddressId) {
		this.receivingAddressId = receivingAddressId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	@Override
	public String toString() {
		return "AddReceivingAddressRequest [receivingAddressId="
				+ receivingAddressId + ", userName=" + userName + ", phoneId="
				+ phoneId + ", province=" + province + ", provinceId="
				+ provinceId + ", city=" + city + ", cityId=" + cityId
				+ ", areaName=" + areaName + ", areaId=" + areaId
				+ ", address=" + address + ", isDefault=" + isDefault + "]";
	}

}
