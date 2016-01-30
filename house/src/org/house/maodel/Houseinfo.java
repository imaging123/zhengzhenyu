package org.house.maodel;

import java.sql.Timestamp;

/**
 * THouseinfo entity. @author MyEclipse Persistence Tools
 */

public class Houseinfo implements java.io.Serializable
{

	// Fields

	private Integer id;
	private User user;
	private RoomType roomtype;
	private Street street;
	private String title;
	private Integer area;
	private Integer price;
	private Timestamp createdate;
	private String conn;
	private String detail;

	// Constructors

	/** default constructor */
	public Houseinfo()
	{
	}

	/** minimal constructor */
	public Houseinfo(String title, Integer area, Integer price,
			Timestamp createdate, String conn)
	{
		this.title = title;
		this.area = area;
		this.price = price;
		this.createdate = createdate;
		this.conn = conn;
	}

	/** full constructor */
	public Houseinfo(User TUser2, RoomType TRoomtype, Street TStreet,
			String title, Integer area, Integer price,
			Timestamp createdate, String conn, String detail)
	{
		this.user = TUser2;
		this.roomtype = TRoomtype;
		this.street = TStreet;
		this.title = title;
		this.area = area;
		this.price = price;
		this.createdate = createdate;
		this.conn = conn;
		this.detail = detail;
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

	public User getUser()
	{
		return this.user;
	}

	public void setUser(User TUser2)
	{
		this.user = TUser2;
	}

	public RoomType getRoomtype()
	{
		return this.roomtype;
	}

	public void setRoomtype(RoomType TRoomtype)
	{
		this.roomtype = TRoomtype;
	}

	public Street getStreet()
	{
		return this.street;
	}

	public void setStreet(Street TStreet)
	{
		this.street = TStreet;
	}

	public String getTitle()
	{
		return this.title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public Integer getArea()
	{
		return this.area;
	}

	public void setArea(Integer area)
	{
		this.area = area;
	}

	public Integer getPrice()
	{
		return this.price;
	}

	public void setPrice(Integer price)
	{
		this.price = price;
	}

	public Timestamp getCreatedate()
	{
		return this.createdate;
	}

	public void setCreatedate(Timestamp createdate)
	{
		this.createdate = createdate;
	}

	public String getConn()
	{
		return this.conn;
	}

	public void setConn(String conn)
	{
		this.conn = conn;
	}

	public String getDetail()
	{
		return this.detail;
	}

	public void setDetail(String detail)
	{
		this.detail = detail;
	}

}