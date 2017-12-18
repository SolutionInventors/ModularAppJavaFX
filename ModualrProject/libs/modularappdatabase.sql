-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 18, 2017 at 11:52 PM
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
CREATE DEFINER=`root`@`localhost` PROCEDURE `addEducationRecord` (IN `studId` VARCHAR(50), IN `begin` DATE, IN `end` DATE, IN `institute` VARCHAR(200), IN `course` VARCHAR(200), IN `qualification` VARCHAR(200))  BEGIN

INSERT INTO `educational_background`(`StudentId`, `BeginDate`, `EndDate`, `Institution`, `CourseRead`, `QualificationName`) 
VALUES (
 studId,begin,end,institute,course,qualification);

End$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `addExperienceRecord` (IN `studId` VARCHAR(50), IN `beginDate` DATE, IN `endDate` DATE, IN `employer` VARCHAR(200), IN `jobName` VARCHAR(200), OUT `generated` INT)  BEGIN


INSERT INTO `professional_experience`(`StudentId`, `StartDate`, `EndDate`, `Employer`, `Job Title`)
VALUES( studId,beginDate,endDate,employer,jobName);

SELECT id FROM professional_experience 
	WHERE StudentId =  studId   INTO generated;
End$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `addMeansOfDiscovery` (IN `studId` VARCHAR(50), IN `means` VARCHAR(300))  BEGIN
INSERT INTO `means_of_discovery`(`StudentID`, `Means`) 
VALUES (studId,means );

end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `addModToCert` (IN `certName` VARCHAR(200), IN `modName` VARCHAR(100))  BEGIN
INSERT INTO `certificateRegister`(`certificateName`, `moduleName`) 
VALUES ( TRIM(certName), TRIM(modName) );
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `addPhoneNumber` (IN `studId` VARCHAR(50), IN `number` VARCHAR(20))  BEGIN 
INSERT INTO `phone`(`StudentId`, `phone_number`) 
VALUES (studId, number );
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `addResponsibity` (IN `expId` INT, IN `res` VARCHAR(1000), OUT `theId` INT)  NO SQL
BEGIN
INSERT INTO responsibilty(ExperienceId, Duty)
VALUES( expId, res);

SELECT id FROM responsibilty WHERE
	responsibilty.experienceId = expId AND
    responsibilty.Duty = res INTO  theId;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `addSponsor` (IN `studId` VARCHAR(50), IN `fName` VARCHAR(30), IN `lName` VARCHAR(30), IN `addr` VARCHAR(200), IN `phone` VARCHAR(20), IN `mail` VARCHAR(100))  BEGIN
INSERT INTO `sponsor`(`StudentID`, `FirstName`, `LastName`, `Address`, `Telephone`, `Email`) 
VALUES (studId,fName,lName,addr,phone, mail);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `addStudent` (IN `studId` VARCHAR(50), IN `class` VARCHAR(30), IN `img` LONGBLOB, IN `mail` VARCHAR(70), OUT `regDate` DATE)  BEGIN

INSERT INTO `student`(`id_card_number`, `dateAdmitted`, `active`, `emailAddress`, `className`, `image`) 
VALUES( studId,now(),true,mail,class,img);
set regDate = now();
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `attendModule` (IN `regId` INT, IN `studId` VARCHAR(50), IN `modName` VARCHAR(100))  NO SQL
BEGIN

UPDATE module_register as reg 
	SET reg.AttendanceStatus = true
    WHERE reg.ModuleName = modName AND reg.StudentId= studId AND reg.id = regId;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `bookModule` (IN `regId` INT, IN `studId` VARCHAR(50), IN `moduleName` VARCHAR(100))  NO SQL
BEGIN

UPDATE module_register reg 
	SET reg.BookingStatus = true
    WHERE reg.StudentId = studId AND reg.ModuleName = modName AND reg.id = regId;
    
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `createCertificate` (IN `certName` VARCHAR(200), OUT `dateCreated` INT)  BEGIN
Set @dateCreated = now();

INSERT INTO `certificate`
( `dateCreated`, `name`) 
VALUES ( @dateCreated , TRIM(certName));
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `createNewClass` (IN `name` VARCHAR(30), OUT `creationDate` DATE)  BEGIN
set creationDate = now();

