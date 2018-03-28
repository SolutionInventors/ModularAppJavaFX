DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `addEducationRecord`(IN `studId` VARCHAR(50), IN `begin` DATE, IN `end` DATE, IN `institute` VARCHAR(200), IN `course` VARCHAR(200), IN `qualification` VARCHAR(200))
BEGIN

INSERT INTO `educational_background`(`StudentId`, `BeginDate`, `EndDate`, `Institution`, `CourseRead`, `QualificationName`) 
VALUES (
 studId,begin,end,institute,course,qualification);

End$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `addExperienceRecord`(IN `studId` VARCHAR(50), IN `beginDate` DATE, IN `endDate` DATE, IN `employer` VARCHAR(200), IN `jobName` VARCHAR(200), OUT `generated` INT)
BEGIN


INSERT INTO `professional_experience`(`StudentId`, `StartDate`, `EndDate`, `Employer`, `Job Title`)
VALUES( studId,beginDate,endDate,employer,jobName);

SELECT MAX(id) FROM professional_experience 
	INTO generated;
End$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `addModToCert`(IN `certName` VARCHAR(200), IN `modName` VARCHAR(100))
BEGIN
INSERT INTO `certificateRegister`(`certificateName`, `moduleName`) 
VALUES ( TRIM(certName), TRIM(modName) );
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `addMeansOfDiscovery`(IN `studId` VARCHAR(50), IN `means` VARCHAR(300))
BEGIN
INSERT INTO `means_of_discovery`(`StudentID`, `Means`) 
VALUES (studId,means );

end$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `addPhoneNumber`(IN `studId` VARCHAR(50), IN `number` VARCHAR(20))
BEGIN 
INSERT INTO `phone`(`StudentId`, `phone_number`) 
VALUES (studId, number );
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `addResponsibity`(IN `expId` INT, IN `res` VARCHAR(1000), OUT `theId` INT)
    NO SQL
BEGIN
INSERT INTO responsibilty(ExperienceId, Duty)
VALUES( expId, res);

SELECT id FROM responsibilty WHERE
	responsibilty.experienceId = expId AND
    responsibilty.Duty = res INTO  theId;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `addSponsor`(IN `studId` VARCHAR(50), IN `fName` VARCHAR(30), IN `lName` VARCHAR(30), IN `addr` VARCHAR(200), IN `phone` VARCHAR(20), IN `mail` VARCHAR(100))
BEGIN
INSERT INTO `sponsor`(`StudentID`, `FirstName`, `LastName`, `Address`, `Telephone`, `Email`) 
VALUES (studId,fName,lName,addr,phone, mail);
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `attendModule`(IN `regId` INT, IN `studId` VARCHAR(50), IN `modName` VARCHAR(100))
    NO SQL
BEGIN

UPDATE module_register as reg 
	SET reg.AttendanceStatus = true
    WHERE reg.ModuleName = modName AND reg.StudentId= studId AND reg.id = regId;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `bookModule`(IN `regId` INT, IN `studId` VARCHAR(50), IN `modName` VARCHAR(100))
    NO SQL
BEGIN

UPDATE module_register reg 
	SET reg.BookingStatus = true
    WHERE reg.StudentId = studId AND reg.ModuleName = modName AND reg.id = regId;
    
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `createCertificate`(IN `certName` VARCHAR(200), OUT `dateCreated` INT)
BEGIN
Set @dateCreated = now();

INSERT INTO `certificate`
( `dateCreated`, `name`) 
VALUES ( @dateCreated , TRIM(certName));
end$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `createNewClass`(IN `name` VARCHAR(30), OUT `creationDate` DATE)
BEGIN
set creationDate = now();

INSERT INTO `modular_class`(`name`, `dateCreated`)
VALUES (name ,creationDate);
end$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `createNewModule`( IN modName varchar(100), IN numOfUnits int, IN price double, OUT creationDate date)
BEGIN
set creationDate = NOW();
INSERT INTO `module`(`dateCreated`, `name`, `units`, `amountPerUnit`) 
VALUES (creationDate,modName,numOfUnits,price);
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteCertificate`( IN certName varchar(200))
BEGIN 
    DELETE FROM `certificate` WHERE `name` = certName;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllPhoneNumbers`(IN `startIndex` INT)
    NO SQL
