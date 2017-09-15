package com.xmniao.xmn.core.market.entity.cart;

import java.math.BigDecimal;

public class SaleGroup {
    private Integer id;

    private Long codeid;

    private String pvIds;

    private String pvIdsSort;

    private BigDecimal amount;

    private Integer stock;

    private Integer sort;

    private Integer sales;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getCodeid() {
        return codeid;
    }

    public void setCodeid(Long codeid) {
        this.codeid = codeid;
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
		return "SaleGroup [id=" + id + ", codeid=" + codeid + ", pvIds=" + pvIds + ", pvIdsSort=" + pvIdsSort + ", amount=" + amount
			+ ", stock=" + stock + ", sort=" + sort + ", sales=" + sales + "]";
	}
    
    
}