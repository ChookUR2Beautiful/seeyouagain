package com.xmniao.xmn.core.coupon.entity;

import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

public class TCouponIssueRef extends BaseEntity {
	private static final long serialVersionUID = 5339406798270759604L;
	private Integer refId;// 主键
	private Integer issueId;// 发放表Id
	private Integer cid;// 优惠券表Id
	private Double amount;// 消费金额(满送专用)
	private Integer num;// 所送券数，单位为张(满送专用)
	private Integer issueVolume;// 发行总量
	private String creator;// 创建人
	private Date dateCreated;// 创建时间
	private String updator;// 修改人
	private Date dateUpdated;// 修改时间
	private Double hitRatio;//概率(分享发放优惠专用，存储的是小数，如：0.1代表10%)
	private Integer type;//类型(分享发放优惠专用) 1：订单后刮刮卡，2：分享的刮刮卡
	private String cname;// 优惠劵名称
	
	private String refIds;//
	
	public Double getHitRatioPercent(){
		return this.getHitRatio()*100;
	}
	public enum Type {
		ORDER("订单后刮刮卡", 1), SHARE("分享的刮刮卡", 2);
		private String name;
		private int index;

		private Type(String name, Integer index) {
			this.index = index;
			this.name = name;
		}

		public static String getName(int index) {
			for (Type c : Type.values()) {
				if (c.getIndex() == index) {
					return c.name;
				}
			}
			return null;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}

	}
	
	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Integer getRefId() {
		return refId;
	}

	public void setRefId(Integer refId) {
		this.refId = refId;
	}

	public Integer getIssueId() {
		return issueId;
	}

	public void setIssueId(Integer issueId) {
		this.issueId = issueId;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getIssueVolume() {
		return issueVolume;
	}

	public void setIssueVolume(Integer issueVolume) {
		this.issueVolume = issueVolume;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

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

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public Double getHitRatio() {
		return hitRatio;
	}

	public void setHitRatio(Double hitRatio) {
		this.hitRatio = hitRatio;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getRefIds() {
		return refIds;
	}

	public void setRefIds(String refIds) {
		this.refIds = refIds;
	}

	@Override
	public String toString() {
		return "TCouponIssueRef [refId=" + refId + ", issueId=" + issueId
				+ ", cid=" + cid + ", amount=" + amount + ", num=" + num
				+ ", issueVolume=" + issueVolume + ", creator=" + creator
				+ ", dateCreated=" + dateCreated + ", updator=" + updator
				+ ", dateUpdated=" + dateUpdated + ", hitRatio=" + hitRatio
				+ ", type=" + type + ", cname=" + cname + "]";
	}

}
