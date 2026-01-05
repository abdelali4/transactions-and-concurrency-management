package com.learning_projects.transactions_and_concurrency_in_spring.dtos;

public record TransferDTO(
         long fromClient,
         long toClient,
         Double amount
        ) {
}
