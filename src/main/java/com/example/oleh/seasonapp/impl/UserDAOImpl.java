package com.example.oleh.seasonapp.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.oleh.seasonapp.AppUser;
import com.example.oleh.seasonapp.UserDAO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserDAOImpl implements UserDAO {

  private static final String MYBATIS_MAINTENANCE_DIAGNOSTIC = "UserMapper.";

  private final SqlSession sqlSession;

  @Override
  public AppUser findByUsername(String username) {
    return sqlSession.selectOne(MYBATIS_MAINTENANCE_DIAGNOSTIC + "getByUsername", username);
  }

  @Override
  public AppUser save(AppUser user) {
    sqlSession.insert(MYBATIS_MAINTENANCE_DIAGNOSTIC + "insert", user);
    return user;
  }
}
