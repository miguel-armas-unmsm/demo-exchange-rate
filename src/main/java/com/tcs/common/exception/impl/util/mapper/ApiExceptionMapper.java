package com.tcs.common.exception.impl.util.mapper;

import com.tcs.common.exception.impl.model.ApiException;
import com.tcs.common.exception.impl.model.ApiExceptionResponse;
import java.util.function.Function;

/**
 * <br/>Clase Mapper que mueve la información de la excepción entre objetos.<br/>
 *
 * <b>Class</b>: ApiExceptionMapper<br/>
 *
 * @author Miguel Armas Abt <br/>
 *      <u>Developed by</u>: <br/>
 *      <ul>
 *      <li>Miguel Armas Abt</li>
 *      </ul>
 *      <u>Changes</u>:<br/>
 *      <ul>
 *      <li>Oct, 2021 Creación de Clase.</li>
 *      </ul>
 * @version 1.0
 */
public class ApiExceptionMapper {

  public static Function<ApiException, ApiExceptionResponse> buildApiExceptionResponse = ex ->
      ApiExceptionResponse
          .builder(
              ex.getSystemCode(),
              ex.getDescription(),
              ex.getHttpStatus().toString())
          .build();

}
