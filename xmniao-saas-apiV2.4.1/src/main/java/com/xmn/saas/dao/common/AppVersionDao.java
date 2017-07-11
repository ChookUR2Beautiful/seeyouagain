package com.xmn.saas.dao.common;

import com.xmn.saas.entity.common.AppVersion;

public interface AppVersionDao {
    int deleteByPrimaryKey(Integer id);

    int insert(AppVersion record);

    int insertSelective(AppVersion record);

    AppVersion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppVersion record);

    int updateByPrimaryKey(AppVersion record);

    AppVersion selectCurrentAppVersion(AppVersion appVersion);
}