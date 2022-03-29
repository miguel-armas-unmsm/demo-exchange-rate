package com.tcs.common.exception.impl.model;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

/**
 * <br/>Clase Dto que define el modelo de objeto para transmitir información
 * de la excepción personalizada.<br/>
 *
 * <b>Class</b>: ApiExceptionDto<br/>
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
@Getter
@Builder
public class ApiExceptionResponse implements Serializable {

  private String systemCode;

  private String description;

  private String httpStatus;

  /**
   * Método builder que construye un objeto ApiExceptionDto base, con los campos obligatorios.
   *
   * @param systemCode código de error definido para el sistema.
   * @param description descripción del error.
   * @param httpStatus código de estado HTTP.
   * @return ApiExceptionDtoBuilder
   */
  public static ApiExceptionResponseBuilder builder(String systemCode, String description, String httpStatus) {
    return new ApiExceptionResponseBuilder()
        .systemCode(systemCode)
        .description(description)
        .httpStatus(httpStatus);
  }

}
