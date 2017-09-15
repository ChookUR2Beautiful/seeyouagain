package com.xmniao.xmn.core.seller.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.seller.entity.UnsignedSeller;
import com.xmniao.xmn.core.seller.entity.UnsignedSellerRequest;
import com.xmniao.xmn.core.seller.service.UnsignedSellerService;
import com.xmniao.xmn.core.xmer.dao.UnsignedSellerDao;

@Service
public class UnsignedSellerServiceImpl implements UnsignedSellerService {

	@Autowired
	private SessionTokenService sessionTokenService;

	@Autowired
	private UnsignedSellerDao unsignedSellerDao;

	@Override
	public Object addUnsignedSeller(UnsignedSellerRequest request) {
		String uid = sessionTokenService.getStringForValue(request.getSessiontoken()) + "";
		if (StringUtils.isEmpty(uid) || "null".equals(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效，请重新登陆");
		}

		UnsignedSeller seller = new UnsignedSeller();
		seller.setAnchor_uid(Integer.parseInt(uid));
		seller.setSellername(request.getSellername());
		seller.setProvince(request.getProvince());
		seller.setCity(request.getCity());
		seller.setArea(request.getArea());
		seller.setAddress(request.getAddress());
		seller.setZoneid(request.getZoneid());
		seller.setCategory("1");
		seller.setTypename("美食");
		seller.setGenre(request.getGenre());
		seller.setTradename(request.getTradename());
		seller.setConsume(request.getConsume());
		seller.setIsonline(0);
		seller.setCreate_time(new Date());

		unsignedSellerDao.addUnsignedSeller(seller);
		Integer sellerid = seller.getUnsigned_sellerid();
		
		MapResponse mapResponse = new MapResponse(ResponseCode.SUCCESS, "添加成功");
		Map<Object,Object> result = new HashMap<>();
		
		result.put("sellerid", sellerid);
		mapResponse.setResponse(result);
		return mapResponse;
	}

}
