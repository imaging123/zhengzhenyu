package org.house.service;

import org.house.dao.UserDao;
import org.house.maodel.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class UserService
{
	@Autowired
	private UserDao userDao;
	
	public User getUser(User user)
	{
		return userDao.getUser(user);
	}
	
	public  void createUser(User user)
	{
		userDao.createUser(user);
	}
}