BEGIN
SELECT * FROM phone
LIMIT startIndex, 30;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getBiodata`(IN `studId` VARCHAR(50))
    NO SQL
BEGIN
SELECT * FROM biodata 
	WHERE studentId = studId ;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getCertModulesByIndex`( IN startIndex int)
BEGIN
SELECT * FROM certificatemodule 
LIMIT startIndex, 30 ;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getCertificateByDate`( IN sinceDate date, IN startIndex int )
BEGIN 
 SELECT * FROM certificate 
 WHERE dateCreated > sinceDate
 LIMIT startIndex , 30 ;
end$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getCertificatesByIndex`(IN `startIndex` INT)
BEGIN

	SELECT sleep(0.3) INTO @name;
	SELECT * FROM certificate 
    LIMIT startIndex , 30 ;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getClass`(IN `name` VARCHAR(30))
BEGIN
SELECT * FROM  Modular_class 
	WHERE class.name =  TRIM(name) ;
end$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getClassByDate`(IN `creationDate` DATE, IN `startIndex` INT)
BEGIN
SELECT * FROM class 
	where class.dateCreated < creationDate 
    LIMIT startIndex , 0 ;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getClassesByIndex`(IN `startIndex` INT)
BEGIN
SELECT * FROM Modular_class 
LIMIT startIndex , 30 ;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getDuties`(IN `expId` INT)
    NO SQL
BEGIN
select Duty FROM responsibilty
WHERE experienceId = expId;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getEducationRecord`( IN studId varchar(50))
BEGIN
SELECT * FROM `educational_background`
	WHERE StudentId =  studId;
End$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getMeanOfDiscovery`(IN `studId` VARCHAR(50))
    NO SQL
BEGIN 
SELECT Means FROM means_of_discovery 
WHERE StudentId =  studId;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getModuleByIndex`( IN startIndex int)
BEGIN
SELECT * FROM module
LIMIT startIndex , 30;
end$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getProfExperience`(IN `studId` VARCHAR(50))
    NO SQL
BEGIN
SELECT * FROM `professional_experience`
WHERE StudentId = studId;

END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getStudentPhone`(IN `studId` VARCHAR(50))
BEGIN
SELECT * FROM `phone` 
	WHERE StudentId = studId;
end$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getStudentPhoneNumber`(IN `studId` VARCHAR(50), IN `startIndex` INT(50))
    NO SQL
BEGIN
SELECT * FROM phone
	WHERE phone.student_id = studId
 	LIMIT startIndex, 30;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertAdmin`(IN `uName` VARCHAR(200), IN `pass` VARCHAR(500), IN `mail` VARCHAR(256))
    NO SQL
BEGIN
INSERT into admin( username, password, Email)
VALUES( uName, pass, mail);
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertBiodata`(IN `studId` VARCHAR(50), IN `title` VARCHAR(10), IN `permanentAddr` VARCHAR(1000), IN `currentAddr` VARCHAR(1000), IN `religion` VARCHAR(100), IN `state` VARCHAR(100), IN `country` VARCHAR(100), IN `gender` VARCHAR(6), IN `birthDate` DATE, IN `placeOfBirth` VARCHAR(100))
    NO SQL
BEGIN
INSERT into biodata( studentId, Title,  PermanentAddress, CurrentAddress, Religion, stateOfOrigin, country, gender, dateOfBirth, placeOfBirth)

VALUES( studId, title, permanentAddr,  currentAddr ,religion,state, country, gender, birthDate,   placeOfBirth);

END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertDuty`(IN `expId` INT, IN `inDuty` VARCHAR(1000), OUT `theId` INT)
    NO SQL
BEGIN
INSERT into responsibilty( duty, experienceId)
VALUES(inDuty, expId );

SELECT id FROM responsibilty WHERE
	duty = inDuty AND experienceId= expId
    INTO theId;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertStudent`(IN `cardNum` VARCHAR(50), IN `mail` VARCHAR(254), IN `img` LONGBLOB, IN `class` VARCHAR(30), IN `fName` VARCHAR(50), IN `lName` VARCHAR(50), OUT `regDate` DATE)
