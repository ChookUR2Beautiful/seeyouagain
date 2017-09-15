package com.xmniao.xmn.core.seller;

/**
 * 
* 类名称：SellerDetailFoodResponse   
* 类描述：  店铺详情推荐查询响应参数
* 创建人：xiaoxiong   
* 创建时间：2017年1月12日 下午2:28:55
 */
public class SellerDetailFoodResponse {
	/**
	 * 主键
	 */
	private long id;
	
	/**
	 * 图片
	 */
	private String url;
	
	/**
	 * 名称
	 */
	private String name;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
