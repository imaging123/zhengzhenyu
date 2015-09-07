package org.house.dao;

import java.util.List;

import org.house.maodel.RoomType;
import org.house.maodel.Street;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class StreetDao extends SimpleHibernateDao<Street, Integer>
{

	public List<Street> getAllStreets()
	{
		return (List<Street>) getSession().createQuery("from Street");
	}

	public Street getStreetById(Integer id)
	{
		Street street = (Street) getSession()
				.createQuery("from Street where id = " + id).uniqueResult();
		return street;
	}

}