BEGIN
SELECT now() into regDate;
INSERT INTO `student`(`id_card_number`, `dateAdmitted`, `active`, `emailAddress`, `className`, `image`, `FirstName`, `LastName`)
VALUES (cardNum ,regDate,true,mail,class,img, fName, lName);
end$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `makePayment`(IN `modRegisterId` INT, IN `amountPaid` DOUBLE, IN `bankName` VARCHAR(200), IN `tellerNumber` INT(20), IN `paymentDate` DATE)
BEGIN

INSERT INTO `payment`( `RegId`, `amount`, `bankName`, `tellerNumber`, `dateOfPayment`) 
VALUES (modRegisterId ,amountPaid,bankName,tellerNumber,paymentDate );

end$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `registerForModule`(IN `studId` VARCHAR(50), IN `modName` VARCHAR(100), OUT `regDate` DATE)
BEGIN
set regDate = now();

SELECT module.units * module.amountPerUnit 
FROM module  
WHERE module.name = modName 
INTO @price;


INSERT INTO `module_register`( `dateRegistered`, `moduleName`, `AttendanceStatus`, `studentId`, `bookingStatus`, `totalPriceForModule`, `result`)
VALUES (regDate,modName,false,studId,false, @price
        ,null );
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `removeCertificate`( IN certName varchar(200))
BEGIN
DELETE FROM `certificate` 
WHERE certificate.name = certName;
end$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `removeClass`(IN `name` VARCHAR(30))
BEGIN
DELETE FROM `Modular_class`
	WHERE `Modular_class`.name =  TRIM(name) ;
end$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `removeModFromCert`(IN `certName` VARCHAR(100), IN `modName` VARCHAR(100))
BEGIN
DELETE FROM `certificateRegister`
WHERE `certificateName` = certName AND `moduleName` = modName ; 
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `removeModule`( IN moduleName varchar(100))
BEGIN 
DELETE FROM `module` WHERE module.name = moduleName;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `removePhoneNumber`( IN studId varchar(50), IN number varchar(20))
BEGIN 
DELETE FROM phone  
	WHERE phone.student_id = studId AND phone.phone_number = number;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `removeStudent`( IN id varchar(50) )
BEGIN
DELETE FROM student WHERE id_card_number =  id;
 END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `setResult`(IN `modRegId` INT, IN `studId` VARCHAR(50), IN `modName` VARCHAR(100), IN `result` VARCHAR(4))
    NO SQL
BEGIN
UPDATE module_register as reg 
	SET reg.Result = ucase(result) 
    WHERE reg.id = modRegId AND reg.StudentId = studId AND reg.ModuleName = modName;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateAdmin`(IN `oldUsername` VARCHAR(200), IN `newUsername` VARCHAR(200), IN `newPass` VARCHAR(500))
BEGIN 
UPDATE `admin`
SET `username`=TRIM(newUsername) ,`password`=newPass 
	WHERE username = oldUsername ;
end$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateAdminMail`(IN uName varchar(200), IN mail varchar(256))
BEGIN
UPDATE `admin` SET `Email`= mail
WHERE username = uName;

END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateAdminUsername`(IN `exstingUsername` VARCHAR(200), IN `newUname` VARCHAR(256))
    NO SQL
BEGIN
UPDATE admin SET username  = newUname
WHERE username =  exstingUsername ;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateBiodata`(IN `studId` VARCHAR(50), IN `title` VARCHAR(10), IN `surname` VARCHAR(50), IN `midName` VARCHAR(50), IN `lastName` VARCHAR(50), IN `permanentAddr` VARCHAR(1000), IN `currentAddr` VARCHAR(1000), IN `religion` VARCHAR(100), IN `state` VARCHAR(100), IN `country` VARCHAR(100), IN `gender` VARCHAR(6), IN `birthDate` DATE, IN `placeOfBirth` VARCHAR(100))
BEGIN
UPDATE biodata SET Title= title, Surname =surname,MiddleName = midName, LastName =lastName, PermanentAddress= permanentAddr, CurrentAddress = currentAddr, Religion = religion, stateOfOrigin = state, country = country, gender = gender, dateOfBirth = birthDate, placeOfBirth =placeOfBirth
WHERE  studentId = studId ;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateCertificate`(IN `oldCertName` VARCHAR(200), IN `newName` VARCHAR(200))
BEGIN
UPDATE `certificate` 
SET `name`= TRIM(newName) WHERE oldCertName = `name` ;
end$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateClass`(IN `oldClassName` VARCHAR(100), IN `newClassName` VARCHAR(100), OUT `creationDate` DATE)
BEGIN
update Modular_class as class SET class.name = newClassName
WHERE class.name = oldClassName;
SELECT class.dateCreated from Modular_class as class
	WHERE class.name = newClassName into @date;
