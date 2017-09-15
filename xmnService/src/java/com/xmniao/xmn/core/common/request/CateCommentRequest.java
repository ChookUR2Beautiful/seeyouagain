package com.xmniao.xmn.core.common.request;

import net.sf.oval.constraint.Max;
import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：CateCommentRequest   
* 类描述：   发表评论请求参数
* 创建人：yezhiyong   
* 创建时间：2016年6月21日 上午11:05:30   
* @version    
*
 */
public class CateCommentRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -173187269141201104L;

	@NotNull(message="订单号不能为空")
	private Long bid;//订单号
	
	@NotNull(message="商户id不能为空")
	private Integer sellerid;//商户id
	
	@NotNull(message="服务评分不能为空")
	@Max(5)
	private Integer fbranch;//服务评分
	
	@NotNull(message="味道评分不能为空")
	@Max(5)
	private Integer kbranch;//味道评分
	
	@NotNull(message="特色评分不能为空")
	@Max(5)
	private Integer jbranch;//特色评分
	
	@NotNull(message="环境评分不能为空")
	@Max(5)
	private Integer hbranch;//环境评分

	public Long getBid() {
		return bid;
	}

	public void setBid(Long bid) {
		this.bid = bid;
	}

	public Integer getSellerid() {
		return sellerid;
	}

	public void setSellerid(Integer sellerid) {
		this.sellerid = sellerid;
	}

	public Integer getFbranch() {
		return fbranch;
	}

	public void setFbranch(Integer fbranch) {
		this.fbranch = fbranch;
	}

	public Integer getKbranch() {
		return kbranch;
	}

	public void setKbranch(Integer kbranch) {
		this.kbranch = kbranch;
	}

	public Integer getJbranch() {
		return jbranch;
	}

	public void setJbranch(Integer jbranch) {
		this.jbranch = jbranch;
	}

	public Integer getHbranch() {
		return hbranch;
	}

	public void setHbranch(Integer hbranch) {
		this.hbranch = hbranch;
	}

	@Override
	public String toString() {
		return "CateCommentRequest [bid=" + bid + ", sellerid=" + sellerid
				+ ", fbranch=" + fbranch + ", kbranch=" + kbranch
				+ ", jbranch=" + jbranch + ", hbranch=" + hbranch + "]";
	}
	
}
