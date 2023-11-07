package com.pismo.transaction.resource.transaction;

import com.pismo.transaction.dto.transaction.TransactionDTO;
import com.pismo.transaction.resource.mapper.TransactionMapperDTO;
import com.pismo.transaction.resource.transaction.request.TransactionCreateRequest;
import com.pismo.transaction.resource.transaction.response.TransactionResponse;
import com.pismo.transaction.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionResource {

    private final TransactionService transactionService;
    private final TransactionMapperDTO transactionMapperDTO;

    @PostMapping
    public TransactionResponse create(@Valid @RequestBody TransactionCreateRequest request) {
        log.info("C={}, method=create, request={}", getClass().getSimpleName(), request);

        final TransactionDTO transactionDTO = transactionService.create(transactionMapperDTO.toDTO(request));
        return transactionMapperDTO.toResponse(transactionDTO);
    }

}
