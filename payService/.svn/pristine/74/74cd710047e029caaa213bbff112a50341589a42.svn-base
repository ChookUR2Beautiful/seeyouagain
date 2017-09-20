package com.xmniao.entity;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import com.xmniao.common.ParamUtil;
import com.xmniao.common.XmnConstants;
import com.xmniao.service.WalletExService;

public class ExpansionManage {

	/**
	 * 提现金额限制类型 1:固定类型
	 */
	public static final Integer EXPENSES_TYPE_1 = 1;

	/**
	 * V客红包最大提现上线
	 */
	public static final Expenses EX_RTYPE_3_WITHDRAW = new Expenses(EXPENSES_TYPE_1, BigDecimal.valueOf(1000));

	public static final Integer SUCCESS_STATE = 0;

	public static final Integer DEFALT_STATE = 1;

	private WalletExService walletExService;

	public ExpansionManage(WalletExService walletExService) {
		this.walletExService = walletExService;
	}

	public Expenses getAvailableAmount(WalletExpansion wa, BigDecimal withdrawAmount) {
		Expenses expenses = new Expenses();
		int type = wa.getType().intValue();
		if(type==XmnConstants.EX_RTYPE_1){
			//V客推荐钱包可转出金额=amount-上周到现在的收益总和<0?0:(amount-上周到现在的收益总和)
			 BigDecimal availableAmount = walletExService.getExrtype1availableAmount(wa.getAccountid());
			 if(availableAmount.compareTo(BigDecimal.valueOf(0))<=0||availableAmount.compareTo(withdrawAmount)<0){
				 expenses.setState(DEFALT_STATE);
				 expenses.setMsg("钱包可转出余额不足!");	
				 expenses.setAmount(availableAmount);
				}
			 else{
				 expenses.setState(SUCCESS_STATE);
				 expenses.setAmount(withdrawAmount);
				 expenses.setMsg("操作成功!");
			 }
			 return expenses;
		}else if(type==XmnConstants.EX_RTYPE_3){
			//V客红包每天最多转出1000
			BigDecimal amount = walletExService.getExrtype3TodayRecord(wa);
			if(amount.compareTo(EX_RTYPE_3_WITHDRAW.getMaxAmount())>=0){
				expenses.setState(DEFALT_STATE);
				 expenses.setMsg("当日可转出余额已到达上限,请明天再来!");	
				 expenses.setAmount(amount);
			}else{
				BigDecimal subtract = EX_RTYPE_3_WITHDRAW.getMaxAmount().subtract(amount);
				expenses.setState(SUCCESS_STATE);
				expenses.setMsg("成功转出余额:"+(subtract.compareTo(withdrawAmount)>0?withdrawAmount:subtract));	
				expenses.setAmount(subtract.compareTo(withdrawAmount)>0?withdrawAmount:subtract);
			}
		}else{
			if(withdrawAmount.compareTo(wa.getAmount())>0){
				 expenses.setState(DEFALT_STATE);
				 expenses.setMsg("钱包可转出余额不足!");	
				 expenses.setAmount(wa.getAmount());
			}else{
				expenses.setState(SUCCESS_STATE);
				expenses.setAmount(withdrawAmount);
				expenses.setMsg("操作成功!");
			}
		}
		return expenses;
	}

	public static class Expenses {

		private Integer type;
		private BigDecimal amount;
		private Integer state;
		private String msg;

		public Expenses() {

		}

		public Expenses(Integer type, BigDecimal amount) {
			this.type = type;
			this.amount = amount;
		}

		public BigDecimal getMaxAmount() {
			if (type == EXPENSES_TYPE_1) {
				return amount;
			} else {
				return null;
			}
		}

		public Integer getType() {
			return type;
		}

		public void setType(Integer type) {
			this.type = type;
		}

		public BigDecimal getAmount() {
			return amount;
		}

		public void setAmount(BigDecimal amount) {
			this.amount = amount;
		}

		public Integer getState() {
			return state;
		}

		public void setState(Integer state) {
			this.state = state;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

	}

}
