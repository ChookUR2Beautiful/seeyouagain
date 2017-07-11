package com.xmn.saas.service.test.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmn.saas.dao.user.UserDao;
import com.xmn.saas.entity.test.Activety;
import com.xmn.saas.entity.user.User;
import com.xmn.saas.service.test.TestService;

@Service
public class TestServiceImpl implements TestService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public Activety get(Long id) {

		Activety activety = new Activety();
		activety.setActivetyName("活动名称");
		User user = userDao.selectByPrimaryKey(id);
		activety.setUser(user);

		return activety;
	}

}
