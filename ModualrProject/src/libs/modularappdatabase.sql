-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 06, 2017 at 08:53 AM
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
-- Functions
--
CREATE DEFINER=`root`@`localhost` FUNCTION `isPaymentComplete` (`registerId` INT) RETURNS TINYINT(1) BEGIN
	SELECT sum( payment.amount ) FROM payment 
    WHERE payment.moduleRegisterId =  registerId
        INTO @totalAmountPaid;
	select module_name FROM module_register 
    	where id = registerId
        INTO @modName;
    SELECT ( module.units * module.amuntPerUnit) FROM module 
    	WHERE name = @modName INTO @totalAmountPaid;
    
    if @totalAmount <= @totalAmountPaid THEN
    	RETURN true;
    ELSE 
    	return false;
    END IF;
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
-- Table structure for table `biodata`
--

CREATE TABLE `biodata` (
  `studentId` varchar(50) COLLATE latin1_bin DEFAULT NULL,
  `firstName` varchar(50) COLLATE latin1_bin NOT NULL,
  `lastName` varchar(50) COLLATE latin1_bin NOT NULL,
  `homeAddress` varchar(200) COLLATE latin1_bin NOT NULL,
  `stateOfOrigin` varchar(100) COLLATE latin1_bin NOT NULL,
  `country` varchar(100) COLLATE latin1_bin NOT NULL,
  `gender` varchar(6) COLLATE latin1_bin NOT NULL,
  `dateOfBirth` date NOT NULL,
  `placeOfBirth` varchar(100) COLLATE latin1_bin NOT NULL,
  `image` longblob
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

-- --------------------------------------------------------

--
-- Table structure for table `certificate`
--

CREATE TABLE `certificate` (
  `id` int(11) NOT NULL,
  `dateCreated` date DEFAULT NULL,
  `name` varchar(200) COLLATE latin1_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

--
-- Dumping data for table `certificate`
--

INSERT INTO `certificate` (`id`, `dateCreated`, `name`) VALUES
(2, '2017-11-04', 'Modular Automation'),
(3, '2017-11-04', 'Electrotechnics');

--
-- Triggers `certificate`
--
DELIMITER $$
CREATE TRIGGER `storeCertificateUpdate` BEFORE UPDATE ON `certificate` FOR EACH ROW BEGIN 
INSERT INTO certificatelog ( dateOfOperation, 
operationType,certificateName) 
VALUES (now(), 'UPDATE', old.id ) ;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `updateDeleteCertificateLog` AFTER DELETE ON `certificate` FOR EACH ROW BEGIN
INSERT INTO certificatelog( dateOfOperation, operationType, certificateName)
VALUES( now(), 'DELETE', old.name) ;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `updateInsertCertificateLog` AFTER INSERT ON `certificate` FOR EACH ROW BEGIN
INSERT INTO certificatelog( dateOfOperation, operationType, certificateName)
VALUES( now(), 'INSERT', new.name) ;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `certificatelog`
--

CREATE TABLE `certificatelog` (
  `dateOfOperation` date DEFAULT NULL,
  `operationType` varchar(100) COLLATE latin1_bin DEFAULT NULL,
  `certificateName` varchar(100) COLLATE latin1_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

--
-- Dumping data for table `certificatelog`
--

INSERT INTO `certificatelog` (`dateOfOperation`, `operationType`, `certificateName`) VALUES
('2017-11-04', 'INSERT', 'Modular Automation'),
('2017-11-04', 'DELETE', 'Modular Automation'),
('2017-11-04', 'INSERT', 'Modular Automation'),
('2017-11-04', 'INSERT', 'Electrotechnics');

-- --------------------------------------------------------

--
-- Table structure for table `certificatemodule`
--

CREATE TABLE `certificatemodule` (
  `certificateId` int(11) DEFAULT NULL,
  `moduleName` varchar(100) COLLATE latin1_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

-- --------------------------------------------------------

--
-- Table structure for table `class`
--

CREATE TABLE `class` (
  `name` varchar(30) COLLATE latin1_bin NOT NULL,
  `dateCreated` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

--
-- Dumping data for table `class`
--

INSERT INTO `class` (`name`, `dateCreated`) VALUES
('Stream 0', '2017-11-04');

-- --------------------------------------------------------

--
-- Table structure for table `module`
--

CREATE TABLE `module` (
  `name` varchar(100) COLLATE latin1_bin NOT NULL,
  `units` int(11) NOT NULL,
  `amuntPerUnit` double NOT NULL DEFAULT '0',
  `isFree` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

--
-- Dumping data for table `module`
--

INSERT INTO `module` (`name`, `units`, `amuntPerUnit`, `isFree`) VALUES
('Electrical Installation', 5, 0, 0);

--
-- Triggers `module`
--
DELIMITER $$
CREATE TRIGGER `storeModuleDelete` BEFORE DELETE ON `module` FOR EACH ROW BEGIN 
INSERT INTO modulelog ( dateOfOperation, 
operationType, moduleName) 
VALUES (now(), 'DELEETE', old.name ) ;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `storeModuleInsert` BEFORE INSERT ON `module` FOR EACH ROW BEGIN 
INSERT INTO modulelog ( dateOfOperation, 
operationType, moduleName) 
VALUES (now(), 'INSERT', new.name ) ;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `storeModuleUpdate` BEFORE UPDATE ON `module` FOR EACH ROW BEGIN 
INSERT INTO modulelog ( dateOfOperation, 
operationType, moduleName) 
VALUES (now(), 'UPDATE', old.name ) ;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `modulelog`
--

CREATE TABLE `modulelog` (
  `dateOfOperation` date DEFAULT NULL,
  `moduleName` varchar(100) COLLATE latin1_bin DEFAULT NULL,
  `operationType` varchar(20) COLLATE latin1_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

-- --------------------------------------------------------

--
-- Table structure for table `module_register`
--

CREATE TABLE `module_register` (
  `id` int(11) NOT NULL,
  `dateRegistered` date DEFAULT NULL,
  `module_name` varchar(100) COLLATE latin1_bin DEFAULT NULL,
  `student_id` varchar(50) COLLATE latin1_bin NOT NULL,
  `bookingStatus` tinyint(1) DEFAULT NULL,
  `result` varchar(4) COLLATE latin1_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

--
-- Dumping data for table `module_register`
--

INSERT INTO `module_register` (`id`, `dateRegistered`, `module_name`, `student_id`, `bookingStatus`, `result`) VALUES
(2, '2017-10-30', 'Electrical Installation', 'EYY-C3', 1, 'Pass');

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `id` int(11) NOT NULL,
  `moduleRegisterId` int(11) DEFAULT NULL,
  `amount` double NOT NULL,
  `bankName` varchar(200) COLLATE latin1_bin DEFAULT NULL,
  `tellerNumber` int(20) DEFAULT NULL,
  `dateOfPayment` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

-- --------------------------------------------------------

--
-- Table structure for table `paymentlog`
--

CREATE TABLE `paymentlog` (
  `dateofoperation` date DEFAULT NULL,
  `operationType` varchar(30) COLLATE latin1_bin DEFAULT NULL,
  `studentId` varchar(30) COLLATE latin1_bin DEFAULT NULL,
  `moduleName` varchar(30) COLLATE latin1_bin DEFAULT NULL,
  `amount` double DEFAULT NULL
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
('EYY-C3', '08021213322'),
('EYY-C3', '0843030232'),
('EYY-C3', '03202049204');

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
('EYY-C3', NULL, 1, 'email@email.com', 'Stream 0', '2017-11-04');

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

-- --------------------------------------------------------

--
-- Table structure for table `studentlog`
--

CREATE TABLE `studentlog` (
  `dateOfOperation` date DEFAULT NULL,
  `operationType` varchar(10) COLLATE latin1_bin DEFAULT NULL,
  `student_id` varchar(50) COLLATE latin1_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

--
-- Dumping data for table `studentlog`
--

INSERT INTO `studentlog` (`dateOfOperation`, `operationType`, `student_id`) VALUES
('2017-11-04', 'INSERT', 'EYY-C3');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `biodata`
--
ALTER TABLE `biodata`
  ADD KEY `studentId` (`studentId`);

--
-- Indexes for table `certificate`
--
ALTER TABLE `certificate`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `certificatemodule`
--
ALTER TABLE `certificatemodule`
  ADD KEY `certificateLink` (`certificateId`),
  ADD KEY `moduleLink` (`moduleName`);

--
-- Indexes for table `class`
--
ALTER TABLE `class`
  ADD PRIMARY KEY (`name`);

--
-- Indexes for table `module`
--
ALTER TABLE `module`
  ADD PRIMARY KEY (`name`);

--
-- Indexes for table `module_register`
--
ALTER TABLE `module_register`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `uniqueColumns` (`student_id`,`id`,`module_name`),
  ADD KEY `module_foreign` (`module_name`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `phone`
--
ALTER TABLE `phone`
  ADD KEY `phoneLink` (`student_id`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id_card_number`),
  ADD KEY `classLink` (`className`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `certificate`
--
ALTER TABLE `certificate`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `module_register`
--
ALTER TABLE `module_register`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `biodata`
--
ALTER TABLE `biodata`
  ADD CONSTRAINT `biodata_ibfk_1` FOREIGN KEY (`studentId`) REFERENCES `student` (`id_card_number`);

--
-- Constraints for table `certificatemodule`
--
ALTER TABLE `certificatemodule`
  ADD CONSTRAINT `certificateLink` FOREIGN KEY (`certificateId`) REFERENCES `certificate` (`id`),
  ADD CONSTRAINT `moduleLink` FOREIGN KEY (`moduleName`) REFERENCES `module` (`name`);

--
-- Constraints for table `module_register`
--
ALTER TABLE `module_register`
  ADD CONSTRAINT `module_foreign` FOREIGN KEY (`module_name`) REFERENCES `module` (`name`);

--
-- Constraints for table `phone`
--
ALTER TABLE `phone`
  ADD CONSTRAINT `phoneLink` FOREIGN KEY (`student_id`) REFERENCES `student` (`id_card_number`);

--
-- Constraints for table `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `classLink` FOREIGN KEY (`className`) REFERENCES `class` (`name`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
