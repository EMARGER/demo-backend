package com.atul.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.atul.demo.converter.entity.ClientModelToEntity;
import com.atul.demo.converter.entity.ProjectModelToEntity;
import com.atul.demo.converter.model.ClientEntityToModel;
import com.atul.demo.converter.model.ProjectEntityToModel;
import com.atul.demo.entity.Client;
import com.atul.demo.entity.Project;
import com.atul.demo.model.request.ClientAddRequestModel;
import com.atul.demo.model.request.ProjectAddRequestModel;
import com.atul.demo.model.response.ClientResponseModal;
import com.atul.demo.model.response.ProjectResponseModal;
import com.atul.demo.repo.ClientRepo;
import com.atul.demo.repo.ProjectRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClientService {
	
	@Autowired
	private  ClientEntityToModel clientEntityToModel;
	
	@Autowired
	private  ClientModelToEntity clientModelToEntity;
	
	@Autowired
	private ClientRepo clientRepo;
	
	public ClientService() {
		log.debug("ClientService : Object Created");
	}
	
	public ClientResponseModal add(ClientAddRequestModel clientAddRequestModel) {
		Client client = clientModelToEntity.add(clientAddRequestModel);
		Client savedClient = clientRepo.save(client);
		return clientEntityToModel.byId(savedClient);
		
	}
	public List<ClientResponseModal> all (int page,int size) {
		List<Client> clients = clientRepo.findAllClient(PageRequest.of(page, size));
		return clientEntityToModel.all(clients);
	}
	public long count () {
		return clientRepo.countAllClient();
	}
	
}
