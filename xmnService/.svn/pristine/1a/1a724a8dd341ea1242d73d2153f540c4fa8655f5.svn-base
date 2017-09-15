package com.xmniao.xmn.core.api.controller.common;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.catehome.dao.AdvertisingDao;
import com.xmniao.xmn.core.catehome.entity.Advertising;
import com.xmniao.xmn.core.catehome.entity.StartImg;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.AppLoadUrlRequest;
import com.xmniao.xmn.core.common.request.AppStartAdvertisementRequest;
import com.xmniao.xmn.core.thrift.ResponseData;
import com.xmniao.xmn.core.xmer.service.AppLoadUrlService;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：AppLoadUrlApi   
* 类描述：   获取app客户端下载地址
* 创建人：yezhiyong   
* 创建时间：2016年6月16日 下午2:30:54   
* @version    
*
 */
@Controller
public class AppStartAdvertisementApi{
	
	/**
	 * 报错日志
	 */
	private final Logger log = Logger.getLogger(AppStartAdvertisementApi.class);
	
	/**
	 * 注入appLoadUrlService
	 */
	@Autowired
	private AppLoadUrlService appLoadUrlService;
	
	/**
	 * 注入validator验证
	 */
	@Autowired
	private Validator validator;
	
	/**
	 * 注入validator验证
	 */
	@Autowired
	private AdvertisingDao advertisingDao;
	
	@Autowired
	private String fileUrl;
	
	/**
	 * 注入redis 缓存
	 */
	@Autowired
	private SessionTokenService sessionTokenService;
	
	@RequestMapping(value="app/start/advertisement",method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object queryLoadUrl(AppStartAdvertisementRequest appStartAdvertisementRequest){
		//验证请求数据的合法性
		List<ConstraintViolation> result = validator.validate(appStartAdvertisementRequest);
		if(result.size() > 0){
			log.info("数据提交有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题");
		}
		
		MapResponse response = null;
		Map<Object, Object> adverMap = new HashMap<Object, Object>();
		List<StartImg> advertisings = new ArrayList<StartImg>();
		try {
			//查询当前城市的
			if (appStartAdvertisementRequest.getCityId()>0) {
				Map<Object, Object> map = new HashMap<Object, Object>();
				map.put("city", appStartAdvertisementRequest.getCityId());
				advertisings = advertisingDao.selectStartImgByCity(map);
				if(advertisings.size()<=0){
					advertisings = advertisingDao.findAllStartImg();
				}
			}else {
				advertisings = advertisingDao.findAllStartImg();
			}
			
			//取第一条返回记录   
			if(advertisings.size()>0){
				adverMap.put("isdisplay", 1);//有广告
				adverMap.put("id", advertisings.get(0).getId());
				adverMap.put("adbpic", fileUrl+advertisings.get(0).getPic());
				adverMap.put("adburl", advertisings.get(0).getStartUrl()==null?"":advertisings.get(0).getStartUrl());
//				adverMap.put("shareCont", advertisings.get(0).getShareCont());
//				adverMap.put("shareImg", fileUrl+advertisings.get(0).getShareImg());
//				adverMap.put("shareUrl", advertisings.get(0).getShareUrl());
//				adverMap.put("shareTitle", advertisings.get(0).getShareTitle());
//				adverMap.put("picLow", fileUrl+advertisings.get(0).getPicLow());
//				adverMap.put("picMiddle", fileUrl+advertisings.get(0).getPicMiddle());
			}else {
				adverMap.put("isdisplay", 0);//无广告
			}
			
			response = new MapResponse(ResponseCode.SUCCESS, "查询成功");
			response.setResponse(adverMap);
			return response;
		
		} catch (Exception e) {
			e.printStackTrace();
			log.info("查询启动页广告失败");
			return new MapResponse(ResponseCode.FAILURE, "操作异常");
		}
	}

}
