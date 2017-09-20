package com.xmniao.xmn.core.cloud_design.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.DateUtil;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：DMaterial
 * 
 * 类描述： 物料实体类
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-11-22 下午6:26:56 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class DMaterial extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 4836433053970425741L;

	private Long id;//业务主键

    private Long categoryId;//物料分类Id
    
    private Long tagId;//标签Id

    private Long supplierId;//供应商id

    private Integer soldTimes;//使用次数

    private BigDecimal basePrice;//基础价格(原价)

    private BigDecimal salePrice;//销售价格

    private BigDecimal weight;//重量

    private String title;//模板标题

    private String remark;//描述

    private Date createTime;//创建时间
    
    private String createTimeStr;//创建时间Str

    private Date updateTime;//更新时间

    private Integer finishtime;//完成时间(单位：天)

    private String no;//编号

    private String type;//物料类型（平面物料：001，线上广告：002，装饰物料：003）

    private Integer monthlySales;//月销量

    private Integer paymentNumber;//付款人数

    private String picUrl;//主图图片地址
    
    private List<DMaterialCarouselSource> bannerList;//banner图
    
    private String status;//上架状态(001：已上架，002：已下架，默认 001)
    
    private String statusStr;//上架状态

    private String content;//图文详情
    
    private String htmlContent;//图文详情html文本
    
    private String materialAttrVals;//商品规格及规格细项组合信息,字符串格式(1_类型:1_影视,2_传媒;2_副色调:1_浅黄,2_淡蓝)
    
    private List<DMaterialAttr> materialAttrList;//商品规格信息
    
    private List<DMaterialAttrGroup> materialAttrGroupList;//商品规格分组信息
    
    private List<DMaterialCarousel> materialCarouselList;//商品模板列表

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    
    /**
	 * @return the tagId
	 */
	public Long getTagId() {
		return tagId;
	}

	/**
	 * @param tagId the tagId to set
	 */
	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getSoldTimes() {
        return soldTimes;
    }

    public void setSoldTimes(Integer soldTimes) {
        this.soldTimes = soldTimes;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    

    /**
	 * @return the createTimeStr
	 */
	public String getCreateTimeStr() {
		if(createTime==null){
			return null;
		}
		createTimeStr=DateUtil.formatDate(createTime, DateUtil.Y_M_D_HMS);
		return createTimeStr;
	}

	/**
	 * @param createTimeStr the createTimeStr to set
	 */
	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getFinishtime() {
        return finishtime;
    }

    public void setFinishtime(Integer finishtime) {
        this.finishtime = finishtime;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getMonthlySales() {
        return monthlySales;
    }

    public void setMonthlySales(Integer monthlySales) {
        this.monthlySales = monthlySales;
    }

    public Integer getPaymentNumber() {
        return paymentNumber;
    }

    public void setPaymentNumber(Integer paymentNumber) {
        this.paymentNumber = paymentNumber;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }
    
    


	/**
	 * @return the bannerList
	 */
	public List<DMaterialCarouselSource> getBannerList() {
		return bannerList;
	}

	/**
	 * @param bannerList the bannerList to set
	 */
	public void setBannerList(List<DMaterialCarouselSource> bannerList) {
		this.bannerList = bannerList;
	}


	public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
    
    

    /**
	 * @return the statusStr
	 */
	public String getStatusStr() {
		if(status==null){
			return null;
		}
		switch (status) {
		case "001":
			statusStr="已上架";
			break;
		default:
			statusStr="已下架";
			break;
		}
		
		return statusStr;
	}

	/**
	 * @param statusStr the statusStr to set
	 */
	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
    
    

	/**
	 * @return the htmlContent
	 */
	public String getHtmlContent() {
		return htmlContent;
	}

	/**
	 * @param htmlContent the htmlContent to set
	 */
	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}

	/**
	 * 商品规格信息,字符串格式(1_类型:1_影视,2_传媒;2_副色调:1_浅黄,2_淡蓝)
	 * @return the materialAttrVals
	 */
	public String getMaterialAttrVals() {
		return materialAttrVals;
	}

	/**
	 * @param materialAttrVals the materialAttrVals to set
	 */
	public void setMaterialAttrVals(String materialAttrVals) {
		this.materialAttrVals = materialAttrVals;
	}
	
	

	/**
	 * @return the materialAttrList
	 */
	public List<DMaterialAttr> getMaterialAttrList() {
		return materialAttrList;
	}

	/**
	 * @param materialAttrList the materialAttrList to set
	 */
	public void setMaterialAttrList(List<DMaterialAttr> materialAttrList) {
		this.materialAttrList = materialAttrList;
	}
	
	

	/**
	 * @return the materialAttrGroupList
	 */
	public List<DMaterialAttrGroup> getMaterialAttrGroupList() {
		return materialAttrGroupList;
	}

	/**
	 * @param materialAttrGroupList the materialAttrGroupList to set
	 */
	public void setMaterialAttrGroupList(
			List<DMaterialAttrGroup> materialAttrGroupList) {
		this.materialAttrGroupList = materialAttrGroupList;
	}
	
	

	/**
	 * @return the materialCarouselList
	 */
	public List<DMaterialCarousel> getMaterialCarouselList() {
		return materialCarouselList;
	}

	/**
	 * @param materialCarouselList the materialCarouselList to set
	 */
	public void setMaterialCarouselList(List<DMaterialCarousel> materialCarouselList) {
		this.materialCarouselList = materialCarouselList;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DMaterial [id=" + id + ", categoryId=" + categoryId
				+ ", tagId=" + tagId + ", supplierId=" + supplierId
				+ ", soldTimes=" + soldTimes + ", basePrice=" + basePrice
				+ ", salePrice=" + salePrice + ", weight=" + weight
				+ ", title=" + title + ", remark=" + remark + ", createTime="
				+ createTime + ", createTimeStr=" + createTimeStr
				+ ", updateTime=" + updateTime + ", finishtime=" + finishtime
				+ ", no=" + no + ", type=" + type + ", monthlySales="
				+ monthlySales + ", paymentNumber=" + paymentNumber
				+ ", picUrl=" + picUrl + ", bannerList=" + bannerList
				+ ", status=" + status + ", statusStr=" + statusStr
				+ ", content=" + content + ", htmlContent=" + htmlContent
				+ ", materialAttrVals=" + materialAttrVals
				+ ", materialAttrList=" + materialAttrList
				+ ", materialAttrGroupList=" + materialAttrGroupList
				+ ", materialCarouselList=" + materialCarouselList + "]";
	}
    
    
}