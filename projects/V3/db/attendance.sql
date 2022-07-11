/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : attendance

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 10/07/2022 15:29:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_clock
-- ----------------------------
DROP TABLE IF EXISTS `t_clock`;
CREATE TABLE `t_clock`  (
  `clockid` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `start_time` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `end_time` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `start_status` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `end_status` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `time` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`clockid`) USING BTREE,
  INDEX `userid`(`userid`) USING BTREE,
  CONSTRAINT `t_clock_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `t_user` (`userid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_clock
-- ----------------------------
INSERT INTO `t_clock` VALUES (1, 202103, 'Mark', '2021-05-12 08:00:00', '2021-05-12 17:01:00', '正常', '正常', '2021-05-12');
INSERT INTO `t_clock` VALUES (2, 202103, 'Mark', '2021-05-13 08:55:00', '2021-05-13 17:08:00', '正常', '正常', '2021-05-13');
INSERT INTO `t_clock` VALUES (3, 202103, 'Mark', '2021-05-14 08:50:00', '2021-05-14 17:10:00', '正常', '正常', '2021-05-14');
INSERT INTO `t_clock` VALUES (4, 202103, 'Mark', '2021-05-15 08:45:00', '2021-05-15 17:20:00', '正常', '正常', '2021-05-15');
INSERT INTO `t_clock` VALUES (5, 202104, 'Amber', '2021-05-15 08:45:00', '2021-05-15 17:20:00', '正常', '正常', '2021-05-15');
INSERT INTO `t_clock` VALUES (6, 202104, 'Amber', '2021-05-12 08:00:00', '2021-05-12 17:01:00', '正常', '正常', '2021-05-12');
INSERT INTO `t_clock` VALUES (7, 202104, 'Amber', '2021-05-13 09:02:00', '2021-05-13 16:08:00', '迟到', '早退', '2021-05-13');
INSERT INTO `t_clock` VALUES (8, 202104, 'Amber', '2021-05-14 08:50:00', '2021-05-14 17:50:00', '正常', '正常', '2021-05-14');
INSERT INTO `t_clock` VALUES (9, 202104, 'Amber', '2021-05-15 09:05:00', '2021-05-15 17:20:00', '迟到', '正常', '2021-05-15');
INSERT INTO `t_clock` VALUES (10, 202105, 'Ava', '2021-05-15 08:45:00', '2021-05-15 16:20:00', '正常', '早退', '2021-05-15');
INSERT INTO `t_clock` VALUES (11, 202105, 'Ava', '2021-05-12 08:00:00', '2021-05-12 17:01:00', '正常', '正常', '2021-05-12');
INSERT INTO `t_clock` VALUES (12, 202105, 'Ava', '2021-05-13 09:00:00', '2021-05-13 16:08:00', '正常', '早退', '2021-05-13');
INSERT INTO `t_clock` VALUES (13, 202105, 'Ava', '2021-05-14 08:50:00', '2021-05-14 17:50:00', '正常', '正常', '2021-05-14');
INSERT INTO `t_clock` VALUES (14, 202105, 'Ava', '2021-05-15 09:05:00', '2021-05-15 17:20:00', '迟到', '正常', '2021-05-15');
INSERT INTO `t_clock` VALUES (15, 202109, 'Edie', '2021-05-15 08:45:00', '2021-05-15 17:20:00', '正常', '正常', '2021-05-15');
INSERT INTO `t_clock` VALUES (16, 202109, 'Edie', '2021-05-12 08:00:00', '2021-05-12 17:01:00', '正常', '正常', '2021-05-12');
INSERT INTO `t_clock` VALUES (17, 202109, 'Edie', '2021-05-13 09:02:00', '2021-05-13 16:08:00', '迟到', '早退', '2021-05-13');
INSERT INTO `t_clock` VALUES (18, 202109, 'Edie', '2021-05-14 08:50:00', '2021-05-14 17:50:00', '正常', '正常', '2021-05-14');
INSERT INTO `t_clock` VALUES (19, 202109, 'Edie', '2021-05-15 09:05:00', '2021-05-15 17:20:00', '迟到', '正常', '2021-05-15');

