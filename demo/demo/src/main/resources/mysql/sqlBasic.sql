-- CREATE SCHEMA testconfigs; 
USE testconfigs;

CREATE TABLE `testconfigs`.`users` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `email` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `full_name` VARCHAR(45) NULL,
  `enabled` TINYINT NULL DEFAULT 1);

CREATE TABLE `testconfigs`.`users_roles` (
  `user_id` INT PRIMARY KEY,
  `role_id` INT PRIMARY KEY
 );

-- CREATE TABLE `testconfigs`.`users_roles` (
--                                              `id` INT PRIMARY KEY AUTO_INCREMENT,
--                                              `user_id` INT unique,
--                                              `role_id` INT unique
-- );
  
  CREATE TABLE `testconfigs`.`roles` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL);

