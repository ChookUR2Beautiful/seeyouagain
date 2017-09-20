package com.xmniao.xmn.core.fresh.entity;

import java.util.Date;
import java.util.List;

import com.xmniao.xmn.core.base.BaseEntity;
/**
 *@ClassName:PostageTemplate
 *@Description:运费模板实体
 *@author hls
 *@date:2016年6月20日下午3:51:32
 */
public class PostageTemplate extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7795563132279865187L;

	private Integer tid = 0;//模板id
	
	private List<TPostageRule> postageRuleList;//指定地区邮费列表
	
	private List<TPostageFreeRule> postageFreeRuleList;//指定条件包邮列表
	
	private Integer status =0;// 0正常，1 废弃
	
	private String title;//模板标题
	private String copyTitle;//复制模板标题
	
	private String postageRule;//指定地区邮费列表(用于显示)
	
	private String freeRule;//指定条件包邮列表(用于显示)
	
	private Integer defaultTemplate;//是否是默认模板 0否 1是（默认值0）
	
	private Date udate;//模板更新时间
	
	private String areaTitle;//区域名称
	
	//add by lifeng 20160713
	private TPostageRule tPostageRule;
	private Integer postageRuleSize;
	
	public String getAreaTitle() {
		return areaTitle;
	}
	public void setAreaTitle(String areaTitle) {
		this.areaTitle = areaTitle;
	}
	private Integer limit;
	
	public String getCopyTitle() {
		return copyTitle;
	}
	public void setCopyTitle(String copyTitle) {
		this.copyTitle = copyTitle;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Date getUdate() {
		return udate;
	}
	public void setUdate(Date udate) {
		this.udate = udate;
	}
	public Integer getDefaultTemplate() {
		return defaultTemplate;
	}
	public void setDefaultTemplate(Integer defaultTemplate) {
		this.defaultTemplate = defaultTemplate;
	}
	public String getPostageRule() {
		return postageRule;
	}
	public void setPostageRule(String postageRule) {
		this.postageRule = postageRule;
	}
	public String getFreeRule() {
		return freeRule;
	}
	public void setFreeRule(String freeRule) {
		this.freeRule = freeRule;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public List<TPostageRule> getPostageRuleList() {
		return postageRuleList;
	}
	public void setPostageRuleList(List<TPostageRule> postageRuleList) {
		this.postageRuleList = postageRuleList;
	}
	public List<TPostageFreeRule> getPostageFreeRuleList() {
		return postageFreeRuleList;
	}
	public void setPostageFreeRuleList(List<TPostageFreeRule> postageFreeRuleList) {
		this.postageFreeRuleList = postageFreeRuleList;
	}
	
	public TPostageRule gettPostageRule() {
		return tPostageRule;
	}
	public void settPostageRule(TPostageRule tPostageRule) {
		this.tPostageRule = tPostageRule;
	}
	
	public Integer getPostageRuleSize() {
		return postageRuleSize;
	}
	public void setPostageRuleSize(Integer postageRuleSize) {
		this.postageRuleSize = postageRuleSize;
	}
	@Override
	public String toString() {
		return "PostageTemplate [tid=" + tid + ", postageRuleList="
				+ postageRuleList + ", postageFreeRuleList="
				+ postageFreeRuleList + ", status=" + status + ", title="
				+ title + ", copyTitle=" + copyTitle + ", postageRule="
				+ postageRule + ", freeRule=" + freeRule + ", defaultTemplate="
				+ defaultTemplate + ", udate=" + udate + ", areaTitle="
				+ areaTitle + ", tPostageRule=" + tPostageRule + ", limit="
				+ limit + "]";
	}
	
}
