package org.house.maodel;

import java.util.HashSet;
import java.util.Set;

/**
 * TRoomtype entity. @author MyEclipse Persistence Tools
 */

public class RoomType implements java.io.Serializable
{

	// Fields

	private Integer id;
	private String name;
	private Set<Houseinfo> houseinfos = new HashSet(0);

	// Constructors

	/** default constructor */
	public RoomType()
	{
	}

	/** full constructor */
	public RoomType(String name, Set THouseinfos)
	{
		this.name = name;
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

	public Set getHouseinfos()
	{
		return this.houseinfos;
	}

	public void setHouseinfos(Set THouseinfos)
	{
		this.houseinfos = THouseinfos;
	}

}