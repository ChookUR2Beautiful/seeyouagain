package com.xmniao.xmn.core.market.service.common.impl;

import com.xmniao.xmn.core.market.dao.FreshLabelDao;
import com.xmniao.xmn.core.market.entity.FreshLabel;
import com.xmniao.xmn.core.market.entity.activity.indiana.FreshActivityIndiana;
import com.xmniao.xmn.core.market.service.common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yang.qiang on 2017/3/7.
 */
@Service
public class CommonServiceImpl implements CommonService {
    @Autowired
    private FreshLabelDao freshLabelDao;
    @Autowired
    private String fileUrl;

    /**
     * 查询标签列表
     * @return
     */
    @Override
    public List<FreshLabel> queryLabels() {
        List<FreshLabel> freshLabels = freshLabelDao.selectAliveLabel();
        for (FreshLabel freshLabel : freshLabels) {
            freshLabel.setPicUrl(fileUrl + freshLabel.getPicUrl());
        }
        return freshLabels;
    }
}
