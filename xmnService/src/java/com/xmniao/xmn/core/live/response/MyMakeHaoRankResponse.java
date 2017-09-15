package com.xmniao.xmn.core.live.response;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MyMakeHaoRankResponse {
	
	/**
	 * 头像
	 */
	private String avatar;
	 
	/**
	 * 排名
	 */
	private int rankNo;
	
	/**
	 * 鸟币
	 */
	private String birdCoin;
	
	/**
	 * 用户ID
	 */
	private int uid;

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public int getRankNo() {
		return rankNo;
	}

	public void setRankNo(int rankNo) {
		this.rankNo = rankNo;
	}

	

	public String getBirdCoin() {
		return birdCoin;
	}

	public void setBirdCoin(String birdCoin) {
		this.birdCoin = birdCoin;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}
	
	
	
	
}
