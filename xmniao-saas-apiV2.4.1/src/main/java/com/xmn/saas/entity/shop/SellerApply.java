package com.xmn.saas.entity.shop;

/**
 * 
*      
* 类名称：SellerApply   
* 类描述：商家修改店铺资料申请   
* 创建人：xiaoxiong   
* 创建时间：2016年9月27日 下午3:52:38   
* 修改人：xiaoxiong   
* 修改时间：2016年9月27日 下午3:52:38   
* 修改备注：   
* @version    
*
 */
public class SellerApply {
	
	
	/**
	 * 主键ID
	 */
	private Integer id;
	/**
	 * 商家id
	 */
	private Integer sellerid;
	/**
	 * 商家名称
	 */
	private String sellerName;
	/**
	 * 商圈ID
	 */
	private Integer zoneId;
	
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 联系电话
	 */
	private String phone;

	/**
	 * 经营类型
	 */
	private String typeName;

	/**
	 * 申请时间
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
	private Double longitude;
	/**
	 * 纬度
	 */
	private Double latitude;
	
	
	
	private Integer category;
	
	private Integer genre;
	
	private String tradename;
	
	
	//省市
	private String province;
		
	//城市
	private String city;
		
	//地区
	private String area;
	
	private int provinceNo;
	
	private int cityNo;
	
	private int areaNo;
	

	private String tagIds;

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    public int getProvinceNo() {
		return provinceNo;
	}
	public void setProvinceNo(int provinceNo) {
		this.provinceNo = provinceNo;
	}
	public int getCityNo() {
		return cityNo;
	}
	public void setCityNo(int cityNo) {
		this.cityNo = cityNo;
	}
	public int getAreaNo() {
		return areaNo;
	}
	public void setAreaNo(int areaNo) {
		this.areaNo = areaNo;
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	

}
