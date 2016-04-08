
CREATE SCHEMA shortener AUTHORIZATION DBA;

CREATE TABLE shortener.user (
  user_id BIGINT IDENTITY NOT NULL PRIMARY KEY ,
  login LONGVARCHAR (20) NOT NULL UNIQUE ,
  password LONGVARCHAR (40) NOT NULL  ,
  email LONGVARCHAR (60) NOT NULL )

CREATE TABLE shortener.tag (
  tag_id BIGINT IDENTITY NOT NULL  PRIMARY KEY,
  title LONGVARCHAR (40) NOT NULL  )

CREATE TABLE shortener.link (
  short_url LONGVARCHAR (10) NOT NULL  PRIMARY KEY,
  source_url LONGVARCHAR (1000) NOT NULL,
  description LONGVARCHAR (10000) NULL  ,
  click_count BIGINT NOT NULL ,
  user_id BIGINT NOT NULL,
  CONSTRAINT fk_link_tag
    FOREIGN KEY (user_id)
    REFERENCES shortener.user (user_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)

CREATE TABLE shortener.tag_link (
  tag_id BIGINT NOT NULL  ,
  short_url LONGVARCHAR (10) NOT NULL  ,
   PRIMARY KEY (tag_id, short_url)   ,
  CONSTRAINT fk_tag_link_tag
    FOREIGN KEY (tag_id)
    REFERENCES shortener.tag (tag_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_tag_link_link1
    FOREIGN KEY (short_url)
    REFERENCES shortener.link (short_url)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)

