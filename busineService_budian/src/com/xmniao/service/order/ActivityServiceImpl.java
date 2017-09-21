package com.xmniao.service.order;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.common.ResponseState;
import com.xmniao.dao.order.ActivityServiceDao;
import com.xmniao.thrift.busine.activity.ActivityService;
import com.xmniao.thrift.busine.common.FailureException;

/**
 * 活动服务模块实现类
 * @author  LiBingBing
 * @version  [版本号, 2015年1月16日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("activityServiceImpl")
public class ActivityServiceImpl implements ActivityService.Iface
{
    /**
     * 日志记录
     */
    private final Logger log = Logger.getLogger(ActivityServiceImpl.class);
    
    /**
     * 注入活动服务模块DAO层
     */
    @Autowired
    private ActivityServiceDao activityDao;
    
    /**
     * 活动订单更新接口
     * @param paraMap [请求参数]
     * @return Map<String, String> [返回参数]
     * @throws FailureException [异常返回参数]
     * @throws TException [异常返回参数]
     */
    @Override
    public Map<String, String> mdyMobileActivitiesInfos(
            Map<String, String> paraMap) throws FailureException, TException
    {
        log.info("mdyMobileActivitiesInfos start......");
        log.info("mdyMobileActivitiesInfos paraMap::" + paraMap);
        Map<String, String> resMap = new HashMap<String, String>();
        resMap.put("bid", paraMap.get("bid"));
        try
        {
            int activityType = Integer.valueOf(paraMap.get("activityType"));
            //判断请求参数非空,并判断活动类型是否为1(即为IPhone活动)
            if (null != paraMap &&  activityType== 1)
            {
                //根据订单号获取此条订单信息
                Map<String, Object> resBillAllMap = activityDao.queryMobileBillAll(paraMap.get("bid"));
                log.info("resBillAllMap::"+resBillAllMap);
                //判断是否有该条订单信息
                if (null == resBillAllMap)
                {
                    resMap.put("resCode", "107");
                    log.error("没有获取到该订单信息");
                }
                //判断该订单信息已经更新为已支付,则无需再进行更新
                else if (Integer.valueOf(resBillAllMap.get("pstatus").toString())==1)
                {
                    resMap.put("resCode", "108");
                    log.error("该订单已更新");
                }
                //以上条件都不满足则进行更新该条订单信息
                else
                {
                    SimpleDateFormat ptimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    paraMap.put("ptime", ptimeFormat.format(new Date()));
                    //调用活动订单更新接口
                    int resFlag = activityDao.mdyMobileActivitiesInfos(paraMap);
                    if (resFlag == 1)
                    {
                        resMap.put("resCode", "0");
                    }
                    else
                    {
                        resMap.put("resCode", "1");
                        log.error("该订单更新失败");
                    }
                }
            }
        }
        catch (Exception ex)
        {
            log.error("活动订单更新异常", ex);
            throw new FailureException(ResponseState.ORDERFAIL, "活动订单更新出错");
        }
        log.info("mdyMobileActivitiesInfos end::" + resMap);
        return resMap;
    }
}
