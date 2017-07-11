package com.xmn.saas.dao.common;

import com.xmn.saas.entity.common.SystemAnnouncement;

public interface SystemAnnouncementDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SystemAnnouncement record);

    int insertSelective(SystemAnnouncement record);

    SystemAnnouncement selectByPrimaryKey(Integer id);
    
    SystemAnnouncement selectOne();

    int updateByPrimaryKeySelective(SystemAnnouncement record);

    int updateByPrimaryKeyWithBLOBs(SystemAnnouncement record);

    int updateByPrimaryKey(SystemAnnouncement record);
}