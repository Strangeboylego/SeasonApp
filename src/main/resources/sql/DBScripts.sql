CREATE DATABASE seasonapp;

CREATE TABLE IF NOT EXISTS public.user
(
    id SERIAL PRIMARY KEY,
    name character varying(64) NOT NULL,
    user_name character varying(64) NOT NULL,
    password character varying(64) NOT NULL,
    UNIQUE (user_name)
);


CREATE TABLE IF NOT EXISTS public.season
(
    id SERIAL PRIMARY KEY,
    season_number integer  NOT NULL,
    name character varying(64) NOT NULL,
    UNIQUE (season_number)
);

CREATE TABLE IF NOT EXISTS public.series
(
    id SERIAL PRIMARY KEY,
    season_number integer  NOT NULL,
    series_number integer NOT NULL,
    states  varchar(512)[],
    FOREIGN KEY (season_number) REFERENCES public.season (season_number) MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    UNIQUE (season_number, series_number)
    );


INSERT INTO season (season_number, name) VALUES (3, 'Season 3');

INSERT INTO series (season_number, series_number, states)
VALUES (3, 1, '{}');
INSERT INTO series (season_number, series_number, states)
VALUES (3, 2, '{SELECTED}');
INSERT INTO series (season_number, series_number, states)
VALUES (3, 3, '{}');
INSERT INTO series (season_number, series_number, states)
VALUES (3, 4, '{SELECTED}');
INSERT INTO series (season_number, series_number, states)
VALUES (3, 5, '{}');
INSERT INTO series (season_number, series_number, states)
VALUES (3, 6, '{SELECTED}');
INSERT INTO series (season_number, series_number, states)
VALUES (3, 7, '{}');
INSERT INTO series (season_number, series_number, states)
VALUES (3, 8, '{SELECTED}');




