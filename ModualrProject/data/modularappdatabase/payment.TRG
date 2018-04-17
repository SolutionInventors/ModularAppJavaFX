TYPE=TRIGGERS
triggers='CREATE DEFINER=`root`@`localhost` TRIGGER `payementInsertTrigger` AFTER INSERT ON `payment` FOR EACH ROW BEGIN\n\nSELECT reg.ModuleName, reg.StudentId \n	FROM module_register as reg \nWHERE reg.id = new.regId \ninto @modName, @studID;\n\n INSERT INTO PaymentLog\n  ( regId, dateofoperation, operationType,BankName,  amount, paymentlog.StudentID, paymentlog.ModuleName)\n VALUES( new.regId, now(), \'INSERT\',\'\',new.amount, @studID,@modName   );\nEND'
sql_modes=1342177280
definers='root@localhost'
client_cs_names='utf8mb4'
connection_cl_names='utf8mb4_unicode_ci'
db_cl_names='latin1_bin'
