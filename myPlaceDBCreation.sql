--- Creates Database
CREATE Database myPlaceDB
GO

USE [myPlaceDB]

--stores each users info
CREATE TABLE myUser (
	userName VARCHAR(25) PRIMARY KEY,
	userPass VARCHAR(15) NOT NULL,
	firstName VARCHAR(25) NOT NULL,
	lastName VARCHAR(25)
)

--creates a list of users
CREATE TABLE Users (
	UserID INT IDENTITY(1,1) PRIMARY KEY,
	userName VARCHAR(25) FOREIGN KEY REFERENCES myUser(userName),
)

--list of userposts
CREATE TABLE UserPosts (
	postID INT IDENTITY(1,1) PRIMARY KEY,
	userName VARCHAR(25) FOREIGN KEY REFERENCES myUser(userName),
	postContent VARCHAR(MAX) NOT NULL
)


-- Will show everything in myUser table, for testing purposes
SELECT *
FROM myUser

SELECT *
FROM Users

USE [myPlaceDB]
INSERT INTO myUSER (userName, userPass, firstName, lastName) VALUES ('testuser', 'testuser', 'test', 'user')

DELETE FROM myUser where userName = 'testuser'