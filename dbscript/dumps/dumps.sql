-- MySQL dump 10.13  Distrib 5.7.9, for Win32 (AMD64)
--
-- Host: 127.0.0.1    Database: tkbaru
-- ------------------------------------------------------
-- Server version	5.6.28-log

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
-- Table structure for table `tb_bank`
--

DROP TABLE IF EXISTS `tb_bank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_bank` (
  `bank_id` int(11) NOT NULL AUTO_INCREMENT,
  `store_id` int(11) DEFAULT '0',
  `bank_code` varchar(45) DEFAULT NULL,
  `acc_num` varchar(45) DEFAULT NULL,
  `acc_holder_name` varchar(45) DEFAULT NULL,
  `currency_code` varchar(45) DEFAULT NULL,
  `trx_date` datetime DEFAULT NULL,
  `trx_desc` varchar(450) DEFAULT NULL,
  `trx_branch` varchar(45) DEFAULT NULL,
  `trx_amt` bigint(11) DEFAULT NULL,
  `trx_direction` varchar(45) DEFAULT NULL,
  `trx_balance` bigint(11) DEFAULT NULL,
  `beginning_balance` bigint(11) DEFAULT NULL,
  `credit_ttl_amt` bigint(11) DEFAULT NULL,
  `debit_ttl_amt` bigint(11) DEFAULT NULL,
  `ending_balance` bigint(11) DEFAULT NULL,
  `upload_filename` varchar(45) DEFAULT NULL,
  `saved_filename` varchar(45) DEFAULT NULL,
  `created_by` int(11) DEFAULT '0',
  `created_date` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT '0',
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`bank_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_bank`
--

LOCK TABLES `tb_bank` WRITE;
/*!40000 ALTER TABLE `tb_bank` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_bank` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_bankacc`
--

DROP TABLE IF EXISTS `tb_bankacc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_bankacc` (
  `bankacc_id` int(11) NOT NULL AUTO_INCREMENT,
  `short_name` varchar(45) DEFAULT NULL,
  `bank_name` varchar(45) DEFAULT NULL,
  `account` int(11) DEFAULT NULL,
  `remarks` varchar(45) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `created_by` int(11) DEFAULT '0',
  `created_date` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT '0',
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`bankacc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_bankacc`
--

LOCK TABLES `tb_bankacc` WRITE;
/*!40000 ALTER TABLE `tb_bankacc` DISABLE KEYS */;
INSERT INTO `tb_bankacc` VALUES (2,'BCA','BANK CENTRAL ASIA',9933882,'','L001_A',NULL,NULL,NULL,NULL),(3,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(5,'BCA','BANK CENTRAL ASIA',123,'','L001_A',0,NULL,0,NULL),(6,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(7,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(8,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(9,'BCA','BANK CENTRAL ASIA',0,'','',0,NULL,0,NULL),(10,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(11,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(12,'BCA','BANK CENTRAL ASIA',0,'','L001_A',NULL,NULL,NULL,NULL),(13,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(14,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(15,'BCA','BANK CENTRAL ASIA',0,'','',0,NULL,0,NULL),(16,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(17,'BCA','BANK CENTRAL ASIA',0,'','',0,NULL,0,NULL),(18,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(19,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(20,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(21,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(22,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(23,'BCA','BANK CENTRAL ASIA',0,'','L001_A',NULL,NULL,NULL,NULL),(24,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(25,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(26,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(27,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(28,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(29,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(30,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(31,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(32,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(33,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(34,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(35,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(36,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(37,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(38,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(39,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(40,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(41,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(42,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(43,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(44,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(45,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(46,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(47,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(48,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(49,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(50,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(51,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(52,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(53,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(54,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(55,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(56,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(57,'BCA','BANK CENTRAL ASIA',0,'','',0,NULL,0,NULL),(58,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(59,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(60,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(61,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(62,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(63,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(64,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(65,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(66,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(67,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(68,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(69,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(70,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(71,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(72,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(73,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(74,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(75,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(76,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(77,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(78,'BCA','BANK CENTRAL ASIA',0,'','L001_A',0,NULL,0,NULL),(79,'BDI','Bank Danamon Indonesia',123456,'0','L001_A',0,NULL,0,NULL);
/*!40000 ALTER TABLE `tb_bankacc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_customer`
--

DROP TABLE IF EXISTS `tb_customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_customer` (
  `customer_id` int(11) NOT NULL AUTO_INCREMENT,
  `store_id` int(11) DEFAULT '0',
  `customer_name` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `npwp_num` varchar(45) DEFAULT NULL,
  `price_level_id` int(11) DEFAULT NULL,
  `status` varchar(15) DEFAULT NULL,
  `remarks` varchar(45) DEFAULT NULL,
  `created_by` int(11) DEFAULT '0',
  `created_date` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT '0',
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_customer`
--

LOCK TABLES `tb_customer` WRITE;
/*!40000 ALTER TABLE `tb_customer` DISABLE KEYS */;
INSERT INTO `tb_customer` VALUES (1,1,'TOKO SUMBER URIP','WANGON','WANGON','0','',1,'L001_A','',0,NULL,0,NULL),(2,1,'SABEX PUTRA KALI BEREM','WANGON','WANGON','081391234700','',1,'L001_A','',0,NULL,0,NULL),(3,1,'TOKO PLASTIK RIZKI','PASAR KARNA','SIDAREJA','081327289116','',1,'L001_A','',0,NULL,0,NULL),(4,1,'TOKO SAMIJAYA','PASAR AJIBARANG','AJIBARANG','081327451881','',2,'L001_A','',0,NULL,0,NULL),(5,1,'IBU WAGIYAH','SIDAREJA','SIDAREJA','081391211203','',1,'L001_A','',0,NULL,0,NULL),(6,1,'TOKO TEGAL','PURWOKERTO','PURWOKERTO','0281631917','',1,'L001_A','',0,NULL,0,NULL),(7,1,'BAPAK NUH','KELAPA GADING','WANGON','085740568486','',1,'L001_A','',0,NULL,0,NULL),(8,1,'IBU NAP','JL CIKONDANG NO 25','CILACAP','NA','',1,'L001_A','',NULL,NULL,1,'2015-11-24 12:32:52'),(9,1,'TOKO LUMINTIR','MAJENANG','MAJENANG','085643415900','',1,'L001_A','',0,NULL,0,NULL),(10,1,'KIOS BERKAH','WANGON','WANGON','081914921112','',1,'L001_A','',0,NULL,0,NULL),(11,1,'BAPAK HJ TAMAR','SIDAREJA','SIDAREJA','NA','',1,'L001_A','',0,NULL,0,NULL),(12,1,'IBU WARISEM','TAMBAK SARI','TAMBAK SARI','081225652817','',1,'L001_A','',0,NULL,0,NULL),(13,1,'IBU DARSO','KARANG PUCUNG','KARANG PUCUNG','081548846892','',1,'L001_A','',0,NULL,0,NULL),(14,1,'IBU NIKEM','ADIRAJA','ADIRAJA','081578476506','',1,'L001_A','',0,NULL,0,NULL),(15,1,'BAPAK SIGIT','CIPARI','CIPARI','085227142999','',1,'L001_A','',0,NULL,0,NULL),(16,1,'BAPAK JARWO','PURWOJATI','PURWOJATI','082135012932','',1,'L001_A','',0,NULL,0,NULL),(17,1,'BAPAK TRI SB','PURWOJATI','PURWOJATI','082135030318','',1,'L001_A','',0,NULL,0,NULL),(18,1,'IBU RISEM','PURWOJATI','PURWOJATI','081225652817','',1,'L001_A','',0,NULL,0,NULL),(19,1,'IBU HJ IJAH','MAJENANG','MAJENANG','087736844426','',1,'L001_A','',0,NULL,0,NULL),(20,1,'BAPAK CASMANTO','WANGON','WANGON','081226646116','',1,'L001_A','',NULL,NULL,1,'2016-01-01 16:30:53'),(21,1,'IBU MAMI','ADIPALA','ADIPALA','02825264170','',1,'L001_A','',0,NULL,0,NULL),(22,1,'IBU DARISEM','CILACAP','CILACAP','081542933943','',1,'L001_A','',0,NULL,0,NULL),(23,1,'TOKO MAKRO','WANGON','WANGON','081903269816','',1,'L001_A','',0,NULL,0,NULL),(24,1,'BAPAK BANGKIT','WANGON','WANGON','0816691765','',1,'L001_A','',0,NULL,0,NULL),(25,1,'IBU PARTIN','GRISANG','GRISANG','081391562070','',1,'L001_A','',0,NULL,0,NULL),(26,1,'IBU SAMINAH','BANJAR','BANJAR','081323043146','',1,'L001_A','',0,NULL,0,NULL),(27,1,'UD TUNAS INTI','SEBELAH TIMUR PASAR PURWOJATI','PURWOJATI','081548837883','',1,'L001_A','',0,NULL,0,NULL),(28,1,'BAPAK MANTO','CITEPUS','CITEPUS','081804751327','',1,'L001_A','',0,NULL,0,NULL),(29,1,'BAPAK HJ WONO','WANGON','WANGON','085293627878','',1,'L001_A','',0,NULL,0,NULL),(30,1,'TOKO LARIS','WANGON','WANGON','087837027414','',1,'L001_A','',0,NULL,0,NULL),(31,1,'BAPAK ASRUL','SIDAREJA','SIDAREJA','02807111170','',1,'L001_A','',0,NULL,0,NULL),(32,1,'TOKO KUAT','TAMBAK SARI','TAMBAK SARI','081391415252','',1,'L001_A','',0,NULL,0,NULL),(33,1,'TOKO SARI BUMI','MAJENANG','MAJENANG','0816837689','',1,'L001_A','',0,NULL,0,NULL),(34,1,'BAPAK UUS','BANJAR','BANJAR','085324842929','',1,'L001_A','',0,NULL,0,NULL),(35,1,'KUN YAN','SIDAREJA','SIDAREJA','081341953507','',1,'L001_A','',0,NULL,0,NULL),(36,1,'BAPAK DAWUD','GRISENG','GRISENG','085647820666','',1,'L001_A','',0,NULL,0,NULL),(37,1,'IBU WARSUN','BANJAR','BANJAR','081323408383','',1,'L001_A','',0,NULL,0,NULL),(38,1,'TOKO SARWADADI','JL YOS SUDARSO 60','MAJENANG','0281621629','',1,'L001_A','',0,NULL,0,NULL),(39,1,'BAPAK AHMAD SALEH','WANGON','WANGON','081542790895','',1,'L001_A','',0,NULL,0,NULL),(40,1,'IBU MA\'MUN','SIDAREJA','SIDAREJA','082133269403','',1,'L001_A','',0,NULL,0,NULL),(41,1,'BAPAK NGADIMAN','WANGON','WANGON','081903247095','',1,'L001_A','',0,NULL,0,NULL),(42,1,'BAPAK KODARI','CILACAP','CILACAP','0282547669','',1,'L001_A','',0,NULL,0,NULL),(43,1,'IBU DAKUM','CITEPUS','CITEPUS','085842547321','',1,'L001_A','',0,NULL,0,NULL),(44,1,'IBU AI','TIPAR','TIPAR','081226347038','',1,'L001_A','',0,NULL,0,NULL),(45,1,'BAPAK TONI','CILACAP','CILACAP','085726573770','',1,'L001_A','',0,NULL,0,NULL),(46,1,'IBU NUR','CILACAP','CILACAP','085647898116','',1,'L001_A','',0,NULL,0,NULL),(47,1,'BAPAK HJ RAHMAT','NA','NA','0282697105','',1,'L001_A','',0,NULL,0,NULL),(48,1,'BAPAK TONO','KEBUMEN','KEBUMEN','085729054227','',1,'L001_A','',0,NULL,0,NULL),(49,1,'TOKO PANEN','KAWUNGANTEN','KAWUNGANTEN','0282611949','',1,'L001_A','',0,NULL,0,NULL),(50,1,'BAPAK ABDUL MAJID','WANGON','WANGON','081914953665','',1,'L001_A','',0,NULL,0,NULL),(51,1,'BAPAK TAMAT','NA','NA','081327321997','',1,'L001_A','',0,NULL,0,NULL),(52,1,'BAPAK YATNO','PURWOJATI','PURWOJATI','085726320875','',1,'L001_A','',0,NULL,0,NULL),(53,1,'TOKO BUMI NIAGA','PASAR WANGON','WANGON','0816691768','',1,'L001_A','',0,NULL,0,NULL),(54,1,'BAPAK MUJIB','SIDAREJA','SIDAREJA','0280523811','',1,'L001_A','',0,NULL,0,NULL),(55,1,'BAPAK HADI','CILACAP','CILACAP','085227129170','',1,'L001_A','',0,NULL,0,NULL),(56,1,'BAPAK WARIS','SIDAREJA','SIDAREJA','02806260620','',1,'L001_A','',0,NULL,0,NULL),(57,1,'BAPAK DWI','NA','NA','0280623704','',1,'L001_A','',0,NULL,0,NULL),(58,1,'BAPAK HJ EMAN','BANJAR','BANJAR','081222828500','',1,'L001_A','',0,NULL,0,NULL),(59,1,'IBU UMI','NA','NA','02816847799','',1,'L001_A','',0,NULL,0,NULL),(60,1,'BAPAK SUNAR','CILACAP','CILACAP','0282533219','',1,'L001_A','',0,NULL,0,NULL),(61,1,'BAPAK SIRAN','WANGON','WANGON','081327310077','',1,'L001_A','',0,NULL,0,NULL),(62,1,'IBU KIM HWA','NA','NA','0282533996','',1,'L001_A','',0,NULL,0,NULL),(63,1,'BAPAK MIJO','NA','NA','082134881000','',1,'L001_A','',0,NULL,0,NULL),(64,1,'IBU PANDA','NA','NA','081327113462','',1,'L001_A','',0,NULL,0,NULL),(65,1,'TOKO TERUS JAYA','CILACAP','CILACAP','NA','',1,'L001_A','',0,NULL,0,NULL),(66,1,'TOKO ABADI','NA','NA','0816674561','',1,'L001_A','',0,NULL,0,NULL),(67,1,'IBU ENNY/ CAYHIM','KARANG PUCUNG','KARANG PUCUNG','0816693833','',1,'L001_A','',0,NULL,0,NULL),(68,1,'BAPAK WANTO','NA','NA','081391408635','',1,'L001_A','',0,NULL,0,NULL),(69,1,'IBU ULI','NA','NA','081548818264','',1,'L001_A','',0,NULL,0,NULL),(70,1,'TOKO PERTRA','NA','NA','082325843034','',1,'L001_A','',0,NULL,0,NULL),(71,1,'TOKO KONG WAN','NA','NA','NA','NA',1,'L001_A','',0,NULL,0,NULL);
/*!40000 ALTER TABLE `tb_customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_customer_bankacc`
--

DROP TABLE IF EXISTS `tb_customer_bankacc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_customer_bankacc` (
  `customer_id` int(11) NOT NULL,
  `bankacc_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_customer_bankacc`
--

LOCK TABLES `tb_customer_bankacc` WRITE;
/*!40000 ALTER TABLE `tb_customer_bankacc` DISABLE KEYS */;
INSERT INTO `tb_customer_bankacc` VALUES (2,6),(3,7),(4,8),(5,9),(6,10),(7,11),(9,13),(11,15),(12,16),(13,17),(14,18),(15,19),(16,20),(18,21),(19,22),(21,24),(22,25),(23,26),(24,27),(25,28),(26,29),(28,31),(29,32),(30,33),(31,34),(34,37),(35,38),(36,39),(37,40),(39,42),(40,43),(41,44),(42,45),(43,46),(44,47),(45,48),(46,49),(47,50),(48,51),(50,53),(51,54),(52,55),(33,36),(53,56),(54,57),(55,58),(56,59),(57,60),(58,61),(59,62),(60,63),(61,64),(62,65),(63,66),(64,67),(65,68),(66,69),(67,70),(68,71),(69,72),(70,73),(71,74),(10,14),(32,35),(27,30),(49,52),(38,41),(1,79),(8,12),(20,23);
/*!40000 ALTER TABLE `tb_customer_bankacc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_customer_pic`
--

DROP TABLE IF EXISTS `tb_customer_pic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_customer_pic` (
  `customer_id` int(11) NOT NULL,
  `person_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_customer_pic`
--

LOCK TABLES `tb_customer_pic` WRITE;
/*!40000 ALTER TABLE `tb_customer_pic` DISABLE KEYS */;
INSERT INTO `tb_customer_pic` VALUES (2,21),(3,22),(4,23),(5,24),(6,25),(7,26),(9,28),(11,31),(12,32),(13,33),(14,34),(15,35),(16,36),(17,37),(18,38),(18,39),(19,40),(21,42),(22,43),(23,44),(24,45),(25,46),(26,47),(28,50),(29,51),(30,52),(31,53),(34,56),(35,57),(36,58),(37,59),(37,60),(39,62),(40,63),(41,64),(42,65),(43,66),(44,67),(45,68),(46,69),(47,70),(48,71),(50,73),(51,74),(52,75),(33,55),(53,76),(54,77),(55,78),(56,79),(57,80),(58,81),(59,82),(60,83),(61,84),(62,85),(63,86),(64,87),(65,88),(66,89),(67,90),(67,91),(68,92),(69,93),(70,94),(71,95),(10,29),(10,30),(32,54),(27,48),(27,49),(49,72),(38,61),(1,20),(8,27),(20,41);
/*!40000 ALTER TABLE `tb_customer_pic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_deliver`
--

DROP TABLE IF EXISTS `tb_deliver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_deliver` (
  `deliver_id` int(11) NOT NULL AUTO_INCREMENT,
  `store_id` int(11) DEFAULT '0',
  `deliver_date` datetime DEFAULT NULL,
  `items_id` int(11) DEFAULT NULL,
  `unit_code` varchar(15) DEFAULT NULL,
  `base_unit_code` varchar(15) DEFAULT NULL,
  `bruto` bigint(11) DEFAULT '0',
  `to_base_bruto` bigint(11) DEFAULT '0',
  `net` bigint(11) DEFAULT '0',
  `to_base_net` bigint(11) DEFAULT '0',
  `tare` bigint(11) DEFAULT '0',
  `to_base_tare` bigint(11) DEFAULT '0',
  `created_by` int(11) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`deliver_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_deliver`
--

LOCK TABLES `tb_deliver` WRITE;
/*!40000 ALTER TABLE `tb_deliver` DISABLE KEYS */;
INSERT INTO `tb_deliver` VALUES (1,1,'2015-12-09 00:00:00',2,'L005_TN','L005_KG',10,10000,NULL,NULL,NULL,NULL,1,'2015-12-09 12:59:28',NULL,NULL),(2,1,'2016-01-21 00:00:00',5,'L005_TN','L005_KG',100,100000,NULL,NULL,NULL,NULL,1,'2016-01-21 09:42:31',NULL,NULL),(3,1,'2016-03-18 00:00:00',1,'L005_KG','L005_KG',9800,9800,NULL,NULL,NULL,NULL,1,'2016-03-18 20:40:08',NULL,NULL),(4,1,'2016-03-18 00:00:00',2,'L005_KG','L005_KG',9800,9800,NULL,NULL,NULL,NULL,1,'2016-03-18 20:53:04',NULL,NULL),(5,1,'2016-03-18 00:00:00',3,'L005_KG','L005_KG',14900,14900,NULL,NULL,NULL,NULL,1,'2016-03-18 20:56:57',NULL,NULL);
/*!40000 ALTER TABLE `tb_deliver` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_function`
--

DROP TABLE IF EXISTS `tb_function`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_function` (
  `function_id` int(11) NOT NULL AUTO_INCREMENT,
  `function_code` varchar(45) DEFAULT NULL,
  `menu_name` varchar(45) DEFAULT NULL,
  `menu_icon` varchar(45) DEFAULT NULL,
  `url` varchar(45) DEFAULT NULL,
  `parent_function_id` int(11) DEFAULT NULL,
  `order_num` int(11) DEFAULT NULL,
  `created_by` int(11) DEFAULT '0',
  `created_date` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT '0',
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`function_id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_function`
--

LOCK TABLES `tb_function` WRITE;
/*!40000 ALTER TABLE `tb_function` DISABLE KEYS */;
INSERT INTO `tb_function` VALUES (1,'F_PO','Purchase Order','fa fa-truck fa-fw','#',NULL,100100,0,'2016-03-18 22:22:14',0,NULL),(2,'F_PO_PURCHASEORDER','Purchase Order','fa fa-truck fa-fw','/po/add',1,100200,0,'2016-03-18 22:22:14',0,NULL),(3,'F_PO_REVISE','Revise PO','fa fa-code-fork fa-rotate-180 fa-fw','/po/revise',1,100300,0,'2016-03-18 22:22:14',0,NULL),(4,'F_PO_PAYMENT','PO Payment','fa fa-calculator fa-fw','/po/payment',1,100400,0,'2016-03-18 22:22:14',0,NULL),(5,'F_SO','Sales Order','fa fa-cart-arrow-down fa-fw','#',NULL,200100,0,'2016-03-18 22:22:14',0,NULL),(6,'F_SO_SALESORDER','Sales Order','fa fa-cart-arrow-down fa-fw','/sales/add',5,200200,0,'2016-03-18 22:22:14',0,NULL),(7,'F_SO_REVISE','Revise SO','fa fa fa-code-fork fa-fw','/sales/revise',5,200300,0,'2016-03-18 22:22:14',0,NULL),(8,'F_SO_PAYMENT','SO Payment','fa fa-calculator fa-fw','/sales/payment',5,200400,0,'2016-03-18 22:22:14',0,NULL),(9,'F_PRICE','Price','fa  fa-barcode fa-fw','#',NULL,300100,0,'2016-03-18 22:22:14',0,NULL),(10,'F_PRICE_TODAYPRICE','Today Price','fa fa-barcode fa-fw','/price/todayprice',9,300200,0,'2016-03-18 22:22:14',0,NULL),(11,'F_PRICE_PRICELEVEL','Price Level','fa  fa-table fa-fw','/price/pricelevel',9,300300,0,'2016-03-18 22:22:14',0,NULL),(12,'F_WH','Warehouse','fa fa-wrench fa-fw','#',NULL,400100,0,'2016-03-18 22:22:14',0,NULL),(13,'F_WH_WAREHOUSE','Dashboard','fa fa-wrench fa-fw','/warehouse/dashboard',12,400200,0,'2016-03-18 22:22:14',0,NULL),(14,'F_WH_STOCK','Stock Report','fa fa-wrench fa-fw','/warehouse/dashboard/stocks',12,400300,0,'2016-03-18 22:22:14',0,NULL),(15,'F_BANK','Bank','fa fa-bank fa-fw','#',NULL,500100,0,'2016-03-18 22:22:14',0,NULL),(16,'F_BANK_UPLOAD','Upload','fa fa-cloud-upload fa-fw','/bank/upload',15,500200,0,'2016-03-18 22:22:14',0,NULL),(17,'F_BANK_CONSOLIDATE','Consolidate','fa fa-compress fa-fw','/bank/consolidate',15,500300,0,'2016-03-18 22:22:14',0,NULL),(18,'F_CUSTOMER','Customer','fa fa-smile-o fa-fw','#',NULL,600100,0,'2016-03-18 22:22:14',0,NULL),(19,'F_CUSTOMER_CONFIRM','Delivery Confirmation','fa fa-check fa-fw','/customer/delivery/confirmation',18,600200,0,'2016-03-18 22:22:14',0,NULL),(20,'F_RPT','Reports','fa fa-bar-chart-o fa-fw','#',NULL,700100,0,'2016-03-18 22:22:14',0,NULL),(21,'F_RPT_RPTTRX','Transactions','fa fa-connectdevelop fa-fw','/report/id/rpttrx',20,700200,0,'2016-03-18 22:22:15',0,NULL),(22,'F_RPT_RPTMNTR','Monitoring','fa fa-eye fa-fw','/report/id/rptmntr',20,700300,0,'2016-03-18 22:22:15',0,NULL),(23,'F_RPT_RPTTAX','Tax Reports','fa fa-institution fa-fw','/report/id/rpttax',20,700400,0,'2016-03-18 22:22:15',0,NULL),(24,'F_RPT_RPTMASTER','Master Data','fa fa-file-text-o fa-fw','/report/id/rptmaster',20,798100,0,'2016-03-18 22:22:15',0,NULL),(25,'F_RPT_RPTADMIN','Admin Data','glyphicon glyphicon-cog','/report/id/rptadmin',20,799100,0,'2016-03-18 22:22:15',0,NULL),(26,'F_MASTER','Master Data','fa fa-file-text-o fa-fw','#',NULL,998100,0,'2016-03-18 22:22:15',0,NULL),(27,'F_MASTER_CUSTOMER','Customer','fa fa-smile-o fa-fw','/customer',26,998100,0,'2016-03-18 22:22:15',0,NULL),(28,'F_MASTER_SUPPLIER','Supplier','fa fa-building-o fa-fw','/supplier',26,998200,0,'2016-03-18 22:22:15',0,NULL),(29,'F_MASTER_PRODUCT','Product','fa fa-cubes fa-fw','/product',26,998300,0,'2016-03-18 22:22:15',0,NULL),(30,'F_MASTER_WAREHOUSE','Warehouse','fa fa-wrench fa-fw','/warehouse',26,998400,0,'2016-03-18 22:22:15',0,NULL),(31,'F_MASTER_TRUCK','Truck','fa fa-truck fa-flip-horizontal fa-fw','/truck',26,998500,0,'2016-03-18 22:22:15',0,NULL),(32,'F_ADM','Admin Menu','glyphicon glyphicon-cog','#',NULL,999100,0,'2016-03-18 22:22:15',0,NULL),(33,'F_ADM_USER','User','fa fa-user fa-fw','/admin/user',32,999100,0,'2016-03-18 22:22:15',0,NULL),(34,'F_ADM_STORE','Store','fa fa-umbrella fa-fw','/admin/store',32,999200,0,'2016-03-18 22:22:15',0,NULL),(35,'F_ADM_ROLE','Role','fa fa-tree fa-fw','/admin/role',32,999300,0,'2016-03-18 22:22:15',0,NULL),(36,'F_ADM_FUNCTION','Function','fa fa-minus-square fa-fw','/admin/function',32,999400,0,'2016-03-18 22:22:15',0,NULL),(37,'F_ADM_LOOKUP','Lookup','fa fa-hand-o-up fa-fw','/admin/lookup',32,999500,0,'2016-03-18 22:22:15',0,NULL),(38,'F_ADM_SMS','SMS Service','fa fa-cog fa-fw','#',32,999600,0,'2016-03-18 22:22:15',0,NULL),(39,'F_ADM_SMS_SERV','Service Status','fa fa-cog fa-fw','/admin/sms',38,999601,0,'2016-03-18 22:22:15',0,NULL),(40,'F_ADM_SMS_IN','SMS Inbox','fa fa-envelope fa-fw','/admin/smsin',38,999602,0,'2016-03-18 22:22:15',0,NULL),(41,'F_ADM_SMS_OUT','SMS Out','fa fa-paper-plane fa-fw','/admin/smsout',38,999603,0,'2016-03-18 22:22:15',0,NULL),(42,'F_ADM_MODEM','Modem','fa fa-cog fa-fw','/admin/modem',38,999604,0,'2016-03-18 22:22:15',0,NULL);
/*!40000 ALTER TABLE `tb_function` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_items`
--

DROP TABLE IF EXISTS `tb_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_items` (
  `items_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) DEFAULT NULL,
  `stocks_id` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `unit_code` varchar(45) DEFAULT NULL,
  `base_unit_code` varchar(45) DEFAULT NULL,
  `to_base_value` int(11) DEFAULT NULL,
  `to_base_qty` int(11) DEFAULT NULL,
  `price` bigint(11) DEFAULT NULL,
  `created_by` int(11) DEFAULT '0',
  `created_date` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT '0',
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`items_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_items`
--

LOCK TABLES `tb_items` WRITE;
/*!40000 ALTER TABLE `tb_items` DISABLE KEYS */;
INSERT INTO `tb_items` VALUES (1,2,NULL,10,'L005_TN','L005_KG',1000,10000,10000,1,'2016-02-10 10:31:04',NULL,NULL),(2,2,NULL,10,'L005_TN','L005_KG',1000,10000,9800,1,'2016-03-18 20:48:28',NULL,NULL),(3,2,NULL,15000,'L005_KG','L005_KG',1,15000,9000,1,'2016-03-18 20:56:31',NULL,NULL),(4,6,NULL,100,'L005_TN','L005_KG',1000,100000,11400,1,'2016-03-18 21:05:27',NULL,NULL),(5,6,NULL,10,'L005_TN','L005_KG',1000,10000,11300,1,'2016-03-18 22:04:16',NULL,NULL);
/*!40000 ALTER TABLE `tb_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_items_deliver`
--

DROP TABLE IF EXISTS `tb_items_deliver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_items_deliver` (
  `items_id` int(11) DEFAULT NULL,
  `deliver_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_items_deliver`
--

LOCK TABLES `tb_items_deliver` WRITE;
/*!40000 ALTER TABLE `tb_items_deliver` DISABLE KEYS */;
INSERT INTO `tb_items_deliver` VALUES (4,2),(5,2),(1,3),(2,4),(3,5);
/*!40000 ALTER TABLE `tb_items_deliver` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_items_receipt`
--

DROP TABLE IF EXISTS `tb_items_receipt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_items_receipt` (
  `items_id` int(11) DEFAULT NULL,
  `receipt_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_items_receipt`
--

LOCK TABLES `tb_items_receipt` WRITE;
/*!40000 ALTER TABLE `tb_items_receipt` DISABLE KEYS */;
INSERT INTO `tb_items_receipt` VALUES (4,1),(5,2);
/*!40000 ALTER TABLE `tb_items_receipt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_lookup`
--

DROP TABLE IF EXISTS `tb_lookup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_lookup` (
  `lookup_id` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(25) DEFAULT NULL,
  `lookup_key` varchar(15) DEFAULT NULL,
  `loc_msg_code` varchar(45) DEFAULT NULL,
  `val` varchar(245) DEFAULT NULL,
  `order_num` int(11) DEFAULT NULL,
  `status` varchar(15) DEFAULT NULL,
  `maintainable` varchar(10) DEFAULT NULL,
  `created_by` int(11) DEFAULT '0',
  `created_date` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT '0',
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`lookup_id`),
  UNIQUE KEY `lookup_key_UNIQUE` (`lookup_key`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_lookup`
--

LOCK TABLES `tb_lookup` WRITE;
/*!40000 ALTER TABLE `tb_lookup` DISABLE KEYS */;
INSERT INTO `tb_lookup` VALUES (1,'STATUS','L001_A','STATUS.L001_A','Active',1,'L001_A','L003_NO',0,'2016-02-12 07:26:35',0,NULL),(2,'STATUS','L001_I','STATUS.L001_I','Inactive',2,'L001_A','L003_NO',0,'2016-02-12 07:26:36',0,NULL),(3,'SALUTATION','L002_MR','SALUTATION.L002_MR','Mr',10,'L001_A','L003_NO',0,'2016-02-12 07:26:37',0,NULL),(4,'SALUTATION','L002_MRS','SALUTATION.L002_MRS','Mrs',11,'L001_A','L003_NO',0,'2016-02-12 07:26:38',0,NULL),(5,'YESNOSELECTION','L003_YES','YESNOSELECTION.L003_YES','Yes',1,'L001_A','L003_NO',0,'2016-02-12 07:26:39',0,NULL),(6,'YESNOSELECTION','L003_NO','YESNOSELECTION.L003_NO','No',2,'L001_A','L003_NO',0,'2016-02-12 07:26:40',0,NULL),(7,'PRODUCT_TYPE','L004_ML','PRODUCT_TYPE.L004_ML','Minyak',1,'L001_A','L003_NO',0,'2016-02-12 07:26:41',0,NULL),(8,'PRODUCT_TYPE','L004_GL','PRODUCT_TYPE.L004_GL','Gula',1,'L001_A','L003_NO',0,'2016-02-12 07:26:41',0,NULL),(9,'PRODUCT_TYPE','L004_TRG','PRODUCT_TYPE.L004_TRG','Terigu',1,'L001_A','L003_NO',0,'2016-02-12 07:26:42',0,NULL),(10,'PRODUCT_TYPE','L004_ACI','PRODUCT_TYPE.L004_ACI','Aci/Tapioka',1,'L001_A','L003_NO',0,'2016-02-12 07:26:43',0,NULL),(11,'PRODUCT_TYPE','L004_KDLL','PRODUCT_TYPE.L004_KDLL','Kedelai Lokal',1,'L001_A','L003_NO',0,'2016-02-12 07:26:44',0,NULL),(12,'PRODUCT_TYPE','L004_KDLI','PRODUCT_TYPE.L004_KDLI','Kedelai Import',1,'L001_A','L003_NO',0,'2016-02-12 07:26:45',0,NULL),(13,'PRODUCT_TYPE','L004_KTN','PRODUCT_TYPE.L004_KTN','Beras Ketan',1,'L001_A','L003_NO',0,'2016-02-12 07:26:46',0,NULL),(14,'UNIT','L005_DR','UNIT.L005_DR','Drum',1,'L001_A','L003_NO',0,'2016-02-12 07:26:47',0,NULL),(15,'UNIT','L005_KG','UNIT.L005_KG','Kilogram',1,'L001_A','L003_NO',0,'2016-02-12 07:26:48',0,NULL),(16,'UNIT','L005_TN','UNIT.L005_TN','Tonne',1,'L001_A','L003_NO',0,'2016-02-12 07:26:49',0,NULL),(17,'UNIT','L005_KN','UNIT.L005_KN','Kuintal',1,'L001_A','L003_NO',0,'2016-02-12 07:26:50',0,NULL),(18,'UNIT','L005_ZK','UNIT.L005_ZK','Zak',1,'L001_A','L003_NO',0,'2016-02-12 07:26:51',0,NULL),(19,'PHONE_PROVIDER','L006_TSEL','PHONE_PROVIDER.L006_TSEL','T-Sel',1,'L001_A','L003_NO',0,'2016-02-12 07:26:52',0,NULL),(20,'PHONE_PROVIDER','L006_XL','PHONE_PROVIDER.L006_XL','Xl',1,'L001_A','L003_NO',0,'2016-02-12 07:26:53',0,NULL),(21,'PHONE_PROVIDER','L006_ISAT','PHONE_PROVIDER.L006_ISAT','I-Sat',1,'L001_A','L003_NO',0,'2016-02-12 07:26:53',0,NULL),(22,'PHONE_PROVIDER','L006_3','PHONE_PROVIDER.L006_3','Three',1,'L001_A','L003_NO',0,'2016-02-12 07:26:54',0,NULL),(23,'PHONE_PROVIDER','L006_TLKM','PHONE_PROVIDER.L006_TLKM','Telkom',1,'L001_A','L003_NO',0,'2016-02-12 07:26:55',0,NULL),(24,'BANK','L007_BCA','BANK.L007_BCA','Bank Central Asia',1,'L001_A','L003_NO',0,'2016-02-12 07:26:56',0,NULL),(25,'BANK','L007_BDI','BANK.L007_BDI','Bank Danamon Indonesia',1,'L001_A','L003_NO',0,'2016-02-12 07:26:57',0,NULL),(26,'TRX_DIRECTION','L008_DB','TRX_DIRECTION.L008_DB','Debit',1,'L001_A','L003_NO',0,'2016-02-12 07:26:58',0,NULL),(27,'TRX_DIRECTION','L008_CR','TRX_DIRECTION.L008_CR','',1,'L001_A','L003_NO',0,'2016-02-12 07:26:59',0,NULL),(28,'CURR_CODE','L009_IDR','CURR_CODE.L009_IDR','IDR',1,'L001_A','L003_NO',0,'2016-02-12 07:27:00',0,NULL),(29,'CURR_CODE','L009_USD','CURR_CODE.L009_USD','USD',1,'L001_A','L003_NO',0,'2016-02-12 07:27:01',0,NULL),(30,'LANGUAGE','L010_ID','LANGUAGE.L010_ID','Indonesian',1,'L001_A','L003_NO',0,'2016-02-12 07:27:02',0,NULL),(31,'LANGUAGE','L010_EN','LANGUAGE.L010_EN','English',1,'L001_A','L003_NO',0,'2016-02-12 07:27:03',0,NULL),(32,'TRUCK_TYPE','L011_OIL','TRUCK_TYPE.L011_OIL','Oil Truck',1,'L001_A','L003_NO',0,'2016-02-12 07:27:04',0,NULL),(33,'TRUCK_TYPE','L011_CARGO','TRUCK_TYPE.L011_CARGO','Cargo Truck',1,'L001_A','L003_NO',0,'2016-02-12 07:27:05',0,NULL),(34,'WEIGHT_TYPE','L012_MED','WEIGHT_TYPE.L012_MED','Medium Truck (6T)',1,'L001_A','L003_NO',0,'2016-02-12 07:27:06',0,NULL),(35,'WEIGHT_TYPE','L012_HEAVY','WEIGHT_TYPE.L012_HEAVY','Heavy Truck (25T)',1,'L001_A','L003_NO',0,'2016-02-12 07:27:07',0,NULL),(36,'PO_STATUS','L013_D','PO_STATUS.L013_D','Draft',1,'L001_A','L003_NO',0,'2016-02-12 07:27:08',0,NULL),(37,'PO_STATUS','L013_WA','PO_STATUS.L013_WA','Waiting For Arrival',1,'L001_A','L003_NO',0,'2016-02-12 07:27:08',0,NULL),(38,'PO_STATUS','L013_WP','PO_STATUS.L013_WP','Waiting For Payments',1,'L001_A','L003_NO',0,'2016-02-12 07:27:09',0,NULL),(39,'PO_STATUS','L013_C','PO_STATUS.L013_C','Closed',1,'L001_A','L003_NO',0,'2016-02-12 07:27:10',0,NULL),(40,'PO_TYPE','L014_S','PO_TYPE.L014_S','Standard PO',1,'L001_A','L003_NO',0,'2016-02-12 07:27:11',0,NULL),(41,'SO_TYPE','L015_S','SO_TYPE.L015_S','Standard SO',1,'L001_A','L003_NO',0,'2016-02-12 07:27:12',0,NULL),(42,'SO_TYPE','L015_SVC','SO_TYPE.L015_SVC','Service Sales',1,'L001_A','L003_NO',0,'2016-02-12 07:27:13',0,NULL),(43,'SO_STATUS','L016_D','SO_STATUS.L016_D','Draft',1,'L001_A','L003_NO',0,'2016-02-12 07:27:14',0,NULL),(44,'SO_STATUS','L016_WD','SO_STATUS.L016_WD','Awaiting For Delivery',1,'L001_A','L003_NO',0,'2016-02-12 07:27:15',0,NULL),(45,'SO_STATUS','L016_WP','SO_STATUS.L016_WP','Awaiting For Payment',1,'L001_A','L003_NO',0,'2016-02-12 07:27:16',0,NULL),(46,'SO_STATUS','L016_C','SO_STATUS.L016_C','Closed',1,'L001_A','L003_NO',0,'2016-02-12 07:27:17',0,NULL),(47,'PAYMENT_TYPE','L017_CASH','PAYMENT_TYPE.L017_CASH','Cash',1,'L001_A','L003_NO',0,'2016-02-12 07:27:18',0,NULL),(48,'PAYMENT_TYPE','L017_TRANSFER','PAYMENT_TYPE.L017_TRANSFER','Transfer',1,'L001_A','L003_NO',0,'2016-02-12 07:27:19',0,NULL),(49,'PAYMENT_TYPE','L017_GIRO','PAYMENT_TYPE.L017_GIRO','Giro',1,'L001_A','L003_NO',0,'2016-02-12 07:27:19',0,NULL),(50,'PAYMENT_STATUS_CASH','L018_C','PAYMENT_STATUS_CASH.L018_C','Closed',1,'L001_A','L003_NO',0,'2016-02-12 07:27:20',0,NULL),(51,'PAYMENT_STATUS_TRANSFER','L019_A','PAYMENT_STATUS_TRANSFER.L019_A','Transfer A',1,'L001_A','L003_NO',0,'2016-02-12 07:27:21',0,NULL),(52,'PAYMENT_STATUS_TRANSFER','L019_B','PAYMENT_STATUS_TRANSFER.L019_B','Transfer B',1,'L001_A','L003_NO',0,'2016-02-12 07:27:22',0,NULL),(53,'PAYMENT_STATUS_GIRO','L020_WE','PAYMENT_STATUS_GIRO.L020_WE','Waiting Effective Date',1,'L001_A','L003_NO',0,'2016-02-12 07:27:23',0,NULL),(54,'PAYMENT_STATUS_GIRO','L020_F','PAYMENT_STATUS_GIRO.L020_F','Failed',1,'L001_A','L003_NO',0,'2016-02-12 07:27:24',0,NULL),(55,'PAYMENT_STATUS_GIRO','L020_FR','PAYMENT_STATUS_GIRO.L020_FR','Failed & Returned',1,'L001_A','L003_NO',0,'2016-02-12 07:27:25',0,NULL),(56,'PRICE_LEVEL_TYPE','L021P_CT','PRICE_LEVEL_TYPE.L021_PCT','Percentage',1,'L001_A','L003_NO',0,'2016-02-12 07:27:26',0,NULL),(57,'PRICE_LEVEL_TYPE','L021_INC','PRICE_LEVEL_TYPE.L021_INC','Increment',1,'L001_A','L003_NO',0,'2016-02-12 07:27:27',0,NULL),(58,'CUSTOMER_TYPE','L022_R','CUSTOMER_TYPE.L022_R','Return Customer',1,'L001_A','L003_NO',0,'2016-02-12 07:27:28',0,NULL),(59,'CUSTOMER_TYPE','L022_WIN','CUSTOMER_TYPE.L022_WIN','Walk In Customer',1,'L001_A','L003_NO',0,'2016-02-12 07:27:28',0,NULL),(60,'TRUCK_MTC_TYPE','L023_R','TRUCK_MTC_TYPE.L023_R','Regular Checkup',1,'L001_A','L003_NO',0,'2016-02-12 07:27:29',0,NULL),(61,'TRUCK_MTC_TYPE','L023_T','TRUCK_MTC_TYPE.L023_T','Tire Change',1,'L001_A','L003_NO',0,'2016-02-12 07:27:30',0,NULL),(62,'USER_TYPE','L024_ADM','USER_TYPE.L024_ADM','Admin',1,'L001_A','L003_NO',0,'2016-02-12 07:27:31',0,NULL),(63,'USER_TYPE','L024_OWN','USER_TYPE.L024_OWN','Owner',1,'L001_A','L003_NO',0,'2016-02-12 07:27:32',0,NULL),(64,'USER_TYPE','L024_EMP','USER_TYPE.L024_EMP','Employee',1,'L001_A','L003_NO',0,'2016-02-12 07:27:33',0,NULL),(65,'USER_TYPE','L024_CUS','USER_TYPE.L024_CUS','Customer',1,'L001_A','L003_NO',0,'2016-02-12 07:27:34',0,NULL),(66,'USER_TYPE','L024_SUP','USER_TYPE.L024_SUP','Supplier',1,'L001_A','L003_NO',0,'2016-02-12 07:27:35',0,NULL);
/*!40000 ALTER TABLE `tb_lookup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_modem`
--

DROP TABLE IF EXISTS `tb_modem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_modem` (
  `modem_id` int(11) NOT NULL AUTO_INCREMENT,
  `port` varchar(255) DEFAULT NULL,
  `manufacturer` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `baud_rate` int(11) DEFAULT NULL,
  `sms_center` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`modem_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_modem`
--

LOCK TABLES `tb_modem` WRITE;
/*!40000 ALTER TABLE `tb_modem` DISABLE KEYS */;
INSERT INTO `tb_modem` VALUES (1,'COM6','Huawei','E153',9600,'+62818445009');
/*!40000 ALTER TABLE `tb_modem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_payment`
--

DROP TABLE IF EXISTS `tb_payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_payment` (
  `payment_id` int(11) NOT NULL AUTO_INCREMENT,
  `store_id` int(11) DEFAULT '0',
  `payment_type` varchar(45) DEFAULT NULL,
  `payment_date` datetime DEFAULT NULL,
  `total_amount` varchar(45) DEFAULT NULL,
  `bank_code` varchar(45) DEFAULT NULL,
  `effective_date` datetime DEFAULT NULL,
  `is_linked` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `created_by` int(11) DEFAULT '0',
  `created_date` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT '0',
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`payment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_payment`
--

LOCK TABLES `tb_payment` WRITE;
/*!40000 ALTER TABLE `tb_payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_person`
--

DROP TABLE IF EXISTS `tb_person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_person` (
  `person_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `addr_1` varchar(145) DEFAULT NULL,
  `addr_2` varchar(145) DEFAULT NULL,
  `addr_3` varchar(145) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `photo_path` varchar(45) DEFAULT NULL,
  `created_by` int(11) DEFAULT '0',
  `created_date` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT '0',
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`person_id`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_person`
--

LOCK TABLES `tb_person` WRITE;
/*!40000 ALTER TABLE `tb_person` DISABLE KEYS */;
INSERT INTO `tb_person` VALUES (1,'admin','admin','Address 1','','','','1-admin-20150320-89.jpg',NULL,NULL,NULL,NULL),(2,'nonadmin','nonadmin','Address 1','','','','2-nonadmin-20160223-88.jpg',NULL,NULL,NULL,NULL),(4,'Customer PIC 1','Customer PIC 1',NULL,NULL,NULL,NULL,NULL,0,'2015-03-05 13:01:47',0,NULL),(5,'karsun','','','','','asldk',NULL,0,NULL,0,NULL),(6,'tesr','dsadsds','asdsds','dsdsd','dddd','',NULL,0,NULL,0,NULL),(10,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,0,NULL),(11,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,0,NULL),(12,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,0,NULL),(13,'SAH GO TENG','SAH GO TENG','CIREBON','','','',NULL,NULL,NULL,NULL,NULL),(14,'TONI','TONI','CIREBON','','','',NULL,NULL,NULL,NULL,NULL),(15,'TRI','TRI','CIREBON','','','',NULL,NULL,NULL,NULL,NULL),(16,'PUDI LUKITO','PUDI LUKITO','CIREBON','','','',NULL,0,NULL,0,NULL),(17,'FERA','FERA','CIREBON','','','',NULL,0,NULL,0,NULL),(20,'BAEL','BAEL','WANGON','','','',NULL,0,NULL,0,NULL),(21,'IFAN ','WAHYUDIN','WANGON','','','',NULL,0,NULL,0,NULL),(22,'MUTAMAR','MUTAMAR','SIDAREJA','','','',NULL,0,NULL,0,NULL),(23,'LIU','LIU','PASAR AJIBARANG','AJIBARANG','','',NULL,0,NULL,0,NULL),(24,'WAGIYAH','WAGIYAH','SIDAREJA','','','',NULL,0,NULL,0,NULL),(25,'CAY GUAN','GUAN','PURWOKERTO','','','',NULL,0,NULL,0,NULL),(26,'NUH','NUH','KELAPA GADING','','','',NULL,0,NULL,0,NULL),(27,'NAP','NAP','CIKANDE','','','',NULL,NULL,NULL,NULL,NULL),(28,'SIU','SIU','MAJENANG','','','',NULL,0,NULL,0,NULL),(29,'SUEB','SUEB','WANGON','','','',NULL,0,NULL,0,NULL),(30,'MEME','MEME','','','','',NULL,0,NULL,0,NULL),(31,'TAMAR','TAMAR','SIDAREJA','','','',NULL,0,NULL,0,NULL),(32,'WARISEM','WARISEM','TAMBAK SARI','','','',NULL,0,NULL,0,NULL),(33,'IBU DARSO','DARSO','KARANG PUCUNG','','','',NULL,0,NULL,0,NULL),(34,'IBU NIKEM','NIKEM','ADIRAJA','','','',NULL,0,NULL,0,NULL),(35,'SIGIT','SIGIT','CIPARI','','','',NULL,0,NULL,0,NULL),(36,'JARWO','JARWO','PURWOJATI','','','',NULL,0,NULL,0,NULL),(37,'TRI SB','TRI','PURWOJATI','','','',NULL,0,NULL,0,NULL),(38,'RISEM','RISEM','PURWOJATI','','','',NULL,0,NULL,0,NULL),(39,'SOHIR','SOHIR','PURWOJATI','','','',NULL,0,NULL,0,NULL),(40,'HJ IJAH','HJ','MAJENANG','','','',NULL,0,NULL,0,NULL),(41,'CASMANTO','CASMANTO','WANGON','','','',NULL,NULL,NULL,NULL,NULL),(42,'MAMI','MAMI','ADIPALA','','','',NULL,0,NULL,0,NULL),(43,'IBU DARISEM','DARISEM','CILACAP','','','',NULL,0,NULL,0,NULL),(44,'LYNDAWATI','LYNDA','WANGON','','','',NULL,0,NULL,0,NULL),(45,'BANGKIT','BANGKIT','WANGON','','','',NULL,0,NULL,0,NULL),(46,'IBU PARTIN','PARTIN','GRISANG','','','',NULL,0,NULL,0,NULL),(47,'IBU SAMINAH','SAMINAH','BANJAR','','','',NULL,0,NULL,0,NULL),(48,'MUFRIAH','MUFRIAH','PURWOJATI','','','',NULL,0,NULL,0,NULL),(49,'ADI','ADI','PURWOJATI','','','',NULL,0,NULL,0,NULL),(50,'MANTO','MANTO','CITEPUS','','','',NULL,0,NULL,0,NULL),(51,'HJ WONO','WONO','WANGON','','','',NULL,0,NULL,0,NULL),(52,'AYLIANG','AYLIANG','WANGON','','','',NULL,0,NULL,0,NULL),(53,'ASRUL','ASRUL','SIDAREJA','','','',NULL,0,NULL,0,NULL),(54,'KUWAT','KUWAT','TAMBAK SARI','','','',NULL,0,NULL,0,NULL),(55,'AJUN','AJUN','MAJENANG','','','',NULL,0,NULL,0,NULL),(56,'UUS','UUS','BANJAR','','','',NULL,0,NULL,0,NULL),(57,'KUN YAN','KUN','SIDAREJA','','','',NULL,0,NULL,0,NULL),(58,'DAWUD','DAWUD','GRISANG','','','',NULL,0,NULL,0,NULL),(59,'WARSUN','WARSUN','BANJAR','','','',NULL,0,NULL,0,NULL),(60,'HJ MUSTOLIH','HJ MUSTOLIH','BANJAR','','','',NULL,0,NULL,0,NULL),(61,'TRISNO','TRISNO','MAJENANG','','','',NULL,0,NULL,0,NULL),(62,'AHMAD','AHMAD','WANGON','','','',NULL,0,NULL,0,NULL),(63,'MA\'MUN','MA\'MUN','SIDAREJA','','','',NULL,0,NULL,0,NULL),(64,'NGADIMAN','NGADIMAN','WANGON','','','',NULL,0,NULL,0,NULL),(65,'KODARI','KODARI','CILACAP','','','',NULL,0,NULL,0,NULL),(66,'DAKUM','DAKUM','CITEPUS','','','',NULL,0,NULL,0,NULL),(67,'AI','AI','TIPAR','','','',NULL,0,NULL,0,NULL),(68,'TONI','TONI','CILACAP','','','',NULL,0,NULL,0,NULL),(69,'NUR','NUR','CILACAP','','','',NULL,0,NULL,0,NULL),(70,'HJ RAHMAT','HJ RAHMAT','NA','','','',NULL,0,NULL,0,NULL),(71,'TONO','TONO','KEBUMEN','','','',NULL,0,NULL,0,NULL),(72,'ANTON','ANTON','KAWUNG ANTEN','','','',NULL,0,NULL,0,NULL),(73,'ABDUL','ABDUL','WANGON','','','',NULL,0,NULL,0,NULL),(74,'TAMAT','TAMAT','','','','',NULL,0,NULL,0,NULL),(75,'YATNO','YATNO','PURWOJATI','','','',NULL,0,NULL,0,NULL),(76,'SOU LING','SOU LING','WANGON','','','',NULL,0,NULL,0,NULL),(77,'MUJIB','MUJIB','SIDAREJA','','','',NULL,0,NULL,0,NULL),(78,'HADI ','HADI','CILACAP','','','',NULL,0,NULL,0,NULL),(79,'WARIS','WARIS','SIDAREJA','','','',NULL,0,NULL,0,NULL),(80,'DWI','DWI','NA','','','',NULL,0,NULL,0,NULL),(81,'EMAN','EMAN','BANJAR','','','',NULL,0,NULL,0,NULL),(82,'UMI','UMI','NA','','','',NULL,0,NULL,0,NULL),(83,'SUNAR','SUNAR','CILACAP','','','',NULL,0,NULL,0,NULL),(84,'SIRAN','SIRAN','WANGON','','','',NULL,0,NULL,0,NULL),(85,'KIM HWA','KIM HWA','NA','','','',NULL,0,NULL,0,NULL),(86,'MIJO','MIJO','NA','','','',NULL,0,NULL,0,NULL),(87,'PANDA','PANDA','NA','','','',NULL,0,NULL,0,NULL),(88,'CIHONG','CIHONG','CILACAP','','','',NULL,0,NULL,0,NULL),(89,'NA','NA','NA','','','',NULL,0,NULL,0,NULL),(90,'CAY HIM','CAY','KARANG PUCUNG','','','',NULL,0,NULL,0,NULL),(91,'ENNY','ENNY','KARANG PUCUNG','','','',NULL,0,NULL,0,NULL),(92,'WANTO','WANTO','NA','','','',NULL,0,NULL,0,NULL),(93,'ULI','ULI','NA','','','',NULL,0,NULL,0,NULL),(94,'KO SIONG','KO SIONG','NA','','','',NULL,0,NULL,0,NULL),(95,'KONG WAN','KONG WAN','NA','','','',NULL,0,NULL,0,NULL),(96,'SUGIARTO','SUGIARTO','JAKARTA','','','',NULL,0,NULL,0,NULL),(97,'EVI','EVI','JAKARTA','','','',NULL,0,NULL,0,NULL),(98,'PIEKO NYOTO ','PIEKO','JAKARTA','','','',NULL,0,NULL,0,NULL),(99,'VIVI','VIVI','JAKARTA','','','',NULL,0,NULL,0,NULL),(100,'FRANS','FRANS','JAKARTA','','','',NULL,0,NULL,0,NULL),(101,'RUDI','RUDI','SEMARANG','','','',NULL,0,NULL,0,NULL),(102,'TINI','TINI','SEMARANG','','','',NULL,0,NULL,0,NULL),(103,'JANG HIE','JANG HIE','NA','','','',NULL,0,NULL,0,NULL),(104,'JANG MING','JANG MING','NA','','','',NULL,0,NULL,0,NULL);
/*!40000 ALTER TABLE `tb_person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_person_phonelist`
--

DROP TABLE IF EXISTS `tb_person_phonelist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_person_phonelist` (
  `person_id` int(11) DEFAULT NULL,
  `phonelist_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_person_phonelist`
--

LOCK TABLES `tb_person_phonelist` WRITE;
/*!40000 ALTER TABLE `tb_person_phonelist` DISABLE KEYS */;
INSERT INTO `tb_person_phonelist` VALUES (4,5),(4,6),(5,7),(21,16),(22,17),(23,18),(23,19),(24,20),(25,21),(26,22),(28,23),(28,24),(31,26),(32,27),(33,28),(34,29),(35,30),(36,31),(37,32),(38,33),(39,34),(40,35),(40,36),(42,38),(42,39),(43,40),(44,41),(45,42),(46,43),(47,44),(47,45),(50,47),(51,48),(52,49),(53,50),(56,53),(57,54),(58,55),(59,56),(60,57),(62,59),(63,60),(64,61),(65,62),(65,63),(66,64),(67,65),(68,66),(69,67),(70,68),(70,69),(71,70),(73,73),(74,74),(75,75),(75,76),(55,52),(76,77),(77,78),(77,79),(78,80),(79,81),(79,82),(80,83),(80,84),(81,85),(81,86),(82,87),(82,88),(83,89),(84,90),(85,91),(85,92),(86,93),(87,94),(89,95),(90,96),(92,97),(92,98),(93,99),(94,100),(29,25),(54,51),(48,46),(72,71),(72,72),(61,58),(101,105),(102,106),(97,101),(98,102),(99,103),(100,104),(16,11),(16,12),(17,13),(20,15),(13,8),(14,9),(15,10),(27,109),(41,37),(1,1),(2,2),(2,3);
/*!40000 ALTER TABLE `tb_person_phonelist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_phonelist`
--

DROP TABLE IF EXISTS `tb_phonelist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_phonelist` (
  `phonelist_id` int(11) NOT NULL AUTO_INCREMENT,
  `provider` varchar(15) DEFAULT NULL,
  `number` varchar(25) DEFAULT NULL,
  `status` varchar(15) DEFAULT NULL,
  `remarks` varchar(245) DEFAULT NULL,
  `created_by` int(11) DEFAULT '0',
  `created_date` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT '0',
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`phonelist_id`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_phonelist`
--

LOCK TABLES `tb_phonelist` WRITE;
/*!40000 ALTER TABLE `tb_phonelist` DISABLE KEYS */;
INSERT INTO `tb_phonelist` VALUES (1,NULL,'081296639663','L001_A','Admin Number',NULL,NULL,NULL,NULL),(2,NULL,'081296639663','L001_A','Non Admin Number',NULL,NULL,NULL,NULL),(3,NULL,'081296639663','L001_A','Non Admin Number',NULL,NULL,NULL,NULL),(5,'L006_TSEL','081296639663','L001_A','Customer Number',0,NULL,0,NULL),(6,'L006_TSEL','081296639663','L001_A','Customer Number',0,NULL,0,NULL),(7,'L006_TSEL','99277312','L001_A','s,mdsdss',0,NULL,0,NULL),(8,NULL,'0811241837','L001_A','',NULL,NULL,NULL,NULL),(9,NULL,'0231209346','L001_A','',NULL,NULL,NULL,NULL),(10,NULL,'0231209346','L001_A','',NULL,NULL,NULL,NULL),(11,'L006_TSEL','081312088880','L001_A','',0,NULL,0,NULL),(12,'L006_TSEL','0231224327','L001_A','',0,NULL,0,NULL),(13,'L006_TSEL','081312042875','L001_A','',0,NULL,0,NULL),(15,'L006_TSEL','082136044000','L001_A','',0,NULL,0,NULL),(16,'L006_TSEL','081391234700','L001_A','',0,NULL,0,NULL),(17,'L006_TSEL','081327289116','L001_A','',0,NULL,0,NULL),(18,'L006_TSEL','081327451881','L001_A','',0,NULL,0,NULL),(19,'L006_TSEL','081328738490','L001_A','',0,NULL,0,NULL),(20,'L006_TSEL','081391211203','L001_A','',0,NULL,0,NULL),(21,'L006_TLKM','0281631917','L001_A','',0,NULL,0,NULL),(22,'L006_ISAT','085740568486','L001_A','',0,NULL,0,NULL),(23,'L006_ISAT','085643415900','L001_A','',0,NULL,0,NULL),(24,'L006_TLKM','0280621180','L001_A','',0,NULL,0,NULL),(25,'L006_XL','081914921112','L001_A','',0,NULL,0,NULL),(26,'L006_TSEL','','L001_A','',0,NULL,0,NULL),(27,'L006_TSEL','081225652817','L001_A','',0,NULL,0,NULL),(28,'L006_ISAT','081548846892','L001_A','',0,NULL,0,NULL),(29,'L006_ISAT','081578476506','L001_A','',0,NULL,0,NULL),(30,'L006_TSEL','085227142999','L001_A','',0,NULL,0,NULL),(31,'L006_TSEL','082135012932','L001_A','',0,NULL,0,NULL),(32,'L006_TSEL','082135030318','L001_A','',0,NULL,0,NULL),(33,'L006_TSEL','081225652817','L001_A','',0,NULL,0,NULL),(34,'L006_XL','087719658672','L001_A','',0,NULL,0,NULL),(35,'L006_XL','087736844426','L001_A','',0,NULL,0,NULL),(36,'L006_XL','081903188866','L001_A','',0,NULL,0,NULL),(37,'L006_TSEL','081226646116','L001_A','',NULL,NULL,NULL,NULL),(38,'L006_TSEL','081327760777','L001_A','',0,NULL,0,NULL),(39,'L006_TLKM','02825264170','L001_A','',0,NULL,0,NULL),(40,'L006_ISAT','081542933943','L001_A','',0,NULL,0,NULL),(41,'L006_XL','081903269816','L001_A','',0,NULL,0,NULL),(42,'L006_ISAT','0816691765','L001_A','',0,NULL,0,NULL),(43,'L006_TSEL','081391562070','L001_A','',0,NULL,0,NULL),(44,'L006_TSEL','081323043146','L001_A','',0,NULL,0,NULL),(45,'L006_TSEL','085323411434','L001_A','',0,NULL,0,NULL),(46,'L006_ISAT','081548837883','L001_A','',0,NULL,0,NULL),(47,'L006_XL','081804751327','L001_A','',0,NULL,0,NULL),(48,'L006_TSEL','085293627878','L001_A','',0,NULL,0,NULL),(49,'L006_XL','087837027414','L001_A','',0,NULL,0,NULL),(50,'L006_TLKM','02807111170','L001_A','',0,NULL,0,NULL),(51,'L006_TSEL','081391415252','L001_A','',0,NULL,0,NULL),(52,'L006_ISAT','0816837689','L001_A','',0,NULL,0,NULL),(53,'L006_TSEL','085324842929','L001_A','',0,NULL,0,NULL),(54,'L006_TSEL','081341953507','L001_A','',0,NULL,0,NULL),(55,'L006_ISAT','085647820666','L001_A','',0,NULL,0,NULL),(56,'L006_TSEL','081323408383','L001_A','',0,NULL,0,NULL),(57,'L006_TLKM','0265650886','L001_A','',0,NULL,0,NULL),(58,'L006_ISAT','085726450400','L001_A','',0,NULL,0,NULL),(59,'L006_ISAT','081542790895','L001_A','',0,NULL,0,NULL),(60,'L006_TSEL','082133269403','L001_A','',0,NULL,0,NULL),(61,'L006_XL','081903247095','L001_A','',0,NULL,0,NULL),(62,'L006_TLKM','0282547669','L001_A','',0,NULL,0,NULL),(63,'L006_ISAT','085747124662','L001_A','',0,NULL,0,NULL),(64,'L006_XL','085842547321','L001_A','',0,NULL,0,NULL),(65,'L006_TSEL','081226347038','L001_A','',0,NULL,0,NULL),(66,'L006_ISAT','085726573770','L001_A','',0,NULL,0,NULL),(67,'L006_ISAT','085647898116','L001_A','',0,NULL,0,NULL),(68,'L006_TLKM','0282697105','L001_A','',0,NULL,0,NULL),(69,'L006_TSEL','082226000400','L001_A','',0,NULL,0,NULL),(70,'L006_ISAT','085729054227','L001_A','',0,NULL,0,NULL),(71,'L006_TLKM','0282611949','L001_A','',0,NULL,0,NULL),(72,'L006_ISAT','081542972909','L001_A','',0,NULL,0,NULL),(73,'L006_XL','081914953665','L001_A','',0,NULL,0,NULL),(74,'L006_TSEL','081327321997','L001_A','',0,NULL,0,NULL),(75,'L006_ISAT','085726320875','L001_A','',0,NULL,0,NULL),(76,'L006_TSEL','08122674460','L001_A','',0,NULL,0,NULL),(77,'L006_ISAT','0816691768','L001_A','',0,NULL,0,NULL),(78,'L006_TLKM','0280523811','L001_A','',0,NULL,0,NULL),(79,'L006_TSEL','085227973811','L001_A','',0,NULL,0,NULL),(80,'L006_TSEL','085227129170','L001_A','',0,NULL,0,NULL),(81,'L006_TLKM','02806260620','L001_A','',0,NULL,0,NULL),(82,'L006_TSEL','081327189586','L001_A','',0,NULL,0,NULL),(83,'L006_TLKM','0280623704','L001_A','',0,NULL,0,NULL),(84,'L006_ISAT','0816681578','L001_A','',0,NULL,0,NULL),(85,'L006_TSEL','081222828500','L001_A','',0,NULL,0,NULL),(86,'L006_TSEL','082119969333','L001_A','',0,NULL,0,NULL),(87,'L006_TLKM','02816847799','L001_A','',0,NULL,0,NULL),(88,'L006_TSEL','082135642126','L001_A','',0,NULL,0,NULL),(89,'L006_TLKM','0282533219','L001_A','',0,NULL,0,NULL),(90,'L006_TSEL','081327310077','L001_A','',0,NULL,0,NULL),(91,'L006_TLKM','0282533996','L001_A','',0,NULL,0,NULL),(92,'L006_XL','087794555223','L001_A','',0,NULL,0,NULL),(93,'L006_TSEL','082134881000','L001_A','',0,NULL,0,NULL),(94,'L006_TSEL','081327113462','L001_A','',0,NULL,0,NULL),(95,'L006_ISAT','0816674561','L001_A','',0,NULL,0,NULL),(96,'L006_ISAT','0816693833','L001_A','',0,NULL,0,NULL),(97,'L006_TSEL','081391408635','L001_A','',0,NULL,0,NULL),(98,'L006_XL','087737261799','L001_A','',0,NULL,0,NULL),(99,'L006_ISAT','081548818264','L001_A','',0,NULL,0,NULL),(100,'L006_TSEL','082325843034','L001_A','',0,NULL,0,NULL),(101,'L006_XL','081802872500','L001_A','',0,NULL,0,NULL),(102,'L006_ISAT','0816503456','L001_A','',0,NULL,0,NULL),(103,'L006_TLKM','08885345657','L001_A','',0,NULL,0,NULL),(104,'L006_TLKM','0313558000','L001_A','',0,NULL,0,NULL),(105,'L006_TSEL','082134964622','L001_A','',0,NULL,0,NULL),(106,'L006_ISAT','085640414646','L001_A','',0,NULL,0,NULL),(108,NULL,'9939','L001_A','slkdj',NULL,NULL,NULL,NULL),(109,'L006_TSEL','0','L001_A','',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `tb_phonelist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_po`
--

DROP TABLE IF EXISTS `tb_po`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_po` (
  `po_id` int(11) NOT NULL AUTO_INCREMENT,
  `store_id` int(11) DEFAULT '0',
  `po_code` varchar(45) DEFAULT NULL,
  `po_created` datetime DEFAULT NULL,
  `shipping_date` datetime DEFAULT NULL,
  `supplier_id` int(11) DEFAULT NULL,
  `warehouse_id` int(11) DEFAULT NULL,
  `po_type` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `created_by` int(11) DEFAULT '0',
  `created_date` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT '0',
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`po_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_po`
--

LOCK TABLES `tb_po` WRITE;
/*!40000 ALTER TABLE `tb_po` DISABLE KEYS */;
INSERT INTO `tb_po` VALUES (1,1,'FG5BCJ','2016-03-18 00:00:00','2016-03-18 00:00:00',5,1,'L014_S','L013_WP','',1,'2016-03-18 21:05:27',NULL,NULL),(2,1,'WV3SJM','2016-03-18 00:00:00','2016-03-18 00:00:00',5,1,'L014_S','L013_WP','',1,'2016-03-18 22:04:16',NULL,NULL);
/*!40000 ALTER TABLE `tb_po` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_po_items`
--

DROP TABLE IF EXISTS `tb_po_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_po_items` (
  `po_id` int(11) DEFAULT NULL,
  `items_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_po_items`
--

LOCK TABLES `tb_po_items` WRITE;
/*!40000 ALTER TABLE `tb_po_items` DISABLE KEYS */;
INSERT INTO `tb_po_items` VALUES (1,4),(2,5);
/*!40000 ALTER TABLE `tb_po_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_po_payment`
--

DROP TABLE IF EXISTS `tb_po_payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_po_payment` (
  `po_id` int(11) DEFAULT NULL,
  `payment_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_po_payment`
--

LOCK TABLES `tb_po_payment` WRITE;
/*!40000 ALTER TABLE `tb_po_payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_po_payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_price`
--

DROP TABLE IF EXISTS `tb_price`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_price` (
  `price_id` int(11) NOT NULL AUTO_INCREMENT,
  `store_id` int(11) DEFAULT '0',
  `stocks_id` int(11) DEFAULT NULL,
  `price_level_id` int(11) DEFAULT NULL,
  `input_date` datetime DEFAULT NULL,
  `market_price` decimal(10,2) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `status` varchar(15) DEFAULT NULL,
  `created_by` int(11) DEFAULT '0',
  `created_date` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT '0',
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`price_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_price`
--

LOCK TABLES `tb_price` WRITE;
/*!40000 ALTER TABLE `tb_price` DISABLE KEYS */;
INSERT INTO `tb_price` VALUES (1,1,1,1,'2015-12-11 00:00:00',10000.00,10000.00,'L001_A',1,'2015-12-11 08:55:14',0,NULL),(2,1,1,2,'2015-12-11 00:00:00',10000.00,10000.00,'L001_A',1,'2015-12-11 08:55:14',0,NULL),(3,1,1,1,'2015-12-12 00:00:00',12000.00,12000.00,'L001_A',1,'2015-12-14 09:37:44',0,NULL),(4,1,1,2,'2015-12-12 00:00:00',12000.00,12000.00,'L001_A',1,'2015-12-14 09:37:44',0,NULL),(7,1,1,2,'2015-12-13 00:00:00',20000.00,20000.00,'L001_A',1,'2015-12-18 21:17:44',0,NULL),(8,1,1,1,'2015-12-13 00:00:00',20000.00,20000.00,'L001_A',1,'2015-12-18 21:17:44',0,NULL),(9,1,1,1,'2015-12-21 00:00:00',20000.00,20000.00,'L001_A',1,'2015-12-21 23:53:49',0,NULL),(10,1,1,2,'2015-12-21 00:00:00',20000.00,20000.00,'L001_A',1,'2015-12-21 23:53:49',0,NULL),(11,1,1,1,'2015-12-22 00:00:00',20000.00,20000.00,'L001_A',1,'2015-12-21 23:55:34',0,NULL),(12,1,1,2,'2015-12-22 00:00:00',20000.00,20000.00,'L001_A',1,'2015-12-21 23:55:34',0,NULL),(13,1,1,1,'2015-12-22 00:00:00',200001.00,200001.00,'L001_A',1,'2015-12-21 23:57:04',0,NULL),(14,1,1,2,'2015-12-22 00:00:00',200001.00,200001.00,'L001_A',1,'2015-12-21 23:57:04',0,NULL),(15,1,1,1,'2015-12-22 00:00:00',20000.00,20000.00,'L001_A',1,'2015-12-22 00:11:09',0,NULL),(16,1,1,2,'2015-12-22 00:00:00',20000.00,20000.00,'L001_A',1,'2015-12-22 00:11:09',0,NULL),(17,1,1,1,'2015-12-22 00:13:00',20000.00,20000.00,'L001_A',1,'2015-12-22 00:13:43',0,NULL),(18,1,1,2,'2015-12-22 00:13:00',20000.00,20000.00,'L001_A',1,'2015-12-22 00:13:43',0,NULL),(19,1,1,1,'2015-12-23 01:11:00',9999.00,9999.00,'L001_A',1,'2015-12-22 13:11:36',0,NULL),(20,1,1,2,'2015-12-23 01:11:00',9999.00,9999.00,'L001_A',1,'2015-12-22 13:11:36',0,NULL),(21,1,2,1,'2015-12-23 01:11:00',8888.00,8888.00,'L001_A',1,'2015-12-22 13:11:36',0,NULL),(22,1,2,2,'2015-12-23 01:11:00',8888.00,8888.00,'L001_A',1,'2015-12-22 13:11:36',0,NULL),(23,1,1,1,'2015-12-22 01:59:00',0.00,0.00,'L001_A',1,'2015-12-22 13:59:41',0,NULL),(24,1,1,2,'2015-12-22 01:59:00',0.00,0.00,'L001_A',1,'2015-12-22 13:59:41',0,NULL),(25,1,1,1,'2015-12-22 03:00:00',0.00,0.00,'L001_A',1,'2015-12-22 14:01:44',0,NULL),(26,1,1,2,'2015-12-22 03:00:00',0.00,0.00,'L001_A',1,'2015-12-22 14:01:48',0,NULL),(27,1,2,1,'2015-12-22 04:00:00',10000.00,10000.00,'L001_A',1,'2015-12-22 14:04:39',0,NULL),(28,1,2,2,'2015-12-22 04:00:00',10000.00,10000.00,'L001_A',1,'2015-12-22 14:04:39',0,NULL),(29,1,1,1,'2016-01-01 04:26:00',20000.00,20000.00,'L001_A',1,'2016-01-01 16:26:40',0,NULL),(30,1,1,2,'2016-01-01 04:26:00',20000.00,20000.00,'L001_A',1,'2016-01-01 16:26:40',0,NULL);
/*!40000 ALTER TABLE `tb_price` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_price_level`
--

DROP TABLE IF EXISTS `tb_price_level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_price_level` (
  `price_level_id` int(11) NOT NULL AUTO_INCREMENT,
  `store_id` int(11) DEFAULT '0',
  `level_type` varchar(15) DEFAULT NULL,
  `level_weight` int(2) DEFAULT '0',
  `level_name` varchar(100) DEFAULT NULL,
  `level_description` varchar(255) DEFAULT NULL,
  `status` varchar(15) DEFAULT NULL,
  `increment_value` decimal(10,2) DEFAULT NULL,
  `percentage_value` decimal(10,2) DEFAULT NULL,
  `created_by` int(11) DEFAULT '0',
  `created_date` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT '0',
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`price_level_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_price_level`
--

LOCK TABLES `tb_price_level` WRITE;
/*!40000 ALTER TABLE `tb_price_level` DISABLE KEYS */;
INSERT INTO `tb_price_level` VALUES (1,1,'L021_INC',1,'ECERAN','UNTUK PEDAGANG ECERAN','L001_A',200.00,0.00,NULL,NULL,NULL,NULL),(2,1,'L021_INC',2,'GROSIRAN','UNTUK PEDAGANG GROSIRAN','L001_A',100.00,0.00,NULL,NULL,1,'2016-01-07 13:54:39');
/*!40000 ALTER TABLE `tb_price_level` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_product`
--

DROP TABLE IF EXISTS `tb_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_product` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `store_id` int(11) DEFAULT '0',
  `product_type` varchar(45) DEFAULT NULL,
  `short_code` varchar(25) DEFAULT NULL,
  `product_name` varchar(95) DEFAULT NULL,
  `product_description` varchar(245) DEFAULT NULL,
  `image_path` varchar(145) DEFAULT NULL,
  `status` varchar(15) DEFAULT NULL,
  `created_by` int(11) DEFAULT '0',
  `created_date` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT '0',
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_product`
--

LOCK TABLES `tb_product` WRITE;
/*!40000 ALTER TABLE `tb_product` DISABLE KEYS */;
INSERT INTO `tb_product` VALUES (2,1,'L004_KDLI','BIJO','Kedelai Bola Hijau','Kedelai Bola Hijau','','L001_A',0,NULL,0,NULL),(4,1,'L004_ML','ML KPN','Minyak Curah KPN','Minyak Curah','0-Minyak Curah KPN-50.jpg','L001_A',0,NULL,1,'2015-11-17 00:20:38'),(5,1,'L004_ML','ML Indo','Minyak Curah Indo ','Minyak Curah','0-Minyak Curah Indo -44.jpg','L001_A',0,NULL,0,NULL),(6,1,'L004_GL','GL J','Gula Jombang','Gula Putih','0-GULA JOMBANG-50.jpg','L001_A',0,NULL,0,NULL),(7,1,'L004_KTN','KT','Beras Ketan AAA','Beras Ketan','0-Beras Ketan AAA-62.jpg','L001_A',NULL,NULL,1,'2016-02-23 02:04:24');
/*!40000 ALTER TABLE `tb_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_product_type`
--

DROP TABLE IF EXISTS `tb_product_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_product_type` (
  `prod_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(45) DEFAULT NULL,
  `loc_msg_code` varchar(45) DEFAULT NULL,
  `status` varchar(15) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `created_by` int(11) DEFAULT '0',
  `created_date` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT '0',
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`prod_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_product_type`
--

LOCK TABLES `tb_product_type` WRITE;
/*!40000 ALTER TABLE `tb_product_type` DISABLE KEYS */;
INSERT INTO `tb_product_type` VALUES (1,'Minyak','PRDTYPE.ML','L001_A','Minyak Curah',0,'2015-06-10 22:35:00',0,NULL),(2,'Gula','PRDTYPE.GL','L001_A','Gula',0,'2015-06-10 22:35:00',0,NULL),(3,'Terigu','PRDTYPE.TRG','L001_A','Terigu',0,'2015-06-10 22:35:00',0,NULL),(4,'Aci','PRDTYPE.ACI','L001_A','Aci',0,'2015-06-10 22:35:00',0,NULL),(5,'Kedelai Lokal','PRDTYPE.KDLL','L001_A','Kedelai Lokal',0,'2015-06-10 22:35:00',0,NULL),(6,'Kedelai Import','PRDTYPE.KDLI','L001_A','Kedelai Import',0,'2015-06-10 22:35:00',0,NULL);
/*!40000 ALTER TABLE `tb_product_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_product_unit`
--

DROP TABLE IF EXISTS `tb_product_unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_product_unit` (
  `prod_unit_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `unit_code` varchar(45) DEFAULT NULL,
  `is_base` tinyint(1) DEFAULT '0',
  `conversion_val` int(11) DEFAULT NULL,
  `remarks` varchar(45) DEFAULT NULL,
  `created_by` int(11) DEFAULT '0',
  `created_date` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT '0',
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`prod_unit_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_product_unit`
--

LOCK TABLES `tb_product_unit` WRITE;
/*!40000 ALTER TABLE `tb_product_unit` DISABLE KEYS */;
INSERT INTO `tb_product_unit` VALUES (3,2,'L005_KG',1,1,'Base Unit',0,NULL,0,NULL),(4,2,'L005_TN',0,1000,'1 Tonne = 1000 Kg',0,NULL,0,NULL),(7,4,'L005_KG',1,1,'',0,NULL,0,NULL),(8,4,'L005_DR',0,180,'',0,NULL,0,NULL),(9,5,'L005_KG',1,1,'',0,NULL,0,NULL),(10,5,'L005_TN',0,1000,'',0,NULL,0,NULL),(11,6,'L005_KG',1,1,'',0,NULL,0,NULL),(12,6,'L005_TN',0,1000,'',0,NULL,0,NULL),(13,7,'L005_KG',1,1,'',NULL,NULL,NULL,NULL),(14,7,'L005_ZK',0,25,'1 Bags = 25 Kg',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `tb_product_unit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_receipt`
--

DROP TABLE IF EXISTS `tb_receipt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_receipt` (
  `receipt_id` int(11) NOT NULL AUTO_INCREMENT,
  `store_id` int(11) DEFAULT '0',
  `receipt_date` datetime DEFAULT NULL,
  `unit_code` varchar(15) DEFAULT NULL,
  `base_unit_code` varchar(15) DEFAULT NULL,
  `bruto` bigint(11) DEFAULT '0',
  `to_base_bruto` bigint(11) DEFAULT '0',
  `net` bigint(11) DEFAULT '0',
  `to_base_net` bigint(11) DEFAULT '0',
  `tare` bigint(11) DEFAULT '0',
  `to_base_tare` bigint(11) DEFAULT '0',
  `created_by` int(11) DEFAULT '0',
  `created_date` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT '0',
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`receipt_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_receipt`
--

LOCK TABLES `tb_receipt` WRITE;
/*!40000 ALTER TABLE `tb_receipt` DISABLE KEYS */;
INSERT INTO `tb_receipt` VALUES (1,1,'2016-03-18 22:04:00','L005_TN','L005_KG',10,10000,10,10000,0,0,1,'2016-03-18 22:05:09',NULL,NULL),(2,1,'2016-03-18 22:05:00','L005_KG','L005_KG',100,100,100,100,0,0,1,'2016-03-18 22:06:22',NULL,NULL);
/*!40000 ALTER TABLE `tb_receipt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_role`
--

DROP TABLE IF EXISTS `tb_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `status` varchar(15) DEFAULT NULL,
  `created_by` int(11) DEFAULT '0',
  `created_date` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT '0',
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_role`
--

LOCK TABLES `tb_role` WRITE;
/*!40000 ALTER TABLE `tb_role` DISABLE KEYS */;
INSERT INTO `tb_role` VALUES (1,'ADMIN','L001_A',0,'2015-08-06 09:08:42',0,NULL),(2,'NONADMIN','L001_A',0,'2015-08-06 09:08:42',0,NULL);
/*!40000 ALTER TABLE `tb_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_role_function`
--

DROP TABLE IF EXISTS `tb_role_function`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_role_function` (
  `role_id` int(11) DEFAULT NULL,
  `function_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_role_function`
--

LOCK TABLES `tb_role_function` WRITE;
/*!40000 ALTER TABLE `tb_role_function` DISABLE KEYS */;
INSERT INTO `tb_role_function` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,13),(1,14),(1,15),(1,16),(1,17),(1,18),(1,19),(1,20),(1,21),(1,22),(1,23),(1,24),(1,25),(1,26),(1,27),(1,28),(1,29),(1,30),(1,31),(1,32),(1,33),(1,34),(1,35),(1,36),(1,37),(1,38),(1,39),(1,40),(1,41),(1,42),(2,1),(2,2),(2,3),(2,4),(2,5),(2,6),(2,7),(2,8),(2,9),(2,10),(2,11),(2,12),(2,13),(2,14),(2,15),(2,16),(2,17),(2,18),(2,19),(2,20),(2,21),(2,22),(2,23),(2,24),(2,25),(2,26),(2,27),(2,28),(2,29),(2,30),(2,31);
/*!40000 ALTER TABLE `tb_role_function` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_sms_in`
--

DROP TABLE IF EXISTS `tb_sms_in`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_sms_in` (
  `sms_in_id` int(11) NOT NULL AUTO_INCREMENT,
  `sender` varchar(255) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  PRIMARY KEY (`sms_in_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_sms_in`
--

LOCK TABLES `tb_sms_in` WRITE;
/*!40000 ALTER TABLE `tb_sms_in` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_sms_in` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_so`
--

DROP TABLE IF EXISTS `tb_so`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_so` (
  `so_id` int(11) NOT NULL AUTO_INCREMENT,
  `store_id` int(11) DEFAULT '0',
  `so_code` varchar(45) DEFAULT NULL,
  `so_created` datetime DEFAULT NULL,
  `shipping_date` datetime DEFAULT NULL,
  `customer_type` varchar(45) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `walk_in_cust_det` varchar(255) DEFAULT NULL,
  `so_type` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `created_by` int(11) DEFAULT '0',
  `created_date` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT '0',
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`so_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_so`
--

LOCK TABLES `tb_so` WRITE;
/*!40000 ALTER TABLE `tb_so` DISABLE KEYS */;
INSERT INTO `tb_so` VALUES (1,1,'EX0SMY','2016-02-10 00:00:00','2016-02-10 00:00:00','L022_WIN',NULL,'kkk','L015_SVC','L016_WP','',1,'2016-02-10 10:31:04',NULL,NULL),(2,1,'IUHJ1O','2016-03-18 00:00:00','2016-03-18 00:00:00','L022_WIN',NULL,'kkkk','L015_SVC','L016_WP','',1,'2016-03-18 20:48:28',NULL,NULL),(3,1,'IKPYRN','2016-03-18 00:00:00','2016-03-18 00:00:00','L022_WIN',NULL,'kkkk','L015_SVC','L016_WP','',1,'2016-03-18 20:56:31',NULL,NULL);
/*!40000 ALTER TABLE `tb_so` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_so_items`
--

DROP TABLE IF EXISTS `tb_so_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_so_items` (
  `so_id` int(11) DEFAULT NULL,
  `items_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_so_items`
--

LOCK TABLES `tb_so_items` WRITE;
/*!40000 ALTER TABLE `tb_so_items` DISABLE KEYS */;
INSERT INTO `tb_so_items` VALUES (1,1),(2,2),(3,3);
/*!40000 ALTER TABLE `tb_so_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_so_payment`
--

DROP TABLE IF EXISTS `tb_so_payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_so_payment` (
  `so_id` int(11) DEFAULT NULL,
  `payment_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_so_payment`
--

LOCK TABLES `tb_so_payment` WRITE;
/*!40000 ALTER TABLE `tb_so_payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_so_payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_statuses`
--

DROP TABLE IF EXISTS `tb_statuses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_statuses` (
  `status_id` int(11) NOT NULL,
  `constant_code` varchar(45) DEFAULT NULL,
  `loc_msg_code` varchar(45) DEFAULT NULL,
  `remarks` varchar(245) DEFAULT NULL,
  PRIMARY KEY (`status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_statuses`
--

LOCK TABLES `tb_statuses` WRITE;
/*!40000 ALTER TABLE `tb_statuses` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_statuses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_stocks`
--

DROP TABLE IF EXISTS `tb_stocks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_stocks` (
  `stocks_id` int(11) NOT NULL AUTO_INCREMENT,
  `store_id` int(11) DEFAULT '0',
  `po_id` int(11) DEFAULT '0',
  `product_id` int(11) DEFAULT '0',
  `warehouse_id` int(11) DEFAULT '0',
  `quantity` int(11) DEFAULT NULL,
  `current_quantity` int(11) DEFAULT NULL,
  `created_by` int(11) DEFAULT '0',
  `created_date` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT '0',
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`stocks_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_stocks`
--

LOCK TABLES `tb_stocks` WRITE;
/*!40000 ALTER TABLE `tb_stocks` DISABLE KEYS */;
INSERT INTO `tb_stocks` VALUES (1,1,1,6,1,10,10,1,'2016-03-18 22:05:09',NULL,NULL),(2,1,2,6,1,100,100,1,'2016-03-18 22:06:22',NULL,NULL);
/*!40000 ALTER TABLE `tb_stocks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_stocks_out`
--

DROP TABLE IF EXISTS `tb_stocks_out`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_stocks_out` (
  `stocks_out_id` int(11) NOT NULL AUTO_INCREMENT,
  `store_id` int(11) DEFAULT '0',
  `so_id` int(11) DEFAULT NULL,
  `stocks_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `warehouse_id` int(11) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`stocks_out_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_stocks_out`
--

LOCK TABLES `tb_stocks_out` WRITE;
/*!40000 ALTER TABLE `tb_stocks_out` DISABLE KEYS */;
INSERT INTO `tb_stocks_out` VALUES (1,NULL,1,NULL,6,11,1,'2015-12-08 10:14:18',1,NULL,NULL),(2,NULL,1,NULL,4,2000,1,'2015-12-08 10:14:18',1,NULL,NULL),(3,1,1,NULL,6,10,1,'2015-12-09 10:42:25',1,NULL,NULL),(4,1,1,NULL,6,10,1,'2015-12-09 11:04:15',1,NULL,NULL),(5,1,1,NULL,6,10,1,'2015-12-09 12:53:35',1,NULL,NULL),(6,1,1,NULL,6,10,1,'2015-12-09 12:59:28',1,NULL,NULL),(7,1,4,NULL,6,100,1,'2016-01-21 09:42:31',1,NULL,NULL);
/*!40000 ALTER TABLE `tb_stocks_out` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_store`
--

DROP TABLE IF EXISTS `tb_store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_store` (
  `store_id` int(11) NOT NULL AUTO_INCREMENT,
  `store_name` varchar(95) DEFAULT NULL,
  `address_1` varchar(145) DEFAULT NULL,
  `address_2` varchar(145) DEFAULT NULL,
  `address_3` varchar(145) DEFAULT NULL,
  `phone_num` varchar(145) DEFAULT NULL,
  `is_default` varchar(10) DEFAULT NULL,
  `npwp_number` varchar(45) DEFAULT NULL,
  `status` varchar(15) DEFAULT NULL,
  `created_by` int(11) DEFAULT '0',
  `created_date` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT '0',
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`store_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_store`
--

LOCK TABLES `tb_store` WRITE;
/*!40000 ALTER TABLE `tb_store` DISABLE KEYS */;
INSERT INTO `tb_store` VALUES (1,'Toko Baru','Jln Raya Utara No.67','Wangon','Banyumas, 53176',NULL,'L003_YES','1234567890','L001_A',0,'2015-03-05 00:00:00',1,'2015-11-18 11:12:35'),(2,'Toko Baru - ','Jln Raya Utara No.67','Wangon','Banyumas, 53176','000000','L003_NO','12345678900','L001_A',0,NULL,1,'2016-02-23 02:25:56');
/*!40000 ALTER TABLE `tb_store` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_supplier`
--

DROP TABLE IF EXISTS `tb_supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_supplier` (
  `supplier_id` int(11) NOT NULL AUTO_INCREMENT,
  `store_id` int(11) DEFAULT '0',
  `supplier_name` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `npwp_num` varchar(45) DEFAULT NULL,
  `fax` varchar(45) DEFAULT NULL,
  `remarks` varchar(45) DEFAULT NULL,
  `status` varchar(15) DEFAULT NULL,
  `created_by` int(11) DEFAULT '0',
  `created_date` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT '0',
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`supplier_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_supplier`
--

LOCK TABLES `tb_supplier` WRITE;
/*!40000 ALTER TABLE `tb_supplier` DISABLE KEYS */;
INSERT INTO `tb_supplier` VALUES (2,1,'SINAR JAYA','JL PEKALIPAN NO 31','CIREBON','0231209346','0','','','L001_A',NULL,NULL,1,'2015-11-18 13:08:06'),(3,1,'INDOJAYA','JL. KALIJAGA NO 62','CIREBON','0231224327','NA','','','L001_A',0,NULL,0,NULL),(4,1,'SGT','JL. SOEDIBYO NO 123','TEGAL','0283357777','NA','','','L001_A',0,NULL,0,NULL),(5,1,'FAJAR MULYA','SURABAYA','SURABAYA','NA','','','','L001_A',0,NULL,0,NULL),(6,1,'GERBANG CAHAYA UTAMA','SEMARANG','SEMARANG','082134964622','NA','','','L001_A',0,NULL,0,NULL),(7,1,'INDRA MAS','NA','NA','NA','','','','L001_A',0,NULL,0,NULL);
/*!40000 ALTER TABLE `tb_supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_supplier_bankacc`
--

DROP TABLE IF EXISTS `tb_supplier_bankacc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_supplier_bankacc` (
  `supplier_id` int(11) DEFAULT NULL,
  `bankacc_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_supplier_bankacc`
--

LOCK TABLES `tb_supplier_bankacc` WRITE;
/*!40000 ALTER TABLE `tb_supplier_bankacc` DISABLE KEYS */;
INSERT INTO `tb_supplier_bankacc` VALUES (0,0),(0,0),(7,78),(6,77),(4,75),(5,76),(3,3),(2,2);
/*!40000 ALTER TABLE `tb_supplier_bankacc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_supplier_pic`
--

DROP TABLE IF EXISTS `tb_supplier_pic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_supplier_pic` (
  `supplier_id` int(11) NOT NULL,
  `person_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_supplier_pic`
--

LOCK TABLES `tb_supplier_pic` WRITE;
/*!40000 ALTER TABLE `tb_supplier_pic` DISABLE KEYS */;
INSERT INTO `tb_supplier_pic` VALUES (7,103),(7,104),(6,101),(6,102),(4,96),(4,97),(5,98),(5,99),(5,100),(3,16),(3,17),(2,13),(2,14),(2,15);
/*!40000 ALTER TABLE `tb_supplier_pic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_supplier_prod`
--

DROP TABLE IF EXISTS `tb_supplier_prod`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_supplier_prod` (
  `supplier_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_supplier_prod`
--

LOCK TABLES `tb_supplier_prod` WRITE;
/*!40000 ALTER TABLE `tb_supplier_prod` DISABLE KEYS */;
INSERT INTO `tb_supplier_prod` VALUES (4,4),(5,6),(3,5);
/*!40000 ALTER TABLE `tb_supplier_prod` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_truck`
--

DROP TABLE IF EXISTS `tb_truck`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_truck` (
  `truck_id` int(11) NOT NULL AUTO_INCREMENT,
  `store_id` int(11) DEFAULT '0',
  `truck_type` varchar(45) DEFAULT NULL,
  `weight_type` varchar(45) DEFAULT NULL,
  `plate_number` varchar(145) DEFAULT NULL,
  `kir_date` date DEFAULT NULL,
  `driver` int(11) DEFAULT NULL,
  `status` varchar(15) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `created_by` int(11) DEFAULT '0',
  `created_date` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT '0',
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`truck_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_truck`
--

LOCK TABLES `tb_truck` WRITE;
/*!40000 ALTER TABLE `tb_truck` DISABLE KEYS */;
INSERT INTO `tb_truck` VALUES (2,1,'L011_OIL','L012_HEAVY','R1203AE','2016-02-13',1,'L001_A','',1,'2016-02-13 22:24:17',NULL,NULL),(3,1,'L011_OIL','L012_MED','11111','2016-02-13',1,'L001_A','ppp',1,'2016-02-13 00:00:00',1,'2016-02-15 00:17:54');
/*!40000 ALTER TABLE `tb_truck` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_truck_maintenance`
--

DROP TABLE IF EXISTS `tb_truck_maintenance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_truck_maintenance` (
  `truck_maintenance_id` int(11) NOT NULL AUTO_INCREMENT,
  `truck_id` int(11) DEFAULT '0',
  `maintenance_type` varchar(45) DEFAULT NULL,
  `cost` bigint(11) DEFAULT '0',
  `odometer` bigint(11) DEFAULT '0',
  `remarks` varchar(255) DEFAULT NULL,
  `created_by` int(11) DEFAULT '0',
  `created_date` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT '0',
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`truck_maintenance_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_truck_maintenance`
--

LOCK TABLES `tb_truck_maintenance` WRITE;
/*!40000 ALTER TABLE `tb_truck_maintenance` DISABLE KEYS */;
INSERT INTO `tb_truck_maintenance` VALUES (1,NULL,'L023_R',12,1,'1',NULL,NULL,1,'2016-02-13 14:24:27'),(2,2,'L023_R',1,11,'',1,'2016-02-13 22:46:13',NULL,NULL),(3,NULL,'L023_R',1,1,'1',1,'2016-02-13 22:53:12',NULL,NULL),(4,NULL,'L023_R',11,11,'1',1,'2016-02-14 11:47:11',NULL,NULL),(5,3,'L023_T',999,88,'jjjj',1,'2016-02-15 00:18:26',NULL,NULL);
/*!40000 ALTER TABLE `tb_truck_maintenance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_user`
--

DROP TABLE IF EXISTS `tb_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(10) NOT NULL,
  `passwd` varchar(65) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `store_id` int(11) DEFAULT NULL,
  `person_id` int(11) DEFAULT NULL,
  `user_type` varchar(45) DEFAULT NULL,
  `allow_login` varchar(45) DEFAULT NULL,
  `status` varchar(15) DEFAULT NULL,
  `created_by` int(11) DEFAULT '0',
  `created_date` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT '0',
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user`
--

LOCK TABLES `tb_user` WRITE;
/*!40000 ALTER TABLE `tb_user` DISABLE KEYS */;
INSERT INTO `tb_user` VALUES (1,'admin','$2a$10$bfxVPp9/GE6zbRWHo3FKPuMPOCRVcW0qcq1ez9vXUlliLoVdTrav.',1,1,1,'L024_ADM','L003_YES','L001_A',0,'2015-03-05 13:00:10',0,NULL),(2,'nonadmin','$2a$10$CDZEnfvpQe/J.bB75iwXReRya8sF5avW8Pq7uwMOIBjWo7hWp2u3u',2,1,2,'L024_ADM','L003_YES','L001_A',0,'2015-03-05 13:00:10',0,NULL);
/*!40000 ALTER TABLE `tb_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_warehouse`
--

DROP TABLE IF EXISTS `tb_warehouse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_warehouse` (
  `warehouse_id` int(11) NOT NULL AUTO_INCREMENT,
  `store_id` int(11) DEFAULT '0',
  `name` varchar(45) DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,
  `remarks` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `created_by` int(11) DEFAULT '0',
  `created_date` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT '0',
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`warehouse_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_warehouse`
--

LOCK TABLES `tb_warehouse` WRITE;
/*!40000 ALTER TABLE `tb_warehouse` DISABLE KEYS */;
INSERT INTO `tb_warehouse` VALUES (1,1,'Gudang Utama','Wangon','Gudang Utama','L001_A',0,NULL,0,NULL);
/*!40000 ALTER TABLE `tb_warehouse` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-02  0:49:31
