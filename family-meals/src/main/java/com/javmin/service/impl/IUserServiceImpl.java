package com.javmin.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.javmin.dao.IUserDao;
import com.javmin.entity.User;
import com.javmin.service.IUserService;
@Service("userService")
public class IUserServiceImpl implements IUserService{
	@Resource

	  private IUserDao userDao;

	  @Override

	  public User getUserById(int userId) {


	    return this.userDao.selectByPrimaryKey(userId);

	  }
}
