package com.xmniao.xmn.core.live_anchor.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;

public class TLivePurse extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1267784398161870246L;

	private Integer uid;// 寻蜜鸟 会员id

	private Integer walletId;// 直播钱包id

	private Integer option;// 类型 0-增加 1-扣减

	private Integer type; // 虚拟货币 1鸟豆 2鸟币

	private Integer rtype; // 渠道 1.平台充值

	private String money; // 金额数量

	private Date createTime;// 创建日期

	private Integer total;// 总数

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getWalletId() {
		return walletId;
	}

	public void setWalletId(Integer walletId) {
		this.walletId = walletId;
	}

	public Integer getOption() {
		return option;
	}

	public void setOption(Integer option) {
		this.option = option;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getRtype() {
		return rtype;
	}

	public void setRtype(Integer rtype) {
		this.rtype = rtype;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "TLivePurse [uid=" + uid + ", walletId=" + walletId
				+ ", option=" + option + ", type=" + type + ", rtype=" + rtype
				+ ", money=" + money + ", createTime=" + createTime
				+ ", total=" + total + "]";
	}

	
	
	
}