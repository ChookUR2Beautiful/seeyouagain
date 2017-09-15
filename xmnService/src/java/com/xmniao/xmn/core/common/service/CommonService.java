package com.xmniao.xmn.core.common.service;

import java.util.*;

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.catehome.response.HomeImageResponse;
import com.xmniao.xmn.core.common.ConstantDictionary;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.util.VersionUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.api.controller.integral.IntegralMallHomeApi;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.BannerRequest;
import com.xmniao.xmn.core.integral.dao.IntegralMallDao;
import com.xmniao.xmn.core.integral.entity.BannerEntity;
import com.xmniao.xmn.core.util.Base64;
import com.xmniao.xmn.core.xmer.dao.AreaDao;
import com.xmniao.xmn.core.xmer.entity.Area;

/**
 * 
*      
* 类名称：CommonService   
* 类描述：   通用service
* 创建人：xiaoxiong   
* 创建时间：2016年9月14日 上午9:56:44   
* 修改人：xiaoxiong   
* 修改时间：2016年9月14日 上午9:56:44   
* 修改备注：   
* @version    
*
 */

@Service
public class CommonService {

	//报错日志
	private final Logger log = Logger.getLogger(CommonService.class);
	
	//注入Dao
	@Autowired
	private IntegralMallDao integralMallDao;
	
	//注入服务器地址
	@Autowired
	private String fileUrl;
	
	@Autowired
	private AreaDao areaDao;

	@Autowired
	private PropertiesUtil propertiesUtil;


