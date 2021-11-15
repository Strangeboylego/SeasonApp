package com.example.oleh.seasonapp;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.oleh.seasonapp.auth.AppUser;
import com.example.oleh.seasonapp.auth.UserDao;
import com.example.oleh.seasonapp.business.SeriesState;
import com.example.oleh.seasonapp.business.impl.season.SeasonBean;
import com.example.oleh.seasonapp.business.impl.season.SeasonDao;
import com.example.oleh.seasonapp.business.impl.series.SeriesBean;
import com.example.oleh.seasonapp.business.impl.series.SeriesDao;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class MvcTestConfiguration implements WebMvcConfigurer {

  private final BCryptPasswordEncoder passwordEncoder;

  @Bean
  @Primary
  public UserDao userDao() {

    final String password = passwordEncoder.encode("password");
    final AppUser user = new AppUser();
    user.setId(1);
    user.setUsername("test");
    user.setPassword(password);
    user.setName("Test");
    UserDao userDao = mock(UserDao.class);
    when(userDao.findByUsername("test")).thenReturn(user);

    when(userDao.save(any())).thenReturn(user);
    return userDao;
  }

  @Bean
  @Primary
  public SeasonDao seasonDao() {
    final SeasonBean seasonBean = new SeasonBean();
    seasonBean.setId(1);
    seasonBean.setName("Test");
    seasonBean.setNumber(3);
    final SeasonDao seasonDao = mock(SeasonDao.class);
    when(seasonDao.getById(3)).thenReturn(seasonBean);

    return seasonDao;
  }

  @Bean
  @Primary
  public SeriesDao seriesDao() {
    final SeriesDao seriesDao = mock(SeriesDao.class);

    final SeriesBean seriesBean = new SeriesBean();
    seriesBean.setNumber(1);
    seriesBean.setSeasonNumber(3);
    seriesBean.setId(1);
    seriesBean.setStates(new SeriesState[] { SeriesState.SELECTED });

    final List<SeriesBean> seriesBeans = Collections.singletonList(seriesBean);
    when(seriesDao.getBySeasonNumber(3)).thenReturn(seriesBeans);
    when(seriesDao.getByState(SeriesState.SELECTED)).thenReturn(seriesBeans);
    when(seriesDao.updateState(any())).thenReturn(1);

    return seriesDao;
  }

}
