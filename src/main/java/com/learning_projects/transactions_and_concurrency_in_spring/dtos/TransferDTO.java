package com.learning_projects.transactions_and_concurrency_in_spring.dtos;

import java.util.UUID;

public record TransferDTO(
         long fromClient,
         long toClient,
         Double amount
        ) {
}
