package com.xmn.saas.entity.micrograph;

import java.util.Date;
import java.util.List;

public class MicrographPage {
    private Integer id;

    private Integer templateId;

    private Integer page;

    private Date createTime;

    private Date updateTime;

    private String backgroundImage;

    private Integer status;
    
    private List<MicrographModule> micrographModule;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

	public List<MicrographModule> getMicrographModule() {
		return micrographModule;
	}

	public void setMicrographModule(List<MicrographModule> micrographModule) {
		this.micrographModule = micrographModule;
	}
    
    
}