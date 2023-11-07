package com.pismo.transaction.service.impl;

import com.pismo.transaction.dto.account.AccountDTO;
import com.pismo.transaction.entity.Account;
import com.pismo.transaction.exception.TransactionException;
import com.pismo.transaction.exception.TransactionNoResultException;
import com.pismo.transaction.mapper.AccountMapper;
import com.pismo.transaction.repository.AccountRepository;
import com.pismo.transaction.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Override
    public AccountDTO create(AccountDTO accountDTO) {
        log.info("C={}, method=create, accountDTO={}", getClass().getSimpleName(), accountDTO);

        try {
            final Account account = accountRepository.save(accountMapper.toEntity(accountDTO));
            return accountMapper.toDTO(account);
        } catch (Exception e) {
            log.error("C={}, method=create, accountDTO={}, error={}, e={}",
                    getClass().getSimpleName(), accountDTO, e.getMessage(), e);

            throw new TransactionException(e);
        }
    }

    @Override
    public AccountDTO findById(long accountId) {
        log.info("C={}, method=findById, accountId={}", getClass().getSimpleName(), accountId);

        try {
            final Account account = accountRepository.findById(accountId)
                    .orElseThrow(() -> new TransactionNoResultException("Account not found"));
            return accountMapper.toDTO(account);
        } catch (Exception e) {
            log.error("C={}, method=findById accountId={}, message={}, e={}",
                    getClass().getSimpleName(), accountId, e.getMessage(), e);
            throw e;
        }
    }
}
