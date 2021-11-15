package com.example.oleh.seasonapp.business.impl.series;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.oleh.seasonapp.business.Series;
import com.example.oleh.seasonapp.business.SeriesService;
import com.example.oleh.seasonapp.business.SeriesState;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/series")
@RequiredArgsConstructor
public class SeriesController {

  private final SeriesService seriesService;

  @GetMapping("/selected")
  public List<Series> get() {
    return seriesService.findByState(SeriesState.SELECTED);
  }

  @PutMapping
  public ResponseEntity<?> updateStates(@RequestBody Series series) {

    final boolean updated = seriesService.updateState(series);
    return updated ? ResponseEntity.noContent().build()
        : ResponseEntity.badRequest().body("Series does not exist");
  }

}
