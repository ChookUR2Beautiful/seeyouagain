package com.xmniao.xmn.core.fresh.entity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.xmniao.xmn.core.base.BaseEntity;

public class ActivityGroup extends BaseEntity{
    private Integer id;

    private Long codeId;

    private Integer activityId;

    private String pvIds;

    private String pvIdsSort;

    private BigDecimal amount;

    private Integer stock;

    private Integer sort;

    private Integer sales;
    
    private List<String> pvValues;//属性值集合
    
    private BigDecimal maxAmount;
    
    private Integer maxStock;
    
    private String pvValue;//属性值字符串
    
    public String getPvValue() {
		return pvValue;
	}

	public void setPvValue(String pvValue) {
		this.pvValue = pvValue;
		try {
			if(StringUtils.isNotBlank(pvValue)){
				if(pvValues==null||pvValues.size()==0){
					String[] split = pvValue.split(",");
					pvValues = Arrays.asList(split);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Integer getMaxStock() {
		return maxStock;
	}

	public void setMaxStock(Integer maxStock) {
		this.maxStock = maxStock;
	}

	public BigDecimal getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(BigDecimal maxAmount) {
		this.maxAmount = maxAmount;
	}

	public Long getCodeId() {
		return codeId;
	}

	public void setCodeId(Long codeId) {
		this.codeId = codeId;
	}

	public List<String> getPvValues() {
		return pvValues;
	}

	public void setPvValues(List<String> pvValues) {
		this.pvValues = pvValues;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getPvIds() {
        return pvIds;
    }

    public void setPvIds(String pvIds) {
        this.pvIds = pvIds == null ? null : pvIds.trim();
    }

    public String getPvIdsSort() {
        return pvIdsSort;
    }

    public void setPvIdsSort(String pvIdsSort) {
        this.pvIdsSort = pvIdsSort == null ? null : pvIdsSort.trim();
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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

	@Override
	public String toString() {
		return "ActivityGroup [id=" + id + ", codeid=" + codeId + ", activityId=" + activityId + ", pvIds=" + pvIds
				+ ", pvIdsSort=" + pvIdsSort + ", amount=" + amount + ", stock=" + stock + ", sort=" + sort + ", sales="
				+ sales + "]";
	}
    
    
}