package com.xmniao.xmn.core.live_anchor.entity;

import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;


/**
 * 
 * 项目名称：XmnWeb_LVB
 * 
 * 类名称：TLiveGiftBag
 *
 * 类描述：礼包实体类
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016-8-29下午5:52:46
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TLiveGiftBag extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7593802802184740573L;

	private Integer id;//业务主键

    private String giftBagName;//礼包名称

    private Integer status;//是否启用  1 启用  0 未启用

    private Date createTime;//创建时间

    private Date updateTime;//更新时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGiftBagName() {
        return giftBagName;
    }

    public void setGiftBagName(String giftBagName) {
        this.giftBagName = giftBagName == null ? null : giftBagName.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TLiveGiftBag [id=" + id + ", giftBagName=" + giftBagName
				+ ", status=" + status + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "]";
	}
    
    
}