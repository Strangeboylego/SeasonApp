package com.example.oleh.seasonapp.business.impl.series;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.oleh.seasonapp.business.Series;
import com.example.oleh.seasonapp.business.SeriesService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/series")
@RequiredArgsConstructor
public class SeriesController {

  private final SeriesService seriesService;

  @PostMapping
  public ResponseEntity<?> updateStates(@RequestBody Series series) {

    final boolean updated = seriesService.updateState(series);
    return updated ? ResponseEntity.noContent().build()
        : ResponseEntity.badRequest().body("Series does not exist");
  }

}
