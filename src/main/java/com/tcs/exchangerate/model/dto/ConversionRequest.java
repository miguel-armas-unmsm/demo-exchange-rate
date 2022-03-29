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
public class ConversionRequest {

    @NotBlank(message = "El monto no deber ser vacío, no debe tener espacios en blanco y no debe ser nulo")
    private Double amount;

    @NotBlank(message = "La monedaOrigen no deber ser vacío, no debe tener espacios en blanco y no debe ser nulo")
    private String sourceCurrency;

    @NotBlank(message = "la monedaDestino no deber ser vacío, no debe tener espacios en blanco y no debe ser nulo")
    private String targetCurrency;
}
