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
INSERT INTO `confirmacionvoto` VALUES (1,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `elecciones`
--

LOCK TABLES `elecciones` WRITE;
/*!40000 ALTER TABLE `elecciones` DISABLE KEYS */;
INSERT INTO `elecciones` VALUES (1,'26/05/2022','Autonomicas',1),(2,'Wed May 04 00:00:00 CEST 2022','Generales',1),(3,'Wed May 25 00:00:00 CEST 2022','Generales',3),(4,'4/4/122','Generales',1),(5,'4/4/2022','Generales',1),(6,'2/4/2022','Generales',1),(7,'2/4/2022','Generales',1),(8,'2/1/2022','Generales',1),(9,'1/4/2022','Generales',1),(10,'2/4/2022','Generales',1);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `escrutinio`
--

LOCK TABLES `escrutinio` WRITE;
/*!40000 ALTER TABLE `escrutinio` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `localidad`
--

LOCK TABLES `localidad` WRITE;
/*!40000 ALTER TABLE `localidad` DISABLE KEYS */;
INSERT INTO `localidad` VALUES (1,'A','B','C','D','E'),(2,'A','AA','AAA','AAAA','AAAAA'),(3,'A','AB','AAB','AAAB','AAAAB'),(4,'A','AC','AAC','AAAC','AAAC');
/*!40000 ALTER TABLE `localidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partidos`
--

DROP TABLE IF EXISTS `partidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `partidos` (
  `idPartidos` int NOT NULL,
  `Nombre` varchar(45) DEFAULT NULL,
  `Elecciones_idElecciones` int NOT NULL,
  `ProgramaElectoral` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idPartidos`),
  KEY `fk_Partidos_Elecciones1_idx` (`Elecciones_idElecciones`),
  CONSTRAINT `fk_Partidos_Elecciones1` FOREIGN KEY (`Elecciones_idElecciones`) REFERENCES `elecciones` (`idElecciones`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partidos`
--

LOCK TABLES `partidos` WRITE;
/*!40000 ALTER TABLE `partidos` DISABLE KEYS */;
INSERT INTO `partidos` VALUES (1,'PP',1,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personas`
--

LOCK TABLES `personas` WRITE;
/*!40000 ALTER TABLE `personas` DISABLE KEYS */;
INSERT INTO `personas` VALUES (1,'A','B','C','D','E',1);
/*!40000 ALTER TABLE `personas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `representantes`
--

DROP TABLE IF EXISTS `representantes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `representantes` (
  `idRepresentantes` int NOT NULL,
  `Nombre` varchar(45) NOT NULL,
  `Primer_Apellido` varchar(45) NOT NULL,
  `Segundo_Apellido` varchar(45) DEFAULT NULL,
  `Partidos_idPartidos` int NOT NULL,
  `Numero_Lista` int DEFAULT NULL,
  PRIMARY KEY (`idRepresentantes`),
  KEY `fk_Representantes_Partidos1_idx` (`Partidos_idPartidos`),
  CONSTRAINT `fk_Representantes_Partidos1` FOREIGN KEY (`Partidos_idPartidos`) REFERENCES `partidos` (`idPartidos`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `representantes`
--

LOCK TABLES `representantes` WRITE;
/*!40000 ALTER TABLE `representantes` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voto`
--

LOCK TABLES `voto` WRITE;
/*!40000 ALTER TABLE `voto` DISABLE KEYS */;
INSERT INTO `voto` VALUES (1,'VoX',1,1),(2,'1',1,1),(3,'1',1,1);
/*!40000 ALTER TABLE `voto` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-03 17:28:45