INSERT INTO `modular_class`(`name`, `dateCreated`)
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllPhoneNumbers` (IN `startIndex` INT)  NO SQL
BEGIN
SELECT * FROM phone
LIMIT startIndex, 30;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getBiodata` (IN `studId` VARCHAR(50))  NO SQL
BEGIN
SELECT * FROM biodata 
	WHERE studentId = studId ;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getCertificateByDate` (IN `sinceDate` DATE, IN `startIndex` INT)  BEGIN 
 SELECT * FROM certificate 
 WHERE dateCreated > sinceDate
 LIMIT startIndex , 30 ;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getCertificatesByIndex` (IN `startIndex` INT)  BEGIN

	SELECT sleep(0.3) INTO @name;
	SELECT * FROM certificate 
    LIMIT startIndex , 30 ;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getCertModulesByIndex` (IN `startIndex` INT)  BEGIN
SELECT * FROM certificatemodule 
LIMIT startIndex, 30 ;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getClass` (IN `name` VARCHAR(30))  BEGIN
SELECT * FROM  Modular_class 
	WHERE class.name =  TRIM(name) ;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getClassByDate` (IN `creationDate` DATE, IN `startIndex` INT)  BEGIN
SELECT * FROM class 
	where class.dateCreated < creationDate 
    LIMIT startIndex , 0 ;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getClassesByIndex` (IN `startIndex` INT)  BEGIN
SELECT * FROM Modular_class 
LIMIT startIndex , 30 ;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getDuties` (IN `expId` INT)  NO SQL
BEGIN
select Duty FROM responsibilty
WHERE experienceId = expId;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getEducationRecord` (IN `studId` VARCHAR(50))  BEGIN
SELECT * FROM `educational_background`
	WHERE StudentId =  studId;
End$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getMeanOfDiscovery` (IN `studId` VARCHAR(50))  NO SQL
BEGIN 
SELECT Means FROM means_of_discovery 
WHERE StudentId =  studId;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getModuleByIndex` (IN `startIndex` INT)  BEGIN
SELECT * FROM module
LIMIT startIndex , 30;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getProfExperience` (IN `studId` VARCHAR(50))  NO SQL
BEGIN
SELECT * FROM `professional_experience`
WHERE StudentId = studId;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getStudentPhone` (IN `studId` VARCHAR(50))  BEGIN
SELECT * FROM `phone` 
	WHERE StudentId = studId;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getStudentPhoneNumber` (IN `studId` VARCHAR(50), IN `startIndex` INT(50))  NO SQL
BEGIN
SELECT * FROM phone
	WHERE phone.student_id = studId
 	LIMIT startIndex, 30;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertAdmin` (IN `uName` VARCHAR(200), IN `pass` VARCHAR(500), IN `mail` VARCHAR(256))  NO SQL
BEGIN
INSERT into admin( username, password, Email)
VALUES( uName, pass, mail);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertBiodata` (IN `studId` VARCHAR(50), IN `title` VARCHAR(10), IN `surname` VARCHAR(50), IN `midName` VARCHAR(50), IN `lastName` VARCHAR(50), IN `permanentAddr` VARCHAR(1000), IN `currentAddr` VARCHAR(1000), IN `religion` VARCHAR(100), IN `state` VARCHAR(100), IN `country` VARCHAR(100), IN `gender` VARCHAR(6), IN `birthDate` DATE, IN `placeOfBirth` VARCHAR(100))  NO SQL
BEGIN
INSERT into biodata( studentId, Title, Surname,MiddleName, LastName, PermanentAddress, CurrentAddress, Religion, stateOfOrigin, country, gender, dateOfBirth, placeOfBirth)

VALUES( studId, title, surname, midName, lastName,permanentAddr,  currentAddr ,religion,state, country, gender, birthDate,   placeOfBirth);

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertDuty` (IN `expId` INT, IN `inDuty` VARCHAR(1000), OUT `theId` INT)  NO SQL
BEGIN
INSERT into responsibilty( duty, experienceId)
VALUES(inDuty, expId );

