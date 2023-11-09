package com.pismo.transaction.service;

import com.pismo.transaction.dto.account.AccountDTO;

public interface AccountService {

    AccountDTO create(AccountDTO accountDTO);
    void update(AccountDTO accountDTO);
    AccountDTO findById(long accountId);

}
