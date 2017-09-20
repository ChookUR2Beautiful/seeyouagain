package com.xmniao.xmn.core.vstar.entity;

import java.util.Date;

import org.springframework.web.util.HtmlUtils;

import com.xmniao.xmn.core.base.BaseEntity;

public class FashionTickets extends BaseEntity{
    /**
     * ID
     */
    private Integer id;

    /**
     * 活动门票标题
     */
    private String title;

    /**
     * 省id
     */
    private Integer province;

    /**
     * 市id
     */
    private Integer city;

    /**
     * 图片url
     */
    private String pic;

    /**
     * LOGOurl
     */
    private String logo;

    /**
     * 地址
     */
    private String address;

    /**
     * 是否支持选座 0不支持 1支持
     */
    private Byte supportSelectSeats;

    /**
     * 是否仅支持鸟币支付 0否 1是
     */
    private Byte onlyCoin;

    /**
     * 每人限购张数 0.不限购 1.限购1张 n.限购n张
     */
    private Integer limitEveryone;

    /**
     * 开始销售时间
     */
    private Date saleStartTime;

    /**
     * 停止销售时间
     */
    private Date saleEndTime;

    /**
     * 开始使用(验证)时间
     */
    private Date useStartTime;

    /**
     * 停止使用(验证)时间
     */
    private Date useEndTime;

    /**
     * 咨询电话
     */
    private String tel;

    /**
     * 0正常 1无效
     */
    private Byte status;

    /**
     * 活动H5内容
     */
    private String content;
    
    private String seatPic;
    
    private String fids;
    
    private Integer totalSeats;
    
    private Integer seatSum;
    
    private Integer soldSum;
    
    private Integer residueSum;
    
    private String statusStr;
    
    /**
     * 经度
     */
    private Double longitude;
    
    /**
     * 纬度
     */
    private Double latitude;

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

	public Integer getSeatSum() {
		return seatSum;
	}

	public void setSeatSum(Integer seatSum) {
		this.seatSum = seatSum;
	}

	public Integer getSoldSum() {
		return soldSum;
	}

	public void setSoldSum(Integer soldSum) {
		this.soldSum = soldSum;
	}

	public Integer getResidueSum() {
		return residueSum;
	}

	public void setResidueSum(Integer residueSum) {
		this.residueSum = residueSum;
	}

	public String getStatusStr() {
		try {
			if(status==1){
				return "下架中";
			}else if(new Date().getTime()<saleStartTime.getTime()){
				return "未开售";
			}
			else if(new Date().getTime()>saleEndTime.getTime()){
				return "已结束";
			}
			else if(soldSum>=seatSum){
				return "售罄";
			}else{
				return "出售中";
			}
		} catch (Exception e) {
			return "异常";
		}
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public Integer getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(Integer totalSeats) {
		this.totalSeats = totalSeats;
	}

	public String getFids() {
		return fids;
	}

	public void setFids(String fids) {
		this.fids = fids;
	}

	public String getSeatPic() {
		return seatPic;
	}

	public void setSeatPic(String seatPic) {
		this.seatPic = seatPic;
	}

	/**
     * ID
     * @return id ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * ID
     * @param id ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 活动门票标题
     * @return title 活动门票标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 活动门票标题
     * @param title 活动门票标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 省id
     * @return province 省id
     */
    public Integer getProvince() {
        return province;
    }

    /**
     * 省id
     * @param province 省id
     */
    public void setProvince(Integer province) {
        this.province = province;
    }

    /**
     * 市id
     * @return city 市id
     */
    public Integer getCity() {
        return city;
    }

    /**
     * 市id
     * @param city 市id
     */
    public void setCity(Integer city) {
        this.city = city;
    }

    /**
     * 图片url
     * @return pic 图片url
     */
    public String getPic() {
        return pic;
    }

    /**
     * 图片url
     * @param pic 图片url
     */
    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    /**
     * LOGOurl
     * @return logo LOGOurl
     */
    public String getLogo() {
        return logo;
    }

    /**
     * LOGOurl
     * @param logo LOGOurl
     */
    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    /**
     * 地址
     * @return address 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 地址
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 是否支持选座 0不支持 1支持
     * @return support_select_seats 是否支持选座 0不支持 1支持
     */
    public Byte getSupportSelectSeats() {
        return supportSelectSeats;
    }

    /**
     * 是否支持选座 0不支持 1支持
     * @param supportSelectSeats 是否支持选座 0不支持 1支持
     */
    public void setSupportSelectSeats(Byte supportSelectSeats) {
        this.supportSelectSeats = supportSelectSeats;
    }

    /**
     * 是否仅支持鸟币支付 0否 1是
     * @return only_coin 是否仅支持鸟币支付 0否 1是
     */
    public Byte getOnlyCoin() {
        return onlyCoin;
    }

    /**
     * 是否仅支持鸟币支付 0否 1是
     * @param onlyCoin 是否仅支持鸟币支付 0否 1是
     */
    public void setOnlyCoin(Byte onlyCoin) {
        this.onlyCoin = onlyCoin;
    }

    /**
     * 每人限购张数 0.不限购 1.限购1张 n.限购n张
     * @return limit_everyone 每人限购张数 0.不限购 1.限购1张 n.限购n张
     */
    public Integer getLimitEveryone() {
        return limitEveryone;
    }

    /**
     * 每人限购张数 0.不限购 1.限购1张 n.限购n张
     * @param limitEveryone 每人限购张数 0.不限购 1.限购1张 n.限购n张
     */
    public void setLimitEveryone(Integer limitEveryone) {
        this.limitEveryone = limitEveryone;
    }

    /**
     * 开始销售时间
     * @return sale_start_time 开始销售时间
     */
    public Date getSaleStartTime() {
        return saleStartTime;
    }

    /**
     * 开始销售时间
     * @param saleStartTime 开始销售时间
     */
    public void setSaleStartTime(Date saleStartTime) {
        this.saleStartTime = saleStartTime;
    }

    /**
     * 停止销售时间
     * @return sale_end_time 停止销售时间
     */
    public Date getSaleEndTime() {
        return saleEndTime;
    }

    /**
     * 停止销售时间
     * @param saleEndTime 停止销售时间
     */
    public void setSaleEndTime(Date saleEndTime) {
        this.saleEndTime = saleEndTime;
    }

    /**
     * 开始使用(验证)时间
     * @return use_start_time 开始使用(验证)时间
     */
    public Date getUseStartTime() {
        return useStartTime;
    }

    /**
     * 开始使用(验证)时间
     * @param useStartTime 开始使用(验证)时间
     */
    public void setUseStartTime(Date useStartTime) {
        this.useStartTime = useStartTime;
    }

    /**
     * 停止使用(验证)时间
     * @return use_end_time 停止使用(验证)时间
     */
    public Date getUseEndTime() {
        return useEndTime;
    }

    /**
     * 停止使用(验证)时间
     * @param useEndTime 停止使用(验证)时间
     */
    public void setUseEndTime(Date useEndTime) {
        this.useEndTime = useEndTime;
    }

    /**
     * 咨询电话
     * @return tel 咨询电话
     */
    public String getTel() {
        return tel;
    }

    /**
     * 咨询电话
     * @param tel 咨询电话
     */
    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    /**
     * 0正常 1无效
     * @return status 0正常 1无效
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 0正常 1无效
     * @param status 0正常 1无效
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 活动H5内容
     * @return content 活动H5内容
     */
    public String getContent() {
        return HtmlUtils.htmlUnescape(content);
    }

    /**
     * 活动H5内容
     * @param content 活动H5内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}