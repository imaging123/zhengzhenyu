package org.house.service;

import java.util.List;

import org.house.dao.RoomTypeDao;
import org.house.maodel.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoomTypeService
{
	@Autowired
	private RoomTypeDao roomTypeDao;
	
	public List<RoomType> getAll()
	{
		return roomTypeDao.getAll();
	}
}
