package com.xmniao.xmn.core.live_anchor.entity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;

public class TSellerPackage  extends BaseEntity{
	private static final long serialVersionUID = -408688908649455801L;

	private Integer id;

    private String title;

    private Integer sellerid;

    private BigDecimal sellingPrice;

    private BigDecimal sellingCoinPrice;

    private BigDecimal originalPrice;

    private Integer stock;

    private Integer sales;

    private Integer status;

    private Integer ledgerType;

    private BigDecimal ledgerRatio;

    private Date saleStartTime;

    private Date saleEndTime;

    private Date useStartTime;

    private Date useEndTime;

    private Date forbidStartTime;

    private Date forbidEndTime;

    private Integer lookNum;

    private Date updateTime;
    
    private Integer highlyRecommended;
	
	private Integer homeSort;
	
	private String content;

    private String notice;


	/**************************自定义增加********************************/
    private String sellername;// 商家名称
    
    private String statusDesc;
    
    private List<TSellerPackageIssueRef> voucherList;//抵扣券
    
	private List<TSellerPackagePic> sellerPackagePicList;//抵扣券
	
	private String ids;// 前台id集合
	
	private Object[] array;// 批量更新时id集合
	
	private String couponDesc;  //抵用券信息
    

	public String getCouponDesc() {
		return couponDesc;
	}

	public void setCouponDesc(String couponDesc) {
		this.couponDesc = couponDesc;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public List<TSellerPackageIssueRef> getVoucherList() {
		return voucherList;
	}

	public void setVoucherList(List<TSellerPackageIssueRef> voucherList) {
		this.voucherList = voucherList;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getSellername() {
		return sellername;
	}

	public void setSellername(String sellername) {
		this.sellername = sellername;
	}
	
	public List<TSellerPackagePic> getSellerPackagePicList() {
		return sellerPackagePicList;
	}

	public void setSellerPackagePicList(List<TSellerPackagePic> sellerPackagePicList) {
		this.sellerPackagePicList = sellerPackagePicList;
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
	
	/*************************自定义结束*********************************/

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
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
    
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Date getSaleStartTime() {
        return saleStartTime;
    }

    public void setSaleStartTime(Date saleStartTime) {
        this.saleStartTime = saleStartTime;
    }
    
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Date getSaleEndTime() {
        return saleEndTime;
    }

    public void setSaleEndTime(Date saleEndTime) {
        this.saleEndTime = saleEndTime;
    }

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Date getUseStartTime() {
        return useStartTime;
    }

    public void setUseStartTime(Date useStartTime) {
        this.useStartTime = useStartTime;
    }

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Date getUseEndTime() {
        return useEndTime;
    }

  
    public void setUseEndTime(Date useEndTime) {
        this.useEndTime = useEndTime;
    }

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Date getForbidStartTime() {
        return forbidStartTime;
    }

    public void setForbidStartTime(Date forbidStartTime) {
        this.forbidStartTime = forbidStartTime;
    }

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Date getForbidEndTime() {
        return forbidEndTime;
    }

    public void setForbidEndTime(Date forbidEndTime) {
        this.forbidEndTime = forbidEndTime;
    }

    public Integer getLookNum() {
        return lookNum;
    }

    public void setLookNum(Integer lookNum) {
        this.lookNum = lookNum;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
    public Integer getHighlyRecommended() {
		return highlyRecommended;
	}

	public void setHighlyRecommended(Integer highlyRecommended) {
		this.highlyRecommended = highlyRecommended;
	}
	
	public Integer getHomeSort() {
        return homeSort;
    }

    public void setHomeSort(Integer homeSort) {
        this.homeSort = homeSort;
    }
	
	 public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice == null ? null : notice.trim();
    }

	@Override
	public String toString() {
		return "TSellerPackage [id=" + id + ", title=" + title + ", sellerid="
				+ sellerid + ", sellingPrice=" + sellingPrice
				+ ", sellingCoinPrice=" + sellingCoinPrice + ", originalPrice="
				+ originalPrice + ", stock=" + stock + ", sales=" + sales
				+ ", status=" + status + ", ledgerType=" + ledgerType
				+ ", ledgerRatio=" + ledgerRatio + ", saleStartTime="
				+ saleStartTime + ", saleEndTime=" + saleEndTime
				+ ", useStartTime=" + useStartTime + ", useEndTime="
				+ useEndTime + ", forbidStartTime=" + forbidStartTime
				+ ", forbidEndTime=" + forbidEndTime + ", lookNum=" + lookNum
				+ ", updateTime=" + updateTime + ", highlyRecommended="
				+ highlyRecommended + ", homeSort=" + homeSort + ", content="
				+ content + ", notice=" + notice + ", sellername=" + sellername
				+ ", statusDesc=" + statusDesc + ", voucherList=" + voucherList
				+ ", sellerPackagePicList=" + sellerPackagePicList + ", ids="
				+ ids + ", array=" + Arrays.toString(array) + ", couponDesc="
				+ couponDesc + "]";
	}



	
    
    
}