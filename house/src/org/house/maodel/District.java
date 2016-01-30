package org.house.maodel;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * TDistrict entity. @author MyEclipse Persistence Tools
 */

public class District implements java.io.Serializable
{

	// Fields

	private Integer id;
	private String name;
	private Set<Street> streets = new HashSet(0);

	// Constructors

	/** default constructor */
	public District()
	{
	}

	/** full constructor */
	public District(String name, Set TStreets)
	{
		this.name = name;
		this.streets = TStreets;
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

	public Set getStreets()
	{
		return this.streets;
	}

	public void setStreets(Set TStreets)
	{
		this.streets = TStreets;
	}

}