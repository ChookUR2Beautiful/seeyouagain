package com.xmniao.xmn.core.live_anchor.entity;

import java.math.BigDecimal;

import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.NumberUtil;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TFansCouponIssueRef
 * 
 * 类描述： 直播粉丝券与抵用券配置关系实体类
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-10-28 上午11:20:19 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TFansCouponIssueRef extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3861623040732097426L;

	private Integer id;//业务主键

    private Integer cid;//粉丝券ID

    private Integer giftCid;//抵用券ID

    private Integer num;//数量 ,默认1
    
    private BigDecimal denomination;//抵用券面额
    
    private BigDecimal condition;//使用时最低金额，0表示无条件使用

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getGiftCid() {
        return giftCid;
    }

    public void setGiftCid(Integer giftCid) {
        this.giftCid = giftCid;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

	/**
	 * @return the denomination
	 */
	public BigDecimal getDenomination() {
		if(denomination==null){
    		return null;
    	}else{
    		return denomination=new BigDecimal(NumberUtil.getNumberFixedpoint(denomination, 0));
    	}
	}

	/**
	 * @param denomination the denomination to set
	 */
	public void setDenomination(BigDecimal denomination) {
		this.denomination = denomination;
	}

	/**
	 * @return the condition
	 */
	public BigDecimal getCondition() {
		return condition;
	}

	/**
	 * @param condition the condition to set
	 */
	public void setCondition(BigDecimal condition) {
		this.condition = condition;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TFansCouponIssueRef [id=" + id + ", cid=" + cid + ", giftCid="
				+ giftCid + ", num=" + num + ", denomination=" + denomination
				+ ", condition=" + condition + "]";
	}
    
    
}