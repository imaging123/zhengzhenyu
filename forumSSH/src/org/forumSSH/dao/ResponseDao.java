package org.forumSSH.dao;

import java.util.List;

import org.forumSSH.model.Response;
import org.forumSSH.model.Topic;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation=Propagation.REQUIRED)
public class ResponseDao extends SimpleHibernateDao<Response, Integer>
{
	@SuppressWarnings("unchecked")
	public List<Response> getResponses(Topic topic)
	{
		try
		{
			return getSession()
					.createQuery("from Response as r where r.topic.id=:tid")
					.setInteger("tid", topic.getId()).list();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public Response getResponseById(Integer id)
	{
		try
		{
			return (Response) getSession().get(Response.class, id);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public void addResponse(Response theResponse)
	{
			saveOrUpdate(theResponse);
	}

	public void removeResponse(Response theResponse)
	{
		saveOrUpdate(theResponse);

	}

	public void rollbackResponse(Response theResponse)
	{

		
			saveOrUpdate(theResponse);
	}

}
