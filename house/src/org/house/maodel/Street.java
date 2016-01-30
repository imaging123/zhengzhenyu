package org.house.maodel;

import java.util.HashSet;
import java.util.Set;

/**
 * TStreet entity. @author MyEclipse Persistence Tools
 */

public class Street implements java.io.Serializable
{

	// Fields

	private Integer id;
	private District district;
	private String name;
	private Set<Houseinfo> houseinfos = new HashSet(0);

	// Constructors

	/** default constructor */
	public Street()
	{
	}

	/** minimal constructor */
	public Street(District TDistrict, String name)
	{
		this.district = TDistrict;
		this.name = name;
	}

	/** full constructor */
	public Street(District TDistrict, String name, Set THouseinfos)
	{
		this.district = TDistrict;
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

	public District getDistrict()
	{
		return this.district;
	}

	public void setDistrict(District TDistrict)
	{
		this.district = TDistrict;
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

	@Override
	public String toString()
	{
		return "Street [id=" + id + ", district=" + district + ", name=" + name
				+ ", houseinfos=" + houseinfos + "]";
	}

	
	
}