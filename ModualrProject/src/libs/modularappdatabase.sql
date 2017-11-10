-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 10, 2017 at 08:21 AM
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
CREATE DEFINER=`root`@`localhost` PROCEDURE `addModToCert` (IN `certName` VARCHAR(200), IN `modName` VARCHAR(100))  BEGIN
INSERT INTO `certificatemodule`(`certificateName`, `moduleName`) 
VALUES ( TRIM(certName), TRIM(modName) );
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `addPhoneNumber` (IN `studId` VARCHAR(50), IN `number` VARCHAR(20))  BEGIN 
INSERT INTO `phone`(`student_id`, `phone_number`) 
VALUES (studId, number );
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `createCertificate` (IN `certName` VARCHAR(200), OUT `dateCreated` INT)  BEGIN
Set @dateCreated = now();

INSERT INTO `certificate`
( `dateCreated`, `name`) 
VALUES ( @dateCreated , TRIM(certName));
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `createNewClass` (IN `name` VARCHAR(30), OUT `creationDate` DATE)  BEGIN
set creationDate = now();

INSERT INTO `class`(`name`, `dateCreated`)
VALUES (name ,creationDate);
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `createNewModule` (IN `modName` VARCHAR(100), IN `numOfUnits` INT, IN `price` DOUBLE, OUT `creationDate` DATE)  BEGIN
set creationDate = NOW();
INSERT INTO `module`(`dateCreated`, `name`, `units`, `amountPerUnit`) 
VALUES (creationDate,modName,numOfUnits,price);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteCertificate` (IN `certName` VARCHAR(200))  BEGIN 
    DELETE FROM `certificate` WHERE `name` = certName;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getAdmin` (IN `user` VARCHAR(200), IN `pass` VARCHAR(100))  BEGIN
SELECT * FROM admin 
	WHERE admin.username =  user  AND admin.password = pass;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getCertificateByDate` (IN `sinceDate` DATE, IN `startIndex` INT)  BEGIN 
 SELECT * FROM certificate 
 WHERE dateCreated > sinceDate
 LIMIT startIndex , 30 ;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getCertificatesByIndex` (IN `startIndex` INT)  BEGIN
	SELECT * FROM certificate 
    LIMIT startIndex , 30 ;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getCertModulesByIndex` (IN `startIndex` INT)  BEGIN
SELECT * FROM certificatemodule 
LIMIT startIndex, 30 ;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getClass` (IN `name` VARCHAR(30))  BEGIN
SELECT * FROM  class 
	WHERE class.name =  TRIM(name) ;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getClassByDate` (IN `creationDate` DATE, IN `startIndex` INT)  BEGIN
SELECT * FROM class 
	where class.dateCreated < creationDate 
    LIMIT startIndex , 0 ;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getClassesByIndex` (IN `startIndex` INT)  BEGIN
SELECT * FROM class 
LIMIT startIndex , 30 ;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getModuleByIndex` (IN `startIndex` INT)  BEGIN
SELECT * FROM module
LIMIT startIndex , 30;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertNewStudent` (IN `eAddress` VARCHAR(70), IN `theClassName` VARCHAR(30), IN `cardNumber` VARCHAR(50))  BEGIN
INSERT INTO `student`(`id_card_number`, `certificateIssued`, `active`, `emailAddress`, `className`, `dateAdmitted`) 
VALUES (cardNumber,NULL, true ,TRIM(eAddress), TRIM(theClassName) ,NOW() );
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `makePayment` (IN `modRegisterId` INT, IN `amountPaid` DOUBLE, IN `bankName` VARCHAR(200), IN `tellerNumber` INT(20), IN `paymentDate` DATE)  BEGIN

set @payComplete = isPaymentComplete(modRegisterId) ;
IF @paymentComplete =  0 THEN 
INSERT INTO `payment`( `moduleRegisterId`, `amount`, `bankName`, `tellerNumber`, `dateOfPayment`) 
VALUES (modRegisterId ,amountPaid,bankName,tellerNumber,paymentDate );
END  IF ;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `removeCertificate` (IN `certName` VARCHAR(200))  BEGIN
DELETE FROM `certificate` 
WHERE certificate.name = certName;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `removeClass` (IN `name` VARCHAR(30))  BEGIN
DELETE FROM `class`
	WHERE class.name =  TRIM(name) ;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `removeModFromCert` (IN `certName` VARCHAR(200), IN `modName` VARCHAR(100))  BEGIN
