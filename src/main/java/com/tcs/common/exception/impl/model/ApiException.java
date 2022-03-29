package com.tcs.common.exception.impl.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * <br/>Clase modelo que define el objeto de excepción personalizada.<br/>
 *
 * <b>Class</b>: ApiException<br/>
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
@Setter
public class ApiException extends RuntimeException {

  private String systemCode;

  private String description;

  private HttpStatus httpStatus;

  private Throwable cause;

  public ApiException(String systemCode, String description, HttpStatus httpStatus, Throwable cause) {
    super(description, cause);
    this.systemCode = systemCode;
    this.description = description;
    this.httpStatus = httpStatus;
  }

}
