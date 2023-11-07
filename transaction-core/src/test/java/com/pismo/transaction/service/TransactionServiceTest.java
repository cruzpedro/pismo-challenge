package com.pismo.transaction.service;

import com.pismo.transaction.dto.transaction.TransactionDTO;
import com.pismo.transaction.entity.Account;
import com.pismo.transaction.entity.OperationType;
import com.pismo.transaction.entity.Transaction;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    private TransactionService transactionService;

    @BeforeEach
    void setup() {
        transactionService = new TransactionServiceImpl(transactionRepository, new TransactionMapperImpl());
    }

    @Test
    void shouldCreate() {
        final long expectedId = 1L;
        final long expectedAccountId = 2L;
        final long expectedOperationTypeId = 3L;
        final TransactionDTO dto = TransactionDTO.builder()
                .amount(BigDecimal.TEN)
                .accountId(1L)
                .operationTypeId(1L)
                .build();

        when(transactionRepository.save(any()))
                .thenReturn(mockTransaction());

        final TransactionDTO transactionDTO = transactionService.create(dto);
        Assertions.assertEquals(expectedId, transactionDTO.getTransactionId());
        Assertions.assertEquals(expectedAccountId, transactionDTO.getAccountId());
        Assertions.assertEquals(expectedOperationTypeId, transactionDTO.getOperationTypeId());
        Assertions.assertNotNull(transactionDTO.getEventDate());
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

}
