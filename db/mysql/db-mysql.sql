/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50169
Source Host           : localhost:3306
Source Database       : xd-wechat

Target Server Type    : MYSQL
Target Server Version : 50169
File Encoding         : 65001

Date: 2014-12-10 00:09:54
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
  `phone` varchar(255) DEFAULT NULL,
  `qq` varchar(255) DEFAULT NULL,
  `msg` longtext,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of portal_guestbook
-- ----------------------------
INSERT INTO portal_guestbook VALUES ('4028c68149803b640149803bc05f0000', 'xd-fjx@qq.com', '2014-11-05 21:53:59', 'fengjx', null, '', 'test content...........');
INSERT INTO portal_guestbook VALUES ('4028c681498036fe014980383f770001', 'xd-fjx@qq.com', '2014-11-05 21:50:10', 'fengjx', null, '', 'test content...........');

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
INSERT INTO wechat_data_dict VALUES ('1', '微信文本消息', '文本消息', 'text', 'req_type', '2014-11-22 16:05:41', '1', '1', null, '请求消息类型');
INSERT INTO wechat_data_dict VALUES ('3', '微信图片消息', '图片消息', 'image', 'req_type', '2014-11-22 16:06:06', '1', '3', null, '请求消息类型');
INSERT INTO wechat_data_dict VALUES ('4', '消息请求类型', '语音消息', 'voice', 'req_type', '2014-11-30 22:33:25', '1', '4', null, '请求消息类型');
INSERT INTO wechat_data_dict VALUES ('5', '消息请求类型', '视频消息', 'video', 'req_type', '2014-11-30 22:34:04', '1', '5', null, '请求消息类型');
INSERT INTO wechat_data_dict VALUES ('6', '消息请求类型', '地理位置消息', 'location', 'req_type', '2014-11-30 22:36:25', '1', '6', null, '请求消息类型');
INSERT INTO wechat_data_dict VALUES ('7', '消息请求类型', '链接消息', 'link', 'req_type', '2014-11-30 22:36:43', '1', '7', null, '消息请求类型');
INSERT INTO wechat_data_dict VALUES ('8', '消息请求类型', '事件消息', 'event', 'req_type', '2014-11-30 22:34:35', '1', '8', null, '消息请求类型');
INSERT INTO wechat_data_dict VALUES ('12', '扩展应用', '本地api接口', 'api', 'app_type', '2014-11-30 12:25:36', '1', '1', null, '插件类型');
INSERT INTO wechat_data_dict VALUES ('13', 'web应用，url带入用户授权认证的用户标识', 'Web APP', 'web', 'app_type', '2014-11-30 12:26:11', '1', '2', null, '插件类型');
INSERT INTO wechat_data_dict VALUES ('14', '动作响应数据源(素材、接口)', '插件接口', 'api', 'action_type', '2014-11-30 21:01:00', '1', '2', null, '动作响应类型');
INSERT INTO wechat_data_dict VALUES ('15', '动作响应数据源(素材、接口)', '素材', 'material', 'action_type', '2014-11-30 19:35:07', '1', '1', null, '动作响应类型');
INSERT INTO wechat_data_dict VALUES ('16', '消息回复类型', '文字消息', 'text', 'resp_type', '2014-11-30 22:36:48', '1', '1', null, '消息回复类型');
INSERT INTO wechat_data_dict VALUES ('18', '消息回复类型', '音乐消息', 'music', 'resp_type', '2014-11-30 22:35:27', '1', '3', null, '消息回复类型');
INSERT INTO wechat_data_dict VALUES ('19', '消息回复消息', '图文消息', 'news', 'resp_type', '2014-11-30 22:35:34', '1', '4', null, '消息回复类型');
INSERT INTO wechat_data_dict VALUES ('20', '消息回复类型', '语音消息', 'voice', 'resp_type', '2014-11-30 22:35:40', '1', '5', null, '消息回复类型');
INSERT INTO wechat_data_dict VALUES ('21', '消息回复类型', '图片消息', 'image', 'resp_type', '2014-11-30 22:35:43', '1', '6', null, '消息回复类型');
INSERT INTO wechat_data_dict VALUES ('22', '消息回复类型', '视频消息', 'video', 'resp_type', '2014-11-30 22:35:47', '1', '7', null, '消息回复类型');
INSERT INTO wechat_data_dict VALUES ('23', '事件类型', '用户关注', 'subscribe', 'event_type', '2014-11-22 00:13:08', '1', '1', null, '事件类型');
INSERT INTO wechat_data_dict VALUES ('25', '事件类型', '二维码扫描（已关注用户）', 'SCAN', 'event_type', '2014-11-22 00:13:18', '1', '3', null, '事件类型');
INSERT INTO wechat_data_dict VALUES ('24', '事件类型', '取消关注', 'unsubscribe', 'event_type', '2014-11-22 00:13:12', '1', '2', null, '事件类型');
INSERT INTO wechat_data_dict VALUES ('26', '事件类型', '上报地理位置', 'LOCATION', 'event_type', '2014-11-22 00:13:22', '1', '4', null, '事件类型');
INSERT INTO wechat_data_dict VALUES ('27', '事件类型', '菜单点击', 'CLICK', 'event_type', '2014-11-22 00:13:28', '1', '5', null, '事件类型');
INSERT INTO wechat_data_dict VALUES ('28', '用户点击菜单，跳转URL事件', '菜单链接', 'VIEW', 'event_type', '2014-11-22 16:02:28', '1', '6', null, '事件类型');
INSERT INTO wechat_data_dict VALUES ('29', '调用restful接口，与其他系统对接，restful接口直接返回微信规范报文', 'restful远程接口', 'restful', 'app_type', '2014-11-30 12:27:17', '1', '3', null, '插件类型');

