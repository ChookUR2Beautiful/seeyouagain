package com.xmniao.xmn.core.businessman.entity;

import com.xmniao.xmn.core.base.BaseEntity;

public class Card extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7022058834542113549L;
	public Integer sellerid;//商家id
	public String id;//帐号ID
	public String sellername;//连锁店名称，自动带出。
	public String cardId;//银行卡号
	public String cardType;//银行卡类型： 1：借记卡，2：信用卡
	public String  cardUserName;//持卡人姓名
	public String  bankName;//支行名称
	public String cardPhone;//银行预留手机号
	public String cardPurpose;//银行卡提现用途。1：营业收入，2：佣金
	public String ispublic;//0 对私  1对公（银行卡必填）
	public String idtype;//1 身份证2 护照3 驾驶证（银行卡必填）
	public String identity;//证件号码（银行卡必填）
	public String bank;//开户行名称（银行卡必填）
	public String abbrev;//开户行名称缩写（银行卡必填）
	public String province;//银行所属省
	public Integer provinceId;//银行所属省id
	public String cityname;//银行市
	public Integer citynameId;//银行市id
	
	private String uid;//用户uid
	
	private String nickname;//用户名称

    public String getWhereArea(){
     return province+" "+cityname;
    }
	public String getIspublicText() {
		
		if("null".equals(ispublic)){
			return "-";
		}
		if("0".equals(ispublic)){
			return "对私";
		}
		if("1".equals(ispublic)){
			return "对公";
		}
		return "-";
	}
	public String getIdtypeText() {
		if("null".equals(idtype)){
			return "-";
		}
		if("1".equals(idtype)){
			return "身份证";
		}
		if("2".equals(idtype)){
			return "护照";
		}
		if("3".equals(idtype)){
			return "驾驶证";
		}
		return "-";
	}
	
	public Integer getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	public Integer getCitynameId() {
		return citynameId;
	}
	public void setCitynameId(Integer citynameId) {
		this.citynameId = citynameId;
	}
	public String getIspublic() {
		return ispublic;
	}
	public void setIspublic(String ispublic) {
		this.ispublic = ispublic;
	}
	public String getIdtype() {
		return idtype;
	}
	public void setIdtype(String idtype) {
		this.idtype = idtype;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getAbbrev() {
		return abbrev;
	}
	public void setAbbrev(String abbrev) {
		this.abbrev = abbrev;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCityname() {
		return cityname;
	}
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCardPurposeText() {
		if(!("null".equals(this.cardPurpose))&&"1".equals(this.cardPurpose)){
			
			return "营业收入";
		}
		if(!("null".equals(this.cardPurpose))&&"2".equals(this.cardPurpose)){
			return "佣金";
		}
		return "-";
	}
	public String getCardTypeText() {
		if(!("null".equals(this.cardType))&&"1".equals(this.cardType)){
			
			return "借记卡";
		}
		if(!("null".equals(this.cardType))&&"2".equals(this.cardType)){
			return "信用卡";
		}
		return "-";
	}
	
	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardUserName() {
		return cardUserName;
	}

	public void setCardUserName(String cardUserName) {
		this.cardUserName = cardUserName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCardPhone() {
		return cardPhone;
	}

	public void setCardPhone(String cardPhone) {
		this.cardPhone = cardPhone;
	}

	public String getCardPurpose() {
		return cardPurpose;
	}

	public void setCardPurpose(String cardPurpose) {
		this.cardPurpose = cardPurpose;
	}

	public String getSellername() {
		return sellername;
	}

	public void setSellername(String sellername) {
		this.sellername = sellername;
	}

	public Integer getSellerid() {
		return sellerid;
	}

	public void setSellerid(Integer sellerid) {
		this.sellerid = sellerid;
	}
	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}
	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}
	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	

}
