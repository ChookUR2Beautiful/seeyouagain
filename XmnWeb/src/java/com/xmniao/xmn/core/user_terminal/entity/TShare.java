/**   
 * 文件名：TShare.java   
 *    
 * 日期：2014年12月8日18时22分21秒  
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司     
 */
package com.xmniao.xmn.core.user_terminal.entity;
import java.util.List;

import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.coupon.entity.TCouponCity;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：TShare
 * 
 * 类描述：分享信息
 * 
 * 创建人： cao'yingde
 * 
 * 创建时间：2015年06月26日15时22分21秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TShare extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6804410167296325478L;
	private Integer sid;
	private String title;//分享标题
	private String link;//分享链接
	private Integer range;//分享范围，1全国，2指定城市，3指定商家
	private String sellerid;//商家ID
	private String sellername;
	
	private List<TShareRange> shareRange;// 分享范围

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Integer getRange() {
		return range;
	}

	public void setRange(Integer range) {
		this.range = range;
	}

	public List<TShareRange> getShareRange() {
		return shareRange;
	}

	public void setShareRange(List<TShareRange> shareRange) {
		this.shareRange = shareRange;
	}
	
	public String getSellerid() {
		return sellerid;
	}

	public void setSellerid(String sellerid) {
		this.sellerid = sellerid;
	}

	public String getSellername() {
		return sellername;
	}

	public void setSellername(String sellername) {
		this.sellername = sellername;
	}

	@Override
	public String toString() {
		return "TShare [sid=" + sid + ", title=" + title + ", link=" + link
				+ ", range=" + range + ", shareRange=" + shareRange + "]";
	}
}
