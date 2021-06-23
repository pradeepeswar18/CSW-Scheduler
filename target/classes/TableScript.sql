DROP DATABASE IF EXISTS CSW_SCHEDULE;

CREATE DATABASE CSW_SCHEDULE;

USE CSW_SCHEDULE;

CREATE TABLE Employee (
	employee_id VARCHAR(100) PRIMARY KEY
);

CREATE TABLE Schedule (
	schedule_id INT PRIMARY KEY AUTO_INCREMENT,
	employee_id VARCHAR(100),
	start_date DATE NOT NULL,
	end_date DATE NOT NULL,
	time TIME NOT NULL,
	duration INT NOT NULL,
	is_repeat VARCHAR(10) NOT NULL,
	schedule_frequency VARCHAR(10),
	CONSTRAINT fk_employee FOREIGN KEY (employee_id) REFERENCES Employee(employee_id) 
);

CREATE TABLE ScheduleTrigger (
	schedule_trigger_id INT PRIMARY KEY AUTO_INCREMENT,
	schedule_id INT,
	employee_id VARCHAR(100),
	date DATE NOT NULL,
	time TIME NOT NULL,
	duration INT NOT NULL,
	CONSTRAINT fk_schedule FOREIGN KEY (schedule_id) REFERENCES Schedule(schedule_id) ON DELETE CASCADE,
	CONSTRAINT fk_employee_trigger FOREIGN KEY (employee_id) REFERENCES Employee(employee_id) ON DELETE CASCADE
);



--INSERT INTO Employee VALUES ('brad@gmail.com');
--INSERT INTO Schedule VALUES (1, 'brad@gmail.com', '2021-06-25', '2021-06-29', '10:00:00', 30, 'true', 'DAILY');
--INSERT INTO Employee VALUES (2003, 'Jill', 'jill@mail.com', '1990-11-15','U001');
--INSERT INTO Employee VALUES (2004, 'Albert', 'albert@mail.com', '1988-12-30','U003');
--INSERT INTO Employee VALUES (2005, 'Husee', 'husee@mail.com', '1989-03-22','U001');

COMMIT;

SELECT * FROM Employee;