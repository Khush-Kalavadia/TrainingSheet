CREATE DATABASE IF NOT EXISTS MyDatabase;			-- create database with given name 
CREATE SCHEMA MyDatabase;							-- other way to create database
SHOW DATABASES;		 								-- show list of all databases
SHOW SCHEMAS;										-- same as displaying databases
USE MyDatabase;
DROP DATABASE MyDatabase;							-- it gives an exception if database does not exist 
DROP DATABASE IF EXISTS MyDatabase;					-- it gives a warning if database does not exist 

CREATE DATABASE EmployeeDb;
CREATE TABLE employee_table ( 
id int NOT NULL AUTO_INCREMENT,						-- it is set to auto increment
name varchar(45),									-- as it is primary key automatic it is set to NOT NULL	
occupation varchar(35),
age int,
PRIMARY KEY (id, name)								-- can set primary key to multiple column using comma
);
SHOW TABLES;
SHOW TABLES IN MyDatabase;
SHOW TABLES IN MyDatabase WHERE Tables_in_MyDatabase = "employee_information";
DROP TABLE employee_table;
DESCRIBE employee_table;							-- get information or structure of the table. Table name is case sensitive
SHOW FULL COLUMNS FROM employee_work;				-- can get some more information
ALTER TABLE employee_table
ADD gender char(6)
FIRST;												-- add first column 
ALTER TABLE employee_table
ADD lastName varchar(45)
AFTER name;											-- add after the "name" column
/*can also do
ALTER TABLE employee_table
ADD gender char(6)
ADD lastName varchar(45);
*/
ALTER TABLE employee_table
DROP COLUMN gender;
ALTER TABLE employee_table							-- modify column description
MODIFY lastName varchar(50);
ALTER TABLE employee_table							-- rename a column
CHANGE COLUMN name firstName
varchar(40);										-- need to give column description even in case of column rename
ALTER TABLE employee_table
RENAME TO employee_information;
DESCRIBE employee_information;
RENAME TABLE employee_information TO employee_info;
SHOW FULL TABLES;
CREATE TABLE employee_work(
id int,
work varchar(45),
PRIMARY KEY (id)
);
CREATE TEMPORARY TABLE Students( 								-- temporary table accessible only in current session
name VARCHAR(40) NOT NULL, 										-- temporary table can't be converted to permanent
total_marks DECIMAL(12,2) DEFAULT 0.00, 						-- 12 is size of decimal number while 2 digits follow the 10 digits before decimal
total_subjects INT UNSIGNED NOT NULL DEFAULT 0
);    
INSERT INTO Students(name, total_marks, total_subjects) 
VALUES ('Joseph', 150.75, 2), ('Peter', 180.75, 2);				
SELECT * FROM Students;	
TRUNCATE TABLE Students;										-- delete an entire data from a table without removing the table structure.
ALTER TABLE employee_work
ADD FOREIGN KEY (id) REFERENCES employee_info(id);	
CREATE TABLE employee_work(
id int,
work varchar(45),
PRIMARY KEY (id),
CONSTRAINT fk_id FOREIGN KEY (id) REFERENCES employee_info(id)
);
SELECT COLUMN_NAME, CONSTRAINT_NAME, REFERENCED_COLUMN_NAME, REFERENCED_TABLE_NAME		-- find foreign keys
 FROM information_schema.key_column_usage WHERE table_name = "employee_work";			-- can use information_schema.table_constraints
