package com.xmn.saas.controller.api.v1.common.vo;

import com.xmn.saas.base.Request;

import javax.validation.constraints.NotNull;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * create 2016/09/23
 *
 * @author yangQiang
 */

public class ShareRequest extends Request{

    /**
     * 
     */
    private static final long serialVersionUID = -1896553093756957303L;

    @NotNull(message = "id不能为空!")
    private Integer id;
    
    @NotNull(message = "type不能为空!")
    private Integer type;
    
    @NotNull(message = "title不能为空!")
    private String title;
    //描述文字
    private String desc;
    
    private Integer awardType;
    
    
    
    public String getDesc() {
        try {
            return URLEncoder.encode(desc,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTitle() {
        try {
            return URLEncoder.encode(title,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAwardType() {
		return awardType;
	}

	public void setAwardType(Integer awardType) {
		this.awardType = awardType;
	}

	public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ShareRequest [id=" + id + ", type=" + type + "]";
    }

    
}
