/*
Navicat MySQL Data Transfer

Source Server         : 218
Source Server Version : 50173
Source Host           : 192.168.100.218:3306
Source Database       : ezcloud_portal

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2014-09-11 15:09:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `product_cat`
-- ----------------------------
DROP TABLE IF EXISTS `product_cat`;
CREATE TABLE `product_cat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bill_unit_name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `bill_type` int(11) DEFAULT NULL,
  `innerName` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product_cat
-- ----------------------------
INSERT INTO `product_cat` VALUES ('1', '核', 'cpu', 'cpu', '1', 'cpu');
INSERT INTO `product_cat` VALUES ('3', 'GB', 'mem', 'mem', '1', 'mem');
INSERT INTO `product_cat` VALUES ('5', 'GB', 'disk', 'disk', '1', 'disk');
INSERT INTO `product_cat` VALUES ('7', 'Mbps', 'bandwidth', 'bandwidth', '0', 'bandwidth');
INSERT INTO `product_cat` VALUES ('9', 'GB', 'snapshot', 'snapshot', '1', 'snapshot');
