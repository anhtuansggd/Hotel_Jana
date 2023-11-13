-- MySQL dump 10.13  Distrib 8.0.35, for Linux (x86_64)
--
-- Host: localhost    Database: hotel_dbms
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `room_number` int NOT NULL,
  `room_style` enum('Standard','Deluxe','Family_Suite','Business_Suite') NOT NULL,
  `is_smoking` tinyint NOT NULL,
  PRIMARY KEY (`room_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (100,'Standard',1),(101,'Standard',0),(102,'Standard',0),(103,'Standard',0),(104,'Standard',1),(105,'Standard',0),(106,'Standard',1),(107,'Standard',1),(108,'Standard',0),(109,'Standard',1),(110,'Standard',1),(111,'Standard',1),(112,'Standard',1),(113,'Standard',1),(114,'Standard',0),(115,'Standard',0),(116,'Standard',1),(117,'Standard',0),(118,'Standard',0),(119,'Standard',1),(200,'Deluxe',0),(201,'Deluxe',1),(202,'Deluxe',0),(203,'Deluxe',1),(204,'Deluxe',1),(205,'Deluxe',0),(206,'Deluxe',0),(207,'Deluxe',1),(208,'Deluxe',1),(209,'Deluxe',1),(210,'Deluxe',1),(211,'Deluxe',1),(212,'Deluxe',0),(213,'Deluxe',0),(214,'Deluxe',0),(215,'Deluxe',0),(216,'Deluxe',1),(217,'Deluxe',1),(218,'Deluxe',1),(219,'Deluxe',0),(300,'Family_Suite',0),(301,'Family_Suite',0),(302,'Family_Suite',0),(303,'Family_Suite',1),(304,'Family_Suite',0),(305,'Family_Suite',0),(306,'Family_Suite',1),(307,'Family_Suite',1),(308,'Family_Suite',1),(309,'Family_Suite',0),(310,'Family_Suite',0),(311,'Family_Suite',1),(312,'Family_Suite',1),(313,'Family_Suite',1),(314,'Family_Suite',1),(315,'Family_Suite',0),(316,'Family_Suite',1),(317,'Family_Suite',0),(318,'Family_Suite',1),(319,'Family_Suite',1),(400,'Business_Suite',1),(401,'Business_Suite',1),(402,'Business_Suite',0),(403,'Business_Suite',0),(404,'Business_Suite',0),(405,'Business_Suite',1),(406,'Business_Suite',0),(407,'Business_Suite',0),(408,'Business_Suite',0),(409,'Business_Suite',1),(410,'Business_Suite',1),(411,'Business_Suite',0),(412,'Business_Suite',0),(413,'Business_Suite',1),(414,'Business_Suite',0),(415,'Business_Suite',0),(416,'Business_Suite',0),(417,'Business_Suite',0),(418,'Business_Suite',1),(419,'Business_Suite',0);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-13 11:22:13
