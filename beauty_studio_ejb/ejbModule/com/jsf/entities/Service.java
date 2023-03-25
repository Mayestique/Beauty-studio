package com.jsf.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the service database table.
 * 
 */
@Entity
@NamedQuery(name="Service.findAll", query="SELECT s FROM Service s")
public class Service implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int ID_service;

	private String name;

	private String type;

	//bi-directional many-to-one association to Calendar
	@OneToMany(mappedBy="service")
	private List<Calendar> calendars;

	//bi-directional many-to-one association to PriceList
	@OneToMany(mappedBy="service")
	private List<PriceList> priceLists;

	public Service() {
	}

	public int getID_service() {
		return this.ID_service;
	}

	public void setID_service(int ID_service) {
		this.ID_service = ID_service;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Calendar> getCalendars() {
		return this.calendars;
	}

	public void setCalendars(List<Calendar> calendars) {
		this.calendars = calendars;
	}

	public Calendar addCalendar(Calendar calendar) {
		getCalendars().add(calendar);
		calendar.setService(this);

		return calendar;
	}

	public Calendar removeCalendar(Calendar calendar) {
		getCalendars().remove(calendar);
		calendar.setService(null);

		return calendar;
	}

	public List<PriceList> getPriceLists() {
		return this.priceLists;
	}

	public void setPriceLists(List<PriceList> priceLists) {
		this.priceLists = priceLists;
	}

	public PriceList addPriceList(PriceList priceList) {
		getPriceLists().add(priceList);
		priceList.setService(this);

		return priceList;
	}

	public PriceList removePriceList(PriceList priceList) {
		getPriceLists().remove(priceList);
		priceList.setService(null);

		return priceList;
	}

}