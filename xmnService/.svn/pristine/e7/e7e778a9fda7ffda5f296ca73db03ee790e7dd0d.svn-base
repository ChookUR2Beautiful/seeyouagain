package com.xmniao.xmn.core.api.controller.catehome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.api.controller.seller.SellerActivityList;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.catehome.entity.HotWords;
import com.xmniao.xmn.core.catehome.service.HotWordsService;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.HotWordsListRequest;
import com.xmniao.xmn.core.util.PropertiesUtil;

/**
 * 
* 类名称：HotWordsListApi   
* 类描述：热搜关键字   
* 创建人：xiaoxiong   
* 创建时间：2016年11月22日 上午10:27:35
 */
@Controller
@RequestMapping("search/hotWords")
public class HotWordsListApi implements BaseVControlInf{
	private final Logger log = Logger.getLogger(SellerActivityList.class);
	@Autowired
	private Validator validator;
	
	@Autowired
	private HotWordsService hotWordsService;
	
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	@ResponseBody
	@RequestMapping("/list")
	public Object list(HotWordsListRequest request){
		log.info("HotWordsListRequest data:" + request.toString());
		List<ConstraintViolation> result = validator.validate(request);
		if(result.size() >0 && result != null){
			log.info("提交的数据有问题:"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,result.get(0).getMessage());
		}
		return versionControl(request.getApiversion(), request);
	}

	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1:
			return versionControlOne(object);
			default :
				return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}

	private Object versionControlOne(Object object) {
		try {
			HotWordsListRequest request = (HotWordsListRequest)object;
			Map<String,Object> params = new HashMap<>();
			params.put("pageSize", request.getPageSize());
			params.put("page", request.getPage());
			params.put("areaId", request.getAreaId());
			params.put("type", 1);
			List<HotWords> customList = hotWordsService.queryHotWordsOrder(params);
			
			Map<Object,Object> result = new HashMap<Object, Object>();
			params.put("type", 2);
			
			/* 运营要求暂不显示热搜关键字*/
			String showHotSearch = propertiesUtil.getValue("showHotSearch", "conf_live.properties");
			
			List<HotWords>  keyWordList= "0".equals(showHotSearch) ? null : hotWordsService.queryHotWordsOrder(params);
			
			if(customList!=null&&customList.size()>0){
				if(keyWordList!=null&&keyWordList.size()>0){
					customList.addAll(keyWordList);
				}
				result.put("list",customList);
			}else{
				result.put("list",keyWordList);
			}
			MapResponse mapResponse = new MapResponse(ResponseCode.SUCCESS, "成功");
			mapResponse.setResponse(result);
			
			return mapResponse;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BaseResponse(ResponseCode.FAILURE, "错误");
	}

	private List<String> converList(List<HotWords> hotWordList) {
		List<String> responseList = new ArrayList<>();
		for(HotWords h : hotWordList){
			
			responseList.add(h.getHotWords());
		}
		
		return responseList;
	}

}
