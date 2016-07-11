SQL Statements:

1. Create database bece6_db

CREATE DATABASE bece6_db;
USE bece6_db;

2. Create table students_info

CREATE TABLE students_info (
reg_no INT(10) NOT NULL,
first_name VARCHAR(50),
middle_name VARCHAR(50),
last_name VARCHAR(50),
PRIMARY KEY(reg_no)
);

3. Insert values into table students_info

INSERT INTO students_info VALUES
(1,'John','Rhaegar','Snow'),
(2,'Daenerys','Aerys','Targaryen'),
(3,'Tyrion','Tywin','Lannister'),
(4,'Robb','Eddard','Stark'),
(5,'Arya','Eddard','Stark');

4. Create table guardian_info

CREATE TABLE guardian_info (
reg_no INT(10) NOT NULL,
guardian_first_name VARCHAR(50),
guardian_middle_name VARCHAR(50),
guardian_last_name VARCHAR(50),
PRIMARY KEY(reg_no)
);

5. Insert values into table guardian_info

INSERT INTO guardian_info VALUES
(1,'Rhaegar','NA','Targaryen'),
(2,'Aerys','NA','Targaryen'),
(3,'Tywin','NA','Lannister'),
(4,'Eddard','NA','Stark'),
(5,'Catlin','NA','Stark');

6. Create table students_other_info

CREATE TABLE students_other_info (
reg_no INT(10) NOT NULL,
is_admin VARCHAR(1),
password VARCHAR(50),
PRIMARY KEY(reg_no)
);

7. Insert values into table students_other_info

INSERT INTO students_other_info VALUES
(1,'y','qwerty'),
(2,'n','qwerty'),
(3,'n','qwerty'),
(4,'n','qwerty'),
(5,'n','qwerty');