package com.xmniao.service.message.useraction;

/**
 * 
 * 
 * 项目名称：busineService
 * 
 * 类名称：UserActionImpl
 * 
 * 类描述： 更新用户浏览、消费的数据统计
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年12月10日 下午3:27:19 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface UserAction
{
    public void handleMessage(String reqJson);
}
