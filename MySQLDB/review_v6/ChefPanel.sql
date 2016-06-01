-- MySQL dump 10.13  Distrib 5.5.47, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: upbeat
-- ------------------------------------------------------
-- Server version	5.5.47-0ubuntu0.14.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `databag_detail`
--

DROP TABLE IF EXISTS `databag_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `databag_detail` (
  `databag_id` int(11) NOT NULL AUTO_INCREMENT,
  `databag_name` varchar(100) NOT NULL,
  `databag_userid` int(11) NOT NULL,
  PRIMARY KEY (`databag_id`),
  KEY `fk_databag_user_detail` (`databag_userid`),
  CONSTRAINT `databag_detail_ibfk_1` FOREIGN KEY (`databag_userid`) REFERENCES `user_detail` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `item_detail`
--

DROP TABLE IF EXISTS `item_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_detail` (
  `item_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_databag_id` int(11) NOT NULL,
  `item_name` varchar(100) NOT NULL,
  PRIMARY KEY (`item_id`),
  KEY `fk_item_databag_detail` (`item_databag_id`),
  CONSTRAINT `item_detail_ibfk_1` FOREIGN KEY (`item_databag_id`) REFERENCES `databag_detail` (`databag_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_activity_logs`
--

DROP TABLE IF EXISTS `user_activity_logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_activity_logs` (
  `username` varchar(80) NOT NULL,
  `databag` varchar(80) NOT NULL,
  `item` varchar(80) NOT NULL,
  `keyname` varchar(80) NOT NULL,
  `prev_value` varchar(80) NOT NULL,
  `new_value` varchar(80) NOT NULL,
  `action` varchar(80) NOT NULL,
  `actiontimestamp` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_detail`
--

DROP TABLE IF EXISTS `user_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_detail` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `role` varchar(20) NOT NULL DEFAULT 'user',
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-02-16 15:27:39
