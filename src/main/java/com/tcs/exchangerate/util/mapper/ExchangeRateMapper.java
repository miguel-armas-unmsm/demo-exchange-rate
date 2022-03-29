package com.tcs.exchangerate.util.mapper;

import com.tcs.exchangerate.model.dto.*;
import com.tcs.exchangerate.model.entity.ExchangeRate;
import io.vavr.Function3;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.function.BiFunction;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ExchangeRateMapper {

  @Mapping(target = "sourceCurrency", expression = "java(String.valueOf(exchangeRate.getSourceCurrency()).toUpperCase())")
  @Mapping(target = "targetCurrency", expression = "java(String.valueOf(exchangeRate.getTargetCurrency()).toUpperCase())")
  @Mapping(target = "exchangeRate", expression = "java(calculateExchangeRate.apply(sourcePrice, targetPrice))")
  @Mapping(target = "amountWithExchangeRate", expression = "java(calculateAmountWithExchangeRate.apply(exchangeRate.getAmount(), sourcePrice, targetPrice))")
  ConversionResponse fromRequestToResponse(ConversionRequest exchangeRate, Double sourcePrice, Double targetPrice);

  ExchangeRateDto fromEntityToDto(ExchangeRate exchangeRate);

  @Mapping(target = "type", expression = "java(String.valueOf(exchangeRate.getType()).toUpperCase())")
  ExchangeRate fromRequestToEntity(ExchangeRateRequest exchangeRate);

  ExchangeRateResponse fromEntityToResponse(ExchangeRate exchangeRate);

  BiFunction<Double, Double, Double> calculateExchangeRate = (sourcePrice, targetPrice) -> {
    double actualSourcePrice = Double.parseDouble(sourcePrice.toString());
    double actualTargetPrice = Double.parseDouble(targetPrice.toString());
    double result = actualSourcePrice / actualTargetPrice;
    return (double) Math.round(result * 100) / 100;
  };

  Function3<Double, Double, Double, Double> calculateAmountWithExchangeRate =
      (amount, sourcePrice, targetPrice) -> {
        double actualAmount = Double.parseDouble(amount.toString());
        double actualSourcePrice = Double.parseDouble(sourcePrice.toString());
        double actualTargetPrice = Double.parseDouble(targetPrice.toString());
        double result = actualAmount * actualSourcePrice / actualTargetPrice;
        return (double) Math.round(result * 100) / 100;
      };


}
