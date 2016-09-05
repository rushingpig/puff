SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `app_user_cart`
-- ----------------------------
DROP TABLE IF EXISTS `app_user_cart`;
CREATE TABLE `app_user_cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` VARCHAR(32) NOT NULL,
  `sku_id` int(10) NOT NULL,
  `amount` int(10) NOT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户购物车';