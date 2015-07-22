/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50169
Source Host           : localhost:3306
Source Database       : xd-wechat

Target Server Type    : MYSQL
Target Server Version : 50169
File Encoding         : 65001

Date: 2015-07-22 22:37:23
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `portal_guestbook`
-- ----------------------------
DROP TABLE IF EXISTS `portal_guestbook`;
CREATE TABLE `portal_guestbook` (
  `id` varchar(32) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `in_time` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nsg` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `msg` longtext,
  `qq` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `wechat_data_dict`
-- ----------------------------
DROP TABLE IF EXISTS `wechat_data_dict`;
CREATE TABLE `wechat_data_dict` (
  `id` varchar(32) NOT NULL,
  `dict_desc` varchar(255) DEFAULT NULL,
  `dict_name` varchar(255) DEFAULT NULL,
  `dict_value` varchar(255) DEFAULT NULL,
  `group_code` varchar(255) DEFAULT NULL,
  `in_time` varchar(255) DEFAULT NULL,
  `is_valid` varchar(255) DEFAULT NULL,
  `order_num` varchar(255) DEFAULT NULL,
  `parent_id` varchar(255) DEFAULT NULL,
  `group_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wechat_data_dict
-- ----------------------------
INSERT INTO wechat_data_dict VALUES ('22', '消息回复类型', '视频消息', 'video', 'resp_type', '2014-11-30 22:35:47', '1', '7', null, '消息回复类型');
INSERT INTO wechat_data_dict VALUES ('21', '消息回复类型', '图片消息', 'image', 'resp_type', '2014-11-30 22:35:43', '1', '6', null, '消息回复类型');
INSERT INTO wechat_data_dict VALUES ('20', '消息回复类型', '语音消息', 'voice', 'resp_type', '2014-11-30 22:35:40', '1', '5', null, '消息回复类型');
INSERT INTO wechat_data_dict VALUES ('19', '消息回复消息', '图文消息', 'news', 'resp_type', '2014-11-30 22:35:34', '1', '4', null, '消息回复类型');
INSERT INTO wechat_data_dict VALUES ('18', '消息回复类型', '音乐消息', 'music', 'resp_type', '2014-11-30 22:35:27', '1', '3', null, '消息回复类型');
INSERT INTO wechat_data_dict VALUES ('16', '消息回复类型', '文字消息', 'text', 'resp_type', '2014-11-30 22:36:48', '1', '1', null, '消息回复类型');
INSERT INTO wechat_data_dict VALUES ('15', '动作响应数据源(素材、接口)', '素材', 'material', 'action_type', '2014-11-30 19:35:07', '1', '1', null, '动作响应类型');
INSERT INTO wechat_data_dict VALUES ('14', '动作响应数据源(素材、接口)', '插件接口', 'api', 'action_type', '2014-11-30 21:01:00', '1', '2', null, '动作响应类型');
INSERT INTO wechat_data_dict VALUES ('13', 'web应用，url带入用户授权认证的用户标识', 'Web APP', 'web', 'app_type', '2014-11-30 12:26:11', '1', '2', null, '插件类型');
INSERT INTO wechat_data_dict VALUES ('12', '扩展应用', '本地api接口', 'api', 'app_type', '2014-11-30 12:25:36', '1', '1', null, '插件类型');
INSERT INTO wechat_data_dict VALUES ('8', '消息请求类型', '事件消息', 'event', 'req_type', '2014-11-30 22:34:35', '1', '8', null, '消息请求类型');
INSERT INTO wechat_data_dict VALUES ('7', '消息请求类型', '链接消息', 'link', 'req_type', '2014-11-30 22:36:43', '1', '7', null, '消息请求类型');
INSERT INTO wechat_data_dict VALUES ('6', '消息请求类型', '地理位置消息', 'location', 'req_type', '2014-11-30 22:36:25', '1', '6', null, '请求消息类型');
INSERT INTO wechat_data_dict VALUES ('5', '消息请求类型', '视频消息', 'video', 'req_type', '2014-11-30 22:34:04', '1', '5', null, '请求消息类型');
INSERT INTO wechat_data_dict VALUES ('4', '消息请求类型', '语音消息', 'voice', 'req_type', '2014-11-30 22:33:25', '1', '4', null, '请求消息类型');
INSERT INTO wechat_data_dict VALUES ('1', '微信文本消息', '文本消息', 'text', 'req_type', '2014-11-22 16:05:41', '1', '1', null, '请求消息类型');
INSERT INTO wechat_data_dict VALUES ('3', '微信图片消息', '图片消息', 'image', 'req_type', '2014-11-22 16:06:06', '1', '3', null, '请求消息类型');
INSERT INTO wechat_data_dict VALUES ('23', '事件类型', '用户关注', 'subscribe', 'event_type', '2014-11-22 00:13:08', '1', '1', null, '事件类型');
INSERT INTO wechat_data_dict VALUES ('25', '事件类型', '二维码扫描（已关注用户）', 'SCAN', 'event_type', '2014-11-22 00:13:18', '1', '3', null, '事件类型');
INSERT INTO wechat_data_dict VALUES ('24', '事件类型', '取消关注', 'unsubscribe', 'event_type', '2014-11-22 00:13:12', '1', '2', null, '事件类型');
INSERT INTO wechat_data_dict VALUES ('26', '事件类型', '上报地理位置', 'LOCATION', 'event_type', '2014-11-22 00:13:22', '1', '4', null, '事件类型');
INSERT INTO wechat_data_dict VALUES ('27', '事件类型', '菜单点击', 'CLICK', 'event_type', '2014-11-22 00:13:28', '1', '5', null, '事件类型');
INSERT INTO wechat_data_dict VALUES ('28', '用户点击菜单，跳转URL事件', '菜单链接', 'VIEW', 'event_type', '2014-11-22 16:02:28', '1', '6', null, '事件类型');
INSERT INTO wechat_data_dict VALUES ('29', '调用restful接口，与其他系统对接，restful接口直接返回微信规范报文', 'restful远程接口', 'restful', 'app_type', '2014-11-30 12:27:17', '1', '3', null, '插件类型');

-- ----------------------------
-- Table structure for `wechat_ext_app`
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
  `restful_url` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `is_valid` varchar(1) DEFAULT NULL,
  `support_req_type` varchar(255) DEFAULT NULL,
  `method_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wechat_ext_app
-- ----------------------------
INSERT INTO wechat_ext_app VALUES ('1', 'api', '', '1', '2014-03-06 11:45:25', '天气预报', '1', 'weather', null, '根据用户发送的地理位置查询天气信息', '1', null, null);
INSERT INTO wechat_ext_app VALUES ('8a294e2c4c08c595014c0bd4f826000b', 'web', 'http://120.24.159.81/blog/?page_id=31', null, '2015-03-12 10:34:08', '留言板', null, null, '', '', '1', null, null);
INSERT INTO wechat_ext_app VALUES ('8a294e2c4c08c595014c0ceb9f34002b', 'web', 'http://120.24.159.81/blog/', null, '2015-03-12 15:38:30', '我的博客', null, null, '', '', '1', null, null);

-- ----------------------------
-- Table structure for `wechat_ext_app_support_type`
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

-- ----------------------------
-- Table structure for `wechat_material`
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
-- Table structure for `wechat_menu`
-- ----------------------------
DROP TABLE IF EXISTS `wechat_menu`;
CREATE TABLE `wechat_menu` (
  `id` varchar(32) NOT NULL,
  `in_time` datetime DEFAULT NULL,
  `menu_key` varchar(255) DEFAULT NULL,
  `menu_level` int(2) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `parent_id` varchar(32) DEFAULT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  `order_no` int(2) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `menu_key` (`menu_key`),
  KEY `FKF7D64AD8D3E19CF1` (`user_id`),
  KEY `FKF7D64AD8CF82234E` (`parent_id`),
  KEY `FKF7D64AD8EAEA3B74` (`user_id`),
  KEY `FKF7D64AD878D1D69A` (`user_id`),
  KEY `FKF7D64AD86AC1EE68` (`parent_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `wechat_msg_template`
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
INSERT INTO wechat_msg_template VALUES ('1', '接口验证成功', '2014-11-08 00:31:28', '<xml>\r\n<ToUserName><![CDATA[{#ToUserName#}]]></ToUserName>\r\n<FromUserName><![CDATA[{#FromUserName#}]]></FromUserName>\r\n<CreateTime>{#CreateTime#}</CreateTime>\r\n<MsgType><![CDATA[text]]></MsgType>\r\n<Content><![CDATA[验证成功，您的公众账号已成功对接。]]></Content>\r\n</xml>', 'api_valid_success');
INSERT INTO wechat_msg_template VALUES ('2', '接口验证失败', '2014-11-08 00:47:47', '<xml>\r\n<ToUserName><![CDATA[{#ToUserName#}]]></ToUserName>\r\n<FromUserName><![CDATA[{#FromUserName#}]]></FromUserName>\r\n<CreateTime>{#CreateTime#}</CreateTime>\r\n<MsgType><![CDATA[text]]></MsgType>\r\n<Content><![CDATA[发送的消息无效，接口未激活！]]></Content>\r\n</xml>', 'api_valid_fail');

-- ----------------------------
-- Table structure for `wechat_public_account`
-- ----------------------------
DROP TABLE IF EXISTS `wechat_public_account`;
CREATE TABLE `wechat_public_account` (
  `id` varchar(32) NOT NULL,
  `account_id` varchar(255) DEFAULT NULL,
  `app_id` varchar(255) DEFAULT NULL,
  `app_secret` varchar(255) DEFAULT NULL,
  `in_time` datetime DEFAULT NULL,
  `pwd` varchar(255) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `valid_code` varchar(255) DEFAULT NULL,
  `valid_state` varchar(255) DEFAULT NULL,
  `sys_user_id` varchar(32) DEFAULT NULL,
  `encodingAESKey` varchar(255) DEFAULT NULL,
  `ticket` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ticket` (`ticket`),
  KEY `FK255DDC9054A1E57F` (`sys_user_id`),
  KEY `FK255DDC906BAA8402` (`sys_user_id`),
  KEY `FK255DDC90F9921F28` (`sys_user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `wechat_req_msg_log`
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
-- Table structure for `wechat_resp_msg_action`
-- ----------------------------
DROP TABLE IF EXISTS `wechat_resp_msg_action`;
CREATE TABLE `wechat_resp_msg_action` (
  `id` varchar(32) NOT NULL,
  `action_type` varchar(255) DEFAULT NULL,
  `event_type` varchar(255) DEFAULT NULL,
  `ext_type` varchar(255) DEFAULT NULL,
  `in_time` datetime DEFAULT NULL,
  `key_word` varchar(255) DEFAULT NULL,
  `req_type` varchar(255) DEFAULT NULL,
  `app_id` varchar(32) DEFAULT NULL,
  `material_id` varchar(32) DEFAULT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `key_word` (`key_word`,`user_id`),
  KEY `FKF894DD6AD3E19CF1` (`user_id`),
  KEY `FKF894DD6A59F26BD2` (`app_id`),
  KEY `FKF894DD6AD7A67133` (`material_id`),
  KEY `FKF894DD6AEAEA3B74` (`user_id`),
  KEY `FKF894DD6A78D1D69A` (`user_id`),
  KEY `FKF894DD6A24B7D3EC` (`app_id`),
  KEY `FKF894DD6A6B23ACD` (`material_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `wechat_sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `wechat_sys_user`;
CREATE TABLE `wechat_sys_user` (
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
-- Table structure for `wechat_user_group`
-- ----------------------------
DROP TABLE IF EXISTS `wechat_user_group`;
CREATE TABLE `wechat_user_group` (
  `id` varchar(32) NOT NULL,
  `in_time` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `user_id` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `wechat_user_info`
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
  `subscribe_time` datetime DEFAULT NULL,
  `unsubscribe_time` datetime DEFAULT NULL,
  `public_account_id` varchar(32) NOT NULL,
  `group_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKA9C826C99F275FAE` (`public_account_id`),
  KEY `FKA9C826C9FFCFAA76` (`group_id`),
  KEY `FKA9C826C946F171D4` (`public_account_id`),
  KEY `FKA9C826C92814BF9C` (`group_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- View structure for `keyword_action_view`
-- ----------------------------
DROP VIEW IF EXISTS `keyword_action_view`;
