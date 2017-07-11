package com.xmn.saas.entity.shop;


/**
 * 
*      
* 类名称：Dishes   
* 类描述：   推荐菜
* 创建人：xiaoxiong   
* 创建时间：2016年9月28日 下午5:43:06   
* 修改人：xiaoxiong   
* 修改时间：2016年9月28日 下午5:43:06   
* 修改备注：   
* @version    
*
 */
public class Dishes {
	
	
	/**
	 * 主键
	 */
	private Integer id;
	
	/**
	 * 图片
	 */
	private String url;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 价格
	 */
	private Double price;
	
	/**
	 * 描述
	 */
	private String remark;
	
	/**
	 * 商家ID
	 */
	private Integer sellerid;
	
	/**
	 * 状态
	 */
	private Integer datastatus;
	
	/**
	 * 是否为广告位
	 */
	private Integer atag;
	
	/**
	 * 添加时间
	 */
	private String sdate;
	
	/**
	 * 修改时间
	 */
	private String pdate;
	
	/**
	 * 数据来源:1商户APP，2商户PC端，3业务系统，4其他',
	 */
	private Integer source;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getSellerid() {
		return sellerid;
	}

	public void setSellerid(Integer sellerid) {
		this.sellerid = sellerid;
	}

	public Integer getDatastatus() {
		return datastatus;
	}

	public void setDatastatus(Integer datastatus) {
		this.datastatus = datastatus;
	}

	public Integer getAtag() {
		return atag;
	}

	public void setAtag(Integer atag) {
		this.atag = atag;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public String getPdate() {
		return pdate;
	}

	public void setPdate(String pdate) {
		this.pdate = pdate;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	
	

}
