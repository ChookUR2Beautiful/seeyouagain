package com.xmniao.xmn.core.live.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ObjResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.LiveLedgerRecordRequest;
import com.xmniao.xmn.core.live.dao.LiveLedgerRecordDao;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.entity.LiveLedgerRecord;
import com.xmniao.xmn.core.live.entity.LiverInfo;
import com.xmniao.xmn.core.recruit.service.UserService;
import com.xmniao.xmn.core.thrift.LiveOrderService;
import com.xmniao.xmn.core.thrift.ResponseData;
import com.xmniao.xmn.core.thrift.business.java.OrderService;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.util.ThriftBusinessUtil;
import com.xmniao.xmn.core.util.ThriftUtil;
import com.xmniao.xmn.core.verification.dao.UrsDao;
import com.xmniao.xmn.core.verification.entity.Urs;
import com.xmniao.xmn.core.verification.service.UrsService;

@Service
public class LiveLedgerRecordService {
	
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(LiveHomeService.class);
	
	/**
	 * 注入sessionTokenService
	 */
	@Autowired
	private SessionTokenService sessionTokenService;
	
	/**
	 * 注入LiveLedgerRecordDao
	 */
	@Autowired
	private LiveLedgerRecordDao liveLedgerRecordDao;
	
	/**
	 * 注入propertiesUtil
	 */
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	/**
	 * 注入propertiesUtil
	 */
	@Autowired
	private ThriftUtil thriftUtil;
	
	/**
	 * 注入propertiesUtil
	 */
	@Autowired
	private ThriftBusinessUtil thriftBusinessUtil;
	
