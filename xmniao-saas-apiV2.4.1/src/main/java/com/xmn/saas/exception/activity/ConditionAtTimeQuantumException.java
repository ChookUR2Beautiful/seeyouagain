package com.xmn.saas.exception.activity;

/**
 * 最近抽取记录在重设时间段内
 * @author jianming
 *
 */
public class ConditionAtTimeQuantumException extends ActivityException{
	private String detailMessage = "你已抽过奖,请在活动重设后再参加抽奖";
}
