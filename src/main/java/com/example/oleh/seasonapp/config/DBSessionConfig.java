package com.example.oleh.seasonapp.config;

import java.util.Objects;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * Configuration data base access
 *
 * @see SqlSession
 */
@Configuration
public class DBSessionConfig {

  @Bean
  public SqlSessionFactory sqlSessionFactoryAnalytics() throws Exception {
    SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
    factoryBean.setDataSource(getDataSource());
    factoryBean.setConfigLocation(new ClassPathResource("mappersConfig.xml"));
    return factoryBean.getObject();
  }

  @Bean
  public SqlSession sqlSession(SqlSessionFactory sqlSessionFactory) {
    return new SqlSessionTemplate(Objects.requireNonNull(sqlSessionFactory));
  }

  private DataSource getDataSource() {
    return DataSourceBuilder.create().password("postgres").username("postgres")
        .url("jdbc:postgresql://localhost:5432/seasonapp").build();
  }
}
