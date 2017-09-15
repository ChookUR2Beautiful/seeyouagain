package com.xmniao.xmn.core.live.entity;

import java.io.Serializable;
import java.util.Date;

public class UrsEarningsRelationInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7190761508169414465L;

	private Integer id;
	
	//寻蜜鸟会员uid
	private Integer uid;   
	
	//会员渠道来源 1.vip 2.商家 3.直销 4.v客 5.寻蜜客
	private Integer objectOriented; 
	
	//会员uid关系链
	private String uidRelationChain;

	//会员昵称关系链
	private String uidRelationChainNname; 
	
	//会员层级，顶级为1，下级依次+1
	private Integer uidRelationChainLevel;
	
	//会员绑定间接上级
	private Integer indirectUid; 
	
	//(商家)推荐商家
	private Integer referrerSellerid; 
	
	//(商家)推荐商家类型 1普通商家 2连锁总店 3区域代理
	private Integer referrerSellertype;
	
	//更新时间
	private Date createTime;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getUid() {
		return uid;
	}


	public void setUid(Integer uid) {
		this.uid = uid;
	}


	public Integer getObjectOriented() {
		return objectOriented;
	}


	public void setObjectOriented(Integer objectOriented) {
		this.objectOriented = objectOriented;
	}


	public String getUidRelationChain() {
		return uidRelationChain;
	}


	public void setUidRelationChain(String uidRelationChain) {
		this.uidRelationChain = uidRelationChain;
	}


	public String getUidRelationChainNname() {
		return uidRelationChainNname;
	}


	public void setUidRelationChainNname(String uidRelationChainNname) {
		this.uidRelationChainNname = uidRelationChainNname;
	}


	public Integer getUidRelationChainLevel() {
		return uidRelationChainLevel;
	}


	public void setUidRelationChainLevel(Integer uidRelationChainLevel) {
		this.uidRelationChainLevel = uidRelationChainLevel;
	}


	public Integer getIndirectUid() {
		return indirectUid;
	}


	public void setIndirectUid(Integer indirectUid) {
		this.indirectUid = indirectUid;
	}


	public Integer getReferrerSellerid() {
		return referrerSellerid;
	}


	public void setReferrerSellerid(Integer referrerSellerid) {
		this.referrerSellerid = referrerSellerid;
	}


	public Integer getReferrerSellertype() {
		return referrerSellertype;
	}


	public void setReferrerSellertype(Integer referrerSellertype) {
		this.referrerSellertype = referrerSellertype;
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	@Override
	public String toString() {
		return "UrsEarningsRelationInfo [id=" + id + ", uid=" + uid
				+ ", objectOriented=" + objectOriented + ", uidRelationChain="
				+ uidRelationChain + ", uidRelationChainNname="
				+ uidRelationChainNname + ", uidRelationChainLevel="
				+ uidRelationChainLevel + ", indirectUid=" + indirectUid
				+ ", referrerSellerid=" + referrerSellerid
				+ ", referrerSellertype=" + referrerSellertype
				+ ", createTime=" + createTime + "]";
	}

	
	
	
}
