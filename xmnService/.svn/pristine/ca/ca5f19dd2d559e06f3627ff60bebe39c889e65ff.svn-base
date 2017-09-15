package com.xmniao.xmn.core.common.request.personal;

import net.sf.oval.constraint.MaxLength;
import net.sf.oval.constraint.MinLength;
import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：DeletePersonalMentionAccountRequest   
* 类描述：   解绑用户提现银行卡请求参数
* 创建人：yezhiyong   
* 创建时间：2016年11月26日 下午2:30:40   
* @version    
*
 */
public class PersonalUnbundlingAccountRequest extends BaseRequest{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2547063733281196236L;
	
	/**
	 * 银行卡账号id
	 */
	@NotNull(message="银行卡账号id不能为空")
	private Integer bankId;
	
	/**
	 * 银行卡号
	 */
	@NotNull(message="银行卡号不能为空")
	@MaxLength(19)
	@MinLength(16)
	private String bankAccount;

	public Integer getBankId() {
		return bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	@Override
	public String toString() {
		return "PersonalUnbundlingAccountRequest [bankId=" + bankId
				+ ", bankAccount=" + bankAccount + "]";
	}
	
}
