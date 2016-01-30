package org.house.service;

import java.util.List;

import org.house.dao.RoomTypeDao;
import org.house.dao.StreetDao;
import org.house.maodel.Street;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StreetService
{
	@Autowired
	private StreetDao streetDao;
	
	public List<Street> getAllStreets()
	{
		return streetDao.getAll();
	}
	
	public Street getStreetById(Integer id)
	{
		return streetDao.getStreetById(id);
	}
	
	
}
