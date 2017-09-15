package com.xmniao.xmn.core.market.entity.pay;

import java.math.BigDecimal;

/**
 * 
* @projectName: xmnService 
* @ClassName: PostageFreeRule    
* @Description:包邮实体   
* @author: liuzhihao   
* @date: 2016年12月23日 上午11:40:40
 */
public class PostageFreeRule {
    private Integer id;

    private Integer tid;

    private BigDecimal amount;

    private Integer weight;

    private Integer status;

    private String area;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }
}