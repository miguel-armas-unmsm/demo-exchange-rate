package com.tcs.exchangerate.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExchangeRateRequest {

  @NotBlank(message = "El tipo no deber ser vacío, no debe tener espacios en blanco y no debe ser nulo")
  private String type;

  @NotBlank(message = "El valor no deber ser vacío, no debe tener espacios en blanco y no debe ser nulo")
  private Double price;
}
