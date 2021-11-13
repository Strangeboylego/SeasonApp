package com.example.oleh.seasonapp.impl;

import java.util.Collections;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.oleh.seasonapp.AppUser;
import com.example.oleh.seasonapp.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

  private final UserDAOImpl userDAO;
  private final BCryptPasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    final AppUser user = userDAO.findByUsername(username);
    if (user == null) {
      log.error("User {} does not exists", username);
      throw new UsernameNotFoundException("User not found in DB");
    }
    log.info("Found user {} in DB", username);

    return new User(user.getUsername(), user.getPassword(), Collections.emptyList());
  }

  @Override
  public AppUser saveUser(AppUser user) {

    final AppUser response = new AppUser(user);
    user.setPassword(passwordEncoder.encode(user.getPassword()));

    log.info("Adding new user  {} to DB", user.getName());
    final long id = userDAO.save(user).getId();
    response.setId(id);

    return response;
  }

}
