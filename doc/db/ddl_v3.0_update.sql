-- 2.x update to 3.x

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

