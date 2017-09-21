package com.xmniao.service.mike;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.common.ResponseState;
import com.xmniao.dao.mike.MikeServiceDao;
import com.xmniao.service.common.CommonServiceImpl;
import com.xmniao.thrift.busine.common.FailureException;
import com.xmniao.thrift.busine.mike.MikeService;

/**
 * 向蜜客服务接口实现类
 * @author  LiBingBing
 * @version  [版本号, 2014年11月25日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MikeServiceImpl implements MikeService.Iface
{
    /**
     * 日志记录
     */
    private final Logger log = Logger.getLogger(MikeServiceImpl.class);
    
    /**
     * 注入向蜜客服务模块DAO层
     */
    @Autowired
    private MikeServiceDao mikeDao;
    
    /**
     * 注入通用服务接口DAO层
     */
    private CommonServiceImpl commonServiceImpl;
    
    /**
     * 向蜜客缴费更新订单状态接口
     * @param paramMap [请求参数]
     * @return id [返回申请ID]
     * @throws FailureException [异常返回参数]
     * @throws TException [异常返回参数]
     */
    @Override
    public String modifyMikeInviteInfo(Map<String, String> paramMap)
            throws FailureException, TException
    {
        log.info("modifyMikeInviteInfo start:" + paramMap);
        try
        {
            int resMikeFlag = mikeDao.modifyMikeInviteInfo(paramMap);
            if (resMikeFlag == 1)
            {
                long startTime = System.currentTimeMillis();
                //此处调用用户中心服务的向蜜客更新接口
                String result = commonServiceImpl.modifyUserMikeTime(paramMap);
                long endTime = System.currentTimeMillis();
                log.info("调用向蜜客更新接口总共耗费:"+(endTime-startTime)+"ms");
                if (StringUtils.isNotEmpty(result))
                {
                    JSONObject resObject = JSONObject.parseObject(result);
                    
                    if (resObject.getBoolean("status") == false)
                    {
                        log.error(resObject.getString("msg"));
                        for (int i = 1; i <= 2; i++)
                        {
                            commonServiceImpl.modifyUserMikeTime(paramMap);
                        }
                    }
                }
            }
        }
        catch (Exception e)
        {
            log.error("向蜜客缴费订单更新异常", e);
            throw new FailureException(ResponseState.MIKEORDERFAIL,
                    "向蜜客缴费订单更新失败");
        }
        log.info("modifyMikeInviteInfo end......");
        return paramMap.get("id");
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
