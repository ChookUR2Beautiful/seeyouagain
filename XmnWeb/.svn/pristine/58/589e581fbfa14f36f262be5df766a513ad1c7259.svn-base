/**   
 * 文件名：TAgioRecord.java   
 *    
 * 日期：2014年11月11日15时37分03秒  
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司     
 */
package com.xmniao.xmn.core.businessman.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：TAgioRecord
 * 
 * 类描述：折扣设置记录
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月11日15时37分03秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TAgioRecord extends BaseEntity {

	private static final long serialVersionUID = -2704297745038961074L;

	private Integer id;// 记录ID
	private Integer aid;// 折扣ID
	private Integer sellerid;// 商家ID
	private Integer operation;// 操作途径|1=APP(商户版)|2=WEB(商户WEB版)|3=SYSTEM(运营平台)
	private Integer uid;// 运营平台用户ID|商户在APP上修改时此字段为0|运营人员后台修改时为运营人员用户ID
	private Double baseagio;// 折扣
	private Double income;// 营业收入
	private Double sledger;// 商户分账
	private Double yledger;// 用户分账
	private Double pledger;// 平台分账
	private Date stdate;// 起始日期
	private Date endate;// 结束日期
	private Date sdate;// 记录时间
	private Date excdate;//执行时间
	private String remarks;//备注
	private Integer type;// 1=常规折扣|2=周末折扣|3=自定义折扣
	private Integer status;// 1=启用=2关闭常规折扣类型默认开启|周末和自定义折扣类型默认关闭
	
	//新增虚拟字段
	private String sellername;//商家名称
	private Date stdateStart;//起始时间查询条件
	private Date stdateEnd;//起始时间查询条件
	private Date endateStart;//结束时间查询条件
	private Date endateEnd;//结束时间查询条件
	private Date sdateStart;//记录时间查询条件
	private Date sdateEnd;//记录时间查询条件
	
	//获取操作途径文字说明
	public String getOperationText(){
		if(operation == null || operation == 0) return null;
		switch (operation) {
		case 1:
			return "APP(商户版)";
		case 2:
			return "WEB(商户WEB版)";
		case 3:
			return "SYSTEM(运营平台)";
		default:
			return null;
		}
	}
	
	/**
	 * 创建一个新的实例 TAgioRecord.
	 * 
	 */
	public TAgioRecord() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAid() {
		return aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	public Integer getSellerid() {
		return sellerid;
	}

	public void setSellerid(Integer sellerid) {
		this.sellerid = sellerid;
	}

	public Integer getOperation() {
		return operation;
	}

	public void setOperation(Integer operation) {
		this.operation = operation;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Double getBaseagio() {
		return baseagio;
	}

	public void setBaseagio(Double baseagio) {
		this.baseagio = baseagio;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public Double getSledger() {
		return sledger;
	}

	public void setSledger(Double sledger) {
		this.sledger = sledger;
	}

	public Double getYledger() {
		return yledger;
	}

	public void setYledger(Double yledger) {
		this.yledger = yledger;
	}

	public Double getPledger() {
		return pledger;
	}

	public void setPledger(Double pledger) {
		this.pledger = pledger;
	}

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getStdate() {
		return stdate;
	}

	public void setStdate(Date stdate) {
		this.stdate = stdate;
	}

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getEndate() {
		return endate;
	}

	public void setEndate(Date endate) {
		this.endate = endate;
	}

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getSdate() {
		return sdate;
	}

	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}

	public String getSellername() {
		return sellername;
	}

	public void setSellername(String sellername) {
		this.sellername = sellername;
	}

	public Date getStdateStart() {
		return stdateStart;
	}

	public void setStdateStart(Date stdateStart) {
		this.stdateStart = stdateStart;
	}

	public Date getStdateEnd() {
		return stdateEnd;
	}

	public void setStdateEnd(Date stdateEnd) {
		this.stdateEnd = stdateEnd;
	}

	public Date getEndateStart() {
		return endateStart;
	}

	public void setEndateStart(Date endateStart) {
		this.endateStart = endateStart;
	}

	public Date getEndateEnd() {
		return endateEnd;
	}

	public void setEndateEnd(Date endateEnd) {
		this.endateEnd = endateEnd;
	}

	public Date getSdateStart() {
		return sdateStart;
	}

	public void setSdateStart(Date sdateStart) {
		this.sdateStart = sdateStart;
	}

	public Date getSdateEnd() {
		return sdateEnd;
	}

	public void setSdateEnd(Date sdateEnd) {
		this.sdateEnd = sdateEnd;
	}

	public Date getExcdate() {
		return excdate;
	}

	public void setExcdate(Date excdate) {
		this.excdate = excdate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	/**
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "TAgioRecord [id=" + id + ", aid=" + aid + ", sellerid="
				+ sellerid + ", operation=" + operation + ", uid=" + uid
				+ ", baseagio=" + baseagio + ", income=" + income
				+ ", sledger=" + sledger + ", yledger=" + yledger
				+ ", pledger=" + pledger + ", stdate=" + stdate + ", endate="
				+ endate + ", sdate=" + sdate + ", excdate=" + excdate
				+ ", remarks=" + remarks + ", sellername=" + sellername
				+ ", stdateStart=" + stdateStart + ", stdateEnd=" + stdateEnd
				+ ", endateStart=" + endateStart + ", endateEnd=" + endateEnd
				+ ", sdateStart=" + sdateStart + ", sdateEnd=" + sdateEnd + "]";
	}
	
	/*@Override
	public String toString() {
		return "TAgioRecord [id=" + id + ", aid=" + aid + ", sellerid=" + sellerid + ", operation=" + operation + ", uid=" + uid + ", baseagio=" + baseagio + ", income=" + income + ", sledger=" + sledger + ", yledger=" + yledger + ", pledger=" + pledger + ", stdate=" + stdate + ", endate=" + endate + ", sdate=" + sdate + "]";
	}
*/
}
