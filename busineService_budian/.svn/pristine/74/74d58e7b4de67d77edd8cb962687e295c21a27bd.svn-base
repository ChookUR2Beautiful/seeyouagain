package com.xmniao.service.message.push;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.dao.order.OrderServiceDao;
import com.xmniao.domain.push.PushMsgRequest;
import com.xmniao.domain.push.PushSysMsgBean;
import com.xmniao.domain.push.PushtSysUserMsgBean;
import com.xmniao.service.common.CommonServiceImpl;

/**
 * 推送消息业务逻辑处理实现类
 * @author  LiBingBing
 * @version  [版本号, 2015年5月8日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class PushMessageImpl implements PushMessage
{
    /**
     * 日志记录
     */
    private final Logger log = Logger.getLogger(PushMessageImpl.class);
    
    /**
     * 推送消息redis队列KEY
     */
    private String pushMsgQueue;
    
    /**
     * 注入消息推送订单支付标题
     */
    private String pmgordertitle;
    
    /**
     * 注入消息推送分账成功标题
     */
    private String pmgledgertitle;
    
    /**
     * 注入消息推送商户订单支付内容
     */
    private String pmgsordercontent;
    
    /**
     * 注入消息推送用户消费订单支付内容
     */
    private String pmguordercontent;
    
    /**
     * 注入消息推送用户返利到账内容
     */
    private String pmgluordercontent;
    
    /**
     * 注入redis处理
     */
    @Autowired
    private StringRedisTemplate redisTemplate;
    
    /**
     * 注入调用PHP的推送消息服务
     */
    private CommonServiceImpl commonServiceImpl;
    
    /**
     * 注入订单DAO层
     */
    @Autowired
    private OrderServiceDao orderDao;
    
    @Override
    public void handleMessage(String reqJson)
    {
        if (StringUtils.isNotBlank(reqJson))
        {
            log.info("pushMsg handleMessage start:" + reqJson);
            
            PushMsgRequest reqjson = new PushMsgRequest();
            
            String resultPhp = null;
            try
            {
                JSONObject redisJson = JSONObject.parseObject(reqJson);
                //订单支付成功
                if (redisJson.getString("type").equals("1"))
                {
                    //商户版用户版类型区分,1为商户版,2为用户版
                    int sellerUserType=1;
                    
                    //商户版推送消息处理方法
                    reqjson = sellerPushMsgProcess(sellerUserType,redisJson);
                    
                    //调用PHP的推送消息服务
                    resultPhp = commonServiceImpl.pushMessage(reqjson);
                    
                    if(StringUtils.isNotBlank(resultPhp))
                    {
                        JSONObject reObj = JSONObject.parseObject(resultPhp);
                        //若调用失败,则再调用一次
                        if(reObj.getBoolean("status")==false)
                        {
                            resultPhp = commonServiceImpl.pushMessage(reqjson);
                        }
                    }else
                    {
                        //若调用失败,则再调用一次
                        resultPhp = commonServiceImpl.pushMessage(reqjson);
                    }
                    
                    //商户版插入系统信息发布表
                    int sid=addSysMsgInfo(reqjson);
                    
                    //商户版插入用户系统信息发布表
                    addSysUserMsgInfo(sid,redisJson,reqjson.getTdate(),sellerUserType);
                    
                    //若为用户版
                    sellerUserType=2;
                    
                    //用户版推送消息处理方法
                    reqjson = this.userPushMsgProcess(sellerUserType,redisJson);
                    
                    //调用PHP的推送消息服务
                    resultPhp = commonServiceImpl.pushMessage(reqjson);
                    
                    if(StringUtils.isNotBlank(resultPhp))
                    {
                        JSONObject reObj = JSONObject.parseObject(resultPhp);
                        //若调用失败,则再调用一次
                        if(reObj.getBoolean("status")==false)
                        {
                            resultPhp = commonServiceImpl.pushMessage(reqjson);
                        }
                    }else
                    {
                        //若调用失败,则再调用一次
                        resultPhp = commonServiceImpl.pushMessage(reqjson);
                    }
                    
                    //用户版插入系统信息发布表
                    sid=addSysMsgInfo(reqjson);
                    
                    //用户版插入用户系统信息发布表
                    addSysUserMsgInfo(sid,redisJson,reqjson.getTdate(),sellerUserType);
                }
                
                //分账成功,返利到账
                if (redisJson.getString("type").equals("2"))
                {
                    //商户版用户版类型区分,1为商户版,2为用户版
                    int sellerUserType=2;
                    
                    //用户版推送消息处理方法
                    reqjson = this.userPushMsgProcess(sellerUserType,redisJson);
                    //调用PHP的推送消息服务
                    resultPhp=commonServiceImpl.pushMessage(reqjson);
                    if(StringUtils.isNotBlank(resultPhp))
                    {
                        JSONObject reObj = JSONObject.parseObject(resultPhp);
                        //若调用失败,则再调用一次
                        if(reObj.getBoolean("status")==false)
                        {
                            resultPhp = commonServiceImpl.pushMessage(reqjson);
                        }
                    }else
                    {
                        resultPhp = commonServiceImpl.pushMessage(reqjson);
                    }
                    
                    //用户版插入系统信息发布表
                    int sid=addSysMsgInfo(reqjson);
                    
                    //用户版插入用户系统信息发布表
                    addSysUserMsgInfo(sid,redisJson,reqjson.getTdate(),sellerUserType);
                }
            }
            catch (Exception e)
            {
                log.error("pushMsg handleMessage exception:", e);
                e.printStackTrace();
            }
        }
        log.info("pushMsg handleMessage end");
    }
    
    /**
     * 商户版消息推送处理方法
     * @param reqJson [取出来的JSON数据]
     * @return String [返回组装好的JSON数据]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    private PushMsgRequest sellerPushMsgProcess(int sellerUserType,JSONObject reqJson)
    {
        log.info("sellerPushMsgProcess start");
        PushMsgRequest request = new PushMsgRequest();
        List<Map<String, String>> reList = new ArrayList<Map<String, String>>();
        
        //组装uid的JSON数据
        Map<String, String> reqMap = new HashMap<String, String>();
        //查询商户账户信息
        Map<String,String> reSellerAccMap = orderDao.querySellerAccountInfos(reqJson.getString("sellerid"));
        if(null!=reSellerAccMap && StringUtils.isNotBlank(String.valueOf(reSellerAccMap.get("aid"))))
        {
            reqMap.put("uid", String.valueOf(reSellerAccMap.get("aid")));
        }else
        {
            reqMap.put("uid", "");
        }
        reqMap.put("iostoken", "");
        reList.add(reqMap);
        
        //组装安卓的action的JSON数据
        Map<String, String> actionMap = new HashMap<String, String>();
        actionMap.put("activity", "6");
        
        //组装IOS的action的JSON数据
        Map<String, String> iosAction = new HashMap<String, String>();
        iosAction.put("action", "");
        iosAction.put("alert", pmgordertitle);
        iosAction.put("badge", "1");
        iosAction.put("sound", "default");
        iosAction.put("type", "101");
        iosAction.put("account", "1");
        
        //组装最外层消息推送的JSON数据
        request.setUid(JSONObject.toJSONString(reList));
        request.setUsertype("3");
        request.setIsAll("false");
        request.setTid("");
        request.setTitle(pmgordertitle);
        String reOrder = pmgsordercontent.replace("#{0}",reqJson.getString("bid"));
        String pmgSOrderContent = reOrder.replace("#{1}",reqJson.getString("money"));
        request.setContent(pmgSOrderContent);
        request.setType("3");
        request.setAction(JSONObject.toJSONString(actionMap));
        request.setIosaction(JSONObject.toJSONString(iosAction));
        request.setRemind("");
        request.setClient("2");
        request.setContenttype("");
        
       //调用消息发送时间和过期时间处理方法
        Map<String,String> reDateMap = this.msgTimeProcess(sellerUserType,reSellerAccMap,reqJson.getString("sdate"));
        if(null!=reDateMap)
        {
            //消息发送时间
            request.setTdate(reDateMap.get("fTime"));
            //消息过期时间
            request.setEdate(reDateMap.get("gTime"));
        }else
        {
            //消息发送时间
            request.setTdate("");
            //消息过期时间
            request.setEdate("");
        }
        
        String pushMsgJsonReq = JSONObject.toJSONString(request);
        log.info("==sellerPushMsg JSON::" + pushMsgJsonReq);
        log.info("sellerPushMsgProcess end");
        return request;
    }
    
    /**
     * 用户版消息推送处理方法
     * @param reqJson [取出来的JSON数据]
     * @return String [返回组装好的JSON数据]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    private PushMsgRequest userPushMsgProcess(int sellerUserType,JSONObject reqJson)
    {
        log.info("userPushMsgProcess start");
        PushMsgRequest request = new PushMsgRequest();
        List<Map<String, String>> reList = new ArrayList<Map<String, String>>();
        
        //组装uid的JSON数据
        Map<String, String> reqMap = new HashMap<String, String>();
        reqMap.put("uid", reqJson.getString("uid"));
        reqMap.put("iostoken", "0");
        reList.add(reqMap);
        
        //组装安卓的action的JSON数据
        Map<String, String> actionMap = new HashMap<String, String>();
        actionMap.put("activity", "3");
        actionMap.put("order", reqJson.getString("bid"));
        
        //组装IOS的action的JSON数据
        Map<String, String> iosAction = new HashMap<String, String>();
        iosAction.put("action", "");
        iosAction.put("badge", "1");
        iosAction.put("sound", "default");
        
        //组装最外层消息推送的JSON数据
        request.setUid(JSONObject.toJSONString(reList));
        request.setUsertype("1");
        request.setIsAll("false");
        request.setTid("");
        
        //若为1(订单支付),则组装订单支付相应的标题和消息内容
        if (reqJson.getString("type").equals("1"))
        {
            iosAction.put("alert", pmgordertitle);
            iosAction.put("type", "3");
            iosAction.put("order", reqJson.getString("bid"));
            request.setTitle(pmgordertitle);
            
            String reuOrder = pmguordercontent.replace("#{0}",reqJson.getString("sellername"));
            String rePmgUorderContent = reuOrder.replace("#{1}",reqJson.getString("money"));
            request.setContent(rePmgUorderContent);
        }
        //若为2(分账成功),则组装返利到账相应的标题和消息内容
        if (reqJson.getString("type").equals("2"))
        {
            iosAction.put("alert", pmgledgertitle);
            iosAction.put("type", "4");
            request.setTitle(pmgledgertitle);
            
            String reLedger = pmgluordercontent.replace("#{0}",reqJson.getString("bid"));
            double rebateMoney = reqJson.getDouble("rebate");
            double flatMoney = reqJson.getDouble("flat_money");
            //返利金额=本单可返利金额+平台补贴金额
            double orderRebate = rebateMoney+flatMoney;
            //保留两位小数
            DecimalFormat df = new DecimalFormat("######0.00"); 
            String pmgLuUrderContent = reLedger.replace("#{1}",df.format(orderRebate));
            request.setContent(pmgLuUrderContent);
        }
        request.setType("3");
        request.setAction(JSONObject.toJSONString(actionMap));
        request.setIosaction(JSONObject.toJSONString(iosAction));
        request.setRemind("");
        request.setClient("1");
        request.setContenttype("");
        
        //调用消息发送时间和过期时间处理方法
        Map<String,String> reDateMap = this.msgTimeProcess(sellerUserType,null,reqJson.getString("sdate"));
        if(null!=reDateMap)
        {
            //消息发送时间
            request.setTdate(reDateMap.get("fTime"));
            //消息过期时间
            request.setEdate(reDateMap.get("gTime"));
        }else
        {
            //消息发送时间
            request.setTdate("");
            //消息过期时间
            request.setEdate("");
        }
        
        String userPushMspJson = JSONObject.toJSONString(request);
        log.info("==userPushMsg JSON::" + userPushMspJson);
        log.info("userPushMsgProcess end");
        return request;
    }
    
    /**
     * 消息发送时间和过期时间处理
     * @param zdate [订单时间]
     * @return Map<String,String> [返回处理后的消息发送时间和过期时间]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    private Map<String, String> msgTimeProcess(int sellerUserType,Map<String, String> reSellerAccMap,String zdate)
    {
        log.info("msgTimeProcess start");
        Map<String, String> reDateMap = new HashMap<String, String>();
        try
        {
            //若是商户版
            if(sellerUserType==1)
            {
                //若为商户版且商户设置免打扰模式开启
                if(null!=reSellerAccMap && Integer.valueOf(String.valueOf(reSellerAccMap.get("newstatus")))==1)
                {
                    //开启的免打扰时间段
                    String nonewstime=reSellerAccMap.get("nonewstime");
                    
                    SimpleDateFormat sdmft = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    String sysNowTime=sdmft.format(new Date());
                    
                    String nowTimeStr=sysNowTime.substring(0, 10);
                    
                    //拼接免打扰时间段
                    String sTime=nonewstime.substring(0, nonewstime.indexOf("-"));
                    String eTime=nonewstime.substring(nonewstime.indexOf("-")+1,11);
                    
                    sTime=nowTimeStr+" "+sTime;
                    eTime=nowTimeStr+" "+eTime;
                    
                    //免打扰开始时间
                    Date dsTime=sdmft.parse(sTime);
                    //免打扰结束时间
                    Date deTime=sdmft.parse(eTime);
                    
                    //判断免打扰时间段是否跳天
                    if(dsTime.getHours()>deTime.getHours())
                    {
                        //若跳天则免打扰的结束时间加上1天
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(deTime);
                        calendar.add(Calendar.DAY_OF_MONTH,1);;
                        deTime=calendar.getTime();
                    }
                    //若在免打扰时间段之内,则消息发送时间为免打扰的结束时间加上1分钟
                    if(sdmft.parse(sysNowTime).after(dsTime) && sdmft.parse(sysNowTime).before(deTime))
                    {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(deTime);
                        calendar.add(Calendar.MINUTE,1);
                        deTime=calendar.getTime();
                        
                        //消息发送时间
                        sdmft = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
                        reDateMap.put("fTime", sdmft.format(deTime));
                        
                        //消息过期时间=消息发送时间+30分钟
                        calendar.add(Calendar.MINUTE,30);
                        deTime=calendar.getTime();
                        reDateMap.put("gTime", sdmft.format(deTime));
                    }else
                    {
                        sdmft = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
                        
                        //消息发送时间
                        reDateMap.put("fTime", zdate);
                        
                        Calendar gCalendars = Calendar.getInstance();
                        gCalendars.setTime(sdmft.parse(zdate));
                        //消息过期时间在消息发送时间加上半小时
                        gCalendars.add(Calendar.MINUTE, 30);
                        Date gTime = gCalendars.getTime();
                        
                        //消息过期时间
                        reDateMap.put("gTime", sdmft.format(gTime));
                    }
                }else
                {
                    SimpleDateFormat sdmft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    
                    //消息发送时间
                    reDateMap.put("fTime", zdate);
                    
                    Calendar gCalendars = Calendar.getInstance();
                    gCalendars.setTime(sdmft.parse(zdate));
                    //消息过期时间在消息发送时间加上半小时
                    gCalendars.add(Calendar.MINUTE, 30);
                    Date gTime = gCalendars.getTime();
                    
                    //消息过期时间
                    reDateMap.put("gTime", sdmft.format(gTime));
                }
            }
            
            //若是用户版
            if(sellerUserType==2)
            {
                SimpleDateFormat sdmft = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
                Date zdatefmt = sdmft.parse(zdate);
                
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(zdatefmt);
                
                calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
                calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
                calendar.set(Calendar.DATE, calendar.get(Calendar.DATE));
                calendar.set(Calendar.HOUR, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                //设定开始时间0点
                Date sTime = calendar.getTime();
                
                //设定结束时间8点
                calendar.set(Calendar.HOUR_OF_DAY, 8);
                Date eTime = calendar.getTime();
                
                //若订单时间在0点到8点之间,则消息发送时间定在8点钟 ;否则就为订单时间
                if (zdatefmt.after(sTime) && zdatefmt.before(eTime))
                {
                    //消息发送时间
                    reDateMap.put("fTime", sdmft.format(eTime));
                    
                    //消息过期时间在消息发送时间加上半小时
                    calendar.set(Calendar.MINUTE, 30);
                    Date gTime = calendar.getTime();
                    //消息过期时间
                    reDateMap.put("gTime", sdmft.format(gTime));
                }else
                {
                    //消息发送时间
                    reDateMap.put("fTime", zdate);
                    
                    Calendar gCalendars = Calendar.getInstance();
                    gCalendars.setTime(zdatefmt);
                    //消息过期时间在消息发送时间加上半小时
                    gCalendars.add(Calendar.MINUTE, 30);
                    Date gTime = gCalendars.getTime();
                    
                    //消息过期时间
                    reDateMap.put("gTime", sdmft.format(gTime));
                }
            }
        }
        catch (Exception e)
        {
            log.error("msgTimeProcess error:", e);
        }
        log.info("msgTimeProcess end");
        return reDateMap;
    }
    
    /**
     * 插入系统信息发布表
     * @param req 推送消息JSON数据
     * @return int [返回系统信息发布表主键SID]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    private int addSysMsgInfo(PushMsgRequest req)
    {
        PushSysMsgBean reqSysBean = new PushSysMsgBean();
        SimpleDateFormat sidTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //组装商户版插入系统信息发布表的数据
        reqSysBean.setSdate(sidTime.format(new Date()));
        reqSysBean.setTdate(req.getTdate());
        reqSysBean.setEdate(req.getEdate());
        reqSysBean.setStatus("1");
        reqSysBean.setContent(req.getContent());
        reqSysBean.setNumber("0");
        reqSysBean.setIspush("1");
        reqSysBean.setActiontype("2");
        reqSysBean.setType("2");
        //调用插入系统信息发布表
        orderDao.addPushSysMsg(reqSysBean);
        //返回主键SID字段
        int sid=reqSysBean.getSid();
        return sid;
    }
    
    /**
     * 插入用户系统信息发布表
     * @param sid [系统信息发布表中的主键SID]
     * @param redisJson [redis队列的JSON数据]
     * @param tdate [消息发送时间]
     * @param sellerUserType [商户版和用户版类型,1为商户版2为用户版]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    private void addSysUserMsgInfo(int sid,JSONObject redisJson,String tdate,int sellerUserType)
    {
        PushtSysUserMsgBean reqSysUserBean = new PushtSysUserMsgBean();
        
        //组装商户版插入用户系统信息记录表的数据
        reqSysUserBean.setSid(sid);
        reqSysUserBean.setIsshow("0");
        //若为商户版,则设置为商户ID和商户名称
        if(sellerUserType==1)
        {
            reqSysUserBean.setUid(redisJson.getString("sellerid"));
            reqSysUserBean.setNname(redisJson.getString("sellername"));
        }
        //若为用户版,则设置为用户ID和用户名称为空
        if(sellerUserType==2)
        {
            reqSysUserBean.setUid(redisJson.getString("uid"));
            reqSysUserBean.setNname("");
        }
        reqSysUserBean.setPhoneid(redisJson.getString("phoneid"));
        reqSysUserBean.setStatus("0");
        reqSysUserBean.setSdate(tdate);
        //插入用户系统信息记录表
        orderDao.addPushSysUserMsg(reqSysUserBean);
    }
    
    public String getPushMsgQueue()
    {
        return pushMsgQueue;
    }
    
    public void setPushMsgQueue(String pushMsgQueue)
    {
        this.pushMsgQueue = pushMsgQueue;
    }
    
    public String getPmgordertitle()
    {
        return pmgordertitle;
    }
    
    public void setPmgordertitle(String pmgordertitle)
    {
        this.pmgordertitle = pmgordertitle;
    }
    
    public String getPmgledgertitle()
    {
        return pmgledgertitle;
    }
    
    public void setPmgledgertitle(String pmgledgertitle)
    {
        this.pmgledgertitle = pmgledgertitle;
    }
    
    public String getPmgsordercontent()
    {
        return pmgsordercontent;
    }
    
    public void setPmgsordercontent(String pmgsordercontent)
    {
        this.pmgsordercontent = pmgsordercontent;
    }
    
    public String getPmguordercontent()
    {
        return pmguordercontent;
    }
    
    public void setPmguordercontent(String pmguordercontent)
    {
        this.pmguordercontent = pmguordercontent;
    }
    
    public String getPmgluordercontent()
    {
        return pmgluordercontent;
    }
    
    public void setPmgluordercontent(String pmgluordercontent)
    {
        this.pmgluordercontent = pmgluordercontent;
    }

    public CommonServiceImpl getCommonServiceImpl()
    {
        return commonServiceImpl;
    }

    public void setCommonServiceImpl(CommonServiceImpl commonServiceImpl)
    {
        this.commonServiceImpl = commonServiceImpl;
    }
}
