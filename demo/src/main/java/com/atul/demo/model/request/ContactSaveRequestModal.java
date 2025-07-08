package com.atul.demo.model.request;

import lombok.Data;

@Data
public class ContactSaveRequestModal {
	
	private String fullName;
	private String email;
	private String mobileNumber;
	private String city;
	
}
