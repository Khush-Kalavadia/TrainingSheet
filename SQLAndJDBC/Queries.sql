-- QUERIES PRACTISE
-- LINK: https://www.techbeamers.com/sql-query-questions-answers-for-practice/

CREATE DATABASE organisation;
USE organisation;

CREATE TABLE Worker (
	WORKER_ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	FIRST_NAME CHAR(25),
	LAST_NAME CHAR(25),
	SALARY INT,
	JOINING_DATE DATETIME,
	DEPARTMENT CHAR(25)
);

INSERT INTO Worker 
	(WORKER_ID, FIRST_NAME, LAST_NAME, SALARY, JOINING_DATE, DEPARTMENT) VALUES
		(001, 'Monika', 'Arora', 100000, '14-02-20 09.00.00', 'HR'),
		(002, 'Niharika', 'Verma', 80000, '14-06-11 09.00.00', 'Admin'),
		(003, 'Vishal', 'Singhal', 300000, '14-02-20 09.00.00', 'HR'),
		(004, 'Amitabh', 'Singh', 500000, '14-02-20 09.00.00', 'Admin'),
		(005, 'Vivek', 'Bhati', 500000, '14-06-11 09.00.00', 'Admin'),
		(006, 'Vipul', 'Diwan', 200000, '14-06-11 09.00.00', 'Account'),
		(007, 'Satish', 'Kumar', 75000, '14-01-20 09.00.00', 'Account'),
		(008, 'Geetika', 'Chauhan', 90000, '14-04-11 09.00.00', 'Admin');

CREATE TABLE Bonus (
	WORKER_REF_ID INT,
	BONUS_AMOUNT INT,
	BONUS_DATE DATETIME,
	FOREIGN KEY (WORKER_REF_ID)
		REFERENCES Worker(WORKER_ID)
        ON DELETE CASCADE
);

INSERT INTO Bonus 
	(WORKER_REF_ID, BONUS_AMOUNT, BONUS_DATE) VALUES
		(001, 5000, '16-02-20'),
		(002, 3000, '16-06-11'),
		(003, 4000, '16-02-20'),
		(001, 4500, '16-02-20'),
		(002, 3500, '16-06-11');
        
CREATE TABLE Title (
	WORKER_REF_ID INT,
	WORKER_TITLE CHAR(25),
	AFFECTED_FROM DATETIME,
	FOREIGN KEY (WORKER_REF_ID) REFERENCES Worker(WORKER_ID)
	ON DELETE CASCADE
);

INSERT INTO Title(WORKER_REF_ID, WORKER_TITLE, AFFECTED_FROM) VALUES
 (001, 'Manager', '2016-02-20 00:00:00'),
 (002, 'Executive', '2016-06-11 00:00:00'),
 (008, 'Executive', '2016-06-11 00:00:00'),
 (005, 'Manager', '2016-06-11 00:00:00'),
 (004, 'Asst. Manager', '2016-06-11 00:00:00'),
 (007, 'Executive', '2016-06-11 00:00:00'),
 (006, 'Lead', '2016-06-11 00:00:00'),
 (003, 'Lead', '2016-06-11 00:00:00');
 
