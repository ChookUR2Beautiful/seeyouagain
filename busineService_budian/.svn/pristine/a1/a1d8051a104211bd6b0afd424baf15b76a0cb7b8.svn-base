package com.xmniao.service.store;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.common.ResponseState;
import com.xmniao.dao.store.StoreServiceDao;
import com.xmniao.thrift.busine.common.FailureException;
import com.xmniao.thrift.busine.store.StoreService;


/**
 * 连锁店服务接口模块实现类
 * @author  liBingBing
 * @version  [版本号, 2015年5月7日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("storeServiceImpl")
public class StoreServiceImpl implements StoreService.Iface
{
    /**
     * 日志记录
     */
    private final Logger log = Logger.getLogger(StoreServiceImpl.class);

    /**
     * 连锁店服务DAO层
     */
    @Autowired
    private StoreServiceDao storeServiceDao;
    
    
    /**
     * 连锁店提现回调更新接口
     * @param paramMap[请求参数]
     * @return Map<String, String> [返回参数]
     * @throws FailureException
     * @throws TException
     */
    @Override
    public Map<String, String> modifyStoreWithdrawals(List<Map<String, String>> paramMap) 
            throws FailureException,TException
    {
        long sdate = System.currentTimeMillis();
        log.info("modifyStoreWithdrawals start:"+paramMap);   
        Map<String, String> resMap = new HashMap<String,String>();
        try
        {
            for(Map<String, String> reMap : paramMap)
            {
                String flowstatus = reMap.get("flowstatus");
                //重新赋值操作状态
                switch(flowstatus)
                {
                    //打款成功
                    case "1":
                        flowstatus="4";
                        break;
                    //打款失败
                    case "2":
                        flowstatus="5";
                        break;
                    //打款处理中
                    case "3":
                        flowstatus="3";
                        break;
                    //提现退回
                    case "4":
                        flowstatus="6";
                        break;
                    case "5":
                        flowstatus="7";
                        break;
                }
                
                //根据提现编号查询操作类别为提现的资金并拢信息
                Map<String,Object> reWithdrawalMap = storeServiceDao.queryStoreWithdrawalInfo(reMap.get("flowid").toString());
                log.info("reWithdrawalMap info::"+reWithdrawalMap);
                if(null==reWithdrawalMap)
                {
                    resMap.put("flowid", reMap.get("flowid").toString());
                    resMap.put("resCode",String.valueOf(ResponseState.PAYFAIL));
                    resMap.put("resMsg", "没有查询到此【"+reMap.get("flowid")+"】提现记录");
                    log.error("没有查询到此【"+reMap.get("flowid")+"】提现记录");
                    return resMap;
                }
                String withdrawalStatus=reWithdrawalMap.get("status")+"";
                if(StringUtils.isNotBlank(withdrawalStatus) && flowstatus.equals(withdrawalStatus))
                {
                    resMap.put("flowid", reMap.get("flowid").toString());
                    resMap.put("resCode",String.valueOf(ResponseState.PAYREADY));
                    resMap.put("resMsg", "此【"+reMap.get("flowid")+"】已更新了操作状态,无需再次更新");
                    log.error("此【"+reMap.get("flowid")+"】已更新操作状态,无需再次更新");
                    return resMap;
                }
                
                //提现回调更新业务处理方法
                resMap=storeWithdrawalsProcess(flowstatus,reWithdrawalMap);
            }
        }
        catch (Exception e)
        {
            log.error("modifyStoreWithdrawals error::",e);
            e.printStackTrace();
        }
        log.info("modifyStoreWithdrawals end");
        long edate = System.currentTimeMillis();
        log.info("连锁店提现回调更新接口总耗时::"+(edate-sdate)+"ms");
        return resMap;
    }
    
    /**
     * 提现回调更新业务处理方法
     * @param paraMap [分账的请求参数]
     * @param reWithdrawalMap [资金并拢信息]
     * @return Map<String,String> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    private Map<String,String> storeWithdrawalsProcess(String flowstatus,Map<String,Object> reWithdrawalMap)
    {
        Map<String,String> resMap = new HashMap<String,String>();
        log.info("storeWithdrawalsProcess start::"+reWithdrawalMap);
        try
        {
            if(null!=reWithdrawalMap)
            {
                Map<String,String> mdyMap = new HashMap<String,String>();
                SimpleDateFormat sucdateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                mdyMap.put("sucdate", sucdateFormat.format(new Date()));
                //得到分账传递的提现状态
                switch(flowstatus)
                {
                    //若为4打款成功,则提现操作状态更新为4打款成功
                    case "4":
                        mdyMap.put("status", "4");
                        mdyMap.put("remarks", "打款成功");
                        mdyMap.put("flowid", reWithdrawalMap.get("flowid").toString());
                        storeServiceDao.modifyStoreWithdrawals(mdyMap);
                        resMap.put("resCode", "100");
                        resMap.put("resMsg", "打款成功");
                        resMap.put("flowid", reWithdrawalMap.get("flowid").toString());
                        break;
                    //若为5打款失败,则提现操作状态更新为5打款失败
                    case "5":
                        mdyMap.put("status", "5");
                        mdyMap.put("remarks", "打款失败");
                        mdyMap.put("flowid", reWithdrawalMap.get("flowid").toString());
                        storeServiceDao.modifyStoreWithdrawals(mdyMap);
                        resMap.put("resCode", "109");
                        resMap.put("resMsg", "打款失败");
                        resMap.put("flowid", reWithdrawalMap.get("flowid").toString());
                        break;
                    //若为3提现处理中,则提现操作状态更新为3提现处理中
                    case "3":
                        mdyMap.put("status", "3");
                        mdyMap.put("remarks", "提现处理中");
                        mdyMap.put("flowid", reWithdrawalMap.get("flowid").toString());
                        storeServiceDao.modifyStoreWithdrawals(mdyMap);
                        resMap.put("resCode", "109");
                        resMap.put("resMsg", "提现处理中");
                        resMap.put("flowid", reWithdrawalMap.get("flowid").toString());
                        break;
                    //若为6提现退回,则提现操作状态更新为6提现退回(写回钱包)     
                    case "6":
                        mdyMap.put("status", "6");
                        mdyMap.put("remarks", "提现退回(写回钱包)");
                        mdyMap.put("flowid", reWithdrawalMap.get("flowid").toString());
                        storeServiceDao.modifyStoreWithdrawals(mdyMap);
                        resMap.put("resCode", "109");
                        resMap.put("resMsg", "提现退回(写回钱包)");
                        resMap.put("flowid", reWithdrawalMap.get("flowid").toString());
                        break;
                    case "7":
                        mdyMap.put("status", "7");
                        mdyMap.put("remarks", "取消提现(写回钱包)");
                        mdyMap.put("flowid", reWithdrawalMap.get("flowid").toString());
                        storeServiceDao.modifyStoreWithdrawals(mdyMap);
                        resMap.put("resCode", "109");
                        resMap.put("resMsg", "取消提现(写回钱包)");
                        resMap.put("flowid", reWithdrawalMap.get("flowid").toString());
                        break;     
                }
            }
        }
        catch (Exception e)
        {
            log.error("storeWithdrawalsProcess error::",e);
            e.printStackTrace();
        }
        log.info("storeWithdrawalsProcess end");
        return resMap;
    }
}
