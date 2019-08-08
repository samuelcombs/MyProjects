-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jul 22, 2019 at 10:24 PM
-- Server version: 5.7.24
-- PHP Version: 7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mydb`
--

-- --------------------------------------------------------

--
-- Table structure for table `applicant`
--

DROP TABLE IF EXISTS `applicant`;
CREATE TABLE IF NOT EXISTS `applicant` (
  `userID` int(11) NOT NULL,
  `dateadded` date DEFAULT NULL,
  `dateremoved` date DEFAULT NULL,
  `active` tinyint(4) DEFAULT NULL,
  `desiredLocation` int(11) DEFAULT NULL,
  `desiredArea` int(11) DEFAULT NULL,
  PRIMARY KEY (`userID`),
  KEY `fk_userInfo_locations1_idx` (`desiredLocation`),
  KEY `fk_userInfo_departments1_idx` (`desiredArea`),
  KEY `fk_userInfo_users1_idx` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `applicant`
--

INSERT INTO `applicant` (`userID`, `dateadded`, `dateremoved`, `active`, `desiredLocation`, `desiredArea`) VALUES
(3, '2019-07-22', NULL, 1, 7, 14),
(5, '2019-07-11', '2019-07-22', 0, 1, 3),
(6, '2019-07-15', NULL, 1, 7, 8),
(7, '2019-07-16', NULL, 1, 7, 5),
(8, '2019-07-16', NULL, 1, 5, 8),
(9, '2019-07-16', NULL, 1, 1, 10),
(10, '2019-07-16', NULL, 1, 3, 3),
(11, '2019-07-17', NULL, 1, 7, 15),
(12, '2019-07-22', NULL, 1, 2, 6);

-- --------------------------------------------------------

--
-- Table structure for table `departments`
--

DROP TABLE IF EXISTS `departments`;
CREATE TABLE IF NOT EXISTS `departments` (
  `departmentID` int(11) NOT NULL AUTO_INCREMENT,
  `departmentName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`departmentID`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `departments`
--

INSERT INTO `departments` (`departmentID`, `departmentName`) VALUES
(1, '.NET'),
(2, 'Business Analyst'),
(3, 'CAS'),
(4, 'Cloud Technologies : AWS'),
(5, 'Database'),
(6, 'Development: Low Code'),
(7, 'Java'),
(8, 'LIMS'),
(9, 'Mobile'),
(10, 'Not Client Services'),
(11, 'Project Management'),
(12, 'QA Automation'),
(13, 'Quality Assurance'),
(14, 'Salesforce'),
(15, 'SAP-ABAP'),
(16, 'SAP-Basis'),
(17, 'SAP-BI'),
(18, 'SAP-Functional'),
(19, 'SharePoint'),
(20, 'Web');

-- --------------------------------------------------------

--
-- Table structure for table `locations`
--

