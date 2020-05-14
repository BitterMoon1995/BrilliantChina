/*
 Navicat Premium Data Transfer

 Source Server         : mercury
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : zmchina

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 14/05/2020 18:22:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` int(0) NOT NULL,
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `path` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sid` int(0) NULL DEFAULT NULL,
  `ordernum` int(0) NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '项目管理', '', -1, 1, NULL);
INSERT INTO `menu` VALUES (2, '权限管理', '', -1, 2, NULL);
INSERT INTO `menu` VALUES (3, '客户管理', '', -1, 3, NULL);
INSERT INTO `menu` VALUES (11, '景区列表', '/scene', 1, 1, NULL);
INSERT INTO `menu` VALUES (12, '路线列表', '/route', 1, 2, NULL);
INSERT INTO `menu` VALUES (13, '活动列表', '/activity', 1, 3, NULL);
INSERT INTO `menu` VALUES (21, '用户管理', '/authorization', 2, 1, NULL);
INSERT INTO `menu` VALUES (31, '客户管理', '/clientManage', 3, 1, NULL);

-- ----------------------------
-- Table structure for mini_activity
-- ----------------------------
DROP TABLE IF EXISTS `mini_activity`;
CREATE TABLE `mini_activity`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `introduce` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mini_activity
-- ----------------------------

-- ----------------------------
-- Table structure for mini_activity_image
-- ----------------------------
DROP TABLE IF EXISTS `mini_activity_image`;
CREATE TABLE `mini_activity_image`  (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `image_src` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `navigator_url` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `activity_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `weight` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mini_activity_image
-- ----------------------------

-- ----------------------------
-- Table structure for mini_route
-- ----------------------------
DROP TABLE IF EXISTS `mini_route`;
CREATE TABLE `mini_route`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `introduce` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `price` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mini_route
-- ----------------------------
INSERT INTO `mini_route` VALUES ('cadc', '把', '擦', NULL);
INSERT INTO `mini_route` VALUES ('joi', '爱', '透', NULL);
INSERT INTO `mini_route` VALUES ('vae', '情', '爱', NULL);

-- ----------------------------
-- Table structure for mini_route_image
-- ----------------------------
DROP TABLE IF EXISTS `mini_route_image`;
CREATE TABLE `mini_route_image`  (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `image_src` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `navigator_url` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `route_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `weight` int(0) NULL DEFAULT NULL,
  `type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mini_route_image
-- ----------------------------

-- ----------------------------
-- Table structure for mini_scene
-- ----------------------------
DROP TABLE IF EXISTS `mini_scene`;
CREATE TABLE `mini_scene`  (
  `id` varchar(50) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `slogan` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '精简介绍',
  `username` varchar(100) CHARACTER SET gbk COLLATE gbk_bin NULL DEFAULT NULL,
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '景区地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mini_scene
-- ----------------------------
INSERT INTO `mini_scene` VALUES ('a', '则', '牛逼！！！！！', 'admin', '老北京儿儿儿儿儿');
INSERT INTO `mini_scene` VALUES ('a1', '哥', '牛逼！！！！！', 'admin', '老北京儿儿儿儿儿');
INSERT INTO `mini_scene` VALUES ('a2', '大', '牛逼！！！！！', 'admin', '老北京儿儿儿儿儿');
INSERT INTO `mini_scene` VALUES ('a3', '啊', '牛逼！！！！！', 'admin', '老北京儿儿儿儿儿');

-- ----------------------------
-- Table structure for mini_scene_image
-- ----------------------------
DROP TABLE IF EXISTS `mini_scene_image`;
CREATE TABLE `mini_scene_image`  (
  `id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `src` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `url` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `scene_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `order_num` int(0) NULL DEFAULT NULL,
  `type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `top` tinyint(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mini_scene_image
-- ----------------------------

-- ----------------------------
-- Table structure for mini_swiper
-- ----------------------------
DROP TABLE IF EXISTS `mini_swiper`;
CREATE TABLE `mini_swiper`  (
  `id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `src` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `target_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `url` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `ordernum` int(0) NULL DEFAULT NULL,
  `top` tinyint(0) NULL DEFAULT NULL,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mini_swiper
-- ----------------------------

-- ----------------------------
-- Table structure for mini_ticket
-- ----------------------------
DROP TABLE IF EXISTS `mini_ticket`;
CREATE TABLE `mini_ticket`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `price` int(0) NULL DEFAULT NULL,
  `route_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mini_ticket
-- ----------------------------

-- ----------------------------
-- Table structure for task_execution
-- ----------------------------
DROP TABLE IF EXISTS `task_execution`;
CREATE TABLE `task_execution`  (
  `TASK_EXECUTION_ID` bigint(0) NOT NULL,
  `START_TIME` datetime(0) NULL DEFAULT NULL,
  `END_TIME` datetime(0) NULL DEFAULT NULL,
  `TASK_NAME` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `EXIT_CODE` int(0) NULL DEFAULT NULL,
  `EXIT_MESSAGE` varchar(2500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `ERROR_MESSAGE` varchar(2500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `LAST_UPDATED` timestamp(0) NULL DEFAULT NULL,
  `EXTERNAL_EXECUTION_ID` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `PARENT_EXECUTION_ID` bigint(0) NULL DEFAULT NULL,
  PRIMARY KEY (`TASK_EXECUTION_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of task_execution
-- ----------------------------

-- ----------------------------
-- Table structure for task_execution_params
-- ----------------------------
DROP TABLE IF EXISTS `task_execution_params`;
CREATE TABLE `task_execution_params`  (
  `TASK_EXECUTION_ID` bigint(0) NOT NULL,
  `TASK_PARAM` varchar(2500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  INDEX `TASK_EXEC_PARAMS_FK`(`TASK_EXECUTION_ID`) USING BTREE,
  CONSTRAINT `TASK_EXEC_PARAMS_FK` FOREIGN KEY (`TASK_EXECUTION_ID`) REFERENCES `task_execution` (`TASK_EXECUTION_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of task_execution_params
-- ----------------------------

-- ----------------------------
-- Table structure for task_lock
-- ----------------------------
DROP TABLE IF EXISTS `task_lock`;
CREATE TABLE `task_lock`  (
  `LOCK_KEY` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `REGION` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `CLIENT_ID` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `CREATED_DATE` datetime(6) NOT NULL,
  PRIMARY KEY (`LOCK_KEY`, `REGION`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of task_lock
-- ----------------------------

-- ----------------------------
-- Table structure for task_seq
-- ----------------------------
DROP TABLE IF EXISTS `task_seq`;
CREATE TABLE `task_seq`  (
  `ID` bigint(0) NOT NULL,
  `UNIQUE_KEY` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  UNIQUE INDEX `UNIQUE_KEY_UN`(`UNIQUE_KEY`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of task_seq
-- ----------------------------
INSERT INTO `task_seq` VALUES (0, '0');

-- ----------------------------
-- Table structure for task_task_batch
-- ----------------------------
DROP TABLE IF EXISTS `task_task_batch`;
CREATE TABLE `task_task_batch`  (
  `TASK_EXECUTION_ID` bigint(0) NOT NULL,
  `JOB_EXECUTION_ID` bigint(0) NOT NULL,
  INDEX `TASK_EXEC_BATCH_FK`(`TASK_EXECUTION_ID`) USING BTREE,
  CONSTRAINT `TASK_EXEC_BATCH_FK` FOREIGN KEY (`TASK_EXECUTION_ID`) REFERENCES `task_execution` (`TASK_EXECUTION_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of task_task_batch
-- ----------------------------

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `age` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `username` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `role` int(0) NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `activated` tinyint(0) NULL DEFAULT 1,
  `company` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('a', 'admin', '123456', 1, '18598462152', 1, '卓行科技', 'cdacasd@qq.com');
INSERT INTO `user` VALUES ('6ad106db64d3bcb88d611b5ce0416fe8', 'admin2', '111111', 1, '', 1, '', NULL);
INSERT INTO `user` VALUES ('b', 'manager1', '123', 2, NULL, 1, '卓行科技', NULL);
INSERT INTO `user` VALUES ('c', 'manager2', '123', 2, NULL, 1, '卓行科技', NULL);
INSERT INTO `user` VALUES ('as', 'bbbbb', '123456', 3, NULL, 1, NULL, NULL);
INSERT INTO `user` VALUES ('d', 'client1', '123', 3, NULL, 1, NULL, NULL);
INSERT INTO `user` VALUES ('143aa88c11ab7f49b6e073cde4a418bb', 'client9', '123456', 3, '', 1, '', NULL);

-- ----------------------------
-- View structure for nigger
-- ----------------------------
DROP VIEW IF EXISTS `nigger`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `nigger` AS select `mini_scene`.`id` AS `id`,`mini_scene`.`name` AS `name`,`mini_scene`.`slogan` AS `slogan`,`mini_scene`.`username` AS `username`,`mini_scene`.`location` AS `location` from `mini_scene` order by (convert(`mini_scene`.`name` using gbk) collate gbk_chinese_ci);

SET FOREIGN_KEY_CHECKS = 1;
