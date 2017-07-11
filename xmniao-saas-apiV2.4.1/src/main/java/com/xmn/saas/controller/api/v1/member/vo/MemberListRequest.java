package com.xmn.saas.controller.api.v1.member.vo;

import org.springframework.beans.BeanUtils;

import com.xmn.saas.base.Request;

/**
 * 
*      
* 类名称：MemberListRequest   
* 类描述：   查询会员列表请求参数
* 创建人：xiaoxiong   
* 创建时间：2016年9月29日 下午3:47:02   
* 修改人：xiaoxiong   
* 修改时间：2016年9月29日 下午3:47:02   
* 修改备注：   
* @version    
*
 */
public class MemberListRequest extends Request{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 每页显示数量
	 */
	private int pageSize=20;
	
	/**
	 * 当前页
	 */
	private int pageNo=1;
	
	/**
	 * 用户类型 0全部  1绑定会员 2非绑定会员
	 */
	private Integer userType=0;
	
	/**
	 * 查询周期  ，0全部 1绑定会员  2非绑定会员
	 */
	private Integer searchType=0;
	
	/**
	 * 商家ID
	 */
	private int sellerid;
	
	/**
	 * 结束时间
	 */
	private String edate;
	
	/**
	 * 开始时间时间
	 */
	private String sdate;
	
	
	
	
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

	public int getSellerid() {
		return sellerid;
	}

	public void setSellerid(int sellerid) {
		this.sellerid = sellerid;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}


	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public void setSearchType(Integer searchType) {
		this.searchType = searchType;
	}

	public int getSearchType() {
		return searchType;
	}

	public void setSearchType(int searchType) {
		this.searchType = searchType;
	}


	public MemberListParams toMemberListParams(Integer sellerid){
		MemberListParams memberListParams=new MemberListParams();
		BeanUtils.copyProperties(this, memberListParams);
		memberListParams.setSellerid(sellerid);
		
		return memberListParams;
	}
	

}
