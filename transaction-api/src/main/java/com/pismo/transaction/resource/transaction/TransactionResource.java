package com.pismo.transaction.resource.transaction;

import com.pismo.transaction.resource.transaction.request.TransactionCreateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionResource {

    @PostMapping
    public ResponseEntity create(@RequestBody TransactionCreateRequest request) {
        log.info("C={}, method=create, request={}", getClass().getSimpleName(), request);

        return ResponseEntity.ok().build();
    }

}
