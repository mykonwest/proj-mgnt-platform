package org.pmp.controller;

import org.pmp.model.Client;
import org.pmp.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@SuppressWarnings("unused")
public class ClientController {

    @Autowired
    ClientService service;

    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok(service.getAllClients());
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable("id") long id) {
        return ResponseEntity.ok(service.getClientById(id));
    }

    @PostMapping("/client")
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        return ResponseEntity.ok(service.createClient(client));
    }

    @PutMapping("/client/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable("id") long id, @RequestBody Client client) {
        return ResponseEntity.ok(service.updateClient(id, client));
    }

    @DeleteMapping("/client/{id}")
    public ResponseEntity<HttpStatus> deleteClient(@PathVariable("id") long id) {
        service.deleteClient(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}