package com.pismo.transaction.service;

import com.pismo.transaction.dto.account.AccountDTO;
import com.pismo.transaction.dto.transaction.TransactionDTO;

import java.math.BigDecimal;

public interface TransactionService {

    TransactionDTO create(TransactionDTO transactionDTO);
    BigDecimal validateAccountCreditLimit(AccountDTO accountDTO, TransactionDTO transactionDTO);

}
