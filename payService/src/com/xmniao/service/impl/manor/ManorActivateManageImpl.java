package com.xmniao.service.impl.manor;

import com.xmniao.dao.manor.ManorActiveConfigMapper;
import com.xmniao.entity.manor.ActivateManorConfig;

import java.util.List;

/**
 * @author liyuanbo
 * @create 2017-06-01 10:24
 **/
public class ManorActivateManageImpl  {
    private ManorActiveConfigMapper manorActiveConfigMapper;

    public List<ActivateManorConfig> getActiveManorConfig() {
        return manorActiveConfigMapper.getActiveManorConfig();
    }
}
