package org.pmp.service;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.pmp.model.Client;
import org.pmp.repository.ClientRepository;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;

@SpringBootTest
class ClientServiceTest {

    @Mock
    ClientRepository repository;

    @InjectMocks
    @Resource
    ClientService service;

    Client client;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        client = new Client();
        client.setName("John Doe");
        client.setId(1L);

    }

    @Test
    public void contextLoads() {
        assertThat(service).isNotNull();
    }

    @Test
    void getAllClients() {
        final List<Client> clients = new ArrayList<>();
        clients.add(client);
        when(repository.findAll()).thenReturn(clients);
        assertThat(service.getAllClients()).isEqualTo(clients);
    }

    @Test
    void getClientById() {
        when(repository.findById(1L)).thenReturn(Optional.of(client));
        assertThat(service.getClientById(1L)).isEqualTo(client);
    }

    @Test
    void createClient() {
        when(repository.save(client)).thenReturn(client);
        assertThat(service.createClient(client)).isEqualTo(client);
    }

    @Test
    @SuppressWarnings("unchecked")
    void updateClient() {
        Client updatedClient = new Client();
        updatedClient.setName("Joana Doe");
        updatedClient.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(client), Optional.of(updatedClient));
        assertThat(service.getClientById(1L)).isEqualTo(client);
        when(repository.save(updatedClient)).thenReturn(updatedClient);
        assertThat(service.updateClient(1L, updatedClient)).isEqualTo(updatedClient);
        assertThat(service.getClientById(1L)).isEqualTo(updatedClient);
    }

    @Test
    @SuppressWarnings("unchecked")
    void deleteClient() {
        when(repository.findById(1L)).thenReturn(Optional.of(client), Optional.empty());
        assertThat(service.getClientById(1L)).isEqualTo(client);
        assertDoesNotThrow(() -> service.deleteClient(1L));
        assertThat(service.getClientById(1L)).isNull();
    }
}