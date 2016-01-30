package org.forumSSH.dao;


import org.forumSSH.model.User;
import org.hibernate.Transaction;
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
					.setString("userName", user.getUserName())
					.setString("passWord", user.getPassWord()).uniqueResult();//查唯一一个。list差一个列表
		}
	}

	public synchronized void createUser(User user)
	{
			saveOrUpdate(user);
	}
}
