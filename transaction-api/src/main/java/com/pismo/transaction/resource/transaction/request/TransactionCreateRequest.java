package com.pismo.transaction.resource.transaction.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionCreateRequest {

    @JsonProperty("account_id")
    private int accountId;

    @JsonProperty("operation_type_id")
    private int operationTypeId;

    private BigDecimal amount;

}