DROP TABLE IF EXISTS `locations`;
CREATE TABLE IF NOT EXISTS `locations` (
  `locationID` int(11) NOT NULL AUTO_INCREMENT,
  `city` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`locationID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `locations`
--

INSERT INTO `locations` (`locationID`, `city`, `state`) VALUES
(1, 'Albuquerque', 'New Mexico'),
(2, 'Augusta', 'Georgia'),
(3, 'Atlanta', 'Georgia'),
(4, 'Fort Wayne', 'Texas'),
(5, 'Jonesboro', 'Arkansas'),
(6, 'Mobile', 'Alabama'),
(7, 'Oklahoma City', 'Oklahoma');

-- --------------------------------------------------------

--
-- Table structure for table `skills`
--

DROP TABLE IF EXISTS `skills`;
CREATE TABLE IF NOT EXISTS `skills` (
  `skillID` int(11) NOT NULL AUTO_INCREMENT,
  `skillName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`skillID`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `skills`
--

INSERT INTO `skills` (`skillID`, `skillName`) VALUES
(1, '.NET'),
(2, 'ABAP'),
(3, 'Android'),
(4, 'BASIS'),
(5, 'Business Analysis'),
(6, 'C#'),
(7, 'C++'),
(8, 'Data Analysis'),
(9, 'File Maker'),
(10, 'JAVA'),
(11, 'JIRA'),
(12, 'MySQL'),
(13, 'Oracle'),
(14, 'Outsystems'),
(15, 'Project Coordination'),
(16, 'Project Management'),
(17, 'Quality Assurance'),
(18, 'Quick Base'),
(19, 'SalesForce'),
(20, 'Scrum Master'),
(21, 'Selenium'),
(22, 'Sharepoint'),
(23, 'SQL'),
(24, 'Swift'),
(25, 'TFS'),
(26, 'UFT'),
(27, 'Other'),
(30, 'fishing');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `userNumber` varchar(45) DEFAULT NULL,
  `userPassword` varchar(45) DEFAULT NULL,
  `access` varchar(45) DEFAULT NULL,
  `firstName` varchar(45) DEFAULT NULL,
  `middleInitial` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `suffix` varchar(45) DEFAULT NULL,
  `currentWorkLocation` int(11) DEFAULT NULL,
  `currentPracticeArea` int(11) DEFAULT NULL,
  `timeInCurrentProject` varchar(45) DEFAULT NULL,
  `emails` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`userID`),
  KEY `fk_users_locations1_idx` (`currentWorkLocation`),
  KEY `fk_users_departments1_idx` (`currentPracticeArea`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`userID`, `userNumber`, `userPassword`, `access`, `firstName`, `middleInitial`, `lastName`, `suffix`, `currentWorkLocation`, `currentPracticeArea`, `timeInCurrentProject`, `emails`) VALUES
