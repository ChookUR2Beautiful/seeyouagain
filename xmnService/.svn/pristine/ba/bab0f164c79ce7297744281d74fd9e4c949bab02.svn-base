package com.xmniao.xmn.core.common.request;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;

/**
 * 
* @projectName: xmnService 
* @ClassName: RemoveBCRecordRequeset    
* @Description:浏览记录和收藏记录删除请求类   
* @author: liuzhihao   
* @date: 2016年11月25日 下午6:38:49
 */
public class RemoveBCRecordRequeset extends BaseRequest{

	private static final long serialVersionUID = 1L;

	@NotNull(message="商铺或者积分商品ID不能为空")
	private Integer id;//商铺id
	
	@NotNull(message="类型不能为空")
	private Integer type;//与我的分类 1 消费 2 收藏 3 浏览 4口味相似
	
	private Integer status;//类型 0 商铺 1 积分

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
