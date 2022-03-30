package com.tcs.exchangerate.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConversionRequest {

    @NotNull(message = "amount cannot be null")
    private Double amount;

    @NotNull(message = "sourceCurrency cannot be null")
    private String sourceCurrency;

    @NotNull(message = "targetCurrency cannot be null")
    private String targetCurrency;
}
