package com.pismo.transaction.resource.mapper;

import com.pismo.transaction.dto.account.AccountDTO;
import com.pismo.transaction.resource.account.request.AccountCreateRequest;
import com.pismo.transaction.resource.account.response.AccountResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapperDTO {

    @Mapping(source = "documentNumber", target = "documentNumber")
    @Mapping(target = "accountId", ignore = true)
    AccountDTO toDTO(AccountCreateRequest request);
    AccountResponse toResponse(AccountDTO dto);

}
