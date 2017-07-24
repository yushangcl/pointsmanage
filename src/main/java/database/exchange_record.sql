/*
Navicat MySQL Data Transfer

Source Server         : Yun
Source Server Version : 50148
Source Host           : qdm167355438.my3w.com:3306
Source Database       : qdm167355438_db

Target Server Type    : MYSQL
Target Server Version : 50148
File Encoding         : 65001

Date: 2017-07-24 22:27:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for exchange_record
-- ----------------------------
DROP TABLE IF EXISTS `exchange_record`;
CREATE TABLE `exchange_record` (
  `exchange_records` int(11) NOT NULL AUTO_INCREMENT COMMENT '兑换记录号',
  `client_mobile` varchar(255) NOT NULL,
  `exchange_date` datetime DEFAULT NULL,
  `exchange_points` varchar(255) DEFAULT NULL COMMENT '兑换积分',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`exchange_records`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk COMMENT='剩余积分';
