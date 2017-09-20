package com.xmniao.xmn.core.live_anchor.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.live_anchor.entity.TSellerPackageIssueRef;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

public interface TSellerPackageIssueRefDao extends BaseDao<TSellerPackageIssueRef>{
	@DataSource("master")
    int deleteByPrimaryKey(Integer id);

	@DataSource("master")
    int insert(TSellerPackageIssueRef record);

	@DataSource("master")
    int insertSelective(TSellerPackageIssueRef record);

    TSellerPackageIssueRef selectByPrimaryKey(Integer id);

    @DataSource("master")
    int updateByPrimaryKeySelective(TSellerPackageIssueRef record);

    @DataSource("master")
    int updateByPrimaryKey(TSellerPackageIssueRef record);
    
    @DataSource("master")
    List<TSellerPackageIssueRef> getDataListByPid(Integer pid);
    
	@DataSource("master")
    int deleteSellerPackageIssueRef(List<Integer> sellerPackageIssueRefIds);  //Object[] objects  List<Integer> sellerPackageIssueRefIds
	
	@DataSource("master")
	List<TSellerPackageIssueRef> getSellerPackageIssueRefListByPid(Integer pid);
	
	
	@DataSource("master")
	List<TSellerPackageIssueRef> getSellerPackageIssueRefDataList();
    
}