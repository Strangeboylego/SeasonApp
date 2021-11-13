package com.example.oleh.seasonapp.impl;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.oleh.seasonapp.AppUser;
import com.example.oleh.seasonapp.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping
  public ResponseEntity<AppUser> save(@RequestBody AppUser appUser) {
    final URI uri = URI
        .create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user").toUriString());
    return ResponseEntity.created(uri).body(userService.saveUser(appUser));
  }
}
