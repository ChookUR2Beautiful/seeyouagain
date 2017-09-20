package com.xmniao.test;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 服务端main方法启动类
 * @author  LiBingBing
 * @version  [版本号, 2014-11-7]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ThriftServer
{
    public static void main(String[] args) throws IOException
    {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"pay-base.xml"}, true);
        context.start();
        
        System.in.read(); // 按任意键退出
    }
}