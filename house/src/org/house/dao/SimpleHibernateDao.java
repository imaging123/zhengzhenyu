package org.house.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class SimpleHibernateDao<T,PK extends Serializable> {
	private SessionFactory sessionFactory;
	private Session session;
	private Class<T> entityClass;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public Class<T> getEntityClass() {
		return entityClass;
	}

	public SimpleHibernateDao() {
		//获得泛型类型
		entityClass =ReflectionUtils.getSuperClassGenricType(getClass());
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<T> getAll() {
		return getSession().createCriteria(getEntityClass()).list();
	}

	public T getById(PK id) {
		return (T) getSession().load(getEntityClass(), id);
	}

	public void saveOrUpdate(Object entity) {
		getSession().saveOrUpdate(entity);
	}

	public void delete(PK id) {
		getSession().delete(getById(id));
	}

}
