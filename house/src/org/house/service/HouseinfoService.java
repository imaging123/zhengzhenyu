package org.house.service;

import java.util.List;

import org.house.dao.InfoDao;
import org.house.maodel.HouseCond;
import org.house.maodel.Houseinfo;
import org.house.maodel.Page;
import org.house.maodel.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HouseinfoService
{
	@Autowired
	private InfoDao infoDao;
	
	
	public Page getAllByPage(Page page)
	{
		return infoDao.getAllByPage(page);
	}
	
	
	public List<Houseinfo> getByCondition(HouseCond hc)
	{
		return infoDao.getByCondition(hc);
	}
	
	public Houseinfo getInfoById(Integer id)
	{
		return infoDao.getInfoById(id);
	}
	
	public void deleteByTypeId(Integer id)
	{
		infoDao.delete(id);
	}
	
	public void saveOrUpdate(Houseinfo info)
	{
		infoDao.saveOrUpdate(info);
	}
	
}
