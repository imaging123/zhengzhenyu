package org.house.dao;

import org.house.maodel.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserDao extends SimpleHibernateDao<User, Integer>
{
	public User getUser(User user)
	{

		if (user == null)
		{
			return null;
		} else
		{
			return (User) getSession()
					.createQuery(
							"from User where userName=:userName and passWord=:passWord")
					.setString("userName", user.getName())
					.setString("passWord", user.getPass()).uniqueResult();// 查唯一一个。list差一个列表
		}
	}

	public void createUser(User user)
	{
		saveOrUpdate(user);
	}

}
