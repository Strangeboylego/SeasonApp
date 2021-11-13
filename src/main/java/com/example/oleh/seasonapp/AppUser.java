package com.example.oleh.seasonapp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AppUser extends BaseUser {

  private long id;
  private String name;

  public AppUser(AppUser user) {
    super(user.getUsername(), "********");
    this.name = user.getName();
  }
}
