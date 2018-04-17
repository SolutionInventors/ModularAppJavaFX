TYPE=TRIGGERS
triggers='CREATE DEFINER=`root`@`localhost` TRIGGER moduleRegisterUpdateTrigger AFTER UPDATE\nON module_register\nFOR EACH ROW\nBEGIN\nINSERT INTO `moduleregisterlog`(`regId`, `operationType`, `DateOfOperation`, `StudentId`, `OldModuleName`, `NewModuleName`, `OldAttendanceStatus`, `NewAttendanceStatus`, `OldBookingStatus`, `NewBookingStatus`, `OldResult`, `NewResult`)\nVALUES (new.Id,\'INSERT\', NOW() , new.studentId,\n  old.moduleName ,new.moduleName,\n        old.attendanceStatus ,new.attendanceStatus,\n        old.bookingStatus ,\n        new.bookingStatus,old.result ,new.result);\nEND' 'CREATE DEFINER=`root`@`localhost` TRIGGER `moduleRegisterInsertTrigger` AFTER INSERT ON `module_register` FOR EACH ROW BEGIN\nINSERT INTO `moduleregisterlog`(`regId`, `operationType`, `DateOfOperation`, `StudentId`, `OldModuleName`, `NewModuleName`, `OldAttendanceStatus`, `NewAttendanceStatus`, `OldBookingStatus`, `NewBookingStatus`, `OldResult`, `NewResult`)\nVALUES (new.Id,\'INSERT\', NOW() , new.studentId,\n  NULL ,new.moduleName,NULL ,new.attendanceStatus,NULL ,\n        new.bookingStatus,NULL ,new.result);\nEND'
sql_modes=1342177280 1342177280
definers='root@localhost' 'root@localhost'
client_cs_names='utf8mb4' 'utf8mb4'
connection_cl_names='utf8mb4_unicode_ci' 'utf8mb4_unicode_ci'
db_cl_names='latin1_bin' 'latin1_bin'
