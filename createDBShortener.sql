SET FOREIGN_KEY_CHECKS = 0;

CREATE SCHEMA IF NOT EXISTS `shortener` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `shortener` ;


CREATE TABLE IF NOT EXISTS `shortener`.`user` (
  `user_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '',
  `login` VARCHAR(20) NOT NULL COMMENT '',
  `password` VARCHAR(40) NOT NULL COMMENT '',
  `email` VARCHAR(60) NOT NULL COMMENT '',
  UNIQUE INDEX `login_UNIQUE` (`login` ASC)  COMMENT '',
  PRIMARY KEY (`user_id`)  COMMENT '',
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC)  COMMENT '')
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `shortener`.`tag` (
  `tag_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '',
  `title` VARCHAR(40) NOT NULL COMMENT '',
  PRIMARY KEY (`tag_id`)  COMMENT '',
  UNIQUE INDEX `tag_id_UNIQUE` (`tag_id` ASC)  COMMENT '',
  UNIQUE INDEX `title_UNIQUE` (`title` ASC)  COMMENT '')
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `shortener`.`link` (
  `short_url` VARCHAR(10) NOT NULL COMMENT '',
  `source_url` VARCHAR(1000) NOT NULL COMMENT '',
  `description` VARCHAR(10000) NULL COMMENT '',
  `click_count` BIGINT NOT NULL DEFAULT 0 COMMENT '',
  `user_id` BIGINT NULL COMMENT '',
  PRIMARY KEY (`short_url`)  COMMENT '',
  CONSTRAINT `fk_link_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `shortener`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  UNIQUE INDEX `short_url_UNIQUE` (`short_url` ASC)  COMMENT '')
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `shortener`.`tag_link` (
  `tag_id` BIGINT NOT NULL COMMENT '',
  `short_url` VARCHAR(10) NOT NULL COMMENT '',
  INDEX `fk_tag_link_tag_idx` (`tag_id` ASC)  COMMENT '',
  INDEX `fk_tag_link_link1_idx` (`short_url` ASC)  COMMENT '',
  PRIMARY KEY (`tag_id`, `short_url`)  COMMENT '',
  CONSTRAINT `fk_tag_link_tag`
    FOREIGN KEY (`tag_id`)
    REFERENCES `shortener`.`tag` (`tag_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tag_link_link1`
    FOREIGN KEY (`short_url`)
    REFERENCES `shortener`.`link` (`short_url`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET FOREIGN_KEY_CHECKS = 1;