DELETE FROM `certificatemodule`
WHERE `certificateName` = certName AND `moduleName` = modName ; 
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `removeModule` (IN `moduleName` VARCHAR(100))  BEGIN 
DELETE FROM `module` WHERE module.name = moduleName;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `removePhoneNumber` (IN `studId` VARCHAR(50), IN `number` VARCHAR(20))  BEGIN 
DELETE FROM phone  
	WHERE phone.student_id = studId AND phone.phone_number = number;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `removeStudent` (IN `id` VARCHAR(50))  BEGIN
DELETE FROM student WHERE id_card_number =  id;
 END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateAdmin` (IN `oldUsername` VARCHAR(200), IN `oldPass` VARCHAR(500), IN `newUsername` VARCHAR(200), IN `newPass` VARCHAR(500))  BEGIN 
UPDATE `admin`
SET `username`=TRIM(newUsername) ,`password`=newPass 
	WHERE username = oldUsername AND password = oldPass ;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateCertificate` (IN `oldCertName` VARCHAR(200), IN `newName` VARCHAR(200))  BEGIN
UPDATE `certificate` 
SET `name`= TRIM(newName) WHERE oldCertName = `name` ;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateClass` (IN `oldClassName` VARCHAR(100), IN `newClassName` VARCHAR(100), OUT `creationDate` DATE)  BEGIN
update class SET class.name = newClassName
WHERE class.name = oldClassName;
SELECT class.dateCreated from class
	WHERE class.name = newClassName into @date;
SET creationDate = @date;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateModule` (IN `oldName` VARCHAR(100), IN `newName` VARCHAR(100), IN `numOfUnits` INT, IN `unitPrice` DOUBLE, OUT `creationDate` DATE)  BEGIN
SELECT dateCreated FROM module 
	WHERE module.name =  oldName INTO creationDate;
UPDATE `module`
SET `name`= newName,`units`= numOfUnits,`amountPerUnit`=unitPrice
	WHERE module.name =  oldName;
    

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updatePhone` (IN `studId` VARCHAR(50), IN `oldNumber` VARCHAR(20), IN `newNumber` VARCHAR(20))  BEGIN 
UPDATE `phone` 
SET `phone_number`= newNumber 
WHERE `student_id`= studId ; 
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateStudent` (IN `oldStudentId` VARCHAR(50), IN `certIssued` VARCHAR(200), IN `active` BOOLEAN, IN `mail` VARCHAR(70), OUT `admissionDate` DATE)  BEGIN
UPDATE `student` 
SET `certificateIssued`= certIssued,
`active`=active ,`emailAddress`=mail
 WHERE oldStudentId = id_card_number;
 
 SELECT dateAdmitted FROM student 
 	WHERE id_card_number = oldStudentId INTO admissionDate;
 END$$

--
-- Functions
--
CREATE DEFINER=`root`@`localhost` FUNCTION `isPaymentComplete` (`modRegId` INT) RETURNS TINYINT(1) BEGIN
	SELECT sum( payment.amount ) FROM payment 
    WHERE payment.moduleRegisterId =  modRegId
        INTO @totalAmountPaid;
	select module_name FROM module_register 
    	where id = modRegId
        INTO @modName;
    SELECT ( module.units * module.amuntPerUnit) FROM module 
    	WHERE name = @modName INTO @totalAmountPaid;
    
    if @totalAmount <= @totalAmountPaid THEN
    	RETURN true;
    ELSE 
    	return false;
    END IF;
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `validateAdmin` (`uName` VARCHAR(200), `pass` VARCHAR(500)) RETURNS TINYINT(1) begin 
	SELECT COUNT(*) FROM admin where username = uName AND password = uPass  into @numOfOccurrence;
    IF @numOfOccurrence=  1 THEN 
    	RETURN true;
    ELSE 
    	RETURN false;
    END IF ;
