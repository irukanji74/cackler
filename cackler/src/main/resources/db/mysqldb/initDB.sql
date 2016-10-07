CREATE DATABASE IF NOT EXISTS cackler;
GRANT ALL PRIVILEGES ON cackler.* TO pc@localhost IDENTIFIED BY 'pc';

USE cackler;

CREATE TABLE IF NOT EXISTS department (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  department_name VARCHAR(80)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS employee (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(30),
  last_name VARCHAR(30),
  birth_date DATE,
  salary INT(10) UNSIGNED NOT NULL,
  department_id INT(4) UNSIGNED NOT NULL,
  FOREIGN KEY (department_id) REFERENCES department(id)
) engine=InnoDB;
