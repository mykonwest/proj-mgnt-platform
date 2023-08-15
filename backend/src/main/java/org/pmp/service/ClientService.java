package org.pmp.service;

import org.pmp.model.Client;
import org.pmp.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("unused")
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

    public Client createClient(Client client) {
        repository.save(client);
        return client;
    }

    public Client updateClient(long id, Client client) {
        client.setId(id);
        repository.save(client);
        return client;
    }

    public void deleteClient(long id) {
        repository.deleteById(id);
    }
}
