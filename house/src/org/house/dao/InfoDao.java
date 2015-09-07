package org.house.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.house.maodel.HouseCond;
import org.house.maodel.Houseinfo;
import org.house.maodel.Page;
import org.house.maodel.RoomType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class InfoDao extends SimpleHibernateDao<Houseinfo, Integer>
{

	@SuppressWarnings("unchecked")
	public Houseinfo getInfoById(Integer id)
	{
		return (Houseinfo) getSession()
				.createQuery("from Houseinfo h where h.id =:rid ")
				.setInteger("rid", id).uniqueResult();
	}

	public void deleteByTypeId(Integer id)
	{
		Houseinfo info = (Houseinfo) getSession()
				.createQuery("from Houseinfo h where h.roomtype.id =: rid")
				.setInteger("rid", id).uniqueResult();

		getSession().delete(info);
	}

	public Page getAllByPage(Page page)
	{
		String hql = "from Houseinfo ";
		List<Houseinfo> list = new ArrayList<Houseinfo>();
		Session sess = null;

		try
		{
			sess = getSession();
			Query q = sess.createQuery(hql);
			// 分页
			q.setFirstResult(page.getStart());
			q.setMaxResults(page.getPageSize());
			list = (List<Houseinfo>) q.list();
			for (Houseinfo info : list)
			{
				page.getData().add(info);
			}
			hql = "select count(h) from Houseinfo h";
			Query q2 = sess.createQuery(hql);

			int count = Integer.valueOf(q2.uniqueResult().toString());
			page.setTotalCount(count);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return page;

	}

	public String getCondition(HouseCond hc)
	{
		String str = "";
		if (hc.getTitle() != null && !"".equals(hc.getTitle()))
		{
			str += " and title like :title";
		}
		if (hc.getMinPrice() != null)
		{
			str += " and price>:minPrice ";
		}
		if (hc.getMaxPrice() != null)
		{
			str += " and price<:maxPrice ";
		}
		if (hc.getMinArea() != null)
		{
			str += " and area>:minArea ";
		}
		if (hc.getMaxArea() != null)
		{
			str += "and area<:maxArea ";
		}
		if (hc.getStreet() != null && hc.getStreet().getName() != null
				&& !"".equals(hc.getStreet().getName()))
		{
			str += " and name like:street.name";
		}
		if (hc.getRoomtype() != null && hc.getRoomtype().getName() != null
				&& !"".equals(hc.getRoomtype().getName()))
		{
			str += " and roomtype.name like:roomtype.name";
		}
		return str;
	}

	public List<Houseinfo> getByCondition(HouseCond hc)

	{
		String hql = "from Houseinfo  where 1=1" + getCondition(hc);

		Session sess = getSession();
		List<Houseinfo> types = new ArrayList<>();
		try
		{
			System.out.println(hql);
//			hql = "from Houseinfo where 1=1 and street.name like '%香港路%'";
			Query q = sess.createQuery(hql);
			q.setProperties(hc);
			types = (List<Houseinfo>) q.list();

			for (Houseinfo houseinfo : types)
			{

				String title = houseinfo.getTitle();
				// String roomTypeName = houseinfo.getRoomtype().getName();
				// String streetName = houseinfo.getStreet().getName();

				if (title.startsWith("%") && title.endsWith("%"))
				// || roomTypeName.startsWith("%")
				// && roomTypeName.endsWith("%")
				// || streetName.startsWith("%")
				// && streetName.endsWith("%"))
				{
					houseinfo.setTitle(title.substring(1, title.length() - 1));
					// houseinfo.getRoomtype()
					// .setName(
					// roomTypeName.substring(1,
					// roomTypeName.length() - 1));
					// houseinfo.getStreet().setName(
					// streetName.substring(1, streetName.length() - 1));
				}

			}
			return types;

		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

	public void saveOrUpdate(Houseinfo info)
	{
		super.saveOrUpdate(info);
	}

	@SuppressWarnings("unchecked")
	public List<Houseinfo> getAllInfos()
	{
		List<Houseinfo> info = new ArrayList<>();
		info = getSession()
				.createQuery(
						"from Houseinfo where 1=1 and street.name like : streetname")
				.setString("streetname", "香港路").list();
		return info;
	}

	public static void main(String[] args)
	{
		InfoDao dao = new InfoDao();
		List<Houseinfo> infos = new ArrayList<>();

		infos = dao.getAllInfos();

		System.out.println();

	}
}
