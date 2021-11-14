package com.example.oleh.seasonapp.business.impl.series;

import java.io.Serializable;

import com.example.oleh.seasonapp.business.SeriesState;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SeriesBean implements Serializable {

    private static final long serialVersionUID = -4031978767976560386L;

    private long id;
    private int seasonNumber;
    private int number;
    private SeriesState[] states;
}
