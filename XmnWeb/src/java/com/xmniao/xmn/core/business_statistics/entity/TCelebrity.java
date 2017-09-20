package com.xmniao.xmn.core.business_statistics.entity;

import java.math.BigDecimal;

import com.xmniao.xmn.core.base.BaseEntity;


/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TCelebrity
 * 
 * 类描述： 网红角色实体类
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-12-12 下午2:23:03 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TCelebrity extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = -5997453758166506419L;

	private Long id;//主键

    private Integer sex;//性别   1:男    2:女

    private String avatar;//网红头像

    private Integer age;//网红年龄

    private String name;//网红姓名

    private BigDecimal picturePrice;//网红晒图价格

    private BigDecimal sharePrice;//分享价格

    private BigDecimal reviewPrice;//食评费用
    
    private Integer reviewNum;//食评次数

    private Integer orderNum;//订单数量

    private Integer fansNum;//粉丝数量

    private Integer province;//省编号

    private Integer city;//市编号

    private Integer area;//区编号

    private String address;//联系地址

    private String phone;//手机号

    private String email;//邮箱

    private String describe;//简介

    private String imageJson;//网红相册图片,以Json数组格式保存

    private Integer type;//网红类型 1:主播   2:名嘴
    
    private String status;//冻结状态：001 启用 , 002 冻结
    
    private String filterVal;//过滤条件

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public BigDecimal getPicturePrice() {
        return picturePrice;
    }

    public void setPicturePrice(BigDecimal picturePrice) {
        this.picturePrice = picturePrice;
    }

    public BigDecimal getSharePrice() {
        return sharePrice;
    }

    public void setSharePrice(BigDecimal sharePrice) {
        this.sharePrice = sharePrice;
    }

    public BigDecimal getReviewPrice() {
        return reviewPrice;
    }

    public void setReviewPrice(BigDecimal reviewPrice) {
        this.reviewPrice = reviewPrice;
    }
    
    

    /**
	 * @return the reviewNum
	 */
	public Integer getReviewNum() {
		return reviewNum;
	}

	/**
	 * @param reviewNum the reviewNum to set
	 */
	public void setReviewNum(Integer reviewNum) {
		this.reviewNum = reviewNum;
	}

	public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getFansNum() {
        return fansNum;
    }

    public void setFansNum(Integer fansNum) {
        this.fansNum = fansNum;
    }

    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }

    public String getImageJson() {
        return imageJson;
    }

    public void setImageJson(String imageJson) {
        this.imageJson = imageJson == null ? null : imageJson.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    
    

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	

	/**
	 * @return the filterVal
	 */
	public String getFilterVal() {
		return filterVal;
	}

	/**
	 * @param filterVal the filterVal to set
	 */
	public void setFilterVal(String filterVal) {
		this.filterVal = filterVal;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TCelebrity [id=" + id + ", sex=" + sex + ", avatar=" + avatar
				+ ", age=" + age + ", name=" + name + ", picturePrice="
				+ picturePrice + ", sharePrice=" + sharePrice
				+ ", reviewPrice=" + reviewPrice + ", reviewNum=" + reviewNum
				+ ", orderNum=" + orderNum + ", fansNum=" + fansNum
				+ ", province=" + province + ", city=" + city + ", area="
				+ area + ", address=" + address + ", phone=" + phone
				+ ", email=" + email + ", describe=" + describe
				+ ", imageJson=" + imageJson + ", type=" + type + ", status="
				+ status + "]";
	}
    
    
}