(1, 'C136', 'IAmBatMan', 'Admin', 'bruce', '', 'wayne', '', 2, 2, '2017-04-20', 'testingEmail@teastingEmail.com'),
(2, 'ABC123', 'XYZ789', 'HR', 'jane', 'X', 'doe', 'Y', 4, 8, '2016-04-20', 'testingEmail@teastingEmail.com'),
(3, '8675309', 'IVotedHillary', 'App', 'john', '', 'smith', 'Sr.', 6, 10, '2015-04-20', 'testingEmail@teastingEmail.com'),
(4, 'testing2', 'test2', 'App', 'xxx2', '', 'xx2x', '', 1, 12, '2013-04-20', 'testingEmail@teastingEmail.com'),
(5, 'login1', 'password1', 'App', 'John', '', 'doe', '', 5, 4, '2013-04-20', 'testingEmail@teastingEmail.com'),
(6, 'login2', 'password2', 'App', 'jane', 'X', 'doe', 'Y', 4, 6, '2013-04-20', 'testingEmail@teastingEmail.com'),
(7, 'login3', 'password3', 'App', 'john', '', 'doe', 'Sr.', 6, 13, '2013-04-20', 'testingEmail@teastingEmail.com'),
(8, 'login4', 'password4', 'App', 'jane', '', 'doe', '', 1, 6, '2013-04-20', 'testingEmail@teastingEmail.com'),
(9, 'login5', 'password5', 'App', 'John', '', 'doe', '', 2, 9, '2013-04-20', 'testingEmail@teastingEmail.com'),
(10, 'login6', 'password6', 'App', 'jane', 'X', 'doe', 'Y', 4, 8, '2013-04-20', 'testingEmail@teastingEmail.com'),
(11, 'login7', 'password7', 'App', 'john', '', 'doe', 'Sr.', 6, 15, '2013-04-20', 'testingEmail@teastingEmail.com'),
(12, 'login8', 'password8', 'App', 'jane', '', 'doe', '', 1, 16, '2013-04-20', 'testingEmail@teastingEmail.com'),
(13, 'login9', 'password9', 'App', 'John', '', 'doe', '', 2, 2, '2013-04-20', 'testingEmail@teastingEmail.com'),
(14, 'login10', 'password10', 'App', 'jane', 'X', 'doe', 'Y', 4, 17, '2013-04-20', 'testingEmail@teastingEmail.com'),
(15, 'login11', 'password11', 'App', 'john', '', 'doe', 'Sr.', 5, 18, '2013-04-20', 'testingEmail@teastingEmail.com'),
(16, 'login12', 'password12', 'App', 'jane', '', 'doe', '', 3, 12, '2013-04-20', 'testingEmail@teastingEmail.com'),
(17, 'login13', 'password13', 'App', 'John', '', 'doe', '', 5, 6, '2013-04-20', 'testingEmail@teastingEmail.com'),
(18, 'login14', 'password14', 'App', 'jane', 'X', 'doe', 'Y', 3, 3, '2013-04-20', 'testingEmail@teastingEmail.com'),
(19, 'login15', 'password15', 'App', 'john', '', 'doe', 'Sr.', 2, 2, '2013-04-20', 'testingEmail@teastingEmail.com'),
(20, 'login16', 'password16', 'App', 'jane', '', 'doe', '', 3, 16, '2013-04-20', 'testingEmail@teastingEmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `userskills`
--

DROP TABLE IF EXISTS `userskills`;
CREATE TABLE IF NOT EXISTS `userskills` (
  `userID` int(11) NOT NULL,
  `skillID` int(11) NOT NULL,
  `skillDescription` varchar(200) DEFAULT NULL,
  `timeWithSkill` date DEFAULT NULL,
  KEY `fk_userSkills_users1_idx` (`userID`),
  KEY `fk_userSkills_skills1_idx` (`skillID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `userskills`
--

INSERT INTO `userskills` (`userID`, `skillID`, `skillDescription`, `timeWithSkill`) VALUES
(6, 14, '', '2015-03-16'),
(6, 27, 'Flexin', '2014-02-16'),
(9, 15, '', '2015-03-16'),
(9, 25, '', '2011-12-16'),
(8, 14, '', '2016-04-16'),
(8, 16, '', '2013-01-16'),
(7, 12, '', '2017-05-16'),
(7, 14, '', '2017-05-16'),
(7, 7, '', '2012-04-16'),
(10, 20, '', '2013-07-16'),
(10, 14, '', '2016-11-17'),
(11, 14, '', '2018-02-17'),
(11, 22, '', '2014-03-17'),
(5, 12, '', '2017-05-16'),
(5, 14, '', '2016-04-16'),
(5, 16, '', '2017-05-17'),
(5, 15, '', '2017-05-17'),
(5, 7, '', '2016-04-22'),
(5, 3, '', '2016-04-22'),
(5, 11, '', '2015-03-22'),
(3, 2, '', '2015-03-22'),
(3, 6, '', '2015-02-22'),
(3, 24, '', '2016-04-22'),
(3, 11, '', '2007-07-22'),
(12, 2, '', '2017-05-22'),
(12, 12, '', '2016-04-22'),
(12, 9, '', '2018-06-22'),
(12, 27, 'FOITIN', '2016-04-22');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `applicant`
--
ALTER TABLE `applicant`
  ADD CONSTRAINT `fk_userInfo_departments1` FOREIGN KEY (`desiredArea`) REFERENCES `departments` (`departmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_userInfo_locations1` FOREIGN KEY (`desiredLocation`) REFERENCES `locations` (`locationID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_userInfo_users1` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `fk_users_departments1` FOREIGN KEY (`currentPracticeArea`) REFERENCES `departments` (`departmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_users_locations1` FOREIGN KEY (`currentWorkLocation`) REFERENCES `locations` (`locationID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `userskills`
--
ALTER TABLE `userskills`
  ADD CONSTRAINT `fk_userSkills_skills1` FOREIGN KEY (`skillID`) REFERENCES `skills` (`skillID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_userSkills_users1` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
