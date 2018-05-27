DROP DATABASE IF EXISTS `garages`;
CREATE DATABASE garages;
USE garages;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for carport
-- ----------------------------
DROP TABLE IF EXISTS `carport`;
CREATE TABLE `carport` (
  `port_id` smallint(5) UNSIGNED NOT NULL COMMENT '车位号',
  `car_status` smallint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '车位状态',
	`car_height` smallint(2) UNSIGNED NOT NULL DEFAULT 3 COMMENT '车位高度',
	`create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`port_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of carport
-- ----------------------------
INSERT INTO `carport`(port_id,car_status,car_height) VALUES (1, 1, 5);
INSERT INTO `carport`(port_id,car_height) VALUES (2, 4);
INSERT INTO `carport` (port_id) VALUES (3);

-- ----------------------------
-- Table structure for records
-- ----------------------------
DROP TABLE IF EXISTS `records`;
CREATE TABLE `records` (
	`record_id` smallint(5) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '记录ID',
	`record_date` date NOT NULL COMMENT '记录时间',
	`port_id` smallint(5) UNSIGNED NOT NULL COMMENT '车位号',
	FOREIGN KEY (`port_id`) references `carport`(port_id),
	PRIMARY KEY (`record_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of records
-- ----------------------------
INSERT INTO `records`(record_date,port_id) VALUES ('2018-05-16', 2);
INSERT INTO `records`(record_date,port_id) VALUES ('2018-05-16', 1);
INSERT INTO `records`(record_date,port_id) VALUES ('2018-05-16', 1);
INSERT INTO `records`(record_date,port_id) VALUES ('2018-05-16', 1);
INSERT INTO `records`(record_date,port_id) VALUES ('2018-05-16', 3);
INSERT INTO `records`(record_date,port_id) VALUES ('2018-05-16', 2);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
	`user_id` smallint(3) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户ID',
	`username` varchar(11) NOT NULL COMMENT '用户名',
	`password` varchar(22) NOT NULL COMMENT '用户密码',
	`update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user`(username,password) VALUES ('admin', 'adminS809');
INSERT INTO `user`(username,password) VALUES ('nmid', 'nmidS809');
INSERT INTO `user`(username,password) VALUES ('root', 'root');