	/**
	 * 
	 * @Description: 获取banner图
	 * @author xiaoxiong
	 * @date 2016年9月14日
	 * @version
	 */
	public Object getBanner(BannerRequest request) {
		List<Map<Object,Object>> result = new ArrayList<Map<Object,Object>>();//存储banner图的集合map
		try{
			Map<Object,Object> paraMap = new HashMap<Object,Object>();
			paraMap.put("position", request.getPosition());//banner位置信息，1 附近美食，2 积分商城
			paraMap.put("status", 1);//上线状态 0.待上线，1.已上线，2.已下线
			List<BannerEntity> bannerList = integralMallDao.queryBannerList(paraMap);
			if(bannerList.size() >0 && bannerList != null){
				for(BannerEntity banner : bannerList){
					Map<Object,Object> map  = new HashMap<Object,Object>();
					map.put("id", banner.getId());//图片id
					map.put("bannerStyle", banner.getBanner_style()==null?0:banner.getBanner_style());//展示风格。0图片横排一格，1图片横排两格
					map.put("sort", banner.getSort()==null?0:banner.getSort());//排序，数值越大，越优先展示
					String obj_json = banner.getObj_json();
					String contents = Base64.getFromBase64(obj_json);//解密
					List<Map<Object,Object>> picList = new ArrayList<Map<Object,Object>>();//用于存储图片集合
					JSONArray arr = JSON.parseArray(contents);
					for(int i=0; i<arr.size(); i++){
						Map<Object,Object> picMap = new HashMap<Object,Object>();
						JSONObject json = JSON.parseObject(arr.get(i).toString());
						String url = json.getString("pic_url").toString();//图片地址
						Integer type = json.getInteger("type");//图片类型
						String content = json.getString("content").toString();//描述
						Integer sort = json.getInteger("sort");//图片排序
						Integer logRequired = json.getInteger("logRequired");//图片是否需要登录
						picMap.put("url", fileUrl+url);
						picMap.put("type", type);
						picMap.put("content", content);
						picMap.put("sort", sort);
						if(sort==null){
							picMap.put("sort", 0);
						}
						if (logRequired == null) {
							picMap.put("logRequired", 0);  //0不登陆
						}else {
							picMap.put("logRequired", logRequired);
						}
						picList.add(picMap);
					}
					 Collections.sort(picList, new Comparator<Map<Object, Object>>(){
				            public int compare(Map<Object, Object> arg0, Map<Object, Object> arg1) {
				                return arg1.get("sort").toString().compareTo(arg0.get("sort").toString());
				            }
				        });
					map.put("bannerlist", picList);
					result.add(map);
				}
			}
			 Collections.sort(result, new Comparator<Map<Object, Object>>(){
		            public int compare(Map<Object, Object> arg0, Map<Object, Object> arg1) {
		                return arg1.get("sort").toString().compareTo(arg0.get("sort").toString());
		            }
		        });
			 checkIOSManorVersion(request, result);
			 MapResponse mapResponse=new MapResponse(ResponseCode.SUCCESS,"成功");
			 Map<Object,Object> resultMap=new HashMap<>();
			 resultMap.put("banners", result);
			 mapResponse.setResponse(resultMap);
			return mapResponse;
		}catch(Exception e){
			e.printStackTrace();
			log.info("查询banner图异常");
			return new BaseResponse(ResponseCode.FAILURE, "失败");
		}
	}
	/**
	 * 分类查询地区
	 * @author xiaoxiong
	 * @date 2016年11月30日
	 */
	public List<Area> queryAreaByType(int pid) {
		try {
			return areaDao.queryAreaByType(pid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private String getManorAction() {
		String manorAction = "app_manor_home";  //黄金庄园action
		try {
			manorAction = propertiesUtil.getValue("manorAction", "conf_common.properties");
		} catch (Exception e) {
			log.warn("解析配置失败，manorAction", e);
		}
		return manorAction;
	}

	private void checkIOSManorVersion(BannerRequest request, List<Map<Object,Object>> result) {
		checkIOSManorVersion(request, request.getPosition(), result);
	}

	public void checkIOSManorVersion(BaseRequest request, int position, List<Map<Object,Object>> banners) {
		boolean isCanFilter = isCanFilter(request, position, banners == null ? 0 : banners.size());
		if (!isCanFilter) {
			return;
		}
		String manorAction = getManorAction();
		// 审核阶段，去掉黄金庄园轮播图
		Iterator<Map<Object, Object>> itr = banners.iterator();
		while (itr.hasNext()) {
			Map<Object, Object> map = itr.next();
			List<Map<Object,Object>> picList = (List<Map<Object, Object>>) map.get("bannerList");
			List<Map<Object,Object>> picList2 = (List<Map<Object, Object>>) map.get("bannerlist");
			picList = picList == null ? picList2 : picList;  // ......
			if (picList == null || picList.size() == 0) {
				continue;
			}
			Iterator<Map<Object, Object>> picItr = picList.iterator();
			while (picItr.hasNext()) {
				Map<Object,Object> pic = picItr.next();
				String url = pic.get("content") == null ? "" : pic.get("content").toString();
				if (url.contains(manorAction)) {
					// 移除
					picItr.remove();
				}
			}
			if (picList.size() == 0) {
				itr.remove();
			}
		}
	}

	public void checkIOSManorVersion(BaseRequest request,  List<HomeImageResponse> banners) {
		boolean isCanFilter = isCanFilter(request, 9, banners == null ? 0 : banners.size());
		if (!isCanFilter) {
			return;
		}
		String manorAction = getManorAction();
		// 审核阶段，去掉黄金庄园轮播图
		Iterator<HomeImageResponse> itr = banners.iterator();
		while (itr.hasNext()) {
			HomeImageResponse homeImageResponse = itr.next();
			String content = homeImageResponse.getContent() == null ? "" : homeImageResponse.getContent();
			if (content.contains(manorAction) && content.toLowerCase().contains("http")) {
				itr.remove();
			}
		}

	}

	private boolean isCanFilter(BaseRequest request, int position, int size) {
		int isIOSOpenManorHome = 1;  // 0关闭 1 开启审核效验
		int checkIOSManorVersion = Integer.MAX_VALUE;  //审核版本
		String checkDevice = "ios";

		int isIOSOpenManorHome_bird = 1;  // 0关闭 1 开启审核效验
		int checkIOSManorVersion_bird = Integer.MAX_VALUE;  //审核版本
		String checkDevice_bird = "ios";


		try {
			isIOSOpenManorHome = Integer.parseInt(propertiesUtil.getValue("isIOSOpenManorHome", "conf_common.properties"));
			checkIOSManorVersion = Integer.parseInt(propertiesUtil.getValue("checkIOSManorVersion", "conf_common.properties"));
			checkDevice = propertiesUtil.getValue("checkDevice", "conf_common.properties");

			isIOSOpenManorHome_bird = Integer.parseInt(propertiesUtil.getValue("isIOSOpenManorHome_bird", "conf_common.properties"));
			checkIOSManorVersion_bird = Integer.parseInt(propertiesUtil.getValue("checkIOSManorVersion_bird", "conf_common.properties"));
			checkDevice_bird = propertiesUtil.getValue("checkDevice_bird", "conf_common.properties");


		} catch (Exception e) {
			log.warn("解析配置失败，isIOSOpenManorHome， checkIOSManorVersion", e);
		}
//
//		if (position != 9) {
//			return false;
//		}
		if (size == 0) {
			return false;
		}
		int appVersion = VersionUtil.getVersionCode(request);
		boolean isBirdApp = request.getAppSource().equals(ConstantDictionary.AppSourceState.BIRD_APP.getName());  //鸟人直播

		if (isBirdApp) {  //鸟人直播
			// ios，开启审核开关，指定版本
			if (request.getSystemversion() != null && request.getSystemversion().toLowerCase().contains(checkDevice_bird)
					&& isIOSOpenManorHome_bird == 1 && appVersion == checkIOSManorVersion_bird) {
			} else {
				// 不符合条件，不需要过滤
				return false;
			}
		} else {
			// ios，开启审核开关，指定版本
			if (request.getSystemversion() != null && request.getSystemversion().toLowerCase().contains(checkDevice)
					&& isIOSOpenManorHome == 1 && appVersion == checkIOSManorVersion) {
			} else {
				// 不符合条件，不需要过滤
				return false;
			}
		}
		return true;
	}


}
