package org.forumSSH.service;

import java.util.List;
import java.util.Set;

import org.forumSSH.dao.TopicDao;
import org.forumSSH.model.Response;
import org.forumSSH.model.Topic;
import org.forumSSH.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TopicService {
	@Autowired
	private TopicDao topicDao;

//	public TopicManager() {
//		topicDao = new TopicDao();
//	}

	public synchronized void createTopic(Topic topic) {
		topicDao.createTopic(topic);
	}

	public synchronized List<Topic> getTopices(User user) {
		return topicDao.getTopices(user);
	}

	public Topic getTopic(Integer id) {
		return topicDao.getTopic(id);
	}
	
	
	
//	public Integer getSize(Topic topic)
//	{
//		return topicDao.getSize(topic);
//	}
	
	
	
}
