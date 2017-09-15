package com.xmniao.xmn.core.market.entity.pay;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 
* @projectName: xmnService 
* @ClassName: ProductInfo    
* @Description:商品详情实体   
* @author: liuzhihao   
* @date: 2016年12月22日 上午10:57:36
 */
public class ProductInfo {
    private Integer pid;

    private Long codeid;

    private String standard;

    private String pname;

    private String goodsname;

    private Integer classa;

    private Integer secondary;

    private Double price;

    private Double discount;

    private String batching;

    private String weight;

    private String suttle;

    private Integer quality;

    private Integer isadditives;

    private String packing;

    private Date production;

    private String brandname;

    private Integer pstatus;

    private String crafts;

    private String selltype;

    private String deal;

    private String place;

    private String province;

    private String city;

    private String stored;

    private Integer store;

    private Integer sales;

    private Integer choice;

    private String cname;

    private String address;

    private String licenseid;

    private String tel;

    private Date rdate;

    private Date udate;

    private Integer dstatus;

    private Integer sort;

    private Integer supplierid;

    private String suppliername;

    private String breviary;

    private String salesinfo;

    private Integer isintegral;

    private BigDecimal integral;

    private Integer saletype;

    private BigDecimal cash;

    private BigDecimal purchaseprice;

    private Integer deliverytype;

    private Boolean allowrefund;

    private Double expweight;

    private Integer exptid;

    private String goodsserial;

    private String barcode;

    private Integer choicesort;

    /** 包邮价格 */
    private String freePostage;


    public String getFreePostage() {
		return freePostage;
	}

	public void setFreePostage(String freePostage) {
		this.freePostage = freePostage;
	}

	//库存
    private Integer stock;
    //加价金额
    private BigDecimal amount;
    //购买 数量
    private Integer num;
    
    //规格id组合
    private String attrIds;  
    
    //规格值组合
    private String attrVals;
    
    //商品类型（0有效  ；1 无效）
    private Integer type;
    
    //活动id(有活动id时是活动商品)
    private Integer activityId;
    
    //购物记录id
    private String  cartId;
    //商品详情信息
    private String html;
    
    //是否收藏
    private Integer collection;//0已收藏  1 未收藏
    
    //图片前缀
    private List<String>  banner;
    
    private Integer htmlType; //0 富文本   1  html地址

    // Laber标签列表
    private Set<Integer> labels;
    
    private String showFlagContent;//美食首页专用“xx类中人气最高”

    public Set<Integer> getLabels() {
        return labels;
    }

    public void setLabels(Set<Integer> labels) {
        this.labels = labels;
    }

    public Integer getHtmlType() {
        return htmlType;
    }

    public void setHtmlType(Integer htmlType) {
        this.htmlType = htmlType;
    }

    public List<String> getBanner() {
        return banner;
    }

    public void setBanner(List<String> banner) {
        this.banner = banner;
    }

    public Integer getCollection() {
        return collection;
    }

    public void setCollection(Integer collection) {
        this.collection = collection;
    }


    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAttrIds() {
        return attrIds;
    }

    public void setAttrIds(String attrIds) {
        this.attrIds = attrIds;
    }

    public String getAttrVals() {
        return attrVals;
    }

    public void setAttrVals(String attrVals) {
        this.attrVals = attrVals;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Long getCodeid() {
        return codeid;
    }

    public void setCodeid(Long codeid) {
        this.codeid = codeid;
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

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname == null ? null : goodsname.trim();
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

    public Integer getIsadditives() {
        return isadditives;
    }

    public void setIsadditives(Integer isadditives) {
        this.isadditives = isadditives;
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

    public String getBrandname() {
        return brandname;
    }

    public void setBrandname(String brandname) {
        this.brandname = brandname == null ? null : brandname.trim();
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

    public String getSelltype() {
        return selltype;
    }

    public void setSelltype(String selltype) {
        this.selltype = selltype == null ? null : selltype.trim();
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

    public String getLicenseid() {
        return licenseid;
    }

    public void setLicenseid(String licenseid) {
        this.licenseid = licenseid == null ? null : licenseid.trim();
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

    public Integer getSupplierid() {
        return supplierid;
    }

    public void setSupplierid(Integer supplierid) {
        this.supplierid = supplierid;
    }

    public String getSuppliername() {
        return suppliername;
    }

    public void setSuppliername(String suppliername) {
        this.suppliername = suppliername == null ? null : suppliername.trim();
    }

    public String getBreviary() {
        return breviary;
    }

    public void setBreviary(String breviary) {
        this.breviary = breviary == null ? null : breviary.trim();
    }

    public String getSalesinfo() {
        return salesinfo;
    }

    public void setSalesinfo(String salesinfo) {
        this.salesinfo = salesinfo == null ? null : salesinfo.trim();
    }

    public Integer getIsintegral() {
        return isintegral;
    }

    public void setIsintegral(Integer isintegral) {
        this.isintegral = isintegral;
    }

    public BigDecimal getIntegral() {
        return integral;
    }

    public void setIntegral(BigDecimal integral) {
        this.integral = integral;
    }

    public Integer getSaletype() {
        return saletype;
    }

    public void setSaletype(Integer saletype) {
        this.saletype = saletype;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    public BigDecimal getPurchaseprice() {
        return purchaseprice;
    }

    public void setPurchaseprice(BigDecimal purchaseprice) {
        this.purchaseprice = purchaseprice;
    }

    public Integer getDeliverytype() {
        return deliverytype;
    }

    public void setDeliverytype(Integer deliverytype) {
        this.deliverytype = deliverytype;
    }

    public Boolean getAllowrefund() {
        return allowrefund;
    }

    public void setAllowrefund(Boolean allowrefund) {
        this.allowrefund = allowrefund;
    }

    public Double getExpweight() {
        return expweight;
    }

    public void setExpweight(Double expweight) {
        this.expweight = expweight;
    }

    public Integer getExptid() {
        return exptid;
    }

    public void setExptid(Integer exptid) {
        this.exptid = exptid;
    }

    public String getGoodsserial() {
        return goodsserial;
    }

    public void setGoodsserial(String goodsserial) {
        this.goodsserial = goodsserial == null ? null : goodsserial.trim();
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode == null ? null : barcode.trim();
    }

    public Integer getChoicesort() {
        return choicesort;
    }

    public void setChoicesort(Integer choicesort) {
        this.choicesort = choicesort;
    }

	public String getShowFlagContent() {
		return showFlagContent;
	}

	public void setShowFlagContent(String showFlagContent) {
		this.showFlagContent = showFlagContent;
	}
    
}