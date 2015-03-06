-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: tkbaru
-- ------------------------------------------------------
-- Server version	5.6.22-log

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_bankacc`
--

LOCK TABLES `tb_bankacc` WRITE;
/*!40000 ALTER TABLE `tb_bankacc` DISABLE KEYS */;
INSERT INTO `tb_bankacc` VALUES (1,'Bank 1','Bank 1',938475843,'','A',0,'2015-03-05 13:03:30',0,NULL);
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
  `address` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `npwp_num` int(11) DEFAULT NULL,
  `status` varchar(15) DEFAULT NULL,
  `remarks` varchar(45) DEFAULT NULL,
  `created_by` int(11) DEFAULT '0',
  `created_date` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT '0',
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_customer`
--

LOCK TABLES `tb_customer` WRITE;
/*!40000 ALTER TABLE `tb_customer` DISABLE KEYS */;
INSERT INTO `tb_customer` VALUES (1,'Cust 1 Store',NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,0,NULL);
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
  `person_id` int(11) NOT NULL,
  KEY `FK_aj3xwbcq407p69t1mrbeb7uqb` (`person_id`),
  KEY `FK_36bc3kvtvu7b0en9xwa2epjbu` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_customer_pic`
--

LOCK TABLES `tb_customer_pic` WRITE;
/*!40000 ALTER TABLE `tb_customer_pic` DISABLE KEYS */;
INSERT INTO `tb_customer_pic` VALUES (1,7),(2,12),(2,13),(2,15);
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
  `created_by` int(11) DEFAULT '0',
  `created_date` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT '0',
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`function_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_function`
--

LOCK TABLES `tb_function` WRITE;
/*!40000 ALTER TABLE `tb_function` DISABLE KEYS */;
INSERT INTO `tb_function` VALUES (1,'F_PO_PURCHASEORDER','Purchase Order','fa fa-truck fa-fw','Purchase Order','fa fa-truck fa-fw','/po/add',100100,1,0,'2015-03-05 13:02:59',0,NULL),(2,'F_PO_REVISE','Purchase Order','fa fa-truck fa-fw','Revise PO','fa fa-code-fork fa-rotate-180 fa-fw','/po/revise',100200,1,0,'2015-03-05 13:02:59',0,NULL),(3,'F_PO_PAYMENT','Purchase Order','fa fa-truck fa-fw','PO Payment','fa fa-calculator fa-fw','/po/payment',100300,1,0,'2015-03-05 13:02:59',0,NULL),(4,'F_SO_SALESORDER','Sales Order','fa fa-cart-arrow-down fa-fw','Sales Order','fa fa-cart-arrow-down fa-fw','/sales/add',200100,1,0,'2015-03-05 13:02:59',0,NULL),(5,'F_SO_REVISE','Sales Order','fa fa-cart-arrow-down fa-fw','Revise SO','fa fa fa-code-fork fa-fw','/sales/revise',200200,1,0,'2015-03-05 13:02:59',0,NULL),(6,'F_SO_PAYMENT','Sales Order','fa fa-cart-arrow-down fa-fw','SO Payment','fa fa-calculator fa-fw','/sales/payment',200300,1,0,'2015-03-05 13:02:59',0,NULL),(7,'F_PRICE_TODAYPRICE','Price','fa fa-dollar fa-fw','Today Price','fa  fa-barcode fa-fw','/price/todayprice',300100,1,0,'2015-03-05 13:02:59',0,NULL),(8,'F_PRICE_PRICELEVEL','Price','fa fa-dollar fa-fw','Price Level','fa  fa-table fa-fw','/price/pricelevel',300200,1,0,'2015-03-05 13:02:59',0,NULL),(9,'F_WH_WAREHOUSE','Warehouse','fa fa-wrench fa-fw','Dashboard','fa fa-wrench fa-fw','/warehouse/dashboard',400100,1,0,'2015-03-05 13:02:59',0,NULL),(10,'F_BANK_UPLOAD','Bank','fa fa-bank fa-fw','Upload','fa fa-cloud-upload fa-fw','/bank/upload',500100,1,0,'2015-03-05 13:02:59',0,NULL),(11,'F_BANK_CONSOLIDATE','Bank','fa fa-bank fa-fw','Consolidate','fa fa-compress fa-fw','/bank/consolidate',500200,1,0,'2015-03-05 13:02:59',0,NULL),(12,'F_MON_DELIVERY','Monitoring','fa fa-eye fa-fw','Today Delivery','fa fa-bus fa-fw','/monitor/todaydelivery',600100,1,0,'2015-03-05 13:02:59',0,NULL),(13,'F_MON_STOCK','Monitoring','fa fa-eye fa-fw','Stocks','fa fa-database fa-fw','/monitor/stocks',600200,1,0,'2015-03-05 13:02:59',0,NULL),(14,'F_RPT_RPT1','Reports','fa fa-bar-chart-o fa-fw','Report 1','fa fa-plus fa-fw','/report/id/rpt1',700100,1,0,'2015-03-05 13:02:59',0,NULL),(15,'F_RPT_RPT2','Reports','fa fa-bar-chart-o fa-fw','Report 2','fa fa-plus fa-fw','/report/id/rpt2',700200,2,0,'2015-03-05 13:03:00',0,NULL),(16,'F_RPT_RPT3','Reports','fa fa-bar-chart-o fa-fw','Report 3','fa fa-plus fa-fw','/report/id/rpt3',700300,3,0,'2015-03-05 13:03:00',0,NULL),(17,'F_RPT_RPTMASTER','Reports','fa fa-bar-chart-o fa-fw','Master Data','fa fa-file-text-o fa-fw','/report/id/rptmaster',798100,4,0,'2015-03-05 13:03:00',0,NULL),(18,'F_RPT_RPTADMIN','Reports','fa fa-bar-chart-o fa-fw','Admin Data','glyphicon glyphicon-cog','/report/id/rptadmin',799100,5,0,'2015-03-05 13:03:00',0,NULL),(19,'F_MASTER_CUSTOMER','Master Data','fa fa-file-text-o fa-fw','Customer','fa fa-smile-o fa-fw','/customer',998100,1,0,'2015-03-05 13:03:00',0,NULL),(20,'F_MASTER_SUPPLIER','Master Data','fa fa-file-text-o fa-fw','Supplier','fa fa-building-o fa-fw','/supplier',998200,1,0,'2015-03-05 13:03:00',0,NULL),(21,'F_MASTER_PRODUCT','Master Data','fa fa-file-text-o fa-fw','Product','fa fa-cubes fa-fw','/product',998300,1,0,'2015-03-05 13:03:00',0,NULL),(22,'F_MASTER_WAREHOUSE','Master Data','fa fa-file-text-o fa-fw','Warehouse','fa fa-wrench fa-fw','/warehouse',998400,1,0,'2015-03-05 13:03:00',0,NULL),(23,'F_MASTER_TRUCK','Master Data','fa fa-file-text-o fa-fw','Truck','fa fa-truck fa-flip-horizontal fa-fw','/truck',998500,1,0,'2015-03-05 13:03:00',0,NULL),(24,'F_ADM_USER','Admin Menu','glyphicon glyphicon-cog','User','fa fa-user fa-fw','/admin/user',999100,1,0,'2015-03-05 13:03:00',0,NULL),(25,'F_ADM_STORE','Admin Menu','glyphicon glyphicon-cog','Store','fa fa-umbrella fa-fw','/admin/store',999200,1,0,'2015-03-05 13:03:00',0,NULL),(26,'F_ADM_ROLE','Admin Menu','glyphicon glyphicon-cog','Role','fa fa-tree fa-fw','/admin/role.html',999300,1,0,'2015-03-05 13:03:00',0,NULL),(27,'F_ADM_FUNCTION','Admin Menu','glyphicon glyphicon-cog','Function','fa fa-minus-square fa-fw','/admin/function.html',999400,1,0,'2015-03-05 13:03:00',0,NULL),(28,'F_ADM_LOOKUP','Admin Menu','glyphicon glyphicon-cog','Lookup','fa fa-hand-o-up fa-fw','/admin/lookup.html',999500,2,0,'2015-03-05 13:03:00',0,NULL);
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
  `quantity` int(11) DEFAULT NULL,
  `unit_code` varchar(45) DEFAULT NULL,
  `price` bigint(11) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`items_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_items`
--

LOCK TABLES `tb_items` WRITE;
/*!40000 ALTER TABLE `tb_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_items` ENABLE KEYS */;
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
  `order_num` int(11) DEFAULT NULL,
  `status` varchar(15) DEFAULT NULL,
  `maintainable` varchar(10) DEFAULT NULL,
  `created_by` int(11) DEFAULT '0',
  `created_date` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT '0',
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`lookup_id`),
  UNIQUE KEY `lookup_key_UNIQUE` (`lookup_key`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_lookup`
