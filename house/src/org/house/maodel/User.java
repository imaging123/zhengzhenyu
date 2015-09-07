package org.house.maodel;

import java.util.HashSet;
import java.util.Set;

/**
 * TUser2 entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable
{

	// Fields

	private Integer id;
	private String name;
	private String pass;
	private String phone;
	private String realname;
	private Set<Houseinfo> houseinfos = new HashSet(0);

	// Constructors

	/** default constructor */
	public User()
	{
	}

	/** minimal constructor */
	public User(String name, String pass, String phone, String realname)
	{
		this.name = name;
		this.pass = pass;
		this.phone = phone;
		this.realname = realname;
	}

	/** full constructor */
	public User(String name, String pass, String phone, String realname,
			Set THouseinfos)
	{
		this.name = name;
		this.pass = pass;
		this.phone = phone;
		this.realname = realname;
		this.houseinfos = THouseinfos;
	}

	// Property accessors

	public Integer getId()
	{
		return this.id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getPass()
	{
		return this.pass;
	}

	public void setPass(String pass)
	{
		this.pass = pass;
	}

	public String getPhone()
	{
		return this.phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getRealname()
	{
		return this.realname;
	}

	public void setRealname(String realname)
	{
		this.realname = realname;
	}

	public Set getHouseinfos()
	{
		return this.houseinfos;
	}

	public void setHouseinfos(Set THouseinfos)
	{
		this.houseinfos = THouseinfos;
	}

}