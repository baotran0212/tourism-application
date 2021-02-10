-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: tourism
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.17-MariaDB

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
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `identity_card` varchar(255) DEFAULT NULL,
  `address1` varchar(255) DEFAULT NULL,
  `address2` varchar(255) DEFAULT NULL,
  `address3` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Lam','123456789','Lam\'s address 1','Lam\'s address 2','Lam\'s address 3',NULL,'nam','0912345678'),(2,'Vy','1234564534','Lam\'s address 1','Lam\'s address 2','Lam\'s address 3',NULL,'nu','0912345564');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `identity_card` varchar(255) DEFAULT NULL,
  `address1` varchar(255) DEFAULT NULL,
  `address2` varchar(255) DEFAULT NULL,
  `address3` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'name1','1223343','address1','address2','address3',NULL,'male','123435678','active'),(2,'name2','1234567','address2','``','\'\'',NULL,'female','0985654745','active');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel`
--

DROP TABLE IF EXISTS `hotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hotel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `price` double(15,2) DEFAULT NULL,
  `address1` varchar(255) DEFAULT NULL,
  `address2` varchar(255) DEFAULT NULL,
  `address3` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel`
--

LOCK TABLES `hotel` WRITE;
/*!40000 ALTER TABLE `hotel` DISABLE KEYS */;
INSERT INTO `hotel` VALUES (1,'Ngoc Lan',300000.00,'address 1','address 2','address 3',NULL),(2,'Ngọc Dung',500000.00,'address 1','address 2','address 3',NULL);
/*!40000 ALTER TABLE `hotel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `location` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `address1` varchar(255) DEFAULT NULL,
  `address2` varchar(255) DEFAULT NULL,
  `address3` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES (1,'Tỉnh Khánh Hòa - Thành phố Nha Trang - Phường Vĩnh Hòa','Tỉnh Khánh Hòa','Thành phố Nha Trang','Phường Vĩnh Hòa',NULL),(2,'Tỉnh An Giang - Thành phố Long Xuyên - Phường Mỹ Xuyên','Tỉnh An Giang','Thành phố Long Xuyên','Phường Mỹ Xuyên',NULL),(3,'Thành phố Hồ Chí Minh - Quận 1 - Phường Tân Định','Thành phố Hồ Chí Minh','Quận 1','Phường Tân Định',NULL),(4,'Tỉnh Lâm Đồng - Thành phố Đà Lạt - Phường 7','Tỉnh Lâm Đồng','Thành phố Đà Lạt','Phường 7',NULL),(5,'Tỉnh Lâm Đồng - Thành phố Bảo Lộc - Phường Lộc Phát','Tỉnh Lâm Đồng','Thành phố Bảo Lộc','Phường Lộc Phát',NULL);
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `position`
--

DROP TABLE IF EXISTS `position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `position` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position`
--

