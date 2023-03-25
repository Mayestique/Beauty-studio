package com.jsf.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the role database table.
 * 
 */
@Entity
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int ID_role;

	private String name;

	//bi-directional many-to-many association to User
	@ManyToMany
	@JoinTable(
		name="`user-role`"
		, joinColumns={
			@JoinColumn(name="role_ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="user_ID")
			}
		)
	private List<User> users;

	public Role() {
	}

	public int getID_role() {
		return this.ID_role;
	}

	public void setID_role(int ID_role) {
		this.ID_role = ID_role;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}