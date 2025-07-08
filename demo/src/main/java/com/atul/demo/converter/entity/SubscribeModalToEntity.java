package com.atul.demo.converter.entity;

import org.springframework.stereotype.Component;

import com.atul.demo.entity.Contact;
import com.atul.demo.entity.Subscribe;
import com.atul.demo.model.request.ContactSaveRequestModal;
import com.atul.demo.model.request.SubscribeSaveRequestModal;

@Component
public class SubscribeModalToEntity {

	public Subscribe save(SubscribeSaveRequestModal subscribeSaveRequestModal) {
		Subscribe subscribe = new Subscribe();
		subscribe.setEmail(subscribeSaveRequestModal.getEmail());
		return subscribe;
	}
}
