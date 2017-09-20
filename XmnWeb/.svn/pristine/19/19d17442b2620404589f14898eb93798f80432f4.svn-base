package com.xmniao.xmn.core.dataDictionary.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * @项目名称：XmnWeb
 * 
 * @类名称：TBankList
 * 
 * @类描述：银行
 * 
 * @创建人：zhang'zhiwen
 * 
 * @创建时间 ：2015年8月12日 上午10:16:23
 * 
 */
public class TBankList extends BaseEntity {

	private static final long serialVersionUID = -7407129054975136293L;

	private Integer id;// 银行ID
	private String bankName;// 银行名称
	private Integer status;// 0 支持提现 1 不支持提现
	private Integer sorting;// 排序
	private String explains;// 说明
	private Date sdate;// 更新时间
	private String logo;// logo
	private String abbrev;// 英文缩写

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSorting() {
		return sorting;
	}

	public void setSorting(Integer sorting) {
		this.sorting = sorting;
	}

	public String getExplains() {
		return explains;
	}

	public void setExplains(String explains) {
		this.explains = explains;
	}

	@JSONField(format = "yyyy-MM-dd")
	public Date getSdate() {
		return sdate;
	}

	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getAbbrev() {
		return abbrev;
	}

	public void setAbbrev(String abbrev) {
		this.abbrev = abbrev;
	}

	@Override
	public String toString() {
		return "TBankList [id=" + id + ", bankName=" + bankName + ", status="
				+ status + ", sorting=" + sorting + ", explains=" + explains
				+ ", sDate=" + sdate + ", logo=" + logo + ", abbrev=" + abbrev
				+ "]";
	}
}
