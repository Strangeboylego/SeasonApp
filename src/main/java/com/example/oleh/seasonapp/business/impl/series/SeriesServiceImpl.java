package com.example.oleh.seasonapp.business.impl.series;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.oleh.seasonapp.business.Series;
import com.example.oleh.seasonapp.business.SeriesService;
import com.example.oleh.seasonapp.business.SeriesState;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SeriesServiceImpl implements SeriesService {

  private final SeriesDaoImpl seriesDAO;

  @Override
  public List<Series> findByState(SeriesState seriesState) {
    final Collection<SeriesBean> seriesBeans = seriesDAO.getByState(seriesState);
    return seriesBeans.stream().map(SeriesImpl::new).collect(Collectors.toList());
  }

  @Override
  public List<Series> findBySeasonNumber(int seasonNumber) {
    final Collection<SeriesBean> seriesBeans = seriesDAO.getBySeasonNumber(seasonNumber);
    return seriesBeans.stream().map(SeriesImpl::new).collect(Collectors.toList());
  }

  @Override
  public boolean updateState(Series series) {
    if (series.getStates().size() == 0) {
      log.info("Remove states form series {},  season {}", series.getId(),
          series.getSeasonNumber());
    } else {
      log.info("Add states {} form series {},  season {}", series.getStates(), series.getId(),
          series.getSeasonNumber());
    }

    return seriesDAO.updateState(series) == 1;

  }
}
