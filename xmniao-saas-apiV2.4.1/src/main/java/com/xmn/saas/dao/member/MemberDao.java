package com.xmn.saas.dao.member;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xmn.saas.controller.api.v1.member.vo.MemberListParams;
import com.xmn.saas.controller.api.v1.member.vo.MemberListRequest;
import com.xmn.saas.entity.member.MemberBill;
import com.xmn.saas.entity.member.MemberDetail;


@Repository
public interface MemberDao {
	
	/**
	 * 
	 * @Description: 查询用户详细信息
	 * @author xiaoxiong
	 * @date 2016年9月29日
	 */
	MemberDetail detail(@Param("uid")Integer uid,@Param("sellerid")int sellerid);
	
	/**
	 * 
	 * @Description: 根据商户ID查询会员列表
	 * @author xiaoxiong
	 * @date 2016年9月30日
	 */
	List<MemberBill> queryBillBySellerid(MemberListParams request);
	
	/**
	 * 
	 * @Description: 查寻总会员人数
	 * @author xiaoxiong
	 * @date 2016年10月13日
	 */
	int queryMemberAllCount(@Param("sellerid")int sellerid,@Param("userType")Integer userType,@Param("searchType")Integer searchType,@Param("sdate")String sdate,@Param("edate")String edate);
	
	
	
	

}
