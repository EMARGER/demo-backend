package com.atul.demo.entity;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the subscribe database table.
 * 
 */
@Entity
@NamedQuery(name="Subscribe.findAll", query="SELECT s FROM Subscribe s")
public class Subscribe implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String email;

	public Subscribe() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}