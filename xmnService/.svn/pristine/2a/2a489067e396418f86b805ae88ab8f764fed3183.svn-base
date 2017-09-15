package com.xmniao.xmn.core.common.request.personal;

import net.sf.oval.constraint.Max;
import net.sf.oval.constraint.MaxLength;
import net.sf.oval.constraint.Min;
import net.sf.oval.constraint.MinLength;
import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：AddPersonalMentionAccountRequest   
* 类描述：   新添银行卡
* 创建人：yezhiyong   
* 创建时间：2016年11月26日 下午2:42:24   
* @version    
*
 */
public class AddPersonalMentionAccountRequest extends BaseRequest{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2744881080558806809L;
	
	/**
	 * 持卡人姓名
	 */
	@NotNull(message="持卡人姓名不能为空")
	private String userName;
	
	/**
	 * 银行卡号,16~19位
	 */
	@NotNull(message="银行卡号不能为空")
	@MaxLength(19)
	@MinLength(16)
	private String bankAccount;
	
	/**
	 * 银行卡所属银行ID
	 */
	@NotNull(message="银行卡所属银行ID不能为空")
	private Integer bankListId;
	
	/**
	 * 银行所属支行
	 */
	@NotNull(message="银行所属支行名称不能为空")
	private String bankBranch;
	
	/**
	 * 证件类型 1 身份证2 护照3 驾驶证
	 */
	@Min(1)
	@Max(3)
	@NotNull(message="证件类型不能为空")
	private Integer idType;
	
	/**
	 * 证件号码
	 */
	@NotNull(message="证件号码不能为空")
	private String identity;
	
	/**
	 * 银行预留号码
	 */
	@NotNull(message="银行预留号码不能为空")
	private String mobileId;

	/**
	 * 省名称
	 */
	@NotNull(message="省名称不能为空")
	private String province;
	
	/**
	 * 市名称
	 */
	@NotNull(message="市名称不能为空")
	private String city;
	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public Integer getBankListId() {
		return bankListId;
	}

	public void setBankListId(Integer bankListId) {
		this.bankListId = bankListId;
	}

	public String getBankBranch() {
		return bankBranch;
	}

	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}

	public Integer getIdType() {
		return idType;
	}

	public void setIdType(Integer idType) {
		this.idType = idType;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getMobileId() {
		return mobileId;
	}

	public void setMobileId(String mobileId) {
		this.mobileId = mobileId;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "AddPersonalMentionAccountRequest [userName=" + userName
				+ ", bankAccount=" + bankAccount + ", bankListId=" + bankListId
				+ ", bankBranch=" + bankBranch + ", idType=" + idType
				+ ", identity=" + identity + ", mobileId=" + mobileId
				+ ", province=" + province + ", city=" + city + "]";
	}

}
