package com.xmniao.xmn.core.common.request;

import com.xmniao.xmn.core.base.BaseRequest;
import net.sf.oval.constraint.NotNull;

public class StoreInfoUpdateRequest extends BaseRequest {

	private static final long serialVersionUID = 7020390451626401189L;

	@NotNull(message = "店铺信息ID不能为空")
	private Integer sellerid; //店铺信息id
	private String sellername;//商铺名称
	private String province;//省名称
	private String city;//市名称
	private String area;//区名称
	private String provinceName;
	private String cityName;
	private String areaName;
	private String address;  //详细地址
	private Double longitude;//店铺地址--经度
	private Double latitude;//店铺地址--纬度
	private Double consume;//人均消费
	private Integer category;    //	经营类型ID
	private Integer genre;   //二级经营类型ID
	private String typename;  // 一级类别名称
	private String tradename;  //二级类别名称
	private String fullname;//负责人
	private String phoneid;//负责人手机
	private String tel;//店铺电话

	private Double agio;//折扣设置0.8
	private String sdate;//开业时间
	private Long signTimestamp;  //签约时间有效期

	private Integer zoneid;  //商圈id
	private Integer saasType;  //saas类型

	private String company; //公司名称
	private String business_license_name;   //营业执照名称
	private String business_contract;      // 合同图片地址
	private String lssellername;    //连锁店名称
	private Integer fatherid = -1;  //连锁id
	private String sUid;   // h5

	public Integer getSellerid() {
		return sellerid;
	}

	public void setSellerid(Integer sellerid) {
		this.sellerid = sellerid;
	}

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public Double getConsume() {
		return consume;
	}

	public void setConsume(Double consume) {
		this.consume = consume;
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

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getTradename() {
		return tradename;
	}

	public void setTradename(String tradename) {
		this.tradename = tradename;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPhoneid() {
		return phoneid;
	}

	public void setPhoneid(String phoneid) {
		this.phoneid = phoneid;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Double getAgio() {
		return agio;
	}

	public void setAgio(Double agio) {
		this.agio = agio;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public Integer getZoneid() {
		return zoneid;
	}

	public void setZoneid(Integer zoneid) {
		this.zoneid = zoneid;
	}

	public Long getSignTimestamp() {
		return signTimestamp;
	}

	public void setSignTimestamp(Long signTimestamp) {
		this.signTimestamp = signTimestamp;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Integer getSaasType() {
		return saasType;
	}

	public void setSaasType(Integer saasType) {
		this.saasType = saasType;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getBusiness_license_name() {
		return business_license_name;
	}

	public void setBusiness_license_name(String business_license_name) {
		this.business_license_name = business_license_name;
	}

	public String getBusiness_contract() {
		return business_contract;
	}

	public void setBusiness_contract(String business_contract) {
		this.business_contract = business_contract;
	}

	public String getLssellername() {
		return lssellername;
	}

	public void setLssellername(String lssellername) {
		this.lssellername = lssellername;
	}

	public int getFatherid() {
		return fatherid;
	}

	public void setFatherid(int fatherid) {
		this.fatherid = fatherid;
	}

	public String getsUid() {
		return sUid;
	}

	public void setsUid(String sUid) {
		this.sUid = sUid;
	}


	@Override
	public String toString() {
		return "StoreInfoUpdateRequest{" +
				"sellerid=" + sellerid +
				", sellername='" + sellername + '\'' +
				", province='" + province + '\'' +
				", city='" + city + '\'' +
				", area='" + area + '\'' +
				", provinceName='" + provinceName + '\'' +
				", cityName='" + cityName + '\'' +
				", areaName='" + areaName + '\'' +
				", address='" + address + '\'' +
				", longitude=" + longitude +
				", latitude=" + latitude +
				", consume=" + consume +
				", category=" + category +
				", genre=" + genre +
				", typename='" + typename + '\'' +
				", tradename='" + tradename + '\'' +
				", fullname='" + fullname + '\'' +
				", phoneid='" + phoneid + '\'' +
				", tel='" + tel + '\'' +
				", agio=" + agio +
				", sdate='" + sdate + '\'' +
				", signTimestamp=" + signTimestamp +
				", zoneid=" + zoneid +
				", saasType=" + saasType +
				", company='" + company + '\'' +
				", business_license_name='" + business_license_name + '\'' +
				", business_contract='" + business_contract + '\'' +
				", lssellername='" + lssellername + '\'' +
				", fatherid=" + fatherid +
				", sUid='" + sUid + '\'' +
				'}';
	}
}