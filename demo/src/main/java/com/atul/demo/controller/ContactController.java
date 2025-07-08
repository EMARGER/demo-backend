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
import com.atul.demo.model.response.ClientResponseModal;
import com.atul.demo.model.response.ContactResponseModal;
import com.atul.demo.response.RestResponse;
import com.atul.demo.service.ContactService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/contact")
@Slf4j
public class ContactController {
	
	@Autowired
	private  ContactService contactService;

	public ContactController() {
		log.info("ContactController :  Object Created");
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/save", consumes = "application/json", produces = "application/json")
	public RestResponse save(@RequestBody ContactSaveRequestModal contactSaveRequestModal) throws IOException {
		
		try {
			log.info("Start Saving Contact Details with email : {} ", contactSaveRequestModal.getEmail());
			ContactResponseModal contactResponseModal = contactService.save(contactSaveRequestModal);
			log.info("Contact Response Modal Reseived");
			return RestResponse.build().withSuccess("Contact Details Saved Successfully", contactResponseModal);
		} catch (Exception e) {
			log.error("Failed to save  contact details due to : {}", e.getMessage(), e);
			return RestResponse.build().withError("Failed to save contact details due to : " + e.getMessage());
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/all", produces = "application/json")
	public RestResponse findAll(@RequestParam("page") int page, @RequestParam("size") int size) {
		log.info("Fetching All Contact Details");
		try {
			List<ContactResponseModal> contactResponseModals = contactService.all(page, size);
			long totalRecords = contactService.count();
			return RestResponse.build().withSuccess("All Contact Details Found").withTotalRecords(totalRecords)
					.withPageNumber(page).withPageSize(size).withData(contactResponseModals);
		} catch (Exception e) {
			log.error("Failed To Find Contact Details due to  : {} ", e.getMessage(), e);
			return RestResponse.build().withError("Failed To Find Contact Details due to" + e.getMessage());
		}
	}

}
