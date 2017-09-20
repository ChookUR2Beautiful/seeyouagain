package com.xmniao.dao.manor;

import com.xmniao.entity.manor.ActivateManorConfig;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liyuanbo
 * @create 2017-06-01 10:25
 **/
@Repository
public interface ManorActiveConfigMapper {
    /**
     * 根据类型获取配置
     * @return
     */
    List<ActivateManorConfig> getActiveManorConfig();
}
