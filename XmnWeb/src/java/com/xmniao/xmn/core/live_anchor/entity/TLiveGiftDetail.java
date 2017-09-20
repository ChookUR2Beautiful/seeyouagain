package com.xmniao.xmn.core.live_anchor.entity;

import java.math.BigDecimal;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TLiveGiftDetail
 * 
 * 类描述： 礼物详情信息表
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-3-29 上午11:51:49 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TLiveGiftDetail extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = -1762954366501855440L;

	private Long id;//主键ID

    private Integer gid;//礼物ID(t_live_gift主键)
    
    private Integer giftKind;//礼物类型:1、普通礼物，2、商品礼物，3、套餐礼物

    private Long codeid;//产品编号
    
    private String pname;//产品名称

    private String pvIds;//属性值id组合，无序的，","作分隔符

    private String pvValue;//规格值
    
    private BigDecimal birdCoin;//鸟币=integral(t_product_info)+cash(t_product_info)+amount(t_sale_group)

    private String category;//商家经营类别

    private String genre;//二级类别

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    

    /**
	 * @return the giftKind
	 */
	public Integer getGiftKind() {
		return giftKind;
	}

	/**
	 * @param giftKind the giftKind to set
	 */
	public void setGiftKind(Integer giftKind) {
		this.giftKind = giftKind;
	}

	public Long getCodeid() {
        return codeid;
    }

    public void setCodeid(Long codeid) {
        this.codeid = codeid;
    }
    
    

    /**
	 * @return the pname
	 */
	public String getPname() {
		return pname;
	}

	/**
	 * @param pname the pname to set
	 */
	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPvIds() {
        return pvIds;
    }

    public void setPvIds(String pvIds) {
        this.pvIds = pvIds == null ? null : pvIds.trim();
    }

    public String getPvValue() {
        return pvValue;
    }

    public void setPvValue(String pvValue) {
        this.pvValue = pvValue == null ? null : pvValue.trim();
    }
    
    

    /**
	 * @return the birdCoin
	 */
	public BigDecimal getBirdCoin() {
		return birdCoin;
	}

	/**
	 * @param birdCoin the birdCoin to set
	 */
	public void setBirdCoin(BigDecimal birdCoin) {
		this.birdCoin = birdCoin;
	}

	public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre == null ? null : genre.trim();
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TLiveGiftDetail [id=" + id + ", gid=" + gid + ", codeid="
				+ codeid + ", pvIds=" + pvIds + ", pvValue=" + pvValue
				+ ", category=" + category + ", genre=" + genre + "]";
	}
    
    
}