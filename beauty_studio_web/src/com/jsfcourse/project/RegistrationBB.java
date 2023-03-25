package com.jsfcourse.project;

import com.jsf.dao.UserDAO;
import com.jsf.entities.User;

import java.util.List;
import java.util.ResourceBundle;
import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
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
public class RegistrationBB {
	
	private static final String PAGE_CALENDAR = "/pages/user/calendar?faces-redirect=true";
	
	private static final String PAGE_ADMIN = "/pages/admin/adminPanel?faces-redirect=true";
	private static final String PAGE_USER_EDIT = "/pages/admin/adminPanel_UsersEdit?faces-redirect=true";
	
	private static final String PAGE_LOGIN = "/pages/public/login?faces-redirect=true";
	private static final String PAGE_REGISTRY = "/pages/public/registration?faces-redirect=true";
	private static final String PAGE_CENNIK = "/pages/public/login?faces-redirect=true";
	private static final String PAGE_MAIN = "/pages/public/index?faces-redirect=true";
	
	private static final String PAGE_STAY_AT_THE_SAME = null;
	
	private User user = new User();
	//private Role role = new Role();


	@Inject
	Flash flash;

	@EJB
	UserDAO userDAO;
	
	@Inject
	@ManagedProperty("#{txtRegistry}")
	private ResourceBundle txtRegistry;
	
	public User getUser() {
		return user;
	}

	public String newPerson(){
		try {
			/*role.setID_role(1);
			user.setRoles(role);*/
			userDAO.create(user);

		} catch (Exception e) {
			e.printStackTrace();
			//context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "wystąpił błąd podczas zapisu", null));
			return PAGE_LOGIN;
		}
		
		return PAGE_MAIN;
	}
	
}
