package org.forumSSH.action;

import java.util.ArrayList;
import java.util.List;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.forumSSH.model.Response;
import org.forumSSH.model.Topic;
import org.forumSSH.model.User;
import org.forumSSH.service.ResponseService;
import org.forumSSH.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

@Results(
{
		@Result(name = "reload", location = "user!login.action", type = "redirect"),
		@Result(name = "list", location = "topic!list.action", type = "redirect") })
public class TopicAction extends ActionSupport
{
	@Autowired
	private TopicService topicService;
	@Autowired
	private ResponseService responseService;
	private Topic topic;
	private Integer id;
	private Response response;
	private List<Response> responses = new ArrayList<Response>();

	public List<Response> getResponses()
	{
		return responses;
	}

	public void setResponses(List<Response> responses)
	{
		this.responses = responses;
	}

	private List<Topic> topices = new ArrayList<Topic>();

	public List<Topic> getTopices()
	{
		return topices;
	}

	public void setTopices(List<Topic> topices)
	{
		this.topices = topices;
	}

	public Response getResponse()
	{
		return response;
	}

	public void setResponse(Response response)
	{
		this.response = response;
	}

	// private HttpServletRequest
	// request=(HttpServletRequest)ServletActionContext.getRequest();

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Topic getTopic()
	{
		return topic;
	}

	public void setTopic(Topic topic)
	{
		this.topic = topic;
	}

	@Override
	public String execute() throws Exception
	{
		return list();
	}

	public String list() throws Exception
	{
		User user = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("user");
		if (user != null)
		{
			topices = topicService.getTopices(user);
		}
		return SUCCESS;
	}

	public String create() throws Exception
	{

		User user = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("user");
		if (user != null)
		{
			return "topicForm";
		} else
		{
			return "reload";
		}

	}

	public String addTopic() throws Exception
	{
		User user = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("user");
		topic = new Topic(topic.getTitle(), topic.getContent(), user);
		topicService.createTopic(topic);
		return list();
	}

	public String response() throws Exception
	{
		if (id != null && !"".equals(id))
		{
			topic = topicService.getTopic(id);
			ServletActionContext.getRequest().getSession()
					.setAttribute("topic1", topic);
		}
		Topic topic1 = (Topic) ServletActionContext.getRequest().getSession()
				.getAttribute("topic1");

		responses = responseService.getResponses(topic1);
		if (responses.size() <= 0)
		{
			return "responseForm";
		} else
		{
			return "responseList";
		}
	}

	public String addResp() throws Exception
	{
		return "responseForm";
	}

	public String addResponse() throws Exception
	{
		Topic topic1 = (Topic) ServletActionContext.getRequest().getSession()
				.getAttribute("topic1");
		response.setUser((User) ServletActionContext.getRequest().getSession()
				.getAttribute("user"));
		response.setTopic(topic1);

		responseService.addResponse(response);

		return response();
	}

	public String remove() throws Exception
	{
		if (id != null && !"".equals(id))
		{
			response = responseService.getResponseById(id);
		}
		response.setDeleted(true);
		responseService.removeResponse(response);

		Topic topic1 = (Topic) ServletActionContext.getRequest().getSession()
				.getAttribute("topic1");
		responses = responseService.getResponses(topic1);
		return "responseList";
	}

	public String rollBack() throws Exception
	{
		if (id != null && !"".equals(id))
		{
			response = responseService.getResponseById(id);
		}
		response.setDeleted(false);
		responseService.rollbackResponse(response);

		Topic topic1 = (Topic) ServletActionContext.getRequest().getSession()
				.getAttribute("topic1");
		responses = responseService.getResponses(topic1);
		return "responseList";
	}

}
