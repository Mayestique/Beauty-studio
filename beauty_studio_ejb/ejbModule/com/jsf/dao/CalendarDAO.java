package com.jsf.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.NoResultException;

import com.jsf.entities.Calendar;
import com.jsf.entities.PriceList;
import com.jsf.entities.Service;


@Stateless
public class CalendarDAO {
	private final static String UNIT_NAME = "jsfcourse-simplePU";

	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

	public void create(Calendar calendar) {
		em.persist(calendar);
	}

	public Calendar merge(Calendar calendar) {
		return em.merge(calendar);
	}

	public void remove(Calendar calendar) {
		em.remove(em.merge(calendar));
	}

	public Calendar find(Object id) {
		return em.find(Calendar.class, id);
	}
	
	public List<Service> getFullList() {
		List<Service> list = null;

		Query query = em.createQuery("select s from Service s");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<Service> getList(Map<String, Object> searchParams) {
		List<Service> list = null;

		// 1. Build query string with parameters
		String select = "select s ";
		String from = "from Service s ";
		String where = "";
		String orderby = "";

		// search for surname
		String surname = (String) searchParams.get("service");
		if (surname != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "p.service like :service ";
		}
		
		// ... other parameters ... 

		// 2. Create query object
		Query query = em.createQuery(select + from + where + orderby);

		// 3. Set configured parameters
		if (surname != null) {
			query.setParameter("service", surname+"%");
		}

		// ... other parameters ... 

		// 4. Execute query and retrieve list of Person objects
		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