SET creationDate = @date;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateDiscoveryRecord`(IN `studID` VARCHAR(50), IN `newMeans` VARCHAR(300), IN `oldMeans` VARCHAR(300))
    NO SQL
BEGIN

UPDATE means_of_discovery
	SET `Means` = newMeans
    where StudentID = studId AND Means = oldMeans;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateEducationRecord`(IN `studId` VARCHAR(50), IN `institute` VARCHAR(200), IN `beginDate` DATE, IN `endDate` DATE, IN `course` VARCHAR(200), IN `oldStudId` VARCHAR(50), IN `oldInst` VARCHAR(200), IN `oldBegin` DATE, IN `oldEnd` DATE, IN `oldCourse` VARCHAR(200))
    NO SQL
UPDATE educational_background 
 SET StudentId = studId , Institution = institute  , BeginDate = beginDate, EndDate = endDate, CourseRead = course 
WHERE StudentId = oldStudId  AND Institution = oldInst AND BeginDate = oldBegin AND 
			+ 	EndDate = oldEnd AND  CourseRead = oldCourse$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateMail`(IN `studId` VARCHAR(50), IN `newMail` VARCHAR(70), OUT `regDate` DATE)
    NO SQL
BEGIN
update student 
	set emailAddress = newMail
    WHERE id_card_number = studId;
SELECT dateAdmitted from student 
	where id_card_number = studId into regDate;

END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateModule`(IN `oldName` VARCHAR(100), IN `newName` VARCHAR(100), IN `numOfUnits` INT, IN `unitPrice` DOUBLE, OUT `creationDate` DATE)
BEGIN
SELECT dateCreated FROM module 
	WHERE module.name =  oldName INTO creationDate;
UPDATE `module`
SET `name`= newName,`units`= numOfUnits,`amountPerUnit`=unitPrice
	WHERE module.name =  oldName;
    

END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updatePhone`(IN `studId` VARCHAR(50), IN `oldNumber` VARCHAR(20), IN `newNumber` VARCHAR(20))
BEGIN 
UPDATE `phone` 
SET `phone_number`= newNumber 
WHERE `studentId`= studId  AND `phone_number`= oldNumber; 
end$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateSponsor`(IN `studId` VARCHAR(50), IN `fName` VARCHAR(50), IN `lName` VARCHAR(50), IN `addr` VARCHAR(200), IN `tel` VARCHAR(30), IN `mail` VARCHAR(100), IN `oldFirst` VARCHAR(50), IN `oldLast` VARCHAR(50), IN `oldEmail` VARCHAR(100))
    NO SQL
BEGIN
UPDATE sponsor
SET  FirstName = fName,
LastName = lName, Address = addr ,
Telephone = tel, Email = mail

WHERE StudentID = studId AND FirstName =oldFirst AND LastName = oldLast AND
Email = oldEmail ;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateStudent`( IN oldStudentId varchar(50) , IN certIssued varchar(200), IN active  boolean ,IN mail varchar(70) , OUT admissionDate date )
BEGIN
UPDATE `student` 
SET `certificateIssued`= certIssued,
`active`=active ,`emailAddress`=mail
 WHERE oldStudentId = id_card_number;
 
 SELECT dateAdmitted FROM student 
 	WHERE id_card_number = oldStudentId INTO admissionDate;
 END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateStudentImage`(IN `studId` VARCHAR(50), IN `img` LONGBLOB, OUT `regDate` DATE)
    NO SQL
BEGIN
UPDATE student
	SET student.image = img WHERE id_card_number = studId;
    
SELECT dateAdmitted from student WHERE id_card_number = studId INTO regDate;

END$$
DELIMITER ;



DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `makePayment`(IN `modRegisterId` INT, IN `amountPaid` DOUBLE)
BEGIN

INSERT INTO `payment`( `RegId`, `amount`) 
VALUES (modRegisterId ,amountPaid );

end$$
DELIMITER ;