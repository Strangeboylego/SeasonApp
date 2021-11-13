package com.example.oleh.seasonapp.auth;

public interface UserDAO {

    AppUser findByUsername(String username);

    AppUser save(AppUser user);
}
