package com.xmniao.xmn.core.businessman.dao;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.businessman.entity.TSpecialTopic;
import com.xmniao.xmn.core.businessman.entity.TSpecialTopicPic;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

public interface SpecialTopicPicDao  extends BaseDao<TSpecialTopicPic> {
    int deleteByPrimaryKey(Integer id);

    @DataSource("master")
    int insert(TSpecialTopicPic record);

    @DataSource("master")
    int insertSelective(TSpecialTopicPic record);

    TSpecialTopicPic selectByPrimaryKey(Integer id);

    @DataSource("master")
    int updateByPrimaryKey(TSpecialTopicPic record);
    
    @DataSource("master")
    TSpecialTopicPic getSpecialTopicPicByFid(Integer fid);
    
    @DataSource("master")
    int deleteSpecialTopicPicByFid(Integer fid);
}