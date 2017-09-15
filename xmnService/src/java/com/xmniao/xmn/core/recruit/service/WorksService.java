package com.xmniao.xmn.core.recruit.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.MongoBaseService;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.IDRequest;
import com.xmniao.xmn.core.common.request.WorksListRequest;
import com.xmniao.xmn.core.recruit.dao.RecruitDao;
import com.xmniao.xmn.core.recruit.dao.WorksDao;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.xmer.entity.MSeller;

/**
 * 
* 项目名称：xmnService   
* 类名称：WorksService   
* 类描述：岗位Service   
* 创建人：liuzhihao   
* 创建时间：2016年5月20日 下午6:21:43   
* @version    
*
 */
@Service
public class WorksService {

	//注入岗位dao
	@Autowired
	private WorksDao worksDao;
	
	//注入mongoDB
	@Autowired
	private MongoBaseService mongoBaseService;
	
	
	@Autowired
	private SessionTokenService sessionTokenServcie;
	
	//注入根路径
	@Autowired
	private String fileUrl;
	@Autowired
	private RecruitDao  recruitDao;
	
	/**
	 * 
	* @Title: queryWorksList
	* @Description: 岗位信息列表接口实现类
	* @return Object    返回类型
	* @throws
	 */
	public Object queryWorksList(WorksListRequest worksListRequest){
		try{
			Map<Object,Object> map = new HashMap<Object,Object>();
			map.put("id", Integer.valueOf(worksListRequest.getId()));
			map.put("salary", worksListRequest.getSalary());
			map.put("scale", worksListRequest.getScale());
			//获取岗位基本信息
			List<Map<Object,Object>> result = new ArrayList<Map<Object,Object>>();
			List<Map<Object,Object>> worklist = worksDao.queryWorksList(map);
			for(Map<Object,Object> workmap : worklist){
				Integer sellerid = Integer.valueOf(workmap.get("sellerid").toString());
				//获取店铺logo
				Map<Object,Object> sellermap = worksDao.querySellerLogo(sellerid);
				workmap.put("sellerpic", fileUrl+sellermap.get("picurl"));//把logo存入map中
				workmap.remove("sellerid");//删除之前map中的sellerid
				result.add(workmap);
			}
			map.clear();
			map.put("jobs", result);
			MapResponse response = new MapResponse(ResponseCode.SUCCESS,"成功");
			response.setResponse(map);
			return response;
		}catch(Exception e){
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE,"未知错误");
		}
	}
	
	/**
	 * 
	* @Title: queryWorksInfo
	* @Description:岗位详情接口实现类
	* @return Object    返回类型
	* @throws
	 */
	public Object queryWorksInfo(IDRequest idRequest){
		try{
			Integer id = idRequest.getId();
			Map<Object,Object> map = worksDao.queryWorksInfo(id);
			//获取商户id
			Integer sellerid = Integer.valueOf(map.get("sellerid").toString());
			//重复使用map，删除sellerid数据
			map.remove("sellerid");
			//查询标签列表
			Map<Object,Object> tagmap = new HashMap<Object,Object>();
			//岗位标签
			tagmap.put("id", Integer.valueOf(map.get("recruitid").toString()));
			tagmap.put("type",1);
			List<Map<Object,Object>> jobs = worksDao.queryWorksTagInfo(tagmap);
			//待遇标签
			tagmap.put("type", 3);
			List<Map<Object,Object>> workwelfare = worksDao.queryWorksTagInfo(tagmap);
			map.put("jobs", jobs);
			map.put("workwelfare", workwelfare);
			//通过mongoDB获取商户地址
			MSeller mseller = mongoBaseService.findOne("sellerid", sellerid+"",MSeller.class);
			if(mseller == null){
				mseller = new MSeller();
				Map<Object,Object> sellermap = worksDao.querySellerLogo(sellerid);
				mseller.setPic_logo(fileUrl+sellermap.get("picurl"));
				Map<Object,Object> areamap = new HashMap<Object,Object>();
				areamap.put("areaid", Integer.valueOf(sellermap.get("city").toString()));
				areamap.put("pid", Integer.valueOf(sellermap.get("province").toString()));
				String city = worksDao.queryAreaName(areamap);
				areamap.put("areaid", Integer.valueOf(sellermap.get("area").toString()));
				areamap.put("pid", Integer.valueOf(sellermap.get("city").toString()));
				String area = worksDao.queryAreaName(areamap);
				mseller.setCity(city);
				mseller.setArea(area);
				mseller.setAddress(sellermap.get("address").toString());
			}
			map.put("sellerpic", mseller.getPic_logo());
			map.put("sprovince", mseller.getProvince());
			map.put("scity", mseller.getCity());
			map.put("sarea", mseller.getArea());
			map.put("saddress", mseller.getAddress());
			try {
				if(idRequest.getSessiontoken()!=null){
					String uid=sessionTokenServcie.getStringForValue(idRequest.getSessiontoken())+"";
					if(!uid.equals("null")&&!uid.equals("")){																		
						Map<Object,Object> param=new HashMap<>();
						param.put("type", 1);//商家查看用户简历
						param.put("suid", uid);//商家ID
						param.put("cvid", id);//招聘岗位
						param.put("vdate", DateUtil.now());
						recruitDao.deleteRecuritView(param);//删除以前记录
						recruitDao.addRecruitView(param);//添加查看记录
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			MapResponse response = new MapResponse(ResponseCode.SUCCESS,"成功");
			response.setResponse(map);
			return response;
		}catch(Exception e){
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE,"未知错误，请联系管理员");
		}
	}
}
