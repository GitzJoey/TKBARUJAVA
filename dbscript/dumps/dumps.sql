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
-- Table structure for table `tb_action`
--

DROP TABLE IF EXISTS `tb_action`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_action` (
  `action_id` int(11) NOT NULL,
  `function_id` int(11) DEFAULT NULL,
  `action_name` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`action_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_action`
--

LOCK TABLES `tb_action` WRITE;
/*!40000 ALTER TABLE `tb_action` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_action` ENABLE KEYS */;
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
  `status` varchar(45) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`bankacc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_bankacc`
--

LOCK TABLES `tb_bankacc` WRITE;
/*!40000 ALTER TABLE `tb_bankacc` DISABLE KEYS */;
INSERT INTO `tb_bankacc` VALUES (1,'Bank 1','Bank 1',938475843,'','A',0,'2014-12-03 22:47:20',NULL,NULL),(2,'1','test',0,'1','I',NULL,NULL,NULL,NULL),(3,'asdf','a2test',0,'asdf','A',NULL,NULL,NULL,NULL),(4,NULL,'test4',93420394,NULL,NULL,NULL,NULL,NULL,NULL),(5,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(6,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(7,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(8,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(9,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(10,'asdfs','sadfsd',23432432,'sdfsdfs','',NULL,NULL,NULL,NULL);
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
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_customer`
--

LOCK TABLES `tb_customer` WRITE;
/*!40000 ALTER TABLE `tb_customer` DISABLE KEYS */;
INSERT INTO `tb_customer` VALUES (1,'test 1');
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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_function`
--

LOCK TABLES `tb_function` WRITE;
/*!40000 ALTER TABLE `tb_function` DISABLE KEYS */;
INSERT INTO `tb_function` VALUES (1,'F_PO_PURCHASEORDER','Purchase Order','fa fa-truck fa-fw','Purchase Order','fa fa-plus fa-fw','/po/add.html',100100,1,0,'2014-12-18 19:45:47',NULL,NULL),(2,'F_SO_SETTODAYPRICE','Sales Order','fa fa-dollar fa-fw','Sales Order','fa fa-plus fa-fw','/sales/settodayprice.html',100100,1,0,'2014-12-18 19:45:47',NULL,NULL),(3,'F_SO_SALESORDER','Sales Order','fa fa-dollar fa-fw','Sales Order','fa fa-plus fa-fw','/sales/add.html',200200,2,0,'2014-12-18 19:45:47',NULL,NULL),(4,'F_MON_DELIVERY','Monitoring','fa fa-eye fa-fw','Today Delivery','fa fa-plus fa-fw','/monitor/todaydelivery.html',300100,1,0,'2014-12-18 19:45:47',NULL,NULL),(5,'F_RPT_RPT1','Reports','fa fa-bar-chart-o fa-fw','Report 1','fa fa-plus fa-fw','/sales/settodayprice.html',400100,1,0,'2014-12-18 19:45:47',NULL,NULL),(6,'F_SO_SALESORDER','Reports','fa fa-bar-chart-o fa-fw','Report 2','fa fa-plus fa-fw','/sales/add.html',400200,2,0,'2014-12-18 19:45:47',NULL,NULL),(7,'F_MASTER_CUSTOMER','Master Data','fa fa-file-text-o fa-fw','Customer','fa fa-smile-o fa-fw','/customer',998100,1,0,'2014-12-18 19:45:47',NULL,NULL),(8,'F_MASTER_SUPPLIER','Master Data','fa fa-file-text-o fa-fw','Supplier','fa fa-building-o fa-fw','/supplier',998200,1,0,'2014-12-18 19:45:47',NULL,NULL),(9,'F_MASTER_PRODUCT','Master Data','fa fa-file-text-o fa-fw','Product','fa fa-cubes fa-fw','/product',998300,1,0,'2014-12-18 19:45:47',NULL,NULL),(10,'F_ADM_USER','Admin Menu','glyphicon glyphicon-cog','User','fa fa-user fa-fw','/admin/user.html',999100,1,0,'2014-12-18 19:45:47',NULL,NULL),(11,'F_ADM_ROLE','Admin Menu','glyphicon glyphicon-cog','Role','fa fa-tree fa-fw','/admin/role.html',999200,1,0,'2014-12-18 19:45:47',NULL,NULL),(12,'F_ADM_FUNCTION','Admin Menu','glyphicon glyphicon-cog','Function','fa fa-minus-square fa-fw','/admin/function.html',999300,1,0,'2014-12-18 19:45:47',NULL,NULL),(13,'F_ADM_LOOKUP','Admin Menu','glyphicon glyphicon-cog','Lookup','fa fa-hand-o-up fa-fw','/admin/lookup.html',999400,2,0,'2014-12-18 19:45:47',NULL,NULL);
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
  `category` varchar(45) DEFAULT NULL,
  `lookup_code` varchar(45) DEFAULT NULL,
  `short_val` varchar(20) DEFAULT NULL,
  `long_val` varchar(45) DEFAULT NULL,
  `description` varchar(245) DEFAULT NULL,
  `order_num` int(11) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `maintainable` varchar(45) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`lookup_id`),
  UNIQUE KEY `lookup_code_UNIQUE` (`lookup_code`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_lookup`
--

LOCK TABLES `tb_lookup` WRITE;
/*!40000 ALTER TABLE `tb_lookup` DISABLE KEYS */;
INSERT INTO `tb_lookup` VALUES (1,'STATUS','A','ACT','Active','Active',1,'A','N',0,'2014-12-08 20:01:06',NULL,NULL),(2,'STATUS','I','INACT','Inactive','Inactive',2,'A','N',0,'2014-12-08 20:01:06',NULL,NULL),(3,'SALUTATION','L002_MR','Mr','Mr','Mister',10,'A','N',0,'2014-12-08 20:01:06',NULL,NULL),(4,'SALUTATION','L002_MRS','Mrs','Mrs','Miss',11,'A','N',0,'2014-12-08 20:01:06',NULL,NULL),(5,'SALUTATION','L002_TN','Tn','Tuan','Tuan',12,'A','N',0,'2014-12-08 20:01:06',NULL,NULL),(6,'SALUTATION','L002_NY','Ny','Nyonya','Nyonya',13,'A','N',0,'2014-12-08 20:01:06',NULL,NULL),(7,'SALUTATION','L002_KO','K','Koh','Koh',14,'A','N',0,'2014-12-08 20:01:06',NULL,NULL),(8,'SALUTATION','L003_CI','C','Ci','Ci',15,'A','N',0,'2014-12-08 20:01:06',NULL,NULL),(9,'YESNOSELECTION','L003_YES','Y','Yes','Yes',1,'A','N',0,'2014-12-08 20:01:06',NULL,NULL),(10,'YESNOSELECTION','L004_NO','N','No','No',2,'A','L003_YES',0,'2014-12-08 20:01:06',NULL,NULL);
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
  `status` varchar(5) DEFAULT NULL,
  `remarks` varchar(45) DEFAULT NULL,
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
  `product_name` varchar(45) DEFAULT NULL,
  `short_name` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_product`
--

LOCK TABLES `tb_product` WRITE;
/*!40000 ALTER TABLE `tb_product` DISABLE KEYS */;
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
  `status` varchar(45) DEFAULT NULL,
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
INSERT INTO `tb_role` VALUES (1,'ADMIN','A',0,'2014-11-20 13:53:52',NULL,NULL),(2,'NONADMIN','A',0,'2014-11-20 13:53:52',NULL,NULL);
/*!40000 ALTER TABLE `tb_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_rolefunction`
--

DROP TABLE IF EXISTS `tb_rolefunction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_rolefunction` (
  `rolefunction_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `function_id` int(11) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`rolefunction_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_rolefunction`
--

LOCK TABLES `tb_rolefunction` WRITE;
/*!40000 ALTER TABLE `tb_rolefunction` DISABLE KEYS */;
INSERT INTO `tb_rolefunction` VALUES (1,1,1,0,'2014-11-16 18:43:56',NULL,NULL),(2,1,2,0,'2014-11-16 18:43:56',NULL,NULL),(3,1,3,0,'2014-11-16 18:43:56',NULL,NULL),(4,1,4,0,'2014-11-16 18:43:56',NULL,NULL),(5,1,5,0,'2014-11-16 18:43:56',NULL,NULL),(6,1,6,0,'2014-11-16 18:43:56',NULL,NULL),(7,1,7,0,'2014-11-16 18:43:56',NULL,NULL),(8,1,8,0,'2014-11-16 18:43:56',NULL,NULL),(9,1,9,0,'2014-11-16 18:43:56',NULL,NULL),(10,1,10,0,'2014-11-16 18:43:56',NULL,NULL),(11,1,11,0,'2014-11-16 18:43:56',NULL,NULL),(12,1,12,0,'2014-11-16 18:43:56',NULL,NULL),(13,1,13,0,'2014-11-16 18:43:56',NULL,NULL),(14,1,14,0,'2014-11-16 18:43:56',NULL,NULL),(15,1,15,0,'2014-11-16 18:43:56',NULL,NULL),(16,1,16,0,'2014-11-16 18:43:56',NULL,NULL),(17,1,17,0,'2014-11-16 18:43:56',NULL,NULL),(18,1,18,0,'2014-11-16 18:43:56',NULL,NULL),(19,1,19,0,'2014-11-16 18:43:56',NULL,NULL),(20,2,1,0,'2014-11-16 18:43:56',NULL,NULL),(21,2,2,0,'2014-11-16 18:43:56',NULL,NULL),(22,2,3,0,'2014-11-16 18:43:56',NULL,NULL),(23,2,4,0,'2014-11-16 18:43:56',NULL,NULL),(24,2,5,0,'2014-11-16 18:43:56',NULL,NULL),(25,2,6,0,'2014-11-16 18:43:56',NULL,NULL),(26,2,7,0,'2014-11-16 18:43:56',NULL,NULL),(27,2,8,0,'2014-11-16 18:43:56',NULL,NULL),(28,2,9,0,'2014-11-16 18:43:56',NULL,NULL),(29,2,10,0,'2014-11-16 18:43:56',NULL,NULL),(30,2,11,0,'2014-11-16 18:43:56',NULL,NULL),(31,2,12,0,'2014-11-16 18:43:57',NULL,NULL),(32,2,13,0,'2014-11-16 18:43:57',NULL,NULL),(33,2,14,0,'2014-11-16 18:43:57',NULL,NULL);
/*!40000 ALTER TABLE `tb_rolefunction` ENABLE KEYS */;
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
  `status` varchar(5) DEFAULT NULL,
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
INSERT INTO `tb_user` VALUES (1,'admin',1,1,'A',0,'2014-11-16 18:42:51',NULL,NULL,NULL),(2,'nonadmin',2,2,'A',0,'2014-11-16 18:42:51',NULL,NULL,NULL);
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

-- Dump completed on 2014-12-22 12:16:46
