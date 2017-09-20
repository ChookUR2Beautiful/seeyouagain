package com.xmniao.xmn.core.member.entity;

import java.util.HashMap;
import java.util.Map;

import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.StringUtils;

public class MemberCard extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1729160430490625501L;
	private String uId;//用户ID、商家ID，合作商ID
	private String id;//帐号ID
	private String userType;//1.用户；2.商家；3合作商
	private String type;//帐号类型 1 支付宝帐号 2 银行卡号 3 U付帐号 4 其他帐号
	private String account;//银行卡号或支付宝
	private String  cardtype;//银行卡 1 借记卡(储蓄卡) 2 信用卡 0 支付宝帐号
	private String  username;//持卡人姓名
	private String bankname;//支行名称，如 XXXX支行
	private String mobileid;//银行预留手机号
	private String isuse;//银行卡提现用途。1：营业收入，2：佣金
	private String ispublic;//0 对私  1对公（银行卡必填）
	private String idtype;//1 身份证2 护照3 驾驶证（银行卡必填）
	private String identity;//证件号码（银行卡必填）
	private String bank;//开户行名称（银行卡必填）
	private String abbrev;//开户行名称缩写（银行卡必填）
	private String province;//银行所属省
	private Integer provinceId;//银行所属省id
	private String cityname;//银行市
	private Integer citynameId;//银行市id
	
	
	public Map<String, String> getWhere(){
		Map<String, String> map = new HashMap<String, String>();
		if(StringUtils.hasLength(uId)){
			map.put("uId", uId);
		}
		if(StringUtils.hasLength(account)){
			map.put("bankid", account);
		}
		if(StringUtils.hasLength(username)){
			map.put("username", username);
		}
		if(StringUtils.hasLength(mobileid)){
			map.put("mobileid", mobileid);
		}
		if(StringUtils.hasLength(identity)){
			map.put("identity", identity);
		}
		map.put("cPage", String.valueOf(getPage()));
		map.put("pageSize",  String.valueOf(getLimit()));
		map.put("userType", "1");
		return map;
	}
	
	
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
			return null;
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
			return null;
		}
		
		public String getCardPurposeText() {
			if(StringUtils.hasLength(isuse)){
				if("1".equals(this.isuse)){
					return "营业收入";
				}
				if("2".equals(this.isuse)){
					return "佣金";
				}
			}
			return null;
		}
		
		public String getCardTypeText() {
			if(!("null".equals(this.cardtype))&&"1".equals(this.cardtype)){
				
				return "借记卡";
			}
			if(!("null".equals(this.cardtype))&&"2".equals(this.cardtype)){
				return "信用卡";
			}
			if(!("null".equals(this.cardtype))&&"0".equals(this.cardtype)){
				return "支付宝";
			}
			return null;
		}
	
	public String getUId() {
		return uId;
	}
	public void setUId(String uId) {
		this.uId = uId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getCardtype() {
		return cardtype;
	}
	public void setCardtype(String cardtype) {
		this.cardtype = cardtype;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	public String getMobileid() {
		return mobileid;
	}

	public void setMobileid(String mobileid) {
		this.mobileid = mobileid;
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
	public String getIsuse() {
		return isuse;
	}
	public void setIsuse(String isuse) {
		this.isuse = isuse;
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
	public Integer getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	public String getCityname() {
		return cityname;
	}
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	public Integer getCitynameId() {
		return citynameId;
	}
	public void setCitynameId(Integer citynameId) {
		this.citynameId = citynameId;
	}
	
	
	
	
	@Override
	public String toString() {
		return "MemberCard [uId=" + uId + ", id=" + id + ", userType="
				+ userType + ", type=" + type + ", account=" + account
				+ ", cardtype=" + cardtype + ", username=" + username
				+ ", bankname=" + bankname + ", mobileid=" + mobileid
				+ ", ispublic=" + ispublic + ", idtype=" + idtype
				+ ", identity=" + identity + ", bank=" + bank + ", abbrev="
				+ abbrev + ", province=" + province + ", provinceId="
				+ provinceId + ", cityname=" + cityname + ", citynameId="
				+ citynameId + "]";
	}


}
