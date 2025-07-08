package com.atul.demo.converter.entity;

import org.springframework.stereotype.Component;

import com.atul.demo.entity.Contact;
import com.atul.demo.model.request.ContactSaveRequestModal;

@Component
public class ContactModalToEntity {

	public Contact save(ContactSaveRequestModal contactSaveRequestModal) {
		Contact contact = new Contact();
		contact.setName(contactSaveRequestModal.getFullName());
		contact.setEmail(contactSaveRequestModal.getEmail());
		contact.setPhoneNumber(contactSaveRequestModal.getMobileNumber());
		contact.setCity(contactSaveRequestModal.getCity());
		return contact;
	}
}
