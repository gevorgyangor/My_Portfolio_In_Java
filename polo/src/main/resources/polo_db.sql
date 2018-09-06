/*
SQLyog Ultimate v12.14 (64 bit)
MySQL - 10.1.34-MariaDB : Database - polo_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`polo_db` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `polo_db`;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `age` int(3) DEFAULT NULL,
  `phone_number` varchar(15) DEFAULT NULL,
  `username` varchar(32) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `repeat_password` varchar(255) DEFAULT NULL,
  `user_type` enum('USER','ADMIN') DEFAULT 'USER',
  `verify` tinyint(1) NOT NULL DEFAULT '0',
  `token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`id`,`name`,`surname`,`age`,`phone_number`,`username`,`email`,`password`,`repeat_password`,`user_type`,`verify`,`token`) values 
(1,'admin','admin',24,'00000000','Admin','admin@gmail.com','$2a$10$lpQMLngJVAytRLTeTqO4xurIeHLuH.bgWwjG.Oq.8YGtScHAiAtde','$2a$10$.t.MprmGUB2wMltQyPKztO7QwyFrXjKeyKF1xqSdukrk.OmQ31OOq','ADMIN',0,NULL),
(9,'Aram','Hovhannisyan',22,'+37498288024','Aram','gevorgyan604@mail.ru','1234','$2a$10$9/UYM9uDNHaU1Xp0dRgYoeaHsyurElgial7wU70mSclJmLYFeQFQq','USER',0,'d475c6cd-b30a-4cf9-b137-b634bda692eb'),
(10,'Gor','Gevorgyan',24,'+37498149490','Gor','gevorgyan604@mail.ru','1234','$2a$10$5BLivcav/erDKkF7dx5bVO93R16IM9PHg6xbrh6EJOOG..asE9ZPS','USER',0,'b7fefa53-5abb-44da-9259-f90c59e6b37b');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
