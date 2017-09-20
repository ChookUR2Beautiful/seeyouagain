package com.xmniao.xmn.core.jobmanage.entity;

import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.DateUtil;

/**
 *@ClassName:BXmer
 *@Description:寻蜜客成员实体类
 *@author hls
 *@date:2016年5月25日上午11:51:07
 */
public class UserCV extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2444147689389653489L;

	/**
	 * 表b_user_cv
	 */
	private Integer id;//主键ID
	
	private Integer uid;//关联用户ID
	
	private String name;//姓名
	
	private String headpic;//头像
	
	private Integer sex;//性别
	private String sexStr;//性别
	
	private Integer age;//年龄
	
	private String phoneid;//手机号码
	
	private String degrees;//最高学历
	
	private String experie;//工作经验 
	
	private String salary;//薪资要求
	
	private String workProvince;//工作省份编号
	
	private String workCity;//工作城城市编号
	
	private Integer isHide;//隐藏简历 默认0 不隐藏 1 隐藏
	
	private String shieldShop;//屏蔽店铺:入店铺名称，可屏蔽该店铺查看我的求职信息
	
	private Integer status;//简历状态 默认1 正常 0 删除
	
	private Date sdate;//创建时间
	private String sdateStr;//创建时间
	
	private Date edate;//创建时间结束范围
	
	private Date updateDate;//更新时间
	
	private String ids;//批量删除id
	
    /**
     * 表b_tag_entity
     */
	//1：招聘岗位 岗位要求2 简历 我做过岗位 3 简历 我想做岗位 4 简历 培训经历 5 简历 自我评价
	private String iwant;//标签名（我想做）
	private String doing;//标签名（我做过岗位）
	private String experience;//标签名（我做过岗位）
	private String selfAssessment;//标签名（我做过岗位）
	
	
	public String getIwant() {
		return iwant;
	}

	public void setIwant(String iwant) {
		this.iwant = iwant;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getSelfAssessment() {
		return selfAssessment;
	}

	public void setSelfAssessment(String selfAssessment) {
		this.selfAssessment = selfAssessment;
	}


	public String getDoing() {
		return doing;
	}

	public void setDoing(String doing) {
		this.doing = doing;
	}

	public String getExperie() {
		return experie;
	}

	public void setExperie(String experie) {
		this.experie = experie;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getHeadpic() {
		return headpic;
	}

	public void setHeadpic(String headpic) {
		this.headpic = headpic;
	}

	public String getDegrees() {
		return degrees;
	}

	public void setDegrees(String degrees) {
		this.degrees = degrees;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getWorkProvince() {
		return workProvince;
	}

	public void setWorkProvince(String workProvince) {
		this.workProvince = workProvince;
	}

	public String getWorkCity() {
		return workCity;
	}

	public void setWorkCity(String workCity) {
		this.workCity = workCity;
	}

	public Integer getIsHide() {
		return isHide;
	}

	public void setIsHide(Integer isHide) {
		this.isHide = isHide;
	}

	public String getShieldShop() {
		return shieldShop;
	}

	public void setShieldShop(String shieldShop) {
		this.shieldShop = shieldShop;
	}

	
	public String getSdateStr() {
		if(sdate == null || "".equals(sdate)) return "-";
		sdateStr = DateUtil.formatDate(sdate,"yyyy-MM-dd");
		return sdateStr;
	}

	public void setSdateStr(String sdateStr) {
		this.sdateStr = sdateStr;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getPhoneid() {
		return phoneid;
	}

	public void setPhoneid(String phoneid) {
		this.phoneid = phoneid;
	}


	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	public String getSexStr() {
		if(sex == null || "".equals(sex)) return "-";
		if(sex==1) return "男";
		if(sex==2) return "女";
		return sexStr;
	}

	public void setSexStr(String sexStr) {
		this.sexStr = sexStr;
	}

	@Override
	public String toString() {
		return "UserCV [id=" + id + ", uid=" + uid + ", name=" + name
				+ ", headpic=" + headpic + ", sex=" + sex + ", sexStr="
				+ sexStr + ", age=" + age + ", phoneid=" + phoneid
				+ ", degrees=" + degrees + ", experie=" + experie + ", salary="
				+ salary + ", workProvince=" + workProvince + ", workCity="
				+ workCity + ", isHide=" + isHide + ", shieldShop="
				+ shieldShop + ", status=" + status + ", sdate=" + sdate
				+ ", sdateStr=" + sdateStr + ", edate=" + edate
				+ ", updateDate=" + updateDate + ", ids=" + ids + ", iwant="
				+ iwant + ", doing=" + doing + ", experience=" + experience
				+ ", selfAssessment=" + selfAssessment + "]";
	}

}
