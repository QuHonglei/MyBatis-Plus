/*
 Navicat Premium Data Transfer

 Source Server         : 本地数据库
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : mybatis_plus

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 31/12/2021 13:34:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_user_pojo
-- ----------------------------
DROP TABLE IF EXISTS `t_user_pojo`;
CREATE TABLE `t_user_pojo`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `age` int NULL DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `version` int NULL DEFAULT 1 COMMENT '版本号',
  `deleted` int(1) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '逻辑删除',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1473902116184969223 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_pojo
-- ----------------------------
INSERT INTO `t_user_pojo` VALUES (1, 'itheibai02', 18, '123456789@qq.com', 2, 1, NULL, '2021-12-23 17:03:10');
INSERT INTO `t_user_pojo` VALUES (2, 'Jack', 20, 'test2@baomidou.com', 1, 0, NULL, NULL);
INSERT INTO `t_user_pojo` VALUES (3, 'Tom', 28, 'test3@baomidou.com', 1, 0, NULL, NULL);
INSERT INTO `t_user_pojo` VALUES (4, 'Sandy', 21, 'test4@baomidou.com', 1, 0, NULL, NULL);
INSERT INTO `t_user_pojo` VALUES (5, 'itheibai', 19, 'test5@baomidou.com', 1, 0, '2021-12-23 15:18:10', '2021-12-23 15:39:31');

SET FOREIGN_KEY_CHECKS = 1;
