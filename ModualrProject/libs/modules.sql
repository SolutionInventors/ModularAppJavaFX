-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 13, 2018 at 12:57 PM
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
('2017-12-19', 'Electrical Installation', 10, 3000),
('2018-02-09', 'Electrical Load', 10, 3000),
('2018-02-09', 'Electrical Panel', 10, 20000),
('2018-02-13', 'Fundamentals of engines', 10, 2000),
('2018-02-09', 'Knx', 20, 1000),
('2018-02-13', 'Mechanical Mesurements and fitting', 10, 20),
('2017-12-19', 'Motor Controls', 10, 3000),
('2018-02-09', 'Name', 30, 203203),
('2018-02-13', 'Physics', 10, 2000),
('2018-02-13', 'Power electronics', 10, 20),
('2018-02-13', 'Technical Drawing', 10, 2000);

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

--
-- Indexes for dumped tables
--

--
-- Indexes for table `module`
--
ALTER TABLE `module`
  ADD PRIMARY KEY (`name`),
  ADD KEY `date_index` (`amountPerUnit`) USING BTREE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
