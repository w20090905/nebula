/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;


DROP DATABASE IF EXISTS `bookstore`;
CREATE DATABASE `bookstore` /*!40100 DEFAULT CHARACTER SET gbk */;
USE `bookstore`;
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `A_Id` int(11) NOT NULL AUTO_INCREMENT,
  `A_NAME` varchar(255) DEFAULT NULL,
  `A_PASSWORD` varchar(255) DEFAULT NULL,
  `A_TYPE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`A_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

DROP TABLE IF EXISTS `administrator`;
CREATE TABLE `administrator` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `groupId` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  `memo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=gbk;

INSERT INTO `administrator` VALUES (1,2,'test001','123456',2,'test');
INSERT INTO `administrator` VALUES (3,4,'1','1',1,'1');
INSERT INTO `administrator` VALUES (4,23,'2','2',2,'2');
INSERT INTO `administrator` VALUES (5,3,'3','3',3,'3');
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `Book_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Book_Type` int(11) DEFAULT NULL,
  `Book_Name` varchar(255) DEFAULT NULL,
  `Book_Desc` varchar(255) DEFAULT NULL,
  `Book_Publisher` varchar(255) DEFAULT NULL,
  `Book_Count` int(11) NOT NULL DEFAULT '0',
  `Book_Price` float NOT NULL DEFAULT '0',
  PRIMARY KEY (`Book_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=gbk;

INSERT INTO `book` VALUES (1,1,'国际关系学','a good book','水电出版社',9,14.56);
INSERT INTO `book` VALUES (2,1,'资本论','经典图书','外文出版社',11,25);
INSERT INTO `book` VALUES (3,1,'测试书001','测试书001','测试出版社',13,34);
INSERT INTO `book` VALUES (4,2,'心理学','心理学','外文出版社',4,45);
INSERT INTO `book` VALUES (5,1,'Thinking in Java','a good book for java developer.','abc publish',8,68.93);
INSERT INTO `book` VALUES (6,3,'人月神话','一本软件工程的好书','水电出版社',22,20);
DROP TABLE IF EXISTS `book_type`;
CREATE TABLE `book_type` (
  `Type_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Type_Name` varchar(255) DEFAULT NULL,
  `Type_Desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Type_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

DROP TABLE IF EXISTS `borrow_book`;
CREATE TABLE `borrow_book` (
  `borrow_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Book_Id` int(11) NOT NULL DEFAULT '0',
  `Customer_Id` int(11) NOT NULL DEFAULT '0',
  `Borrow_Date` datetime DEFAULT NULL,
  `End_Date` datetime DEFAULT NULL,
  `Return_Date` datetime DEFAULT NULL,
  `Borrow_Memo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`borrow_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=gbk;

INSERT INTO `borrow_book` VALUES (1,1,10001,'2011-04-16 10:56:56','2011-06-16 10:56:56','2011-04-16 10:56:58',NULL);
INSERT INTO `borrow_book` VALUES (2,6,10001,'2011-04-16 10:57:05','2011-06-16 10:57:05','2011-04-16 10:57:06',NULL);
INSERT INTO `borrow_book` VALUES (3,5,10001,'2011-04-16 10:57:25','2011-06-16 10:57:25',NULL,NULL);
INSERT INTO `borrow_book` VALUES (4,3,10001,'2011-04-16 14:03:09','2011-06-16 14:03:09','2011-04-16 14:03:11',NULL);
INSERT INTO `borrow_book` VALUES (5,3,10001,'2011-04-16 14:03:17','2011-06-16 14:03:17',NULL,NULL);
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Customer_name` varchar(255) DEFAULT NULL,
  `Customer_sex` varchar(255) DEFAULT NULL,
  `Customer_birthday` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `S_ID` int(11) NOT NULL AUTO_INCREMENT,
  `S_NUMBER` varchar(8) DEFAULT NULL,
  `S_PASSWORD` varchar(8) DEFAULT NULL,
  `C_ID` int(11) DEFAULT NULL,
  `S_NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`S_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;


/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
