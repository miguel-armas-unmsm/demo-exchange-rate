package com.tcs.exchangerate.model.dto;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateDto {

    private String type;
    private Double price;
}
