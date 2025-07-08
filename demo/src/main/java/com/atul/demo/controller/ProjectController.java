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
import com.atul.demo.model.request.ProjectAddRequestModel;
import com.atul.demo.model.response.ProjectResponseModal;
import com.atul.demo.response.RestResponse;
import com.atul.demo.service.FileService;
import com.atul.demo.service.ProjectService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/project")
@Slf4j
public class ProjectController {

	@Autowired
	private ProjectService projectService;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private FileService fileService;
	
	public ProjectController() {
		log.info("ProjectController :  Object Created");
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "application/json")
	public RestResponse addProject(@RequestParam("projectRequestModalJson") String projectRequestModalJson,@RequestParam("img") MultipartFile img) throws IOException {
		String filePath = null;
 
		try {

			ProjectAddRequestModel projectAddRequestModel = objectMapper.readValue(projectRequestModalJson,ProjectAddRequestModel.class);
			log.info("Start Add Project With Name : {} ", projectAddRequestModel.getName());
			
			if(img != null) {
				log.info("File Name : "+ img.getOriginalFilename() + " and Size : " + img.getSize() );
				
				if(img.getSize() > fileService.getFileMaxSize()) {
					throw new Exception(" File Size Can Not Greater Than : " + fileService.getFileMaxSize() + " bytes");
				}
				
				filePath = fileService.saveFile(img.getBytes(),"project",FilenameUtils.getExtension(img.getOriginalFilename()));
			}
			
			projectAddRequestModel.setImg(filePath);
			
			ProjectResponseModal projectResponseModal = projectService.add(projectAddRequestModel);
			log.info("Prject Response Modal Reseived");
			return RestResponse.build().withSuccess("Project Add Successfully", projectResponseModal);
		} catch (Exception e) {
			FileUtils.delete(new File(filePath));
			log.error("Failed to save Project due to : {}", e.getMessage(), e);
			return RestResponse.build().withError("Failed to Add Project due to : " + e.getMessage());
		}
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/all", produces = "application/json")
	public RestResponse findAll(@RequestParam("page") int page, @RequestParam("size") int size) {
		log.info("Fetching All Project Details");
		try {
			List<ProjectResponseModal> projectResponseModals = projectService.all(page, size);
			long totalRecords = projectService.count();
			return RestResponse.build().withSuccess("All Project Found").withTotalRecords(totalRecords)
					.withPageNumber(page).withPageSize(size).withData(projectResponseModals);
		} catch (Exception e) {
			log.error("Failed To Find Project due to  : {} ", e.getMessage(), e);
			return RestResponse.build().withError("Failed To Find Project due to" + e.getMessage());
		}
	}

	

}
