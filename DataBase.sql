CREATE DATABASE  IF NOT EXISTS `mydb` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `mydb`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: mydb
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `administrador`
--

DROP TABLE IF EXISTS `administrador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrador` (
  `idAdministrador` int NOT NULL,
  `Usuario` varchar(45) NOT NULL,
  `Contrasenya` varchar(45) NOT NULL,
  PRIMARY KEY (`idAdministrador`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrador`
--

LOCK TABLES `administrador` WRITE;
/*!40000 ALTER TABLE `administrador` DISABLE KEYS */;
INSERT INTO `administrador` VALUES (1,'admin','admin');
/*!40000 ALTER TABLE `administrador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `confirmacionvoto`
--

DROP TABLE IF EXISTS `confirmacionvoto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `confirmacionvoto` (
  `Personas_idPersona` int NOT NULL,
  `Elecciones_idElecciones` int NOT NULL,
  PRIMARY KEY (`Personas_idPersona`,`Elecciones_idElecciones`),
  KEY `fk_Votado_Elecciones1_idx` (`Elecciones_idElecciones`),
  CONSTRAINT `fk_Votado_Elecciones1` FOREIGN KEY (`Elecciones_idElecciones`) REFERENCES `elecciones` (`idElecciones`),
  CONSTRAINT `fk_Votado_Personas1` FOREIGN KEY (`Personas_idPersona`) REFERENCES `personas` (`idPersona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `confirmacionvoto`
--

LOCK TABLES `confirmacionvoto` WRITE;
/*!40000 ALTER TABLE `confirmacionvoto` DISABLE KEYS */;
/*!40000 ALTER TABLE `confirmacionvoto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `elecciones`
--

DROP TABLE IF EXISTS `elecciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `elecciones` (
  `idElecciones` int NOT NULL AUTO_INCREMENT,
  `Fecha` varchar(45) NOT NULL,
  `Tipo` varchar(45) NOT NULL,
  `Localidad_idLocalidad` int NOT NULL,
  PRIMARY KEY (`idElecciones`,`Localidad_idLocalidad`),
  KEY `fk_Elecciones_Localidad1_idx` (`Localidad_idLocalidad`),
  CONSTRAINT `fk_Elecciones_Localidad1` FOREIGN KEY (`Localidad_idLocalidad`) REFERENCES `localidad` (`idLocalidad`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `elecciones`
--

LOCK TABLES `elecciones` WRITE;
/*!40000 ALTER TABLE `elecciones` DISABLE KEYS */;
INSERT INTO `elecciones` VALUES (1,'06/06/2022','Generales',1),(2,'06/06/2022','Autonomicas',2),(3,'06/06/2022','Municipales',3),(4,'01/06/2022','Generales',1),(5,'05/06/2022','Autonomicas',2),(6,'05/06/2022','Municipales',3);
/*!40000 ALTER TABLE `elecciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `escrutinio`
--

DROP TABLE IF EXISTS `escrutinio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `escrutinio` (
  `idEscrutinio` int NOT NULL AUTO_INCREMENT,
  `Resultados` varchar(200) DEFAULT NULL,
  `Elecciones_idElecciones` int NOT NULL,
  `Elecciones_Localidad_idLocalidad` int NOT NULL,
  PRIMARY KEY (`idEscrutinio`,`Elecciones_idElecciones`,`Elecciones_Localidad_idLocalidad`),
  KEY `fk_Escrutinio_Elecciones1_idx` (`Elecciones_idElecciones`,`Elecciones_Localidad_idLocalidad`),
  CONSTRAINT `fk_Escrutinio_Elecciones1` FOREIGN KEY (`Elecciones_idElecciones`, `Elecciones_Localidad_idLocalidad`) REFERENCES `elecciones` (`idElecciones`, `Localidad_idLocalidad`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `escrutinio`
--

LOCK TABLES `escrutinio` WRITE;
/*!40000 ALTER TABLE `escrutinio` DISABLE KEYS */;
INSERT INTO `escrutinio` VALUES (4,'Podemos:42;Ciudadanos:24;PP:39;PSOE:58;Total:186',4,1);
/*!40000 ALTER TABLE `escrutinio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `localidad`
--

DROP TABLE IF EXISTS `localidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `localidad` (
  `idLocalidad` int NOT NULL AUTO_INCREMENT,
  `Pais` varchar(45) NOT NULL,
  `Comunidad_Autonoma` varchar(45) NOT NULL,
  `Provincia` varchar(45) NOT NULL,
  `Municipio` varchar(45) NOT NULL,
  `Codigo_Postal` varchar(45) NOT NULL,
  PRIMARY KEY (`idLocalidad`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `localidad`
--

LOCK TABLES `localidad` WRITE;
/*!40000 ALTER TABLE `localidad` DISABLE KEYS */;
INSERT INTO `localidad` VALUES (1,'Espa??a','Castilla y Leon','Leon','Leon','24007'),(2,'Espa??a','Asturias','Asturias','Gijon','24007'),(3,'Espa??a','Madrid','Madrid','Madrid','24007'),(4,'Espa??a','Castilla y Leon','Leon','Valencia de don Juan','24007');
/*!40000 ALTER TABLE `localidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partidos`
--

DROP TABLE IF EXISTS `partidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `partidos` (
  `idPartidos` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) DEFAULT NULL,
  `Elecciones_idElecciones` int NOT NULL,
  `ProgramaElectoral` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idPartidos`),
  KEY `fk_Partidos_Elecciones1_idx` (`Elecciones_idElecciones`),
  CONSTRAINT `fk_Partidos_Elecciones1` FOREIGN KEY (`Elecciones_idElecciones`) REFERENCES `elecciones` (`idElecciones`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partidos`
--

LOCK TABLES `partidos` WRITE;
/*!40000 ALTER TABLE `partidos` DISABLE KEYS */;
INSERT INTO `partidos` VALUES (1,'PP',1,'https://www.pp.es/'),(5,'PSOE',1,'https://www.psoe.es/'),(7,'Podemos',1,'https://podemos.info/'),(8,'Ciudadanos',1,'https://www.ciudadanos-cs.org/'),(9,'Ciudadanos',2,'https://www.ciudadanos-cs.org/'),(10,'PP',2,'https://www.pp.es/'),(11,'PSOE',2,'https://www.psoe.es/'),(14,'PP',3,'https://www.pp.es/'),(16,'PSOE',3,'https://www.psoe.es/'),(18,'Podemos',4,'https://podemos.info/'),(19,'Ciudadanos',4,'https://www.ciudadanos-cs.org/'),(20,'PP',4,'https://www.pp.es/'),(21,'PSOE',4,'https://www.psoe.es/'),(23,'Ciudadanos',5,'https://www.ciudadanos-cs.org/'),(24,'Podemos',6,'https://podemos.info/'),(26,'PSOE',2,'https://www.psoe.es/'),(27,'PP',5,'https://www.pp.es/'),(28,'PP',6,'https://www.pp.es/'),(29,'Podemos',5,'https://podemos.info/'),(30,'Ciudadanos',6,'https://www.ciudadanos-cs.org/');
/*!40000 ALTER TABLE `partidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personas`
--

DROP TABLE IF EXISTS `personas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personas` (
  `idPersona` int NOT NULL AUTO_INCREMENT,
  `DNI` varchar(9) NOT NULL,
  `Nombre` varchar(45) NOT NULL,
  `Primer_Apellido` varchar(45) NOT NULL,
  `Segundo_Apellido` varchar(45) DEFAULT NULL,
  `Pasaporte` varchar(45) DEFAULT NULL,
  `idLocalidad` int NOT NULL,
  PRIMARY KEY (`idPersona`),
  KEY `fk_Personas_Localidad_idx` (`idLocalidad`),
  CONSTRAINT `fk_Personas_Localidad` FOREIGN KEY (`idLocalidad`) REFERENCES `localidad` (`idLocalidad`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personas`
--

LOCK TABLES `personas` WRITE;
/*!40000 ALTER TABLE `personas` DISABLE KEYS */;
INSERT INTO `personas` VALUES (1,'71481685P','Mario','Alvarez','Iglesias',NULL,2),(2,'71455269P','Roberto','Diaz','Menedez',NULL,1),(3,'75849658P','Diego','Narciandi','Rodriguez',NULL,1),(4,'76598548P','Sergio','P??rez','Blanco',NULL,2),(5,'72496859P','Adolfo','De la fuente','Herrera',NULL,3),(6,'78596358P','Diego','Martinez','Martinez',NULL,4),(7,'74589685P','Jaime','Herrera','D??az',NULL,3);
/*!40000 ALTER TABLE `personas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `representantes`
--

DROP TABLE IF EXISTS `representantes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `representantes` (
  `idRepresentantes` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  `Primer_Apellido` varchar(45) NOT NULL,
  `Segundo_Apellido` varchar(45) DEFAULT NULL,
  `Partidos_idPartidos` int NOT NULL,
  `Numero_Lista` int DEFAULT NULL,
  PRIMARY KEY (`idRepresentantes`),
  KEY `fk_Representantes_Partidos1_idx` (`Partidos_idPartidos`),
  CONSTRAINT `fk_Representantes_Partidos1` FOREIGN KEY (`Partidos_idPartidos`) REFERENCES `partidos` (`idPartidos`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `representantes`
--

LOCK TABLES `representantes` WRITE;
/*!40000 ALTER TABLE `representantes` DISABLE KEYS */;
INSERT INTO `representantes` VALUES (1,'Pedro','Sanchez','P??rez-Castej??n',5,1),(2,'Alberto','N????ez','Feij??o',1,1),(3,'In??s','Arrimadas','Garc??a',8,1),(4,'Ione','Belarra','Urteaga',8,1),(5,'Jos??','Guirao','Cabrera',5,2),(6,'Sonia','Ferrer','Tesoro',5,3),(7,'Indalecio','Guti??rrez','Salinas',5,4),(8,'Marina','Bravo','Sobrino',8,2),(9,'Edmundo','Bal','Franc??s',8,3);
/*!40000 ALTER TABLE `representantes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `voto`
--

DROP TABLE IF EXISTS `voto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `voto` (
  `idVoto` int NOT NULL AUTO_INCREMENT,
  `Voto` varchar(45) NOT NULL,
  `Localidad_idLocalidad` int NOT NULL,
  `Elecciones_idElecciones` int NOT NULL,
  PRIMARY KEY (`idVoto`),
  KEY `fk_Voto_Localidad1_idx` (`Localidad_idLocalidad`),
  KEY `fk_Voto_Elecciones1_idx` (`Elecciones_idElecciones`),
  CONSTRAINT `fk_Voto_Elecciones1` FOREIGN KEY (`Elecciones_idElecciones`) REFERENCES `elecciones` (`idElecciones`),
  CONSTRAINT `fk_Voto_Localidad1` FOREIGN KEY (`Localidad_idLocalidad`) REFERENCES `localidad` (`idLocalidad`)
) ENGINE=InnoDB AUTO_INCREMENT=206 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voto`
--

LOCK TABLES `voto` WRITE;
/*!40000 ALTER TABLE `voto` DISABLE KEYS */;
INSERT INTO `voto` VALUES (20,'PP',1,4),(21,'PP',1,4),(22,'PP',1,4),(23,'PP',1,4),(24,'PP',1,4),(25,'PP',1,4),(26,'PP',1,4),(27,'PP',1,4),(28,'PP',1,4),(29,'PP',1,4),(30,'PP',1,4),(31,'PP',1,4),(32,'PP',1,4),(33,'PP',1,4),(34,'PP',1,4),(35,'PP',1,4),(36,'PP',1,4),(37,'PP',1,4),(38,'PP',1,4),(39,'PP',1,4),(40,'PP',1,4),(41,'PP',1,4),(42,'PP',1,4),(43,'PP',1,4),(44,'PP',1,4),(45,'PP',1,4),(46,'PP',1,4),(47,'PP',1,4),(48,'PP',1,4),(49,'PP',1,4),(50,'PP',1,4),(51,'PP',1,4),(52,'PP',1,4),(53,'PP',1,4),(54,'PP',1,4),(55,'PP',1,4),(56,'PP',1,4),(57,'PP',1,4),(58,'PP',1,4),(59,'PSOE',2,4),(60,'PSOE',2,4),(61,'PSOE',2,4),(62,'PSOE',2,4),(63,'PSOE',2,4),(64,'PSOE',2,4),(65,'PSOE',2,4),(66,'PSOE',2,4),(67,'PSOE',2,4),(68,'PSOE',2,4),(69,'PSOE',2,4),(70,'PSOE',2,4),(71,'PSOE',2,4),(72,'PSOE',2,4),(73,'PSOE',2,4),(74,'PSOE',2,4),(75,'PSOE',2,4),(76,'PSOE',2,4),(77,'PSOE',2,4),(78,'PSOE',2,4),(79,'PSOE',2,4),(80,'PSOE',2,4),(81,'PSOE',2,4),(82,'PSOE',2,4),(83,'PSOE',2,4),(84,'PSOE',2,4),(85,'PSOE',2,4),(86,'PSOE',2,4),(87,'PSOE',2,4),(88,'PSOE',2,4),(89,'PSOE',2,4),(90,'PSOE',2,4),(91,'PSOE',2,4),(92,'PSOE',2,4),(93,'PSOE',2,4),(94,'PSOE',2,4),(95,'PSOE',2,4),(96,'PSOE',2,4),(97,'PSOE',2,4),(98,'PSOE',2,4),(99,'PSOE',2,4),(100,'PSOE',2,4),(101,'PSOE',2,4),(102,'PSOE',2,4),(103,'PSOE',2,4),(104,'PSOE',2,4),(105,'PSOE',2,4),(106,'PSOE',2,4),(107,'PSOE',2,4),(108,'PSOE',2,4),(109,'PSOE',2,4),(110,'PSOE',2,4),(111,'PSOE',2,4),(112,'PSOE',2,4),(113,'PSOE',2,4),(114,'PSOE',2,4),(115,'PSOE',2,4),(116,'PSOE',2,4),(117,'Ciudadanos',3,4),(118,'Ciudadanos',3,4),(119,'Ciudadanos',3,4),(120,'Ciudadanos',3,4),(121,'Ciudadanos',3,4),(122,'Ciudadanos',3,4),(123,'Ciudadanos',3,4),(124,'Ciudadanos',3,4),(125,'Ciudadanos',3,4),(126,'Ciudadanos',3,4),(127,'Ciudadanos',3,4),(128,'Ciudadanos',3,4),(129,'Ciudadanos',3,4),(130,'Ciudadanos',3,4),(131,'Ciudadanos',3,4),(132,'Ciudadanos',3,4),(133,'Ciudadanos',3,4),(134,'Ciudadanos',3,4),(135,'Ciudadanos',3,4),(136,'Ciudadanos',3,4),(137,'Ciudadanos',3,4),(138,'Ciudadanos',3,4),(139,'Ciudadanos',3,4),(140,'Ciudadanos',3,4),(164,'Podemos',4,4),(165,'Podemos',4,4),(166,'Podemos',4,4),(167,'Podemos',4,4),(168,'Podemos',4,4),(169,'Podemos',4,4),(170,'Podemos',4,4),(171,'Podemos',4,4),(172,'Podemos',4,4),(173,'Podemos',4,4),(174,'Podemos',4,4),(175,'Podemos',4,4),(176,'Podemos',4,4),(177,'Podemos',4,4),(178,'Podemos',4,4),(179,'Podemos',4,4),(180,'Podemos',4,4),(181,'Podemos',4,4),(182,'Podemos',4,4),(183,'Podemos',4,4),(184,'Podemos',4,4),(185,'Podemos',4,4),(186,'Podemos',4,4),(187,'Podemos',4,4),(188,'Podemos',4,4),(189,'Podemos',4,4),(190,'Podemos',4,4),(191,'Podemos',4,4),(192,'Podemos',4,4),(193,'Podemos',4,4),(194,'Podemos',4,4),(195,'Podemos',4,4),(196,'Podemos',4,4),(197,'Podemos',4,4),(198,'Podemos',4,4),(199,'Podemos',4,4),(200,'Podemos',4,4),(201,'Podemos',4,4),(202,'Podemos',4,4),(203,'Podemos',4,4),(204,'Podemos',4,4),(205,'Podemos',4,4);
/*!40000 ALTER TABLE `voto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'mydb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-05  1:04:13
