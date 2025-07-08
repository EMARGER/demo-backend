package com.atul.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.atul.demo.converter.entity.ContactModalToEntity;
import com.atul.demo.converter.entity.SubscribeModalToEntity;
import com.atul.demo.converter.model.ContactEntityToModal;
import com.atul.demo.converter.model.SubscribeEntityToModal;
import com.atul.demo.entity.Contact;
import com.atul.demo.entity.Subscribe;
import com.atul.demo.model.request.ContactSaveRequestModal;
import com.atul.demo.model.request.SubscribeSaveRequestModal;
import com.atul.demo.model.response.ContactResponseModal;
import com.atul.demo.model.response.SubscribeResponseModal;
import com.atul.demo.repo.ContactRepo;
import com.atul.demo.repo.SubscribeRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SubscribeService {
	
	@Autowired
	private SubscribeEntityToModal subscribeEntityToModal;
	@Autowired
	private SubscribeModalToEntity subscribeModalToEntity;
	@Autowired
	private SubscribeRepo subscribeRepo;

	public SubscribeService() {
		log.debug("SubscribeService : Object Created");
	}
	
	public SubscribeResponseModal save(SubscribeSaveRequestModal subscribeSaveRequestModal) throws Exception {
		Subscribe byemail = subscribeRepo.findByemail(subscribeSaveRequestModal.getEmail());
		if (byemail != null) {
			throw new Exception("Contact Details Is Already Exist with this Email : " + subscribeSaveRequestModal.getEmail());
		}
		Subscribe subscribe = subscribeModalToEntity.save(subscribeSaveRequestModal);
		Subscribe savedSubscribe = subscribeRepo.save(subscribe);
		return subscribeEntityToModal.byId(savedSubscribe);
	}
	
	public List<SubscribeResponseModal> all (int page,int size) {
		List<Subscribe> subscribes = subscribeRepo.findAllSubscribe(PageRequest.of(page, size));
		return subscribeEntityToModal.all(subscribes);
	}
	public long count () {
		return subscribeRepo.countAllSubscribe();
	}
	
	
	
	
}
