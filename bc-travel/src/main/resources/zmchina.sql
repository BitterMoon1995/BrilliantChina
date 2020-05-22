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

 Date: 22/05/2020 16:19:24
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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mini_activity
-- ----------------------------
INSERT INTO `mini_activity` VALUES ('c698c33e2978c42fb7864c9d03bedef9', '富乐山划船', '划船', 'admin', '富乐山');

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
INSERT INTO `mini_activity_image` VALUES ('b2d99228ed11a1760451e73cf0671b2e', '7427eaa176f5185c3ae82c.jpg', 'http://192.168.156.128//group1/M00/00/05/wKicgF7F8i6Aaoi4AAIqUdn1iAA170.jpg', NULL, 'c698c33e2978c42fb7864c9d03bedef9', 'postcard', NULL, 0);
INSERT INTO `mini_activity_image` VALUES ('c11fe77d8f49d033dc15288b8c2b595b', '37f65f0e6703730b9876e623127dd905.jpg.jpg', 'http://192.168.156.128//group1/M00/00/05/wKicgF7F8k-AbC3NAABbFpimP1k965.jpg', NULL, 'c698c33e2978c42fb7864c9d03bedef9', 'richText', NULL, 0);
INSERT INTO `mini_activity_image` VALUES ('cced6376c8161d9433a6ef9225b6823d', 'ef0d1a42c874dfdeb43ad66b41d273b1.jpg.jpg', 'http://192.168.156.128//group1/M00/00/05/wKicgF7F8hmAEkphAAAk82vp4Jw793.jpg', NULL, 'c698c33e2978c42fb7864c9d03bedef9', 'intros', 0, 0);

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
INSERT INTO `mini_icon` VALUES ('43ff58b07542b797c0c52c46edf20a3a', '更多.png', 'http://192.168.156.128//group1/M00/00/05/wKicgF7GTjeACm-lAAAc9nEEeT8666.png', NULL, NULL, 4, 'category');
INSERT INTO `mini_icon` VALUES ('5cba8de9d8023865baf9822b2d6f8b32', '旅游.png', 'http://192.168.156.128//group1/M00/00/05/wKicgF7GTiSAMRuIAAAeAhtCDiw832.png', NULL, NULL, 1, 'category');
INSERT INTO `mini_icon` VALUES ('5fc591b0d1a8ae8ff25edcb85596924c', '学习经历.png', 'http://192.168.156.128//group1/M00/00/05/wKicgF7GTjOAaUfBAAAd_jtces8618.png', NULL, NULL, 3, 'category');
INSERT INTO `mini_icon` VALUES ('7678844fd037e3eaaf04eb26f20b85b0', '门票.png', 'http://192.168.156.128//group1/M00/00/05/wKicgF7GTiqABc8eAAAmn2RYNdY638.png', NULL, NULL, 2, 'category');
INSERT INTO `mini_icon` VALUES ('9398f977d50d764f0c864e27f9ec9722', '会员卡.png', 'http://192.168.156.128//group1/M00/00/05/wKicgF7GTiGAVincAAAQqFG_qDA709.png', NULL, NULL, 0, 'category');

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
  `order_num` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mini_route
