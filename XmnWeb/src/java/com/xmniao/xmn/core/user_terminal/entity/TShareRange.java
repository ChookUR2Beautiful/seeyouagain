/**   
 * 文件名：TShare.java   
 *    
 * 日期：2014年12月8日18时22分21秒  
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司     
 */
package com.xmniao.xmn.core.user_terminal.entity;
import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：TShareRange
 * 
 * 类描述：分享信息范围
 * 
 * 创建人： cao'yingde
 * 
 * 创建时间：2015年06月26日15时22分21秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TShareRange extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4994757450201582768L;
	private Integer rid;
	private Integer sid;//分享信息ID
	private Integer rangetype;//范围类型，2指定城市，3指定商家
	private String rangecontent;//城市或者商家ID
	private String province;//省编号（若为商家是为空）
	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Integer getRangetype() {
		return rangetype;
	}

	public void setRangetype(Integer rangetype) {
		this.rangetype = rangetype;
	}

	public String getRangecontent() {
		return rangecontent;
	}

	public void setRangecontent(String rangecontent) {
		this.rangecontent = rangecontent;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Override
	public String toString() {
		return "TShare [rid=" + rid + ", sid=" + sid + ", rangetype=" + rangetype
				+ ", rangecontent=" + rangecontent + ", province=" + province +"]";
	}
}
