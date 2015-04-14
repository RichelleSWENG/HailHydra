-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: hydraforce_db
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
INSERT INTO `acknowledgementreceipt` VALUES ('1234',2,'2014-02-12',10.00,'123','123','123','123','123','123',1.00,123.00,'Open','','Acknowledgement Receipt'),('23',2,'2015-03-29',87.00,'345','','','','','45',12.00,87.00,'Open',NULL,'Acknowledgement Receipt'),('6',1,'2015-03-23',290.00,'6','a','a','a','a','6',0.00,0.00,'Closed',NULL,'Acknowledgement Receipt');
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
INSERT INTO `arlineitem` VALUES ('1234',2,'123',10.00,20.00),('6',1,'234',70.00,70.00),('6',1,'SHV_E300K',220.00,220.00),('23',1,'345',87.00,87.00);
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
INSERT INTO `collection` VALUES (1,'2015-04-04',20.00,'Acknowledgement Receipt','6','','','Cash','',NULL),(2,'2015-04-04',20.00,'Acknowledgement Receipt','6','','','Cash','',NULL),(3,'2015-04-04',290.00,'Acknowledgement Receipt','6','','','Cash','',NULL),(4,'2015-04-04',0.00,'Acknowledgement Receipt','6','','','Cash','',NULL),(5,'2015-04-04',0.00,'Acknowledgement Receipt','6','','','Cash','',NULL),(6,'2015-04-04',20.00,'Acknowledgement Receipt','6','','','Cash','',NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (1,'Janine','asdsaffdgdsas','ffsdsadasd','fasdfsafdf','afddsafasdfaf','123','123','123','123','dsfdsfsd','dfsdf','sdfsdfsfsfsd','Active',12.00,3,'Retail Customer'),(2,'Richelle','adkldjsfsk','sdfjslkdfjl','ksdflskdjfl','kdflkjflkdjsa','123','123','123','123','kxdlksjl','kjsdlkfjsl','kdfslkj','Active',500.00,23,'Walk-in Customer'),(4,'dsfdfsdfs','dsfsdfsfsdf23232','fdsfsdfsdf23232','fdsfsdf23232','1234','21834344','','','2313421','','fdgfhfggsdfsfds','','Active',20.00,3,'Retail Customer'),(5,'janien','','','','','','','','','','','','Active',30000.00,30,'Sister Company Customer'),(6,'adsad','','','','','','','','','','','','Active',0.00,30,'Sister Company Customer'),(7,'jeje','taft ','Manila','','','','','','','','','','Active',30.00,30,'Sister Company Customer'),(8,'bianca','','','','','','','','','','','','Active',100.00,30,'Supplier'),(9,'Nancy','','','','','','','','','','','','Active',20.00,30,'Supplier'),(10,'xcsda','','','','','','','','','','','','Active',0.00,0,'Supplier'),(11,'James Harden','','','','','','','','','','','','Active',20.00,0,'Supplier'),(12,'Steph Curry','Oracle Arena','San Francisco','USA','1234','','','','','','','','Active',250000.00,30,'Sister Company Customer');
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
INSERT INTO `creditmemo` VALUES ('000001','2015-04-10','000002','345',1,'Replacement',0.00),('000002','2015-04-10','000001','234',1,'Not Replacement',0.00),('000003','2015-04-10','000003','234',0,'Replacement',0.00),('000004','2015-04-10','000003','234',0,'Not Replacement',0.00),('000005','2015-04-11','000006','234',1,'Not Replacement',0.00),('000006','2015-04-11','000007','234',0,'Not Replacement',0.00),('000007','2015-04-11','000007','234',0,'Replacement',0.00),('000008','2015-04-11','000007','234',0,'Replacement',0.00),('000009','2015-04-11','000007','234',0,'Replacement',0.00),('000010','2015-04-11','000007','234',0,'Not Replacement',0.00),('000011','2015-04-11','000007','234',0,'Replacement',0.00),('000012','2015-04-11','000004','234',1,'Replacement',0.00);
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
INSERT INTO `debitmemo` VALUES ('000001','2015-04-12',4,1000.00,'Sales Invoice Receipt','56s4da56sa','','','',NULL,'',1,'Replacement'),('000002','2015-04-12',2,100.00,'Acknowledgement Receipt','1234','','','',NULL,'',1,'Replacement'),('000003','2015-04-12',2,150.00,'Acknowledgement Receipt','23','','','',NULL,'',0,'Not Replacement'),('000004','2015-04-12',1,45.00,'Acknowledgement Receipt','6','2015-04-15','MArk','2015-04-15','2015-04-20','',0,'Not Replacement');
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
INSERT INTO `dmlineitem` VALUES ('1',2,'123',10.00,20.00),('abc',1,'234',45.00,45.00),('111',1,'234',45.00,45.00),('111',2,'123',50.00,100.00),('000001',20,'123',50.00,1000.00),('000002',2,'123',50.00,100.00),('000003',2,'123',50.00,100.00),('000003',1,'SHV_E300K',50.00,50.00),('000004',1,'234',45.00,45.00);
/*!40000 ALTER TABLE `dmlineitem` ENABLE KEYS */;
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
INSERT INTO `item` VALUES ('123','nothing',2,'sdda',170.00,50.00,50.00,40.00,4,20,'','C:/Users/Janine/Desktop/Inventory/Screen Shot 2015-03-16 at 11.36.15 AM.png',1),('234','cdfjdsl',2,'czxcm,dc',40.00,45.00,45.00,45.00,0,2,'','C:/Users/Janine/Desktop/Inventory/Screen Shot 2015-03-14 at 10.18.37 PM.png',1),('345','great description',5,'',156.00,50.00,100.00,50.00,0,0,'','C:/Users/Janine/Desktop/Inventory/Screen Shot 2015-03-16 at 11.35.54 AM.png',1),('SHV_E300K','Samsung S4',3,'Rack Number 5',200.00,50.00,50.00,55.00,1,5,NULL,'C:/Users/Janine/Desktop/Inventory/Screen Shot 2015-03-14 at 10.32.56 PM.png',1);
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
INSERT INTO `payments` VALUES (1,20.00,1,'','2015-03-29','','','','Cash','',NULL,NULL,NULL),(2,20.00,1,'','2015-03-29','','','','Cash','',NULL,NULL,NULL),(3,20.00,1,'','2015-03-29','','','','Cash','',NULL,NULL,NULL),(4,12.00,1,'','2015-03-29','','','','Cash','',NULL,NULL,NULL),(5,12.00,1,'','2015-03-29','','','','Cash','',NULL,NULL,NULL),(6,12.00,1,'','2015-03-29','','','','Cash','',NULL,NULL,NULL),(7,12.00,1,'','2015-03-29','','','','Cash','',NULL,NULL,NULL),(8,12.00,2,'','2015-03-29','','','','Cash','',NULL,NULL,NULL),(9,12.00,2,'','2015-03-29','','','','Cash','',NULL,NULL,NULL),(10,12.00,3,'','2015-03-29','','','','Cash','',NULL,NULL,NULL),(11,23.00,1,'','2015-03-30','','','','Cash','',NULL,NULL,NULL),(12,123.00,2,'','2015-04-03','','','','Cash','',NULL,NULL,NULL),(13,0.00,3,'','2015-04-03','','','','Cash','',NULL,NULL,NULL);
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
INSERT INTO `ptlineitem` VALUES ('000001',1,'123',170,170.00),('000001',2,'234',40,80.00),('000002',1,'234',40,40.00);
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
INSERT INTO `purchasetransaction` VALUES ('000001',9,'2015-04-11',250.00,0.00,'1616','','6416','','',30.00,'1616',280.00,'Open',NULL,0.00),('000002',8,'2015-04-11',40.00,0.00,'959','','2066','','',4.80,'',44.80,'Open',NULL,0.00);
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
INSERT INTO `returnslip` VALUES ('000001','2015-04-10',0.00,11,'1123','','','','',NULL,'Defective w/Debit Memo','',''),('000002','2015-04-10',0.00,8,'123','','','','',NULL,'Defective w/Debit Memo','',''),('000003','2015-04-10',0.00,10,'123','','','','',NULL,'Functional','',''),('000004','2015-04-11',0.00,8,'1','','','','',NULL,'Defective w/Debit Memo','',''),('000005','2015-04-11',0.00,9,'2','','','','',NULL,'Defective w/Debit Memo','',''),('000006','2015-04-11',0.00,11,'1','','','','',NULL,'Defective w/Debit Memo','',''),('000007','2015-04-11',0.00,9,'1','','','','',NULL,'Functional','',''),('000008','2015-04-12',90.00,11,'1','','','','',NULL,'Functional','','');
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
INSERT INTO `rslineitem` VALUES ('1',2,'123',10.00,20.00,0),('23',1,'345',20.00,20.00,0),('125',1,'234',20.00,20.00,0),('120',1,'234',0.00,0.00,0),('129',1,'234',200.00,200.00,0),('123',1,'234',20.00,20.00,0),('56',1,'123',20.00,20.00,0),('Hellow',1,'234',30.00,30.00,0),('Hellow',1,'SHV_E300K',30.00,30.00,0),('Testing',1,'234',20.00,20.00,0),('Testing',1,'SHV_E300K',30.00,30.00,0),('1st',1,'123',30.00,30.00,0),('1st',1,'234',20.00,20.00,0),('001',2,'234',50.00,100.00,0),('001',1,'123',30.00,30.00,0),('002',1,'123',0.00,0.00,0),('003',0,'345',0.00,0.00,0),('004',1,'234',0.00,0.00,0),('',2,'SHV_E300K',0.00,0.00,0),('party',1,'234',0.00,0.00,0),('eto!',2,'234',0.00,0.00,0),('000001',1,'234',0.00,0.00,0),('000002',0,'345',0.00,0.00,0),('000003',2,'234',0.00,0.00,0),('000004',1,'234',0.00,0.00,0),('000005',1,'234',0.00,0.00,0),('000006',1,'234',0.00,0.00,0),('000007',1,'234',0.00,0.00,0),('000008',1,'234',30.00,30.00,0),('000008',2,'123',30.00,60.00,0);
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
INSERT INTO `salesinvoice` VALUES ('1234',2,'2017-02-12',21.00,212.00,12.00,'12','21','sdasd','dada','sadasda','123','xcvcsc',12.00,'Open','',0.00,'Sales Invoice'),('56s4da56sa',4,'2015-04-12',50.00,6.00,0.00,'13','','','','','adcda54','adsasd',56.00,'Open',NULL,0.00,'Sales Invoice');
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
INSERT INTO `silineitem` VALUES ('1234',2,'123',10.00,20.00),('56s4da56sa',1,'123',50.00,50.00);
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
INSERT INTO `systemaccount` VALUES (123,'df','sadasd','asdsad',0);
/*!40000 ALTER TABLE `systemaccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `systeminfo`
--

DROP TABLE IF EXISTS `systeminfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `systeminfo` (
  `system_info_id` int(11) NOT NULL AUTO_INCREMENT,
  `company_name` varchar(60) NOT NULL,
  `company_address` varchar(200) DEFAULT NULL,
  `vat_percentage` int(11) NOT NULL,
  `credit_alert` int(11) NOT NULL,
  `terms_report` int(11) NOT NULL,
  PRIMARY KEY (`system_info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `systeminfo`
--

LOCK TABLES `systeminfo` WRITE;
/*!40000 ALTER TABLE `systeminfo` DISABLE KEYS */;
INSERT INTO `systeminfo` VALUES (1,'Hydraforce Enterprises','206 Rizal Ave. Ext., 117 Caloocan City, Manila, Philippines',18,70,5);
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

-- Dump completed on 2015-04-12 22:15:02
