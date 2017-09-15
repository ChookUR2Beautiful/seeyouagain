package com.xmniao.xmn.core.xmer.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.AppLoadUrlRequest;
import com.xmniao.xmn.core.xmer.dao.AppLoadUrlDao;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：AppLoadUrlService   
* 类描述：   获取app客户端下载地址
* 创建人：yezhiyong   
* 创建时间：2016年6月16日 下午2:46:38   
* @version    
*
 */
@Service
public class AppLoadUrlService {
	
	/**
	 * 注入appLoadUrlDao
	 */
	@Autowired
	private AppLoadUrlDao appLoadUrlDao;
	
	/**
	 * 注入文件头
	 */
	@Autowired
	private String fileUrl;

	/**
	 * 
	* @Title: queryLoadUrl
	* @Description: 获取app客户端下载地址
	* @return Object    返回类型
	* @author
	* @throws
	 */
	public Object queryLoadUrl(AppLoadUrlRequest appLoadUrlRequest) {
		try {
			//手机类型
			Integer vtype = appLoadUrlRequest.getVtype();
			//查询下载地址
			Map<Object, Object> resultMap = appLoadUrlDao.queryLoadUrlByVtype(vtype);
			if (vtype ==1 && resultMap.get("url") != null) {
				resultMap.put("url", fileUrl + resultMap.get("url"));
			}
			//响应
			MapResponse response = new MapResponse(ResponseCode.SUCCESS, "查询成功");
			response.setResponse(resultMap);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "获取app下载地址失败，请联系管理员");
		}
	}

}
