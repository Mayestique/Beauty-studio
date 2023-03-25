package com.jsfcourse.project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Time;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.simplesecurity.RemoteClient;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.faces.view.ViewScoped;
import javax.persistence.NoResultException;

import com.jsf.dao.*;
import com.jsf.entities.*;


@Named
@RequestScoped
public class CalendarBB {
	
	private static final String PAGE_CALENDAR = "/pages/user/calendar?faces-redirect=true";
	
	private static final String PAGE_ADMIN = "/pages/admin/adminPanel?faces-redirect=true";
	private static final String PAGE_USER_EDIT = "/pages/admin/adminPanel_UsersEdit?faces-redirect=true";
	
	private static final String PAGE_LOGIN = "/pages/public/login?faces-redirect=true";
	private static final String PAGE_REGISTRY = "/pages/public/registration?faces-redirect=true";
	private static final String PAGE_CENNIK = "/pages/public/login?faces-redirect=true";
	private static final String PAGE_MAIN = "/pages/public/index?faces-redirect=true";
	
	private static final String PAGE_STAY_AT_THE_SAME = null;
	
	private Calendar calendar = new Calendar();
	private User user = new User();
	private int idservice;
	private String service;


	@Inject
	Flash flash;
	
	@Inject
	ExternalContext extcontext;

	@EJB
	CalendarDAO calendarDAO;
	@EJB
	UserDAO userDAO;
	@EJB
	PriceDAO priceDAO;
	@EJB
	ServiceDAO serviceDAO;
	
	@Inject
	@ManagedProperty("#{txtLogin}")
	private ResourceBundle txtLogin;
	
	public Calendar getCalendar() {
		return calendar;
	} 
	public User getUser() {
		
		return user;
	}

	public int getIdservice() {
		return idservice;
	}
	public void setIdservice(int idservice) {
		this.idservice = idservice;
	}
	
	public String newService(){
		try {
			Time time = new Time(calendar.getDate().getTime());
			calendar.setTime(time);
			
			FacesContext ctx = FacesContext.getCurrentInstance();
			HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();
			RemoteClient<User> client = RemoteClient.load(request.getSession());
			calendar.setUser(client.getDetails());
			
			Service s = serviceDAO.find(idservice);
			calendar.setService(s);
			calendarDAO.create(calendar);

		} catch (Exception e) {
			e.printStackTrace();
			//context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "wystąpił błąd podczas zapisu", null));
			return PAGE_STAY_AT_THE_SAME;
		}
		
		return PAGE_MAIN;
	}
	
	public List<Service> getFullList(){
		return calendarDAO.getFullList();
	}

	public List<Service> getList(){
		List<Service> list = null;
		
		//1. Prepare search params
		Map<String,Object> searchParams = new HashMap<String, Object>();
		
		if (service != null && service.length() > 0){
			searchParams.put("service", service);
		}
		
		//2. Get list
		list = calendarDAO.getList(searchParams);
		
		return list;
	}
	
}
