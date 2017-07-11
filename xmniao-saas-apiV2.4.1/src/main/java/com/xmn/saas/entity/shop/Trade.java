package com.xmn.saas.entity.shop;

import java.util.List;

/**
 * 
*      
* 类名称：Trade   
* 类描述：   分类实体类
* 创建人：xiaoxiong   
* 创建时间：2016年9月22日 下午4:17:46   
* 修改人：xiaoxiong   
* 修改时间：2016年9月22日 下午4:17:46   
* 修改备注：   
* @version    
*
 */
public class Trade {
	
	private Integer id;//id
	
	private String name;//分类名称
	
	private List<Trade> genres;//下一级分类
	

	public List<Trade> getGenres() {
		return genres;
	}

	public void setGenres(List<Trade> genres) {
		this.genres = genres;
	}

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
	
	
}
