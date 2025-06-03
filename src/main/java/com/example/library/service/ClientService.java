package com.example.library.service;

import com.example.library.model.Client;
import com.example.library.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
    public Client update(long id, Client updateclient)
    {
        Client client = getClientById(id);
        client.setId(id);
        client.setName(updateclient.getName());
        client.setBorrowedBooks(updateclient.getBorrowedBooks());
        return saveClient(client);
    }
}
