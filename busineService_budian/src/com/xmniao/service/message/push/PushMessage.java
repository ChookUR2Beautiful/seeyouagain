package com.xmniao.service.message.push;

/**
 * 推送消息业务处理接口类
 * @author  LiBingBing
 * @version  [版本号, 2015年5月8日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface PushMessage
{
    public void handleMessage(String reqJson);
}