end$$

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
('Chidiebere', 'Fred'),
('pwd', 'Chidi@chi.com');

-- --------------------------------------------------------

--
-- Table structure for table `biodata`
--

CREATE TABLE `biodata` (
  `studentId` varchar(50) COLLATE latin1_bin DEFAULT NULL,
  `Surname` varchar(50) COLLATE latin1_bin NOT NULL,
  `MiddleName` varchar(50) COLLATE latin1_bin NOT NULL,
  `LastName` int(11) NOT NULL,
  `PermanentAddress` varchar(2000) COLLATE latin1_bin NOT NULL,
  `CurrentAddress` varchar(200) COLLATE latin1_bin NOT NULL,
  `Religion` varchar(100) COLLATE latin1_bin NOT NULL,
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
  `dateCreated` date DEFAULT NULL,
  `name` varchar(200) COLLATE latin1_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

--
-- Dumping data for table `certificate`
--

INSERT INTO `certificate` (`dateCreated`, `name`) VALUES
('2017-11-08', 'Electromecnics'),
('2017-11-04', 'Electrotechnics'),
('2017-11-08', 'Mecha tronics');

--
-- Triggers `certificate`
--
DELIMITER $$
CREATE TRIGGER `storeCertificateUpdate` BEFORE UPDATE ON `certificate` FOR EACH ROW BEGIN 
INSERT INTO certificatelog ( dateOfOperation, 
operationType,certificateName) 
VALUES (now(), 'UPDATE', old.name ) ;
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
('2017-11-04', 'INSERT', 'Electrotechnics'),
('2017-11-06', 'UPDATE', '2'),
('2017-11-06', 'DELETE', 'Mod Auto'),
('2017-11-08', 'INSERT', 'Modular Mecatronics'),
('2017-11-08', 'INSERT', 'Modular Automation'),
('2017-11-08', 'DELETE', 'Modular Mecatronics'),
('2017-11-08', 'INSERT', 'Mod Mecatronics'),
('2017-11-08', 'DELETE', 'Modular Automation'),
('2017-11-08', 'INSERT', 'Mod Mech'),
('2017-11-08', 'DELETE', 'Mod Mech'),
('2017-11-08', 'INSERT', 'Electromecnics'),
('2017-11-08', 'DELETE', 'Mod Mecatronics'),
('2017-11-08', 'INSERT', 'Mecha'),
('2017-11-08', 'INSERT', 'Mechan'),
('2017-11-08', 'DELETE', 'Mecha'),
('2017-11-08', 'UPDATE', 'Electrotechnics'),
('2017-11-08', 'INSERT', 'Name'),
('2017-11-08', 'DELETE', 'Name'),
('2017-11-08', 'UPDATE', 'Mechan'),
('2017-11-08', 'UPDATE', 'Mecha'),
('2017-11-08', 'INSERT', 'Mecha tronics'),
('2017-11-08', 'DELETE', 'Mechana'),
('2017-11-08', 'UPDATE', 'ElectrotechnicSA');

-- --------------------------------------------------------

--
-- Table structure for table `certificatemodule`
--

CREATE TABLE `certificatemodule` (
  `certificateName` varchar(100) COLLATE latin1_bin DEFAULT NULL,
  `moduleName` varchar(100) COLLATE latin1_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

--
-- Dumping data for table `certificatemodule`
--

INSERT INTO `certificatemodule` (`certificateName`, `moduleName`) VALUES
('Electromecnics', 'Electrical Installation'),
('Electromecnics', 'Ethics'),
('Electromecnics', 'Motor Controls'),
('Electrotechnics', 'Electrical Installation'),
('Electrotechnics', 'Motor Controls');

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
('Stream 1', '2017-11-08'),
('Stream 2', '2017-11-07'),
('Stream 3', '2017-11-08'),
('Stream 5', '2017-11-08');

--
-- Triggers `class`
--
DELIMITER $$
CREATE TRIGGER `classUpdateTrigger` AFTER UPDATE ON `class` FOR EACH ROW BEGIN
	UPDATE `student` SET `className`=  new.name WHERE `className` = old.name;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `deleteClassTrigger` BEFORE DELETE ON `class` FOR EACH ROW BEGIN
UPDATE student SET student.className = NULL 
	WHERE student.className = old.name;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `educational_background`
--

CREATE TABLE `educational_background` (
  `StudentId` varchar(50) COLLATE latin1_bin NOT NULL,
  `BeginDate` date NOT NULL,
  `EndDate` date NOT NULL,
  `Institution` varchar(200) COLLATE latin1_bin NOT NULL,
  `CourseRead` varchar(200) COLLATE latin1_bin NOT NULL,
  `QualificationName` varchar(200) COLLATE latin1_bin NOT NULL
) ENGINE=CSV DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

-- --------------------------------------------------------

--
-- Table structure for table `module`
--

CREATE TABLE `module` (
  `dateCreated` date DEFAULT NULL,
  `name` varchar(100) COLLATE latin1_bin NOT NULL,
  `units` int(11) NOT NULL,
  `amountPerUnit` double NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

--
-- Dumping data for table `module`
--

INSERT INTO `module` (`dateCreated`, `name`, `units`, `amountPerUnit`) VALUES
('2017-11-09', 'Eating Etiquette', 10, 8000),
('2017-11-08', 'Electrical Installation', 10, 8000),
('2017-11-08', 'Ethics', 6, 6500),
('2017-11-09', 'Motor Controls', 7, 4000),
('2017-11-09', 'Technical Drawing', 10, 8000);

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

--
-- Dumping data for table `modulelog`
--

INSERT INTO `modulelog` (`dateOfOperation`, `moduleName`, `operationType`) VALUES
('2017-11-08', 'Electrical Installation', 'UPDATE'),
('2017-11-08', 'Electrical Installation', 'UPDATE'),
('2017-11-08', 'Ethics', 'INSERT'),
('2017-11-09', 'Motor Controls', 'INSERT'),
('2017-11-09', 'VSD', 'INSERT'),
('2017-11-09', 'VSD', 'DELEETE'),
('2017-11-09', 'Feed', 'INSERT'),
('2017-11-09', 'Variable Speed Drives', 'INSERT'),
('2017-11-09', 'VSD', 'INSERT'),
('2017-11-09', 'VSD 1', 'INSERT'),
('2017-11-09', 'VSD', 'DELEETE'),
('2017-11-09', 'VSD', 'INSERT'),
('2017-11-09', 'VD', 'INSERT'),
('2017-11-09', 'Vd', 'INSERT'),
('2017-11-09', 'Vd', 'DELEETE'),
('2017-11-09', 'VSD 1', 'DELEETE'),
('2017-11-09', 'VD', 'DELEETE'),
('2017-11-09', 'VSD', 'DELEETE'),
('2017-11-09', 'Feed', 'DELEETE'),
('2017-11-09', 'Technical Drawing', 'INSERT'),
('2017-11-09', 'Variable Speed Drives', 'DELEETE'),
('2017-11-09', 'Biology', 'INSERT'),
('2017-11-09', 'Technical Drawing', 'DELEETE'),
('2017-11-09', 'Feed', 'INSERT'),
('2017-11-09', 'Biology', 'DELEETE'),
('2017-11-09', 'Technical Drawing', 'INSERT'),
('2017-11-09', 'Live Science', 'INSERT'),
('2017-11-09', 'Feed', 'DELEETE'),
('2017-11-09', 'Feed', 'INSERT'),
('2017-11-09', 'Live Science', 'DELEETE'),
('2017-11-09', 'Technical', 'INSERT'),
('2017-11-09', 'Technical', 'DELEETE'),
('2017-11-09', 'Feed', 'UPDATE');

-- --------------------------------------------------------

--
-- Table structure for table `module_register`
--

CREATE TABLE `module_register` (
  `id` int(11) NOT NULL,
  `dateRegistered` date DEFAULT NULL,
  `module_name` varchar(100) COLLATE latin1_bin DEFAULT NULL,
  `student_id` varchar(50) COLLATE latin1_bin NOT NULL,
  `bookingStatus` tinyint(1) DEFAULT '0',
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
('EYY-C3', '03202049204'),
('EYY-C3', '08021213322'),
('EYY-C3', '0843030232');

-- --------------------------------------------------------

--
-- Table structure for table `professional_experience`
--

CREATE TABLE `professional_experience` (
  `StudentId` varchar(50) COLLATE latin1_bin NOT NULL,
  `StartDate` date NOT NULL,
  `EndDate` date NOT NULL,
  `Employer` varchar(200) COLLATE latin1_bin NOT NULL,
  `Job Title` varchar(200) COLLATE latin1_bin NOT NULL,
  `Responsibility` varchar(2000) COLLATE latin1_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `id_card_number` varchar(50) COLLATE latin1_bin NOT NULL,
  `dateAdmitted` date NOT NULL,
  `certificateIssued` varchar(200) COLLATE latin1_bin DEFAULT NULL,
  `active` tinyint(1) DEFAULT '1',
  `emailAddress` varchar(70) COLLATE latin1_bin DEFAULT NULL,
  `className` varchar(30) COLLATE latin1_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`id_card_number`, `dateAdmitted`, `certificateIssued`, `active`, `emailAddress`, `className`) VALUES
('EYY-C3', '2017-11-04', NULL, 1, 'email@email.com', NULL);

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
('2017-11-04', 'INSERT', 'EYY-C3'),
('2017-11-07', 'DELETE', 'EYY-C3'),
('2017-11-07', 'DELETE', 'EYY-C3'),
('2017-11-07', 'DELETE', 'EYY-C3'),
('2017-11-07', 'DELETE', 'EYY-C3'),
('2017-11-07', 'DELETE', 'EYY-C3'),
('2017-11-07', 'DELETE', 'EYY-C3'),
('2017-11-08', 'DELETE', 'EYY-C3');

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
  ADD UNIQUE KEY `studentId` (`studentId`);

--
-- Indexes for table `certificate`
--
ALTER TABLE `certificate`
  ADD PRIMARY KEY (`name`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `certificatemodule`
--
ALTER TABLE `certificatemodule`
  ADD UNIQUE KEY `certModuleKey` (`certificateName`,`moduleName`) USING BTREE,
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
  ADD UNIQUE KEY `student_id` (`student_id`,`phone_number`);

--
-- Indexes for table `professional_experience`
--
ALTER TABLE `professional_experience`
  ADD KEY `StudentLink` (`StudentId`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id_card_number`),
  ADD KEY `student_certificate_link` (`certificateIssued`),
  ADD KEY `student_ibfk_1` (`className`);

--
-- AUTO_INCREMENT for dumped tables
--

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
  ADD CONSTRAINT `studentID_link` FOREIGN KEY (`studentId`) REFERENCES `student` (`id_card_number`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `certificatemodule`
--
ALTER TABLE `certificatemodule`
  ADD CONSTRAINT `certificateLink` FOREIGN KEY (`certificateName`) REFERENCES `certificate` (`name`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `moduleLink` FOREIGN KEY (`moduleName`) REFERENCES `module` (`name`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `module_register`
--
ALTER TABLE `module_register`
  ADD CONSTRAINT `module_foreign` FOREIGN KEY (`module_name`) REFERENCES `module` (`name`) ON UPDATE CASCADE,
  ADD CONSTRAINT `student_foreign_link` FOREIGN KEY (`student_id`) REFERENCES `student` (`id_card_number`) ON UPDATE CASCADE;

--
-- Constraints for table `phone`
--
ALTER TABLE `phone`
  ADD CONSTRAINT `phoneLink` FOREIGN KEY (`student_id`) REFERENCES `student` (`id_card_number`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `professional_experience`
--
ALTER TABLE `professional_experience`
  ADD CONSTRAINT `StudentLink` FOREIGN KEY (`StudentId`) REFERENCES `student` (`id_card_number`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `student_certificate_link` FOREIGN KEY (`certificateIssued`) REFERENCES `certificate` (`name`),
  ADD CONSTRAINT `student_ibfk_1` FOREIGN KEY (`className`) REFERENCES `class` (`name`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
