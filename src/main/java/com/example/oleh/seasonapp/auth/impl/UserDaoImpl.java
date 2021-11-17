package com.example.oleh.seasonapp.auth.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.oleh.seasonapp.auth.AppUser;
import com.example.oleh.seasonapp.auth.UserDao;
import com.example.oleh.seasonapp.exception.UserAlreadyExistsException;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {

  private static final String USER_MAPPER = "UserMapper.";

  private final SqlSession sqlSession;

  @Override
  public AppUser findByUsername(String username) {
    return sqlSession.selectOne(USER_MAPPER + "getByUsername", username);
  }

  @Override
  public AppUser save(AppUser user) {
    try {
      sqlSession.insert(USER_MAPPER + "insert", user);
    } catch (Exception e) {
      throw new UserAlreadyExistsException(
          "User with username " + user.getUsername() + " already exists");
    }

    return user;
  }
}
