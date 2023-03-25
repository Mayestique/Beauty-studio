package com.jsfcourse.project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
// import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
// import javax.servlet.http.HttpSession;

import com.jsf.entities.PriceList;
// import com.jsf.dao.UserDAO;
// import com.jsf.entities.User;
// import com.jsfcourse.calc.ManagedProperty;
// import com.jsfcourse.calc.ResourceBundle;
// import com.jsfcourse.calc.ViewScoped;
import com.jsf.dao.PriceDAO;
// import com.jsf.entities.PriceList;

// import javax.annotation.PostConstruct;
import javax.faces.annotation.ManagedProperty;
// import javax.faces.application.FacesMessage;
import java.util.ResourceBundle;
// import javax.faces.view.ViewScoped;


@Named
@RequestScoped
public class PriceListBB {
	
	private static final String PAGE_CALENDAR = "/pages/user/calendar?faces-redirect=true";
	
	private static final String PAGE_ADMIN = "/pages/admin/adminPanel?faces-redirect=true";
	private static final String PAGE_USER_EDIT = "/pages/admin/adminPanel_UsersEdit?faces-redirect=true";
	
	private static final String PAGE_LOGIN = "/pages/public/login?faces-redirect=true";
	private static final String PAGE_REGISTRY = "/pages/public/registration?faces-redirect=true";
	private static final String PAGE_CENNIK = "/pages/public/login?faces-redirect=true";
	private static final String PAGE_MAIN = "/pages/public/index?faces-redirect=true";
	
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String service;
		
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	PriceDAO priceDAO;
	
	@Inject
	@ManagedProperty("#{txtCennik}")
	private ResourceBundle txtCennik;
		
	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}
	
	

	public List<PriceList> getFullList(){
		return priceDAO.getFullList();
	}

	public List<PriceList> getList(){
		List<PriceList> list = null;
		
		//1. Prepare search params
		Map<String,Object> searchParams = new HashMap<String, Object>();
		
		if (service != null && service.length() > 0){
			searchParams.put("service", service);
		}
		
		//2. Get list
		list = priceDAO.getList(searchParams);
		
		return list;
	}

	/*public String newPerson(){
		Person person = new Person();
		
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("person", person);
		
		//2. Pass object through flash	
		flash.put("person", person);
		
		return PAGE_PERSON_EDIT;
	}

	public String editPerson(Person person){
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("person", person);
		
		//2. Pass object through flash 
		flash.put("person", person);
		
		return PAGE_PERSON_EDIT;
	}

	public String deletePerson(Person person){
		priceDAO.remove(person);
		return PAGE_STAY_AT_THE_SAME;
	}*/
	
}
