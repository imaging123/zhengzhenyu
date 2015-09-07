package org.forumSSH.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Topic extends Posting implements Serializable {
	private String title;
	private Set<Response> responses = new HashSet<Response>();
	
	public Topic() {

	}

	public Topic(String theTitle, String theContent, User theUser) {
		super(theContent, theUser);
		title = theTitle;
	}
	


	public String getTitle() {
		return title;
	}

	public void setTitle(String theTitle) {
		title = theTitle;
	}

	public Set<Response> getResponses() {
		return responses;
	}

	public void setResponses(Set<Response> theResponses) {
		responses = theResponses;
	}

	public int getSize() {
		int count = 0;
		for (Response response : this.getResponses()) {
			if (!response.isDeleted()) {
				count++;
			}
		}
		return count;
	}
	
	public Response getResponseById(String theId) {
		Response result = null;
		Integer id = Integer.valueOf(theId);
		for (Response response : this.getResponses()) {
			if (id.equals(response.getId())) {
				result = response;
				break;
			}
		}
		return result;
	}

	@Override
	public boolean equals(Object theObj) {
		if (this == theObj) {
			return true;
		}
		if (!(theObj instanceof Topic)) {
			return false;
		}
		Topic topic = (Topic) theObj;
		if (!this.title.equals(topic.title)) {
			return false;
		}
		if (!topic.content.equals(topic.content)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int result;
		result = title.hashCode();
		result += content.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return this.title + " " + this.content;
	}
}
