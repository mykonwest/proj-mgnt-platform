package org.pmp.service;

import org.pmp.model.Client;
import org.pmp.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    ClientRepository repository;


    public List<Client> getAllClients() {
        return repository.findAll();
    }

    public Client getClientById(long id) {
        return repository.getReferenceById(id);
    }
}
