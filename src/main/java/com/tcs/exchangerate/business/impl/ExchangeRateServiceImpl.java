package com.tcs.exchangerate.business.impl;

import com.tcs.common.exception.ExceptionCatalog;
import com.tcs.exchangerate.business.ExchangeRateService;
import com.tcs.exchangerate.model.dto.*;
import com.tcs.exchangerate.model.entity.ExchangeRate;
import com.tcs.exchangerate.util.mapper.ExchangeRateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.tcs.exchangerate.repository.ExchangeRepository;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * <br/>Clase Service que define los métodos necesarios para tramitar la lógica de negocio del
 * contexto Exchange rate.<br/>
 *
 * <b>Class</b>: ExchangeRateServiceImpl<br/>
 *
 * @author Miguel Armas Abt <br/>
 *      <u>Developed by</u>: <br/>
 *      <ul>
 *      <li>Miguel Armas Abt</li>
 *      </ul>
 *      <u>Changes</u>:<br/>
 *      <ul>
 *      <li>Marzo, 2022 Creación de Clase.</li>
 *      </ul>
 * @version 1.0
 */
@RequiredArgsConstructor
@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

  private final ExchangeRepository exchangeRepository;
  private final ExchangeRateMapper exchangeRateMapper;

  @Override
  public ConversionResponse convert(ConversionRequest conversionRequest) {

    return Optional.ofNullable(conversionRequest)
        .filter(isValidConversionRequest)
        .map(request -> {
          ExchangeRateDto source = exchangeRateMapper
              .fromEntityToDto(exchangeRepository.findByType(request.getSourceCurrency().toUpperCase())
                  .orElseThrow(ExceptionCatalog.ERROR0004::buildException));

          ExchangeRateDto target = exchangeRateMapper
              .fromEntityToDto(exchangeRepository.findByType(request.getTargetCurrency().toUpperCase())
                  .orElseThrow(ExceptionCatalog.ERROR0005::buildException));

          return exchangeRateMapper.fromRequestToResponse(request, source.getPrice(), target.getPrice());
        })
        .orElseThrow(ExceptionCatalog.ERROR0001::buildException);
  }

  @Override
  public Long save(ExchangeRateRequest exchangeRateRequest) {
    return Optional.ofNullable(exchangeRateRequest)
        .filter(isValidExchangeRateRequest)
        .filter(request -> !isAlreadySaved.test(request))
        .map(request -> exchangeRepository.save(exchangeRateMapper.fromRequestToEntity(exchangeRateRequest)).getId())
        .orElseThrow(ExceptionCatalog.ERROR0002::buildException);
  }

  @Override
  public void update(Long id, ExchangeRateRequest exchangeRateRequest) {
    exchangeRepository.findById(id)
        .map(exchangeRateFound -> {
          ExchangeRate exchangeRateEntity = exchangeRateMapper.fromRequestToEntity(exchangeRateRequest);
          exchangeRateEntity.setId(id);
          exchangeRepository.save(exchangeRateEntity);
          return exchangeRateFound;
        })
        .orElseThrow(ExceptionCatalog.ERROR0003::buildException);
  }

  @Override
  public List<ExchangeRateResponse> findAll() {
    return exchangeRepository.findAll()
        .stream()
        .map(exchangeRateMapper::fromEntityToResponse)
        .collect(Collectors.toList());
  }

  private final Predicate<ExchangeRateRequest> isValidExchangeRateRequest = currencyRequest -> {
    if(currencyRequest.getType() != null && currencyRequest.getPrice() != null) {
      return !currencyRequest.getType().isBlank() && currencyRequest.getPrice() >= 0;
    } else {
      return false;
    }
  };

  private final Predicate<ConversionRequest> isValidConversionRequest = request -> {
    if(request.getAmount() != null && request.getSourceCurrency() != null && request.getTargetCurrency() != null) {
      return request.getAmount() >= 0
          && !request.getSourceCurrency().isBlank()
          && !request.getTargetCurrency().isBlank();
    } else {
      return false;
    }
  };

  private final Predicate<ExchangeRateRequest> isAlreadySaved = request -> findAll()
      .stream()
      .anyMatch(savedExchangeRate -> savedExchangeRate.getType().equalsIgnoreCase(request.getType()));
}