-- ----------------------------
INSERT INTO `mini_route` VALUES ('a071029a6a55bd552c518ce7e1871ef4', '八旗深圳', '八旗深圳', '八旗深圳', 'cdmin', '2020-05-22 14:52:44', 3);
INSERT INTO `mini_route` VALUES ('b2ede76a65391a938225d81a6e7f481b', '八旗老北京儿', '老北京儿', '老北京', 'bdmin', '2020-05-22 14:41:17', 1);
INSERT INTO `mini_route` VALUES ('z073b43d0dd5331c68d86897b94a165f', '八旗广州', '八旗广州', '八旗广州', 'admin', '2020-05-22 14:53:21', 2);

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
INSERT INTO `mini_route_image` VALUES ('0093dda124f26a7072e585c8958b164a', 'd.jpg', 'http://192.168.156.128//group1/M00/00/05/wKicgF7HdtuAZ-6-AAERJUqHdzI038.jpg', NULL, 'b073b43d0dd5331c68d86897b94a165f', 0, 'intros', 0);
INSERT INTO `mini_route_image` VALUES ('02f2ac4d743b1622f4876e627f66ad69', '微信图片_2020051510462714.jpg', 'http://192.168.156.128//group1/M00/00/05/wKicgF7HRZWAIGyhAAFGo0f5C-Y882.jpg', NULL, '17463182c3a99d2ed6befbcd898acd53', NULL, 'richText', 0);
INSERT INTO `mini_route_image` VALUES ('0398feeda8d4077eed2e2193416f4ff6', 'b.jpg', 'http://192.168.156.128//group1/M00/00/05/wKicgF7HdAGAM0P4AAfEtqyGnBM746.jpg', NULL, 'a2ede76a65391a938225d81a6e7f481b', NULL, 'postcard', 0);
INSERT INTO `mini_route_image` VALUES ('1219d31735c08d70f42b7fc3347026f9', 'c.jpg', 'http://192.168.156.128//group1/M00/00/05/wKicgF7HdrGAPX72AADLwkE-dHc475.jpg', NULL, '3071029a6a55bd552c518ce7e1871ef4', NULL, 'postcard', 0);
INSERT INTO `mini_route_image` VALUES ('21292e701a534bc71ecc974dc7445200', 'xiaosaobi.jpg', 'http://192.168.156.128//group1/M00/00/05/wKicgF7HRQ6AFkKEAAGKocrHUek915.jpg', NULL, '5a95562dacd0dc1fdefad2690f20b910', NULL, 'richText', 0);
INSERT INTO `mini_route_image` VALUES ('47c1cd2438fd04605afb611e411423c6', '微信图片_2020051510462714.jpg', 'http://192.168.156.128//group1/M00/00/05/wKicgF7HRYmAFFD-AAFGo0f5C-Y057.jpg', NULL, '17463182c3a99d2ed6befbcd898acd53', NULL, 'postcard', 0);
INSERT INTO `mini_route_image` VALUES ('53409d1e36163ae58b54c699a26bd813', 'c.jpg', 'http://192.168.156.128//group1/M00/00/05/wKicgF7HdryAB4Z1AADLwkE-dHc902.jpg', NULL, '3071029a6a55bd552c518ce7e1871ef4', NULL, 'richText', 0);
INSERT INTO `mini_route_image` VALUES ('59b8e7bda54162941fce5a4475573bd9', 'timg (1).jpg', 'http://192.168.156.128//group1/M00/00/05/wKicgF7HRe-AVCrUAACvuPGMNGE357.jpg', NULL, 'ddcf3f3945a2e12b15a07c1b33d44b93', NULL, 'postcard', 0);
INSERT INTO `mini_route_image` VALUES ('736fe4159225eb636c8c3d51655eeef7', 'd.jpg', 'http://192.168.156.128//group1/M00/00/05/wKicgF7Hdt-AX_s0AAERJUqHdzI509.jpg', NULL, 'b073b43d0dd5331c68d86897b94a165f', NULL, 'richText', 0);
INSERT INTO `mini_route_image` VALUES ('8f71031ec20fbc6af79205e4c2f3debc', 'xiaosaobi.jpg', 'http://192.168.156.128//group1/M00/00/05/wKicgF7HRQKAb1XVAAGKocrHUek088.jpg', NULL, '5a95562dacd0dc1fdefad2690f20b910', NULL, 'postcard', 0);
INSERT INTO `mini_route_image` VALUES ('a9cd9dc83f8e8d594af9fe297119ffa8', 'timg (1).jpg', 'http://192.168.156.128//group1/M00/00/05/wKicgF7HRfeAQ4RFAACvuPGMNGE977.jpg', NULL, 'ddcf3f3945a2e12b15a07c1b33d44b93', 0, 'intros', 0);
INSERT INTO `mini_route_image` VALUES ('bf67c7b5f19f18c5a8fc1d01896c6dd2', 'b.jpg', 'http://192.168.156.128//group1/M00/00/05/wKicgF7HdAyAI7McAAfEtqyGnBM729.jpg', NULL, 'a2ede76a65391a938225d81a6e7f481b', NULL, 'richText', 0);
INSERT INTO `mini_route_image` VALUES ('c97d9e4a20f017ccde714a22f2322c83', 'c.jpg', 'http://192.168.156.128//group1/M00/00/05/wKicgF7HdriAMz1IAADLwkE-dHc817.jpg', NULL, '3071029a6a55bd552c518ce7e1871ef4', 0, 'intros', 0);
INSERT INTO `mini_route_image` VALUES ('d367dd09a45881984c463dfae85cfc33', '微信图片_2020051510462714.jpg', 'http://192.168.156.128//group1/M00/00/05/wKicgF7HRZGAflO5AAFGo0f5C-Y293.jpg', NULL, '17463182c3a99d2ed6befbcd898acd53', 0, 'intros', 0);
INSERT INTO `mini_route_image` VALUES ('e73162cd4f9f8a73085af01fde671683', 'd.jpg', 'http://192.168.156.128//group1/M00/00/05/wKicgF7HdtSAYJpYAAERJUqHdzI180.jpg', NULL, 'b073b43d0dd5331c68d86897b94a165f', NULL, 'postcard', 0);
INSERT INTO `mini_route_image` VALUES ('ef4e80978b4ba410d516c916a971cd7b', 'timg (1).jpg', 'http://192.168.156.128//group1/M00/00/05/wKicgF7HRfuARMs6AACvuPGMNGE573.jpg', NULL, 'ddcf3f3945a2e12b15a07c1b33d44b93', NULL, 'richText', 0);
INSERT INTO `mini_route_image` VALUES ('efc3c0dbe7689050d335be6a01a77c09', 'xiaosaobi.jpg', 'http://192.168.156.128//group1/M00/00/05/wKicgF7HRQqAMrVpAAGKocrHUek391.jpg', NULL, '5a95562dacd0dc1fdefad2690f20b910', 0, 'intros', 0);
INSERT INTO `mini_route_image` VALUES ('fa2bd7a7d812e787a67e9db9c34176ae', 'b.jpg', 'http://192.168.156.128//group1/M00/00/05/wKicgF7HdAmAAowJAAfEtqyGnBM071.jpg', NULL, 'a2ede76a65391a938225d81a6e7f481b', 0, 'intros', 0);

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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mini_scene
-- ----------------------------
INSERT INTO `mini_scene` VALUES ('0f10e8557d73e4ba87d9dbc9c4f25c88', '测试3 ', '北京北京北京北京北京北京', 'admin', '北京天安门');
INSERT INTO `mini_scene` VALUES ('41794e46daec0c3eb76fd4ff3481c9e9', '测试2', '成都成都成都成都成都成都成都', 'admin', '成都市天府新区');
INSERT INTO `mini_scene` VALUES ('f93391ee04cac2bd92f40a4db3b84be6', '测试1', '绵阳绵阳绵阳绵阳绵阳绵阳', 'admin', '四川省绵阳市安昌桥');
INSERT INTO `mini_scene` VALUES ('fe356a158b7dba4690ef324f7c72e919', '测试4', '上海外滩上海外滩上海外滩', 'admin', '上海外滩');

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
INSERT INTO `mini_scene_image` VALUES ('0d45666dbeeff497f123d60d5d8d2d15', 'postcard2.jpg', 'http://192.168.156.128//group1/M00/00/04/wKicgF7Epu6AVIh9AADMkpkt4Wg228.jpg', '/pages/scene/test3/test3', '41794e46daec0c3eb76fd4ff3481c9e9', 2, 'postcard', 0);
INSERT INTO `mini_scene_image` VALUES ('2459493b37d1cb822e20c2d00603ee77', 'slider2.jpg', 'http://192.168.156.128//group1/M00/00/04/wKicgF7Ep0yAIrbIAAR_RmKZK60556.jpg', NULL, '41794e46daec0c3eb76fd4ff3481c9e9', 0, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('40301d1ae66d60719b533064f6b7d5ae', 'slider1.jpg', 'http://192.168.156.128//group1/M00/00/04/wKicgF7Ep-uARhmYAAOo3gB0zW0037.jpg', NULL, 'f93391ee04cac2bd92f40a4db3b84be6', 2, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('41af73ce1fd41ddd4b06a957f5c35080', 'slider2.jpg', 'http://192.168.156.128//group1/M00/00/04/wKicgF7Ep1OAagIlAAR_RmKZK60162.jpg', NULL, '41794e46daec0c3eb76fd4ff3481c9e9', 2, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('5392909e02f8cc865ea6d2aa10b5efd9', 'slider1.jpg', 'http://192.168.156.128//group1/M00/00/04/wKicgF7Ep-SAR90WAAOo3gB0zW0152.jpg', NULL, 'f93391ee04cac2bd92f40a4db3b84be6', 0, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('5a388e79bc049b5fdf96a33136cbb34e', 'richText.png', 'http://192.168.156.128//group1/M00/00/04/wKicgF7EpvuAaR5XAAAoi2RRW1o807.png', NULL, '41794e46daec0c3eb76fd4ff3481c9e9', NULL, 'richText', 0);
INSERT INTO `mini_scene_image` VALUES ('5b021e2554c3ed4ff9fa3bb8782434ca', 'slider4.jpg', 'http://192.168.156.128//group1/M00/00/04/wKicgF7Ep56AKJTHAAGwPW2Jtks477.jpg', NULL, 'fe356a158b7dba4690ef324f7c72e919', 2, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('672223536afbc588d9bf15e20293eb0d', 'slider2.jpg', 'http://192.168.156.128//group1/M00/00/04/wKicgF7Ep0-AXACVAAR_RmKZK60152.jpg', NULL, '41794e46daec0c3eb76fd4ff3481c9e9', 1, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('82903eb69053a5ff604b5514cb0f09b8', 'slider3.jpg', 'http://192.168.156.128//group1/M00/00/04/wKicgF7EpzuAMKZBAAJUD4UDa2k026.jpg', NULL, '0f10e8557d73e4ba87d9dbc9c4f25c88', 2, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('8db4f8770fb9bffe5e15b385ebe7d626', 'richText.png', 'http://192.168.156.128//group1/M00/00/04/wKicgF7EppeAZPQPAAAoi2RRW1o462.png', NULL, 'f93391ee04cac2bd92f40a4db3b84be6', NULL, 'richText', 0);
INSERT INTO `mini_scene_image` VALUES ('90d72dbade51cc438f195b4bc19c5253', 'slider4.jpg', 'http://192.168.156.128//group1/M00/00/04/wKicgF7Ep5iAM0TmAAGwPW2Jtks528.jpg', NULL, 'fe356a158b7dba4690ef324f7c72e919', 0, 'intros', 0);
INSERT INTO `mini_scene_image` VALUES ('93b5db81bd8ea12e2695628099ef9b83', 'postcard4.jpg', 'http://192.168.156.128//group1/M00/00/04/wKicgF7Ep5CAZsZSAABFKxQwtK0630.jpg', '/pages/scene/test4/test4', 'fe356a158b7dba4690ef324f7c72e919', 1, 'postcard', 1);
INSERT INTO `mini_scene_image` VALUES ('983bc81b2b91349c9f9741dec6dd9078', 'richText.png', 'http://192.168.156.128//group1/M00/00/04/wKicgF7Epz-AaSLaAAAoi2RRW1o599.png', NULL, '0f10e8557d73e4ba87d9dbc9c4f25c88', NULL, 'richText', 0);
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
INSERT INTO `mini_slider` VALUES ('04bfc2ae673ff2fe9f7810701bb4ae03', 'http://192.168.156.128//group1/M00/00/04/wKicgF7Epo-AD8_QAAOo3gB0zW0783.jpg', 'f93391ee04cac2bd92f40a4db3b84be6', '/pages/scene/test1/test1', 1, 0, 'slider1.jpg');
INSERT INTO `mini_slider` VALUES ('1cc3d6f20372336a4361537bbdd9e82a', 'http://192.168.156.128//group1/M00/00/05/wKicgF7HdrSAOKWDAADLwkE-dHc124.jpg', '3071029a6a55bd552c518ce7e1871ef4', NULL, NULL, 0, 'c.jpg');
INSERT INTO `mini_slider` VALUES ('29200d1406de9157758cc6960958d6d5', 'http://192.168.156.128//group1/M00/00/04/wKicgF7EpyyABjFPAAJUD4UDa2k998.jpg', '0f10e8557d73e4ba87d9dbc9c4f25c88', '/pages/scene/test2/test2', 2, 0, 'slider3.jpg');
INSERT INTO `mini_slider` VALUES ('302b22c2f1979c04743bc3ef338ba7bb', 'http://192.168.156.128//group1/M00/00/05/wKicgF7HdtiADTPjAAERJUqHdzI510.jpg', 'b073b43d0dd5331c68d86897b94a165f', NULL, NULL, 0, 'd.jpg');
INSERT INTO `mini_slider` VALUES ('36e032cb296ecd762269dd71d0dea832', 'http://192.168.156.128//group1/M00/00/05/wKicgF7HdAWAPdfdAAfEtqyGnBM159.jpg', 'a2ede76a65391a938225d81a6e7f481b', NULL, NULL, 0, 'b.jpg');
INSERT INTO `mini_slider` VALUES ('74c6c84feee65eeadc12feba985c81f1', 'http://192.168.156.128//group1/M00/00/05/wKicgF7HRfOANAnpAACvuPGMNGE100.jpg', 'ddcf3f3945a2e12b15a07c1b33d44b93', NULL, NULL, 0, 'timg (1).jpg');
INSERT INTO `mini_slider` VALUES ('9382e0080f55170e8e3f9baefbb28f6c', 'http://192.168.156.128//group1/M00/00/05/wKicgF7F8haADYcwAAAk82vp4Jw622.jpg', 'c698c33e2978c42fb7864c9d03bedef9', NULL, NULL, 0, 'ef0d1a42c874dfdeb43ad66b41d273b1.jpg.jpg');
INSERT INTO `mini_slider` VALUES ('93fc5cbca0454a3cc706cd9af2e05f47', 'http://192.168.156.128//group1/M00/00/04/wKicgF7EpvOAXYwjAAR_RmKZK60986.jpg', '41794e46daec0c3eb76fd4ff3481c9e9', '/pages/scene/test3/test3', 3, 1, 'slider2.jpg');
INSERT INTO `mini_slider` VALUES ('a33aaa7e29cedcab1b91cbd5f09b3a45', 'http://192.168.156.128//group1/M00/00/04/wKicgF7Ep5SAEbZ_AAGwPW2Jtks876.jpg', 'fe356a158b7dba4690ef324f7c72e919', '/pages/scene/test4/test4', 4, 1, 'slider4.jpg');
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
INSERT INTO `user` VALUES ('6ad106db64d3bcb88d611b5ce0416fe8', 'admin2', '111111', 1, '', 1, '', NULL);
INSERT INTO `user` VALUES ('b', 'manager', '123456', 2, NULL, 1, '卓行科技', NULL);
INSERT INTO `user` VALUES ('c', 'manager2', '123', 2, NULL, 1, '卓行科技', NULL);
INSERT INTO `user` VALUES ('34ad9662cfcaee3e4c15d9e0f5e7dbd1', '123', '123456', 3, '是是', 1, '是s', '是');
INSERT INTO `user` VALUES ('as', 'bbbbb', '123456', 3, NULL, 1, NULL, NULL);
INSERT INTO `user` VALUES ('d', 'client1', '123456', 3, NULL, 1, NULL, NULL);
INSERT INTO `user` VALUES ('143aa88c11ab7f49b6e073cde4a418bb', 'client9', '123456', 3, '', 1, '', NULL);

-- ----------------------------
-- View structure for nigger
-- ----------------------------
DROP VIEW IF EXISTS `nigger`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `nigger` AS select `mini_scene`.`id` AS `id`,`mini_scene`.`name` AS `name`,`mini_scene`.`slogan` AS `slogan`,`mini_scene`.`username` AS `username`,`mini_scene`.`location` AS `location` from `mini_scene` order by (convert(`mini_scene`.`name` using gbk) collate gbk_chinese_ci);

SET FOREIGN_KEY_CHECKS = 1;
