package com.example.oleh.seasonapp.business.impl.season;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.oleh.seasonapp.business.Season;
import com.example.oleh.seasonapp.business.SeasonService;
import com.example.oleh.seasonapp.business.Series;
import com.example.oleh.seasonapp.business.SeriesService;
import com.example.oleh.seasonapp.business.SeriesState;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SeasonServiceImpl implements SeasonService {

  private final SeasonDao seasonDao;
  private final SeriesService seriesService;

  @Override
  public Optional<Season> findByIdAndState(int id, SeriesState seriesState) {

    final SeasonBean seasonBean = seasonDao.getById(id);

    if (seasonBean == null) {
      log.warn("Season {} does not exist", id);
      return Optional.empty();
    }

    final List<Series> series = getSeries(id, seriesState);

    return Optional.of(
        new SeasonImpl(seasonBean.getId(), seasonBean.getNumber(), seasonBean.getName(), series));
  }

  private List<Series> getSeries(int id, SeriesState seriesState) {
    if (seriesState.equals(SeriesState.DEFAULT)) {
      return seriesService.findBySeasonNumber(id);
    }
    return seriesService.findBySeasonAndState(id, seriesState);
  }

}
