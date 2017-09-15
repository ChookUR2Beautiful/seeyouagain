package com.xmniao.xmn.core.common.request;

import com.xmniao.xmn.core.base.BaseRequest;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotNull;
import org.springframework.format.annotation.NumberFormat;

public class StoreInfoModifyRequest extends BaseRequest{

	private static final long serialVersionUID = 870383905177507023L;

	private Integer saasType = 1;  // 	V客和中脉用户 saas类型不能为空 '4':'V客赠送','3':'食尚V客','2':'中脉','1':'寻蜜客
	@NotNull(message="商铺名称不能为空")
	private String sellername;//商铺名称
//	@NotNull(message="省名称不能为空")
	private String province;//省名称
//	@NotNull(message="市名称不能为空")
	private String city;//市名称
//	@NotNull(message="区名称不能为空")
	private String area;//区名称
//	@NotNull(message = "商圈Id不能为空")
	private Integer zoneid;
//	@NotNull(message="详细地址不能为空")
	private String address;//详细地址
	@NotNull(message="本店法人不能为空")
	private String fullname;//本店法人
	@NotNull(message="联系人手机不能为空")
	private String phoneid;//联系人手机
//	@NotNull(message="营业执照正面不能为空")
	private String licenseurl;//营业执照url
	@NotNull(message="折扣设置不能为空")
	private Double agio;//折扣设置0.8
	private Double longitude;//经度
	private Double latitude;//纬度
	//
	private String company;  //公司
	private int agree = 1;  // 0 不同意 1 统一
	private String licensefurl; 		//卫生许可证
	private String envirpic;	//环境图

	private String lssellername;  //连锁店名称
	private Integer fatherid = -1;  //连锁id
	private String sUid;   // h5

	public Integer getSaasType() {
		return saasType;
	}

	public void setSaasType(Integer saasType) {
		this.saasType = saasType;
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

	public Integer getZoneid() {
		return zoneid;
	}

	public void setZoneid(Integer zoneid) {
		this.zoneid = zoneid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getLicenseurl() {
		return licenseurl;
	}

	public void setLicenseurl(String licenseurl) {
		this.licenseurl = licenseurl;
	}

	public Double getAgio() {
		return agio;
	}

	public void setAgio(Double agio) {
		this.agio = agio;
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

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public int getAgree() {
		return agree;
	}

	public void setAgree(int agree) {
		this.agree = agree;
	}

	public String getLicensefurl() {
		return licensefurl;
	}

	public void setLicensefurl(String licensefurl) {
		this.licensefurl = licensefurl;
	}

	public String getEnvirpic() {
		return envirpic;
	}

	public void setEnvirpic(String envirpic) {
		this.envirpic = envirpic;
	}

	public String getLssellername() {
		return lssellername;
	}

	public void setLssellername(String lssellername) {
		this.lssellername = lssellername;
	}

	public String getsUid() {
		return sUid;
	}

	public void setsUid(String sUid) {
		this.sUid = sUid;
	}

	public int getFatherid() {
		return fatherid;
	}

	public void setFatherid(int fatherid) {
		this.fatherid = fatherid;
	}

	@Override
	public String toString() {
		return "StoreInfoModifyRequest{" +
				"saasType=" + saasType +
				", sellername='" + sellername + '\'' +
				", province='" + province + '\'' +
				", city='" + city + '\'' +
				", area='" + area + '\'' +
				", zoneid=" + zoneid +
				", address='" + address + '\'' +
				", fullname='" + fullname + '\'' +
				", phoneid='" + phoneid + '\'' +
				", licenseurl='" + licenseurl + '\'' +
				", agio=" + agio +
				", longitude=" + longitude +
				", latitude=" + latitude +
				", company='" + company + '\'' +
				", agree=" + agree +
				", licensefurl='" + licensefurl + '\'' +
				", envirpic='" + envirpic + '\'' +
				", lssellername='" + lssellername + '\'' +
				", fatherid=" + fatherid +
				", sUid='" + sUid + '\'' +
				'}';
	}
}