-- ----------------------------
-- Table structure for t_leave
-- ----------------------------
DROP TABLE IF EXISTS `t_leave`;
CREATE TABLE `t_leave`  (
  `leaveid` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `leave_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `leave_content` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `start_time` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `end_time` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `approval_status` int(11) NULL DEFAULT 0,
  `approval_result` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`leaveid`) USING BTREE,
  INDEX `userid`(`userid`) USING BTREE,
  CONSTRAINT `t_leave_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `t_user` (`userid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_leave
-- ----------------------------
INSERT INTO `t_leave` VALUES (1, 202104, 'Amber', '事假', '私事', '2021-05-11 17:05:22', '2021-05-13 17:55:22', 1, '同意');
INSERT INTO `t_leave` VALUES (2, 202107, 'Bree', '婚假', '感冒', '2021-05-11 17:25:22', '2021-05-19 17:35:22', 1, '同意');
INSERT INTO `t_leave` VALUES (3, 202108, 'Dara', '病假', '发烧', '2021-05-11 17:55:22', '2021-05-13 17:55:22', 1, '同意');
INSERT INTO `t_leave` VALUES (4, 202109, 'Edie', '事假', '私事', '2021-05-11 10:55:22', '2021-05-12 17:55:22', 1, '同意');
INSERT INTO `t_leave` VALUES (5, 202109, 'Edie', '事假', '私事', '2021-05-11 10:55:22', '2021-05-20 17:55:22', 1, '拒绝');
INSERT INTO `t_leave` VALUES (6, 202107, 'Bree', '事假', '私事', '2021-05-11 10:55:22', '2021-05-12 17:55:22', 1, '拒绝');
INSERT INTO `t_leave` VALUES (7, 202103, 'Mark', '事假', '私事', '2021-05-12 17:55:22', '2021-05-13 17:55:22', 0, NULL);
INSERT INTO `t_leave` VALUES (8, 202106, 'Bella', '丧假', '私事', '2021-05-13 17:55:22', '2021-05-14 17:55:22', 0, NULL);
INSERT INTO `t_leave` VALUES (9, 202107, 'Bree', '事假', '私事', '2021-05-15 17:55:22', '2021-05-7 17:55:22', 0, NULL);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `gender` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `position` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `tele` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `email` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`userid`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 202113 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (202102, 'Nina', 'nina', '女', '人事经理', '15878947895', 'nina@lan.com');
INSERT INTO `t_user` VALUES (202103, 'Mark', 'mark', '男', '员工', '15884548523', 'mark@lan.com');
INSERT INTO `t_user` VALUES (202104, 'Amber', 'amber', '男', '员工', '15878978463', 'amber@lan.com');
INSERT INTO `t_user` VALUES (202105, 'Ava', 'ava', '女', '员工', '15878948523', 'ava@lan.com');
INSERT INTO `t_user` VALUES (202106, 'Bella', 'bella', '女', '员工', '15878947526', 'bella@lan.com');
INSERT INTO `t_user` VALUES (202107, 'Bree', 'bree', '男', '员工', '15885468523', 'bree@lan.com');
INSERT INTO `t_user` VALUES (202108, 'Dara', 'dara', '男', '员工', '15878948523', 'dara@lan.com');
INSERT INTO `t_user` VALUES (202109, 'Edie', 'edie', '男', '员工', '15456348523', 'edie@lan.com');
INSERT INTO `t_user` VALUES (202110, 'Ella', 'ella', '女', '员工', '15878948523', 'ella@lan.com');
INSERT INTO `t_user` VALUES (202111, 'Gail', 'gail', '男', '员工', '15878948523', 'gail@lan.com');
INSERT INTO `t_user` VALUES (202112, 'Irene', 'irene', '女', '员工', '15875268523', 'irene@lan.com');

SET FOREIGN_KEY_CHECKS = 1;
