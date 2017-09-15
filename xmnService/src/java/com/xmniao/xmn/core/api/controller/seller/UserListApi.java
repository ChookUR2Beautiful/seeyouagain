package com.xmniao.xmn.core.api.controller.seller;

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

import com.xmniao.xmn.core.api.controller.xmer.SellerInfoApi;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.SelleridPageRequest;
import com.xmniao.xmn.core.live.entity.LiverInfo;
import com.xmniao.xmn.core.live.service.AnchorLiveRecordService;
import com.xmniao.xmn.core.live.service.LiveUserService;
import com.xmniao.xmn.core.verification.dao.UrsDao;
import com.xmniao.xmn.core.verification.dao.UrsInfoDao;
import com.xmniao.xmn.core.verification.entity.Urs;
import com.xmniao.xmn.core.verification.entity.UrsInfo;
import com.xmniao.xmn.core.verification.service.UrsService;
import com.xmniao.xmn.core.xmer.service.SellerService;


/**
 * 
*      
* 类名称：UserListApi   
* 类描述：   查询店铺光顾用户
* 创建人：xiaoxiong   
* 创建时间：2016年11月14日 下午4:49:35     
*
 */
@RequestMapping("seller")
@Controller
public class UserListApi implements BaseVControlInf{
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(SellerInfoApi.class);
	
	/**
	 * 商家业务类
	 */
	@Autowired
	private SellerService sellerService;
	
	@Autowired
	private AnchorLiveRecordService anchorLiveRecordService;
	
	@Autowired
	private String fileUrl;
		
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	
	@Autowired
	private SessionTokenService sessionService;
	
	@Autowired
	private UrsService ursService;
	
	@Autowired
	private LiveUserService liveUserService;
	
	@Autowired
	private UrsDao ursDao;
	
	@Autowired
	private UrsInfoDao ursInfoDao;
	
	@ResponseBody
	@RequestMapping("/consumer")
	public Object consumer(SelleridPageRequest request){
		List<ConstraintViolation> result = validator.validate(request);
		if(result != null && result.size()>0){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
			return versionControl(request.getApiversion(),request);
	}
	
	
	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1:
			return versionOne(object);
			default :
			return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}

	public Object versionOne(Object object) {
		
		SelleridPageRequest request = (SelleridPageRequest)object;
		String uid = sessionService.getStringForValue(request.getSessiontoken())+"";
		if(uid.equals("null")){
			uid="";
		}
		//查询用户的最近消费时间
		List<Map<String,Object>>  userList = sellerService.queryUidBySellerid(request);
		
		List<Map<String,Object>> reulstList = userList(userList,uid);
		
		Map<Object,Object> resultMap = new HashMap<>();
		resultMap.put("userList", reulstList);
		
		MapResponse mapResponse=new MapResponse(ResponseCode.SUCCESS, "成功");
		mapResponse.setResponse(resultMap);
		return mapResponse;
	}

	
	/**
	 * 
	 * @Description: 根据订单表返回用户最近消费时间和uid查寻用户头像和相似度
	 * @author xiaoxiong
	 * @date 2016年11月15日
	 */
	private List<Map<String, Object>> userList(List<Map<String, Object>> userList,String uid) {
		List<Map<String,Object>> resultList=new ArrayList<>();
		
		for(Map<String,Object> map : userList){
			try {
				//查询用户基本信息
//				UrsInfo ursInfo= anchorLiveRecordService.queryUrsByUid(Long.parseLong(map.get("uid")+""));
//				if(ursInfo==null){
//					log.info("查询用户信息ursInfo失败："+map.get("uid"));
//					continue;
//				}
				
				Urs urs = ursDao.queryUrsByUid(Integer.parseInt(map.get("uid")+""));
				UrsInfo ursInfo = ursInfoDao.queryUrsInfoByUid(Integer.parseInt(map.get("uid")+""));
				if(urs==null||ursInfo==null){
					log.info("没有找到用户基本信息:"+map.get("uid")+"");
					continue;
				}
				
				LiverInfo liveInfo = liveUserService.queryLiverByUid(map.get("uid")+"");
				String avatar = ursInfo.getAvatar()==null?"":fileUrl+ursInfo.getAvatar();//头像
				
				/*用户显出处理（如果用户没有昵称则显示登入名）*/
				String name="";
				if(urs.getNname()==null||urs.getNname().length()==0){
					if(urs.getUname()!=null){
						if(urs.getUname()!=null&&urs.getUname().length()==11){
							name=urs.getUname().substring(0,3)+"****"+urs.getUname().substring(7);
						}else{
//							name="微信用户"+urs.getUname().substring(3,7);
							name="匿名用户";
						}
					}else {
//						name="微信用户" + urs.getOpenid().substring(3,7);
						name="匿名用户";
					}
					
				}else{
						name=urs.getNname();
				}	     
				String sex = ursInfo.getSex() == null?"0": ursInfo.getSex()+"";			// 性别
				String utype = liveInfo == null?"2":liveInfo.getUtype()+"";	//用户类型
				map.put("avatar",avatar);
				map.put("sex", sex);
				map.put("name", name);
				map.put("utype",utype);
				
				int isfollow = 0; //是否关注
				if(uid.equals("")||uid.length()==0||uid.equals("null")){
					map.put("lable", 0);
					isfollow=0;
				}else{
					int similarity = lable(uid,map.get("uid")+"");//相似度
					map.put("lable", similarity);	
					/**
					 * 查询是否关注
					 */
					try {
						LiverInfo cliveInfo = liveUserService.queryLiverByUid(uid);
						/*如果没有找到用户直播记录则不返回给前端*/
						if(liveInfo==null){
							isfollow=0;
						}else{
							Map<String,Object> params=new HashMap<String, Object>();
							params.put("uid", cliveInfo.getId());
							params.put("cuid",liveInfo.getId());
							int ursColletUrsCount = ursService.ursColletUrsCount(params);
							if(ursColletUrsCount>0){
								isfollow=1;
							}
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
 				map.put("isfollow", isfollow);
				resultList.add(map);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return resultList;
	}
	
	/**
	 * 查询用户标签 0 无标签 1非常相似2一般相似
	 * @author xiaoxiong
	 * @date 2016年11月21日
	 * @param 第一个用户
	 * @param 第二个用户
	 */
	public int lable(String uid, String cuid) {
		int similarity = 0;
		try {
			/**
			 * 查询两个用户是否在同一个店消费过
			 */
			int billCount = ursService.isInSellerCons(uid,cuid);
			if(billCount>0)
			{
				similarity = 1;
			}else
			{
				/**
				 * 查询是否有同一关注记录
				 */
				int followCount = ursService.ursColletUrsCount(uid,cuid);
				if(followCount>0)
				{
					similarity = 2;
				}else
				{	
					/**
					 * 查询是否有同一收藏店铺记录
					 */
					int collectCount = ursService.ursCollectSellerCount(uid,cuid);
					if(collectCount>0)
					{
						similarity = 2;
					}else
					{
						/**
						 * 查询是否有同一浏览记录
						 */
						int browsedCount = ursService.ursBrowsedCount(uid,cuid);
						if(browsedCount>0)
						{
							similarity = 2;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return similarity;
	}


	

}
