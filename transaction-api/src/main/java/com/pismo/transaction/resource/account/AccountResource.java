package com.pismo.transaction.resource.account;

import com.pismo.transaction.dto.account.AccountDTO;
import com.pismo.transaction.resource.account.request.AccountCreateRequest;
import com.pismo.transaction.resource.account.response.AccountResponse;
import com.pismo.transaction.resource.mapper.AccountMapperDTO;
import com.pismo.transaction.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountResource {

    private final AccountMapperDTO accountMapperDTO;
    private final AccountService accountService;

    @PostMapping
    public AccountResponse create(@Valid @RequestBody AccountCreateRequest request) {
        log.info("C={}, method=create, request={}", getClass().getSimpleName(), request);

        final AccountDTO accountDTO = accountService.create(accountMapperDTO.toDTO(request));
        return accountMapperDTO.toResponse(accountDTO);
    }

    @GetMapping("/{accountId}")
    public AccountResponse findById(@PathVariable("accountId") long accountId) {
        log.info("C={}, method=findById, accountId={}", getClass().getSimpleName(), accountId);

        final AccountDTO accountDTO = accountService.findById(accountId);
        return accountMapperDTO.toResponse(accountDTO);
    }

}
