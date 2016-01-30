package org.house.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.house.maodel.HouseCond;
import org.house.maodel.Houseinfo;
import org.house.maodel.Page;
import org.house.maodel.RoomType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class RoomTypeDao extends SimpleHibernateDao<RoomType, Integer>
{
	
	
}
