/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50552
Source Host           : localhost:3306
Source Database       : api-gateway

Target Server Type    : MYSQL
Target Server Version : 50552
File Encoding         : 65001

Date: 2018-11-16 14:55:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for am_api
-- ----------------------------
DROP TABLE IF EXISTS `am_api`;
CREATE TABLE `am_api` (
  `api_id` int(11) NOT NULL AUTO_INCREMENT,
  `api_name` varchar(64) DEFAULT NULL,
  `api_version` varchar(32) DEFAULT NULL,
  `api_context` varchar(128) DEFAULT NULL,
  `context` varchar(64) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `icon` varchar(128) DEFAULT NULL,
  `endpoint` varchar(32) DEFAULT NULL,
  `uri` varchar(128) DEFAULT NULL,
  `method` char(6) DEFAULT NULL,
  `protocol` varchar(8) DEFAULT NULL,
  `content_type` varchar(255) DEFAULT NULL,
  `params` varchar(128) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `api_consumer` varchar(32) DEFAULT NULL,
  `api_provider` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`api_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of am_api
-- ----------------------------
INSERT INTO `am_api` VALUES ('1', '加法运算', null, null, '/api/v1', null, null, 'http://localhost:8080/', '/add?x=23&y=88', 'get', null, 'application/json', null, null, null, null, null);

-- ----------------------------
-- Table structure for application
-- ----------------------------
DROP TABLE IF EXISTS `application`;
CREATE TABLE `application` (
  `application_id` int(11) NOT NULL AUTO_INCREMENT,
  `application_name` varchar(32) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of application
-- ----------------------------
INSERT INTO `application` VALUES ('19', null, null);
INSERT INTO `application` VALUES ('20', null, null);
INSERT INTO `application` VALUES ('21', null, null);
INSERT INTO `application` VALUES ('22', null, null);
INSERT INTO `application` VALUES ('23', null, null);
INSERT INTO `application` VALUES ('24', null, null);
INSERT INTO `application` VALUES ('25', null, null);
INSERT INTO `application` VALUES ('26', null, null);
INSERT INTO `application` VALUES ('27', null, null);
INSERT INTO `application` VALUES ('28', null, null);

-- ----------------------------
-- Table structure for oauth_access_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token` (
  `token_id` int(11) NOT NULL AUTO_INCREMENT,
  `token` varchar(64) DEFAULT NULL,
  `authentication_id` varchar(250) NOT NULL,
  `username` varchar(256) DEFAULT NULL,
  `client_id` varchar(32) DEFAULT NULL,
  `authentication` blob,
  `refresh_token` varchar(256) DEFAULT NULL,
  `valid` tinyint(1) DEFAULT NULL,
  `expired_timestamp` bigint(20) DEFAULT NULL,
  `create_timestamp` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`token_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of oauth_access_token
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(32) NOT NULL,
  `client_secret` varchar(32) DEFAULT NULL,
  `username` varchar(16) DEFAULT NULL,
  `scope` varchar(64) DEFAULT NULL,
  `authorized_grant_types` varchar(64) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `auto_approve` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of oauth_refresh_token
-- ----------------------------
