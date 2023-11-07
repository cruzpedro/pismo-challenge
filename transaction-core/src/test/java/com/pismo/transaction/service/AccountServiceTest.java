package com.pismo.transaction.service;

import com.pismo.transaction.dto.account.AccountDTO;
import com.pismo.transaction.entity.Account;
import com.pismo.transaction.exception.TransactionNoResultException;
import com.pismo.transaction.mapper.AccountMapperImpl;
import com.pismo.transaction.repository.AccountRepository;
import com.pismo.transaction.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    private AccountService accountService;

    @BeforeEach
    void setup() {
        accountService = new AccountServiceImpl(accountRepository, new AccountMapperImpl());
    }

    @Test
    void shouldCreate() {
        final long expectedId = 1L;
        final long documentNumber = 12345678900L;
        final AccountDTO dto = AccountDTO.builder()
                .documentNumber(documentNumber)
                .build();

        when(accountRepository.save(any()))
                .thenReturn(mockAccount());

        final AccountDTO accountDTO = accountService.create(dto);
        assertEquals(documentNumber, accountDTO.getDocumentNumber());
        assertEquals(expectedId, accountDTO.getAccountId());
    }

    @Test
    void shouldListById() {
        final long accountId = 1L;
        final long expectedDocumentNumber = 12345678900L;

        when(accountRepository.findById(accountId))
                .thenReturn(Optional.of(mockAccount()));

        final AccountDTO accountDTO = accountService.findById(accountId);
        assertEquals(accountId, accountDTO.getAccountId());
        assertEquals(expectedDocumentNumber, accountDTO.getDocumentNumber());
    }

    @Test
    void shouldNotFoundWhenListById() {
        final long accountId = 1L;

        when(accountRepository.findById(accountId))
                .thenReturn(Optional.empty());

        assertThrows(TransactionNoResultException.class, () -> accountService.findById(accountId));
    }

    private Account mockAccount() {
        return Account.builder()
                .id(1L)
                .document(12345678900L)
                .build();
    }

}
