package com.learning_projects.transactions_and_concurrency_in_spring.controllers;

import com.learning_projects.transactions_and_concurrency_in_spring.dtos.TransferDTO;
import com.learning_projects.transactions_and_concurrency_in_spring.entities.Client;
import com.learning_projects.transactions_and_concurrency_in_spring.services.ClientService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/clients")
    List<Client> findAll() {
        return clientService.getAllClients();
    }

    @GetMapping("/clients/{id}")
    Client findById(@PathVariable final long id) {
        log.info("Finding client with id {}", id);
        return clientService.findById(id);
    }

    @PostMapping("/transfer")
    void transferMoney(@RequestBody final TransferDTO dto){
        log.info("Transferring {} amount from {} to {}", dto.amount(), dto.toClient(), dto.fromClient());
        clientService.transfer(dto);
    }

}
