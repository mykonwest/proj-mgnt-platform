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
        return repository.findById(id).orElse(null);
    }

    public Client createClient(Client client) {
        return repository.save(client);
    }

    public Client updateClient(long id, Client client) {
        repository.findById(id).ifPresentOrElse(
                c-> {
                    c.setName(client.getName());
                    repository.save(c);
                }
        , () -> System.out.println("Client not found"));
        return repository.findById(id).orElse(null);
    }

    public void deleteClient(long id) {
        repository.deleteById(id);
    }
}
