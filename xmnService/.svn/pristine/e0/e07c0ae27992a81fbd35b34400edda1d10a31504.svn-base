package com.xmniao.xmn.core.integral.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
* 项目名称：xmnService   
* 类名称：BannerEntity   
* 类描述：banner实体类   
* 创建人：liuzhihao   
* 创建时间：2016年6月20日 上午11:31:30   
* @version    
*
 */
@SuppressWarnings("serial")
public class BannerEntity implements Serializable{

	private Integer id;//主键id
	
	private String position;//banner位置信息，INDEX_XXX主页位置
	
	private Integer banner_style;//展示风格。0图片横排一格，1图片横排两格
	
	private String obj_json;//内容,经过Base64编码的JSON串
	
	private Integer status;//上线状态 0.待上线，1.已上线，2.已下线
	
	private String remarks;//banner描述
	
	private Date create_time;//创建时间
	
	private Date update_time;//更新时间
	
	private Integer sort;//排序，数值越大，越优先展示

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Integer getBanner_style() {
		return banner_style;
	}

	public void setBanner_style(Integer banner_style) {
		this.banner_style = banner_style;
	}

	public String getObj_json() {
		return obj_json;
	}

	public void setObj_json(String obj_json) {
		this.obj_json = obj_json;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Override
	public String toString() {
		return "BannerEntity [id=" + id + ", position=" + position
				+ ", banner_style=" + banner_style + ", obj_json=" + obj_json
				+ ", status=" + status + ", remarks=" + remarks
				+ ", create_time=" + create_time + ", update_time="
				+ update_time + ", sort=" + sort + "]";
	}
	
}
