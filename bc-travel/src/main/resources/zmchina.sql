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

 Date: 17/07/2020 18:32:49
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
  `role` int(0) NULL DEFAULT NULL COMMENT '角色',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '项目管理', '', -1, 1, 3);
INSERT INTO `menu` VALUES (2, '权限管理', '', -1, 2, 1);
INSERT INTO `menu` VALUES (3, '客户管理', '', -1, 3, 2);
INSERT INTO `menu` VALUES (4, '置顶管理', NULL, -1, 4, 2);
INSERT INTO `menu` VALUES (5, '页面管理', NULL, -1, 5, 2);
INSERT INTO `menu` VALUES (11, '景区列表', '/scene', 1, 1, NULL);
INSERT INTO `menu` VALUES (12, '线路列表', '/route', 1, 2, NULL);
INSERT INTO `menu` VALUES (13, '活动列表', '/activity', 1, 3, NULL);
INSERT INTO `menu` VALUES (21, '用户管理', '/authorization', 2, 1, NULL);
INSERT INTO `menu` VALUES (31, '客户管理', '/client', 3, 1, NULL);
INSERT INTO `menu` VALUES (32, '提现管理', '/withdraw', 3, 2, NULL);
INSERT INTO `menu` VALUES (41, '景区', '/stickyScene', 4, 1, NULL);
INSERT INTO `menu` VALUES (42, '线路', '/stickyRoute', 4, 2, NULL);
INSERT INTO `menu` VALUES (43, '活动', '/stickyActivity', 4, 3, NULL);
INSERT INTO `menu` VALUES (44, '热搜', '/topSearch', 4, 4, NULL);
INSERT INTO `menu` VALUES (51, '首页页面', '/homepage', 5, 1, NULL);

