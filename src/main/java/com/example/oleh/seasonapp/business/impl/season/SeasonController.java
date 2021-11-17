package com.example.oleh.seasonapp.business.impl.season;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.oleh.seasonapp.business.Season;
import com.example.oleh.seasonapp.business.SeasonService;
import com.example.oleh.seasonapp.business.SeriesState;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/seasons")
@RequiredArgsConstructor
public class SeasonController {

  private final SeasonService seasonService;

  @GetMapping("{seasonNumber}/selected")
  public ResponseEntity<?> getSelected(@PathVariable("seasonNumber") int seasonNumber) {
    return getResponseEntity(seasonNumber, SeriesState.SELECTED);
  }

  @GetMapping("{seasonNumber}")
  public ResponseEntity<?> get(@PathVariable("seasonNumber") int seasonNumber) {
    return getResponseEntity(seasonNumber, SeriesState.DEFAULT);
  }

  private ResponseEntity<?> getResponseEntity(int seasonNumber, SeriesState seriesState) {
    final Optional<Season> season = seasonService.findByIdAndState(seasonNumber, seriesState);

    if (season.isPresent()) {
      return ResponseEntity.of(season);
    }
    return ResponseEntity.badRequest().body("Season does not exist");
  }

}
