CREATE DATABASE IF NOT EXISTS cackler;
GRANT ALL PRIVILEGES ON homework.* TO pc@localhost IDENTIFIED BY 'pc';

USE cackler;

CREATE TABLE IF NOT EXISTS offices (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  office_name VARCHAR(80)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS employees (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(30),
  last_name VARCHAR(30),
  birth_date DATE,
  salary INT(10) UNSIGNED NOT NULL,
  office_id INT(4) UNSIGNED NOT NULL,
  FOREIGN KEY (office_id) REFERENCES offices(id)
  INDEX(last_name)
) engine=InnoDB;
