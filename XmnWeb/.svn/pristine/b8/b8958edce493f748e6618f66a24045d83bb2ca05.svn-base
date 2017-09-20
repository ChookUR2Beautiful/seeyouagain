/**
 * 
 */
package com.xmniao.xmn.core.businessman.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;
import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.businessman.dao.ClassifyTagDao;
import com.xmniao.xmn.core.businessman.dao.SellerTopicDao;
import com.xmniao.xmn.core.businessman.entity.SellerTopic;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：SellerTopicService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年5月17日 上午10:19:51
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */

@Service
public class SellerTopicService extends BaseService<SellerTopic> {

	@Autowired
	private SellerTopicDao sellerTopicDao;

	@Autowired
	private ClassifyTagDao classifyTagDao;

	@Override
	protected BaseDao getBaseDao() {
		return sellerTopicDao;
	}

	@Override
	public List<SellerTopic> getList(SellerTopic sellerTopic) {
		List<SellerTopic> list = sellerTopicDao.getList(sellerTopic);
		if (list.isEmpty()) {
			return list;
		}
		HashSet<Integer> hashSet = new HashSet<>();
		for (SellerTopic sellerTopic2 : list) {
			String tagIds = sellerTopic2.getTagIds();
			if (StringUtils.isNotBlank(tagIds)) {
				String[] split = tagIds.split(",");
				for (String tagId : split) {
					hashSet.add(Integer.valueOf(tagId));
				}
			}
		}
		List<Map<String, Object>> names = classifyTagDao.selectTagNameByIds(hashSet.toArray());
		Map<Integer, String> nameMap = new HashMap<>();
		for (Map<String, Object> map : names) {
			nameMap.put((Integer) map.get("id"), (String) map.get("tag_name"));
		}
		for (SellerTopic sellerTopic2 : list) {
			String tagIds = sellerTopic2.getTagIds();
			if (StringUtils.isNotBlank(tagIds)) {
				String[] split = tagIds.split(",");
				List<String> array = new ArrayList<>();
				for (String string : split) {
					array.add(nameMap.get(Integer.valueOf(string)));
				}
				sellerTopic2.setTagIdStr(StringUtils.join(array, ","));
			}
		}
		return list;

	}

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年5月17日下午6:28:10 <br/>
	 * @param id
	 * @param sort
	 */
	public int updateTopicSort(Integer id, Integer sort) {
		return sellerTopicDao.updateTopicSort(id,sort);
	}

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年5月17日下午6:32:50 <br/>
	 * @param id
	 */
	public int deleteTopic(Integer id) {
		return sellerTopicDao.deleteTopic(id);
	}

}
