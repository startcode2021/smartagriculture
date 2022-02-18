/*
 Navicat MySQL Data Transfer

 Source Server         : 11
 Source Server Type    : MySQL
 Source Server Version : 50736
 Source Host           : 121.5.35.48:3306
 Source Schema         : SmartAgriculture

 Target Server Type    : MySQL
 Target Server Version : 50736
 File Encoding         : 65001

 Date: 18/02/2022 15:13:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for Place
-- ----------------------------
DROP TABLE IF EXISTS `Place`;
CREATE TABLE `Place`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '大棚id',
  `place_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '大棚名字',
  `gps_name` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `lon` double NOT NULL DEFAULT 0 COMMENT '经度',
  `lat` double NOT NULL DEFAULT 0 COMMENT '纬度',
  `air_temp` double NOT NULL DEFAULT 0 COMMENT '空气温度',
  `air_hump` double NOT NULL DEFAULT 0 COMMENT '空气湿度',
  `ground_temp` double NOT NULL DEFAULT 0 COMMENT '土壤温度',
  `ground_hump` double NOT NULL DEFAULT 0,
  `lux` double NOT NULL DEFAULT 0 COMMENT '光照',
  `lighting` int(11) NOT NULL DEFAULT 0 COMMENT '光照开关',
  `fan` int(11) NOT NULL DEFAULT 0 COMMENT '风扇开关',
  `water_valve` int(11) NOT NULL DEFAULT 0 COMMENT '水闸开关',
  `co2` double NOT NULL DEFAULT 0 COMMENT '二氧化碳浓度',
  `nh3` double NOT NULL DEFAULT 0 COMMENT '氨气浓度',
  `h2s` double NOT NULL DEFAULT 0 COMMENT '硫化氢浓度',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of Place
-- ----------------------------
INSERT INTO `Place` VALUES (1, '大棚一号', 'gps_1', 25, 55, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0);

-- ----------------------------
-- Table structure for ill
-- ----------------------------
DROP TABLE IF EXISTS `ill`;
CREATE TABLE `ill`  (
  `id` int(11) NOT NULL COMMENT '虫害id',
  `ill_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '虫害名称',
  `robot_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '传感器名',
  `region` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '安装大棚',
  `robot_num` int(11) NOT NULL DEFAULT 0 COMMENT '设备数量',
  `operation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作说明',
  `add_date` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ill
-- ----------------------------
INSERT INTO `ill` VALUES (0, 'aaa', 'aaa', '大棚一号', 2, 'aa', '2022-02-11T19:35:34.207+08:00');

-- ----------------------------
-- Table structure for journal
-- ----------------------------
DROP TABLE IF EXISTS `journal`;
CREATE TABLE `journal`  (
  `id` int(11) NOT NULL COMMENT '日记id',
  `title` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `content` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  `add_time` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of journal
-- ----------------------------
INSERT INTO `journal` VALUES (0, '挨打的', 'PHA+6JCo6L6+PC9wPg==', '2022-02-11T19:52:41.371+08:00');

-- ----------------------------
-- Table structure for robot_record
-- ----------------------------
DROP TABLE IF EXISTS `robot_record`;
CREATE TABLE `robot_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '传感器提交数据id',
  `robot_id` int(11) NOT NULL COMMENT '传感器id',
  `robot_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '传感器名',
  `region` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '安装大棚名',
  `air_temp` double NOT NULL DEFAULT 0 COMMENT '空气温度',
  `air_hump` double NOT NULL DEFAULT 0 COMMENT '空气湿度',
  `ground_temp` double NOT NULL DEFAULT 0 COMMENT '土壤温度',
  `ground_hump` double NOT NULL DEFAULT 0 COMMENT '土壤湿度',
  `lux` double NOT NULL DEFAULT 0 COMMENT '光照强度',
  `co2` double NOT NULL DEFAULT 0 COMMENT 'co2浓度',
  `nh3` double NOT NULL DEFAULT 0 COMMENT 'nh3浓度',
  `h2s` double NOT NULL DEFAULT 0 COMMENT 'h2s浓度',
  `add_time` datetime NOT NULL COMMENT '上传时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of robot_record
-- ----------------------------
INSERT INTO `robot_record` VALUES (1, 1, '温度传感器', '大棚一号', 14, 0, 0, 0, 0, 0, 0, 0, '2022-02-11 19:42:18');
INSERT INTO `robot_record` VALUES (2, 1, '温度传感器', '大棚一号', 16, 0, 0, 0, 0, 0, 0, 0, '2022-02-03 19:42:51');
INSERT INTO `robot_record` VALUES (3, 1, '温度传感器', '大棚一号', 5, 0, 0, 0, 0, 0, 0, 0, '2022-02-09 19:43:30');

-- ----------------------------
-- Table structure for robot_type
-- ----------------------------
DROP TABLE IF EXISTS `robot_type`;
CREATE TABLE `robot_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '传感器id',
  `robot_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '传感器名',
  `robot_type` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '传感器类型',
  `robot_address` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '安装大棚',
  `robot_num` int(11) NOT NULL DEFAULT 0 COMMENT '传感器数量',
  `add_date` datetime NOT NULL COMMENT '安装时间',
  `other` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `index_num` int(11) NOT NULL COMMENT '设备序列号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of robot_type
-- ----------------------------
INSERT INTO `robot_type` VALUES (1, 'aaa', 'aaa', '大棚一号', 2, '2022-02-11 11:30:18', 'aaa', 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `roleid` int(11) NULL DEFAULT NULL,
  UNIQUE INDEX `user_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '计算机爱好者协会理事', '$2a$10$OFiaXabTy5q32yCcgVRC7OMqDa6h5XU4./yQBNTBDpKiBdSEO9Fke', '1372383702@qq.com', '13566112897', 2);
INSERT INTO `user` VALUES (2, '计算机爱好者协会理事', '$2a$10$a/7iE8rkpSj85pNRow3VsOLCuHTddtjaKKktuE7pNHFAuoUXElf2O', '1372383702@qq.com', '15869468091', 2);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gmtCreate` date NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_role_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (2, '用户', '普通用户', '2022-02-11');

SET FOREIGN_KEY_CHECKS = 1;