--

LOCK TABLES `tb_lookup` WRITE;
/*!40000 ALTER TABLE `tb_lookup` DISABLE KEYS */;
INSERT INTO `tb_lookup` VALUES (1,'STATUS','L001_A',1,'L001_A','L003_NO',0,'2015-03-05 13:02:37',0,NULL),(2,'STATUS','L001_I',2,'L001_A','L003_NO',0,'2015-03-05 13:02:37',0,NULL),(3,'SALUTATION','L002_MR',10,'L001_A','L003_NO',0,'2015-03-05 13:02:37',0,NULL),(4,'SALUTATION','L002_MRS',11,'L001_A','L003_NO',0,'2015-03-05 13:02:37',0,NULL),(5,'YESNOSELECTION','L003_YES',1,'L001_A','L003_NO',0,'2015-03-05 13:02:37',0,NULL),(6,'YESNOSELECTION','L003_NO',2,'L001_A','L003_NO',0,'2015-03-05 13:02:37',0,NULL),(7,'PRODUCT_TYPE','L004_ML',1,'L001_A','L003_NO',0,'2015-03-05 13:02:37',0,NULL),(8,'PRODUCT_TYPE','L004_GL',1,'L001_A','L003_NO',0,'2015-03-05 13:02:37',0,NULL),(9,'PRODUCT_TYPE','L004_TRG',1,'L001_A','L003_NO',0,'2015-03-05 13:02:37',0,NULL),(10,'PRODUCT_TYPE','L004_ACI',1,'L001_A','L003_NO',0,'2015-03-05 13:02:37',0,NULL),(11,'PRODUCT_TYPE','L004_KDLL',1,'L001_A','L003_NO',0,'2015-03-05 13:02:37',0,NULL),(12,'PRODUCT_TYPE','L004_KDLI',1,'L001_A','L003_NO',0,'2015-03-05 13:02:37',0,NULL),(13,'UNIT','L005_DR',1,'L001_A','L003_NO',0,'2015-03-05 13:02:37',0,NULL),(14,'UNIT','L005_KG',1,'L001_A','L003_NO',0,'2015-03-05 13:02:37',0,NULL),(15,'UNIT','L005_TN',1,'L001_A','L003_NO',0,'2015-03-05 13:02:37',0,NULL),(16,'UNIT','L005_KN',1,'L001_A','L003_NO',0,'2015-03-05 13:02:37',0,NULL),(17,'PHONE_PROVIDER','L006_TSEL',1,'L001_A','L003_NO',0,'2015-03-05 13:02:37',0,NULL),(18,'PHONE_PROVIDER','L006_XL',1,'L001_A','L003_NO',0,'2015-03-05 13:02:37',0,NULL),(19,'PHONE_PROVIDER','L006_ISAT',1,'L001_A','L003_NO',0,'2015-03-05 13:02:37',0,NULL),(20,'PHONE_PROVIDER','L006_3',1,'L001_A','L003_NO',0,'2015-03-05 13:02:38',0,NULL),(21,'PHONE_PROVIDER','L006_FLX',1,'L001_A','L003_NO',0,'2015-03-05 13:02:38',0,NULL),(22,'PHONE_PROVIDER','L006_SMRTFRN',1,'L001_A','L003_NO',0,'2015-03-05 13:02:38',0,NULL),(23,'PHONE_PROVIDER','L006_CRA',1,'L001_A','L003_NO',0,'2015-03-05 13:02:38',0,NULL),(24,'BANK','L007_BCA',1,'L001_A','L003_NO',0,'2015-03-05 13:02:38',0,NULL),(25,'BANK','L007_BDI',1,'L001_A','L003_NO',0,'2015-03-05 13:02:38',0,NULL),(26,'TRX_DIRECTION','L008_DB',1,'L001_A','L003_NO',0,'2015-03-05 13:02:38',0,NULL),(27,'TRX_DIRECTION','L008_CR',1,'L001_A','L003_NO',0,'2015-03-05 13:02:38',0,NULL),(28,'CURR_CODE','L009_IDR',1,'L001_A','L003_NO',0,'2015-03-05 13:02:38',0,NULL),(29,'CURR_CODE','L009_USD',1,'L001_A','L003_NO',0,'2015-03-05 13:02:38',0,NULL),(30,'LANGUAGE','L010_ID',1,'L001_A','L003_NO',0,'2015-03-05 13:02:38',0,NULL),(31,'LANGUAGE','L010_EN',1,'L001_A','L003_NO',0,'2015-03-05 13:02:38',0,NULL),(32,'TRUCK_TYPE','L011_OIL',1,'L001_A','L003_NO',0,'2015-03-05 13:02:38',0,NULL),(33,'TRUCK_TYPE','L011_CARGO',1,'L001_A','L003_NO',0,'2015-03-05 13:02:38',0,NULL),(34,'WEIGHT_TYPE','L012_MED',1,'L001_A','L003_NO',0,'2015-03-05 13:02:38',0,NULL),(35,'WEIGHT_TYPE','L012_HEAVY',1,'L001_A','L003_NO',0,'2015-03-05 13:02:38',0,NULL);
/*!40000 ALTER TABLE `tb_lookup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_lookup_detail`
--

DROP TABLE IF EXISTS `tb_lookup_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_lookup_detail` (
  `lookup_detail_id` int(11) NOT NULL AUTO_INCREMENT,
  `lookup_id` int(11) NOT NULL,
  `language_code` varchar(15) DEFAULT NULL,
  `lookup_val` varchar(45) DEFAULT NULL,
  `lookup_alt_val` varchar(45) DEFAULT NULL,
  `created_by` int(11) DEFAULT '0',
  `created_date` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT '0',
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`lookup_detail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_lookup_detail`
--

LOCK TABLES `tb_lookup_detail` WRITE;
/*!40000 ALTER TABLE `tb_lookup_detail` DISABLE KEYS */;
INSERT INTO `tb_lookup_detail` VALUES (1,1,'L010_ID','Aktif','Aktif',0,'2015-03-05 13:02:48',0,NULL),(2,1,'L010_EN','Active','Active',0,'2015-03-05 13:02:48',0,NULL),(3,2,'L010_ID','Non-aktif','Non-aktif',0,'2015-03-05 13:02:48',0,NULL),(4,2,'L010_EN','Inactive','Inactive',0,'2015-03-05 13:02:48',0,NULL),(5,3,'L010_ID','Tuan','Tuan',0,'2015-03-05 13:02:48',0,NULL),(6,3,'L010_EN','Mr','Mr',0,'2015-03-05 13:02:48',0,NULL),(7,4,'L010_ID','Ny','Ny',0,'2015-03-05 13:02:48',0,NULL),(8,4,'L010_EN','Mrs','Mrs',0,'2015-03-05 13:02:48',0,NULL),(9,5,'L010_ID','Ya','Ya',0,'2015-03-05 13:02:48',0,NULL),(10,5,'L010_EN','Yes','Yes',0,'2015-03-05 13:02:48',0,NULL),(11,6,'L010_ID','Tidak','Tidak',0,'2015-03-05 13:02:48',0,NULL),(12,6,'L010_EN','No','No',0,'2015-03-05 13:02:48',0,NULL),(13,7,'L010_ID','Minyak Sawit','Minyak Sawit',0,'2015-03-05 13:02:48',0,NULL),(14,7,'L010_EN','Palm Oil','Palm Oil',0,'2015-03-05 13:02:48',0,NULL),(15,8,'L010_ID','Gula','Gula',0,'2015-03-05 13:02:48',0,NULL),(16,8,'L010_EN','Sugar','Sugar',0,'2015-03-05 13:02:48',0,NULL),(17,9,'L010_ID','Terigu','Terigu',0,'2015-03-05 13:02:48',0,NULL),(18,9,'L010_EN','Wheat','Wheat',0,'2015-03-05 13:02:48',0,NULL),(19,10,'L010_ID','Aci','Tapioka',0,'2015-03-05 13:02:48',0,NULL),(20,10,'L010_EN','Tapioca','Tapioca',0,'2015-03-05 13:02:48',0,NULL),(21,11,'L010_ID','Kedelai Lokal','Kedelai Lokal',0,'2015-03-05 13:02:48',0,NULL),(22,11,'L010_EN','Local Soybean','Local Soybean',0,'2015-03-05 13:02:48',0,NULL),(23,12,'L010_ID','Kedelai Impor','Kedelai Impor',0,'2015-03-05 13:02:48',0,NULL),(24,12,'L010_EN','Imported Soybean','Imported Soybean',0,'2015-03-05 13:02:48',0,NULL),(25,13,'L010_ID','Drum','Drum',0,'2015-03-05 13:02:48',0,NULL),(26,13,'L010_EN','Drum','Drum',0,'2015-03-05 13:02:48',0,NULL),(27,14,'L010_ID','Kilogram','Kilogram',0,'2015-03-05 13:02:49',0,NULL),(28,14,'L010_EN','Kilogram','Kilogram',0,'2015-03-05 13:02:49',0,NULL),(29,15,'L010_ID','Ton','Ton',0,'2015-03-05 13:02:49',0,NULL),(30,15,'L010_EN','Tonne','Tonne',0,'2015-03-05 13:02:49',0,NULL),(31,16,'L010_ID','Kuintal','Kuintal',0,'2015-03-05 13:02:49',0,NULL),(32,16,'L010_EN','Kuintal','Kuintal',0,'2015-03-05 13:02:49',0,NULL),(33,17,'L010_ID','T-Sel','T-Sel',0,'2015-03-05 13:02:49',0,NULL),(34,17,'L010_EN','T-Sel','T-Sel',0,'2015-03-05 13:02:49',0,NULL),(35,18,'L010_ID','XL','XL',0,'2015-03-05 13:02:49',0,NULL),(36,18,'L010_EN','XL','XL',0,'2015-03-05 13:02:49',0,NULL),(37,19,'L010_ID','Indosat','Indosat',0,'2015-03-05 13:02:49',0,NULL),(38,19,'L010_EN','Indosat','Indosat',0,'2015-03-05 13:02:49',0,NULL),(39,20,'L010_ID','3','3',0,'2015-03-05 13:02:49',0,NULL),(40,20,'L010_EN','3','3',0,'2015-03-05 13:02:49',0,NULL),(41,21,'L010_ID','Flexi','Flexi',0,'2015-03-05 13:02:49',0,NULL),(42,21,'L010_EN','Flexi','Flexi',0,'2015-03-05 13:02:49',0,NULL),(43,22,'L010_ID','SmartFren','SmartFren',0,'2015-03-05 13:02:49',0,NULL),(44,22,'L010_EN','SmartFren','SmartFren',0,'2015-03-05 13:02:49',0,NULL),(45,23,'L010_ID','Ceria','Ceria',0,'2015-03-05 13:02:49',0,NULL),(46,23,'L010_EN','Ceria','Ceria',0,'2015-03-05 13:02:49',0,NULL),(47,24,'L010_ID','Bank Central Asia','Bank Central Asia',0,'2015-03-05 13:02:49',0,NULL),(48,24,'L010_EN','Bank Central Asia','Bank Central Asia',0,'2015-03-05 13:02:49',0,NULL),(49,25,'L010_ID','Bank Danamon Indonesia','Bank Danamon Indonesia',0,'2015-03-05 13:02:49',0,NULL),(50,25,'L010_EN','Bank Danamon Indonesia','Bank Danamon Indonesia',0,'2015-03-05 13:02:49',0,NULL),(51,26,'L010_ID','Debit','Debit',0,'2015-03-05 13:02:49',0,NULL),(52,26,'L010_EN','Debit','Debit',0,'2015-03-05 13:02:49',0,NULL),(53,27,'L010_ID','Kredit','Kredit',0,'2015-03-05 13:02:49',0,NULL),(54,27,'L010_EN','Credit','Credit',0,'2015-03-05 13:02:49',0,NULL),(55,28,'L010_ID','Rupiah','Rupiah',0,'2015-03-05 13:02:49',0,NULL),(56,28,'L010_EN','Indonesian Rupiah','Indonesian Rupiah',0,'2015-03-05 13:02:49',0,NULL),(57,29,'L010_ID','Dolar','Dolar',0,'2015-03-05 13:02:50',0,NULL),(58,29,'L010_EN','US Dollar','US Dollar',0,'2015-03-05 13:02:50',0,NULL),(59,30,'L010_ID','Bahasa Indonesia','Bahasa Indonesia',0,'2015-03-05 13:02:50',0,NULL),(60,30,'L010_EN','Indonesian','Indonesian',0,'2015-03-05 13:02:50',0,NULL),(61,31,'L010_ID','Bahasa Inggris','Bahasa Inggris',0,'2015-03-05 13:02:50',0,NULL),(62,31,'L010_EN','English','English',0,'2015-03-05 13:02:50',0,NULL),(63,32,'L010_ID','Truk Tangki','Truk Tangki',0,'2015-03-05 13:02:50',0,NULL),(64,32,'L010_EN','Oil Truck','Oil Truck',0,'2015-03-05 13:02:50',0,NULL),(65,33,'L010_ID','Truk Barang','Truk Barang',0,'2015-03-05 13:02:50',0,NULL),(66,33,'L010_EN','Cargo Truck','Cargo Truck',0,'2015-03-05 13:02:50',0,NULL),(67,34,'L010_ID','Truk Sedang (8T)','Truk Sedang (8T)',0,'2015-03-05 13:02:50',0,NULL),(68,34,'L010_EN','Medium Truck (6T)','Medium Truck (6T)',0,'2015-03-05 13:02:50',0,NULL),(69,35,'L010_ID','Truk Berat (25T)','Truk Berat (25T)',0,'2015-03-05 13:02:50',0,NULL),(70,35,'L010_EN','Heavy Truck (25T)','Heavy Truck (25T)',0,'2015-03-05 13:02:50',0,NULL);
/*!40000 ALTER TABLE `tb_lookup_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_payment`
--

DROP TABLE IF EXISTS `tb_payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_payment` (
  `payment_id` int(11) NOT NULL AUTO_INCREMENT,
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_person`
--

LOCK TABLES `tb_person` WRITE;
/*!40000 ALTER TABLE `tb_person` DISABLE KEYS */;
INSERT INTO `tb_person` VALUES (1,'admin','admin',NULL,NULL,NULL,NULL,NULL,0,'2015-03-05 13:01:46',0,NULL),(2,'non admin','non admin',NULL,NULL,NULL,NULL,NULL,0,'2015-03-05 13:01:47',0,NULL),(3,'Supplier PIC 1','Supplier PIC 1',NULL,NULL,NULL,NULL,NULL,0,'2015-03-05 13:01:47',0,NULL),(4,'Customer PIC 1','Customer PIC 1',NULL,NULL,NULL,NULL,NULL,0,'2015-03-05 13:01:47',0,NULL);
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
INSERT INTO `tb_person_phonelist` VALUES (1,1),(2,2),(2,3),(3,4),(4,5),(4,6);
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_phonelist`
--

LOCK TABLES `tb_phonelist` WRITE;
/*!40000 ALTER TABLE `tb_phonelist` DISABLE KEYS */;
INSERT INTO `tb_phonelist` VALUES (1,'L006_TSEL','081296639663','L001_A','Admin Number',0,NULL,0,NULL),(2,'L006_TSEL','081296639663','L001_A','Non Admin Number',0,NULL,0,NULL),(3,'L006_TSEL','081296639663','L001_A','Non Admin Number',0,NULL,0,NULL),(4,'L006_TSEL','081296639663','L001_A','Supplier Number',0,NULL,0,NULL),(5,'L006_TSEL','081296639663','L001_A','Customer Number',0,NULL,0,NULL),(6,'L006_TSEL','081296639663','L001_A','Customer Number',0,NULL,0,NULL);
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
  `po_code` varchar(45) DEFAULT NULL,
  `po_created` datetime DEFAULT NULL,
  `shipping_date` datetime DEFAULT NULL,
  `supplier_id` int(11) DEFAULT NULL,
  `po_type` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `created_by` int(11) DEFAULT '0',
  `created_date` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT '0',
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`po_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_po`
--

LOCK TABLES `tb_po` WRITE;
/*!40000 ALTER TABLE `tb_po` DISABLE KEYS */;
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
  `base_unit` varchar(10) DEFAULT NULL,
  `image_path` varchar(145) DEFAULT NULL,
  `status` varchar(15) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_product`
