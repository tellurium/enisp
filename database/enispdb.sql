-- MySQL dump 10.13  Distrib 5.5.22, for debian-linux-gnu (i686)
--
-- Host: localhost    Database: enispdb
-- ------------------------------------------------------
-- Server version	5.5.22-0ubuntu1

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
-- Table structure for table `cooperativeenttable`
--

DROP TABLE IF EXISTS `cooperativeenttable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cooperativeenttable` (
  `num` int(10) NOT NULL AUTO_INCREMENT,
  `id` int(11) NOT NULL,
  `partnerid` int(11) NOT NULL,
  PRIMARY KEY (`num`,`id`,`partnerid`),
  KEY `coentid` (`partnerid`),
  KEY `entid` (`id`),
  CONSTRAINT `entid` FOREIGN KEY (`id`) REFERENCES `enterprisetable` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cooperativeenttable`
--

LOCK TABLES `cooperativeenttable` WRITE;
/*!40000 ALTER TABLE `cooperativeenttable` DISABLE KEYS */;
INSERT INTO `cooperativeenttable` VALUES (2,10,1),(10,7,2),(3,10,3),(7,10,4),(1,9,5),(30,18,5),(8,9,6),(4,10,7),(9,9,8),(31,18,8),(11,10,9),(5,12,10),(6,13,10),(12,10,11),(13,8,13),(29,18,13),(27,2,15),(14,17,18),(15,12,19),(17,1,19),(19,3,19),(23,11,19),(24,5,19),(28,10,19),(22,19,20),(16,19,21),(20,19,22),(21,14,23),(26,16,23),(18,19,24),(25,12,25);
/*!40000 ALTER TABLE `cooperativeenttable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enterprisetable`
--

DROP TABLE IF EXISTS `enterprisetable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `enterprisetable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `enterprisename` varchar(100) CHARACTER SET latin1 NOT NULL,
  `establishmenttime` date NOT NULL,
  `address` varchar(200) CHARACTER SET latin1 NOT NULL DEFAULT 'none',
  `telephonenumber` varchar(20) CHARACTER SET latin1 NOT NULL DEFAULT 'none',
  `faxnumber` varchar(20) CHARACTER SET latin1 NOT NULL DEFAULT 'none',
  `email` varchar(50) CHARACTER SET latin1 NOT NULL DEFAULT 'none',
  `officalwebsite` varchar(100) CHARACTER SET latin1 NOT NULL DEFAULT 'none',
  `logopic` varchar(100) CHARACTER SET latin1 NOT NULL DEFAULT 'none',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enterprisetable`
--

LOCK TABLES `enterprisetable` WRITE;
/*!40000 ALTER TABLE `enterprisetable` DISABLE KEYS */;
INSERT INTO `enterprisetable` VALUES (1,'Hewlett-Packard Co','1939-00-00','3000 Hanover Street Palo Alto, CA 94304-1185 USA','650-8571501','650-8575518 ','none','http://www.hp.com','/logopic/1.jpg'),(2,'Toshiba Corp','1875-00-00','Room 501, Block W2 Dongfang Plaza,1 East Chang\'an Street, Dongcheng District, Beijing','010-85183111','010-85182258','none','http://www.toshiba.com.cn','/logopic/2.jpg'),(3,'International Business Machines Corporation','1911-00-00','Pangudaguan Plaza Building,27 Middle Beisihuan Road, Chaoyang District, Beijing ','800-810-1818 ','none','none','http://www.ibm.com/cn','/logopic/3.jpg'),(4,'CHINA GREATWALL COMPUTER SHENZHEN CO., LTD.','1997-06-20','Great Wall Computer Building,Science & Industry Park,Nanshan District, Shenzhen','+86-755-26639997','+86-755-26631695','mobingyu@greatwall.com.cn','http://www.greatwall.cn/ ','/logopic/4.jpg'),(5,'Legend Computer Group','1984-00-00','1009 Think Place Morrisville, NC 27560 USA','+1 866-458-4465','none','none','http://www.lenovo.com.cn/','/logopic/5.jpg'),(6,'Panasonic Corporation','1918-00-00','6F Block C YuanyangGuanghua International Building,5 South Jinghua Street, Chaoyang District, Beijing','010-65626688 ','none','none','http://panasonic.cn/','/logopic/6.jpg'),(7,'Cisco Systems, Inc','2000-00-00','No. 2 Jianguomenwai St, Chaoyang District, Beijing (Levels 7 – 12, Yintai Building)','+86-10-85155000','+86-10-85155960','cin-china@cisco.com','http://www.cisco.com/web/CN/index.html','/logopic/7.jpg'),(8,'Tsinghua Tongfang Co Ltd','1997-06-00','Tower A, Tsinghua Tongfang High-tech Plaza, Haidian District, Beijing ,China','+86-10-82399988','+86-10-82399765','600100@thtf.com.cn','http://www.thtf.com.cn/','/logopic/8.jpg'),(9,'AI Software Co Ltd','1998-00-00','1304F Kangfulai Building,313 middle Yanjiang road, Guangzhou','+86-20-38791406','+86-20-38791407','gzaisoft@aisoft.com.cn','http://www.aisoft.com.cn/','/logopic/9.jpgg'),(10,'Peking University Founder Group Corp','1986-00-00','6F Founder Building, No.298 Chengfu Road, Haidian District, Beijing,China','+86-010-82532288','+86-010-82529456','none','http://www.founder.com/','/logopic/10.jpg'),(11,'Acer Incorporated','1976-08-01','3F Dushizongbu Building, 168 Middle Xizang Road, Huangpu District, Shanghai','021-51178999','none','none','http://www.acer.com.cn/ac/zh/CN/content/home','/logopic/11.jpg'),(12,'Intel Corporation','1968-00-00','2200 Mission College Blvd.Santa Clara, CA 95054-1549 USA',' (408) 765-8080','none','none','http://www.intel.com/cn/index.htm','/logopic/12.jpg'),(13,'Seiko Epson','1942-00-00','7F Jinbao Building,89 Jinbao Street,DongCheng District,Beijing,China','+86-10-8522-1199 ','none','none','http://www.epson.com.cn/','/logopic/13.jpg'),(14,'Samsung Electronics','1938-00-00','23F Merchants Building, 2 East Huannan Road, Chaoyang District, Beijing','010-65668100','010-65668131','none','http://www.samsung.com.cn','/logopic/14.jpg'),(15,' Hitachi Ltd','1910-00-00','18F Fazhan Building, 5 East Sanhuanbei Road, Chaoyang District, Beijing','010-65908111','010-65908110','none','http://www.hitachi.com.cn','/logopic/15.jpg'),(16,'SIEMENS AG FWB:SIE, NYSE:SI','1847-00-00','7 South Wangjingzhonghuan Road, Chaoyang District, Beijing','+86-10-64768888 ','+86-10-64764901 ','contact.slc@siemens.com','http://www.siemens.com.cn','/logopic/16.jpg'),(17,'Sony Corporation','1946-05-00','11F Block B Fenglian Plaza Building, 18 Chaowai Avenue, Beijing','010-65880833 ','010-65880988 ','ccc@sony.com.cn ','http://www.sony.com.cn','/logopic/17.jpg'),(18,'Apple, Inc.','1976-04-01','1 Jianguomenwai Avenue, Chaoyang District, Beijing','800-8102399','none','','http://www.apple.com.cn/',''),(19,'MICROSOFT CORPORATION','1975-00-00','12F Microsoft Building,Lixingxing Plaza, 8 Wangjing Street, Chaoyang District, Beijing','010-59178888','010-59258588','none','http://www.microsoft.com/zh/cn/','/logopic/19.jpg'),(20,'Dell Inc.','1984-00-00','3F South Jiali Center Building, 1 Guanghua Road, Chaoyang District, Beijing','800-858-2969 ','800-858-0478 ','none','http://www.dell.com.cn','/logopic/20.jpg'),(21,'ASUSTeK Computer Inc.','1990-00-00','15 Lide Road, Beitou District, Taibei, Taiwan',' +886-2-2894-3447','+886-2-2895-9254','none','http://www.asus.com.cn','/logopic/21.jpg'),(22,'NEC  Co., Ltd.','1996-11-00','18F Shining Building, 35 Xueyuan Road, Haidian District, Beijing','010-59181111','none','none','http://www.nec.com.cn','/logopic/22.jpg'),(23,'LG Electronics','1947-01-05','21F West Tower Shuangzizuo Building, Yi12 Jianguomenwai Avenue, Chaoyang District, Beijing','400-819-9999','none ','none','http://www.lg.com.cn/','/logopic/23.jpg'),(24,'Huawei Technologies Co., Ltd. ','1987-00-00','Bantian Huawei Base, Longgang District, Shenzhen','0086-755-28780808 ','none','none','http://www.huawei.com','/logopic/24.jpg'),(25,'SVA Technologies, Co., Ltd.','1995-00-00','5F 192 Tianlin Road, Xuhui District, Shanghai','021-34280766','021-34280766-8066 ','contact@email.sva.com.cn','http://www.sva.com.cn/','/logopic/25.jpg');
/*!40000 ALTER TABLE `enterprisetable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test`
--

DROP TABLE IF EXISTS `test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` text,
  `des` text,
  `time` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test`
--

LOCK TABLES `test` WRITE;
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
/*!40000 ALTER TABLE `test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(16) DEFAULT NULL,
  `privilege` int(11) NOT NULL,
  `password` text,
  `status` text NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'guest',1,'123456','active'),(2,'guest2',1,'222','active'),(3,'hello',1,'2','active'),(4,'admin',2,'123456','active'),(5,'gui',1,'gui','inactive');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userinfo`
--

DROP TABLE IF EXISTS `userinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userinfo` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `enterpriseid` int(11) NOT NULL,
  `status` text NOT NULL,
  PRIMARY KEY (`userid`),
  UNIQUE KEY `enterpriseid_UNIQUE` (`enterpriseid`),
  KEY `enterpriseid` (`enterpriseid`),
  CONSTRAINT `` FOREIGN KEY (`enterpriseid`) REFERENCES `enterprisetable` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userinfo`
--

LOCK TABLES `userinfo` WRITE;
/*!40000 ALTER TABLE `userinfo` DISABLE KEYS */;
INSERT INTO `userinfo` VALUES (1,'yestall','987987',18,'active');
/*!40000 ALTER TABLE `userinfo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-09-22  9:44:43
