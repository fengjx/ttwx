/*
Navicat MySQL Data Transfer

Source Server         : rds-dev
Source Server Version : 50616
Source Host           : fengjx2015.mysql.rds.aliyuncs.com:3306
Source Database       : ttwx-dev

Target Server Type    : MYSQL
Target Server Version : 50616
File Encoding         : 65001

Date: 2015-12-28 14:12:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for portal_guestbook
-- ----------------------------
DROP TABLE IF EXISTS `portal_guestbook`;
CREATE TABLE `portal_guestbook` (
  `id` varchar(32) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `in_time` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `qq` varchar(255) DEFAULT NULL,
  `msg` longtext,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of portal_guestbook
-- ----------------------------
INSERT INTO `portal_guestbook` VALUES ('4028c68149803b640149803bc05f0000', 'xd-fjx@qq.com', '2014-11-05 21:53:59', 'fengjx', null, '', 'test content...........');
INSERT INTO `portal_guestbook` VALUES ('4028c681498036fe014980383f770001', 'xd-fjx@qq.com', '2014-11-05 21:50:10', 'fengjx', null, '', 'test content...........');
INSERT INTO `portal_guestbook` VALUES ('78d383ac5a9611e5b872b8975ab8baa1', 'sas', '2015-09-14 12:10:02', 'aaaaasa', null, 'asasa', 'aaaaaaaa');

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` varchar(32) NOT NULL,
  `dict_desc` varchar(255) DEFAULT NULL,
  `dict_name` varchar(255) DEFAULT NULL,
  `dict_value` varchar(255) DEFAULT NULL,
  `group_code` varchar(255) DEFAULT NULL,
  `in_time` varchar(255) DEFAULT NULL,
  `is_valid` tinyint(4) DEFAULT '0',
  `order_no` tinyint(255) DEFAULT '0' COMMENT '排序字段',
  `parent_id` varchar(255) DEFAULT NULL,
  `group_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('1', '消息请求类型', '文本消息', 'text', 'req_type', '2014-2-26 17:47:18', '1', '1', null, null);
INSERT INTO `sys_dict` VALUES ('3', '消息请求类型', '图片消息', 'image', 'req_type', '2014-2-26 17:50:32', '1', '3', null, null);
INSERT INTO `sys_dict` VALUES ('4', '消息请求类型', '语音消息', 'voice', 'req_type', '2014-2-26 17:51:41', '1', '4', null, null);
INSERT INTO `sys_dict` VALUES ('5', '消息请求类型', '视频消息', 'video', 'req_type', '2014-2-26 17:52:15', '1', '5', null, null);
INSERT INTO `sys_dict` VALUES ('6', '消息请求类型', '地理位置消息', 'location', 'req_type', '2014-2-26 17:53:02', '1', '6', null, null);
INSERT INTO `sys_dict` VALUES ('7', '消息请求类型', '链接消息', 'link', 'req_type', '2014-2-26 17:53:44', '1', '7', null, null);
INSERT INTO `sys_dict` VALUES ('8', '消息请求类型', '事件消息', 'event', 'req_type', '2014-2-26 17:54:48', '1', '8', null, null);
INSERT INTO `sys_dict` VALUES ('12', '扩展应用', '本地api接口', 'api', 'app_type', '2014-3-6 16:49:03', '1', '1', null, null);
INSERT INTO `sys_dict` VALUES ('13', '扩展应用', 'web类型', 'web', 'app_type', '2014-3-6 16:49:35', '1', '2', null, null);
INSERT INTO `sys_dict` VALUES ('14', '动作响应数据源(素材、接口)', '接口应用', 'api', 'action_type', '2015-09-12 17:41:11', '1', '1', null, '');
INSERT INTO `sys_dict` VALUES ('15', '动作响应数据源(素材、接口)', '素材', 'material', 'action_type', '2014-3-6 17:01:14', '1', '1', null, null);
INSERT INTO `sys_dict` VALUES ('16', '消息回复类型', '文字消息', 'text', 'resp_type', '2014-3-20 15:20:59', '1', '1', null, null);
INSERT INTO `sys_dict` VALUES ('17', '消息回复类型', '接口数据', 'busiapp_api', 'resp_type', '2014-3-20 15:22:04', '1', '2', null, null);
INSERT INTO `sys_dict` VALUES ('18', '消息回复类型', '音乐消息', 'music', 'resp_type', '2014-3-20 15:28:16', '1', '3', null, null);
INSERT INTO `sys_dict` VALUES ('19', '消息回复消息', '图文消息', 'news', 'resp_type', '2014-3-20 15:29:20', '1', '4', null, null);
INSERT INTO `sys_dict` VALUES ('20', '消息回复类型', '语音消息', 'voice', 'resp_type', '2014-3-20 15:29:50', '1', '5', null, null);
INSERT INTO `sys_dict` VALUES ('21', '消息回复类型', '图片消息', 'image', 'resp_type', '2014-3-20 15:30:32', '1', '6', null, null);
INSERT INTO `sys_dict` VALUES ('22', '消息回复类型', '视频消息', 'video', 'resp_type', '2014-3-20 15:31:09', '1', '7', null, null);
INSERT INTO `sys_dict` VALUES ('23', '事件类型', '用户关注', 'subscribe', 'event_type', '2014-3-20 17:17:32', '1', '1', null, null);
INSERT INTO `sys_dict` VALUES ('25', '事件类型', '二维码扫描（已关注用户）', 'SCAN', 'event_type', '2014-3-20 17:18:55', '1', '3', null, null);
INSERT INTO `sys_dict` VALUES ('24', '事件类型', '取消关注', 'unsubscribe', 'event_type', '2014-3-20 17:18:17', '1', '2', null, null);
INSERT INTO `sys_dict` VALUES ('26', '事件类型', '上报地理位置', 'LOCATION', 'event_type', '2014-3-20 17:19:25', '1', '4', null, null);
INSERT INTO `sys_dict` VALUES ('27', '事件类型', '菜单点击', 'CLICK', 'event_type', '2014-3-20 17:20:16', '1', '5', null, null);
INSERT INTO `sys_dict` VALUES ('28', '事件类型', '菜单链接', 'VIEW', 'event_type', '2014-3-20 17:21:11', '1', '6', null, null);
INSERT INTO `sys_dict` VALUES ('29', '扩展应用', 'restful远程接口', 'restful', 'app_type', '2014-11-08 17:21:11', '1', '3', null, null);
INSERT INTO `sys_dict` VALUES ('f3d77b97504c11e588a6b8975ab8baa1', 'test dict test1 update', 'test1', 't1', 'test', '2015-09-07 10:55:04', '0', '1', null, 'test group');
INSERT INTO `sys_dict` VALUES ('bcf40e9e508d11e588a6b8975ab8baa1', 'test dict test2', 'test2', 't2', 'test', '2015-09-07 10:54:59', '0', '2', null, 'test group');
INSERT INTO `sys_dict` VALUES ('79b8113f544911e58fa8b8975ab8baa1', '', '是', '1', 'yesNo', '2015-09-06 13:34:02', '1', '3', null, '是或否');
INSERT INTO `sys_dict` VALUES ('908f6350544911e58fa8b8975ab8baa1', '否', '否', '0', 'yesNo', '2015-09-06 13:33:52', '1', '2', null, '是或否');
INSERT INTO `sys_dict` VALUES ('3826d2e1576511e5a12eb8975ab8baa1', '', '完全匹配', '1', 'keywordFuzzy', '2015-09-10 10:40:28', '1', '1', null, '关键字匹配类型');
INSERT INTO `sys_dict` VALUES ('533231b2576511e5a12eb8975ab8baa1', '', '包含', '2', 'keywordFuzzy', '2015-09-10 10:40:40', '1', '2', null, '关键字匹配类型');
INSERT INTO `sys_dict` VALUES ('6146ee33576511e5a12eb8975ab8baa1', '', '关键字开头', '3', 'keywordFuzzy', '2015-09-10 10:41:03', '1', '3', null, '关键字匹配类型');
INSERT INTO `sys_dict` VALUES ('6a14a754576511e5a12eb8975ab8baa1', '', '关键字结尾', '4', 'keywordFuzzy', '2015-09-10 10:41:18', '1', '4', null, '关键字匹配类型');
INSERT INTO `sys_dict` VALUES ('fe2566aead2411e5b3e0b8975ab8baa1', '1', '111', '1', '1', '2015-12-28 13:36:49.842', '0', '1', null, '1');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` varchar(32) NOT NULL COMMENT '编号',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父级编号',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `url` varchar(2000) DEFAULT NULL COMMENT '链接',
  `icon` varchar(100) DEFAULT NULL COMMENT '图标',
  `permission` varchar(200) DEFAULT NULL COMMENT '权限标识',
  `level` tinyint(2) DEFAULT NULL COMMENT '菜单级别',
  `is_show` char(1) NOT NULL COMMENT '是否在菜单中显示',
  `order_no` tinyint(10) NOT NULL COMMENT '排序',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `is_valid` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sys_menu_parent_id` (`parent_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('50db352e774511e58d9e00ffac6fdde1', null, '平台管理', '/admin/wechat', null, null, '1', '1', '2', '2015-10-30 00:13:20', null, null);
INSERT INTO `sys_menu` VALUES ('dd6e24cf774511e592a600ffac6fdde1', '9c352aa77e5811e59ba400ffac6fdde1', '接口管理', '/admin/sys/ext', null, 'sys_menu_view', '2', '1', '2', '2015-10-30 00:21:02', null, null);
INSERT INTO `sys_menu` VALUES ('9476957f7d8511e5929900ffac6fdde1', null, '门户', '/admin/portal', null, null, '1', '1', '5', '2015-10-30 00:14:02', null, null);
INSERT INTO `sys_menu` VALUES ('a7e29a347e4f11e59ba400ffac6fdde1', '9476957f7d8511e5929900ffac6fdde1', '在线留言', '/admin/portal/guestbook', null, null, '2', '1', '4', '2015-10-30 00:25:16', null, null);
INSERT INTO `sys_menu` VALUES ('5c33d2357e5811e59ba400ffac6fdde1', null, '天天小店', '/admin/shop', null, null, '1', '1', '10', '2015-10-30 00:16:30', null, null);
INSERT INTO `sys_menu` VALUES ('876ede467e5811e59ba400ffac6fdde1', null, '微社区', '/admin/wsq', null, null, '1', '1', '20', '2015-10-30 00:17:20', null, null);
INSERT INTO `sys_menu` VALUES ('9c352aa77e5811e59ba400ffac6fdde1', null, '系统管理', '/admin/sys', null, null, '1', '1', '30', '2015-10-30 00:17:55', null, null);
INSERT INTO `sys_menu` VALUES ('cb9e9e287e5811e59ba400ffac6fdde1', '9c352aa77e5811e59ba400ffac6fdde1', '字典管理', '/admin/dict', null, null, '2', '1', '10', '2015-10-30 00:21:17', null, null);
INSERT INTO `sys_menu` VALUES ('012ccad97e5911e59ba400ffac6fdde1', '9c352aa77e5811e59ba400ffac6fdde1', '菜单管理', null, null, null, '2', '1', '30', '2015-10-30 00:21:28', null, null);
INSERT INTO `sys_menu` VALUES ('3ac5aa5a7e5911e59ba400ffac6fdde1', '012ccad97e5911e59ba400ffac6fdde1', '查看', '/admin/menu', null, null, '3', '1', '10', '2015-10-30 00:22:21', null, null);
INSERT INTO `sys_menu` VALUES ('52d0c17b7e5911e59ba400ffac6fdde1', '012ccad97e5911e59ba400ffac6fdde1', '编辑', '/admin/menu/form', null, null, '3', '0', '20', '2015-10-30 00:23:01', null, null);
INSERT INTO `sys_menu` VALUES ('b905c3ac7e5911e59ba400ffac6fdde1', '50db352e774511e58d9e00ffac6fdde1', '权限配置', '/admin/wechat/setting', null, null, '2', '1', '10', '2015-10-30 00:25:52', null, null);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(64) NOT NULL COMMENT '编号',
  `name` varchar(100) NOT NULL COMMENT '角色名称',
  `role_code` varchar(255) NOT NULL COMMENT '角色标识',
  `is_sys` varchar(64) DEFAULT NULL COMMENT '是否系统角色（超级管理员才能修改）',
  `is_valid` varchar(64) DEFAULT NULL COMMENT '是否可用',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_role_code` (`role_code`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '系统管理员', 'dept', '1', '1', '2015-11-13 01:04:09', null);
INSERT INTO `sys_role` VALUES ('2', '公司管理员', 'hr', '0', '1', '2015-11-14 12:04:32', null);
INSERT INTO `sys_role` VALUES ('3', '本公司管理员', 'a', '0', '1', '2015-11-14 12:04:46', null);
INSERT INTO `sys_role` VALUES ('9af033157fb111e59abe00ffac6fdde1', '平台管理', 'aaaaa', '0', '1', '2015-10-31 17:27:29', null);
INSERT INTO `sys_role` VALUES ('0717d8107ffb11e5a85c00ffac6fdde1', '平台管理2', 'asasa1212121', '0', '1', '2015-11-01 02:41:55', null);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` varchar(64) NOT NULL COMMENT '角色编号',
  `menu_id` varchar(64) NOT NULL COMMENT '菜单编号',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='角色-菜单';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('0717d8107ffb11e5a85c00ffac6fdde1', '012ccad97e5911e59ba400ffac6fdde1');
INSERT INTO `sys_role_menu` VALUES ('0717d8107ffb11e5a85c00ffac6fdde1', '3ac5aa5a7e5911e59ba400ffac6fdde1');
INSERT INTO `sys_role_menu` VALUES ('0717d8107ffb11e5a85c00ffac6fdde1', '50db352e774511e58d9e00ffac6fdde1');
INSERT INTO `sys_role_menu` VALUES ('0717d8107ffb11e5a85c00ffac6fdde1', '52d0c17b7e5911e59ba400ffac6fdde1');
INSERT INTO `sys_role_menu` VALUES ('0717d8107ffb11e5a85c00ffac6fdde1', '9476957f7d8511e5929900ffac6fdde1');
INSERT INTO `sys_role_menu` VALUES ('0717d8107ffb11e5a85c00ffac6fdde1', '9c352aa77e5811e59ba400ffac6fdde1');
INSERT INTO `sys_role_menu` VALUES ('0717d8107ffb11e5a85c00ffac6fdde1', '9d0290977f2011e5a83bce65e765ff8d');
INSERT INTO `sys_role_menu` VALUES ('0717d8107ffb11e5a85c00ffac6fdde1', 'a7e29a347e4f11e59ba400ffac6fdde1');
INSERT INTO `sys_role_menu` VALUES ('0717d8107ffb11e5a85c00ffac6fdde1', 'b905c3ac7e5911e59ba400ffac6fdde1');
INSERT INTO `sys_role_menu` VALUES ('0717d8107ffb11e5a85c00ffac6fdde1', 'cb9e9e287e5811e59ba400ffac6fdde1');
INSERT INTO `sys_role_menu` VALUES ('0717d8107ffb11e5a85c00ffac6fdde1', 'dd6e24cf774511e592a600ffac6fdde1');
INSERT INTO `sys_role_menu` VALUES ('1', '012ccad97e5911e59ba400ffac6fdde1');
INSERT INTO `sys_role_menu` VALUES ('1', '3ac5aa5a7e5911e59ba400ffac6fdde1');
INSERT INTO `sys_role_menu` VALUES ('1', '52d0c17b7e5911e59ba400ffac6fdde1');
INSERT INTO `sys_role_menu` VALUES ('1', '9c352aa77e5811e59ba400ffac6fdde1');
INSERT INTO `sys_role_menu` VALUES ('1', 'cb9e9e287e5811e59ba400ffac6fdde1');
INSERT INTO `sys_role_menu` VALUES ('1', 'dd6e24cf774511e592a600ffac6fdde1');
INSERT INTO `sys_role_menu` VALUES ('9af033157fb111e59abe00ffac6fdde1', '50db352e774511e58d9e00ffac6fdde1');
INSERT INTO `sys_role_menu` VALUES ('9af033157fb111e59abe00ffac6fdde1', 'b905c3ac7e5911e59ba400ffac6fdde1');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(32) NOT NULL,
  `email` varchar(255) NOT NULL,
  `in_time` datetime DEFAULT NULL,
  `is_valid` varchar(255) DEFAULT NULL,
  `phone_no` varchar(255) DEFAULT NULL,
  `pwd` varchar(255) NOT NULL,
  `score` int(11) NOT NULL DEFAULT '0',
  `username` varchar(50) NOT NULL,
  `valid_uid` varchar(255) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `valid_uid` (`valid_uid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('66f4e0295eaf11e59601b8975ab8baa1', 'xd-fjx@163.com', '2015-09-19 17:18:34', '1', null, '814e3472f179b9f9f36bedb74de4e3c1', '0', 'fjx', '66f99b1a5eaf11e59601b8975ab8baa1', null);
INSERT INTO `sys_user` VALUES ('338eff638b9711e59d4800ffac6fdde1', 'fengjx_mt@qq.com', '2015-11-15 20:48:43', '0', null, '4e91c3cee70bc8a43f8d2d9c7caa2bab', '1000', 'fengjx', '339ce2148b9711e59d4800ffac6fdde1', 'aaaaaaaaaaaaaaaa');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` varchar(64) NOT NULL COMMENT '用户编号',
  `role_id` varchar(64) NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='用户-角色';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('338eff638b9711e59d4800ffac6fdde1', '0717d8107ffb11e5a85c00ffac6fdde1');
INSERT INTO `sys_user_role` VALUES ('66f4e0295eaf11e59601b8975ab8baa1', '5090bf77895f11e5bb3a9aa2c1dd9f75');

-- ----------------------------
-- Table structure for wechat_data_dict
-- ----------------------------
DROP TABLE IF EXISTS `wechat_data_dict`;
CREATE TABLE `wechat_data_dict` (
  `id` varchar(32) NOT NULL,
  `dict_desc` varchar(255) DEFAULT NULL,
  `dict_name` varchar(255) DEFAULT NULL,
  `dict_value` varchar(255) DEFAULT NULL,
  `group_code` varchar(255) DEFAULT NULL,
  `in_time` datetime DEFAULT NULL,
  `is_valid` varchar(255) DEFAULT NULL,
  `order_num` varchar(255) DEFAULT NULL,
  `parent_id` varchar(255) DEFAULT NULL,
  `group_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wechat_data_dict
-- ----------------------------
INSERT INTO `wechat_data_dict` VALUES ('1', '微信文本消息', '文本消息', 'text', 'req_type', '2014-11-22 16:05:41', '1', '1', null, '请求消息类型');
INSERT INTO `wechat_data_dict` VALUES ('3', '微信图片消息', '图片消息', 'image', 'req_type', '2014-11-22 16:06:06', '1', '3', null, '请求消息类型');
INSERT INTO `wechat_data_dict` VALUES ('4', '消息请求类型', '语音消息', 'voice', 'req_type', '2014-11-30 22:33:25', '1', '4', null, '请求消息类型');
INSERT INTO `wechat_data_dict` VALUES ('5', '消息请求类型', '视频消息', 'video', 'req_type', '2014-11-30 22:34:04', '1', '5', null, '请求消息类型');
INSERT INTO `wechat_data_dict` VALUES ('6', '消息请求类型', '地理位置消息', 'location', 'req_type', '2014-11-30 22:36:25', '1', '6', null, '请求消息类型');
INSERT INTO `wechat_data_dict` VALUES ('7', '消息请求类型', '链接消息', 'link', 'req_type', '2014-11-30 22:36:43', '1', '7', null, '消息请求类型');
INSERT INTO `wechat_data_dict` VALUES ('8', '消息请求类型', '事件消息', 'event', 'req_type', '2014-11-30 22:34:35', '1', '8', null, '消息请求类型');
INSERT INTO `wechat_data_dict` VALUES ('12', '扩展应用', '本地api接口', 'api', 'app_type', '2014-11-30 12:25:36', '1', '1', null, '插件类型');
INSERT INTO `wechat_data_dict` VALUES ('13', 'web应用，url带入用户授权认证的用户标识', 'Web APP', 'web', 'app_type', '2014-11-30 12:26:11', '1', '2', null, '插件类型');
INSERT INTO `wechat_data_dict` VALUES ('14', '动作响应数据源(素材、接口)', '插件接口', 'api', 'action_type', '2014-11-30 21:01:00', '1', '2', null, '动作响应类型');
INSERT INTO `wechat_data_dict` VALUES ('15', '动作响应数据源(素材、接口)', '素材', 'material', 'action_type', '2014-11-30 19:35:07', '1', '1', null, '动作响应类型');
INSERT INTO `wechat_data_dict` VALUES ('16', '消息回复类型', '文字消息', 'text', 'resp_type', '2014-11-30 22:36:48', '1', '1', null, '消息回复类型');
INSERT INTO `wechat_data_dict` VALUES ('18', '消息回复类型', '音乐消息', 'music', 'resp_type', '2014-11-30 22:35:27', '1', '3', null, '消息回复类型');
INSERT INTO `wechat_data_dict` VALUES ('19', '消息回复消息', '图文消息', 'news', 'resp_type', '2014-11-30 22:35:34', '1', '4', null, '消息回复类型');
INSERT INTO `wechat_data_dict` VALUES ('20', '消息回复类型', '语音消息', 'voice', 'resp_type', '2014-11-30 22:35:40', '1', '5', null, '消息回复类型');
INSERT INTO `wechat_data_dict` VALUES ('21', '消息回复类型', '图片消息', 'image', 'resp_type', '2014-11-30 22:35:43', '1', '6', null, '消息回复类型');
INSERT INTO `wechat_data_dict` VALUES ('22', '消息回复类型', '视频消息', 'video', 'resp_type', '2014-11-30 22:35:47', '1', '7', null, '消息回复类型');
INSERT INTO `wechat_data_dict` VALUES ('23', '事件类型', '用户关注', 'subscribe', 'event_type', '2014-11-22 00:13:08', '1', '1', null, '事件类型');
INSERT INTO `wechat_data_dict` VALUES ('25', '事件类型', '二维码扫描（已关注用户）', 'SCAN', 'event_type', '2014-11-22 00:13:18', '1', '3', null, '事件类型');
INSERT INTO `wechat_data_dict` VALUES ('24', '事件类型', '取消关注', 'unsubscribe', 'event_type', '2014-11-22 00:13:12', '1', '2', null, '事件类型');
INSERT INTO `wechat_data_dict` VALUES ('26', '事件类型', '上报地理位置', 'LOCATION', 'event_type', '2014-11-22 00:13:22', '1', '4', null, '事件类型');
INSERT INTO `wechat_data_dict` VALUES ('27', '事件类型', '菜单点击', 'CLICK', 'event_type', '2014-11-22 00:13:28', '1', '5', null, '事件类型');
INSERT INTO `wechat_data_dict` VALUES ('28', '用户点击菜单，跳转URL事件', '菜单链接', 'VIEW', 'event_type', '2014-11-22 16:02:28', '1', '6', null, '事件类型');
INSERT INTO `wechat_data_dict` VALUES ('29', '调用restful接口，与其他系统对接，restful接口直接返回微信规范报文', 'restful远程接口', 'restful', 'app_type', '2014-11-30 12:27:17', '1', '3', null, '插件类型');

-- ----------------------------
-- Table structure for wechat_ext_app
-- ----------------------------
DROP TABLE IF EXISTS `wechat_ext_app`;
CREATE TABLE `wechat_ext_app` (
  `id` varchar(32) NOT NULL,
  `app_type` varchar(255) DEFAULT NULL,
  `app_url` varchar(255) DEFAULT NULL,
  `group_id` varchar(255) DEFAULT NULL,
  `in_time` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `order_no` varchar(255) DEFAULT NULL,
  `bean_name` varchar(255) DEFAULT NULL,
  `method_name` varchar(255) DEFAULT NULL,
  `restful_url` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `is_valid` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wechat_ext_app
-- ----------------------------
INSERT INTO `wechat_ext_app` VALUES ('4028c68149ffd2a60149ffe491ab0000', 'api', null, null, '2015-09-22 12:37:53', '天气预报', '1', 'weather', 'weatherInfo', null, '天气预报天气预报', '1');
INSERT INTO `wechat_ext_app` VALUES ('4028c6814a00dad2014a010597060004', 'api', null, null, '2015-09-19 17:30:25', 'a', '4', 'a', 'a', null, 'aaaaaaaaaaa', '1');
INSERT INTO `wechat_ext_app` VALUES ('4028c6814a00dad2014a011b6cd5000d', 'restful', null, null, '2015-09-07 10:31:30', 'restful', '1', null, null, 'http://localhost:8080/ttwx/admin', 'test restful', '1');
INSERT INTO `wechat_ext_app` VALUES ('49965682545911e581dfb8975ab8baa1', 'web', 'http://localhost:8080/ttwx/admin/sys/ext', null, '2015-09-07 10:31:19', 'test web app', '2', null, null, null, 'test web app', '1');
INSERT INTO `wechat_ext_app` VALUES ('79761834550811e5b63eb8975ab8baa1', 'web', 'http://localhost:8080/ttwx/admin/sys/ext', null, '2015-09-07 10:30:59', 'aaaaaa', '2', null, null, null, 'aaaaaaaaaa', '0');
INSERT INTO `wechat_ext_app` VALUES ('fec0d5e38aa711e5ab4400ffac6fdde1', 'api', null, null, '2015-11-14 16:16:24', 'assasasasa', '2', 'ASA', null, null, 'sdasdas', '0');
INSERT INTO `wechat_ext_app` VALUES ('17eb18a78aa811e5ab4400ffac6fdde1', 'api', null, null, '2015-11-14 16:17:06', 'dfgdfgdf', '3', 'dfghh', null, null, 'dfgdfg', '0');

-- ----------------------------
-- Table structure for wechat_ext_app_support_type
-- ----------------------------
DROP TABLE IF EXISTS `wechat_ext_app_support_type`;
CREATE TABLE `wechat_ext_app_support_type` (
  `id` varchar(32) NOT NULL,
  `event_type` varchar(255) DEFAULT NULL,
  `msg_type` varchar(255) DEFAULT NULL,
  `ext_app_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK289AE0DFB93DCFCA` (`ext_app_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wechat_ext_app_support_type
-- ----------------------------
INSERT INTO `wechat_ext_app_support_type` VALUES ('b013abc560e311e59cadb8975ab8baa1', null, 'text', '4028c68149ffd2a60149ffe491ab0000');
INSERT INTO `wechat_ext_app_support_type` VALUES ('4028c6814a00dad2014a011b6cd6000e', null, '', '4028c6814a00dad2014a011b6cd5000d');
INSERT INTO `wechat_ext_app_support_type` VALUES ('0eee2b115eb111e59601b8975ab8baa1', 'CLICK', 'event', '4028c6814a00dad2014a010597060004');
INSERT INTO `wechat_ext_app_support_type` VALUES ('b017ca7660e311e59cadb8975ab8baa1', null, 'location', '4028c68149ffd2a60149ffe491ab0000');
INSERT INTO `wechat_ext_app_support_type` VALUES ('ff931a048aa711e5ab4400ffac6fdde1', 'CLICK', 'event', 'fec0d5e38aa711e5ab4400ffac6fdde1');
INSERT INTO `wechat_ext_app_support_type` VALUES ('ffa0fcb58aa711e5ab4400ffac6fdde1', 'LOCATION', 'event', 'fec0d5e38aa711e5ab4400ffac6fdde1');
INSERT INTO `wechat_ext_app_support_type` VALUES ('ffaf06768aa711e5ab4400ffac6fdde1', 'SCAN', 'event', 'fec0d5e38aa711e5ab4400ffac6fdde1');
INSERT INTO `wechat_ext_app_support_type` VALUES ('183408888aa811e5ab4400ffac6fdde1', 'VIEW', 'event', '17eb18a78aa811e5ab4400ffac6fdde1');
INSERT INTO `wechat_ext_app_support_type` VALUES ('18440e198aa811e5ab4400ffac6fdde1', 'CLICK', 'event', '17eb18a78aa811e5ab4400ffac6fdde1');

-- ----------------------------
-- Table structure for wechat_material
-- ----------------------------
DROP TABLE IF EXISTS `wechat_material`;
CREATE TABLE `wechat_material` (
  `id` varchar(32) NOT NULL,
  `in_time` datetime DEFAULT NULL,
  `msg_type` varchar(255) DEFAULT NULL,
  `xml_data` longtext NOT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK717D3620D3E19CF1` (`user_id`),
  KEY `FK717D3620EAEA3B74` (`user_id`),
  KEY `FK717D362078D1D69A` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wechat_menu
-- ----------------------------
DROP TABLE IF EXISTS `wechat_menu`;
CREATE TABLE `wechat_menu` (
  `id` varchar(32) NOT NULL,
  `in_time` datetime DEFAULT NULL,
  `menu_key` varchar(255) DEFAULT NULL,
  `menu_level` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `parent_id` varchar(32) DEFAULT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  `order_no` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `menu_key` (`menu_key`),
  KEY `FKF7D64AD8D3E19CF1` (`user_id`),
  KEY `FKF7D64AD8CF82234E` (`parent_id`),
  KEY `FKF7D64AD8EAEA3B74` (`user_id`),
  KEY `FKF7D64AD878D1D69A` (`user_id`),
  KEY `FKF7D64AD86AC1EE68` (`parent_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wechat_msg_template
-- ----------------------------
DROP TABLE IF EXISTS `wechat_msg_template`;
CREATE TABLE `wechat_msg_template` (
  `id` varchar(32) NOT NULL,
  `description` longtext,
  `in_time` datetime DEFAULT NULL,
  `msg_content` longtext,
  `msg_key` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wechat_msg_template
-- ----------------------------
INSERT INTO `wechat_msg_template` VALUES ('1', '接口验证成功', '2014-11-08 00:31:28', '<xml>\r\n<ToUserName><![CDATA[{#ToUserName#}]]></ToUserName>\r\n<FromUserName><![CDATA[{#FromUserName#}]]></FromUserName>\r\n<CreateTime>{#CreateTime#}</CreateTime>\r\n<MsgType><![CDATA[text]]></MsgType>\r\n<Content><![CDATA[验证成功，您的公众账号已成功对接。]]></Content>\r\n</xml>', 'api_valid_success');
INSERT INTO `wechat_msg_template` VALUES ('2', '接口验证失败', '2014-11-08 00:47:47', '<xml>\r\n<ToUserName><![CDATA[{#ToUserName#}]]></ToUserName>\r\n<FromUserName><![CDATA[{#FromUserName#}]]></FromUserName>\r\n<CreateTime>{#CreateTime#}</CreateTime>\r\n<MsgType><![CDATA[text]]></MsgType>\r\n<Content><![CDATA[发送的消息无效，接口未激活！]]></Content>\r\n</xml>', 'api_valid_fail');

-- ----------------------------
-- Table structure for wechat_public_account
-- ----------------------------
DROP TABLE IF EXISTS `wechat_public_account`;
CREATE TABLE `wechat_public_account` (
  `id` varchar(32) NOT NULL,
  `account_id` varchar(255) DEFAULT NULL,
  `app_id` varchar(255) DEFAULT NULL,
  `app_secret` varchar(255) DEFAULT NULL,
  `in_time` datetime DEFAULT NULL,
  `pwd` varchar(255) DEFAULT NULL,
  `ticket` varchar(255) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `valid_code` varchar(255) DEFAULT NULL,
  `valid_state` varchar(255) DEFAULT NULL,
  `sys_user_id` varchar(32) DEFAULT NULL,
  `encodingAESKey` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ticket` (`ticket`),
  KEY `FK255DDC9054A1E57F` (`sys_user_id`),
  KEY `FK255DDC906BAA8402` (`sys_user_id`),
  KEY `FK255DDC90F9921F28` (`sys_user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wechat_public_account
-- ----------------------------
INSERT INTO `wechat_public_account` VALUES ('2d2fdf108aaf11e5bf7700ffac6fdde1', null, null, null, '2015-11-14 17:07:49', null, '2d796b328aaf11e5bf7700ffac6fdde1', '2d5b5be18aaf11e5bf7700ffac6fdde1', null, 'http://localhost:8080/ttwx/wechat/api?ticket=f9fb2417240aee1a8815c603fe6b2b0be02f397d78d907b19b6df77e1a655ba2895cf397417f11216d5ffa9e624808c2', null, '42683', '0', '2cea4a8e8aaf11e5bf7700ffac6fdde1', null);
INSERT INTO `wechat_public_account` VALUES ('31fb834e8b9611e599bb00ffac6fdde1', null, null, null, '2015-11-15 20:41:31', null, '321b8e708b9611e599bb00ffac6fdde1', '320dd2cf8b9611e599bb00ffac6fdde1', null, 'http://localhost:8080/ttwx/wechat/api?ticket=4a7b793c04ba223e99f09a2ddcb57b782dc2fed2cf4e518049a20e97865b7abcf29b6841415ab5f500daeb0620415d96', null, '54431', '0', '319fcebc8b9611e599bb00ffac6fdde1', null);
INSERT INTO `wechat_public_account` VALUES ('67013c3b5eaf11e59601b8975ab8baa1', 'toUser', 'wx5afd9d53f7755357', '533d8a6047faf43fbc435246577d9c11', '2015-09-21 17:18:50', null, 'c57d4556604111e59f67b8975ab8baa1', 'c5788a65604111e59f67b8975ab8baa1', null, 'http://localhost:8080/ttwx/wechat/api?ticket=d3b9d2c650f92ad84894916a796e0bf32b4afd7b609b4dfd37c01fd737cc44c20ef024052179fd58513794409eb3527e', null, '61486', '2', '66f4e0295eaf11e59601b8975ab8baa1', null);
INSERT INTO `wechat_public_account` VALUES ('340456758b9711e59d4800ffac6fdde1', null, null, null, '2015-11-15 20:48:44', null, '3434b5478b9711e59d4800ffac6fdde1', '342106368b9711e59d4800ffac6fdde1', null, 'http://localhost:8080/ttwx/wechat/api?ticket=0888d27a784886424cb551f245ff57b6210c8375335a94fc18cd5a7019e1304b2b0fd7d54e2a09d6040ef6bfcb03bfe6', null, '41764', '0', '338eff638b9711e59d4800ffac6fdde1', null);
INSERT INTO `wechat_public_account` VALUES ('acf8dcb1a3a511e5aaa5b8975ab8baa1', null, null, null, '2015-12-16 11:32:47', null, 'ad19f943a3a511e5aaa5b8975ab8baa1', 'ad09a592a3a511e5aaa5b8975ab8baa1', null, 'http://localhost:8080/ttwx/wechat/api?ticket=8c7ca18a22ee1f6ed22e8bfa4e2dc4c610e57fd267a5ae2f18d83d2fbdb488344d91ad0fa75d5efd181b7020955f76d1', null, '98229', '0', 'acce985fa3a511e5aaa5b8975ab8baa1', null);

-- ----------------------------
-- Table structure for wechat_req_msg_log
-- ----------------------------
DROP TABLE IF EXISTS `wechat_req_msg_log`;
CREATE TABLE `wechat_req_msg_log` (
  `id` varchar(32) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `event_type` varchar(255) DEFAULT NULL,
  `from_user_name` varchar(255) DEFAULT NULL,
  `in_time` datetime DEFAULT NULL,
  `msg_id` bigint(20) DEFAULT NULL,
  `req_type` varchar(255) DEFAULT NULL,
  `req_xml` longtext,
  `resp_time` datetime DEFAULT NULL,
  `resp_xml` longtext,
  `to_user_name` varchar(255) DEFAULT NULL,
  `public_account_id` varchar(32) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7068134C9F275FAE` (`public_account_id`),
  KEY `FK7068134C46F171D4` (`public_account_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wechat_resp_msg_action
-- ----------------------------
DROP TABLE IF EXISTS `wechat_resp_msg_action`;
CREATE TABLE `wechat_resp_msg_action` (
  `id` varchar(32) NOT NULL,
  `action_type` varchar(255) DEFAULT NULL,
  `event_type` varchar(255) DEFAULT NULL,
  `ext_type` varchar(255) DEFAULT NULL,
  `in_time` datetime DEFAULT NULL,
  `key_word` varchar(255) DEFAULT NULL,
  `fuzzy` tinyint(2) DEFAULT NULL COMMENT '关键字匹配方式（1：完全匹配，2：包含，3：关键字开头，4：关键字结尾）',
  `req_type` varchar(255) DEFAULT NULL,
  `app_id` varchar(32) DEFAULT NULL,
  `material_id` varchar(32) DEFAULT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  `order_no` tinyint(2) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `key_word` (`key_word`,`user_id`,`fuzzy`) USING BTREE,
  KEY `FKF894DD6AD3E19CF1` (`user_id`),
  KEY `FKF894DD6A59F26BD2` (`app_id`),
  KEY `FKF894DD6AD7A67133` (`material_id`),
  KEY `FKF894DD6AEAEA3B74` (`user_id`),
  KEY `FKF894DD6A78D1D69A` (`user_id`),
  KEY `FKF894DD6A24B7D3EC` (`app_id`),
  KEY `FKF894DD6A6B23ACD` (`material_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wechat_resp_msg_action
-- ----------------------------
INSERT INTO `wechat_resp_msg_action` VALUES ('20159872a01d11e5b9413c970e59bd99', 'material', null, null, '2015-12-11 23:37:45', '1', '2', 'text', null, '1ffd2e71a01d11e5b9413c970e59bd99', '66f4e0295eaf11e59601b8975ab8baa1', '1');
INSERT INTO `wechat_resp_msg_action` VALUES ('3007c784a01d11e5b9413c970e59bd99', 'material', null, null, '2015-12-11 23:38:12', '1', '3', 'text', null, '2fedd6e3a01d11e5b9413c970e59bd99', '66f4e0295eaf11e59601b8975ab8baa1', '1');
INSERT INTO `wechat_resp_msg_action` VALUES ('767ccef960eb11e58530b8975ab8baa1', 'material', 'CLICK', null, '2015-09-22 13:33:44', 'key_fa02f18f5eb011e59601b8975ab8baa1', null, 'event', null, 'ffe0fcda60da11e5b220b8975ab8baa1', '66f4e0295eaf11e59601b8975ab8baa1', '1');
INSERT INTO `wechat_resp_msg_action` VALUES ('227d06af60ee11e58530b8975ab8baa1', 'material', 'CLICK', null, '2015-09-22 13:52:40', 'key_fcd8cde05eb011e59601b8975ab8baa1', null, 'event', null, '2273deee60ee11e58530b8975ab8baa1', '66f4e0295eaf11e59601b8975ab8baa1', '1');
INSERT INTO `wechat_resp_msg_action` VALUES ('7f8040e360ef11e5a35ab8975ab8baa1', 'api', null, null, '2015-09-22 14:02:26', null, null, 'location', '4028c68149ffd2a60149ffe491ab0000', null, '66f4e0295eaf11e59601b8975ab8baa1', '1');
INSERT INTO `wechat_resp_msg_action` VALUES ('853d998365e911e5891400ffac6fdde1', 'material', 'CLICK', null, '2015-09-28 22:02:14', 'key_7cab62c265e911e5891400ffac6fdde1', null, 'event', null, '6c90c7e062d211e5892500ffac6fdde1', '66f4e0295eaf11e59601b8975ab8baa1', '1');

-- ----------------------------
-- Table structure for wechat_sys_user_copy
-- ----------------------------
DROP TABLE IF EXISTS `wechat_sys_user_copy`;
CREATE TABLE `wechat_sys_user_copy` (
  `id` varchar(32) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `in_time` datetime DEFAULT NULL,
  `is_valid` varchar(255) DEFAULT NULL,
  `phone_no` varchar(255) DEFAULT NULL,
  `pwd` varchar(255) DEFAULT NULL,
  `score` int(11) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `valid_uid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `valid_uid` (`valid_uid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wechat_sys_user_copy
-- ----------------------------
INSERT INTO `wechat_sys_user_copy` VALUES ('66f4e0295eaf11e59601b8975ab8baa1', 'xd-fjx@163.com', '2015-09-19 17:18:34', '0', null, '814e3472f179b9f9f36bedb74de4e3c1', '0', 'fjx', '66f99b1a5eaf11e59601b8975ab8baa1');

-- ----------------------------
-- Table structure for wechat_user_group
-- ----------------------------
DROP TABLE IF EXISTS `wechat_user_group`;
CREATE TABLE `wechat_user_group` (
  `id` varchar(32) NOT NULL,
  `in_time` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `user_id` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wechat_user_group
-- ----------------------------
INSERT INTO `wechat_user_group` VALUES ('4028c6814971421f01497156ed400000', '2014-11-03 00:29:22', 'dddff', '');
INSERT INTO `wechat_user_group` VALUES ('4028c6814976381401497639035b0000', '2014-11-03 23:14:48', 'ccc', '');
INSERT INTO `wechat_user_group` VALUES ('4028c68149c90bf60149c90ca4c60000', '2014-11-20 01:14:49', 'aaaaaa', '');
INSERT INTO `wechat_user_group` VALUES ('8312d6eb513311e5ac06b8975ab8baa1', '2015-09-02 13:28:58', 'kkk', '4dd200794c6e11e58526b8975ab8baa1');
INSERT INTO `wechat_user_group` VALUES ('86e1922c513311e5ac06b8975ab8baa1', '2015-09-02 13:29:05', '456', '4dd200794c6e11e58526b8975ab8baa1');

-- ----------------------------
-- Table structure for wechat_user_info
-- ----------------------------
DROP TABLE IF EXISTS `wechat_user_info`;
CREATE TABLE `wechat_user_info` (
  `id` varchar(32) NOT NULL,
  `busi_user_id` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `headimgurl` varchar(255) DEFAULT NULL,
  `language` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `openid` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `subscribe` varchar(255) DEFAULT NULL,
  `unsubscribe_time` datetime DEFAULT NULL,
  `wechat_openid` varchar(255) DEFAULT NULL,
  `public_account_id` varchar(32) NOT NULL,
  `group_id` varchar(32) DEFAULT NULL,
  `subscribe_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKA9C826C99F275FAE` (`public_account_id`),
  KEY `FKA9C826C9FFCFAA76` (`group_id`),
  KEY `FKA9C826C946F171D4` (`public_account_id`),
  KEY `FKA9C826C92814BF9C` (`group_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wechat_user_info
-- ----------------------------
INSERT INTO `wechat_user_info` VALUES ('4028818c4961519d0149615397f00000', null, null, null, null, null, null, 'fromUser', null, null, null, '2014-11-02 22:59:59', 'fromUser', '4028818c4961519d0149615397f00000', '4028c6814971421f01497156ed400000', '2014-11-02 23:42:07');
INSERT INTO `wechat_user_info` VALUES ('4028c681499334b30149933fed9d0001', null, null, null, null, null, null, 'fromUser', null, null, null, null, null, '4028818c4961519d0149615397f00000', '4028c6814976381401497639035b0000', '2014-11-09 14:31:20');
INSERT INTO `wechat_user_info` VALUES ('fca1eb465b4c11e5b773b8975ab8baa1', null, null, null, null, null, null, 'FromUser', null, null, null, null, null, '42e876ca4c6f11e58526b8975ab8baa1', null, '2015-09-15 09:56:31');
