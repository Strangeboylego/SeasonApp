package com.example.oleh.seasonapp.business.impl.season;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SeasonBean implements Serializable {

  private static final long serialVersionUID = -8587232582139331720L;

  private long id;
  private int number;
  private String name;
}
