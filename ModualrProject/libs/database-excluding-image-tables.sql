-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 21, 2018 at 02:45 PM
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
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `username` varchar(200) COLLATE latin1_bin NOT NULL,
  `Email` varchar(256) COLLATE latin1_bin NOT NULL,
  `password` varchar(500) COLLATE latin1_bin NOT NULL,
  `AccessType` set('SUPER','READ','READ_AND_WRITE','ACCOUNTANT') COLLATE latin1_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`username`, `Email`, `password`, `AccessType`) VALUES
('Chdosahcahdp', 'cdcda', '0fceea1d587d99c040dde23d2b19331867a66f5deda3b01a8e0de28382496eb2e627178d2663caa8cd430e31848b5ec02abf96f6e52e50a1b7b9e4efbc136a3deIEPtBlWHHvxpsWdHagPpAzGhYHyQovMcZAcXLjfesFARhPCGYBmXagJcflXJYdswXbHrBdAwquupfFagnlFclgSvAbUCeuTfeje', ''),
('Chid', 'cwne', 'f63b6fc8fc970471ddcd996bdca97d0b0577ddb2264fd41b75bc7523e7ba6af52c5e26c6ef89d5f815a70e1a2148753ee956bc6c04d39279356d5c46c76fde6bfmoOqzwogshEPDXpyCtIVsrdfpzMmgSTdfyhbgDcwGgIyZOxxVbVlkmBMJFvNvOelHXJrfOQUCoZZvqTgXWChJRKSgAwwGNfTGld', 'READ_AND_WRITE'),
('Chidi', 'chidioguejiofor@gmail.com', '0cf1d7f6d1100bb8f06ff9dbeb071c936fe636f37adfaedbd6d73dd7cd919d7f8c2ef51fbb373ab19b8631588b74cf70d84347e5dfecf6fd168ee395b3e7aab6hODtmFDGmCfztixmGghjRghknVLVsitYghdgJcOHoDMklYlHasvgjbpcakAiXXToQFBykyfjlUTZZzddfMjYcbsuyavueKOIpByB', 'SUPER'),
('Fred101', 'chidi@gmail.com', 'b6924b180616ac9af1c13f8937bb535caf6080e6e6ab1010ba08a2d79bf252391eddfbd7771bdec499d9a3551ad18f4344bd10b7c1bd07601cad3f9bc9dfb434vbzbuAbOhlHMgZqgiyxxqYyhYzgwGxIORaHVpxdyVmvJrcKUHiZfQhDBudfCIcrvSkwawlggrKDVZgZZdfLbnvYhwgMgOdociyfg', ''),
('Fred202', 'nameMail.com', '01b3fa985c05d7cda71aa39839fc25e0f1da1803f2aa1d921675dbda38efda6a3b1eb2aa2e592300039a5d35dc216495cf63856c9adc43897811a83e065a2a3bWhnYyGTjJzBTGhnopXEokXncRLadamnLcFYBwhbObrSfepHZmmSkudEOjznNAhNJVbUKUlMvgxcfcbrCjrSOLlYmylwyfKmDsslr', ''),
('Name', 'dns;', '8474f1a9610fcafae66676c9853b34c5b08ab318445b60ae035de3e6949af035f30c491266f8a31f36890611894e99eb35cb9cf53133fd6aa6fd44b2ce7c315fkiMTWclNCLDKAXpoLHhgncoUqeLfFrTeamcswFOtcFWeTHxoWhjcStmEjNKbCICEdOrifnyQBmQTAhTgwkOsudgvJcGHIcJxfWaN', ''),
('Ncndjnadcna', 'cdcsca', '22a79eb3a0081a8ff1cc98a5e3c29ba12e859a252a7f9769aaa01a8ae00927076efd8a18296c16069caef860387fb46ee6bbdd9e3965305f404ad7595b1e8ccdJopcTurhKoyDggldZVgslKBrdLdnxlDvjhuLFtxTdVlntfYWYnLAivFOCkwvjhHWPtyZmIYWLghGkKpAhePvwnzcgTnVocAwhckv', ''),
('NewAdmin', 'emsia', '05f5d5188aecdb802c486b58251e32afffc31710800998f838acde6026257f10ecdf76281ecf60d37c0da0f213331103ecc23c887b6977adefb1757bd456268eadAuWbQipBaewbrTkzxMxPiAjpQMfArIZTnQzrfOdSZNSQlTHMcShJyHyEkOqRtbcZZZWCjmJpdYZytxWqHobMhtptOwZpTHpkGZ', ''),
('cdsacdc', 'cdsc', '5fd16099b63c5eac6fd92911aea030e8dc1abcdbda59cb32b1880d1e1f9e3da86a4b17de83698290d227ca0d8df7fa856cbe61ce8970341f7babc21f72a30f6dWPUbyzeMBlcDfjVSWXeGeLxHObKczMdcyvhHKEbFeuiBYgjbdxvBdQcwtRzfQnBtLgfUgdeRuwdWEdDixIgBwuHCpojsrJoLhdDt', '');

-- --------------------------------------------------------

--
-- Table structure for table `aspiringexperience`
--

