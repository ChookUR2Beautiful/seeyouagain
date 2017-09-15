package com.xmniao.xmn.core.xmer.entity;

import java.io.Serializable;

public class XmerPic implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;//图片id
	
	private String name;//图片名称
	
	private String url;//图片地址

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "XmerPic [id=" + id + ", name=" + name + ", url=" + url + "]";
	}
	
}
