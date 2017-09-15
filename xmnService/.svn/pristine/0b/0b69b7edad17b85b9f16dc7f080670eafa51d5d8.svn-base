/**
 * 2016年8月23日 下午2:17:59
 */
package com.xmniao.xmn.core.live.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.rocketmq.ProducerServiceImpl;
import com.xmniao.xmn.core.common.rocketmq.model.TopicInfo;
import com.xmniao.xmn.core.live.dao.SelfGiftDao;
import com.xmniao.xmn.core.util.DateUtil;

/**
 * @项目名称：xmnService_3.1.2_zb
 * @类名称：SelfGiftService
 * @类描述：
 * @创建人： yeyu
 * @创建时间 2016年8月23日 下午2:17:59
 * @version
 */
@Service
public class SelfGiftService {
	private Logger log=Logger.getLogger(SelfGiftService.class);
	@Autowired
	private SelfGiftDao selfgiftDao;
	
	@Autowired
	private PersonalCenterService  personalcenterService;
	
	@Autowired
	private LiveGiftsInfoService livegiftsinfoService; 
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Autowired
	private ProducerServiceImpl xmnGiftConsumerService;
	@Autowired
	private TopicInfo topicInfo;
	/**
	 * 
	* @Title: TakeDraw
	* @Description: 当前使用抽奖
	* @return Object
	 */
	public  Object TakeDraw( String randomkey,Integer uid){
	try {
		
		/*List<Map<Object,Object>> resultList=this.queryDrawList(1);
		if(resultList==null || resultList.size()<=0){
			return new BaseResponse(ResponseCode.SUCCESS, "未抽中");
		}
		RandomGift.initRedisDrawMap(randomkey, stringRedisTemplate,(long) 10, resultList);*/
		
		
		
		Map<Object,Object> paramMap=RandomGift.getGumDrawMap2(randomkey, stringRedisTemplate,uid+"");
		
		if(paramMap==null || paramMap.size()<=0){
			return new BaseResponse(ResponseCode.SUCCESS, "礼品抽取已结束");
		}
		if(paramMap.get("state")!=null && paramMap.get("state").equals("0")){
			return new BaseResponse(ResponseCode.SUCCESS, paramMap.get("msg")
					.toString());
		}
		if(paramMap.get("state")!=null && paramMap.get("state").equals("1")){
			
			Map<Object,Object> resultMap=new HashMap<>();
			resultMap.put("giftName ", paramMap.get("gift_name"));
			//丢入生成队列
			SendResult result=xmnGiftConsumerService.send(topicInfo, JSONObject.toJSONString(paramMap));
			log.info("ID:"+result.getMsgId()+"------"+result.getSendStatus());
//			System.out.println("ID:"+result.getMsgId()+"------"+result.getSendStatus());
			//返回参数
			MapResponse response=new MapResponse(ResponseCode.SUCCESS, "抽取礼品成功");
			response.setResponse(resultMap);
			return response;
		}
		
	} catch (Exception e) {
		log.error("抽奖异常");
		e.printStackTrace();
		return new BaseResponse(ResponseCode.FAILURE, e.getMessage());
	}
	return new BaseResponse(ResponseCode.SUCCESS, "礼品抽取已结束");
	} 
	
