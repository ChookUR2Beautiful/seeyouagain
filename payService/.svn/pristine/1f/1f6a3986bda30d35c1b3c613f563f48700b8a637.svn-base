package com.xmniao.common;

public enum LianLianBankCodeEnum {

	ICBC_CODE("中国工商银行", "01020000"),
	CMB_CODE("招商银行", "03080000"),
	CCB_CODE("中国建设银行", "01050000"),
	BOC_CODE("中国银行", "01040000"),
	ABC_CODE("中国农业银行", "01030000"),
	BOCOM_CODE("交通银行", "03010000"),
	PSBC_CODE("中国邮政储蓄银行", "01000000"),
	CEB_CODE("中国光大银行", "03030000"),
	CMBC_CODE("中国民生银行", "03050000"),
	CIB_CODE("兴业银行", "03090000"),
	PAB_CODE("平安银行", "03070000"),
	ECITIC_CODE("中信银行", "03020000"),
	SPDB_CODE("上海浦东发展银行", "03100000"),
	GDB_CODE("广东发展银行", "03060000"),
	HXB_CODE("华夏银行", "03040000"),
	GZCB_CODE("广州银行", "64135810"),
	
	GZRCB_CODE("广州农商银行", "14055810"),
	BOCOM2_CODE("中国交通银行", "03010000"),
	GDB2_CODE("广发银行", "03060000"),
	CMB2_CODE("中国招商银行", "03080000");
	
	private String bankName;
	
	private String bankCode;

	/**
	 * 根据商户类型，获取商户类型code
	 * @param sellType 商户类型
	 * @return
	 */
	public static String getCodeByName(String bankName){
		for (LianLianBankCodeEnum bankCodeEnum : LianLianBankCodeEnum.values()) {
			if (bankCodeEnum.getBankName().equals(bankName)) {
				return bankCodeEnum.getBankCode();
			}
		}
		return null;
	}
	

	private LianLianBankCodeEnum(String bankName, String bankCode) {
		this.bankName = bankName;
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}


	public void setBankName(String bankName) {
		this.bankName = bankName;
	}


	public String getBankCode() {
		return bankCode;
	}


	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

}
