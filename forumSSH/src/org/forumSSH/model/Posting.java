package org.forumSSH.model;

import java.io.Serializable;

public abstract class Posting implements Serializable{
	protected Integer id;

	protected String content;

	protected User user;

	public Posting() {
	}

	public Posting(String theContent, User theUser) {
		content = theContent;
		user = theUser;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer theId) {
		id = theId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String theContent) {
		content = theContent;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User theUser) {
		user = theUser;
	}

}
