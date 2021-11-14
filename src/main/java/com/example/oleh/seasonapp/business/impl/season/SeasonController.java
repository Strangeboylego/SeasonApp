package com.example.oleh.seasonapp.business.impl.season;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.oleh.seasonapp.business.Season;
import com.example.oleh.seasonapp.business.SeasonService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/seasons")
@RequiredArgsConstructor
public class SeasonController {

  private final SeasonService seasonService;

  @GetMapping("{seasonNumber}")
  public ResponseEntity<?> get(@PathVariable("seasonNumber") int seasonNumber) {
    final Optional<Season> season = seasonService.findById(seasonNumber);

    if (season.isPresent()) {
      return ResponseEntity.of(season);
    }
    return ResponseEntity.badRequest().body("Season does not exist");
  }

}
