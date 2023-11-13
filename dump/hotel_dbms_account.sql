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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `id` int NOT NULL,
  `account_type` enum('Manager','Receptionist','Guest') NOT NULL DEFAULT 'Guest',
  `user_name` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `race` enum('Frankenstein','Human','Invisible Human','Mummy','Vampire','Werewolf','Witch','Zombie') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='	';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'Manager','drac_34','5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8','Dracula','Vampire'),(2,'Receptionist','flyingsfun','1f8ac10f23c5b5bc1167bda84b833e5c057a77d2','Mavis','Vampire'),(3,'Guest','djjohnny','304c6bab4d8d71d58cd02d522be8c0cda8f06f2f','Johnny','Human'),(4,'Guest','frfr','7c4a8d09ca3762af61e59520943dc26494f8941b','Frank','Frankenstein'),(5,'Guest','mywifespregnantagain','a6408dbd30e8ad4f580cb9150cf95b054567ca97','Wayne','Werewolf'),(6,'Guest','sandsorcerer482','8575a31888b4ec86653b2dd610993fa07908d734','Murray','Mummy'),(7,'Guest','marvingaye','f03f015c35289a0c077a5da846a4dd34b9242cd7','Griffin','Vampire'),(8,'Guest','guest_','356a192b7913b04c54574d18c28d46e6395428ab','Guest','Vampire'),(9,'Receptionist','receptionist_','356a192b7913b04c54574d18c28d46e6395428ab','Receptionist','Human'),(10,'Manager','manager_','356a192b7913b04c54574d18c28d46e6395428ab','Manager','Vampire'),(11,'Manager','testC','a94a8fe5ccb19ba61c4c0873d391e987982fbbd3','testAcc','Frankenstein'),(12,'Manager','123','40bd001563085fc35165329ea1ff5c5ecbdbbeef','1234','Frankenstein'),(13,'Manager','user_3390','8ff30efdbfe9d5d8181add54d1ed4d4cc6dc3fea','Name_8025','Werewolf'),(14,'Guest','user_5087','52c45c5002f6302b00a7ff4f6fc72f659ad8855b','Name_8980','Human'),(15,'Guest','user_1364','78b38cb26fe46a01491c0889353d5f5a14dbcdf9','Name_2259','Vampire'),(16,'Guest','user_1922','356a192b7913b04c54574d18c28d46e6395428ab','Name_721','Vampire'),(17,'Receptionist','user_5305','56455e62ddc3f2410c9b81914e44167fb5fa780e','Name_3112','Frankenstein'),(18,'Receptionist','user_8163','351ac6caf1ecb5c90f8e188080c69cb813421d74','Name_2714','Zombie'),(19,'Manager','user_9612','e6e9677ab2efb33d138e7c8f5f3eedcac4da38b4','Name_9669','Zombie'),(20,'Guest','user_81','52826fb2a947bad56b3c505bc39f4264ab66f1a0','Name_6594','Werewolf'),(21,'Manager','user_7346','a0c9e4b0c3619052a0d8387aee1654226f23992f','Name_8523','Human'),(22,'Manager','user_3799','f8edf210aff70bed8ca9e968f70f22ec51b68713','Name_9853','Human'),(23,'Guest','user_8767','c1f139a5e3f64db70abdcc4b4864c4f76caa8e54','Name_9368','Human'),(24,'Receptionist','user_8560','d9d898e911faea14720ce4b1bf4ce82079a58313','Name_754','Human'),(25,'Receptionist','user_1945','ef4b6b0aca9743af8958b38e138d8413384f4995','Name_9359','Human'),(26,'Receptionist','user_1997','e5da57f6724117c724fb6479c14a5d2a76f1d87c','Name_4998','Mummy'),(27,'Manager','user_7896','8cf41e60645222ff2ac53477bb305537e890d639','Name_7083','Mummy'),(28,'Guest','user_3167','c56cf798ee51f7d2b77f8ee2cd61437bc2f08ea8','Name_8481','Mummy'),(29,'Manager','user_3996','4f2c2d29a02df36d6dff7acf565d2cb0c3ab8a2e','Name_2095','Human'),(30,'Manager','user_4333','44a104eaaaa3d6e742c3d1aba386059144e313c2','Name_7659','Human'),(31,'Guest','user_5072','0e2aeef0228abf65e3cbd53f83ea5fd030065f26','Name_7638','Mummy'),(32,'Receptionist','user_1274','0626691731a95e10b30dd749ba46f3a38f806c1f','Name_8936','Frankenstein'),(33,'Guest','user_7835','62037f1d778e0d60591bbd6076ce09fa41566285','Name_3910','Zombie'),(34,'Manager','user_6618','f4b69b7ea631088cf4d2a58e79b75142aa33fcb6','Name_1737','Zombie');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
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
