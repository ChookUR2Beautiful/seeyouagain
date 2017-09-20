package com.xmniao.xmn.core.businessman.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：TbankApply
 * 
 * 类描述：商家银行卡修改申请
 * 
 * 创建人： zhou'dekun
 * 
 * 创建时间：2015年1月4日15时37分03秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TbankApply extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4990930541085729259L;
	   
	   private Integer appid;//申请id
	   private Integer aid;//商家id或会员id
	   private Integer accounttype;//类型 1 商家 2 会员
	   private String fullname;//开户姓名
	   private String bank;//开户行名称
	   private String location;//省
	   private String cityname;// 市
	   private Integer idtype;//1 身份证2 护照3 驾驶证
	   private String idtypeText;//1 身份证2 护照3 驾驶证
	   private String idcard;//证件号码
	   private String bankid;//银行卡号
	   private Integer type;//银行卡类型1 借记卡(储蓄卡)2 信用卡
	   private String typeText;//银行卡类型1 借记卡(储蓄卡)2 信用卡
	   private Date bdate;//申请时间
	   private String bankphone;//手机预留号
	   private String remark;// 申请原因
	   private Integer handletype;//处理状态: 1 处理中 0 已处理 2不通过
	   private String handletypeText;//处理状态: 1 处理中 0 已处理 2不通过
	   private String handleremark;//申请不通过原因
	   private Integer modifitype;//1营业收入银行卡  2 佣金收益银行卡
	   private String modifitypeText;//1营业收入银行卡  2 佣金收益银行卡
	   private String abbrev;//开户行名称缩写
	   private Integer  ispublic ;//0 对私  1对公
	   private String bankname;//支行名称
	   private Integer accountid;//'银行卡记录ID',
	   private Date bdateStart;//搜索查询
	   private Date bdateEnd;//搜索查询
	   private String license;//商户执照URL
	   private String upidcard;//身份证正面URL
	   private String dwidcard;//身份证反面URL
	   private Integer applytype;//申请类型:0修改，1新增
	   private String  supplementaryInformation;//补充资料
	   
	   
	   
	public String getSupplementaryInformation() {
		return  "查看";
	}

	public String getIdtypeText() {
		if(idtype==null){
			return "";
		}
		if(idtype==1){
			return "身份证";
		}
		if(idtype==2){
			return "护照";
		}
		if(idtype==3){
			return "驾驶证";
		}
		if(idtype==4){
			return "台胞证";
		}
		return "";
	}
	public void setIdtypeText(String idtypeText) {
		this.idtypeText = idtypeText;
	}
	public String getTypeText() {
		if(type==null){
			return "";
		}
		if(type==1){
			return "借记卡(储蓄卡)";
		}
		if(type==2){
			return "信用卡";
		}
		return "";
	}
	public void setTypeText(String typeText) {
		this.typeText = typeText;
	}
	public String getHandletypeText() {
		if(handletype==null){
			return "";
		}
		if(handletype==0){
			return "审核通过";
		}
		if(handletype==1){
			return "待审核";
		}
		if(handletype==2){
			return "未通过";
		}
		return "";
	}
	public void setHandletypeText(String handletypeText) {
		this.handletypeText = handletypeText;
	}
	public String getModifitypeText() {
		if(modifitype==null){
			return "";
		}
		if(modifitype==1){
			return "营业收入";
		}
		if(modifitype==2){
			return "佣金收益";
		}
		return "";
	}
	public void setModifitypeText(String modifitypeText) {
		this.modifitypeText = modifitypeText;
	}
	public Integer getApplytype() {
		return applytype;
	}
	public void setApplytype(Integer applytype) {
		this.applytype = applytype;
	}
	public String getApplytypeText() {
		 if(applytype==1){
			 return "新增";
		 }
		 if(applytype==0){
			 return "修改";
		 }
		return "-";
	}
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	public String getUpidcard() {
		return upidcard;
	}
	public void setUpidcard(String upidcard) {
		this.upidcard = upidcard;
	}
	public String getDwidcard() {
		return dwidcard;
	}
	public void setDwidcard(String dwidcard) {
		this.dwidcard = dwidcard;
	}
	public Integer getAppid() {
		return appid;
	}
	public void setAppid(Integer appid) {
		this.appid = appid;
	}
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public Integer getAccounttype() {
		return accounttype;
	}
	public void setAccounttype(Integer accounttype) {
		this.accounttype = accounttype;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCityname() {
		return cityname;
	}
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	public Integer getIdtype() {
		return idtype;
	}
	public void setIdtype(Integer idtype) {
		this.idtype = idtype;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getBankid() {
		return bankid;
	}
	public void setBankid(String bankid) {
		this.bankid = bankid;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getBdate() {
		return bdate;
	}
	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}
	public String getBankphone() {
		return bankphone;
	}
	public void setBankphone(String bankphone) {
		this.bankphone = bankphone;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getHandletype() {
		return handletype;
	}
	public void setHandletype(Integer handletype) {
		this.handletype = handletype;
	}
	public String getHandleremark() {
		return handleremark;
	}
	public void setHandleremark(String handleremark) {
		this.handleremark = handleremark;
	}
	public Integer getModifitype() {
		return modifitype;
	}
	public void setModifitype(Integer modifitype) {
		this.modifitype = modifitype;
	}
	public String getAbbrev() {
		return abbrev;
	}
	public void setAbbrev(String abbrev) {
		this.abbrev = abbrev;
	}
	public Integer getIspublic() {
		return ispublic;
	}
	public void setIspublic(Integer ispublic) {
		this.ispublic = ispublic;
	}
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	
	public Date getBdateStart() {
		return bdateStart;
	}
	public void setBdateStart(Date bdateStart) {
		this.bdateStart = bdateStart;
	}
	public Date getBdateEnd() {
		return bdateEnd;
	}
	public void setBdateEnd(Date bdateEnd) {
		this.bdateEnd = bdateEnd;
	}
	public Integer getAccountid() {
		return accountid;
	}
	public void setAccountid(Integer accountid) {
		this.accountid = accountid;
	}
	

	@Override
	public String toString() {
		return "TbankApply [appid=" + appid + ", aid=" + aid + ", accounttype="
				+ accounttype + ", fullname=" + fullname + ", bank=" + bank
				+ ", location=" + location + ", cityname=" + cityname
				+ ", idtype=" + idtype + ", idcard=" + idcard + ", bankid="
				+ bankid + ", type=" + type + ", bdate=" + bdate
				+ ", bankphone=" + bankphone + ", remark=" + remark
				+ ", handletype=" + handletype + ", handleremark="
				+ handleremark + ", modifitype=" + modifitype + ", abbrev="
				+ abbrev + ", ispublic=" + ispublic + ", bankname=" + bankname
				+ ", accountid=" + accountid + ", bdateStart=" + bdateStart
				+ ", bdateEnd=" + bdateEnd + "]";
	}
	   
}
