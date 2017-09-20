package com.xmniao.xmn.core.vstar.entity;

import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.DateUtil;

/**
 * 
 * 
 * 项目名称：XmnWeb_vstar
 * 
 * 类名称：TVstarPlayerSetting
 * 
 * 类描述： 星食尚大赛设置表(唯一记录)实体类
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-6-6 下午8:44:20 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TVstarPlayerSetting extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 8961565036443948114L;

	private Integer id;//业务主键

    private Integer autoPassFirst;//是否通过海选审核 0 否 1 是

    private Integer autoPassSecond;//是否自动通过实名审核 0 否 1 是

    private Integer autoRecognition;//是否自动认识并审核实名信息(暂没实现) 0 否 1 是

    private Date enrollStartTime;//报名开始时间
    
    private String enrollStartTimeStr;//报名开始时间

    private Date enrollEndTime;//报名结束时间
    
    private String enrollEndTimeStr;//报名结束时间
    
    private Date contestStartTime;//复赛开始时间
    
    private String contestStartTimeStr;//复赛开始时间
    
    private Date contestEndTime;//复赛结束时间
    
    private String contestEndTimeStr;//复赛结束时间

    private String liveStartTime;//试播开始时间
    
    private String liveEndTime;//试播结束时间
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAutoPassFirst() {
        return autoPassFirst;
    }

    public void setAutoPassFirst(Integer autoPassFirst) {
        this.autoPassFirst = autoPassFirst;
    }

    public Integer getAutoPassSecond() {
        return autoPassSecond;
    }

    public void setAutoPassSecond(Integer autoPassSecond) {
        this.autoPassSecond = autoPassSecond;
    }

    public Integer getAutoRecognition() {
        return autoRecognition;
    }

    public void setAutoRecognition(Integer autoRecognition) {
        this.autoRecognition = autoRecognition;
    }

    public Date getEnrollStartTime() {
        return enrollStartTime;
    }

    public void setEnrollStartTime(Date enrollStartTime) {
        this.enrollStartTime = enrollStartTime;
    }

    public Date getEnrollEndTime() {
        return enrollEndTime;
    }

    public void setEnrollEndTime(Date enrollEndTime) {
        this.enrollEndTime = enrollEndTime;
    }

    public Date getContestStartTime() {
        return contestStartTime;
    }

    public void setContestStartTime(Date contestStartTime) {
        this.contestStartTime = contestStartTime;
    }

    public Date getContestEndTime() {
        return contestEndTime;
    }

    public void setContestEndTime(Date contestEndTime) {
        this.contestEndTime = contestEndTime;
    }

    
    
    

	/**
	 * @return the liveStartTime
	 */
	public String getLiveStartTime() {
		return liveStartTime;
	}

	/**
	 * @param liveStartTime the liveStartTime to set
	 */
	public void setLiveStartTime(String liveStartTime) {
		this.liveStartTime = liveStartTime;
	}

	/**
	 * @return the liveEndTime
	 */
	public String getLiveEndTime() {
		return liveEndTime;
	}

	/**
	 * @param liveEndTime the liveEndTime to set
	 */
	public void setLiveEndTime(String liveEndTime) {
		this.liveEndTime = liveEndTime;
	}

	/**
	 * @return the enrollStartTimeStr
	 */
	public String getEnrollStartTimeStr() {
		if(enrollStartTime==null){
			return null;
		}
		enrollStartTimeStr=DateUtil.formatDate(enrollStartTime, DateUtil.Y_M_D_HM);
		return enrollStartTimeStr;
	}

	/**
	 * @param enrollStartTimeStr the enrollStartTimeStr to set
	 */
	public void setEnrollStartTimeStr(String enrollStartTimeStr) {
		this.enrollStartTimeStr = enrollStartTimeStr;
	}

	/**
	 * @return the enrollEndTimeStr
	 */
	public String getEnrollEndTimeStr() {
		if(enrollEndTime==null){
			return null;
		}
		enrollEndTimeStr=DateUtil.formatDate(enrollEndTime, DateUtil.Y_M_D_HM);
		return enrollEndTimeStr;
	}

	/**
	 * @param enrollEndTimeStr the enrollEndTimeStr to set
	 */
	public void setEnrollEndTimeStr(String enrollEndTimeStr) {
		this.enrollEndTimeStr = enrollEndTimeStr;
	}

	/**
	 * @return the contestStartTimeStr
	 */
	public String getContestStartTimeStr() {
		if(contestStartTime==null){
			return null;
		}
		contestStartTimeStr=DateUtil.formatDate(contestStartTime, DateUtil.Y_M_D_HM);
		return contestStartTimeStr;
	}

	/**
	 * @param contestStartTimeStr the contestStartTimeStr to set
	 */
	public void setContestStartTimeStr(String contestStartTimeStr) {
		this.contestStartTimeStr = contestStartTimeStr;
	}

	/**
	 * @return the contestEndTimeStr
	 */
	public String getContestEndTimeStr() {
		if(contestEndTime==null){
			return null;
		}
		contestEndTimeStr=DateUtil.formatDate(contestEndTime, DateUtil.Y_M_D_HM);
		return contestEndTimeStr;
	}

	/**
	 * @param contestEndTimeStr the contestEndTimeStr to set
	 */
	public void setContestEndTimeStr(String contestEndTimeStr) {
		this.contestEndTimeStr = contestEndTimeStr;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TVstarPlayerSetting [id=" + id + ", autoPassFirst="
				+ autoPassFirst + ", autoPassSecond=" + autoPassSecond
				+ ", autoRecognition=" + autoRecognition + ", enrollStartTime="
				+ enrollStartTime + ", enrollEndTime=" + enrollEndTime
				+ ", contestStartTime=" + contestStartTime
				+ ", contestEndTime=" + contestEndTime + ", liveStartTime="
				+ liveStartTime + ", liveEndTime=" + liveEndTime + "]";
	}
    
    
}