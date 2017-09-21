package com.xmniao.service.message.fault;

import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 容错机制工作线程处理类
 * @author  LiBingBing
 * @version  [版本号, 2015年8月19日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class FaultTolerantWorkerTheads implements Runnable
{
    /**
     * 日志记录
     */
    private final Logger log = Logger.getLogger(FaultTolerantWorkerTheads.class);
    
    /**
     * ehcache缓存存储key
     */
    private static final String FAULT_KEY ="faultKey";
    
    /**
     * 注入容错机制实现处理类
     */
    @Autowired
    private FaultTolerantImpl faultTolerantImpl;
    
    /**
     * 注入本地缓存
     */
    @Autowired
    private Cache dataCache;
    
    public FaultTolerantWorkerTheads()
    {
        
    }

    public FaultTolerantWorkerTheads(FaultTolerantImpl faultTolerantImpl, Cache dataCache)
    {
        this.faultTolerantImpl = faultTolerantImpl;
        this.dataCache = dataCache;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void run()
    {
        log.info("send Ecache工作线程"+Thread.currentThread().getName()+"已启动......");
        while(true)
        {
            try
            {               
            	Thread.sleep(1000);
//            	Element cacheCode = dataCache.get(FAULT_KEY);
//                if(null!=cacheCode)
//                {
//                    //容错机制业务逻辑处理方法
//                    faultTolerantImpl.faultTolerantProcess(FAULT_KEY);
//                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            } 
        }
    }
}
