package com.example.oleh.seasonapp.business.impl.season;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.oleh.seasonapp.business.Season;
import com.example.oleh.seasonapp.business.SeasonService;
import com.example.oleh.seasonapp.business.Series;
import com.example.oleh.seasonapp.business.SeriesService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SeasonServiceImpl implements SeasonService {

  private final SeasonDao seasonDao;
  private final SeriesService seriesService;

  @Override
  public Optional<Season> findById(int number) {

    final SeasonBean seasonBean = seasonDao.getById(number);

    if (seasonBean == null) {
      log.warn("Season {} does not exist", number);
      return Optional.empty();
    }

    final List<Series> series = seriesService.findBySeasonNumber(number);

    return Optional.of(
        new SeasonImpl(seasonBean.getId(), seasonBean.getNumber(), seasonBean.getName(), series));
  }
}
