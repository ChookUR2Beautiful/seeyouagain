package com.xmniao.domain.seller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 
 * 
 * 项目名称：busineService
 * 
 * 类名称：Seller
 * 
 * 类描述： 商家表实体
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年6月13日 下午3:13:19 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class SellerBean implements Serializable{
	
	private static final long serialVersionUID = -438061276251960068L;

	private Integer sellerid;

    private Integer staffid;

    private Integer jointid;

    private String sellername;

    private String province;

    private String city;

    private String area;

    private Integer zoneid;

    private String category;

    private String genre;

    private String bewrite;

    private String address;

    private String tel;

    private String fullname;

    private String identity;

    private String phoneid;

    private String orgid;

    private String licenseid;

    private String licenseurl;

    private String sdate;

    private String sexplain;

    private Date svalidity;

    private Date evalidity;

    private Integer status;

    private String examineinfo;

    private Date signdate;

    private Date udate;

    private Integer give;

    private Integer fatherid;

    private String remarks;

    private String email;

    private String identityzurl;

    private String identityfurl;

    private String identitynurl;

    private String licensefurl;

    private String agreement;

    private String lssellername;

    private String typename;

    private String tradename;

    private Integer isprotocol;

    private Byte agioType;

    private Double agio;

    private Float yledger;

    private Date agioTime;

    private Float entry;

    private Integer isVirtual;

    private Double flatAgio;

    private Integer isforce;

    private Integer isonline;

    private Integer agioAgio;

    private String agioInstruction;

    private Integer ismultiple;

    private Boolean recommend;

    private Integer recomNum;

    private Integer dataSource;

    private String offlineReason;

    private Date dateOperate;

    private Integer xmnService;

    private Integer islock;

    private Double ratio;

    private Integer isfees;

    private Double debit;

    private String agreement2;

    private String agreement3;

    private String agreement4;

    private Integer label;

    private Integer order;

    private Integer realOrder;

    private BigDecimal agioAgioNum;

    private Integer isKa;

    private Integer brandId;

    private BigDecimal saveMoney;

    private Integer uid;

    //行业 美食-西餐
    private String hyText;

    private Integer isPublic;
    
    private Integer isPaid;
    
    private Integer joinDividend;
    
    public Integer getSellerid() {
        return sellerid;
    }

    public void setSellerid(Integer sellerid) {
        this.sellerid = sellerid;
    }

    public Integer getStaffid() {
        return staffid;
    }

    public void setStaffid(Integer staffid) {
        this.staffid = staffid;
    }

    public Integer getJointid() {
        return jointid;
    }

    public void setJointid(Integer jointid) {
        this.jointid = jointid;
    }

    public String getSellername() {
        return sellername;
    }

    public void setSellername(String sellername) {
        this.sellername = sellername == null ? null : sellername.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public Integer getZoneid() {
        return zoneid;
    }

    public void setZoneid(Integer zoneid) {
        this.zoneid = zoneid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre == null ? null : genre.trim();
    }

    public String getBewrite() {
        return bewrite;
    }

    public void setBewrite(String bewrite) {
        this.bewrite = bewrite == null ? null : bewrite.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname == null ? null : fullname.trim();
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity == null ? null : identity.trim();
    }

    public String getPhoneid() {
        return phoneid;
    }

    public void setPhoneid(String phoneid) {
        this.phoneid = phoneid == null ? null : phoneid.trim();
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid == null ? null : orgid.trim();
    }

    public String getLicenseid() {
        return licenseid;
    }

    public void setLicenseid(String licenseid) {
        this.licenseid = licenseid == null ? null : licenseid.trim();
    }

    public String getLicenseurl() {
        return licenseurl;
    }

    public void setLicenseurl(String licenseurl) {
        this.licenseurl = licenseurl == null ? null : licenseurl.trim();
    }

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate == null ? null : sdate.trim();
    }

    public String getSexplain() {
        return sexplain;
    }

    public void setSexplain(String sexplain) {
        this.sexplain = sexplain == null ? null : sexplain.trim();
    }

    public Date getSvalidity() {
        return svalidity;
    }

    public void setSvalidity(Date svalidity) {
        this.svalidity = svalidity;
    }

    public Date getEvalidity() {
        return evalidity;
    }

    public void setEvalidity(Date evalidity) {
        this.evalidity = evalidity;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getExamineinfo() {
        return examineinfo;
    }

    public void setExamineinfo(String examineinfo) {
        this.examineinfo = examineinfo == null ? null : examineinfo.trim();
    }

    public Date getSigndate() {
        return signdate;
    }

    public void setSigndate(Date signdate) {
        this.signdate = signdate;
    }

    public Date getUdate() {
        return udate;
    }

    public void setUdate(Date udate) {
        this.udate = udate;
    }

    public Integer getGive() {
        return give;
    }

    public void setGive(Integer give) {
        this.give = give;
    }

    public Integer getFatherid() {
        return fatherid;
    }

    public void setFatherid(Integer fatherid) {
        this.fatherid = fatherid;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getIdentityzurl() {
        return identityzurl;
    }

    public void setIdentityzurl(String identityzurl) {
        this.identityzurl = identityzurl == null ? null : identityzurl.trim();
    }

    public String getIdentityfurl() {
        return identityfurl;
    }

    public void setIdentityfurl(String identityfurl) {
        this.identityfurl = identityfurl == null ? null : identityfurl.trim();
    }

    public String getIdentitynurl() {
        return identitynurl;
    }

    public void setIdentitynurl(String identitynurl) {
        this.identitynurl = identitynurl == null ? null : identitynurl.trim();
    }

    public String getLicensefurl() {
        return licensefurl;
    }

    public void setLicensefurl(String licensefurl) {
        this.licensefurl = licensefurl == null ? null : licensefurl.trim();
    }

    public String getAgreement() {
        return agreement;
    }

    public void setAgreement(String agreement) {
        this.agreement = agreement == null ? null : agreement.trim();
    }

    public String getLssellername() {
        return lssellername;
    }

    public void setLssellername(String lssellername) {
        this.lssellername = lssellername == null ? null : lssellername.trim();
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename == null ? null : typename.trim();
    }

    public String getTradename() {
        return tradename;
    }

    public void setTradename(String tradename) {
        this.tradename = tradename == null ? null : tradename.trim();
    }

    public Integer getIsprotocol() {
        return isprotocol;
    }

    public void setIsprotocol(Integer isprotocol) {
        this.isprotocol = isprotocol;
    }

    public Byte getAgioType() {
        return agioType;
    }

    public void setAgioType(Byte agioType) {
        this.agioType = agioType;
    }

    public Double getAgio() {
        return agio;
    }

    public void setAgio(Double agio) {
        this.agio = agio;
    }

    public Float getYledger() {
        return yledger;
    }

    public void setYledger(Float yledger) {
        this.yledger = yledger;
    }

    public Date getAgioTime() {
        return agioTime;
    }

    public void setAgioTime(Date agioTime) {
        this.agioTime = agioTime;
    }

    public Float getEntry() {
        return entry;
    }

    public void setEntry(Float entry) {
        this.entry = entry;
    }

    public Integer getIsVirtual() {
        return isVirtual;
    }

    public void setIsVirtual(Integer isVirtual) {
        this.isVirtual = isVirtual;
    }

    public Double getFlatAgio() {
        return flatAgio;
    }

    public void setFlatAgio(Double flatAgio) {
        this.flatAgio = flatAgio;
    }

    public Integer getIsforce() {
        return isforce;
    }

    public void setIsforce(Integer isforce) {
        this.isforce = isforce;
    }

    public Integer getIsonline() {
        return isonline;
    }

    public void setIsonline(Integer isonline) {
        this.isonline = isonline;
    }

    public Integer getAgioAgio() {
        return agioAgio;
    }

    public void setAgioAgio(Integer agioAgio) {
        this.agioAgio = agioAgio;
    }

    public String getAgioInstruction() {
        return agioInstruction;
    }

    public void setAgioInstruction(String agioInstruction) {
        this.agioInstruction = agioInstruction == null ? null : agioInstruction.trim();
    }

    public Integer getIsmultiple() {
        return ismultiple;
    }

    public void setIsmultiple(Integer ismultiple) {
        this.ismultiple = ismultiple;
    }

    public Boolean getRecommend() {
        return recommend;
    }

    public void setRecommend(Boolean recommend) {
        this.recommend = recommend;
    }

    public Integer getRecomNum() {
        return recomNum;
    }

    public void setRecomNum(Integer recomNum) {
        this.recomNum = recomNum;
    }

    public Integer getDataSource() {
        return dataSource;
    }

    public void setDataSource(Integer dataSource) {
        this.dataSource = dataSource;
    }

    public String getOfflineReason() {
        return offlineReason;
    }

    public void setOfflineReason(String offlineReason) {
        this.offlineReason = offlineReason == null ? null : offlineReason.trim();
    }

    public Date getDateOperate() {
        return dateOperate;
    }

    public void setDateOperate(Date dateOperate) {
        this.dateOperate = dateOperate;
    }

    public Integer getXmnService() {
        return xmnService;
    }

    public void setXmnService(Integer xmnService) {
        this.xmnService = xmnService;
    }

    public Integer getIslock() {
        return islock;
    }

    public void setIslock(Integer islock) {
        this.islock = islock;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    public Integer getIsfees() {
        return isfees;
    }

    public void setIsfees(Integer isfees) {
        this.isfees = isfees;
    }

    public Double getDebit() {
        return debit;
    }

    public void setDebit(Double debit) {
        this.debit = debit;
    }

    public String getAgreement2() {
        return agreement2;
    }

    public void setAgreement2(String agreement2) {
        this.agreement2 = agreement2 == null ? null : agreement2.trim();
    }

    public String getAgreement3() {
        return agreement3;
    }

    public void setAgreement3(String agreement3) {
        this.agreement3 = agreement3 == null ? null : agreement3.trim();
    }

    public String getAgreement4() {
        return agreement4;
    }

    public void setAgreement4(String agreement4) {
        this.agreement4 = agreement4 == null ? null : agreement4.trim();
    }

    public Integer getLabel() {
        return label;
    }

    public void setLabel(Integer label) {
        this.label = label;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getRealOrder() {
        return realOrder;
    }

    public void setRealOrder(Integer realOrder) {
        this.realOrder = realOrder;
    }

    public BigDecimal getAgioAgioNum() {
        return agioAgioNum;
    }

    public void setAgioAgioNum(BigDecimal agioAgioNum) {
        this.agioAgioNum = agioAgioNum;
    }

    public Integer getIsKa() {
        return isKa;
    }

    public void setIsKa(Integer isKa) {
        this.isKa = isKa;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public BigDecimal getSaveMoney() {
        return saveMoney;
    }

    public void setSaveMoney(BigDecimal saveMoney) {
        this.saveMoney = saveMoney;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

	public String getHyText() {
		StringBuilder sb = new StringBuilder();
		if(org.apache.commons.lang.StringUtils.isNotBlank(typename)){
			sb.append(typename);
		}
		if(org.apache.commons.lang.StringUtils.isNotBlank(tradename)){
			sb.append("-");
			sb.append(tradename);
		}
		hyText = sb.toString();
		return hyText;
	}

	public Integer getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Integer isPublic) {
		this.isPublic = isPublic;
	}

	public Integer getIsPaid() {
		return isPaid;
	}

	public void setIsPaid(Integer isPaid) {
		this.isPaid = isPaid;
	}

	public Integer getJoinDividend() {
		return joinDividend;
	}

	public void setJoinDividend(Integer joinDividend) {
		this.joinDividend = joinDividend;
	}

	@Override
	public String toString() {
		return "SellerBean [sellerid=" + sellerid + ", staffid=" + staffid
				+ ", jointid=" + jointid + ", sellername=" + sellername
				+ ", province=" + province + ", city=" + city + ", area="
				+ area + ", zoneid=" + zoneid + ", category=" + category
				+ ", genre=" + genre + ", bewrite=" + bewrite + ", address="
				+ address + ", tel=" + tel + ", fullname=" + fullname
				+ ", identity=" + identity + ", phoneid=" + phoneid
				+ ", orgid=" + orgid + ", licenseid=" + licenseid
				+ ", licenseurl=" + licenseurl + ", sdate=" + sdate
				+ ", sexplain=" + sexplain + ", svalidity=" + svalidity
				+ ", evalidity=" + evalidity + ", status=" + status
				+ ", examineinfo=" + examineinfo + ", signdate=" + signdate
				+ ", udate=" + udate + ", give=" + give + ", fatherid="
				+ fatherid + ", remarks=" + remarks + ", email=" + email
				+ ", identityzurl=" + identityzurl + ", identityfurl="
				+ identityfurl + ", identitynurl=" + identitynurl
				+ ", licensefurl=" + licensefurl + ", agreement=" + agreement
				+ ", lssellername=" + lssellername + ", typename=" + typename
				+ ", tradename=" + tradename + ", isprotocol=" + isprotocol
				+ ", agioType=" + agioType + ", agio=" + agio + ", yledger="
				+ yledger + ", agioTime=" + agioTime + ", entry=" + entry
				+ ", isVirtual=" + isVirtual + ", flatAgio=" + flatAgio
				+ ", isforce=" + isforce + ", isonline=" + isonline
				+ ", agioAgio=" + agioAgio + ", agioInstruction="
				+ agioInstruction + ", ismultiple=" + ismultiple
				+ ", recommend=" + recommend + ", recomNum=" + recomNum
				+ ", dataSource=" + dataSource + ", offlineReason="
				+ offlineReason + ", dateOperate=" + dateOperate
				+ ", xmnService=" + xmnService + ", islock=" + islock
				+ ", ratio=" + ratio + ", isfees=" + isfees + ", debit="
				+ debit + ", agreement2=" + agreement2 + ", agreement3="
				+ agreement3 + ", agreement4=" + agreement4 + ", label="
				+ label + ", order=" + order + ", realOrder=" + realOrder
				+ ", agioAgioNum=" + agioAgioNum + ", isKa=" + isKa
				+ ", brandId=" + brandId + ", saveMoney=" + saveMoney
				+ ", uid=" + uid + ", hyText=" + hyText + ", isPublic="
				+ isPublic + ", isPaid=" + isPaid + ", joinDividend="
				+ joinDividend + "]";
	}

}