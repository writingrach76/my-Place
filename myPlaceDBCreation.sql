--- Creates Database
CREATE Database myPlaceDB
GO

USE [myPlaceDB]

---Creating a table in which to store a list of users
CREATE TABLE Users (
	UserID INT IDENTITY(1,1) PRIMARY KEY,
	userName VARCHAR(25) FOREIGN KEY REFERENCES myUser(userName),
)

CREATE TABLE myUser (
	userName VARCHAR(25) PRIMARY KEY,
	userPass VARCHAR(15) NOT NULL,
	firstName VARCHAR(25) NOT NULL,
	lastName VARCHAR(25)
)

CREATE TABLE UserPosts (
	postID INT IDENTITY(1,1) PRIMARY KEY,
	postContent VARCHAR(150) NOT NULL
)