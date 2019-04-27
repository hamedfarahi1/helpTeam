-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Apr 17, 2019 at 08:47 PM
-- Server version: 5.6.35
-- PHP Version: 7.0.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: HelpDeskV2
--

-- --------------------------------------------------------

--
-- Table structure for table Admin
--
USE HelpDeskV2;
CREATE TABLE Admin (
  FirstName varchar(20) DEFAULT NULL,
  FamilyName varchar(20) DEFAULT NULL,
  UserName varchar(30) NOT NULL,
  Password varchar(30) NOT NULL,
  Email varchar(30) NOT NULL,
  RoleID int(11) NOT NULL,
  PhoneNumber varchar(15) NOT NULL,
  Id int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table EmailVerfy
--

CREATE TABLE EmailVerfy (
  ID int(11) NOT NULL,
  UserID int(11) NOT NULL,
  Token varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table Question
--

CREATE TABLE Question (
  ID int(10) NOT NULL,
  Question int(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table Role
--

CREATE TABLE Role (
  Id int(11) NOT NULL,
  Name varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table User
--

CREATE TABLE User (
  Id int(11) NOT NULL,
  FirstName varchar(20) NOT NULL,
  FamilyName varchar(20) NOT NULL,
  UserName varchar(20) NOT NULL,
  Password varchar(20) NOT NULL,
  Email varchar(40) NOT NULL,
  PhoneNumber varchar(20) NOT NULL,
  Status int(11) NOT NULL,
  LastLogin timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  AnswerOne varchar(40) NOT NULL,
  AnswerTwo varchar(40) NOT NULL,
  AnswerThree varchar(40) NOT NULL,
  QuestionOneID int(11) NOT NULL,
  QuestionTwoID int(11) NOT NULL,
  QuestionThreeID int(11) NOT NULL,
  LoginCaptchaText varchar(40) NOT NULL,
  AuthCode varchar(40) NOT NULL,
  isEmailVerify tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table Admin
--
ALTER TABLE Admin
  ADD PRIMARY KEY (Id),
  ADD UNIQUE KEY UserName (UserName,PhoneNumber),
  ADD UNIQUE KEY Email (Email),
  ADD KEY RoleID (RoleID);

--
-- Indexes for table EmailVerfy
--
ALTER TABLE EmailVerfy
  ADD PRIMARY KEY (ID),
  ADD KEY fkey1 (UserID);

--
-- Indexes for table Question
--
ALTER TABLE Question
  ADD PRIMARY KEY (ID);

--
-- Indexes for table Role
--
ALTER TABLE Role
  ADD PRIMARY KEY (Id);

--
-- Indexes for table User
--
ALTER TABLE User
  ADD PRIMARY KEY (Id),
  ADD UNIQUE KEY UserName (UserName,PhoneNumber),
  ADD UNIQUE KEY Email (Email),
  ADD KEY qoneID (QuestionOneID),
  ADD KEY qtwoID (QuestionTwoID),
  ADD KEY qthreeID (QuestionThreeID);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table Admin
--
ALTER TABLE Admin
  MODIFY Id int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table Role
--
ALTER TABLE Role
  MODIFY Id int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table User
--
ALTER TABLE User
  MODIFY Id int(11) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Constraints for table Admin
--
ALTER TABLE Admin
  ADD CONSTRAINT RoleID FOREIGN KEY (RoleID) REFERENCES Role (Id);

--
-- Constraints for table EmailVerfy
--
ALTER TABLE EmailVerfy
  ADD CONSTRAINT fkey1 FOREIGN KEY (UserID) REFERENCES User (Id);

--
-- Constraints for table User
--
ALTER TABLE User
  ADD CONSTRAINT qoneID FOREIGN KEY (QuestionOneID) REFERENCES Question (ID),
  ADD CONSTRAINT qthreeID FOREIGN KEY (QuestionThreeID) REFERENCES Question (ID),
  ADD CONSTRAINT qtwoID FOREIGN KEY (QuestionTwoID) REFERENCES Question (ID);