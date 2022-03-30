package com.tcs.authentication.expose.web;

import com.tcs.authentication.business.UserDetailsServiceImpl;
import com.tcs.authentication.model.dto.AuthenticationRequest;
import com.tcs.authentication.model.dto.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tcs/v1")
public class AuthenticationController {

  private final UserDetailsServiceImpl userDetailsService;

  @PostMapping(value = "/auth")
  public ResponseEntity<AuthenticationResponse> createAuthenticationToken(
      @Valid @RequestBody  AuthenticationRequest authenticationRequest) {
    return ResponseEntity.ok(userDetailsService.authenticate(authenticationRequest));
  }

}
