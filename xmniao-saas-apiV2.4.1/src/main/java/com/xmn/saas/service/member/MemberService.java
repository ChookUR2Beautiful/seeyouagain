package com.xmn.saas.service.member;

import java.util.Date;
import java.util.List;

import com.xmn.saas.base.thrift.common.ResponseData;
import com.xmn.saas.controller.api.v1.member.vo.MemberListParams;
import com.xmn.saas.controller.api.v1.member.vo.MemberListRequest;
import com.xmn.saas.entity.member.MemberDetail;
import com.xmn.saas.entity.member.MemberList;
import com.xmn.saas.entity.member.MemberStatistics;

/**
 * 
*      
* 类名称：MemberService   
* 类描述：会员管理   
* 创建人：xiaoxiong   
* 创建时间：2016年9月29日 下午5:38:12   
* 修改人：xiaoxiong   
* 修改时间：2016年9月29日 下午5:38:12   
* 修改备注：   
* @version    
*
 */
public interface MemberService {
	
	/**
	 * 
	 * @Description: 查询会员详细信息
	 * @author xiaoxiong
	 * @date 2016年9月29日
	 */
	MemberDetail detail(Integer uid,int sellerid);
	/**
	 * 
	 * @Description: 查询用户信息
	 * @author xiaoxiong
	 * @date 2016年9月30日
	 */
	ResponseData getThriftUserInfo(Integer uid);
	
	/**
	 * 
	 * @Description: 查询会员
	 * @author xiaoxiong
	 * @date 2016年9月30日
	 */
	List<MemberList> list(MemberListParams request);
	
	/**
	 * 
	 * @Description: 查询会员统计
	 * @author xiaoxiong
	 * @date 2016年10月13日
	 */
	MemberStatistics statistics(Integer userType, Integer searchType,int sellerid,String sdate,String edate);

}
