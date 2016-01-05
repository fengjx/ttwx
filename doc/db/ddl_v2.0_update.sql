-- 1.x update to 2.x

ALTER TABLE `wechat_ext_app`
DROP COLUMN `method_name`,
DROP COLUMN `beanName`,
DROP COLUMN `methodName`,
MODIFY COLUMN `restful_url`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL AFTER `bean_name`,
MODIFY COLUMN `support_req_type`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL AFTER `is_valid`;

ALTER TABLE `wechat_menu`
ADD COLUMN `order_no`  int(2) NULL AFTER `user_id`;

ALTER TABLE `wechat_user_group`
ADD COLUMN `user_id`  varchar(32) NOT NULL AFTER `name`;

ALTER TABLE `wechat_public_account`
DROP COLUMN `ticket`,
MODIFY COLUMN `token`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL AFTER `pwd`;

ALTER TABLE `wechat_material`
ADD COLUMN `file_name`  varchar(255) NULL AFTER `msg_type`;

alter table `wechat_data_dict` rename `sys_dict`;

ALTER TABLE `wechat_user_group`
ADD COLUMN `user_id`  varchar(64) NOT NULL AFTER `name`;

alter table `wechat_data_dict` rename `sys_dict`;

ALTER TABLE `sys_dict`
MODIFY COLUMN `is_valid`  tinyint NULL DEFAULT 0 AFTER `in_time`;

ALTER TABLE `sys_dict`
MODIFY COLUMN `order_num`  tinyint(255) NULL DEFAULT 0 COMMENT '排序字段' AFTER `is_valid`;

ALTER TABLE `sys_dict`
CHANGE COLUMN `order_num` `order_no`  tinyint(255) NULL DEFAULT 0 COMMENT '排序字段' AFTER `is_valid`;

ALTER TABLE `wechat_ext_app`
MODIFY COLUMN `is_valid`  tinyint(1) NULL DEFAULT NULL AFTER `description`;

ALTER TABLE `wechat_resp_msg_action`
ADD COLUMN `fuzzy`  tinyint(2) NULL COMMENT '关键字匹配方式（1：完全匹配，2：包含，3：关键字开头，4：关键字结尾）' AFTER `key_word`;

ALTER TABLE `portal_guestbook`
DROP COLUMN `nsg`;

-- 修改唯一约束
ALTER TABLE `wechat_resp_msg_action`
DROP INDEX `key_word` ,
ADD UNIQUE INDEX `key_word` (`key_word`, `user_id`, `fuzzy`) USING BTREE ;

ALTER TABLE `wechat_resp_msg_action`
ADD COLUMN `order_no`  tinyint(2) NULL DEFAULT 1 AFTER `user_id`;

alter table `wechat_sys_user` rename `sys_user`;

ALTER TABLE `sys_user`
MODIFY COLUMN `score`  int(11) NOT NULL DEFAULT 0 AFTER `pwd`;

ALTER TABLE `sys_user`
ADD COLUMN `salt`  varchar(12) NULL COMMENT '盐' AFTER `pwd`;

ALTER TABLE `sys_user`
ADD COLUMN `is_admin`  char(1) NULL COMMENT '超级管理员' AFTER `valid_uid`;