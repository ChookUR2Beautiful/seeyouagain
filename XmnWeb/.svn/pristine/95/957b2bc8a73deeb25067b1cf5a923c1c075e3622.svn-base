package com.xmniao.xmn.core.marketingmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.marketingmanagement.dao.HotWordsDao;
import com.xmniao.xmn.core.marketingmanagement.entity.HotWords;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：HotWordsService
 * 
 * 类描述：推广设置列表
 * 
 * 创建人： caoyingde
 * 
 * 创建时间：2015年03月16日17时39分38秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class HotWordsService extends BaseService<HotWords> {

	@Autowired
	private HotWordsDao hotWordsDao;

	@Override
	protected BaseDao getBaseDao() {
		return hotWordsDao;
	}

	/**
	 * 获取商家列表信息
	 * 
	 * @param refund
	 * @return
	 */
	public Pageable<HotWords> getHotWordsList(HotWords hotWords) {
		Pageable<HotWords> hotWordsList = new Pageable<HotWords>(hotWords);
		// 订单列表内容
		hotWordsList.setContent(hotWordsDao.getHotWordsList(hotWords));
		// 总条数
		hotWordsList.setTotal(hotWordsDao.getHotWordsCount(hotWords));
		return hotWordsList;
	}

	/**
	 * 获取商家列表信息
	 * 
	 * @param refund
	 * @return
	 */
	public String isHotWordsOfArea(HotWords hotWords) {
		// 查询在同一城市类关键字是否已经存在
		Long count = hotWordsDao.isHotWordsOfArea(hotWords);
		// 查询在同一城市类关键字总数（最多设置5个）
//		Long exist = hotWordsDao.isHotWordsOfAreaId(hotWords);
		// 查询在同一城市类排序是否重复
		Long isExistHOrder = hotWordsDao.isExistHOrder(hotWords);
		if (count > 0) {
			return "关键词在同一个城市已存在！";
		}
//		if (exist > 5) {
//			return "关键词在同一个城市超出5个！";
//		}
		if (isExistHOrder > 0) {
			return "在同一个城市中已存在排序";
		}
		return "success";
	}
	
	/**
	 * 修改数据时校验
	 * 
	 * @param refund
	 * @return
	 */
	public String isChangeHotWords(HotWords hotWords) {
		// 查询在同一城市类关键字是否已经存在
		Long count = hotWordsDao.isHotWordsOfArea(hotWords);
		// 查询修改前排序
		String OldhotOrder = hotWordsDao.isHotWordsOfHid(hotWords);
		// 查询修改前关键字
		String OldhotWords = hotWordsDao.isHotWordsOfHid2(hotWords);
		// 查询在同一城市类排序是否重复
		Long isExistHOrder = hotWordsDao.isExistHOrder(hotWords);
		String newHO = hotWords.getHotOrder();
		String newHW = hotWords.getHotWord();
		if (!newHO.equals(OldhotOrder)) {
			if (isExistHOrder > 0) {
				return "在同一个城市中已存在排序";
			}
		}if(!newHW.equals(OldhotWords)){
			if (count > 0) {
				return "关键词在同一个城市已存在！";
			}
		}else if (count >1 ) {
			return "关键词在同一个城市已存在！";
		}else if (isExistHOrder >1 ) {
			return "在同一个城市中已存在排序";
		}
		return "success";
	}
	
}
