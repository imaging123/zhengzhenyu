package org.forumSSH.model;

import java.io.Serializable;

public class Response extends Posting implements Serializable{
	private Topic topic;
	
	private boolean deleted ;

	private String status="normal";

	public Response() {

	}

	public Response(String theContent, User theUser) {
		super(theContent, theUser);
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic theTopic) {
		topic = theTopic;
	}

//	public boolean isDeleted() {
//		return deleted;
//	}
//
//	public void setDeleted(boolean theDeleted) {
//		deleted = theDeleted;
//	}
//
//	public String getStatus() {
//		if(isDeleted()) {
//			return "deleted";
//		}
//		return "normal";
//	}
//
//	public void setStatus(String theStatus) {
//		status = theStatus;
//	}
	
	public String getStatus()
	{
		return status;
	}
	public void setStatus(String status)
	{
		this.status = status;
		if("deleted".equals(status))
			deleted = true;
		else
			deleted = false;
	}
	public boolean isDeleted()
	{
		if("deleted".equals(status)) {
		return true;
	}
	return false;
	}
	public void setDeleted(boolean deleted)
	{
		this.deleted = deleted;
		if(deleted) {
			setStatus("deleted");
		}else {
			setStatus("normal");
		}
	}

}
