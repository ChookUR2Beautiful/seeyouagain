package com.xmn.saas.entity.wallet;


/**
 * 
*      
* 类名称：BankList   
* 类描述：   查询所有银行列表
* 创建人：xiaoxiong   
* 创建时间：2016年10月13日 下午6:26:21     
*
 */
public class BankList {

	private int id;
	
	private String bankName;
	
	private String abbrev;
	
	

	public String getAbbrev() {
		return abbrev;
	}

	public void setAbbrev(String abbrev) {
		this.abbrev = abbrev;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	
	
	
	
}
