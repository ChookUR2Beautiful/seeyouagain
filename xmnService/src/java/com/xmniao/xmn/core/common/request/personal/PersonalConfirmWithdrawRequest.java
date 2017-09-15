package com.xmniao.xmn.core.common.request.personal;

import net.sf.oval.constraint.Max;
import net.sf.oval.constraint.Min;
import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：PersonalConfirmWithdrawRequest   
* 类描述：   用户申请提现请求参数
* 创建人：yezhiyong   
* 创建时间：2016年11月29日 下午5:45:45   
* @version    
*
 */
public class PersonalConfirmWithdrawRequest extends BaseRequest{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2334942448058342847L;
	
	/**
	 * 提现金额
	 */
	@NotNull(message="提现金额不能为空")
	@Min(2)
	@Max(50000)
	private Double amount;
	
	/**
	 * 银行卡账号
	 */
	@NotNull(message="银行卡账号不能为空")
	private Integer bankId;

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getBankId() {
		return bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	@Override
	public String toString() {
		return "PersonalConfirmWithdrawRequest [amount=" + amount + ", bankId="
				+ bankId + "]";
	}
	
}
