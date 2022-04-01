package com.tcs.common.exception;

import com.tcs.common.exception.impl.model.ApiException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ExceptionCatalog {

  ERROR0001("Los datos proporcionados para realizar la conversión no son válidos.", HttpStatus.BAD_REQUEST),
  ERROR0002("El tipo de cambio solicitado ya se encuentra registrado.", HttpStatus.BAD_REQUEST),
  ERROR0003("No se encontró el tipo de cambio solicitado.", HttpStatus.NOT_FOUND),
  ERROR0004("No se encontró el tipo de moneda origen.", HttpStatus.BAD_REQUEST),
  ERROR0005("No se encontró el tipo de moneda destino.", HttpStatus.BAD_REQUEST),
  ERROR0006("Los datos proporcionados para la autenticación son incorrectos", HttpStatus.BAD_REQUEST);

  private final String description;
  private final HttpStatus httpStatus;

  public ApiException buildException(Throwable cause) {
    return (cause instanceof ApiException)
        ? (ApiException) cause
        : new ApiException(this.name(), this.getDescription(), this.getHttpStatus(), cause);
  }

  public ApiException buildException() {
    return new ApiException(this.name(), this.getDescription(), this.getHttpStatus(), new Throwable());
  }

}
