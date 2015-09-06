/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 10.0.21-MariaDB : Database - test
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`test` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `test`;

/*Table structure for table `p2p_account` */

DROP TABLE IF EXISTS `p2p_account`;

CREATE TABLE `p2p_account` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `total_money` bigint(15) DEFAULT '0' COMMENT '资金总额，单位分',
  `use_money` bigint(15) DEFAULT '0' COMMENT '可用资金',
  `frost_money` bigint(15) DEFAULT '0' COMMENT '冻结资金',
  `have_interest` bigint(15) DEFAULT '0' COMMENT '已收利息',
  `wait_interest` bigint(15) DEFAULT '0' COMMENT '待收利息',
  `wait_total_money` bigint(15) DEFAULT '0' COMMENT '待收总额',
  `exp_money` bigint(15) DEFAULT '0' COMMENT '体验金',
  `exp_use_money` bigint(15) DEFAULT '0' COMMENT '已用体验金',
  `sign` varchar(255) DEFAULT NULL COMMENT '验签，备用字段',
  `is_warning` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户资金表';

/*Data for the table `p2p_account` */

/*Table structure for table `p2p_account_bank` */

DROP TABLE IF EXISTS `p2p_account_bank`;

CREATE TABLE `p2p_account_bank` (
  `bid` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '银行卡ID',
  `user_id` int(10) NOT NULL COMMENT '用户ID',
  `status` int(2) NOT NULL DEFAULT '1' COMMENT '银行卡状态(0,关闭;1,激活)',
  `cardno` varchar(24) NOT NULL COMMENT '银行卡号',
  `bank` int(10) NOT NULL COMMENT '银行ID',
  `branch` varchar(128) DEFAULT NULL COMMENT '支行名称',
  `city` int(11) DEFAULT NULL COMMENT '开户市',
  `province` int(11) DEFAULT NULL COMMENT '开户省份',
  `addtime` varchar(128) NOT NULL COMMENT '添加时间',
  `addip` varchar(128) NOT NULL COMMENT '添加IP',
  PRIMARY KEY (`bid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='银行卡表';

/*Data for the table `p2p_account_bank` */

/*Table structure for table `p2p_account_cash` */

DROP TABLE IF EXISTS `p2p_account_cash`;

CREATE TABLE `p2p_account_cash` (
  `cid` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '提现ID',
  `user_id` bigint(18) NOT NULL COMMENT '用户ID',
  `c_status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态:0未审，1通过，2失败',
  `cardno` varchar(24) NOT NULL COMMENT '银行卡号',
  `bank` int(18) DEFAULT NULL COMMENT '银行',
  `branch` varchar(255) DEFAULT NULL COMMENT '银行支行',
  `money` bigint(15) NOT NULL COMMENT '提现金额',
  `realmoney` bigint(15) DEFAULT NULL COMMENT '到账金额',
  `fee` bigint(15) DEFAULT NULL COMMENT '提现手续费',
  `verify_user` int(10) DEFAULT NULL COMMENT '提现审核人员',
  `verify_time` int(10) DEFAULT NULL COMMENT '提现审核时间',
  `verify_remark` varchar(255) DEFAULT NULL COMMENT '提现审核备注',
  `addtime` int(10) NOT NULL COMMENT '添加时间',
  `addip` varchar(15) NOT NULL COMMENT '添加IP',
  PRIMARY KEY (`cid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='提现记录表';

/*Data for the table `p2p_account_cash` */

/*Table structure for table `p2p_account_log` */

DROP TABLE IF EXISTS `p2p_account_log`;

CREATE TABLE `p2p_account_log` (
  `log_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '资金记录id',
  `user_id` int(10) DEFAULT NULL COMMENT '用户id',
  `log_money` bigint(15) DEFAULT NULL COMMENT '资金变动金额，单位分',
  `log_type` tinyint(2) DEFAULT NULL COMMENT '变动类型（1，收入；2，支出）',
  `account_type` varchar(64) DEFAULT NULL COMMENT '资金类型',
  `total_money` bigint(15) DEFAULT NULL COMMENT '用户资金总额',
  `use_money` bigint(15) DEFAULT NULL COMMENT '用户可用资金',
  `frost_money` bigint(15) DEFAULT NULL COMMENT '用户冻结资金',
  `have_interest` bigint(15) DEFAULT NULL,
  `wait_interest` bigint(15) DEFAULT NULL,
  `wait_total_money` bigint(15) DEFAULT NULL,
  `exp_money` bigint(15) DEFAULT NULL COMMENT '体验金',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `addtime` int(10) DEFAULT NULL COMMENT '变动时间',
  `addip` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资金日志表';

/*Data for the table `p2p_account_log` */

/*Table structure for table `p2p_account_recharge` */

DROP TABLE IF EXISTS `p2p_account_recharge`;

CREATE TABLE `p2p_account_recharge` (
  `rid` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '充值ID',
  `billno` varchar(128) NOT NULL COMMENT '充值订单流水',
  `user_id` int(10) NOT NULL COMMENT '充值用户id',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '充值状态，0申请，1成功，2失败，',
  `money` bigint(15) NOT NULL COMMENT '充值金额',
  `realmoney` bigint(15) NOT NULL COMMENT '实际到账金额',
  `fee` bigint(15) NOT NULL COMMENT '充值手续费',
  `type` int(11) NOT NULL COMMENT '充值类型1,线上，2，线下',
  `recharge_type` varchar(128) DEFAULT NULL COMMENT '充值类型，对应充值通道id',
  `ret` varchar(1024) DEFAULT NULL COMMENT '线上充值返回参数序列化存储',
  `verify_user` int(10) DEFAULT NULL COMMENT '审核人',
  `verify_time` int(10) DEFAULT NULL COMMENT '审核时间',
  `verify_remark` varchar(255) DEFAULT NULL COMMENT '审核备注',
  `addtime` int(10) NOT NULL COMMENT '添加时间',
  `addip` varchar(15) NOT NULL COMMENT '添加IP',
  PRIMARY KEY (`rid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `p2p_account_recharge` */

/*Table structure for table `p2p_article` */

DROP TABLE IF EXISTS `p2p_article`;

CREATE TABLE `p2p_article` (
  `article_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '文章ID',
  `article_cat_id` int(10) DEFAULT NULL COMMENT '文章分类ID',
  `user_id` int(10) DEFAULT NULL COMMENT '发布用户ID',
  `show_status` tinyint(2) NOT NULL DEFAULT '1' COMMENT '显示状态(0,不显示；1，显示)',
  `article_title` varchar(96) DEFAULT NULL COMMENT '文章标题',
  `article_pic` varchar(96) DEFAULT NULL COMMENT '文章图片',
  `article_desc` varchar(255) DEFAULT NULL COMMENT '文章简介',
  `article_content` varchar(20000) DEFAULT NULL COMMENT '文章内容',
  `acc_num` int(10) DEFAULT NULL COMMENT '访问次数',
  `sort` int(5) DEFAULT '0' COMMENT '排序权重',
  `addtime` int(10) DEFAULT NULL COMMENT '添加时间',
  `addip` varchar(15) DEFAULT NULL COMMENT '文章添加ip',
  `updatetime` int(10) DEFAULT NULL COMMENT '修改时间',
  `issupport` tinyint(2) DEFAULT '0' COMMENT '是否推荐（0，默认；1，推荐；）',
  PRIMARY KEY (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章表';

/*Data for the table `p2p_article` */

/*Table structure for table `p2p_articlecat` */

DROP TABLE IF EXISTS `p2p_articlecat`;

CREATE TABLE `p2p_articlecat` (
  `article_cat_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '文章分类ID',
  `article_cat_name` varchar(64) DEFAULT NULL COMMENT '文章分类名称',
  `article_cat_alias` varchar(32) DEFAULT NULL COMMENT '类别别名',
  `article_cat_desc` varchar(255) DEFAULT NULL COMMENT '文章分类简介',
  `cat_type` tinyint(1) DEFAULT NULL COMMENT '栏目类别（1，列表页；2，单页3，封面页）',
  `show_status` tinyint(1) DEFAULT NULL COMMENT '是否显示（0，不显示；1，显示）',
  `p_id` int(10) DEFAULT NULL COMMENT '父栏目id',
  `tree` varchar(256) DEFAULT NULL COMMENT '子分类等级',
  `list_tmp_path` varchar(128) DEFAULT NULL COMMENT '列表模板路径',
  `page_tmp_path` varchar(128) DEFAULT NULL COMMENT '内容模板路径',
  `special_tmp_path` varchar(128) DEFAULT NULL COMMENT '专题模板路径',
  `sort` int(8) DEFAULT NULL COMMENT '排序',
  `pub_user_id` int(10) DEFAULT NULL COMMENT '发布用户ID',
  `addtime` int(10) DEFAULT NULL COMMENT '添加时间',
  `updatetime` int(10) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`article_cat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章目录表';

/*Data for the table `p2p_articlecat` */

/*Table structure for table `p2p_user` */

DROP TABLE IF EXISTS `p2p_user`;

CREATE TABLE `p2p_user` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(16) NOT NULL COMMENT '用户登录名',
  `login_pass` varchar(32) NOT NULL COMMENT '登陆密码',
  `pay_pass` varchar(32) DEFAULT NULL COMMENT '支付密码',
  `email` varchar(64) NOT NULL COMMENT '用户邮箱',
  `phone` varchar(11) DEFAULT NULL COMMENT '用户手机',
  `home_tel` varchar(16) DEFAULT NULL,
  `user_qq` bigint(20) DEFAULT NULL COMMENT '用户QQ',
  `user_pic` varchar(64) DEFAULT NULL COMMENT '用户头像',
  `real_name` varchar(16) DEFAULT NULL COMMENT '真实姓名',
  `card_num` varchar(30) DEFAULT '' COMMENT '证件号码',
  `user_sex` tinyint(1) unsigned DEFAULT NULL COMMENT '用户性别（0，未知；1，男；2，女）',
  `user_age` smallint(5) unsigned DEFAULT NULL COMMENT '用户年龄',
  `user_edu` tinyint(1) DEFAULT NULL COMMENT '用户学历（0，未知；1，小学；2，初中；3，高中/高专；4，大专；5，本科；6，硕士/博士/及以上）',
  `birth_place` varchar(128) DEFAULT NULL COMMENT '出生地',
  `live_place` varchar(128) DEFAULT NULL COMMENT '居住地',
  `user_address` varchar(128) DEFAULT NULL COMMENT '用户联系地址',
  `invite_user_id` int(11) DEFAULT NULL COMMENT '父id/推荐人id',
  `user_type` tinyint(2) DEFAULT '0' COMMENT '用户类型（0，普通注册用户；1，手机注册用户2，后台手动添加用户）',
  `is_email_check` tinyint(1) DEFAULT '0' COMMENT '邮箱认证（0，未认证；1已认证）',
  `is_phone_check` tinyint(1) DEFAULT '0' COMMENT '手机认证（0，未认证；1已认证）',
  `is_realname_check` tinyint(1) DEFAULT '0' COMMENT '实名认证（0，未认证；1已认证）',
  `is_safequestion_check` tinyint(1) DEFAULT '0' COMMENT '密保问题（0，未设置；1，已设置）',
  `vip_stop_time` int(10) DEFAULT NULL COMMENT 'VIP到期时间',
  `is_lock` tinyint(1) DEFAULT '0' COMMENT '账户锁定（0，正常；1，锁定）',
  `register_time` int(10) DEFAULT NULL COMMENT '注册时间',
  `login_time` int(10) DEFAULT NULL COMMENT '登录时间',
  `register_ip` varchar(15) DEFAULT NULL,
  `invite_num` int(11) DEFAULT '0' COMMENT '邀请人数',
  PRIMARY KEY (`user_id`),
  KEY `i_username` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `p2p_user` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
