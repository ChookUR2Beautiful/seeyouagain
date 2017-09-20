/**
 * 
 */
package com.xmniao.xmn.core.businessman.entity;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：GetRed
 * 
 * 类描述： 红包领取详情
 * 
 * 创建人： chenJie
 * 
 * 创建时间：2016年12月8日 上午10:15:35 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public class GetRedPackageRecord extends BaseEntity{
	
	private static final long serialVersionUID = 4385010816088327109L;

	private Long id ;
	
	private Long redPackageId;//红包id
	
	private Long userId;//用户id
	
	private String userName;//用户名称
	
	private String phone;//手机号
	
	private BigDecimal denomination;//领取金额
	
	private Date recordTime;//领取时间
	
	private String recordTimeStr;
	
	private Date sDate;
	
	private Date eDate;
	
	private Integer isBanding;//是否绑定
	
	private Integer isShare;//是否分享
	
	private Integer status;//到账状态

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRedPackageId() {
		return redPackageId;
	}

	public void setRedPackageId(Long redPackageId) {
		this.redPackageId = redPackageId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public BigDecimal getDenomination() {
		return denomination;
	}

	public void setDenomination(BigDecimal denomination) {
		this.denomination = denomination;
	}

	public Date getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

	public String getRecordTimeStr() {
		return new SimpleDateFormat("yyyy-MM-dd").format(recordTime);
	}

	public void setRecordTimeStr(String recordTimeStr) {
		this.recordTimeStr = recordTimeStr;
	}

	public Date getsDate() {
		return sDate;
	}

	public void setsDate(Date sDate) {
		this.sDate = sDate;
	}

	public Date geteDate() {
		return eDate;
	}

	public void seteDate(Date eDate) {
		this.eDate = eDate;
	}

	public Integer getIsBanding() {
		return isBanding;
	}

	public void setIsBanding(Integer isBanding) {
		this.isBanding = isBanding;
	}

	public Integer getIsShare() {
		return isShare;
	}

	public void setIsShare(Integer isShare) {
		this.isShare = isShare;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "GetRed [id=" + id + ", redPackageId=" + redPackageId
				+ ", userId=" + userId + ", userName=" + userName + ", phone="
				+ phone + ", denomination=" + denomination + ", recordTime="
				+ recordTime + ", recordTimeStr=" + recordTimeStr + ", sDate="
				+ sDate + ", eDate=" + eDate + ", isBanding=" + isBanding
				+ ", isShare=" + isShare + ", status=" + status + "]";
	}
	
}
