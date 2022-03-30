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
public class ExchangeRateRequest {

  @NotNull(message = "type cannot be null")
  private String type;

  @NotNull(message = "price cannot be null")
  private Double price;
}
