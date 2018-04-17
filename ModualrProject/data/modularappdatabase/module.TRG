TYPE=TRIGGERS
triggers='CREATE DEFINER=`root`@`localhost` TRIGGER `moduleInsertTrigger` AFTER INSERT ON `module` FOR EACH ROW BEGIN\n	INSERT INTO ModuleLog( dateOfOperation, operationType, oldModuleName, newModuleName)\n    VALUES( now(), \'INSERT\', NULL , new.name);\nEND' 'CREATE DEFINER=`root`@`localhost` TRIGGER `moduleDeleteTrigger` AFTER DELETE ON `module` FOR EACH ROW BEGIN\n	INSERT INTO ModuleLog( dateOfOperation, operationType, oldModuleName , newModuleName)\n    VALUES( now(), \'DELETE\', old.name, NULL);\nEND' 'CREATE DEFINER=`root`@`localhost` TRIGGER `moduleUpdateTrigger` AFTER UPDATE ON `module` FOR EACH ROW BEGIN\n	INSERT INTO ModuleLog( dateOfOperation, operationType, oldModuleName, newModuleName)\n    VALUES( now(), \'UPDATE\', old.name, new.name);\nEND'
sql_modes=1342177280 1342177280 1342177280
definers='root@localhost' 'root@localhost' 'root@localhost'
client_cs_names='utf8mb4' 'utf8mb4' 'utf8mb4'
connection_cl_names='utf8mb4_unicode_ci' 'utf8mb4_unicode_ci' 'utf8mb4_unicode_ci'
db_cl_names='latin1_bin' 'latin1_bin' 'latin1_bin'
