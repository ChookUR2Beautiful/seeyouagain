package com.xmniao.service.message.fault;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import net.sf.ehcache.Cache;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 容错机制线程池
 * @author  LiBingBing
 * @version  [版本号, 2015年8月19日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class FaultTolerantPool implements Runnable
{
    /**
     * 日志记录
     */
    private final Logger log = Logger.getLogger(FaultTolerantPool.class);
    /**
     * 启动线程数
     */
    private int threadSize = 1;
    
    /**
     * 容错机制实现处理类
     */
    @Autowired
    private FaultTolerantImpl faultTolerantImpl;
    
    /**
     * 注入本地缓存
     */
    @Autowired
    private Cache dataCache;
    
    
    public FaultTolerantPool(int size)
    {
        if(size<1)
        {
            size = 1;
        }
        this.threadSize = size;
    }
    public FaultTolerantPool()
    {
        
    }
    
    @Override
    public void run()
    {
        if (this.threadSize < 1)
        {
            this.threadSize = 1;
        }
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(this.threadSize);
        log.info("send Ecache监听线程已启动......");
        fixedThreadPool.execute(new FaultTolerantWorkerTheads(faultTolerantImpl,dataCache));
    }
    
    public int getThreadSize()
    {
        return threadSize;
    }
    public void setThreadSize(int threadSize)
    {
        this.threadSize = threadSize;
    }
}