SELECT FIRST_NAME AS WORKER_NAME FROM Worker;
SELECT upper(FIRST_NAME) FROM Worker;											-- upper
SELECT DISTINCT DEPARTMENT FROM Worker;
SELECT SUBSTRING(FIRST_NAME, 1, 3) FROM Worker;									-- substring
SELECT INSTR(FIRST_NAME, BINARY 'a') FROM Worker WHERE FIRST_NAME = 'Amitabh';	-- instr to return FIRST occurance of substring
SELECT RTRIM(FIRST_NAME) FROM Worker;
SELECT DISTINCT LENGTH(DEPARTMENT), DEPARTMENT FROM Worker;
SELECT REPLACE(FIRST_NAME, 'A', 'a') FROM Worker;								-- replace
SELECT CONCAT(FIRST_NAME, ' ',LAST_NAME) AS COMPLETE_NAME FROM Worker;			-- concat
SELECT FIRST_NAME, LAST_NAME FROM Worker ORDER BY FIRST_NAME, LAST_NAME DESC;
SELECT * FROM Worker WHERE FIRST_NAME IN ('Vipul', 'Satish'); 
SELECT * FROM Worker WHERE FIRST_NAME NOT IN ('Vipul', 'Satish'); 
SELECT FIRST_NAME FROM Worker WHERE FIRST_NAME LIKE '%a%';						-- first name contains 'a'
SELECT FIRST_NAME FROM Worker WHERE FIRST_NAME LIKE '______h';					-- ends with h and has 6 character in total
SELECT SALARY FROM Worker WHERE SALARY BETWEEN 100000 AND 5000000;
SELECT * FROM Worker WHERE YEAR(JOINING_DATE) = 2014 AND MONTH(JOINING_DATE) = 2; 	-- people who joined in FEBRUARY 2014
SELECT COUNT(*) FROM Worker WHERE DEPARTMENT = 'Admin';							-- Employees in admin departement
SELECT CONCAT(FIRST_NAME, ' ', LAST_NAME) As Worker_Name, Salary FROM Worker WHERE Salary BETWEEN 50000 AND 100000;
SELECT DEPARTMENT, COUNT(*) no_of_worker FROM Worker 
GROUP BY DEPARTMENT ORDER BY no_of_worker DESC;	-- creating variable and sorting in descending order
-- Workers who are Managers. (both present below are same)
SELECT * FROM Worker WHERE WORKER_ID IN (SELECT WORKER_REF_ID FROM Title where WORKER_TITLE = 'Manager');
SELECT * FROM Worker W INNER JOIN Title T ON W.WORKER_ID = T.WORKER_REF_ID WHERE WORKER_TITLE = 'Manager';
SELECT WORKER_TITLE, AFFECTED_FROM, COUNT(*)  FROM Title GROUP BY WORKER_TITLE, AFFECTED_FROM HAVING COUNT(*)>1;
SELECT * FROM Title WHERE MOD(WORKER_ID, 2) <> 0;							-- Find odd rows
-- Duplicating table
CREATE TABLE TABLE_COPY LIKE Title;
CREATE TABLE TABLE_COPY (SELECT * FROM Title);
SELECT * INTO TABLE_COPY FROM Title;

SELECT * FROM Worker WHERE Worker.WORKER_ID IN (SELECT Title.WORKER_REF_ID FROM Title);				-- Intersection
SELECT * FROM Worker WHERE Worker.WORKER_ID NOT IN (SELECT Title.WORKER_REF_ID FROM Title);			-- Minus. Records that other table does not have
SELECT CURDATE();				-- Current date
SELECT NOW();					-- Current date and time 

SELECT * FROM Worker ORDER BY SALARY DESC LIMIT 10;				-- Get TOP 10 salaries
SELECT * FROM Worker ORDER BY SALARY DESC LIMIT 5,1;			-- get specific "6th" record
SELECT * FROM Worker W1 WHERE 5 = (SELECT COUNT(DISTINCT(SALARY)) -- without using limit
FROM Worker W2 WHERE W1.salary <= W2.salary);  

-- Workers having same salary
Select distinct W.WORKER_ID, W.FIRST_NAME, W.Salary 
from Worker W, Worker W1 
where W.Salary = W1.Salary 
and W.WORKER_ID != W1.WORKER_ID;
SELECT W1.WORKER_ID, W1.FIRST_NAME, W1.SALARY 
FROM Worker W1 INNER JOIN Worker W2 ON W1.WORKER_ID <> W2.WORKER_ID 
WHERE W1.SALARY = W2.SALARY; 

-- Second highest salary
SELECT DISTINCT SALARY FROM Worker ORDER BY SALARY DESC LIMIT 1,1;
Select max(Salary) from Worker where Salary not in (Select max(Salary) from Worker);

SELECT * FROM Worker WHERE WORKER_ID <= (SELECT count(WORKER_ID)/2 from Worker);		-- 50% records of the table
SELECT DEPARTMENT, COUNT(*) FROM Worker GROUP BY DEPARTMENT HAVING COUNT(*) < 5;		-- Department having less than 5 workers
SELECT * FROM Worker WHERE WORKER_ID = (SELECT MAX(WORKER_ID) FROM Worker);				-- Get last row from the table





 