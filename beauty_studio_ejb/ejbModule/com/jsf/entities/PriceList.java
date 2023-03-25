package com.jsf.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the price_list database table.
 * 
 */
@Entity
@Table(name="price_list")
@NamedQuery(name="PriceList.findAll", query="SELECT p FROM PriceList p")
public class PriceList implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int ID_price;

	private double price;

	//bi-directional many-to-one association to Service
	@ManyToOne
	@JoinColumn(name="service_ID")
	private Service service;

	public PriceList() {
	}

	public int getID_price() {
		return this.ID_price;
	}

	public void setID_price(int ID_price) {
		this.ID_price = ID_price;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Service getService() {
		return this.service;
	}

	public void setService(Service service) {
		this.service = service;
	}

}