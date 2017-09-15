package com.xmniao.xmn.core.common.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.dao.AppUpdateDao;
import com.xmniao.xmn.core.common.request.AppUpdateRequest;
import com.xmniao.xmn.core.util.PropertiesUtil;

/**
 * 
* 项目名称：saasService   
* 类名称：AppUpdateService   
* 类描述：APP版本更新接口Service   
* 创建人：liuzhihao   
* 创建时间：2016年4月15日 下午3:21:15   
* @version    
*
 */
@Service
public class AppUpdateService {

	/**
	 * 注入Dao
	 */
	@Autowired
	private AppUpdateDao appUpdateDao;
	
	@Autowired
	private String fileUrl;
	
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	@Autowired
	private String localDomain;
	/**
	 * 
	* @Title: queryAppUpdate
	* @Description: (查询最新版本信息函数)
	* @return Object    返回类型
	* @throws
	 */
	public Object queryAppUpdate(AppUpdateRequest appUpdateRequest){
		try{
			Map<Object,Object> map = new HashMap<Object,Object>();
			Map<Object,Object> result = new HashMap<Object,Object>();
//			'1 经销商版\n    2 商户版\n      3 用户版\n     4 商户版(演示)\n    5 用户版(演示)\n  6 其他',
			map.put("apptype", 3);
			if(!appUpdateRequest.getAppSource().equals("xmn")){
				result.put("mustupdate", 0);
				result.put("issame", 1);
				result.put("url", "");
				result.put("alertImg", "http://testpay.xmniao.com/xmnService/images/window_bg_niaorenfuli@3x.png ");
				result.put("isAlert", 0);
				result.put("series", 1);
				result.put("content", "最新版本");
				MapResponse response = new MapResponse(ResponseCode.SUCCESS,"查找成功");
				response.setResponse(result);
				return response;
			}
			
			String vtype = appUpdateRequest.getSystemversion();
			if(vtype.toUpperCase().contains("IOS")){
				map.put("vtype", Constant.VTYPE_IOS);
				result = appUpdateDao.queryAppUpdate(map);
				if(result!=null && result.get("version")!=null){
					//是否不许更新0不需要 1需要
					if(result.get("version").toString().equals(appUpdateRequest.getAppversion())){
						result.put("issame", Constant.STATUS_YES);//是最新版本
					}else{
						try {
							result.put("issame", Constant.STATUS_NO);//不是最新版本
							String tempOnes =  result.get("version").toString().replace(".","");//服务器版本
							String tempTwos = appUpdateRequest.getAppversion().replace(".","");//客户端版本
							if(Integer.parseInt(tempOnes)<Integer.parseInt(tempTwos)){
								result.put("issame", Constant.STATUS_YES);
							}
						} catch (Exception e) {
							e.printStackTrace();
							result.put("issame", Constant.STATUS_NO);
						}
					}
				} else {
					result = new HashMap<Object,Object>();
				}
			}
			if(vtype.toUpperCase().contains("ANDROID")){
				map.put("vtype", Constant.VTYPE_ANDROID);
				result = appUpdateDao.queryAppUpdate(map);
				if(result!=null && result.get("version")!=null){
					if(result.get("version").toString().equals(appUpdateRequest.getAppversion())){
						result.put("issame", Constant.STATUS_YES);//是最新版本
					}else{
						try {
							result.put("issame", Constant.STATUS_NO);//不是最新版本
							String tempOnes =  result.get("version").toString().replace(".","");//服务器版本
							String tempTwos = appUpdateRequest.getAppversion().replace(".","");//客户端版本
							if(Integer.parseInt(tempOnes)<Integer.parseInt(tempTwos)){
								result.put("issame", Constant.STATUS_YES);
							}
							
						} catch (Exception e) {
							e.printStackTrace();
							result.put("issame", Constant.STATUS_NO);
						}
					}
					result.put("url", fileUrl+result.get("url"));
				} else {
					result = new HashMap<Object,Object>();
					result.put("url", "");
					result.put("issame", Constant.STATUS_YES);//是最新版本
				}
			}
			result.remove("version");
			//是否弹
			result.put("isAlert", propertiesUtil.getValue("isAlert", "conf_config.properties"));
			//弹出图片
			result.put("alertImg", localDomain+propertiesUtil.getValue("alertImg", "conf_config.properties"));
			MapResponse response = new MapResponse(ResponseCode.SUCCESS,"查找成功");
			response.setResponse(result);
			return response;
		}catch(Exception e){
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE,"未知错误,请联系管理员");
		}
	}

}
