package org.house.service;

import java.util.List;

import org.house.dao.DistrictDao;
import org.house.maodel.District;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DistrictService
{
	@Autowired
	private DistrictDao districtDao;
	
	public List<District> getAll()
	{
		return districtDao.getAll();
	}
	
	public District getById(Integer id)
	{
		return districtDao.getById(id);
	}

}
