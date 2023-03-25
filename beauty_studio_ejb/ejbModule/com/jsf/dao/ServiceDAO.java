package com.jsf.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.NoResultException;

import com.jsf.entities.Service;

@Stateless
public class ServiceDAO {
	private final static String UNIT_NAME = "jsfcourse-simplePU";

	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

	public void create(Service service) {
		em.persist(service);
	}

	public Service merge(Service service) {
		return em.merge(service);
	}

	public void remove(Service service) {
		em.remove(em.merge(service));
	}

	public Service find(Object id) {
		return em.find(Service.class, id);
	}

}
