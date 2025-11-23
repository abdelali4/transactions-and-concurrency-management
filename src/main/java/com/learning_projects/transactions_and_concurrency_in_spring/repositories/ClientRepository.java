package com.learning_projects.transactions_and_concurrency_in_spring.repositories;

import com.learning_projects.transactions_and_concurrency_in_spring.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
