package org.forumSSH.service;

import java.util.List;

import org.forumSSH.dao.ResponseDao;
import org.forumSSH.model.Response;
import org.forumSSH.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ResponseService
{

	@Autowired
	private ResponseDao responseDao;
	
	
	public List<Response> getResponses(Topic topic)
	{
		return responseDao.getResponses(topic);
	}
	
	public void addResponse(Response theResponse)
	{
		responseDao.addResponse(theResponse);
	}
	
	public void removeResponse(Response theResponse) {
		responseDao.removeResponse(theResponse);
	}
	
	public void rollbackResponse(Response theResponse) {
		responseDao.rollbackResponse(theResponse);
	}
	
	public Response getResponseById(Integer id)
	{
		return responseDao.getResponseById(id);
	}
	
}
