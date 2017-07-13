package com.javmin.dao;

import com.javmin.entity.User;

public interface IUserDao {

	User selectByPrimaryKey(int userId);

}
