-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 07, 2017 at 09:48 PM
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

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `id_card_number` varchar(50) COLLATE latin1_bin NOT NULL,
  `certificateIssued` varchar(200) COLLATE latin1_bin DEFAULT NULL,
  `active` tinyint(1) DEFAULT NULL,
  `emailAddress` varchar(70) COLLATE latin1_bin DEFAULT NULL,
  `className` varchar(30) COLLATE latin1_bin DEFAULT NULL,
  `dateAdmitted` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`id_card_number`, `certificateIssued`, `active`, `emailAddress`, `className`, `dateAdmitted`) VALUES
('EYY-C3', '', 1, 'email@email.com', NULL, '2017-11-04');

--
-- Triggers `student`
--
DELIMITER $$
CREATE TRIGGER `storeStudentDelete` AFTER DELETE ON `student` FOR EACH ROW BEGIN 
INSERT into studentlog( dateOfOperation, student_Id, operationType)
VALUES( now(), old.id_card_number, 'DELETE');
end
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `storeStudentInsert` AFTER INSERT ON `student` FOR EACH ROW BEGIN 
INSERT into studentlog( dateOfOperation, student_Id, operationType)
VALUES( now(), new.id_card_number, 'INSERT');
end
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `storeStudentUpdate` AFTER UPDATE ON `student` FOR EACH ROW BEGIN 
INSERT into studentlog( dateOfOperation, student_Id, operationType)
VALUES( now(), new.id_card_number, 'DELETE');
end
$$
DELIMITER ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id_card_number`),
  ADD KEY `className` (`className`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `student_ibfk_1` FOREIGN KEY (`className`) REFERENCES `class` (`name`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
