package com.pismo.transaction.resource.account.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse implements Serializable {

    @JsonProperty("account_id")
    private Long accountId;

    @JsonProperty("document_number")
    private Long documentNumber;

    @JsonProperty("num_available_credit_limit")
    private Long availableCreditLimit;

}
