CREATE DATABASE  IF NOT EXISTS `hydraforce_db` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `hydraforce_db`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: hydraforce_db
-- ------------------------------------------------------
-- Server version	5.6.20

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
  `original_amount` float NOT NULL,
  `po_num` varchar(45) DEFAULT NULL,
  `ordered_by` varchar(45) DEFAULT NULL,
  `sales_person` varchar(45) DEFAULT NULL,
  `delivered_by` varchar(45) DEFAULT NULL,
  `delivery_notes` varchar(45) DEFAULT NULL,
  `delivery_receipt_num` varchar(45) DEFAULT NULL,
  `discount` float DEFAULT NULL,
  `current_balance` float NOT NULL DEFAULT '0',
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`acknowledgement_receipt_id`),
  KEY `customer_id_idx` (`company_id`),
  CONSTRAINT `customer_id` FOREIGN KEY (`company_id`) REFERENCES `company` (`company_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acknowledgementreceipt`
--

LOCK TABLES `acknowledgementreceipt` WRITE;
/*!40000 ALTER TABLE `acknowledgementreceipt` DISABLE KEYS */;
INSERT INTO `acknowledgementreceipt` VALUES ('1234',2,'2014-02-12',10,'123','123','123','123','123','123',123,123,'sdasda');
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
  `unit_price` float NOT NULL,
  `line_total` float NOT NULL,
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
  `system_account_num` int(20) DEFAULT NULL,
  PRIMARY KEY (`bb_collection_id`),
  KEY `system_account_num_idx` (`system_account_num`),
  CONSTRAINT `system_account_num` FOREIGN KEY (`system_account_num`) REFERENCES `systemaccount` (`account_num`) ON DELETE NO ACTION ON UPDATE NO ACTION
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
  `account_name` varchar(45) DEFAULT NULL,
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
  `collection_type_id` int(11) NOT NULL,
  `date` date NOT NULL,
  `amount` float NOT NULL,
  `type` varchar(45) NOT NULL,
  `number` varchar(45) NOT NULL,
  `notes` varchar(500) DEFAULT NULL,
  `reveived_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`collection_id`),
  KEY `collection_type_id_idx` (`collection_type_id`),
  CONSTRAINT `collection_type_id` FOREIGN KEY (`collection_type_id`) REFERENCES `bbcollection` (`bb_collection_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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
  `name` varchar(50) NOT NULL,
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
  `credit_limit` float NOT NULL,
  `terms` int(11) NOT NULL,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (1,'Janine','asdsaffdgdsas','ffsdsadasd','fasdfsafdf','afddsafasdfaf','123','123','123','123','dsfdsfsd','dfsdf','sdfsdfsfsfsd','Active',12,3,'customer'),(2,'Richelle','adkldjsfsk','sdfjslkdfjl','ksdflskdjfl','kdflkjflkdjsa','123','123','123','123','kxdlksjl','kjsdlkfjsl','kdfslkj','Active',12,3,'customer');
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
  PRIMARY KEY (`credit_memo_id`)
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
-- Table structure for table `debitmemo`
--

DROP TABLE IF EXISTS `debitmemo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `debitmemo` (
  `debit_memo_id` varchar(45) NOT NULL,
  `date` date NOT NULL,
  `company_id` int(11) NOT NULL,
  `total_amount` float NOT NULL,
  `type` varchar(45) NOT NULL,
  `number` varchar(45) NOT NULL,
  `approved_by` varchar(45) DEFAULT NULL,
  `received_by` varchar(45) DEFAULT NULL,
  `approved_date` date DEFAULT NULL,
  `notes` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`debit_memo_id`)
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
  `last_cost` float NOT NULL DEFAULT '0',
  `walk_in_price` float NOT NULL DEFAULT '0',
  `traders_price` float NOT NULL DEFAULT '0',
  `sister_company_price` float NOT NULL DEFAULT '0',
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
INSERT INTO `item` VALUES ('123','nothing',2,'sdda',60,50,50,50,0,0,'','null',1);
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
  `payments_type_id` int(11) NOT NULL,
  `amount` float NOT NULL,
  `purchase_transaction_id` int(11) NOT NULL,
  `notes` varchar(500) DEFAULT NULL,
  `date` date NOT NULL,
  `approved_by` varchar(45) DEFAULT NULL,
  `prepared_by` varchar(45) DEFAULT NULL,
  `received_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`payment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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
  `purchase_transaction_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `part_num` varchar(20) NOT NULL,
  `unit_price` int(11) NOT NULL,
  `line_total` float NOT NULL
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
  `purchase_transaction_id` int(11) NOT NULL AUTO_INCREMENT,
  `company_id` int(11) NOT NULL,
  `date` date NOT NULL,
  `original_amount` float NOT NULL,
  `discount` float DEFAULT NULL,
  `ref_sales_invoice_num` varchar(45) DEFAULT NULL,
  `ordered_by` varchar(45) DEFAULT NULL,
  `po_num` varchar(45) DEFAULT NULL,
  `received_by` varchar(45) DEFAULT NULL,
  `receiving_notes` varchar(45) DEFAULT NULL,
  `vat` float DEFAULT NULL,
  `delivery_receipt_num` varchar(45) DEFAULT NULL,
  `current_balance` float NOT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`purchase_transaction_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchasetransaction`
--

LOCK TABLES `purchasetransaction` WRITE;
/*!40000 ALTER TABLE `purchasetransaction` DISABLE KEYS */;
INSERT INTO `purchasetransaction` VALUES (1,2,'2015-01-13',23,1,'fsdfs','dfs','fsf','sdfdsfs','dsfsfs',12,'ddfdsv',12,'dfsdfs'),(2,1,'2016-02-02',123,1,'dfdsf','dfsdf','dfsfs','dsfs','sdfsdf',13,'cvsdf',12,'sdfs');
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
  `total_amount` float NOT NULL,
  `company_id` int(11) NOT NULL,
  `purchase_transaction_num` int(11) NOT NULL,
  `returned_by` varchar(45) DEFAULT NULL,
  `returned_date` varchar(45) DEFAULT NULL,
  `approved_by` varchar(45) DEFAULT NULL,
  `received_by` varchar(45) DEFAULT NULL,
  `notes` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`return_slip_id`)
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
  `unit_price` float NOT NULL,
  `line_total` float NOT NULL
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
  `original_amount` float NOT NULL,
  `vat` float NOT NULL,
  `discount` float NOT NULL,
  `po_num` varchar(45) DEFAULT NULL,
  `ordered_by` varchar(45) DEFAULT NULL,
  `sales_person` varchar(45) DEFAULT NULL,
  `delivered_by` varchar(45) DEFAULT NULL,
  `delivery_notes` varchar(45) DEFAULT NULL,
  `delivery_receipt_num` varchar(45) DEFAULT NULL,
  `pwd_id_number_notes` varchar(45) DEFAULT NULL,
  `current_balance` float NOT NULL DEFAULT '0',
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`sales_invoice_id`),
  KEY `customerid_idx` (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salesinvoice`
--

LOCK TABLES `salesinvoice` WRITE;
/*!40000 ALTER TABLE `salesinvoice` DISABLE KEYS */;
INSERT INTO `salesinvoice` VALUES ('1234',2,'2014-02-12',21,212,12,'12','21','sdasd','dada','sadasda','123','xcvcsc',12,'dasd');
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
  `qunatity` int(11) NOT NULL,
  `part_num` varchar(20) NOT NULL,
  `unit_price` float NOT NULL,
  `line_total` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `silineitem`
--

LOCK TABLES `silineitem` WRITE;
/*!40000 ALTER TABLE `silineitem` DISABLE KEYS */;
/*!40000 ALTER TABLE `silineitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `systemaccount`
--

DROP TABLE IF EXISTS `systemaccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `systemaccount` (
  `account_num` int(20) NOT NULL,
  `account_name` varchar(45) NOT NULL,
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
  `company_name` varchar(60) NOT NULL,
  `company_address` varchar(100) NOT NULL,
  `vat_percentage` int(11) NOT NULL,
  `credit_alert` int(11) NOT NULL,
  `terms_report` int(11) NOT NULL,
  PRIMARY KEY (`company_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `systeminfo`
--

LOCK TABLES `systeminfo` WRITE;
/*!40000 ALTER TABLE `systeminfo` DISABLE KEYS */;
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

-- Dump completed on 2015-03-09  0:44:14