-- ----------------------------
-- Table structure for mini_activity
-- ----------------------------
DROP TABLE IF EXISTS `mini_activity`;
CREATE TABLE `mini_activity`  (
  `id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `slogan` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `location` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  UNIQUE INDEX `index_name`(`name`) USING BTREE,
  INDEX `index_create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mini_activity
-- ----------------------------

-- ----------------------------
-- Table structure for mini_activity_image
-- ----------------------------
DROP TABLE IF EXISTS `mini_activity_image`;
CREATE TABLE `mini_activity_image`  (
  `id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `src` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `url` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `activity_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `order_num` int(0) NULL DEFAULT NULL,
  `top` tinyint(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mini_activity_image
-- ----------------------------

-- ----------------------------
-- Table structure for mini_icon
-- ----------------------------
DROP TABLE IF EXISTS `mini_icon`;
CREATE TABLE `mini_icon`  (
  `id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `src` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `url` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `target_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `order_num` int(0) NULL DEFAULT NULL,
  `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mini_icon
-- ----------------------------
INSERT INTO `mini_icon` VALUES ('43ff58b07542b797c0c52c46edf20a3a', '更多.png', 'http://192.168.156.128//group1/M00/00/05/wKicgF7GTjeACm-lAAAc9nEEeT8666.png', '/pages/vip/vipcenter/vipcenter', NULL, 4, 'category');
INSERT INTO `mini_icon` VALUES ('5cba8de9d8023865baf9822b2d6f8b32', '旅游.png', 'http://192.168.156.128//group1/M00/00/05/wKicgF7GTiSAMRuIAAAeAhtCDiw832.png', '/pages/vip/vipcenter/vipcenter', NULL, 1, 'category');
INSERT INTO `mini_icon` VALUES ('5fc591b0d1a8ae8ff25edcb85596924c', '学习经历.png', 'http://192.168.156.128//group1/M00/00/05/wKicgF7GTjOAaUfBAAAd_jtces8618.png', '/pages/vip/vipcenter/vipcenter', NULL, 3, 'category');
INSERT INTO `mini_icon` VALUES ('7678844fd037e3eaaf04eb26f20b85b0', '门票.png', 'http://192.168.156.128//group1/M00/00/05/wKicgF7GTiqABc8eAAAmn2RYNdY638.png', '/pages/vip/vipcenter/vipcenter', NULL, 2, 'category');
INSERT INTO `mini_icon` VALUES ('9398f977d50d764f0c864e27f9ec9722', '会员卡.png', 'http://192.168.156.128//group1/M00/00/05/wKicgF7GTiGAVincAAAQqFG_qDA709.png', '/pages/vip/vipcenter/vipcenter', NULL, 0, 'category');

-- ----------------------------
-- Table structure for mini_route
-- ----------------------------
DROP TABLE IF EXISTS `mini_route`;
CREATE TABLE `mini_route`  (
  `id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `planning` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '旅游线路游玩规划',
  `slogan` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间，以创建时间排序',
  UNIQUE INDEX `index_name`(`name`) USING BTREE,
  INDEX `index_create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mini_route
-- ----------------------------

-- ----------------------------
-- Table structure for mini_route_image
-- ----------------------------
DROP TABLE IF EXISTS `mini_route_image`;
CREATE TABLE `mini_route_image`  (
  `id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `src` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `url` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `route_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `order_num` int(0) NULL DEFAULT NULL,
  `type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `top` tinyint(0) NOT NULL,
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
  `id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `price` int(0) NOT NULL,
  `slogan` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标语最好16字以内，表单符号要用英文符号',
  `username` varchar(100) CHARACTER SET gbk COLLATE gbk_bin NULL DEFAULT NULL COMMENT '项目所属主人',
  `location` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '景区地址',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `level` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '景区星级',
  `search_time` int(0) NULL DEFAULT NULL COMMENT '搜索次数，搜索页跳转到详情页时可以确定',
  `longitude` double NULL DEFAULT NULL COMMENT '经度',
  `latitude` double NULL DEFAULT NULL COMMENT '纬度',
  INDEX `index_create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mini_scene
-- ----------------------------
INSERT INTO `mini_scene` VALUES ('62fee1c0efe40974de662f490257c3fc', '越王楼', 70, '一座越王楼，半部文学史', 'admin', '绵阳市涪城区', '2020-07-14 15:03:34', '5A', NULL, 104.756081, 31.469391);
INSERT INTO `mini_scene` VALUES ('6be2b6dd4e19c481919be3106f4b39dc', '太乙山植物园', 88, '太乙仙山  法力无边', 'admin', '太乙山', '2020-07-13 16:52:26', '4A', NULL, NULL, NULL);
INSERT INTO `mini_scene` VALUES ('a6f9047e511bdfe3222a9583ca0e0341', '原香国际香草园', 25, '绵阳版“普罗旺斯”', 'admin', '涪城区杨家镇', '2020-07-13 10:57:41', '4A', NULL, NULL, NULL);
INSERT INTO `mini_scene` VALUES ('5a21aca0d84cb9724c398581d20e592f', '佛爷洞', 60, '一山一水一溶洞', 'admin', '绵阳市区14公里', '2020-07-03 16:36:31', '4A', NULL, NULL, NULL);
INSERT INTO `mini_scene` VALUES ('54f0bb9f1e65e13a7a08e6db353c4626', '窦圌山', 67, '天下奇山', 'saobi', '绵阳江油', '2020-07-03 16:23:39', '4A', NULL, NULL, NULL);
INSERT INTO `mini_scene` VALUES ('967746d9af6800370f69276e91c36277', '北川羌城旅游区', 115, '生命永恒   魅力无限', 'admin', '老北川', '2020-07-03 16:18:47', '5A', NULL, NULL, NULL);
INSERT INTO `mini_scene` VALUES ('9342013d67c7c252fbf291df96454871', '平武报恩寺', 40, '深山里藏着的一座\"紫禁城\"', 'admin', '平武', '2020-07-03 16:09:16', '4A', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for mini_scene_image
-- ----------------------------
DROP TABLE IF EXISTS `mini_scene_image`;
CREATE TABLE `mini_scene_image`  (
  `id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `src` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片的左下部颜色尽量深一些',
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
INSERT INTO `mini_scene_image` VALUES ('01986c3772a75a8656ecdbfcaac91450', '6.png', 'http://192.168.156.128//group1/M00/00/08/wKicgF7-6d6AIOwIAA75dSP4bv4227.png', NULL, '967746d9af6800370f69276e91c36277', 3, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('0bb4bf818d261c0393445044c04c7f34', '6.png', 'http://192.168.156.128//group1/M00/00/07/wKicgF7-6AmAcgSuABhFVmZZeAo854.png', NULL, '9342013d67c7c252fbf291df96454871', 2, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('123af779f34d3d6ac0846fb48631e05b', '2.png', 'http://192.168.156.128//group1/M00/00/07/wKicgF7-6AmAA3cWABUdEL28mZU883.png', NULL, '9342013d67c7c252fbf291df96454871', 3, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('150879a9e3ace73cfcf4349a12ef0628', '1.png', 'http://192.168.156.128//group1/M00/00/07/wKicgF7-6AmAesaEABZtbdxnnhA225.png', NULL, '9342013d67c7c252fbf291df96454871', 1, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('17d8ffefbca3abf49afc9d028371fc24', '图文详情页.png', 'http://192.168.156.128//group1/M00/00/08/wKicgF7-7guAKDumALo3sD2YGUY405.png', NULL, '5a21aca0d84cb9724c398581d20e592f', NULL, 'richText', 0);
INSERT INTO `mini_scene_image` VALUES ('18f3eeb4cce6b7f5641b2e006cfaa14b', '6.png', 'http://192.168.156.128//group1/M00/00/08/wKicgF8LzZ2AMjIRABaw1F_IRQY387.png', NULL, 'a6f9047e511bdfe3222a9583ca0e0341', 5, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('1ba8206c8e213f256566a226d47feeab', '窦团山名片图.png', 'http://192.168.156.128//group1/M00/00/08/wKicgF7-6kyASeKLAA8qDZQoWfI581.png', NULL, '54f0bb9f1e65e13a7a08e6db353c4626', 7, 'postcard', 1);
INSERT INTO `mini_scene_image` VALUES ('1c8fef54d4dbee1aa72bb4df357c93c6', '5.png', 'http://192.168.156.128//group1/M00/00/08/wKicgF8LzZ2AafsHABFVYCl0pSg593.png', NULL, 'a6f9047e511bdfe3222a9583ca0e0341', 2, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('1f772d0d64d50eac98cd485c4b0c0de5', '2.png', 'http://192.168.156.128//group1/M00/00/08/wKicgF7-6lqAWFi7AAsomz5fj00283.png', NULL, '54f0bb9f1e65e13a7a08e6db353c4626', 0, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('2746ec3f340e7820d4e0b6b4d0dc8842', '4.png', 'http://192.168.156.128//group1/M00/00/08/wKicgF7-6d-Aa_vbABMeFgV6MMc834.png', NULL, '967746d9af6800370f69276e91c36277', 5, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('29554ef86047f502c13e7db8a41763c2', '报恩寺名片图.png', 'http://192.168.156.128//group1/M00/00/07/wKicgF7-5_mAMZxUABBFyll6Ojs623.png', NULL, '9342013d67c7c252fbf291df96454871', 2, 'postcard', 1);
INSERT INTO `mini_scene_image` VALUES ('29d5492577466384c86d02c432ea3553', '6.png', 'http://192.168.156.128//group1/M00/00/08/wKicgF8NWL2Adch6ABy5a3T9gsM707.png', NULL, '62fee1c0efe40974de662f490257c3fc', 3, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('2a79f323e9b3d209adf2b22e24648d64', '5.png', 'http://192.168.156.128//group1/M00/00/08/wKicgF7-7geAPcR-ABm201HCzDE121.png', NULL, '5a21aca0d84cb9724c398581d20e592f', 5, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('2f37c90549f02bedc256e74cceea379a', '图文详情页.png', 'http://192.168.156.128//group1/M00/00/08/wKicgF8LzaGAZi2WALMJq6WSGME787.png', NULL, 'a6f9047e511bdfe3222a9583ca0e0341', NULL, 'richText', 0);
INSERT INTO `mini_scene_image` VALUES ('3439106ede428d72adb8eae8bcbb00c2', '5.png', 'http://192.168.156.128//group1/M00/00/08/wKicgF8NWL2AJIgeABwvbLUt48s235.png', NULL, '62fee1c0efe40974de662f490257c3fc', 4, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('34ea3f990b86a64a4d82cc0787f070e9', '5.png', 'http://192.168.156.128//group1/M00/00/08/wKicgF7-6lqABs7RAAtLpH2SPYo199.png', NULL, '54f0bb9f1e65e13a7a08e6db353c4626', 1, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('3b9133eb32f982b060ba7cd32fe9d638', '6.png', 'http://192.168.156.128//group1/M00/00/08/wKicgF7-6lqAO9jqABaziSLBZlc330.png', NULL, '54f0bb9f1e65e13a7a08e6db353c4626', 5, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('3c863996bf0b8970196d36c6cd99e3ec', '6.png', 'http://192.168.156.128//group1/M00/00/08/wKicgF8MIMGAJDVkABMhqS2sNSU330.png', NULL, '6be2b6dd4e19c481919be3106f4b39dc', 3, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('4c2a44734a44e478fabaf74bd816d9c2', '图文详情页.png', 'http://192.168.156.128//group1/M00/00/08/wKicgF7-6mOAGduXAI_bMO-D8Ic263.png', NULL, '54f0bb9f1e65e13a7a08e6db353c4626', NULL, 'richText', 0);
INSERT INTO `mini_scene_image` VALUES ('509c40da5a3d5f1ea4c2a2a576045f65', '1.png', 'http://192.168.156.128//group1/M00/00/08/wKicgF8LzZ2AFOneAAvWeHoJ5kw157.png', NULL, 'a6f9047e511bdfe3222a9583ca0e0341', 0, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('53edea45771f7135d34ab71189bee543', '4.png', 'http://192.168.156.128//group1/M00/00/08/wKicgF8MIMGAVvpiAAxZOVf9jWE987.png', NULL, '6be2b6dd4e19c481919be3106f4b39dc', 0, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('5e10f9c20bef803f3964cfe0bb0833bc', '1.png', 'http://192.168.156.128//group1/M00/00/08/wKicgF8NWL2ALyv6AAs9-lfUl7o499.png', NULL, '62fee1c0efe40974de662f490257c3fc', 0, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('5e6e314eda564dfc54d2558bf6f025de', '1.png', 'http://192.168.156.128//group1/M00/00/08/wKicgF7-6lqAXkkWAA-pV9ldnms062.png', NULL, '54f0bb9f1e65e13a7a08e6db353c4626', 4, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('64cdd1947881747b5a245ce0f9c2e8c0', '4.png', 'http://192.168.156.128//group1/M00/00/08/wKicgF8LzZ2APOpLAA8fSO4DPjc486.png', NULL, 'a6f9047e511bdfe3222a9583ca0e0341', 4, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('6571793c39241df072e1937b4272a990', '4.png', 'http://192.168.156.128//group1/M00/00/08/wKicgF7-6lqASRJvABMWTgnUJME005.png', NULL, '54f0bb9f1e65e13a7a08e6db353c4626', 3, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('6bfb8f085c6e74ffd2f212d9d6e051a0', '3.png', 'http://192.168.156.128//group1/M00/00/08/wKicgF8MIMGAP7DrABDZPrGwpq8034.png', NULL, '6be2b6dd4e19c481919be3106f4b39dc', 2, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('6d30b667fb69a06201391a90209f5313', '图文详情页.png', 'http://192.168.156.128//group1/M00/00/07/wKicgF7-6BOAAaKGAK8ugR3GlYY942.png', NULL, '9342013d67c7c252fbf291df96454871', NULL, 'richText', 0);
INSERT INTO `mini_scene_image` VALUES ('72d07f70eb0785b3b694bef6fff46bb9', '6.png', 'http://192.168.156.128//group1/M00/00/08/wKicgF7-7geATYN5ABnTHvdK3N4799.png', NULL, '5a21aca0d84cb9724c398581d20e592f', 3, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('72e69a3035dc117259855ba6eab0e1b7', '2.png', 'http://192.168.156.128//group1/M00/00/08/wKicgF7-6d6AAOM-AAxmLBWTrBk715.png', NULL, '967746d9af6800370f69276e91c36277', 1, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('781450ba76695f9f0a645f7ea0074d09', '4.png', 'http://192.168.156.128//group1/M00/00/08/wKicgF7-7geAL0VqABNZuWSVL1s211.png', NULL, '5a21aca0d84cb9724c398581d20e592f', 2, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('7d9d98bf95e6620a0a57c92ab4f86a04', '图文详情页.png', 'http://192.168.156.128//group1/M00/00/08/wKicgF8NWMGAYijoAHkTPnArs3A165.png', NULL, '62fee1c0efe40974de662f490257c3fc', NULL, 'richText', 0);
INSERT INTO `mini_scene_image` VALUES ('81c7485b303bfcce4826444005d8b440', '3.png', 'http://192.168.156.128//group1/M00/00/08/wKicgF8LzZ2AXpPFAA_r4W8eCio942.png', NULL, 'a6f9047e511bdfe3222a9583ca0e0341', 3, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('83363be1ba923867376e0475bbf1ed48', '2.png', 'http://192.168.156.128//group1/M00/00/08/wKicgF8MIMGAVcBkAA4G_fG7AZk935.png', NULL, '6be2b6dd4e19c481919be3106f4b39dc', 1, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('841dad74dbf22d4721a992debff8485d', '1.png', 'http://192.168.156.128//group1/M00/00/08/wKicgF7-7geAFNEjABnYpYLEtX8659.png', NULL, '5a21aca0d84cb9724c398581d20e592f', 1, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('879cd2571f7b9889814064670e38149e', '佛爷洞名片图.png', 'http://192.168.156.128//group1/M00/00/08/wKicgF7-7bqAOaegAAypqBLL3Qw590.png', NULL, '5a21aca0d84cb9724c398581d20e592f', NULL, 'postcard', 0);
INSERT INTO `mini_scene_image` VALUES ('8b9c96680070d945bbdc23f61812c2bc', '北川羌城旅游区名片图.jpg', 'http://192.168.156.128//group1/M00/00/08/wKicgF7-6dGAMg4tAAUuX-Rh2KI532.jpg', NULL, '967746d9af6800370f69276e91c36277', 3, 'postcard', 0);
INSERT INTO `mini_scene_image` VALUES ('91f6730500061a4c3e33889ea5b455f5', '2.png', 'http://192.168.156.128//group1/M00/00/08/wKicgF8LzZ2ABuBTABICG-5kTeY799.png', NULL, 'a6f9047e511bdfe3222a9583ca0e0341', 1, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('9a4e297565177a455c5505d1c98c53ce', '5.png', 'http://192.168.156.128//group1/M00/00/08/wKicgF7-6d6AbIN2ABP4pInxJjM685.png', NULL, '967746d9af6800370f69276e91c36277', 2, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('9e20f1871e2d2235cd4792f2a806e34f', '爱情谷名片图.png', 'http://192.168.156.128//group1/M00/00/08/wKicgF8LzYaAEcDuAA5oqouZYws201.png', NULL, 'a6f9047e511bdfe3222a9583ca0e0341', 1, 'postcard', 1);
INSERT INTO `mini_scene_image` VALUES ('a861a0354c2958bd476313995d1fbdee', '5.png', 'http://192.168.156.128//group1/M00/00/07/wKicgF7-6AmAKUVHABNBO17XvxE906.png', NULL, '9342013d67c7c252fbf291df96454871', 0, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('aafd60f6586364793b54ab3680dab102', '1.png', 'http://192.168.156.128//group1/M00/00/08/wKicgF8MIMGAQx3YABXHv_j8L9o800.png', NULL, '6be2b6dd4e19c481919be3106f4b39dc', 4, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('b083dbcd225359e0487c007d1bc23d78', '1 - 副本.png', 'http://192.168.156.128//group1/M00/00/08/wKicgF8NWKuAYERiAAxKQL0GMZM082.png', '/pages/scene/YuewangTower/YuewangTower', '62fee1c0efe40974de662f490257c3fc', 3, 'postcard', 1);
INSERT INTO `mini_scene_image` VALUES ('ba76bb93cb91768746f772597bad4046', '3.png', 'http://192.168.156.128//group1/M00/00/08/wKicgF8NWL2AMtpZAAhc5DnLrhE230.png', NULL, '62fee1c0efe40974de662f490257c3fc', 1, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('bbda7b013d185162612f6d554e5f2e9f', '3.png', 'http://192.168.156.128//group1/M00/00/08/wKicgF7-7geAPBYkABJHNQwTDCg654.png', NULL, '5a21aca0d84cb9724c398581d20e592f', 4, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('c1c8050d23d6944d55f10e8f467d3e98', '3.png', 'http://192.168.156.128//group1/M00/00/08/wKicgF7-6d6AFiB3ABCcNZz-DOo310.png', NULL, '967746d9af6800370f69276e91c36277', 0, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('ca9638a1a2b8b4b1239fa01b79f9245a', '5.png', 'http://192.168.156.128//group1/M00/00/08/wKicgF8MIMGAeaVlABZgonQhsQc632.png', NULL, '6be2b6dd4e19c481919be3106f4b39dc', 5, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('cd82f027d713a249c3c25375dab5dfe0', '4.png', 'http://192.168.156.128//group1/M00/00/07/wKicgF7-6AmATsYnABT_ZupHzio739.png', NULL, '9342013d67c7c252fbf291df96454871', 5, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('cdf0bf30fc189beff2bb684fe211f4ce', '3.png', 'http://192.168.156.128//group1/M00/00/08/wKicgF7-6lqAM24OAA7DRlyUaVs818.png', NULL, '54f0bb9f1e65e13a7a08e6db353c4626', 2, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('d28baccabc02aceca52cc92092893662', '2.png', 'http://192.168.156.128//group1/M00/00/08/wKicgF7-7geAdtqbABK33nPXiEU040.png', NULL, '5a21aca0d84cb9724c398581d20e592f', 0, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('df234b4e0ffc296e1c927dcd8b606d76', '图文详情页.png', 'http://192.168.156.128//group1/M00/00/08/wKicgF7-6eOAHrrUAIQlgt6zGlI459.png', NULL, '967746d9af6800370f69276e91c36277', NULL, 'richText', 0);
INSERT INTO `mini_scene_image` VALUES ('e32be7dbf6a815648c6a58c7f1e87046', '2.png', 'http://192.168.156.128//group1/M00/00/08/wKicgF8NWL2AVI3nAB502dmAmWs350.png', NULL, '62fee1c0efe40974de662f490257c3fc', 5, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('e636748fdefb91aecedae8b16d1fa4ab', '1.png', 'http://192.168.156.128//group1/M00/00/08/wKicgF7-6d6AKlwoABbmZl22Umg808.png', NULL, '967746d9af6800370f69276e91c36277', 4, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('eede946f7c0ecc88c890c54594a96516', '太乙山植物园名片图.png', 'http://192.168.156.128//group1/M00/00/08/wKicgF8MIfiAGWXFAAiWPMWqCC0983.png', NULL, '6be2b6dd4e19c481919be3106f4b39dc', NULL, 'postcard', 0);
INSERT INTO `mini_scene_image` VALUES ('eef42d8088a25be71100daff8558128b', '3.png', 'http://192.168.156.128//group1/M00/00/07/wKicgF7-6AmAYUOeABg4sQZBigI931.png', NULL, '9342013d67c7c252fbf291df96454871', 4, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('f686cd7fe149c44e64c771a0bf38d077', '4.png', 'http://192.168.156.128//group1/M00/00/08/wKicgF8NWL2AYbiAABdA3V6gbjY711.png', NULL, '62fee1c0efe40974de662f490257c3fc', 2, 'intros', 0);

-- ----------------------------
-- Table structure for mini_slider
-- ----------------------------
DROP TABLE IF EXISTS `mini_slider`;
CREATE TABLE `mini_slider`  (
  `id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `src` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '1080*600！！！！！',
  `target_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `url` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `order_num` int(0) NULL DEFAULT NULL,
  `top` tinyint(0) NULL DEFAULT NULL,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mini_slider
-- ----------------------------
INSERT INTO `mini_slider` VALUES ('05b7d5fbf83e100410dbaa43530fb35e', 'http://192.168.156.128//group1/M00/00/08/wKicgF8MLxqAAHMGABOSRMFaFt4326.png', '9342013d67c7c252fbf291df96454871', NULL, 2, 1, '平武报恩寺首页轮播图.png');
INSERT INTO `mini_slider` VALUES ('1a87af18f1e283c1e61a27e98f55e502', 'http://192.168.156.128//group1/M00/00/08/wKicgF8MLlKADm9ZAA8fIIgWpec851.png', '5a21aca0d84cb9724c398581d20e592f', NULL, NULL, 0, '佛爷洞首页轮播图.png');
INSERT INTO `mini_slider` VALUES ('4b78ad6c01011669306c030b6faf5c32', 'http://192.168.156.128//group1/M00/00/08/wKicgF8MLt2APiMxAAp6j5hsVCY961.png', '967746d9af6800370f69276e91c36277', NULL, 3, 1, '北川旅游.png');
INSERT INTO `mini_slider` VALUES ('5fa28c761cded45630ca863bec72d4af', 'http://192.168.156.128//group1/M00/00/08/wKicgF8LzZCAIMYoAA-8ZSjmgso727.png', 'a6f9047e511bdfe3222a9583ca0e0341', NULL, 1, 1, '微信图片_20200713101534.png');
INSERT INTO `mini_slider` VALUES ('7afc03e1fb0484a0d1f96bd50fb8cba5', 'http://192.168.156.128//group1/M00/00/08/wKicgF8MIf6AUCCmABoKsH22EjY936.png', '6be2b6dd4e19c481919be3106f4b39dc', NULL, NULL, 0, '太乙山植物园首页轮播图.png');
INSERT INTO `mini_slider` VALUES ('7eab089ccf31534a517b788926068b7c', 'http://192.168.156.128//group1/M00/00/08/wKicgF8NWLaAU8YZABLxvHhTKXM019.png', '62fee1c0efe40974de662f490257c3fc', '/pages/scene/YuewangTower/YuewangTower', 3, 0, '越王楼首页轮播图.png');
INSERT INTO `mini_slider` VALUES ('c669a7d4ea4c1daed37bcf96eab7ccce', 'http://192.168.156.128//group1/M00/00/08/wKicgF8MLsWAAk6dAA3icv9Qd34288.png', '54f0bb9f1e65e13a7a08e6db353c4626', NULL, 4, 1, '窦团山首页轮播图.png');

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
-- Table structure for search
-- ----------------------------
DROP TABLE IF EXISTS `search`;
CREATE TABLE `search`  (
  `id` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `content` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '搜索内容',
  `searcher` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '搜索者用户id',
  `search_time` datetime(0) NULL DEFAULT NULL COMMENT '搜索时间。用户只保留最近5次搜索',
  `hot` tinyint(0) NULL DEFAULT NULL COMMENT '是否是热搜',
  `order_num` int(0) NULL DEFAULT 20 COMMENT '热搜顺序，默认20',
  `owner` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '买热搜的商家id'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of search
-- ----------------------------
INSERT INTO `search` VALUES ('595ed9ab87bd80a628cd22b9597ca67e', '肖战', 'oXAfJ5YrJ6RIuEzF5ZeADeRtqDJ0', '2020-07-16 15:21:07', NULL, 20, NULL);
INSERT INTO `search` VALUES ('694986315747486a9cc1764ab03aab7e', '搜索', 'oXAfJ5YrJ6RIuEzF5ZeADeRtqDJ0', '2020-07-16 15:21:25', NULL, 20, NULL);
INSERT INTO `search` VALUES ('6add346dc80c02dff701d125794d6a01', '越南', NULL, NULL, 1, 2, NULL);
INSERT INTO `search` VALUES ('cad195b18ce2c3af3d8b0ff53debdf24', '泰国', NULL, NULL, 1, 4, NULL);
INSERT INTO `search` VALUES ('60a904a5e5b97cb7cd379f9fd0990271', '缅甸', NULL, NULL, 1, 5, NULL);
INSERT INTO `search` VALUES ('7d4ba9219973a67e68cdc76ea0ed565a', '纽约', NULL, NULL, 1, 9, NULL);
INSERT INTO `search` VALUES ('2df76940a22906dbc9d6ccaeedddf0db', '泰国', 'oXAfJ5YrJ6RIuEzF5ZeADeRtqDJ0', '2020-07-16 17:59:00', NULL, 20, NULL);

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
  `role` int(0) NULL DEFAULT NULL COMMENT '1：超级管理员   2：管理员   3：客户',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `activated` tinyint(0) NULL DEFAULT 1,
  `company` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('a', 'admin', '123456', 1, '18598462152', 1, '卓行科技', 'cdacasd@qq.com');
INSERT INTO `user` VALUES ('b', 'manager', '123456', 2, '1561651', 1, '卓行科技', '15616516');
INSERT INTO `user` VALUES ('c', 'manager2', '123', 2, NULL, 1, '卓行科技', NULL);
INSERT INTO `user` VALUES ('34ad9662cfcaee3e4c15d9e0f5e7dbd1', '123', '123456', 3, '是是', 1, '是s', '是');
INSERT INTO `user` VALUES ('d', 'client1', '123456', 3, NULL, 1, NULL, NULL);
INSERT INTO `user` VALUES ('143aa88c11ab7f49b6e073cde4a418bb', 'client9', '123456', 3, '', 1, '', NULL);
INSERT INTO `user` VALUES ('as', 'saobi', '123456', 3, NULL, 1, NULL, NULL);
INSERT INTO `user` VALUES ('00f435b3d42b7501cebf1e187883b1c0', 'saobibi', '123456', 3, 'cdascsa', 1, 'cdascdas', 'cdsacsa');

-- ----------------------------
-- Table structure for vip_card
-- ----------------------------
DROP TABLE IF EXISTS `vip_card`;
CREATE TABLE `vip_card`  (
  `id` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `openid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '小程序用户openid',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话号码',
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '收货地址',
  `real_name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '真名',
  `id_num` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '身份证号',
  `photo_src` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '真人照片',
  `promo_code` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '6位推广码',
  `superior_id` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '上级ID',
  `expiration_time` datetime(0) NULL DEFAULT NULL COMMENT '到期时间！！！！',
  `remaining_days` int(0) NULL DEFAULT NULL COMMENT '剩余天数',
  `gender` tinyint(0) NULL DEFAULT NULL COMMENT '性别',
  `birthday` datetime(0) NULL DEFAULT NULL COMMENT '生日',
  `edit_time` datetime(0) NULL DEFAULT NULL COMMENT '一天只能改一次，防止乱搞',
  `profit` int(0) NULL DEFAULT NULL COMMENT '推广收益',
  `wechat_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '提现用微信号'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vip_card
-- ----------------------------
INSERT INTO `vip_card` VALUES ('a', 'oXAfJ5YrJ6RIuEzF5ZeADeRtqDJ0', '18683951862', '公主殿', '周薇儿', '510704199506011511', 'http://192.168.156.128//group1/M00/00/07/wKicgF7sbySAUo7CACFwR8gkRy8533.jpg', 'aaaaaaaa', NULL, '2021-07-09 10:41:59', 0, NULL, NULL, NULL, 0, 'yuweier123');
INSERT INTO `vip_card` VALUES ('b', 'dsacsacs', '18688888888', '绵阳射洪', '周小薇', '51888', 'http://192.168.156.128//group1/M00/00/07/wKicgF7sg8CAKKTzADDRgJ7bLLo127.jpg', 'bbbbbbbb', 'a', '2021-07-02 16:43:26', 0, 0, NULL, NULL, 20, NULL);
INSERT INTO `vip_card` VALUES ('c', 'oXAfJ5YrJ6RIuEzF5ZeADeRtqDJ', '3', '3', '3', '510704198906048888', 'http://192.168.156.128//group1/M00/00/07/wKicgF7wG3aAM1TFAAMW2sZ45Ts658.jpg', 'cccccccc', 'b', '2020-07-07 12:21:22', 0, 0, '2000-06-04 10:46:14', NULL, 0, '天滅中共');

-- ----------------------------
-- Table structure for withdraw
-- ----------------------------
DROP TABLE IF EXISTS `withdraw`;
CREATE TABLE `withdraw`  (
  `id` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `amount` int(0) NULL DEFAULT NULL,
  `wechat_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '申请时间（提现工单创建时间）',
  `state` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '提现状态：0.未处理 1.已处理 2.有问题'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of withdraw
-- ----------------------------
INSERT INTO `withdraw` VALUES ('49a24bcec4261a65375e707c2382c074', 40, 'zzr123', '2020-07-08 12:01:01', '处理中');
INSERT INTO `withdraw` VALUES ('506c7ba46efe3af0735eca0365a99519', 2, 'wuyuwei123', '2020-07-13 11:31:35', '未处理');

-- ----------------------------
-- View structure for nigger
-- ----------------------------
DROP VIEW IF EXISTS `nigger`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `nigger` AS select `mini_scene`.`id` AS `id`,`mini_scene`.`name` AS `name`,`mini_scene`.`slogan` AS `slogan`,`mini_scene`.`username` AS `username`,`mini_scene`.`location` AS `location` from `mini_scene` order by (convert(`mini_scene`.`name` using gbk) collate gbk_chinese_ci);

-- ----------------------------
-- Procedure structure for nigger
-- ----------------------------
DROP PROCEDURE IF EXISTS `nigger`;
delimiter ;;
CREATE PROCEDURE `nigger`()
BEGIN
UPDATE vip_card SET remaining_days = remaining_days - 1
WHERE remaining_days >=50;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for reduceVIP
-- ----------------------------
DROP PROCEDURE IF EXISTS `reduceVIP`;
delimiter ;;
CREATE PROCEDURE `reduceVIP`()
BEGIN 
	UPDATE vip_card 
	SET remaining_days = remaining_days - 1
	WHERE remaining_days >=1;
END
;;
delimiter ;

-- ----------------------------
-- Event structure for reduceVIP
-- ----------------------------
DROP EVENT IF EXISTS `reduceVIP`;
delimiter ;;
CREATE EVENT `reduceVIP`
ON SCHEDULE
EVERY '1' SECOND STARTS '2020-06-22 15:59:28'
ON COMPLETION PRESERVE
DISABLE
DO call reduceVIP()
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
