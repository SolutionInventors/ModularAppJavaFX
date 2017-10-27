-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 27, 2017 at 05:00 PM
-- Server version: 10.1.26-MariaDB
-- PHP Version: 7.1.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `modularappdatabase`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllModules` ()  BEGIN

SELECT * FROM module;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllStudents` ()  BEGIN
	SELECT CONCAT( firstName , ' ' , lastName ) as Name, 
           id_card_Number as 'ID Card Number' , 
           emailAddress as 'Email', 
           active as Active,
           phone.phone_number as 'Phone Number'
     FROM student
     JOIN phone 
     	ON phone.student_id = student.id_card_number;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getStudent` (IN `card_number` VARCHAR(50))  BEGIN
SELECT * FROM student 
	WHERE id_card_number = card_number;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertAdmin` (IN `newUser` VARCHAR(200), IN `pass` VARCHAR(500))  BEGIN
	INSERT INTO admin( username, password) 
    VALUES( newUser, pass);
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertModule` (IN `theName` VARCHAR(100), IN `numOfUnits` INT)  BEGIN
INSERT INTO module( name, units ) 
VALUES( theName , numOfUnits);

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertPhone` (IN `id` VARCHAR(200), IN `phoneNumber` VARCHAR(20))  BEGIN
	INSERT INTO phone( student_id, phone_number) 
    VALUES( id , phoneNumber);
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertPhoneNumber` (IN `cardNumber` VARCHAR(50), IN `newPhone` VARCHAR(20))  BEGIN
SELECT COUNT(id_card_number) FROM student
 WHERE cardNumber = id_card_number INTO @total;
if @total > 0 THEN
	INSERT INTO phone( student_id, phone_number) 
    VALUES(  id_cardNumber, newPhone);
END IF;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertStudent` (IN `card_number` VARCHAR(50), IN `fName` VARCHAR(30), IN `lName` VARCHAR(30), IN `email` VARCHAR(30))  BEGIN
INSERT INTO 
student( id_card_number, firstName, lastName, active, emailAddress ) 
VALUES( card_number, fName, lName, true, email);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateAdminPassword` (IN `theUser` VARCHAR(200), IN `oldPass` VARCHAR(500), IN `newPass` VARCHAR(500))  BEGIN
	UPDATE admin SET password = newPass
    WHERE username = theUser AND password = oldPass;
end$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `username` varchar(200) COLLATE latin1_bin NOT NULL,
  `password` varchar(500) COLLATE latin1_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`username`, `password`) VALUES('Og', 'Chidi@chi.com');
INSERT INTO `admin` (`username`, `password`) VALUES('Oguejifor', 'Chidi@chi.com');
INSERT INTO `admin` (`username`, `password`) VALUES('chi', 'fred');

-- --------------------------------------------------------

--
-- Table structure for table `module`
--

CREATE TABLE `module` (
  `id` int(11) NOT NULL,
  `name` varchar(100) COLLATE latin1_bin DEFAULT NULL,
  `units` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

-- --------------------------------------------------------

--
-- Table structure for table `module_status`
--

CREATE TABLE `module_status` (
  `module_id` int(11) DEFAULT NULL,
  `student_id` varchar(50) COLLATE latin1_bin DEFAULT NULL,
  `paymentStatus` tinyint(1) DEFAULT NULL,
  `bookingStatus` tinyint(1) DEFAULT NULL,
  `attended` tinyint(1) DEFAULT NULL,
  `resuult` varchar(4) COLLATE latin1_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

-- --------------------------------------------------------

--
-- Table structure for table `phone`
--

CREATE TABLE `phone` (
  `student_id` varchar(50) COLLATE latin1_bin DEFAULT NULL,
  `phone_number` varchar(20) COLLATE latin1_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

--
-- Dumping data for table `phone`
--

INSERT INTO `phone` (`student_id`, `phone_number`) VALUES('EYY-C3', '80222192');
INSERT INTO `phone` (`student_id`, `phone_number`) VALUES('EYY-C3', '08021213322');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `id_card_number` varchar(50) COLLATE latin1_bin NOT NULL,
  `firstName` varchar(30) COLLATE latin1_bin DEFAULT NULL,
  `lastName` varchar(30) COLLATE latin1_bin DEFAULT NULL,
  `active` tinyint(1) DEFAULT NULL,
  `emailAddress` varchar(150) COLLATE latin1_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`id_card_number`, `firstName`, `lastName`, `active`, `emailAddress`) VALUES('EYY-C3', 'Chidiebere', 'Steven', 0, 'professorchidi@yahoo.com');
INSERT INTO `student` (`id_card_number`, `firstName`, `lastName`, `active`, `emailAddress`) VALUES('Ety-C32', 'Chidi', 'Fres', 1, 'Chidi@Chidi');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `module`
--
ALTER TABLE `module`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `module_status`
--
ALTER TABLE `module_status`
  ADD KEY `module_id` (`module_id`),
  ADD KEY `student_id` (`student_id`);

--
-- Indexes for table `phone`
--
ALTER TABLE `phone`
  ADD KEY `student_id` (`student_id`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id_card_number`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `module`
--
ALTER TABLE `module`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `module_status`
--
ALTER TABLE `module_status`
  ADD CONSTRAINT `module_status_ibfk_1` FOREIGN KEY (`module_id`) REFERENCES `module` (`id`),
  ADD CONSTRAINT `module_status_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`id_card_number`);

--
-- Constraints for table `phone`
--
ALTER TABLE `phone`
  ADD CONSTRAINT `phone_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id_card_number`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
