package com.xmniao.xmn.core.xmermanagerment.entity;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.DateUtil;

/**
 *@ClassName:XmerSeller
 *@Description:寻蜜客商铺信息实体类
 *@author hls
 *@date:2016年5月30日上午9:58:55
 */
public class XmerSeller extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2766389459018907050L;

	/**
	 * 表t_seller
	 */
	private Integer id;//寻蜜客id
	
	private Integer uid;//寻蜜客用户身份编号
	
	private Integer sellerid;//商铺ID
	
	private String sellername;//商铺名称
	
	private String address;//商铺地址
	
	private String typename;//商铺类别
	
	private Integer status;//店铺状态
	private String statusStr;//店铺状态
	
	private Date evalidity;//签约到期日
	private String evalidityStr;//签约到期日
	
	private Date sdate;//签约到期日
	private String sdateStr;//加入时间
	
	private Date edate;//加入时间结束范围
	
	private Date updateDate;//更新时间
	
	private String phoneid;//负责人手机
	/**
	 * 表b_wallet_record
	 */
	private BigDecimal balance;//店铺流水
	private String balanceStr;//店铺流水
	
	private Integer uidtogetseller;//接收用于查询商铺信息的uid

	private Integer saasType;	//签约所用SAAS类型
	DecimalFormat  df = new DecimalFormat("0.00"); 
	
	
	public Integer getUidtogetseller() {
		return uidtogetseller;
	}

	public void setUidtogetseller(Integer uidtogetseller) {
		this.uidtogetseller = uidtogetseller;
	}

	public String getPhoneid() {
		return phoneid;
	}

	public void setPhoneid(String phoneid) {
		this.phoneid = phoneid;
	}

	public BigDecimal getBalance() {
		if(balance == null || "".equals(balance)){
			balance = new BigDecimal(0.00); 
		} 
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getBalanceStr() {
		if(balanceStr == null || "".equals(balanceStr)) return "-";
		balanceStr = df.format(balanceStr);
		return balanceStr;
	}

	public void setBalanceStr(String balanceStr) {
		this.balanceStr = balanceStr;
	}

	public Date getEvalidity() {
		return evalidity;
	}

	public void setEvalidity(Date evalidity) {
		this.evalidity = evalidity;
	}

	public String getEvalidityStr() {
		if(evalidity == null || "".equals(evalidity)) return "-";
		evalidityStr = DateUtil.formatDate(evalidity,"yyyy-MM-dd");
		return evalidityStr;
	}

	public void setEvalidityStr(String evalidityStr) {
		this.evalidityStr = evalidityStr;
	}

	public String getSdateStr() {
		if(sdate == null || "".equals(sdate)) return "-";
		sdateStr = DateUtil.formatDate(sdate,"yyyy-MM-dd");
		return sdateStr;
	}

	public void setSdateStr(String sdateStr) {
		this.sdateStr = sdateStr;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Date getSdate() {
		return sdate;
	}

	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}

	public Date getEdate() {
		return edate;
	}

	public void setEdate(Date edate) {
		this.edate = edate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getStatusStr() {
		if(status == null && "".equals(status)){
			return "-";
		}else{
			switch(status){
			case 0:statusStr="未验证";
			case 1:statusStr="审核中";
			case 2:statusStr="未通过";
			case 3:statusStr="已签约";
			case 4:statusStr="未签约";
			}
		}
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getSellerid() {
		return sellerid;
	}

	public void setSellerid(Integer sellerid) {
		this.sellerid = sellerid;
	}

	public String getSellername() {
		return sellername;
	}

	public void setSellername(String sellername) {
		this.sellername = sellername;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public DecimalFormat getDf() {
		return df;
	}

	public void setDf(DecimalFormat df) {
		this.df = df;
	}



	public Integer getSaasType() {
		return saasType;
	}

	public void setSaasType(Integer saasType) {
		this.saasType = saasType;
	}

	@Override
	public String toString() {
		return "XmerSeller [id=" + id + ", uid=" + uid + ", sellerid="
				+ sellerid + ", sellername=" + sellername + ", address="
				+ address + ", typename=" + typename + ", status=" + status
				+ ", evalidity=" + evalidity + ", evalidityStr=" + evalidityStr
				+ ", sdate=" + sdate + ", sdateStr=" + sdateStr + ", edate="
				+ edate + ", updateDate=" + updateDate + ", phoneid=" + phoneid
				+ ", balance=" + balance + ", balanceStr=" + balanceStr
				+ ", uidtogetseller=" + uidtogetseller + ", df=" + df + "]";
	}

}