SELECT id FROM responsibilty WHERE
	duty = inDuty AND experienceId= expId
    INTO theId;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertStudent` (IN `cardNum` VARCHAR(50), IN `mail` VARCHAR(254), IN `img` LONGBLOB, IN `class` VARCHAR(30), OUT `regDate` DATE)  BEGIN
SELECT now() into regDate;
INSERT INTO `student`(`id_card_number`, `dateAdmitted`, `active`, `emailAddress`, `className`, `image`)
VALUES (cardNum ,regDate,true,mail,class,img);
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `makePayment` (IN `modRegisterId` INT, IN `amountPaid` DOUBLE, IN `bankName` VARCHAR(200), IN `tellerNumber` INT(20), IN `paymentDate` DATE)  BEGIN

set @payComplete = isPaymentComplete(modRegisterId) ;
IF @paymentComplete =  0 THEN 
INSERT INTO `payment`( `RegId`, `amount`, `bankName`, `tellerNumber`, `dateOfPayment`) 
VALUES (modRegisterId ,amountPaid,bankName,tellerNumber,paymentDate );
END  IF ;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `registerForModule` (IN `studId` VARCHAR(50), IN `modName` VARCHAR(100), OUT `regDate` DATE)  BEGIN
set regDate = now();

SELECT module.units * module.amountPerUnit 
FROM module  INTO @price;


INSERT INTO `module_register`( `dateRegistered`, `moduleName`, `AttendanceStatus`, `studentId`, `bookingStatus`, `totalPriceForModule`, `result`)
VALUES (regDate,modName,false,studId,false, @price
        ,null );
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `removeCertificate` (IN `certName` VARCHAR(200))  BEGIN
DELETE FROM `certificate` 
WHERE certificate.name = certName;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `removeClass` (IN `name` VARCHAR(30))  BEGIN
DELETE FROM `Modular_class`
	WHERE `Modular_class`.name =  TRIM(name) ;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `removeModFromCert` (IN `certName` VARCHAR(100), IN `modName` VARCHAR(100))  BEGIN
DELETE FROM `certificateRegister`
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `setResult` (IN `modRegId` INT, IN `studId` VARCHAR(50), IN `modName` VARCHAR(100), IN `result` VARCHAR(4))  NO SQL
BEGIN
UPDATE module_register as reg 
	SET Result = result 
    WHERE reg.id = modRegId AND reg.StudentId = studId AND reg.ModuleName = modName;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateAdmin` (IN `oldUsername` VARCHAR(200), IN `oldPass` VARCHAR(500), IN `newUsername` VARCHAR(200), IN `newPass` VARCHAR(500))  BEGIN 
UPDATE `admin`
SET `username`=TRIM(newUsername) ,`password`=newPass 
	WHERE username = oldUsername AND password = oldPass ;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateBiodata` (IN `studId` VARCHAR(50), IN `title` VARCHAR(10), IN `surname` VARCHAR(50), IN `midName` VARCHAR(50), IN `lastName` VARCHAR(50), IN `permanentAddr` VARCHAR(1000), IN `currentAddr` VARCHAR(1000), IN `religion` VARCHAR(100), IN `state` VARCHAR(100), IN `country` VARCHAR(100), IN `gender` VARCHAR(6), IN `birthDate` DATE, IN `placeOfBirth` VARCHAR(100))  BEGIN
UPDATE biodata SET Title= title, Surname =surname,MiddleName = midName, LastName =lastName, PermanentAddress= permanentAddr, CurrentAddress = currentAddr, Religion = religion, stateOfOrigin = state, country = country, gender = gender, dateOfBirth = birthDate, placeOfBirth =placeOfBirth
WHERE  studentId = studId ;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateCertificate` (IN `oldCertName` VARCHAR(200), IN `newName` VARCHAR(200))  BEGIN
UPDATE `certificate` 
SET `name`= TRIM(newName) WHERE oldCertName = `name` ;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateClass` (IN `oldClassName` VARCHAR(100), IN `newClassName` VARCHAR(100), OUT `creationDate` DATE)  BEGIN
update Modular_class as class SET class.name = newClassName
WHERE class.name = oldClassName;
SELECT class.dateCreated from Modular_class as class
	WHERE class.name = newClassName into @date;
SET creationDate = @date;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateDiscoveryRecord` (IN `studID` VARCHAR(50), IN `newMeans` VARCHAR(300), IN `oldMeans` VARCHAR(300))  NO SQL
BEGIN

