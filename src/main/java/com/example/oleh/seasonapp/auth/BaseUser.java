package com.example.oleh.seasonapp.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseUser {

  private String username;
  private String password;
}
