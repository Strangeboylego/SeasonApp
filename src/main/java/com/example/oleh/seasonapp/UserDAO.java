package com.example.oleh.seasonapp;

public interface UserDAO {

    AppUser findByUsername(String username);

    AppUser save(AppUser user);
}
