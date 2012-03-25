/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;


DROP DATABASE IF EXISTS `crm`;
CREATE DATABASE `crm` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `crm`;
DROP TABLE IF EXISTS `assignment`;
CREATE TABLE `assignment` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `memo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `campaign`;
CREATE TABLE `campaign` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `memo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `fax` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `zip` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

INSERT INTO `company` VALUES (1,'test','11123',NULL,'shanghai',NULL,'test comany');
INSERT INTO `company` VALUES (3,'aaa',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `company` VALUES (4,'aaa','1212',NULL,'gfk',NULL,'');
INSERT INTO `company` VALUES (11,'123','123',NULL,'aa',NULL,'bb');
INSERT INTO `company` VALUES (14,'1','2',NULL,'3',NULL,'43');
INSERT INTO `company` VALUES (16,'test company','010-1234567',NULL,'address',NULL,'just a memo');
DROP TABLE IF EXISTS `contact`;
CREATE TABLE `contact` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `cellphone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `birthday` varchar(255) DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `zip` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

INSERT INTO `contact` VALUES (1,'11',NULL,NULL,'34','1test',NULL,NULL,NULL,NULL);
INSERT INTO `contact` VALUES (4,'aaa_bbb',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `contact` VALUES (5,'aaa_bbb',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `contact` VALUES (9,'aaa_bbb',NULL,NULL,'mobile','email',NULL,NULL,NULL,NULL);
INSERT INTO `contact` VALUES (11,'11',NULL,NULL,'11','11',NULL,1,NULL,NULL);
INSERT INTO `contact` VALUES (13,'123',NULL,NULL,'456','789',NULL,NULL,NULL,NULL);
INSERT INTO `contact` VALUES (14,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `contact` VALUES (15,'aaa',NULL,NULL,'wew','sdd',NULL,3,NULL,NULL);
INSERT INTO `contact` VALUES (16,'aaa',NULL,NULL,'232','12121',NULL,4,NULL,NULL);
INSERT INTO `contact` VALUES (17,'1',NULL,NULL,'1212','sdfasfd',NULL,14,NULL,NULL);
INSERT INTO `contact` VALUES (18,'test',NULL,NULL,'121','121sdfsdf',NULL,1,NULL,NULL);
INSERT INTO `contact` VALUES (19,'aaa',NULL,NULL,'adsasd','saasd',NULL,3,NULL,NULL);
INSERT INTO `contact` VALUES (20,'aaa',NULL,NULL,'3333','4444',NULL,3,NULL,NULL);
INSERT INTO `contact` VALUES (21,'ji',NULL,NULL,'222','4444433222',NULL,3,NULL,NULL);
INSERT INTO `contact` VALUES (22,'aaa',NULL,NULL,'121','qwqw',NULL,4,NULL,NULL);
INSERT INTO `contact` VALUES (23,'aaa44',NULL,NULL,'444','4444',NULL,4,NULL,NULL);
INSERT INTO `contact` VALUES (24,'contact001',NULL,NULL,'1389','test@test.com',NULL,1,NULL,NULL);
INSERT INTO `contact` VALUES (25,'contact001',NULL,NULL,'1389','test@test.com',NULL,1,NULL,NULL);
INSERT INTO `contact` VALUES (26,'test',NULL,NULL,'sdsd','sds',NULL,1,NULL,NULL);
INSERT INTO `contact` VALUES (27,'test',NULL,NULL,'sdsd','sds',NULL,1,NULL,NULL);
INSERT INTO `contact` VALUES (28,'test',NULL,NULL,'sdsd','sds',NULL,1,NULL,NULL);
INSERT INTO `contact` VALUES (31,'test',NULL,NULL,'sdsd','sds',NULL,1,NULL,NULL);
INSERT INTO `contact` VALUES (32,'test',NULL,NULL,'sdsd','sds',NULL,1,NULL,NULL);
INSERT INTO `contact` VALUES (33,'test',NULL,NULL,'sdsd','sds',NULL,1,NULL,NULL);
INSERT INTO `contact` VALUES (34,'test',NULL,NULL,'sdsd','sds',NULL,1,NULL,NULL);
INSERT INTO `contact` VALUES (35,'test',NULL,NULL,'sdsd','sds',NULL,1,NULL,NULL);
INSERT INTO `contact` VALUES (36,'test',NULL,NULL,'sdsd','sds',NULL,1,NULL,NULL);
INSERT INTO `contact` VALUES (37,'test',NULL,NULL,'sdsd','sds',NULL,1,NULL,NULL);
INSERT INTO `contact` VALUES (38,'test',NULL,NULL,'sdsd','sds',NULL,1,NULL,NULL);
INSERT INTO `contact` VALUES (39,'test',NULL,NULL,'sdsd','sds',NULL,1,NULL,NULL);
INSERT INTO `contact` VALUES (40,'test',NULL,NULL,'sdsd','sds',NULL,1,NULL,NULL);
INSERT INTO `contact` VALUES (41,'test',NULL,NULL,'sdsd','sds',NULL,1,NULL,NULL);
INSERT INTO `contact` VALUES (42,'test',NULL,NULL,'sdsd','sds',NULL,1,NULL,NULL);
INSERT INTO `contact` VALUES (43,'test',NULL,NULL,'sdsd','sds',NULL,1,NULL,NULL);
INSERT INTO `contact` VALUES (44,'test',NULL,NULL,'sdsd','sds',NULL,1,NULL,NULL);
INSERT INTO `contact` VALUES (45,'test',NULL,NULL,'sdsd','sds',NULL,1,NULL,NULL);
INSERT INTO `contact` VALUES (46,'test',NULL,NULL,'sdsd','sds',NULL,1,NULL,NULL);
INSERT INTO `contact` VALUES (47,'test',NULL,NULL,'sdsd','sds',NULL,1,NULL,NULL);
INSERT INTO `contact` VALUES (48,'test',NULL,NULL,'sdsd','sds',NULL,1,NULL,NULL);
INSERT INTO `contact` VALUES (49,'test12121',NULL,NULL,'1q21212','ewfdasfasdfdsaf',NULL,1,NULL,NULL);
INSERT INTO `contact` VALUES (50,'test company',NULL,NULL,'','',NULL,16,NULL,NULL);
DROP TABLE IF EXISTS `opportunity`;
CREATE TABLE `opportunity` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `memo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `company` varchar(255) DEFAULT NULL,
  `contact` varchar(255) DEFAULT NULL,
  `product` varchar(255) DEFAULT NULL,
  `order_date` varchar(255) DEFAULT NULL,
  `memo` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `memo` varchar(255) DEFAULT NULL,
  `no` varchar(255) DEFAULT NULL,
  `price` varchar(255) DEFAULT NULL,
  `reserve` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `solution`;
CREATE TABLE `solution` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `memo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `touch`;
CREATE TABLE `touch` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `touch_date` varchar(255) DEFAULT NULL,
  `contact_id` varchar(255) DEFAULT NULL,
  `touch_type` varchar(255) DEFAULT NULL,
  `memo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

INSERT INTO `touch` VALUES (1,'2011-11-11 11:30:33','9',NULL,'test memo');
INSERT INTO `touch` VALUES (2,NULL,NULL,NULL,'23');
INSERT INTO `touch` VALUES (3,'2012-02-25 11:41:48',NULL,NULL,'afsdfasdf');
INSERT INTO `touch` VALUES (4,'2012-02-25 11:44:20',NULL,NULL,'dsfvasdfdsaf');
INSERT INTO `touch` VALUES (5,'2012-03-24 13:02:00',NULL,NULL,'asdfasdf');
INSERT INTO `touch` VALUES (6,'2012-03-24 13:09:05','18',NULL,'asdfasdf');
INSERT INTO `touch` VALUES (7,'2012-03-24 14:00:15','18',NULL,'asdfsda');
INSERT INTO `touch` VALUES (8,'2012-03-24 14:00:29','18',NULL,'asdfasdfsdafasdf sdfasdf');
INSERT INTO `touch` VALUES (9,'2012-03-24 14:00:35','18',NULL,'asdfasdfsdafasdf sdfasdf');
INSERT INTO `touch` VALUES (10,'2012-03-24 14:00:35','18',NULL,'asdfasdfsdafasdf sdfasdf');
INSERT INTO `touch` VALUES (11,'2012-03-24 14:00:35','18',NULL,'asdfasdfsdafasdf sdfasdf');
INSERT INTO `touch` VALUES (12,'2012-03-24 14:00:36','18',NULL,'asdfasdfsdafasdf sdfasdf');
INSERT INTO `touch` VALUES (13,'2012-03-24 14:00:36','18',NULL,'asdfasdfsdafasdf sdfasdf');
INSERT INTO `touch` VALUES (14,'2012-03-24 14:02:03','15',NULL,'????');
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `last_login_date` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

INSERT INTO `user` VALUES (1,NULL,'1231','123',NULL);

/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
