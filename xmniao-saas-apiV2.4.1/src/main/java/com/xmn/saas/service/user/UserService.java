package com.xmn.saas.service.user;

import com.xmn.saas.entity.user.User;

public interface UserService {

	public User findUserByPrimaryKey(Long userId)throws Exception;
}
