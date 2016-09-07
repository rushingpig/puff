SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `app_user_cart`
-- ----------------------------
DROP TABLE IF EXISTS `app_user_cart`;
CREATE TABLE `app_user_cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` VARCHAR(32) NOT NULL COMMENT '用户id',
  `sku_id` int(10) NOT NULL COMMENT 'sku_id',
  `amount` int(10) NOT NULL COMMENT '数量',
  `greeting_card` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '所有的祝福贺卡集合',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户购物车';