/**
 * 
 */
package com.xmniao.domain.maibao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;


/**
 * 
 * 项目名称：busineService_maibao
 * 
 * 类名称：MaibaoLedgerNoticeBean
 * 
 * 类描述： 通知脉宝的分账的bean
 * 
 * 创建人： Administrator
 * 
 * 创建时间：2017年7月13日 上午9:43:23 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
public class MaibaoLedgerNotifyBean {
	private String ecno;
	private String mobile;
	private String transNo;
	private String amount;
	private String discount;
	private Integer merchantType;
	private String merchantName;
	private String signedEcno;
	private String ledgerAmount;
	private String ledgerTime;
	private String sign;
	
	public String getEcno() {
		return ecno;
	}
	public void setEcno(String ecno) {
		this.ecno = ecno;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getTransNo() {
		return transNo;
	}
	public void setTransNo(String transNo) {
		this.transNo = transNo;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public Integer getMerchantType() {
		return merchantType;
	}
	public void setMerchantType(Integer merchantType) {
		this.merchantType = merchantType;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public String getSignedEcno() {
		return signedEcno;
	}
	public void setSignedEcno(String signedEcno) {
		this.signedEcno = signedEcno;
	}
	public String getLedgerAmount() {
		return ledgerAmount;
	}
	public void setLedgerAmount(String ledgerAmount) {
		this.ledgerAmount = ledgerAmount;
	}
	public String getLedgerTime() {
		return ledgerTime;
	}
	public void setLedgerTime(String ledgerTime) {
		this.ledgerTime = ledgerTime;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	@Override
	public String toString() {
		return "MaibaoLedgerNoticeBean [ecno=" + ecno + ", mobile=" + mobile + ", transNo=" + transNo + ", amount="
				+ amount + ", discount=" + discount + ", merchantType=" + merchantType + ", merchantName="
				+ merchantName + ", signedEcno=" + signedEcno + ", ledgerAmount=" + ledgerAmount + ", ledgerTime="
				+ ledgerTime + ", sign=" + sign + "]";
	}

	public String getSignSource(String key) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException{
		Class<MaibaoLedgerNotifyBean> clazz = MaibaoLedgerNotifyBean.class;
		Field[] fields =  clazz.getDeclaredFields();
		List<String> fieldList = new ArrayList<String>(fields.length);
		for(Field field:fields){
			fieldList.add(field.getName());
		}
        Collections.sort(fieldList);

        StringBuilder signStr = new StringBuilder();
        for (String fieldName:fieldList) {
        	if(fieldName.equals("sign")){
        		continue;
        	}
        	Object value= clazz.getDeclaredField(fieldName).get(this);
        	if(value==null){
        		continue;
        	}
        	if(StringUtils.isBlank(value.toString())){
        		continue;
        	}
        	signStr.append(fieldName).append("=").append(clazz.getDeclaredField(fieldName).get(this)).append("&");
        }
        signStr.append(key);
		return signStr.toString();
	}
}
