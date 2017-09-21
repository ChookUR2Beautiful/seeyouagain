package com.xmniao.service.quartz;

import com.alibaba.fastjson.JSON;
import com.xmniao.common.DateParseUtils;
import com.xmniao.common.PropertiesUtil;
import com.xmniao.common.RedisService;
import com.xmniao.common.ThriftClientFactory;
import com.xmniao.domain.manor.ManorFlowerCount;
import com.xmniao.domain.manor.ManorInfo;
import com.xmniao.domain.manor.ManorLevel;
import com.xmniao.domain.manor.ManorLevelEarningRecord;
import com.xmniao.service.manor.ManorEarningService;
import com.xmniao.service.manor.ManorFlowerService;
import com.xmniao.service.manor.ManorInfoService;
import com.xmniao.service.manor.ManorLogService;
import com.xmniao.thrift.pay.ManorPropsThriftService;
import com.xmniao.thrift.pay.ResultList;
import com.xmniao.urs.dao.UrsEarningsRelationDao;
import com.xmniao.urs.dao.manor.ManorInfoMapper;
import com.xmniao.urs.dao.manor.ManorLevelMapper;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.xmniao.service.manor.ManorConstant.*;

/**
 * Created by yang.qiang on 2017/6/12.
 */
public class ManorQuatrzService {
    private final Logger logger = LoggerFactory.getLogger(ManorQuatrzService.class);

    @Autowired
    private ManorInfoService manorInfoService;
    @Autowired
    private ManorInfoMapper manorInfoMapper;
    @Autowired
    private ManorEarningService manorEarningService;
    @Autowired
    private ManorFlowerService manorFlowerService;
    @Autowired
    private ManorLogService manorLogService;
    @Autowired
    private ManorLevelMapper manorLevelMapper;
    @Autowired
    private UrsEarningsRelationDao ursEarningsRelationDao;
    @Autowired
    private RedisService redisService;


    /**
     * 注入支付服务IP地址
     */
    @Resource(name = "transLedgerIP")
    private String transLedgerIP;

    /**
     * 注入支付服务端口号
     */
    @Resource(name = "transLedgerPort")
    private int transLedgerPort;

    @Resource(name = "xmnServiceMessageUrl")
    private String xmnServiceMessageUrl;

    @Resource(name = "manorHomeUrl")
    private String manorHomeUrl;

    /**
     * 自动操作24小时未种植的花朵
     */
    public void autoPlantFlowers(){
        logger.info("启动定时任务, 自动种植超过24小时没有种植的花朵");

        // 调用支付服务查询查询指定时间没有种植花朵的用户

        ResultList resultList = null;
        try (ThriftClientFactory.ThriftClient<ManorPropsThriftService.Client> manorPropsThriftServiceClient = ThriftClientFactory.getManorPropsThriftServiceClient(transLedgerIP, transLedgerPort)) {
            ManorPropsThriftService.Client thriftClient = manorPropsThriftServiceClient.getThriftClient();
            resultList = thriftClient.getAllNotFinishGrowUserByHours(AUTO_PLANT_HOURS);
            logger.info("支付服务调用结果" + JSON.toJSON(resultList));
        } catch (Exception e) {
            logger.error("调用支付服务失败!",e);
        }

        if (resultList != null) {
            for (Map<String, String> uidMap : resultList.getValues()) {
                Integer uid = null;
                try {
                    uid = Integer.valueOf(uidMap.get("uid"));
                    manorFlowerService.autoPlantFlowers(uid);
                } catch (Exception e) {
                    logger.error("用户["+uid+"]自动种花失败! ",e);
                }
            }
        }

    }

    /**
     * 扫描庄园, 更新庄园信息
     * 1. 重置庄园redis缓存数据
     * 2. 更新庄园等级
     * 3. 庄园升降级信息
     */
    public void scanManorAndUpdateManorInfo() throws Exception {
        long startTime = System.currentTimeMillis();
        logger.info("启动定时任务:更新庄园信息");

        // 扫描所有激活状态中的庄园
        List<ManorInfo> manorInfoList = manorInfoService.queryActivatedManors();
        logger.info("待更新庄园等级信息 manorInfoList.size:" + manorInfoList.size());

        // 遍历庄园
        for (ManorInfo manorInfo : manorInfoList) {
            try {
            manorInfoService.updateManorLevel(manorInfo);
            } catch (Exception e) {
                logger.error("更新庄园信息失败! manorInfo:" + JSON.toJSONString(manorInfo),e);
            }
        }

        logger.info("执行定时任务:更新庄园信息完成, 共耗时:"+(System.currentTimeMillis()-startTime));
    }


