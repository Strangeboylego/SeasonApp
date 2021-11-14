package com.example.oleh.seasonapp.auth;

public interface UserDao {

    AppUser findByUsername(String username);

    AppUser save(AppUser user);
}
