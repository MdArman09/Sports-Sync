-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: matchesdb
-- ------------------------------------------------------
-- Server version	8.0.38

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
-- Table structure for table `matches`
--

DROP TABLE IF EXISTS `matches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `matches` (
  `uuid` binary(16) NOT NULL,
  `away_goals` int NOT NULL,
  `date` date DEFAULT NULL,
  `home_goals` int NOT NULL,
  `revenue` decimal(38,2) DEFAULT NULL,
  `spectators` int NOT NULL,
  `time` time(6) DEFAULT NULL,
  `away_team_id` bigint NOT NULL,
  `home_team_id` bigint NOT NULL,
  `stadium_id` bigint DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `FKbh4fpc4xaux9kop27bfypabra` (`away_team_id`),
  KEY `FKav2sryrpg0rdk68v4sa1pe0rx` (`home_team_id`),
  KEY `FKckd7lh29rbegw2o9c6m0uve6y` (`stadium_id`),
  CONSTRAINT `FKav2sryrpg0rdk68v4sa1pe0rx` FOREIGN KEY (`home_team_id`) REFERENCES `team` (`id`),
  CONSTRAINT `FKbh4fpc4xaux9kop27bfypabra` FOREIGN KEY (`away_team_id`) REFERENCES `team` (`id`),
  CONSTRAINT `FKckd7lh29rbegw2o9c6m0uve6y` FOREIGN KEY (`stadium_id`) REFERENCES `stadium` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matches`
--

LOCK TABLES `matches` WRITE;
/*!40000 ALTER TABLE `matches` DISABLE KEYS */;
INSERT INTO `matches` VALUES (_binary '`\Ï\»\√\⁄BπêßóÚˆ⁄£\»',2,'2025-04-07',5,62500.00,125,'05:30:00.000000',2,1,4),(_binary '≥*ªD)zM£çsÜ\ﬂÛß3E',2,'2025-04-07',5,125000.00,500,'12:30:00.000000',3,1,4);
/*!40000 ALTER TABLE `matches` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-08  3:37:12
