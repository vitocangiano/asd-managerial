-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: V-WIN7ULT-PC    Database: authentication
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.33-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tb_user`
--

DROP TABLE IF EXISTS `tb_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_user` (
  `userId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `userUUID` binary(16) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `dateFrom` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateTo` datetime DEFAULT NULL,
  `active` bit(1) NOT NULL DEFAULT b'1',
  `frontEndConfigurationJson` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `tb_user_un` (`userUUID`),
  UNIQUE KEY `user_email` (`email`),
  UNIQUE KEY `user_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user`
--

LOCK TABLES `tb_user` WRITE;
/*!40000 ALTER TABLE `tb_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_user_role`
--

DROP TABLE IF EXISTS `tb_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_user_role` (
  `userRoleId` tinyint(3) unsigned NOT NULL AUTO_INCREMENT,
  `userRoleUUID` binary(16) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `active` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`userRoleId`),
  UNIQUE KEY `tb_user_role_name` (`name`),
  UNIQUE KEY `tb_user_role_uuid` (`userRoleUUID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user_role`
--

LOCK TABLES `tb_user_role` WRITE;
/*!40000 ALTER TABLE `tb_user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_user_role_join`
--

DROP TABLE IF EXISTS `tb_user_role_join`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_user_role_join` (
  `userRoleJoinId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `userRoleJoinUUID` binary(16) DEFAULT NULL,
  `userRoleId` tinyint(3) unsigned NOT NULL,
  `userId` int(10) unsigned NOT NULL,
  `dateFrom` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateTo` datetime DEFAULT NULL,
  `active` bit(1) NOT NULL,
  PRIMARY KEY (`userRoleJoinId`),
  UNIQUE KEY `tb_user_role_join_uuid` (`userRoleJoinUUID`),
  KEY `tb_user_role_join_FK` (`userId`) USING BTREE,
  KEY `tb_user_role_join_FK_1` (`userRoleId`) USING BTREE,
  CONSTRAINT `tb_user_role_join_FK` FOREIGN KEY (`userId`) REFERENCES `tb_user` (`userId`),
  CONSTRAINT `tb_user_role_join_FK_1` FOREIGN KEY (`userRoleId`) REFERENCES `tb_user_role` (`userRoleId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user_role_join`
--

LOCK TABLES `tb_user_role_join` WRITE;
/*!40000 ALTER TABLE `tb_user_role_join` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_user_role_join` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_user_session`
--

DROP TABLE IF EXISTS `tb_user_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_user_session` (
  `userSessionId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `userSessionUUID` binary(16) DEFAULT NULL,
  `userId` int(10) unsigned NOT NULL,
  `MD5` varchar(32) DEFAULT NULL,
  `agent` text,
  `dateFrom` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`userSessionId`),
  UNIQUE KEY `tb_user_session_uuid` (`userSessionUUID`),
  KEY `tb_user_session_FK` (`userId`) USING BTREE,
  CONSTRAINT `tb_user_session_FK` FOREIGN KEY (`userId`) REFERENCES `tb_user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user_session`
--

LOCK TABLES `tb_user_session` WRITE;
/*!40000 ALTER TABLE `tb_user_session` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_user_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `vw_user_role`
--

DROP TABLE IF EXISTS `vw_user_role`;
/*!50001 DROP VIEW IF EXISTS `vw_user_role`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `vw_user_role` AS SELECT 
 1 AS `userId`,
 1 AS `userUUID`,
 1 AS `userUUIDTxt`,
 1 AS `username`,
 1 AS `password`,
 1 AS `email`,
 1 AS `userDateFrom`,
 1 AS `userDateTo`,
 1 AS `userActive`,
 1 AS `userRoleId`,
 1 AS `userRoleUUID`,
 1 AS `userRoleUUIDTxt`,
 1 AS `roleName`,
 1 AS `roleActive`,
 1 AS `userRoleJoinId`,
 1 AS `userRoleJoinUUID`,
 1 AS `userRoleJoinUUIDTxt`,
 1 AS `userRoleDateFrom`,
 1 AS `userRoleDateTo`,
 1 AS `userRoleActive`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `vw_user_session`
--

DROP TABLE IF EXISTS `vw_user_session`;
/*!50001 DROP VIEW IF EXISTS `vw_user_session`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `vw_user_session` AS SELECT 
 1 AS `userId`,
 1 AS `userUUID`,
 1 AS `userUUIDTxt`,
 1 AS `username`,
 1 AS `userActive`,
 1 AS `userSessionId`,
 1 AS `userSessionUUID`,
 1 AS `userSessionUUIDTxt`,
 1 AS `MD5`,
 1 AS `agent`,
 1 AS `dateFrom`*/;
SET character_set_client = @saved_cs_client;

--
-- Dumping routines for database 'authentication'
--
/*!50003 DROP FUNCTION IF EXISTS `fn_uuid_binary_to_text` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'IGNORE_SPACE,STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `fn_uuid_binary_to_text`(UUID BINARY(16)) RETURNS varchar(38) CHARSET utf8
    SQL SECURITY INVOKER
BEGIN

  DECLARE ret VARCHAR(38);

  SET ret = lower(concat(
  substr(hex(UUID), 1, 8), '-',
  substr(hex(UUID), 9, 4), '-',
  substr(hex(UUID), 13, 4), '-',
  substr(hex(UUID), 17, 4), '-',
  substr(hex(UUID), 21)
  ));

  RETURN (ret);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `fn_uuid_text_to_binary` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'IGNORE_SPACE,STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `fn_uuid_text_to_binary`(UUID VARCHAR(38)) RETURNS binary(16)
    SQL SECURITY INVOKER
BEGIN

  DECLARE ret BINARY(16);

  SET ret = cast(unhex(replace(UUID, "-", "")) AS BINARY(16));
  RETURN ret;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `vw_user_role`
--

/*!50001 DROP VIEW IF EXISTS `vw_user_role`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_unicode_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vw_user_role` AS select `tu`.`userId` AS `userId`,`tu`.`userUUID` AS `userUUID`,`authentication`.`fn_uuid_binary_to_text`(`tu`.`userUUID`) AS `userUUIDTxt`,`tu`.`username` AS `username`,`tu`.`password` AS `password`,`tu`.`email` AS `email`,`tu`.`dateFrom` AS `userDateFrom`,`tu`.`dateTo` AS `userDateTo`,`tu`.`active` AS `userActive`,`tb_user_role`.`userRoleId` AS `userRoleId`,`tb_user_role`.`userRoleUUID` AS `userRoleUUID`,`authentication`.`fn_uuid_binary_to_text`(`tb_user_role`.`userRoleUUID`) AS `userRoleUUIDTxt`,`tb_user_role`.`name` AS `roleName`,`tb_user_role`.`active` AS `roleActive`,`tb_user_role_join`.`userRoleJoinId` AS `userRoleJoinId`,`tb_user_role_join`.`userRoleJoinUUID` AS `userRoleJoinUUID`,`authentication`.`fn_uuid_binary_to_text`(`tb_user_role_join`.`userRoleJoinUUID`) AS `userRoleJoinUUIDTxt`,`tb_user_role_join`.`dateFrom` AS `userRoleDateFrom`,`tb_user_role_join`.`dateTo` AS `userRoleDateTo`,`tb_user_role_join`.`active` AS `userRoleActive` from ((`tb_user_role_join` join `tb_user` `tu` on((`tb_user_role_join`.`userId` = `tu`.`userId`))) join `tb_user_role` on((`tb_user_role_join`.`userRoleId` = `tb_user_role`.`userRoleId`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vw_user_session`
--

/*!50001 DROP VIEW IF EXISTS `vw_user_session`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_unicode_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vw_user_session` AS select `tu`.`userId` AS `userId`,`tu`.`userUUID` AS `userUUID`,`authentication`.`fn_uuid_binary_to_text`(`tu`.`userUUID`) AS `userUUIDTxt`,`tu`.`username` AS `username`,`tu`.`active` AS `userActive`,`tus`.`userSessionId` AS `userSessionId`,`tus`.`userSessionUUID` AS `userSessionUUID`,`authentication`.`fn_uuid_binary_to_text`(`tus`.`userSessionUUID`) AS `userSessionUUIDTxt`,`tus`.`MD5` AS `MD5`,`tus`.`agent` AS `agent`,`tus`.`dateFrom` AS `dateFrom` from (`tb_user` `tu` join `tb_user_session` `tus` on((`tu`.`userId` = `tus`.`userId`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-17 12:12:12
