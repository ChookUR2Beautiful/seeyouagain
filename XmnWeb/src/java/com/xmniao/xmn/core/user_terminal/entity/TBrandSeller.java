package com.xmniao.xmn.core.user_terminal.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.NumberUtil;
import com.xmniao.xmn.core.util.StringUtils;
import com.xmniao.xmn.core.util.handler.AreaHandler;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TBrandSeller
 * 
 * 类描述：品牌店
 * 
 * 创建人：zhang'zhiwen
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 * 
 */
public class TBrandSeller extends BaseEntity {

	public static final int IS_BRAND_APPEND = 0;
	public static final int IS_BRAND_ONLINE = 1;
	public static final int IS_BRAND_OFFLINE = 2;
	/**
	 * 
	 */
	private static final long serialVersionUID = 4646544163959591821L;

	private Integer brandId;// 品牌店(主键id)
	private String brandName;// 品牌店名称
	private String breviary;// 品牌商家logo缩略图URL
	private String picUrl;// 品牌商家logo图片URL
	private String bewrite;// 品牌商家logo图片描述
	private String creator;// 创建人
	private Date dateCreated;// 创建时间
	private String updator;// 修改人
	private Date dateUpdated;// 修改时间
	private Integer isBrand;// 上线状态：0 待上线, 1 已上线 2 已下线
	private Integer agioAgio;// 是否折上折 0全单返 1折上返
	private Double rebate;// 返利
	private Date dateStart;// 开始时间
	private Date dateEnd;// 结束时间
	private Integer sort;// 排序
	private Integer isAll;// 是否全国，0:否，1:是 默认为是
	private String province;// 省编号
	private String city;// 市编号
	private String area;// 区域编号,多个区域以“;”隔开

	private String picLow;// 低分辨率品牌logo图片
	private String picMiddle;// 中分辨率品牌logo图片
	private String brandImageLow;// 低分辨率品牌形象图片
	private String brandImageMiddle;// 中分辨率品牌形象图片
	private String brandImageHigh;// 高分辨率品牌形象图片
	private String activCont;// 非新用户满返活动描述
	private String activNewUser;// 新用户满返活动描述
	private Double agio;// 全单折扣

	private String areaTitle;// 区域名称
	private String cityTitle;// 城市名称
	private String rebatePercentageText;

	public Double getAgioPercent(){
		if(null!=getAgio()){
			return NumberUtil.getDoubleMultiply100Value(getAgio());
		}
		return null;
	}
	
	public String getAgioPercentText(){
		if(null!=getAgioPercent()){
			return getAgioPercent()+"%";
		}
		return "-";
	}
	
	public String getIsBrandText() {
		String isBrandText;
		if (getIsBrand() != null) {
			switch (getIsBrand()) {
			case IS_BRAND_APPEND:
				isBrandText = "待上线";
				break;
			case IS_BRAND_OFFLINE:
				isBrandText = "已下线";
				break;
			case IS_BRAND_ONLINE:
				isBrandText = "已上线";
				break;
			default:
				isBrandText = null;
				break;
			}
		} else {
			isBrandText = null;
		}
		return isBrandText;
	}

	public String getAgioAgioText() {
		if (agioAgio == null) {
			return "-";
		} else if (agioAgio == 0) {
			return "否";
		} else if (agioAgio == 1) {
			return "是";
		}
		return "";

	}

	public Integer getAgioAgio() {
		return agioAgio;
	}

	public void setAgioAgio(Integer agioAgio) {
		this.agioAgio = agioAgio;
	}

	public String getRebatePercentageText() {
		rebatePercentageText = (String
				.valueOf(this.rebate != null ? new BigDecimal(this.rebate
						.toString()).multiply(new BigDecimal(100.00))
						.doubleValue() : 0d) + "%");
		return rebatePercentageText;
	}

	public void setRebatePercentageText(String rebatePercentageText) {
		this.rebatePercentageText = rebatePercentageText;
	}

	public Double getRebatePercentage() {
		return this.rebate != null ? new BigDecimal(this.rebate.toString())
				.multiply(new BigDecimal(100.00)).doubleValue() : 0L;
	}

	/**
	 * 取得区域的初始化值
	 * 
	 * @return
	 */
	public String getAreaInitValue() {
		String st = null;
		if (this.isAll == 0) {
			if (this.province != null && this.province.length() > 0) {
				st = getProvince();
			}
			if (this.city != null && this.city.length() > 0) {
				st = getCity();
			}
			if (this.area != null && this.area.length() > 0) {
				st = getArea();
			}
		}
		return st;
	}

