package com.xmn.saas.controller.api.v1.common.vo;

import javax.validation.constraints.NotNull;

import com.xmn.saas.base.Request;

/**
 * H5物料下载请求参数
 * create 2016/10/21
 * @author huangkun
 */

public class DownloadMaterialRequest extends Request{

    /**
     * 
     */
    private static final long serialVersionUID = -1896553093756957303L;
    
    @NotNull(message = "活动id不能为空!")
    private Integer id;

    private Integer sellerId;
    
    /*
     * 主要是区分下载的物料类型
     */
    @NotNull(message = "type不能为空!")
    private Integer type;
    
    /**
     * 以get方式拼接url参数
     */
    public String getParamUrl(String url){
    	//现金券、赠品券url增加商户id
    	if(type==6||type==7){
    		url += sellerId;
    	}
    	return url;
    }
    
	public Integer getSellerId() {
		return sellerId;
	}


	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
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
        return "DownloadMaterialRequest [id = " + id + ",sellerId=" + sellerId + ", type=" + type + "]";
    }

    
}
