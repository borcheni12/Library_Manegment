package com.example.library.controller.rest;

import com.example.library.model.Client;
import com.example.library.service.ClientService;
import com.example.library.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ObjectOutputStream;
import java.util.List;

@RestController
@RequestMapping("/api/rest-clients")
public class ClientRestController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private ClientRepository clientRepository;

    // Create Client
    @PostMapping("")
    public ResponseEntity<Client> create(@RequestBody Client client) {
        Client createdClient = clientService.saveClient(client);
        return new ResponseEntity<>(createdClient, HttpStatus.CREATED);
    }

    // Get Client by ID
    @GetMapping("/{id}")
    public ResponseEntity<Client> getById(@PathVariable("id") Long id) {
        Client client = clientService.getClientById(id);
        if (client != null) {
            return new ResponseEntity<>(client, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get all clients
    @GetMapping("/all")
    public ResponseEntity<List<Client>> getAll() {
        List<Client> clients = clientService.getAllClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }



    // Delete Client
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        clientService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Client> updateclient(@PathVariable("id") Long id,@RequestBody Client client) {
        Client clientupdate = clientService.update(id,client);


        return new ResponseEntity<>(client, HttpStatus.ACCEPTED);
    }
}