--

LOCK TABLES `tb_product` WRITE;
/*!40000 ALTER TABLE `tb_product` DISABLE KEYS */;
INSERT INTO `tb_product` VALUES (4,'L004_TRG','333','ddd','dddd','L005_TN','0-ddd--50.jpg','L001_I',NULL,NULL,NULL,NULL),(5,'L004_GL','gl','asdfa','adf','L005_KG','0-asdfa--50.jpg','L001_A',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `tb_product` ENABLE KEYS */;
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
  `conversion_val` int(11) DEFAULT NULL,
  `remarks` varchar(45) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`prod_unit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_product_unit`
--

LOCK TABLES `tb_product_unit` WRITE;
/*!40000 ALTER TABLE `tb_product_unit` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_product_unit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_purchaseorder`
--

DROP TABLE IF EXISTS `tb_purchaseorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_purchaseorder` (
  `po_id` int(11) NOT NULL AUTO_INCREMENT,
  `po_code` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`po_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_purchaseorder`
--

LOCK TABLES `tb_purchaseorder` WRITE;
/*!40000 ALTER TABLE `tb_purchaseorder` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_purchaseorder` ENABLE KEYS */;
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
INSERT INTO `tb_role` VALUES (1,'ADMIN','L001_A',0,'2015-03-05 13:12:42',0,NULL),(2,'NONADMIN','L001_A',0,'2015-03-05 13:12:42',0,NULL);
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
INSERT INTO `tb_role_function` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,13),(1,14),(1,15),(1,16),(1,17),(1,18),(1,19),(1,20),(1,21),(1,22),(1,23),(1,24),(1,25),(1,26),(1,27),(1,28),(2,1),(2,2),(2,3),(2,4),(2,5),(2,6),(2,7),(2,8),(2,9),(2,10),(2,11),(2,12),(2,13),(2,14),(2,15),(2,16),(2,17),(2,18),(2,19),(2,20),(2,21),(2,22),(2,23);
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
INSERT INTO `tb_store` VALUES (1,'Toko Baru','','','','L003_YES',NULL,'L001_A',0,'2015-03-05 13:00:48',0,NULL),(2,'Toko Baru - ','','','','L003_NO',NULL,'L001_A',0,'2015-03-05 13:00:48',0,NULL);
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
  `status` varchar(15) DEFAULT NULL,
  `created_by` int(11) DEFAULT '0',
  `created_date` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT '0',
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`supplier_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_supplier`
--

