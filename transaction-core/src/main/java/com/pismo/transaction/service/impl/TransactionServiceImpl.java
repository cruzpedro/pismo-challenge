package com.pismo.transaction.service.impl;

import com.pismo.transaction.dto.account.AccountDTO;
import com.pismo.transaction.dto.transaction.TransactionDTO;
import com.pismo.transaction.entity.Transaction;
import com.pismo.transaction.exception.TransactionException;
import com.pismo.transaction.exception.TransactionValidationException;
import com.pismo.transaction.mapper.TransactionMapper;
import com.pismo.transaction.repository.TransactionRepository;
import com.pismo.transaction.service.AccountService;
import com.pismo.transaction.service.TransactionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final AccountService accountService;

    @Override
    @Transactional
    public TransactionDTO create(TransactionDTO transactionDTO) {
        log.info("C={}, method=create, transactionDTO={}", getClass().getSimpleName(), transactionDTO);

        try {
            final AccountDTO accountDTO = accountService.findById(transactionDTO.getAccountId());
            accountDTO.setAvailableCreditLimit(validateAccountCreditLimit(accountDTO, transactionDTO));

            final Transaction transaction = transactionRepository.save(transactionMapper.toEntity(transactionDTO));
            accountService.update(accountDTO);

            return transactionMapper.toDTO(transaction);
        } catch (Exception e) {
            log.error("C={}, method=create, transactionDTO={}, error={}, e={}",
                    getClass().getSimpleName(), transactionDTO, e.getMessage(), e);

            throw new TransactionException(e);
        }
    }

    public BigDecimal validateAccountCreditLimit(AccountDTO accountDTO, TransactionDTO transactionDTO) {
        final BigDecimal amount = transactionDTO.getAmount();
        final BigDecimal availableCreditLimit = accountDTO.getAvailableCreditLimit();

        final BigDecimal value = switch (transactionDTO.getOperationTypeId().intValue()) {
            case 1, 2, 3: {
                yield availableCreditLimit.subtract(amount);
            }
            case 4: {
                yield availableCreditLimit.add(amount);
            }
            default:
                throw new IllegalStateException("Unexpected value: " + transactionDTO.getOperationTypeId());
        };

        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new TransactionValidationException("No limit available for this amount!");
        }

        return value;
    }
}
