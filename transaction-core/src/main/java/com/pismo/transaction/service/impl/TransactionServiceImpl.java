package com.pismo.transaction.service.impl;

import com.pismo.transaction.dto.transaction.TransactionDTO;
import com.pismo.transaction.entity.Transaction;
import com.pismo.transaction.exception.TransactionException;
import com.pismo.transaction.mapper.TransactionMapper;
import com.pismo.transaction.repository.TransactionRepository;
import com.pismo.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    @Override
    public TransactionDTO create(TransactionDTO transactionDTO) {
        log.info("C={}, method=create, transactionDTO={}", getClass().getSimpleName(), transactionDTO);

        try {
            final Transaction transaction = transactionRepository.save(transactionMapper.toEntity(transactionDTO));
            return transactionMapper.toDTO(transaction);
        } catch (Exception e) {
            log.error("C={}, method=create, transactionDTO={}, error={}, e={}",
                    getClass().getSimpleName(), transactionDTO, e.getMessage(), e);

            throw new TransactionException(e);
        }
    }
}
