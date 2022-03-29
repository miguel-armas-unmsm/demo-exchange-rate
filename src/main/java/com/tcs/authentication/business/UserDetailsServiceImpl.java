package com.tcs.authentication.business;

import com.tcs.authentication.model.dto.AuthenticationRequest;
import com.tcs.authentication.model.dto.AuthenticationResponse;
import com.tcs.common.exception.ExceptionCatalog;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.tcs.authentication.util.JwtUtil;
import com.tcs.authentication.repository.UserRepository;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;
  private final JwtUtil jwtTokenUtil;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByUsername(username);
  }

  public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
    return Optional.ofNullable(authenticationRequest)
        .filter(isAuthenticationValid)
        .flatMap(request -> Optional.ofNullable(this.loadUserByUsername(authenticationRequest.getUsername()))
              .filter(userDetail -> isCorrectPassword.test(userDetail, request.getPassword()))
              .map(jwtTokenUtil::generateToken))
        .map(token -> AuthenticationResponse.builder().jwt(token).build())
        .orElseThrow(ExceptionCatalog.ERROR0006::buildException);
  }

  private final Predicate<AuthenticationRequest> isAuthenticationValid = request -> {
    if (request.getUsername() != null && request.getPassword() != null) {
      return !request.getUsername().isBlank() && !request.getPassword().isBlank();
    } else {
      return false;
    }
  };

  private final BiPredicate<UserDetails, String> isCorrectPassword = (userDetail, typedPassword) -> {
    if (userDetail != null) {
      return typedPassword.equalsIgnoreCase(userDetail.getPassword());
    } else {
      return false;
    }
  };

}