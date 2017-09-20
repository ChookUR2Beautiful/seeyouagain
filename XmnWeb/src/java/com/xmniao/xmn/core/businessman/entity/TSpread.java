package com.xmniao.xmn.core.businessman.entity;

import java.io.Serializable;

import com.xmniao.xmn.core.base.BaseEntity;
/**
 * 服务员推广表实体类
 *@ClassName:TSpreadConfig
 *@Description:TODO
 *@author hls
 *@date:2016年2月25日下午8:47:30
 */
public class TSpread extends BaseEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 731472538400786149L;
	private Integer id;//服务员推广表主键
	private String phoneid;//服务员账号（用于接收form元素,包含服务员账号和账号id）
	private String account;//服务员账号（用于添加到数据库）
	private Integer aid;//服务员账号ID（用于添加到数据库）
	private String sellerid;// 商家ID
	private String sellername;// 商家名称
	private String url;// 推广URL地址
	private Integer orderCount;// 已推广订单数
	private Double brokerage;//已获得佣金总额
	private Integer tgStatus;//推广状态，0正常   1停止
	private String rdate;//'创建时间'
	private String udate;//'更新时间'
	private String tgStatusStr;//推广状态，0正常   1停止
	
	private String fullname;	//服务员名称
	private Integer uid;		//服务员会员编号
	
	private String rule;	//奖励规则
	private Double money;	//奖励金额
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPhoneid() {
		return phoneid;
	}
	public void setPhoneid(String phoneid) {
		this.phoneid = phoneid;
	}
	public String getSellerid() {
		return sellerid;
	}
	public void setSellerid(String sellerid) {
		this.sellerid = sellerid;
	}
	public String getSellername() {
		return sellername;
	}
	public void setSellername(String sellername) {
		this.sellername = sellername;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}
	
	public Double getBrokerage() {
		return brokerage;
	}
	public void setBrokerage(Double brokerage) {
		this.brokerage = brokerage;
	}
	
	public Integer getTgStatus() {
		return tgStatus;
	}
	public void setTgStatus(Integer tgStatus) {
		this.tgStatus = tgStatus;
		if(tgStatus==null){
		} else if(tgStatus==1){
			tgStatusStr="停止";
		}else if(tgStatus==0){
			tgStatusStr="正常";
		}else{
			tgStatusStr="-";
		}
	}
	public String getRdate() {
		return rdate;
	}
	public void setRdate(String rdate) {
		this.rdate = rdate;
	}
	public String getUdate() {
		return udate;
	}
	public void setUdate(String udate) {
		this.udate = udate;
	}
	
	public String getTgStatusStr() {
		return tgStatusStr;
	}
	public String getRule() {
		return rule;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
}
