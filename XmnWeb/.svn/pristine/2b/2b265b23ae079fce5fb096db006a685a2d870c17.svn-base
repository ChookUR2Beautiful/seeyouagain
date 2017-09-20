package com.xmniao.xmn.core.dataDictionary.entity;

import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TSystemAnnouncement
 * 
 * 类描述： 系统公告实体类
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-12-17 下午4:39:49 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TSystemAnnouncement extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = -3541597912453983440L;

	private Integer id;//公告ID

    private Date sdate;//创建时间

    private Integer status;//0 无效 1有效

    private Integer app;//app类型 ,1 商户端 2 用户端

    private String content;//公告内容

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getApp() {
        return app;
    }

    public void setApp(Integer app) {
        this.app = app;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TSystemAnnouncement [id=" + id + ", sdate=" + sdate
				+ ", status=" + status + ", app=" + app + ", content="
				+ content + "]";
	}
    
    
}