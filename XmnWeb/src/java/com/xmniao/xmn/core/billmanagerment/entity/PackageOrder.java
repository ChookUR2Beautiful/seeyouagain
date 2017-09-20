package com.xmniao.xmn.core.billmanagerment.entity;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.SystemConstants;

public class PackageOrder extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2640089077540489018L;

	private Integer id;

    private String orderNo;

    private Integer pid;//套餐编号

    private String title;//套餐名称

    private Integer sellerid;

    private Integer uid;

    private String phone;//会员手机

    private String uname;//会员昵称

    private Date createTime;

    private Date payTime;

    private Date lastTime;

    private Integer nums;

    private BigDecimal originalPrice;

    private BigDecimal sellingPrice;

    private BigDecimal sellingCoinPrice;

    private BigDecimal totalAmount;//订单总价(现金价)
    
    private BigDecimal totalCoinAmount;//订单总价（鸟币价）

    private BigDecimal cash;

    private BigDecimal balance;

    private BigDecimal commision;

    private BigDecimal zbalance;

    private BigDecimal beans;
    
    private BigDecimal sellerCoin;

    private BigDecimal cuser;

    private Integer cdid;

    private BigDecimal retrunCouponAmount;

    private String paymentType;//支付方式
    
    private String paymentTypeText;//支付方式

    private String payid;

    private Integer status;

    private Integer ledgerType;

    private BigDecimal ledgerRatio;

    private Date updateTime;

    private String uidRelationChain;

    private Integer version;

    private Integer orderSource;

    private String notice;
    
    private double base;//鸟币现金转换比
    
    private String sellername;
    
    public String getSellername() {
		return sellername;
	}

	public void setSellername(String sellername) {
		this.sellername = sellername;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPaymentTypeText() {
		return SystemConstants.getPayTypeText(paymentType);
	}

	public void setPaymentTypeText(String paymentTypeText) {
		this.paymentTypeText = paymentTypeText;
	}

	public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getSellerid() {
        return sellerid;
    }

    public void setSellerid(Integer sellerid) {
        this.sellerid = sellerid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }
    
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    public Date getCreateTime() {
        return createTime;
    }
    
    public String getCreateTimeStr(){
    	return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createTime);
    }
    
    public String getPayTimeStr(){
    	return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(payTime);
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public BigDecimal getSellingCoinPrice() {
        return sellingCoinPrice;
    }

    public void setSellingCoinPrice(BigDecimal sellingCoinPrice) {
        this.sellingCoinPrice = sellingCoinPrice;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getCommision() {
        return commision;
    }

    public void setCommision(BigDecimal commision) {
        this.commision = commision;
    }

    public BigDecimal getZbalance() {
        return zbalance;
    }

    public void setZbalance(BigDecimal zbalance) {
        this.zbalance = zbalance;
    }

    public BigDecimal getBeans() {
        return beans;
    }

    public void setBeans(BigDecimal beans) {
        this.beans = beans;
    }

    public BigDecimal getCuser() {
        return cuser;
    }

    public void setCuser(BigDecimal cuser) {
        this.cuser = cuser;
    }

    public Integer getCdid() {
        return cdid;
    }

    public void setCdid(Integer cdid) {
        this.cdid = cdid;
    }

    public BigDecimal getRetrunCouponAmount() {
        return retrunCouponAmount;
    }

    public void setRetrunCouponAmount(BigDecimal retrunCouponAmount) {
        this.retrunCouponAmount = retrunCouponAmount;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType == null ? null : paymentType.trim();
    }

    public String getPayid() {
        return payid;
    }

    public void setPayid(String payid) {
        this.payid = payid == null ? null : payid.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getLedgerType() {
        return ledgerType;
    }

    public void setLedgerType(Integer ledgerType) {
        this.ledgerType = ledgerType;
    }

    public BigDecimal getLedgerRatio() {
        return ledgerRatio;
    }

    public void setLedgerRatio(BigDecimal ledgerRatio) {
        this.ledgerRatio = ledgerRatio;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUidRelationChain() {
        return uidRelationChain;
    }

    public void setUidRelationChain(String uidRelationChain) {
        this.uidRelationChain = uidRelationChain == null ? null : uidRelationChain.trim();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(Integer orderSource) {
        this.orderSource = orderSource;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice == null ? null : notice.trim();
    }

	public BigDecimal getTotalCoinAmount() {
		return totalCoinAmount;
	}

	public void setTotalCoinAmount(BigDecimal totalCoinAmount) {
		this.totalCoinAmount = totalCoinAmount;
	}

	public BigDecimal getSellerCoin() {
		return sellerCoin;
	}

	public void setSellerCoin(BigDecimal sellerCoin) {
		this.sellerCoin = sellerCoin;
	}

	public double getBase() {
		return base;
	}

	public void setBase(double base) {
		this.base = base;
	}

	@Override
	public String toString() {
		return "PackageOrder [id=" + id + ", orderNo=" + orderNo + ", pid="
				+ pid + ", title=" + title + ", sellerid=" + sellerid
				+ ", uid=" + uid + ", phone=" + phone + ", uname=" + uname
				+ ", createTime=" + createTime + ", payTime=" + payTime
				+ ", lastTime=" + lastTime + ", nums=" + nums
				+ ", originalPrice=" + originalPrice + ", sellingPrice="
				+ sellingPrice + ", sellingCoinPrice=" + sellingCoinPrice
				+ ", totalAmount=" + totalAmount + ", cash=" + cash
				+ ", balance=" + balance + ", commision=" + commision
				+ ", zbalance=" + zbalance + ", beans=" + beans + ", cuser="
				+ cuser + ", cdid=" + cdid + ", retrunCouponAmount="
				+ retrunCouponAmount + ", paymentType=" + paymentType
				+ ", paymentTypeText=" + paymentTypeText + ", payid=" + payid
				+ ", status=" + status + ", ledgerType=" + ledgerType
				+ ", ledgerRatio=" + ledgerRatio + ", updateTime=" + updateTime
				+ ", uidRelationChain=" + uidRelationChain + ", version="
				+ version + ", orderSource=" + orderSource + ", notice="
				+ notice + ", sellername=" + sellername + "]";
	}
    
}