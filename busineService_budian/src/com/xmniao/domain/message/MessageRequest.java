package com.xmniao.domain.message;

import java.io.Serializable;

/**
 * 通用接口服务发送短信请求参数类
 * @author  LiBingBing
 * @version  [版本号, 2014年11月24日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MessageRequest implements Serializable
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 8633245719971192279L;
    
    /**
     * 手机号码
     */
    private String mobileId;
    
    /**
     * 消息内容
     */
    private String content;
    
    public String getMobileId()
    {
        return mobileId;
    }
    
    public void setMobileId(String mobileId)
    {
        this.mobileId = mobileId;
    }
    
    public String getContent()
    {
        return content;
    }
    
    public void setContent(String content)
    {
        this.content = content;
    }
}
