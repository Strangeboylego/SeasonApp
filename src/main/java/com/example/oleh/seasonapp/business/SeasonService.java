package com.example.oleh.seasonapp.business;

import java.util.Optional;

public interface SeasonService {
  
  Optional<Season> findByIdAndState(int id, SeriesState seriesState);
}
