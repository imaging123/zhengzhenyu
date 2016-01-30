package org.forumSSH.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User implements Serializable{
	private Integer id;
	private String userName;
	private String passWord;
//	private List<Posting> posting = new ArrayList<Posting>();
	private Set<Topic> topics=new HashSet<>();
	
	public User() {
	}

	public User(String theName, String thePasswd) {
		userName = theName;
		passWord = thePasswd;
	}

	public User(Integer theId, String theName, String thePasswd) {
		id = theId;
		userName = theName;
		passWord = thePasswd;
	}

	
	public Set<Topic> getTopics()
	{
		return topics;
	}

	public void setTopics(Set<Topic> topics)
	{
		this.topics = topics;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer theId) {
		id = theId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String theName) {
		userName = theName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String thePasswd) {
		passWord = thePasswd;
	}

	@Override
	public boolean equals(Object theObj) {
		if (this == theObj) {
			return true;
		}
		if (!(theObj instanceof User)) {
			return false;
		}
		User user = (User) theObj;
		if (!this.userName.equals(user.userName)) {
			return false;
		}
		if (!this.passWord.equals(user.passWord)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int result;
		result = userName.hashCode();
		result += passWord.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return this.getUserName();
	}

}
