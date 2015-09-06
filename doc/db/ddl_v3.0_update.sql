-- 1.x update to 2.x

ALTER TABLE 'wechat_ext_app'
DROP COLUMN 'method_name',
DROP COLUMN 'beanName',
DROP COLUMN 'methodName',
MODIFY COLUMN 'restful_url'  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL AFTER 'bean_name',
MODIFY COLUMN 'support_req_type'  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL AFTER 'is_valid';

ALTER TABLE 'wechat_menu'
ADD COLUMN 'order_no'  int(2) NULL AFTER 'user_id';

ALTER TABLE 'wechat_user_group'
ADD COLUMN 'user_id'  varchar(32) NOT NULL AFTER 'name';

ALTER TABLE 'wechat_public_account'
DROP COLUMN 'ticket',
MODIFY COLUMN 'token'  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL AFTER 'pwd';

ALTER TABLE 'wechat_material'
ADD COLUMN 'file_name'  varchar(255) NULL AFTER 'msg_type';

alter table 'wechat_data_dict' rename 'sys_dict';

ALTER TABLE 'wechat_user_group'
ADD COLUMN 'user_id'  varchar(64) NOT NULL AFTER 'name';

alter table 'wechat_data_dict' rename 'sys_dict';

ALTER TABLE `sys_dict`
MODIFY COLUMN `is_valid`  tinyint NULL DEFAULT 0 AFTER `in_time`;

ALTER TABLE `sys_dict`
MODIFY COLUMN `order_num`  tinyint(255) NULL DEFAULT 0 COMMENT 'ÅÅÐò×Ö¶Î' AFTER `is_valid`;

ALTER TABLE `sys_dict`
CHANGE COLUMN `order_num` `order_no`  tinyint(255) NULL DEFAULT 0 COMMENT 'ÅÅÐò×Ö¶Î' AFTER `is_valid`;