UPDATE means_of_discovery
	SET `Means` = newMeans
    where StudentID = studId AND Means = oldMeans;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateEducationRecord` (IN `studId` VARCHAR(50), IN `institute` VARCHAR(200), IN `beginDate` DATE, IN `endDate` DATE, IN `course` VARCHAR(200), IN `oldStudId` VARCHAR(50), IN `oldInst` VARCHAR(200), IN `oldBegin` DATE, IN `oldEnd` DATE, IN `oldCourse` VARCHAR(200))  NO SQL
UPDATE educational_background 
 SET StudentId = studId , Institution = institute  , BeginDate = beginDate, EndDate = endDate, CourseRead = course 
WHERE StudentId = oldStudId  AND Institution = oldInst AND BeginDate = oldBegin AND 
			+ 	EndDate = oldEnd AND  CourseRead = oldCourse$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateMail` (IN `studId` VARCHAR(50), IN `newMail` VARCHAR(70), OUT `regDate` DATE)  NO SQL
BEGIN
update student 
	set emailAddress = newMail
    WHERE id_card_number = studId;
SELECT dateAdmitted from student 
	where id_card_number = studId into regDate;

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
WHERE `studentId`= studId  AND `phone_number`= oldNumber; 
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateSponsor` (IN `studId` VARCHAR(50), IN `fName` VARCHAR(50), IN `lName` VARCHAR(50), IN `addr` VARCHAR(200), IN `tel` VARCHAR(30), IN `mail` VARCHAR(100), IN `oldFirst` VARCHAR(50), IN `oldLast` VARCHAR(50), IN `oldEmail` VARCHAR(100))  NO SQL
BEGIN
UPDATE sponsor
SET  FirstName = fName,
LastName = lName, Address = addr ,
Telephone = tel, Email = mail

WHERE StudentID = studId AND FirstName =oldFirst AND LastName = oldLast AND
Email = oldEmail ;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateStudent` (IN `oldStudentId` VARCHAR(50), IN `certIssued` VARCHAR(200), IN `active` BOOLEAN, IN `mail` VARCHAR(70), OUT `admissionDate` DATE)  BEGIN
UPDATE `student` 
SET `certificateIssued`= certIssued,
`active`=active ,`emailAddress`=mail
 WHERE oldStudentId = id_card_number;
 
 SELECT dateAdmitted FROM student 
 	WHERE id_card_number = oldStudentId INTO admissionDate;
 END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateStudentImage` (IN `studId` VARCHAR(50), IN `img` LONGBLOB, OUT `regDate` DATE)  NO SQL
BEGIN
UPDATE student
	SET student.image = img WHERE id_card_number = studId;
    
SELECT dateAdmitted from student WHERE id_card_number = studId INTO regDate;

END$$

--
-- Functions
--
CREATE DEFINER=`root`@`localhost` FUNCTION `isPaymentComplete` (`modRegId` INT) RETURNS TINYINT(1) BEGIN
	SELECT modReg.id , 
  if( SUM( pay.amount) is not null, SUM( pay.amount), 0)  as 'Total Paid', 
  (module.units  * module.amountPerUnit) as 'Total For Module'
   
  FROM module_register as modReg
    LEFT JOIN module 
      ON module.name = modReg.ModuleName
    LEFT JOIN  payment as  pay
      ON modReg.id = pay.RegId
    WHERE modReg.id = modRegId
    GROUP BY modReg.id
    INTO @id, @totalPaid, @totalToPay;
    RETURN @totalPaid >= @totalToPay;
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
  `Email` varchar(256) COLLATE latin1_bin NOT NULL,
  `username` varchar(200) COLLATE latin1_bin NOT NULL,
  `password` varchar(500) COLLATE latin1_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

-- --------------------------------------------------------

--
-- Table structure for table `biodata`
--

CREATE TABLE `biodata` (
  `studentId` varchar(50) COLLATE latin1_bin DEFAULT NULL,
  `Title` varchar(15) COLLATE latin1_bin NOT NULL,
  `Surname` varchar(50) COLLATE latin1_bin NOT NULL,
  `MiddleName` varchar(50) COLLATE latin1_bin NOT NULL,
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

-- --------------------------------------------------------

--
-- Table structure for table `certificate`
--

CREATE TABLE `certificate` (
  `DateCreated` date DEFAULT NULL,
  `Name` varchar(200) COLLATE latin1_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

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

-- --------------------------------------------------------

--
-- Table structure for table `certificateregister`
--

CREATE TABLE `certificateregister` (
  `certificateName` varchar(100) COLLATE latin1_bin DEFAULT NULL,
  `moduleName` varchar(100) COLLATE latin1_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

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
  `OldModuleName` varchar(100) COLLATE latin1_bin DEFAULT NULL,
  `NewCertificateName` varchar(100) COLLATE latin1_bin DEFAULT NULL,
  `OldCertificateName` varchar(100) COLLATE latin1_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

-- --------------------------------------------------------

--
-- Table structure for table `educational_background`
--

CREATE TABLE `educational_background` (
  `StudentId` varchar(50) COLLATE latin1_bin NOT NULL,
  `Institution` varchar(200) COLLATE latin1_bin NOT NULL,
  `QualificationName` varchar(200) COLLATE latin1_bin NOT NULL,
  `CourseRead` varchar(200) COLLATE latin1_bin NOT NULL,
  `BeginDate` date NOT NULL,
  `EndDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

