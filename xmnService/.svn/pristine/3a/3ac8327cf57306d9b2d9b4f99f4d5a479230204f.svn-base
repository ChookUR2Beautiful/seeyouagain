package com.xmniao.xmn.core.integral.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
* 项目名称：xmnService   
* 类名称：TradeEntity   
* 类描述：分类表实体类   
* 创建人：liuzhihao   
* 创建时间：2016年6月20日 下午2:30:09   
* @version    
*
 */
@SuppressWarnings("serial")
public class TradeEntity implements Serializable{

	private Integer id;//分类ID
	
	private String name;//分类名称
	
	private Integer fid;//父分类ID默认0 表示没有父级分类
	
	private Integer dstatus;//数据状态 默认0 0正常 1已删除
	
	private Date rdate;//创建时间
	
	private Date udate;//更新时间
	
	private Integer sort;//分类排序 数值越大 排序越靠前
	
	private String show_big_img;//首页分类显示的大图
	
	private String show_small_img;//首页分类显示的小图
	
	private Integer hot;//热门类别

	private Integer type;//跳转类型，1 WAP，2 APP
	
	private String url;//type=1时，输入跳转的wap url地址。 type=2时，seller代表跳转到商户列表页；grow代表跳转到成长记列表；more代表跳转到分类列表。

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public Integer getDstatus() {
		return dstatus;
	}

	public void setDstatus(Integer dstatus) {
		this.dstatus = dstatus;
	}

	public Date getRdate() {
		return rdate;
	}

	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}

	public Date getUdate() {
		return udate;
	}

	public void setUdate(Date udate) {
		this.udate = udate;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getShow_big_img() {
		return show_big_img;
	}

	public void setShow_big_img(String show_big_img) {
		this.show_big_img = show_big_img;
	}

	public String getShow_small_img() {
		return show_small_img;
	}

	public void setShow_small_img(String show_small_img) {
		this.show_small_img = show_small_img;
	}

	public Integer getHot() {
		return hot;
	}

	public void setHot(Integer hot) {
		this.hot = hot;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "TradeEntity [id=" + id + ", name=" + name + ", fid=" + fid
				+ ", dstatus=" + dstatus + ", rdate=" + rdate + ", udate="
				+ udate + ", sort=" + sort + ", show_big_img=" + show_big_img
				+ ", show_small_img=" + show_small_img + ", hot=" + hot
				+ ", type=" + type + ", url=" + url + "]";
	}
	
	
	
}
