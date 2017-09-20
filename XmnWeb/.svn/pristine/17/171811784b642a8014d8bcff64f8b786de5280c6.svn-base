package com.xmniao.xmn.core.jobmanage.entity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 项目名称： XmnWeb 
 * 类名称： RecruitStation.java 
 * 类描述：招聘岗位信息实体类 
 * 创建人： lifeng 
 * 创建时间：2016年5月30日下午4:37:17 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 * @version
 */
public class RecruitStation extends BaseEntity {

	private static final long serialVersionUID = -724315207585971848L;

	private Integer recruitStationId;// 招聘岗位ID
	private Integer recruitId;// 招聘表ID
	private Integer sellerId;// 商家ID
	private Integer tagId;// 招聘标签ID
	private List<String> tagIds;// 标签id集合，用于保存多个岗位要求（招聘标签id）
	private String stationName;// 岗位名称
	private Date updateDate;// 发布时间（岗位添加或修改时间）
	private Date sdate;
	private Date edate;
	private String sellerName;// --店铺名称（商家名称）
	private String contact;// --联系人
	private String phone;// --联系电话
	private Integer nums;// 招聘人数
	private String strNums;// 招聘人数，字符串类型
	private String salary;// 薪资要求,编号
	private String strSalary;// 薪资要求（3000～5000）
	private Integer ageMax;// 最大年龄
	private Integer ageMin;// 最小年龄
	private String age;// --年龄
	private String province;// 省份,编号
	private String provinceName;// 对应的省份名称
	private String city;// 工作城市，编号
	private String cityName;// 对应的城市名称
	private String experie;// 工作经验（编号）
	private String strExperie;// 工作经验（1-3年）
	private String degrees;// 学历编号
	private String strDegrees;// 对应的学历名称
	private String stationRequire; // --岗位要求
	private List<RecruitTag> stationRequireList;// --岗位要求(招聘标签集合)
	private Integer status;// 状态 默认1 1 正常 0 删除
	private String recruitStationIds;
	private Integer[] recruitIds;// 招聘信息表的id集合，用于条件查询

	public Integer getRecruitStationId() {
		return recruitStationId;
	}

	public void setRecruitStationId(Integer recruitStationId) {
		this.recruitStationId = recruitStationId;
	}

	public Integer getRecruitId() {
		return recruitId;
	}

	public void setRecruitId(Integer recruitId) {
		this.recruitId = recruitId;
	}

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public Integer getTagId() {
		return tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}

	public List<String> getTagIds() {
		return tagIds;
	}

	public void setTagIds(List<String> tagIds) {
		this.tagIds = tagIds;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Date getSdate() {
		return sdate;
	}

	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}

	public Date getEdate() {
		return edate;
	}

	public void setEdate(Date edate) {
		this.edate = edate;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getNums() {
		return nums;
	}

	public void setNums(Integer nums) {
		this.nums = nums;
	}

	public String getStrNums() {
		if (strNums == null)
			return nums + "人";
		return strNums;
	}

	public void setStrNums(String strNums) {
		this.strNums = strNums;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public Integer getAgeMax() {
		return ageMax;
	}

	public void setAgeMax(Integer ageMax) {
		this.ageMax = ageMax;
	}

	public Integer getAgeMin() {
		return ageMin;
	}

	public void setAgeMin(Integer ageMin) {
		this.ageMin = ageMin;
	}

	public String getAge() {
		if (this.ageMax != null && this.ageMin != null)
			return this.ageMin + "-" + this.ageMax + "岁";
		return age;
	}

	public void setAge(String age) {
		if (this.ageMax != null && this.ageMin != null)
			this.age = this.ageMin + "-" + this.ageMax + "岁";
		this.age = age;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getExperie() {
		return experie;
	}

	public void setExperie(String experie) {
		this.experie = experie;
	}

	public String getDegrees() {
		return degrees;
	}

	public void setDegrees(String degrees) {
		this.degrees = degrees;
	}

	public String getStationRequire() {
		return stationRequire;
	}

	public void setStationRequire(String stationRequire) {
		this.stationRequire = stationRequire;
	}

	public List<RecruitTag> getStationRequireList() {
		return stationRequireList;
	}

	public void setStationRequireList(List<RecruitTag> stationRequireList) {
		this.stationRequireList = stationRequireList;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRecruitStationIds() {
		return recruitStationIds;
	}

	public void setRecruitStationIds(String recruitStationIds) {
		this.recruitStationIds = recruitStationIds;
	}

	public Integer[] getRecruitIds() {
		return recruitIds;
	}

	public void setRecruitIds(Integer[] recruitIds) {
		this.recruitIds = recruitIds;
	}

	public String getStrSalary() {
		if(StringUtils.isBlank(salary)){
			return "-";
		}
		switch (Integer.parseInt(salary)) {
		case 0:
			return "面议";
		case 1:
			return "3000以下";
		case 2:
			return "3000-5000";
		case 3:
			return "5000-8000";
		case 4:
			return "8000-10000";
		case 5:
			return "10000以上";
		default :
			return "-";
		}
	}

	public void setStrSalary(String strSalary) {
		this.strSalary = strSalary;
	}

	public String getStrExperie() {
		if(StringUtils.isBlank(experie)){
			return "-";
		}
		switch (Integer.parseInt(experie)) {
		case 0:
			return "不限";
		case 1:
			return "1年以下";
		case 2:
			return "1-3年";
		case 3:
			return "3-5年";
		case 4:
			return "5-10年";
		case 5:
			return "10年以上";
		default :
			return "-";
		}
	}

	public void setStrExperie(String strExperie) {
		this.strExperie = strExperie;
	}

	public String getStrDegrees() {
		if(StringUtils.isBlank(degrees)){
			return "-";
		}
		switch (Integer.parseInt(degrees)) {
		case 0:
			return "小学";
		case 1:
			return "初中";
		case 2:
			return "高中";
		case 3:
			return "大专";
		case 4:
			return "本科以上";
		default :
			return "-";
		}
	}

	public void setStrDegrees(String strDegrees) {
		this.strDegrees = strDegrees;
	}

	@Override
	public String toString() {
		return "RecruitStation [recruitStationId=" + recruitStationId
				+ ", recruitId=" + recruitId + ", sellerId=" + sellerId
				+ ", tagId=" + tagId + ", tagIds=" + tagIds + ", stationName="
				+ stationName + ", updateDate=" + updateDate + ", sdate="
				+ sdate + ", edate=" + edate + ", sellerName=" + sellerName
				+ ", contact=" + contact + ", phone=" + phone + ", nums="
				+ nums + ", strNums=" + strNums + ", salary=" + salary
				+ ", strSalary=" + strSalary + ", ageMax=" + ageMax
				+ ", ageMin=" + ageMin + ", age=" + age + ", province="
				+ province + ", provinceName=" + provinceName + ", city="
				+ city + ", cityName=" + cityName + ", experie=" + experie
				+ ", strExperie=" + strExperie + ", degrees=" + degrees
				+ ", strDegrees=" + strDegrees + ", stationRequire="
				+ stationRequire + ", stationRequireList=" + stationRequireList
				+ ", status=" + status + ", recruitStationIds="
				+ recruitStationIds + ", recruitIds="
				+ Arrays.toString(recruitIds) + "]";
	}
	
}
