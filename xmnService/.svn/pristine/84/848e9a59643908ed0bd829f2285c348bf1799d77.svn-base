package com.xmniao.xmn.core.xmer.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 项目名称：xmnService
 * 
 * 类名称：Xmer
 * 
 * 类描述：寻密客
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016-5-19下午8:11:32
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class Xmer implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5123969471613812116L;

	private Integer id;//员工ID

    private Integer uid;//用户编号

    private Integer rtype;//1=个人寻密客,2=企业寻密客

    private String phoneid;//手机号码

    private String email;//邮箱

    private String weixinid;//微信号

    private String achievement;//头衔名称

    private Integer partnerNums;//伙伴数量

    private Integer parentid;//上级ID

    private Integer levels;//级别

    private String levelSwap;//等级分区,字符串等级划分

    private Integer soldNums;//卖出数量
    
    private Date sdate;//加入时间

    private Integer status;//默认0启用  , 1停用
    
    private Integer age;//年龄
    
    private Date update_date;
    
    private Integer	level;//伙伴级别,1:下级   2:下下级
    
    //性别字段，从b_urs_info中关联取出 0未知，1男，2女，默认为1
    private Integer sex;
    
    //真实姓名，从b_urs_info中关联取出 
    private String name = "";
    
    //头像url，从b_urs_info中关联取出
    private String avatar;
    
    //登录名，没设置昵称时，显示登录名(手机号打码)
    private String uname;
    

    public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getName() {
    	return name;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    
    public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "Xmer [id=" + id + ", uid=" + uid + ", rtype=" + rtype + ", phoneid=" + phoneid + ", email=" + email
				+ ", weixinid=" + weixinid + ", achievement=" + achievement + ", partnerNums=" + partnerNums
				+ ", parentid=" + parentid + ", levels=" + levels + ", levelSwap=" + levelSwap + ", soldNums="
				+ soldNums + ", sdate=" + sdate + ", status=" + status + ", age=" + age + ", update_date=" + update_date
				+ ", level=" + level + ", sex=" + sex + ", name=" + name + "]";
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

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


    public Integer getRtype() {
		return rtype;
	}

	public void setRtype(Integer rtype) {
		this.rtype = rtype;
	}

	public String getPhoneid() {
        return phoneid;
    }

    public void setPhoneid(String phoneid) {
        this.phoneid = phoneid == null ? null : phoneid.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getWeixinid() {
        return weixinid;
    }

    public void setWeixinid(String weixinid) {
        this.weixinid = weixinid == null ? null : weixinid.trim();
    }

    public String getAchievement() {
        return achievement;
    }

    public void setAchievement(String achievement) {
        this.achievement = achievement == null ? null : achievement.trim();
    }

    public Integer getPartnerNums() {
        return partnerNums;
    }

    public void setPartnerNums(Integer partnerNums) {
        this.partnerNums = partnerNums;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public Integer getLevels() {
        return levels;
    }

    public void setLevels(Integer levels) {
        this.levels = levels;
    }

    public String getLevelSwap() {
        return levelSwap;
    }

    public void setLevelSwap(String levelSwap) {
        this.levelSwap = levelSwap == null ? null : levelSwap.trim();
    }

    public Integer getSoldNums() {
        return soldNums;
    }

    public void setSoldNums(Integer soldNums) {
        this.soldNums = soldNums;
    }

    public Date getSdate() {
        return sdate;
    }

    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	/**
	 * @return the age
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

}