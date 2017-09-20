/**
 * 
 */
package com.xmniao.xmn.core.cloud_design.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：MaterialGroup
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： chenJie
 * 
 * 创建时间：2016年12月13日 上午10:58:06 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public class MaterialGroup implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4959736312587512431L;

	private Integer id;
	
	private Integer picSize;
	
	private List<CommonMaterialPics> pics;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<CommonMaterialPics> getPics() {
		return pics;
	}

	public void setPics(List<CommonMaterialPics> pics) {
		this.pics = pics;
	}
	
	public Integer getPicSize() {
		if(pics != null){
			return pics.size();
		}else {
			return 0;
		}
		
	}

	public void setPicSize(Integer picSize) {
		this.picSize = picSize;
	}

	@Override
	public String toString() {
		return "MaterialGroup [id=" + id + ", pics=" + pics + "]";
	}
	
	
}