LOCK TABLES `position` WRITE;
/*!40000 ALTER TABLE `position` DISABLE KEYS */;
INSERT INTO `position` VALUES (1,'lái xe'),(2,'hướng dẫn viên'),(3,'phục vụ'),(4,'thông dịch viên'),(5,'tiền trạm');
/*!40000 ALTER TABLE `position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `position_in_tour`
--

DROP TABLE IF EXISTS `position_in_tour`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `position_in_tour` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tourist_group_id` int(11) NOT NULL,
  `position_id` int(11) NOT NULL,
  `employee_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `role_tour_id` (`position_id`),
  KEY `employee_id` (`employee_id`),
  KEY `position_in_tour_ibfk_1_idx` (`tourist_group_id`),
  CONSTRAINT `position_in_tour_ibfk_1` FOREIGN KEY (`tourist_group_id`) REFERENCES `tourist_group` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `position_in_tour_ibfk_2` FOREIGN KEY (`position_id`) REFERENCES `position` (`id`),
  CONSTRAINT `position_in_tour_ibfk_3` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position_in_tour`
--

LOCK TABLES `position_in_tour` WRITE;
/*!40000 ALTER TABLE `position_in_tour` DISABLE KEYS */;
INSERT INTO `position_in_tour` VALUES (1,1,1,1),(2,1,1,2),(3,1,2,1),(4,1,2,2),(5,1,3,1),(6,1,3,2),(7,1,4,1);
/*!40000 ALTER TABLE `position_in_tour` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tour`
--

DROP TABLE IF EXISTS `tour`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tour` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `price` double(15,2) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `type_id` (`type_id`),
  CONSTRAINT `tour_ibfk_1` FOREIGN KEY (`type_id`) REFERENCES `type_of_tour` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tour`
--

LOCK TABLES `tour` WRITE;
/*!40000 ALTER TABLE `tour` DISABLE KEYS */;
INSERT INTO `tour` VALUES (1,1,'Tour Sài Gòn – Nha Trang','\"\"',300000.00,'active'),(2,3,'Sài Gòn – Đà Lạt','description',500000.00,'active'),(3,2,'Sài Gòn – Miền Tây','description',500000.00,'active'),(4,2,'Sài Gòn – Tây Nguyên','descrip',200000.00,'active');
/*!40000 ALTER TABLE `tour` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tour_location`
--

DROP TABLE IF EXISTS `tour_location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tour_location` (
  `tour_id` int(11) NOT NULL,
  `location_id` int(11) NOT NULL,
  PRIMARY KEY (`tour_id`,`location_id`),
  KEY `location_id` (`location_id`),
  CONSTRAINT `tour_location_ibfk_1` FOREIGN KEY (`tour_id`) REFERENCES `tour` (`id`),
  CONSTRAINT `tour_location_ibfk_2` FOREIGN KEY (`location_id`) REFERENCES `location` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tour_location`
--

LOCK TABLES `tour_location` WRITE;
/*!40000 ALTER TABLE `tour_location` DISABLE KEYS */;
/*!40000 ALTER TABLE `tour_location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tourist_group`
--

DROP TABLE IF EXISTS `tourist_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tourist_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tour_id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `depature_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `description` text DEFAULT NULL,
  `food_price` double(15,2) DEFAULT NULL,
  `transport_price` double(15,2) DEFAULT NULL,
  `hotel_price` double(15,2) DEFAULT NULL,
  `other_price` double(15,2) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tourist_group_tour_fk` (`tour_id`),
  CONSTRAINT `tourist_group_tour_fk` FOREIGN KEY (`tour_id`) REFERENCES `tour` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tourist_group`
--

LOCK TABLES `tourist_group` WRITE;
/*!40000 ALTER TABLE `tourist_group` DISABLE KEYS */;
INSERT INTO `tourist_group` VALUES (1,1,'name tour','2020-01-01','2020-01-05','this is description',300.00,400.00,500.00,800.00,NULL),(2,1,'name tour 2','2020-02-01','2020-02-05','this is description 2',300.00,400.00,500.00,800.00,NULL),(3,1,'name tour 3','2020-03-01','2020-03-05','this is description 3',300.00,400.00,500.00,800.00,NULL);
/*!40000 ALTER TABLE `tourist_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tourist_group_customer`
--

DROP TABLE IF EXISTS `tourist_group_customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tourist_group_customer` (
  `tourist_group_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  PRIMARY KEY (`tourist_group_id`,`customer_id`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `tourist_group_customer_ibfk_1` FOREIGN KEY (`tourist_group_id`) REFERENCES `tourist_group` (`id`),
  CONSTRAINT `tourist_group_customer_ibfk_2` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tourist_group_customer`
--

LOCK TABLES `tourist_group_customer` WRITE;
/*!40000 ALTER TABLE `tourist_group_customer` DISABLE KEYS */;
/*!40000 ALTER TABLE `tourist_group_customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tourist_group_hotel`
--

DROP TABLE IF EXISTS `tourist_group_hotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tourist_group_hotel` (
  `tourist_group_id` int(11) NOT NULL,
  `hotel_id` int(11) NOT NULL,
  PRIMARY KEY (`tourist_group_id`,`hotel_id`),
  KEY `tourist_group_hotel_ibfk_2_idx` (`hotel_id`),
  CONSTRAINT `tourist_group_hotel_ibfk_1` FOREIGN KEY (`tourist_group_id`) REFERENCES `tourist_group` (`id`),
  CONSTRAINT `tourist_group_hotel_ibfk_2` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tourist_group_hotel`
--

LOCK TABLES `tourist_group_hotel` WRITE;
/*!40000 ALTER TABLE `tourist_group_hotel` DISABLE KEYS */;
/*!40000 ALTER TABLE `tourist_group_hotel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type_of_tour`
--

DROP TABLE IF EXISTS `type_of_tour`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `type_of_tour` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_of_tour`
--

LOCK TABLES `type_of_tour` WRITE;
/*!40000 ALTER TABLE `type_of_tour` DISABLE KEYS */;
INSERT INTO `type_of_tour` VALUES (1,'du lịch di động'),(2,'du lịch kết hợp nghề nghiệp'),(3,'du lịch xã hội và gia đình');
/*!40000 ALTER TABLE `type_of_tour` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-08 10:07:17
