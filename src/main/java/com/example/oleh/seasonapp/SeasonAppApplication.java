package com.example.oleh.seasonapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class,
    DataSourceTransactionManagerAutoConfiguration.class, JdbcTemplateAutoConfiguration.class,
    JmxAutoConfiguration.class })
public class SeasonAppApplication {

  public static void main(String[] args) {
    SpringApplication.run(SeasonAppApplication.class, args);
  }

}
