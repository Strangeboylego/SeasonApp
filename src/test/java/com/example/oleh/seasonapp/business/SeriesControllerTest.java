package com.example.oleh.seasonapp.business;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.example.oleh.seasonapp.BaseMvcTest;

public class SeriesControllerTest extends BaseMvcTest {

  protected static final String SERIES_PATH = "/series";

  @Test
  public void updateState() throws Exception {

    Map<String, Object> body = new HashMap<>();
    body.put("id", 1);
    body.put("seasonNumber", 3);
    body.put("number", 1);
    body.put("states", new SeriesState[]{SeriesState.SELECTED});

    preparePut(SERIES_PATH, body).andExpect(status().isNoContent())
            .andDo(document("{method-name}/"));
  }
}
