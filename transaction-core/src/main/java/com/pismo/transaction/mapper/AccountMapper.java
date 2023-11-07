package com.pismo.transaction.mapper;

import com.pismo.transaction.dto.account.AccountDTO;
import com.pismo.transaction.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(source = "accountId", target = "id")
    @Mapping(source = "documentNumber", target = "document")
    Account toEntity(AccountDTO accountDTO);

    @Mapping(source = "id", target = "accountId")
    @Mapping(source = "document", target = "documentNumber")
    AccountDTO toDTO(Account account);

}
