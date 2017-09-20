/**   
 * 文件名：BillRecord.java   
 *    
 * 日期：2014年11月25日17时39分38秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司     
 */
package com.xmniao.xmn.core.marketingmanagement.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.NumberUtil;
import com.xmniao.xmn.core.util.StringUtils;
import com.xmniao.xmn.core.util.handler.AreaHandler;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：bargainProduct
 * 
 * 类描述：爆品管理
 * 
 * 创建人： caoyingde
 * 
 * 创建时间：2015年06月12日12时39分38秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class BargainProduct extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer bpid;
	private Integer bid;//爆品区域ID
	private String activityname;//活动名称
	private Integer sellerid;//关联商家ID
	private String pname;//产品名称
	private Integer user;//针对用户，1新用户，2老用户，3所有用户
	private BigDecimal originalprice;//原价
	private BigDecimal price;//产品价格
	private Integer quota;//是否限额，0不限制，1限制
	private String quotaText;
	private Integer quotanum;//限制数量，不限制填0
	private Integer salenum;//已销售数量
	private Date startdate;//开始日期
	private Date enddate;//结束日期
	private String image;//产品图片
	private String refund;//是否可以退款，0不可以，1可以
	private Integer order;//排序，倒序
	private Integer status;//状态，0下架，1上架，2审核中，3不通过
	private Long adduser;//添加用户ID
	private Date addtime;//添加时间
	private Long updateuser;//最后修改用户ID
	private Date updatetime;//最后修改时间
	private Integer isrebate;//是否支持返利. 0：否，1：是
	private String sellername;
	private String address;
	private String province;
	private String city;
	private String area;
	private String provinceText;
	private BigDecimal purchasePrice;	//采购价
	private Integer everyday;
	private String Html;
	private List<TBargainPrice> bargainPrice;// 爆品价格
	/**
	 * 新增属性 add by hls
	 */
	private BigDecimal cash;//现金支付
	private BigDecimal integral;//积分支付
	
	public BigDecimal getCash() {
		return cash;
	}
	public void setCash(BigDecimal cash) {
		this.cash = cash;
	}
	public BigDecimal getIntegral() {
		return integral;
	}
	public void setIntegral(BigDecimal integral) {
		this.integral = integral;
	}
	public Integer getBpid() {
		return bpid;
	}
	public void setBpid(Integer bpid) {
		this.bpid = bpid;
	}
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}
	public String getActivityname() {
		return activityname;
	}
	public void setActivityname(String activityname) {
		this.activityname = activityname;
	}
	public Integer getSellerid() {
		return sellerid;
	}
	public void setSellerid(Integer sellerid) {
		this.sellerid = sellerid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public Integer getUser() {
		return user;
	}
	public void setUser(Integer user) {
		this.user = user;
	}
	public BigDecimal getOriginalprice() {
		if(originalprice==null){
			return null;
		}else{
			return originalprice = new BigDecimal(NumberUtil.getNumberFixedpoint(originalprice, 0));
		}
	}
	public void setOriginalprice(BigDecimal originalprice) {
		this.originalprice = originalprice;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Integer getQuota() {
		return quota;
	}
	public void setQuota(Integer quota) {
		this.quota = quota;
	}
	
	public String getQuotaText() {
		quotaText = "-";
		if(quota!=null){
			if(quota == 0){
				quotaText = "不限制";
			}
			if(quota == 1){
				quotaText = "限制";
			}
		}
		return quotaText;
	}
	public void setQuotaText(String quotaText) {
		this.quotaText = quotaText;
	}
	public Integer getQuotanum() {
		return quotanum;
	}
	public void setQuotanum(Integer quotanum) {
		this.quotanum = quotanum;
	}
	public Integer getSalenum() {
		return salenum;
	}
	public void setSalenum(Integer salenum) {
		this.salenum = salenum;
	}
	@JSONField(format = "yyyy-MM-dd")
	public Date getStartdate() {
		return startdate;
	}
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	@JSONField(format = "yyyy-MM-dd")
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getRefund() {
		return refund;
	}
	public void setRefund(String refund) {
		this.refund = refund;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	public Integer getIsrebate() {
		return isrebate;
	}
	public void setIsrebate(Integer isrebate) {
		this.isrebate = isrebate;
	}
	public String getSellername() {
		return sellername;
	}
	public void setSellername(String sellername) {
		this.sellername = sellername;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getProvinceText() {
		if(StringUtils.hasLength(province)){
			province = AreaHandler.getAreaHandler().getAreaIdByTitle(Integer.parseInt(province));
		}
		if(StringUtils.hasLength(city)){
			city = AreaHandler.getAreaHandler().getAreaIdByTitle(Integer.parseInt(city));
		}
		if(StringUtils.hasLength(area)){
			area = AreaHandler.getAreaHandler().getAreaIdByTitle(Integer.parseInt(area));
		}
		provinceText = province+"-"+city +"-"+area;
		return provinceText;
	}
	public void setProvinceText(String provinceText) {
		this.provinceText = provinceText;
	}
	public List<TBargainPrice> getBargainPrice() {
		return bargainPrice;
	}
	public void setBargainPrice(List<TBargainPrice> bargainPrice) {
		this.bargainPrice = bargainPrice;
	}
	public Long getAdduser() {
		return adduser;
	}
	public void setAdduser(Long adduser) {
		this.adduser = adduser;
	}
	public Long getUpdateuser() {
		return updateuser;
	}
	public void setUpdateuser(Long updateuser) {
		this.updateuser = updateuser;
	}
	public BigDecimal getPurchasePrice() {
		if(purchasePrice==null){
			return purchasePrice;
		}else{
			return purchasePrice = new BigDecimal(NumberUtil.getNumberFixedpoint(purchasePrice, 0));
		}
	}
	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	public Integer getEveryday() {
		return everyday;
	}
	public void setEveryday(Integer everyday) {
		this.everyday = everyday;
	}
	public String getHtml() {
		return Html;
	}
	public void setHtml(String html) {
		Html = html;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BargainProduct [bpid=" + bpid + ", bid=" + bid
				+ ", activityname=" + activityname + ", sellerid=" + sellerid
				+ ", pname=" + pname + ", user=" + user + ", originalprice="
				+ originalprice + ", price=" + price + ", quota=" + quota
				+ ", quotaText=" + quotaText + ", quotanum=" + quotanum
				+ ", salenum=" + salenum + ", startdate=" + startdate
				+ ", enddate=" + enddate + ", image=" + image + ", refund="
				+ refund + ", order=" + order + ", status=" + status
				+ ", adduser=" + adduser + ", addtime=" + addtime
				+ ", updateuser=" + updateuser + ", updatetime=" + updatetime
				+ ", isrebate=" + isrebate + ", sellername=" + sellername
				+ ", address=" + address + ", province=" + province + ", city="
				+ city + ", area=" + area + ", provinceText=" + provinceText
				+ ", purchasePrice=" + purchasePrice + ", everyday=" + everyday
				+ ", Html=" + Html + ", bargainPrice=" + bargainPrice
				+ ", cash=" + cash + ", integral=" + integral + "]";
	}
	
	
	
	
	
}
