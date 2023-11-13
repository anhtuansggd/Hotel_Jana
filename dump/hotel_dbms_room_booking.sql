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
-- Table structure for table `room_booking`
--

DROP TABLE IF EXISTS `room_booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room_booking` (
  `reservation_number` int NOT NULL,
  `start_date` datetime NOT NULL,
  `duration` int NOT NULL,
  `room_number` int NOT NULL,
  `account_id` int NOT NULL,
  PRIMARY KEY (`reservation_number`),
  KEY `room_number_idx` (`room_number`),
  KEY `account_id_idx` (`account_id`),
  CONSTRAINT `account_id` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `room_number` FOREIGN KEY (`room_number`) REFERENCES `room` (`room_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_booking`
--

LOCK TABLES `room_booking` WRITE;
/*!40000 ALTER TABLE `room_booking` DISABLE KEYS */;
INSERT INTO `room_booking` VALUES (1,'2024-02-03 00:00:00',2,101,1),(2,'2024-02-02 00:00:00',2,202,2),(3,'2024-02-19 00:00:00',2,303,3),(4,'2024-02-11 00:00:00',2,404,4),(5,'2024-02-18 00:00:00',2,305,5),(6,'2024-02-16 00:00:00',2,206,6),(7,'2024-02-09 00:00:00',2,107,7),(8,'2024-02-18 00:00:00',2,108,11),(9,'2024-02-01 00:00:00',2,109,12),(10,'2024-02-12 00:00:00',2,110,13),(11,'2024-02-18 00:00:00',2,111,14),(12,'2024-02-16 00:00:00',2,112,15),(13,'2024-02-05 00:00:00',2,113,16),(14,'2024-02-18 00:00:00',2,114,17),(15,'2024-02-16 00:00:00',2,115,18),(16,'2024-02-06 00:00:00',2,116,19),(17,'2024-02-03 00:00:00',2,117,20),(18,'2024-02-10 00:00:00',2,118,21),(19,'2024-01-31 00:00:00',2,119,22),(20,'2024-02-10 00:00:00',2,110,12),(21,'2024-02-10 00:00:00',2,113,12),(22,'2024-02-10 00:00:00',2,111,12),(23,'2024-02-10 00:00:00',2,104,12),(24,'2024-02-10 00:00:00',2,109,12),(25,'2024-02-10 00:00:00',2,115,12),(26,'2024-02-10 00:00:00',2,112,12);
/*!40000 ALTER TABLE `room_booking` ENABLE KEYS */;
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
