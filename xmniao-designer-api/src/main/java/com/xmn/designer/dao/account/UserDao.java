package com.xmn.designer.dao.account;

import com.xmn.designer.entity.account.User;


public interface UserDao {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    /**
     * 通过out_id查询用户信息
     * @param outId
     * @return
     */
    User findUserByOutId(Long outId);
}