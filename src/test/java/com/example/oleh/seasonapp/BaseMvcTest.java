package com.example.oleh.seasonapp;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.example.oleh.seasonapp.auth.impl.UserDaoImpl;
import com.example.oleh.seasonapp.business.impl.season.SeasonDao;
import com.example.oleh.seasonapp.business.impl.series.SeriesDao;
import com.example.oleh.seasonapp.config.ApplicationConfig;
import com.example.oleh.seasonapp.config.SecurityConfig;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ContextConfiguration(classes = { ApplicationConfig.class, MvcTestConfiguration.class })
@ComponentScan(basePackages = "com.example.oleh.seasonapp.auth, com.example.oleh.seasonapp.business", basePackageClasses = SecurityConfig.class, excludeFilters = {
    @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = { UserDaoImpl.class,
        SeasonDao.class, SeriesDao.class }) })
@WebMvcTest
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter(AccessLevel.PROTECTED)
@AutoConfigureRestDocs
public abstract class BaseMvcTest {

  private static final String ENCODING = "UTF-8";
  private static final String AUTH = "Authorization";
  private static final String TOKEN = "token";
  protected static final String PATH_LOGIN = "/login";

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  ObjectMapper objectMapper;


  protected ResultActions prepareGet(String path) throws Exception {
    final MockHttpServletRequestBuilder builder = get(path);
    String token = getToken();
    builder.header(AUTH, token);
    builder.contentType(MediaType.APPLICATION_JSON).characterEncoding(ENCODING);
    return getMockMvc().perform(builder);
  }

  protected ResultActions preparePost(String path, Map<String, String> body) throws Exception {
    final MockHttpServletRequestBuilder builder = post(path);

    builder.content(objectMapper.writeValueAsString(body));
    builder.contentType(MediaType.APPLICATION_JSON).characterEncoding(ENCODING);
    return getMockMvc().perform(builder);
  }

  protected ResultActions preparePut(String path, Map<String, Object> body) throws Exception {
    final MockHttpServletRequestBuilder builder = put(path);
    String token = getToken();
    builder.header(AUTH, token);
    builder.content(objectMapper.writeValueAsString(body));
    builder.contentType(MediaType.APPLICATION_JSON).characterEncoding(ENCODING);
    return getMockMvc().perform(builder);
  }

  protected ResultActions login(String username, String password) throws Exception {
    Map<String, String> body = new HashMap<>();
    body.put("username", username);
    body.put("password", password);
    return preparePost(PATH_LOGIN, body);
  }

  protected String getToken(ResultActions login) {
    return login.andReturn().getResponse().getHeader(TOKEN);
  }

  protected String getToken() throws Exception {
    final ResultActions login = login("test", "password");
    return getToken(login);
  }

}
