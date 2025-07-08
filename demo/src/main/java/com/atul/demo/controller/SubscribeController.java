package com.atul.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.atul.demo.model.request.ContactSaveRequestModal;
import com.atul.demo.model.request.SubscribeSaveRequestModal;
import com.atul.demo.model.response.ClientResponseModal;
import com.atul.demo.model.response.ContactResponseModal;
import com.atul.demo.model.response.SubscribeResponseModal;
import com.atul.demo.response.RestResponse;
import com.atul.demo.service.ContactService;
import com.atul.demo.service.SubscribeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/subscribe")
@Slf4j
public class SubscribeController {
	
	@Autowired
	private  SubscribeService subscribeService;

	public SubscribeController() {
		log.info("SubscribeController :  Object Created");
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/save", consumes = "application/json", produces = "application/json")
	public RestResponse save(@RequestBody SubscribeSaveRequestModal subscribeSaveRequestModal) throws IOException {
		
		try {
			log.info("Start Saving Subscriber Details with email : {} ", subscribeSaveRequestModal.getEmail());
			SubscribeResponseModal subscribeResponseModal = subscribeService.save(subscribeSaveRequestModal);
			log.info("Subscribe Response Modal Reseived");
			return RestResponse.build().withSuccess("Subscribe Details Saved Successfully", subscribeResponseModal);
		} catch (Exception e) {
			log.error("Failed to save  contact details due to : {}", e.getMessage(), e);
			return RestResponse.build().withError("Failed to save Subscribe details due to : " + e.getMessage());
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/all", produces = "application/json")
	public RestResponse findAll(@RequestParam("page") int page, @RequestParam("size") int size) {
		log.info("Fetching All Subscribe Details");
		try {
			List<SubscribeResponseModal> subscribeResponseModals = subscribeService.all(page, size);
			long totalRecords = subscribeService.count();
			return RestResponse.build().withSuccess("All Subscribe Details Found").withTotalRecords(totalRecords)
					.withPageNumber(page).withPageSize(size).withData(subscribeResponseModals);
		} catch (Exception e) {
			log.error("Failed To Find Subscribe Details due to  : {} ", e.getMessage(), e);
			return RestResponse.build().withError("Failed To Find Subscribe Details due to" + e.getMessage());
		}
	}

}
