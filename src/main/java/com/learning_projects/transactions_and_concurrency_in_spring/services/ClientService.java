package com.learning_projects.transactions_and_concurrency_in_spring.services;

import com.learning_projects.transactions_and_concurrency_in_spring.dtos.TransferDTO;
import com.learning_projects.transactions_and_concurrency_in_spring.entities.Client;
import com.learning_projects.transactions_and_concurrency_in_spring.repositories.ClientRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
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

    @SneakyThrows
    public void transfer(TransferDTO transferDTO)  {
        final Client sender = clientRepository.findByIdAndLock(transferDTO.fromClient()).orElseThrow(() -> new RuntimeException("sender not found"));
        final Client receiver = clientRepository.findByIdAndLock(transferDTO.toClient()).orElseThrow(() -> new RuntimeException("sender not found"));
        log.info("receiver and sender are found");
        Thread.sleep(8*1000);
        if(sender.getBalance() < transferDTO.amount()){
            log.warn(transferDTO.fromClient() + " does not have enough balance");
            throw new RuntimeException("Sender's balance is less than transfer's amount");
        }

        sender.setBalance(sender.getBalance() - transferDTO.amount());
        receiver.setBalance(receiver.getBalance() + transferDTO.amount());
        log.info("Saving sender's balance is " + sender.getBalance());
        log.info("Saving receivers's balance is " + receiver.getBalance());

        clientRepository.save(sender);
        clientRepository.save(receiver);
    }


}
