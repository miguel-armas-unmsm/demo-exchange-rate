package com.tcs.exchangerate.expose.web;

import com.tcs.exchangerate.model.dto.ConversionRequest;
import com.tcs.exchangerate.model.dto.ConversionResponse;
import com.tcs.exchangerate.model.dto.ExchangeRateRequest;
import com.tcs.exchangerate.model.dto.ExchangeRateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tcs.exchangerate.business.ExchangeRateService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.function.Function;

/**
 * <br/>Clase Controller que implementa los métodos necesarios para exponer mediante REST
 * los servicios del contexto Exchange rate.<br/>
 *
 * <b>Class</b>: ExchangeRateController<br/>
 *
 * @author Miguel Armas Abt <br/>
 *      <u>Developed by</u>: <br/>
 *      <ul>
 *      <li>Miguel Armas Abt</li>
 *      </ul>
 *      <u>Changes</u>:<br/>
 *      <ul>
 *      <li>Set, 2021 Creación de Clase.</li>
 *      </ul>
 * @version 1.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/tcs/v1/exchange-rates")
public class ExchangeRateController {

  private final ExchangeRateService service;

  @PostMapping(value = "/convert")
  public ResponseEntity<ConversionResponse> convert(@Valid @RequestBody ConversionRequest exchangeRate) {
    return ResponseEntity.ok(service.convert(exchangeRate));
  }

  @GetMapping(produces = "application/json")
  public ResponseEntity<List<ExchangeRateResponse>> findAll() {
    return ResponseEntity.ok(service.findAll());
  }

  @PostMapping
  public ResponseEntity<Void> save(@Valid @RequestBody ExchangeRateRequest exchangeRateRequest) {
    Long id = service.save(exchangeRateRequest);
    return ResponseEntity.created(buildPostUriLocation.apply(id)).build();
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<Void> update(@Valid @RequestBody ExchangeRateRequest currency, @PathVariable("id") Long id) {
    service.update(id, currency);
    return ResponseEntity.created(buildPutUriLocation.apply(id)).build();
  }

  private final static Function<Long, URI> buildPostUriLocation = id ->
      ServletUriComponentsBuilder.fromCurrentRequest()
          .path("/{id}")
          .buildAndExpand(id)
          .toUri();

  private final static Function<Long, URI> buildPutUriLocation = id ->
      ServletUriComponentsBuilder.fromCurrentRequest()
          .buildAndExpand()
          .toUri();

}
