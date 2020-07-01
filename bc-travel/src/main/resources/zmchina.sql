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

 Date: 01/07/2020 18:05:35
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
INSERT INTO `menu` VALUES (4, '置顶管理', NULL, -1, 4, NULL);
INSERT INTO `menu` VALUES (5, '页面管理', NULL, -1, 5, NULL);
INSERT INTO `menu` VALUES (11, '景区列表', '/scene', 1, 1, NULL);
INSERT INTO `menu` VALUES (12, '线路列表', '/route', 1, 2, NULL);
INSERT INTO `menu` VALUES (13, '活动列表', '/activity', 1, 3, NULL);
INSERT INTO `menu` VALUES (21, '用户管理', '/authorization', 2, 1, NULL);
INSERT INTO `menu` VALUES (31, '客户管理', '/client', 3, 1, NULL);
INSERT INTO `menu` VALUES (41, '景区', '/stickyScene', 4, 1, NULL);
INSERT INTO `menu` VALUES (42, '线路', '/stickyRoute', 4, 2, NULL);
INSERT INTO `menu` VALUES (43, '活动', '/stickyActivity', 4, 3, NULL);
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
INSERT INTO `mini_route` VALUES ('66301802e60784c43ac04067ab9a49be', '富乐山一日游', '一日游', '一日游', 'admin', '2020-06-10 09:52:50');

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
INSERT INTO `mini_route_image` VALUES ('61e9b0350bbd311250ee960e335b1be7', '船袜.jpg', 'http://192.168.156.128//group1/M00/00/06/wKicgF7gPO6AXPfWAAV8J5lkDpc982.jpg', NULL, '66301802e60784c43ac04067ab9a49be', 0, 'intros', 0);
INSERT INTO `mini_route_image` VALUES ('79dd0c8c0c9d428d6394adc354f023a2', '659a62e84b27ec9648ef6e84df8eacc4.jpg', 'http://192.168.156.128//group1/M00/00/06/wKicgF7gPMiAdXx2AAgMHSR613g373.jpg', NULL, '66301802e60784c43ac04067ab9a49be', NULL, 'richText', 0);
INSERT INTO `mini_route_image` VALUES ('c247ecc6a6f034511c84c9eb8675f6e7', '352b54dc6c9fd58b3f303793dfc29d48.jpg', 'http://192.168.156.128//group1/M00/00/06/wKicgF7gPLmABpWLAAZTykKHkxo358.jpg', NULL, '66301802e60784c43ac04067ab9a49be', NULL, 'postcard', 0);
INSERT INTO `mini_route_image` VALUES ('f65dba118dc0cce9fa43943b3ef9cbbc', '352b54dc6c9fd58b3f303793dfc29d48.jpg', 'http://192.168.156.128//group1/M00/00/06/wKicgF7gPPGAKSOUAAZTykKHkxo825.jpg', NULL, '66301802e60784c43ac04067ab9a49be', 1, 'intros', 0);

