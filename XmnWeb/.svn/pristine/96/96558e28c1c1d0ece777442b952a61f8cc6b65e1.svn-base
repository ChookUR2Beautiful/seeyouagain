package com.xmniao.xmn.core.reward_dividends.dao;

import java.util.List;
import java.util.Map;

import com.xmniao.xmn.core.reward_dividends.entity.TLiveGivedGiftVke;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

public interface TLiveGivedGiftVkeDao {
	@DataSource(value = "slave")
	int deleteByPrimaryKey(Long id);

	@DataSource(value = "slave")
	int insert(TLiveGivedGiftVke record);

	@DataSource(value = "slave")
	int insertSelective(TLiveGivedGiftVke record);

	@DataSource(value = "slave")
	TLiveGivedGiftVke selectByPrimaryKey(Long id);

	@DataSource(value = "slave")
	int updateByPrimaryKeySelective(TLiveGivedGiftVke record);

	@DataSource(value = "slave")
	int updateByPrimaryKey(TLiveGivedGiftVke record);
	
	@DataSource(value = "slave")
	List<TLiveGivedGiftVke> getVerProfitLiveGiftList(TLiveGivedGiftVke record);
	
	@DataSource(value = "slave")
	Long countVerProfitLiveGift(TLiveGivedGiftVke record);
	
	@DataSource(value = "slave")
	List<Map<String, Object>> getVerProfitCountLiveGift(Object[] array);
	
	@DataSource(value = "slave")
	List<TLiveGivedGiftVke> getVerProfitCountLiveGiftList(Integer vkeUid);
	
	@DataSource(value = "slave")
	Integer getVkeProfitVkeCount(Map<String, Object> params);
}