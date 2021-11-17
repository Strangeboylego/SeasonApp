package com.example.oleh.seasonapp.business;

import java.util.List;

public interface SeriesService {

  List<Series> findBySeasonAndState(int seasonNumber, SeriesState seriesState);

  List<Series> findBySeasonNumber(int seasonId);

  boolean updateState(Series series);
}
