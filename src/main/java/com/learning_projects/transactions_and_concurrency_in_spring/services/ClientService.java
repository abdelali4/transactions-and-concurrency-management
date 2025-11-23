package com.learning_projects.transactions_and_concurrency_in_spring.services;

import com.learning_projects.transactions_and_concurrency_in_spring.dtos.TransferDTO;
import com.learning_projects.transactions_and_concurrency_in_spring.entities.Client;
import com.learning_projects.transactions_and_concurrency_in_spring.repositories.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client findById(final long id) {
        return clientRepository.findById(id).orElseThrow(()-> new RuntimeException("client not found"));
    }

    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public void transfer(TransferDTO transferDTO) {
        final Client sender = clientRepository.findById(transferDTO.fromClient()).orElseThrow(() -> new RuntimeException("sender not found"));
        final Client receiver = clientRepository.findById(transferDTO.toClient()).orElseThrow(() -> new RuntimeException("sender not found"));

        if(sender.getBalance() < transferDTO.amount()){
            throw new RuntimeException("Sender's balance is less than transfer's amount");
        }

        sender.setBalance(sender.getBalance() - transferDTO.amount());
        receiver.setBalance(receiver.getBalance() + transferDTO.amount());
        System.out.println("Saving sender's balance is " + sender.getBalance());
        clientRepository.save(sender);
        //System.out.println("Throwing exception");
        //System.out.printf(String.valueOf(1/0));

        System.out.println("Saving receivers's balance is " + receiver.getBalance());
        //clientRepository.save(receiver);
    }


}
