package com.jsfcourse.project;

import com.jsf.dao.UserDAO;
import com.jsf.entities.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

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

import com.jsf.dao.*;
import com.jsf.entities.*;

@Named
@RequestScoped
public class LoginBB {
	
	private static final String PAGE_CALENDAR = "/pages/user/calendar?faces-redirect=true";
	
	private static final String PAGE_ADMIN = "/pages/admin/adminPanel?faces-redirect=true";
	private static final String PAGE_USER_EDIT = "/pages/admin/adminPanel_UsersEdit?faces-redirect=true";
	
	private static final String PAGE_LOGIN = "/pages/public/login?faces-redirect=true";
	private static final String PAGE_REGISTRY = "/pages/public/registration?faces-redirect=true";
	private static final String PAGE_CENNIK = "/pages/public/login?faces-redirect=true";
	private static final String PAGE_MAIN = "/pages/public/index?faces-redirect=true";
	
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String login;
	private String password;
	private String rpassword;

	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;

	@EJB
	UserDAO userDAO;

	@Inject
	@ManagedProperty("#{txtLogin}")
	private ResourceBundle txtLogin;

	public void setLogin(String login) {
		this.login = login;
	}

	public String getLogin() {
		return login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setRPassword(String rpassword) {
		this.rpassword = rpassword;
	}

	public String getRPassword() {
		return rpassword;
	}

	public String doLogin() {

		FacesContext ctx = FacesContext.getCurrentInstance();

		User user = userDAO.getUserByLoginPassword(login, password);

		if (user == null) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Niepoprawny login lub hasło", null));
			return PAGE_MAIN;
		}

		RemoteClient<User> client = new RemoteClient<User>();
		client.setDetails(user);

		if (user.getRoles() != null) {
			for (Role role : user.getRoles()) {
				client.getRoles().add(role.getName());
			}
		}

		HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();
		client.store(request);

		return PAGE_LOGIN;
	}
	
	/*public String doLogin() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) extcontext.getSession(true);
		// 1. verify login and password - get User from "database"
		Map<String,Object> searchParams = new HashMap<String,Object>();
		searchParams.put("login", getLogin());
		searchParams.put("pass", getPassword());

		List<User> uzytkownikList = userDAO.getList(searchParams);

		User uzytkownik = null;
		
		RemoteClient<User> client = new RemoteClient<User>(); //create new RemoteClient
		
		if(!uzytkownikList.isEmpty()) {
	
			uzytkownik = uzytkownikList.get(0);
			client.setDetails(uzytkownik);
			if (uzytkownik.getRoles() != null) {
				for (Role role : uzytkownik.getRoles()) {
					client.getRoles().add(role.getName());
				}
			}

			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Znaleziono użytkownika", null));
		}

		
		HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();
		session.setAttribute("uzytkownik", uzytkownik);
		client.store(request);
		
		// and enter the system (now SecurityFilter will pass the request)
		return PAGE_MAIN;
	}*/

	public String doLogout(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);
		//Invalidate session
		// - all objects within session will be destroyed
		// - new session will be created (with new ID)
		session.invalidate();
		
		return PAGE_LOGIN;
	}
}
