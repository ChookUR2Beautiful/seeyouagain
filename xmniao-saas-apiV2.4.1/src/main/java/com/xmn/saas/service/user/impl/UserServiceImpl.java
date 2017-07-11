package com.xmn.saas.service.user.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmn.saas.dao.user.UserDao;
import com.xmn.saas.entity.user.User;
import com.xmn.saas.service.user.UserService;


@SuppressWarnings("all")
@Service
public class UserServiceImpl implements UserService {

	/**
	 * 日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDao userDao;
	
	
	public User findUserByPrimaryKey(Long userId)throws Exception{
		return userDao.selectByPrimaryKey(userId);
	}
}
