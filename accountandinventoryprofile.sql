-- MySQL dump 10.13  Distrib 5.6.19, for osx10.7 (i386)
--
-- Host: 127.0.0.1    Database: hydraforce_db
-- ------------------------------------------------------
-- Server version	5.6.16

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `user_name` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `type` int(1) NOT NULL,
  PRIMARY KEY (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES ('admin','1234',0),('employee','12345',1);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acknowledgementreceipt`
--

DROP TABLE IF EXISTS `acknowledgementreceipt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `acknowledgementreceipt` (
  `acknowledgement_receipt_id` varchar(45) NOT NULL,
  `company_id` int(11) NOT NULL,
  `date` date NOT NULL,
  `original_amount` decimal(10,2) NOT NULL DEFAULT '0.00',
  `po_num` varchar(45) DEFAULT NULL,
  `ordered_by` varchar(45) DEFAULT NULL,
  `sales_person` varchar(45) DEFAULT NULL,
  `delivered_by` varchar(45) DEFAULT NULL,
  `delivery_notes` varchar(45) DEFAULT NULL,
  `delivery_receipt_num` varchar(45) DEFAULT NULL,
  `discount` decimal(10,2) DEFAULT NULL,
  `current_balance` decimal(10,2) NOT NULL DEFAULT '0.00',
  `status` varchar(45) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  `type` varchar(45) NOT NULL DEFAULT 'Acknowledgement Receipt',
  PRIMARY KEY (`acknowledgement_receipt_id`),
  KEY `company_id_idx` (`company_id`),
  CONSTRAINT `company_id` FOREIGN KEY (`company_id`) REFERENCES `company` (`company_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acknowledgementreceipt`
--

LOCK TABLES `acknowledgementreceipt` WRITE;
/*!40000 ALTER TABLE `acknowledgementreceipt` DISABLE KEYS */;
/*!40000 ALTER TABLE `acknowledgementreceipt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `arlineitem`
--

DROP TABLE IF EXISTS `arlineitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `arlineitem` (
  `acknowledgement_receipt_id` varchar(45) NOT NULL,
  `quantity` int(11) NOT NULL,
  `part_num` varchar(20) NOT NULL,
  `unit_price` decimal(10,2) NOT NULL,
  `line_total` decimal(10,2) NOT NULL,
  KEY `acknowledgement_receipt_id_idx` (`acknowledgement_receipt_id`),
  KEY `part_num_idx` (`part_num`),
  CONSTRAINT `acknowledgement_receipt_id` FOREIGN KEY (`acknowledgement_receipt_id`) REFERENCES `acknowledgementreceipt` (`acknowledgement_receipt_id`) ON UPDATE CASCADE,
  CONSTRAINT `part_num` FOREIGN KEY (`part_num`) REFERENCES `item` (`part_num`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `arlineitem`
--

LOCK TABLES `arlineitem` WRITE;
/*!40000 ALTER TABLE `arlineitem` DISABLE KEYS */;
/*!40000 ALTER TABLE `arlineitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bbcollection`
--

DROP TABLE IF EXISTS `bbcollection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bbcollection` (
  `bb_collection_id` int(11) NOT NULL AUTO_INCREMENT,
  `system_account_num` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`bb_collection_id`),
  KEY `system_account_num_idx` (`system_account_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bbcollection`
--

LOCK TABLES `bbcollection` WRITE;
/*!40000 ALTER TABLE `bbcollection` DISABLE KEYS */;
/*!40000 ALTER TABLE `bbcollection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bbpayment`
--

DROP TABLE IF EXISTS `bbpayment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bbpayment` (
  `bb_payment_id` int(11) NOT NULL AUTO_INCREMENT,
  `account_num` int(20) NOT NULL,
  `bank_name` varchar(45) NOT NULL,
  `bank_branch` varchar(45) NOT NULL,
  `system_account_num` int(20) NOT NULL,
  PRIMARY KEY (`bb_payment_id`),
  KEY `system_account_num_idx` (`system_account_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bbpayment`
--

LOCK TABLES `bbpayment` WRITE;
/*!40000 ALTER TABLE `bbpayment` DISABLE KEYS */;
/*!40000 ALTER TABLE `bbpayment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cashcollection`
--

DROP TABLE IF EXISTS `cashcollection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cashcollection` (
  `cash_collection_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`cash_collection_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cashcollection`
--

LOCK TABLES `cashcollection` WRITE;
/*!40000 ALTER TABLE `cashcollection` DISABLE KEYS */;
/*!40000 ALTER TABLE `cashcollection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cashpayment`
--

DROP TABLE IF EXISTS `cashpayment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cashpayment` (
  `cash_payment_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`cash_payment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cashpayment`
--

LOCK TABLES `cashpayment` WRITE;
/*!40000 ALTER TABLE `cashpayment` DISABLE KEYS */;
/*!40000 ALTER TABLE `cashpayment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `checkcollection`
--

DROP TABLE IF EXISTS `checkcollection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `checkcollection` (
  `check_collection_id` int(11) NOT NULL AUTO_INCREMENT,
  `account_name` varchar(100) DEFAULT NULL,
  `account_number` int(20) NOT NULL,
  `bank_name` varchar(45) NOT NULL,
  `branch` varchar(45) NOT NULL,
  `check_number` int(11) NOT NULL,
  PRIMARY KEY (`check_collection_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `checkcollection`
--

LOCK TABLES `checkcollection` WRITE;
/*!40000 ALTER TABLE `checkcollection` DISABLE KEYS */;
/*!40000 ALTER TABLE `checkcollection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `checkpayment`
--

DROP TABLE IF EXISTS `checkpayment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `checkpayment` (
  `check_payment_id` int(11) NOT NULL,
  `check_number` int(11) NOT NULL,
  `system_account_num` int(20) NOT NULL,
  PRIMARY KEY (`check_payment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `checkpayment`
--

LOCK TABLES `checkpayment` WRITE;
/*!40000 ALTER TABLE `checkpayment` DISABLE KEYS */;
/*!40000 ALTER TABLE `checkpayment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `collection`
--

DROP TABLE IF EXISTS `collection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `collection` (
  `collection_id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `receipt_type` varchar(45) NOT NULL,
  `number` varchar(45) NOT NULL,
  `notes` varchar(500) DEFAULT NULL,
  `received_by` varchar(45) DEFAULT NULL,
  `collection_type` varchar(100) NOT NULL,
  `debit_memo_id` varchar(45) DEFAULT NULL,
  `received_date` date DEFAULT NULL,
  PRIMARY KEY (`collection_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `collection`
--

LOCK TABLES `collection` WRITE;
/*!40000 ALTER TABLE `collection` DISABLE KEYS */;
/*!40000 ALTER TABLE `collection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company` (
  `company_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(70) NOT NULL,
  `address_location` varchar(100) DEFAULT NULL,
  `address_city` varchar(45) DEFAULT NULL,
  `address_country` varchar(45) DEFAULT NULL,
  `address_postal_code` varchar(45) DEFAULT NULL,
  `phone1` varchar(11) DEFAULT NULL,
  `phone2` varchar(11) DEFAULT NULL,
  `phone3` varchar(11) DEFAULT NULL,
  `fax_num` varchar(11) DEFAULT NULL,
  `website` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `contact_person` varchar(45) DEFAULT NULL,
  `status` varchar(8) NOT NULL DEFAULT 'Active',
  `credit_limit` decimal(10,2) NOT NULL,
  `terms` int(4) NOT NULL,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (13,'YC Chen Industrial Company','Kaoshiung','','Taiwan','','','','','','','','','Active',0.00,0,'Supplier'),(14,'TAEWOO Seal Corporation','','','Korea','','','','','','','','','Active',0.00,0,'Supplier'),(15,'Santian Lebon','','','China','','','','','','','','','Active',0.00,0,'Supplier'),(16,'Autox','','','Korea','','','','','','','','','Active',0.00,0,'Supplier'),(17,'Triple A','','','','','','','','','','','','Active',0.00,120,'Retail Customer'),(18,'HENJIKOI','','','','','','','','','','','','Active',0.00,120,'Retail Customer'),(19,'YRREHC Tractor','155-C 4th Ave','Caloocan City','','','3109967','','','','','','','Active',0.00,90,'Retail Customer'),(20,'Fastpace Tractor','','','Philippines','','','','','','','','','Active',0.00,120,'Retail Customer'),(21,'Trackstar Enterprises','D. Tuazon','','Philippines','','3650569','','','','','','','Active',0.00,120,'Sister Company Customer'),(22,'Basud','','','','','','','','','','','','Active',0.00,120,'Retail Customer'),(23,'Precision Hydraulic','','','','','','','','','','','','Active',0.00,120,'Sister Company Customer'),(24,'Tugatog Hydraulic','','','','','3630033','','','','','','','Active',0.00,120,'Sister Company Customer'),(25,'China Construction','','','','','','','','','','','','Active',0.00,0,'Retail Customer'),(26,'Tractstar Hydraulic Supply','','','','','3656592','','','','','','','Active',0.00,120,'Sister Company Customer'),(27,'Paccar Hydraulic','','','','','','','','','','','','Active',0.00,120,'Retail Customer'),(28,'Butch Buaquinan','','','','','','','','','','','','Active',0.00,120,'Retail Customer'),(29,'Saagundo H/E Parts Supply','','','','','','','','','','','','Active',0.00,0,'Retail Customer'),(30,'Grasco','','','','','','','','','','','','Active',0.00,120,'Retail Customer'),(31,'Oilgear','','Cebu City','Philippines','','','','','','','','','Active',0.00,120,'Sister Company Customer'),(32,'Reflecstar','','','','','','','','','','','','Active',0.00,0,'Retail Customer'),(33,'Danny Lipata','','','','','','','','','','','','Active',0.00,0,'Retail Customer'),(34,'Alison Izuparts','','','','','','','','','','','','Active',0.00,120,'Retail Customer'),(35,'Sun Valley Golf','','','','','','','','','','','','Active',0.00,0,'Walk-in Customer'),(36,'Advanced Foundation','','','','','','','','','','','','Active',0.00,90,'Walk-in Customer'),(37,'APD Construction','','','','','','','','','','','','Active',0.00,90,'Walk-in Customer'),(38,'Phil Galv Industrial Coating Inc.','','','','','','','','','','','','Active',0.00,30,'Walk-in Customer'),(39,'Gavino Trucking','','','','','','','','','','','','Active',0.00,0,'Walk-in Customer'),(40,'DHSI','','','','','','','','','','','','Active',0.00,0,'Walk-in Customer'),(41,'BZF Construction','','','','','','','','','','','','Active',0.00,0,'Walk-in Customer'),(42,'Flavie Subic Motor Trading Corp.','','','','','','','','','','','','Active',0.00,0,'Walk-in Customer'),(43,'K Trading','','','','','','','','','','','','Active',0.00,0,'Walk-in Customer'),(44,'KEKAIKO','','','','','','','','','','','','Active',0.00,0,'Walk-in Customer'),(45,'Montalban Millex Aggregates','','','','','','','','','','','','Active',0.00,30,'Walk-in Customer'),(46,'PB Faustino','','','','','','','','','','','','Active',0.00,30,'Walk-in Customer'),(47,'Julius Tribilo','','','','','','','','','','','','Active',0.00,0,'Walk-in Customer'),(48,'Josefa','','','','','','','','','','','','Active',0.00,0,'Walk-in Customer'),(49,'Rolly Santos','','','','','','','','','','','','Active',0.00,0,'Walk-in Customer'),(50,'JCI Trading','','','','','','','','','','','','Active',0.00,0,'Walk-in Customer'),(51,'AC Mojares','','','','','','','','','','','','Active',0.00,0,'Walk-in Customer'),(52,'Ian','','','','','','','','','','','','Active',0.00,0,'Walk-in Customer'),(53,'Moral Hydroparts Center','','','','','','','','','','','','Active',0.00,0,'Walk-in Customer'),(54,'JOP','','','','','','','','','','','','Active',0.00,0,'Walk-in Customer'),(55,'Iron Angelo Engr.','','','','','','','','','','','','Active',0.00,0,'Walk-in Customer'),(56,'Northern Builders','','','','','','','','','','','','Active',0.00,0,'Walk-in Customer'),(57,'Alleway','','','','','','','','','','','','Active',0.00,0,'Walk-in Customer'),(58,'Laz Caerus Homes Inc','','','','','','','','','','','','Active',0.00,0,'Walk-in Customer'),(59,'Arroway','','','','','','','','','','','','Active',0.00,0,'Walk-in Customer');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `creditmemo`
--

DROP TABLE IF EXISTS `creditmemo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `creditmemo` (
  `credit_memo_id` varchar(45) NOT NULL,
  `date` date NOT NULL,
  `return_slip_id` varchar(45) NOT NULL,
  `part_number` varchar(45) NOT NULL,
  `status` int(1) NOT NULL DEFAULT '0',
  `type` varchar(45) NOT NULL,
  `total_amount` decimal(10,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`credit_memo_id`),
  KEY `return_slip_id_idx` (`return_slip_id`),
  CONSTRAINT `return_slip_id` FOREIGN KEY (`return_slip_id`) REFERENCES `returnslip` (`return_slip_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `creditmemo`
--

LOCK TABLES `creditmemo` WRITE;
/*!40000 ALTER TABLE `creditmemo` DISABLE KEYS */;
/*!40000 ALTER TABLE `creditmemo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `creditmemopayment`
--

DROP TABLE IF EXISTS `creditmemopayment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `creditmemopayment` (
  `credit_memo_payment_id` int(11) NOT NULL,
  `credit_memo_id` varchar(45) NOT NULL,
  PRIMARY KEY (`credit_memo_payment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `creditmemopayment`
--

LOCK TABLES `creditmemopayment` WRITE;
/*!40000 ALTER TABLE `creditmemopayment` DISABLE KEYS */;
/*!40000 ALTER TABLE `creditmemopayment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `customer_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `address_location` varchar(100) DEFAULT NULL,
  `address_city` varchar(45) DEFAULT NULL,
  `address_country` varchar(45) DEFAULT NULL,
  `address_postal_code` varchar(45) DEFAULT NULL,
  `phone1` int(11) DEFAULT NULL,
  `phone2` int(11) DEFAULT NULL,
  `phone3` int(11) DEFAULT NULL,
  `fax_num` int(11) DEFAULT NULL,
  `website` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `conatct_person` varchar(45) DEFAULT NULL,
  `status` varchar(8) NOT NULL DEFAULT 'Active',
  `credit_limit` int(11) NOT NULL,
  `terms` int(11) NOT NULL,
  `type` varchar(45) NOT NULL,
  `current_balance` float NOT NULL DEFAULT '0',
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `debitmemo`
--

DROP TABLE IF EXISTS `debitmemo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `debitmemo` (
  `debit_memo_id` varchar(45) NOT NULL,
  `date` date NOT NULL,
  `company_id` int(11) NOT NULL,
  `total_amount` decimal(10,2) NOT NULL,
  `receipt_type` varchar(45) NOT NULL,
  `number` varchar(45) NOT NULL,
  `approved_by` varchar(45) DEFAULT NULL,
  `received_by` varchar(45) DEFAULT NULL,
  `approved_date` varchar(45) DEFAULT NULL,
  `received_date` varchar(45) DEFAULT NULL,
  `notes` varchar(500) DEFAULT NULL,
  `status` int(1) NOT NULL DEFAULT '0',
  `type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`debit_memo_id`),
  KEY `company_id_idx` (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `debitmemo`
--

LOCK TABLES `debitmemo` WRITE;
/*!40000 ALTER TABLE `debitmemo` DISABLE KEYS */;
/*!40000 ALTER TABLE `debitmemo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `debitmemocollection`
--

DROP TABLE IF EXISTS `debitmemocollection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `debitmemocollection` (
  `debit_memo_collection_id` int(11) NOT NULL AUTO_INCREMENT,
  `debit_memo_id` varchar(45) NOT NULL,
  PRIMARY KEY (`debit_memo_collection_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `debitmemocollection`
--

LOCK TABLES `debitmemocollection` WRITE;
/*!40000 ALTER TABLE `debitmemocollection` DISABLE KEYS */;
/*!40000 ALTER TABLE `debitmemocollection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dmlineitem`
--

DROP TABLE IF EXISTS `dmlineitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dmlineitem` (
  `debit_memo_id` varchar(45) NOT NULL,
  `quantity` int(11) NOT NULL,
  `part_num` varchar(20) NOT NULL,
  `unit_price` decimal(10,2) NOT NULL,
  `line_total` decimal(10,2) NOT NULL,
  KEY `part_num_idx` (`part_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dmlineitem`
--

LOCK TABLES `dmlineitem` WRITE;
/*!40000 ALTER TABLE `dmlineitem` DISABLE KEYS */;
/*!40000 ALTER TABLE `dmlineitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dmlinetem`
--

DROP TABLE IF EXISTS `dmlinetem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dmlinetem` (
  `debit_memo_id` varchar(45) NOT NULL,
  `quantity` int(11) NOT NULL,
  `part_num` varchar(20) NOT NULL,
  `unit_price` float NOT NULL,
  `line_total` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dmlinetem`
--

LOCK TABLES `dmlinetem` WRITE;
/*!40000 ALTER TABLE `dmlinetem` DISABLE KEYS */;
/*!40000 ALTER TABLE `dmlinetem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item` (
  `part_num` varchar(20) NOT NULL,
  `description` varchar(100) NOT NULL,
  `stock_minimum` int(11) NOT NULL,
  `rack_location` varchar(45) NOT NULL,
  `last_cost` decimal(10,2) NOT NULL DEFAULT '0.00',
  `walk_in_price` decimal(10,2) NOT NULL DEFAULT '0.00',
  `traders_price` decimal(10,2) NOT NULL DEFAULT '0.00',
  `sister_company_price` decimal(10,2) NOT NULL DEFAULT '0.00',
  `quantity_defective` int(11) NOT NULL DEFAULT '0',
  `quantity_functional` int(11) NOT NULL DEFAULT '0',
  `notes` varchar(500) DEFAULT NULL,
  `image` varchar(200) DEFAULT NULL,
  `status` int(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`part_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES ('12N53 -80321','Charge Pump',5,'',0.00,26000.00,24000.00,15000.00,0,10,'','null',1),('297 FS','Floating Seal',10,'',0.00,5500.00,5000.00,4000.00,0,20,'','null',1),('AS 022','o-ring',250,'',0.00,7.50,7.50,3.00,0,300,'','null',1),('AS 034','o-ring',250,'',0.00,15.00,15.00,7.50,0,300,'','null',1),('AS 040','o-ring',250,'',0.00,30.00,30.00,10.00,0,300,'','null',1),('AS 328','o-ring',250,'',0.00,25.00,20.00,15.00,0,300,'','null',1),('BRNW 70*83*3','Back up ring',50,'',0.00,195.00,180.00,74.00,0,100,'','null',1),('Cancelled','Cancelled',0,'',0.00,0.00,0.00,0.00,0,10000,'','null',1),('DKB 22*32','wiper seal',25,'',0.00,175.00,150.00,95.00,0,100,'','null',1),('DKB 65*79','Wiper Seal',25,'',0.00,275.00,250.00,195.00,0,100,'','null',1),('HBY 70','Buffer Seal',25,'',0.00,480.00,395.00,210.00,0,50,'','null',1),('IDI 50*65*9','Mono Seal',25,'',0.00,215.00,165.00,120.00,0,50,'','null',1),('ISI 22*30*5','Mono Seal',25,'',0.00,100.00,80.00,60.00,0,50,'','null',1),('KP 35 (2MM)','Thrust plate',30,'',0.00,1200.00,1000.00,250.00,0,60,'','null',1),('LBA 7002','Head Light',5,'',0.00,1100.00,900.00,450.00,0,10,'','null',1),('LBA 7005','Head Light',5,'',0.00,1200.00,950.00,450.00,0,10,'','null',1),('NCF 110 (5)','NCF',10,'',0.00,1550.00,1300.00,980.00,0,50,'','null',1),('ODI 70*85*9','Mono Seal',25,'',0.00,380.00,350.00,250.00,0,100,'','null',1),('P 100','o-ring',250,'',0.00,53.00,53.00,37.50,0,300,'','null',1),('P 39','o-ring',250,'',0.00,10.00,10.00,6.00,0,300,'','null',1),('P18','o-ring',250,'',0.00,6.50,6.50,3.00,0,300,'','null',1),('P50-55','o-ring',250,'',0.00,23.50,23.50,10.00,0,300,'','null',1),('P8','o-ring',250,'',0.00,4.50,4.50,1.50,0,300,'','null',1),('SGP1','Thrust Plate',30,'',0.00,2400.00,2000.00,1000.00,0,60,'','null',1),('SGP1- BIG','Repair Kit',2,'',0.00,2400.00,2000.00,1200.00,0,5,'','null',1),('SGP1- SMALL','Repair Kit',2,'',0.00,1200.00,1000.00,800.00,0,5,'','null',1),('T3P 100','Back up ring',100,'',0.00,140.00,130.00,75.00,0,250,'','null',1),('T3P 18','Back up ring',20,'',0.00,25.00,20.00,12.00,0,40,'','null',1),('T3P 8','Back up ring',100,'',0.00,20.00,15.00,12.00,0,200,'','null',1),('TCV 12*22*8','oil seal',10,'',0.00,120.00,95.00,50.00,0,20,'','null',1);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payments`
--

DROP TABLE IF EXISTS `payments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payments` (
  `payment_id` int(11) NOT NULL AUTO_INCREMENT,
  `amount` decimal(10,2) NOT NULL,
  `purchase_transaction_id` int(11) NOT NULL,
  `notes` varchar(500) DEFAULT NULL,
  `date` date NOT NULL,
  `approved_by` varchar(45) DEFAULT NULL,
  `prepared_by` varchar(45) DEFAULT NULL,
  `received_by` varchar(45) DEFAULT NULL,
  `payments_type` varchar(100) NOT NULL,
  `credit_memo_id` varchar(45) DEFAULT NULL,
  `approved_date` date DEFAULT NULL,
  `received_date` date DEFAULT NULL,
  `prepared_date` date DEFAULT NULL,
  PRIMARY KEY (`payment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payments`
--

LOCK TABLES `payments` WRITE;
/*!40000 ALTER TABLE `payments` DISABLE KEYS */;
/*!40000 ALTER TABLE `payments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ptlineitem`
--

DROP TABLE IF EXISTS `ptlineitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ptlineitem` (
  `purchase_transaction_id` varchar(45) NOT NULL,
  `quantity` int(11) NOT NULL,
  `part_num` varchar(20) NOT NULL,
  `unit_price` int(11) NOT NULL,
  `line_total` decimal(10,2) NOT NULL,
  KEY `purchase_transaction_id_idx` (`purchase_transaction_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ptlineitem`
--

LOCK TABLES `ptlineitem` WRITE;
/*!40000 ALTER TABLE `ptlineitem` DISABLE KEYS */;
/*!40000 ALTER TABLE `ptlineitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchasetransaction`
--

DROP TABLE IF EXISTS `purchasetransaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchasetransaction` (
  `purchase_transaction_id` varchar(45) NOT NULL,
  `company_id` int(11) NOT NULL,
  `date` date NOT NULL,
  `original_amount` decimal(10,2) NOT NULL,
  `discount` decimal(10,2) DEFAULT NULL,
  `ref_sales_invoice_num` varchar(45) DEFAULT NULL,
  `ordered_by` varchar(45) DEFAULT NULL,
  `po_num` varchar(45) DEFAULT NULL,
  `received_by` varchar(45) DEFAULT NULL,
  `receiving_notes` varchar(45) DEFAULT NULL,
  `vat` decimal(10,2) DEFAULT NULL,
  `delivery_receipt_num` varchar(45) DEFAULT NULL,
  `current_balance` decimal(10,2) NOT NULL,
  `status` varchar(45) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  `subtotal` decimal(10,2) DEFAULT '0.00',
  PRIMARY KEY (`purchase_transaction_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchasetransaction`
--

LOCK TABLES `purchasetransaction` WRITE;
/*!40000 ALTER TABLE `purchasetransaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `purchasetransaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `returnslip`
--

DROP TABLE IF EXISTS `returnslip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `returnslip` (
  `return_slip_id` varchar(45) NOT NULL,
  `date` date NOT NULL,
  `total_amount` decimal(10,2) NOT NULL,
  `company_id` int(11) NOT NULL,
  `purchase_transaction_num` varchar(45) NOT NULL,
  `returned_by` varchar(45) DEFAULT NULL,
  `returned_date` varchar(45) DEFAULT NULL,
  `approved_by` varchar(45) DEFAULT NULL,
  `received_by` varchar(45) DEFAULT NULL,
  `notes` varchar(500) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `approved_date` varchar(45) DEFAULT NULL,
  `received_date` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`return_slip_id`),
  KEY `company_id_idx` (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `returnslip`
--

LOCK TABLES `returnslip` WRITE;
/*!40000 ALTER TABLE `returnslip` DISABLE KEYS */;
/*!40000 ALTER TABLE `returnslip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rslineitem`
--

DROP TABLE IF EXISTS `rslineitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rslineitem` (
  `return_slip_id` varchar(45) NOT NULL,
  `quantity` int(11) NOT NULL,
  `part_num` varchar(20) NOT NULL,
  `unit_price` decimal(10,2) NOT NULL,
  `line_total` decimal(10,2) NOT NULL,
  `status` int(1) NOT NULL DEFAULT '0',
  KEY `return_slip_id_idx` (`return_slip_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rslineitem`
--

LOCK TABLES `rslineitem` WRITE;
/*!40000 ALTER TABLE `rslineitem` DISABLE KEYS */;
/*!40000 ALTER TABLE `rslineitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salesinvoice`
--

DROP TABLE IF EXISTS `salesinvoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `salesinvoice` (
  `sales_invoice_id` varchar(45) NOT NULL,
  `company_id` int(11) NOT NULL,
  `date` date NOT NULL,
  `original_amount` decimal(10,2) NOT NULL,
  `vat` decimal(10,2) NOT NULL,
  `discount` decimal(10,2) NOT NULL,
  `po_num` varchar(45) DEFAULT NULL,
  `ordered_by` varchar(45) DEFAULT NULL,
  `sales_person` varchar(45) DEFAULT NULL,
  `delivered_by` varchar(45) DEFAULT NULL,
  `delivery_notes` varchar(45) DEFAULT NULL,
  `delivery_receipt_num` varchar(45) DEFAULT NULL,
  `pwd_id_number_notes` varchar(45) DEFAULT NULL,
  `current_balance` decimal(10,2) NOT NULL DEFAULT '0.00',
  `status` varchar(45) NOT NULL DEFAULT 'Open',
  `address` varchar(100) DEFAULT NULL,
  `subtotal` decimal(10,2) NOT NULL DEFAULT '0.00',
  `type` varchar(45) NOT NULL DEFAULT 'Sales Invoice',
  PRIMARY KEY (`sales_invoice_id`),
  KEY `customerid_idx` (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salesinvoice`
--

LOCK TABLES `salesinvoice` WRITE;
/*!40000 ALTER TABLE `salesinvoice` DISABLE KEYS */;
INSERT INTO `salesinvoice` VALUES ('5805',17,'2014-08-14',0.00,0.00,0.00,'','','','','','','',0.00,'Closed',NULL,0.00,'Sales Invoice'),('5806',35,'2015-08-01',668.80,91.20,0.00,'','','','','','','',760.00,'Open',NULL,0.00,'Sales Invoice');
/*!40000 ALTER TABLE `salesinvoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `silineitem`
--

DROP TABLE IF EXISTS `silineitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `silineitem` (
  `sales_invoice_id` varchar(45) NOT NULL,
  `quantity` int(11) NOT NULL,
  `part_num` varchar(20) NOT NULL,
  `unit_price` decimal(10,2) NOT NULL DEFAULT '0.00',
  `line_total` decimal(10,2) NOT NULL DEFAULT '0.00',
  KEY `sales_invoice_id_idx` (`sales_invoice_id`),
  CONSTRAINT `sales_invoice_id` FOREIGN KEY (`sales_invoice_id`) REFERENCES `salesinvoice` (`sales_invoice_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `silineitem`
--

LOCK TABLES `silineitem` WRITE;
/*!40000 ALTER TABLE `silineitem` DISABLE KEYS */;
INSERT INTO `silineitem` VALUES ('5805',1,'Cancelled',0.00,0.00),('5806',2,'ODI 70*85*9',380.00,760.00);
/*!40000 ALTER TABLE `silineitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supplier` (
  `supplier_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `address_location` varchar(100) DEFAULT NULL,
  `address_city` varchar(45) DEFAULT NULL,
  `address_country` varchar(45) DEFAULT NULL,
  `addresspostalcode` varchar(45) DEFAULT NULL,
  `phone1` int(11) DEFAULT NULL,
  `phone2` int(11) DEFAULT NULL,
  `phone3` int(11) DEFAULT NULL,
  `faxnum` int(11) DEFAULT NULL,
  `website` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `contact_person` varchar(45) DEFAULT NULL,
  `status` varchar(45) NOT NULL DEFAULT 'Active',
  `credit_limit` int(11) DEFAULT NULL,
  `terms` int(11) DEFAULT NULL,
  `current_balance` float NOT NULL DEFAULT '0',
  PRIMARY KEY (`supplier_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `systemaccount`
--

DROP TABLE IF EXISTS `systemaccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `systemaccount` (
  `account_num` varchar(250) NOT NULL,
  `account_name` varchar(100) DEFAULT NULL,
  `bank_name` varchar(45) NOT NULL,
  `bank_branch` varchar(45) NOT NULL,
  `type` int(1) NOT NULL,
  PRIMARY KEY (`account_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `systemaccount`
--

LOCK TABLES `systemaccount` WRITE;
/*!40000 ALTER TABLE `systemaccount` DISABLE KEYS */;
/*!40000 ALTER TABLE `systemaccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `systeminfo`
--

DROP TABLE IF EXISTS `systeminfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `systeminfo` (
  `system_info_id` int(11) NOT NULL,
  `company_name` varchar(60) NOT NULL,
  `company_address` varchar(200) DEFAULT NULL,
  `vat_percentage` int(11) NOT NULL,
  `credit_alert` int(11) NOT NULL,
  `terms_report` int(11) NOT NULL,
  PRIMARY KEY (`system_info_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `systeminfo`
--

LOCK TABLES `systeminfo` WRITE;
/*!40000 ALTER TABLE `systeminfo` DISABLE KEYS */;
INSERT INTO `systeminfo` VALUES (1,'Hydraforce Enterprises',NULL,12,20,30);
/*!40000 ALTER TABLE `systeminfo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-04-20 23:36:10
