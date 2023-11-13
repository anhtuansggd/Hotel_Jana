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
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification` (
  `id` int NOT NULL,
  `reservation_number` int NOT NULL,
  `description` varchar(200) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `n_notification_res_resx` (`reservation_number`),
  CONSTRAINT `n_notification_res` FOREIGN KEY (`reservation_number`) REFERENCES `room_booking` (`reservation_number`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` VALUES (1,20,'Greetings, Sir/Miss with customer number: 12. You have successfully booked room 110 on 2023-11-10 with our app. Thank you for choosing us.'),(2,21,'Greetings, Sir/Miss with customer number: 12. You have successfully booked room 113 on 2023-11-10 with our app. Thank you for choosing us.'),(3,22,'Greetings, Sir/Miss with customer number: 12. You have successfully booked room 111 on 2023-11-10 with our app. Thank you for choosing us.'),(4,23,'Greetings, Sir/Miss with customer number: 12. You have successfully booked room 104 on 2023-11-10 with our app. Thank you for choosing us.'),(5,24,'Greetings, Sir/Miss with customer number: 12. You have successfully booked room 109 on 2023-11-10 with our app. Thank you for choosing us.'),(6,25,'Greetings, Sir/Miss with customer number: 12. You have successfully booked room 115 on 2023-11-10 with our app. Thank you for choosing us.'),(7,26,'Greetings, Sir/Miss with customer number: 12. You have successfully booked room 112 on 2023-11-10 with our app. Thank you for choosing us.');
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
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
