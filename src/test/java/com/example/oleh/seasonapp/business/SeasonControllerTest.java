package com.example.oleh.seasonapp.business;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.example.oleh.seasonapp.BaseMvcTest;

public class SeasonControllerTest extends BaseMvcTest {

  protected static final String SEASONS_PATH = "/seasons";

  @Test
  public void loadBySeasonNumber() throws Exception {

    prepareGet(SEASONS_PATH, Optional.of("3")).andExpect(status().isOk())
        .andDo(document("{method-name}/"));
  }
}
