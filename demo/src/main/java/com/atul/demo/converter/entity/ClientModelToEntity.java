package com.atul.demo.converter.entity;

import org.springframework.stereotype.Component;

import com.atul.demo.entity.Client;
import com.atul.demo.model.request.ClientAddRequestModel;
import com.atul.demo.model.request.ProjectAddRequestModel;

@Component
public class ClientModelToEntity {

	public Client add(ClientAddRequestModel clientAddRequestModel) {
		Client client = new Client();
		client.setImg(clientAddRequestModel.getImg());
		client.setName(clientAddRequestModel.getName());
		client.setDescription(clientAddRequestModel.getDescription());
		client.setDesignation(clientAddRequestModel.getDesignation());
		return client;
	}
}
