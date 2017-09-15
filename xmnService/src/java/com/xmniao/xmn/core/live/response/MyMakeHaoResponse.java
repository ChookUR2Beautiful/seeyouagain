package com.xmniao.xmn.core.live.response;

public class MyMakeHaoResponse {
	
	/**
	 * 累计鸟币
	 */
	private String sumBirdCoin ;
	
	/**
	 * 预计鸟币
	 */
	private String gaveBirdCoin;
	
	/**
	 * 嚎友数量
	 */
	private int haoCount;
	
	/**
	 * 等级
	 */
	private int level;
	
	/**
	 * 可用鸟币
	 */
	private String canUseBirdCoin;

	/**
	 * 是否可以邀请好友
	 */
	private int isInvitFriend ;
	
	
	private int ledgerId;
	
	private String levelName;
	
	private String levelNewImg;
	
	private String levelImg;
	
	private String sellerCoin;//商家专享(充值卡的鸟币总余额)
	
	private String zbalance;//鸟粉卡鸟币(属于公共部分的鸟币)
	
	public String getLevelNewImg() {
		return levelNewImg;
	}


	public void setLevelNewImg(String levelNewImg) {
		this.levelNewImg = levelNewImg;
	}


	public String getLevelImg() {
		return levelImg;
	}


	public void setLevelImg(String levelImg) {
		this.levelImg = levelImg;
	}


	public String getLevelName() {
		return levelName;
	}


	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}


	public int getLedgerId() {
		return ledgerId;
	}


	public void setLedgerId(int ledgerId) {
		this.ledgerId = ledgerId;
	}


	/**
	 * 是否有壕礼
	 */
	private int isHaoLi;


	public String getSumBirdCoin() {
		return sumBirdCoin;
	}


	public void setSumBirdCoin(String sumBirdCoin) {
		this.sumBirdCoin = sumBirdCoin;
	}


	public String getGaveBirdCoin() {
		return gaveBirdCoin;
	}


	public void setGaveBirdCoin(String gaveBirdCoin) {
		this.gaveBirdCoin = gaveBirdCoin;
	}


	public int getHaoCount() {
		return haoCount;
	}


	public void setHaoCount(int haoCount) {
		this.haoCount = haoCount;
	}


	public int getLevel() {
		return level;
	}


	public void setLevel(int level) {
		this.level = level;
	}


	public String getCanUseBirdCoin() {
		return canUseBirdCoin;
	}


	public void setCanUseBirdCoin(String canUseBirdCoin) {
		this.canUseBirdCoin = canUseBirdCoin;
	}


	public int getIsInvitFriend() {
		return isInvitFriend;
	}


	public void setIsInvitFriend(int isInvitFriend) {
		this.isInvitFriend = isInvitFriend;
	}


	public int getIsHaoLi() {
		return isHaoLi;
	}


	public void setIsHaoLi(int isHaoLi) {
		this.isHaoLi = isHaoLi;
	}


	public String getSellerCoin() {
		return sellerCoin;
	}


	public void setSellerCoin(String sellerCoin) {
		this.sellerCoin = sellerCoin;
	}


	public String getZbalance() {
		return zbalance;
	}


	public void setZbalance(String zbalance) {
		this.zbalance = zbalance;
	}


	@Override
	public String toString() {
		return "MyMakeHaoResponse [sumBirdCoin=" + sumBirdCoin + ", gaveBirdCoin=" + gaveBirdCoin + ", haoCount=" + haoCount
			+ ", level=" + level + ", canUseBirdCoin=" + canUseBirdCoin + ", isInvitFriend=" + isInvitFriend + ", ledgerId="
			+ ledgerId + ", levelName=" + levelName + ", levelNewImg=" + levelNewImg + ", levelImg=" + levelImg + ", sellerCoin="
			+ sellerCoin + ", zbalance=" + zbalance + ", isHaoLi=" + isHaoLi + "]";
	}
	
	
}
