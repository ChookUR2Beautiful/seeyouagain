package com.xmniao.xmn.core.live_anchor.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.xmniao.xmn.core.base.BaseEntity;

public class TExperienceofficerActivity extends BaseEntity{
	
    private Integer id;

    private Integer recordId;

    private Integer sellerid;

    private String sellername;

    private Integer limitNum;
    
    /*修改前的名额：修改场次信息时*/
    private Integer oldLimitNum;

    private Integer remainderNum;
    
    private Date enrollTime;

    private Integer activityState;

    private Date createTime;

    private String description;

    private String zhiboTitle;	//通告标题

//    private List<TExperienceofficerEnrollRecord> enrollSuccessInfoList;	//报名成功信息
        
    private Date planStartDate;	//体验时间-预计开播时间-报名结束时间
        
    private String zhiboAddress;
    
    private String userInfo;
    
    private Date now;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getSellerid() {
        return sellerid;
    }

    public void setSellerid(Integer sellerid) {
        this.sellerid = sellerid;
    }

    public String getSellername() {
        return sellername;
    }

    public void setSellername(String sellername) {
        this.sellername = sellername == null ? null : sellername.trim();
    }

    public Integer getLimitNum() {
        return limitNum;
    }

    public void setLimitNum(Integer limitNum) {
        this.limitNum = limitNum;
    }

    public Date getEnrollTime() {
        return enrollTime;
    }

    public void setEnrollTime(Date enrollTime) {
        this.enrollTime = enrollTime;
    }

    public Integer getActivityState() {
        return activityState;
    }

    public void setActivityState(Integer activityState) {
        this.activityState = activityState;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

	public String getZhiboTitle() {
		return zhiboTitle;
	}

	public void setZhiboTitle(String zhiboTitle) {
		this.zhiboTitle = zhiboTitle;
	}


	public Date getPlanStartDate() {
		return planStartDate;
	}

	public void setPlanStartDate(Date planStartDate) {
		this.planStartDate = planStartDate;
	}

	
	public String getZhiboAddress() {
		return zhiboAddress;
	}

	public void setZhiboAddress(String zhiboAddress) {
		this.zhiboAddress = zhiboAddress;
	}

//	public List<TExperienceofficerEnrollRecord> getEnrollSuccessInfoList() {
//		return enrollSuccessInfoList;
//	}

	public Integer getOldLimitNum() {
		return oldLimitNum;
	}

	public void setOldLimitNum(Integer oldLimitNum) {
		this.oldLimitNum = oldLimitNum;
	}

	public Integer getRemainderNum() {
		return remainderNum;
	}

	public void setRemainderNum(Integer remainderNum) {
		this.remainderNum = remainderNum;
	}

//	public void setEnrollSuccessInfoList(
//			List<TExperienceofficerEnrollRecord> enrollSuccessInfoList) {
//		this.enrollSuccessInfoList = enrollSuccessInfoList;
//	}
	
	public String getPlanStartDateStr(){
		String str ="";
		if(this.planStartDate  != null){
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 str=df.format(this.planStartDate);
		}
		return str;
	}
    public String getEnrollTimeStr(){
		String str ="";
		if(this.enrollTime  != null){
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 str=df.format(this.enrollTime);
		}
		return str;
    }
    
    public String getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}

	public Date getNow() {
		return now;
	}

	public void setNow(Date now) {
		this.now = now;
	}

	public String getActivityStateStr(){
    	if(activityState !=null){
			if(activityState==2){
				return "已取消";
			}else if(activityState==1){
				Date now = new Date();
				if(planStartDate==null){
					return "状态异常";
				}
				if(planStartDate.before(now)){
					return "已结束";
				}else {
					if(enrollTime.after(now)){
						return "未开始";
					}else {
						if(remainderNum<=0){
							return "已售罄";
						}else{
							return "抢位中";
						}
					}
				}
			}
    	}
		return "未知状态";
    }
}