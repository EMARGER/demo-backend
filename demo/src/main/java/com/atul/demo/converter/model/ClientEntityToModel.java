package com.atul.demo.converter.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.atul.demo.entity.Client;
import com.atul.demo.entity.Project;
import com.atul.demo.model.response.ClientResponseModal;
import com.atul.demo.model.response.ProjectResponseModal;

@Component
public class ClientEntityToModel {
	public ClientResponseModal byId(Client client) {
		ClientResponseModal clientResponseModal = new ClientResponseModal();
		clientResponseModal.setId(client.getId());
		clientResponseModal.setImg(client.getImg());
		clientResponseModal.setName(client.getName());
		clientResponseModal.setDescription(client.getDescription());
		clientResponseModal.setDesignation(client.getDesignation());
		return clientResponseModal;
	}
	
	public List<ClientResponseModal> all(List<Client> clients){
		List<ClientResponseModal> clientResponseModals = new ArrayList<>();
		for (Client client : clients) {
			ClientResponseModal clientResponseModal = new ClientResponseModal();
			clientResponseModal.setId(client.getId());
			clientResponseModal.setImg(client.getImg());
			clientResponseModal.setName(client.getName());
			clientResponseModal.setDescription(client.getDescription());
			clientResponseModal.setDesignation(client.getDesignation());
			clientResponseModals.add(clientResponseModal);
		}
		return clientResponseModals;
	}
	
}
