package com.xmniao.xmn.core.coupon.entity;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.StringUtils;
import com.xmniao.xmn.core.util.handler.AreaHandler;

public class TCouponIssue extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private Integer issueId;// 主键
	private Integer cid;// 优惠券Id
	private Double hitRatio;// 摇中概率
	private Integer intHitRatio;// 摇中概率
	private String hitRatioText;// 摇中概率
	private Integer maxTimes;// 最大次数
	private Integer maximum;// 最大数量
	private Integer rate;// 赠送频率，1新用户（首单），2,首满，3每次
	private Integer activityType;// 活动类型：1：摇一摇，2：满就送，3：短信发送
	private String activityName;// 活动名称
	private Date dateStart;// 活动开始时间
	private Date startDateStart;//活动开始时间页面查询条件  开始时间
	private Date endDateStart;//活动开始时间页面查询条件     结束时间  
	private Date dateEnd;// 活动结束时间
	private Integer status;// 启动状态 0:停止 1:启动 2 未启动
	private String message;// 短信内容
	private String remark;// 备注
	private String creator;// 创建人 默认：system
	private Date dateCreated;// 创建时间
	private String updator;// 修改人
	private Date dateUpdated;// 修改时间
	private Integer sendNum;//发送用户数(短信发送专用)
	private String sendObject;//发送对象(短信发送专用)。为空为所有用户，不为空发送给指定用户。{object:[{"用户编号":"1001","用户呢称":"张三"}]}
	private String isSendAll;//是否发送所有用户(短信发送专用)：0：否，1：是
	private Integer startIssueVolume;//start发行总量
	private Integer endIssueVolume;//end发行总量
	private Integer issueVolume;//发行总量
	private String  limitingCondition;//活动限时条件
	private Integer sendStatus;//发送状态(短信发送专用)，0 待发送 1 已发送
	private Date dateSend;//发送时间(短信发送专用)
	private Double amount;//消费金额(分享发放优惠专用)
	private String province;//省编号(分享发放优惠专用)
	private String city;//市编号(分享发放优惠专用)
	private String area;//区域编号，多个用";"隔开，格式  10001;123;1545(分享发放优惠专用)
	private Date startDateSend;//页面查询条件     开始发送时间(短信发送专用)
	private Date endDateSend;//页面查询条件         结束发送时间(短信发送专用)
	private Integer isGenerate;//是否已生成优惠券 0:未生成 1：已生成
	private List<TCouponIssueRef> tCouponIssueRefs;
	private Integer totalVolume;//用于存直接发放时发放总量，本字段只用于直接发放
	private String userIds;//短信发送优惠券，用户编号
	private Long usernum;//短信发送优惠券，发送的用户数
	private String cname;//优惠券名称
	private Double amountStart;
	private Double amountEnd;
	private Integer issueVolumeOrder;//订单后发行量,刮优惠劵使用
	private Integer issueVolumeShare;//分享后发行量,刮优惠劵使用
	private Integer ctype;// 优惠券类型(用于接收sql操作结果和form表单元素)，0本地生活类优惠卷(不可使用积分组合支付) 1生鲜类现金卷(可以和积分组合支付)
	private String ctypeStr;//优惠券类型(用于页面显示)
	

	public Integer getCtype() {
		return ctype;
	}
	public void setCtype(Integer ctype) {
		this.ctype = ctype;
	}
	
	public String getCtypeStr() {
		if(ctype==0) return "消费优惠劵";
		if(ctype==1) return "商城优惠劵";
		if(ctype==5) return "平台通用优惠劵";
		return ctypeStr;
	}
	public void setCtypeStr(String ctypeStr) {
		this.ctypeStr = ctypeStr;
	}
	public String  getStatusText(){
		String statusText;
		if(this.status==0){
			statusText = "停止";
		}else if(this.status==1){
			statusText = "启动";
		}else if(this.status==2){
			statusText = "未启动";
		}else{
			statusText = null;
		}
		return statusText;
	}
	public String getAreaText() {
		StringBuilder sb = new StringBuilder();
		if(StringUtils.hasLength(this.province)){
			sb.append(AreaHandler.getAreaHandler().getAreaIdByTitle(Integer.parseInt(this.province)));
			
		}
		if(StringUtils.hasLength(this.city)){
			sb.append(" - ");
			sb.append(AreaHandler.getAreaHandler().getAreaIdByTitle(Integer.parseInt(this.city)));
			
		}
		if(StringUtils.hasLength(this.area)){
			sb.append(" - ");
			String delim = ",";
			int index = this.area.indexOf(delim);
			if(index==-1){
				delim=";";
			}
			String[] areas = StringUtils.paresToArray(this.area,delim);
			for (int i = 0; i < areas.length; i++) {
				if(i>0){
					sb.append(",");
				}
				sb.append(AreaHandler.getAreaHandler().getAreaIdByTitle(Integer.parseInt(areas[i])));
			}
		}
		return sb.toString();
	}
	
	public Date getStartDateSend() {
		return startDateSend;
	}

	public void setStartDateSend(Date startDateSend) {
		this.startDateSend = startDateSend;
	}

	public Date getEndDateSend() {
		return endDateSend;
	}

	public void setEndDateSend(Date endDateSend) {
		this.endDateSend = endDateSend;
	}

	public Integer getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(Integer sendStatus) {
		this.sendStatus = sendStatus;
	}
	
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getDateSend() {
		return dateSend;
	}

	public void setDateSend(Date dateSend) {
		this.dateSend = dateSend;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public void setHitRatioText(String hitRatioText) {
		this.hitRatioText = hitRatioText;
	}

	public Integer getIsGenerate() {
		return isGenerate;
	}

	public void setIsGenerate(Integer isGenerate) {
		this.isGenerate = isGenerate;
	}

	public Date getStartDateStart() {
		return startDateStart;
	}

	public void setStartDateStart(Date startDateStart) {
		this.startDateStart = startDateStart;
	}

	public Date getEndDateStart() {
		return endDateStart;
	}

	public void setEndDateStart(Date endDateStart) {
		this.endDateStart = endDateStart;
	}

	public Integer getIntHitRatio() {
		return intHitRatio;
	}

	public void setIntHitRatio(Integer intHitRatio) {
		this.intHitRatio = intHitRatio;
	}

	public String getHitRatioText() {
		if(null==hitRatio){
			return "0";
		}	
		return (hitRatio*100)+" %";
	}

	public String getLimitingCondition() {
		String title="";
		String end="";
		boolean flag1=false;
		boolean flag2=false;
		if(maxTimes==null||maxTimes==0){
			 flag1=true;
			 title="";
		}else{
			if(activityType!=null&activityType==1){
				title="每人每天最多摇【"+maxTimes+"】次，";
			}
			if(activityType!=null&activityType==2){
				title="每人每天最多送【"+maxTimes+"】次，";
			} 
		}
		if(maximum==null||maximum==0){
			flag2=true;
			end= "";
		}else{
			end="每人限领【"+maximum+"】次。";
		}
		if(flag1&&flag2){
			return "无限制";
		}
		return title+end;
	}
	public void setLimitingCondition(String limitingCondition) {
		this.limitingCondition = limitingCondition;
	}

	public List<TCouponIssueRef> gettCouponIssueRefs() {
		return tCouponIssueRefs;
	}

	public void settCouponIssueRefs(List<TCouponIssueRef> tCouponIssueRefs) {
		this.tCouponIssueRefs = tCouponIssueRefs;
	}

	public Integer getSendNum() {
		return sendNum;
	}

	public void setSendNum(Integer sendNum) {
		this.sendNum = sendNum;
	}

	public String getSendObject() {
		return sendObject;
	}

	public void setSendObject(String sendObject) {
		this.sendObject = sendObject;
	}

	public String getIsSendAll() {
		return isSendAll;
	}

	public void setIsSendAll(String isSendAll) {
		this.isSendAll = isSendAll;
	}

	public Integer getStartIssueVolume() {
		return startIssueVolume;
	}

	public void setStartIssueVolume(Integer startIssueVolume) {
		this.startIssueVolume = startIssueVolume;
	}

	public Integer getEndIssueVolume() {
		return endIssueVolume;
	}

	public void setEndIssueVolume(Integer endIssueVolume) {
		this.endIssueVolume = endIssueVolume;
	}

	public Integer getIssueVolume() {
		return issueVolume;
	}

	public void setIssueVolume(Integer issueVolume) {
		this.issueVolume = issueVolume;
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

	public Double getHitRatio() {
		return hitRatio;
	}

	public void setHitRatio(Double hitRatio) {
		this.hitRatio = hitRatio;
	}

	public Integer getMaxTimes() {
		return maxTimes;
	}

	public void setMaxTimes(Integer maxTimes) {
		this.maxTimes = maxTimes;
	}

	public Integer getMaximum() {
		return maximum;
	}

	public void setMaximum(Integer maximum) {
		this.maximum = maximum;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public Integer getActivityType() {
		return activityType;
	}

	public void setActivityType(Integer activityType) {
		this.activityType = activityType;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public Integer getTotalVolume() {
		return totalVolume;
	}

	public void setTotalVolume(Integer totalVolume) {
		this.totalVolume = totalVolume;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public Long getUsernum() {
		return usernum;
	}

	public void setUsernum(Long usernum) {
		this.usernum = usernum;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Double getAmountStart() {
		return amountStart;
	}

	public Double getAmountEnd() {
		return amountEnd;
	}

	public void setAmountEnd(Double amountEnd) {
		this.amountEnd = amountEnd;
	}

	public void setAmountStart(Double amountStart) {
		this.amountStart = amountStart;
	}

	public Integer getIssueVolumeOrder() {
		return issueVolumeOrder;
	}

	public void setIssueVolumeOrder(Integer issueVolumeOrder) {
		this.issueVolumeOrder = issueVolumeOrder;
	}

	public Integer getIssueVolumeShare() {
		return issueVolumeShare;
	}

	public void setIssueVolumeShare(Integer issueVolumeShare) {
		this.issueVolumeShare = issueVolumeShare;
	}

	@Override
	public String toString() {
		return "TCouponIssue [issueId=" + issueId + ", cid=" + cid
				+ ", hitRatio=" + hitRatio + ", intHitRatio=" + intHitRatio
				+ ", hitRatioText=" + hitRatioText + ", maxTimes=" + maxTimes
				+ ", maximum=" + maximum + ", rate=" + rate + ", activityType="
				+ activityType + ", activityName=" + activityName
				+ ", dateStart=" + dateStart + ", startDateStart="
				+ startDateStart + ", endDateStart=" + endDateStart
				+ ", dateEnd=" + dateEnd + ", status=" + status + ", message="
				+ message + ", remark=" + remark + ", creator=" + creator
				+ ", dateCreated=" + dateCreated + ", updator=" + updator
				+ ", dateUpdated=" + dateUpdated + ", sendNum=" + sendNum
				+ ", sendObject=" + sendObject + ", isSendAll=" + isSendAll
				+ ", startIssueVolume=" + startIssueVolume
				+ ", endIssueVolume=" + endIssueVolume + ", issueVolume="
				+ issueVolume + ", limitingCondition=" + limitingCondition
				+ ", sendStatus=" + sendStatus + ", dateSend=" + dateSend
				+ ", amount=" + amount + ", province=" + province + ", city="
				+ city + ", area=" + area + ", startDateSend=" + startDateSend
				+ ", endDateSend=" + endDateSend + ", isGenerate=" + isGenerate
				+ ", tCouponIssueRefs=" + tCouponIssueRefs + ", totalVolume="
				+ totalVolume + ", userIds=" + userIds + ", usernum=" + usernum
				+ ", cname=" + cname + "]";
	}

}
