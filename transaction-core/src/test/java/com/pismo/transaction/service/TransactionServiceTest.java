package com.pismo.transaction.service;

import com.pismo.transaction.dto.account.AccountDTO;
import com.pismo.transaction.dto.transaction.TransactionDTO;
import com.pismo.transaction.entity.Account;
import com.pismo.transaction.entity.OperationType;
import com.pismo.transaction.entity.Transaction;
import com.pismo.transaction.exception.TransactionNoResultException;
import com.pismo.transaction.exception.TransactionValidationException;
import com.pismo.transaction.mapper.TransactionMapperImpl;
import com.pismo.transaction.repository.TransactionRepository;
import com.pismo.transaction.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountService accountService;

    private TransactionService transactionService;

    @BeforeEach
    void setup() {
        transactionService = new TransactionServiceImpl(transactionRepository, new TransactionMapperImpl(), accountService);
    }

    @Test
    void shouldCreate() {
        final long expectedId = 1L;
        final long expectedAccountId = 2L;
        final long expectedOperationTypeId = 3L;
        final TransactionDTO dto = TransactionDTO.builder()
                .amount(BigDecimal.TEN)
                .accountId(2L)
                .operationTypeId(1L)
                .build();

        when(transactionRepository.save(any()))
                .thenReturn(mockTransaction());

        when(accountService.findById(expectedAccountId))
                .thenReturn(mockAccount());

        doNothing()
            .when(accountService)
            .update(any());

        final TransactionDTO transactionDTO = transactionService.create(dto);
        Assertions.assertEquals(expectedId, transactionDTO.getTransactionId());
        Assertions.assertEquals(expectedAccountId, transactionDTO.getAccountId());
        Assertions.assertEquals(expectedOperationTypeId, transactionDTO.getOperationTypeId());
        Assertions.assertNotNull(transactionDTO.getEventDate());
    }

    @Test
    void shouldIsValidTransactionOperation() {
        final AccountDTO accountDTO = AccountDTO.builder()
            .availableCreditLimit(BigDecimal.TEN)
            .build();

        final TransactionDTO transactionDTO = TransactionDTO.builder()
            .amount(BigDecimal.TEN)
            .operationTypeId(1L)
            .build();

        final BigDecimal expectedValue = BigDecimal.ZERO;
        final BigDecimal expectedValueAdd = new BigDecimal("20");
        BigDecimal currentCreditLimit = transactionService.validateAccountCreditLimit(accountDTO, transactionDTO);

        Assertions.assertEquals(expectedValue, currentCreditLimit);

        transactionDTO.setOperationTypeId(2L);
        currentCreditLimit = transactionService.validateAccountCreditLimit(accountDTO, transactionDTO);
        Assertions.assertEquals(expectedValue, currentCreditLimit);

        transactionDTO.setOperationTypeId(3L);
        currentCreditLimit = transactionService.validateAccountCreditLimit(accountDTO, transactionDTO);
        Assertions.assertEquals(expectedValue, currentCreditLimit);

        transactionDTO.setOperationTypeId(4L);
        currentCreditLimit = transactionService.validateAccountCreditLimit(accountDTO, transactionDTO);
        Assertions.assertEquals(expectedValueAdd, currentCreditLimit);
    }

    @Test
    void shouldIsInvalidTransactionOperation() {
        final AccountDTO accountDTO = AccountDTO.builder()
            .availableCreditLimit(BigDecimal.TEN)
            .build();

        final TransactionDTO transactionDTO = TransactionDTO.builder()
            .amount(new BigDecimal("11"))
            .operationTypeId(1L)
            .build();

        assertThrows(TransactionValidationException.class,
            () -> transactionService.validateAccountCreditLimit(accountDTO, transactionDTO));
    }

    private Transaction mockTransaction() {
        final Account account = Account.builder()
                .id(2L)
                .document(12345678900L)
                .build();

        final OperationType operationType = OperationType.builder()
                .id(3L)
                .description("")
                .build();

        return Transaction.builder()
                .id(1L)
                .eventDate(LocalDateTime.now())
                .amount(BigDecimal.TEN)
                .account(account)
                .operationType(operationType)
                .build();
    }

    private AccountDTO mockAccount() {
        return AccountDTO.builder()
            .accountId(1L)
            .documentNumber(12345678900L)
            .availableCreditLimit(BigDecimal.TEN)
            .build();
    }

}
