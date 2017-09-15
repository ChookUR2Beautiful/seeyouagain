package com.xmniao.xmn.core.vod.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.vod.CreateClassRequest;
import com.xmniao.xmn.core.common.request.vod.AnchorVideoRequest;
import com.xmniao.xmn.core.common.request.vod.CreateRecordRequest;
import com.xmniao.xmn.core.common.request.vod.DescribeVodPlayInfoRequest;
import com.xmniao.xmn.core.common.request.vod.DescribeVodPlayUrlsRequest;
import com.xmniao.xmn.core.common.request.vod.MultipartUploadVodFileRequest;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.vod.QcloudApiModuleCenter;
import com.xmniao.xmn.core.vod.Module.Vod;
import com.xmniao.xmn.core.vod.dao.LiveVodDao;
/**
 * 
*    
* 项目名称：xmnService_3.1.2_zb   
* 类名称：LiveVodService   
* 类描述：   直播-点播 服务类
* 创建人：xiaoxiong   
* 创建时间：2016年9月18日 11:40:51  
* @version    
*
 */

@Service
public class LiveVodService {
	
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(LiveVodService.class);
	
	@Autowired
	private LiveVodDao liveVodDao;
	
	
	@Autowired
	private String fileUrl;
	
	
	@Autowired
	private String localDomain;
	
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	/**
	 * 查询视频列表
	 * @author xiaoxiong
	 * @date 2016年8月25日
	 * @version
	 */
	public Object DescribeVodinfo(BaseRequest request) {
		try {
			QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Vod(),"GET");
			//TreeMap<String, Object> params = new TreeMap<String, Object>();
			/* 将需要输入的参数都放入 params 里面，必选参数是必填的。 */
			/* DescribeInstances 接口的部分可选参数如下 */
			/* generateUrl方法生成请求串,可用于调试使用 */
			//System.out.println(module.generateUrl("DescribeInstances", params));
			String result = null;
			try {
				/* call 方法正式向指定的接口名发送请求，并把请求参数params传入，返回即是接口的请求结果。 */
				result = module.call("DescribeVodInfo", null);
			} catch (Exception e) {
				System.out.println("error..." + e.getMessage());
			}
			//返回对象
			JSONObject infoJson=JSONObject.parseObject(result);
			if(infoJson.getIntValue("code")==0){
				MapResponse mapResponse=new MapResponse(ResponseCode.SUCCESS, "成功");
				Map<Object, Object> map=new HashMap<Object, Object>();
				JSONArray jsonArray=JSONArray.parseArray(infoJson.getString("fileSet"));
				map.put("fileSet",jsonArray);
				map.put("totalCount", infoJson.getIntValue("totalCount"));
				mapResponse.setResponse(map);
				return mapResponse;
			}else{
				MapResponse mapResponse=new MapResponse(ResponseCode.FAILURE,  infoJson.getString("message"));
				Map<Object, Object> map=new HashMap<Object, Object>();
//				map.put("message", infoJson.getString("message"));//错误信息
//				map.put("message", infoJson.getString("message"));
				mapResponse.setResponse(map);
				
				return mapResponse;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BaseResponse(ResponseCode.FAILURE, "获取视频信息失败");
	}
	
	/**
	 * 
	 * @author xiaoxiong
	 * @date 2016年8月25日
	 * @version
	 */
	public Object createClass(CreateClassRequest request) {
		try {
			QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Vod(),"GET");
			//请求参数
			TreeMap<String, Object> params = new TreeMap<String, Object>();
			params.put("className", request.getClassname());
			if(request.getParentId()!=null)
			params.put("parentId", request.getParentId());
			
			String result = null;
			try {
				/* call 方法正式向指定的接口名发送请求，并把请求参数params传入，返回即是接口的请求结果。 */
				result = module.call("CreateClass", params);
				log.info("创建分类返回参数："+result);
			} catch (Exception e) {
				System.out.println("error..." + e);
			}
			//返回对象
			JSONObject infoJson=JSONObject.parseObject(result);
			if(infoJson.getIntValue("code")==0){
				MapResponse mapResponse=new MapResponse(ResponseCode.SUCCESS, "成功");
				Map<Object, Object> map=new HashMap<Object, Object>();
				map.put("newClassId", infoJson.getString("newClassId"));//分类ID
//				map.put("message", infoJson.getIntValue("message"));//错误消息
				mapResponse.setResponse(map);
				return mapResponse;
			}else{
				MapResponse mapResponse=new MapResponse(ResponseCode.FAILURE,infoJson.getString("message"));
				Map<Object, Object> map=new HashMap<Object, Object>();
//				map.put("message", infoJson.getString("message"));
				mapResponse.setResponse(map);
				return mapResponse;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BaseResponse(ResponseCode.FAILURE, "创建分类失败");
	}
	
	/**
	 * 获得当前用户所有的分类层级关系
	 * @author xiaoxiong
	 * @date 2016年8月25日
	 * @version
	 */
	public Object DescribeAllClass(BaseRequest request) {
		try {
			QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Vod(),"GET");
			String result = null;
			try {
				/* call 方法正式向指定的接口名发送请求，并把请求参数params传入，返回即是接口的请求结果。 */
				result = module.call("DescribeAllClass", null);
				log.info("获得当前用户所有的分类层级关系返回参数："+result);
			} catch (Exception e) {
				log.error("获得当前用户所有的分类层级关系"+e);
			}
			//返回对象
			JSONObject infoJson=JSONObject.parseObject(result);
			if(infoJson.getIntValue("code")==0){
				MapResponse mapResponse=new MapResponse(ResponseCode.SUCCESS, "成功");
				Map<Object, Object> map=new HashMap<Object, Object>();
				map.put("data", infoJson.getString("data"));//分类ID
//				map.put("message", infoJson.getIntValue("message"));//错误消息
				mapResponse.setResponse(map);
				return mapResponse;
			}else{
				MapResponse mapResponse=new MapResponse(ResponseCode.FAILURE, infoJson.getString("message"));
				Map<Object, Object> map=new HashMap<Object, Object>();
//				map.put("message", infoJson.getString("message"));
				mapResponse.setResponse(map);
				return mapResponse;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BaseResponse(ResponseCode.FAILURE, "查询分类失败");
	}
	
	/**
	 * @Description: 获取全局分类列表，包括ID和分类描述的具体关系，和具体文件无关
	 * @author xiaoxiong
	 * @date 2016年8月25日
	 * @version
	 */
	public  Object DescribeClass(BaseRequest request) {
		try {
			QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Vod(),"GET");
			String result = null;
			try {
				/* call 方法正式向指定的接口名发送请求，并把请求参数params传入，返回即是接口的请求结果。 */
				result = module.call("DescribeClass", null);
				log.info("获取全局分类列表返回参数："+result);
			} catch (Exception e) {
				System.out.println("error..." + e);
			}
			//返回对象
			JSONObject infoJson=JSONObject.parseObject(result);
			if(infoJson.getIntValue("code")==0){
				MapResponse mapResponse=new MapResponse(ResponseCode.SUCCESS, "成功");
				Map<Object, Object> map=new HashMap<Object, Object>();
				map.put("data", infoJson.getString("data"));//分类ID
//				map.put("message", infoJson.getIntValue("message"));//错误消息
				mapResponse.setResponse(map);
				return mapResponse;
			}else{
				MapResponse mapResponse=new MapResponse(ResponseCode.FAILURE, infoJson.getString("message"));
				Map<Object, Object> map=new HashMap<Object, Object>();
//				map.put("message", infoJson.getString("message"));
				mapResponse.setResponse(map);
				return mapResponse;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BaseResponse(ResponseCode.FAILURE, "查询分类失败");
	}

	/**
	 * 
	 * @Description: 获取视频播放信息
	 * @author xiaoxiong
	 * @date 2016年8月27日
	 * @version
	 */
	public Object DescribeVodPlay(DescribeVodPlayInfoRequest request) {
		try {
			QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Vod(),"GET");
			String result = null;
			try {
				//请求参数
				TreeMap<String, Object> params = new TreeMap<String, Object>();
				params.put("fileName", request.getFilename());
				if(request.getPageno()!=null)params.put("pageNo", request.getPageno());
				if(request.getPagesize()!=null)params.put("pageSize", request.getPagesize());

				/* call 方法正式向指定的接口名发送请求，并把请求参数params传入，返回即是接口的请求结果。 */
				result = module.call("DescribeVodPlayInfo", params);
				log.info("获取视频播放信息返回参数："+result);
			} catch (Exception e) {
				System.out.println("error..." + e);
			}
			//返回对象
			JSONObject infoJson=JSONObject.parseObject(result);
			if(infoJson.getIntValue("code")==0){
				MapResponse mapResponse=new MapResponse(ResponseCode.SUCCESS, "成功");
				Map<Object, Object> map=new HashMap<Object, Object>();
				JSONArray jsonArray=JSONArray.parseArray( infoJson.getString("fileSet"));
				map.put("fileSet",jsonArray);//分类ID
				map.put("totalCount", infoJson.getIntValue("totalCount"));//数量
				mapResponse.setResponse(map);
				return mapResponse;
			}else{
				MapResponse mapResponse=new MapResponse(ResponseCode.FAILURE, infoJson.getString("message"));
				Map<Object, Object> map=new HashMap<Object, Object>();
//				map.put("message", infoJson.getString("message"));
				mapResponse.setResponse(map);
				return mapResponse;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BaseResponse(ResponseCode.FAILURE, "查询视频播放信息失败");
	}
	
	/**
	 * 获取视频详细信息
	 * @author xiaoxiong
	 * @date 2016年8月26日
	 * @version
	 */
	public Object DescribeVodPlayUrls(DescribeVodPlayUrlsRequest request) {
		try {
			QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Vod(),"GET");
			String result = null;
			try {
				//请求参数
				TreeMap<String, Object> params = new TreeMap<String, Object>();
				params.put("fileId", request.getFileid());

				/* call 方法正式向指定的接口名发送请求，并把请求参数params传入，返回即是接口的请求结果。 */
				result = module.call("DescribeVodPlayUrls", params);
				log.info("获取视频详细信息返回参数："+result);
			} catch (Exception e) {
				System.out.println("error..." + e);
			}
			//返回对象
			JSONObject infoJson=JSONObject.parseObject(result);
			if(infoJson.getIntValue("code")==0){
				MapResponse mapResponse=new MapResponse(ResponseCode.SUCCESS, "成功");
				Map<Object, Object> map=new HashMap<Object, Object>();
				JSONArray jsonArray=JSONArray.parseArray( infoJson.getString("playSet"));
				map.put("playSet",jsonArray);//分类ID
				mapResponse.setResponse(map);
				return mapResponse;
			}else{
				MapResponse mapResponse=new MapResponse(ResponseCode.FAILURE, infoJson.getString("message"));
				Map<Object, Object> map=new HashMap<Object, Object>();
				//map.put("message", infoJson.getString("message"));
				mapResponse.setResponse(map);
				return mapResponse;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BaseResponse(ResponseCode.FAILURE, "查询视频详细信息失败");
	}
	
	/**
	 * 图片上传
	 * @author xiaoxiong
	 * @date 2016年8月26日
	 * @version
	 */
	public Object MultipartUploadVodFile(MultipartUploadVodFileRequest request) {
		try {
			QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Vod(),"POST");
			String result = null;
			try {
				int fixDataSize = 1024*1024*50;  //每次上传字节数，可自定义
				//int firstDataSize = 1024*10;    //切片上传：最小片字节数（默认不变）,如果：dataSize + offset > fileSize,把这个值变小即可
				int tmpDataSize = request.getDatesize();
				long fileSize=request.getFilesize();
				long remainderSize = fileSize;
				int tmpOffset = 0;
				int code, flag;
				String fileId;
				if(remainderSize<=0){
					System.out.println("wrong file path...");
				}
				while (remainderSize>0) {
					TreeMap<String, Object> params = new TreeMap<String, Object>();
					params.put("fileSha", request.getFilesha());
					params.put("fileType", request.getFiletype());
					params.put("fileName", request.getFilename());
					params.put("fileSize", request.getFilesize());
					params.put("dataSize", tmpDataSize);
					params.put("offset", tmpOffset);
					params.put("file", request.getFile());
					if(request.getIstranscode()!=null)params.put("isTranscode", request.getIstranscode());//是否转码
					if(request.getIsscreenshot()!=null)params.put("isScreenshot", request.getIsscreenshot());//是否截图
					if(request.getIswatermark()!=null)params.put("isWatermark", request.getIswatermark());//是否添加水印
					
					result = module.call("MultipartUploadVodFile", params);
					log.info("查询视频详细信息返回参数："+result);
					com.xmniao.xmn.core.vod.Utilities.Json.JSONObject json_result = new com.xmniao.xmn.core.vod.Utilities.Json.JSONObject(result);
					code = json_result.getInt("code");
					if (code == -3002) {               //服务器异常返回，需要重试上传(offset=0, dataSize=10K,满足大多数视频的上传)
						tmpDataSize = request.getDatesize();
						tmpOffset = 0;
						continue;
					} else if (code != 0) {
						break;
					}
					flag = json_result.getInt("flag");
					if (flag == 1) {
						fileId = json_result.getString("fileId");
						break;
					} else {
						tmpOffset = Integer.parseInt(json_result.getString("offset"));
					}
					remainderSize = fileSize - tmpOffset;
					if (fixDataSize < remainderSize) {
						tmpDataSize = fixDataSize;
					} else {
						tmpDataSize = (int) remainderSize;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("error..." + e);
			}
			//返回对象
			JSONObject infoJson=JSONObject.parseObject(result);
			if(infoJson.getIntValue("code")==0){
				MapResponse mapResponse=new MapResponse(ResponseCode.SUCCESS, "成功");
				Map<Object, Object> map=new HashMap<Object, Object>();
				if(infoJson.getIntValue("flag")==1)
				map.put("fileId", infoJson.getString("fileId"));//分类ID
				mapResponse.setResponse(map);
				return mapResponse;
			}else{
				MapResponse mapResponse=new MapResponse(ResponseCode.FAILURE, infoJson.getString("message"));
				Map<Object, Object> map=new HashMap<Object, Object>();
				//map.put("message", infoJson.getString("message"));
				mapResponse.setResponse(map);
				return mapResponse;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BaseResponse(ResponseCode.FAILURE, "查询视频详细信息失败");
	}
	
	/**
	 * 
	 * @Description:结束录制绑定 直播记录ID
	 * @author xiaoxiong
	 * @date 2016年9月18日 11:34:23
	 * @version
	 */
	public Object CreateRecord(CreateRecordRequest request) {
		try {
			//是否需要截图
			String isScreenshot=propertiesUtil.getValue("isScreenshot", "conf_config.properties");
			//是否需要水印
			String isWatermark=propertiesUtil.getValue("isWatermark", "conf_config.properties");
			Map<String,Object> param=new HashMap<>();
			/**记录ID*/
			param.put("recordId", request.getRecordId());
			param.put("createTime", new Date());
			param.put("updateTime", new Date());
			try {
				String[] files=request.getFileId().split(",");
				for(String vid:files){
					try {
						QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Vod(),"GET");
						String result = null;
							//请求参数
							TreeMap<String, Object> params = new TreeMap<String, Object>();
							
//							String notifyUrl=localDomain+"/notifyInfo";
//							log.info("回调地址："+notifyUrl);
							params.put("vid", vid);
//							params.put("notifyUrl", notifyUrl);	//视频回调路径
							/* call 方法正式向指定的接口名发送请求，并把请求参数params传入，返回即是接口的请求结果。 */
							result = module.call("DescribeRecordPlayInfo", params);
							log.info("获取录制视频返回参数："+result);
							//返回对象
							JSONObject infoJson=JSONObject.parseObject(result);
							if(infoJson.getIntValue("code")==0){
								JSONArray fileSet=JSONArray.parseArray(infoJson.getString("fileSet"));
								for(int i=0;i<fileSet.size();i++){
									JSONObject fileJSON=fileSet.getJSONObject(i);
									log.info(fileJSON.toJSONString());
									param.put("fileId", fileJSON.getString("fileId"));	//视频ID
									param.put("fileName", fileJSON.getString("fileName"));//文件名次
									param.put("duration", fileJSON.getString("duration"));//时长
									param.put("imageUrl", fileJSON.getString("image_url"));//封面图片
									param.put("status", fileJSON.getString("status"));//封面图片
									liveVodDao.insertLiveVideo(param);
									try {
									params.clear();
									params.put("fileId", fileJSON.getString("fileId"));//视频文件ID
									params.put("isScreenshot",isScreenshot);//是否需要截图
									params.put("isWatermark",isWatermark);//是否需要转码
									result = module.call("ConvertVodFile", params);
									log.info("视频转码调用返回参数："+result);
									} catch (Exception e) {
										e.printStackTrace();
										log.error("调用转码接口失败。");
									}
								}
							}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				return new BaseResponse(ResponseCode.DATAERR,"fileId错误");
			}
			return new BaseResponse(ResponseCode.SUCCESS, "成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BaseResponse(ResponseCode.FAILURE, "失败");
	}
	
	/**
	 * 
	 * @Description: 根据主播ID 查询主播的视频回放
	 * @author xiaoxiong
	 * @date 2016年8月30日
	 * @version
	 */
	public Object queryAnchorVedio(AnchorVideoRequest request) {
		try {
			Map<String,Object> params=new HashMap<>();
			params.put("pageNo", request.getPageNo());
			params.put("pageSize", request.getPageSize());
			params.put("id", request.getId());
			params.put("fileUrl", fileUrl);
			if(request.getStartDate()!=null)
				params.put("startDate", request.getStartDate());
			if(request.getEndDate()!=null)
				params.put("endDate", request.getEndDate());
				
			List<Map<String,Object>> fileList=liveVodDao.queryAnchorVedio(params);
			Map<Object,Object> resultMap=new HashMap<>();//返回参数
//			List<Object> resultList=new ArrayList<>();
//			for(String fileid:fileList){
//				queryVideo(resultList, fileid);
//			}
			resultMap.put("fileList", fileList);
			MapResponse mapResponse=new MapResponse(ResponseCode.SUCCESS, "成功");
			mapResponse.setResponse(resultMap);
			return mapResponse;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BaseResponse(ResponseCode.FAILURE,"失败");
	}

	/**
	 * 
	 * @Description: 根据文件ID获取视频信息
	 * @author xiaoxiong
	 * @date 2016年8月30日
	 * @version
	 */
	public void queryVideo(List<Object> resultList,String fileId){
		try {
			QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Vod(),"GET");
			String result = null;
			try {
				TreeMap<String, Object> params = new TreeMap<String, Object>();
				params.put("fileIds.1",fileId);
				
				result = module.call("DescribeVodInfo", params);
			} catch (Exception e) {
				System.out.println("error..." + e.getMessage());
			}
			//返回对象
			JSONObject infoJson=JSONObject.parseObject(result);
			if(infoJson.getIntValue("code")==0){
				JSONArray jsonArray=JSONArray.parseArray(infoJson.getString("fileSet"));
				resultList.add(jsonArray.getJSONObject(0));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
