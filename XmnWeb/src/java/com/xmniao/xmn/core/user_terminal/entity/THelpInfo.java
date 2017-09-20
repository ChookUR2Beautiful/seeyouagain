package com.xmniao.xmn.core.user_terminal.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;

public class THelpInfo extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4691165378910003681L;

	private Integer id;

    private Integer itemId;

    private String title;

    private String context;

    private Integer sort;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date udate;

    private String item;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context == null ? null : context.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Date getUdate() {
        return udate;
    }

    public void setUdate(Date udate) {
        this.udate = udate;
    }

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	@Override
	public String toString() {
		return "THelpInfo [id=" + id + ", itemId=" + itemId + ", title="
				+ title + ", context=" + context + ", sort=" + sort
				+ ", udate=" + udate + ", item=" + item + "]";
	}
    
	
}