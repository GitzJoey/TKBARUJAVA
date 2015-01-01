CREATE DATABASE  IF NOT EXISTS `tkbaru` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `tkbaru`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: tkbaru
-- ------------------------------------------------------
-- Server version	5.6.21-log

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
  `created_by` int(11) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`bankacc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_bankacc`
--

LOCK TABLES `tb_bankacc` WRITE;
/*!40000 ALTER TABLE `tb_bankacc` DISABLE KEYS */;
INSERT INTO `tb_bankacc` VALUES (1,'Bank 1','Bank 1',938475843,'','A',0,'2014-12-03 22:47:20',NULL,NULL),(2,'1','test',0,'1','I',NULL,NULL,NULL,NULL),(3,'asdf','a2test',0,'asdf','A',NULL,NULL,NULL,NULL),(4,NULL,'test4',93420394,NULL,NULL,NULL,NULL,NULL,NULL),(5,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(6,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(7,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(8,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(9,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(10,'asdfs','sadfsd',23432432,'sdfsdfs','',NULL,NULL,NULL,NULL),(11,'a1','a1',11,'a1','',NULL,NULL,NULL,NULL);
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
  `store_name` varchar(45) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_customer`
--

LOCK TABLES `tb_customer` WRITE;
/*!40000 ALTER TABLE `tb_customer` DISABLE KEYS */;
INSERT INTO `tb_customer` VALUES (1,'test 1',NULL,NULL,NULL,NULL,NULL);
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
INSERT INTO `tb_customer_bankacc` VALUES (1,11);
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
INSERT INTO `tb_customer_pic` VALUES (1,7);
/*!40000 ALTER TABLE `tb_customer_pic` ENABLE KEYS */;
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
  `module` varchar(45) DEFAULT NULL,
  `module_icon` varchar(45) DEFAULT NULL,
  `menu_name` varchar(45) DEFAULT NULL,
  `menu_icon` varchar(45) DEFAULT NULL,
  `url` varchar(45) DEFAULT NULL,
  `order_num` int(11) DEFAULT NULL,
  `deep_level` int(11) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`function_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_function`
--

LOCK TABLES `tb_function` WRITE;
/*!40000 ALTER TABLE `tb_function` DISABLE KEYS */;
INSERT INTO `tb_function` VALUES (1,'F_PO_PURCHASEORDER','Purchase Order','fa fa-truck fa-fw','Purchase Order','fa fa-clipboard fa-fw','/po/add',100100,1,0,'2014-12-31 22:20:40',NULL,NULL),(2,'F_PO_POPAYMENT','Purchase Order','fa fa-truck fa-fw','PO Payment','fa fa-calculator fa-fw','/po/payment',100200,1,0,'2014-12-31 22:20:40',NULL,NULL),(3,'F_SO_TODAYPRICE','Sales Order','fa fa-dollar fa-fw','Today Price','fa  fa-barcode fa-fw','/sales/todayprice',200100,1,0,'2014-12-31 22:20:40',NULL,NULL),(4,'F_SO_SALESORDER','Sales Order','fa fa-dollar fa-fw','Sales Order','fa  fa-book fa-fw','/sales/add',200200,2,0,'2014-12-31 22:20:40',NULL,NULL),(5,'F_WH_WAREHOUSE','Warehouse','fa fa-wrench fa-fw','Dashboard','fa fa-desktop fa-fw','/warehouse/dashboard',300100,1,0,'2014-12-31 22:20:40',NULL,NULL),(6,'F_MON_DELIVERY','Monitoring','fa fa-eye fa-fw','Today Delivery','fa fa-bus fa-fw','/monitor/todaydelivery',400100,1,0,'2014-12-31 22:20:40',NULL,NULL),(7,'F_RPT_RPT1','Reports','fa fa-bar-chart-o fa-fw','Report 1','fa fa-plus fa-fw','/report/id/rpt1',500100,1,0,'2014-12-31 22:20:40',NULL,NULL),(8,'F_RPT_RPT2','Reports','fa fa-bar-chart-o fa-fw','Report 2','fa fa-plus fa-fw','/report/id/rpt2',500200,2,0,'2014-12-31 22:20:40',NULL,NULL),(9,'F_RPT_RPT3','Reports','fa fa-bar-chart-o fa-fw','Report 3','fa fa-plus fa-fw','/report/id/rpt3',500300,3,0,'2014-12-31 22:20:40',NULL,NULL),(10,'F_RPT_RPT4','Reports','fa fa-bar-chart-o fa-fw','Report 4','fa fa-plus fa-fw','/report/id/rpt4',500400,4,0,'2014-12-31 22:20:40',NULL,NULL),(11,'F_RPT_RPT5','Reports','fa fa-bar-chart-o fa-fw','Report 5','fa fa-plus fa-fw','/report/id/rpt5',500500,5,0,'2014-12-31 22:20:40',NULL,NULL),(12,'F_MASTER_CUSTOMER','Master Data','fa fa-file-text-o fa-fw','Customer','fa fa-smile-o fa-fw','/customer',998100,1,0,'2014-12-31 22:20:40',NULL,NULL),(13,'F_MASTER_SUPPLIER','Master Data','fa fa-file-text-o fa-fw','Supplier','fa fa-building-o fa-fw','/supplier',998200,1,0,'2014-12-31 22:20:40',NULL,NULL),(14,'F_MASTER_PRODUCT','Master Data','fa fa-file-text-o fa-fw','Product','fa fa-cubes fa-fw','/product',998300,1,0,'2014-12-31 22:20:40',NULL,NULL),(15,'F_ADM_USER','Admin Menu','glyphicon glyphicon-cog','User','fa fa-user fa-fw','/admin/user.html',999100,1,0,'2014-12-31 22:20:40',NULL,NULL),(16,'F_ADM_ROLE','Admin Menu','glyphicon glyphicon-cog','Role','fa fa-tree fa-fw','/admin/role.html',999200,1,0,'2014-12-31 22:20:40',NULL,NULL),(17,'F_ADM_FUNCTION','Admin Menu','glyphicon glyphicon-cog','Function','fa fa-minus-square fa-fw','/admin/function.html',999300,1,0,'2014-12-31 22:20:40',NULL,NULL),(18,'F_ADM_LOOKUP','Admin Menu','glyphicon glyphicon-cog','Lookup','fa fa-hand-o-up fa-fw','/admin/lookup.html',999400,2,0,'2014-12-31 22:20:40',NULL,NULL),(19,'F_ADM_STORE','Admin Menu','glyphicon glyphicon-cog','Store','fa fa-umbrella fa-fw','/admin/store',999500,3,0,'2014-12-31 22:20:40',NULL,NULL);
/*!40000 ALTER TABLE `tb_function` ENABLE KEYS */;
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
  `lookup_code` varchar(10) DEFAULT NULL,
  `short_val` varchar(20) DEFAULT NULL,
  `description` varchar(245) DEFAULT NULL,
  `order_num` int(11) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `maintainable` varchar(10) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`lookup_id`),
  UNIQUE KEY `lookup_code_UNIQUE` (`lookup_code`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_lookup`
--

LOCK TABLES `tb_lookup` WRITE;
/*!40000 ALTER TABLE `tb_lookup` DISABLE KEYS */;
INSERT INTO `tb_lookup` VALUES (1,'STATUS','L001_A','ACT','Active',1,'L001_A','L003_NO',0,'2014-12-28 21:36:09',NULL,NULL),(2,'STATUS','L001_I','INACT','Inactive',2,'L001_A','L003_NO',0,'2014-12-28 21:36:09',NULL,NULL),(3,'SALUTATION','L002_MR','Mr','Mr',10,'L001_A','L003_NO',0,'2014-12-28 21:36:09',NULL,NULL),(4,'SALUTATION','L002_MRS','Mrs','Mrs',11,'L001_A','L003_NO',0,'2014-12-28 21:36:09',NULL,NULL),(5,'SALUTATION','L002_TN','Tn','Tuan',12,'L001_A','L003_NO',0,'2014-12-28 21:36:09',NULL,NULL),(6,'SALUTATION','L002_NY','Ny','Nyonya',13,'L001_A','L003_NO',0,'2014-12-28 21:36:09',NULL,NULL),(7,'SALUTATION','L002_KO','K','Koh',14,'L001_A','L003_NO',0,'2014-12-28 21:36:09',NULL,NULL),(8,'SALUTATION','L003_CI','C','Ci',15,'L001_A','L003_NO',0,'2014-12-28 21:36:09',NULL,NULL),(9,'YESNOSELECTION','L003_YES','Y','Yes',1,'L001_A','L003_NO',0,'2014-12-28 21:36:09',NULL,NULL),(10,'YESNOSELECTION','L003_NO','N','No',2,'L001_A','L003_NO',0,'2014-12-28 21:36:10',NULL,NULL),(11,'PRODUCT_TYPE','L004_ML','ML','Minyak Curah',1,'L001_A','L003_NO',0,'2014-12-28 21:36:10',NULL,NULL),(12,'PRODUCT_TYPE','L004_GL','GL','Gula Pasir',1,'L001_A','L003_NO',0,'2014-12-28 21:36:10',NULL,NULL),(13,'PRODUCT_TYPE','L004_TRG','TRG','Tepung Terigu (Gandum)',1,'L001_A','L003_NO',0,'2014-12-28 21:36:10',NULL,NULL),(14,'PRODUCT_TYPE','L004_ACI','ACI','Tepung Tapioka/Kanji (Singkong)',1,'L001_A','L003_NO',0,'2014-12-28 21:36:10',NULL,NULL),(15,'PRODUCT_TYPE','L004_KDLL','KDLL','Kedelai Lokal',1,'L001_A','L003_NO',0,'2014-12-28 21:36:10',NULL,NULL),(16,'PRODUCT_TYPE','L004_KDLI','KDLI','Kedelai Import',1,'L001_A','L003_NO',0,'2014-12-28 21:36:10',NULL,NULL),(17,'UNIT','L005_DR','DR','Drum',1,'L001_A','L003_NO',0,'2014-12-28 21:36:10',NULL,NULL),(18,'UNIT','L005_KG','Kg','Kilogram (Kg)',1,'L001_A','L003_NO',0,'2014-12-28 21:36:10',NULL,NULL),(19,'UNIT','L005_TN','Ton','Ton',1,'L001_A','L003_NO',0,'2014-12-28 21:36:10',NULL,NULL),(20,'UNIT','L005_KN','Kn','Kuintal (Kn)',1,'L001_A','L003_NO',0,'2014-12-28 21:36:10',NULL,NULL);
/*!40000 ALTER TABLE `tb_lookup` ENABLE KEYS */;
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
  `created_by` int(11) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`person_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_person`
--

LOCK TABLES `tb_person` WRITE;
/*!40000 ALTER TABLE `tb_person` DISABLE KEYS */;
INSERT INTO `tb_person` VALUES (1,'admin','admin',NULL,NULL,NULL,NULL,NULL,0,'2014-12-21 11:52:29',NULL,NULL),(2,'non admin','non admin',NULL,NULL,NULL,NULL,NULL,0,'2014-12-21 11:52:29',NULL,NULL),(3,'Supplier PIC 1','Supplier PIC 1',NULL,NULL,NULL,NULL,NULL,0,'2014-12-21 11:52:29',NULL,NULL),(4,'Customer PIC 1','Customer PIC 1',NULL,NULL,NULL,NULL,NULL,0,'2014-12-21 11:52:29',NULL,NULL),(7,'test1','test1','test1','test1 ','test1','test1','',NULL,NULL,NULL,NULL);
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
INSERT INTO `tb_person_phonelist` VALUES (7,9),(7,10);
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
  `person_id` int(11) DEFAULT NULL,
  `provider` varchar(15) DEFAULT NULL,
  `number` varchar(25) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `remarks` varchar(245) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`phonelist_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_phonelist`
--

LOCK TABLES `tb_phonelist` WRITE;
/*!40000 ALTER TABLE `tb_phonelist` DISABLE KEYS */;
INSERT INTO `tb_phonelist` VALUES (1,1,'T-Sel','081296639663','A','Admin Number',NULL,NULL,NULL,NULL),(2,3,'T-Sel','081296639663','A','Supplier Number',NULL,NULL,NULL,NULL),(3,4,'T-Sel','081296639663','A','Customer Number',NULL,NULL,NULL,NULL),(4,4,'T-Sel','081296639663','A','Customer Number',NULL,NULL,NULL,NULL),(9,NULL,'test1','test1','test1','test1',NULL,NULL,NULL,NULL),(10,NULL,'test1','test1','test1','test1',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `tb_phonelist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_product`
--

DROP TABLE IF EXISTS `tb_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_product` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_type` varchar(45) DEFAULT NULL,
  `short_code` varchar(25) DEFAULT NULL,
  `product_name` varchar(95) DEFAULT NULL,
  `product_description` varchar(245) DEFAULT NULL,
  `unit` varchar(10) DEFAULT NULL,
  `in_kg` int(11) DEFAULT NULL,
  `image_path` varchar(145) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_product`
--

LOCK TABLES `tb_product` WRITE;
/*!40000 ALTER TABLE `tb_product` DISABLE KEYS */;
INSERT INTO `tb_product` VALUES (4,'L004_TRG','333','ddd','dddd','L005_TN',1000,'0-ddd--50.jpg','L001_I',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `tb_product` ENABLE KEYS */;
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
  `status` varchar(10) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_ by` int(11) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_role`
--

LOCK TABLES `tb_role` WRITE;
/*!40000 ALTER TABLE `tb_role` DISABLE KEYS */;
INSERT INTO `tb_role` VALUES (1,'ADMIN','L001_A',0,'2014-12-31 22:55:21',NULL,NULL),(2,'NONADMIN','L001_A',0,'2014-12-31 22:55:21',NULL,NULL);
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
INSERT INTO `tb_role_function` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,13),(1,14),(1,15),(1,16),(1,17),(1,18),(1,19),(2,1),(2,2),(2,3),(2,4),(2,5),(2,6),(2,7),(2,8),(2,9),(2,10),(2,11),(2,12),(2,13),(2,14);
/*!40000 ALTER TABLE `tb_role_function` ENABLE KEYS */;
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
  `is_default` varchar(10) DEFAULT NULL,
  `npwp_number` varchar(45) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`store_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_store`
--

LOCK TABLES `tb_store` WRITE;
/*!40000 ALTER TABLE `tb_store` DISABLE KEYS */;
INSERT INTO `tb_store` VALUES (2,'Toko Baru','Jln Raya No 67 Wangon','','','L003_YES','123456789','L001_A',NULL,NULL,NULL,NULL);
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
  `company_name` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `fax` varchar(45) DEFAULT NULL,
  `remarks` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`supplier_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_supplier`
--

LOCK TABLES `tb_supplier` WRITE;
/*!40000 ALTER TABLE `tb_supplier` DISABLE KEYS */;
INSERT INTO `tb_supplier` VALUES (1,'Supplier Company 1','Address 1','City 1','12345','1234567','Remarks 1',NULL,0,'2014-12-16 21:07:34',NULL,NULL),(2,'a','a','a','a','a','a','A',NULL,NULL,NULL,NULL),(3,'a2','a2','a2','asd','asdf','sdf','A',NULL,NULL,NULL,NULL);
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
INSERT INTO `tb_supplier_bankacc` VALUES (1,1),(0,0),(0,0);
/*!40000 ALTER TABLE `tb_supplier_bankacc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_supplier_pic`
--

DROP TABLE IF EXISTS `tb_supplier_pic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_supplier_pic` (
  `supplier_id` int(11) DEFAULT NULL,
  `person_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_supplier_pic`
--

LOCK TABLES `tb_supplier_pic` WRITE;
/*!40000 ALTER TABLE `tb_supplier_pic` DISABLE KEYS */;
INSERT INTO `tb_supplier_pic` VALUES (1,3);
/*!40000 ALTER TABLE `tb_supplier_pic` ENABLE KEYS */;
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
  `role_id` int(11) DEFAULT NULL,
  `person_id` int(11) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `passwd` varchar(65) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='Table User';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user`
--

LOCK TABLES `tb_user` WRITE;
/*!40000 ALTER TABLE `tb_user` DISABLE KEYS */;
INSERT INTO `tb_user` VALUES (1,'admin',1,1,'L001_A',0,'2014-12-31 22:58:39',NULL,NULL,NULL),(2,'nonadmin',2,2,'L001_A',0,'2014-12-31 22:58:39',NULL,NULL,NULL);
/*!40000 ALTER TABLE `tb_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-12-31 23:06:35
