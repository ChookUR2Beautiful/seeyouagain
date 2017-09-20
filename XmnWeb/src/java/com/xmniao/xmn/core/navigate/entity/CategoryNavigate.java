package com.xmniao.xmn.core.navigate.entity;

import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 分类导航实体类 
 * @author wangzhimin
 *
 */
public class CategoryNavigate extends BaseEntity {

	private static final long serialVersionUID = 3082043620228816067L;
	
	private String nId;
	private Integer type;  //1：一级导航   2：二级导航
	private String title;  //导航标题
	private String img_high;//高清图
	private String img_middle;   //中清图
	private String img_low; //低清图
	private String category; //一级分类
	private String genre; //二级分类
	private String add_user;  //创建人
	private Date add_time;  //创建时间
	private String up_user; //修改人
	private Date up_time; //修改时间 
	private Integer order;  //排序 顺序1在最前面
	
	private String category_genre;  //拼装一级和二级分类用于页面显示
	
	private Integer flag;  //判别上移(0表示)或者下移(1表示)
	
	public String getNId() {
		return nId;
	}

	public void setNId(String nId) {
		this.nId = nId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImg_high() {
		return img_high;
	}

	public void setImg_high(String img_high) {
		this.img_high = img_high;
	}

	public String getImg_middle() {
		return img_middle;
	}

	public void setImg_middle(String img_middle) {
		this.img_middle = img_middle;
	}

	public String getImg_low() {
		return img_low;
	}

	public void setImg_low(String img_low) {
		this.img_low = img_low;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getAdd_user() {
		return add_user;
	}

	public void setAdd_user(String add_user) {
		this.add_user = add_user;
	}

	public Date getAdd_time() {
		return add_time;
	}

	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
	}

	public String getUp_user() {
		return up_user;
	}

	public void setUp_user(String up_user) {
		this.up_user = up_user;
	}

	public Date getUp_time() {
		return up_time;
	}

	public void setUp_time(Date up_time) {
		this.up_time = up_time;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}
	
	public String getCategory_genre() {
		return category_genre;
	}

	public void setCategory_genre(String category_genre) {
		this.category_genre = category_genre;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "CategoryNavigate [nId=" + nId + ", type=" + type + ", title="
				+ title + ", img_high=" + img_high + ", img_middle="
				+ img_middle + ", img_low=" + img_low + ", category="
				+ category + ", genre=" + genre + ", add_user=" + add_user
				+ ", add_time=" + add_time + ", up_user=" + up_user
				+ ", up_time=" + up_time + ", order=" + order
				+ ", category_genre=" + category_genre + "]";
	}
}
