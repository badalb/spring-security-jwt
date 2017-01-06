SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `uaa` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;

USE `uaa`;

CREATE  TABLE IF NOT EXISTS `tbluser` (
  `user_id` VARCHAR(38) NOT NULL ,
  `user_name` VARCHAR(50) NOT NULL ,
  `first_name` VARCHAR(50) NOT NULL ,
  `last_name` VARCHAR(50) NULL DEFAULT NULL ,
  `is_inactive` VARCHAR(1) NULL DEFAULT NULL ,
  `is_locked` VARCHAR(1) NULL DEFAULT NULL ,
  `password` VARCHAR(256) NULL DEFAULT NULL ,
  `created_date` DATETIME NOT NULL ,
  `last_login_date` DATETIME NULL DEFAULT NULL ,
  `last_modified_date` DATETIME NULL DEFAULT NULL ,
  `created_by` VARCHAR(38) NULL DEFAULT NULL ,
  `last_modified_by` VARCHAR(38) NULL DEFAULT NULL ,
  `is_deleted` VARCHAR(1) NULL DEFAULT NULL ,
  PRIMARY KEY (`user_id`) ,
  UNIQUE INDEX `user_name_UNIQUE` (`user_name` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE  TABLE IF NOT EXISTS `tblrole` (
  `role_id` VARCHAR(38) NOT NULL ,
  `name` VARCHAR(50) NULL DEFAULT NULL ,
  `description` VARCHAR(100) NULL DEFAULT NULL ,
  `is_inactive` VARCHAR(1) NULL DEFAULT NULL ,
  `created_date` DATETIME NULL DEFAULT NULL ,
  `created_by` VARCHAR(38) NULL DEFAULT NULL ,
  `last_modified_date` DATETIME NULL DEFAULT NULL ,
  `last_modified_by` VARCHAR(38) NULL DEFAULT NULL ,
  `is_deleted` VARCHAR(1) NULL DEFAULT NULL ,
  PRIMARY KEY (`role_id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE  TABLE IF NOT EXISTS `tbluser_role` (
  `user_role_id` VARCHAR(38) NOT NULL ,
  `user_id` VARCHAR(38) NOT NULL ,
  `role_id` VARCHAR(38) NOT NULL ,
  `is_default` VARCHAR(1) NULL DEFAULT NULL ,
  `created_date` DATETIME NULL DEFAULT NULL ,
  `created_by` VARCHAR(38) NULL DEFAULT NULL ,
  `last_modified_date` DATETIME NULL DEFAULT NULL ,
  `last_modified_by` VARCHAR(38) NULL DEFAULT NULL ,
  `is_deleted` VARCHAR(1) NULL DEFAULT NULL ,
  PRIMARY KEY (`user_role_id`, `user_id`, `role_id`) ,
  INDEX `fk_tblUserRole_tblUser1_idx` (`user_id` ASC) ,
  INDEX `fk_tblUserRole_tblRole1_idx` (`role_id` ASC) ,
  UNIQUE INDEX `un_tblUserRole` (`user_id` ASC, `role_id` ASC) ,
  CONSTRAINT `fk_tblUserRole_tblUser1`
    FOREIGN KEY (`user_id` )
    REFERENCES `tbluser` (`user_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tblUserRole_tblRole1`
    FOREIGN KEY (`role_id` )
    REFERENCES `tblrole` (`role_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE  TABLE IF NOT EXISTS `tblpermission` (
  `permission_id` VARCHAR(38) NOT NULL ,
  `name` VARCHAR(50) NOT NULL ,
  `key_id` VARCHAR(45) NOT NULL ,
  `description` VARCHAR(255) NULL DEFAULT NULL ,
  `is_inactive` VARCHAR(1) NULL DEFAULT NULL ,
  `created_date` DATETIME NOT NULL ,
  `created_by` VARCHAR(38) NULL DEFAULT NULL ,
  `last_modified_by` VARCHAR(38) NULL DEFAULT NULL ,
  `last_modified_date` DATETIME NULL DEFAULT NULL ,
  `is_deleted` VARCHAR(1) NULL DEFAULT NULL ,
  PRIMARY KEY (`permission_id`) ,
  UNIQUE INDEX `key_id_UNIQUE` (`key_id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE  TABLE IF NOT EXISTS `tblrole_permission` (
  `role_permission_id` VARCHAR(38) NOT NULL ,
  `permission_id` VARCHAR(38) NOT NULL ,
  `role_id` VARCHAR(38) NOT NULL ,
  `created_date` DATETIME NULL DEFAULT NULL ,
  `is_inactive` VARCHAR(1) NULL DEFAULT NULL ,
  `created_by` VARCHAR(38) NULL DEFAULT NULL ,
  `last_modified_by` VARCHAR(38) NULL DEFAULT NULL ,
  `last_modified_date` DATETIME NULL DEFAULT NULL ,
  `is_deleted` VARCHAR(1) NULL DEFAULT NULL ,
  PRIMARY KEY (`role_permission_id`, `permission_id`, `role_id`) ,
  INDEX `fk_tblRolePermission_tblPermission1_idx` (`permission_id` ASC) ,
  INDEX `fk_tblRolePermission_tblRole1_idx` (`role_id` ASC) ,
  CONSTRAINT `fk_tblRolePermission_tblPermission1`
    FOREIGN KEY (`permission_id` )
    REFERENCES `tblpermission` (`permission_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tblRolePermission_tblRole1`
    FOREIGN KEY (`role_id` )
    REFERENCES `tblrole` (`role_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;