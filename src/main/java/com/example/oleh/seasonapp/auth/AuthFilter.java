package com.example.oleh.seasonapp.auth;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j

public class AuthFilter extends UsernamePasswordAuthenticationFilter {

  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  private final AuthenticationManager authenticationManager;

  public AuthFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response) throws AuthenticationException {

    final String json = extractJsonBody(request);

    final BaseUser appUser = mapToUser(json).get();

    log.info("Authentication attempt from user {} ", appUser.getUsername());

    final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
        appUser.getUsername(), appUser.getPassword());
    return authenticationManager.authenticate(authenticationToken);
  }

  private Optional<BaseUser> mapToUser(String json) {
    Optional<BaseUser> appUser = Optional.empty();
    try {
      appUser = Optional.of(OBJECT_MAPPER.readValue(json, BaseUser.class));
    } catch (JsonProcessingException e) {
      log.info("Can't map json to Java object");
      e.printStackTrace();
    }
    return appUser;
  }

  private String extractJsonBody(HttpServletRequest request) {
    String json = "";
    try {
      json = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
    } catch (IOException e) {
      log.error("Can't extract body from request");
      e.printStackTrace();
    }
    return json;
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain, Authentication authentication) {

    final User user = (User) authentication.getPrincipal();
    final Algorithm algorithm = Algorithm.HMAC256("секрет".getBytes());

    final String token = JWT.create().withSubject(user.getUsername())
        .withExpiresAt(new Date(System.currentTimeMillis() + 5 * 60 * 1000))
        .withIssuer(request.getRequestURL().toString()).sign(algorithm);
    response.setHeader("token", token);
  }
}
