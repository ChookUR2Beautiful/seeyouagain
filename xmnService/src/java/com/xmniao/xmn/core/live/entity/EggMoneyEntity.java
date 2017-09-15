/**
 * 2016年8月11日 上午11:46:29
 */
package com.xmniao.xmn.core.live.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @项目名称：xmnService_3.1.2_zb
 * @类名称：EggMoneyEntity
 * @类描述：
 * @创建人： yeyu
 * @创建时间 2016年8月11日 上午11:46:29
 * @version
 */
public class EggMoneyEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8400581238226461651L;
	
	private int id;//记录ID
	private int accountId;//钱包表ID
	private int rtype;//'0 充值 1 消费 2 转出 '
	private Double eggMoney;//交易鸟蛋金额
	private Date sdate ;//记录时间
	private int description ;//描述
	private Date createTime;// 创建时间
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public int getRtype() {
		return rtype;
	}
	public void setRtype(int rtype) {
		this.rtype = rtype;
	}
	public Double getEggMoney() {
		return eggMoney;
	}
	public void setEggMoney(Double eggMoney) {
		this.eggMoney = eggMoney;
	}
	public Date getSdate() {
		return sdate;
	}
	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}
	public int getDescription() {
		return description;
	}
	public void setDescription(int description) {
		this.description = description;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "EggMoneyEntity [id=" + id + ", accountId=" + accountId
				+ ", rtype=" + rtype + ", eggMoney=" + eggMoney + ", sdate="
				+ sdate + ", description=" + description + ", createTime="
				+ createTime + "]";
	}
}
