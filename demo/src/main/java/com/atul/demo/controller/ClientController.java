package com.atul.demo.controller;

import java.io.File;


import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.atul.demo.model.request.ClientAddRequestModel;
import com.atul.demo.model.response.ClientResponseModal;
import com.atul.demo.response.RestResponse;
import com.atul.demo.service.ClientService;
import com.atul.demo.service.FileService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/client")
@Slf4j
public class ClientController {

	@Autowired
	private ClientService clientService;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private FileService fileService;
	
	public ClientController() {
		log.info("ClientController :  Object Created");
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "application/json")
	public RestResponse addClient(@RequestParam("clientRequestModalJson") String clientRequestModalJson,@RequestParam("img") MultipartFile img) throws IOException {
		String filePath = null;
 
		try {

			ClientAddRequestModel clientAddRequestModel = objectMapper.readValue(clientRequestModalJson,ClientAddRequestModel.class);
			log.info("Start Add Client With Name : {} ", clientAddRequestModel.getName());
			
			if(img != null) {
				log.info("File Name : "+ img.getOriginalFilename() + " and Size : " + img.getSize() );
				
				if(img.getSize() > fileService.getFileMaxSize()) {
					throw new Exception(" File Size Can Not Greater Than : " + fileService.getFileMaxSize() + " bytes");
				}
				
				filePath = fileService.saveFile(img.getBytes(),"client",FilenameUtils.getExtension(img.getOriginalFilename()));
			}
			
			clientAddRequestModel.setImg(filePath);
			
			ClientResponseModal clientResponseModal = clientService.add(clientAddRequestModel);
			log.info("Client Response Modal Reseived");
			return RestResponse.build().withSuccess("Project Add Successfully", clientResponseModal);
		} catch (Exception e) {
			FileUtils.delete(new File(filePath));
			log.error("Failed to save CLient due to : {}", e.getMessage(), e);
			return RestResponse.build().withError("Failed to Add Client due to : " + e.getMessage());
		}
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/all", produces = "application/json")
	public RestResponse findAll(@RequestParam("page") int page, @RequestParam("size") int size) {
		log.info("Fetching All Client Details");
		try {
			List<ClientResponseModal> clientResponseModals = clientService.all(page, size);
			long totalRecords = clientService.count();
			return RestResponse.build().withSuccess("All Clients Found").withTotalRecords(totalRecords)
					.withPageNumber(page).withPageSize(size).withData(clientResponseModals);
		} catch (Exception e) {
			log.error("Failed To Find CLient due to  : {} ", e.getMessage(), e);
			return RestResponse.build().withError("Failed To Find Client due to" + e.getMessage());
		}
	}

	

}
