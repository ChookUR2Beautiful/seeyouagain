package com.xmniao.xmn.core.recruit.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.EditUserCVRequest;
import com.xmniao.xmn.core.recruit.dao.UserDao;
import com.xmniao.xmn.core.recruit.entity.EditUserInfo;
import com.xmniao.xmn.core.recruit.entity.UserString;
import com.xmniao.xmn.core.util.DateUtil;

@Service
public class UserService {

	//注入dao
	@Autowired
	private UserDao userDao;
	
	//注入缓存
	@Autowired
	private SessionTokenService sessionTokenService;
	
	//注入根路劲
	@Autowired
	private String fileUrl;
	
	
	//用户填写简历信息资料
	public Object editUserInfo(EditUserCVRequest userRequest){
		try{
			//存简历基本信息
			Map<Object,Object> map = new HashMap<Object,Object>();
			//获取简历信息
			EditUserInfo userinfo = JSON.parseObject(userRequest.getInfo(), EditUserInfo.class);
			map.put("name", userinfo.getName());
			String uid = sessionTokenService.getStringForValue(userRequest.getSessiontoken()).toString();
			if(uid.equals("") || uid.equals("null")){
				return new BaseResponse(ResponseCode.TOKENERR,"token已过期,请重新登录");
			}
			map.put("uid",uid);//用户id
			map.put("sex", userinfo.getSex());//用户性别
			map.put("age", userinfo.getAge());//用户年龄
			map.put("degrees", userinfo.getDegrees());
			map.put("phoneid", userinfo.getPhoneid());//用户电话
			map.put("experie", userinfo.getExperie());//经验
			map.put("salary", userinfo.getSalary());
			map.put("workcity", userinfo.getWorkcity());
			map.put("headpic", userinfo.getPhoto().replace(fileUrl, ""));//头像
			map.put("sdate", DateUtil.format(DateUtil.now(),"yyyy-MM-dd HH:mm:ss"));
			//修改
			if(userRequest.getId() != null){
				map.put("ishide", userRequest.getIshide());
				map.put("shieldshop", userRequest.getShieldshop());
				map.put("id", userRequest.getId());
				//删除所有的标签
				userDao.deleteUserTagInfo(userRequest.getId());
				userDao.editUserCVInfo(map);
				return updateOrInsert(userinfo,userRequest.getId());
			}else{
				//第一次添加简历：插入
				userDao.InseetUserCVInfo(map);
				return updateOrInsert(userinfo,Integer.valueOf(map.get("id").toString()));
			}
		}catch(Exception e){
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE,"未知错误，请联系管理员");
		}
	}
	
	public Object updateOrInsert(EditUserInfo userinfo,int result){
		JSONArray jobs = JSON.parseArray(userinfo.getJobs());
		if(getInsertResult(jobs,3,result) == 0){
			return new BaseResponse(ResponseCode.FAILURE,"编辑失败");
		}
		//做过工作标签
		JSONArray works = JSON.parseArray(userinfo.getWorks());
		if(getInsertResult(works,2,result) == 0){
			return new BaseResponse(ResponseCode.FAILURE,"编辑失败");
		}
		//培训标签
		JSONArray trains = JSON.parseArray(userinfo.getTrains());
		if(getInsertResult(trains,4,result) == 0){
			return new BaseResponse(ResponseCode.FAILURE,"编辑失败");
		}
		//自我评价
		JSONArray evaluate = JSON.parseArray(userinfo.getEvaluate());
		if(getInsertResult(evaluate,4,result) == 0){
			return new BaseResponse(ResponseCode.FAILURE,"编辑失败");
		}
		return new BaseResponse(ResponseCode.SUCCESS,"编辑成功");
	}
	
	private int getInsertResult(JSONArray json,int type,int id){
		//存简历标签信息
		List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
		//存放插入信息参数
		Map<Object,Object> map = new HashMap<Object,Object>();
		for(int i=0; i<json.size(); i++){
			UserString user = JSON.parseObject(json.getString(i), UserString.class);
			map.put("id", id);
			map.put("tagid", user.getId());
			map.put("type", type);
			map.put("sdate", DateUtil.format(DateUtil.now(), "yyyy-MM-dd HH:mm:ss"));
			list.add(map);
		}
		return userDao.insertUserTagInfo(list);
	}
	
	/**
	 * 
	* @Title: viewUserInfo
	* @Description:查看我的简历信息接口实现类
	* @return Object    返回类型
	* @throws
	 */
	public Object viewUserInfo(BaseRequest baseRequest){
		try{
			//从缓存中获取用户id
			String uid = sessionTokenService.getStringForValue(baseRequest.getSessiontoken()).toString();
			if(uid.equals("") || uid.equals("null")){
				return new BaseResponse(ResponseCode.TOKENERR,"token已过期，请重新登录");
			}
			//通过用户id查询用户简历信息
			Map<Object,Object> usermap = userDao.queryUserInfo(Integer.valueOf(uid));
			if(usermap == null){//如果为空说明用户还没有编辑简历
				return new BaseResponse(ResponseCode.FAILURE,"亲，你还没有编辑个人简历哦");
			}
			//获取用户屏蔽的商铺
			Integer sellerid = Integer.valueOf(usermap.get("shieldshopid").toString());
			//获取用户屏蔽商铺的名称
			String shieldshopname = userDao.querySellerName(sellerid);
			usermap.put("shieldshopname", shieldshopname);
			Map<Object,Object> tagmap = new HashMap<Object,Object>();
			tagmap.put("id", usermap.get("id"));
			//获取用户希望的工作岗位标签列表
			tagmap.put("type", 3);
			List<Map<Object,Object>> jobs = userDao.queryUserTagInfo(tagmap);
			//获取用户曾做过的工作岗位标签列表
			tagmap.put("type", 2);
			List<Map<Object,Object>> works = userDao.queryUserTagInfo(tagmap);
			//获取用户培训经历标签列表
			tagmap.put("type", 4);
			List<Map<Object,Object>> trains = userDao.queryUserTagInfo(tagmap);
			//获取用户自我评价标签列表
			tagmap.put("type", 5);
			List<Map<Object,Object>> evaluates = userDao.queryUserTagInfo(tagmap);
			//汇总响应参数
			usermap.remove("id");//删除不需要的数据
			usermap.put("jobs", jobs);
			usermap.put("works", works);
			usermap.put("trains", trains);
			usermap.put("evaluates", evaluates);
			MapResponse response = new MapResponse(ResponseCode.SUCCESS,"成功");
			response.setResponse(usermap);
			return response;
		}catch(Exception e){
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE,"未知错误，请联系管理员");
		}
	}
}
