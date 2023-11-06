package com.pismo.transaction.resource.account;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountResource {

    @PostMapping
    public ResponseEntity create() {
        log.info("C={}, method=create", getClass().getSimpleName());

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{accountId}")
    public ResponseEntity findById(@PathVariable("accountId") int accountId) {
        log.info("C={}, method=findById, accountId={}", getClass().getSimpleName(), accountId);

        return ResponseEntity.ok().build();
    }

}
