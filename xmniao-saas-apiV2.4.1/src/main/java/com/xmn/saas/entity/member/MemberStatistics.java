package com.xmn.saas.entity.member;


/**
*      
* 类名称：MemberStatistics   
* 类描述：会员统计   
* 创建人：xiaoxiong   
* 创建时间：2016年10月12日 下午5:53:14     
*
 */
public class MemberStatistics {
	
	/**
	 * 
	 */
	private int allCount;
	
	/**
	 * 新增客户数量
	 */
	private int addCount;
	
	/**
	 * 开始时间
	 */
	private String sdate;
	/**
	 * 结束时间
	 */
	private String edate;
	
	
	
	
	public int getAllCount() {
		return allCount;
	}
	public void setAllCount(int allCount) {
		this.allCount = allCount;
	}
	public int getAddCount() {
		return addCount;
	}
	public void setAddCount(int addCount) {
		this.addCount = addCount;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public String getEdate() {
		return edate;
	}
	public void setEdate(String edate) {
		this.edate = edate;
	}

	
	
}
