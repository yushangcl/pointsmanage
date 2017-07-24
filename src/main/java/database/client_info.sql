/*
Navicat MySQL Data Transfer

Source Server         : Yun
Source Server Version : 50148
Source Host           : qdm167355438.my3w.com:3306
Source Database       : qdm167355438_db

Target Server Type    : MYSQL
Target Server Version : 50148
File Encoding         : 65001

Date: 2017-07-24 22:26:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for client_info
-- ----------------------------
DROP TABLE IF EXISTS `client_info`;
CREATE TABLE `client_info` (
  `client_id` int(255) NOT NULL AUTO_INCREMENT COMMENT '客户编号',
  `client_name` varchar(255) NOT NULL DEFAULT '' COMMENT '客户姓名',
  `client_mobile` varchar(255) NOT NULL DEFAULT '' COMMENT '客户电话',
  `purchased_points` int(11) NOT NULL COMMENT '已购积分',
  `converted_points` int(11) NOT NULL COMMENT '已换积分',
  `remaining_points` int(11) NOT NULL COMMENT '剩余积分',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`client_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=gbk COMMENT='客户信息表';
