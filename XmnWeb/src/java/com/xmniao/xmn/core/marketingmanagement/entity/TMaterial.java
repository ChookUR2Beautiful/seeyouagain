package com.xmniao.xmn.core.marketingmanagement.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.FastfdsConstant;


/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TMaterial
 * 
 * 类描述：物料信息实体表
 * 
 * 创建人： yhl
 * 
 * 创建时间：2016年7月15日13:47:56
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TMaterial extends BaseEntity{

	private static final long serialVersionUID = 2167826478116105478L;
	
	private Long id;
	
	//'物料名称'
	private String name; 
	
	//'物料缩略图'
	private String thumbnail; 
	
	//'物料缩略图'
	private String thumbnailUrl; 
	
	//'物料单价'
	private BigDecimal price;
	
	//'上下架状态 默认1 上架 0 下架'
	private Integer status;
	
	//'是否必须买 默认0 无必须 1 必须'
	private Integer ismust;
	
	//'销售数量'
	private Integer sold_quantity;
	
	//'添加时间'
	private Date create_time;
	
	private String create_time_str;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	public String getCreate_time_str() {
		if(null==create_time) return "-";
		return DateUtil.smartFormat(create_time);
	}

	public void setCreate_time_str(String create_time_str) {
		this.create_time_str = create_time_str;
	}

	public Integer getIsmust() {
		return ismust;
	}

	public void setIsmust(Integer ismust) {
		this.ismust = ismust;
	}

	public Integer getSold_quantity() {
		return sold_quantity;
	}

	public void setSold_quantity(Integer sold_quantity) {
		this.sold_quantity = sold_quantity;
	}

	public String getThumbnailUrl() {
		return FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP + thumbnail;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	@Override
	public String toString() {
		return "TMaterial [id=" + id + ", name=" + name + ", thumbnail="
				+ thumbnail + ", price=" + price + ", status=" + status
				+ ", ismust=" + ismust
				+ ", sold_quantity=" + sold_quantity + ", create_time="
				+ create_time + ", create_time_str=" + create_time_str + "]";
	}


	
	
	
}
