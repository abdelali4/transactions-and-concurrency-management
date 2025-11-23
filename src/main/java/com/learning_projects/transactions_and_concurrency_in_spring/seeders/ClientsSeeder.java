package com.learning_projects.transactions_and_concurrency_in_spring.seeders;

import com.learning_projects.transactions_and_concurrency_in_spring.entities.Client;
import com.learning_projects.transactions_and_concurrency_in_spring.repositories.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ClientsSeeder implements CommandLineRunner {
    final ClientRepository clientRepository;

    @Override
    public void run(String... args) throws Exception {
        final Client client1 = new Client();
        client1.setFullName("Client 1");
        client1.setBalance(1000d);
        clientRepository.save(client1);

        final Client client2 = new Client();
        client2.setFullName("Client 2");
        client2.setBalance(1000d);
        clientRepository.save(client2);
    }
}