	/**
	 * 
	* @Title: ModifySelfGift
	* @Description: 更新个人礼物信息表
	* @param uid 寻蜜客ID    
	* @param gift_id 礼物ID  
	* @param gift_nums 礼物数，type=2时，不为空 
	* @param type 1,2:礼物 3：积分 
	* @param  integral 获取积分数，type=3时，不为空
	* @return Integer
	 * @throws Exception 
	 */
	public void ModifySelfGift(Integer uid,Integer gift_id,Integer type,Integer integral ) throws Exception 
	{
		try {
			//类型为1或者2时为礼物
			if(type==2||type==1){
				this.addSelfGift(uid, gift_id);
			}else if(type==3){
				//类型为3时获取到的为积分
				this.ModifyIntegral(uid, integral);
			}
		} catch (Exception e) {
			log.error("更新礼物信息异常");
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}
	
	
	
	
	/**
	 * 
	* @Title: addSelfGift
	* @Description: 获得礼品数据，同步信息
	* @return void
	 */
	public void addSelfGift(Integer uid,Integer gift_id) throws Exception{
		try{
			Integer selfnums=0;
			//查询个人礼物信息表中是否存在该礼物
			Map<Object, Object> personMap=personalcenterService.queryLiverPersonByUid(uid);
			if(personMap==null || personMap.size()<=0){
				log.info("获取用户信息失败");
				return;
			}
			Integer liver_id=Integer.parseInt(personMap.get("anchorid").toString());
			Map<Object,Object> selfGiftMap=this.querySelfGiftToObj(liver_id, gift_id);
			
			
			if(selfGiftMap!=null && selfGiftMap.size()>0){
				//若是存在修改礼物信息记录
				Map<Object,Object> giftMap=new HashMap<>();
				giftMap.put("gift_id", gift_id);//礼物id
				giftMap.put("liver_id", liver_id);//直播的用户ID
				Integer gifsums=0;
				if(StringUtils.isNotEmpty(selfGiftMap.get("gift_nums").toString())){
					 gifsums=Integer.parseInt(selfGiftMap.get("gift_nums").toString());
				}
				giftMap.put("gift_nums", gifsums+1);//礼物个数
				giftMap.put("update_time", DateUtil.format(new Date()));//更新时间
				selfnums=this.updateSelfGift(giftMap);
			}else{
				//如果不存在，新增礼物信息
				Map<Object,Object> giftMap=new HashMap<>();
				giftMap.put("gift_id", gift_id);//礼物id
				giftMap.put("liver_id", liver_id);//直播的用户ID
				giftMap.put("gift_nums", 1);//礼物个数
				giftMap.put("create_time", DateUtil.format(new Date()));//创建时间
				giftMap.put("update_time", null);//更新时间
				selfnums=this.insertSelfGift(giftMap);
			}
			if(selfnums<1){
				log.error("抽奖获取礼品失败");
			}else{
			log.info("抽奖获取礼品成功");
			}
		}catch(Exception e){
			log.error("抽取礼品失败");
			e.printStackTrace();
			throw new Exception("抽取礼品失败"+e.getMessage());
		}
	}
	
	
	
	
	
	/**
	 * 
	* @Title: ModifyIntegral
	* @Description: 抽奖获取积分，并更新到寻蜜客钱包
	* @param uid 寻蜜客ID
	* @param integral 抽奖获取积分数量
	* @return Object
	 */
	public void ModifyIntegral(Integer uid,Integer integral) throws Exception{
		try{
			Map<String, String> xmnWalletMap =new HashMap<>();
			xmnWalletMap.put("uId", uid+"");//用户ID
			xmnWalletMap.put("integral", integral+"");//积分余额
			xmnWalletMap.put("userType", "1");//用户
			xmnWalletMap.put("orderId",System.currentTimeMillis()+"");//订单ID型 
			xmnWalletMap.put("remark", "抽取礼品获得"+integral+"积分");//描述
			xmnWalletMap.put("rType", "4");
			xmnWalletMap.put("option", "0");
			Map<String, String>  xmnMapResp= livegiftsinfoService.subtractXmnWalletBlance(xmnWalletMap);
			
			if(xmnMapResp!=null ){
				if(xmnMapResp.get("state").equals("0")){
					log.info("抽取礼品获取积分成功");
				}else{
				log.error("获取积分失败,失败原因："+xmnMapResp.get("msg").toString());
				}
			}
		}catch(Exception e){
			log.error("抽奖获取积分异常");
			e.printStackTrace();
			throw new Exception("抽奖获取积分异常"+e.getMessage());
		}
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * 
	* @Title: querySelfGiftList
	* @Description: 获取个人礼物列表
	* @return List<Map<Object,Object>>
	 * @throws Exception 
	 */
	public List<Map<Object,Object>> querySelfGiftList(Integer uid) throws Exception{
		List<Map<Object,Object>> selfGiftList=null;
		try{
		selfGiftList=selfgiftDao.querySelfGiftList(uid);
		}catch(Exception e){
			log.error("获取个人礼物列表异常");
			e.printStackTrace();
			throw new Exception("获取个人礼物列表异常");
		}
		return selfGiftList;
	}
	
	/**
	 * 
	* @Title: querySelfGiftToObj
	* @Description: 获取某个礼物信息
	* @return Map<Object,Object>
	 * @throws Exception 
	 */
	public Map<Object,Object> querySelfGiftToObj(Integer liver_id,Integer gift_id) throws Exception{
		Map<Object,Object> selfGiftMap=null;
		try{
			Map<Object,Object> param=new HashMap<Object, Object>();
			param.put("liver_id", liver_id);
			param.put("gift_id", gift_id);
			selfGiftMap=selfgiftDao.querySelfGiftToObj(param);
		}catch(Exception e){
			log.error("获取个人礼物对象异常");
			e.printStackTrace();
			throw new Exception("获取个人礼物对象异常");
		}
		return selfGiftMap;
	}
	
	/**
	 * 
	* @Title: insertSelfGift
	* @Description: 新增礼物信息
	* @return Integer
	 * @throws Exception 
	 */
	public Integer insertSelfGift(Map<Object,Object> giftMap) throws Exception{
		Integer selfnums=0;
		try {
			selfnums=selfgiftDao.insertSelfGift(giftMap);
		} catch (Exception e) {
			log.error("新增个人礼物信息异常");
			e.printStackTrace();
			throw new Exception("新增个人礼物信息异常");
		}
		return selfnums;
	}
	
	/**
	 * 
	* @Title: modifySelfGift
	* @Description: 修改礼物信息
	* @return Integer
	 * @throws Exception 
	 */
	public Integer updateSelfGift(Map<Object,Object> giftMap) throws Exception{
		
		Integer selfnums=0;
		try {
			selfnums=selfgiftDao.updateSelfGift(giftMap);
		} catch (Exception e) {
			log.error("新增个人礼物信息异常");
			e.printStackTrace();
			throw new Exception("新增个人礼物信息异常");
		}
		return selfnums;
	}
	
	/**
	 * 
	* @Title: queryDrawList
	* @Description: 获取抽奖大礼包礼物
	* @return List<Map<Object,Object>>
	 */
	public List<Map<Object,Object>> queryDrawList(Integer gift_bag_id) throws Exception{
		List<Map<Object,Object>> resultList=null;
		try {
			resultList=selfgiftDao.queryDrawList(gift_bag_id);
		} catch (Exception e) {
			log.error("获取抽奖大礼包礼物失败");
			e.printStackTrace();
			throw new Exception("获取抽奖大礼包礼物失败");
		}
		return resultList;
	}
	
}
