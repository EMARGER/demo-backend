package com.atul.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.atul.demo.converter.entity.ContactModalToEntity;
import com.atul.demo.converter.model.ContactEntityToModal;
import com.atul.demo.entity.Contact;
import com.atul.demo.model.request.ContactSaveRequestModal;
import com.atul.demo.model.response.ContactResponseModal;
import com.atul.demo.repo.ContactRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ContactService {
	
	@Autowired
	private ContactEntityToModal contactEntityToModal;
	@Autowired
	private ContactModalToEntity contactModalToEntity;
	@Autowired
	private ContactRepo contactRepo;

	public ContactService() {
		log.debug("ContactService : Object Created");
	}
	
	public ContactResponseModal save(ContactSaveRequestModal contactSaveRequestModal) throws Exception {
		Contact byemail = contactRepo.findByemail(contactSaveRequestModal.getEmail());
		if (byemail != null) {
			throw new Exception("Contact Details Is Already Exist with this Email : " + contactSaveRequestModal.getEmail());
		}
		Contact byPhonenumber = contactRepo.findByPhonenumber(contactSaveRequestModal.getMobileNumber());
		if (byPhonenumber != null) {
			throw new Exception("Contact Details Is Already Exist with this Mobile Number : " + contactSaveRequestModal.getMobileNumber());
		}
		Contact contact = contactModalToEntity.save(contactSaveRequestModal);
		Contact savedContact = contactRepo.save(contact);
		return contactEntityToModal.byId(savedContact);
	}
	
	public List<ContactResponseModal> all (int page,int size) {
		List<Contact> contacts = contactRepo.findAllContact(PageRequest.of(page, size));
		return contactEntityToModal.all(contacts);
	}
	public long count () {
		return contactRepo.countAllContact();
	}
	
	
	
	
}
