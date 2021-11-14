package com.example.oleh.seasonapp.business;

import java.util.List;

public interface Series {

  long getId();

  int getSeasonNumber();

  int getNumber();

 List<SeriesState> getStates();
}
