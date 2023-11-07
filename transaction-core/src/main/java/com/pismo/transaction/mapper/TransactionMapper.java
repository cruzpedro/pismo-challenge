package com.pismo.transaction.mapper;

import com.pismo.transaction.dto.transaction.TransactionDTO;
import com.pismo.transaction.entity.Account;
import com.pismo.transaction.entity.OperationType;
import com.pismo.transaction.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(source = ".", target = "amount", qualifiedByName = "amount")
    @Mapping(target = "eventDate", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(source = "accountId", target = "account", qualifiedByName = "account")
    @Mapping(source = "operationTypeId", target = "operationType", qualifiedByName = "operationType")
    Transaction toEntity(TransactionDTO transactionDTO);

    @Mapping(source = "id", target = "transactionId")
    @Mapping(source = "account.id", target = "accountId")
    @Mapping(source = "operationType.id", target = "operationTypeId")
    TransactionDTO toDTO(Transaction transaction);

    @Named("amount")
    default BigDecimal amount(TransactionDTO transactionDTO) {
        final BigDecimal amount = transactionDTO.getAmount();
        return switch (transactionDTO.getOperationTypeId().intValue()) {
            case 1, 2, 3: {
                yield amount.compareTo(BigDecimal.ZERO) > 0 ? amount.negate() : amount;
            }
            case 4: {
                yield transactionDTO.getAmount().abs();
            }
            default:
                throw new IllegalStateException("Unexpected value: " + transactionDTO.getOperationTypeId());
        };
    }

    @Named("account")
    default Account account(Long accountId) {
        return Account.builder()
                .id(accountId)
                .build();
    }

    @Named("operationType")
    default OperationType operationType(Long operationTypeId) {
        return OperationType.builder()
                .id(operationTypeId)
                .build();
    }
}
