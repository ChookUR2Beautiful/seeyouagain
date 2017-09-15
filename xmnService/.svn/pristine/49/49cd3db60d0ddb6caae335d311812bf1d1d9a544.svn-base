package com.xmniao.xmn.core.xmer.entity;

/**
 * 寻蜜客排行榜实体类
 * @ClassName:TopRankXmer
 * @Description:包含寻蜜客排行榜的想关信息
 * @Author:xw
 * @Date:2017年5月31日上午11:04:33
 */
public class TopRankXmer implements Comparable<TopRankXmer>{

	private Integer sort;					//排序号
	private String name;					//用户名
	private Integer sex;					//性别
	private String income;					//总收入
	private Integer sellerNums;				//店铺销量
	private Integer partnerNums;			//伙伴数量
	private String avatar;					//头像
	private String achievement;				//成就
	private Integer id;						//寻蜜客id
	private Integer uid;					//用户uid
	private String uname;					//登录手机号，当昵称为空时显示
	
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
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
	public String getIncome() {
		return income;
	}
	public void setIncome(String income) {
		this.income = income;
	}
	public Integer getSellerNums() {
		return sellerNums;
	}
	public void setSellerNums(Integer sellerNums) {
		this.sellerNums = sellerNums;
	}
	public Integer getPartnerNums() {
		return partnerNums;
	}
	public void setPartnerNums(Integer partnerNums) {
		this.partnerNums = partnerNums;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getAchievement() {
		return achievement;
	}
	public void setAchievement(String achievement) {
		this.achievement = achievement;
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
	@Override
	public int compareTo(TopRankXmer o) {
		
		int i = o.getSellerNums().compareTo(this.getSellerNums());
		    
		if(i==0){
			return Double.compare(Double.parseDouble(o.getIncome()), Double.parseDouble(this.getIncome()));
		}
		return i;
	}
	@Override
	public String toString() {
		return "TopRankXmer [sort=" + sort + ", name=" + name + ", sex=" + sex + ", income=" + income + ", sellerNums="
				+ sellerNums + ", partnerNums=" + partnerNums + ", avatar=" + avatar + ", achievement=" + achievement
				+ ", id=" + id + ", uid=" + uid + "]";
	}
	
	
	
}
