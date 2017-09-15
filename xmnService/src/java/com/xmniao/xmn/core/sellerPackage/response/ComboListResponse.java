package com.xmniao.xmn.core.sellerPackage.response;

/**
 * 
* @projectName: xmnService 
* @ClassName: ComboResponse    
* @Description:套餐列表返回类   
* @author: liuzhihao   
* @date: 2017年2月20日 上午11:06:09
 */
public class ComboListResponse {

	private Integer comboId;//套餐ID
	
	private String comboTitle;//套餐标题
	
	private String comboPrice;//套餐价格
	
	private String comboCoin;//套餐鸟币价格
	
	private Integer sellerId;//店铺ID
	
	private String sellerName;//店铺名称
	
	private String tradeName;//二级分类名称
	
	private String ranges;//距离
	
	private String zoneName;//商圈名称
	
	private Integer consums;//消费人次
	
	private String comboImage;//套餐封面图
	
	private String comboUrl;//套餐跳转H5地址

	public Integer getComboId() {
		return comboId;
	}

	public void setComboId(Integer comboId) {
		this.comboId = comboId;
	}

	public String getComboTitle() {
		return comboTitle;
	}

	public void setComboTitle(String comboTitle) {
		this.comboTitle = comboTitle;
	}

	public String getComboPrice() {
		return comboPrice;
	}

	public void setComboPrice(String comboPrice) {
		this.comboPrice = comboPrice;
	}

	public String getComboCoin() {
		return comboCoin;
	}

	public void setComboCoin(String comboCoin) {
		this.comboCoin = comboCoin;
	}

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public String getRanges() {
		return ranges;
	}

	public void setRanges(String ranges) {
		this.ranges = ranges;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public Integer getConsums() {
		return consums;
	}

	public void setConsums(Integer consums) {
		this.consums = consums;
	}

	public String getComboImage() {
		return comboImage;
	}

	public void setComboImage(String comboImage) {
		this.comboImage = comboImage;
	}

	public String getComboUrl() {
		return comboUrl;
	}

	public void setComboUrl(String comboUrl) {
		this.comboUrl = comboUrl;
	}

	@Override
	public String toString() {
		return "ComboListResponse [comboId=" + comboId + ", comboTitle=" + comboTitle + ", comboPrice=" + comboPrice
			+ ", comboCoin=" + comboCoin + ", sellerId=" + sellerId + ", sellerName=" + sellerName + ", tradeName=" + tradeName
			+ ", ranges=" + ranges + ", zoneName=" + zoneName + ", consums=" + consums + ", comboImage=" + comboImage
			+ ", comboUrl=" + comboUrl + "]";
	}

	
}
