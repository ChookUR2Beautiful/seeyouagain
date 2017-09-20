package com.xmniao.common;

import java.util.Map;

/**
 * 数据组合处理
 * 
 * @author Dongjietao
 * 
 */
public class PayDatas { // 数据组装

	String Version; // 版本号 目前固定为 10。如版本升级，能向前兼容。
	String CmdId;// 消息类型 每一种消息类型代表一种交易
	String CustId; // 客户号 由钱管家系统分配的 16 位数字代码，客户的唯一标识
	String Charset;// 字符集
	String ChkType;// 签名方式 ‘M’– MD5 类型‘R’ –RSA 用户调用接口的当前签名方式
	// 汇付天下数据返回地址 客户接收异步应答的 url 地址
	String RetUrl; // 本地地址
	String MerId; // 正式商户号，6 位数字，由汇付分配
	String SubAcctId; // 出款子账户的子账户号，如果没有，走默认代发配置
	String payUrl;
    String queryCmdId;
    String queryUrl;
    


	public String getQueryUrl() {
		return queryUrl;
	}

	public void setQueryUrl(String queryUrl) {
		this.queryUrl = queryUrl;
	}

	public String getQueryCmdId() {
		return queryCmdId;
	}

	public void setQueryCmdId(String queryCmdId) {
		this.queryCmdId = queryCmdId;
	}

	public String getPayUrl() {
		return payUrl;
	}

	public void setPayUrl(String payUrl) {
		this.payUrl = payUrl;
	}

	/**
	 * 汇付天下支付提现，数据post提交前拼装
	 * 
	 * @param map
	 * @return
	 */
	public String getTheMap(Map map) {

		StringBuffer sb1 = new StringBuffer();

		sb1.append("Version=" + this.Version.trim())
				.append("&CmdId=" + this.CmdId.trim())

				.append("&CustId=" + this.CustId.trim())
				.append("&OrdId=" + map.get("OrdId").toString().trim())

				.append("&OrdAmt=" + map.get("OrdAmt").toString().trim())
				.append("&MerPriv=" + map.get("MerPriv").toString()

				.trim())
				.append("&AcctName=" + map.get("AcctName").toString().trim())
				.append("&BankId=" + map.get("BankId").toString().trim())
				.append("&AcctId=" + map.get("AcctId").toString().trim())
				.append("&PrType=" + map.get("PrType").toString().trim())
				.append("&RetUrl=" + this.RetUrl.trim())
				.append("&Charset=" + this.Charset.trim())
				.append("&ChkType=" + this.ChkType.trim())
				.append("&ChkValue=" + map.get("ChkValue").toString().trim());

		String str1 = sb1.toString();// 提交参数拼装完成

		// System.out.println("汇付天下支付提现提交参数：" + str1);

		return str1;
	}
	
	
	public String getQueryOneParam(String orderId , String ordAmt,String chkValue) {

		StringBuffer sb1 = new StringBuffer();

		sb1.append("Version=" + this.Version.trim())
				.append("&CmdId=" + this.queryCmdId.trim())
				.append("&CustId=" + this.CustId.trim())
				.append("&OrdId=" + orderId.trim())
				.append("&OrdAmt=" + ordAmt.trim())
				.append("&Charset=" + this.Charset.trim())
				.append("&ChkType=" + this.ChkType.trim())
		       .append("&ChkValue=" + chkValue.trim());
		String str1 = sb1.toString();//
		return str1;
	}
	
	
	

	public String getVersion() {
		return Version;
	}

	public void setVersion(String version) {
		Version = version;
	}

	public String getCmdId() {
		return CmdId;
	}

	public void setCmdId(String cmdId) {
		CmdId = cmdId;
	}

	public String getCustId() {
		return CustId;
	}

	public void setCustId(String custId) {
		CustId = custId;
	}

	public String getCharset() {
		return Charset;
	}

	public void setCharset(String charset) {
		Charset = charset;
	}

	public String getChkType() {
		return ChkType;
	}

	public void setChkType(String chkType) {
		ChkType = chkType;
	}

	public String getRetUrl() {
		return RetUrl;
	}

	public void setRetUrl(String retUrl) {
		RetUrl = retUrl;
	}

	public String getMerId() {
		return MerId;
	}

	public void setMerId(String merId) {
		MerId = merId;
	}

	public String getSubAcctId() {
		return SubAcctId;
	}

	public void setSubAcctId(String subAcctId) {
		SubAcctId = subAcctId;
	}
	

}
