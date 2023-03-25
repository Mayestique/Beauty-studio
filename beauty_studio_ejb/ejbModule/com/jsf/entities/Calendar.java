package com.jsf.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the calendar database table.
 * 
 */
@Entity
@NamedQuery(name="Calendar.findAll", query="SELECT c FROM Calendar c")
public class Calendar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int ID_calendar;

	@Temporal(TemporalType.DATE)
	private Date date;

	private Time time;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user_ID")
	private User user;

	//bi-directional many-to-one association to Service
	@ManyToOne
	@JoinColumn(name="service_ID")
	private Service service;

	//bi-directional many-to-one association to Status
	@ManyToOne
	@JoinColumn(name="status_ID")
	private Status status;

	public Calendar() {
	}

	public Calendar(Time time2) {
		// TODO Auto-generated constructor stub
	}

	public int getID_calendar() {
		return this.ID_calendar;
	}

	public void setID_calendar(int ID_calendar) {
		this.ID_calendar = ID_calendar;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getTime() {
		return this.time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Service getService() {
		return this.service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setTime(Calendar time2) {
		// TODO Auto-generated method stub
		
	}

}