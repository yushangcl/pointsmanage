/*
Navicat MySQL Data Transfer

Source Server         : Yun
Source Server Version : 50148
Source Host           : qdm167355438.my3w.com:3306
Source Database       : qdm167355438_db

Target Server Type    : MYSQL
Target Server Version : 50148
File Encoding         : 65001

Date: 2017-07-24 22:27:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for expenses_record
-- ----------------------------
DROP TABLE IF EXISTS `expenses_record`;
CREATE TABLE `expenses_record` (
  `record_number` int(11) NOT NULL AUTO_INCREMENT,
  `client_mobile` varchar(255) NOT NULL COMMENT '客户电话',
  `consumption_date` datetime DEFAULT NULL COMMENT '消费日期',
  `amount` double(255,0) DEFAULT NULL COMMENT '消费金额',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`record_number`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk COMMENT='消费记录表';
