package com.xmniao.xmn.core.catehome.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.catehome.dao.CateHomeDao;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.CateCommentRequest;
import com.xmniao.xmn.core.common.request.CateHomeRequest;
import com.xmniao.xmn.core.common.request.IDRequest;
import com.xmniao.xmn.core.common.request.PageRequest;
import com.xmniao.xmn.core.integral.entity.BannerEntity;
import com.xmniao.xmn.core.util.Base64;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.verification.dao.BillDao;
import com.xmniao.xmn.core.verification.dao.UrsDao;
import com.xmniao.xmn.core.verification.dao.UrsInfoDao;
import com.xmniao.xmn.core.verification.entity.Urs;
import com.xmniao.xmn.core.verification.entity.VerifyResponseBean;
import com.xmniao.xmn.core.xmer.dao.AreaDao;
import com.xmniao.xmn.core.xmer.dao.SellerInfoDao;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：CateHomeService   
* 类描述：   发现美食首页信息Service
* 创建人：yezhiyong   
* 创建时间：2016年6月21日 上午9:07:54   
* @version    
*
 */
@Service
public class CateHomeService {

	/**
	 * 注入cateHomeDao
	 */
	@Autowired
	private CateHomeDao cateHomeDao;
	
	/**
	 * 注入billDao
	 */
	@Autowired
	private BillDao billDao;
	
	/**
	 * 注入areaDao
	 */
	@Autowired
	private AreaDao areaDao;
	
	/**
	 * 注入ursDao
	 */
	@Autowired
	private UrsDao ursDao;
	
	/**
	 * ursInfoDao
	 */
	@Autowired
	private UrsInfoDao ursInfoDao;
	
	/**
	 * sellerInfoDao
	 */
	@Autowired
	private SellerInfoDao sellerInfoDao;
	
	/**
	 * 注入redis缓存
	 */
	@Autowired
	private SessionTokenService sessionTokenService;
	
	/**
	 * 注入fileUrl
	 */
	@Autowired
	private String fileUrl;
	
