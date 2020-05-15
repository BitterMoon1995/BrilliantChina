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

 Date: 15/05/2020 17:13:24
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
INSERT INTO `mini_scene` VALUES ('11905ff9733a4ae554df9c711d9d9e8b', '西湖', '老北京', 'admin', '北京');
INSERT INTO `mini_scene` VALUES ('75cda79b9ca048d9e1685eac824b17a2', '小人国成都', '小人国成都', 'admin', '小人国成都');
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
INSERT INTO `mini_scene_image` VALUES ('223c553a4ad2287240678ae5cb9f3893', 'f.jpg', 'http://192.168.156.128//group1/M00/00/02/wKicgF6-LEKADI7OAAd6u4dG9C8272.jpg', NULL, '11905ff9733a4ae554df9c711d9d9e8b', NULL, 'postcard', 0);
INSERT INTO `mini_scene_image` VALUES ('269a779cdbc233094fc9c10662efc450', 'f.jpg', 'http://192.168.156.128//group1/M00/00/02/wKicgF6-LD-AKcFNAAd6u4dG9C8083.jpg', NULL, '11905ff9733a4ae554df9c711d9d9e8b', 0, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('2a8e341d631b837d38b0548440445faa', '微信图片_2020051510462710.jpg', 'http://192.168.156.128//group1/M00/00/02/wKicgF6-IB2AQWMKAAEijgvxXmU689.jpg', NULL, '120a118606f24ffcc22acd67bce7aa81', 1, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('3c3c5de22ab7009a0d1d486d3561fe88', 'saobi.jpg', 'http://192.168.156.128//group1/M00/00/02/wKicgF6-T1KATdFRAAFgkcPQfk4256.jpg', NULL, '96132df092b29d6205fc508b841fe6fe', 0, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('5f841e87b2ef4b4b839a52f907ec6a54', 'choubi.jpg', 'http://192.168.156.128//group1/M00/00/02/wKicgF6-Uu-AQZ26AAGReDCmTKM435.jpg', NULL, '75cda79b9ca048d9e1685eac824b17a2', NULL, 'postcard', 0);
INSERT INTO `mini_scene_image` VALUES ('70a648af44fa3e854b624617820ce277', 'saobi.jpg', 'http://192.168.156.128//group1/M00/00/02/wKicgF6-T2GADtB3AAFgkcPQfk4889.jpg', NULL, '96132df092b29d6205fc508b841fe6fe', NULL, 'richText', 0);
INSERT INTO `mini_scene_image` VALUES ('8148bb0ba6f5b2daf47fa99bdee3d9e5', 'choubi.jpg', 'http://192.168.156.128//group1/M00/00/02/wKicgF6-UveAMXedAAGReDCmTKM107.jpg', NULL, '75cda79b9ca048d9e1685eac824b17a2', NULL, 'richText', 0);
INSERT INTO `mini_scene_image` VALUES ('9abb2ce95c62a0c7c6b7a74efbea9d8b', 'saobi.jpg', 'http://192.168.156.128//group1/M00/00/02/wKicgF6-T1KATdFRAAFgkcPQfk4256.jpg', NULL, 'ce7658eb8a22f9bfd78b8c0573414d24', 0, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('ae55ce91c07ee7fcfc81f1dc63444dc0', 'saobi.jpg', 'http://192.168.156.128//group1/M00/00/02/wKicgF6-T1aAbzoZAAFgkcPQfk4521.jpg', NULL, 'ce7658eb8a22f9bfd78b8c0573414d24', NULL, 'postcard', 0);
INSERT INTO `mini_scene_image` VALUES ('c4b87cef3ed1bd039631c9fcf2c1ed1d', 'saobi.jpg', 'http://192.168.156.128//group1/M00/00/02/wKicgF6-T1aAbzoZAAFgkcPQfk4521.jpg', NULL, '96132df092b29d6205fc508b841fe6fe', NULL, 'postcard', 0);
INSERT INTO `mini_scene_image` VALUES ('d9393d63cdeb728849278e2152461cec', 'saobi.jpg', 'http://192.168.156.128//group1/M00/00/02/wKicgF6-T2GADtB3AAFgkcPQfk4889.jpg', NULL, 'ce7658eb8a22f9bfd78b8c0573414d24', NULL, 'richText', 0);
INSERT INTO `mini_scene_image` VALUES ('e42dbb13f3f3579db8a7d7cf92e3b2c7', '微信图片_202005151046279.jpg', 'http://192.168.156.128//group1/M00/00/02/wKicgF6-IBmAcXtBAADrzBKiP_E532.jpg', NULL, '120a118606f24ffcc22acd67bce7aa81', 0, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('f7f895ff596ce4b809ed7df02373e211', 'choubi.jpg', 'http://192.168.156.128//group1/M00/00/02/wKicgF6-UuuAAbBiAAGReDCmTKM667.jpg', NULL, '75cda79b9ca048d9e1685eac824b17a2', 0, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('fcc73858f5548dbca1a4bed3c0a71803', '微信图片_202005151046271.jpg', 'http://192.168.156.128//group1/M00/00/02/wKicgF6-ICGAIhgxAAIlVFNtuJ0294.jpg', NULL, '120a118606f24ffcc22acd67bce7aa81', NULL, 'postcard', 0);

-- ----------------------------
-- Table structure for mini_swiper
-- ----------------------------
DROP TABLE IF EXISTS `mini_swiper`;
CREATE TABLE `mini_swiper`  (
  `id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `src` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `target_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `url` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `order_num` int(0) NULL DEFAULT NULL,
  `top` tinyint(0) NULL DEFAULT NULL,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mini_swiper
-- ----------------------------
INSERT INTO `mini_swiper` VALUES ('172dfa65f7bbe957ebfe2348f3cb6839', 'http://192.168.156.128//group1/M00/00/02/wKicgF6-T1qADQc2AAFgkcPQfk4220.jpg', '96132df092b29d6205fc508b841fe6fe', NULL, NULL, 0, 'saobi.jpg');
INSERT INTO `mini_swiper` VALUES ('b2390d260811b4d57209a8d4e6ffd71d', 'http://192.168.156.128//group1/M00/00/02/wKicgF6-UvOAZg66AAGReDCmTKM277.jpg', '75cda79b9ca048d9e1685eac824b17a2', NULL, NULL, 0, 'choubi.jpg');
INSERT INTO `mini_swiper` VALUES ('cfad945e2abf09b05cd55f6397d28def', 'http://192.168.156.128//group1/M00/00/02/wKicgF6-ICWAD3wQAAMfv50gLVE815.jpg', '120a118606f24ffcc22acd67bce7aa81', NULL, NULL, 0, '微信图片_202005151046273.jpg');
INSERT INTO `mini_swiper` VALUES ('d6fc932c0cfc3e7622e0aedde541c757', 'http://192.168.156.128//group1/M00/00/02/wKicgF6-LEaACxRaAAd6u4dG9C8152.jpg', '11905ff9733a4ae554df9c711d9d9e8b', NULL, NULL, 0, 'f.jpg');
INSERT INTO `mini_swiper` VALUES ('ece6a67dc27e2d8542526374322e02c5', 'http://192.168.156.128//group1/M00/00/02/wKicgF6-T1qADQc2AAFgkcPQfk4220.jpg', 'ce7658eb8a22f9bfd78b8c0573414d24', NULL, NULL, 0, 'saobi.jpg');

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
INSERT INTO `user` VALUES ('34ad9662cfcaee3e4c15d9e0f5e7dbd1', '123', '123456', 3, '是是', 1, '是s', '是');
INSERT INTO `user` VALUES ('as', 'bbbbb', '123456', 3, NULL, 1, NULL, NULL);
INSERT INTO `user` VALUES ('d', 'client1', '123', 3, NULL, 1, NULL, NULL);
INSERT INTO `user` VALUES ('143aa88c11ab7f49b6e073cde4a418bb', 'client9', '123456', 3, '', 1, '', NULL);

-- ----------------------------
-- View structure for nigger
-- ----------------------------
DROP VIEW IF EXISTS `nigger`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `nigger` AS select `mini_scene`.`id` AS `id`,`mini_scene`.`name` AS `name`,`mini_scene`.`slogan` AS `slogan`,`mini_scene`.`username` AS `username`,`mini_scene`.`location` AS `location` from `mini_scene` order by (convert(`mini_scene`.`name` using gbk) collate gbk_chinese_ci);

SET FOREIGN_KEY_CHECKS = 1;