	public String getAreaText() {
		StringBuilder sb = new StringBuilder();
		if (this.isAll != null && this.isAll == 1) {
			sb.append("全国");
		} else {
			if (StringUtils.hasLength(this.province)) {
				sb.append(AreaHandler.getAreaHandler().getAreaIdByTitle(
						Integer.parseInt(this.province)));

			}
			if (StringUtils.hasLength(this.city)) {
				sb.append(" - ");
				sb.append(AreaHandler.getAreaHandler().getAreaIdByTitle(
						Integer.parseInt(this.city)));

			}
			if (StringUtils.hasLength(this.area)) {
				sb.append(" - ");
				String delim = ",";
				int index = this.area.indexOf(delim);
				if (index == -1) {
					delim = ";";
				}
				String[] areas = StringUtils.paresToArray(this.area, delim);
				for (int i = 0; i < areas.length; i++) {
					if (i > 0) {
						sb.append(",");
					}
					sb.append(AreaHandler.getAreaHandler().getAreaIdByTitle(
							Integer.parseInt(areas[i])));
				}
			}

		}
		return sb.toString();
	}

	public String getIsAllText() {
		if (isAll != null) {
			return isAll == 1 ? "是" : "否";
		}
		return null;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getBreviary() {
		return breviary;
	}

	public void setBreviary(String breviary) {
		this.breviary = breviary;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getBewrite() {
		return bewrite;
	}

	public void setBewrite(String bewrite) {
		this.bewrite = bewrite;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getUpdator() {
		return updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public Integer getIsBrand() {
		return isBrand;
	}

	public void setIsBrand(Integer isBrand) {
		this.isBrand = isBrand;
	}

	public Double getRebate() {
		/*
		 * return new BigDecimal(rebate.toString()).multiply(new
		 * BigDecimal(100.00)).doubleValue();
		 */
		return this.rebate;
	}

	public void setRebate(Double rebate) {
		this.rebate = rebate;
	}

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getIsAll() {
		return isAll;
	}

	public void setIsAll(Integer isAll) {
		this.isAll = isAll;
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

	public String getPicLow() {
		return picLow;
	}

	public void setPicLow(String picLow) {
		this.picLow = picLow;
	}

	public String getPicMiddle() {
		return picMiddle;
	}

	public void setPicMiddle(String picMiddle) {
		this.picMiddle = picMiddle;
	}

	public String getBrandImageLow() {
		return brandImageLow;
	}

	public void setBrandImageLow(String brandImageLow) {
		this.brandImageLow = brandImageLow;
	}

	public String getBrandImageMiddle() {
		return brandImageMiddle;
	}

	public void setBrandImageMiddle(String brandImageMiddle) {
		this.brandImageMiddle = brandImageMiddle;
	}

	public String getBrandImageHigh() {
		return brandImageHigh;
	}

	public void setBrandImageHigh(String brandImageHigh) {
		this.brandImageHigh = brandImageHigh;
	}

	public String getActivCont() {
		return activCont;
	}

	public void setActivCont(String activCont) {
		this.activCont = activCont;
	}

	public String getActivNewUser() {
		return activNewUser;
	}

	public void setActivNewUser(String activNewUser) {
		this.activNewUser = activNewUser;
	}

	public Double getAgio() {
		return agio;
	}

	public void setAgio(Double agio) {
		this.agio = agio;
	}

	public String getAreaTitle() {
		return areaTitle;
	}

	public void setAreaTitle(String areaText) {
		this.areaTitle = areaText;
	}

	public String getCityTitle() {
		return cityTitle;
	}

	public void setCityTitle(String cityTitle) {
		this.cityTitle = cityTitle;
	}

	@Override
	public String toString() {
		return "TBrandSeller [brandId=" + brandId + ", brandName=" + brandName
				+ ", breviary=" + breviary + ", picUrl=" + picUrl
				+ ", bewrite=" + bewrite + ", creator=" + creator
				+ ", dateCreated=" + dateCreated + ", updator=" + updator
				+ ", dateUpdated=" + dateUpdated + ", isBrand=" + isBrand
				+ ", rebate=" + rebate + ", dateStart=" + dateStart
				+ ", dateEnd=" + dateEnd + ", sort=" + sort + ", isAll="
				+ isAll + ", province=" + province + ", city=" + city
				+ ", area=" + area + "]";
	}
}
