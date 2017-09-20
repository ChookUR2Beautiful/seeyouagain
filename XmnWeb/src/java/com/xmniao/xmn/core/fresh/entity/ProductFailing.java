package com.xmniao.xmn.core.fresh.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：ProductFailing
 * 
 * 类描述: 产品导入失败信息
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016-7-15下午2:11:35
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class ProductFailing extends BaseEntity implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3285604482397431604L;

	private Integer cid;

    private Long importserial;
    
    private String standard;

    private String pname;

    private String goodsname;

    private String classa;

    private Integer secondary;

    private String price;

    private String discount;

    private String batching;

    private String weight;

    private String suttle;

    private String quality;

    private String isadditives;

    private String packing;

    private String production;

    private String brandname;

    private Integer pstatus;

    private String crafts;

    private String selltype;
    
    private String integral;

    private String deal;

    private String place;

    private String province;

    private String city;

    private String stored;

    private String store;

    private String sales;

    private String choice;

    private String cname;

    private String address;

    private String licenseid;

    private String tel;

    private Date rdate;

    private Integer dstatus;

    private String sort;

    private Integer supplierid;

    private String suppliername;

    private String salesinfo;

    private String cash;

    private String purchaseprice;

    private String deliverytype;

    private String allowrefund;

    private String expweight;

    private String goodsserial;

    private String barcode;

    private String delivery;

    private String postnote;

    private String servicenote;

    private String remarks;
    
    private String comments;//导入失败说明信息
    
    private String prpoName;	//导入分类名称
	private String groupName;  //导入规格名称
	private BigDecimal amount; //规格加价
	private Integer stock;	//规格库存

    public String getPrpoName() {
		return prpoName;
	}

	public void setPrpoName(String prpoName) {
		this.prpoName = prpoName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Long getImportserial() {
        return importserial;
    }

    public void setImportserial(Long importserial) {
        this.importserial = importserial;
    }

    
    /**
	 * @return the standard
	 */
	public String getStandard() {
		return standard;
	}

	/**
	 * @param standard the standard to set
	 */
	
	public void setStandard(String standard) {
		this.standard = standard;
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

    public String getClassa() {
        return classa;
    }

    public void setClassa(String classa) {
        this.classa = classa;
    }

    public Integer getSecondary() {
        return secondary;
    }

    public void setSecondary(Integer secondary) {
        this.secondary = secondary;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price == null ? null : price.trim();
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount == null ? null : discount.trim();
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

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality == null ? null : quality.trim();
    }

    public String getIsadditives() {
        return isadditives;
    }

    public void setIsadditives(String isadditives) {
        this.isadditives = isadditives == null ? null : isadditives.trim();
    }

    public String getPacking() {
        return packing;
    }

    public void setPacking(String packing) {
        this.packing = packing == null ? null : packing.trim();
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production == null ? null : production.trim();
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

    
    /**
	 * @return the integral
	 */
	public String getIntegral() {
		return integral;
	}

	/**
	 * @param integral the integral to set
	 */
	public void setIntegral(String integral) {
		this.integral = integral;
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

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store == null ? null : store.trim();
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales == null ? null : sales.trim();
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice == null ? null : choice.trim();
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

    public Integer getDstatus() {
        return dstatus;
    }

    public void setDstatus(Integer dstatus) {
        this.dstatus = dstatus;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort == null ? null : sort.trim();
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

    public String getSalesinfo() {
        return salesinfo;
    }

    public void setSalesinfo(String salesinfo) {
        this.salesinfo = salesinfo == null ? null : salesinfo.trim();
    }

    public String getCash() {
        return cash;
    }

    public void setCash(String cash) {
        this.cash = cash == null ? null : cash.trim();
    }

    public String getPurchaseprice() {
        return purchaseprice;
    }

    public void setPurchaseprice(String purchaseprice) {
        this.purchaseprice = purchaseprice == null ? null : purchaseprice.trim();
    }

    public String getDeliverytype() {
        return deliverytype;
    }

    public void setDeliverytype(String deliverytype) {
        this.deliverytype = deliverytype == null ? null : deliverytype.trim();
    }

    public String getAllowrefund() {
        return allowrefund;
    }

    public void setAllowrefund(String allowrefund) {
        this.allowrefund = allowrefund == null ? null : allowrefund.trim();
    }

    public String getExpweight() {
        return expweight;
    }

    public void setExpweight(String expweight) {
        this.expweight = expweight == null ? null : expweight.trim();
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

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery == null ? null : delivery.trim();
    }

    public String getPostnote() {
        return postnote;
    }

    public void setPostnote(String postnote) {
        this.postnote = postnote == null ? null : postnote.trim();
    }

    public String getServicenote() {
        return servicenote;
    }

    public void setServicenote(String servicenote) {
        this.servicenote = servicenote == null ? null : servicenote.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProductFailing [cid=" + cid + ", importserial=" + importserial
				+ ", standard=" + standard + ", pname=" + pname
				+ ", goodsname=" + goodsname + ", classa=" + classa
				+ ", secondary=" + secondary + ", price=" + price
				+ ", discount=" + discount + ", batching=" + batching
				+ ", weight=" + weight + ", suttle=" + suttle + ", quality="
				+ quality + ", isadditives=" + isadditives + ", packing="
				+ packing + ", production=" + production + ", brandname="
				+ brandname + ", pstatus=" + pstatus + ", crafts=" + crafts
				+ ", selltype=" + selltype + ", integral=" + integral
				+ ", deal=" + deal + ", place=" + place + ", province="
				+ province + ", city=" + city + ", stored=" + stored
				+ ", store=" + store + ", sales=" + sales + ", choice="
				+ choice + ", cname=" + cname + ", address=" + address
				+ ", licenseid=" + licenseid + ", tel=" + tel + ", rdate="
				+ rdate + ", dstatus=" + dstatus + ", sort=" + sort
				+ ", supplierid=" + supplierid + ", suppliername="
				+ suppliername + ", salesinfo=" + salesinfo + ", cash=" + cash
				+ ", purchaseprice=" + purchaseprice + ", deliverytype="
				+ deliverytype + ", allowrefund=" + allowrefund
				+ ", expweight=" + expweight + ", goodsserial=" + goodsserial
				+ ", barcode=" + barcode + ", delivery=" + delivery
				+ ", postnote=" + postnote + ", servicenote=" + servicenote
				+ ", remarks=" + remarks + ", comments=" + comments + "]";
	}
    
	
    
}