package com.xmniao.xmn.core.user_terminal.entity;

import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.coupon.entity.TCoupon;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TRegisterGift
 *
 * 类描述：注册礼包实体类
 * 
 * 创建人： ChenBo
 * 
 * 创建时间：2016年8月10日上午10:04:07
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TRegisterGift extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 2906469301527048186L;

	private Integer id;

    private Integer giftType;

    private Double quota;

    private Integer couponId;

    private Integer num;

    private Date edate;

    /*
     * 优惠券实体
     */
    private TCoupon coupon;
    
    /*
     * 礼物详情
     */
    private String giftStr;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGiftType() {
        return giftType;
    }

    public void setGiftType(Integer giftType) {
        this.giftType = giftType;
    }

    public Double getQuota() {
        return quota;
    }

    public void setQuota(Double quota) {
        this.quota = quota;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Date getEdate() {
        return edate;
    }

    public void setEdate(Date edate) {
        this.edate = edate;
    }

	public TCoupon getCoupon() {
		return coupon;
	}

	public void setCoupon(TCoupon coupon) {
		this.coupon = coupon;
	}

	
	public String getGiftStr() {
		StringBuilder giftSB = new StringBuilder();
		if(giftType != null){
			if(giftType==1){
				giftSB.append(String.format("%.2f",quota)).append("积分");
			}else if(giftType==2){
				//giftStr = coupon.getDenomination()+"元优惠券"+num+"张"+coupon.getDayNum()+"天内有效 "+;
				giftSB.append(String.format("%.2f",coupon.getDenomination())).append("元优惠券,");
				giftSB.append(num).append("张,");
				if(coupon.getCondition()==0){
					giftSB.append("无限制使用,");
				}else{
					giftSB.append("满").append(String.format("%.2f",coupon.getCondition())).append("元使用,");
				}
				giftSB.append("每次限用").append(coupon.getUseNum()).append("张,");
				giftSB.append("有效期").append(coupon.getDayNum()).append("天");
			}else if(giftType==3){
				giftSB.append(String.format("%.2f",quota)).append("鸟豆");
			}
		}
		return giftSB.toString();
	}

//	public void setGiftStr(String giftStr) {
//		this.giftStr = giftStr;
//	}

	@Override
	public String toString() {
		return "TRegisterGift [id=" + id + ", giftType=" + giftType
				+ ", quota=" + quota + ", couponId=" + couponId + ", num="
				+ num + ", edate=" + edate + ", coupon=" + coupon
				+ ", giftStr=" + giftStr + "]";
	}


}