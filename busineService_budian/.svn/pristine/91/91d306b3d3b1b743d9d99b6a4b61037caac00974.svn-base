package com.xmniao.proxy;

import java.io.Serializable;

/**
 * 服务端代理类
 * 用来管理多个服务端的接口类和接口实现类
 * @author  LiBingBing
 * @version  [版本号, 2014-11-6]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ThriftServiceProxy implements Serializable
{
    private static final long serialVersionUID = -4013517461382472034L;
    
    //服务端的接口类名字
    private String serviceName;
    
    // 服务端接口类
    private String serviceInterface;
    
    // 服务端接口实现类
    private Object serviceImpl;
    
    public String getServiceInterface()
    {
        return serviceInterface;
    }
    
    public void setServiceInterface(String serviceInterface)
    {
        this.serviceInterface = serviceInterface;
    }
    
    public Object getServiceImpl()
    {
        return serviceImpl;
    }
    
    public void setServiceImpl(Object serviceImpl)
    {
        this.serviceImpl = serviceImpl;
    }
    
    public String getServiceName()
    {
        return serviceName;
    }
    
    public void setServiceName(String serviceName)
    {
        this.serviceName = serviceName;
    }
}