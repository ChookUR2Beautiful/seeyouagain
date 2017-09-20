package com.xmniao.xmn.core.live_anchor.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.DateUtil;

public class TFansCouponAnchorRef extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = -5351038701501095938L;

	private Integer anchorCid;//业务主键

    private Integer cid;//直播券ID

    private Integer uid;//主播会员ID

    private Integer recordId;//通告ID

    private Date startDate;//优惠券开始时间
    
    private String startDateStr;//优惠券开始时间

    private Date endDate;//优惠券结束时间
    
    private String endDateStr;//优惠券结束时间

    private String anchorRoomNo;//主播房间号

    private Integer sellerid;//商户ID

    private Integer sendNum;//发行总量
    
    private Integer currentSendNum;//修改前发行总量

    private Integer stock;//库存
    
    private Integer sendStatus;//发放状态:1 预售中 2 预售结束 3 已售罄
    
    private String haveCoupon;//提供直播券 1是  2否 
    
    private String introduce;//预告介绍
    
    private Integer stockChange;//库存变更值
    
    private Date sdate;//创建时间
    
    private BigDecimal saleCouponRation;//粉丝券销售比例
    
    private BigDecimal useCouponRation;//粉丝券使用比例
    
    private Date soldOutTime;//优惠券下架时间
    
    private String soldOutTimeStr;//优惠券下架时间
    
    

    public Integer getAnchorCid() {
        return anchorCid;
    }

    public void setAnchorCid(Integer anchorCid) {
        this.anchorCid = anchorCid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getAnchorRoomNo() {
        return anchorRoomNo;
    }

    public void setAnchorRoomNo(String anchorRoomNo) {
        this.anchorRoomNo = anchorRoomNo == null ? null : anchorRoomNo.trim();
    }

    public Integer getSellerid() {
        return sellerid;
    }

    public void setSellerid(Integer sellerid) {
        this.sellerid = sellerid;
    }

    public Integer getSendNum() {
        return sendNum;
    }

    public void setSendNum(Integer sendNum) {
        this.sendNum = sendNum;
    }
    
    

    /**
	 * @return the currentSendNum
	 */
	public Integer getCurrentSendNum() {
		return currentSendNum;
	}

	/**
	 * @param currentSendNum the currentSendNum to set
	 */
	public void setCurrentSendNum(Integer currentSendNum) {
		this.currentSendNum = currentSendNum;
	}

	public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(Integer sendStatus) {
        this.sendStatus = sendStatus;
    }
    
    

	/**
	 * @return the havaCoupon
	 */
	public String getHaveCoupon() {
		return haveCoupon;
	}

	/**
	 * @param havaCoupon the havaCoupon to set
	 */
	public void setHaveCoupon(String havaCoupon) {
		this.haveCoupon = havaCoupon;
	}
	
	

	/**
	 * @return the introduce
	 */
	public String getIntroduce() {
		return introduce;
	}

	/**
	 * @param introduce the introduce to set
	 */
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
	

	/**
	 * @return the startDateStr
	 */
	public String getStartDateStr() {
		if(startDate==null){
			return null;
		}
		return startDateStr = DateUtil.formatDate(startDate, DateUtil.Y_M_D_HM);
	}

	/**
	 * @param startDateStr the startDateStr to set
	 */
	public void setStartDateStr(String startDateStr) {
		this.startDateStr = startDateStr;
	}

	/**
	 * @return the endDateStr
	 */
	public String getEndDateStr() {
		if(endDate==null){
			return null;
		}
		return endDateStr=DateUtil.formatDate(endDate, DateUtil.Y_M_D_HM);
	}

	/**
	 * @param endDateStr the endDateStr to set
	 */
	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}
	
	

	/**
	 * @return the stockChange
	 */
	public Integer getStockChange() {
		return stockChange;
	}

	/**
	 * @param stockChange the stockChange to set
	 */
	public void setStockChange(Integer stockChange) {
		this.stockChange = stockChange;
	}
	
	

	/**
	 * @return the sdate
	 */
	public Date getSdate() {
		return sdate;
	}

	/**
	 * @param sdate the sdate to set
	 */
	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}

	/**
	 * @return the saleCouponRation
	 */
	public BigDecimal getSaleCouponRation() {
		return saleCouponRation;
	}

	/**
	 * @param saleCouponRation the saleCouponRation to set
	 */
	public void setSaleCouponRation(BigDecimal saleCouponRation) {
		this.saleCouponRation = saleCouponRation;
	}

	/**
	 * @return the useCouponRation
	 */
	public BigDecimal getUseCouponRation() {
		return useCouponRation;
	}

	/**
	 * @param useCouponRation the useCouponRation to set
	 */
	public void setUseCouponRation(BigDecimal useCouponRation) {
		this.useCouponRation = useCouponRation;
	}

	
	/**
	 * @return the soldOutTime
	 */
	public Date getSoldOutTime() {
		return soldOutTime;
	}

	/**
	 * @param soldOutTime the soldOutTime to set
	 */
	public void setSoldOutTime(Date soldOutTime) {
		this.soldOutTime = soldOutTime;
	}

	/**
	 * @return the soldOutTimeStr
	 */
	public String getSoldOutTimeStr() {
		if(soldOutTime==null){
			return null;
		}
		return soldOutTimeStr = DateUtil.formatDate(soldOutTime, DateUtil.Y_M_D_HM);
	}

	/**
	 * @param soldOutTimeStr the soldOutTimeStr to set
	 */
	public void setSoldOutTimeStr(String soldOutTimeStr) {
		this.soldOutTimeStr = soldOutTimeStr;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TFansCouponAnchorRef [anchorCid=" + anchorCid + ", cid=" + cid
				+ ", uid=" + uid + ", recordId=" + recordId + ", startDate="
				+ startDate + ", startDateStr=" + startDateStr + ", endDate="
				+ endDate + ", endDateStr=" + endDateStr + ", anchorRoomNo="
				+ anchorRoomNo + ", sellerid=" + sellerid + ", sendNum="
				+ sendNum + ", currentSendNum=" + currentSendNum + ", stock="
				+ stock + ", sendStatus=" + sendStatus + ", haveCoupon="
				+ haveCoupon + ", introduce=" + introduce + ", stockChange="
				+ stockChange + ", sdate=" + sdate + ", saleCouponRation="
				+ saleCouponRation + ", useCouponRation=" + useCouponRation
				+ ", soldOutTime=" + soldOutTime + ", soldOutTimeStr="
				+ soldOutTimeStr + "]";
	}
    
    
}