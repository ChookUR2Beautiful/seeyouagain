package com.xmniao.main;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.io.IOException;

/**
 * 服务端main方法启动类
 * @author  LiBingBing
 * @version  [版本号, 2014-11-7]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ThriftServer
{
    /**
     * 日志记录
     */
    private final static Logger log = Logger.getLogger(ThriftServer.class);
    
    public static void main(String[] args) throws IOException
    {
        try
        {
            FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext("conf/busine-base.xml");
//            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("conf/busine-base.xml");
            context.start();
        }
        catch (Exception e)
        {
            log.error("加载配置文件异常......",e);
        }
        System.in.read(); // 按任意键退出
    }
}