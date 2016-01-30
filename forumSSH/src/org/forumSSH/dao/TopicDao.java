package org.forumSSH.dao;

import java.sql.SQLException;
import java.util.List;
import org.forumSSH.model.Response;
import org.forumSSH.model.Topic;
import org.forumSSH.model.User;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class TopicDao extends SimpleHibernateDao<Topic, Integer>
{
	public synchronized void createTopic(Topic topic)
	{

			saveOrUpdate(topic);
	}

	@SuppressWarnings("unchecked")
	public List<Topic> getTopices(User user)
	{
		try
		{
			return getSession().createQuery("from Topic as t where t.user.id=:uid")
					.setInteger("uid", user.getId()).list();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public Topic getTopic(Integer id)
	{
		try
		{
			return (Topic) getSession().get(Topic.class, id);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

}
