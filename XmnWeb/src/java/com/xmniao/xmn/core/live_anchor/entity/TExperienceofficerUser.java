/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TExperienceofficerUser
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： ChenBo
 * 
 * 创建时间：2017年5月15日 上午10:22:07 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public class TExperienceofficerUser extends BaseEntity{

    private Integer uid;

    private String uids;
    
    private String phone;

    private String nname;

    /* 体验卡ID*/
    private Integer cardId;
    /*购买日期*/
    private Date buyTime;

    /*到期日期*/
    private Date limitTime;
   
    private Integer used;
    
    private Integer unused;
    
    private Integer status;
    

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNname() {
		return nname;
	}

	public void setNname(String nname) {
		this.nname = nname;
	}

	public Date getBuyTime() {
		return buyTime;
	}

	public void setBuyTime(Date buyTime) {
		this.buyTime = buyTime;
	}

	public Date getLimitTime() {
		return limitTime;
	}

	public void setLimitTime(Date limitTime) {
		this.limitTime = limitTime;
	}

	public String getBuyTimeStr() {
		if(buyTime != null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.format(buyTime);
		}
		return null;
	}


	public String getLimitTimeStr() {
		if(limitTime != null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.format(limitTime);
		}
		return null;
	}

	public Integer getUsed() {
		return used;
	}

	public void setUsed(Integer used) {
		this.used = used;
	}

	public Integer getUnused() {
		return unused;
	}

	public void setUnused(Integer unused) {
		this.unused = unused;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCardId() {
		return cardId;
	}

	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}

	public String getUids() {
		return uids;
	}

	public void setUids(String uids) {
		this.uids = uids;
	}

	public String getStatusStr() {
		if(this.status!=null && this.status==1){
			return "已停用";
		}
		if(limitTime!=null  && limitTime.before(new Date())){
			return "已过期";
		}
		String str=null;
		if(this.status!=null){
			switch (this.status) {
			case 0:
				str="使用中";
				break;
			case 1:
				str="已停用";
				break;
			default:
				str="未知状态";
				break;
			}
		}
		return str;
	}

    
}
