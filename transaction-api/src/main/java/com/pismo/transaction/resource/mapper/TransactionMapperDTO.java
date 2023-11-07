package com.pismo.transaction.resource.mapper;

import com.pismo.transaction.dto.transaction.TransactionDTO;
import com.pismo.transaction.resource.transaction.request.TransactionCreateRequest;
import com.pismo.transaction.resource.transaction.response.TransactionResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapperDTO {

    TransactionDTO toDTO(TransactionCreateRequest request);
    TransactionResponse toResponse(TransactionDTO dto);

}
