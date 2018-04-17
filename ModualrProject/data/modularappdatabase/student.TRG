TYPE=TRIGGERS
triggers='CREATE DEFINER=`root`@`localhost` TRIGGER `studentInsertTrigger` AFTER INSERT ON `student` FOR EACH ROW BEGIN\n  INSERT INTO StudentLog\n( dateOfOperation, operationType,  NewID, \n NewCertificateIssued,  \nNewActiveStatus,  NewEmail)\n    VALUES( now(), \'UPDATE\', new.id_card_number, \n     new.certificateIssued, \n      new.active,  new.emailAddress);\nEND' 'CREATE DEFINER=`root`@`localhost` TRIGGER `studentUpdateTrigger` AFTER UPDATE ON `student` FOR EACH ROW BEGIN\n  INSERT INTO StudentLog\n( dateOfOperation, operationType, OldId, NewID, \nPrevCertificateIssued, NewCertificateIssued, PrevActiveStatus, \nNewActiveStatus, PrevEmail, NewEmail)\n    VALUES( now(), \'UPDATE\', old.id_card_number, new.id_card_number, \n     old.certificateIssued, new.certificateIssued, old.active, \n      new.active, old.emailAddress, new.emailAddress);\nEND'
sql_modes=1342177280 1342177280
definers='root@localhost' 'root@localhost'
client_cs_names='utf8mb4' 'utf8mb4'
connection_cl_names='utf8mb4_unicode_ci' 'utf8mb4_unicode_ci'
db_cl_names='latin1_bin' 'latin1_bin'
