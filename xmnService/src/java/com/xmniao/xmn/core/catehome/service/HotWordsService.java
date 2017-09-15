package com.xmniao.xmn.core.catehome.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.catehome.dao.HotWordsDao;
import com.xmniao.xmn.core.catehome.entity.HotWords;


@Service
public class HotWordsService {
	
	@Autowired
	private HotWordsDao hotWordsDao;
	
	
	public List<HotWords> queryHotWordsOrder(Map<String,Object> params) {
		try {
			return hotWordsDao.queryHotWordsOrder(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询关键词数量
	 * @author xiaoxiong
	 * @date 2016年11月23日
	 */
	public HotWords keyWordCount(String keyWord,Integer areaId) {
		try {
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("keyWord", keyWord);
			params.put("areaId", areaId);
			
			return hotWordsDao.queryKeyWordByKeyWord(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 添加热搜关键字
	 * @author xiaoxiong
	 * @date 2016年11月23日
	 */
	public void insertSelective(HotWords hotWord) {
		
		hotWordsDao.insertSelective(hotWord);
	}
	
	/**
	 * 修改数量
	 * @author xiaoxiong
	 * @date 2016年11月23日
	 */
	public void updateByPrimaryKeySelective(HotWords hotWord) {
		
		hotWordsDao.updateByPrimaryKeySelective(hotWord);
	}

}
