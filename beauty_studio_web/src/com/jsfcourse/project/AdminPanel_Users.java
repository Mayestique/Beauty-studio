package com.jsfcourse.project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.http.HttpSession;

import com.jsf.dao.UserDAO;
import com.jsf.entities.User;

@Named
@RequestScoped
public class AdminPanel_Users {

	private static final String PAGE_CALENDAR = "/pages/user/calendar?faces-redirect=true";
	
	private static final String PAGE_ADMIN = "/pages/admin/adminPanel?faces-redirect=true";
	private static final String PAGE_USER_EDIT = "/pages/admin/adminPanel_Edit?faces-redirect=true";
	
	private static final String PAGE_LOGIN = "/pages/public/login?faces-redirect=true";
	private static final String PAGE_REGISTRY = "/pages/public/registration?faces-redirect=true";
	private static final String PAGE_CENNIK = "/pages/public/login?faces-redirect=true";
	private static final String PAGE_MAIN = "/pages/public/index?faces-redirect=true";
	
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String login;
		
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	UserDAO userDAO;
		
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public List<User> getFullList(){ //Tworzenie całej listy
		return userDAO.getFullList();
	}

	public List<User> getList(){
		List<User> list = null;
		
		Map<String,Object> searchParams = new HashMap<String, Object>();
		if (login != null && login.length() > 0){
			searchParams.put("login", login);
		}
		list = userDAO.getList(searchParams);
		
		return list;
	}
	
	public String editUser(User user){
		
		flash.put("user", user);
		
		return PAGE_USER_EDIT;
	}
	
	public String newUser(){
		User user = new User();
		flash.put("user", user);
		
		return PAGE_REGISTRY;
	}

	public String deleteUser(User user){
		userDAO.remove(user);
		return PAGE_STAY_AT_THE_SAME;
	}
	 

}
