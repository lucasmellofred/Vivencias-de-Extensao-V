CREATE DATABASE  IF NOT EXISTS `teste` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `teste`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: teste
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `acao_social`
--

DROP TABLE IF EXISTS `acao_social`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `acao_social` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data_final` datetime(6) DEFAULT NULL,
  `data_inicial` datetime(6) DEFAULT NULL,
  `local` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acao_social`
--

LOCK TABLES `acao_social` WRITE;
/*!40000 ALTER TABLE `acao_social` DISABLE KEYS */;
INSERT INTO `acao_social` VALUES (1,'2024-07-15 00:00:00.000000','2024-07-01 00:00:00.000000','Joinville','Primeira ação social',NULL),(2,'2024-07-31 00:00:00.000000','2024-07-02 00:00:00.000000','Itajaí','Segunda ação social',NULL),(3,'2024-09-30 00:00:00.000000','2024-06-07 00:00:00.000000','Curitiba','Terceira ação social',NULL),(5,'2024-07-02 00:00:00.000000','2024-07-01 00:00:00.000000','Pirabeiraba','Quinta ação social',NULL),(6,'2024-07-03 00:00:00.000000','2024-07-02 00:00:00.000000','São Paulo','Sexta ação social',NULL),(7,NULL,NULL,NULL,'Lucas Frederico Roeder de Mello','h');
/*!40000 ALTER TABLE `acao_social` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `administrador`
--

DROP TABLE IF EXISTS `administrador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrador` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ativo` bit(1) NOT NULL,
  `data_expiracao_token_redefinicao_senha` datetime(6) DEFAULT NULL,
  `data_login` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `lembrar_de_mim` bit(1) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  `telefone` varchar(255) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `token_protecao` varchar(255) DEFAULT NULL,
  `token_redefinicao_senha` varchar(255) DEFAULT NULL,
  `token_seguranca` varchar(255) DEFAULT NULL,
  `token_seguro_volatil` varchar(255) DEFAULT NULL,
  `token_servidor` varchar(255) DEFAULT NULL,
  `token_servidor_volatil` varchar(255) DEFAULT NULL,
  `ultima_data_envio_email_redefinicao_senha` datetime(6) DEFAULT NULL,
  `token_ativacao_conta` varchar(255) DEFAULT NULL,
  `data_expiracao_token_ativacao` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrador`
--

LOCK TABLES `administrador` WRITE;
/*!40000 ALTER TABLE `administrador` DISABLE KEYS */;
INSERT INTO `administrador` VALUES (1,_binary '',NULL,'2024-07-10 08:25:47.746796','www.lucasmello@gmail.com',_binary '\0','Lucas Frederico Roeder de Mello','$2a$10$0HfjzyWeo/HB7H7OdDK9ruhJqxbY0Dw52bJgoG4W2we/iqla82qW6','(12) 34567-8901','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ3d3cubHVjYXNtZWxsb0BnbWFpbC5jb20iLCJleHAiOjE3MjA2MTI1NDd9.dunQWwjyf7Y6k3mcJiEbL6X2ggOYx8X8btQHAvPB3x0','eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJ3d3cubHVjYXNtZWxsb0BnbWFpbC5jb20iLCJleHAiOjE3MjA2MTI1NDd9.h9lCOme6PGg9IK5yy7KaDCvFgzKa2Ef7sXx9IsTVEn9O8VWBszcT6CfkU8KFDGkD',NULL,'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJ3d3cubHVjYXNtZWxsb0BnbWFpbC5jb20iLCJleHAiOjE3MjA2MTI1NDd9.GAQFcGwV-ngiwEWNZYIJat2NawLJypKBV2oce3GZDdSxl_JDJO_KSDvjDj0MDWj8','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ3d3cubHVjYXNtZWxsb0BnbWFpbC5jb20iLCJleHAiOjE3MjA2MTI1NDd9.Ma8kTMUZAiWn5lkud_7de_TRFL0DR6p_ZizE7wyUK2dWN_pBGS7rNkH8mAlq57JwZq2e5UCFRyNAo2Q7ZlQgIg','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ3d3cubHVjYXNtZWxsb0BnbWFpbC5jb20iLCJleHAiOjE3MjA2MTI1NDd9.NgZidW7RnLPhzhHHr7nnuXx0s0D145e2JuaOSMpAHC0','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ3d3cubHVjYXNtZWxsb0BnbWFpbC5jb20iLCJleHAiOjE3MjA2MTI1NDd9.xSwPALbgyceyhv-OXi4848RCr9z1iKm6x1H9sBiY0-j144qoK4VgwsC4p57U-yMPL60QE72UPNmEM8RGvsXR6g',NULL,NULL,NULL),(2,_binary '\0',NULL,'2024-07-03 17:47:21.748551','teste2@gmail.com',_binary '\0','Testador 2','$2a$10$b2IH6gMiyl8rpMkzJZzMLumQFH/OkfhxiJXwetQ5w.5eaF8qerA.y','(92) 92929-2929',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(3,_binary '\0',NULL,NULL,'teste3@gmail.com',NULL,'Testador 3','$2a$10$ScGYRZQhZ0szKUNV4KVXVeqD8jQtk3ujscNDu4ex/m6YWAar87tV6','(71) 71717-1717',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `administrador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `acao_social_id` bigint DEFAULT NULL,
  `nome` varchar(1000) NOT NULL,
  `categoria` varchar(255) DEFAULT NULL,
  `codigo_identificacao` varchar(255) DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `info_adicional` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKi906g6nqfjqtetmw67v9dhl4v` (`acao_social_id`),
  CONSTRAINT `FKi906g6nqfjqtetmw67v9dhl4v` FOREIGN KEY (`acao_social_id`) REFERENCES `acao_social` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (1,1,'Roupa',NULL,NULL,NULL,NULL),(2,1,'ADAWDWDadawd',NULL,NULL,NULL,NULL),(3,1,'LEITE',NULL,NULL,NULL,NULL),(4,1,'Arroz',NULL,NULL,NULL,NULL),(5,1,'Arroz','Alimento','123','Muito arroz','3 KG'),(6,1,'BANANA','Alimento','321','MUITAS','3KG');
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `organograma`
--

DROP TABLE IF EXISTS `organograma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `organograma` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `quantidade_necessaria` int NOT NULL,
  `quantidade_pronta` int NOT NULL,
  `acao_social_id` bigint DEFAULT NULL,
  `item_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKns9n4r43tndnwraboeykh7ogs` (`acao_social_id`),
  KEY `FK24j8e45ogdpdg7s0yhjj0cbum` (`item_id`),
  CONSTRAINT `FK24j8e45ogdpdg7s0yhjj0cbum` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`),
  CONSTRAINT `FKns9n4r43tndnwraboeykh7ogs` FOREIGN KEY (`acao_social_id`) REFERENCES `acao_social` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `organograma`
--

LOCK TABLES `organograma` WRITE;
/*!40000 ALTER TABLE `organograma` DISABLE KEYS */;
/*!40000 ALTER TABLE `organograma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'teste'
--

--
-- Dumping routines for database 'teste'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-10  9:01:29
