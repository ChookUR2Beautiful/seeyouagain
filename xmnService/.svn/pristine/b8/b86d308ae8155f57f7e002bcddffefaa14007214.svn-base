package com.xmniao.xmn.core.common.request.market.pay;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;

/**
 * 
* @projectName: xmnService 
* @ClassName: EditAddressRequest    
* @Description:修改收货地址请求类  
* @author: liuzhihao   
* @date: 2016年12月21日 下午1:49:28
 */
@SuppressWarnings("serial")
public class EditAddressRequest extends BaseRequest{
	
	private Integer id;//收货地址ID
	
	@NotNull(message="收货人姓名不能为空")
	private String username;//收货人姓名
	
	@NotNull(message="收货人电话不能为空")
	private String phone;//收货人电话
	
	@NotNull(message="省ID不能为空")
	private Integer provinceId;//省ID
	
	@NotNull(message="省名称不能为空")
	private String provinceName;//省名称;
	
	@NotNull(message="城市ID不能为空")
	private Integer cityId;//城市ID
	
	@NotNull(message="城市名称不能为空")
	private String cityName;//城市名称
	
	@NotNull(message="区ID不能为空")
	private Integer areaId;//区ID
	
	@NotNull(message="区名称不能为空")
	private String areaName;//区名称
	
	@NotNull(message="地址不能为空")
	private String address;//地址
	
	@NotNull(message="收货地址状态不能为空")
	private Integer type;//收货地址状态
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "EditAddressRequest [id=" + id + ", username=" + username + ", phone=" + phone + ", provinceId=" + provinceId
			+ ", provinceName=" + provinceName + ", cityId=" + cityId + ", cityName=" + cityName + ", areaId=" + areaId
			+ ", areaName=" + areaName + ", address=" + address + ", type=" + type + "]";
	}

	
	
}
