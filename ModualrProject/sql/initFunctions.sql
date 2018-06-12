
DELIMITER $$
  
  CREATE   FUNCTION IF NOT EXISTS `isPaymentComplete` (`modRegId` INT) RETURNS TINYINT(1) 
  BEGIN
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
END $$
  
DELIMITER ;






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
( dateOfOperation, operationType, OldId, NewID, 
PrevCertificateIssued, NewCertificateIssued, PrevActiveStatus, 
NewActiveStatus, PrevEmail, NewEmail)
    VALUES( now(), 'UPDATE', old.id_card_number, new.id_card_number, 
     old.certificateIssued, new.certificateIssued, old.active, 
      new.active, old.emailAddress, new.emailAddress);
END
$$
DELIMITER ;

