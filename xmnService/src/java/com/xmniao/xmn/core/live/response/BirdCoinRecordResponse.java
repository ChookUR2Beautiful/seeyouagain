package com.xmniao.xmn.core.live.response;

import java.math.BigDecimal;
import java.math.RoundingMode;


/**
 * 
* 类名称：BirdCoinRecordResponse   
* 类描述：   鸟币记录奖励或消费返回实体类
* 创建人：xiaoxiong   
* 创建时间：2016年12月22日 下午2:01:37
 */
public class BirdCoinRecordResponse {
	
	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 类型
	 */
	private int type;
	
	/**
	 * 鸟币数
	 */
	private String birdCoin;
	
	/**
	 * 日期
	 */
	private String sdate;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	

	public String getBirdCoin() {
		return birdCoin;
	}

	public void setBirdCoin(String birdCoin) {
		this.birdCoin = birdCoin;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	
	
	
}
