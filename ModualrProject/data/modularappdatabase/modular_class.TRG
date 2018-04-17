TYPE=TRIGGERS
triggers='CREATE DEFINER=`root`@`localhost` TRIGGER `ClassInsertTrigger` AFTER INSERT ON `modular_class` FOR EACH ROW BEGIN\nINSERT INTO `modularclasslog`\n(`OldName`, `NewName`, `dateofoperation`, `OperationType`) \n\nVALUES (NULL ,new.name  ,now() , \'INSERT\');\nEND' 'CREATE DEFINER=`root`@`localhost` TRIGGER `ClassUpdateTrigger` AFTER UPDATE ON `modular_class` FOR EACH ROW BEGIN\nINSERT INTO `modularclasslog`\n(`OldName`, `NewName`, `dateofoperation`, `OperationType`) \nVALUES (old.name ,new.name  ,now() , \'UPDATE\');\nEND'
sql_modes=1342177280 1342177280
definers='root@localhost' 'root@localhost'
client_cs_names='utf8mb4' 'utf8mb4'
connection_cl_names='utf8mb4_unicode_ci' 'utf8mb4_unicode_ci'
db_cl_names='latin1_bin' 'latin1_bin'
