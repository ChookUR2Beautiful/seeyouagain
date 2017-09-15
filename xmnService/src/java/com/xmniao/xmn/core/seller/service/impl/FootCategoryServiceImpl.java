package com.xmniao.xmn.core.seller.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.seller.dao.TradeDao;
import com.xmniao.xmn.core.seller.service.FoodCategoryService;

@Service
public class FootCategoryServiceImpl implements FoodCategoryService {

	@Autowired
	private TradeDao tradeDao;
	
	@Override
	public Object queryFoodCategory(BaseRequest request) {
		
		List<Map<Object,Object>> foodCategoryList = tradeDao.listFoodCategory();
		for (Map<Object, Object> map : foodCategoryList) {
			map.put("genre", map.get("tid"));
		}
		
		Map<Object,Object> result = new HashMap<>();
		result.put("foodCategoryList", foodCategoryList);
		
		MapResponse mapResponse = new MapResponse(ResponseCode.SUCCESS, "成功");
		mapResponse.setResponse(result);
		
		return mapResponse;
	}

}
