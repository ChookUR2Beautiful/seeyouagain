package com.xmniao.xmn.core.fresh.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.HtmlUtils;

import com.xmniao.xmn.core.base.BaseEntity;

public class MallPackage extends BaseEntity{
    /**
     * 
     */
    private Long id;

    /**
     * 套餐名称
     */
    private String title;

    /**
     * 原价
     */
    private Double originalPrice;

    /**
     * 售价
     */
    private Double price;

    /**
     * 是否仅支持鸟币支付
     */
    private Byte onlyLiveCoin;

    /**
     * 上架状态 0待上线 1已上线 2已售罄 3已下线
     */
    private Byte status;

    /**
     * 套餐排序
     */
    private Integer sort;

    /**
     * 关联的奖励等级类型(b_live_fans_rank表)
     */
    private Integer rankType;

    /**
     * 关联的奖励等级ID(b_live_fans_rank表)
     */
    private Long rankId;

    /**
     * H5内容
     */
    private String html;
    
    
    /**
     * 图片
     */
    private String imgUrls;
    
    /**
     * 缩略图
     */
    private String packageImgMine;
    
    private String productIds;
    
    private String productName;
    
    private BigDecimal minPrice;
    
    private BigDecimal maxPrice;
    
    private String statusStr;
    
    private List<String> imgUrlsArr;
    
    private List<String> productIdsArr;
    
	public List<String> getImgUrlsArr() {
		try {
			if(StringUtils.isBlank(imgUrls)){
				return new ArrayList<>();
			}
			return Arrays.asList(imgUrls.split(","));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setImgUrlsArr(List<String> imgUrlsArr) {
		this.imgUrlsArr = imgUrlsArr;
	}

	public List<String> getProductIdsArr() {
		try {
			if(StringUtils.isBlank(productIds)){
				return new ArrayList<>();
			}
			return Arrays.asList(productIds.split(","));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setProductIdsArr(List<String> productIdsArr) {
		this.productIdsArr = productIdsArr;
	}

	public String getStatusStr() {
		if(status==null){
			return "";
		}
		switch (status) {
		case 0:
			return "待上线";
		case 1:
			return "已上线";
		case 2:
			return "已售罄";
		case 3:
			return "已下线";
		default:
			break;
		}
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public BigDecimal getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}

	public BigDecimal getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductIds() {
		return productIds;
	}

	public void setProductIds(String productIds) {
		this.productIds = productIds;
	}

	public String getImgUrls() {
		return imgUrls;
	}

	public void setImgUrls(String imgUrls) {
		this.imgUrls = imgUrls;
	}

	public String getPackageImgMine() {
		return packageImgMine;
	}

	public void setPackageImgMine(String packageImgMine) {
		this.packageImgMine = packageImgMine;
	}

    /**
     * 
     * @return id 
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 套餐名称
     * @return title 套餐名称
     */
    public String getTitle() {
        return title;
    }

    /**
     * 套餐名称
     * @param title 套餐名称
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 原价
     * @return original_price 原价
     */
    public Double getOriginalPrice() {
        return originalPrice;
    }

    /**
     * 原价
     * @param originalPrice 原价
     */
    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    /**
     * 售价
     * @return price 售价
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 售价
     * @param price 售价
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 是否仅支持鸟币支付
     * @return only_live_coin 是否仅支持鸟币支付
     */
    public Byte getOnlyLiveCoin() {
        return onlyLiveCoin;
    }

    /**
     * 是否仅支持鸟币支付
     * @param onlyLiveCoin 是否仅支持鸟币支付
     */
    public void setOnlyLiveCoin(Byte onlyLiveCoin) {
        this.onlyLiveCoin = onlyLiveCoin;
    }

    /**
     * 上架状态 0待上线 1已上线 2已售罄 3已下线
     * @return status 上架状态 0待上线 1已上线 2已售罄 3已下线
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 上架状态 0待上线 1已上线 2已售罄 3已下线
     * @param status 上架状态 0待上线 1已上线 2已售罄 3已下线
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 套餐排序
     * @return sort 套餐排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 套餐排序
     * @param sort 套餐排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 关联的奖励等级类型(b_live_fans_rank表)
     * @return rank_type 关联的奖励等级类型(b_live_fans_rank表)
     */
    public Integer getRankType() {
        return rankType;
    }

    /**
     * 关联的奖励等级类型(b_live_fans_rank表)
     * @param rankType 关联的奖励等级类型(b_live_fans_rank表)
     */
    public void setRankType(Integer rankType) {
        this.rankType = rankType;
    }

    /**
     * 关联的奖励等级ID(b_live_fans_rank表)
     * @return rank_id 关联的奖励等级ID(b_live_fans_rank表)
     */
    public Long getRankId() {
        return rankId;
    }

    /**
     * 关联的奖励等级ID(b_live_fans_rank表)
     * @param rankId 关联的奖励等级ID(b_live_fans_rank表)
     */
    public void setRankId(Long rankId) {
        this.rankId = rankId;
    }

    /**
     * H5内容
     * @return html H5内容
     */
    public String getHtml() {
        return HtmlUtils.htmlUnescape(html);
    }

    /**
     * H5内容
     * @param html H5内容
     */
    public void setHtml(String html) {
        this.html = html == null ? null : html.trim();
    }
}