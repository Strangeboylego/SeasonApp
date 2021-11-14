package com.example.oleh.seasonapp.business.impl.series;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.oleh.seasonapp.business.Series;
import com.example.oleh.seasonapp.business.SeriesState;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SeriesImpl implements Series {
  private final long id;
  private final int seasonNumber;
  private final int number;
  private final List<SeriesState> states;

  public SeriesImpl(SeriesBean seriesBean) {
    this.id = seriesBean.getId();
    this.seasonNumber = seriesBean.getSeasonNumber();
    this.number = seriesBean.getNumber();
    this.states = new ArrayList<>(Arrays.asList(seriesBean.getStates()));
  }
}
