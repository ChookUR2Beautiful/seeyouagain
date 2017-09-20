package com.xmniao.xmn.core.manor.dao;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.manor.entity.TManorInfo;
import com.xmniao.xmn.core.reward_dividends.entity.BursEarningsRelation;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public interface ManorInfoDao extends BaseDao<TManorInfo>{
	@DataSource("burs")
    int deleteByPrimaryKey(Integer id);

	@DataSource("burs")
    Integer insert(TManorInfo record);

	@DataSource("burs")
    int insertSelective(TManorInfo record);

	@DataSource("burs")
    TManorInfo selectByPrimaryKey(Integer id);

	@DataSource("burs")
    int updateByPrimaryKeySelective(TManorInfo record);

	@DataSource("burs")
    int updateByPrimaryKey(TManorInfo record);
    
    
	@DataSource("burs")
	public List<TManorInfo> getManorMemberList(TManorInfo record);
	
	@DataSource("burs")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Long countManorMember(TManorInfo record);
	
	@DataSource("burs")
	public List<Map<String, String>> getManorMemberLevelNameList(Object[] objects);

	@DataSource("burs")
	List<TManorInfo> selectByUids(@Param("allUsers") LinkedList<BursEarningsRelation> allUsers);

	/**
	 * 方法描述：根据父id查询
	 * 创建人： jianming <br/>
	 * 创建时间：2017年9月7日下午4:51:42 <br/>
	 * @param uid
	 * @return
	 */
	@DataSource("burs")
	List<TManorInfo> getBySuperUid(Integer uid);
	
	@DataSource("burs")
	public TManorInfo getObject(Long id);
}