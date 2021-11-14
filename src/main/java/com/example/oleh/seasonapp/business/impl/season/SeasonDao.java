package com.example.oleh.seasonapp.business.impl.season;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SeasonDao {

  private static final String SEASON_MAPPER = "SeasonMapper.";

  private final SqlSession sqlSession;

  public SeasonBean getById(int number) {
    return sqlSession.selectOne(SEASON_MAPPER + "getByNumber", number);
  }
}
