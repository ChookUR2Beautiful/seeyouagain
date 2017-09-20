/**   
 * 文件名：TComment.java   
 *    
 * 日期：2014年11月11日16时00分41秒  
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司     
 */
package com.xmniao.xmn.core.businessman.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;


/**
 * 项目名称：XmnWeb
 * 
 * 类名称：TComment
 * 
 * 类描述：商家评论
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月11日16时00分41秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TComment extends BaseEntity  {
	
	private static final long serialVersionUID = 6690372544661856967L;
	private Integer cid;// 评论ID
	private Integer sellerid;// 商家ID
	private Integer uid;// 用户ID
	private String nname;// 用户昵称
	private String content;// 点评内容
	private Date sdate;// 点评时间
	private Integer number;// 回复次数
	private Integer total;// 点评总分
	private Integer hbranch;// 环境分
	private Integer fbranch;// 服务分
	private Integer kbranch;// 口味分
	private Integer jbranch;// 房间分
	private Integer percapita;// 人均消费
	private Integer status;// 0=未审核|1=已审核
	private Short isDel;
	
	//新增虚拟字段
	private String sellername;//商家名称
	private Date sdateStart;//点评时间查询条件
	private Date sdateEnd;//点评时间查询条件
	
	
	public String getStatusText(){
		if(status == null) return null;
		return status == 0 ? "未审核" : "已审核";
	}
	/**   
	 * 创建一个新的实例 TComment.   
	 *      
	 */
	public TComment() {
		super();
	}
	
	public TComment(Integer cid) {
		this.cid = cid;
	}

	
	/**
	 * cid
	 * 
	 * @return the cid
	 */
	public Integer getCid() {
		return cid;
	}

	/**
	 * @param cid
	 *            the cid to set
	 */
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	
	/**
	 * sellerid
	 * 
	 * @return the sellerid
	 */
	public Integer getSellerid() {
		return sellerid;
	}

	/**
	 * @param sellerid
	 *            the sellerid to set
	 */
	public void setSellerid(Integer sellerid) {
		this.sellerid = sellerid;
	}
	
	/**
	 * uid
	 * 
	 * @return the uid
	 */
	public Integer getUid() {
		return uid;
	}

	/**
	 * @param uid
	 *            the uid to set
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	
	/**
	 * nname
	 * 
	 * @return the nname
	 */
	public String getNname() {
		return nname;
	}

	/**
	 * @param nname
	 *            the nname to set
	 */
	public void setNname(String nname) {
		this.nname = nname;
	}
	
	/**
	 * content
	 * 
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * sdate
	 * 
	 * @return the sdate
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getSdate() {
		return sdate;
	}

	/**
	 * @param sdate
	 *            the sdate to set
	 */
	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}
	
	/**
	 * number
	 * 
	 * @return the number
	 */
	public Integer getNumber() {
		return number;
	}

	/**
	 * @param number
	 *            the number to set
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	/**
	 * total
	 * 
	 * @return the total
	 */
	public Integer getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(Integer total) {
		this.total = total;
	}
	
	/**
	 * hbranch
	 * 
	 * @return the hbranch
	 */
	public Integer getHbranch() {
		return hbranch;
	}

	/**
	 * @param hbranch
	 *            the hbranch to set
	 */
	public void setHbranch(Integer hbranch) {
		this.hbranch = hbranch;
	}
	
	/**
	 * fbranch
	 * 
	 * @return the fbranch
	 */
	public Integer getFbranch() {
		return fbranch;
	}

	/**
	 * @param fbranch
	 *            the fbranch to set
	 */
	public void setFbranch(Integer fbranch) {
		this.fbranch = fbranch;
	}
	
	/**
	 * kbranch
	 * 
	 * @return the kbranch
	 */
	public Integer getKbranch() {
		return kbranch;
	}

	/**
	 * @param kbranch
	 *            the kbranch to set
	 */
	public void setKbranch(Integer kbranch) {
		this.kbranch = kbranch;
	}
	
	/**
	 * jbranch
	 * 
	 * @return the jbranch
	 */
	public Integer getJbranch() {
		return jbranch;
	}

	/**
	 * @param jbranch
	 *            the jbranch to set
	 */
	public void setJbranch(Integer jbranch) {
		this.jbranch = jbranch;
	}
	
	/**
	 * percapita
	 * 
	 * @return the percapita
	 */
	public Integer getPercapita() {
		return percapita;
	}

	/**
	 * @param percapita
	 *            the percapita to set
	 */
	public void setPercapita(Integer percapita) {
		this.percapita = percapita;
	}
	
	/**
	 * status
	 * 
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Short getIsDel() {
		return isDel;
	}
	public void setIsDel(Short isDel) {
		this.isDel = isDel;
	}
	public Date getSdateStart() {
		return sdateStart;
	}

	public void setSdateStart(Date sdateStart) {
		this.sdateStart = sdateStart;
	}

	public Date getSdateEnd() {
		return sdateEnd;
	}

	public void setSdateEnd(Date sdateEnd) {
		this.sdateEnd = sdateEnd;
	}

	public String getSellername() {
		return sellername;
	}

	public void setSellername(String sellername) {
		this.sellername = sellername;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TComment [cid=" + cid + ", sellerid=" + sellerid + ", uid=" + uid + ", nname=" + nname + ", content=" + content + ", sdate=" + sdate + ", number=" + number + ", total=" + total + ", hbranch=" + hbranch + ", fbranch=" + fbranch + ", kbranch=" + kbranch + ", jbranch=" + jbranch + ", percapita=" + percapita + ", status=" + status + ", ]";
	}
}
