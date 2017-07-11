package com.xmn.saas.controller.api.v1.shop.vo;

import com.xmn.saas.base.Request;
import com.xmn.saas.entity.shop.SellerApply;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;

/**
 * 
 * 
 * 类名称：ShopPutRequest 类描述： 修改店铺资料请求类 创建人：xiaoxiong 创建时间：2016年9月26日 下午6:06:06
 * 修改人：xiaoxiong 修改时间：2016年9月26日 下午6:06:06 修改备注：
 * 
 * @version
 * 
 */
public class ShopPutRequest extends Request {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 商家id
	 */
	private Integer sellerid;
	/**
	 * 商家名称
	 */
	@NotNull(message="商家名称不能为空")
	private String sellerName;
	/**
	 * 商圈ID
	 */
	@NotNull(message="商圈ID不能为空")
	private Integer zoneId;
	
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 联系电话
	 */
	@NotNull(message="手机号码不能为空")
	private String phone;

	/**
	 * 经营类型
	 */
	private String typeName;

	/**
	 * 营业时间
	 */
	private String sdate;
	/**
	 * 是否有wifi
	 */
	private Integer isWifi;
	/**
	 * 是否可以停车
	 */
	private Integer isParking;
	/**
	 * 地标
	 */
	private String landMark;
	/**
	 * 平均消费
	 */
	private Double consume;
	/**
	 * 营业时间
	 */
	private String openDate;
	/**
	 * 修改数据来源0基本数据 1图片数据
	 */
	private Integer source;
	
	/**
	 * 经度
	 */
	@NotNull(message="longitude不能为空")
	private Double longitude;
	/**
	 * 纬度
	 */
	@NotNull(message="latitude不能为空")
	private Double latitude;
	
	private Integer category;
	
	//二级类别名称
	private Integer genre;
	
	//一级类别名称
	private String tradename;
	
	//省市
	@NotNull(message="省不能为空不能为空")
	private String province;
	
	//城市
	@NotNull(message="市不能为空")
	private String city;
	
	//地区
	@NotNull(message="区不能为空")
	private String area;

	private String tagIds;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
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
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	public Integer getGenre() {
		return genre;
	}
	public void setGenre(Integer genre) {
		this.genre = genre;
	}
	public String getTradename() {
		return tradename;
	}
	public void setTradename(String tradename) {
		this.tradename = tradename;
	}
	public String getOpenDate() {
		return openDate;
	}
	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}
	public Integer getSource() {
		return source;
	}
	public void setSource(Integer source) {
		this.source = source;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Integer getSellerid() {
		return sellerid;
	}
	public void setSellerid(Integer sellerid) {
		this.sellerid = sellerid;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public Integer getZoneId() {
		return zoneId;
	}
	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public Integer getIsWifi() {
		return isWifi;
	}
	public void setIsWifi(Integer isWifi) {
		this.isWifi = isWifi;
	}
	public Integer getIsParking() {
		return isParking;
	}
	public void setIsParking(Integer isParking) {
		this.isParking = isParking;
	}
	public String getLandMark() {
		return landMark;
	}
	public void setLandMark(String landMark) {
		this.landMark = landMark;
	}
	public Double getConsume() {
		return consume;
	}
	public void setConsume(Double consume) {
		this.consume = consume;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public SellerApply convertSellerApply(Integer sellerid){
		
		SellerApply sellerApply=new SellerApply();
		BeanUtils.copyProperties(this, sellerApply);
		sellerApply.setSellerid(sellerid);
		
		return sellerApply;
	}
	
}
