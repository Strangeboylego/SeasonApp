package com.example.oleh.seasonapp.business.impl.series;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.oleh.seasonapp.business.Series;
import com.example.oleh.seasonapp.business.SeriesState;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SeriesDao {

  private static final String SERIES_MAPPER = "SeriesMapper.";

  private final SqlSession sqlSession;

  public Collection<SeriesBean> getBySeasonAndState(int seasonNumber, SeriesState state) {
    Map<String, Object> params = new HashMap<>();
    params.put("seasonNumber", seasonNumber);
    params.put("state", state.name());
    return sqlSession.selectList(SERIES_MAPPER + "getBySeasonAndState", params);
  }

  public Collection<SeriesBean> getBySeasonNumber(long seasonNumber) {
    return sqlSession.selectList(SERIES_MAPPER + "getBySeasonNumber", seasonNumber);
  }

  public int updateState(Series series) {
   final SeriesBean seriesBean = new SeriesBean(series.getId(), series.getSeasonNumber(),
        series.getNumber(), series.getStates().toArray(new SeriesState[0]));
    return sqlSession.update(SERIES_MAPPER + "updateState", seriesBean);
  }
}
