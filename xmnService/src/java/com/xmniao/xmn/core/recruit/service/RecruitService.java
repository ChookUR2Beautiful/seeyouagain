package com.xmniao.xmn.core.recruit.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.RecruitTagRequest;
import com.xmniao.xmn.core.recruit.dao.RecruitDao;

/**
 * 
*    
* 项目名称：saasService   
* 类名称：RecruitService   
* 类描述：   招聘service
* 创建人：xiaoxiong   
* 创建时间：2016年5月17日 下午5:11:28   
* @version    
*
 */
@Service
public class RecruitService {
	/**
	 * 注入Dao
	 */
	@Autowired
	private RecruitDao recruitDao;
	
	/**
	 * 注入商户信息Dao
	 */

	/**
	 * 
	* @Title: queryRecruitTagList
	* @Description: 查询所有标签
	* @return Object    返回类型
	* @throws
	 */
	public Object queryRecruitTagList(RecruitTagRequest recruitTagRequest) {
		try {
			//查询系统标签标签
			List<Map<Object,Object>> list=recruitDao.queryRecruitTagList(recruitTagRequest);
			
			Map<Object,Object> paramMap=new HashMap<>();
			//自我评价
			if(recruitTagRequest.getType()==1){  
				paramMap.put("entityType", 4);	//关系实体类型
			}
			
			if(recruitTagRequest.getType()==2){
				paramMap.put("entityType", 5);	//关系实体类型
			}
				// 查询简历
				Integer recruitID=recruitDao.queryUsedrCVByuid(recruitTagRequest.getUid());
				if(recruitID!=null){
					paramMap.put("pojoID", recruitID);	//实体ID
					paramMap.put("tagtype", recruitTagRequest.getType());//3 福利标签
					List<Map<Object,Object>> recruitList=recruitDao.queryCustomTag(paramMap);//查询自定义标签
					if(recruitList!=null&&recruitList.size()>0){
						list.addAll(recruitList);
					}
				}
			//查询自定义岗位要求
			Map<Object,Object> map=new HashMap<>();
			MapResponse mapResponse=new MapResponse(ResponseCode.SUCCESS,"查询成功");
			map.put("data", list);
			mapResponse.setResponse(map);
			return mapResponse;
			
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "未知错误，查询标签失败！");
		}
	}
	
	

}
