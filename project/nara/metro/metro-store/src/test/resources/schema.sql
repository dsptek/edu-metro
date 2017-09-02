DROP TABLE IF EXISTS mt_metro;

CREATE TABLE mt_metro (
  id                VARCHAR PRIMARY KEY,
  name              VARCHAR UNIQUE,
  memo              VARCHAR,
  pavilion_sequence NUMBER,
  citizen_sequence  NUMBER,
  admins_json       VARCHAR,
  time              NUMBER,
  entity_version    NUMBER
);

DROP TABLE IF EXISTS mt_metro_book;

CREATE TABLE mt_metro_book (
  id       VARCHAR PRIMARY KEY,
  sequence NUMBER
);

DROP TABLE IF EXISTS mt_citizen;

CREATE TABLE mt_citizen (
  id             VARCHAR PRIMARY KEY,
  sequence       NUMBER,
  name_json      VARCHAR,
  display_name   VARCHAR,
  username       VARCHAR,
  email          VARCHAR,
  phone          VARCHAR,
  guest          VARCHAR,
  time           NUMBER,
  disqualified   VARCHAR,
  metro_id       VARCHAR,
  entity_version NUMBER
);

CREATE UNIQUE INDEX IU_CITIZEN_METROID_EMAIL
  ON mt_citizen (
    metro_id ASC,
    email ASC
  );

DROP TABLE IF EXISTS mt_disqualified_citizen;

CREATE TABLE mt_disqualified_citizen (
  id                VARCHAR PRIMARY KEY,
  sequence          NUMBER,
  name_json         VARCHAR,
  display_name      VARCHAR,
  email             VARCHAR,
  phone             VARCHAR,
  guest             VARCHAR,
  time              NUMBER,
  disqualified_time NUMBER,
  metro_id          VARCHAR,
  entity_version    NUMBER
);

DROP TABLE IF EXISTS mt_login_user;

CREATE TABLE mt_login_user (
  id                    VARCHAR PRIMARY KEY,
  username              VARCHAR,
  email                 VARCHAR,
  password              VARCHAR,
  password_changed_time NUMBER,
  metro_id              VARCHAR,
  entity_version        NUMBER
);

CREATE UNIQUE INDEX IU_LOGINUSER_METROID_EMAIL
  ON mt_login_user (
    metro_id ASC,
    email ASC
  );

DROP TABLE IF EXISTS mt_login_time;

CREATE TABLE mt_login_time (
  id         VARCHAR PRIMARY KEY,
  citizen_id VARCHAR,
  time_json  VARCHAR
);