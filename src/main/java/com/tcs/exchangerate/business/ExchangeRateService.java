package com.tcs.exchangerate.business;

import com.tcs.exchangerate.model.dto.ConversionResponse;
import com.tcs.exchangerate.model.dto.ExchangeRateRequest;
import com.tcs.exchangerate.model.dto.ExchangeRateResponse;
import com.tcs.exchangerate.model.dto.ConversionRequest;

import java.util.List;

/**
 * <br/>Interface Service que define los métodos necesarios para tramitar la lógica de negocio del
 * contexto Exchange rate.<br/>
 *
 * <b>Interface</b>: ExchangeRateService<br/>
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
public interface ExchangeRateService {

  ConversionResponse convert(ConversionRequest exchangeRate);

  Long save(ExchangeRateRequest currency);

  void update(Long id, ExchangeRateRequest currency);

  List<ExchangeRateResponse> findAll();
}
