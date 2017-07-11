package com.xmn.saas.exception.activity;

/**
 * 超出可领取次数
 * @author jianming
 *
 */
public class MaxLimitNumberException extends ActivityException {
	private String detailMessage = "你已领过奖品,请下次再参加";
}
