package com.xmniao.xmn.core.vstar.entity;

import java.util.List;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * 
 * 项目名称：XmnWeb_vstar
 * 
 * 类名称：TVstarEnrollImg
 * 
 * 类描述： 新时尚大赛报名相册信息
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-6-3 下午4:24:23 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TVstarEnrollImg extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4205739700606067127L;

	private Integer id;//业务主键

    private Integer pid;//参赛编号 t_vstar_enroll 主键

    private String imgType;//照片类型 101.个人风采照 ;201.身份证正面照 202.身份证反面照 203.手持身份证照

    private String imgUrl;//图片地址

    private Integer imgSort;//排序号 大->小排序
    
    private List<Integer> eids;//参赛编号集合

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getImgType() {
        return imgType;
    }

    public void setImgType(String imgType) {
        this.imgType = imgType == null ? null : imgType.trim();
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public Integer getImgSort() {
        return imgSort;
    }

    public void setImgSort(Integer imgSort) {
        this.imgSort = imgSort;
    }

	/**
	 * @return the eids
	 */
	public List<Integer> getEids() {
		return eids;
	}

	/**
	 * @param eids the eids to set
	 */
	public void setEids(List<Integer> eids) {
		this.eids = eids;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TVstarEnrollImg [id=" + id + ", pid=" + pid + ", imgType="
				+ imgType + ", imgUrl=" + imgUrl + ", imgSort=" + imgSort
				+ ", eids=" + eids + "]";
	}
    
    
}