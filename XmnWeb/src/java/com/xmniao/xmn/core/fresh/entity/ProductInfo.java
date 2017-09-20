package com.xmniao.xmn.core.fresh.entity;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：ProductInfo
 * 
 * 类描述： 生鲜产品信息实体类
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年1月5日 上午10:45:53
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class ProductInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;

	// 产品主键ID
	private Integer pid;
	// 产品唯一标识编号，由程序生成
	private Long codeId;
	// 产品执行标准
	private String standard;
	// 产品的具体名称
	private String pname;
	// 商品名称 产品的售卖促销名称
	private String goodsName;
	// 1级分类编号 家禽 海鲜 水果 蔬菜
	private Integer classa;
	private String classaVal;//分类导入值	20001|美食,20002|食品,20003|美妆,20004|百货
	// 2级类编号
	private Integer secondary;
	// 产品原单品价格
	private Double price;
	// 产品优惠后价格
	private Double discount;
	// 产品配料信息
	private String batching;
	// 产品重量 如500克
	private String weight;
	// 产品净重,去包装后的重量 如100克
	private String suttle;
	// 保质期 7天 1年等，最大值365
	private Integer quality;
	// 是否有添加剂 默认0 0无添加 1有添加
	private Integer isAdditives;
	private String isAdditivesVal;
	private String isAdditivesStr;
	// 包装方式 散装或包装
	private String packing;
	// 生产日期
	private Date production;
	// 品牌名称
	private String brandName;
	// 产品状态默认0 0待上线 1已上线 2已售罄 3已下线
	private Integer pstatus;
	// 如冷冻水产或熟食加工等
	private String crafts;
	// 如单品或套餐等
	private String sellType;
	// 如一人份或5人份
	private String deal;
	// 产品原产地如中国 智利等
	private String place;
	// 如广东 山东
	private String province;
	// 如广州 青岛
	private String city;
	// 如冷藏或-18℃冷冻
	private String stored;
	// 库存产品总数
	private Integer store;
	// 已销售总数
	private Integer sales;
	// 是否精选 默认0  0不是精选， 1是精选
	private Integer choice;
	private String choiceVal;
	private String choiceStr;
	//热气热卖排序
	private Integer choiceSort;//热卖排序 默认为0 数值越高，排序越靠前
	// 生产厂名
	private String cname;
	// 生产厂家地址
	private String address;
	// 生产许可证编号
	private String licenseId;
	// 厂家联系电话
	private String tel;
	// 创建时间
	private Date rdate;
	// 更新时间
	private Date udate;
	// 数据状态 默认0 0正常 1已删除
	private Integer dstatus;
	// 产品排序 数值越大 排序越靠前
	private Integer sort;
	// 供应商ID
	private Integer supplierId;
	// 供应商名称
	private String supplierName;
	// 产品列表缩略图，相对路径
	private String salesInfo;
	// 产品信息 如冬季进补 营养美味
	private String breviary;
	// 可否使用积分支付
	private Integer isIntegral;
	// 查找价格区间，最小值
	private Double minPrice = 0.00;
	// 查找价格区间，最大值
	private Double maxPrice = 0.00;
	// 生产日期string
	private String productionStr;
	// 一级分类String
	private String classaStr;
	// 二级分类String
	private String secondaryStr;

	// 城市名
	private String cityStr;

	// 省、直辖市名
	private String provinceStr;

	/**
	 * add by lifeng
	 */
	// 产品类型 
	private Integer saleType; // 0 线上 1 线下
	// 产品采购价
	private BigDecimal purchasePrice;
	// 积分支付金额
	private BigDecimal integral;
	//现金支付金额
	private BigDecimal cash;
	private TSupplier tSupplier;
	//供应商联系人 
	private String contacts;
	//供应商联系电话
	private String supplierPhone;
	//供应商地址
	private String supplierAddress;
	//配送方式 0快递 1虚拟物品 2到店自提 3兑换码
	private Integer deliveryType;
	private String deliveryTypeVal;//excel导入值 ,格式code|value
	private String deliveryTypeStr;  //excel导出值
	//是否支持退款 0 否 1 是
	private Boolean allowRefund;
	private String allowRefundVal;
	private String allowRefundStr;
	//快递计重,单位(kg)
	private Double expWeight;
	//运费模板ID
	private Integer expTid;
	private String deliveryCity;//配送城市
	private String strDeliveryCity;
	private String notDeliveryCity;//不配送城市
	private String strNotDeliveryCity;
	private String saleCity;//'销售城市
	private String strSaleCity;
	private String notSaleCity;//不销售城市
	private String strNotSaleCity;
	private String goodsSerial;//产品序号
	private String barcode;//条形码
	//
	private String expTitle;	//运费模板
	private String ids;// 前台id集合
	private Object[] array;// 批量跟新时id集合
	
	
	//产品详细信息
	private String delivery;//配送描述
	private String servicenote;//服务描述
	private String postnote;//包邮描述
	private String remarks;//备注
	private String comments;//导入产品失败提示信息
	
	//用于接收产品的属性和属性值，加价、库存：add by lifeng 20160806 16:14:55
	private List<TSaleProperty> propertyList;//属性
	private List<String> propList;//属性名和属性值的拼接集合
	private List<String> propValList;//属性值集合,例如:"iphone7s,土豪金,5.5寸,128g"...
	private List<TSaleGroup> saleGroupList;//商品销售分组
	private Integer brandId;	//品牌id
	private String prpoName;	//导入分类名称
	private String groupName;  //导入规格名称
	private BigDecimal amount; //规格加价
	private Integer stock;	//规格库存
	private ProductDetail productDetail; 
	
	public ProductDetail getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(ProductDetail productDetail) {
		this.productDetail = productDetail;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getPrpoName() {
		return prpoName;
	}

	public void setPrpoName(String prpoName) {
		this.prpoName = prpoName;
	}

	public String getIsAdditivesStr() {
		if(isAdditives!=null){
			return isAdditives==0?"否":"是";
		}
		return isAdditivesStr;
	}

	public void setIsAdditivesStr(String isAdditivesStr) {
		this.isAdditivesStr = isAdditivesStr;
	}

	public String getChoiceStr() {
		if(choice!=null){
			return choice==0?"否":"是";
		}
		return choiceStr;
	}

	public void setChoiceStr(String choiceStr) {
		this.choiceStr = choiceStr;
	}

	public String getAllowRefundStr() {
		if(this.allowRefund!=null){
			return this.allowRefund?"否":"是";
		}
		return allowRefundStr;
	}

	public void setAllowRefundStr(String allowRefundStr) {
		this.allowRefundStr = allowRefundStr;
	}

	public String getDeliveryTypeStr() {
		if(this.deliveryType!=null){
			switch (deliveryType) {
			case 0:
				return deliveryType+"|快递";
			case 1:
				return deliveryType+"|虚拟物品";
			case 2:
				return deliveryType+"|到店自提";
			case 3:
				return deliveryType+"|兑换码";
			}
		}
		return deliveryTypeStr;
	}

	public void setDeliveryTypeStr(String deliveryTypeStr) {
		this.deliveryTypeStr = deliveryTypeStr;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Long getCodeId() {
		return codeId;
	}

	public void setCodeId(Long codeId) {
		this.codeId = codeId;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard == null ? null : standard.trim();
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname == null ? null : pname.trim();
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName == null ? null : goodsName.trim();
	}

	public Integer getClassa() {
		return classa;
	}

	public void setClassa(Integer classa) {
		this.classa = classa;
	}

	public Integer getSecondary() {
		return secondary;
	}

	public void setSecondary(Integer secondary) {
		this.secondary = secondary;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getBatching() {
		return batching;
	}

	public void setBatching(String batching) {
		this.batching = batching == null ? null : batching.trim();
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight == null ? null : weight.trim();
	}

	public String getSuttle() {
		return suttle;
	}

	public void setSuttle(String suttle) {
		this.suttle = suttle == null ? null : suttle.trim();
	}

	public Integer getQuality() {
		return quality;
	}

	public void setQuality(Integer quality) {
		this.quality = quality;
	}

	public Integer getIsAdditives() {
		return isAdditives;
	}

	public void setIsAdditives(Integer isAdditives) {
		this.isAdditives = isAdditives;
	}

	public String getPacking() {
		return packing;
	}

	public void setPacking(String packing) {
		this.packing = packing == null ? null : packing.trim();
	}

	public Date getProduction() {
		return production;
	}

	public void setProduction(Date production) {
		this.production = production;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName == null ? null : brandName.trim();
	}

	public Integer getPstatus() {
		return pstatus;
	}

	public void setPstatus(Integer pstatus) {
		this.pstatus = pstatus;
	}

	public String getCrafts() {
		return crafts;
	}

	public void setCrafts(String crafts) {
		this.crafts = crafts == null ? null : crafts.trim();
	}

	public String getSellType() {
		return sellType;
	}

	public void setSellType(String sellType) {
		this.sellType = sellType == null ? null : sellType.trim();
	}

	public String getDeal() {
		return deal;
	}

	public void setDeal(String deal) {
		this.deal = deal == null ? null : deal.trim();
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place == null ? null : place.trim();
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province == null ? null : province.trim();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city == null ? null : city.trim();
	}

	public String getStored() {
		return stored;
	}

	public void setStored(String stored) {
		this.stored = stored == null ? null : stored.trim();
	}

	public Integer getStore() {
		return store;
	}

	public void setStore(Integer store) {
		this.store = store;
	}

	public Integer getSales() {
		return sales;
	}

	public void setSales(Integer sales) {
		this.sales = sales;
	}

	public Integer getChoice() {
		return choice;
	}

	public void setChoice(Integer choice) {
		this.choice = choice;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname == null ? null : cname.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public String getLicenseId() {
		return licenseId;
	}

	public void setLicenseId(String licenseId) {
		this.licenseId = licenseId == null ? null : licenseId.trim();
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel == null ? null : tel.trim();
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

	public Integer getDstatus() {
		return dstatus;
	}

	public void setDstatus(Integer dstatus) {
		this.dstatus = dstatus;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName == null ? null : supplierName.trim();
	}

	public String getSalesInfo() {
		return salesInfo;
	}

	public void setSalesInfo(String salesInfo) {
		this.salesInfo = salesInfo;
	}

	public String getBreviary() {
		return breviary;
	}

	public void setBreviary(String breviary) {
		this.breviary = breviary;
	}

	public Integer getIsIntegral() {
		return isIntegral;
	}

	public void setIsIntegral(Integer isIntegral) {
		this.isIntegral = isIntegral;
	}

	public Double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}

	public Double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}

	/*
	 * 日期格式化
	 */
	public String getProductionStr() {
		productionStr = "";
		if (production != null) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			this.productionStr = df.format(this.production);
		}
		return productionStr;
	}

	public void setProductionStr(String productionStr) {
		this.productionStr = productionStr;
	}

	public String getClassaStr() {
		return classaStr;
	}

	public void setClassaStr(String classaStr) {
		this.classaStr = classaStr;
	}

	public String getSecondaryStr() {
		return secondaryStr;
	}

	public void setSecondaryStr(String secondaryStr) {
		this.secondaryStr = secondaryStr;
	}

	public String getCityStr() {
		return cityStr;
	}

	public void setCityStr(String cityStr) {
		this.cityStr = cityStr;
	}

	public String getProvinceStr() {
		return provinceStr;
	}

	public void setProvinceStr(String provinceStr) {
		this.provinceStr = provinceStr;
	}

	public String getCityName() {
		return provinceStr + "-" + cityStr;
	}

	public Integer getSaleType() {
		return saleType;
	}

	public void setSaleType(Integer saleType) {
		this.saleType = saleType;
	}

	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public BigDecimal getIntegral() {
		return integral;
	}

	public void setIntegral(BigDecimal integral) {
		this.integral = integral;
	}

	public BigDecimal getCash() {
		return cash;
	}

	public void setCash(BigDecimal cash) {
		this.cash = cash;
	}

	public TSupplier gettSupplier() {
		return tSupplier;
	}

	public void settSupplier(TSupplier tSupplier) {
		this.tSupplier = tSupplier;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getSupplierPhone() {
		return supplierPhone;
	}

	public void setSupplierPhone(String supplierPhone) {
		this.supplierPhone = supplierPhone;
	}

	public String getSupplierAddress() {
		return supplierAddress;
	}

	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}
	
	public Integer getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(Integer deliveryType) {
		this.deliveryType = deliveryType;
	}

	public Boolean getAllowRefund() {
		return allowRefund;
	}

	public void setAllowRefund(Boolean allowRefund) {
		this.allowRefund = allowRefund;
	}

	public Double getExpWeight() {
		return expWeight;
	}

	public void setExpWeight(Double expWeight) {
		this.expWeight = expWeight;
	}

	public Integer getExpTid() {
		return expTid;
	}

	public void setExpTid(Integer expTid) {
		this.expTid = expTid;
	}

	public String getDeliveryCity() {
		return deliveryCity;
	}

	public void setDeliveryCity(String deliveryCity) {
		this.deliveryCity = deliveryCity;
	}

	public String getNotDeliveryCity() {
		return notDeliveryCity;
	}

	public void setNotDeliveryCity(String notDeliveryCity) {
		this.notDeliveryCity = notDeliveryCity;
	}

	public String getSaleCity() {
		return saleCity;
	}

	public void setSaleCity(String saleCity) {
		this.saleCity = saleCity;
	}

	public String getNotSaleCity() {
		return notSaleCity;
	}

	public void setNotSaleCity(String notSaleCity) {
		this.notSaleCity = notSaleCity;
	}

	public String getStrDeliveryCity() {
		return strDeliveryCity;
	}

	public void setStrDeliveryCity(String strDeliveryCity) {
		this.strDeliveryCity = strDeliveryCity;
	}

	public String getStrNotDeliveryCity() {
		return strNotDeliveryCity;
	}

	public void setStrNotDeliveryCity(String strNotDeliveryCity) {
		this.strNotDeliveryCity = strNotDeliveryCity;
	}

	public String getStrSaleCity() {
		return strSaleCity;
	}

	public void setStrSaleCity(String strSaleCity) {
		this.strSaleCity = strSaleCity;
	}

	public String getStrNotSaleCity() {
		return strNotSaleCity;
	}

	public void setStrNotSaleCity(String strNotSaleCity) {
		this.strNotSaleCity = strNotSaleCity;
	}

	public String getGoodsSerial() {
		return goodsSerial;
	}

	public void setGoodsSerial(String goodsSerial) {
		this.goodsSerial = goodsSerial;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getExpTitle() {
		return expTitle;
	}

	public void setExpTitle(String expTitle) {
		this.expTitle = expTitle;
	}
	//组合价如：35现金+15积分
	public String getCombinePrice() {
		return (cash==null?"0":cash)+"现金+"+(integral==null?"0":integral)+"积分";
	}
	
	
	
	/**
	 * @return the classaVal
	 */
	public String getClassaVal() {
		return classaVal;
	}

	/**
	 * @param classaVal the classaVal to set
	 */
	public void setClassaVal(String classaVal) {
		this.classaVal = classaVal;
	}

	/**
	 * @return the isAdditivesVal
	 */
	public String getIsAdditivesVal() {
		return isAdditivesVal;
	}

	/**
	 * @param isAdditivesVal the isAdditivesVal to set
	 */
	public void setIsAdditivesVal(String isAdditivesVal) {
		this.isAdditivesVal = isAdditivesVal;
	}

	/**
	 * @return the choiceVal
	 */
	public String getChoiceVal() {
		return choiceVal;
	}

	/**
	 * @param choiceVal the choiceVal to set
	 */
	public void setChoiceVal(String choiceVal) {
		this.choiceVal = choiceVal;
	}

	/**
	 * @return the deliveryTypeVal
	 */
	public String getDeliveryTypeVal() {
		return deliveryTypeVal;
	}

	/**
	 * @param deliveryTypeVal the deliveryTypeVal to set
	 */
	public void setDeliveryTypeVal(String deliveryTypeVal) {
		this.deliveryTypeVal = deliveryTypeVal;
	}

	/**
	 * @return the allowRefundVal
	 */
	public String getAllowRefundVal() {
		return allowRefundVal;
	}

	/**
	 * @param allowRefundVal the allowRefundVal to set
	 */
	public void setAllowRefundVal(String allowRefundVal) {
		this.allowRefundVal = allowRefundVal;
	}

	/**
	 * @return the delivery
	 */
	public String getDelivery() {
		return delivery;
	}

	/**
	 * @param delivery the delivery to set
	 */
	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}

	/**
	 * @return the servicenote
	 */
	public String getServicenote() {
		return servicenote;
	}

	/**
	 * @param servicenote the servicenote to set
	 */
	public void setServicenote(String servicenote) {
		this.servicenote = servicenote;
	}

	/**
	 * @return the postnote
	 */
	public String getPostnote() {
		return postnote;
	}

	/**
	 * @param postnote the postnote to set
	 */
	public void setPostnote(String postnote) {
		this.postnote = postnote;
	}

	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Object[] getArray() {
		return array;
	}

	public void setArray(Object[] array) {
		this.array = array;
	}

	public List<TSaleProperty> getPropertyList() {
		return propertyList;
	}

	public void setPropertyList(List<TSaleProperty> propertyList) {
		this.propertyList = propertyList;
	}

	public List<String> getPropList() {
		return propList;
	}

	public void setPropList(List<String> propList) {
		this.propList = propList;
	}

	public List<String> getPropValList() {
		return propValList;
	}

	public void setPropValList(List<String> propValList) {
		this.propValList = propValList;
	}

	public List<TSaleGroup> getSaleGroupList() {
		return saleGroupList;
	}

	public void setSaleGroupList(List<TSaleGroup> saleGroupList) {
		this.saleGroupList = saleGroupList;
	}

	public Integer getChoiceSort() {
		return choiceSort;
	}

	public void setChoiceSort(Integer choiceSort) {
		this.choiceSort = choiceSort;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProductInfo [pid=" + pid + ", codeId=" + codeId + ", standard="
				+ standard + ", pname=" + pname + ", goodsName=" + goodsName
				+ ", classa=" + classa + ", classaVal=" + classaVal
				+ ", secondary=" + secondary + ", price=" + price
				+ ", discount=" + discount + ", batching=" + batching
				+ ", weight=" + weight + ", suttle=" + suttle + ", quality="
				+ quality + ", isAdditives=" + isAdditives
				+ ", isAdditivesVal=" + isAdditivesVal + ", packing=" + packing
				+ ", production=" + production + ", brandName=" + brandName
				+ ", pstatus=" + pstatus + ", crafts=" + crafts + ", sellType="
				+ sellType + ", deal=" + deal + ", place=" + place
				+ ", province=" + province + ", city=" + city + ", stored="
				+ stored + ", store=" + store + ", sales=" + sales
				+ ", choice=" + choice + ", choiceVal=" + choiceVal
				+ ", choiceSort=" + choiceSort + ", cname=" + cname
				+ ", address=" + address + ", licenseId=" + licenseId
				+ ", tel=" + tel + ", rdate=" + rdate + ", udate=" + udate
				+ ", dstatus=" + dstatus + ", sort=" + sort + ", supplierId="
				+ supplierId + ", supplierName=" + supplierName
				+ ", salesInfo=" + salesInfo + ", breviary=" + breviary
				+ ", isIntegral=" + isIntegral + ", minPrice=" + minPrice
				+ ", maxPrice=" + maxPrice + ", productionStr=" + productionStr
				+ ", classaStr=" + classaStr + ", secondaryStr=" + secondaryStr
				+ ", cityStr=" + cityStr + ", provinceStr=" + provinceStr
				+ ", saleType=" + saleType + ", purchasePrice=" + purchasePrice
				+ ", integral=" + integral + ", cash=" + cash + ", tSupplier="
				+ tSupplier + ", contacts=" + contacts + ", supplierPhone="
				+ supplierPhone + ", supplierAddress=" + supplierAddress
				+ ", deliveryType=" + deliveryType + ", deliveryTypeVal="
				+ deliveryTypeVal + ", allowRefund=" + allowRefund
				+ ", allowRefundVal=" + allowRefundVal + ", expWeight="
				+ expWeight + ", expTid=" + expTid + ", deliveryCity="
				+ deliveryCity + ", strDeliveryCity=" + strDeliveryCity
				+ ", notDeliveryCity=" + notDeliveryCity
				+ ", strNotDeliveryCity=" + strNotDeliveryCity + ", saleCity="
				+ saleCity + ", strSaleCity=" + strSaleCity + ", notSaleCity="
				+ notSaleCity + ", strNotSaleCity=" + strNotSaleCity
				+ ", goodsSerial=" + goodsSerial + ", barcode=" + barcode
				+ ", expTitle=" + expTitle + ", ids=" + ids + ", array="
				+ Arrays.toString(array) + ", delivery=" + delivery
				+ ", servicenote=" + servicenote + ", postnote=" + postnote
				+ ", remarks=" + remarks + ", comments=" + comments
				+ ", propertyList=" + propertyList + ", propList=" + propList
				+ ", propValList=" + propValList + ", saleGroupList="
				+ saleGroupList + "]";
	}

}