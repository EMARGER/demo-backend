package com.atul.demo.model.response;

import lombok.Data;

@Data
public class ContactResponseModal {

	private int id;
	private String fullName;
	private String email;
	private String mobileNumber;
	private String city;
}
