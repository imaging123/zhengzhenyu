package org.house.dao;

import java.util.List;

import org.house.maodel.District;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class DistrictDao extends SimpleHibernateDao<District, Integer>
{
	
}