	/**
	 * 
	* @Title: queryCate
	* @Description: 查询发现美食首页信息
	* @return Object    返回类型
	* @author
	* @throws
	 */
	public Object queryCateHome(CateHomeRequest cateHomeRequest) {
		try {
			//查询轮播图列表信息
			List<Map<Object, Object>> advertisings;
			try {
				advertisings = cateHomeDao.queryCateAdvertisingList(cateHomeRequest.getCity());
			} catch (Exception e) {
				e.printStackTrace();
				return new BaseResponse(ResponseCode.FAILURE, "查询发现美食首页轮播图列表信息失败");
			}
			
			//查询分类信息(大类)
			List<Map<Object, Object>> categorys;
			try {
				categorys = cateHomeDao.queryCateCategory();
			} catch (Exception e) {
				e.printStackTrace();
				return new BaseResponse(ResponseCode.FAILURE, "查询发现美食首页分类信息(大类)失败");
			}
			
			//查询banner信息
			List<Map<Object,Object>> banners = new ArrayList<Map<Object,Object>>();
			try{
				List<BannerEntity> bannerList = cateHomeDao.queryBannerList();
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
							if(sort==null)sort=0;
							Integer logRequired = json.getInteger("logRequired");//图片是否需要登录
							picMap.put("url", fileUrl+url);
							picMap.put("type", type);
							picMap.put("content", content);
							picMap.put("sort", sort);
							if (logRequired == null) {
								picMap.put("logRequired", 0);
							}else {
								picMap.put("logRequired", logRequired);
							}
							picList.add(picMap);
						}
						
						//图片排序
						 Collections.sort(picList, new Comparator<Map<Object, Object>>(){
					            public int compare(Map<Object, Object> arg0, Map<Object, Object> arg1) {
					                return arg1.get("sort").toString().compareTo(arg0.get("sort").toString());
					            }
					        });
						map.put("contents", picList);
						banners.add(map);
					}
				}
			}catch(Exception e){
				e.printStackTrace();
				return new BaseResponse(ResponseCode.FAILURE, "查询发现美食首页banner信息失败");
			}
			
			//响应
			MapResponse response = new MapResponse(ResponseCode.SUCCESS, "查询发现美食首页信息成功");
			Map<Object, Object> resultMap = new HashMap<>();
			resultMap.put("advertisings", advertisings);
			resultMap.put("categorys", categorys);
			
			//banner图排序展示
			 Collections.sort(banners, new Comparator<Map<Object, Object>>(){
		            public int compare(Map<Object, Object> arg0, Map<Object, Object> arg1) {
		                return arg1.get("sort").toString().compareTo(arg0.get("sort").toString());
		            }
		        });
			resultMap.put("banners", banners);
			response.setResponse(resultMap);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "查询发现美食首页信息失败");
		}
	}

	/**
	 * 
	* @Title: insertCateComment
	* @Description: 发表评价
	* @return Object    返回类型
	* @author
	* @throws
	 */
	public Object insertCateComment(CateCommentRequest cateCommentRequest) {
		try {
			//查询是否存在该订单
			VerifyResponseBean billBean = billDao.getBillById(cateCommentRequest.getBid());
			if (billBean == null) {
				return new BaseResponse(ResponseCode.FAILURE, "查无此订单号,发表评价失败");
			}
			
			//查询是否存在订单评价信息
			Map<Object, Object> commentMap = cateHomeDao.queryCommentByBid(cateCommentRequest.getBid());
			if (commentMap != null) {
				return new BaseResponse(ResponseCode.CATE_COMMENT_RESUBMIT, "您已经对该订单评价了，请不要重复评价");
			}
			
			//组装参数
			Map<Object, Object> paramMap = new HashMap<>();
			paramMap.put("bid", cateCommentRequest.getBid());
			paramMap.put("sellerid", cateCommentRequest.getSellerid());
			paramMap.put("fbranch", cateCommentRequest.getFbranch());
			paramMap.put("hbranch", cateCommentRequest.getHbranch());
			paramMap.put("jbranch", cateCommentRequest.getJbranch());
			paramMap.put("kbranch", cateCommentRequest.getKbranch());
			String uid = sessionTokenService.getStringForValue(cateCommentRequest.getSessiontoken())+"";
			paramMap.put("uid", uid);
			
			//查询用户呢称
			Urs urs = ursDao.queryUrsByUid(Integer.parseInt(uid));
			paramMap.put("nname", urs.getNname());
			
			//查询用户头像
			String avatar = ursInfoDao.queryUrsAvatarByUid(Integer.parseInt(uid));
			paramMap.put("avatar", avatar);
			paramMap.put("sdate", DateUtil.format(DateUtil.now(), "yyyy-MM-dd HH:mm:ss"));
			
			//查询店铺的人均消费
			Map<Object, Object> sellerDetailMap = sellerInfoDao.querySellerDetailedBySellerid(cateCommentRequest.getSellerid());
			if (sellerDetailMap != null) {
				paramMap.put("percapita", sellerDetailMap.get("consume"));
			}
			
			//保存订单评价信息
			int commentResult = cateHomeDao.insertCateComment(paramMap);
			
			//修改订单评价状态为已评价
			int billResult = billDao.modifyCommentStatus(cateCommentRequest.getBid());
			if (commentResult == 0 || billResult == 0) {
				return new BaseResponse(ResponseCode.FAILURE, "发表评价失败");
			}
			
			//响应
			BaseResponse response = new BaseResponse(ResponseCode.SUCCESS, "发表评价成功");
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "发表评价失败");
		}
	}

	/**
	 * 
	* @Title: queryCateCommentList
	* @Description: 查询评价列表
	* @return Object    返回类型
	* @author
	* @throws
	 */
	public Object queryCateCommentList(PageRequest pageRequest) {
		try {
			//验证uid
			String uid = sessionTokenService.getStringForValue(pageRequest.getSessiontoken()) + "";
			if (StringUtils.isEmpty(uid)) {
				return new BaseResponse(ResponseCode.FAILURE, "token验证失败，请重新登录");
			}
			
			//组装参数
			Map<Object, Object> paramMap = new HashMap<>();
			paramMap.put("page", pageRequest.getPage());
			paramMap.put("limit", Constant.PAGE_LIMIT);
			paramMap.put("uid",uid);
			
			//查询用户的评价列表
			List<Map<Object, Object>> cateCommentList = cateHomeDao.queryCateCommentList(paramMap);
			Map<Object, Object> resultMap = new HashMap<>();
			if (cateCommentList != null && cateCommentList.size() > 0) {
				for (Map<Object, Object> map : cateCommentList) {
					if (StringUtils.isNotEmpty(map.get("picurl").toString())) {
						map.put("picurl", fileUrl + map.get("picurl"));
					}
				}
				resultMap.put("cateComments", cateCommentList);
			}else {
				cateCommentList = new ArrayList<>();
				resultMap.put("cateComments", cateCommentList);
			}
			
			//响应
			MapResponse response = new MapResponse(ResponseCode.SUCCESS, "查询用户评价列表成功");
			response.setResponse(resultMap);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "查询评价列表失败");
		}
	}

	/**
	 * 
	* @Title: dedeteCateComment
	* @Description: 删除评价信息
	* @return Object    返回类型
	* @author
	* @throws
	 */
	public Object dedeteCateComment(IDRequest idRequest) {
		//验证
		String uid = sessionTokenService.getStringForValue(idRequest.getSessiontoken()) + "";
		if (StringUtils.isEmpty(uid)) {
			return new BaseResponse(ResponseCode.FAILURE, "token失效，请重新登录");
		}
		
		//查询是否存在该评价信息
		Map<Object, Object> commentMap = cateHomeDao.queryCommentByCid(idRequest.getId());
		if (commentMap == null) {
			return new BaseResponse(ResponseCode.FAILURE, "不存在该订单评价,请重新操作");
		}
		
		//删除评价信息
		int result = cateHomeDao.dedeteCateCommentByCid(idRequest.getId());
		if (result ==0) {
			return new BaseResponse(ResponseCode.FAILURE, "删除订单信息失败,请联系管理员");
		}
		
		//响应
		BaseResponse response = new BaseResponse(ResponseCode.SUCCESS, "删除评价信息成功");
		return response;
	}
	/**
	 * 搜索联想
	 * @author xiaoxiong
	 * @date 2016年11月24日
	 */
	public List<String> searchConnectList(Map<String, Object> params) {
		try {
			return cateHomeDao.searchConnectList(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
	
	
	
}
