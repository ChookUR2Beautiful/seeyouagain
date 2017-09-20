package com.xmniao.xmn.core.live_anchor.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.live_anchor.entity.TLiveRecommendRecord;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;


public interface TLiveRecommendRecordDao  extends BaseDao<TLiveRecommendRecord>{
	@DataSource("slave")
    int deleteByPrimaryKey(Integer id);

    int insert(TLiveRecommendRecord record);
    @DataSource("slave")
    int insertSelective(TLiveRecommendRecord record);
    
    @DataSource("slave")
    TLiveRecommendRecord selectByPrimaryKey(Integer id);
    
    @DataSource("slave")
    int updateByPrimaryKeySelective(TLiveRecommendRecord record);
    
    @DataSource("slave")
    int updateByPrimaryKey(TLiveRecommendRecord record);
    
    @DataSource("slave")
    public List<TLiveRecommendRecord> getLiveRecommendRecordList(TLiveRecommendRecord record);
    
    @DataSource("slave")
    public Long countLiveRecommendRecord(TLiveRecommendRecord record);
    
    @DataSource("slave")
    public List<TLiveRecommendRecord> getFreshmanRecommendRecordList(TLiveRecommendRecord record);
    
    @DataSource("slave")
    public Long countFreshmanRecommendRecord(TLiveRecommendRecord record);

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年6月19日下午5:06:51 <br/>
	 * @param video
	 * @return
	 */
	List<TLiveRecommendRecord> getTVstarRecords(TLiveRecommendRecord video);

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年6月19日下午5:57:29 <br/>
	 * @param id
	 * @param sort
	 * @return
	 */
	int updateVideoSort(@Param("id")Integer id,@Param("sort") Integer sort);

    
    @DataSource("slave")
    public List<TLiveRecommendRecord> getFreshmanVideoRecommendList(TLiveRecommendRecord record);
    
    @DataSource("slave")
    public Long countFreshmanVideoRecommend(TLiveRecommendRecord record);    
}