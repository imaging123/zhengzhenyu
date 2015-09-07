package org.forumSSH.action;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.forumSSH.model.User;
import org.forumSSH.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

@Results({ @Result(name = "reload", location = "topic!list.action", type = "redirect") })

public class UserAction extends ActionSupport
{
	@Autowired
	private UserService userService;
	private User user;
	public User getUser()
	{
		return user;
	}
	public void setUser(User user)
	{
		this.user = user;
	}
	
	
	public String login() throws Exception
	{
		user=userService.getUser(user);
		
		if(user != null)
		{
			ServletActionContext.getRequest().getSession().setAttribute("user", user);
			return "reload";
		}
		else
		{
//			ServletActionContext.getRequest().setAttribute("errors", (new ForumException(
//					"not exists this user.")).getMessage());
			return "login";
		}
	}
	
	public String logOut() throws Exception
	{
		ServletActionContext.getRequest().getSession().removeAttribute("user");
		return "reload";
	}
	
	public String register() throws Exception
	{
		userService.createUser(user);
		user=null;
		return login();
	}
	public String reg() throws Exception
	{
		return "register";
	}
}