CREATE TABLE `aspiringexperience` (
  `ID` int(255) NOT NULL,
  `AspID` int(255) NOT NULL,
  `BeginDate` date NOT NULL,
  `EndDate` date NOT NULL,
  `JobTitle` varchar(50) COLLATE latin1_bin NOT NULL,
  `Employer` varchar(90) COLLATE latin1_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

--
-- Dumping data for table `aspiringexperience`
--

INSERT INTO `aspiringexperience` (`ID`, `AspID`, `BeginDate`, `EndDate`, `JobTitle`, `Employer`) VALUES
(16, 52, '2012-01-01', '2013-01-01', 'Manager', 'Nigeria'),
(17, 53, '2000-01-01', '2001-01-01', 'Manager', 'Name'),
(18, 54, '2016-01-01', '2017-01-01', 'Intern', 'Cummins');

-- --------------------------------------------------------

--
-- Table structure for table `aspiringjobresponsibility`
--

CREATE TABLE `aspiringjobresponsibility` (
  `AspExpID` int(255) NOT NULL,
  `Duty` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

--
-- Dumping data for table `aspiringjobresponsibility`
--

INSERT INTO `aspiringjobresponsibility` (`AspExpID`, `Duty`) VALUES
(16, 0),
(16, 0),
(16, 0),
(16, 0),
(17, 0),
(17, 0),
(17, 0),
(18, 0),
(18, 0),
(18, 0),
(18, 0);

-- --------------------------------------------------------

--
-- Table structure for table `aspiringmeans`
--

CREATE TABLE `aspiringmeans` (
  `AspID` int(255) NOT NULL,
  `Means` varchar(255) COLLATE latin1_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

--
-- Dumping data for table `aspiringmeans`
--

INSERT INTO `aspiringmeans` (`AspID`, `Means`) VALUES
(52, 'IIT Web Site'),
(52, 'IIT Brochure'),
(52, 'My Employer'),
(52, 'Former IIT Student'),
(53, 'IIT Web Site'),
(53, 'IIT Brochure'),
(53, 'Tutor or Supervisor'),
(53, 'My Employer'),
(53, 'Former IIT Student'),
(54, 'IIT Web Site'),
(54, 'IIT Brochure'),
(54, 'Tutor or Supervisor'),
(55, 'IIT Web Site'),
(55, 'IIT Brochure'),
(55, 'Tutor or Supervisor'),
(55, 'My Employer'),
(55, 'Former IIT Student'),
(56, 'IIT Web Site'),
(56, 'IIT Brochure'),
(56, 'Tutor or Supervisor'),
(56, 'My Employer');

-- --------------------------------------------------------

--
-- Table structure for table `aspiringsponsor`
--

CREATE TABLE `aspiringsponsor` (
  `AspID` int(11) NOT NULL,
  `FirstName` varchar(50) COLLATE latin1_bin NOT NULL,
  `LastName` varchar(50) COLLATE latin1_bin NOT NULL,
  `Address` varchar(500) COLLATE latin1_bin NOT NULL,
  `Email` varchar(255) COLLATE latin1_bin NOT NULL,
  `Telephone` varchar(20) COLLATE latin1_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

--
-- Dumping data for table `aspiringsponsor`
--

INSERT INTO `aspiringsponsor` (`AspID`, `FirstName`, `LastName`, `Address`, `Email`, `Telephone`) VALUES
(52, 'Somto', 'Chinonso', 'Anambra', 'chidioguejiofor@gmail.com', '081313527289');

-- --------------------------------------------------------

--
-- Table structure for table `aspiringstudenteducation`
--

CREATE TABLE `aspiringstudenteducation` (
  `AspID` int(255) NOT NULL,
  `BeginDate` date NOT NULL,
  `EndDate` date NOT NULL,
  `CourseRead` varchar(150) COLLATE latin1_bin NOT NULL,
  `Institution` varchar(150) COLLATE latin1_bin NOT NULL,
  `Qualification` varchar(100) COLLATE latin1_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

--
-- Dumping data for table `aspiringstudenteducation`
--

INSERT INTO `aspiringstudenteducation` (`AspID`, `BeginDate`, `EndDate`, `CourseRead`, `Institution`, `Qualification`) VALUES
(52, '1999-12-31', '2011-12-31', 'Bsc', 'Fred', 'Bsc'),
(53, '1899-12-31', '1959-12-31', 'ds', 'IIT', 'cdsa'),
(54, '2008-06-30', '2014-04-30', 'O\'leve', 'Penny', 'Waec'),
(55, '2008-06-30', '2013-12-31', 'aa', 'a', 'a'),
(56, '1957-12-31', '1957-12-31', 'grw', 'gr', 'grw');

-- --------------------------------------------------------

--
-- Table structure for table `aspiringstudentphone`
--

CREATE TABLE `aspiringstudentphone` (
  `AspID` int(255) NOT NULL,
  `Phone` varchar(30) COLLATE latin1_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

-- --------------------------------------------------------

--
-- Table structure for table `aspmodule`
--

CREATE TABLE `aspmodule` (
  `AspID` int(11) NOT NULL,
  `Module` varchar(100) COLLATE latin1_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

--
-- Dumping data for table `aspmodule`
--

INSERT INTO `aspmodule` (`AspID`, `Module`) VALUES
(52, 'Electrical Installation'),
(52, 'Motor Controls'),
(52, 'Power electronics'),
(52, 'Technical Drawing'),
(53, 'Electrical Installation'),
(53, 'Technical Drawing'),
(54, 'Electrical Installation'),
(54, 'Power Test'),
(55, 'Chess'),
(55, 'Electrical Installation'),
(56, 'Chess'),
(56, 'Electrical Installation');

-- --------------------------------------------------------

--
-- Table structure for table `biodata`
--

CREATE TABLE `biodata` (
  `studentId` varchar(50) COLLATE latin1_bin DEFAULT NULL,
  `Title` varchar(15) COLLATE latin1_bin NOT NULL,
  `FirstName` varchar(50) COLLATE latin1_bin NOT NULL,
  `LastName` varchar(50) COLLATE latin1_bin NOT NULL,
  `PermanentAddress` varchar(2000) COLLATE latin1_bin NOT NULL,
  `CurrentAddress` varchar(200) COLLATE latin1_bin NOT NULL,
  `Religion` varchar(100) COLLATE latin1_bin NOT NULL,
  `stateOfOrigin` varchar(100) COLLATE latin1_bin NOT NULL,
  `country` varchar(100) COLLATE latin1_bin NOT NULL,
  `gender` varchar(6) COLLATE latin1_bin NOT NULL,
  `dateOfBirth` date NOT NULL,
  `placeOfBirth` varchar(100) COLLATE latin1_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

--
-- Dumping data for table `biodata`
--

INSERT INTO `biodata` (`studentId`, `Title`, `FirstName`, `LastName`, `PermanentAddress`, `CurrentAddress`, `Religion`, `stateOfOrigin`, `country`, `gender`, `dateOfBirth`, `placeOfBirth`) VALUES
('EMY-C32', 'Mr', 'Ogu', 'Joe', 'Lagos', 'Lagos', 'Catholic', 'Anam', 'Nig', 'Male', '2012-01-10', 'Lagos'),
('EMUAIDA', 'Mr', '', '', 'Lagos', 'Lagos', 'Catholic', 'Anam', 'Nig', 'Male', '2012-01-10', 'Lagos'),
('EMY-C1', 'Mr', '', '', 'Lagos', 'Lagos', 'Catholic', 'Anam', 'Nig', 'Male', '2012-01-10', 'Lagos'),
('EMY-C54', 'Mr', '', '', 'Lagos', 'Lagos', 'Catholic', 'Anam', 'Nig', 'Male', '2012-01-10', 'Lagos'),
('NAME', 'Mr', '', '', 'Lagos', 'Lagos', 'Catholic', 'Anam', 'Nig', 'Male', '2012-01-10', 'Lagos'),
('ETY-C3', 'Mr', '', '', 'Lagos', 'Lagos', 'Catholic', 'Anam', 'Nig', 'Male', '2012-01-10', 'Lagos'),
('EMY-C4', 'Mr', '', '', 'Lagos', 'Lagos', 'Catholic', 'Anam', 'Nig', 'Male', '2012-01-10', 'Lagos'),
('MINUS', 'Mr', '', '', 'Lagos', 'Lagos', 'Catholic', 'Anam', 'Nig', 'Male', '2012-01-10', 'Lagos'),
('PIO2828', 'Mr', '', '', 'Lagos', 'Lagos', 'Catholic', 'Anam', 'Nig', 'Male', '2012-01-10', 'Lagos');

-- --------------------------------------------------------

--
-- Table structure for table `certificate`
--

CREATE TABLE `certificate` (
  `DateCreated` date DEFAULT NULL,
  `Name` varchar(200) COLLATE latin1_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

--
-- Dumping data for table `certificate`
--

INSERT INTO `certificate` (`DateCreated`, `Name`) VALUES
('2017-12-19', 'Electromecnics'),
('2017-12-19', 'Modular Electrotechnics'),
('2017-12-19', 'Modular Mecatronics'),
('2017-12-22', 'Junk'),
('2017-12-22', 'Name'),
('2018-04-12', 'Chidi Cert');

--
-- Triggers `certificate`
--
DELIMITER $$
CREATE TRIGGER `certificateDeleteTrigger` AFTER DELETE ON `certificate` FOR EACH ROW BEGIN
	INSERT INTO CertificateLog( dateOfOperation, operationType, oldCertificateName)
    VALUES( now(), 'DELETE', old.name);
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `certificateInsertTrigger` AFTER INSERT ON `certificate` FOR EACH ROW BEGIN
	INSERT INTO CertificateLog( dateOfOperation, operationType, oldCertificateName, newCertificateName)
    VALUES( now(), 'INSERT', NULL , new.name);
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `certificateUpdateTrigger` AFTER UPDATE ON `certificate` FOR EACH ROW BEGIN
	INSERT INTO CertificateLog( dateOfOperation, operationType, oldCertificateName, newCertificateName)
    VALUES( now(), 'UPDATE', old.name, new.name);
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
  `oldCertificateName` varchar(100) COLLATE latin1_bin DEFAULT NULL,
  `newCertificateName` varchar(100) COLLATE latin1_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

--
-- Dumping data for table `certificatelog`
--

INSERT INTO `certificatelog` (`dateOfOperation`, `operationType`, `oldCertificateName`, `newCertificateName`) VALUES
('2017-12-19', 'DELETE', 'Modular', NULL),
('2017-12-19', 'DELETE', 'Modular Electrotechnics', NULL),
('2017-12-19', 'UPDATE', 'Modular Electromecnics', 'Electromecnics'),
('2017-12-19', 'INSERT', NULL, 'Modular'),
('2017-12-19', 'INSERT', NULL, 'Modular Electromecnics'),
('2017-12-19', 'INSERT', NULL, 'Modular Electrotechnics'),
('2017-12-19', 'INSERT', NULL, 'Modular Electrotechnics'),
('2017-12-19', 'INSERT', NULL, 'Modular Mecatronics'),
('2017-12-22', 'DELETE', 'Bew', NULL),
('2017-12-22', 'DELETE', 'New', NULL),
('2017-12-22', 'DELETE', 'Newcert', NULL),
('2017-12-22', 'DELETE', 'Newcert', NULL),
('2017-12-22', 'DELETE', 'Newcert', NULL),
('2017-12-22', 'INSERT', NULL, 'Bew'),
('2017-12-22', 'INSERT', NULL, 'Junk'),
('2017-12-22', 'INSERT', NULL, 'Name'),
('2017-12-22', 'INSERT', NULL, 'New'),
('2017-12-22', 'INSERT', NULL, 'Newcert'),
('2017-12-22', 'INSERT', NULL, 'Newcert'),
('2017-12-22', 'INSERT', NULL, 'Newcert'),
('2018-04-12', 'INSERT', NULL, 'Chidi Cert');

-- --------------------------------------------------------

--
-- Table structure for table `certificateregister`
--

CREATE TABLE `certificateregister` (
  `certificateName` varchar(100) COLLATE latin1_bin DEFAULT NULL,
  `moduleName` varchar(100) COLLATE latin1_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

--
-- Dumping data for table `certificateregister`
--

INSERT INTO `certificateregister` (`certificateName`, `moduleName`) VALUES
('Chidi Cert', 'Chess'),
('Electromecnics', 'Chess'),
('Electromecnics', 'Custom'),
('Chidi Cert', 'Electrical Installation'),
('Electromecnics', 'Electrical Installation'),
('Modular Mecatronics', 'Electrical Installation'),
('Electromecnics', 'Electrical Load'),
('Electromecnics', 'Electrical Panel'),
('Electromecnics', 'Mechanical Mesurements and fitting'),
('Modular Mecatronics', 'Motor Controls'),
('Chidi Cert', 'Motor Installation'),
('Electromecnics', 'Physics'),
('Modular Mecatronics', 'Power Test');

--
-- Triggers `certificateregister`
--
DELIMITER $$
CREATE TRIGGER `certificateRegisterDeleteTrigger` AFTER DELETE ON `certificateregister` FOR EACH ROW BEGIN
INSERT INTO `certificateregisterlog`(`DateOfOperation`, `operationType`, `NewModuleName`, `OldModuleName`, `NewCertificateName`, `OldCertificateName`)
VALUES (now(),'DELETE',NULL,
        old.ModuleName,NULL, old.certificateName);
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `certificateRegisterInsertTrigger` AFTER INSERT ON `certificateregister` FOR EACH ROW BEGIN
INSERT INTO `certificateregisterlog`(`DateOfOperation`, `operationType`, `NewModuleName`, `OldModuleName`, `NewCertificateName`, `OldCertificateName`)
VALUES (now(),'INSERT',new.ModuleName,NULL,new.CertificateName,NULL);
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `certificateRegisterUpdateTrigger` AFTER UPDATE ON `certificateregister` FOR EACH ROW BEGIN
INSERT INTO `certificateregisterlog`(`DateOfOperation`, `operationType`, `NewModuleName`, `OldModuleName`, `NewCertificateName`, `OldCertificateName`)
VALUES (now(),'UPDATE',new.ModuleName,old.ModuleName,
        new.CertificateName, old.certificateName);
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `certificateregisterlog`
--

CREATE TABLE `certificateregisterlog` (
  `DateOfOperation` date DEFAULT NULL,
  `operationType` set('INSERT','UPDATE','DELETE') COLLATE latin1_bin DEFAULT NULL,
  `NewModuleName` varchar(100) COLLATE latin1_bin DEFAULT NULL,
  `NewCertificateName` varchar(100) COLLATE latin1_bin DEFAULT NULL,
  `OldModuleName` varchar(100) COLLATE latin1_bin DEFAULT NULL,
  `OldCertificateName` varchar(100) COLLATE latin1_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

--
-- Dumping data for table `certificateregisterlog`
--

INSERT INTO `certificateregisterlog` (`DateOfOperation`, `operationType`, `NewModuleName`, `NewCertificateName`, `OldModuleName`, `OldCertificateName`) VALUES
('2017-12-19', 'INSERT', 'Motor Controls', 'Electromecnics', NULL, NULL),
('2017-12-19', 'DELETE', NULL, NULL, 'Motor Controls', 'Electromecnics'),
('2018-02-13', 'INSERT', 'Electrical Installation', 'Electromecnics', NULL, NULL),
('2018-02-13', 'INSERT', 'Electrical Panel', 'Electromecnics', NULL, NULL),
('2018-02-13', 'INSERT', 'Motor Controls', 'Electromecnics', NULL, NULL),
('2018-03-07', 'INSERT', 'Mechanical Mesurements and fitting', 'Electromecnics', NULL, NULL),
('2018-03-07', 'INSERT', 'Physics', 'Electromecnics', NULL, NULL),
('2018-03-07', 'INSERT', 'Electrical Installation', 'Modular Mecatronics', NULL, NULL),
('2018-03-07', 'INSERT', 'Motor Controls', 'Modular Mecatronics', NULL, NULL),
('2018-03-07', 'INSERT', 'Power electronics', 'Modular Mecatronics', NULL, NULL),
('2018-03-07', 'INSERT', 'Technical Drawing', 'Modular Mecatronics', NULL, NULL),
('2018-03-07', 'INSERT', 'Electrical Installation', 'Name', NULL, NULL),
('2018-03-07', 'INSERT', 'Mechanical Mesurements and fitting', 'Name', NULL, NULL),
('2018-03-07', 'INSERT', 'Physics', 'Name', NULL, NULL),
('2018-03-07', 'INSERT', 'Power electronics', 'Name', NULL, NULL),
('2018-03-07', 'DELETE', NULL, NULL, 'Electrical Installation', 'Electromecnics'),
('2018-03-07', 'DELETE', NULL, NULL, 'Motor Controls', 'Electromecnics'),
('2018-04-12', 'INSERT', 'Chess', 'Chidi Cert', NULL, NULL),
('2018-04-12', 'INSERT', 'Electrical Installation', 'Chidi Cert', NULL, NULL),
('2018-04-12', 'INSERT', 'Motor Installation', 'Chidi Cert', NULL, NULL),
('2018-04-12', 'INSERT', 'Chess', 'Electromecnics', NULL, NULL),
('2018-04-12', 'INSERT', 'Custom', 'Electromecnics', NULL, NULL),
('2018-04-12', 'INSERT', 'Electrical Installation', 'Electromecnics', NULL, NULL),
('2018-04-12', 'INSERT', 'Electrical Load', 'Electromecnics', NULL, NULL),
('2018-04-12', 'INSERT', 'Electrical Panel', 'Electromecnics', NULL, NULL),
('2018-04-12', 'INSERT', 'Mechanical Mesurements and fitting', 'Electromecnics', NULL, NULL),
('2018-04-12', 'INSERT', 'Physics', 'Electromecnics', NULL, NULL),
('2018-04-12', 'DELETE', NULL, NULL, 'Electrical Installation', 'Name'),
('2018-04-12', 'DELETE', NULL, NULL, 'Electrical Panel', 'Electromecnics'),
('2018-04-12', 'DELETE', NULL, NULL, 'Mechanical Mesurements and fitting', 'Electromecnics'),
('2018-04-12', 'DELETE', NULL, NULL, 'Mechanical Mesurements and fitting', 'Name'),
('2018-04-12', 'DELETE', NULL, NULL, 'Physics', 'Electromecnics'),
('2018-04-12', 'DELETE', NULL, NULL, 'Physics', 'Name'),
('2018-04-12', 'DELETE', NULL, NULL, 'Power Test', 'Name');

-- --------------------------------------------------------

--
-- Table structure for table `educational_background`
--

CREATE TABLE `educational_background` (
  `StudentID` varchar(50) COLLATE latin1_bin NOT NULL,
  `BeginDate` date NOT NULL,
  `EndDate` date NOT NULL,
  `Institution` varchar(200) COLLATE latin1_bin NOT NULL,
  `CourseRead` varchar(200) COLLATE latin1_bin NOT NULL,
  `QualificationName` varchar(200) COLLATE latin1_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

--
-- Dumping data for table `educational_background`
--

INSERT INTO `educational_background` (`StudentID`, `BeginDate`, `EndDate`, `Institution`, `CourseRead`, `QualificationName`) VALUES
('EMY-C32', '2010-01-10', '2016-01-10', 'Iit', 'Bsc', 'Bsc'),
('EMY-C32', '2010-01-10', '2016-01-10', 'Peeny', 'O-lneve', 'O-level'),
('EMUAIDA', '2010-01-10', '2016-01-10', 'Ii', 'Technical Studies', 'Bsc'),
('EMY-C1', '2010-01-10', '2016-01-10', 'Penny', 'Math', 'O\'level'),
('EMY-C54', '2010-01-10', '2016-01-10', 'Iit', 'Automation', 'Bsc'),
('EMY-C54', '2010-01-10', '2016-01-10', 'Penny', 'O Level', 'Bsc'),
('NAME', '2010-01-10', '2016-01-10', 'Iit', 'Math', 'Bsc'),
('ETY-C3', '2010-01-10', '2016-01-10', 'Iit', 'Name', 'Bsc'),
('EMY-C4', '2010-01-10', '2016-01-10', 'Unilag', 'Bsc', 'Bsc'),
('MINUS', '2010-01-10', '2016-01-10', 'Iit', 'Name', 'Bsc'),
('PIO2828', '2010-01-10', '2016-01-10', 'Iit', 'Bsc', 'Bsc');

-- --------------------------------------------------------

--
-- Table structure for table `means_of_discovery`
--

CREATE TABLE `means_of_discovery` (
  `StudentID` varchar(50) COLLATE latin1_bin NOT NULL,
  `Means` varchar(300) COLLATE latin1_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

--
-- Dumping data for table `means_of_discovery`
--

INSERT INTO `means_of_discovery` (`StudentID`, `Means`) VALUES
('EMUAIDA', 'Facebook'),
('EMY-C1', 'Facebook'),
('EMY-C32', 'Facebook'),
('EMY-C32', 'News'),
('EMY-C32', 'Twitter'),
('EMY-C4', 'Whatsapp'),
('EMY-C54', 'Facebook'),
('EMY-C54', 'News'),
('EMY-C54', 'Newspaper'),
('ETY-C3', 'Facbook'),
('MINUS', 'Youtube'),
('NAME', 'Facbook'),
('PIO2828', 'Facebook');

-- --------------------------------------------------------

--
-- Table structure for table `modular_class`
--

CREATE TABLE `modular_class` (
  `name` varchar(30) COLLATE latin1_bin NOT NULL,
  `dateCreated` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

--
-- Dumping data for table `modular_class`
--

INSERT INTO `modular_class` (`name`, `dateCreated`) VALUES
('Stream 1', '2017-12-19'),
('Stream 2', '2017-12-19');

--
-- Triggers `modular_class`
--
DELIMITER $$
CREATE TRIGGER `ClassInsertTrigger` AFTER INSERT ON `modular_class` FOR EACH ROW BEGIN
INSERT INTO `modularclasslog`
(`OldName`, `NewName`, `dateofoperation`, `OperationType`) 

VALUES (NULL ,new.name  ,now() , 'INSERT');
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `ClassUpdateTrigger` AFTER UPDATE ON `modular_class` FOR EACH ROW BEGIN
INSERT INTO `modularclasslog`
(`OldName`, `NewName`, `dateofoperation`, `OperationType`) 
VALUES (old.name ,new.name  ,now() , 'UPDATE');
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `modular_class_log`
--

CREATE TABLE `modular_class_log` (
  `OldName` varchar(100) COLLATE latin1_bin DEFAULT NULL,
  `NewName` varchar(100) COLLATE latin1_bin DEFAULT NULL,
  `DateOfOperation` date NOT NULL,
  `OperationType` set('INSERT','UPDATE','DELETE') COLLATE latin1_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

--
-- Dumping data for table `modular_class_log`
--

INSERT INTO `modular_class_log` (`OldName`, `NewName`, `DateOfOperation`, `OperationType`) VALUES
(NULL, 'Stream 1', '2017-12-19', 'INSERT'),
(NULL, 'Stream 1', '2017-12-19', 'INSERT'),
('Stream 4', 'Stream 2', '2017-12-19', 'UPDATE'),
('Stream 1', 'Stream 4', '2017-12-19', 'UPDATE');

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
('2018-03-12', 'Chess', 20, 1000),
('2018-03-28', 'Custom', 5, 8000),
('2017-12-19', 'Electrical Installation', 10, 3000),
('2018-02-09', 'Electrical Load', 10, 3000),
('2018-02-09', 'Electrical Panel', 10, 20000),
('2018-02-13', 'Fundamentals of engines', 10, 2000),
('2018-02-13', 'Mechanical Mesurements and fitting', 10, 20),
('2017-12-19', 'Motor Controls', 10, 3000),
('2018-02-09', 'Motor Installation', 5, 2000),
('2018-02-13', 'Physics', 10, 2000),
('2018-02-13', 'Power Test', 10, 1000),
('2018-03-28', 'Second Test Module', 10, 1000),
('2018-03-28', 'Test Module 2', 10, 1000);

--
-- Triggers `module`
--
DELIMITER $$
CREATE TRIGGER `moduleDeleteTrigger` AFTER DELETE ON `module` FOR EACH ROW BEGIN
	INSERT INTO ModuleLog( dateOfOperation, operationType, oldModuleName , newModuleName)
    VALUES( now(), 'DELETE', old.name, NULL);
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `moduleInsertTrigger` AFTER INSERT ON `module` FOR EACH ROW BEGIN
	INSERT INTO ModuleLog( dateOfOperation, operationType, oldModuleName, newModuleName)
    VALUES( now(), 'INSERT', NULL , new.name);
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `moduleUpdateTrigger` AFTER UPDATE ON `module` FOR EACH ROW BEGIN
	INSERT INTO ModuleLog( dateOfOperation, operationType, oldModuleName, newModuleName)
    VALUES( now(), 'UPDATE', old.name, new.name);
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `modulelog`
--

CREATE TABLE `modulelog` (
  `dateOfOperation` date DEFAULT NULL,
  `oldModuleName` varchar(100) COLLATE latin1_bin DEFAULT NULL,
  `newModuleName` varchar(100) COLLATE latin1_bin DEFAULT NULL,
  `operationType` varchar(20) COLLATE latin1_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

--
-- Dumping data for table `modulelog`
--

INSERT INTO `modulelog` (`dateOfOperation`, `oldModuleName`, `newModuleName`, `operationType`) VALUES
('2017-12-19', 'cdcd', NULL, 'DELETE'),
('2017-12-19', NULL, 'Electrical Installation', 'INSERT'),
('2017-12-19', NULL, 'Electrical Installation', 'INSERT'),
('2017-12-19', NULL, 'cdcd', 'INSERT'),
('2017-12-19', 'Electrical Installation', 'Motor Controls', 'UPDATE'),
('2018-02-09', NULL, 'Electrical Load', 'INSERT'),
('2018-02-09', NULL, 'Electrical Panel', 'INSERT'),
('2018-02-09', NULL, 'KNX', 'INSERT'),
('2018-02-09', NULL, 'Name', 'INSERT'),
('2018-02-13', NULL, 'Fundamentals of engines', 'INSERT'),
('2018-02-13', NULL, 'Mechanical Mesurements and fitting', 'INSERT'),
('2018-02-13', NULL, 'Physics', 'INSERT'),
('2018-02-13', NULL, 'Power electronics', 'INSERT'),
('2018-02-13', NULL, 'Technical Drawing', 'INSERT'),
('2018-02-13', 'KNX', 'Knx', 'UPDATE'),
('2018-03-12', 'Chess', NULL, 'DELETE'),
('2018-03-12', NULL, 'Chess', 'INSERT'),
('2018-03-12', NULL, 'Chess', 'INSERT'),
('2018-03-12', 'Knx', 'Motor Installation', 'UPDATE'),
('2018-03-28', 'Intro to Reasoning', NULL, 'DELETE'),
('2018-03-28', 'Name', NULL, 'DELETE'),
('2018-03-28', 'Technical Drawing', NULL, 'DELETE'),
('2018-03-28', 'Test Module', NULL, 'DELETE'),
('2018-03-28', 'Test Module 1', NULL, 'DELETE'),
('2018-03-28', NULL, 'Custom', 'INSERT'),
('2018-03-28', NULL, 'Intro to Reasoning', 'INSERT'),
('2018-03-28', NULL, 'Second Test Module', 'INSERT'),
('2018-03-28', NULL, 'Test Module', 'INSERT'),
('2018-03-28', NULL, 'Test Module 1', 'INSERT'),
('2018-03-28', NULL, 'Test Module 2', 'INSERT'),
('2018-03-28', 'Power electronics', 'Power Test', 'UPDATE');

-- --------------------------------------------------------

--
-- Table structure for table `moduleregisterlog`
--

CREATE TABLE `moduleregisterlog` (
  `regId` int(11) NOT NULL,
  `operationType` set('INSERT','UPDATE','DELETE') COLLATE latin1_bin DEFAULT NULL,
  `DateOfOperation` date DEFAULT NULL,
  `StudentId` varchar(50) COLLATE latin1_bin DEFAULT NULL,
  `OldModuleName` varchar(100) COLLATE latin1_bin DEFAULT NULL,
  `NewModuleName` varchar(100) COLLATE latin1_bin DEFAULT NULL,
  `OldAttendanceStatus` tinyint(1) DEFAULT NULL,
  `NewAttendanceStatus` tinyint(1) DEFAULT NULL,
  `OldBookingStatus` tinyint(1) DEFAULT NULL,
  `NewBookingStatus` int(11) DEFAULT NULL,
  `OldResult` set('PASS','FAIL') COLLATE latin1_bin DEFAULT NULL,
  `NewResult` set('PASS','FAIL') COLLATE latin1_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

--
-- Dumping data for table `moduleregisterlog`
--

INSERT INTO `moduleregisterlog` (`regId`, `operationType`, `DateOfOperation`, `StudentId`, `OldModuleName`, `NewModuleName`, `OldAttendanceStatus`, `NewAttendanceStatus`, `OldBookingStatus`, `NewBookingStatus`, `OldResult`, `NewResult`) VALUES
(4, 'INSERT', '2017-12-21', 'EMY-C32', NULL, 'Motor Controls', NULL, 0, NULL, 0, NULL, NULL),
(4, 'INSERT', '2017-12-22', 'EMY-C32', 'Motor Controls', 'Motor Controls', 0, 0, 0, 1, NULL, NULL),
(4, 'INSERT', '2017-12-22', 'EMY-C32', 'Motor Controls', 'Motor Controls', 0, 0, 1, 1, NULL, NULL),
(4, 'INSERT', '2017-12-22', 'EMY-C32', 'Motor Controls', 'Motor Controls', 0, 1, 1, 1, NULL, NULL),
(4, 'INSERT', '2017-12-22', 'EMY-C32', 'Motor Controls', 'Motor Controls', 1, 1, 1, 1, NULL, ''),
(4, 'INSERT', '2017-12-22', 'EMY-C32', 'Motor Controls', 'Motor Controls', 1, 1, 1, 1, '', 'FAIL'),
(4, 'INSERT', '2018-04-11', 'EMY-C32', 'Motor Controls', 'Motor Controls', 1, 0, 1, 0, 'FAIL', NULL),
(4, 'INSERT', '2018-04-11', 'EMY-C32', 'Motor Controls', 'Motor Controls', 1, 1, 1, 1, 'FAIL', 'FAIL'),
(5, 'INSERT', '2017-12-22', 'EMY-C32', NULL, 'Electrical Installation', NULL, 0, NULL, 0, NULL, NULL),
(5, 'INSERT', '2018-02-13', 'EMY-C32', 'Electrical Installation', 'Electrical Installation', 0, 0, 0, 1, NULL, NULL),
(5, 'INSERT', '2018-04-11', 'EMY-C32', 'Electrical Installation', 'Electrical Installation', 0, 0, 1, 0, NULL, NULL),
(5, 'INSERT', '2018-04-11', 'EMY-C32', 'Electrical Installation', 'Electrical Installation', 0, 0, 1, 1, NULL, NULL),
(6, 'INSERT', '2017-12-22', 'EMY-C32', NULL, 'Electrical Installation', NULL, 0, NULL, 0, NULL, NULL),
(6, 'INSERT', '2018-04-11', 'EMY-C32', 'Electrical Installation', 'Electrical Installation', 0, 0, 0, 0, NULL, NULL),
(6, 'INSERT', '2018-04-11', 'EMY-C32', 'Electrical Installation', 'Electrical Installation', 0, 0, 0, 0, NULL, NULL),
(6, 'INSERT', '2018-04-11', 'EMY-C32', 'Electrical Installation', 'Electrical Installation', 0, 0, 0, 1, NULL, NULL),
(6, 'INSERT', '2018-04-11', 'EMY-C32', 'Electrical Installation', 'Electrical Installation', 0, 0, 1, 1, NULL, NULL),
(6, 'INSERT', '2018-04-11', 'EMY-C32', 'Electrical Installation', 'Electrical Installation', 0, 1, 1, 1, NULL, NULL),
(6, 'INSERT', '2018-04-11', 'EMY-C32', 'Electrical Installation', 'Electrical Installation', 0, 1, 1, 1, NULL, NULL),
(6, 'INSERT', '2018-04-11', 'EMY-C32', 'Electrical Installation', 'Electrical Installation', 1, 0, 1, 1, NULL, NULL),
(6, 'INSERT', '2018-04-11', 'EMY-C32', 'Electrical Installation', 'Electrical Installation', 1, 1, 1, 1, NULL, 'PASS'),
(7, 'INSERT', '2018-02-09', 'EMUAIDA', NULL, 'Motor Controls', NULL, 0, NULL, 0, NULL, NULL),
(7, 'INSERT', '2018-02-09', 'EMUAIDA', 'Motor Controls', 'Motor Controls', 0, 0, 0, 0, NULL, 'PASS'),
(8, 'INSERT', '2018-02-09', 'EMUAIDA', NULL, 'Motor Controls', NULL, 0, NULL, 0, NULL, NULL),
(9, 'INSERT', '2018-02-09', 'EMUAIDA', NULL, 'Motor Controls', NULL, 0, NULL, 0, NULL, NULL),
(9, 'INSERT', '2018-02-09', 'EMUAIDA', 'Motor Controls', 'Motor Controls', 0, 0, 0, 0, NULL, 'FAIL'),
(9, 'INSERT', '2018-04-11', 'EMUAIDA', 'Motor Controls', 'Motor Controls', 0, 0, 0, 0, 'FAIL', NULL),
(9, 'INSERT', '2018-04-11', 'EMUAIDA', 'Motor Controls', 'Motor Controls', 0, 0, 0, 0, 'FAIL', 'FAIL'),
(9, 'INSERT', '2018-04-13', 'EMUAIDA', 'Motor Controls', 'Motor Controls', 0, 0, 0, 1, NULL, NULL),
(9, 'INSERT', '2018-04-13', 'EMUAIDA', 'Motor Controls', 'Motor Controls', 0, 1, 1, 1, NULL, NULL),
(9, 'INSERT', '2018-04-13', 'EMUAIDA', 'Motor Controls', 'Motor Controls', 1, 1, 1, 1, NULL, 'PASS'),
(10, 'INSERT', '2018-02-09', 'EMUAIDA', NULL, 'Motor Controls', NULL, 0, NULL, 0, NULL, NULL),
(10, 'INSERT', '2018-04-11', 'EMUAIDA', 'Motor Controls', 'Motor Controls', 0, 0, 0, 1, NULL, NULL),
(10, 'INSERT', '2018-04-11', 'EMUAIDA', 'Motor Controls', 'Motor Controls', 0, 0, 1, 0, NULL, NULL),
(10, 'INSERT', '2018-04-11', 'EMUAIDA', 'Motor Controls', 'Motor Controls', 0, 0, 1, 1, NULL, NULL),
(11, 'INSERT', '2018-04-11', 'ETY-C3', 'Motor Controls', 'Motor Controls', 0, 0, 0, 0, NULL, NULL),
(11, 'INSERT', '2018-04-11', 'ETY-C3', 'Motor Controls', 'Motor Controls', 0, 0, 0, 0, NULL, NULL),
(11, 'INSERT', '2018-04-12', 'ETY-C3', 'Motor Controls', 'Motor Controls', 0, 0, 0, 1, NULL, NULL),
(11, 'INSERT', '2018-04-12', 'ETY-C3', 'Motor Controls', 'Motor Controls', 0, 1, 1, 1, NULL, NULL),
(11, 'INSERT', '2018-02-09', 'ETY-C43', NULL, 'Motor Controls', NULL, 0, NULL, 0, NULL, NULL),
(12, 'INSERT', '2018-02-13', 'EMUAIDA', NULL, 'Electrical Load', NULL, 0, NULL, 0, NULL, NULL),
(12, 'INSERT', '2018-04-11', 'EMUAIDA', 'Electrical Load', 'Electrical Load', 0, 0, 0, 0, NULL, NULL),
(12, 'INSERT', '2018-04-11', 'EMUAIDA', 'Electrical Load', 'Electrical Load', 0, 0, 0, 0, NULL, NULL),
(13, 'INSERT', '2018-02-13', 'EMUAIDA', NULL, 'Electrical Panel', NULL, 0, NULL, 0, NULL, NULL),
(13, 'INSERT', '2018-04-11', 'EMUAIDA', 'Electrical Panel', 'Electrical Panel', 0, 0, 0, 0, NULL, NULL),
(13, 'INSERT', '2018-04-11', 'EMUAIDA', 'Electrical Panel', 'Electrical Panel', 0, 0, 0, 0, NULL, NULL),
(14, 'INSERT', '2018-02-13', 'EMY-C54', NULL, 'Electrical Panel', NULL, 0, NULL, 0, NULL, NULL),
(14, 'INSERT', '2018-02-13', 'EMY-C54', 'Electrical Panel', 'Electrical Panel', 0, 0, 0, 1, NULL, NULL),
(14, 'INSERT', '2018-04-11', 'EMY-C54', 'Electrical Panel', 'Electrical Panel', 0, 0, 1, 0, NULL, NULL),
(14, 'INSERT', '2018-04-11', 'EMY-C54', 'Electrical Panel', 'Electrical Panel', 0, 0, 1, 1, NULL, NULL),
(15, 'INSERT', '2018-02-13', 'EMY-C54', NULL, 'Knx', NULL, 0, NULL, 0, NULL, NULL),
(15, 'INSERT', '2018-04-11', 'EMY-C54', 'Motor Installation', 'Motor Installation', 0, 0, 0, 0, NULL, NULL),
(15, 'INSERT', '2018-04-11', 'EMY-C54', 'Motor Installation', 'Motor Installation', 0, 0, 0, 0, NULL, NULL),
(16, 'INSERT', '2018-02-13', 'MINUS', NULL, 'Knx', NULL, 0, NULL, 0, NULL, NULL),
(16, 'INSERT', '2018-02-13', 'MINUS', 'Knx', 'Knx', 0, 0, 0, 1, NULL, NULL),
(16, 'INSERT', '2018-02-13', 'MINUS', 'Knx', 'Knx', 0, 1, 1, 1, NULL, NULL),
(16, 'INSERT', '2018-02-13', 'MINUS', 'Knx', 'Knx', 1, 1, 1, 1, NULL, 'FAIL'),
(16, 'INSERT', '2018-04-11', 'MINUS', 'Motor Installation', 'Motor Installation', 1, 0, 1, 0, 'FAIL', NULL),
(16, 'INSERT', '2018-04-11', 'MINUS', 'Motor Installation', 'Motor Installation', 1, 1, 1, 1, 'FAIL', 'FAIL'),
(16, 'INSERT', '2018-04-12', 'MINUS', 'Motor Installation', 'Motor Installation', 0, 0, 0, 1, NULL, NULL),
(16, 'INSERT', '2018-04-12', 'MINUS', 'Motor Installation', 'Motor Installation', 0, 1, 1, 1, NULL, NULL),
(16, 'INSERT', '2018-04-12', 'MINUS', 'Motor Installation', 'Motor Installation', 1, 1, 1, 1, NULL, 'PASS'),
(17, 'INSERT', '2018-02-13', 'NAME', NULL, 'Knx', NULL, 0, NULL, 0, NULL, NULL),
(17, 'INSERT', '2018-02-13', 'NAME', 'Knx', 'Knx', 0, 0, 0, 1, NULL, NULL),
(17, 'INSERT', '2018-04-11', 'NAME', 'Motor Installation', 'Motor Installation', 0, 0, 1, 0, NULL, NULL),
(17, 'INSERT', '2018-04-11', 'NAME', 'Motor Installation', 'Motor Installation', 0, 0, 1, 1, NULL, NULL),
(18, 'INSERT', '2018-02-13', 'PIO2828', NULL, 'Electrical Load', NULL, 0, NULL, 0, NULL, NULL),
(18, 'INSERT', '2018-04-11', 'PIO2828', 'Electrical Load', 'Electrical Load', 0, 0, 0, 0, NULL, NULL),
(18, 'INSERT', '2018-04-11', 'PIO2828', 'Electrical Load', 'Electrical Load', 0, 0, 0, 0, NULL, NULL),
(19, 'INSERT', '2018-02-13', 'EMY-C4', NULL, 'Knx', NULL, 0, NULL, 0, NULL, NULL),
(19, 'INSERT', '2018-04-11', 'EMY-C4', 'Motor Installation', 'Motor Installation', 0, 0, 0, 0, NULL, NULL),
(19, 'INSERT', '2018-04-11', 'EMY-C4', 'Motor Installation', 'Motor Installation', 0, 0, 0, 0, NULL, NULL),
(20, 'INSERT', '2018-02-13', 'EMY-C4', NULL, 'Electrical Panel', NULL, 0, NULL, 0, NULL, NULL),
(20, 'INSERT', '2018-04-11', 'EMY-C4', 'Electrical Panel', 'Electrical Panel', 0, 0, 0, 0, NULL, NULL),
(20, 'INSERT', '2018-04-11', 'EMY-C4', 'Electrical Panel', 'Electrical Panel', 0, 0, 0, 0, NULL, NULL),
(21, 'INSERT', '2018-03-12', 'EMY-C32', NULL, 'Chess', NULL, 0, NULL, 0, NULL, NULL),
(22, 'INSERT', '2018-04-11', 'PIO2828', NULL, 'Chess', NULL, 0, NULL, 0, NULL, NULL),
(23, 'INSERT', '2018-04-11', 'ETY-C3', NULL, 'Chess', NULL, 0, NULL, 0, NULL, NULL),
(24, 'INSERT', '2018-04-11', 'ETY-C3', NULL, 'Chess', NULL, 0, NULL, 0, NULL, NULL),
(25, 'INSERT', '2018-04-11', 'ETY-C3', NULL, 'Chess', NULL, 0, NULL, 0, NULL, NULL),
(26, 'INSERT', '2018-04-11', 'ETY-C3', NULL, 'Chess', NULL, 0, NULL, 0, NULL, NULL),
(27, 'INSERT', '2018-04-11', 'ETY-C3', NULL, 'Chess', NULL, 0, NULL, 0, NULL, NULL),
(27, 'INSERT', '2018-04-11', 'ETY-C3', 'Chess', 'Chess', 0, 0, 0, 0, NULL, NULL),
(28, 'INSERT', '2018-04-11', 'ETY-C3', NULL, 'Chess', NULL, 0, NULL, 0, NULL, NULL),
(28, 'INSERT', '2018-04-11', 'ETY-C3', 'Chess', 'Chess', 0, 0, 0, 1, NULL, NULL),
(28, 'INSERT', '2018-04-11', 'ETY-C3', 'Chess', 'Chess', 0, 1, 1, 1, NULL, NULL),
(28, 'INSERT', '2018-04-11', 'ETY-C3', 'Chess', 'Chess', 1, 1, 1, 1, NULL, 'PASS'),
(28, 'INSERT', '2018-04-11', 'ETY-C3', 'Chess', 'Chess', 1, 1, 1, 1, 'PASS', 'PASS'),
(29, 'INSERT', '2018-04-11', 'PIO2828', NULL, 'Chess', NULL, 0, NULL, 0, NULL, NULL),
(29, 'INSERT', '2018-04-11', 'PIO2828', 'Chess', 'Chess', 0, 0, 0, 1, NULL, NULL),
(29, 'INSERT', '2018-04-11', 'PIO2828', 'Chess', 'Chess', 0, 1, 1, 1, NULL, NULL),
(29, 'INSERT', '2018-04-11', 'PIO2828', 'Chess', 'Chess', 1, 1, 1, 1, NULL, 'PASS');

-- --------------------------------------------------------

--
-- Table structure for table `module_register`
--

CREATE TABLE `module_register` (
  `id` int(11) NOT NULL,
  `DateRegistered` date NOT NULL,
  `ModuleName` varchar(100) COLLATE latin1_bin NOT NULL,
  `StudentId` varchar(50) COLLATE latin1_bin NOT NULL,
  `amountPerUnit` double NOT NULL,
  `NumberOfUnits` int(11) NOT NULL,
  `AttendanceStatus` tinyint(1) NOT NULL,
  `BookingStatus` tinyint(1) NOT NULL,
  `Result` set('PASS','FAIL') COLLATE latin1_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

--
-- Dumping data for table `module_register`
--

INSERT INTO `module_register` (`id`, `DateRegistered`, `ModuleName`, `StudentId`, `amountPerUnit`, `NumberOfUnits`, `AttendanceStatus`, `BookingStatus`, `Result`) VALUES
(4, '2017-12-21', 'Motor Controls', 'EMY-C32', 30000, 5, 0, 0, NULL),
(5, '2017-12-22', 'Electrical Installation', 'EMY-C32', 30000, 5, 0, 0, NULL),
(6, '2017-12-22', 'Electrical Installation', 'EMY-C32', 30000, 5, 1, 1, 'PASS'),
(9, '2018-02-09', 'Motor Controls', 'EMUAIDA', 30000, 5, 1, 1, 'PASS'),
(10, '2018-02-09', 'Motor Controls', 'EMUAIDA', 30000, 4, 0, 0, NULL),
(11, '2018-02-09', 'Motor Controls', 'ETY-C3', 30000, 5, 1, 1, NULL),
(12, '2018-02-13', 'Electrical Load', 'EMUAIDA', 30000, 5, 0, 0, NULL),
(13, '2018-02-13', 'Electrical Panel', 'EMUAIDA', 200000, 5, 0, 0, NULL),
(14, '2018-02-13', 'Electrical Panel', 'EMY-C54', 200000, 5, 0, 0, NULL),
(15, '2018-02-13', 'Motor Installation', 'EMY-C54', 20000, 5, 0, 0, NULL),
(16, '2018-02-13', 'Motor Installation', 'MINUS', 20000, 5, 1, 1, 'PASS'),
(17, '2018-02-13', 'Motor Installation', 'NAME', 20000, 5, 0, 0, NULL),
(18, '2018-02-13', 'Electrical Load', 'PIO2828', 30000, 5, 0, 0, NULL),
(19, '2018-02-13', 'Motor Installation', 'EMY-C4', 20000, 5, 0, 0, NULL),
(20, '2018-02-13', 'Electrical Panel', 'EMY-C4', 200000, 5, 0, 0, NULL),
(28, '2018-04-11', 'Chess', 'ETY-C3', 1000, 20, 1, 1, 'PASS'),
(29, '2018-04-11', 'Chess', 'PIO2828', 1000, 20, 1, 1, 'PASS');

--
-- Triggers `module_register`
--
DELIMITER $$
CREATE TRIGGER `moduleRegisterInsertTrigger` AFTER INSERT ON `module_register` FOR EACH ROW BEGIN
INSERT INTO `moduleregisterlog`(`regId`, `operationType`, `DateOfOperation`, `StudentId`, `OldModuleName`, `NewModuleName`, `OldAttendanceStatus`, `NewAttendanceStatus`, `OldBookingStatus`, `NewBookingStatus`, `OldResult`, `NewResult`)
VALUES (new.Id,'INSERT', NOW() , new.studentId,
  NULL ,new.moduleName,NULL ,new.attendanceStatus,NULL ,
        new.bookingStatus,NULL ,new.result);
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `moduleRegisterUpdateTrigger` AFTER UPDATE ON `module_register` FOR EACH ROW BEGIN
INSERT INTO `moduleregisterlog`(`regId`, `operationType`, `DateOfOperation`, `StudentId`, `OldModuleName`, `NewModuleName`, `OldAttendanceStatus`, `NewAttendanceStatus`, `OldBookingStatus`, `NewBookingStatus`, `OldResult`, `NewResult`)
VALUES (new.Id,'INSERT', NOW() , new.studentId,
  old.moduleName ,new.moduleName,
        old.attendanceStatus ,new.attendanceStatus,
        old.bookingStatus ,
        new.bookingStatus,old.result ,new.result);
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `other_student_info`
--

CREATE TABLE `other_student_info` (
  `StudentID` varchar(50) COLLATE latin1_bin NOT NULL,
  `HighestQualification` varchar(50) COLLATE latin1_bin NOT NULL,
  `CurrentWorkPlace` varchar(100) COLLATE latin1_bin NOT NULL,
  `YearsWorkingExperience` int(11) NOT NULL,
  `LastCourseRead` varchar(50) COLLATE latin1_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `id` int(11) NOT NULL,
  `RegId` int(11) DEFAULT NULL,
  `amount` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`id`, `RegId`, `amount`) VALUES
(21, 6, 150000),
(26, 9, 150000),
(25, 11, 150000),
(24, 16, 100000),
(22, 28, 20000),
(23, 29, 20000);

--
-- Triggers `payment`
--
DELIMITER $$
CREATE TRIGGER `payementInsertTrigger` AFTER INSERT ON `payment` FOR EACH ROW BEGIN

SELECT reg.ModuleName, reg.StudentId 
	FROM module_register as reg 
WHERE reg.id = new.regId 
into @modName, @studID;

 INSERT INTO PaymentLog
  ( regId, dateofoperation, operationType,BankName,  amount, paymentlog.StudentID, paymentlog.ModuleName)
 VALUES( new.regId, now(), 'INSERT','',new.amount, @studID,@modName   );
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `paymentlog`
--

CREATE TABLE `paymentlog` (
  `RegId` int(11) NOT NULL,
  `Dateofoperation` date DEFAULT NULL,
  `StudentID` varchar(50) COLLATE latin1_bin NOT NULL,
  `ModuleName` varchar(200) COLLATE latin1_bin NOT NULL,
  `OperationType` varchar(30) COLLATE latin1_bin DEFAULT NULL,
  `BankName` varchar(50) COLLATE latin1_bin NOT NULL,
  `Amount` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

--
-- Dumping data for table `paymentlog`
--

INSERT INTO `paymentlog` (`RegId`, `Dateofoperation`, `StudentID`, `ModuleName`, `OperationType`, `BankName`, `Amount`) VALUES
(14, '2018-03-28', 'EMY-C54', 'Electrical Panel', 'INSERT', '', 255),
(18, '2018-04-11', 'PIO2828', 'Electrical Load', 'INSERT', '', 3000),
(18, '2018-04-11', 'PIO2828', 'Electrical Load', 'INSERT', '', 22),
(14, '2018-04-11', 'EMY-C54', 'Electrical Panel', 'INSERT', '', 5000),
(10, '2018-04-11', 'EMUAIDA', 'Motor Controls', 'INSERT', '', 30000),
(11, '2018-04-11', 'ETY-C3', 'Motor Controls', 'INSERT', '', 30000),
(6, '2018-04-11', 'EMY-C32', 'Electrical Installation', 'INSERT', '', 25000),
(5, '2018-04-11', 'EMY-C32', 'Electrical Installation', 'INSERT', '', 30000),
(6, '2018-04-11', 'EMY-C32', 'Electrical Installation', 'INSERT', '', 150000),
(28, '2018-04-11', 'ETY-C3', 'Chess', 'INSERT', '', 20000),
(29, '2018-04-11', 'PIO2828', 'Chess', 'INSERT', '', 20000),
(16, '2018-04-12', 'MINUS', 'Motor Installation', 'INSERT', '', 100000),
(11, '2018-04-12', 'ETY-C3', 'Motor Controls', 'INSERT', '', 150000),
(9, '2018-04-13', 'EMUAIDA', 'Motor Controls', 'INSERT', '', 150000);

-- --------------------------------------------------------

--
-- Table structure for table `phone`
--

CREATE TABLE `phone` (
  `StudentId` varchar(50) COLLATE latin1_bin DEFAULT NULL,
  `phone_number` varchar(20) COLLATE latin1_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

--
-- Dumping data for table `phone`
--

INSERT INTO `phone` (`StudentId`, `phone_number`) VALUES
('EMUAIDA', '039019301'),
('EMY-C1', '90239209320'),
('EMY-C32', '3920920'),
('EMY-C32', '930239029'),
('EMY-C4', '309209390239'),
('EMY-C54', '08123232232'),
('ETY-C3', '320929320'),
('MINUS', '39203920'),
('NAME', '02302392032930'),
('PIO2828', '4839439');

-- --------------------------------------------------------

--
-- Table structure for table `professional_experience`
--

CREATE TABLE `professional_experience` (
  `id` int(11) NOT NULL,
  `StudentId` varchar(50) COLLATE latin1_bin NOT NULL,
  `Employer` varchar(200) COLLATE latin1_bin NOT NULL,
  `Job Title` varchar(200) COLLATE latin1_bin NOT NULL,
  `StartDate` date NOT NULL,
  `EndDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

--
-- Dumping data for table `professional_experience`
--

INSERT INTO `professional_experience` (`id`, `StudentId`, `Employer`, `Job Title`, `StartDate`, `EndDate`) VALUES
(13, 'EMY-C32', 'Cadbury', 'Manager', '2000-01-10', '2009-01-10'),
(12, 'EMY-C32', 'Cummins', 'Intern', '2000-01-10', '2009-01-10'),
(15, 'EMY-C54', 'Cadbury', 'Intern', '2000-01-10', '2009-01-10'),
(14, 'EMY-C54', 'Cummins', 'Intern', '2000-01-10', '2009-01-10');

-- --------------------------------------------------------

--
-- Table structure for table `resources`
--

CREATE TABLE `resources` (
  `id` int(11) NOT NULL,
  `DefaultImage` longblob NOT NULL,
  `ContactPhone` varchar(20) COLLATE latin1_bin NOT NULL,
  `ContactEmail` varchar(255) COLLATE latin1_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

--
-- Dumping data for table `resources`
--

INSERT INTO `resources` (`id`, `DefaultImage`, `ContactPhone`, `ContactEmail`) VALUES
(1, 0x89504e470d0a1a0a0000000d4948445200000750000007500806000000628dd9a00000001974455874536f6674776172650041646f626520496d616765526561647971c9653c0000032469545874584d4c3a636f6d2e61646f62652e786d7000000000003c3f787061636b657420626567696e3d22efbbbf222069643d2257354d304d7043656869487a7265537a4e54637a6b633964223f3e203c783a786d706d65746120786d6c6e733a783d2261646f62653a6e733a6d6574612f2220783a786d70746b3d2241646f626520584d5020436f726520352e332d633031312036362e3134353636312c20323031322f30322f30362d31343a35363a32372020202020202020223e203c7264663a52444620786d6c6e733a7264663d22687474703a2f2f7777772e77332e6f72672f313939392f30322f32322d7264662d73796e7461782d6e7323223e203c7264663a4465736372697074696f6e207264663a61626f75743d222220786d6c6e733a786d703d22687474703a2f2f6e732e61646f62652e636f6d2f7861702f312e302f2220786d6c6e733a786d704d4d3d22687474703a2f2f6e732e61646f62652e636f6d2f7861702f312e302f6d6d2f2220786d6c6e733a73745265663d22687474703a2f2f6e732e61646f62652e636f6d2f7861702f312e302f73547970652f5265736f75726365526566232220786d703a43726561746f72546f6f6c3d2241646f62652050686f746f73686f702043533620284d6163696e746f7368292220786d704d4d3a496e7374616e636549443d22786d702e6969643a45324636463839394631343131314533394445444541354544413136313130302220786d704d4d3a446f63756d656e7449443d22786d702e6469643a4532463646383941463134313131453339444544454135454441313631313030223e203c786d704d4d3a4465726976656446726f6d2073745265663a696e7374616e636549443d22786d702e6969643a4532463646383937463134313131453339444544454135454441313631313030222073745265663a646f63756d656e7449443d22786d702e6469643a4532463646383938463134313131453339444544454135454441313631313030222f3e203c2f7264663a4465736372697074696f6e3e203c2f7264663a5244463e203c2f783a786d706d6574613e203c3f787061636b657420656e643d2272223f3e684d077000010ad94944415478daecddbf7356479ee8e1f6cd98e452a3545b573718a5d2664bb4320e4dd565025210593b1a70ba81e11fb07134274390120c5b350ecd6822265b29c5c97bcb4ad9e2264bb8f77cdff7bc2030ef4134ef8ff3e379aa4e1dccd8d86ede526bfae3eefee2bffffbbf1300000000000000297d21a002000000000000cc08a8000000000000000d0115000000000000a021a0020000000000003404540000000000008086800a000000000000d01050010000000000001a022a000000000000404340050000000000006808a8000000000000000d0115000000000000a021a0020000000000003404540000000000008086800a000000000000d01050010000000000001a022a000000000000404340050000000000006808a8000000000000000d0115000000000000a021a0020000000000003404540000000000008086800a000000000000d01050010000000000001a022a000000000000404340050000000000006808a8000000000000000d0115000000000000a021a0020000000000003404540000000000008086800a000000000000d01050010000000000001a022a000000000000404340050000000000006808a8000000000000000d0115000000000000a021a0020000000000003404540000000000008086800a000000000000d01050010000000000001a022a000000000000404340050000000000006808a8000000000000000d0115000000000000a021a0020000000000003404540000000000008086800a000000000000d01050010000000000001a022a000000000000404340050000000000006808a8000000000000000d0115000000000000a021a0020000000000003404540000000000008086800a000000000000d01050010000000000001a022a000000000000404340050000000000006808a8000000000000000d0115000000000000a021a0020000000000003404540000000000008086800a000000000000d01050010000000000001a022a000000000000404340050000000000006808a8000000000000000d0115000000000000a021a0020000000000003404540000000000008086800a000000000000d01050010000000000001a022a000000000000404340050000000000006808a8000000000000000d0115000000000000a021a0020000000000003404540000000000008086800a000000000000d01050010000000000001a022a000000000000404340050000000000006808a8000000000000000d0115000000000000a021a0020000000000003404540000000000008086800a000000000000d01050010000000000001a022a000000000000404340050000000000006808a8000000000000000d0115000000000000a021a0020000000000003404540000000000008086800a000000000000d01050010000000000001a022a000000000000404340050000000000006808a8000000000000000d0115000000000000a021a0020000000000003404540000000000008086800a000000000000d01050010000000000001a022a000000000000404340050000000000006808a8000000000000000d0115000000000000a021a0020000000000003404540000000000008086800a000000000000d01050010000000000001a022a000000000000404340050000000000006808a8000000000000000d0115000000000000a021a0020000000000003404540000000000008086800a000000000000d01050010000000000001a022a000000000000404340050000000000006808a8000000000000000d0115000000000000a021a0020000000000003404540000000000008086800a000000000000d01050010000000000001a022a000000000000404340050000000000006808a8000000000000000d0115000000000000a021a0020000000000003404540000000000008086800a000000000000d01050010000000000001a022a000000000000404340050000000000006808a8000000000000000d0115000000000000a021a0020000000000003404540000000000008086800a000000000000d010500100609ddf807ff18541606372ce071ff8e9cbf5b3dff297fdeb057ff99de659a757f57372c13f37febcffb7e07f9b34cf6ffe9aaaaa5ef9e410ac9f0000c07808a80000b0ce6fc005540ae49c23705e3ef753effff1de7b7ffcb1284ab949fa6d6cfdfb7b7f7c7cfe0faaaa3a366cfd67fd040000c64340050080757e032ea08ede7bbb40cf87d0f31154001dae497a37c09e8fafc7e77e6cf76bc7583f010080f110500100609ddf800ba883742e8a9e0f9fe783e8fb3b46e1531c37ef08aaa7cd8f27e96d88155bd7c0fa0900008c87800a0000ebfc065c40ed957347e79e0fa3f33b4177d2faeffc8436e7ef849ddff93a699e5755559d18a272d64f0000603c0454000058e737e0026a67e49ce751f44371f4c0083160c7cdfbfdc83aa9aa6a62783eccfa0900008c87800a0000ebfc065c405dabe668dd7920fd9fe9b7c114f8adc9b9e7ff268175cafa0900008c87800a0000ebfc065c405daa73bb48779a677eefe881d181959934cf7c07eb71fc645555c743fe97b67e020000e321a00200c03abf0117503f99480abd32bf87759266bb57a73f1ec2fdabd64f0000603c0454000058e737e002ea4239e79d340ba407f5f3bf9a1fcf8fdc05fa6f92deee5c3d1f57277df887b77e020000e321a00200c03abf0117501785d2039f0e18b5e3f476d7eaf4c75d0babd64f0000603c0454000058e737e0230aa84229b00427e9ed8ed5e3b4c1b06afd040000c64340050080757e033ed0809a733e486fef27dd4f8ede0556eb38cd76ac9ea626b25655f56a957f43eb270000301e022a0000acf31bf09e07d49c7344d188a30769b6ab741e4b01366dd23c7f4f6fef573d59d62f6efd040000c64340050080757e03dea380fa5e2c9def2cddf1bb08f4cc717a7b0c70ec543d2ef945ac9f0000c07808a80000b0ce6fc03b1a50c5526064e677ab4e8f00be4854b57e020000e321a00200c03abf01ef48406dee2c8d48faaf492c0508ad51d5fa0900008c87800a0000ebfc067c030135e73cbfa73476961e247796025cd4717a37aa9e1812807eb1fe0d400901150000d6f90df88a03ea7b47f1ce77975e36f2004bf12acd82eadf9bf7715555af0c0b407759ff06a084800a0000ebfc067cc90135e7bc93dede5b1a6fbb4b01d66b92663b556397eab15daa00dd62fd1b8012022a0000acf31bf0cf0ca8cd71bc07c9dda5005d769c66bb54e37d62972ac0e658ff06a084800a0000ebfc06fc13036acef920398e17a0efa6c7fda6e6e8dfaaaa268604603dac7f03504240050080757e03fe9180fa5e303d306200833449e776a90aaa00ab63fd1b8012022a0000acf31bf0f702aa600a409a05d5d8a53a0faaee51055812ebdf009410500100608dbef9e69b3886f77a124c01582cee4c3d4e822ac067b3fe0d4009011580f689e213efea03e05d39e708a607e96d30758729009f6a921cf90b50c4fa370025045400da270a0115e093e49c77d2bbc174c7a800b06493f436a83eadaaea952101f830ebdf0094105001689f280454808fca399f3f9277df8800b06671c4ef71fdfcbdaaaaa78603e02debdf0094105001689f28045480df78ef58deeb4604808e39ae9f7f4fee4f051050012822a002d03e5108a800114ce3ded283faf93fc9b1bc00f44b1cef1bbb521df70b8c92f56f004a08a800b44f14022a3052cd5da6b1bb741e4d01600862476aec4e7d6a772a3006d6bf012821a002d03e5108a8c0889cbbcb34de3b46048081b33b15183cebdf0094105001689f28045460c09aa379cfef32bd6c54001831bb5381c1b1fe0d4009011580f6894240050626e7bc9f66b1f456fdec1b1100f8a049fd1cd7cfbf5755f5d470007d65fd1b8012022a00ed1385800a0c40cef920cd76993a9a1700ca44448ddda9c755554d0c07d017d6bf012821a002d03e5108a8404f35f799cea3a9a379016079e278df476916531df50b749af56f004a08a800b44f14022ad013ee3305808d98a466776a5555c78603e81aebdf0094105001689f280454a0c3de8ba6d78d08006cd4abf436a6ba3715e804ebdf0094105001689f280454a063445300e885373135cd8efa7d6548804db0fe0d4009011580f6894240053a20e7bc9366b1f456fdec1b1100e89d794c7d2aa602eb64fd1b8012022a00ed1385800a6cc8b99da67f4aa229000c89980aac8df56f004a08a800b44f14022ab0468ee70580d111538195b2fe0d4009011580f689424005564c3405001a622ab074d6bf012821a002d03e5108a8c08ae49ce7779a8aa600c0fb8eeae7dfabaa7a6a2880cf61fd1b8012022a00ed1385800a2c51134de73b4d2f1b1100e0236227ea7467aa980a94b0fe0d4009011580f6894240053e53ce793fbddd69ba634400804211538feae7515555278603b808ebdf0094105001689f280454a040ce7927cd826984d37d2302002cd9a47e1ed5cf51555513c3012c62fd1b8012022a00ed1385800a5c50ce398ee49d47d303230200ac49ec469dc7d457860338cffa370025045400da270a0115f8889cf3417a7b44af7b4d01804d9adf977a64288060fd1b8012022a00ed1385800a7c407344ef9f927b4d01806e8a9da811537f745f2a8c9bf56f004a08a800b44f14022ad070442f00d05393faf9b17e9eba2f15c6c7fa370025045400da270a011546cf11bd00c08038e21746c6fa370025045400da270a011546a939a23782691cd3bb634400808171c42f8c84f56f004a08a800b44f14022a8c4ace797e44ef75a301008c4404d4f911bfaf0c070c8bf56f004a08a800b44f14022a0c5eb3dbf430cdc2e98e11010046eca87e1e5555756c286018ac7f0350424005a07da2105061b0ec36050058689266bb528fec4a857eb3fe0d4009011580f68942408541b1db1400e0931d25bb52a1b7ac7f0350424005a07da210506110ec360500f86c9364572af48ef56f004a08a800b44f14022af456cef972fdba93ec36050058b6a364572af482f56f004a08a800b44f14022af44ecef920cda2e9a1d1000058a949fddcaf9fa776a5423759ff06a084800a40fb4421a0422f34bb4de378deef92dda60000eb16f1f469fddcafaa6a6238a03bac7f0350424005a07da21050a1d372ce3bf5eb4f69b6dbf4b2110100d8b8e3343bdef7c850c0e659ff06a084800a40fb4421a04227e59c63b76984d303a30100d04993fa79543f0f1cef0b9b63fd1b8012022a00ed1385800a9dd11cd37b9866e174c7880000f4c651fdfc5855d589a180f5b2fe0d4009011580f6894240858d6b8ee98dbb4d63d7a9637a0100faeb3839de17d6cafa370025045400da270a01153626e77c9066bb4daf1b0d0080419924c7fbc25a58ff06a084800a40fb4421a0c2dae59c0fd36cc7e98ed1000018bca3fab95f55d5c450c0f259ff06a084800a40fb4421a0c25a34f79bde49b31da78ee90500189fe3340ba9c7860296c7fa370025045400da270a011556eadcfda68746030080343bdef7be7b526139ac7f0350424005a07da2105061259afb4d239c1e180d00003e20ee46fd31b927153e8bf56f004a08a800b44f14022a2c5573bf691cd3bb6f340000b88088a74f937b52a188f56f004a08a800b44f14022a7cb6e67ed3eb69b6e374c788000050e8a87e7eacaaeac450c0c558ff06a084800a40fb4421a042b1269cde49b31da7978d0800004b729c663b528f0d05b4b3fe0d4009011580f6894240854f9673de49b3dda6b1eb54380500605562276aec483d3214f061d6bf012821a002d03e5108a87061e7c2e9a1d10000608d2669b623f5c850c0bbac7f0350424005a07da21050e1a372ce0769164e0f8c0600001b34a99f47f5f3a0aaaa578603045400ca08a800b44f14022a2c249c0200d051114f7f4c422a08a80014115001689f280454f88d9c73dc6dfaa7249c0200d06d422aa367fd1b8012022a00ed1385800a6fe49c0fd36cc7e98ed10000a047229e3e4db37b5227868331b1fe0d4009011580f689424005e114008021394a422a2362fd1b8012022a00ed138580ca8809a700000cd85112521901ebdf0094105001689f280454464838050060448e9290ca8059ff06a084800a40fb4421a03222c2290000237694845406c8fa370025045400da270a019511104e0100e08da324a43220d6bf012821a002d03e5108a80c98700a00000b1d25219501b0fe0d4009011580f6894240658084530000b8b0a324a4d263d6bf012821a002d03e5108a80c88700a0000c5eed7cf83aaaa5e190afac4fa370025045400da270a019501104e01006029229efe9884547ac4fa370025045400da270a01951ecb391fd4af874938050080651252e90debdf0094105001689f2804547aa809a7b1e3f4c0680000c0ca4c436a5555f70c055d65fd1b8012022a00ed1385804a8f08a70000b01193fab95f55d591a1a06bac7f0350424005a07da21050e9819cf34efdfaa17eae1b0d0000d898491252e918ebdf0094105001689f2804543aac09a7b1e3f4d068000040674ceae7765555c786824db3fe0d4009011580f6894240a58372ce97ebd79d348ba7000040371da7d98ed46343c1a658ff06a084800a40fb4421a0d221e7c2e99feae7b2110100805e384eb31da91343c1ba59ff06a084800a40fb4421a0d21139e7c334db71ba63340000a0978ed26c47eac450b02ed6bf012821a002d03e5108a86c58cef9a07e3d4cc22900000cc1abfaf9b17e1e5455f5ca70b06ad6bf012821a002d03e5108a86c48134e63c7e981d1000080c19986d4aaaaee190a56c9fa370025045400da270a019535cb39efa459383d341a0000307893343bd6f7c850b00ad6bf012821a002d03e5108a8ac49cef972fdba9366f11400001897e3340ba9c7868265b2fe0d4009011580f6894240650d72cef3707ad9680000c0a83dad9fbb55554d0c05cb60fd1b8012022a00ed138580ca0a35f79c3eac9f1da30100009cf320cd76a4be32147c0eebdf0094105001689f2804545620e7bc5fbf7ea89f03a30100002c10f13422ea03434129ebdf0094105001689f28045496a8b9e734c2e9a1d10000002e68523fb7dd8f4a09ebdf0094105001689f2804549624e77caf7efd29b9e714000028739c6621756228b828ebdf0094105001689f2804543e53cef97a9aed3add311a00dd75e9d2a5f44ffff44f1ffcdf7677775bffdaededede95fbf49af5fbf4e676767ad7fceafbffe3afdf32efaf3007496fb51b930ebdf0094105001689f2804540ae59c77ead7c3e49e538095dbdada9a3e7311347ff7bbdfbdf3c7e70367fc6ff1732cf6f2e5cbe973de2fbffcf2ce1fbf78f1e2cd8fffebbffeeba3011780a5723f2a1762fd1b8012022a00ed138580ca276aee39fdae7eee180d8032f31d9fe77785c68fcf47cf8fed0a65b322a646540d1162fff33ffff3cd8fe761f6439116804f3649ee47a585f56f004a08a800b44f14022a9f20e77c9866c7f5bae714e03de77789cee3e7ef7ffffb373f17a174d3c7e0b239f3e07afe28e27960b5bb15e0429ed6cf5df7a3f23eebdf0094105001689f2804542e20e77c9066e174df680063348fa3e7778cfee10f7f98be855196691e5ae7bb5acfc7d5f3470a038cd8fdfa79e07e54e6ac7f0350424005a07da2105069d11cd71be1f4d0680043360fa4f367be73f4fdbb47a10be62135c26aec68fdf5d75fdf79038cc024cd76a33e351458ff06a084800a40fb4421a0b240ce39ee388dbb4e1dd70b0cc2fc58dd78cfef1b154819a27960fde5975fdeec60751f2b3050c769763feac4508c97f56f004a08a800b44f14022aef715c2fd06711457ff7bbdfbd13491db10b6fcd43ea3caa8aabc04038d677c4ac7f0350424005a07da210506938ae17e89308a4f3dda371176944d388a540b90f4555f7ae023d32498ef51d25ebdf0094105001689f28045492e37a81ee9a87d288a38edc85cd98c7d438125858057ae03839d67754ac7f0350424005a07da2105047cd71bd4057cc8fda9def28154aa1fb8455a0e31ceb3b12d6bf012821a002d03e5108a8a3e4b85e6053e22ed208a5b1b3f4f7bffffdf4c78ede8561397f1470045577ac021b3449b3dda8c78662b8ac7f0350424005a07da210504727e77c9866f1d471bdc04acd7791462c3d7f042f303eaf5fbf4ebffefaeb9bddaaf1e308ac006b12f7a2de75acef3059ff06a084800a40fb4421a08e46ce398ee98d707a603480658b9da5114a6347691cc11beff8398036b14335426a3ca22ab0627194effdaaaa1e188a61b1fe0d4009011580f68942401dbce6b8de3bf5f39dd10096e1fc31bc769602cb26aa022b769266c7fa9e188a61b0fe0d4009011580f68942401db49cf341fd7a583f3b4603281581741e4bdd590a6c4244d538fe771e54dda90a2c41ec448d1da9af0c45bf59ff06a084800a40fb4421a00e52b3eb34c2e975a3017c8af78fe28d1f03744d04d408a9f3ddaaf106283049b3bb519f1a8afeb2fe0d4009011580f68942401d9c9cf3fcb8decb4603f898387a3722e9fcde52bb4b81be3a1f53ed52053e5104d4db76a3f693f56f004a08a800b44f1402ea60e49c77d26cd7e981d10016991fc73b7f62c729c01045407d3faa02b488781a47fa3e3014fd62fd1b8012022a00ed1385803a0839e77b69b6eb14e01d8229c0ccebd7afa72175fe08aac002c769b61b756228fac1fa370025045400da270a01b5d772cefb69b6eb74df6800413005b8b808a9bffcf2cb9ba80a704eec46bd6718bacffa370025045400da270a01b59772ce71bf69ec38bd633460dc045380e5115481f79ca4d96ed41343d15dd6bf012821a002d03e5108a8bd93733e48b35da73b4603c6676b6b6b1a4afff0873fa4fdfd7dc11460854e4f4f1df90b84b8173576a4be3214dd63fd1b8012022a00ed138580da1b769dc23845208d60bab7b7377d47400560fde677a8cea3eacb972f0d0a8ccb24cd76a31e1b8a6eb1fe0d4009011580f6894240ed859cf3f534db757ad968c0f09d0fa671442f00dd1301f57c508dc00a8c82dda81d63fd1b8012022a00ed138580da69cdaed308a7d78d060cd7f97b4c239c02d03f71c46fc4d4939313c7fdc2f04d92dda89d61fd1b8012022a00ed138580da59769dc27039961760d81cf70ba361376a0758ff06a084800a40fb4421a0768e5da7304cf35da6f3680ac078c48ed4f34115189449b21b75a3ac7f0350424005a07da210503bc5ae531896792c8db75da60084d89d1ac7fcfef2cb2fd3b7bb536130ec46dd10ebdf0094105001689f2804d44eb0eb1486218ee6dddfdf9f06537799027011b13bf5f9f3e7d39da9ee4e85de9b24bb51d7cefa370025045400da270a0175e3ec3a857e9b1fcd7be5ca95e98f01a054dc951ac7fc3aea177acf6ed435b2fe0d4009011580f6894240dd18bb4ea1bf2294463075342f00ab323fea771e531df50bbd334976a3ae85f56f004a08a800b44f1402ea46e49c0fead75f925da7d01bf36379e388de38aa1700d669be33d5bda9d03b0faaaaba6b1856c7fa370025045400da270a0175ad9a5da7dfd5cf1da301dd279a02d045b123751e54e3d85fa0f34ed26c37ea89a1583eebdf0094105001689f2804d4b569769dc691bd3b4603ba292269c4d279380580ae3b3b3b4bcf9f3f1753a11fe25ed47b8661b9ac7f0350424005a07da21050d722e7fc43b2eb143a4934056028c454e885e334db8d3a3114cb61fd1b8012022a00ed138580ba5239e7fd34db75ba6f34a05bae5cb9229a023058622a74daab34db8dfac0507c3eebdf0094105001689f2804d495c939df4bb3fb4e818e70a729006324a642673d4db3dda8af0c4539ebdf0094105001689f2804d4a5cb39efa4d9aed303a3019b279a02c05b11539f3d7b964e4e4ed2ebd7af0d086c5ec4d388a84f0d4519ebdf0094105001689f2804d4a5ca391fd6afb8eff4b2d180cdd9dede7e7344efd6d6960101800f881da9f188a9d009719cef7dbb513f9df56f004a08a800b44f1402ea52e49c2398c6aed3eb46033623426904d3ab57af8aa600f009229e46449d075560634ed26c37ea89a1b838ebdf0094105001689f2804d4cf96733e48b378ba633460bde248de389a37a269ec3a05003e4fc4d4b82f359e38ee17d888bb55553d300c1763fd1b8012022a00ed138580fa5972cef7ead7774602d66b7eaf691cd30b00acc6cb972fa7f7a5c6aed4f831b056c7f5f34747fa7e9cf56f004a08a800b44f14026a919cf34efdfa4bfdec1b0d588ff9bda6f1c4ce5300607d5ebc7891fef18f7fb82f15d62be2691ce9fbd4502c66fd1b8012022a00ed138580fac972ce87f5eb87fab96c3460b51cd10b00dd32bf2f35626a4455602de238dffb76a37e98f56f004a08a800b44f1402ea85e59c2398c65da7d78d06ac96237a01a0fbe258df08a9715faa237e61e54ed26c37ea89a17897f56f004a08a800b44f1402ea85e49ce3a8de38b277c768c06a6c6d6d4d83e9bffccbbf4c7f0c00f447dc931a4fc454606562076aec447d6028deb2fe0d4009011580f6894240fda89cf3bdfaf59d9180d598ef348d3700d06f71c46f44d467cf9ed9950aab1377a2de76a4ef8cf56f004a08a800b44f1402ea42cd91bdb1ebf4c068c072c50ed3b8d734c269dc730a000ccfd9d9d934a4c69da9115681a59ad4cf1f1de92ba00250464005a07da210503f28e71cf79cc67da7978d062ccffc88dedddd5d8301002311f134226ac4d488aac052dd1dfb91bed6bf012821a002d03e5108a8bf9173fea17edd3112b01cee360500e6ec4a8595384eb3dda8a33cd2d7fa370025045400da270a01f58d9cf34e9a1dd9bb6f34e0f3b9db140058c45da9b074114f23a21e8fed5fdcfa370025045400da270a0175ca91bdb01c719f6944d3b8dfd46e5300e0225ebc7891fef18f7f4c832af0d9ee5755756f4cffc2d6bf012821a002d03e5108a88eec8525d8dede9e46d388a70000256227ea3ca4da950a9fe5388de8485febdf0094105001689f28461c501dd90b9f6f7eb7e9eeeeaec100009626226ac4d4d89d0a148978fa6555552743ff17b5fe0d4009011580f68962a401d591bd502e8ee9fdeaabafa6e1d431bd00c02a9d9d9d4def4975bc2f14bb5b55d58321ff0b5aff06a084800a40fb4431c280eac85e28e3985e0060535ebf7efd26a43ade173ed9d3fab93dd4237dad7f0350424005a07da218514075642f94d9dbdb9b8653c7f402005de0785f283249b37b510777a4aff56f004a08a800b44f142309a839e783348ba78eec850b88637a63a7698453c7f402005d14013542aae37de193c44ed4a321fd0b59ff06a084800a40fb443182809a73be57bfbef3bb0d1f17b1741e4e23a20200745d1ce93b3fde378efa053eea28cdee461dc491bed6bf012821a002d03e510c38a0e69c63b769ec3a3df03b0deddc6f0a00f45dc4d388a81153dd930a1f1547f9c691be93beff8b58ff06a084800a40fb4431d0809a738e7b4e239eeef85d86c5e25ed3afbffedafda600c0a0cc43ead9d999c180c562076a1ce9fbb4cfff12d6bf012821a002d03e510c30a0e69c0febd743bfbbb058ec348d70ea7e530060c8e29ed49f7efa69fa0616ba5f55d5bdbefec35bff06a084800a40fb4431a080da1cd9fb43fd1cfa9d85df8a3b4de7f79b0aa700c098c491be115263672af041c76976a46fefee45b5fe0d4009011580f68962200135e7bc936647f6eefb5d85774538fdeaabafa6e1347e0c003056f3907a727232bd331578c724cd22ea499ffea1ad7f0350424005a07da2184040cd395f4fb3237b2ffb1d85b762976944d3d8752a9c0200bc15f134ee48fdf9e79f85547857ec40bd5b55d5515ffe81ad7f0350424005a07da2e87940cd39dfab5fdff99d84b7229cc6fda6114e0100586c1e52e368dfd89d0abcf1a0aaaabb7df807b5fe0d4009011580f689a2a701b5b9ef34769d5ef7bb0833c2290040b988a871bcaf900a6f1ca71edc8b6afd1b8012022a00ed13450f036ace39ee398d78eabe53a8edeeee4e8feadddbdb331800009f494885774c52c7ef45b5fe0d4009011580f689a26701d57da7f05684d3d8711a6f0000964b4885373a7d2faaf56f004a08a800b44f143d0aa8ee3b8519e11400607d845478a393f7a25aff06a084800a40fb44d18380eabe5398114e0100362742eab367cfd2d9d999c160cc8e53c7ee45b5fe0d4009011580f689a2e301d57da7209c020074891da9d0ad7b51ad7f0350424005a07da2e87040cd391fd4afbf24f79d3252c22900407709a98c5cec40bd5d55d5d34dff8358ff06a084800a40fb44d1d1809a73be53bf7ef03bc41809a70000fd21a43272f7abaabab7c97f00ebdf0094105001689f283a185073ce7164efa1df1dc6667b7b3bddb87143380500e8212195113baa9fbb9bba17d5fa370025045400da278a0e05d49c731cd5fbb7e4be5346666b6b6bbae3f4ca952b060300a0e78454462aee43fd721311d5fa370025045400da278a8e04d49c7344d388a7ee3b653484530080e18a90fae4c993f4faf56b83c158443c8d887ab2cebfa9f56f004a08a800b44f141d08a839e7c334bbef543c65142e5dba343daa5738050018b688a7cf9e3d4b3ffffcb390ca5844448de37c8fd6f537b4fe0d4009011580f68962c30135e77caf7e7de77782318870fad5575fa5ab57af4e7f0c00c03808a98cd0fdaaaaeeade36f64fd1b8012022a00ed13c586026a73df69ec3a3df4bbc0d009a7000084b81735ee478de37d61048eaaaababdeabf89f56f004a08a800b44f141b08a84d3c8dfb4ef7fd0e3074714c6fdc731af79d02004088901af7a39e9e9e1a0c862eee438d7b515fadea6f60fd1b8012022a00ed13c59a036ace39a269c453f79d32687b7b7bd37b4e855300001679f1e2c574476abc61c026f5f3c7aaaa4e56f18b5bff06a084800a40fb44b1c6809a73be5ebf1e26f19401dbdddd9dee388d3700005c44ec448d1da9b13315062a76a046443d5ef62f6cfd1b8012022a00ed13c59a026acef930cde2290c52ec348d1da7b1f31400004ac4dda811525fbf7e6d3018aadb55551d2df317b4fe0d4009011580f689620d0135e71ce1f4d0683344972e5d4ad7ae5d4b57af5e351800007cb688a7cf9e3d4b3ffffcb390ca503da8aaeaeeb27e31ebdf0094105001689f2856185073ce7154ef5feae7c0483344f3701a1115000096298ef38dfb5163572a0cd051fddcadaaead5e7fe42d6bf012821a002d03e51ac28a036f1f46ff5b36f94199a2b57ae4cef398d637b010060955ebc78310da9f1868139a99f2f3f37a25aff06a084800a40fb44b182809a738e681af1f4b2116648767777a7e134de0000b04ea7a7a7d3fb5163672a0cc8a47efe5855d549e92f60fd1b8012022a00ed13c592036acef97afd8a3b4fc5530623769a46388d9da70000b049b11bd5fda80c4cec40fdb234a25aff06a084800a40fb44b1c4809a733e4cb3780a8310779b7ef5d557ee390500a053229ec66e54f7a33230b7abaa3afad4bfc8fa370025045400da278a2505d49cf30ff5eb8e116528dc730a0040d7b91f9501ba5b55d5834ff90bac7f0350424005a07da2584240cd39c7aed343a3c9106c6f6fa71b376eb8e7140080de889da8b123d5b1be0cc4515555b72ffa275bff06a084800a40fb44f1190135e71cf79cfead7ef68d247d1747f4463875cf2900007d14f1f4d9b367e9af7ffdabc160088ed26c37eaab8ffd89d6bf012821a002d03e511406d49cf34efdfa4b124f1980b8e3f4dab56bee390500a0f75ebe7c991e3f7eec585f86e0a47ebefc5844b5fe0d4009011580f689a220a0e69c239ac6ced3cb46903e8b637a63d7691cdb0b000043727a7a3a3dd637822af45844d43f56553559f42758ff06a084800a40fb44f1890135e77c90663b4fc5537a6b6b6b6b1a4ef7f6f60c06000083e5585f062276a0c64ed4930ffd8fd6bf012821a002d03e517c4240cd391fd6af87468d3e8ba37ae3c85ec7f5020030168ef56500164654ebdf0094105001689f282e1850c553fa2e8eebbd79f3e674f72900008c91637d1980db55551d9dff09ebdf0094105001689f282e105073ce114e0f8d167d143b4de3b8de2b57ae180c0000462f8ef58d237de3685fe8a97722aaf56f004a08a800b44f141f09a8e2297d1647f5c691bd8eeb050080779d9d9da5478f1e4ddfd0430faaaaba1b3fb0fe0d4009011580f689624140cd395fae5f7fa99f03a344df6c6f6fa75bb76e4ddf0000c062b1133576a4c6ce54e899a3aaaa6e5bff06a084800a40fb44f18180dac4d3bfd5cfbe11a24f62a769ec388d9da70000c0c5443c8ddda871472af4ccd19ffffce7db8601804f25a002d03e51bc175073ce3b69b6f3543ca557f6f6f6a6bb4e1dd70b00006522a03e79f224bd7cf9d260d02727f5f3655555afceffa4757100da08a800b44f14e7026ace39a269ec3cbd6c64e88badadad74f3e6cdb4bbbb6b300000e033c56ed438d2378ef6851ef94d44b52e0e401b011580f689a209a8e2297d343faed7ae53000058aeb3b3b3e9b1bef1869e7827a25a1707a08d800a40fb44f1c517114f0fd2ecd85ef1945ed8dede9e1ed71b6f000060757efae9a7e98e54e88988a711514fac8b03d0464005a0d537df7c7358bf1e1a09fa20769ace779d020000eb1177a23e7efc38bd78f1c260d007d388fae73ffff9c45000b088800ad0d72fe0e7ee265d959cf361124fe989b8e334ee3a8d3b4f010080f58b7b5163376adc930a1df76627aaa118377d0458444005e8eb17f0150754f194be885da7715cefdede9ec10000800db31b951e89887abbaaaaa78662bcf41160110115a0af5fc057185073ce3fd4af3b4699ae8b681af134222a0000d01d76a3d22311518f0cc338e923c022022a405fbf80af28a0e69c63d7e9a111a6cbec3a050080eeb31b951e1151474a1f0116115001fafa057c0501553ca50fae5ebd9aae5dbb66d7290000f484dda8f484883a42fa08b088800ad0d72fe04b0ea8e2295db7b5b5956edebc997677770d060000f48cdda8f484883a32fa08b088800ad0d72fe04b0aa839e7cbf52bee3c3d34aa74955da70000300c76a3d203475555dd360ce3a08f008b08a8007dfd02be8480dac4d3bfd5cfbe11a58bec3a050080e1b11b951e105147421f0116115001fafa05fc3303aa784ad7d9750a0000c366372a1d27a28e803e022c22a002f4f50bf8670454f1942eb3eb140000c6e3ecec2c3d7af468fa860e1251074e1f0116115001fafa05bc30a08aa774d9dede5eba75eb965da7000030323ffdf4d374372a7490883a60fa08b088800ad0d72fe00501553ca5ab229846388d800a00008c53ec42adaa6a7a472a748c883a50fa08b088800ad0d72fe09f1850c553bacaae530000602eee438d9da8713f2a748c883a40fa08b088800ad0d72fe09f1050c553ba2882e98d1b37d2952b570c060000f08ed3d3d3e9dda81154a14344d481d1478045045480be7e01bf6040cd3947348d787ad9a8d115bbbbbbe9e6cd9b696b6bcb600000001f14f1348ef47df1e285c1a04b8eebe78ff567f395a1e83f7d0458444005e8eb17f00b0454f1942e8a5da757af5e35100000c085c471be4f9e3c311074c949fd7c29a2f69f3e022c22a002f4f50bf84702aa784ad76c6f6f4fef3a8d370000c0a7383b3b9b1ee91b6fe808117500f41160110115a0af5fc05b02aa784ad7c48ed3d8790a0000502a8ef4fdeb5fff3add910a1d21a2f69c3e022c22a002f4f50bf882802a9ed225972e5d8acfe4f4ce5300008065383d3d9dee468da00a1d20a2f6983e022c22a002f4f50bf80702aa784a97eceded4d8fec8d880a0000b04c2f5fbe4c8f1f3f4e2f5ebc3018748188da53fa08b088800ad0d72fe0ef0554f194ae88607aeddab5e9b1bd000000abf4d34f3f4d8ff5850e10517b481f0116115001fafa05fc5c40154fe98aededede9aed378030000ac43ec42adaaca91be748188da33fa08b088800ad0d72fe04d40154fe98ad8717ae3c60d03010000ac5dc4d388a88ef4a50344d41ed1478045045480be7e01ffe20bf1944e88237bebcf62dadddd35180000c04639d2978e38aaaaeab661e83e7d0458444005e8a96fbef9463c65e3229a463c8d880a0000d0058ef4a52344d41ed04780450454fafbe13d77ff238c4dce39a2e97fd4cf8ed16053ae5dbb96befefa6b03010000748e237de90811b5e3f41160110195fe7e78055446aa89a7b1f374df68b0095b5b5bd35da7dbdbdb06030000e83447fad201226a87e923c022022afdfdf00aa88c9078caa6edededa55bb76e39b2170000e80d47fad201226a47e923c022022afdfdf00aa88c8c78caa6ddb871235dbd7ad540000000bd13f1f4fbefbf4f67676706834d11513b481f01161150e9ef8757406544c45336c991bd0000c0503c79f2243d7bf6cc40b029226ac7e823c022022afdfdf00aa88c8478ca2639b2170000189ad3d3d3f4e8d12347fab229226a87e823c022022afdfdf00aa88c8078ca2639b217000018aa38ca3722aa237dd990fb5555dd330c9ba78f008b08a8f4f7c32ba0320239e7ff48e2296be6c85e0000600c62076a44d4d8910a1b70bbaaaa23c3b059fa08b088804a7f3fbc022a0397737e58bf0e8d04ebe4c85e0000606ce24ed4b81b15364044dd307d04584440a5bf1f5e019501134fd9846bd7aea5afbffeda40000000a3f3e2c58b5455957b51d904117583f41160110195fe7e780554064a3c65dd62b7691cd9bbbbbb6b30000080d17af9f2e534a2ba17950d105137441f01161150e9ef8757406580c453d62dee39fdf6db6f1dd90b00009066f7a2c671becf9f3f3718acdb3f5755756218d64b1f01161150e9ef875740656072cef7ead777468275b97af56aba71e38681000000788f7b51d98057f5f3a588ba5efa08b088804a7f3fbc022a0392733eac5f0f8d04eb10bb4d239c5eb972c5600000002ce05e543640445d337d04584440a5bf1f5e019581104f59a7adadade97da771742f000000ede23ed4478f1eb91795751251d7481f01161150e9ef8757406500c453d6696f6f2fddba75cb7da70000009f2076a046443d3d3d3518accb24cdee447d6528564b1f01161150e9ef875740a5e772cefbf5eb6ff573d968b06ad7ae5d4b5f7ffdb581000000281477a2c6dda8b026b103f54b1175b5f41160110195fe7e7805547a4c3c655d62b769ec3a8ddda70000007c9ee7cf9fa7c78f1f1b08d645445d317d0458e47f180280f5124f5997b8e7f4db6fbf154f01000096e4ca952be9dffeeddf5c8dc2bac41ad25f0c03c0fad9814a7f3fbc76a0d24339e788a6ff513f3b46835572df290000c0eabc7cf932555595cecece0c06eb70547fde6e1b86e5d34780450454fafbe11550e999269ec6ced37da3c12ab9ef14000060f55ebf7e9d1e3d7a944e4f4f0d06eb20a2ae803e022c22a0d2df0faf804a8f88a7ac43ec36bd71e3c6f44829000000d6e3c99327e9d9b367068275b85b55d503c3b03cfa08b0883b5001d6e387249eb2425b5b5bd3fb4ec553000080f58aff90f5e6cd9b068275f821e77c68180056cf0e54fafbe1b503959ea8bfb17d58bf7c73cbca6c6f6f4fe3a9fb4e01000036e7c58b17d37b51e3685f58b1dbf567edc8307c3e7d04584440a5bf1f5e01951ec839c7ced33b468255891da7fe4b670000806e383b3b9b46d4972f5f1a0c56e955fd7c597fd64e0cc5e7d14780450454fafbe11550e9b8e64895874682558970eac85e0000806e891da8df7ffffd34a6c20a89a84ba08f008b08a8f4f7c32ba0d261e229ab1447f5d69fb1b4bbbb6b300000003a2822eaa3478fd2e9e9a9c1609522a2feefaaaa5e198a32fa08b088804a7f3fbc022a1d9573deaf5f7fab9fcb4683658bfb4e6fddba357d030000d06d8f1f3f4ecf9f3f3710ac52ec40fd52442da38f008bfc0f4300b03ce229ab143b4ebffdf65bf1140000a027e2ea951b376e18085669be1605c012d9814a7f3fbc76a0d23139e788a6ff513f3b4683658bbb4ee3ff78030000d03fb10b3576a3c20a1d555575db307c1a7d04584440a5bf1f5e01950e69e269fcd77efb4683658bff5af9ead5ab06020000a0c7cececed2f7df7f3fbd1f1556e4415555770dc3c5e923c0228ef005588e87493c65c92e5dba14715e3c0500001880b88e25ae6589ffaf072b7227e77c6818003e9f1da8f4f7c36b072a1d517f631af1d437a72c55fc1f6af79d0200000c4fec408d9da8b1231556e48f55553d350c1fa78f008b08a8f4f7c32ba0d20139e73bf5eb0723c132f9af92010000864d4465c55ed5cf9755559d188a76fa08b088804a7f3fbc022a1bd61c89f2d048b04c7b7b7be9d6ad5be2290000c0c045447df2e4497afefcb9c1601522a2fe7355551343f1ffd9bb7be0a8ae6c6dc0c7539340a46ba5a26e4f8052c9198aa6478450359a40a9a4c9f68d8c49b9553818a7f065f76436a40433811d9a718633432a025305292e2208fdf56aba197ed4a09f3edd67effd3c555d07db33fc2c3547a7f7bbd7dab3c947805904a8e4fbe615a0b24429a538eff41795609eb6b6b69abdbd3d85000000a8c8ddbb7785a874253a50a313f585521c4d3e02ccf247250038994978fa6f95609e22388d0015000080fa3e0fc614a2fbf7ef2b06f3365dc3fa4229004e46072af9be7975a0b20429a595c983e7a66a300ff12179777757780a000050b9e8428d6e54e8c0776ddbfe5d193e241f0166f98312009c88f094b989f0f4faf5ebc253000000c69f0d63832d74e020a5f4b532001c9f0015e098460f9adf36c253e6646d6d6d1c9ec6150000005ebd7ae52c54ba7433a574a00c00c763842ff9be798df0658126bbf46eaa04f3300d4fa30315000000223cbd75eb56f3ecd933c5a06b5fb46dfb50195e938f00b30850c9f7cd2b40654126bbf3be5509e6616363a3d9dfdf179e02000030263c65c15e8c5e7f11a2be261f016611a092ef9b5780ca02a4946264ef2f2ac13cc479367b7b7b0a010000c098f0942589f03442d417b517423e02cce20c548019524a83d1e5df2ac13c5cbd7a55780a0000c01bc25396281a06fea90c00b3e94025df37af0e543a94525a695e87a79baac15945701adda70000001084a7f4c4776ddbfebde602c847805974a0021c2dce3c159e722671cea9f014000080b7094fe9918394d28132007c48072af9be7975a0d291d183e3edd1e59a4a7016119e5ebf7ebd595b5b530c000000c684a7f4d4dfdab6fd578d7f70f908308b00957cdfbc02543a30d975f7ad4a7016c253000000de273ca5c75e8c5e7f69dbf6616d7f70f908308b00957cdfbc0254e62ca514237b7f5109ce2242d3fdfd7de1290000006f084fc9c093d1eb8bb66d5fd4f487968f00b3380315a01987a783d1e5df2ac1594468aaf314000080b7094fc9c460f4faa73200bca603957cdfbc3a50999394d24af33a3cdd540d4e6b1a9ec6f85e00000008c25332f45ddbb67fafe50f2b1f0166d1810af0facc53e129a7b6b5b5253c050000e01dc253327590523a5006a0763a50c9f7cdab039539183d107e3dbadc54094e2bc2d3bdbd3d85000000e00de12905f84bdbb63f95fe87948f00b30850c9f7cd2b40e58c524a3b8db31d3803e129000000ef139e528817a3d7176ddb3e29f90f291f016611a092ef9b5780ca19a49462646f9c7bbaa21a9cc6f6f676b3bbbbab10000000bc213ca5300f9bd79da82f4afd03ca478059fea804406d524a119a46e7a9f0945389aed3e83e0500008029e129058a06846f47afbf2905509b3f280150a1084f07cac069084f010000789ff09482eda494be5606a03646f892ef9bd7085f4e61f4c0777b74b9a6129c86f014000080f7094fa9c4dfdab6fd57697f28f908308b00957cdfbc02544e28a574d0bc1e3b0227263c050000e07dc2532a12e7a0c679a80f4bfa43c947805904a8e4fbe615a0720229a538b3e1df8d734f39a173e7ce35fbfbfbcdc6c68662000000f086f0940a45781a21ea8b52fe40f211601667a002c54b2945681ae79e0a4f3991084faf5fbf2e3c050000e01dc2532a150d0aa6bb015510a0023588f074a00c9cc4343c5d5b5b530c000000de109e52b99d94d2d7ca00944e800a146df2403754094e42780a0000c02c77eedc119e52bb9b29a51d65004ae60c54f27df33a03954f983cc8fd53253809e129000000b3dcbd7bb779f0e0814240d3c439a8711eeac39cff10f2116016012af9be7905a87c444a29ce64f877e3dc534e40780a0000c02cc253f84084a711a2bec8f50f201f016631c217284e4a2942d338d05e78cab1094f0100009845780a478a06866f95012891001528d1edc9031c1c8bf0140000805984a7f0513b29a56bca0094c6085ff27df31ae1cb11260f6cb75582e3129e020000308bf0148e2d46f9fe94db6f5a3e02cc224025df37af0095f74cce3dfd4525382ee129000000b3084fe144e21cd43fe5761eaa7c0498c5085fa00893734fffad121c97f0140000805984a77062d6e680a208508152fc73f2a0069f243c0500006016e1299cda664ac9d15a40118cf025df37af11be4c8c1eccbe1e5d6eaa04c7213c0500006016e129ccc5dfdbb6fd2e87dfa87c049845804abe6f5e012acd383c1d36c683704cc25300000066119ec2dcc439a87f69dbf661df7fa3f2116016012af9be7905a8d54b290d46975f1aa37b3906e129000000b3084f61ee223c8d10f5459f7f93f211601667a0023973ee29c7223c0500006016e129746273f4721e2a902d012a90a5c981f49b2ac1a7084f0100009845780a9d3a48291d28039023237cc9f7cd6b846fb5460f5e3bcdebee53f828e129000000b3084f6161bee8eb79a8f2116016012af9be7905a85572ee29c7253c0500006016e1292cd493e67588dabbf350e523c02c46f802b971ee299f243c0500006016e1292cdc60f4fa5619809c0850816c38f794e3109e020000308bf014966627a5744d19805c18e14bbe6f5e237cab327ac01a8e2eff56098ef15e69363636140200008077084fa1177a751eaa7c049845804abe6f5e016a35524a31b2f7d7c6e85e3e616f6fafd9dada5208000000de213c85de78d2f4e83c54f908308b11be400e9c7bca27094f010000388af0147a65d0380f15c8800015e8b594d2d7a3cb5025f818e12900000047119e422f390f15e83d237cc9f7cd6b846ff19c7bca71084f010000388af0147a2d46f8fe65d9e7a1ca47805974a002bd3439f7d4380f3e2a8253e129000000ef139e42ef8dd7fe266b8000bd234005fa2ac2d38132304b04a7d17d0a0000006f139e42363647afdbca00f49111bee4fbe635c2b7589333103c3c3193f014000080a3084f214b7f6bdbf65fcbf885e523c02c0254f27df30a508b94528a9d6771eea9f11d1c69636323de270a010000c03b84a790ad380ff58bb66d9f2cfa17968f00b318e10bf44d8cee159e72a4b5b5b5667f7f5f210000007887f014b2166b81ff5406a04f04a8406fa494626cefa64a7094084faf5fbfde9c3b774e310000007843780a45d84c297dad0c405f18e14bbe6f5e237c8b327a401a36af47f7c20784a70000001c45780ac5f94bdbb63f2dea17938f00b30850c9f7cd2b402d464a29c674fcda18ddcb1122348df034425400000098129e4291e23cd43fb56dfb6211bf987c0498c5085fa00f9c7bca9184a70000001c45780ac58a35c26f95015836012ab05429a56ba3cb8e4a7014e129000000ef139e42f176266b86004b63842ff9be798df0cddee8416830bafcd2e83ee5087b7b7bcdd6d69642000000f086f014aa11237ce33cd4875dfe22f21160161da8c032fdb3119e7204e129000000ef139e42558cf205964a800a2c454ae9ebd165532578dff6f6b6f01400008077084fa14a9b29a5dbca002c8311bee4fbe635c2375ba3079f084e7f5109de17c169749f020000c094f014aa17a37c7feae227968f00b30850c9f7cd2b40cd524a29c66f44783a500ddeb6b1b111ef0f85000000e00de12930f264f4faa26ddb17f3fe89e523c02c46f8028b76b3119ef29eb5b5b5667f7f5f210000007843780a4c0c1ae7a1020ba603957cdfbc3a50b393521a8e2eff5609deb6babadadcb871a33977ee9c6200000030263c058ef0b7b66dff35cf9f503e02cc224025df37af00352b93d1bdbf8e5e2baac15484a6d7af5f1f77a002000040109e0233c408df18e5fb645e3fa17c0498c5085f605162cc86f09477084f01000078db0f3ffc203c056689b545a37c818510a0029d4b29ed8c2e3b2ac1dbf6f6f684a7000000bc11c1e9f7df7faf10c0c70c534ad79401e89a11bee4fbe635c2370b46f77294dddddd667b7b5b21000000188bf03446f7021c538cf27d78d69f443e02cca20315e89ad1bdbc636b6b4b780a0000c01bc253e0148cf2053a2540053a93523a688ceee52debebebe3d1bd0000001084a7c0296da694be5606a02b46f892ef9bd708df5e1b3dc00c46975f1adda74cc479a7d7af5f6fce9d3ba718000000084f817938d3285ff908308b0e54a02b46f7f24684a62925e12900000063c253604ebe4d29598304e64e800accdde8a1e5dae83254094284a6d179bababaaa18000000084f8179da1cbd6e2a03306f46f892ef9bd708df5e32ba97f7c599a75b5b5b0a01000080f014e8ca5fdab6fd4919fa490e458e74a002f366742f6feceeee0a4f01000018139e021d32ca17982b012a303746f7f2b6084eb7b7b71502000000e129d0b54163942f304746f892ef9bd708df5e99ecf0fab5d17dcac8dada5a73e3c60d8500000040780a2c9251be3d248722473a50817931ba97b1d5d5d5e6faf5eb0a01000080f0145834a37c81b910a00267367a28d9195d76548273e7cec5fb617c050000a06ec2536009068d51bec01c18e14bbe6f5e237c7bc1e85ede7b3f341b1b1b0a0100005039e129b06446f9f6881c8a1ce94005cecae85ec676777785a7000000084f813e30ca173813012a706a46f732b5b5b5d56c6f6f2b04000040e584a7404f0c1aa37c813330c2977cdfbc46f82e95d1bd4cadadad35376edc5008000080ca094f811e32cab707e450e448072a705a46f7d29c3b77aeb97efdba42000000544e780af49451bec0a90850811333ba97a9084f2344050000a05ec253a0c706a3d73565004eca085ff27df31ae1bb1446f732b5b7b7373efb140000807a094f814c7cd1b6ed4365580e391439d2810a9c94d1bd34dbdbdbc253000080ca094f818c7cab04c0490850816333ba97b0bebedeeceeee2a04000040c584a7406636534a5f2b03705c46f892ef9bd708df8532ba97b0babadadcb871c3b9a700000015139e0219fb53dbb64f9461b1e450e448072a705cb71be169d522344d29094f0100002a263c053267942f702c0254e093524ac3d1e54025ea16637bd7d6d614020000a052c253a000c394d23565003ec5085ff27df31ae1bb1093d1bdbf8c5e03d5a8d7f6f6b6734f0100002a263c050af262f4fac228dfc5914391231da8c0a7dc6c84a7555b5f5f179e020000544c780a14261a466e2b03f0313a50c9f7cdab03b57329a5cde675f729958af34ebff9e61be79e020000544a780a14ec6f6ddbfe4b19ba278722473a50818f71a87ae5ae5fbf2e3c050000a894f01428dcb793e3cb003e2040058e347a78f87a74d954897ac5d8deb5b53585000000a890f014a84084a7379501388a11bee4fbe635c2b73329a541f37a74af1d5895dadada6af6f6f614020000a042c253a0327f69dbf62765e88e1c8a1ce940058e12a37b85a7958aaed3e83e050000a03ec253a042b79500789f001578474a69677419aa449de2bcd3fdfd7de79e0200005448780a546a73729c19c01b46f892ef9bd708dfb99b1c9afe6ba3fbb4e6f740b3b1b1a11000000095119e02957b317a7dd1b6ed13a5983f391439d2810abc2dc655084f2bb5bdbd2d3c050000a890f01460bc26faad3200530254602ca5341c5d0e54a24ece3d050000a893f014e08de1e4783300012af086c3d22b15e79dc6e85e000000ea223c05f8c0edc9316740e504a8403339247d5325eab4bfbfdfacaeae2a040000404584a700471a8c5e379501f8cce1bd64fbe6fdec3345988394523c14fcd238fbb44a57af5e6dae5cb9a21000000015119e027cd2176ddb3e5486f9904391231da8408cee159e56687d7d5d780a00005019e129c0b138ee0c2a2740858a4d0e4577307a859c7b0a0000501fe129c0b10d534a07ca00f532c2977cdfbc46f89ec9e430f418dd3b508d2abffecdc6c686420000005442780a70622f46af3fb56dfb4229ce460e458e74a042bdae35c2d32a6d6f6f0b4f0100002a223c0538956840b9a90c50271da8e4fbe6d5817a6a29a5c1e8f2ab4ad4676d6dadb971e386420000005442780a70665fb46dfb50194e4f0e458e74a0429dbe5582fa38f7140000a02ec25380b9b8ad04501f012a5426a5b433ba0c55a23efbfbfbcdeaeaaa420000005440780a3037c394d28132405d8cf025df37af11be2736fa461f73fb7f699c7d5a9d38f774777757210000002a203c0598bb17a3d79fdab67da11427278722473a50a12ed71ae16975e2dcd3ab57af2a040000400584a7009d88c6949bca00f510a04225524a03dfe4eb13e79ec6e8deb80200005036e129746b63634311ea762da5b4a90c5007012ad4e35b25a84f749e46072a00000065139e42b7b6b6b6a241c11149dc5602a88300152a307ab81b8e2e4395a84bec8a8cb34f010000289bf014ba15e1e9dededef8c7b1d6a213b56ac394d2813240f904a85007dda795998eee050000a06cc253e8d6dbe1e954acb9acaeae2a4ebd6ea694569401ca264085c28dbe997f3dba0c54a2baafbb734f0100000a273c856e1d159e86587389b517aa3518bdae2903944d800a051b3dc8c537f32f55a22e314a667d7d5d210000000a263c856ecd0a4fa7d6d6d69c875ab79b93b557a050025428fc1bf9e8659c44453cbc030000944f780addfa54783ae53cd4eadd5602289700150a95521a8e2e072a510fe79e020000944f780add3a6e783ae53cd4aaed4cd66081020950a15c3795a02e57af5e1d77a00200005026e12974eba4e169701e6af574a142a104a850a0d143dbc1e83254897ac499a731360600008032094fa15ba7094fa71ca954b5cdc95a2c5018012a1466f40d3bce3cd57d5a113b1d010000ca263c856e9d253c9d8a8dedb1c19d2add9eacc9020511a04279ae8d5e0365a8479cb511212a000000e5119e42b7e6119e4ec506776b34558af0f49a32405904a85090d143da6074f95225eab1b1b1317e010000501ee129746b9ee1693025ac6a37276bb3402104a850d837eae6f58e272ab0baba3aee3e050000a03cc253e8d6bcc3d3a918e37bf5ea5505aed36d2580720850a11029a5e1e872a012f588877c6361000000ca233c856ec579a55d84a75357ae5c69d6d6d614ba3e3b93355aa000025428c74d25a8eb413f76340200005016e129742b82d3ddddddce7f1de7a156cb1a2d1442800a05183d90ed8c2e4395a8438cee350a060000a03cc253e85684a731ba771162fd6611412dbd334c291d2803e44f800a65305fbfb2877d3b18010000ca223c856e2d323c9d8a5f6f636343f1eb7333a5b4a20c9037012a646ef4cdf8ebd165a0127530ba170000a03cc253e8d632c2d3a9fdfdfd71372a55198c5ed79401f22640858c4d76327da91275585b5b33fa050000a030c253e8d632c3d31053c4e2f74075bed4850a7913a042de6227936fc495881d8b0000009443780add5a76783a15d3c462aa185589355bc7ae41c604a890a994d26074b9a91275b87af5eab8031500008032084fa15b7d094fa762aa98b59dea1c4cd670810c0950215fc2d34ac4c3f5952b5714020000a010c253e856dfc2d329d3c5aaa40b15322540850ca59486a3cb814ad4c1c3350000403984a7d0adbe86a72136c947272a55d999ace5029911a0429e749f56c2e85e00008072084fa15b7d0e4fa7e22cd4381395aa58cb850c09502133931d4b439528dfeaeaaad1bd00000085109e42b772084fdffebd9e3b77ce17ad1ec394d28e32405e04a8909f6f95a09e077f000000f2273c856ee5149e86d8341f53c7a88ab35021330254c8484ae9607419a844f98c730100002883f014ba955b783a156b3f1b1b1bbe80f5184cd676814c0850212fe6e557c02e4400008032084fa15bb986a753fbfbfb46f9d6e5664a694519200f0254c8c4e89bebb546f769350fff1e9e010000f2263c856ee51e9e8658ff8910956a0c46af6bca007910a04206263b93749f56c0e85e000080fc094fa15b2584a75331c6d728dfaa7ca90b15f22040853cc4ce24df580b17bb0e8dee050000c89bf014ba5552783a65946f55628d57172a6440800a3d37d991f4a54a94cfc332000040de84a7d0ad12c3d360946f75a20b75a00cd06f0254e8bf18ddabfbb470c6b5000000e44d780add2a353c9db2365415c7b5410604a8d063939d48463a142e7619eeeeee2a04000040a684a7d0add2c3d329d3c9aa72a00b15fa4d800afd66275205e2dcd3d5d55585000000c890f014ba554b781a8cf2ad8eb55fe831012af4d46407d2814a946d7d7dbdd9dede56080000800c094fa15b3585a75346f95645172af4980015facb0ea40a18dd0b00009027e12974273a316b0c4fa78cf2adcab74a00fd2440851ed27d5a8718ddbbb6b6a61000000099119e42772238bc7efd7ab5e1e9b406b16e44158629a5a13240ff0850a19f6e2b41d9e2cc53a37b010000f2233c85ee4cc3531bce9bf1ba511cfd44154c22841e12a042cf4c761ceda844d9620c8d512c00000079119e427784a71fb27e540d5da8d0430254e81f3b8e0ab7b1b16107210000406684a7d01de1e9d162829951bed5b0260c3d2340851e99ec341aaa44d91f08767777150200002023c253e88ef0f4e36294afda5441172af48c0015fac54ea3c2c5aec1d83d080000401e84a7d01de1e9f1ecefef2b421dac0d438f0850a127749f962f3e0cc4ae41000000f2203c85ee084f8f2f6a64946f1574a1428f0850a13fec302a9cd1bd000000f9109e427784a727179bf24d35ab823562e809012af480eed3f26d6d6d35ebebeb0a0100009001e1297447787afabaededed2944f974a1424f0850a11fec2c2afc0157f7290000401e84a7d01de1e9d9c4e6fc8d8d0d85289fb562e801012a2c99eed3f2c51915f101010000807e139e427784a7f3b1bfbf6f9da97cba50a10704a8b07c7614152c3e14c41915000000f49bf014ba233c9d6f2d63b33ec5b3660c4b26408525d27d5a3ea37b010000fa4f780add119ece5f6cd68f71be144d172a2c99001596cb4ea2826d6d6d7998050000e839e129744778da1d9bf6ab60ed189648800a4ba2fbb4fc0f081e64010000fa4d780add119e76cbb15155d0850a4b244085e5b183a8607116457c50000000a09f84a7d01de1e962c4fad3eaeaaa4294cd1a322c8900159640f769d9ec00040000e837e129744778bad85a9b80563c5da8b0240254588e2f95a05c1e5c010000fa4b780add119e2edec6c646b3bebeae1065d3850a4b204085054b290d46971d95f0d00a0000c062094fa13bc2d3e5d9dbdb5384b2e942852510a0c2e2d9315430dda7000000fd243c85ee084f972bce418df350299a35655830012a2cd0a4fbf44025ca140faaf1c00a000040bf084fa13bc2d37ed8dedeb62e55365da8b0600254582c3b850a150fa8f1a00a000040bf084fa13bc2d37e7d2d4c462bde974a008b23408505d17d5ab62b57ae8c1f54010000e80fe129744778da3f1b1b1bcdfafaba42946b67b2c60c2c8000151647f769a1e2c1746b6b4b210000007a44780add119ef6d7dede9e2294cd1a332cc867bffffebb2a90e79bf7b3cfb2f9bda6945646975f47af155fb9f27cf5d55776f701000b737878f8c9ffcdab57af9a67cf9e1dfbe77cfefcf9f87516f3f8394a178bcce7cf9fefece78f6325e679f6592c8ecf7361dc33338b243c85ee084ffbefdebd7bcdfdfbf715a25c7f6adbf6494ebf61391439faa312c0425c6b84a7458ace530b410050b75981e6e3c78f8ffcf71f0b1a5fbe7c79a2e093bcf8daced7499fc3a701f334189e77e04c7f084fa13bc2d33c5cbd7a757c2f8c4d751429ba50ffae0cd02d1da8e4fbe6cda40355f769d9fef18f7f58740180c244b8f9f3cf3f7f108ceab084f2441070e1c28571181be7c60904f2273c856eef99c253f7437ae3bfdab67d91cb6f560e458e74a042f7749f162a76f3094f01a01c1198c6a8b3478f1e290654223a73e2ef7ebcbefffefb7138b0b9b9d95cba74c9a4990c090ba03bc2d3fcc4d4b478b63501a358b1e6fcb532407774a092ef9b379f0ed4e83e1df88a95f7c1e19b6fbe195f0180bc4570f2c30f3f1ceb6c51a01eb159727b7b7bbc00edb9bfff84a7d01de169decfb9b76fdf56883245f7e99f72e942954391231da8d0a194d241233c2d52749f5a440180bcc538de586c179c02b3ee11f7eedd1b77a65ebe7c791ca6fa0cd04fc253e88ef0346fd311f526ac1429261eea42850efd4109a0533795a03cd39de80040bea2e3f47ffff77f85a7c027c598df08516fdcb8310eeae817e1297447785a86dddd5d1b80caf5a51240778cf025df376fcf47f84eba4fbff5952acfe86b3bdebd0700e427ce806adb76dc5906701ad1cdb3b7b737de58c972094fa13bc2d3b2c4e6c1d80c4491fe3efa7cf35ddf7f93722872a40315bab3af04e5998e3e0100f273fffefdf119e6c253e02ca2733dee25c6212e97f014ba233c2d8f31f4453301113a2240850ea49486a3cb5025ca73e5ca15450080ccc4f8cde83a8db30c01e6795f898e1e164f780add119e96fb758d51be146930998408cc990015ba61fe7c81b6b6b6c61da800403e22e4b875eb964e31a013310e5190b758c253e88ef0b46cb1aee56b5b2c9310a103025498b394d26074d95189f2e83e0580bcc479a7316633ae005d11e8a9359440785a075da8c51a4e26220273244085f93377be40b14b6f75755521002013119a46e7a9f34e814510eca931e44c785a8f98ac66ba5ab14c44843913a0c21ca594564697039528ef83841d7a00908fe9d8deb8022c8a804f6d2147c2d3faeceded29429976269311813911a0c27c5d5382f25cbe7c79fc810200e83fe129b04c11f4ddbf7f5f21e65c53e1297443785aa798b01693d62892c98830470254982fa3120afc30b1bdbdad10009089b66d9d790a2cd5bd7bf79ac3c343859803e1297447785ab798b4a659a04807930989c01c0850614e46df9c0e4617dfa00a73f5ea550f94009009a105d017b1994327fcd9084fa13bc253e23d1013d72892098930270254981f23120a13234d749f02401e2238353613e88b084fefdcb9a310a7243c85ee084f998a352f4d0345322111e644800a7390521a8e2e039528cb952b5714010032104145747b01f4c9a3478fc62f4e46780add119ef2fefb2146f9529c95c9a444e08c04a8301fba4f0b13dda75b5b5b0a01001988d1bd466502ee4ff9139e427784a71c25d6be620d8ce258ab863910a0c219a59406a3cb5025caa2fb1400f210a37b63c11da08f9e3f7f6ebcf831094fa13bc2533e46176a9106938989c0190850e1ecece829ccfafabaee5300c884c576a0efbefffefb7190ca6cc253e88ef0944fd9d8d818af85511c67a1c2190950e10c524a2ba3cb8e4a9445f72900e42116dc8512400e7ef8e10745f8c8bd5c780add109e725cd6c28ab433999c089c920015cee6dae8b5a20ce5881d7776dd01401e0412402e6cf8985d17e129744378ca49580f2b96c98970060254389b7d25288b1d770090076104901b9b3ede75efde3de129744478ca69380bb5483b93098ac0290850e19446df7c0e4697814a94c36e3b00c8872002c8cdc3870f9b57af5e2944f3fafceafbf7ef2b04744078ca69c57b666b6b4b21ca12e1e98132c0e90850e1f4749f1646f72900e441f72990a3084fe3fe55bb084fd501ba213ce5acac8d15e94b2580d311a0c229a494364797a14a9443f72900e4e3e79f7f5604204bb5775d0a4fa13bc253e661757555176a790629a51d65809313a0c2e9d8b953183bec00200fcf9e3d6b0e0f0f1502c85274cfd77a0f139e427784a7cc9335b22259cb8653f8a312c0c94c0ede3e508972e83e057212e3ff9e3e7dfae69f6321f6b7df7e9bcbcf7df1e2c577fef9c2850be3c518e8138bef40eea28bbeb6cf1fc253e88ef094799b76a1ba6f176598521ab46dfb4429e0f804a87072d794a02c76d6017d31ed4879fcf8f1f81a416904a62f5fbe1c77dd2d532cc89c3f7f7efce3e9a26ffcbb58b011b4b248167280dc3d7cf8b0d9ddddade67ba7f014ba233ca52bf17d2abe5fc5e7518a7173f4fabb32c0f10950e1e4f695a01cba4f81459b769046201adda3719d06a57df676803b6bf4e034648dfb6a2ce6c43f0b5799a7478f1e59c4018a78168845e91ace98139e427784a774fdfeba7cf972f3fdf7df2b463976524a5fb56dfb4229e07804a870029303b7072a510edda74097a601e9f4ccc61c82d2b39886ac4705ac11aac6e24e8c838aabcd2b9c4604a80025886913a507a8c253e88ef09445d8dede6e7efcf1471b18cb11c7d2c5daf6774a01c72340859371e07641749f02f3360d4ae335ed30e5b5695dde360d53a34b35ce5fd5adca71de470025880ed4bdbdbd62ff7cc253e88ef09445bed774a11627c6f87ea70c703c9ffdfefbefaa409e6fdecf3e5be8af17076d8f2ebfaa7c39befaea2b012a70266f07a6f1b233f7ecde0e54e31e1d212b4cffbe7df3cd370a011463f419b3d9d8d828eecf253c85ee084f59b4f88c7be3c60d9f75cbf297b66d7f5af42f2a8722473a50e1f8749f1644f72970da0f8f1194c618d1b8ea309dbfe9c8e3e9c26b2c124defd9d331c0d4c9f85ea034f12c515a802a3c85ee084f59d6fb4e176a71628dfb2765804f13a0c2f11d2841399c7d0a9c46dbb646882e5884d6119c4dc3b3fffbbfff53944af9bb07b8aff59bf014ba253c65599c855a9c9d98b4d8b6ed13a5808ffb8312c0a78dbea91c34af0fdaa600ba4f01203f0254a0343171a194c568e129742bce4c169eb22cd32e548a72a004f0690254381ee37b0ba2fb1400f2223c05dcdffa4b780add8af0746b6b4b2158aae8428d209562ec2b017c9a00153e21a5b439ba6caa4419749f026761d7372c477469019428f70055780add129ed217ba508b33984c5c043e42800a9fa6fbb420ba4f81b3387ffebc22c012e84005dcdffa47780add129ed237ba508ba30b153e41800a1f91528a734f7754a20cabababba4f0120433a5001f7b77e119e42b784a7f4912ed4e20c534a036580d904a8f071119eae284319749f02407e5ebd7ad53c7ffe5c218062e5d6852a3c856e094fe9335da8c53179113e42800abe895421ba4f7d0001c89f51aef579faf4a9220045cba90b55780add129ed277ba508b73a004309b00156648296d8e2e9b2a5106dda7009027e37b01f7b97e109e42b784a7e422ba5029c64a4ae94019e0680254984df76921749f0240be8cef05dce7964f780add129e9293e842f57e2dcabe12c0d104a8708494529c7bbaa31265b0330e00f2a50315285d9fc7d3c739d4c253e896f0941c99f45694614a69a00cf021012a1c2dc2d31565c89f5d710090371da8807bdd7244787aebd62de1297448784aae4c7b2b8e498c7004012af8a651b438d83e425400204f0254c0bd6ef1a6e1a92900d01de129b9d3855a940325800f0950e13d29a5cdd1655325f217c1a9f1bd00902fe129508bc78f1ff7e6f7223c85ee094f29812ed4a2aca4940e9401de2540850fe93e2dc4e6e6a6ee5300c8980015a8c5cb972f7bf1fb109e42f784a79444e34251f69500de2540850fed2841198c120180bc0950815af421b0149e42f784a794666d6dad595f5f5788320c534a036580ff10a0c25b26a30a5654227ff1812446890000f9faedb7df1401a8c2b2378c084fa17bd1a9273ca5441a188a623223bc45800aef32aaa0a00f26000000395866802a3c85ee4570babbbbab1014293a5075a116c36446788b00152626230a862a51c6835b8c100100f2767878a80840359611a20a4fa17b119ec6e85e28d9a54b9714a10c83949210152604a8f01f074a5006a34380ae9c3b774e1100804e2c3a40159e42f784a7d4f45e779456314c688409012af8e650947858333604e88aee7658ac972f5f2a02508d083417f96b094fa15bc2536aa3a1a1183b29a5156500012a8c4d46130c54c2c31a00d01f16f601f7bcf9139e42f784a7d4fabe37b5a918074a00025498faab12e42fba4fe3610d80722db23b07004afc3e2a3c856e094fa9d9e5cb9715a10c263542234085663292e04025caf8900240d92cf80250aaa74f9f76faf30b4fa17bc2536ab7bdbdad0bb50c9b29a54d65a0760254689a1d25c85f3c9cc5431a0050060bfc406dba9cb2203c85ee094fe1f5fadce6a6dcad105f2a01b513a0826f06458887333bdc00a01c2f5fbe5404c07d6f0e84a7d03de129fcc7952b5714a10c9a8ea89e0095aaa59406a38b6d511ece00000096aa8b8053780add139ec2bb5657571db355869594d281325033012ab5d37d5a808d8d8df1c319000000af45687ae3c60de12974686d6dadd9dddd550878cfa54b9714a10c7f55026a2640a576461114c0d9a7c0a2acafaf2b022c4897670102947eef8bd0343a4fdd4ba13b119e5ebf7edd714230e3b3b3cfcf45d84929ad2803b512a052add1cd7f38ba0c5422ff0f2c1ec800a03c3aa6801a3d7dfa742ef74fe129744b780a9fa60bb518074a40ad04a8d46c5f09f2a7fb140000e035e129744f780ac713e7a03a72ab08d6d0a99600959a19df9bb9f8b0e2507a60d19cbb0c00f491f014ba273c8593b16e5784cd94d2a63250a33f2a01351addf40f4617f3db3377f9f265450096f13d647c8dc5c9c3c3c3f162655c63e49e05cbf989903a5eb14835bd5eb870c1621500457bfcf8f1a98e28119e42f784a770723139eec71f7ff4fd297fd185fa5019a88d00955afd5509f2e72c05609962e124ba51e375e5ca95f1bf8b0f8511a4c6e2e7f3e7cfc7af085739dadb21e9f9f3e79b8b172f8eaff1cf00c0f1084fa17bc25338fde7e6cdcdcde6c183078a91b798e4f89532501b012ad54929ad34c6f766cf390a405f3f1c46d7c8fb9d23d3603502d5df7efbed4db76ae95dabd33a4c03d2cf3ffffc4d68ea1ecea7c4df17003e4e780add139ec2d9c48663016af60629a59db66dffa514d444804a8d0e94207fba4f819c4c83d55962f1f3e5cb97e3c5cff8719876b0beffe3659a06a1d33f538cd49dfe78da352a1c655e04a8408de279e0b884a7b098e778e1299c4d7c3e8ccfc3a633652f263a0a50a98a00951aed2b41de6291fe34e70201f4f9be361523813fe563816a2ca69e642135c6e61ec5285d0058bce946aae3fcef84a7d02de129cc4f9c852a40cd5e4c74fcbb325013012a5549290d46974d95c8ffa10ba0661febf2b4c10400ca263c85ee4dc3531b0a613e62a3707c863565266b2b29a583b66dbf530a6af10725a032ce3e2de0434c9c7f0a0000501be129744f780addd0105184bf2a013511a0529b2f95206fc2530000a0541f3b0355780add139e4277ace9156127a5b4a20cd442804a354637f718dd3b5089bcd9ad060000946ad619a88f1e3d129e42c784a7d0fddf31216a114c78a41a02546ab2af04799b9e97000094efe9d3a78a0030f2e0c183a66d5be1297448780a8ba131a208d6d8a98600959ad81d9339bbd400a01e820280d7e1e9ddbb7715023a263c85c588bf67ebebeb0a91b7614a69a00cd440804a158cefcd5f749e46072a000040c9a61b4884a7b0187b7b7bc25358a04b972e2942fe342a5105012ab5305a2073467c000000358811e6c253588c084f4dbb82c58abf7331369bac596ba70a02546a61574c010f57000000a5fbf9e79f85a7b000c253581e7ff7b2b7698c2f3510a052bcd1cd3cc25337f4cc1faaec4c0300006a10dda740b784a7b05c26cd1541c312c513a05283bf2a41de9c8d00007579f6ec992200009d109ec2f2adaeae36ebebeb0a9137637c299e00951ad80de3810a00c8c8cb972f150100983be129f4872ed4ec19e34bf104a8146d32be7745253c4c01000000f58af505e129f4c7c6c6c6b87182ac695ca26802544a677c6fe67cb801000000ce22d61676777715027af87793ac19e34bd104a894ce2e98cc1fa2ce9d3ba71000509957af5e2902003017b1b610a37b81feb974e99222e4cd185f8a2640a558c6f77a880200f2f4ecd933450000ce4c780afd16237c63942f59d3c044b104a894ccf8decc1fa0d6d7d715020000003831e129e441809a3d637c2996009592d9fd92b1eded6d45000000004ee5f9f3e7cd83070f1c0d003de708afec19e34bb1fea80494c8f8de321e9e00803a3d7dfa5411008033393c3c1cbfeedebddbacadad8dd71962d255fc18e897f8fb79fffe7d85c857acc5ff3f65a03402544a657c6fe60f4d769e0140bd748a0000f314e7abdfbb776ffce3e99141313634aed61f60f962129d00356b31c657804a713efbfdf7df55813cdfbc9f7d36f3bfa5947e1d5d06aa94a7d1d7cff907c052c40ef54f89c59765863b2f5fbe1cff1efa2a76f49f3f7fde9b2973f37c9ff5fd3d0b00d42d9e5f3737379b8b172f8e035560396edfbe7daccfe4f4d69fdab67d32eb3fcaa1c8910e548a9352da6c84a7d98a9da0c253a02b710e52bc1e3f7efce6c7d317f3e1032f000039898d5ed3cd5ed18d1a21eab44335d62880c5b874e992cf937933c697e2085029d1be12e4cbd9a7c03cc542c8f4eca378190b0a0000cc129f171e3d7a347ec5c8dfe926ef69a00a74273ac1e3ef9dcfedd932c697e218e14bbe6fde19237c8defcddb3ffef10f3b3c8133992e783c7cf8d0072f0000606e22448d57043dce4e85f9bb7bf76ef3e0c10385c8d7cc31be722872a40395a218df9bb7d8d1293c054e43680a00002cea7347843c71766a4cd132ea17e627c6f80a50b3668c2f4511a0521ae37b337f480238ae084ae383d5fdfbf79d610a00002c541c1712e346e335ed4c752c119ccdb4b9c267fc6cfdb511a05210237cc9f7cd7bc408df94d22fa3cba6eae42746dfdcba754b21804f8ae03442d31f7ffc51b7290000d01bb1b611e37daf5cb9a22b154e293eefc7c604b2f55f6ddbbe78ff5fcaa1c8910e548a91521a34c2d36cc5070c808f119c0200007dffcc125372e2159d7411a4c61538bee8e616a0662dc6f87ea70c9440804a69376732b5bdbdad08c04c71ce507c8032c6070000c8c1e1e1e1f82548859389eeed0851631d802cc518dfef94811208502989f34f337e305a5b5b5308e003b183fbce9d3b3e380100005912a4c2c90950b3a6c98962fc410928414a69a531be375bba4f81a3c422c38d1b377c680200008af87c73fbf6ede6eeddbb8e24814f88a3bee24c61f2945212a25204012aa57053ce58ec2a0378db0f3ffc305e5cb0b00000009424ce478d8da2f7efdf570c9821c2d30851c9d65f9580120850715366a962744d8cf005988a1dd9df7fffbd42000000458a8da2f7eedd6bdab6b5691466d0709135cd4e1441808a9b324b75e9d2254500de88f0347664030000942e8e2bf9e69b6f9a67cf9e2906bc2702544d17d95a49296921267b0254b267a67abe8ce300de263c0500006af3fcf9f3e6d6ad5b3e0bc11174a1666d5f09c89d00951218df9b2907c20353c2530000a05631c6d76722f8d0d6d69622e44bd313d913a05282a112e4c92e3220dcbf7fdf42010000503d212abc6b6d6d6dfc224b8394d24019c8990095ac4d66a9bb1167283a4f05a8409cf573efde3d850000006884a8f03e5da859d3854ad604a8b809e3e107588a1853d5b6ad42000000bc2542d4478f1e29043426d865ced17b644d808a9b304b2140056274eff3e7cf15020000e03d77eedc194fec81daadaeae36ebebeb0a91a7614a694519c89500956c4d66a86faa449e0f3ece2f80ba4570fafdf7df2b040000c01162624f84a87185da5dba744911f2658224d912a092b3a112e449f729f0c30f3f28020000c0474407aa8da7d0349b9b7a6832f667252057025472667c6fa6ec1a83ba45f7e983070f14020000e0137c7682a63977ee9cb350f3a503956c0950c9d95009f213a37b63842f50af38fb140000804f8b11bece428546809aaf9594921662b22440e5d43efbecb3a5bdfee77ffe2776ae38803a43c6f70276500300001cdfe1e1a122503d637cb3b6b3cc2c014e4b804aaecc4ecf94dd6250b7478f1e8d7750030000703c025430c637738ee2234b02547265767a868cef052240050000e0f88cf085d704a8d9da4c290d9481dc0850c9cee466eb869b21e37b013ba70100004ee6f9f3e7e317d42ec6f846272a591a2a01b911a09223dda79912a0820ffd3ef40300009c9c2e54783dc6d759a8d932c697ec0850c991f34f33142336ec1083ba094f0100004e47800aaf19e39bada112901b012a39d281eae106c8d0e3c78f15010000e0141c8702af69d2c8d64a4a49fb305911a09295d14d76a80a79325e0378f9f2a5220000009c82893ef01fd619b3a5318aac0850c98d59e919b2330c08464e0100009c8e0015fec3a4bb6c59db272b0254723354020f35800ffc000000b531c6175e5b5f5f57843c6da6945694815c0850c9c6e8e63a889bac4a64f89dd1580da011a0020000f84c05671793ee346c64cb185fb2214025274325c88ff1bd4030be170000e06c7efbed37458009016ab6feac04e442808a9b2b1e6680cebd7cf95211000000cee0e9d3a78a001326de656ba804e442804a4eb4f77b98013265d4140000c0d9bc7af54a1160c218df6c0d524a168cc98200952c4c6eaa0e98ceccdada9af1bdc098515300000067a30315de2540cdd65009c881001537553ab3b5b5a508c09811be00000067a30315de65f25db61cd5471604a8e4e2af4a901fbbc080a967cf9e2902000080cf56303731f92e26e0919da1129003012a6eaa74221e5e5657571502000000604e4cf78177998097a59594d25019e83b012abde766eae105f0211f000000e07d26e0656ba804f49d001537533cbc009d33660a0000e0ec1e3f7eac08f0969880678c6f969c834aef0950c981f34f337c7031be1700000000e8dafafaba22e467a804f49d00955e4b29ad8c2e9b2a9117dda700000000c022384a2c4f29a51d55a0cf04a8f4dd50093cb400793b3c3c54040000009fafa01331c2d734bc2c19e34baf0950711365ae9c3b00000000002c9231be591a2a017d2640c54d140f2b0000000040b61c2996a5cdc9117ed04b02547acbf9a71e5600000000003ec59a64b6864a405f095071f3c4c30ad0a957af5e29020000c01c3c7dfa54116006eb925972841fbd2540c5cd130f2940a79e3d7ba6080000007360832acc666d324b4325a0af04a8b879e2210500000000c8dafafaba22e4c739a8f49600955e72fe69a6dfed367dc90000000080c55b5d5d6dd6d6d614223f4325a08f04a8b8693217f17072eedc398500000000009642176a961ce5472f095071d3642e749f0200000000cbb4b5b5a508f9192a017d2440c54d93b970fe2900000000b04ca6e465c939a8f4920095de71fe697e9c2f0000000000f4814979591a2a017d2340c5cd923373b60000000000d007172f5e5484fc38d28fde11a0e266c99919df0b00000000f4810ed42c0d9580be11a0e266c999e9400500000000fa20ce4075dc5876a4def48e0015374bce24c25307b303000000007da1e1233f29a5a12ad0270254dc243913e37b01000000803eb16699a5a112d0270254dc243913bbb900000000803e31352f4b7f5602fa44808a9b24a7b6babaea3c0100000000a077347e6467a804f489001537493c84000000000045b176999f94d2a62ad0170254dc1c3935670900000000007d64ed324b4325a02f04a8b839726a767101000000007d14c78fc58bac38e28fde10a0e2e6c8a9c4d9a70e620700000000fa4a0348764ca9a43704a8b83972ba2fd6a62f1700000000d05fc6f8666790521a28037d2040a51726374537c68c5cbc7851110000000016c8385238191da8591a2a017d2040c54d91138bd1bd1e3e00000000164b800a2713eb9871141959d1364c2f08507153e4c484a7c0493fac000000002c83b5ccec0c95803e10a0e2a688870ea053767a02000000cb622d333b9b4a401ffc51097053c4430700003989cd39e7cf9fffe0c7478d688bff368fcd3ccf9e3d6b5ebe7cf9cebf7bfefc79f3db6fbfbdf9e7a74f9f36af5ebd7af3dfe20500c07259cbcc4f4a69d8b6ed4f2ac1320950e9c5cd5015f211676de8260300a0cbe7cdb75f9f7ffef9f83aaf20f4b4cef26b1f1e1e8eafd3c03582d80864dffe6f0000742336d94588eab92b2bc3d1eb2765609904a8f4e5664826ecd80200605ecf95d390f4e2c58b4b0f4897fd0c1d0b7ad1bd1ac1eab47bf5ed8e560008f17d1338ddf39800352b1b4ac0b209507133e44462710b00008e6b1a92c6a25504a4f1b2f8fba169c8bab1f1eec7a3085023489d76afc6c29f6015a0eeefabc0c959d3cc8e23ff583a012a6e869c880e5400003ef5bc282c9d9fe9c8b9a92b57ae8cafd360353a56e335fd310000473fa39295414a69d0b6ed13a5605904a82c55dc04e366a8127998760f009cc4850b171401a050d3706f1a985a985a4eeddf161daa425500800f19e39b9d68bc7aa20c2c8b00953edc04c9e82103e0a462811780b29e09e315a3664b3db3b484afcfdb62a1f0f1e3c7e340757ad62a00408dcf4902d4ac4476f02f65605904a8f4e12648269c150000509fe9f9a51198c6d5c698fcbc1faac679aad35035aef1cf00e4c1e625383d6b9bd9f9b312b04c0254dc0439361da800007588c5d9cdcd4d5da6858a507c6b6b6bfc0ad340f5d1a347e31700fd6523139c9eb5cdec0c95806512a0b26c3a5033e1fc530080b245501a815a84a69efbea7bd68faffdfdfbf7150300289a31be7949296db66dfb50255806012a4bbdf98d2e2b2a91cfc305c069c5a2fcb367cf1402a067a6c1d9a54b9784a695bb7bf7aeefd50040f104a8d9890c4180ca52085059f6cd8f4c382300388bf3e7cf2b02404fc4e8bf18cfbbbdbd6d3c2f630f1e3c18bf00e83f1b9ee06c3cff666743095816012a6e7e1c8b0e540080fc9fe7a2d3747aee2584e83a8dee5300f2204085b33f1393154d582c8d0015373f8ef570ee011d0020cfe7b8e83475ae294779f5ea5573ebd62d850000aa11d3581c339495a112b02c0254dcfcf8243bb380797c40016071f75c237a398eb66dc7212a00404d62ad53809a8f94d2e6e8b9d539a82c9c0095a5ddf454211fce3f05ceeac2850bcda3478f1402a043119646681ae1a98d2b7ccabd7bf79ac3c3438500c888efef301f11a0debf7f5f21f211598200958513a0b2cc9b1e193d540000d03fba4d398dd8d464d110203fb13115383bcfcdd9d950029641808a9b1e1f158b72cecb0200e897e9d9a65b5b5bba513891185777e7ce1d850000aa7e968ed7f3e7cf15230f9ab1580a012a6e7a7c94ee5300807e3d9b4570bab1613f222717e79d4678eadc5300c073f57af3e0c10385c8c350095806012a6e7a7cf26102e0ac8cc701389be834bd72e58ac9209c499c7b1a1da80000b5b34e919794d266dbb6ce4165a104a82ce566a70af910a002f360bc24c0e9ee9d972f5f1e779cba8f7256d161a1cb02206fd668c0dfa78a45a6204065a104a82ceb664726ecc6020058ace8328d6ed3e83a857988aed3bb77ef2a0400c08435cfec38c3848513a0e266c74c766201002cf6d92b8253cf60cc539c77dab6ad4200001cf1fc7d7878a81079d094c5c2095071b3e3a30f1100f3e0cc3e80d99c6f4a97223c7dfefcb9420000bc47809a95a112b06802549641809a09a32c8079110a00bccbf9a62cc2bd7bf72c0a0214c43a0df83b55b394d2a06ddb272ac1a2085059f84d6e745951893ce840050098af58a489d0d4f9a674edc18307cdfdfbf71502a020365dc17c59fbcc4e34663d5106164580ca326e72642016f73c980300cc4704a6119cdae5ce223c7bf66cdc7d0a00c06cb1f61913b31c77908dc816fea50c2c8a009565dce4c8c0850b17140100e00c623166da6d6a631a8bf2ead5abf1b9a7710500e0e3a20b35267790853f2b018b244065d13694200fba23802e3e9438870da84104a6972e5d32128ca588f054170500c0f15803cdca40095824012a8ba603351316fc00004ef6ec14a1e9e6e6a66e5396e6eeddbb362b0114febc01cc9700352b8394d24adbb62f94824510a0b2f09b9c127878000028e57929ba4d373636c6e37a619962f49cf17300002763634276a241eb2765601104a82c4c4a69a80a1e1c0000727f4e8ac054684a9f3c7af468dc7d0a00c0c9c5c6c867cf9e29441e04a82c8c009545dfdcc8800015e8829196408e22249d86a671752fa36f62b1efce9d3b0a0100704af19c2f40cdc67f2b018b2240c5cd8d0f18df0b74e1c2850be30e1980be3f07c5fdeae2c58be385145da6f4d9ab57af9a5bb76e8daf00009cfe3300d9d0a4c5c208507173c3430300509de9c48db87efef9e76f3a4d2117c2530080f9880d946463a8042c8a00954512a06620c6d2e9b400ba10dd5c57af5eade2cf1a1b516a1cf3f9f8f1636f747afd772b9e713ce7508269786ad41ce4f559fba805fa8f7d6f8affcf493638f77923d0e1e1616f9e37e7f57b5934cf30d0ede70cf291521ab46dfb4425e89a009585ddd446971595e83f3bae80aec4828eeeaef2bfc60074efdebd7bc25358b0585c3f7ffefcf8c7ef87a1d3490653f1bfb318dfcd73e23c7e9e2b57aef8820047de5f72dd6051a1c1e8f54419e89a009545ded4a8e8430d0000307f77efde6d1e3c78a010d0b1adadad71d0a6eb0fa00eb1f145809a8de1e8f59332d035012a8bbca9918118b1090000f48ff014ba179b8af7f6f604a700953139202bffad042c8200153735de61842f0000f48ff014ba15816904a7a63201d4c99a6856064ac02208507153e39d0f8c71960b0000d01fc253e8d6f6f67673f5ea559f87012aa603352b43256011fea004b8a9e141010000fa49780add894dc45f7df555b3bbbb2b3c05c014828ca494365581aee940651137b3812ae4c1a80a0000e88757af5e356ddb368787878a011dd8dada129c02f00ee75f67654509e89a0095451828411e2e5ebca808c0ff67effe61e24ab2be01b7569b34c93b3269a37142a7100ed13762429c92da29e9389d78533bbda99d92bc0184464c84434821e9119d6279133adccfd5b3ec78deb5c740df3f75aa9e476acd4abbd2ce3d8deaf6bdbf3aa7008081a5f0f4d5ab57a3f97cae18d0b21498a6e03405a800f039d3f942f9f1d3e75419e8920095be163302b0cb0a0000869542d3d4797a7373a318d0c133efc1c18117e4007c91fb4328df2b015d13a062316329edc215a00200c0702e2e2e466fdebc5976a002ed4a2fc55fbe7c69642f005fe50cd4509e2a015d13a0623163c9f9a70000309ce3e3e3d1d1d191424007d2b8dee7cf9f2b0400df9436dc384621841f9580aefd4d09b09891d861050000fd4bdda6af5fbf169e42479e3d7b263c05e0de4ce88be3e0e0e0a92ad0251da874bd887da70a3198f10f0000fd32b217ba9582d3d47d0a00f795a6f4a5df6884f0f4d367a60c7445804ad7b6952006bbab0000a01f29304d1da72727278a011d119e02f0189b9b9b8a1047ca1e4e9581ae0850e9631123001da80000d0bdd4d170787838bab9b9510ce888f01480c7d26412caf74a409704a858c470fe290000742c05a66fdfbe1d5d5e5e2a067448780ac02a04a8a168dea25302542c62f8610000001d31ae17fa233c05a00da9d9c4a6b7109e2a015d12a06211c3f85e000068517ae1369fcf979ff3f3f365880a744b780a405bd2bb52016a084f95802e0950b18821400500804748c1e8f5f5f57fc2d2bbff0cf44b780a409b4ceb8be3e0e0e0c7a6694e55822e0850e972f132be37888d8d0d45000080aff83c284d6799de85a53a4b61782938159e02d026cd26a17ca7047445804a979e2a41fec6e3f1f2030000354bc1e85d387a1798a67f1adf06f94ac169ea3e058036693609253571fdaf32d005012a5d2f5ef841000000834bc1e8ededed7f02d2bbc0f4ee03c492ba8384a70074e1aee1c4b49110be5702ba2240c5e2e5a15311000008edae6334b9baba5afef3ae73d4a85d28f339f6e5cb970a01406752d3894924213c5502ba2240c5e2e5c153110000c8de5d07e9e701e95d4729508fd411f4e2c50b47d100d0a9f4ce54801a8229987446808ac5ab72ebebeb8a00004016eec6e9a690f42e1c356217f85cea3cb5111880ae79671ac6774a405704a858bc2a379d4e150100805edd8ddc4d01e95d486a873ff02de9cc53e129007d70bf89e3e0e0e0c7a6694e5582b60950e96cd15285fc19790400401f52389aba4aef42531da5c043edecec2c3f00d08774062a6168e4a21302542c5a7e080000406b5277690a4cef3ece28055695ba8052f72900f425359ea44ffa6d4bf6d25182ffab0cb44d804a978b16011e42010060552928bdb8b8109802ad4b2fafd3b9a700d0b7d47ce2988910be5702ba2040c5a2553187a10300f0186927fef9f9f97f42533bf381aea4f0d4f133000cc1bbd3309e2a015d10a062d1aa980e540000eeebf3d0347d00ba96c6f67a6e05602802d4309e2a015d10a062d1aa9833500100f896b3b333a129d0bb9d9d9de5070086b2b9b9a908313c5502ba2040c5a2553163900000f892748ee9c9c9c9b2e3d4785ea06fa9eb747f7f5f210018d4dada9a2204717070b0dd34cdb94ad026012a9d2c56aa90bfe974aa080000fc49ea367dfffefdf25c538021a48dbe07070736fc02303863e443f94e09689b00158b55a5ccf0070020b9b9b95986a6efdebdd36d0a0ceec58b179e5701c8460a51d37416b2979aba4e9581360950e96ab122731e480100ea9682d3e3e3e365d729400e9e3d7b36dadada520800b2618c6f189aba689d00158b55a58ca00000a8531acf9b8253637a819ca46366f6f6f6140280ecee4f7e3787600716ad13a062b1aa94f3640000ea223805727e3e4de79e02408ef72842d0d445eb04a858ac2a95764f0100503ec12990bb972f5f7a410d40964cf10bc3b182b44e808ac5aa421e4c0100ca27380522d8dfdff7721a806cadafaf2b420c9aba689d00158b5585363636140100a050f3f97c747878283805b2b7b5b535dadddd550800b225408de3e0e060bb699a7395a02d02545a5fa454c18d1f0080fedddcdc2c3b4ecfcece1403c85eea3a7df1e285420010e29e953629923d8d5db44a808a45aa4202540080722c168bd1c9c9c9e8ddbb77cbff0c90bb74ac4c0a4f1d2f0340046b6b6b8a10436aee3a5506da2240a58b458acc395f0600a00ca9db34759da6ee5380285278eab9148028a6d3a9e33162d0dc45ab04a858a42a64972f00406cce3905227bf3e6cd686363e39bffbb343de92113949e3c79d2cac4a5f4a29c58f7c4dbdbdb30ffbea993cd0602804e7caf04b449808a45aa42f779500568537ac1fffaf56b8580cf3cf4a5705bd20bbb364750fddf97d5435d572dd288dea3a3a3e5c85e80c86b990d20f07029dcfff9e79f15027ab6b9b9a908313c5502da2440c52255211da80030bc34727588b1ab5dbeb04ebf317ef9e5175f6e472e2e2e965da7c6f5020000fc97a74a409b04a8b4cd08dfcc1945040074657f7f5ff7690752a7561a779902540000a05fdea786f1540968d3df9480966d2b0100407db6b6b6463b3b3b0ad1b2149aa6ae5ee12900000cc744bf180e0e0e3478d11a1da8589c2a63c71400d0b6f432e1c58b170ad1225da70000908f8d8d0de777c7901abc4e9581360850697b710200a032293cb523bb3d29344de1690a51010080e179de81fa18e10b95d9dcdc540400a03569746ffab0ba14981e1e1e8e9aa6119e0200404652072a21fca804b445072a6dd2810a005011a37bdb339fcf975da7e99f0000407ecf3e405d04a8b4c919a80138031500688bd1bded38393959769e020000799a4c268a10c3f74a405b04a8589c00007830a37b5797c6f4a6aed374e6290000002b7baa04b445808ac5a922ba4f01803618ddbb3a237b0100200eef55a13e02540000e0418cee5dcdd9d9d972646fea40050000a0353f2a016df99b126071aa8759fdc05074584139d2ce6ba37b1f2f05a76fdfbe159e020040c06721a01e0254a8c8dada9a2200831014401952d7e9f3e7cf15e291ebe0ebd7af472727278a010000d091838383a7aa401b04a858942af2e4c9134500001eeda79f7e1aadafaf2bc403a52efc7ffce31fa3cbcb4bc5000080a04cf70be3a912d00667a06251aa88179e00c063a597057b7b7b0af14029346d9a46273e00000467ba1fd445800a6ef20000dfb4bfbfaf080f747676b63cef14000088cf74bf307efcf4395506562540a5cd4589cc193301003cc6cecece683a9d2ac4031c1e1e3aef1400000a62ba1fd445800a00007cd5783cd67dfa40a9eb34759f020000d0bbff5102da2040c5a25409dda700c0633c7bf66c19a2f26de99cd374de693af7140000288ba93c616c2b016d10a06251aa84f34f0180874a1bb076777715e21e5278faead5abd17c3e570c00000008ee6f4a0075d03902003c94d1bdf7233c0500fa707b7bab083030e7a086f05409688300158b5225363636140100b8b79d9d1d23aaee2185a6c25300a0afdf1dc0b004a8213c5502da60842f16250000fe244daed8dbdb53886fb80b4f53072a000000500e1da8508974861900c07dfcf4d34f76567f83f0140000ea634a4f0c070707dbaac0aa04a8588c2ae10c5400e03e5270babbbbab107f41780a000090b5ef9480550950b1180100f01f6974af8d575f273c0500807a3d79f24411a0120254a884f112c0902e2f2f1501024823ff77767614e22b84a700005037479d84616a262b13a06231020060697f7f5f11be2285a64dd3084f010000f2676a262b13a0623102006039adc2c48a2f4ba169ea3cbdb9b9510ca048a99b266da2f9f9e79f150300fec2c6c686224025feae04503e2f4301806fd17dfa6577e1691adf0b509a34b6fd871f7ef8d333e3b367cf464747478a03005f301e8f152186ef9580550950b1180100542ebd404fe79ff2df84a74069d28bdfedededd1dedede17cf71dbdddd1dbd7bf7cec8720020b2a74ac0aa04a8588c00002a975ea2f3dfdebe7d2b3c058a92ba4b5340fa57dd33e9bffbe9a79f74a102c057a4cda79e13a07c0254a88011be00c0d7a4eed32f7520d52e85a76767670a0114b3d67fade3f44b74a102c0d7adadad2942febe5302562540c56204005031dda7ff2d05a7c253a00469336d5ae71fbaa956172a0010dcb612b0aabf2901162300803aa50e23dda77f767171b1ec3e05882c05a0fbfbfba39f7ffef9d11389be35ea17006a9546f802e5d3810a6eea004085d24bf174161e7f48e718bd79f3462180d0b6b6b696e1e9aa1b6474a102c09719e10b75d0810a15b06b1800f8bfd24b71bf11fe90cef96b9ac6797f40e8e7be149c1e1c1cb4365d40172a0010d5a7df442667b212012a16210080caa497e1e9a5387f78f5ead5e8e6e646218090d2d4a15f7ef9a5f5b5fdae0b1500f8c393274f142186ef9480550850b10855c0580900e0733b3b3b3a8a3e93ce3c4de37b01224aa1690a4fbb3ad35a172a00fc5957f75c202f0254a88033500180cfe93efdc3d9d9d9f203104d0a35d3b8de34b6b7ebff1f5da80000d446800a00001549dda7764cff2e759da6ee538068d226d9972f5f8eb6b6b67af9ffd3850a0004e4f84156224065553f2a0100401c7b7b7b8af0c962b118354da3104038d3e974199ef6396948172a00fcf95e4c088e1f64250254289c0e1300e08eeed33fbc79f366747373a31040b875fce79f7f1ea41bf4871f7ef0050000500d012a14ce4b5200e08eeed3df1d1f1f8f2e2e2e140208259d75fafcf9f3419f2d53800b00003510a002004005d298291bab7e3ff7f4e8e8c81f04104a0a4ed339a443b31107007ee7d92a842d256015025456f5ff9400806f31261386e7a5b7734f8178d2a8de149ee6d2f9995e163bf70d0004a841380395950850a182076e80a10950615893c9c40bef91734f8178cf722f5fbecc6e6cae0d390000d440800a85dbd8d8500400a85c0e631f87767676e6dc53208cbbf0346d80c98d91f00000d440800a0000054b2fb973eb5eea5bea3a3d3c3cf4c700849073787a47172a00eed7a6fe0560842f2b11a0b2aaa74a000090afdac3d3e4eddbb7cbf34f017217213c4db6b7b7bd388681cce77345800c98fa17c2b612b00a012aab7aaa040000794a2fb76b1fdf7b727232babcbcf4c7008458b32384a777ffae36e8c0306e6f6f1501007a2040850a1ec201803ad5de219446f71e1d1df94300423cb745094fef385f1b0080920950a170911ec0018076d5fe72dbe85e208288e16992ced89e4ea7be4000008a244005008002a597da356fa432ba1788206a787ae7871f7ef0250250254d2b311c1c1c7ca70a3c96009555161f8730030064aae697daa9ebd4e85e2082fdfdfdd02f60d339a88e8d01a046ee7f61c8307834012aabb07b030020d387f9f452bb566fdebc31ba17c8def3e7cf8b58ab6bbedf0000502e012a000014a6e697d9696cefc5c5853f02206ba584a749ede76d03005026012a146e6d6d4d1100a03235bfcc7efbf6ad3f00206bcf9e3d2b6aa3cbfafafaf2dc6d0000288900150ae7407300a84b7a899d5e66d7e8f8f878747373e38f00c8560a4ef7f6f68abbae9acfdd06a04e1b1b1b8a008513a0020040416a7d899d82d377efdef90300b295c2d334bab744dbdbdbcbf3b701a016ee7b613c55021e4b80ca2a7e54020080bc1ee26b3dfff4f0f070b4582cfc1100594a93814a0d4fefee3f29440500c8cc5325e0b104a8000050885ac3d3cbcbcbd1c5c5853f00205b359c116a8c2f00002511a0020040216aee3e05c8590d9b3c6a3e831b0080f208500100a000693c64fad4e6ecec6c349fcffd0100594be734a76ef9d26d6d6df9b201002882001500000a506bf7e9f1f1b12f1f08e1fdfbf7ee45005090740638502e012a000014a0c6ae9f149ea6ae2e8008cecfcf8bbfc63409c1185f006ab1b1b1a1085030012aabf81f25c8ffe11500285f0a4f6b7b61bd582c46efdebdf3e503a1d6ad1ace42ddddddf5650300b9f85e09782c012aabd85682bcadadad29020054a0c6eed393939365180110493ab7d93d0900a0374f9580c712a002004070dbdb75ed6bd37d0a44953a504bdffc91262298860400407402540000082c75fa8cc7e3aaae59f72910590d67a1eeececf8a20100084d800a000081d5362a51f72910ddfbf7efdd9b0000207302540000082a759ed6d6e5a3fb1488eef2f27274737353f435a631bee903000051095001002028679f02c494ce422d9d2e5400002213a0020040509b9b9b555dafee53a014676767c55fa373500128dd6432510428980015000082aaad03b586c001a8c37c3e5f7e4a965e2a1be30b40c9d6d6d614010a264005008080d268c474066a2d52785afa9981405d6ad814329d4e7dd1000084244065153f2a0100c0306a3b5beef8f8d8970e14c539a800009ddb56021e4b800a0000119f022b1adf7b7979a9fb14284e5ad74a1fe3ab03150018d8774ac06309500100209874ae5c4de37b759f02a52a7d8c6fba57e94205002022012a000004b3b3b353cdb5a60eadd4810a50a21ac6f8ea4205002022012a00000453d3cbe89393135f3850acb449a4f41055800a0040440254000008647d7d7d39c2b716a58fb704283d404df7ac74ef0200804804a80040a78cde8476d5d4c993c2d3c562e14b078a767e7eeede0500009911a0020040205b5b5bd55cebfbf7ef7de140f1d24691d2bb503737377dd10000842240050080406ae9e249e702ea60076a517a80babdbded4b06002014012a00000491c2d3f1785cc5b59e9c9cf8c2816a943ec637ddbb6a3abf1b0080f804a8000010444d67c895de8d05f0b91ac6f83a0715da5b2f0080ee0950010020885ace3f4d21421ae10b5013012a701ff3f95c1100a0070254000008a0a6f187ba4f811a953ec657800a0040240254000008a09617cf692c5de92102c0d7d6bfcbcbcb62af2f6d045a5f5ff745030010820015000002a825404de1a9b3bd805a19e30b00007910a002004000b5bc7436be17a859e96be0e6e6a62f1900801004a85030bb7b01a00cb59c7f9a3a4f05a840cd6e6e6e46f3f9bcd8ebdbd8d8f02503001082001500003257cb0b67679f0294dd859a3603a54d410000903b012a000064ae96a912575757be6ca07aa56f26d1850a00400402540000c85c2d67c6e94005182d47f8a651bea572d40c00001108500100207335bc6c4e232bd319a8008c46979797c55e5b2d9b820000884d800a0000194be7c5d5a0e4b000e0a14a3e07d5085f00002210a0020040c66a197558725800604dfcc3783c1eadafaffb920100c89a00150000325643076a3aebafe4f3fe001ea3e410b596e90a0000c425400500808cd530ead0f85e80bad646637c0100c89d00150000325643978ef1bd0075ad8d9b9b9bbe600000b226400500804cd572fea90e5480ff56f2787367a00200903b012a14fec00d00c45543f7e97c3e1f2d160b5f36c01794bac12405a8e3f1d8170c4068debd42d904a8e0260e0064aa860055f729c0d7953cc6d739a80044e7dd2b944d800a000099aa61c4a10015a0ce35b2864d420000c425400500804cd57006aa0015e0ebd288f334eabc44ce410500206702540000c8500d9d3969e495f34f01fe5aa91b4d74a002003df8a8043c960015000032647c2f0025af95ce4005007a70ae043c96001500003254c38be552c75202b4a9d400753c1efb720100c896009555d8bd0100d0911a461b0a5001beade473506b38eb1b00809804a8acc2fc7000808e18e10b40e9eba52e5400007225400500800c95de81aafb14c09ae91c540000722540050080ccd4d07d7a7d7ded8b06b8a7523b509f3c79e2cb0500204b02540000c84c0d01ea870f1f7cd100f7747373b33c0bd5fd0e0000fa2140050080cc943ebe3771fe298075d3085f0022bbbdbd550428980015dcc40180ccacadad157f8da99b0a80fb2bf11cd4f178ec8b05c0bd19c8920015dcc40180cc6c6e6e167f8d0254808729b573bf86a90b00c060664ac06309500100805e19df0bf070d7d7d7455e570d53170080c1fca6043c96009555cc940000a07dd3e9b4e8ebd37d0af0708bc5a2c8f5737d7ddd970b00407604a8acc2ee0d00001eecc3870f8a00f00802540000e8870015000032527af769a20315e071aeaeae8abba6f178ec8b0500203b02540000a057025480c729f11cd4c964e28b0500203b02540000c8480d2f9205a800d64f0000c89900150a777979a9080010c8dada5af1d7280000789cf97c5edc353903158088bc730d63a6043c96001500003252fa59708bc5c2970cb082d236a1085001800ecd9480c712a0b28a732500006857e9237c4b3cbf0fa04fbaf80100a07b025456f15109000000a03f575757c55d530de77f0300108b001500003252fa28c312cfef036035359cff0d00402c0254289cf14e00104be901aa33500156737979a9080000d031012a14eec3870f8a00000040b6c6e3b12200104a8923f50b75ae043c96001500e8d4743a5504b8a71a5e209b8e01b09a123b503736367cb10040eb9aa6f9a80a3c96009555169f53550000684f0d2f9005a800000040ee04a80000000081cce773450000800e0950a170d7d7d78a0000005090dbdb5b450000800e0950a1708bc542110000003ce7656b3299f8520108a5c433c90b74ae04ac4280caaa1cc20c00d092e9745afc353a03156075a58df01d8fc7be5400a06db20b562240655576710000706f025400000020770254289c9794000000000000f7274085c20950010000ca727d7dad08003020ef5c4330c297950850010080deacafaf2b02c08a168b852200c08004a8215c2801ab10a0b22a67a00200706f02540000002077025456f54f25c8df7c3e57040000000000807b10a042056e6f6f15010002188fc78a0000009031cd2a613803959508500100201393c9441100000032a659250cc70fb212012a16a10a2c160b45000000204bcec706002037025456a50d3e006325000000c89500150080dc085001000000188c4dbf0044727575a50831cc948055085001000000188cb3e40080b6354d33530556214065d545e85415f2777d7dad080080df2500000000f72040850a2c160b450080006e6e6efc2e010000c8984da15007012a000064e2c3870f455f5f0d0131000050369b4243385702562540a50d3325c89b5d5100400e04a8000000f4e0a312b02a012a6d982941deec8a020072707575a508000040683686421d04a80000402f4cc50068c7fafaba2200c04004a821cc9480550950698376f8002e2f2f15010018d47c3e5704801608500100fed26f4ac0aa04a8b4e142090000f82b6997b69dda000040643685423d04a8000040e72e2eecb903e0cbbc8c06208adbdb5b4588c1d44c562640c5625489abab2b450080cc957c46a8e30400f89ac562a108704f267a00dccbb912b02a012a162300804c94fa02395d970e540080d5095061589a54a01e0254f0031b00a053e7e7f6db01b469737353110000beced44c562640c5625409012a0030949393134500e0ab9c2707807b166d6a9ac62e5e562640c5620400908912373ccde7f3e50700feea5e0100ee59404e04a85089cbcb4b450080cc9518a0ea3e0568dfdada9a2200007cd94c0968830095b618e30b00c09fa440f8ecec4c21005a36994c14010006707d7dad08f99b29016d10a0d216637c03700e2a00e46fb15814732dc7c7c7be5000bec9cb68003caf01b911a0424504a80090bf525e22eb3e05e8c6fafa7a71d7e4653400ee57b448b317ad10a0d2969912000070e7f0f05011003a5062800a0011989810c63f958036085069cb6f4a90bfabab2b450080cccde7f3f0d770797939bab8b8f0650274603c1e17753de99e010000b911a0020040464a180ba5fb14a03b1b1b1b8a0000032861b36b254e9580360850b12855c4980900c8dfeded6de87fffe3e3632f16003a545a07aaf3e40070cf0272244005377900202391c3c7f4ef7e7474e44b04e8d0643271df038001dcdcdc28420c3325a00d02542c4a6ef20000ad78f3e68d2200746c6d6d4d11006000deadc6d034cd4c15688300158b929b3c009091cbcbcb90ffdee9dc535d4400dd2bad0335ea7d0f80fa443f6e057818012a5446880a00b4ede2e262747272a210001d5b5f5f57040018880da3219c2a016d11a06271aa8c001500f217a91b27bd4430ba17a01f2506a83a50018860b15828025446800a6ef60080fbf5a3ff3d5378eaf705403fa6d3a92200c000aeafaf1521869912d016012a16a7ca18350100eed76d699ac66f0b801e3d79f2a4a8ebd17d0a00b4ec3725a02d02542c4e9571d83900e42fc2c8fdb76fdf7af10dd0b3d246f89a600040145757578a10c34725a02d02542c4e95d1250200f9cb3d404de1e9d9d9992f0aa067a58df0f57c0a00b4ec5c09688b00158b536574a00240fe723e5f2705a7c25380fe4d2693e2ae29c2c40500484cdf81fa0850a13276f80240fed248c31cc71aa6e034759f02d0bf8d8d8de2ae49800a00b4a9699a5355a02d02542c4e1572ce0c00e42fb72e54e129c0b04aec40d5cd03807b16902b012a5428e7b18000c0ef729a1a213c05185e6901aa8dbd00b867d132470cd2aabf2b011d2c52dbca9037639200c0fdfabe8e8f8f47474747be1080814da7d3a2aec7c65e00dcb368d94725a04d3a50b14855e8c3870f8a00f46a7d7d5d11e08172e8404d5da7c25380e19538be37a7490b00f05774a086315302da2440c52255211da840df04a8f070439fb193c2d334ba1780e195d67d9a08500170cfa265bf29016d32c2178b548504a80010e79eddf70684b4bbfad5ab575e120064a4c40e54cfa500b867d132d33169950e54da365382fc99db0f0031f41d62a61703c25380fc94d8813af4a4050078c87312219c2b016d12a0d2b69912e4cfdc7e0088a1cf2033fd7ffde31fff109e0264264d2228ed3804f71a002211a086a1039556095069db4c0962b0db1700dcafefa4b34e53786a9315407e4aec3e351509804804a831344da30395560950697b919aa9420c5e900240fefa0850dfbe7dbbfc0090a7cdcdcde2ae49072a00ee59b44cf729ad13a062b172f307002abc67a7cd54af5fbf5e769f0290afeded6df7360018c8ededad22c4a0fb94d60950b15855cae8090088a18b3187e9c5f52fbffc62a43f40e62693c9683c1e17775dee3f0044717575a5083168eaa2750254ba305382fc095001a0ce07f6939313e79d020451e2f9a7ba4f0188c43bd4302e9480b6fd5d09e8c06f4a90bf2eba590080f6b5d5a59302d3376fde8c2e2e3c570244b1b5b5e5be06000312a086a10395d60950e9c24c09f2975ea2a64f89e3a000a0b407f655efd9a9dba7691a0fff0081a4755f072a000c4b134a188e15a47546f8d2859912f8010000b467956e9dbb91bdc2538058b6b7b7ddd30060608e3e0963a604b44d808ac5aa625ea402400c8f79d99c1ef453d7e9e1e1a1020204b4b9b959dc35a57b93e750004a7e0e63189f9e7d67aa40db04a858ac2af6e1c307450080021fdcd378c4d475eabc5380b84aec40f5221a80486cfa0963a6047441804a57cc1cf7f00a00b42405a2f71d1d757c7c6c642f40705b5b5b2b9d7ded19140056a7f9248c9912d005012a5df9a804f9bbbdbd55040008e2fcfcaff7a7a580f5f5ebd7a3a3a323c502082e05a82512a00210c9f5f5b52204795c5602ba2040c5a255b1d4cd0200c4707575f5d5ff2e8deafde5975fbc9806284489e37bd3461fcfa0004462aa4f18ff5402baf07725c0a255b7f4003b994c14020032f7a57034bd8c4e1da72727270a045008e37b01200f36fe84a1998b4ee840a52ba74a10839d540010e79efdf9037cfacfaf5ebd129e0214a6d4f1bd695a020044213c0dc571827442808a45cb8f0145008020eeba7752689ac253f77180f29438bef7f37b180044a0e9248ea6694e55812e18e14b578bd6f9c1c1814204e030740088e3ecec6cf9025a170f40997676768a1cdf9b5e427b110d402436ab86a1918bce0850e9d2ecd3e7a932e4ff200b00c47988f7200f502ee37b01200f9a4ec270fe299d31c2972ecd94207f5ec20200000c6f7d7dbdd800d5f85e00a2d17412c64c09e88a00952ed9fd1184101500006058a586a7890e5400a2f1be348cdf9480ae0850b1786147150000c0c07677778bbc2ee12900d1084f43d1c4456704a858bcf0a30000006040d3e97439c2b744025400a2d16c12ca4c09e88a00158b17cea301000018d00f3ffce079130032a1d9248ea6693471d119012a5d2e5e335588c1ae2a00008061a4ced39d9d9d22af2dbd80f6bc09403436ff84315302ba2440a56b768004e0811600006018a586a7c9d9d9992f188070bc2b0d63a6047449808a458c253bab000000fa57f2f85ee79f0210cd62b110a0c6f1ab12d025012a9d3f2f29410c66fb030000f42b759fa611bea53e637a010d4034d7d7d78a10c74725a04b0254ba365382183cd8020000f46b7777b7d86b33be178088349984e2f8403a2540a56b3325f0e3000000803f9b4ea7a3c96452ecf519df0b4044de91862240a55302543ad534cda92ac4e00c54000080fe94dc7d6a7c2f0051b97f85f1b1691a237ce99400953ecc9420ce432e000000dd4ae79e6e6d6d157b7dc6f7021095269330749fd239012a7d9829410c7658010000746f6f6fafe8eb33be178088349784325302ba2640a50fbf2a811f09000000fcde7dbab3b353ecf5a5f0d4e65c0022babebe5684387e5302ba2640a50f332588c1880a0000806ee93e055631994c14013aa2b924945325a06b02541eed5ffffad7bd3e23016a18765901000074a7f4eed3c562e1fc53e8d8dada9a22404704a8a1cc1e904fc0a30850e983039d033dec1ab5040000d08dd2bb4fcfcf3dfe031097e692389aa699a9025d13a0d2c762f6f1d33f3eaa440c765a010000b4aff4eed3e4e4e4c4170d4048a9a924359710c2a912d007012a7db10d3508012a000040fb4aef3e4dcf929e2701887c1f238c9912d007012a7df9550962b8bcbc540400008016e93e0580bc095043f94d09e8830095becc942006b3fe010000dab5bfbf5ff4f5a59187ce3f0520324d25a19c2a017d10a0d2979912c479f04d33ff01000058dd743a1d6d6d6d157d8d293c756e1c0091692a89f5d34309e88300955e344d73aa0a711859010000d08ed2cf3e4d8cef0520b2f42ed446a0303e364df35119e88300953ecd9420ce8f060000005693ce3d4d1da8254b230f3d43021099eed350749fd21b012a1637bef8000cd0a6f5f575450000aa321e8fabe83e7dfffebd2f1b80d06c040a45c6406f04a8f4e9420962b0eb0a689b001500a8cd4f3ffd54fc6fa09b9b9bd1d9d9992f1b80d004a8a1c818e88d00953ed91d12449af9ef87030000c0e3a4e0747777b7f8eb74f6290025308d2f949912d017012a7d12a006a20b150000e071f6f7f797237c4b9636deea3e05203ae1692c4dd39caa027d11a0d2e7e23653853874a00200003cdc743a1d6d6d6d157f9da9fb3485a800109977a0a168d0a2570254fa76aa0431d87d050000f070cf9f3fafe23a759f025002016a283325a04f0254fa6697881f0f000000457af6ecd9f2fcd3d2a5f0f4e6e6c6170e40789a4842b95002fa2440a56fbf29811f10000000a5994c26a3bdbdbd2aaef5f8f8d8170e40786914bd0d41a19c2a017d12a0d2371da8815c5d5d29020000c03decefef57719d1717175e36035004cd23e1c816e89500955e354d73aa0a715c5f5f2b020000c037eceeee8ea6d36915d77a7272e20b07a0088e300be563d3341f95813e095019829d2241d885050000f0d7d299a7e9ecd35a9e113d270250d27d8d30640af44e808ac58eaf720e000000c05f7bfefcf9683c1e5771adefdfbff78503500c016a28bf2a017d13a03284df94c00f09000080e86a1add9b36d79e9d9df9d2012882f1bde168caa27702548670aa047e4c0000004436994caa19dd9b1c1f1ffbd2012886a6917004a8f44e808ac50e3f260000001ee8c58b17d58ceed57d0a4069bcf30ce563d3343365a06f02547af769b1fbf8e91f16bc2052076a3a0b15000080dfedefef2f3b506ba1fb1480d298ba178a862c062140c5a2c7375d5f5f2b020000c027e9ccd374f6692d749f0250e2bd2d7d08e35725600802548672a104715c5d5d2902000050bd34b2f7e0e0a0aa6bd67d0a40698cef0d4733168310a032945325f0a30200002092149ed672ee69a2fb1480126916094780ca2004a858f4f826012a000050bb67cf9e2dc7f7d644f7290025725c59281f9ba69929034310a032884f8bdec74fffb0f00522440500006a9582d3bdbdbdea9e01759f02509ac562319acfe70a1187462c0623406548a74a1087d1160000408d6a3cf734d17d0a4089348984f3ab123014012a43ba50023f2e00000072f6f2e5cbaace3dbd7bfef30c0840a9f73842d181ca6004a858fcb8176703000000b579fefcf96832995477dd878787be7c008a24400d4786c06004a80ca6699a535588c3f9000000404d767676969fdaa4734f3dfb015022ef37c3f9d834cd4c19188a0095a1d94112881d5a0000400da6d3e9b2fbb436e9c5b2b34f012895779be19c2a014312a06211c48f0c0000807f4b237b0f0e0eaabcf6939393d1cdcd8d3f02008ae4dd6638174ac09004a85804f123030000e093f1783c7af1e2c5f29fb549c1e9d1d1913f02008ae5dd6638a74ac09004a80ccd08df409c1300000094ece5cb97cb0ed41a1d1e1efa0300a058de6b86243b6050025406d5344d5a043faa441c766a010000254a679ed61a9ea6e7bc8b0b03a20028fb5e4728e74dd3c80d189400952c16432588c343350000509afdfdfdd1cece4eb5d7fff6ed5b7f0400144d801a8ecc80c10950c9c1af4ae0c7060000c0105270babbbb5bedf51f1f1f2fcf3f058092690a89f79529014313a0928353258845880a0000942085a769746fad5270faeedd3b7f0800147fbfb359289c53256068025472a01d3f98abab2b4500000042dbdadaaa3a3c4dd2e8dec562e18f0180a2690689a7691a99018313a092c362980e83b620fad1010000d08bc964327af1e245d53548a30c3ddb015003cd20e19c2a013910a0920b016a20e921db2e65000020a2149ebe7cf972341e8fabad417a9e7bf3e68d3f0600aa60c35038bf2a013910a06251c40f0f0000a00ac2d3dfa5f0d4a658006a309fcf9d7f1acfa9129003012ab9d0811a8c00150000884478fabb34ba377d00a006de6186242b200b0254b2f0ef43a13faa841f1f0000006d139efe2e759d1e1e1efa8300a01ade618673de348d9c802c0850c9c9a912c461fc0500001081f0f40f6974afe738006a22400d47f729d910a092133384fc00010000688df0f4b3076ea37b01a84c7a77e9ccef707e55027221402527a74a10cbd5d5952200000059dadada129efe5b7a799cba4f01a026360e8574aa04e442804a369aa6b13806737e6ea2020000909f9d9d9dd1c1c181f0f4df5278aa030780da989e17ceac699a9932908bff2f007bf70f1bc775c50d7b14b8a12bbf514b034e11b5523ab3b3e5522aec822d2575e3ea0bd5ca402c206a29b9caed4cb2651106204b319b8aea4cb674f111085bfa6565957ee7ac761d5a2625fed9d99d7be77980c1288963d387cbe5ecfddd73ae0095ae1928413ee203789c850a0000d015119e2e2d2d29c4c8cece8e0e1c007ac7ba659674ebd0290254bac68cf3ccd8c905000074c5fdfbf785a7a71c1f1f575b5b5b0a0140ef989c9725d9009d2240a573bfdb94202f76320300005d10c1e9bd7bf714e2949492d1bd00f4d28f3ffea808f91928015df28112e04d92eb880ed4f840ee6c2100006016e2b3489c777aebd62dc538656363c3e842007a4b076a764e524abe69748a0e543aa579933ca974a166c7185f000060166edebc593d7efc5878fa96981414679f02401fc506221318b2335002ba46808a374b26f2e11c0000609ae6e7e7ab274f9e0ceffc4f9c7bbab6b6a61000f496b5ca3cbf6d4a40d718e14b17c561d17f55867ce840050000a66961616178e629bfe7dc5300facef8de2c0d9480ae11a0e2cd926b8b1dce311ac3ce6f0000a06d119c4680caef39f71480be8b4d447e17e627a5345005bac6085fbaf866e91cd40ce942050000da343737371cd92b3c3ddbeeeeae734f01e83ddda7591a28015d2440c59b2613fbb00e0000d0865bb76e55cf9e3d33f5e61cd16913dda700d077ce3fcdd27f94802e32c2972ebf693a0735b30fec31222376850300004ccafdfbf7ab7bf7ee29c439e273d8dada9a734f01a032252f530325a08b74a0e24d938931220300009894d89cb9bcbc2c3c7d8f084f9df506006fc2531b8af2e3fc53ba4a804a57df349d839aa11f7ffc51110000806b1b8fec8d3be7dbdede36aa100046fc4eccd24009e82a012ade3c99181da80000c0752d2e2e0e3b4f1d0ff26ebbbbbbd5d6d6964200c08800354bce3fa5b304a878f364626244867306000080ab989f9faf9e3c7952ddbd7b5731de2346f66e6c6c2804008c1c1f1f0f2fb2335002baea0325c09b2793143bbd8cd90200002ee3fefdfbce3abda0d8b8bab2b2e28c37003845f769964e9c7f4a97e940a5b39c83ea6105000028dbb8eb54787a31c25300385b8cb6273b0325a0cb74a092c39be81d65c8c7785cc6cd9b37150300003897aed3cb5b5b5b1b8eef0500fe273616f9fd982547f8d1693a50e9ba7f29417e74a1020000e789233f749d5edefafabacf5a007086bd3d430c33355002ba4c804aa799819e272333000080b7cdcdcd558b8b8bd5f2f2f270742f17b7b3b3e37316009cc306a32cc5f9a7926f3acd085f723068aecf94211f3132c3185f0000606c616161189e4688cae54470bab1b1a11000708618df2b40cdd24009e83a012a398859e89f29435e0e0e0e868b240000407fc5b8de18d51b772e2f168463742f0070b65883244b8eeea3f304a8e460b3b9fea60cf97dd017a00200403fc5349a084e7d26b8ba98ecb3b6b6a61000f00eba4fb3355002bace19a874de6816fa894ae4f7f0122334000098bc18857af7ee5de350e99c784ddebf7fbf7af2e489f0f41a223c5d5959f1990a00de636fcf319a193a4c291d2a035da703955c0c9aeb4b65c8ef01c6a20900c0642d2d2dfdfa8c15416a6c5c8b2b9ebd842dcc5204a782fdeb139e02c0c568e0c8d64009c88100955cc44c74016a667efcf147012a00c0844428f5f8f1e36a7e7efe37fffdeddbb7875784a911a28e0355989678e68f71bd31b697eb894560e129005c8c67de6c39ff942c0850c9c54009f2130b78d1210100c0f59c179ebefdd744901557842ff12cf6ead5abeae0e040016985e074b284a7007039c6f7666ba004e440804a1662267a5dd787cd1f3f518dbc1600622758744400007035114e35cfc2ef0c4fdf763a4c3d3e3e1e3e93edeeee0e4783c275c46bebce9d3b82d3163e3b4578ea6714002e267e67da7494a5bd94d289329003012a39d96caebf2a435e04a800005717a169749e5ee74cc908b9e25ccab884a95c55bc06bff8e20b679cb640780a009717cfb364c9f85eb221402527ffa904a8d931c61700e06a26119ebe4d98ca555e33d16d1a5da782d3c9139e0297f5e73fff5911a072fe69c6064a402e04a86423a5b419a3cbc86f4140172a00c0e5c4b3d383070f5a0dac84a9bcef3518af8d5bb76e29468b9f9584a7007079f1bb339e5fc9ce494a69a00ce442804a6ee20df63365c88b001500e0e2e2dcd2694ff0381da646a8135344e219cecefe7e89d741bcfe3efdf453e79bb64c780a0057677c6fb6064a404e04a8e42666a47fa60c7931c61700e06266119ebe2dba5ee3eb882b429e83838361901acf74f19f294f6c768cefb74d8fd3213c0580ebb1c92f5bce3f252b025472335082fc18e30b00f07ef7efdf1f9e35d92511a6c6335c5c11ec8ec3d4b80b7ff21667ec46c7b1b34da72b7e6e524ac60e02c0357e97fa3d9aad81129013012a59693e68eed5757dd8fcf113d5c88b001500e07c114e460760d7c57998e33331c7e7a646981a97eed4ee8bd074dc696a44eff4c5826f749efa590180ab33be375b7b29a5436520270254723468ae87ca90d96f48637c0100ce944b78fab6d3e7a606dda9dd1481f7b88b58683a3bc25300980ce37bb33550027223402547312bfda132e4c5185f0080df8ab1a9755dffdad199bbd3dda9e3b353c7974075baafab18cbfbe73fffd978de8e88cf416b6b6bc25300b826e37bb3e6fc53b2234025470325c893001500e08d08b51e3f7e3c1ca95aeabfdfb8eb310854dbadf538bc8eabd4d754ae62cce0fafaba4200c0847eaf92a59394d24019c88d0095ec346fb627755dc71bee67aa9117637c0100ca0f4fcffb777e3b50fdef7fff5bfdf8e38fc34035feac3bef62620c6fbc7604a6ddb7b3b3536d6c6c2804004c8800355b03252047025472152dff9f29435e8cf185fe8ac57100aa61d815637bfb7e16e5e9aec97bf7ee0dffbbe84a8d2035ee71f9ddf1262c8d6b1c94c6e51cd33c44d7a9455e00989c5853b4e12e5bc6f79225012ab9da6caee7ca90e7c38e001500e8a308bfa2f3d49994e7d7e7ed6ecaf13957e34035fe5ceab957e3ced28f3ffe78787e69dcbd56f2130bbb71de697cee010026c7efd6ac0d94801c0950c9524ae9b0aeebc3e68f9fa8465e628cefe2e2a2c52000a05784a757af5b5cb1016fdca91ac661ea4f3ffdf4ebf8df9c3a56a3a374dc5d2a2c2d47bc0e5756569cf10b002d883545f2fcd6c55abe329023012a398b2ed4bf2a435e6251211e78161616140300e88508ff1e3c7820209ba00820cf330e52e37cd570ba6b751a67adc6f73902d110e1ef871f7e58fdf18f7f1c86a582d27245681ae1a9d182003079c6f766cdf85eb225402567ffa904a8d93ef4085001803e88679ea5a5258598a271b8faae90359c370e781cbc9e671c869e36ee26a5bf9f6f626caf855d0068ef772dd9da5402722540255b29a5cdbaae4f9a3f7ea41af93df4c4e282ddf70040c984a7dd765ee8f9bee0154edbdedeaeb6b6b61402005a126b88bbbbbb0a91a7939492d9cb64eb0f4a40e6064a9027e716000025bb7bf7aef0140a168bb9ebebebc25300689935c4ace93e256b02547267867aa65ebd7aa50800409122385d5c5c540828548c7e8ef34e75c30040fb8cefcd9ab57bb22640257776b164eae0e0e0cc33a700007216e1a9b3dea1eccf31cf9e3dab8e8e8e1403005a166b8702d4ac0d94809c0950c95a4ae9c41b71be3c00010025119e42d9e2bcd3e7cf9f0fc7f70200edb37698b5cdd1da3d644b804a098c02c8d4cece8e220000d99b9b9bab9e3c79223c854245601ac1a9f34e0160ba8ccbcfda7f9480dc0950298131be998a311c465f0100398bf0f4f1e3c7d5fcfcbc624081e2f34a8cec8dd1bd00c0747f075b37cc9a357bb22740257b29a5c3e6b6a71279b2930c00c895f014ca161373223c8d8d9f00c0745933ccdade68cd1eb22640a5140325f0300400302d119a0a4fa14ce391bd1b1b1b8a010033e2fcd3ac39728f22085029c59a12e42916273c10010039119e42b962546f9c696c642f00cc4eac159a009135e37b2982009522a4946284efa14ae4fb500400908371781ae37b81b244c769749ec6264f006076ac1566ed70b4560fd913a052123b5b3215637c2d5200005d273c85321d1d1d0dcf3a8d334f0180d98a3542477e65cd1a3dc5f8400928c87f9aebafca90a7bdbdbd6a6161412100804e8ae794c5c545e12914264253679d024077c41a215973fe29c5d0814a31524ab1bbe54425f264b73700d055119e2e2d2d094fa12071ae5a8ceb159e0240b75823ccda494a69a00c9442072aa58910f5a132e427c666c522c6cd9b37150300e88c71780a94231666b7b6b61c2302001d136b83b14648b68cefa5283a50298d110119b3c30c00e89238f354780ae538dd752a3c0580eeb136983d6bf31445804a518cf1cddbfefebe2200009d11bbdf9f3d7b561d1c1c2806646e7b7bbbfae69b6ffc3c034087591bccdac9686d1e8a61842f2532c63753b1233c1e946edfbead1800402744881a1d6bb76eddaaeeddbb37bc03f988c0343a4e8d0304806e8b35c1581b245bc2538a2340a544ffa904a8d9dadddd15a002009d13214c5c71266a04a9ce6d876e8b11bd71cea951800090875813246bff51024a2340a544b1dbe57b65c853ec368bc58eb9b939c500003a271676e28a20757171d1330b74f4e7d439a700908ff89d6d7c6ff674a0521c67a0529c94d28937ecbcd9710600e4f0bcf2e4c993e1b98a421ae88618fb1723b7d7d7d7fd5c024066cfd6646d73b4260f45d1814aa9fed55c5f2a439e62ccd6ddbb77150200e8b4f188d0972f5f565f7cf1c5f0f945472accee67d1b85e00c893dfe1d9fb97125022012aa532c63763b1733cce18bb75eb966200009d773a48bd7fffbe8d603045c6f50240de620d30d602c99a699014c9085f8a648c6ffe5ebd7aa50850101f86803e880027829c6fbef9c6183268592cb63e7bf6ccb85e00c89c35c0ec19df4bb104a894cce8808cededed59088182085081bebde745a8234885767ebee29cd3b88e8e8e14040032166b7f9e97b3670d9e6219e14bc98cf1cdfc012a42d4858505c50000b2340e52b7b7b7ab7bf7ee79ae816bfe3cc5cf924556002887dfeb4530059262e940a558c6f8e6cf01f200400974a4c2d5c5c6ca084efdfc004079acfd65cff85e8aa60395d2c508812f95214f31922baef9f979c50000b2a723152e2e82d358547df9f2a5a33d00a040719eb9e37eb2677c2f4513a0523a637c33178b264b4b4b0a010014e374907af7eedd61903a3737a73050094e01a02f5ebd7aa508f933fd91a219e14bd14623045655225f710eaa851300a04411a46e6c6c544f9e3c1986a99e79e8b3f1a8def879d8dadaf2f3000085ffde379a3f7bc6f7523c1da8f4418c1278a80cf93e5045886ac41d0050f2f34e0446d17117cf3cd1957af3e64d85a137af7f1da700d02fc2d32218df4bf104a8142fa5b459d775ec86f94835f2143bd105a80040e9c641525cf1ec13e7a40a5229557460c76b3d165005a700d02ff10c40d662adddf85e8a2740a52fe20dfda132e4291657e260f95bb76e290600d00b112ac515cf3f11a47a0ea2a467fbd820a9f304e06a3efcf04345206bb1c617cf0364cdf85e7a41804a5f18e39bb93858dec22100d037b1c0145774a246907ae7ce9d6a6e6e4e61c8f2b51cc169dc01b8baf9f97945206bba4f8b607c2fbd70e3975f7e5105da7d91ddb8d189afa3aeebff5b19e39bb59595150b8690a9afbffe5a110026209e85bef8e28bead34f3f35de97ce8bd1bc7b7b7bc3e054a709c064fce31fff5004b215cf03df7cf38d42e4ed24a5f47fbaf085c8b6689b0e54fac418dfccc50eb5e8bc0000e8ab08a4b6b6b686d7eddbb7abbb77ef9ad241e738df1400384b4c98237bce3ea53704a8f4c9779500356bb1002340050078637f7f7f7845276a04a90b0b0ba67530f3e7f5581835a6170038cbcb972f15217fc6f7d21b46f8d2fe8bac23237c435dd7ff7f73fbc477255fcdf770d86d01e4c5085f80e9881035c6fbea4a655a749b024c9711bee42a9e15d6d7d715226f8729a53f75e58b916dd1361da8f44d8c18f8ab32e42b166704a80000678b85a9b8c65da9f1dce4ac54266d7cb6693c9b1f1d1d290800f05ec6f716c1f85e7a45804adfc4185f016ac6621c58ec72b710080070be785edad8d8185e11a2c615dda9701de3b1d111d203005c546cb832e2bf086b4a409f18e14bfb2fb20e8df00d755dffd0dceef8cee42b16ff96969614023262842fc0ecc5f9a877eedcf93550858b1887a6d1716a442fc0ec19e14b8e6274af0d58d9ebd4f8de20dba26d3a50e9a3d8292340cd582cde2c2e2e0e170181ee8b2e2800662fc2afd3237ec75da9f3f3f38ac36f084d0180493f8392bdef9480be11a0d24731abfdb932e4fde0158b39c6d0411e04a800dd7c6f8ef32be312a61284a600401be279932238ff94de11a0d23b29a5c3baaee30dff4bd5c8d7f6f6b600150060024e87a9c6fcf6ebfb1e679145681a77a12900d006dda745d88b357565a06f04a8f4d5bf2a016ad662c127167b2cec01004ccee931bf611ca4deba756bd8a94ade4e07a64747470a0200b42a9e294da52a82f1bdf4920095be1a8ff1fd4829f2155d1202540080f68cc7ba8618ef1b41eaf8721e7df745481a61e9f8d2650a004cd3ab57af14a10cc6f7d24b02547a29a574321ae3fb5035f2158b40b18b4d37040040fb228c8b6b7c8ed53848fdf39fff3cbcd38def91c01400e882f1f308d95b8db57465a08f04a8f4d95a2540cd5e9c85bab4b4a410000053f6f6a2d8e90ed5f8b34d6eed8a70743c8ad7022500d035ba4f8bf12f25a0af04a8f4564a6950d7f561f3c74f54235f7b7b7bd5e2e2a21172000033f676876a3c9f7dfcf1c7bf06aa42d5ab8bb0f4bffffd6ff5e38f3f0eef5167e78901005d15cf2971fe29d93b492919df4b6f0950e9bbf805f05765c8572c26c522ddbd7bf7140300a063cf6967754646a01a416a5c31fe77fc67de181f53f1d34f3f0dff1c81a951bc00404ec61beac8deaa12d0670254faeebb4a809abdd8d126400500c8c379a366a343f5c30f3ffcf5fec73ffef1d760b5b43356c7e7938e3b49e3129402002588e719dda7c5585302fa4c804aafa5940eebbade6bfe784735f2351e0bb2b0b0a0180000998a3031bceb2ccf71b7ea783c7088c0757c9c43fc77b33cdae1f4d71ee376c7cfaa71fdfcf3cfbffe3b0200942ad6e86c0a2bc25e4a694f19e833012abce942fd5e19f216a34104a80000651b8791617f7fff9d7fede990f5b4ab76b39e15ec9efe7a000030beb720ba4fe93d012abc3907f579737da414f98addfcb1a855da78370000ae667c06ebdbded5e10a00c0d545f7a9cd65c5585502faee0f4a40dfa5944eaa37212a99dbdede560400000000988157af5e29421936476be6d06b025478c34882024437815d6ed03dce7b03000080b2c5ba9c491fc5b0560e95001586524a83e676a812f9d3850add132314010000807239fbb418872925d31aa112a0c26976d614c0590b00000000303db116b7bfbfaf1065109ec2880015fe675509cae0bc0500000000980e13e18af29d12c01b02541849291d5676d814e1e5cb9746860200000040cba2fb3426c25184c1688d1ca804a8f0b67f2941fe223c75ee0200000000b4cb24b8a238e20e4e11a0c22929a5d5e676a212f9b3f30d00000000da134d0c31098e22c49ab8e98c708a00157e6f5509f2677c0800000000b42726c03946ab189b29258d45708a00157ecf41d98570803d00000000b443f76951ac89c35b04a8f096d141d90395c89f2e540000000098bc5873d37d5a8cbd94d29e32c06f0950e16c0ecc2e8483ec0100000060b24c7e2b8aee5338830015ce90525aadde1c9c4de60e0e0e8617303b3ffffcb3220000004021a2fb3426bf51845803df5406f83d012a9c6f5509ca60471cccd6d1d191220000004021acb5156533a5a49108ce204085f3195d50085da800000000707dba4f8b630d1cce21408573a4940e9bdb4025ca60671c000000005ccfab57af14a11c7b29a53d6580b30950e1ddd694a00cd1816a8c28000000005c8d296fc5d17d0aef204085774829ad566f0ed2a6003b3b3b8a00000000005760c25b5162cd7b5319e07c025478bf55252883331a000000805cddba754b119819dda7c5d94c29691c827710a0c2fb196550103be500000000e072aca915c79a37bc870015de23a574581967500c5da8000000007071ba4f8b334829ed2903bc9b00152e664d09ca61c71c000000005c8cb5b4e258eb860b10a0c205a494a203f55025caa00b1500000000de4ff769714e524aabca00ef2740858bb333a72076cec1743f6c01000000f9b186569c5525808b11a0c2c5bd508272e8420500000080f3e93e2dd2774a00172340850b4a299d5476e814c50e3a00000000389bb5b3e26ca6940e95012e46800a97638c6f4174a102000000c0efe93e2d92ee53b804012a5c424a69d0dcf654a21c76d201000000c06f59332bcee1686d1bb820012a5c9e9d3a05d1850a000000e4c0fa05d3a2fbb448d6b4e19204a8704929a5d5e676a212e5b0a30e000000e83a012ad362adac38b196bdaa0c70390254b81a3b760aa20b1500000000749f166a33a5a421082e49800a57b3aa0465b1b30e00000080beb34656a4a74a00972740852b48291d5642d4a24417aadd7530797eae000000209fcff03ec71767305acb062e49800a57b7a60465b1c30e000000e8aab9b93945a0551b1b1b8a501e47d1c1150950e18a524a83e6b6a712e5b0cb0e000000e8aa8f3ffe5811684d4c673b3a3a5288b21ca694369501ae46800ad763074f6174a1020000005da403953659132b92b34fe11a04a8700d29a5d5e676a212e5d0850a00000074910e54da12dda7c7c7c70a519658b3d67d0ad7204085ebd3855a183bee00000080ae999f9f57045a612dac48ab29258d3f700d0254b8be174a5096e8408d9d77000000005d2140a50d3b3b3bba4fcba4e907ae49800ad734dac9b3aa1265b1f30e000000e88a9b376f0e2f98a4d7af5f575b5b5b0a519ee83e3d5406b81e012a4c8603b90b133bef74a1c2f51d1d1d29020000c035ddbe7d5b1198b8e83e8d1095e2ac29015c9f00152660b4a367a01265892e540f91703d7e86000000ae6f6161411198f8e7f5972f5f2a44790629a58132c0f50950617274a11626ba5063271e000000c0acc4e85ee79f326931bad7a6e722e93e850911a0c2848c76f61caa445962279e87490000006056eedebdab084c94a681621da6945695012643800a93a50bb530119e7aa00400000066616e6ecef85e262e8eada248d6a6618204a83041a31d3e872a51961869123bf300000000a6e98b2fbe1886a83029070707d5eeeeae4294e7a4b93695012647800a9367ce7c81eccc03000000a6298253e37b99346b5cc5fa2ea574a20c3039025498bc17d59b1d3f142476e61d1d1d29045cd2cf3fffac080000005770fffe7ddda74c54749fc64591569500264b800a1336dae9e317568136363614012ec9c603000080cb9b9f9fd77dcac4adafaf2b429956534a87ca0093254085767ca704e5b14b0f2e273ab7fdcc0000005c4e749d3e78f0402198f867f4e3e3638528d3532580c913a0420b463b7e5655a23c76eac1c544e7a99f17000080cb5b5c5c1c76a0c2a4bc7efdda64b5726dea3e85760850a13dba500b143bf562c71e70be084f57565614020000e09216161686174cd2cecece3044a548d6a0a125025468494a69afb90d54a23cb163cf43279c2d7e36d6d6d6fc8c0000005c52749d2e2d2d29041315cd002f5fbe5488320d524a036580760850a15de6cf172882a1d8b907fc5ef3e03eec40050000e0e2223c7dfcf8b1423071dbdbdb3639974bf729b448800a2d1aed001aa84479b6b6b6863bf880ff89334f0f0e0e14020000e012c6e1e9dcdc9c623051b1c1d95154c53a4c296d2a03b447800aed5b538232c50e3ee07f3f0f3e940100005c8ef09436c5315414cbe443689900155a96525a6d6e872a519e088b74dbc19b9f85e8ca060000e0e216161684a7b4667f7fdfba55b90e476bce408b04a8301d7604154a172a7d17e38062742f0000001777fffefd6a696949784a6b749f16cd5a334c810015a640176ab962279fb1a5f45584a72b2b2b0a0100007041376fdeac969797ab7bf7ee2906ad890dffc7c7c70a51a693e672f6294c810015a6e73b252853ece87bfdfab542d02bf19a4f2979ed0300005c508cec7df2e44975ebd62dc5a0d5cfeb2f5fbe5488727d97523a5106689f0015a667b57ab34388021f4c77767614825ebde6a3f3d46e56000080f71b779d1ad9cb34d8e85fb4585b7ea10c301d02549892d1ce205da885dadada1226d1a7f7b3e1f85e000000ce17c16984a67ffffbdf759d3215f159dd515345d37d0a53f48112c054c50ea1ffafb93e528af2c40ebfbaae1582a2adafaf0fcffe050000e06c119cc619a731b217a629d6a62896ee5398321da83045ba50cbb6bfbf2f58a2f80f6276b20200009cedf6eddbc38dd5d1712a3c65dae2f3ba75a9a2e93e8529d3810ad3a70bb560d19d171f94a0c40f62cefa050000f8ad18cd1bc1695cd1790ab310679e6e6f6f2b44d9569500a6ebc62fbffca20ab4fb22bb714311de52d7f5b7cded6f2a51a6fbf7ef0f47f54029223c8dcd010000007d373f3f3f0c4dc7d7dcdc9ca23073119e6e6d6d2944b956534a8f94e1b7645bb44d072acce8975e25402dd6cb972fabbb77effa1045118e8e8e84a7d011b140170b761f7ef8e1f07edeef99e3e3e3eaa79f7e52300080f778dfb8d3b79fbde2790cba269eff85a7c57baa04307d3a5069ff45a603f54c755d7fdfdc1eaa4499e2ac93a5a52585206b119eaeacac0c470101d31763e0c69d0db168070000f0b6e7cf9f3bfbb46cba4fcf21dba26d3a50617662e7d0436528538c3cfdf4d34fed4e255bc253988d084a638ac19d3b774c32000000de298253e169f1749fc28ce840a5fd17990ed473e9422d5b2c823f79f24421c84e84a6119e46880a4c474c2e88e054a72900007051df7cf3cd70842fc5d27dfa0eb22ddaa60315664b176ac1227cdad9d9192e88432e84a7305d119cdebb77afba79f3a66200000017b6bdbd2d3c2d9fee5398a13f2801cc4e4ae9b0b9adaa44b9b6b6b68c40251bc253989e18f1fef7bfff7d785eb6f0140000b88c084e5fbe7ca910655b1dad1d0333224085d9b393a8601148adadad290459d8d8d8109e42cbe25cd3baaeabe5e565c12900007025ebebeb36ec97cf9a31cc980015664c176af9f6f7f7ab83830385a0f31fbe76777715025a14e37a9f3d7b56ddbe7d5b310000802bb1ced40bba4fa10304a8d00d7614152ec229e8f2eb53780aed19779dc6b8def8330000c05544d7694c8fa278d68aa10304a8d001ba50cb1767536c6f6f2b049d13af4be129b4677e7ebe7af2e489ae530000e0da767676866b4c144df72974840015bac3cea2c2bd7cf9d2432e9d12c1e9d6d69642404beedebd3b0c4f9d750a00005c57ac29f90cdf0bd688a12304a8d011ba50cb1763568cf2a52b223cf57a84f6c4b8dec5c5458500000026c267f85ed07d0a1d2240856eb1c3a870070707d5fefebe423053c253684f9c711a5da70b0b0b8a0100004cec737cac29513c6bc3d0210254e8105da8fdb0b6b636ec468559880f5cc25368c7f8bcd3b80300004c42ac216d6c6c2844f9749f42c70850a17bec34eac183af332b9885a3a3a3d8a8a110d082084d1f3f7eecbc53000060a2223cb511bf17ac0943c70850a163463b8dfcc22cdccece8ed12b4c5584a72b2b2b3e74410b6edfbe3d0c4f637c2f0000c0a4c4da518cefa578ba4fa18304a8d04d2f9aeb4419ca66fc0ad3223c85f6c459a7755d0b4f0100808973044f2fc41af0b23240f70850a183524af18bf33b95285b845adbdbdb0a41ebaf33e129b423c2d3a5a52585000000262ed68c8e8f8f15a27cdf8dd682818e11a04277e942ed81380bd5c3306d119e427b84a70000405b62ad28d68c285eacfdbe5006e826012a74942ed4fe308e85364468bab6b6263c8516084f01008036592bea0ddda7d0610254e8365da83d70707050edecec28041313a169749e46072a3059c2530000a04db146146b45144ff729749c00153a6cb403e9a94a942fc6b2e814641284a7d01ee1290000d0f6677aa37b7b43f729749c00153aaef9451a3b910e55a2fc07e418b70ad77d1d094fa11dc2530000a06d8ee2e98dc394d2b7ca00dd2640853ce842ed81fdfdfde10557213c85f6084f010080b65917ea156bbd9001012a6420a5b45ae942ed053b0db90ae129b447780a00004ce373bdc964bd71385aeb053a4e800af95856020fcc70d66b46780aed109e020000d3b0b1b161437d7fe83e854c0850211329a5cde6365089f219d9c245094fa13dc2530000601a0e0e0eaadddd5d85e88781ee53c8870015f26287524f18e5cbfb084fa13dc2530000605a9fedd7d7d715a23faced424604a8909194d2a0d285da9b07e818df02e789905d780a93273c050000a6656b6bab3a3e3e56887e188cd676814c0850213fce42ed8918df12635ce06db13bd59867983ce1290000302db1e6b3b3b3a310fd614d1732234085cca494f69adbaa4af443046546f9f2f66bc2d9283079c2530000605a8ceeed9dd5d19a2e9011012ae4c9bcfc9e88312e31aa1582f014da213c050000a6c9e8dedeb1960b1912a04286524a877ef1f6478c6a35ae15e129b443780a00004c93d1bdbdf374b4960b6446800af97ad15c27cad00fd1856a946f7f094fa11dc2530000609a8ceeed9d58bb7da10c9027012a642aa514bf80bf5389fe3c601be5db4fc2536887f01400009836a37b7be7bbd11a2e9021012a64acf905fc6d733b54897e30cab77f84a7d00ee1290000306d46f7f6cee168ed16c8940015f2e72cd41e31cab73f84a7d00ee1290000306d46f7f692355bc89c00153297525a6d6e7b2ad19f076ea37ccb273c8576dcbe7d5b780a00004c9dd1bdbd3318add9021913a042199695a03f628caf912fe5129e423be6e7e7ab070f1e280400003055d6717a49f7291440800a0548290d9adb4025fac3cec532094fa11d119e3e7efcb89a9b9b530c0000606a4c12eba5cdd15a2d9039012a94e39112f4eb01dcd91965119e423b84a70000c0ac44781a6b38f48a49815008012a1422a574d8dc5ea8447f1c1c1c54dbdbdb0a5100e129b443780a0000cc4a8cee8d8b5e793a5aa3050a204085c27e4937d78932f4478cf23d3a3a52884c8d3b8985a73079c25300006056e2d825a37b7b27d66435b7404104a850909452fca2fe4e25fac538983cc5f76c656545780a2d109e020000b3149ba5add5f4ced3d1da2c5008012a14a6f945fd6d733b5489fe880ed4e844251fe3f054f7304c9ef014000098a59d9d9de1b14bf4ca614a49f7291446800a657258790f1fce9dab9107e129b447780a0000cc527cd6dfd8d85088fe79a404501e012a1428a5b4d9dc062ad12f46f9769ff014da13a1e983070f84a70000c0cc38f7b4970629a58132407904a8502e5da83d13e15cf3c0a6101dfefe084fa11d119a46e76974a0020000cc42749efaccdf4bba4fa150025428544a69afb9adaa44bfc4191b31ce976e119e427b84a70000c0ac598fe9ad1729a5436580320950a16cd1857aa20cfd62c763b7084fa13dc2530000a00b9ffb4d04eba558737daa0c502e012a14ac7978f38bbca79c87da9d0f51c2536887f0140000e8026b30bdb53c5a7b050a254085c235bfc85f34b74395e89708eca21395d9119e427b84a700004017c4d8defdfd7d85e89fbd94d2aa3240d904a8d00f0e33efa1dddd5d0ff133223c85f6084f0100802e88cffc5b5b5b0ad14fcb4a00e513a0420fa49406cd6d5325fa27c6c81c1f1f2bc414094fa13dc2530000a02b8ceeedadcdd15a2b5038012af4879d513d140ff2cd439d424c4984a6c2536887f0140000e88a3836c967ff5e8a334fadb1424f0850a127524a87cdeda94af48ff350a75767e129b443780a000074451c9714679fd24bdf8dd658811e10a042bfbc682ebfe47b281eec9d87da9e71786a740f4c9ef0140000e88a38262946f7d24b8729a56f9501fa43800a3dd2fc923766a2c79ccdd10ee129b447780a000074c9fafabacffffdf54809a05f04a8d03329a5cde6365089fe711eeae4094fa15d0f1e3c109e0200009db0bdbd5d1d1c1c28443f0d524a0365807e11a0423fe942eda978d08f077eae4f780aed5a5a5aaa6edfbead100000c0ccc57acad6d69642f497ee53e821012af4504a69af7a731e2a3d140ffc764c5e8ff014da15e1e9c2c282420000003367a257ef3d6dbeff87ca00fd2340851efff26fae1365e8a778f03f3e3e56882b109e42bb84a700004097c41a8a3580de3aac34a1406f0950a1bf0f7f119e1ae5db53764f5ecdfefebef0145a243c050000bac4b9a7bdb73c5a43057a48800a3dd63c00ac36b7814af45374526e6c6c28c405edeeeeda750a2d129e0200005de2dcd3dedb4c296d2a03f497001570087a8fedecec0cbb2a79b7084fd7d7d715025a223c050000bac4e4aede33b90f10a042df8d0e417faa12fdb5b6b636ec46e56cc2536897f0140000e81a13a87aefbbd19a29d063025420c461e81e0a7a2a3e104488ea83c1ef094fa15dc2530000a06b9c7bda7b8729a56f950110a002d5e8307463297acc79a8bf273c8576094f010080ae71ee2995e3ce8011012a30343a14ddc1e83d1681619c898af014da263c050000bae6f8f8d8b9a7ac36af8181320041800a9c165da827cad05fd185daf7f3502344169e427b84a70000401739f7b4f74ce8037e43800a9c7e503c6c6edfa944bfadacacf4f6034304a74619437b84a700004017d9504ee3e9e898338021012af01ba343d2f754a2bf223cede3c89a084f63742fd00ee1290000d0458e34a2314829bd5006e034012a7016e32a7aeee0e0a0579d98c2536897f0140000e8a2e83a35898aca5a287006012af03ba3c3d2edbaeab9d87dd9875051780aed129e0200005d1413b8d6d6d69c7b4a8cee358d0ff81d012a70eec343f5e6f0747aacf4334084a7d02ee1290000d055119e3af7b4f70e2b4d24c03904a8c0994687a63f52897e1b9f875ae26e4ce129b44b780a000074d5f6f676b5bfbfaf103c1aad8102fc8e00153857f300b1d9dc062ad16fc7c7c7c310b524c2536897f0140000e8aa8383836a6b6b4b21d81c1d63067026012af03ed1856a27960f17c371beb98b4edae7cf9f0b4fa145c2530000a0ab4adc24ce9598bc07bc97001578a7e6a1f2b07a731e2a3db7b3b39375f018e1e9cacaca300c06da213c050000baaae4638ab8b465a37b81f711a002efd53c50c461ea7b2a418cbe3d3a3acaf2435284a7397eed900be1290000d0653159cbba008d414a69551980f711a0021765ac05431144e6b45b53780aed139e0200005d96fb542d26c6e85ee0c204a8c085a494a203d5285f7e0d2473085185a7d03ee1290000d06571944f749f42e3bbd1716500ef2540052e2346f97ac860184876fdc387f014da273c050000ba2cd604e2dc5368ec35af856f9501b828012a7061a3c3d58db9602846df6c6f6f77f26b139e42fb84a700004097c5dac0dada5a56c710d12a6b9ac0a50850814b49290daa379da8506d6d6d75ee0c11e129b44f780a0000745d84a7d60618793a3a9e0ce0c204a8c0951e3a2aa37c198951be5df940125f87f014da253c050000ba2e2666edefef2b04c1e85ee04a04a8c0a58d46f92eab0461dcf139eb9138c253689ff0140000e8ba98941513b360c41a2670250254e04a524a9bcd6d532508b30e51c7e1a9734da03dc2530000a0eb627d202665c1c88bd171640097264005ae230e5f3f510666f92145780aed139e0200005dd795095974c661f5e61832802b11a00257361ae5fb4825188b3139d30c5185a7d03ee1290000d075c253cef068b4760970250254e05a8cf2e56d3b3b3bc320b56dc253689ff0140000c8416ce68e75021831ba17b836012a300946f9f21bebebebd5c1c1416b7f7fe129b44f780a0000e4607b7b7b2a1bb9c9c66165742f30010254e0da8cf2e59cd7452bbb3f85a7d03ee12900009083084eb7b6b61482d38cee052642800a4c8451bebc2d02ce08512719740a4fa17dc25300002007b14610a37be114a37b818911a0029364942fbf717c7c3cb1c053780aed139e0200003988b5016b04bce5b032ba179820012a303146f9729649ec08159e42fb84a70000400e84a79cc3e85e60a204a8c04419e5cb59e24c92f5f5f52bfd7f85a7d03ee1290000908bd8a41d6b05708ad1bdc0c40950813618e5cbef44881ad765084fa17dc25300002017dbdbdb975e5ba0788795d1bd400b6efcf2cb2faa40bb2fb21b3714a187eabafeb2b9fd532578db45c39aeb74ad0293fd790400009835eb049ce373dda7fd24dba26d3a5081568c46f9aeaa046f8b0f3bef1bb5e34311b44f780a0000e422d61162742fbce5a9f014688b001568d372f5668c06fc468ce53d2f44159e42fb84a70000402e8e8f8f1defc359f6524adf2a03d016012ad09ae62126ce417da412bc2d3ef49cf5e147780aed139e020000b98875839492f094b35873045a2540055a351aa3f1422538eb43d0e91055780aed139e0200003989f0f47dc700d14b31ba774f198036dd70d02eadbfc86edc50849eabebfaa3e6f643737da21abc6d7e7e7e18e838cb04da253c05000072129bac63b335bc2546f7fe4519906dd136012aedbfc804a854c310f54ef526440560ca84a70000404e7676766cb4e62c715cd85f524a874a816c8bb619e10b4cc568acc6539500982ee12900009093e83a159e728ea7c253605a74a0d2fe8b4c072aa7d4751d5da8775402a07dc2530000202771dee9caca4af5faf56bc5e06d9b29a5af948131d9166dd3810a4c5b3ce89c280340bb84a70000404e84a7bc43ac253e5206609a04a8c0548dc66c18e50bd022e12900009093084dd7d6d684a79ce7514a494306305546f8d2fe8bcc085fce50d7f53f9bdb972a013059c25300002027119a46e76974a0c2195ea494969581b7c9b6689b0e54605662ec869d630013243c05000072139da7c253ce7158996407cc8800159889d1d80d6717004c88f0140000c8cdfafa7ab5bfbfaf109ce72ba37b815911a00233d33c006d36b7172a01703dc25300002037dbdbdbd5eeeeae42709ea729a53d650066e503250066fd30d45c9f35d71da500b89cb9b9b93853baba75eb9662000000d988e0746b6b4b2138cf20a5f4ad3200b3a403159829a37c01ae26c2d3c78f1f0b4f010080acc4c8de18dd0be7b056087482001598b9d1388e659500b89871783a3f3faf18000040368e8e8eaab5b53585e05d1ea5940e950198b51bbffcf28b2ad0ee8becc60d45e042eabafe77f5669c2f00e7109e020000398af0746565a57afdfab562709ed59492ee532e44b645db74a0025df255f5664c070067109e020000398ad05478ca7b985007748a0015e88cd179a85fa904c0ef094f0100801c094fb9a047a3b541804e10a0029dd23c280d9adb0b9500f81fe129000090a371781ae37be11d96534a7bca007489335069ff45e60c54aea0aeeb1f9adb1d9500fa4e780a0000e4eaf9f3e7d5c1c18142f02e9b292513e9b834d9166dd3810a7495f35081de139e020000b95a5f5f179ef23eb1f6f74819802e12a0029d94523aac1c1c0ff498f0140000c85584a7bbbbbb0ac1fb7ce5dc53a0ab04a84067350f50abcd6d552580be119e020000b9129e72414f534a036500ba4a800a745d74a13a441ee80de129000090ab084e85a75cc020a5f4ad32005d2640053a6d34c623ce4230ce03289ef0140000c85504a7d17d0aefe1dc53200b0254a0f3524ad181ea3c54a068c25300002057c2532ee1514ae9501980aebbf1cb2fbfa802edbec86edc500426a2aeebef9bdb4395004a233c05000072253ce5125ea49434493011b22ddaa60315c889f35081e2084f0100805cedefef0b4fb9a83de1299013012a900de7a102a5119e020000b93a3a3aaad6d6d614828b88b5bcaf9401c8890015c88af3508152084f0100805c4578bab2b252bd7efd5a31b808e79e02d971062aedbfc89c814a0b9c870ae44c780a0000e44a78ca2539f79456c8b6689b0e542057ce4305b2243c05000072253ce59206c25320570254204bce430572243c05000072253ce5929c7b0a644d800a64cb79a8404e84a7000040ae84a75cc157a30608802c095081ac350f62abcd6d5525802e139e020000b9129e72054f534a0365007276c341bbb4fe22bb714311685d5dd73f34b73b2a01748df0140000c895f0942b88734f3f5706da26dba26d3a508152c4990ac682009d223c05000072253ce50a0e2be79e0285d0814afb2f321da84c495dd79f35b77fab04d005c25300002057c253aee82f29a53d65601a645bb44d072a508cd1d90a4f55029835e1290000902be12957f448780a9444072a509cafbffefa9fcded4b95006641780a0000e44a78ca15adfee31fff78a40c404974a002258a07b6436500a64d780a0000e44a78ca1545d7e9b23200a5d1810a94f7c676e3469c877aa77a731eea472a024c83f0140000c895f0942b3aa9de9c7b7a2867004aa3031528d2e8cc05bbdf80a9109e020000b9129e720d71eee9a132002512a002c56a1ee0569bdb0b9500da243c05000072253ce51a9ea694369501289511be40796f6c376efce63fd775fd4373bba332c0a4094f0100805c094fb986cd94d257a7ff0b3903501a1da8401f7c5ebd399301606284a7000040ae84a75c431c9bf5481980d2e94005ca7b637bab0335d4751d1da83fa80e3009c25300002057c253ae211a143e4f29edbdfd3fc81980d2e840057a61f46067771c706dc25300002057c253aee9d159e129408974a002e5bdb19dd1813a56d7f5f7cdeda12a0157213c05000072253ce59a9ea694be3def7f943300a511a002e5bdb1bd23400d755dc728df3b2a055c86f0140000c8d5eeee6eb5bebeae105cd5664ae9ab77fd057206a03446f8027df479731d2a037051c25300002057c253aec9b158402fe94005ca7b637b4f076aa8eb3a3a50ffdd5c1fa918f02ec25300002057c253aee9a4b93ebfc8b9a77206a0340254a0bc37b60b04a8a1aeeb87cded7b1503ce233c05000072253c6502be4a296d5ee42f943300a511a002e5bdb15d30400d755d3f6f6e7f5535e06dc253000020571b1b1bd5cece8e42701dcb29a51717fd8be50c406904a840796f6c970850435dd7ff6c6e5faa1c30263c05000072155da7d17d0ad7b09a52bad4b9a77206a0347f5002802a1e08f7940108c25300002057c2532620d6c8969501e83b1da840796f6c97ec400d755ddf696eff6eae8f5410fa4b780a0000e4e8f5ebd7c3b1bdc253aee9a4b9fe94523ab9ecff51ce009446800a94f7c676850035d475fd59f52644057a48780a0000e428c2d3959595eae8e84831b88e084d3f4f295d694a9b9c01288d11be0023cd03e2a07a33ce17e819e12900009023e12913b47cd5f014a0443a5081f2ded8aed8813a56d7f5f7cdeda14a423f084f0100801c4568bab6b6263c65129ea694bebdcedf40ce009446800a94f7c676cd0035d4751da37c3f534d289bf0140000c85184a6d1791a1da8704dab29a56b4f64933300a531c217e06c5f3597b1255030e12900009023e12913146b5fcbca00f07b3a5081f2ded826d0811aeabafea4b9fdd05c1fa92a9445780a0000e4687777b75a5f5f570826e1a4b9fe94523a99c4df4cce009446072ac0399a07c8c3e6f6b94a407984a70000406e84a74c5084a69f4f2a3c0528910015e01d9a07c91865f24825a01c4b4b4bc2530000202b1b1b1bc253266979b4e605c0398cf005ca7b639bd008dfd3eabafeb6b9fd4d75216f119e2e2c2c28040000908d084ea3fb142624c2d31793fe9bca1980d2085081f2ded85a0850435dd7df37b7872a0c79129e0200003979fdfa754cc6aa0e0e0e148349596d5e53ad4c5a933300a511a002e5bdb1b514a086baae7f686e775419f2223c0500007212e1e9caca4a757474a4184cca5e4ae92f6dfdcde50c40693e5002804bf9bcb9fe5d0951211bc25300002027119a46781a212a4c489c77fab932005c9c0e54a0bc37b6163b50435dd7119e4688fa916a43b7094f0100809cc4b8de18db2b3c65824e9aebf3e675b5d7e63f44ce009446800a94f7c6d672801a4621ea0faa0ddd253c05000072b2bbbb5badafaf2b04933495f034c81980d2085081f2ded8a610a086baae1f36b7ef551cba47780a0000e4647b7bbbdadada520826ed514a69751aff203903501a012a50de1bdb9402d450d7f55f9bdb735587ee109e0200003989aed3e83e85095b4e29bd98d63f4cce009446800a94f7c636c50035d4751d5da80f551e664f780a0000e422ce395d5959a98e8e8e1483495b4d293d9ae63f50ce009446800a94f7c636e50035d475fdcfe6f6a5eac3ec084f0100805c4468bab6b6263ca50d9b29a5afa6fd0f953300a5f9400900262276f57dd25c779402a64f780a0000e42242d3e83c8d0e5498b0bdeacd1a1500d7a4031528ef8d6d061da8a1aeeb8f9adbbf2b212a4cd5e2e26275f7ee5d850000003a2fce3a8d334fa10587cdf59794d2c92cfee17206a0340254a0bc37b61905a8a1aeeb084f2344fdc87702da175da7d17d0a0000d0751b1b1bd5cece8e42d086084d3f4f29edcdea0b903300a511a002e5bdb1cd30400d4254980ee1290000908318d51be79deeefef2b066d9879781ae40c406904a840796f6c330e50435dd75f36b77ffa6e403b84a70000400e8e8f8fab94d2f0dc5368c9a3e635b63aeb2f42ce009446800a94f7c6d6810035d475fdb0b97def3b0293253c0500007210a1e9cacacab003155ad289f034c81980d2085081f2ded83a12a086baaebf6d6e7ff35d81c9109e02000039d8ddddadd6d7d7158236bd48292d77e58b913300a511a002e5bdb17528400d755d4717ea43df19b81ee129000090838d8d8d6a6767472168d36a4ae95197be203903501a012a50de1b5bc702d4204485eb119e0200005d17a37ae3bcd3838303c5a04d9bcdebecabae7d517206a0347f500280f68d7605eea9045c9ef0140000e8baf179a7c2535a166b4b8f9401a07d1f2801c0d47cde5cff6eae3b4a0117333f3f5f2d2e2e2a040000d059fbfbfbd5dadadab003155a14e1e9e729a513a500689f11be40796f6c1d1ce13b56d7f5479510152e24c2d3c78f1f577373738a01000074d2f6f676b5b5b5a510b42d42d33f75393c953300a511a002e5bdb17538400d755d7fd2dc7e68ae8f7cb7e06cc2530000a0cba2db34ba4ea3fb145a16a169749e76fa68283903501a012a50de1b5bc703d450d77574a04627aa1015de223c050000ba2cce3b8df034eed0b22cc2d32067004a234005ca7b63cb20400d4254f83de1290000d065ce3b65ca223c1de4f085ca1980d2085081f2ded8320950435dd79f556f4254e83de1290000d065ce3b65ca1ea5945673f962e50c406904a840796f6c1905a8a1aeeb87cded7bdf39faece6cd9bd593274f84a7000040e738ef9419c82a3c0d7206a0340254a0bc37b6cc02d42044a5cf22348dced3e840050000e812e79d3203d985a741ce009446800a94f7c69661801a84a8f491f0140000e8aadddddd6a6363c379a74cd36a4ae9518e5fb89c01288d001528ef8d2dd30035d475fd6d73fb9bef227d203c050000ba2a82d39d9d1d85609ab20d4f839c01288d001528ef8d2de30035d4751d5da80f7d272999f0140000e8a2e3e3e32aa564642fd39675781ae40c406904a840796f6c9907a841884ac984a7000040171d1c1c0cc353237b99b2ecc3d32067004af3072500e89ed183f3aa4a50a2070f1e084f0100804ed9dedeae9e3f7f2e3c65daf69a6b591900ba47072a50de1b5b011da8633a5129cdd2d252b5b0b0a0100000402744601a5da7d17d0a5316e1e9e7cdebefa4847f193903501a012a50de1b5b41016aa8ebfa9fcded4bdf5972273c050000ba24ce395d5959d175ca2c14159e063903509a0f9400a0f3629cef27cd754729c895f0140000e8929d9d9d6a636343219885e2c2538012e94005ca7b632bac0335d475fd5173fb7725442543c2530000a02ba2db746d6daddadfdf570c66a1d8f054ce009446800a94f7c65660801a84a8e42882d30850010000662dce395d5f5faf8e8f8f15835988d0f44fa5769eca1980d2085081f2ded80a0d508310959c084f010080aed8dedeaeb6b6b614825989d0343a4ff74afd17943300a511a002e5bdb1151ca006212a39109e0200005d10237b534ac3ee539891e2c3d32067004a234005ca7b632b3c400d4254ba4c780a0000744184a6119e46880a33d28bf034c81980d208500132f5f5d75f0b51e99c5bb76e55cbcbcb0a010000ccd4c6c646b5b3b3a310cc526fc2d32067004af3072500c853f3003e7c106fae43d5a02b6edebca9080000c0cc1c1f1f57cf9e3d139e326bbd0a4f014aa4031520d737f0d1a8e2baaea303353a513f5215ba607e7e3e5e97c254000060aa767777879da746f63263bd0c4fe50c406904a800b9be819f3aeb55884ad7cccdcd550f1e3ca86edfbead18000040ab22308de034025498b1de769eca1980d2085001727d033f15a006212a5d74f7eedd6a71715121000080561c1d1dc51137c3d1bd3063bd1edb2b67004a234005c8f50dfcad00350851e922237d010080366c6f6f575b5b5b0af1ffdabb63e72a8e74e1c373436f72a94bca562931290a4db43284b8eac3c1a646649d19fe02db7f8171b49359903a305b6587b0dac80e452a27e7d692b2a59b2ce937ef991e90650152eb9ca3e999e7a99a3ac2de05bbe5ea52f5afde1ec660f6ef3cd51980a91150016addc04f09a84144658c5ce90b0000ac4a4c9b3e79f2a4393c3cb4188cc1ece369d01980a91150016addc0df11508388ca58c595be9f7df6d932a80200009c57bce734de771aef3d8511104f339d01981a0115a0d60dfc3d013588a88c555ce91bd3a8f10900007016114c1f3f7edcbc78f1c2623016e2e9313a033035022a40ad1bf807026a105119ab98408d49d49848050000789fb8aab76d5b53a78c89787a82ce004c8d800a50eb067e86801a4454c62cde891ad3a8aef40500004e8a60fad34f3f35cf9f3fb7188c89787a0a9d01981a0115a0d60dfc8c013588a88cd9d5ab579b2fbef8a2b97efdbac5000000965ebe7cb99c3a7df5ea95c5604cc4d377d01980a91150016addc0cf11508388cad8c595be77eedcb110000030733ffffcf372f21446463c7d0f9d01981a0115a0d60dfc9c013588a88cddb56bd7e2bfd3e5542a0000302f3175faf8f1e3e5278c8c78fa013a033035022a40ad1b7841400d222a6317ef43fdeb5fffdadcbc79d3620000c04c983a65c4c4d333d01980a91150016addc00b036ac811f5c7eed9b2928cd58d1b379a7bf7ee2d832a0000304da64e1939f1f48c7406606a0454805a37f00b04d490528a09d49844ddb69a8c55c4d3b8d2f7faf5eb1603000026e6f9f3e7cd0f3ffc6021182bf1f41c7406606a0454805a37f00b06d420a2528b5bb76e359f7df69969540000988057af5e354f9e3c690e0f0f2d066315d1f4f3b66d1796e26c7406606a0454805a37f01504d420a2528bab57af2ea751af5dbb66310000a052de754a05229ec6e4e991a5383b9d01981a0115a0d60d7c45013588a8d4242651efdcb963210000a022de754a25c4d3423a033035022a40ad1bf80a036a1051a9494ca1debb77cf342a000054c0d42995104f2f406700a6464005a875035f71400d222ab5318d0a0000e365ea948a88a717a433005323a002d4ba81af21a0861c51bfed9e5dab4c0d4ca30200c0b8bc7efdba79fefcb9a9536af1b47bee8ba717a333005323a002d4ba81af29a00e524adf37222a15318d0a000097eff0f0b079f2e449f3ead52b8b410df6dab6bd6f192e4e6700a6464005a875035f73400d222ab5318d0a00009723a64e63e234264fa112e2e90ae90cc0d408a800b56ee01b08a84144a546a651010060735ebc78b17cd7694454a88478ba623a033035022a40ad1bf886026a48293d68faf7a242354ca30200c07ac535bd3ffcf0c332a042451eb66dfbc832ac96ce004c8d800a50eb06bec1801a524abbddc7f7569eda9846050080d58bab7ae3ca5e53a754e67edbb67b9661f57406606a0454805a37f00d07d420a252ab9842fdeb5fffda5cbf7edd620000c005bc7cf972795d6f7c4265c4d335d21980a91150016addc02f21a08694d2dda68fa8577c17a8cdad5bb79613a91f7df491c5000080738849d398388dc953a8cc51f77cdeb6edbea5581f9d01981a0115a0d60dfc92026a48296d771fff6844542a74f5ead5e534ea8d1b372c0600009c41bce334de751aef3c85ca443cfdb46ddb034bb15e3a033035022a40ad1bf82506d420a252bb08a8f7eedd338d0a0000ef10c1f4c99327cde1e1a1c5a046cb78fab7bffd4d3c05e0dc0454805a37f04b0ea82147d4b8ce77db77841a453c8d2b7de36a5f0000e0ad9f7ffeb979f6ecd9f2ea5ea85044d3983c3d72fe0d40090115a0d60d7c040135a49462023526514554aa75fdfaf5e5b5bed7ae5db3180000cc5a4c9bc6d4a9eb7aa9d89b781abf70fe0d40090115a0d60d7c24013588a84cc5308dea5a5f0000e6268269bce734de770a157bda3df787781a9c7f0350424005a875031f51401da494e23adf5ddf1d6a76f5ead5e68b2fbe584ea50200c01cb8ae9789d86bdbf6fec9bfe8fc1b8012022a40ad1bf808036a1051998a1b376e2caff58da00a000053e4ba5e26e451dbb60f4ffb1bcebf012821a002d4ba818f34a08694d283eee35bdf256a1757f9debe7dbbb973e78ec5000060325cd7cbc4c495bd7beffa9bcebf012821a002d4ba818f38a08694d26ef7f1bdef1453e05a5f0000a620aee87dfefc79f3d34f3f590ca620de73faf07df13438ff06a084800a50eb063ef2801a524a3bddc78fdd73c5778c2970ad2f0000b5fae5975f96ef3a755d2f1311f1f4d3b66d0f3ef43f74fe0d40090115a0d60dbc82801a524adbddc73f1a11958970ad2f00003579f9f2e5f2bade78df294cc4a27b3e3f4b3c0dcebf012821a002d4ba81571250434a69abe92751b77de7980ad7fa02003066715d6f84d3983c850989681a93a74767fd3f38ff06a084800a50eb065e51400d29a598408d495411954971ad2f0000631357f53e7bf66c195161429e76cffdf3c4d3e0fc1b8012022a40ad1b786501759052fabefbd8f51d646a3efbecb3e6d6ad5bcb2b7e0100e032bc78f1623975ea3da74cd05edbb6f74bfe8fcebf012821a002d4ba81571a50434ae9dbeee381ef225313f134a6516fdebc69310000d89878bf694c9d7acf2913f5b06ddb47a5ff67e7df00941050016addc02b0ea821a5b4db7d7cef3bc914c57b51efdcb9e3fda80000ac554c9a4638f59e53262aaeea8d78ba7791dfc4f93700250454805a37f0ca036a4829ed741f3f76cf15df51a628265123a47a3f2a0000ab14ef367dfefcb9f79c3265114f3f6ddbf6e0a2bf91f36f004a08a800b56ee01308a821a5b4dd7dfca3115199a8b8d6f7f6eddbde8f0a00c04ac4b469bce7543865c2229a7edeb6ed6215bf99f36f004a08a800b56ee01309a821a514f13422eab6ef2c53e5fda800005cc48b172f96e134aeed8509db6ffa787ab4aadfd0f93700250454805a37f00905d490236abc13f5aeef2e5316d7f97ef1c517de8f0a00c0991c1e1e2edf731a9f30717b6ddbde5ff56feafc1b8012022a40ad1bf8c402ea20a5f46df7f1c07798a98b801a13a9d7ae5db3180000fc414c9ac6c4694c9ec20c3c6cdbf6d13a7e63e7df00941050016addc0271a50434a69b7e9a75161f2e24adf3b77ee2c2753010020c2694c9cc6bb4e6106e2aade88a77bebfa039c7f0350424005a875039f70400d29a59deee3c7eeb9e2bbcd1c7cf6d967cdad5bb796ef4a0500607e5ebf7edd3c7ffebc79f6ecd9f26b988188a79fb66d7bb0ce3fc4f93700250454805a37f08907d49052da6efa49d46ddf71e620e2e9eddbb78554008019114e99a988a6114f8fd6fd0739ff06a084800a50eb063e83801a524a31811a93a83bbeebcc455ce71bd7fac6f5be00004c575cd31bef39154e9999bda6bfb6f768137f98f36f004a08a800b56ee03309a88394524ca2eeface3327422a00c03445388df79cc6fb4e6166be69dbf6eb4dfe81cebf012821a002d4ba81cf2ca08694d26ed35fe90bb372fdfaf565488d4f0000ea259c3263316d1a53a77b9bfe839d7f0350424005a875039f61400d29a59da6bfd2f78aff0a981b211500a04ec2293317f134de777a70197fb8f36f004a08a800b56ee0330da821a5b4ddf493a8dbfe4b608e845400803a1c1e1e2ec3697cc24c4534fd7453ef3b3d8df36f004a08a800b56ee0330ea821a51413a81151effaaf81b98a80fac5175f2cdf950a00c07808a7b0b4d7b6edfdcbfe8770fe0d40090115a0d60d7ce601759052fab6fb78602598b39b376f2e275285540080cb259cc21bf1bed34763f80771fe0d40090115a0d60d5c407d23a5b4db7d4448f55e54664d480500b81cc229bc1157f57edeb6edfe58fe819c7f0350424005a8750317507f27bf17f5c7eed9b21acc9d900a00b019c229fc4ebcef34e2e9624cff50cebf012821a002d4ba810ba87f90df8b1a1175c76a80900a00b02ebffcf24bf3fcf9f3e6e5cb9716037a7b4d7f6defd1d8fec19c7f0350424005a875031750dfc97b51e1f722a47ef2c927cdf5ebd72d0600c00544388d89d357af5e590c786b34ef3b3d8df36f004a08a800b56ee002ea7b792f2afc5104d4984815520100ce473885538dee7da7a771fe0d40090115a0d60d5c40fd20ef4585d309a900001ff6faf5ebe6e0e0403885d38df27da7a771fe0d40090115a0d60d5c403d13ef45857713520100fe28c269bcdff4d9b367cbaf813fd86b46fabed3d338ff06a084800a50eb062ea09e8bf7a2c2bb5dbd7a751952e35da90000731553a6bffefaab700aef37eaf79d9ec6f93700250454805a377001f5dc524a77bb8fef1bef4585530d21757b7bbbf9e8a38f2c0800300b114ee39ade78cf29f04e316dfa69dbb607b5fd833bff06a084800a50eb062ea016c9ef458d88ba6d35e074114f6fdfbedddcba754b48050026ebf0f07039712a9cc207ed37fdfb4e8f6afc8777fe0d40090115a0d60d5c402d96df8b1a11f5aed580778b781ad3a831951ad3a9000053f0e2c58be53b4e23a0021ff4a86ddb8735ff0b38ff06a084800a50eb062ea05e584a29de89faad95800f8bf7a3c644eab56bd72c060050a598348dab7ae3ca5ee08362daf47edbb64f6bff1771fe0d40090115a0d60d5c405d8994d24ef7f163e3bda87026d7af5f5f86d41b376e580c0060f45ebf7ebd9c367df6ecd9f26be04ce23da7f76b7cdfe9699c7f0350424005a87503175057265fe91b1175c76ac0d9c495be71b56f5cf1eb3da900c0d8c494694c9b7abf299cdb5ef73cacf57da7a771fe0d40090115a0d60d5c405db994d2d7ddc7575602ce2ee2e9eddbb79b4f3ef9c47b5201804be7fda670213175ba37b57f29e7df00941050016addc005d4b54829dded3ebe6f5ce90be716ef498d901ad7fc02006c4a5ccd7b7070e0fda6506ed13d9f4fe5cade939c7f0350424005a875031750d726a5b4d5f457fa6e5b0d38bf08a8115223a80200ac4bc4d298368d6b7abddf148a3d6dfac9d3a3a9fe0b3aff06a084800a50eb062ea0ae5d4ae9dbeee38195803271a5ef3095ea7a5f006055e27ade08a7715d2f7021f1aed34753ff9774fe0d40090115a0d60d5c40dd0857fac26ab8de1700b808d7f4c24a2d9a095fd97b92f36f004a08a800b56ee002eac6b8d21756e7dab56bcdad5bb79aededede6a38f3eb22000c07b452c8d681af1d435bdb01293bfb2f724e7df00941050016addc005d48d73a52fac4ec4d3984a8d98ea7a5f00e0a478afe9afbffebabcae175899595cd97b92f36f004a08a800b56ee002eaa570a52fac5e5ceb1bd7fb46500500e62ba64d239a3e7bf6ccb429ac565cd57b7f2e57f69ee4fc1b8012022a40ad1bb8807a69f295be115177ac06ac8ea9540098a7172f5e2c274ee31358b9bda69f3c3d9aeb0238ff06a084800a50eb062ea05eba94d2d7ddc757560256cf542a004cdb306d1ae134be06562e826984d3bdb92f84f36f004a08a800b56ee002ea28a49476ba8f1f1b57fac25a984a058069316d0a1b1157f57edeb6edc25208a800941150016addc005d4d14829453c8d2b7def5a0d589f612a757b7b7b195601803a9836858d7ad4b6ed43cbf096f36f004a08a800b56ee002eae8a4941e741fdf5a0958af88a71151632af5dab56b160400462a826984d3c3c3438b01eb1757f6c6d4e9bea5f83de7df00941050016addc005d4514a296d37fd34eab6d580f58b6b7d23a4deb871c315bf0030022f5fbe6c9e3f7fde1c1c1c34af5fbfb620b0194fbbe77edbb64796e28f9c7f0350424005a875031750472b5fe9fb55f73cb01ab0391151e38977a602009b13a134a64d239cbaa217362a82e9376ddb3eb214efe6fc1b8012022a40ad1bb8803a7a29a578276a4ca35eb11ab039aef80580cd8868fae2c58be5036cdc41d34f9d1e588af773fe0d40090115a0d60d5c40ad429e46fdb17b76ac066c5e5ceb1b13a99f7cf2892b7e0160055cd10ba3f0a86ddb8796e16c9c7f0350424005a875031750ab92528aeb7cbfb5127079621a35a652633a35a6540180b3896b797ffdf5d7e5c4a92b7ae152c595bd9fb76dbb6f29cecef93700250454805a377001b53a29a5eda6bfd277db6ac0e5f2be540078bf982e8d29d398368da953e0d23d6dfa2b7b8f2cc5f938ff06a084800a50eb062ea0562ba51493a80fac045cbee17da943500580391ba2a9f79ac2a844307dd8b6ed9ea528e3fc1b8012022a40ad1bb8805ab594d24ed34fa36e590d1807311580b91a82a9f79ac2e8ec37fdd4e9c2529473fe0d40090115a0d60d5c40ad5e4ae94ad347d4bb5603c625626a5cef1b4fbc3b1500a6463485d18ba9d34796e1e29c7f0350424005a8750317502723a514013542ea15ab01e373f5ead537ef4b155301a859bccb34de692a9ac2a81d34fdd4e981a5580de7df00941050016addc005d449c9d3a83f76cf8ed580f11a62eaf5ebd75df30b4015229afef2cb2fcb69d357af5e591018b76fdab6fdda32ac96f36f004a08a800b56ee002ea24a5941e741f5f35a65161f4bc331580b11aaee73d3c3c144da10ea64ed7c8f93700250454805a37700175b2524a5b4d7fa5ef8ed5803a1c8fa9319d1abf06804df24e53a856bce734264f8f2cc57a38ff06a084800a50eb062ea04e9e6954a8d730951a51554c05601d2292c684a9680ad55a34fdd4e9bea5582fe7df00941050016addc005d45948296d37fd34eab6d5803a5dbb76adb979f3e6723235be068052114923960ed3a640b54c9d6e90f36f004a08a800b56ee002eaaca494be6efa6954a06257af5e7d339d1a4115003e24de611ab1f4975f7e695ebe7c6941a06e8bc6d4e9c639ff06a084800a50eb062ea0ce8e69549896b8da3722aaab7e013869b89a379e08a8c024983abd24cebf012821a002d4ba810ba8b3651a15a629aef78d901a41d555bf00f3325ccdfbdb6fbf799f294ccfa231757aa99c7f0350424005a87503175067cd342a4c5b4ca3464cfdf8e38f4da7024c545cc73b4c9ac6273049a64e47c0f93700250454805a37700195e6cd34ea97dd73c56ac0740dd3a91154bd3b15a04e31557a3c98ba9a1726eda0e9a74e0f2cc5e573fe0d40090115a0d60d5c40254b296d35fd34ea8ed580e91bde9d3abc3ff5ead5ab160560a462ca7408a6a64c613662e2f46bcb301ecebf012821a002d4ba810ba89c90527ad0f4ef46358d0a3312013562aaeb7e012e5f4c954628f52e53982553a723e5fc1b8012022a40ad1bb880ca294ca30271ddef30a11a8fa00ab03ec3b5bcc31313a7c0ecc4fb4d63eaf491a51827e7df00941050016addc00554de23a574b7e943aa69549839411560b5860953d7f2029dfda69f3a5d588af172fe0d40090115a0d60d5c40e503524a114f23a2deb51ac0405005381fc1143885a9d38a38ff06a084800a50eb062ea0724629a59da60fa95b560338e978508dafe39daa00732698021ff0b4e9a74e8f2c451d9c7f0350424005a87503175039873c8dfa55f73cb01ac0fb44408d98faf1c71f377ffef39f97511560ca0453e08c16ddf3b06ddba796a22ecebf012821a002d4ba810ba81448296d37fd34eab6d500ce22aef88da01a3135a26a7c0d50abd7af5fbf09a52f5fbe144c81b38aab7abf31755a27e7df00941050016addc005542e20a5f475f7f165f75cb11ac0790dd7fe0e9faefd05c62a22e9bffef5afe584697cc6af01cee1a0e9a74ef72d45bd9c7f0350424005a875031750b9a094d256d34fa3ee580de022624a3526548f5ffd1b7f0d609362ba7488a531591a5fc75f03281093a6dfb56dfbb5a5a89ff36f004a08a800b56ee0022a2b9252badbf421d5342ab03231951a13aac3d5bfa22ab06ac315bcc394a9e9526045f6bbe77edbb60b4b310dcebf012821a002d4ba810ba8ac504a29e2e957ddf3c06a00eb22aa02a58e4752ef2e05d624a64e239c3eb514d3e2fc1b8012022a40ad1bb880ca1aa494b69b7e1a75db6a009b703caac6673cdea90af326960297e051f77cd3b6ed91a5981ee7df00941050016addc00554d628a51493a83191ea5a5f60e38677aa1e0faaf17e55607a228ebe7af54a2c052ecb41d34f9d1e588ae972fe0d40090115a0d60d5c4065cdf2b5bedf76cfaed500c6e0e4b4eaf06b60fc5ebf7ebd9c2afdedb7df96c1d43b4b814b1693a63171fac8524c9ff36f004a08a800b56ee0022a1b9252da69fa90ea5a5f609486982aacc2389c9c2a8d581a01156024f6bae7a1eb7ae7c3f93700250454805a377001950d73ad2f509b08a9c3f5bffff33ffff326b2c615c1c0c544208d27264afff39fff08a5400de29ade08a7fb96625e9c7f0350424005a875031750b90429a5ada68fa8bb5603a85944d5e3ef5a1dbe1657e1ade1dadd08a5fffef7bf9793a5432c05a888eb7a67cef93700250454805a377001954be45a5f60ca8ec7d561727578606a22860e513482a9480a4ccc5ee3baded973fe0d40090115a0d60d5c4065045ceb0bcccdf1981a8fc0cad89d0ca4c335bbaedb0526ce75bdbce1fc1b8012022a40ad1bb880ca48a494229ec634eaaed500e66e985c0d31c91a862b824556566d88a3c315bbc7274763921460865cd7cb1f38ff06a084800a50eb062ea03232f95adf9846ddb11a00ef37c4d5e351f5e38f3f5e7efee94f7f5a4657e66b889f31213a04d16162d4f5ba00ef14d1f41bd7f57292f36f004a08a800b56ee0022a239552da6dfa90ba6535002ee678603dfe754cb41e8fac4390659c8e4f830e57e986e3d7e8ba5217a0d87ed35fd77b6029388df36f004a08a800b56ee0022a2396aff51dde8f0ac0069dbc2a38426b4cb50e86f7b61e17d70e4794e58f86f7851e773c8286b842379e81180ab0118ba69f38ddb314bc8ff36f004a08a800b56ee0022a1548296d35fdfb51ef5a0d80babcef9dad27a3ec694e0bb5eb76326c9ee67d57e00a9f0055882b7abfeb9e47aeebe52c9c7f0350424005a875031750a9487e3f6a84d46dab01000014da6bfaa9d385a5e0ac9c7f0350424005a875031750a9507e3f6a84d42b5603000038a3fda60fa7fb9682f372fe0d40090115a0d60d5c40a552de8f0a00009cd1a2f19e532ec8f93700250454805a37700195cae5f7a34644ddb51a0000c031de73caca38ff06a084800a50eb062ea03211f9fda8115277ac060000ccde5ef73c144e5915e7df00941050016addc005542626a574b7e9df8fba653500006076f6bbe77edbb60b4bc12a39ff06a084800a50eb062ea0325129a5e1fda857ac0600004cde41d34f9cee5b0ad6c1f93700250454805a37700195094b29453c8d90fa6523a40200c0142dbae79bb66df72c05ebe4fc1b8012022a40ad1bb880ca0ca494b69a7e1a75d76a0000c024c4bb4dbfeb9e47de73ca2638ff06a084800a50eb062ea0322339a47edf3d3b56030000aaf5a8e9a74e855336c6f93700250454805a37700195194a29ed34fd44ea8ed50000806aec357d385d580a36cdf93700250454805a37700195194b29dded3ebeed9e2dab010000a3b5df3d0fdbb63db0145c16e7df00941050016addc005548890badbf413a95b560300004663bfe9274ef72d0597cdf93700250454805a37700115de48293d68fa907ac56a0000c0a55974cf7de1943171fe0d40090115a0d60d5c4085df4929453c8d90fa6523a40200c0262d9a7ee274cf523036cebf012821a002d4ba810ba8702a211500003666d108a78c9cf36f004a08a800b56ee0022abc570ea971adef03ab0100002b75d43ddfb56dfbb5a560ec9c7f0350424005a875031750e14c524a5b4d1f5277ad0600005cc8329c76cfa3b66d8f2c073570fe0d40090115a0d60d5c40857311520100a098704ab59c7f0350424005a875031750a188900a000067269c523de7df00941050016addc00554b81021150000de493865329c7f0350424005a87503175061258454000078433865729c7f0350424005a87503175061a584540000664c3865b29c7f0350424005a875031750612d8454000066443865f29c7f0350424005a87503175061ad84540000264c3865369c7f0350424005a87503175061238454000026443865769c7f0350424005a87503175061a3524a57ba8f07ddf365f75cb122000054443865b69c7f0350424005a875031750e15208a900005464d13ddfb46dbb6729982be7df00941050016addc00554b854c742eabdeed9b22200008cc8a2114e61c9f93700250454805a377001154623a5b4dbf4ef49ddb21a00005ca2fdee792c9cc25bcebf012821a002d4ba810ba8303a39a4c6d5bedb560300800dda6ffa89d37d4b01bfe7fc1b8012022a40ad1bb8800aa39552da69fa89d41dab0100c01aed75cf776ddb1e580a389df36f004a08a800b56ee0022a8c5e4a292651632275d76a0000b0427b4d3f71bab014f07ecebf012821a002d4ba810ba8508d94d256d34fa4deed9e2b560400800247ddf35df73c6adbf6c872c0d938ff06a084800a50eb062ea04275524a114f1f34fd54aa900a00c0592cbae79bee792a9cc2f939ff06a084800a50eb062ea042d5524abb4d3f95ba6535000038c57ef73c6edb76cf524039e7df00941050016addc00554988494d24ed387d41dab010040d3bfdf34c2e9bea5808b73fe0d40090115a0d60d5c40854939f69ed45dab0100303b7135ef5ef77cd7b6edc272c0ea38ff06a084800a50eb062ea0c2241d7b4feabdc6f5be000053b7e89eefba67cffb4d613d9c7f0350424005a87503175061f2f27b52bfec9e6dab01003029fb4d3f6dfad452c07a39ff06a084800a50eb062ea0c26ce4f7a4c644eaaed50000a8da5ed387d3034b019be1fc1b8012022a40ad1bb8800ab393df93badbf453a957ac0800401516ddf3b87b1eb9a61736cff93700250454805a3770011566cdf5be0000a3b7df3d8fdbb6ddb31470799c7f0350424005a8750317508166195223a04648ddb51a0000972e264ce3bda6dfb46dbbb01c70f99c7f0350424005a8750317508163524a71a5ef6ed3c7d42d2b0200b0518beef9ae7bf65cd30be3e2fc1b8012022a40ad1bb8800abc434ae96ef771af7bee5a0d0080b5da6bfa6b7af72d058c93f36f004a08a800b56ee0022af00129a5ade6ed54ea152b0200b0128bee79dcf4d3a60bcb01e3e6fc1b8012022a40ad1bb8800a9c434a69b7e9a75277ac0600409178b7694c9b3eb514500fe7df00941050016addc00554a0409e4a8d89d4ddc6542a00c0872c1ad3a65035e7df00941050016addc00554e0824ca50200bc9369539808e7df00941050016addc0055460458ebd2b3562ea96150100666ad1983685c971fe0d40090115a0d60d5c4005d620a574b7e943ea5dab0100ccc45ef7fcddb4294c93f36f004a08a800b56ee0022ab04629a5783fea6ed3bf2f75cb8a000013b3e89eef9a7edaf4c872c07439ff06a084800a50eb062ea0021b9252da6efa901a53a957ac080050a908a53165fa5ddbb6079603e6c1f93700250454805a37700115d8b03c953a5cf1bb634500804a44348d2b7af72c05cc8ff36f004a08a800b56ee0022a7089524a5b4d1f535df10b008cd1a2e9afe87ddab6edc272c07c39ff06a084800a50eb062ea00223e18a5f0060245cd10bfc81f36f004a08a800b56ee0022a304229a5e18adfbb560300d89088a68fdbb67d6a2980939c7f0350424005a87503175081113bf6bed4984cddb62200c08ac584e97045ef91e500dec5f93700250454805a37700115a884f7a502002bb268bcd7143827e7df00941050016addc00554a042f97da9c315bf5b560400f88045f3f68a5eef3505cecdf93700250454805a37700115a85c8ea931951a31f58a150100b2b8927788a6fb9603b808e7df00941050016addc005546042524a1151ff5f23a602c05c0dd1f4ef6ddb3eb51cc0aa38ff06a084800a50eb062ea0021325a602c06c88a6c0da39ff06a084800a50eb062ea0023320a602c0e488a6c04639ff06a084800a50eb062ea0023323a60240b54453e0d238ff06a084800a50eb062ea0023326a602c0e889a6c02838ff06a084800a50eb062ea0022ce598fa97a68fa95b5604002e8d680a8c8ef36f004a08a800b56ee0022ac01fa494b6bb8f7b8d980a009bb268de46d37dcb018c8df36f004a08a800b56ee0022ac07be598bad3f44175db8a00c0ca1c74cfdfbbe769dbb60796031833e7df00941050016addc0055480334b296d357d4c1dde9b0a009c4f4c99feb3e9a3e9c27200b570fe0d40090115a0d60d5c4005289252bad2fc3ea65eb12a00f007c3fb4c87687a6449801a39ff06a084800a50eb062ea002acc4b1f7a6ee34aefa0560de168df7990213e3fc1b8012022a40ad1bb8800ab07227aefa8d4fd3a9004c9dab79814973fe0d40090115a0d60d5c400558bb94525cf1fb97a6bfea77cb8a0030018beed9ef9ebfc7a7ab7981a973fe0d40090115a0d60d5c4005d828d3a900546cbf791b4c0f2c073027cebf012821a002d4ba810ba800972aa5b4d3bc8da9de9d0ac0982c9a7c356fdbb64f2d073067cebf012821a002d4ba810ba800a391528a69d4e1badf9dc675bf006c565cc3bbdfbc9d325d5812809ef36f004a08a800b56ee0022ac068a594622275a7791b545df70bc0aaed77cf3fbbe7a96b7901decdf93700250454805a37700115a01af9badf7886a00a00e7159174bf7bfedeb6edbee500381be7df00941050010060933f80ffd77f09aa009cc5104c63ca34aee53db22400e7e7fc1b8012022a00006cf207f0536e1010540168045380b570fe0d40090115000036f903f819ae6017540166413005d800e7df00941050010060933f8017bcc3fa4450ddee9e2b5612a03a8229c02570fe0d40090115000036f9037841403d29a5141175a7791b54b7ac2cc0a8441c8d603ac4d27d4b0270399c7f035042400500804dfe00be82807a524a69abe983ea8dfcb96da501366ad1f4d3a52f9a3e981e5812807170fe0d40090115000036f903f81a02ea698e5dfb3b4455d7fe02acce7ef376c2f4a06ddb8525011827e7df00941050010060933f806f28a09e94a754633275b8f677c77703e04c168de952806a39ff06a084800a00009bfc01fc9202ea698ebd4b35a654b71b57ff022c9a7eb274194b9b7eba34de67ea001e00006644400500804dfe003ea2807a9a7cf56f8454511598ba08a36faee16d3e7015aff3130000980f0115000036f903f8c803ea694455600216f939532c3d8df3130000980f0115000036f903788501f534f9fadf9351f58aef303002114817cd29d7f05e84f3130000980f0115000036f903f84402ea69524a5bdd473c3b4d1f56e36bd3aac0ba0c57f00eef2c5db46dbbbfae3fccf9090000cc87800a00009bfc017cc201f55df215c05bf9f9cbb1af01ce6abfe9a74aff77f8fabc57f05e94f3130000980f0115000036f903f80c03eabb08abc029f69bb7a1b4e85da5ebe2fc040000e643400500804dfe002ea07e500eabf13ed5e11dabf1f58e9581c9387ef5eeff359734517a5ece4f0000603e04540000d8e40fe0026ab163ef588db01a51f52fcddbd00a8ccf7ed3c7d2e5fb499b35bfa374dd9c9f0000c07c08a80000b0c91fc005d4b578475c0d3b5607d66abf3911499bfedadda3a9fd8b3a3f010080f91050010060933f800ba81b97521aa65487cfff3ef16be0dde29adde1caddff1b7e5df3246929e7270000301f022a00006cf207700175744e09ac6198601dfe3a4cd11046c33ff3e79b603ac529d28b707e020000f321a00200c0267f001750ab74ec8ae0e391f546feb54956c668919f70328ec604e981253a1fe7270000301f022a00006cf207700175d2524ac7275677f2e7706570d8ca0f94dacf9fc37b47c3223fcd1cafd6dd14e7270000301f022a00006cf207700195ecd8d5c1e1e414eb30dd7adadf633a8689d0a6f97d100dfbc317a2e838383f010080f91050010060933f800ba85cd089f03ad839f1ebff6e4e8faedee9ba3afba7fcb57f9ef8f5a2797b8deef2d76ddb2e2c5d9d9c9f0000c07c08a80000b0c91fc00554462aa5b47386ffd956b39e2b88ffb2e2df6fd13dff7b8efffdfe7bfede41dbb647fe0bc1f9090000cc87800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a0000000000009009a80000000000000099800a00000000000090fd7ffdac50205345a0a40000000049454e44ae426082, '08131231212', '');

-- --------------------------------------------------------

--
-- Table structure for table `responsibilty`
--

CREATE TABLE `responsibilty` (
  `id` int(11) NOT NULL,
  `experienceId` int(11) NOT NULL,
  `Duty` varchar(1000) COLLATE latin1_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

--
-- Dumping data for table `responsibilty`
--

INSERT INTO `responsibilty` (`id`, `experienceId`, `Duty`) VALUES
(13, 12, 'REpair'),
(14, 12, 'Overhaul'),
(15, 13, 'Manage Projects'),
(16, 14, 'Repair Of Alternator'),
(17, 14, 'Installation Of Gensets'),
(18, 15, 'Solving Math'),
(19, 15, 'Reapair Of Installation Equipments');

-- --------------------------------------------------------

--
-- Table structure for table `sponsor`
--

CREATE TABLE `sponsor` (
  `StudentID` varchar(50) COLLATE latin1_bin NOT NULL,
  `FirstName` varchar(30) COLLATE latin1_bin NOT NULL,
  `LastName` varchar(30) COLLATE latin1_bin NOT NULL,
  `Address` varchar(500) COLLATE latin1_bin NOT NULL,
  `Telephone` varchar(30) COLLATE latin1_bin NOT NULL,
  `Email` varchar(254) COLLATE latin1_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

--
-- Dumping data for table `sponsor`
--

INSERT INTO `sponsor` (`StudentID`, `FirstName`, `LastName`, `Address`, `Telephone`, `Email`) VALUES
('EMY-C32', 'John', 'Kennedy', 'currAddr', '39209320', 'email.com');

-- --------------------------------------------------------

--
-- Table structure for table `studentlog`
--

CREATE TABLE `studentlog` (
  `dateOfOperation` date DEFAULT NULL,
  `operationType` varchar(10) COLLATE latin1_bin DEFAULT NULL,
  `OldID` varchar(50) COLLATE latin1_bin DEFAULT NULL,
  `NewID` varchar(50) COLLATE latin1_bin DEFAULT NULL,
  `PrevCertificateIssued` varchar(100) COLLATE latin1_bin DEFAULT NULL,
  `NewCertificateIssued` varchar(100) COLLATE latin1_bin DEFAULT NULL,
  `PrevActiveStatus` tinyint(1) DEFAULT NULL,
  `NewActiveStatus` tinyint(1) DEFAULT NULL,
  `PrevEmail` varchar(256) COLLATE latin1_bin DEFAULT NULL,
  `NewEmail` varchar(256) COLLATE latin1_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

--
-- Dumping data for table `studentlog`
--

INSERT INTO `studentlog` (`dateOfOperation`, `operationType`, `OldID`, `NewID`, `PrevCertificateIssued`, `NewCertificateIssued`, `PrevActiveStatus`, `NewActiveStatus`, `PrevEmail`, `NewEmail`) VALUES
('2017-12-19', 'UPDATE', NULL, 'EMY-C4', '', NULL, NULL, 1, NULL, 'email@email.com'),
('2017-12-19', 'UPDATE', NULL, 'ETY-C3', '', NULL, NULL, 1, NULL, 'email@email.com'),
('2017-12-21', 'UPDATE', NULL, 'EMY-C32', '', NULL, NULL, 1, NULL, 'email@email.com'),
('2018-02-06', 'UPDATE', NULL, 'EMUAIDA', '', NULL, NULL, 1, NULL, 'email@email.com'),
('2018-02-06', 'UPDATE', NULL, 'EMY-CDASCA', '', NULL, NULL, 1, NULL, 'email@email.com'),
('2018-02-09', 'UPDATE', 'EMY-C32', 'EMY-C32', NULL, NULL, 1, 1, 'email@email.com', 'email@email.com'),
('2018-02-09', 'UPDATE', 'EMY-C32', 'EMY-C32', NULL, NULL, 1, 1, 'email@email.com', 'email@email.com'),
('2018-02-09', 'UPDATE', NULL, 'EMY-C4340', '', NULL, NULL, 1, NULL, 'email@email.com'),
('2018-02-09', 'UPDATE', NULL, 'EMY-C54', '', NULL, NULL, 1, NULL, 'email@email.com'),
('2018-02-09', 'UPDATE', NULL, 'ETY-C43', '', NULL, NULL, 1, NULL, 'email@email.com'),
('2018-02-09', 'UPDATE', NULL, 'MINUS', '', NULL, NULL, 1, NULL, 'email@email.com'),
('2018-02-09', 'UPDATE', NULL, 'NAME', '', NULL, NULL, 1, NULL, 'email@email.com'),
('2018-02-12', 'UPDATE', 'EMY-C32', 'EMY-C32', NULL, 'Modular Mecatronics', 1, 1, 'email@email.com', 'email@email.com'),
('2018-02-12', 'UPDATE', NULL, 'Fred1010', NULL, NULL, NULL, 1, NULL, 'email@email.com'),
('2018-02-12', 'UPDATE', NULL, 'PIO2828', NULL, NULL, NULL, 1, NULL, 'email@email.com'),
('2018-02-13', 'UPDATE', 'EMY-C4340', 'EMY-C$', NULL, NULL, 1, 1, 'email@email.com', 'email@email.com'),
('2018-02-13', 'UPDATE', 'EMY-CDASCA', 'EMY-C1', NULL, NULL, 1, 1, 'email@email.com', 'email@email.com'),
('2018-02-13', 'UPDATE', 'EMY-C$', 'EMY-C4', NULL, NULL, 1, 1, 'email@email.com', 'email@email.com'),
('2018-02-13', 'UPDATE', 'ETY-C43', 'ETY-C3', NULL, NULL, 1, 1, 'email@email.com', 'email@email.com'),
('2018-02-13', 'UPDATE', 'Fred1010', 'FRED1010', NULL, NULL, 1, 1, 'email@email.com', 'email@email.com');

-- --------------------------------------------------------

--
-- Table structure for table `test`
--

CREATE TABLE `test` (
  `DATA` int(11) NOT NULL,
  `Name` varchar(255) COLLATE latin1_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

--
-- Dumping data for table `test`
--

INSERT INTO `test` (`DATA`, `Name`) VALUES
(2, ''),
(2, ''),
(2, 'Chidiebere');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`username`) USING BTREE,
  ADD UNIQUE KEY `Email` (`Email`),
  ADD KEY `Email_Index` (`username`,`password`) USING BTREE;

--
-- Indexes for table `aspiringexperience`
--
ALTER TABLE `aspiringexperience`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `aspiring_stud_link` (`AspID`);

--
-- Indexes for table `aspiringjobresponsibility`
--
ALTER TABLE `aspiringjobresponsibility`
  ADD KEY `expLink` (`AspExpID`);

--
-- Indexes for table `aspiringmeans`
--
ALTER TABLE `aspiringmeans`
  ADD KEY `aspMeansLink` (`AspID`);

--
-- Indexes for table `aspiringsponsor`
--
ALTER TABLE `aspiringsponsor`
  ADD KEY `aspSpnsLink` (`AspID`);

--
-- Indexes for table `aspiringstudenteducation`
--
ALTER TABLE `aspiringstudenteducation`
  ADD KEY `aspEduLink` (`AspID`);

--
-- Indexes for table `aspiringstudentphone`
--
ALTER TABLE `aspiringstudentphone`
  ADD KEY `sudLink` (`AspID`);

--
-- Indexes for table `aspmodule`
--
ALTER TABLE `aspmodule`
  ADD KEY `AspID` (`AspID`);

--
-- Indexes for table `biodata`
--
ALTER TABLE `biodata`
  ADD UNIQUE KEY `studentId` (`studentId`);
ALTER TABLE `biodata` ADD FULLTEXT KEY `name_index` (`FirstName`,`LastName`);

--
-- Indexes for table `certificate`
--
ALTER TABLE `certificate`
  ADD PRIMARY KEY (`Name`),
  ADD KEY `DateCreated` (`DateCreated`,`Name`) USING BTREE;

--
-- Indexes for table `certificatelog`
--
ALTER TABLE `certificatelog`
  ADD KEY `log_date_index` (`dateOfOperation`,`newCertificateName`,`oldCertificateName`,`operationType`) USING BTREE;
ALTER TABLE `certificatelog` ADD FULLTEXT KEY `log_type_index` (`operationType`);

--
-- Indexes for table `certificateregister`
--
ALTER TABLE `certificateregister`
  ADD KEY `moduleLink` (`moduleName`,`certificateName`) USING BTREE,
  ADD KEY `certModuleKey` (`certificateName`,`moduleName`) USING BTREE;

--
-- Indexes for table `certificateregisterlog`
--
ALTER TABLE `certificateregisterlog`
  ADD KEY `log_type_index` (`operationType`),
  ADD KEY `date_index` (`DateOfOperation`,`operationType`,`NewCertificateName`,`NewModuleName`,`OldModuleName`,`OldCertificateName`) USING BTREE;

--
-- Indexes for table `educational_background`
--
ALTER TABLE `educational_background`
  ADD KEY `StudentID` (`StudentID`);

--
-- Indexes for table `means_of_discovery`
--
ALTER TABLE `means_of_discovery`
  ADD KEY `studentMeansLink` (`StudentID`,`Means`) USING BTREE;

--
-- Indexes for table `modular_class`
--
ALTER TABLE `modular_class`
  ADD PRIMARY KEY (`name`),
  ADD KEY `dateCreated` (`dateCreated`);

--
-- Indexes for table `modular_class_log`
--
ALTER TABLE `modular_class_log`
  ADD KEY `DateOfOperation` (`DateOfOperation`,`OperationType`,`NewName`,`OldName`) USING BTREE;

--
-- Indexes for table `module`
--
ALTER TABLE `module`
  ADD PRIMARY KEY (`name`),
  ADD KEY `date_index` (`amountPerUnit`) USING BTREE;

--
-- Indexes for table `modulelog`
--
ALTER TABLE `modulelog`
  ADD KEY `dateOfOperation` (`dateOfOperation`,`operationType`,`newModuleName`,`oldModuleName`) USING BTREE;

--
-- Indexes for table `moduleregisterlog`
--
ALTER TABLE `moduleregisterlog`
  ADD KEY `regId` (`regId`,`StudentId`,`operationType`,`DateOfOperation`,`OldModuleName`,`NewModuleName`,`OldAttendanceStatus`,`NewAttendanceStatus`,`OldBookingStatus`,`NewBookingStatus`,`OldResult`,`NewResult`) USING BTREE;

--
-- Indexes for table `module_register`
--
ALTER TABLE `module_register`
  ADD PRIMARY KEY (`id`),
  ADD KEY `StudentLink2` (`StudentId`),
  ADD KEY `mod_student_index` (`ModuleName`,`StudentId`) USING BTREE;

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `tbl_link` (`RegId`,`amount`) USING BTREE;

--
-- Indexes for table `paymentlog`
--
ALTER TABLE `paymentlog`
  ADD KEY `StudentID` (`StudentID`,`RegId`) USING BTREE,
  ADD KEY `bankName_index` (`BankName`(5),`Dateofoperation`);

--
-- Indexes for table `phone`
--
ALTER TABLE `phone`
  ADD UNIQUE KEY `student_id` (`StudentId`,`phone_number`);

--
-- Indexes for table `professional_experience`
--
ALTER TABLE `professional_experience`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `StudentId` (`StudentId`,`Employer`,`Job Title`,`StartDate`,`EndDate`);

--
-- Indexes for table `resources`
--
ALTER TABLE `resources`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `responsibilty`
--
ALTER TABLE `responsibilty`
  ADD PRIMARY KEY (`id`),
  ADD KEY `experienceId` (`experienceId`);

--
-- Indexes for table `sponsor`
--
ALTER TABLE `sponsor`
  ADD UNIQUE KEY `StudentID` (`StudentID`,`FirstName`,`LastName`,`Email`) USING BTREE,
  ADD KEY `sponsor_mail` (`Email`);
ALTER TABLE `sponsor` ADD FULLTEXT KEY `sponsor_name_index` (`FirstName`,`LastName`);

--
-- Indexes for table `studentlog`
--
ALTER TABLE `studentlog`
  ADD KEY `operationType` (`operationType`,`dateOfOperation`,`NewID`,`OldID`,`PrevCertificateIssued`,`NewCertificateIssued`,`PrevActiveStatus`,`NewActiveStatus`,`PrevEmail`,`NewEmail`) USING BTREE;

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `aspiringexperience`
--
ALTER TABLE `aspiringexperience`
  MODIFY `ID` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `module_register`
--
ALTER TABLE `module_register`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `professional_experience`
--
ALTER TABLE `professional_experience`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `resources`
--
ALTER TABLE `resources`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `responsibilty`
--
ALTER TABLE `responsibilty`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `aspiringexperience`
--
ALTER TABLE `aspiringexperience`
  ADD CONSTRAINT `aspiring_stud_link` FOREIGN KEY (`AspID`) REFERENCES `aspiringstudent` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `aspiringjobresponsibility`
--
ALTER TABLE `aspiringjobresponsibility`
  ADD CONSTRAINT `expLink` FOREIGN KEY (`ASpExpID`) REFERENCES `aspiringexperience` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `aspiringmeans`
--
ALTER TABLE `aspiringmeans`
  ADD CONSTRAINT `aspMeansLink` FOREIGN KEY (`AspID`) REFERENCES `aspiringstudent` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `aspiringsponsor`
--
ALTER TABLE `aspiringsponsor`
  ADD CONSTRAINT `aspSpnsLink` FOREIGN KEY (`AspID`) REFERENCES `aspiringstudent` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `aspiringstudenteducation`
--
ALTER TABLE `aspiringstudenteducation`
  ADD CONSTRAINT `aspEduLink` FOREIGN KEY (`AspID`) REFERENCES `aspiringstudent` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `aspiringstudentphone`
--
ALTER TABLE `aspiringstudentphone`
  ADD CONSTRAINT `sudLink` FOREIGN KEY (`AspID`) REFERENCES `aspiringstudent` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `aspmodule`
--
ALTER TABLE `aspmodule`
  ADD CONSTRAINT `aspmodule_ibfk_1` FOREIGN KEY (`AspID`) REFERENCES `aspiringstudent` (`ID`);

--
-- Constraints for table `biodata`
--
ALTER TABLE `biodata`
  ADD CONSTRAINT `studentID_link` FOREIGN KEY (`studentId`) REFERENCES `student` (`id_card_number`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `certificateregister`
--
ALTER TABLE `certificateregister`
  ADD CONSTRAINT `certificateLink` FOREIGN KEY (`certificateName`) REFERENCES `certificate` (`name`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `moduleLink` FOREIGN KEY (`moduleName`) REFERENCES `module` (`name`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `educational_background`
--
ALTER TABLE `educational_background`
  ADD CONSTRAINT `LinktoStudent` FOREIGN KEY (`StudentID`) REFERENCES `student` (`id_card_number`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `means_of_discovery`
--
ALTER TABLE `means_of_discovery`
  ADD CONSTRAINT `studentMeansLink` FOREIGN KEY (`StudentID`) REFERENCES `student` (`id_card_number`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `module_register`
--
ALTER TABLE `module_register`
  ADD CONSTRAINT `ModuleLink3` FOREIGN KEY (`ModuleName`) REFERENCES `module` (`name`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `StudentLink2` FOREIGN KEY (`StudentId`) REFERENCES `student` (`id_card_number`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `RegLink` FOREIGN KEY (`RegId`) REFERENCES `module_register` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `phone`
--
ALTER TABLE `phone`
  ADD CONSTRAINT `phoneLink` FOREIGN KEY (`StudentId`) REFERENCES `student` (`id_card_number`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `professional_experience`
--
ALTER TABLE `professional_experience`
  ADD CONSTRAINT `StudentLink` FOREIGN KEY (`StudentId`) REFERENCES `student` (`id_card_number`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `responsibilty`
--
ALTER TABLE `responsibilty`
  ADD CONSTRAINT `responsibilty_ibfk_1` FOREIGN KEY (`experienceId`) REFERENCES `professional_experience` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `sponsor`
--
ALTER TABLE `sponsor`
  ADD CONSTRAINT `financeLink` FOREIGN KEY (`StudentID`) REFERENCES `student` (`id_card_number`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
