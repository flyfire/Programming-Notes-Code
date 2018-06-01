/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50521
Source Host           : localhost:3306
Source Database       : mybatis

Target Server Type    : MYSQL
Target Server Version : 50521
File Encoding         : 65001

Date: 2015-04-09 16:03:53
*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for `orders`
-- ----------------------------
DROP TABLE IF EXISTS `MyBatis_orders`;
CREATE TABLE `MyBatis_orders` (
  `id`         INT(11)     NOT NULL AUTO_INCREMENT,
  `user_id`    INT(11)     NOT NULL
  COMMENT '下单用户id',
  `number`     VARCHAR(32) NOT NULL
  COMMENT '订单号',
  `createtime` DATETIME    NOT NULL
  COMMENT '创建订单时间',
  `note`       VARCHAR(100)         DEFAULT NULL
  COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `FK_orders_1` (`user_id`),
  CONSTRAINT `FK_orders_id` FOREIGN KEY (`user_id`) REFERENCES `MyBatis_user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 6
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `MyBatis_orders` VALUES ('3', '1', '1000010', '2015-02-04 13:22:35', NULL);
INSERT INTO `MyBatis_orders` VALUES ('4', '1', '1000011', '2015-02-03 13:22:41', NULL);
INSERT INTO `MyBatis_orders` VALUES ('5', '10', '1000012', '2015-02-12 16:13:23', NULL);

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `MyBatis_user`;
CREATE TABLE `MyBatis_user` (
  `id`       INT(11)     NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(32) NOT NULL
  COMMENT '用户名称',
  `birthday` DATE                 DEFAULT NULL
  COMMENT '生日',
  `sex`      CHAR(1)              DEFAULT NULL
  COMMENT '性别',
  `address`  VARCHAR(256)         DEFAULT NULL
  COMMENT '地址',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 27
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `MyBatis_user` VALUES ('1', '王五', NULL, '2', NULL);
INSERT INTO `MyBatis_user` VALUES ('10', '张三', '2014-07-10', '1', '北京市');
INSERT INTO `MyBatis_user` VALUES ('16', '张小明', NULL, '1', '河南郑州');
INSERT INTO `MyBatis_user` VALUES ('22', '陈小明', NULL, '1', '河南郑州');
INSERT INTO `MyBatis_user` VALUES ('24', '张三丰', NULL, '1', '河南郑州');
INSERT INTO `MyBatis_user` VALUES ('25', '陈小明', NULL, '1', '河南郑州');
INSERT INTO `MyBatis_user` VALUES ('26', '王五', NULL, NULL, NULL);
