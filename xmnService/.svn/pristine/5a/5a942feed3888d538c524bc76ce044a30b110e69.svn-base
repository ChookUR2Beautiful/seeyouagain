package com.xmniao.xmn.core.live.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 描述：用户钱包表实体类
 * @author yhl
 * 2016年8月16日14:40:45
 * */
public class LiveWalletInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7983136216671488446L;
	
	//'钱包ID',
	private Long id;
	//'寻蜜鸟员ID',
	private Long uid;
	//'状态  1正常  2锁定  3注销',
	private Integer status;
	//'鸟蛋余额',
	private BigDecimal balance;
	//'鸟币余额',
	private BigDecimal commision;
	//'签名数据',
	private String sign;
	//'签名类型\n 1 MD5(默认)\n 2 RAS',
	private Integer sign_type;
	//'创建时间',
	private Date create_time;
	//'最后一笔交易时间',
	private Date update_time;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public Integer getSign_type() {
		return sign_type;
	}
	public void setSign_type(Integer sign_type) {
		this.sign_type = sign_type;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	@Override
	public String toString() {
		return "LiverWalletInfo [id=" + id + ", uid=" + uid + ", status="
				+ status + ", balance=" + balance + ", commision=" + commision
				+ ", sign=" + sign + ", sign_type=" + sign_type
				+ ", create_time=" + create_time + ", update_time="
				+ update_time + "]";
	}
	
	
}