ALTER TABLE table_name DROP FOREIGN KEY fk_constraint_name;  
SHOW CREATE TABLE employee_work;
describe employee_work;
INSERT INTO employee_info(id, firstName) VALUES (1, "Rahul"), (2, "Ramesh");		
INSERT INTO employee_work(id, work) VALUES (1,"developement"), (2, "hr");			-- foreign key so need to insert in info table first
CREATE TABLE IF NOT EXISTS duplicate_employee_work SELECT * FROM employee_work;		-- duplicate table
SELECT * FROM duplicate_employee_work;												
ALTER TABLE duplicate_employee_work ADD COLUMN temp_col varchar(10) DEFAULT "HELLO";-- add drop column
ALTER TABLE duplicate_employee_work DROP COLUMN temp_col;
ALTER TABLE duplicate_employee_work CHANGE COLUMN temp_col temp_column VARCHAR(25);  				-- rename column
ALTER TABLE duplicate_employee_work RENAME COLUMN temp_col TO temp_column;
SELECT * FROM employee_work;
INSERT INTO employee_info(firstName) VALUES ("Ravi");
INSERT INTO employee_work(work) VALUES ("QA");
DESCRIBE employee_work;

CREATE TABLE employee_work_trial(	-- DEFAULT in primary key works for one time becuase parent table has 1 and it would repeat after one usage	
id int DEFAULT 1,
work varchar(30),
PRIMARY KEY (id),
CONSTRAINT fk_id FOREIGN KEY (id) REFERENCES employee_info(id)		-- cannot add foreign key to temporary table
);
INSERT INTO employee_work_trial(work) VALUES ("HR");				-- this won't give error
INSERT INTO employee_work_trial(work) VALUES ("SALES");				-- this will give error
TRUNCATE TABLE employee_info;										-- can't truncate as its primary key is being used as foreign key in employee_work_trial

SHOW INDEXES FROM employee_work_trial;
SHOW CREATE TABLE employee_work_trial;
select * from information_schema.table_constraints where TABLE_NAME="employee_work_trial";
ALTER TABLE employee_work ADD COLUMN date date;						-- add column having type date
SELECT * FROM employee_work;
INSERT INTO employee_work(id, work, date) VALUES (5, "hr", "2000-3-4");
INSERT INTO employee_work(id, work, date) VALUES (6, "hr", "2020-12-31");
SELECT date_format(date, '%d/%m/%Y') FROM employee_work;			-- printing date in specific format
SELECT * FROM employee_work;
UPDATE employee_work SET date = '2020-11-30' where work = "developement";	-- update single value in a row
UPDATE employee_work SET work = 'sales', date = '2010-9-16' WHERE id = 2;  -- update multiple value in a row
ALTER TABLE employee_info ADD COLUMN email varchar(45);						-- adding column
SELECT * FROM employee_info;
UPDATE employee_info SET email = 'rahul@gmail.com' where firstName = 'Rahul';	
UPDATE employee_info SET email = REPLACE(email, '@gmail.com', '@yahoo.com') where firstName = 'Rahul';	-- update a sub-string
INSERT INTO employee_info(id, firstName) VALUES (3,'Pravin');
DELETE FROM employee_info WHERE id=3;										-- delete row based on given condition
DELETE FROM employee_info ORDER BY id LIMIT 2;								-- delete first 2 rows if they are not set as foreign key in other table
SELECT work, count(*), MAX(date) FROM employee_work 					-- specifing the columns which would be available even after grouping
GROUP BY work 												-- grouping the above query
ORDER BY MAX(date) DESC										-- ordering based on the available grouped dates
LIMIT 2;													-- limiting the output to 2 rows
SELECT count(*), work FROM employee_work GROUP BY work HAVING work = 'hr';
SELECT * FROM employee_work;

CREATE TEMPORARY TABLE sales(
item int,
sales int
);
INSERT INTO sales VALUES (1,2), (2,3), (3,2), (1,4), (2,3); 
SELECT * FROM sales;
SELECT DISTINCT item FROM sales;
SELECT DISTINCT sales FROM sales;
SELECT DISTINCT item, sales FROM sales;				-- this gives distinct combimation of {item and sales}.
SELECT DISTINCT sales, item FROM sales;				-- this also gives same result just the position of column is different
SELECT * FROM sales ORDER BY item, sales DESC;		-- first orders based on item then based on sales
SELECT item, SUM(sales) AS "Total sales" FROM sales GROUP BY item;
SELECT item, SUM(sales) AS "Total sales" FROM sales GROUP BY item HAVING SUM(sales)>4;
