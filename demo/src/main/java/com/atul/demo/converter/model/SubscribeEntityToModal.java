package com.atul.demo.converter.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.atul.demo.entity.Contact;
import com.atul.demo.entity.Subscribe;
import com.atul.demo.model.response.ContactResponseModal;
import com.atul.demo.model.response.SubscribeResponseModal;

@Component
public class SubscribeEntityToModal {

	public SubscribeResponseModal byId(Subscribe subscribe) {
		SubscribeResponseModal subscribeResponseModal = new SubscribeResponseModal();
		subscribeResponseModal.setId(subscribe.getId());
		subscribeResponseModal.setEmail(subscribe.getEmail());
		return subscribeResponseModal;
	}
	
	public List<SubscribeResponseModal> all(List<Subscribe> subscribes){
		List<SubscribeResponseModal> subscribeResponseModals =  new ArrayList<>();
		for (Subscribe subscribe : subscribes) {
			SubscribeResponseModal subscribeResponseModal = new SubscribeResponseModal();
			subscribeResponseModal.setId(subscribe.getId());
			subscribeResponseModal.setEmail(subscribe.getEmail());
			subscribeResponseModals.add(subscribeResponseModal);
		}
		return subscribeResponseModals;
	}
}