-- --------------------------------------------------------

--
-- Table structure for table `means_of_discovery`
--

CREATE TABLE `means_of_discovery` (
  `StudentID` varchar(50) COLLATE latin1_bin NOT NULL,
  `Means` varchar(300) COLLATE latin1_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

-- --------------------------------------------------------

--
-- Table structure for table `modularclasslog`
--

CREATE TABLE `modularclasslog` (
  `OldName` varchar(100) COLLATE latin1_bin DEFAULT NULL,
  `NewName` varchar(100) COLLATE latin1_bin DEFAULT NULL,
  `DateOfOperation` date NOT NULL,
  `OperationType` set('INSERT','UPDATE','DELETE') COLLATE latin1_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

-- --------------------------------------------------------

--
-- Table structure for table `modular_class`
--

CREATE TABLE `modular_class` (
  `name` varchar(30) COLLATE latin1_bin NOT NULL,
  `dateCreated` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

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
-- Table structure for table `module`
--

CREATE TABLE `module` (
  `dateCreated` date DEFAULT NULL,
  `name` varchar(100) COLLATE latin1_bin NOT NULL,
  `units` int(11) NOT NULL,
  `amountPerUnit` double NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

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

-- --------------------------------------------------------

--
-- Table structure for table `module_register`
--

CREATE TABLE `module_register` (
  `id` int(11) NOT NULL,
  `DateRegistered` date NOT NULL,
  `ModuleName` varchar(100) COLLATE latin1_bin NOT NULL,
  `StudentId` varchar(50) COLLATE latin1_bin NOT NULL,
  `totalPriceForModule` double NOT NULL,
  `AttendanceStatus` tinyint(1) NOT NULL,
  `BookingStatus` tinyint(1) NOT NULL,
  `Result` set('PASS','FAIL') COLLATE latin1_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

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
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `id` int(11) NOT NULL,
  `RegId` int(11) DEFAULT NULL,
  `amount` double NOT NULL,
  `bankName` varchar(200) COLLATE latin1_bin DEFAULT NULL,
  `tellerNumber` int(20) DEFAULT NULL,
  `dateOfPayment` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

--
-- Triggers `payment`
--
DELIMITER $$
CREATE TRIGGER `payementInsertTrigger` AFTER INSERT ON `payment` FOR EACH ROW BEGIN
 INSERT INTO PaymentLog
  ( regId, dateofoperation, operationType,BankName,  amount)
 VALUES( new.regId, now(), 'INSERT',  new.bankName,new.amount);
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
  `OperationType` varchar(30) COLLATE latin1_bin DEFAULT NULL,
  `BankName` varchar(50) COLLATE latin1_bin NOT NULL,
  `Amount` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

-- --------------------------------------------------------

--
-- Table structure for table `phone`
--

CREATE TABLE `phone` (
  `StudentId` varchar(50) COLLATE latin1_bin DEFAULT NULL,
  `phone_number` varchar(20) COLLATE latin1_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

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

-- --------------------------------------------------------

--
-- Table structure for table `responsibilty`
--

CREATE TABLE `responsibilty` (
  `id` int(11) NOT NULL,
  `experienceId` int(11) NOT NULL,
  `Duty` varchar(1000) COLLATE latin1_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

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

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `id_card_number` varchar(50) COLLATE latin1_bin NOT NULL,
  `dateAdmitted` date NOT NULL,
  `certificateIssued` varchar(200) COLLATE latin1_bin DEFAULT NULL,
  `active` tinyint(1) DEFAULT '1',
  `emailAddress` varchar(254) COLLATE latin1_bin DEFAULT NULL,
  `className` varchar(30) COLLATE latin1_bin DEFAULT NULL,
  `image` longblob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

--
-- Triggers `student`
--
DELIMITER $$
CREATE TRIGGER `studentInsertTrigger` AFTER INSERT ON `student` FOR EACH ROW BEGIN
  INSERT INTO StudentLog
( dateOfOperation, operationType,  NewID, 
 NewCertificateIssued,  
NewActiveStatus,  NewEmail)
    VALUES( now(), 'UPDATE', new.id_card_number, 
     new.certificateIssued, 
      new.active,  new.emailAddress);
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `studentUpdateTrigger` AFTER UPDATE ON `student` FOR EACH ROW BEGIN
  INSERT INTO StudentLog
( dateOfOperation, operationType, OldIdCardNumber, NewIDCardNumber, 
OldCertificateIssued, NewCertificateIssued, PrevActiveStatus, 
NewActiveStatus, PrevEmail, NewEmail)
    VALUES( now(), 'UPDATE', old.id_card_number, new.id_card_number, 
     old.certificateIssued, new.certificateIssued, old.active, 
      new.active, old.emailAddress, new.emailAddress);
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `studentlog`
--

CREATE TABLE `studentlog` (
  `dateOfOperation` date DEFAULT NULL,
  `operationType` varchar(10) COLLATE latin1_bin DEFAULT NULL,
  `OldID` varchar(50) COLLATE latin1_bin DEFAULT NULL,
  `NewID` varchar(50) COLLATE latin1_bin DEFAULT NULL,
  `PrevCertificateIssued` varchar(100) COLLATE latin1_bin NOT NULL,
  `NewCertificateIssued` varchar(100) COLLATE latin1_bin DEFAULT NULL,
  `PrevActiveStatus` tinyint(1) DEFAULT NULL,
  `NewActiveStatus` tinyint(1) DEFAULT NULL,
  `PrevEmail` varchar(256) COLLATE latin1_bin DEFAULT NULL,
  `NewEmail` varchar(256) COLLATE latin1_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

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
  ADD PRIMARY KEY (`Name`),
  ADD UNIQUE KEY `name` (`Name`);

--
-- Indexes for table `certificateregister`
--
ALTER TABLE `certificateregister`
  ADD UNIQUE KEY `certModuleKey` (`certificateName`,`moduleName`) USING BTREE,
  ADD KEY `moduleLink` (`moduleName`);

--
-- Indexes for table `educational_background`
--
ALTER TABLE `educational_background`
  ADD UNIQUE KEY `CompositeKey` (`StudentId`,`Institution`,`BeginDate`,`EndDate`,`CourseRead`) USING BTREE;

--
-- Indexes for table `means_of_discovery`
--
ALTER TABLE `means_of_discovery`
  ADD KEY `studentMeansLink` (`StudentID`);

--
-- Indexes for table `modular_class`
--
ALTER TABLE `modular_class`
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
  ADD KEY `ModuleLink3` (`ModuleName`),
  ADD KEY `StudentLink2` (`StudentId`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `RegLink` (`RegId`);

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
-- Indexes for table `responsibilty`
--
ALTER TABLE `responsibilty`
  ADD PRIMARY KEY (`id`),
  ADD KEY `experienceId` (`experienceId`);

--
-- Indexes for table `sponsor`
--
ALTER TABLE `sponsor`
  ADD UNIQUE KEY `StudentID` (`StudentID`,`FirstName`,`LastName`,`Email`) USING BTREE;

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id_card_number`),
  ADD KEY `student_ibfk_1` (`className`),
  ADD KEY `student_certificate_link` (`certificateIssued`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `module_register`
--
ALTER TABLE `module_register`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `professional_experience`
--
ALTER TABLE `professional_experience`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `responsibilty`
--
ALTER TABLE `responsibilty`
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
-- Constraints for table `certificateregister`
--
ALTER TABLE `certificateregister`
  ADD CONSTRAINT `certificateLink` FOREIGN KEY (`certificateName`) REFERENCES `certificate` (`name`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `moduleLink` FOREIGN KEY (`moduleName`) REFERENCES `module` (`name`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `educational_background`
--
ALTER TABLE `educational_background`
  ADD CONSTRAINT `Student_Link` FOREIGN KEY (`StudentId`) REFERENCES `student` (`id_card_number`) ON DELETE CASCADE ON UPDATE CASCADE;

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

--
-- Constraints for table `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `student_certificate_link` FOREIGN KEY (`certificateIssued`) REFERENCES `certificate` (`name`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `student_ibfk_1` FOREIGN KEY (`className`) REFERENCES `modular_class` (`name`) ON DELETE NO ACTION ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