LOCK TABLES `tb_supplier` WRITE;
/*!40000 ALTER TABLE `tb_supplier` DISABLE KEYS */;
INSERT INTO `tb_supplier` VALUES (1,'Supplier Company 1','Address 1','City 1','12345','1234567','Remarks 1',NULL,0,'2015-03-05 13:00:36',0,NULL);
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
  `supplier_id` int(11) NOT NULL,
  `person_id` int(11) NOT NULL
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
  `truck_type` varchar(45) DEFAULT NULL,
  `weight_type` varchar(45) DEFAULT NULL,
  `plate_number` varchar(145) DEFAULT NULL,
  `kir_date` date DEFAULT NULL,
  `driver` int(11) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `created_by` int(11) DEFAULT '0',
  `created_date` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT '0',
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`truck_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_truck`
--

LOCK TABLES `tb_truck` WRITE;
/*!40000 ALTER TABLE `tb_truck` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_truck` ENABLE KEYS */;
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
INSERT INTO `tb_user` VALUES (1,'admin',NULL,1,1,1,'L001_A',0,'2015-03-05 13:00:10',0,NULL),(2,'nonadmin',NULL,2,1,2,'L001_A',0,'2015-03-05 13:00:10',0,NULL);
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
  `name` varchar(45) DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,
  `remarks` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`warehouse_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_warehouse`
--

LOCK TABLES `tb_warehouse` WRITE;
/*!40000 ALTER TABLE `tb_warehouse` DISABLE KEYS */;
INSERT INTO `tb_warehouse` VALUES (1,'Toko','Toko','Toko','L001_A',NULL,NULL,NULL,NULL),(2,'Gudang','Gudang','Gudang','L001_A',NULL,NULL,NULL,NULL);
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

-- Dump completed on 2015-03-05 13:18:02
