package com.xmniao.service;

import com.xmniao.entity.manor.ActivateManorConfig;

/**
 * 激活/续租庄园配置管理
 * @author liyuanbo
 * @create 2017-06-01 10:16
 **/
public interface ManorActivateManage {
    /**
     * 根据TYPE获取配置的数量
     * @param type
     * @return
     */
     ActivateManorConfig getActiveManorConfig(int type);
}
