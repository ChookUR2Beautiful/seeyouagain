package com.xmn.saas.entity.shop;

/**
 * 
*      
* 类名称：SellerPic   
* 类描述：   商家图片类
* 创建人：xiaoxiong   
* 创建时间：2016年9月28日 上午10:50:00   
* 修改人：xiaoxiong   
* 修改时间：2016年9月28日 上午10:50:00   
* 修改备注：   
* @version    
*
 */
public class SellerPic {
	
	/**
	 * 图片ID
	 */
	private Integer id;
	
	/**
	 * 图片url
	 */
	private String url;
	/**
	 * 图片类型
	 */
	private Integer type;
	
	/**
	 * 商户ID
	 */
	private Integer sellerid;
	
	/**
	 * 修改时间
	 */
	private String udate;
	
	
	public String getUdate() {
		return udate;
	}
	public void setUdate(String udate) {
		this.udate = udate;
	}
	public Integer getSellerid() {
		return sellerid;
	}
	public void setSellerid(Integer sellerid) {
		this.sellerid = sellerid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	

}
