package com.xmn.saas.controller.api.v1.wallet.vo;

import com.xmn.saas.base.Request;
import com.xmn.saas.entity.wallet.BankApply;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;

public class BankPutRequest extends Request {

    /**
     * 绑定/修改银行卡类
     * 
     * @author zhouxiaojian
     */
    private static final long serialVersionUID = -1962911603144737737L;

    @NotNull( message = "类型不能为空！" )
    private String isPublic;

    @NotNull( message = "账户姓名不能为空！" )
    private String fullName;

    @NotNull( message = "银行卡号不能为空" )
    private String bankId;

    @NotNull( message = "开户行不能为空" )
    private String bank;

    @NotNull( message = "支行名称不能为空" )
    private String bankName;

    private String idCard;

    @NotNull( message = "银行预留电话不能为空" )
    private String bankPhone;

    @NotNull( message = "银行卡户地址不能为空" )
    private String bankAddress;

    @NotNull( message = "城市不能为空" )
    private String cityname;

    @NotNull( message = "省份不能为空" )
    private String province;

    @NotNull( message = "缩写不能为空" )
    private String abbrev;

    @NotNull(message = "短信验证码(verifyCode)不能为空")
    private String verifyCode;

    public String getAbbrev() {
        return abbrev;
    }

    public void setAbbrev(String abbrev) {
        this.abbrev = abbrev;
    }


    private Integer appid;// 主键



    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }


    /**
     * 后端生成的签名
     * 明文格式：fullName=张三&bank=中国工商银行&idCard=430681199107253713&bankId=6212264000026393591&bankPhone
     * =18573116140&abbrev=ICBC&bankname=中国工商银行深圳南新支行&ispublic=0
     * 
     * 密文：437f8e12d07d6e1750d1e861f35da772
     */
    private String signature;// MD5加密的签名字段，


    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Integer getAppid() {
        return appid;
    }

    public void setAppid(Integer appid) {
        this.appid = appid;
    }

    public String getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(String isPublic) {
        this.isPublic = isPublic;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getBankPhone() {
        return bankPhone;
    }

    public void setBankPhone(String bankPhone) {
        this.bankPhone = bankPhone;
    }

    public String getBankAddress() {
        return bankAddress;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    @Override
    public String toString() {
        return "BankPutRequest{" +
                "isPublic='" + isPublic + '\'' +
                ", fullName='" + fullName + '\'' +
                ", bankId='" + bankId + '\'' +
                ", bank='" + bank + '\'' +
                ", bankName='" + bankName + '\'' +
                ", idCard='" + idCard + '\'' +
                ", bankPhone='" + bankPhone + '\'' +
                ", bankAddress='" + bankAddress + '\'' +
                ", cityname='" + cityname + '\'' +
                ", province='" + province + '\'' +
                ", abbrev='" + abbrev + '\'' +
                ", verifyCode='" + verifyCode + '\'' +
                ", appid=" + appid +
                ", signature='" + signature + '\'' +
                '}';
    }

    public BankApply converToBean(int sellerId) {
        BankApply bankApply = new BankApply();
        BeanUtils.copyProperties(this, bankApply);
        bankApply.setSellerId(sellerId);
        bankApply.setBankid(this.getBankId());
        bankApply.setBankname(this.getBankName());
        bankApply.setBankphone(this.getBankPhone());
        bankApply.setIspublic(Integer.valueOf(this.getIsPublic()));
        bankApply.setFullname(this.getFullName());
        bankApply.setIdcard(this.getIdCard());
        bankApply.setAppid(this.getAppid());
        bankApply.setAbbrev(this.getAbbrev());
        bankApply.setLocation(this.getProvince());
        bankApply.setCityname(this.getCityname());
        return bankApply;
    }


}
