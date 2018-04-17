TYPE=TRIGGERS
triggers='CREATE DEFINER=`root`@`localhost` TRIGGER certificateUpdateTrigger AFTER update \nON Certificate   \nFOR EACH ROW\nBEGIN\n	INSERT INTO CertificateLog( dateOfOperation, operationType, oldCertificateName, newCertificateName)\n    VALUES( now(), \'UPDATE\', old.name, new.name);\nEND' 'CREATE DEFINER=`root`@`localhost` TRIGGER certificateInsertTrigger AFTER INSERT \nON Certificate  \nFOR EACH ROW\nBEGIN\n	INSERT INTO CertificateLog( dateOfOperation, operationType, oldCertificateName, newCertificateName)\n    VALUES( now(), \'INSERT\', NULL , new.name);\nEND' 'CREATE DEFINER=`root`@`localhost` TRIGGER `certificateDeleteTrigger` AFTER DELETE ON `certificate` FOR EACH ROW BEGIN\n	INSERT INTO CertificateLog( dateOfOperation, operationType, oldCertificateName)\n    VALUES( now(), \'DELETE\', old.name);\nEND'
sql_modes=1342177280 1342177280 1342177280
definers='root@localhost' 'root@localhost' 'root@localhost'
client_cs_names='utf8mb4' 'utf8mb4' 'utf8mb4'
connection_cl_names='utf8mb4_unicode_ci' 'utf8mb4_unicode_ci' 'utf8mb4_unicode_ci'
db_cl_names='latin1_bin' 'latin1_bin' 'latin1_bin'