    /** 推送新园友信息 */
    public void pushNewFriend(){
        String value;
        do{
            value = redisService.lpop(MANOR_PUSH_NWE_FRIEND_LIST);
            if (StringUtils.isNotBlank(value)) {
                Integer uid = Integer.valueOf(value);
                String newFriends = redisService.hget(PREFIX_MANOR_FLOWER + uid, MANOR_REDIS_KEY_COUPONS_PUSH_NEWFRIEND);
                if (StringUtils.isNotBlank(newFriends)) {
                    // 调用用户端接口 推送消息
                    try {
                        HashMap<String, String> postData = new HashMap<>();

                        postData.put("pushUid",value);
                        postData.put("sendTime", DateParseUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
                        postData.put("title","黄金庄园");
                        postData.put("content","您有"+newFriends+"位新园友开启了黄金庄园，奖励的现金券已发放到了您的鸟人直播账户哟~可在“我的-卡券”中查看。");
                        Jsoup.connect("http://192.168.50.205:8080/xmnService/pushXingeMessage").data(postData).ignoreContentType(true).post();
                    } catch (Exception e) {
                        logger.error("调用xmnService推送消息失败!",e);
                    }

                    redisService.hdel(PREFIX_MANOR_FLOWER + uid,MANOR_REDIS_KEY_COUPONS_PUSH_NEWFRIEND);
                }

            }else {
                value = null;
            }
        } while (value != null);
    }

    /** 推送没有领取花蜜的用户 */
    public void pushMessageEarning(){
        logger.info("启动定时任务: 推送没有领取花蜜的用户");
        List<ManorLevelEarningRecord> recordList = manorEarningService.queryUnclaimed();

        for (ManorLevelEarningRecord record : recordList) {

            try {
                HashMap<String, String> postData = new HashMap<>();
                postData.put("pushUid",record.getUid().toString());
                postData.put("sendTime", DateParseUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
                postData.put("title","黄金庄园");
                postData.put("content","亲！今日有"+record.getNumber()+"滴花蜜还未领取哦~\n去采蜜>");
                postData.put("url",manorHomeUrl);
                Jsoup.connect(xmnServiceMessageUrl).data(postData).ignoreContentType(true).post();
            } catch (Exception e) {
                logger.error("调用xmnService推送消息失败!",e);
            }


        }


    }

    /**
     * 
     * 方法描述：庄园每日收益 <br/>
     * 创建人： ChenBo <br/>
     * 创建时间：2017年7月14日下午2:12:51 <br/>
     */
    public void manorDailyEarning(){
    	
        logger.info(com.xmniao.common.DateUtil.getCurrentTimeStr()+"启动定时任务:庄园每日收益");
        String repeat = PropertiesUtil.readValue("manor.earning.daily.repeat", "busine_sundry.properties");
        if(repeat==null){	//是否可一天多次发放收益
        	repeat="0";
        }

        if(repeat.equals("1")){
    	}else{
    		if(manorEarningService.hasEarningToday(new Date())){
    			logger.info("当天已有每日庄园收益奖励，退出本次奖励");
        		return;
    		}
    	}
        
        final int limit=500;
        int page=1;
        
        long count=0;
        long success=0;
        
        List<ManorInfo> manorInfoList = null;
        do{
        	
	        // 扫描所有激活状态中的庄园
	        manorInfoList = manorInfoService.queryActivatedManorsByPage(page, limit);
	        logger.info("待更新庄园等级信息 manorInfoList.size:" + manorInfoList.size());
	        count += manorInfoList.size();
	        // 遍历庄园
	        for (ManorInfo manorInfo : manorInfoList) {
	
	            try {
	                logger.info("更新庄园信息 manorInfo:"+JSON.toJSONString(manorInfo));
	
	                // 统计庄园实时花朵数
	                List<ManorFlowerCount> flowerCountList = manorFlowerService.countAliveFlower(manorInfo.getUid());
	
	                // 查询庄园等级
	                ManorLevel currentLevel = manorInfoService.getManorLevel(flowerCountList);
	                
	                // 庄园每日收益
                    manorEarningService.manorDailyNectarEarning(currentLevel,manorInfo.getUid(),manorInfo.getPhone(),
                    		com.xmniao.common.DateUtil.calendarDay(new Date(),-1));
                    success ++;
	            } catch (Exception e) {
	                logger.error("给【"+manorInfo.getUid()+"】发送庄园每日收益异常! manorInfo:" + JSON.toJSONString(manorInfo),e);
	            }
	        }
	        page++;
        }while(manorInfoList.size()==limit);
        logger.info("执行定时任务:发放庄园完成.总有效庄园数:"+count+",总执行成功数:"+success);
    }

}
