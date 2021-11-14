package com.example.oleh.seasonapp.auth;

import com.auth0.jwt.algorithms.Algorithm;

public class Secret {

  public static final Algorithm ALGORITHM = Algorithm.HMAC256("секрет".getBytes());
}
