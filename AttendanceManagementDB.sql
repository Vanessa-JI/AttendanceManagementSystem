DROP DATABASE IF EXISTS attendanceDB;
CREATE DATABASE attendanceDB; -- creating a new database 
USE attendanceDB; -- stating the newly created database is is the database to be used 

-- creating a table called games in the cowsandbulls database with the required fields 
CREATE TABLE student (
studentUsername VARCHAR(20) PRIMARY KEY,
firstName VARCHAR(20) NOT NULL,
lastName VARCHAR(20),
isPresent BOOLEAN DEFAULT false, 
note VARCHAR(250),
password VARCHAR(20) NOT NULL);

-- creating a table called games in the cowsandbulls database with the required fields 
CREATE TABLE teacher (
teacherUsername VARCHAR(20) PRIMARY KEY,
password VARCHAR(20) NOT NULL);

-- creating a table called games in the cowsandbulls database with the required fields 
CREATE TABLE classEntity (
className VARCHAR(20) PRIMARY KEY,
teacherUsername VARCHAR(20),
course VARCHAR(20) NOT NULL, 
date VARCHAR(20) NOT NULL,
CONSTRAINT fk_teacherUsername
		FOREIGN KEY (teacherUsername) -- guestID is a foreign key that links this table to the guests table 
        REFERENCES teacher);

-- creating a table called games in the cowsandbulls database with the required fields 
CREATE TABLE classStudent (
relationshipID INT PRIMARY KEY AUTO_INCREMENT,
className VARCHAR(20) NOT NULL,
studentUsername VARCHAR(20) NOT NULL, 
CONSTRAINT fk_className
		FOREIGN KEY (className) -- guestID is a foreign key that links this table to the guests table 
        REFERENCES classEntity,
CONSTRAINT fk_studentUsername
		FOREIGN KEY (studentUsername) -- guestID is a foreign key that links this table to the guests table 
        REFERENCES student);


-- -- inserting some sample data into the games table 
-- INSERT INTO games (answer, inprogress, win)
-- VALUES 
-- 	ROW ('1234', false, true), 
--     ROW ('4519', false, true),
--     ROW ('9021', false, false);
--     
-- -- displaying all information (all fields for all records) of the games table 
-- SELECT * from Games;