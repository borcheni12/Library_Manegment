package com.example.library.controller;

import com.example.library.model.Client;
import com.example.library.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/members")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public String showMembers(Model model,
                              @RequestParam(value = "success", required = false) String success) {
        model.addAttribute("clients", clientService.getAllClients());
        model.addAttribute("newClient", new Client());
        model.addAttribute("success", success);
        return "members";
    }

    @PostMapping("/add")
    public String addMember(@ModelAttribute("newClient") Client client) {
        clientService.saveClient(client);
        return "redirect:/members?success=added";
    }

    @PostMapping("/delete/{id}")
    public String deleteMember(@PathVariable Long id) {
        clientService.deleteClient(id);
        return "redirect:/members?success=deleted";
    }
}
