package com.tcs.exchangerate.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ConversionResponse {

    private Double amount;
    private String sourceCurrency;
    private String targetCurrency;
    private Double exchangeRate;
    private Double amountWithExchangeRate;
    private String author = "Miguel Rodrigo Armas Abt";
}
