package com.xmn.saas.entity.micrograph;

import java.util.Date;

public class MicrographPageShare {
    private Integer id;

    private Integer sellerid;

    private Integer templateShareId;

    private Integer page;

    private Date createTime;

    private String backgroundImage;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSellerid() {
        return sellerid;
    }

    public void setSellerid(Integer sellerid) {
        this.sellerid = sellerid;
    }


    public Integer getTemplateShareId() {
		return templateShareId;
	}

	public void setTemplateShareId(Integer templateShareId) {
		this.templateShareId = templateShareId;
	}

	public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage == null ? null : backgroundImage.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}