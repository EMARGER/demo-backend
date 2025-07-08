package com.atul.demo.converter.entity;

import org.springframework.stereotype.Component;

import com.atul.demo.entity.Project;
import com.atul.demo.model.request.ProjectAddRequestModel;

@Component
public class ProjectModelToEntity {

	public Project add(ProjectAddRequestModel projectAddRequestModel) {
		Project project = new Project();
		project.setImg(projectAddRequestModel.getImg());
		project.setName(projectAddRequestModel.getName());
		project.setDescription(projectAddRequestModel.getDescription());
		return project;
	}
}
