package com.xmniao.xmn.core.live_anchor.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.live_anchor.entity.TSellerPackagePic;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

public interface TSellerPackagePicDao extends BaseDao<TSellerPackagePic>{
	@DataSource("master")
	public int deleteByPrimaryKey(Integer id);

    @DataSource("master")
    public int insert(TSellerPackagePic record);

    @DataSource("master")
    public int insertSelective(TSellerPackagePic record);

    public TSellerPackagePic selectByPrimaryKey(Integer id);

    @DataSource("master")
    public int updateByPrimaryKeySelective(TSellerPackagePic record);

    @DataSource("master")
    public int updateByPrimaryKey(TSellerPackagePic record);
    
    @DataSource("master")
    public List<TSellerPackagePic> getDataListByPid(Integer pid);
    
    @DataSource("master")
    public void deleteDataListByPid(Integer id);
    
}