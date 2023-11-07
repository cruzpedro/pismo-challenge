package com.pismo.transaction.dto.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {

    private Long transactionId;
    private LocalDateTime eventDate;
    private BigDecimal amount;
    private Long accountId;
    private Long operationTypeId;

}
