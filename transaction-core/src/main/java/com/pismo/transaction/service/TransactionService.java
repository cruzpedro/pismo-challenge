package com.pismo.transaction.service;

import com.pismo.transaction.dto.transaction.TransactionDTO;

public interface TransactionService {

    TransactionDTO create(TransactionDTO transactionDTO);

}
