package com.example.oleh.seasonapp.business;

import java.util.List;

public interface Season {

  long getId();

  String getName();

  List<Series> getSeries();
}