-- ----------------------------
-- Table structure for `wechat_data_dict_copy`
-- ----------------------------
DROP TABLE IF EXISTS `wechat_data_dict_copy`;
CREATE TABLE `wechat_data_dict_copy` (
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
-- Records of wechat_data_dict_copy
-- ----------------------------
INSERT INTO wechat_data_dict_copy VALUES ('1', '消息请求类型', '文本消息', 'text', 'req_type', '2014-2-26 17:47:18', '1', '1', null, null);
INSERT INTO wechat_data_dict_copy VALUES ('3', '消息请求类型', '图片消息', 'image', 'req_type', '2014-2-26 17:50:32', '1', '3', null, null);
INSERT INTO wechat_data_dict_copy VALUES ('4', '消息请求类型', '语音消息', 'voice', 'req_type', '2014-2-26 17:51:41', '1', '4', null, null);
INSERT INTO wechat_data_dict_copy VALUES ('5', '消息请求类型', '视频消息', 'video', 'req_type', '2014-2-26 17:52:15', '1', '5', null, null);
INSERT INTO wechat_data_dict_copy VALUES ('6', '消息请求类型', '地理位置消息', 'location', 'req_type', '2014-2-26 17:53:02', '1', '6', null, null);
INSERT INTO wechat_data_dict_copy VALUES ('7', '消息请求类型', '链接消息', 'link', 'req_type', '2014-2-26 17:53:44', '1', '7', null, null);
INSERT INTO wechat_data_dict_copy VALUES ('8', '消息请求类型', '事件消息', 'event', 'req_type', '2014-2-26 17:54:48', '1', '8', null, null);
INSERT INTO wechat_data_dict_copy VALUES ('12', '扩展应用', '本地api接口', 'api', 'app_type', '2014-3-6 16:49:03', '1', '1', null, null);
INSERT INTO wechat_data_dict_copy VALUES ('13', '扩展应用', 'web类型', 'web', 'app_type', '2014-3-6 16:49:35', '1', '2', null, null);
INSERT INTO wechat_data_dict_copy VALUES ('14', '动作响应数据源(素材、接口)', '应用接口', 'busiapp_api', 'action_type', '2014-3-6 16:53:22', '1', '1', null, null);
INSERT INTO wechat_data_dict_copy VALUES ('15', '动作响应数据源(素材、接口)', '素材', 'material', 'action_type', '2014-3-6 17:01:14', '1', '1', null, null);
INSERT INTO wechat_data_dict_copy VALUES ('16', '消息回复类型', '文字消息', 'text', 'resp_type', '2014-3-20 15:20:59', '1', '1', null, null);
INSERT INTO wechat_data_dict_copy VALUES ('17', '消息回复类型', '接口数据', 'busiapp_api', 'resp_type', '2014-3-20 15:22:04', '1', '2', null, null);
INSERT INTO wechat_data_dict_copy VALUES ('18', '消息回复类型', '音乐消息', 'music', 'resp_type', '2014-3-20 15:28:16', '1', '3', null, null);
INSERT INTO wechat_data_dict_copy VALUES ('19', '消息回复消息', '图文消息', 'news', 'resp_type', '2014-3-20 15:29:20', '1', '4', null, null);
INSERT INTO wechat_data_dict_copy VALUES ('20', '消息回复类型', '语音消息', 'voice', 'resp_type', '2014-3-20 15:29:50', '1', '5', null, null);
INSERT INTO wechat_data_dict_copy VALUES ('21', '消息回复类型', '图片消息', 'image', 'resp_type', '2014-3-20 15:30:32', '1', '6', null, null);
INSERT INTO wechat_data_dict_copy VALUES ('22', '消息回复类型', '视频消息', 'video', 'resp_type', '2014-3-20 15:31:09', '1', '7', null, null);
INSERT INTO wechat_data_dict_copy VALUES ('23', '事件类型', '用户关注', 'subscribe', 'event_type', '2014-3-20 17:17:32', '1', '1', null, null);
INSERT INTO wechat_data_dict_copy VALUES ('25', '事件类型', '二维码扫描（已关注用户）', 'SCAN', 'event_type', '2014-3-20 17:18:55', '1', '3', null, null);
INSERT INTO wechat_data_dict_copy VALUES ('24', '事件类型', '取消关注', 'unsubscribe', 'event_type', '2014-3-20 17:18:17', '1', '2', null, null);
INSERT INTO wechat_data_dict_copy VALUES ('26', '事件类型', '上报地理位置', 'LOCATION', 'event_type', '2014-3-20 17:19:25', '1', '4', null, null);
INSERT INTO wechat_data_dict_copy VALUES ('27', '事件类型', '菜单点击', 'CLICK', 'event_type', '2014-3-20 17:20:16', '1', '5', null, null);
INSERT INTO wechat_data_dict_copy VALUES ('28', '事件类型', '菜单链接', 'VIEW', 'event_type', '2014-3-20 17:21:11', '1', '6', null, null);
INSERT INTO wechat_data_dict_copy VALUES ('29', '扩展应用', 'restful远程接口', 'restful', 'app_type', '2014-11-08 17:21:11', '1', '3', null, null);

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
  `method_name` varchar(255) DEFAULT NULL,
  `restful_url` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `is_valid` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wechat_ext_app
-- ----------------------------
INSERT INTO wechat_ext_app VALUES ('4028c68149ffd2a60149ffe491ab0000', 'api', '', null, '2014-11-30 17:24:50', '天气预报', null, 'myExtService', 'weatherInfo', '', '天气预报天气预报', '1');
INSERT INTO wechat_ext_app VALUES ('4028c6814a00dad2014a010597060004', 'api', '', null, '2014-11-30 22:05:51', 'a', null, 'a', 'a', '', 'aaaaaaaaaaa', '1');
INSERT INTO wechat_ext_app VALUES ('4028c6814a00dad2014a011b6cd5000d', 'restful', '', null, '2014-11-30 22:29:42', 'restful', null, null, null, 'http://localhost:8080/ttwx/admin', 'test restful', '1');

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
INSERT INTO wechat_ext_app_support_type VALUES ('4028c68149fffdba014a000450b70006', null, 'location', '4028c68149ffd2a60149ffe491ab0000');
INSERT INTO wechat_ext_app_support_type VALUES ('4028c6814a00dad2014a0105970f0008', null, 'video', '4028c6814a00dad2014a010597060004');
INSERT INTO wechat_ext_app_support_type VALUES ('4028c6814a00dad2014a0105970f0009', null, 'link', '4028c6814a00dad2014a010597060004');
INSERT INTO wechat_ext_app_support_type VALUES ('4028c6814a00dad2014a011b6cd6000e', null, '', '4028c6814a00dad2014a011b6cd5000d');
INSERT INTO wechat_ext_app_support_type VALUES ('4028c6814a00dad2014a0105970e0007', null, 'voice', '4028c6814a00dad2014a010597060004');
INSERT INTO wechat_ext_app_support_type VALUES ('4028c6814a00dad2014a0105970e0006', null, 'image', '4028c6814a00dad2014a010597060004');
INSERT INTO wechat_ext_app_support_type VALUES ('4028c6814a00dad2014a010597060005', null, 'text', '4028c6814a00dad2014a010597060004');

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
-- Records of wechat_material
-- ----------------------------
INSERT INTO wechat_material VALUES ('4028c681492dde1601492df5a42a0000', '2014-10-20 22:28:33', 'text', '<xml>\n  <Content><![CDATA[1111111]]></Content>\n  <ToUserName><![CDATA[]]></ToUserName>\n  <FromUserName><![CDATA[]]></FromUserName>\n  <CreateTime><![CDATA[0]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', '4028c681492dd51601492dd6fdd90000');
INSERT INTO wechat_material VALUES ('4028c681494818e2014948aa8f460000', '2014-10-26 02:56:17', 'text', '<xml>\n  <Content><![CDATA[222]]></Content>\n  <ToUserName><![CDATA[]]></ToUserName>\n  <FromUserName><![CDATA[]]></FromUserName>\n  <CreateTime><![CDATA[0]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', '4028c681492dd51601492dd6fdd90000');
INSERT INTO wechat_material VALUES ('4028c681494b994701494cc0e8880002', '2014-10-26 21:59:11', 'text', '<xml>\n  <Content><![CDATA[啊啊啊啊啊]]></Content>\n  <ToUserName><![CDATA[]]></ToUserName>\n  <FromUserName><![CDATA[]]></FromUserName>\n  <CreateTime><![CDATA[0]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', '4028c681494b994701494b99aba50000');
INSERT INTO wechat_material VALUES ('4028818c49614e8f0149614f1de20000', '2014-10-30 21:46:55', 'text', '<xml>\n  <Content><![CDATA[111111111]]></Content>\n  <ToUserName><![CDATA[]]></ToUserName>\n  <FromUserName><![CDATA[]]></FromUserName>\n  <CreateTime><![CDATA[0]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', '4028c681494b994701494b99aba50000');
INSERT INTO wechat_material VALUES ('4028818c496159e301496162853e0002', '2014-10-30 22:08:06', 'text', '<xml>\n  <Content><![CDATA[2222222222222222]]></Content>\n  <ToUserName><![CDATA[{#ToUserName#}]]></ToUserName>\n  <FromUserName><![CDATA[{#FromUserName#}]]></FromUserName>\n  <CreateTime><![CDATA[0]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', '4028c681494b994701494b99aba50000');
INSERT INTO wechat_material VALUES ('4028c681496c4a9f01496c4c4a5e0000', '2014-11-02 00:59:39', 'news', '<xml><ToUserName><![CDATA[]]></ToUserName><FromUserName><![CDATA[]]></FromUserName><CreateTime><![CDATA[]]></CreateTime><MsgType><![CDATA[news]]></MsgType><ArticleCount>1</ArticleCount><Articles><item><Title><![CDATA[1111111111111111111111111]]></Title><Description><![CDATA[1111111111111111111111111111111111111111111111111111111111111111111111]]></Description><PicUrl><![CDATA[http://localhost/wechat/upload/images/material/20141102/15471414861124482.jpg]]></PicUrl><Url><![CDATA[http://www.baidu.com]]></Url></item></Articles></xml>', '4028c681494b994701494b99aba50000');
INSERT INTO wechat_material VALUES ('4028c681498a475c01498a49b1670000', '2014-11-07 20:45:25', 'text', '<xml>\n  <Content><![CDATA[4444444444]]></Content>\n  <ToUserName><![CDATA[{#ToUserName#}]]></ToUserName>\n  <FromUserName><![CDATA[{#FromUserName#}]]></FromUserName>\n  <CreateTime><![CDATA[0]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', '4028c681494b994701494b99aba50000');
INSERT INTO wechat_material VALUES ('4028c681498ae26201498b0b1c970001', '2014-11-08 00:16:41', 'text', '<xml>\n  <Content><![CDATA[5555555555]]></Content>\n  <ToUserName><![CDATA[{#ToUserName#}]]></ToUserName>\n  <FromUserName><![CDATA[{#FromUserName#}]]></FromUserName>\n  <CreateTime><![CDATA[0]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', '4028c681494b994701494b99aba50000');
INSERT INTO wechat_material VALUES ('4028c6814990122c0149901719920000', '2014-11-08 23:47:53', 'text', '<xml>\n  <Content><![CDATA[3333333333333]]></Content>\n  <ToUserName><![CDATA[{#ToUserName#}]]></ToUserName>\n  <FromUserName><![CDATA[{#FromUserName#}]]></FromUserName>\n  <CreateTime><![CDATA[0]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', '4028c681494b994701494b99aba50000');
INSERT INTO wechat_material VALUES ('4028c6814990122c0149901856940002', '2014-11-08 23:49:14', 'text', '<xml>\n  <Content><![CDATA[1111111111111]]></Content>\n  <ToUserName><![CDATA[{#ToUserName#}]]></ToUserName>\n  <FromUserName><![CDATA[{#FromUserName#}]]></FromUserName>\n  <CreateTime><![CDATA[0]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', '4028c681494b994701494b99aba50000');
INSERT INTO wechat_material VALUES ('4028c6814990122c01499018668d0004', '2014-11-08 23:49:18', 'text', '<xml>\n  <Content><![CDATA[222222222222]]></Content>\n  <ToUserName><![CDATA[{#ToUserName#}]]></ToUserName>\n  <FromUserName><![CDATA[{#FromUserName#}]]></FromUserName>\n  <CreateTime><![CDATA[0]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', '4028c681494b994701494b99aba50000');
INSERT INTO wechat_material VALUES ('4028c6814990122c0149901876830006', '2014-11-08 23:49:22', 'text', '<xml>\n  <Content><![CDATA[444444444444]]></Content>\n  <ToUserName><![CDATA[{#ToUserName#}]]></ToUserName>\n  <FromUserName><![CDATA[{#FromUserName#}]]></FromUserName>\n  <CreateTime><![CDATA[0]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', '4028c681494b994701494b99aba50000');
INSERT INTO wechat_material VALUES ('4028c6814990122c0149901890e60008', '2014-11-08 23:49:29', 'text', '<xml>\n  <Content><![CDATA[5555555555]]></Content>\n  <ToUserName><![CDATA[{#ToUserName#}]]></ToUserName>\n  <FromUserName><![CDATA[{#FromUserName#}]]></FromUserName>\n  <CreateTime><![CDATA[0]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', '4028c681494b994701494b99aba50000');
INSERT INTO wechat_material VALUES ('4028c6814990122c01499018ffde000a', '2014-11-08 23:49:57', 'text', '<xml>\n  <Content><![CDATA[111111111111111]]></Content>\n  <ToUserName><![CDATA[{#ToUserName#}]]></ToUserName>\n  <FromUserName><![CDATA[{#FromUserName#}]]></FromUserName>\n  <CreateTime><![CDATA[0]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', '4028c681494b994701494b99aba50000');
INSERT INTO wechat_material VALUES ('4028c6814990122c0149901968bd000c', '2014-11-08 23:50:24', 'text', '<xml>\n  <Content><![CDATA[22222222222222]]></Content>\n  <ToUserName><![CDATA[{#ToUserName#}]]></ToUserName>\n  <FromUserName><![CDATA[{#FromUserName#}]]></FromUserName>\n  <CreateTime><![CDATA[0]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', '4028c681494b994701494b99aba50000');
INSERT INTO wechat_material VALUES ('4028c6814990122c01499020e8d8000e', '2014-11-08 23:58:36', 'text', '<xml>\n  <Content><![CDATA[111111111111111122222222222223333]]></Content>\n  <ToUserName><![CDATA[{#ToUserName#}]]></ToUserName>\n  <FromUserName><![CDATA[{#FromUserName#}]]></FromUserName>\n  <CreateTime><![CDATA[0]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', '4028c681494b994701494b99aba50000');
INSERT INTO wechat_material VALUES ('4028c6814990122c0149902299080010', '2014-11-09 00:00:26', 'text', '<xml>\n  <Content><![CDATA[2222222222222244444]]></Content>\n  <ToUserName><![CDATA[{#ToUserName#}]]></ToUserName>\n  <FromUserName><![CDATA[{#FromUserName#}]]></FromUserName>\n  <CreateTime><![CDATA[0]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', '4028c681494b994701494b99aba50000');
INSERT INTO wechat_material VALUES ('4028c6814990122c01499028f57e0012', '2014-11-09 00:07:23', 'text', '<xml>\n  <Content><![CDATA[333344]]></Content>\n  <ToUserName><![CDATA[{#ToUserName#}]]></ToUserName>\n  <FromUserName><![CDATA[{#FromUserName#}]]></FromUserName>\n  <CreateTime><![CDATA[0]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', '4028c681494b994701494b99aba50000');
INSERT INTO wechat_material VALUES ('4028c6814990122c014990291cb10014', '2014-11-09 00:07:33', 'text', '<xml>\n  <Content><![CDATA[333344fff]]></Content>\n  <ToUserName><![CDATA[{#ToUserName#}]]></ToUserName>\n  <FromUserName><![CDATA[{#FromUserName#}]]></FromUserName>\n  <CreateTime><![CDATA[0]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', '4028c681494b994701494b99aba50000');
INSERT INTO wechat_material VALUES ('4028c6814990122c014990299ade0016', '2014-11-09 00:08:06', 'text', '<xml>\n  <Content><![CDATA[333344fff111]]></Content>\n  <ToUserName><![CDATA[{#ToUserName#}]]></ToUserName>\n  <FromUserName><![CDATA[{#FromUserName#}]]></FromUserName>\n  <CreateTime><![CDATA[0]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', '4028c681494b994701494b99aba50000');
INSERT INTO wechat_material VALUES ('4028c6814990122c0149902d5b2d0018', '2014-11-09 00:12:11', 'text', '<xml>\n  <Content><![CDATA[333344fff111ghh]]></Content>\n  <ToUserName><![CDATA[{#ToUserName#}]]></ToUserName>\n  <FromUserName><![CDATA[{#FromUserName#}]]></FromUserName>\n  <CreateTime><![CDATA[0]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', '4028c681494b994701494b99aba50000');
INSERT INTO wechat_material VALUES ('4028c6814990122c0149902fd4c9001a', '2014-11-09 00:14:54', 'text', '<xml>\n  <Content><![CDATA[333344fff111ghhfffff]]></Content>\n  <ToUserName><![CDATA[{#ToUserName#}]]></ToUserName>\n  <FromUserName><![CDATA[{#FromUserName#}]]></FromUserName>\n  <CreateTime><![CDATA[0]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', '4028c681494b994701494b99aba50000');
INSERT INTO wechat_material VALUES ('4028c6814990122c014990340008001d', '2014-11-09 00:19:27', 'text', '<xml>\n  <Content><![CDATA[啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊]]></Content>\n  <ToUserName><![CDATA[{#ToUserName#}]]></ToUserName>\n  <FromUserName><![CDATA[{#FromUserName#}]]></FromUserName>\n  <CreateTime><![CDATA[0]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', '4028c681494b994701494b99aba50000');
INSERT INTO wechat_material VALUES ('4028c6814990122c014990360e9b0022', '2014-11-09 00:21:42', 'text', '<xml>\n  <Content><![CDATA[感谢你的关注！]]></Content>\n  <ToUserName><![CDATA[{#ToUserName#}]]></ToUserName>\n  <FromUserName><![CDATA[{#FromUserName#}]]></FromUserName>\n  <CreateTime><![CDATA[0]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', '4028c681494b994701494b99aba50000');
INSERT INTO wechat_material VALUES ('4028c6814990122c0149903657590024', '2014-11-09 00:22:00', 'text', '<xml>\n  <Content><![CDATA[暂时无法匹配你发生的消息！]]></Content>\n  <ToUserName><![CDATA[{#ToUserName#}]]></ToUserName>\n  <FromUserName><![CDATA[{#FromUserName#}]]></FromUserName>\n  <CreateTime><![CDATA[0]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', '4028c681494b994701494b99aba50000');
INSERT INTO wechat_material VALUES ('4028c6814990122c0149903a2f970028', '2014-11-09 00:26:12', 'text', '<xml>\n  <Content><![CDATA[系统暂时无法匹配你发送的消息]]></Content>\n  <ToUserName><![CDATA[{#ToUserName#}]]></ToUserName>\n  <FromUserName><![CDATA[{#FromUserName#}]]></FromUserName>\n  <CreateTime><![CDATA[0]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', '4028c681494b994701494b99aba50000');
INSERT INTO wechat_material VALUES ('4028c68149b3614b0149b4002a130000', '2014-11-15 23:09:09', 'text', '<xml>\n  <Content><![CDATA[aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa]]></Content>\n  <ToUserName><![CDATA[{#ToUserName#}]]></ToUserName>\n  <FromUserName><![CDATA[{#FromUserName#}]]></FromUserName>\n  <CreateTime><![CDATA[0]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', '4028c681494b994701494b99aba50000');
INSERT INTO wechat_material VALUES ('4028c68149c90bf60149c90df7e00002', '2014-11-20 01:16:16', 'text', '<xml>\n  <Content><![CDATA[ffffffffffff]]></Content>\n  <ToUserName><![CDATA[{#ToUserName#}]]></ToUserName>\n  <FromUserName><![CDATA[{#FromUserName#}]]></FromUserName>\n  <CreateTime><![CDATA[0]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', '4028c681494b994701494b99aba50000');
INSERT INTO wechat_material VALUES ('4028c68149c90bf60149c90e44460004', '2014-11-20 01:16:35', 'text', '<xml>\n  <Content><![CDATA[感谢你的关注！！！]]></Content>\n  <ToUserName><![CDATA[{#ToUserName#}]]></ToUserName>\n  <FromUserName><![CDATA[{#FromUserName#}]]></FromUserName>\n  <CreateTime><![CDATA[0]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', '4028c681494b994701494b99aba50000');
INSERT INTO wechat_material VALUES ('4028c68149c90bf60149c90e6ccf0006', '2014-11-20 01:16:46', 'text', '<xml>\n  <Content><![CDATA[系统暂时无法匹配你发送的消息！~]]></Content>\n  <ToUserName><![CDATA[{#ToUserName#}]]></ToUserName>\n  <FromUserName><![CDATA[{#FromUserName#}]]></FromUserName>\n  <CreateTime><![CDATA[0]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', '4028c681494b994701494b99aba50000');
INSERT INTO wechat_material VALUES ('4028c6814a00dad2014a0104bbf90001', '2014-11-30 22:04:55', 'text', '<xml>\n  <Content><![CDATA[aaaaaaaaaaaa]]></Content>\n  <ToUserName><![CDATA[{#ToUserName#}]]></ToUserName>\n  <FromUserName><![CDATA[{#FromUserName#}]]></FromUserName>\n  <CreateTime><![CDATA[0]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', '4028c681494b994701494b99aba50000');
INSERT INTO wechat_material VALUES ('4028c6814a00dad2014a011752a0000b', '2014-11-30 22:25:13', 'text', '<xml>\n  <Content><![CDATA[啊啊啊啊啊啊啊啊啊啊啊啊]]></Content>\n  <ToUserName><![CDATA[{#ToUserName#}]]></ToUserName>\n  <FromUserName><![CDATA[{#FromUserName#}]]></FromUserName>\n  <CreateTime><![CDATA[0]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', '4028c681494b994701494b99aba50000');

-- ----------------------------
-- Table structure for `wechat_menu`
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `menu_key` (`menu_key`),
  KEY `FKF7D64AD8D3E19CF1` (`user_id`),
  KEY `FKF7D64AD8CF82234E` (`parent_id`),
  KEY `FKF7D64AD8EAEA3B74` (`user_id`),
  KEY `FKF7D64AD878D1D69A` (`user_id`),
  KEY `FKF7D64AD86AC1EE68` (`parent_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wechat_menu
-- ----------------------------
INSERT INTO wechat_menu VALUES ('4028c681494818e2014948cd62bb0002', '2014-10-26 03:34:20', null, '0', 'aaaa', null, '2014-10-26 03:34:20', null, null, '4028c681492dd51601492dd6fdd90000');
INSERT INTO wechat_menu VALUES ('4028c681497aeaae01497b1476760004', '2014-11-04 21:52:58', null, '0', 'url', null, '2014-11-04 21:52:58', null, null, '4028c681494b994701494b99aba50000');
INSERT INTO wechat_menu VALUES ('4028c681497aeaae01497b15b0e00005', '2014-11-04 21:54:19', null, '0', 'csdn', 'view', '2014-11-04 21:54:50', 'http://www.csdn.net/', '4028c681497aeaae01497b1476760004', '4028c681494b994701494b99aba50000');
INSERT INTO wechat_menu VALUES ('4028c68149c90bf60149c90d3fba0001', '2014-11-20 01:15:29', null, '0', '36kr', 'view', '2014-11-20 01:15:34', 'http://www.36kr.com/', null, '4028c681494b994701494b99aba50000');
INSERT INTO wechat_menu VALUES ('4028c6814a23c307014a23d426bb0000', '2014-12-07 16:18:33', null, '0', 'fjx', 'view', '2014-12-07 16:18:57', 'http://ttwx.sinaapp.com/', null, '4028c681494b994701494b99aba50000');
INSERT INTO wechat_menu VALUES ('4028c681497aeaae01497b15d31f0006', '2014-11-04 21:54:28', null, '0', '51cto', 'view', '2014-11-04 21:55:00', 'http://down.51cto.com/', '4028c681497aeaae01497b1476760004', '4028c681494b994701494b99aba50000');
INSERT INTO wechat_menu VALUES ('4028c681497aeaae01497b15f8910007', '2014-11-04 21:54:37', null, '0', 'oschina', 'view', '2014-11-04 21:55:11', 'http://www.oschina.net/', '4028c681497aeaae01497b1476760004', '4028c681494b994701494b99aba50000');

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
INSERT INTO wechat_public_account VALUES ('4028818c4961519d0149615397f00000', null, 'wx5afd9d53f7755357', '533d8a6047faf43fbc435246577d9c11', '2014-10-30 21:51:48', null, '780a0f7e7de711e498ac6036dd68230b', '78041c0d7de711e498ac6036dd68230b', null, 'http://localhost:8080/ttwx/wechat/api?ticket=484CC8CCD1ADCBC9C971310E4195C99FCD90832DABE40C06D9920962A9CC23C2ACA3D33315EE60774521598134478BDD', null, '52731', '0', '4028c681494b994701494b99aba50000', null);

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
-- Records of wechat_req_msg_log
-- ----------------------------
INSERT INTO wechat_req_msg_log VALUES ('4028818c496159e30149615a7ebe0000', '2012-09-28 19:31:00', null, 'fromUser', '2014-10-30 21:59:20', '1234567890123456', 'text', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[41702]]></Content>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-10-30 21:59:21', '<xml>\r\n<ToUserName><![CDATA[fromUser]]></ToUserName>\r\n<FromUserName><![CDATA[toUser]]></FromUserName>\r\n<CreateTime>1414677561172</CreateTime>\r\n<MsgType><![CDATA[text]]></MsgType>\r\n<Content><![CDATA[验证成功，您的公众账号已成功对接。]]></Content>\r\n</xml>', 'toUser', '');
INSERT INTO wechat_req_msg_log VALUES ('4028818c496159e30149615ad9790001', '2012-09-28 19:31:00', null, 'fromUser', '2014-10-30 21:59:44', '1234567890123456', 'text', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[1]]></Content>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-10-30 21:59:44', '<xml>\n  <Content><![CDATA[111111111]]></Content>\n  <ToUserName><![CDATA[]]></ToUserName>\n  <FromUserName><![CDATA[]]></FromUserName>\n  <CreateTime><![CDATA[0]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', 'toUser', '');
INSERT INTO wechat_req_msg_log VALUES ('4028818c496159e301496162ea5d0004', '2012-09-28 19:31:00', null, 'fromUser', '2014-10-30 22:08:32', '1234567890123456', 'text', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[2]]></Content>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-10-30 22:08:32', '<xml>\n  <Content><![CDATA[2222222222222222]]></Content>\n  <ToUserName><![CDATA[fromUser]]></ToUserName>\n  <FromUserName><![CDATA[toUser]]></FromUserName>\n  <CreateTime><![CDATA[0]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', 'toUser', '');
INSERT INTO wechat_req_msg_log VALUES ('4028c681496c1a9201496c1d70e20001', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-02 00:08:29', '1234567890123456', 'text', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[this is a test]]></Content>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-11-02 00:08:29', '<xml>\r\n<ToUserName><![CDATA[fromUser]]></ToUserName>\r\n<FromUserName><![CDATA[toUser]]></FromUserName>\r\n<CreateTime>1414858109260</CreateTime>\r\n<MsgType><![CDATA[text]]></MsgType>\r\n<Content><![CDATA[系统暂时无法响应次消息]]></Content>\r\n</xml>', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c681496c2bb701496c336f510000', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-02 00:32:30', '1234567890123456', 'text', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[1]]></Content>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-11-02 00:32:30', '<xml>\n  <Content><![CDATA[111111111]]></Content>\n  <ToUserName><![CDATA[]]></ToUserName>\n  <FromUserName><![CDATA[]]></FromUserName>\n  <CreateTime><![CDATA[0]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c681496c4a9f01496c52a5be0002', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-02 01:06:36', '1234567890123456', 'text', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[3]]></Content>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-11-02 01:06:36', '<xml><ToUserName><![CDATA[]]></ToUserName><FromUserName><![CDATA[]]></FromUserName><CreateTime><![CDATA[]]></CreateTime><MsgType><![CDATA[news]]></MsgType><ArticleCount>1</ArticleCount><Articles><item><Title><![CDATA[1111111111111111111111111]]></Title><Description><![CDATA[1111111111111111111111111111111111111111111111111111111111111111111111]]></Description><PicUrl><![CDATA[http://localhost/wechat/upload/images/material/20141102/15471414861124482.jpg]]></PicUrl><Url><![CDATA[http://www.baidu.com]]></Url></item></Articles></xml>', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c681498b186c01498b28d0340000', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-08 00:49:08', '1234567890123456', 'text', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[this is a test]]></Content>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-11-08 00:49:08', '', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c681498b186c01498b29a98b0001', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-08 00:50:03', '1234567890123456', 'text', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[this is a test]]></Content>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-11-08 00:50:03', '<xml>\r\n<ToUserName><![CDATA[fromUser]]></ToUserName>\r\n<FromUserName><![CDATA[toUser]]></FromUserName>\r\n<CreateTime>1415379003823</CreateTime>\r\n<MsgType><![CDATA[text]]></MsgType>\r\n<Content><![CDATA[发送的消息无效，接口未激活！]]></Content>\r\n</xml>', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c681498b186c01498b2b05df0002', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-08 00:51:32', '1234567890123456', 'text', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[this is a test]]></Content>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-11-08 00:52:37', '<xml>\r\n<ToUserName><![CDATA[fromUser]]></ToUserName>\r\n<FromUserName><![CDATA[toUser]]></FromUserName>\r\n<CreateTime>1415379157739</CreateTime>\r\n<MsgType><![CDATA[text]]></MsgType>\r\n<Content><![CDATA[发送的消息无效，接口未激活！]]></Content>\r\n</xml>', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c681498b2fc701498b3019020000', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-08 00:57:05', '1234567890123456', 'text', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[this is a test]]></Content>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-11-08 00:57:05', '<xml>\r\n<ToUserName><![CDATA[fromUser]]></ToUserName>\r\n<FromUserName><![CDATA[toUser]]></FromUserName>\r\n<CreateTime>1415379425604</CreateTime>\r\n<MsgType><![CDATA[text]]></MsgType>\r\n<Content><![CDATA[发送的消息无效，接口未激活！]]></Content>\r\n</xml>', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c681498b353401498b3ae9e60000', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-08 01:08:54', '1234567890123456', 'text', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[this is a test]]></Content>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-11-08 01:08:54', '<xml>\r\n<ToUserName><![CDATA[fromUser]]></ToUserName>\r\n<FromUserName><![CDATA[toUser]]></FromUserName>\r\n<CreateTime>1415380134435</CreateTime>\r\n<MsgType><![CDATA[text]]></MsgType>\r\n<Content><![CDATA[发送的消息无效，接口未激活！]]></Content>\r\n</xml>', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c681498b353401498b3b99b00001', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-08 01:09:39', '1234567890123456', 'text', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[45812]]></Content>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-11-08 01:09:39', '<xml>\r\n<ToUserName><![CDATA[fromUser]]></ToUserName>\r\n<FromUserName><![CDATA[toUser]]></FromUserName>\r\n<CreateTime>1415380179391</CreateTime>\r\n<MsgType><![CDATA[text]]></MsgType>\r\n<Content><![CDATA[验证成功，您的公众账号已成功对接。]]></Content>\r\n</xml>', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c681498ff66901498ff9626c0001', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-08 23:15:25', '1234567890123456', 'location', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[location]]></MsgType>\n<Location_X>23.134521</Location_X>\n<Location_Y>113.358803</Location_Y>\n<Scale>20</Scale>\n<Label><![CDATA[位置信息]]></Label>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-11-08 23:15:25', '', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c681499001fa0149900222610000', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-08 23:24:59', '1234567890123456', 'location', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[location]]></MsgType>\n<Location_X>23.134521</Location_X>\n<Location_Y>113.358803</Location_Y>\n<Scale>20</Scale>\n<Label><![CDATA[位置信息]]></Label>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-11-08 23:25:00', '<xml>\n  <Content><![CDATA[广州市天气情况\r\n\r\n时间：周六 11月08日 (实时：17℃)\r\n天气状况：阴\r\n风力：微风\r\n温度：17℃\r\n==============\r\n时间：周日\r\n天气状况：阴\r\n风力：微风\r\n温度：22 ~ 17℃\r\n==============\r\n时间：周一\r\n天气状况：阴转多云\r\n风力：微风\r\n温度：22 ~ 18℃\r\n==============\r\n时间：周二\r\n天气状况：多云转阴\r\n风力：微风\r\n温度：23 ~ 18℃\r\n==============]]></Content>\n  <ToUserName><![CDATA[fromUser]]></ToUserName>\n  <FromUserName><![CDATA[toUser]]></FromUserName>\n  <CreateTime><![CDATA[1415460299432]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c681499001fa014990039f6a0001', '2012-09-28 19:31:00', null, 'fromUsers', '2014-11-08 23:26:36', '1234567890123456', 'location', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUsers]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[location]]></MsgType>\n<Location_X>23.134521</Location_X>\n<Location_Y>113.358803</Location_Y>\n<Scale>20</Scale>\n<Label><![CDATA[位置信息]]></Label>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-11-08 23:26:36', '', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c681499001fa0149900400860002', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-08 23:27:01', '1234567890123456', 'location', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[location]]></MsgType>\n<Location_X>23.134521</Location_X>\n<Location_Y>113.358803</Location_Y>\n<Scale>20</Scale>\n<Label><![CDATA[位置信息]]></Label>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-11-08 23:27:02', '<xml>\n  <Content><![CDATA[广州市天气情况\r\n\r\n时间：周六 11月08日 (实时：17℃)\r\n天气状况：阴\r\n风力：微风\r\n温度：17℃\r\n==============\r\n时间：周日\r\n天气状况：阴\r\n风力：微风\r\n温度：22 ~ 17℃\r\n==============\r\n时间：周一\r\n天气状况：阴转多云\r\n风力：微风\r\n温度：22 ~ 18℃\r\n==============\r\n时间：周二\r\n天气状况：多云转阴\r\n风力：微风\r\n温度：23 ~ 18℃\r\n==============]]></Content>\n  <ToUserName><![CDATA[fromUser]]></ToUserName>\n  <FromUserName><![CDATA[toUser]]></FromUserName>\n  <CreateTime><![CDATA[1415460421781]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c6814990122c01499033da51001c', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-09 00:19:17', '1234567890123456', 'location', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[location]]></MsgType>\n<Location_X>23.134521</Location_X>\n<Location_Y>113.358803</Location_Y>\n<Scale>20</Scale>\n<Label><![CDATA[位置信息]]></Label>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-11-09 00:19:18', '<xml>\n  <Content><![CDATA[广州市天气情况\r\n\r\n时间：周日 11月09日\r\n天气状况：阴\r\n风力：微风\r\n温度：17℃\r\n==============\r\n时间：周一\r\n天气状况：阴转多云\r\n风力：微风\r\n温度：22 ~ 18℃\r\n==============\r\n时间：周二\r\n天气状况：多云转阴\r\n风力：微风\r\n温度：23 ~ 18℃\r\n==============\r\n时间：周三\r\n天气状况：阴\r\n风力：微风\r\n温度：22 ~ 17℃\r\n==============]]></Content>\n  <ToUserName><![CDATA[fromUser]]></ToUserName>\n  <FromUserName><![CDATA[toUser]]></FromUserName>\n  <CreateTime><![CDATA[1415463557751]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c6814990122c014990340c51001f', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-09 00:19:30', '1234567890123456', 'location', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[location]]></MsgType>\n<Location_X>23.134521</Location_X>\n<Location_Y>113.358803</Location_Y>\n<Scale>20</Scale>\n<Label><![CDATA[位置信息]]></Label>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-11-09 00:19:30', '<xml>\n  <Content><![CDATA[啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊]]></Content>\n  <ToUserName><![CDATA[fromUser]]></ToUserName>\n  <FromUserName><![CDATA[toUser]]></FromUserName>\n  <CreateTime><![CDATA[0]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c6814990122c0149903432a20021', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-09 00:19:40', '1234567890123456', 'location', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[location]]></MsgType>\n<Location_X>23.134521</Location_X>\n<Location_Y>113.358803</Location_Y>\n<Scale>20</Scale>\n<Label><![CDATA[位置信息]]></Label>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-11-09 00:19:40', '<xml>\n  <Content><![CDATA[广州市天气情况\r\n\r\n时间：周日 11月09日\r\n天气状况：阴\r\n风力：微风\r\n温度：17℃\r\n==============\r\n时间：周一\r\n天气状况：阴转多云\r\n风力：微风\r\n温度：22 ~ 18℃\r\n==============\r\n时间：周二\r\n天气状况：多云转阴\r\n风力：微风\r\n温度：23 ~ 18℃\r\n==============\r\n时间：周三\r\n天气状况：阴\r\n风力：微风\r\n温度：22 ~ 17℃\r\n==============]]></Content>\n  <ToUserName><![CDATA[fromUser]]></ToUserName>\n  <FromUserName><![CDATA[toUser]]></FromUserName>\n  <CreateTime><![CDATA[1415463580334]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c6814990122c0149903746c30026', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-09 00:23:02', '1234567890123456', 'text', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[this is a test]]></Content>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-11-09 00:23:02', '', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c6814990122c01499038a17b0027', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-09 00:24:30', '1234567890123456', 'text', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[this is a test]]></Content>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-11-09 00:24:30', '', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c6814990122c0149903a4122002a', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-09 00:26:17', '1234567890123456', 'text', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[this is a test]]></Content>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-11-09 00:26:17', '<xml>\n  <Content><![CDATA[系统暂时无法匹配你发送的消息]]></Content>\n  <ToUserName><![CDATA[fromUser]]></ToUserName>\n  <FromUserName><![CDATA[toUser]]></FromUserName>\n  <CreateTime><![CDATA[0]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c6814990122c0149903cd3b4002b', '1973-11-30 05:33:09', 'subscribe', 'FromUser', '2014-11-09 00:29:05', '0', 'event', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[FromUser]]></FromUserName>\n<CreateTime>123456789</CreateTime>\n<MsgType><![CDATA[event]]></MsgType>\n<Event><![CDATA[subscribe]]></Event>\n</xml>', '2014-11-09 00:29:05', '', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c68149903dee0149903e402d0000', '1973-11-30 05:33:09', 'subscribe', 'FromUser', '2014-11-09 00:30:39', '0', 'event', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser2]]></ToUserName>\n<FromUserName><![CDATA[FromUser]]></FromUserName>\n<CreateTime>123456789</CreateTime>\n<MsgType><![CDATA[event]]></MsgType>\n<Event><![CDATA[subscribe]]></Event>\n</xml>', '2014-11-09 00:30:39', '', 'toUser2', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c68149903dee01499047d74e0001', '1973-11-30 05:33:09', 'subscribe', 'FromUser', '2014-11-09 00:41:07', '0', 'event', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser2]]></ToUserName>\n<FromUserName><![CDATA[FromUser]]></FromUserName>\n<CreateTime>123456789</CreateTime>\n<MsgType><![CDATA[event]]></MsgType>\n<Event><![CDATA[subscribe]]></Event>\n</xml>', '2014-11-09 00:41:07', '', 'toUser2', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c6814990488d01499048b67f0000', '1973-11-30 05:33:09', 'subscribe', 'FromUser', '2014-11-09 00:42:04', '0', 'event', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser2]]></ToUserName>\n<FromUserName><![CDATA[FromUser]]></FromUserName>\n<CreateTime>123456789</CreateTime>\n<MsgType><![CDATA[event]]></MsgType>\n<Event><![CDATA[subscribe]]></Event>\n</xml>', '2014-11-09 00:42:04', '', 'toUser2', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c68149904a030149904a2fa80000', '1973-11-30 05:33:09', 'subscribe', 'FromUser', '2014-11-09 00:43:41', '0', 'event', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser2]]></ToUserName>\n<FromUserName><![CDATA[FromUser]]></FromUserName>\n<CreateTime>123456789</CreateTime>\n<MsgType><![CDATA[event]]></MsgType>\n<Event><![CDATA[subscribe]]></Event>\n</xml>', '2014-11-09 00:43:41', '', 'toUser2', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c68149904a030149904aa2180001', '1973-11-30 05:33:09', 'subscribe', 'fromUser', '2014-11-09 00:44:10', '0', 'event', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser2]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>123456789</CreateTime>\n<MsgType><![CDATA[event]]></MsgType>\n<Event><![CDATA[subscribe]]></Event>\n</xml>', '2014-11-09 00:44:10', '<xml>\n  <Content><![CDATA[感谢你的关注！]]></Content>\n  <ToUserName><![CDATA[fromUser]]></ToUserName>\n  <FromUserName><![CDATA[toUser2]]></FromUserName>\n  <CreateTime><![CDATA[0]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', 'toUser2', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c68149904a030149904bae0d0002', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-09 00:45:19', '1234567890123456', 'image', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[image]]></MsgType>\n<PicUrl><![CDATA[视频NBA]]></PicUrl>\n<MediaId><![CDATA[media_id]]></MediaId>\n<MsgId>1234567890123456</MsgId>\n</xml>', null, null, 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c68149904a030149904ceb450003', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-09 00:46:40', '1234567890123456', 'image', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[image]]></MsgType>\n<PicUrl><![CDATA[视频NBA]]></PicUrl>\n<MediaId><![CDATA[media_id]]></MediaId>\n<MsgId>1234567890123456</MsgId>\n</xml>', null, null, 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c68149904a030149904db14d0004', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-09 00:47:31', '1234567890123456', 'image', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[image]]></MsgType>\n<PicUrl><![CDATA[视频NBA]]></PicUrl>\n<MediaId><![CDATA[media_id]]></MediaId>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-11-09 00:47:31', '', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c68149904a030149904dcbec0005', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-09 00:47:37', '1234567890123456', 'text', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[this is a test]]></Content>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-11-09 00:47:37', '<xml>\n  <Content><![CDATA[系统暂时无法匹配你发送的消息]]></Content>\n  <ToUserName><![CDATA[fromUser]]></ToUserName>\n  <FromUserName><![CDATA[toUser]]></FromUserName>\n  <CreateTime><![CDATA[0]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c68149904a030149904df41b0006', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-09 00:47:48', '1234567890123456', 'text', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[视频NBA]]></Content>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-11-09 00:47:49', '<xml>\n  <ArticleCount><![CDATA[7]]></ArticleCount>\n  <Articles>\n    <item>\n      <Title><![CDATA[NBA]]></Title>\n      <Description><![CDATA[NBA超级英雄 MV]]></Description>\n      <PicUrl><![CDATA[http://g2.ykimg.com/0100641F46545B5CBF68F317674F5498547489-DE0F-9615-4355-664A6E3B4D71]]></PicUrl>\n      <Url><![CDATA[http://v.youku.com/v_show/id_XODIwNTUxNDU2.html]]></Url>\n    </item>\n    <item>\n      <Title><![CDATA[NBA,搞笑]]></Title>\n      <Description><![CDATA[The NBA Store “Mannequin Prank”]]></Description>\n      <PicUrl><![CDATA[http://g2.ykimg.com/0100641F46545D8DD60B3C1B8C83A8B5674FD0-E04C-99B1-7C92-78B0DED5657E]]></PicUrl>\n      <Url><![CDATA[http://v.youku.com/v_show/id_XODIyMDQ5ODY4.html]]></Url>\n    </item>\n    <item>\n      <Title><![CDATA[NBA,决胜时刻,宣传,老爷爷,萝莉,横扫,篮球场]]></Title>\n      <Description><![CDATA[《NBA：决胜时刻》宣传片：老爷爷和萝莉横扫篮球场]]></Description>\n      <PicUrl><![CDATA[http://g1.ykimg.com/0100641F465459906B4C6B043BDF213B230392-0BDC-5DAA-005F-7369AFB1BCF5]]></PicUrl>\n      <Url><![CDATA[http://v.youku.com/v_show/id_XODE5MzM2NTcy.html]]></Url>\n    </item>\n    <item>\n      <Title><![CDATA[搞笑,篮球]]></Title>\n      <Description><![CDATA[搞笑时刻！看NBA球星们场外被恶搞]]></Description>\n      <PicUrl><![CDATA[http://g1.ykimg.com/0100641F46545A5579D23107F750DEB3D6CA06-8755-CF06-72DB-0279EEB3A472]]></PicUrl>\n      <Url><![CDATA[http://v.youku.com/v_show/id_XODE5ODc2ODUy.html]]></Url>\n    </item>\n    <item>\n      <Title><![CDATA[原创]]></Title>\n      <Description><![CDATA[吉特巴 歌一生 花开只等你来采 Allure Girls 美女模特内衣诱惑]]></Description>\n      <PicUrl><![CDATA[http://g2.ykimg.com/0100641F46545E3C80461C0748B972285224ED-19CC-1675-6A32-1BE5CC48C97D]]></PicUrl>\n      <Url><![CDATA[http://v.youku.com/v_show/id_XODIyNTIzMzA0.html]]></Url>\n    </item>\n    <item>\n      <Title><![CDATA[Gibson]]></Title>\n      <Description><![CDATA[5岁萌娃圆NBA之梦 过人后大力“灌篮”]]></Description>\n      <PicUrl><![CDATA[http://g3.ykimg.com/0100641F465434CBCFCADB1A52385CEFDA73A3-CC90-D861-52F8-391395D052AD]]></PicUrl>\n      <Url><![CDATA[http://v.youku.com/v_show/id_XNzk5MDM1OTA0.html]]></Url>\n    </item>\n    <item>\n      <Title><![CDATA[原创]]></Title>\n      <Description><![CDATA[吉特巴 云菲菲 李青 月夜相思情]]></Description>\n      <PicUrl><![CDATA[http://g2.ykimg.com/0100641F46545E3ECE350E0748B972D4D0B990-E552-BF9A-E8EA-AD1360766927]]></PicUrl>\n      <Url><![CDATA[http://v.youku.com/v_show/id_XODIyNDMzODcy.html]]></Url>\n    </item>\n  </Articles>\n  <ToUserName><![CDATA[fromUser]]></ToUserName>\n  <FromUserName><![CDATA[toUser]]></FromUserName>\n  <CreateTime><![CDATA[1415465268261]]></CreateTime>\n  <MsgType><![CDATA[news]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c68149904a030149904e2b260007', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-09 00:48:02', '1234567890123456', 'text', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[翻译NBA]]></Content>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-11-09 00:48:02', '<xml>\n  <Content><![CDATA[NBA]]></Content>\n  <ToUserName><![CDATA[fromUser]]></ToUserName>\n  <FromUserName><![CDATA[toUser]]></FromUserName>\n  <CreateTime><![CDATA[1415465282350]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c68149904a030149904e75df0008', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-09 00:48:21', '1234567890123456', 'text', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[翻译l love you]]></Content>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-11-09 00:48:21', '<xml>\n  <Content><![CDATA[我爱你]]></Content>\n  <ToUserName><![CDATA[fromUser]]></ToUserName>\n  <FromUserName><![CDATA[toUser]]></FromUserName>\n  <CreateTime><![CDATA[1415465301488]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c68149904a030149904e9fe30009', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-09 00:48:32', '1234567890123456', 'text', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[运势5]]></Content>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-11-09 00:48:32', '<xml>\n  <Content><![CDATA[处女座今日运势\r\n日期：2014-11-09\r\n==============\r\n综合运势：4\r\n==============\r\n爱情运势：3\r\n==============\r\n工作状况：3\r\n==============\r\n理财投资：4\r\n==============\r\n健康指数：80%\r\n==============\r\n商谈指数：77%\r\n==============\r\n幸运颜色：黄色\r\n==============\r\n幸运数字：3\r\n==============\r\n速配星座：天蝎座\r\n==============\r\n综合概述：与自己或对方兴趣相关的话题意外带来好机会的暗示。或许双方都只是业馀的专家，但在彼此经验交换中，不仅切磋出好意见，还可能引发出对另一个领域的兴趣呢。而今天在金钱方面也不妨宽大一点看待，在小地方计较的话会让人留下心胸狭窄的印象，反而是种损失。\r\n]]></Content>\n  <ToUserName><![CDATA[fromUser]]></ToUserName>\n  <FromUserName><![CDATA[toUser]]></FromUserName>\n  <CreateTime><![CDATA[1415465312234]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c68149904a030149904ec732000a', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-09 00:48:42', '1234567890123456', 'text', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[天气广州]]></Content>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-11-09 00:48:42', '<xml>\n  <Content><![CDATA[龙王休息了，请稍后再试。。。]]></Content>\n  <ToUserName><![CDATA[fromUser]]></ToUserName>\n  <FromUserName><![CDATA[toUser]]></FromUserName>\n  <CreateTime><![CDATA[1415465322300]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c68149904a030149904f1bc4000b', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-09 00:49:03', '1234567890123456', 'text', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[天气广州]]></Content>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-11-09 00:49:04', '<xml>\n  <Content><![CDATA[龙王休息了，请稍后再试。。。]]></Content>\n  <ToUserName><![CDATA[fromUser]]></ToUserName>\n  <FromUserName><![CDATA[toUser]]></FromUserName>\n  <CreateTime><![CDATA[1415465343949]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c68149904a03014990508f6e000c', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-09 00:50:39', '1234567890123456', 'text', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[天气广州]]></Content>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-11-09 00:50:39', '<xml>\n  <Content><![CDATA[龙王休息了，请稍后再试。。。]]></Content>\n  <ToUserName><![CDATA[fromUser]]></ToUserName>\n  <FromUserName><![CDATA[toUser]]></FromUserName>\n  <CreateTime><![CDATA[1415465439097]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c68149904a0301499051ed46000d', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-09 00:52:08', '1234567890123456', 'text', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[天气广州]]></Content>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-11-09 00:52:08', '<xml>\n  <Content><![CDATA[龙王休息了，请稍后再试。。。]]></Content>\n  <ToUserName><![CDATA[fromUser]]></ToUserName>\n  <FromUserName><![CDATA[toUser]]></FromUserName>\n  <CreateTime><![CDATA[1415465528654]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c68149904a030149905830c9000e', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-09 00:58:59', '1234567890123456', 'text', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[天气广州]]></Content>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-11-09 00:58:59', '<xml>\n  <Content><![CDATA[龙王休息了，请稍后再试。。。]]></Content>\n  <ToUserName><![CDATA[fromUser]]></ToUserName>\n  <FromUserName><![CDATA[toUser]]></FromUserName>\n  <CreateTime><![CDATA[1415465939154]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c68149904a030149905a067c000f', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-09 01:00:59', '1234567890123456', 'text', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[天气广州]]></Content>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-11-09 01:00:59', '<xml>\n  <Content><![CDATA[龙王休息了，请稍后再试。。。]]></Content>\n  <ToUserName><![CDATA[fromUser]]></ToUserName>\n  <FromUserName><![CDATA[toUser]]></FromUserName>\n  <CreateTime><![CDATA[1415466059396]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c68149904a030149905a64a60010', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-09 01:01:23', '1234567890123456', 'text', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[天气广州]]></Content>\n<MsgId>1234567890123456</MsgId>\n</xml>', null, null, 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c68149904a030149905b06de0011', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-09 01:02:05', '1234567890123456', 'text', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[天气广州]]></Content>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-11-09 01:02:05', '<xml>\n  <Content><![CDATA[龙王休息了，请稍后再试。。。]]></Content>\n  <ToUserName><![CDATA[fromUser]]></ToUserName>\n  <FromUserName><![CDATA[toUser]]></FromUserName>\n  <CreateTime><![CDATA[1415466125030]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c68149904a030149905bb7830012', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-09 01:02:50', '1234567890123456', 'text', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[天气广州]]></Content>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-11-09 01:02:50', '<xml>\n  <Content><![CDATA[龙王休息了，请稍后再试。。。]]></Content>\n  <ToUserName><![CDATA[fromUser]]></ToUserName>\n  <FromUserName><![CDATA[toUser]]></FromUserName>\n  <CreateTime><![CDATA[1415466170251]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c68149904a030149905c48e20013', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-09 01:03:27', '1234567890123456', 'text', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[天气广州]]></Content>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-11-09 01:03:41', '<xml>\n  <Content><![CDATA[龙王休息了，请稍后再试。。。]]></Content>\n  <ToUserName><![CDATA[fromUser]]></ToUserName>\n  <FromUserName><![CDATA[toUser]]></FromUserName>\n  <CreateTime><![CDATA[1415466207467]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c68149904a030149905d0efc0014', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-09 01:04:18', '1234567890123456', 'text', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[天气广州]]></Content>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-11-09 01:04:18', '<xml>\n  <Content><![CDATA[龙王休息了，请稍后再试。。。]]></Content>\n  <ToUserName><![CDATA[fromUser]]></ToUserName>\n  <FromUserName><![CDATA[toUser]]></FromUserName>\n  <CreateTime><![CDATA[1415466258180]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c68149904a030149905d96fa0015', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-09 01:04:52', '1234567890123456', 'text', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[天气广州]]></Content>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-11-09 01:04:53', '<xml>\n  <Content><![CDATA[龙王休息了，请稍后再试。。。]]></Content>\n  <ToUserName><![CDATA[fromUser]]></ToUserName>\n  <FromUserName><![CDATA[toUser]]></FromUserName>\n  <CreateTime><![CDATA[1415466292995]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c68149904a030149905e4e620016', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-09 01:05:39', '1234567890123456', 'text', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[天气广州]]></Content>\n<MsgId>1234567890123456</MsgId>\n</xml>', null, null, 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c68149904a030149905e83780017', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-09 01:05:53', '1234567890123456', 'text', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[天气广州]]></Content>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-11-09 01:05:53', '<xml>\n  <ToUserName><![CDATA[fromUser]]></ToUserName>\n  <FromUserName><![CDATA[toUser]]></FromUserName>\n  <CreateTime><![CDATA[1415466353537]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c68149904a030149905fdf350018', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-09 01:07:22', '1234567890123456', 'text', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[天气广州]]></Content>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-11-09 01:07:22', '<xml>\n  <Content><![CDATA[龙王休息了，请稍后再试。。。]]></Content>\n  <ToUserName><![CDATA[fromUser]]></ToUserName>\n  <FromUserName><![CDATA[toUser]]></FromUserName>\n  <CreateTime><![CDATA[1415466442557]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c68149904a03014990616cf20019', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-09 01:09:04', '1234567890123456', 'text', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[天气广州]]></Content>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-11-09 01:09:04', '<xml>\n  <Content><![CDATA[龙王休息了，请稍后再试。。。]]></Content>\n  <ToUserName><![CDATA[fromUser]]></ToUserName>\n  <FromUserName><![CDATA[toUser]]></FromUserName>\n  <CreateTime><![CDATA[1415466544380]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c68149904a030149906224c8001a', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-09 01:09:51', '1234567890123456', 'text', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[天气116.305145,39.982368]]></Content>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-11-09 01:09:51', '<xml>\n  <Content><![CDATA[龙王休息了，请稍后再试。。。]]></Content>\n  <ToUserName><![CDATA[fromUser]]></ToUserName>\n  <FromUserName><![CDATA[toUser]]></FromUserName>\n  <CreateTime><![CDATA[1415466591440]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c68149904a0301499062a2d2001b', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-09 01:10:23', '1234567890123456', 'text', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[天气116.305145,39.982368]]></Content>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-11-09 01:10:24', '<xml>\n  <Content><![CDATA[北京市天气情况\r\n\r\n时间：周日 11月09日\r\n天气状况：晴\r\n风力：微风\r\n温度：1℃\r\n==============\r\n时间：周一\r\n天气状况：霾转阴\r\n风力：微风\r\n温度：14 ~ 5℃\r\n==============\r\n时间：周二\r\n天气状况：多云转晴\r\n风力：北风4-5级\r\n温度：13 ~ 2℃\r\n==============\r\n时间：周三\r\n天气状况：晴\r\n风力：北风4-5级\r\n温度：12 ~ -1℃\r\n==============]]></Content>\n  <ToUserName><![CDATA[fromUser]]></ToUserName>\n  <FromUserName><![CDATA[toUser]]></FromUserName>\n  <CreateTime><![CDATA[1415466623706]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c6814990699701499069cf170000', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-09 01:18:13', '1234567890123456', 'text', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[天气116.305145,39.982368]]></Content>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-11-09 01:18:14', '<xml>\n  <Content><![CDATA[北京市天气情况\r\n\r\n时间：周日 11月09日\r\n天气状况：晴\r\n风力：微风\r\n温度：1℃\r\n==============\r\n时间：周一\r\n天气状况：霾转阴\r\n风力：微风\r\n温度：14 ~ 5℃\r\n==============\r\n时间：周二\r\n天气状况：多云转晴\r\n风力：北风4-5级\r\n温度：13 ~ 2℃\r\n==============\r\n时间：周三\r\n天气状况：晴\r\n风力：北风4-5级\r\n温度：12 ~ -1℃\r\n==============]]></Content>\n  <ToUserName><![CDATA[fromUser]]></ToUserName>\n  <FromUserName><![CDATA[toUser]]></FromUserName>\n  <CreateTime><![CDATA[1415467093865]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c681499069970149906a2bd10001', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-09 01:18:37', '1234567890123456', 'text', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[天气116.305145,39.982368]]></Content>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-11-09 01:18:37', '<xml>\n  <Content><![CDATA[北京市天气情况\r\n\r\n时间：周日 11月09日\r\n天气状况：晴\r\n风力：微风\r\n温度：1℃\r\n==============\r\n时间：周一\r\n天气状况：霾转阴\r\n风力：微风\r\n温度：14 ~ 5℃\r\n==============\r\n时间：周二\r\n天气状况：多云转晴\r\n风力：北风4-5级\r\n温度：13 ~ 2℃\r\n==============\r\n时间：周三\r\n天气状况：晴\r\n风力：北风4-5级\r\n温度：12 ~ -1℃\r\n==============]]></Content>\n  <ToUserName><![CDATA[fromUser]]></ToUserName>\n  <FromUserName><![CDATA[toUser]]></FromUserName>\n  <CreateTime><![CDATA[1415467117532]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c681499069970149906ab5ba0002', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-09 01:19:12', '1234567890123456', 'text', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[天气广州]]></Content>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-11-09 01:19:13', '<xml>\n  <Content><![CDATA[龙王休息了，请稍后再试。。。]]></Content>\n  <ToUserName><![CDATA[fromUser]]></ToUserName>\n  <FromUserName><![CDATA[toUser]]></FromUserName>\n  <CreateTime><![CDATA[1415467152837]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c681499069970149906b34af0003', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-09 01:19:45', '1234567890123456', 'text', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[天气广州]]></Content>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-11-09 01:19:45', '<xml>\n  <Content><![CDATA[龙王休息了，请稍后再试。。。]]></Content>\n  <ToUserName><![CDATA[fromUser]]></ToUserName>\n  <FromUserName><![CDATA[toUser]]></FromUserName>\n  <CreateTime><![CDATA[1415467185339]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c681499069970149906cd3ba0004', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-09 01:21:31', '1234567890123456', 'text', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[天气116.305145,39.982368]]></Content>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-11-09 01:21:32', '<xml>\n  <Content><![CDATA[北京市天气情况\r\n\r\n时间：周日 11月09日\r\n天气状况：晴\r\n风力：微风\r\n温度：1℃\r\n==============\r\n时间：周一\r\n天气状况：霾转阴\r\n风力：微风\r\n温度：14 ~ 5℃\r\n==============\r\n时间：周二\r\n天气状况：多云转晴\r\n风力：北风4-5级\r\n温度：13 ~ 2℃\r\n==============\r\n时间：周三\r\n天气状况：晴\r\n风力：北风4-5级\r\n温度：12 ~ -1℃\r\n==============]]></Content>\n  <ToUserName><![CDATA[fromUser]]></ToUserName>\n  <FromUserName><![CDATA[toUser]]></FromUserName>\n  <CreateTime><![CDATA[1415467291586]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c681499069970149906f36db0005', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-09 01:24:08', '1234567890123456', 'text', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[天气广州]]></Content>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-11-09 01:26:27', '<xml>\n  <Content><![CDATA[龙王休息了，请稍后再试。。。]]></Content>\n  <ToUserName><![CDATA[fromUser]]></ToUserName>\n  <FromUserName><![CDATA[toUser]]></FromUserName>\n  <CreateTime><![CDATA[1415467448042]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c6814990699701499072ee230006', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-09 01:28:11', '1234567890123456', 'text', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[天气广州]]></Content>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-11-09 01:28:14', '<xml>\n  <Content><![CDATA[龙王休息了，请稍后再试。。。]]></Content>\n  <ToUserName><![CDATA[fromUser]]></ToUserName>\n  <FromUserName><![CDATA[toUser]]></FromUserName>\n  <CreateTime><![CDATA[1415467691565]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c68149906997014990747a030007', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-09 01:29:52', '1234567890123456', 'text', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[天气广州]]></Content>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-11-09 01:29:53', '<xml>\n  <Content><![CDATA[龙王休息了，请稍后再试。。。]]></Content>\n  <ToUserName><![CDATA[fromUser]]></ToUserName>\n  <FromUserName><![CDATA[toUser]]></FromUserName>\n  <CreateTime><![CDATA[1415467792909]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c6814990778401499077adca0000', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-09 01:33:22', '1234567890123456', 'text', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[天气广州]]></Content>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-11-09 01:33:23', '<xml>\n  <Content><![CDATA[龙王休息了，请稍后再试。。。]]></Content>\n  <ToUserName><![CDATA[fromUser]]></ToUserName>\n  <FromUserName><![CDATA[toUser]]></FromUserName>\n  <CreateTime><![CDATA[1415468002841]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c6814990778401499077c98b0001', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-09 01:33:29', '1234567890123456', 'text', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[天气广州]]></Content>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-11-09 01:33:30', '<xml>\n  <Content><![CDATA[龙王休息了，请稍后再试。。。]]></Content>\n  <ToUserName><![CDATA[fromUser]]></ToUserName>\n  <FromUserName><![CDATA[toUser]]></FromUserName>\n  <CreateTime><![CDATA[1415468009880]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c6814990793801499079be100000', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-09 01:35:37', '1234567890123456', 'text', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[天气广州]]></Content>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-11-09 01:35:38', '<xml>\n  <Content><![CDATA[龙王休息了，请稍后再试。。。]]></Content>\n  <ToUserName><![CDATA[fromUser]]></ToUserName>\n  <FromUserName><![CDATA[toUser]]></FromUserName>\n  <CreateTime><![CDATA[1415468138071]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c681499079380149907eabf60001', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-09 01:41:01', '1234567890123456', 'text', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[天气广州]]></Content>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-11-09 01:41:01', '<xml>\n  <Content><![CDATA[广州天气情况\r\n\r\n时间：周日 11月09日\r\n天气状况：阴\r\n风力：微风\r\n温度：17℃\r\n==============\r\n时间：周一\r\n天气状况：阴转多云\r\n风力：微风\r\n温度：22 ~ 18℃\r\n==============\r\n时间：周二\r\n天气状况：多云转阴\r\n风力：微风\r\n温度：23 ~ 18℃\r\n==============\r\n时间：周三\r\n天气状况：阴\r\n风力：微风\r\n温度：22 ~ 17℃\r\n==============]]></Content>\n  <ToUserName><![CDATA[fromUser]]></ToUserName>\n  <FromUserName><![CDATA[toUser]]></FromUserName>\n  <CreateTime><![CDATA[1415468461059]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c681499079380149907fe3540002', '2012-09-28 19:31:00', null, 'fromUser', '2014-11-09 01:42:20', '1234567890123456', 'text', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>1348831860</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[2]]></Content>\n<MsgId>1234567890123456</MsgId>\n</xml>', '2014-11-09 01:42:20', '<xml>\n  <Content><![CDATA[2222222222222244444]]></Content>\n  <ToUserName><![CDATA[fromUser]]></ToUserName>\n  <FromUserName><![CDATA[toUser]]></FromUserName>\n  <CreateTime><![CDATA[0]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c681499334b30149933fed690000', '1973-11-30 05:33:09', 'subscribe', 'fromUser', '2014-11-09 14:31:20', '0', 'event', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser2]]></ToUserName>\n<FromUserName><![CDATA[fromUser]]></FromUserName>\n<CreateTime>123456789</CreateTime>\n<MsgType><![CDATA[event]]></MsgType>\n<Event><![CDATA[subscribe]]></Event>\n</xml>', '2014-11-09 14:31:20', '<xml>\n  <Content><![CDATA[感谢你的关注！]]></Content>\n  <ToUserName><![CDATA[fromUser]]></ToUserName>\n  <FromUserName><![CDATA[toUser2]]></FromUserName>\n  <CreateTime><![CDATA[0]]></CreateTime>\n  <MsgType><![CDATA[text]]></MsgType>\n  <FuncFlag><![CDATA[0]]></FuncFlag>\n</xml>', 'toUser2', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c681499334b3014993439b490002', '1973-11-30 05:33:09', 'subscribe', 'fromUser2', '2014-11-09 14:35:21', '0', 'event', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser2]]></FromUserName>\n<CreateTime>123456789</CreateTime>\n<MsgType><![CDATA[event]]></MsgType>\n<Event><![CDATA[subscribe]]></Event>\n</xml>', '2014-11-09 14:35:21', '', 'toUser', '4028818c4961519d0149615397f00000');
INSERT INTO wechat_req_msg_log VALUES ('4028c681499334b30149934a438b0003', '1973-11-30 05:33:09', 'subscribe', 'fromUser2', '2014-11-09 14:42:38', '0', 'event', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[fromUser2]]></FromUserName>\n<CreateTime>123456789</CreateTime>\n<MsgType><![CDATA[event]]></MsgType>\n<Event><![CDATA[subscribe]]></Event>\n</xml>', '2014-11-09 14:42:38', '', 'toUser', '4028818c4961519d0149615397f00000');

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
-- Records of wechat_resp_msg_action
-- ----------------------------
INSERT INTO wechat_resp_msg_action VALUES ('4028c681492dde1601492df5a44e0001', 'material', '', '', '2014-10-20 22:28:33', '1', 'text', null, '4028c681492dde1601492df5a42a0000', '4028c681492dd51601492dd6fdd90000');
INSERT INTO wechat_resp_msg_action VALUES ('4028c681494818e2014948aa8f630001', 'material', '', '', '2014-10-26 02:56:17', '11', 'text', null, '4028c681494818e2014948aa8f460000', '4028c681492dd51601492dd6fdd90000');
INSERT INTO wechat_resp_msg_action VALUES ('4028c6814a00dad2014a011c2e91000f', 'api', '', 'wechat_default_msg', '2014-11-30 22:30:31', null, '', '4028c6814a00dad2014a011b6cd5000d', null, '4028c681494b994701494b99aba50000');
INSERT INTO wechat_resp_msg_action VALUES ('4028c6814990122c01499022990f0011', 'material', '', '', '2014-11-09 00:00:26', '2', 'text', null, '4028c6814990122c0149902299080010', '4028c681494b994701494b99aba50000');
INSERT INTO wechat_resp_msg_action VALUES ('4028c68149b3614b0149b4002a250001', 'material', '', '', '2014-11-15 23:09:09', 'aaaa', 'text', null, '4028c68149b3614b0149b4002a130000', '4028c681494b994701494b99aba50000');
INSERT INTO wechat_resp_msg_action VALUES ('4028c681497aeaae01497b0812970000', 'api', 'CLICK', '', '2014-11-04 21:39:26', 'key_4028c681495273930149528ecd500000', 'event', '1', null, '4028c681494b994701494b99aba50000');
INSERT INTO wechat_resp_msg_action VALUES ('4028c6814990122c0149902fd4d1001b', 'material', '', '', '2014-11-09 00:14:54', '3', 'text', null, '4028c6814990122c0149902fd4c9001a', '4028c681494b994701494b99aba50000');
INSERT INTO wechat_resp_msg_action VALUES ('4028c68149c90bf60149c90e444f0005', 'material', 'subscribe', '', '2014-11-20 01:16:35', null, 'event', null, '4028c68149c90bf60149c90e44460004', '4028c681494b994701494b99aba50000');
INSERT INTO wechat_resp_msg_action VALUES ('4028c68149c90bf60149c90df7f10003', 'material', '', '', '2014-11-20 01:16:16', 'ff', 'text', null, '4028c68149c90bf60149c90df7e00002', '4028c681494b994701494b99aba50000');
INSERT INTO wechat_resp_msg_action VALUES ('4028c6814a00dad2014a011d51ae0010', 'api', '', '', '2014-11-30 22:31:46', 'b', 'text', '4028c6814a00dad2014a010597060004', null, '4028c681494b994701494b99aba50000');
INSERT INTO wechat_resp_msg_action VALUES ('4028c6814a00dad2014a0104e6030003', 'api', '', '', '2014-11-30 22:05:05', null, 'location', '4028c68149ffd2a60149ffe491ab0000', null, '4028c681494b994701494b99aba50000');

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
-- Records of wechat_sys_user
-- ----------------------------
INSERT INTO wechat_sys_user VALUES ('4028c681494b994701494b99aba50000', 'xd-fjx@qq.com', '2014-10-26 16:36:42', '1', null, '4a3e00961a08879c34f91ca0070ea2f5', '0', 'fengjx', '3641cfbb5ceb11e483ae6036dd68230b');
INSERT INTO wechat_sys_user VALUES ('4028c681495265ac0149526735890000', '466516623@qq.com', '2014-10-28 00:18:55', '1', null, '4a3e00961a08879c34f91ca0070ea2f5', '0', 'fjx', 'f31c02705df411e481cb6036dd68230b');
INSERT INTO wechat_sys_user VALUES ('4028c68149800d340149800ea1270000', 'fengjx_mt@qq.com', '2014-11-05 21:04:42', '0', null, '4a3e00961a08879c34f91ca0070ea2f5', '0', 'xd-fjx', '4ef7f5eb64ec11e4b7af6036dd68230b');

-- ----------------------------
-- Table structure for `wechat_user_group`
-- ----------------------------
DROP TABLE IF EXISTS `wechat_user_group`;
CREATE TABLE `wechat_user_group` (
  `id` varchar(32) NOT NULL,
  `in_time` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wechat_user_group
-- ----------------------------
INSERT INTO wechat_user_group VALUES ('4028c6814971421f01497156ed400000', '2014-11-03 00:29:22', 'dddff');
INSERT INTO wechat_user_group VALUES ('4028c6814976381401497639035b0000', '2014-11-03 23:14:48', 'ccc');
INSERT INTO wechat_user_group VALUES ('4028c68149c90bf60149c90ca4c60000', '2014-11-20 01:14:49', 'aaaaaa');

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
INSERT INTO wechat_user_info VALUES ('4028818c4961519d0149615397f00000', null, null, null, null, null, null, 'fromUser', null, null, null, '2014-11-02 22:59:59', 'fromUser', '4028818c4961519d0149615397f00000', '4028c6814971421f01497156ed400000', '2014-11-02 23:42:07');
INSERT INTO wechat_user_info VALUES ('4028c681499334b30149933fed9d0001', null, null, null, null, null, null, 'fromUser', null, null, null, null, null, '4028818c4961519d0149615397f00000', '4028c6814976381401497639035b0000', '2014-11-09 14:31:20');

-- ----------------------------
-- View structure for `keyword_action_view`
-- ----------------------------
DROP VIEW IF EXISTS `keyword_action_view`;
