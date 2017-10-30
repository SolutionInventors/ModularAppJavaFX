-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 30, 2017 at 08:18 AM
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
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteAdmin` (IN `admin_username` VARCHAR(100))  BEGIN 
DELETE FROM admin
WHERE admin.username = admin_username ;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteModule` (IN `theName` VARCHAR(100))  BEGIN
DELETE FROM module 
WHERE name = theName;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `deletePhone` (IN `studentId` VARCHAR(20), `number` VARCHAR(20))  BEGIN
DELETE from phone
where phone.student_id  = studentId and phone.phone_number = number;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getActiveStudentsCount` ()  BEGIN
SELECT COUNT(*) from student WHERE active= true ;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getAdmin` (IN `name` VARCHAR(30), IN `pass` VARCHAR(30))  NO SQL
begin
SELECT username, password FROM admin
	WHERE username = name AND password = pass ;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllActiveStudents` (IN `startIndex` INT)  BEGIN
	SELECT CONCAT( firstName , ' ' , lastName ) as Name, 
           id_card_Number as 'ID Card Number' , 
           emailAddress as 'Email', 
           active as Active
     FROM student
     where active = true
     LIMIT startIndex, 30;
        
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllAdminFrom` (IN `startIndex` INT)  NO SQL
BEGIN 
SELECT* FROM admin
LIMIT startIndex, 30 ;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllInactiveStudents` (IN `startIndex` INT)  BEGIN
	SELECT CONCAT( firstName , ' ' , lastName ) as Name, 
           id_card_Number as 'ID Card Number' , 
           emailAddress as 'Email', 
           active as Active
     FROM student
     where active = false
     LIMIT startIndex, 30;
        
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllModules` (IN `startIndex` INT)  BEGIN

SELECT * FROM module
limit startIndex, 30; 
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllPhone` (IN `id` VARCHAR(20))  BEGIN
SELECT * from phone
where phone.student_id = id;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getInactiveStudentsCount` ()  BEGIN
SELECT COUNT(*) from student WHERE active= false ;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getModule` (IN `searchName` VARCHAR(100))  BEGIN
SELECT * from module where name = searchName;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getStudent` (IN `card_number` VARCHAR(50))  BEGIN
SELECT * FROM student 
	WHERE id_card_number = card_number;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getTotalAdmin` ()  BEGIN
SELECT COUNT(*) as total FROM admin;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getTotalModule` ()  BEGIN 
SELECT count( *) as Total from module;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertAdmin` (IN `newUser` VARCHAR(200), IN `pass` VARCHAR(500))  BEGIN
	INSERT INTO admin( username, password) 
    VALUES( newUser, pass);
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertModule` (IN `theName` VARCHAR(100), IN `numOfUnits` INT)  BEGIN
INSERT INTO module( name, units ) 
VALUES( TRIM(theName) , numOfUnits);

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

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateAdmin` (IN `oldUsername` VARCHAR(20), IN `oldPass` VARCHAR(20), IN `newUserName` VARCHAR(20), IN `newPass` VARCHAR(20))  BEGIN
	UPDATE admin SET username = newUserName, password = newPass
    
     WHERE username = oldUsername AND password = oldPass;
    
   
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateModule` (IN `oldName` VARCHAR(100), IN `newName` VARCHAR(100), `newUnits` INT)  BEGIN 
	UPDATE module  set name = newName , units = newUnits 
    	where name = oldName ;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateStudent` (IN `cardNum` VARCHAR(50), IN `fName` VARCHAR(30), IN `lName` VARCHAR(30), IN `isActive` BOOLEAN, `email` VARCHAR(150))  BEGIN
UPDATE `student` SET `id_card_number`=cardNum  ,`firstName`=fName,`lastName`=lName ,`active`=isActive,`emailAddress`=email WHERE student.id_card_number = cardNum;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `username` varchar(200) COLLATE latin1_bin NOT NULL,
  `password` varchar(500) COLLATE latin1_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`username`, `password`) VALUES
('chidiebere', 'fred'),
('pwd', 'Chidi@chi.com');

-- --------------------------------------------------------

--
-- Table structure for table `module`
--

CREATE TABLE `module` (
  `name` varchar(100) COLLATE latin1_bin NOT NULL,
  `units` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

--
-- Dumping data for table `module`
--

INSERT INTO `module` (`name`, `units`) VALUES
('Electrical Installation', 5);

-- --------------------------------------------------------

--
-- Table structure for table `module_status`
--

CREATE TABLE `module_status` (
  `modue_name` varchar(100) COLLATE latin1_bin NOT NULL,
  `student_id` varchar(50) COLLATE latin1_bin DEFAULT NULL,
  `paymentStatus` tinyint(1) NOT NULL,
  `bookingStatus` tinyint(1) NOT NULL,
  `attended` tinyint(1) NOT NULL,
  `resuult` varchar(4) COLLATE latin1_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

-- --------------------------------------------------------

--
-- Table structure for table `phone`
--

CREATE TABLE `phone` (
  `student_id` varchar(50) COLLATE latin1_bin DEFAULT NULL,
  `phone_number` varchar(20) COLLATE latin1_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

--
-- Dumping data for table `phone`
--

INSERT INTO `phone` (`student_id`, `phone_number`) VALUES
('EYY-C3', '08021213322');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `id_card_number` varchar(50) COLLATE latin1_bin NOT NULL,
  `firstName` varchar(30) COLLATE latin1_bin NOT NULL,
  `lastName` varchar(30) COLLATE latin1_bin NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '0',
  `emailAddress` varchar(150) COLLATE latin1_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`id_card_number`, `firstName`, `lastName`, `active`, `emailAddress`) VALUES
('ChidiETY ', 'chidi', 'fred', 0, 'emai@email.com'),
('EYY-C3', 'Chidiebere', 'Steven', 0, 'professorchidi@yahoo.com'),
('Ety-C32', 'Chidi', 'Fres', 1, 'Chidi@Chidi');

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
  ADD PRIMARY KEY (`name`);

--
-- Indexes for table `module_status`
--
ALTER TABLE `module_status`
  ADD KEY `student_id` (`student_id`),
  ADD KEY `modue_name` (`modue_name`);

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
-- Constraints for dumped tables
--

--
-- Constraints for table `module_status`
--
ALTER TABLE `module_status`
  ADD CONSTRAINT `module_status_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`id_card_number`),
  ADD CONSTRAINT `module_status_ibfk_3` FOREIGN KEY (`modue_name`) REFERENCES `module` (`name`);

--
-- Constraints for table `phone`
--
ALTER TABLE `phone`
  ADD CONSTRAINT `phone_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id_card_number`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