-- ----------------------------
-- Table structure for mini_scene
-- ----------------------------
DROP TABLE IF EXISTS `mini_scene`;
CREATE TABLE `mini_scene`  (
  `id` varchar(100) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `name` varchar(50) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NULL DEFAULT NULL,
  `slogan` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '精简介绍',
  `username` varchar(100) CHARACTER SET gbk COLLATE gbk_bin NULL DEFAULT NULL,
  `location` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '景区地址',
  `create_time` datetime(0) NULL DEFAULT NULL,
  INDEX `index_create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mini_scene
-- ----------------------------
INSERT INTO `mini_scene` VALUES ('ec082d41e6ab17a58337a8c530a951d0', '西山公园', '他出发找最爱  今天也未回来', 'admin', '薇儿公主家背后', '2020-06-10 10:04:57');
INSERT INTO `mini_scene` VALUES ('f93391ee04cac2bd92f40a4db3b84be6', '测试1', '最美绵阳最美绵阳最美绵阳最美绵阳最美绵阳最美绵阳最美绵阳最美绵阳最美绵阳', 'admin', '四川省绵阳市安昌桥', '2020-05-25 14:53:21');
INSERT INTO `mini_scene` VALUES ('41794e46daec0c3eb76fd4ff3481c9e9', '测试2', '最美成都最美成都最美成都最美成都最美成都最美成都最美成都最美成都最美成都', 'admin', '成都市天府新区', '2020-05-24 14:53:21');
INSERT INTO `mini_scene` VALUES ('fe356a158b7dba4690ef324f7c72e919', '测试4', '最美上海最美上海最美上海最美上海最美上海最美上海最美上海最美上海最美上海', 'admin', '上海外滩', '2020-05-21 14:53:21');
INSERT INTO `mini_scene` VALUES ('0f10e8557d73e4ba87d9dbc9c4f25c88', '测试3 ', '最美纽约最美纽约最美纽约最美纽约最美纽约最美纽约最美纽约最美纽约最美纽约', 'admin', '北京天安门', '2020-05-20 14:53:21');

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
INSERT INTO `mini_scene_image` VALUES ('007a489f56c8afc769b083bb32d53b66', 'slider1.jpg', 'http://192.168.156.128//group1/M00/00/04/wKicgF7Ep-iAMpbgAAOo3gB0zW0092.jpg', NULL, 'f93391ee04cac2bd92f40a4db3b84be6', 1, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('0d45666dbeeff497f123d60d5d8d2d15', 'postcard2.jpg', 'http://192.168.156.128//group1/M00/00/04/wKicgF7Epu6AVIh9AADMkpkt4Wg228.jpg', '/pages/scene/test3/test3', '41794e46daec0c3eb76fd4ff3481c9e9', 2, 'postcard', 1);
INSERT INTO `mini_scene_image` VALUES ('2459493b37d1cb822e20c2d00603ee77', 'slider2.jpg', 'http://192.168.156.128//group1/M00/00/04/wKicgF7Ep0yAIrbIAAR_RmKZK60556.jpg', NULL, '41794e46daec0c3eb76fd4ff3481c9e9', 0, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('40301d1ae66d60719b533064f6b7d5ae', 'slider1.jpg', 'http://192.168.156.128//group1/M00/00/04/wKicgF7Ep-uARhmYAAOo3gB0zW0037.jpg', NULL, 'f93391ee04cac2bd92f40a4db3b84be6', 2, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('41af73ce1fd41ddd4b06a957f5c35080', 'slider2.jpg', 'http://192.168.156.128//group1/M00/00/04/wKicgF7Ep1OAagIlAAR_RmKZK60162.jpg', NULL, '41794e46daec0c3eb76fd4ff3481c9e9', 2, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('5392909e02f8cc865ea6d2aa10b5efd9', 'slider1.jpg', 'http://192.168.156.128//group1/M00/00/04/wKicgF7Ep-SAR90WAAOo3gB0zW0152.jpg', NULL, 'f93391ee04cac2bd92f40a4db3b84be6', 0, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('5a388e79bc049b5fdf96a33136cbb34e', 'richText.png', 'http://192.168.156.128//group1/M00/00/04/wKicgF7EpvuAaR5XAAAoi2RRW1o807.png', NULL, '41794e46daec0c3eb76fd4ff3481c9e9', NULL, 'richText', 0);
INSERT INTO `mini_scene_image` VALUES ('5b021e2554c3ed4ff9fa3bb8782434ca', 'slider4.jpg', 'http://192.168.156.128//group1/M00/00/04/wKicgF7Ep56AKJTHAAGwPW2Jtks477.jpg', NULL, 'fe356a158b7dba4690ef324f7c72e919', 2, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('5e8e9e60394f4ec898772e8b067eb528', '微信图片_2020051510462712.jpg', 'http://192.168.156.128//group1/M00/00/06/wKicgF7gP8CAfM0gAAEfP4coLr0324.jpg', NULL, 'ec082d41e6ab17a58337a8c530a951d0', 0, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('672223536afbc588d9bf15e20293eb0d', 'slider2.jpg', 'http://192.168.156.128//group1/M00/00/04/wKicgF7Ep0-AXACVAAR_RmKZK60152.jpg', NULL, '41794e46daec0c3eb76fd4ff3481c9e9', 1, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('82903eb69053a5ff604b5514cb0f09b8', 'slider3.jpg', 'http://192.168.156.128//group1/M00/00/04/wKicgF7EpzuAMKZBAAJUD4UDa2k026.jpg', NULL, '0f10e8557d73e4ba87d9dbc9c4f25c88', 2, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('8db4f8770fb9bffe5e15b385ebe7d626', 'richText.png', 'http://192.168.156.128//group1/M00/00/04/wKicgF7EppeAZPQPAAAoi2RRW1o462.png', NULL, 'f93391ee04cac2bd92f40a4db3b84be6', NULL, 'richText', 0);
INSERT INTO `mini_scene_image` VALUES ('90d72dbade51cc438f195b4bc19c5253', 'slider4.jpg', 'http://192.168.156.128//group1/M00/00/04/wKicgF7Ep5iAM0TmAAGwPW2Jtks528.jpg', NULL, 'fe356a158b7dba4690ef324f7c72e919', 0, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('93b5db81bd8ea12e2695628099ef9b83', 'postcard4.jpg', 'http://192.168.156.128//group1/M00/00/04/wKicgF7Ep5CAZsZSAABFKxQwtK0630.jpg', '/pages/scene/test4/test4', 'fe356a158b7dba4690ef324f7c72e919', 1, 'postcard', 1);
INSERT INTO `mini_scene_image` VALUES ('983bc81b2b91349c9f9741dec6dd9078', 'richText.png', 'http://192.168.156.128//group1/M00/00/04/wKicgF7Epz-AaSLaAAAoi2RRW1o599.png', NULL, '0f10e8557d73e4ba87d9dbc9c4f25c88', NULL, 'richText', 0);
INSERT INTO `mini_scene_image` VALUES ('a407f24a578db8705eb4331ea1d8e60a', '微信图片_2020051510462713.jpg', 'http://192.168.156.128//group1/M00/00/06/wKicgF7gP7eAGls7AAEdnvZJ7Yw226.jpg', NULL, 'ec082d41e6ab17a58337a8c530a951d0', NULL, 'postcard', 0);
INSERT INTO `mini_scene_image` VALUES ('ab345295a396b67aece8221ffca1d65a', '微信图片_2020051510462712.jpg', 'http://192.168.156.128//group1/M00/00/06/wKicgF7gP8SAPuwzAAEfP4coLr0559.jpg', NULL, 'ec082d41e6ab17a58337a8c530a951d0', NULL, 'richText', 0);
INSERT INTO `mini_scene_image` VALUES ('cb2668ded99efabfded320fc212bb6e3', 'slider3.jpg', 'http://192.168.156.128//group1/M00/00/04/wKicgF7EpzKAEbE6AAJUD4UDa2k226.jpg', NULL, '0f10e8557d73e4ba87d9dbc9c4f25c88', 0, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('ce0090f2fff480665656820581736e2a', 'richText.png', 'http://192.168.156.128//group1/M00/00/04/wKicgF7Ep66ARxHeAAAoi2RRW1o433.png', NULL, 'fe356a158b7dba4690ef324f7c72e919', NULL, 'richText', 0);
INSERT INTO `mini_scene_image` VALUES ('ce59fa4f92be813d83a7a4bc7c415461', 'slider3.jpg', 'http://192.168.156.128//group1/M00/00/04/wKicgF7EpzeAJkF-AAJUD4UDa2k338.jpg', NULL, '0f10e8557d73e4ba87d9dbc9c4f25c88', 1, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('cf4f012426d6db8c76d310caddcdadeb', 'postcard3.jpg', 'http://192.168.156.128//group1/M00/00/04/wKicgF7EpyWAILFPAACPI1U4lk4334.jpg', '/pages/scene/test2/test2', '0f10e8557d73e4ba87d9dbc9c4f25c88', 3, 'postcard', 1);
INSERT INTO `mini_scene_image` VALUES ('cfdd3b1f84ab60fa25c27fa64f7ddd95', 'slider4.jpg', 'http://192.168.156.128//group1/M00/00/04/wKicgF7Ep5uAXthUAAGwPW2Jtks286.jpg', NULL, 'fe356a158b7dba4690ef324f7c72e919', 1, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('fddc5c1b2fee4022398e89fe28346404', 'postcard1.jpg', 'http://192.168.156.128//group1/M00/00/04/wKicgF7EpoOAfDG3AAC3pl1CF78814.jpg', '/pages/scene/test1/test1', 'f93391ee04cac2bd92f40a4db3b84be6', 4, 'postcard', 1);

-- ----------------------------
-- Table structure for mini_slider
-- ----------------------------
DROP TABLE IF EXISTS `mini_slider`;
CREATE TABLE `mini_slider`  (
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
-- Records of mini_slider
-- ----------------------------
INSERT INTO `mini_slider` VALUES ('04bfc2ae673ff2fe9f7810701bb4ae03', 'http://192.168.156.128//group1/M00/00/04/wKicgF7Epo-AD8_QAAOo3gB0zW0783.jpg', 'f93391ee04cac2bd92f40a4db3b84be6', '/pages/scene/test1/test1', 1, 1, 'slider1.jpg');
INSERT INTO `mini_slider` VALUES ('0d080f53d27616886c9325cdffd138b4', 'http://192.168.156.128//group1/M00/00/06/wKicgF7gP7uAPmiWAAEdnvZJ7Yw046.jpg', 'ec082d41e6ab17a58337a8c530a951d0', NULL, NULL, 0, '微信图片_2020051510462713.jpg');
INSERT INTO `mini_slider` VALUES ('14006ada6822c3bc791c71321e79d9b8', 'http://192.168.156.128//group1/M00/00/05/wKicgF7Ll0OAapoiAACPcd0c1jk188.jpg', 'ba34eeaba69bf43a8d584b906f445a63', NULL, NULL, 0, 'yongsu.jpg');
INSERT INTO `mini_slider` VALUES ('1cc3d6f20372336a4361537bbdd9e82a', 'http://192.168.156.128//group1/M00/00/05/wKicgF7HdrSAOKWDAADLwkE-dHc124.jpg', '3071029a6a55bd552c518ce7e1871ef4', NULL, NULL, 0, 'c.jpg');
INSERT INTO `mini_slider` VALUES ('29200d1406de9157758cc6960958d6d5', 'http://192.168.156.128//group1/M00/00/04/wKicgF7EpyyABjFPAAJUD4UDa2k998.jpg', '0f10e8557d73e4ba87d9dbc9c4f25c88', '/pages/scene/test2/test2', 2, 1, 'slider3.jpg');
INSERT INTO `mini_slider` VALUES ('302b22c2f1979c04743bc3ef338ba7bb', 'http://192.168.156.128//group1/M00/00/05/wKicgF7HdtiADTPjAAERJUqHdzI510.jpg', 'b073b43d0dd5331c68d86897b94a165f', NULL, NULL, 0, 'd.jpg');
INSERT INTO `mini_slider` VALUES ('36e032cb296ecd762269dd71d0dea832', 'http://192.168.156.128//group1/M00/00/05/wKicgF7HdAWAPdfdAAfEtqyGnBM159.jpg', 'a2ede76a65391a938225d81a6e7f481b', NULL, NULL, 0, 'b.jpg');
INSERT INTO `mini_slider` VALUES ('74c6c84feee65eeadc12feba985c81f1', 'http://192.168.156.128//group1/M00/00/05/wKicgF7HRfOANAnpAACvuPGMNGE100.jpg', 'ddcf3f3945a2e12b15a07c1b33d44b93', NULL, NULL, 0, 'timg (1).jpg');
INSERT INTO `mini_slider` VALUES ('93fc5cbca0454a3cc706cd9af2e05f47', 'http://192.168.156.128//group1/M00/00/04/wKicgF7EpvOAXYwjAAR_RmKZK60986.jpg', '41794e46daec0c3eb76fd4ff3481c9e9', '/pages/scene/test3/test3', 3, 1, 'slider2.jpg');
INSERT INTO `mini_slider` VALUES ('a33aaa7e29cedcab1b91cbd5f09b3a45', 'http://192.168.156.128//group1/M00/00/04/wKicgF7Ep5SAEbZ_AAGwPW2Jtks876.jpg', 'fe356a158b7dba4690ef324f7c72e919', '/pages/scene/test4/test4', 4, 1, 'slider4.jpg');
INSERT INTO `mini_slider` VALUES ('d4f1a963b69024e0cab3ef055a7e888b', 'http://192.168.156.128//group1/M00/00/06/wKicgF7gPNaAV6j1AAZTykKHkxo734.jpg', '66301802e60784c43ac04067ab9a49be', NULL, NULL, 0, '352b54dc6c9fd58b3f303793dfc29d48.jpg');
INSERT INTO `mini_slider` VALUES ('e677a52ff7f181a336653101c612b283', 'http://192.168.156.128//group1/M00/00/05/wKicgF7HRQeAQ4IXAAGKocrHUek506.jpg', '5a95562dacd0dc1fdefad2690f20b910', NULL, NULL, 0, 'xiaosaobi.jpg');
INSERT INTO `mini_slider` VALUES ('fd9f2d0e0ee9db89788608d00927295b', 'http://192.168.156.128//group1/M00/00/05/wKicgF7HRY2AbGzLAAFGo0f5C-Y116.jpg', '17463182c3a99d2ed6befbcd898acd53', NULL, NULL, 0, '微信图片_2020051510462714.jpg');

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
INSERT INTO `user` VALUES ('b', 'manager', '123456', 1, '1561651', 1, '卓行科技', '15616516');
INSERT INTO `user` VALUES ('c', 'manager2', '123', 2, NULL, 1, '卓行科技', NULL);
INSERT INTO `user` VALUES ('34ad9662cfcaee3e4c15d9e0f5e7dbd1', '123', '123456', 3, '是是', 1, '是s', '是');
INSERT INTO `user` VALUES ('as', 'bbbbb', '123456', 3, NULL, 1, NULL, NULL);
INSERT INTO `user` VALUES ('00f435b3d42b7501cebf1e187883b1c0', 'cdsa', 'cdascs', 3, 'cdascsa', 1, 'cdascdas', 'cdsacsa');
INSERT INTO `user` VALUES ('d', 'client1', '123456', 3, NULL, 1, NULL, NULL);
INSERT INTO `user` VALUES ('143aa88c11ab7f49b6e073cde4a418bb', 'client9', '123456', 3, '', 1, '', NULL);

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
  `photo_src` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '真人照片',
  `promo_code` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '6位推广码',
  `superior_id` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '上级ID',
  `expiration_time` datetime(0) NULL DEFAULT NULL COMMENT '到期时间！！！！',
  `remaining_days` int(0) NULL DEFAULT NULL COMMENT '剩余天数',
  `gender` tinyint(0) NULL DEFAULT NULL COMMENT '性别',
  `birthday` datetime(0) NULL DEFAULT NULL COMMENT '生日',
  `edit_time` datetime(0) NULL DEFAULT NULL COMMENT '一天只能改一次，防止乱搞'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vip_card
-- ----------------------------
INSERT INTO `vip_card` VALUES ('1ad25b61b05a35d057fc920fc52b9c54', NULL, '18683951862', '公主殿', '周薇儿', '510704199506011511', 'http://192.168.156.128//group1/M00/00/07/wKicgF7sbySAUo7CACFwR8gkRy8533.jpg', 't54rpl', NULL, NULL, 153, NULL, NULL, NULL);
INSERT INTO `vip_card` VALUES ('d0a046bf197fedd1b9d6bf774a7a1396', NULL, '18688888888', '绵阳射洪', '周小薇', '51888', 'http://192.168.156.128//group1/M00/00/07/wKicgF7sg8CAKKTzADDRgJ7bLLo127.jpg', 'ZXWbho', NULL, NULL, 0, 0, NULL, NULL);
INSERT INTO `vip_card` VALUES ('6f89bea02ab74636be9fa5a212d8e135', NULL, '3', '3', '3', '510704200006048888', 'http://192.168.156.128//group1/M00/00/07/wKicgF7wG3aAM1TFAAMW2sZ45Ts658.jpg', '349ikb', NULL, NULL, 0, 0, '2000-06-04 10:46:14', NULL);
INSERT INTO `vip_card` VALUES ('df16490e372d3943eac3f3a15640610b', 'oXAfJ5YrJ6RIuEzF5ZeADeRtqDJ0', '8848', '777777777777777777', '女神周薇儿', '510704199506011511', 'undefined', 'NSZWEm', NULL, '2021-07-01 15:13:38', NULL, 0, '1995-06-01 14:38:03', NULL);

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
