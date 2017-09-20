package com.xmniao.xmn.core.live_anchor.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.live_anchor.entity.BVerAnchorRelation;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;


public interface BVerAnchorRelationDao extends BaseDao<BVerAnchorRelation>{
	@DataSource(value = "burs")
    int deleteByPrimaryKey(Integer id);

    int insert(BVerAnchorRelation record);
    
	@DataSource(value = "burs")
    int insertSelective(BVerAnchorRelation record);

    BVerAnchorRelation selectByPrimaryKey(Integer id);
    
	@DataSource(value = "burs")
    int updateByPrimaryKeySelective(BVerAnchorRelation record);

    int updateByPrimaryKey(BVerAnchorRelation record);
    
	@DataSource(value = "burs")
    public List<BVerAnchorRelation> getAnchorRecruitDataList (BVerAnchorRelation record);
	
	@DataSource(value = "burs")
    public Long countAnchorRecruit(BVerAnchorRelation record);
	
	@DataSource(value = "burs")
    int updateOptionState(BVerAnchorRelation record);
	
	@DataSource(value = "burs")
    public List<BVerAnchorRelation> getVerAnchorRelationList (BVerAnchorRelation record);
	
	@DataSource(value = "burs")
	public List<Map<String, String>> getRecommendLiveCountList(Object[] objects);
	
	@DataSource(value = "burs")
    public List<BVerAnchorRelation> getRecommendLiveList (Integer uid);
	
	@DataSource(value = "burs")
    int abandonVerAnchorRelation(BVerAnchorRelation record);
	
	/**
	 * 方法描述：根据主播uid, 查询推荐人<br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年8月31日上午10:30:11 <br/>
	 * @param liveUid
	 * @return
	 */
	@DataSource(value = "burs")
	Map<String, Object> getVkeNameByLiveUid(@Param("liveUid") Integer liveUid);
	
}