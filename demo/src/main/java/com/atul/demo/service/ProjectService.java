package com.atul.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.atul.demo.converter.entity.ProjectModelToEntity;
import com.atul.demo.converter.model.ProjectEntityToModel;
import com.atul.demo.entity.Project;
import com.atul.demo.model.request.ProjectAddRequestModel;
import com.atul.demo.model.response.ProjectResponseModal;
import com.atul.demo.repo.ProjectRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProjectService {
	
	@Autowired
	private  ProjectEntityToModel projectEntityToModel;
	
	@Autowired
	private  ProjectModelToEntity projectModelToEntity;
	
	@Autowired
	private ProjectRepo projectRepo;
	
	public ProjectService() {
		log.debug("Project Service : Object Created");
	}
	
	public ProjectResponseModal add(ProjectAddRequestModel projectAddRequestModel) {
		Project project = projectModelToEntity.add(projectAddRequestModel);
		Project savedProject = projectRepo.save(project);
		return projectEntityToModel.byId(savedProject);
		
	}
	public List<ProjectResponseModal> all (int page,int size) {
		List<Project> projects = projectRepo.findAllProject(PageRequest.of(page, size));
		return projectEntityToModel.all(projects);
	}
	public long count () {
		return projectRepo.countAllProject();
	}
	
}
