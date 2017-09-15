package com.xmniao.xmn.core.xmer.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
* @projectName: xmnService 
* @ClassName: Coupon    
* @Description:优惠卷实体类  
* @author: liuzhihao   
* @date: 2016年11月26日 下午4:05:59
 */
public class Coupon {
    private Integer cid;

    private String cname;

    private BigDecimal denomination;

    private String validityDesc;

    private Date startDate;

    private Date endDate;

    private BigDecimal condition;

    private Integer useNum;

    private String pic;

    private String breviary;

    private Byte showall;

    private Integer isAllSeller;

    private Integer dayNum;

    private Integer ctype;

    private Integer invalidToday;

    private Integer defaultMaxNum;

    private String introduce;

    private Date updateTime;

    private String rule;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public BigDecimal getDenomination() {
        return denomination;
    }

    public void setDenomination(BigDecimal denomination) {
        this.denomination = denomination;
    }

    public String getValidityDesc() {
        return validityDesc;
    }

    public void setValidityDesc(String validityDesc) {
        this.validityDesc = validityDesc == null ? null : validityDesc.trim();
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getCondition() {
        return condition;
    }

    public void setCondition(BigDecimal condition) {
        this.condition = condition;
    }

    public Integer getUseNum() {
        return useNum;
    }

    public void setUseNum(Integer useNum) {
        this.useNum = useNum;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public String getBreviary() {
        return breviary;
    }

    public void setBreviary(String breviary) {
        this.breviary = breviary == null ? null : breviary.trim();
    }

    public Byte getShowall() {
        return showall;
    }

    public void setShowall(Byte showall) {
        this.showall = showall;
    }

    public Integer getIsAllSeller() {
        return isAllSeller;
    }

    public void setIsAllSeller(Integer isAllSeller) {
        this.isAllSeller = isAllSeller;
    }

    public Integer getDayNum() {
        return dayNum;
    }

    public void setDayNum(Integer dayNum) {
        this.dayNum = dayNum;
    }

    public Integer getCtype() {
        return ctype;
    }

    public void setCtype(Integer ctype) {
        this.ctype = ctype;
    }

    public Integer getInvalidToday() {
        return invalidToday;
    }

    public void setInvalidToday(Integer invalidToday) {
        this.invalidToday = invalidToday;
    }

    public Integer getDefaultMaxNum() {
        return defaultMaxNum;
    }

    public void setDefaultMaxNum(Integer defaultMaxNum) {
        this.defaultMaxNum = defaultMaxNum;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule == null ? null : rule.trim();
    }
}