	/**
	 * 注入redis
	 * */
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	private UrsDao ursDao;
	

	
	/**
	 * 每天获取用户是否可领平台分账红包   仅限推荐用户
	 * @param BaseRequest request
	 * @return object
	 * */
	public Object queryLiveRedPacketBranch(LiveLedgerRecordRequest request){
		MapResponse response = null;
		Map<Object, Object> result = new HashMap<Object, Object>();
		StringBuffer sb = new StringBuffer(DateUtil.format(new Date(),DateUtil.daySimpleFormater));
		String live_redpacket_redis = "";
		try {
			sb = sb.append(" ").append(propertiesUtil.getValue("getRedPacketsTime", "conf_common.properties"));
			String currDate = sb.toString();
			long currDateLong= DateUtil.parse(currDate).getTime();//组装红包领取时间  每天九点开始领取
			long nowDateLong = new Date().getTime();//当前用户访问时间
			
			if (nowDateLong>currDateLong) {
				String uid = sessionTokenService.getStringForValue(request.getSessiontoken()) + "";
				if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
					result.put("isLogged", 0);//未登陆的情况下  显示立即行动页面 
					result.put("isReceive", 0);//不显示领取页面
					response = new MapResponse(ResponseCode.SUCCESS, "红包查询成功");
				}else {
					//使用redis key 记录incr函数
					String hourSimpleDateFormat = propertiesUtil.getValue("hourSimpleDateFormat", "conf_common.properties");
					live_redpacket_redis = "live_redpacket_"+DateUtil.format(new Date(),hourSimpleDateFormat)+"_"+uid;
					
					Long resultNum = stringRedisTemplate.opsForValue().increment(live_redpacket_redis, 1);
					//设置时间
					stringRedisTemplate.expire(live_redpacket_redis, 1, TimeUnit.DAYS);
					//下面是已经登录状态下
					if (resultNum>1) {
						result.put("isLogged", 1);//不显示行动页面  如果一登录 并且已经领取过 处理
						result.put("isReceive", 0);//不显示领取页面
						response = new MapResponse(ResponseCode.SUCCESS, "红包已经领取");
					}else {
						
						Map<Object, Object> recordMap = new HashMap<Object, Object>();
						String currDateHour = DateUtil.format(new Date(), propertiesUtil.getValue("hourSimpleDateFormat", "conf_common.properties"));
//						String currDateHour = DateUtil.format(new Date(), DateUtil.daySimpleFormater);
						
						recordMap.put("currDate", currDateHour);
						recordMap.put("uid", uid);
						LiveLedgerRecord record = liveLedgerRecordDao.queryLiveLedgerRecordByUid(recordMap);
						if (record!=null) {
							result.put("isLogged", 1);//一登录 并且 有可领取红包的操作
							result.put("isReceive", 1);//显示领取页面
							result.put("id", record.getId());
							response = new MapResponse(ResponseCode.SUCCESS, "红包查询成功");
						}else {
							result.put("isLogged", 1); //一登录 未查找到红包的情况下  显示立即行动弹窗
							result.put("isReceive", 2);//显示立即行动
							response = new MapResponse(ResponseCode.SUCCESS, "未找到红包记录");
						}
						
					}
				}
				
			}else {
				result.put("isLogged", 0);
				response = new MapResponse(ResponseCode.SUCCESS, "未到红包领取时间");
			}
			
		} catch (Exception e) {
			log.info("查询每日红包出错");
			e.printStackTrace();
			response = new MapResponse(ResponseCode.FAILURE, "系统异常");
		}
		response.setResponse(result);
		return response;
	}
	
	
	/**
	 * 每天获取用户是否可领平台分账红包   仅限推荐用户
	 * @param BaseRequest request
	 * @return object
	 * */
	public Object liveRedPacketBranchGot(LiveLedgerRecordRequest request){
		MapResponse response = null;
		String uid = sessionTokenService.getStringForValue(request.getSessiontoken()) + "";
		if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
//		String uid = request.getUid().toString();
		try {
			LiveLedgerRecord record = liveLedgerRecordDao.queryLiveLedgerRecordByRedpacketId(request.getRedId());
			if (record!=null) {
				
				//调接口存入钱包   完毕后返回领取数据
				Map<String, String> receiveMap = new HashMap<String, String>();
				receiveMap.put("uid", uid);
				receiveMap.put("recordId", request.getRedId().toString());
//				receiveMap.put("redpacketAmount", record.getLedgerAmount().toString());
				
				TMultiplexedProtocol tMultiplexedProtocol = thriftBusinessUtil.getProtocol("LiveOrderService");
				LiveOrderService.Client  liveClient= new LiveOrderService.Client(tMultiplexedProtocol); 
				thriftBusinessUtil.openTransport();
				ResponseData responseData = liveClient.receiveDailyRedpacket(receiveMap);
				if (responseData.getState()==0) {
					Map<Object, Object> result = new HashMap<Object, Object>();
					result.put("id", record.getId());
					result.put("uid", record.getUid());
					result.put("realLedgerAmount", responseData.getResultMap().get("redpacketAmount"));
					result.put("redpacketCoin", responseData.getResultMap().get("redpacketCoin"));
					result.put("isVirtual", responseData.getResultMap().get("isVirtual"));
					int isVirtual = 0;
					if (responseData.getResultMap().get("isVirtual")!=null) {
						isVirtual = Integer.parseInt(responseData.getResultMap().get("isVirtual").toString()) ;
					}
					//首页领红包的到账描述  0 是到账的 没有文字描述
					result.put("redpacketImg", "");//无文字图
					if (isVirtual == 0) {
						result.put("redpacketImg", propertiesUtil.getValue("liveLedgerRedpacketImg", "conf_common.properties"));//有文字图
					}
					response = new MapResponse(ResponseCode.SUCCESS, "红包查询成功");
					response.setResponse(result);
				}else {
					response = new MapResponse(ResponseCode.FAILURE, "领取红包异常");
				}
				
			}else {
				response = new MapResponse(ResponseCode.FAILURE, "操作异常,请重试");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			response = new MapResponse(ResponseCode.FAILURE, "系统异常");
		}
		return response;
	}


	public Object queryLiveLedgerRecordOrderList() {
		try {
			List<LiveLedgerRecord> list = liveLedgerRecordDao.queryLiveLedgerRecordOrderList();
			List<Map<String,String>> result = new ArrayList<>();
			if(list!=null&&list.size()>0){
				for(LiveLedgerRecord liveLedgerRecord:list){
					Map<String,String> map = new HashMap<>();
					//查询用户昵称
					Urs urs = ursDao.queryUrsByUid(Integer.parseInt(liveLedgerRecord.getUid()+""));
					String name="";
					if(urs!=null&&urs.getNname()!=null&&urs.getNname().length()>0){
						name = urs.getNname();
					}else{
						name = urs.getUname().substring(0,3)+"****"+urs.getUname().substring(7);
					}
				
					if(liveLedgerRecord.getLedgerSource()==1){
						map.put("title", name+"打赏收获"+liveLedgerRecord.getLedgerAmount()+"鸟币");
					}else{
						if(liveLedgerRecord.getLedgerSource()==2){
							map.put("title", name+"收获一枚壕友，获得壕友充值奖励"+liveLedgerRecord.getLedgerAmount()+"鸟币");
						}else{
							map.put("title", name+"天降壕礼获得"+liveLedgerRecord.getLedgerAmount()+"鸟币");
						}
					}
					map.put("sdate",new SimpleDateFormat("yyyy-MM-dd").format(liveLedgerRecord.getCreateDate()));
					result.add(map);
				}
				
			}
			ObjResponse objResponse = new ObjResponse(ResponseCode.SUCCESS,"成功");
			objResponse.setResponse(result);
			return objResponse;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BaseResponse(ResponseCode.FAILURE,"获取壕赚广播信息失败！");
	}
	
	

}
