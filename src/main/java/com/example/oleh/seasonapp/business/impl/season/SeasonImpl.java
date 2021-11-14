package com.example.oleh.seasonapp.business.impl.season;

import java.util.List;

import com.example.oleh.seasonapp.business.Season;
import com.example.oleh.seasonapp.business.Series;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SeasonImpl implements Season {

  private final long id;
  private final int number;
  private final String name;
  private final List<Series> series;

}
