package com.atul.demo.converter.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.atul.demo.entity.Contact;
import com.atul.demo.model.response.ContactResponseModal;

@Component
public class ContactEntityToModal {

	public ContactResponseModal byId(Contact contact) {
		ContactResponseModal contactResponseModal = new ContactResponseModal();
		contactResponseModal.setId(contact.getId());
		contactResponseModal.setFullName(contact.getName());
		contactResponseModal.setEmail(contact.getEmail());
		contactResponseModal.setMobileNumber(contact.getPhoneNumber());
		contactResponseModal.setCity(contact.getCity());
		return contactResponseModal;
	}
	
	public List<ContactResponseModal> all(List<Contact> contacts){
		List<ContactResponseModal> contactResponseModals =  new ArrayList<>();
		for (Contact contact : contacts) {
			ContactResponseModal contactResponseModal = new ContactResponseModal();
			contactResponseModal.setId(contact.getId());
			contactResponseModal.setFullName(contact.getName());
			contactResponseModal.setEmail(contact.getEmail());
			contactResponseModal.setMobileNumber(contact.getPhoneNumber());
			contactResponseModal.setCity(contact.getCity());
			contactResponseModals.add(contactResponseModal);
		}
		return contactResponseModals;
	}
}
