package com.pismo.transaction.resource.transaction;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionResource {

    @PostMapping
    public ResponseEntity create() {
        log.info("C={}, method=create", getClass().getSimpleName());

        return ResponseEntity.ok().build();
    }

}
