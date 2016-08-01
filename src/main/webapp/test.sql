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
  `useid` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `totalmoney` bigint(15) NOT NULL DEFAULT '0' COMMENT '资金总额，单位分',
  `usemoney` bigint(15) DEFAULT '0' COMMENT '可用资金',
  `frostmoney` bigint(15) DEFAULT '0' COMMENT '冻结资金',
  `haveinterest` bigint(15) DEFAULT '0' COMMENT '已收利息',
  `waitinterest` bigint(15) DEFAULT '0' COMMENT '待收利息',
  `waittotalmoney` bigint(15) DEFAULT '0' COMMENT '待收总额',
  `expmoney` bigint(15) DEFAULT '0' COMMENT '体验金',
  `expusemoney` bigint(15) DEFAULT '0' COMMENT '已用体验金',
  `sign` varchar(255) DEFAULT NULL COMMENT '验签，备用字段',
  `warning` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`useid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户资金表';

/*Data for the table `p2p_account` */

/*Table structure for table `p2p_account_bank` */

DROP TABLE IF EXISTS `p2p_account_bank`;

CREATE TABLE `p2p_account_bank` (
  `bid` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '银行卡ID',
  `userid` int(10) NOT NULL COMMENT '用户ID',
  `status` int(2) NOT NULL DEFAULT '1' COMMENT '银行卡状态(0,关闭;1,激活)',
  `cardno` varchar(24) NOT NULL COMMENT '银行卡号',
  `bank` int(10) NOT NULL COMMENT '银行ID',
  `branch` varchar(128) DEFAULT NULL COMMENT '支行名称',
  `city` int(11) DEFAULT NULL COMMENT '开户市',
  `province` int(11) DEFAULT NULL COMMENT '开户省份',
  `addtime` int(10) NOT NULL COMMENT '添加时间',
  `addip` varchar(15) DEFAULT NULL COMMENT '添加IP',
  PRIMARY KEY (`bid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='银行卡表';

/*Data for the table `p2p_account_bank` */

/*Table structure for table `p2p_account_cash` */

DROP TABLE IF EXISTS `p2p_account_cash`;

CREATE TABLE `p2p_account_cash` (
  `cid` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '提现ID',
  `userid` int(10) NOT NULL COMMENT '用户ID',
  `cashstatus` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态:0未审，1通过，2失败',
  `cardno` varchar(24) NOT NULL COMMENT '银行卡号',
  `bank` int(18) DEFAULT NULL COMMENT '银行',
  `branch` varchar(255) DEFAULT NULL COMMENT '银行支行',
  `money` bigint(15) NOT NULL COMMENT '提现金额',
  `realmoney` bigint(15) DEFAULT NULL COMMENT '到账金额',
  `fee` int(10) DEFAULT NULL COMMENT '提现手续费',
  `verifyuser` int(10) DEFAULT NULL COMMENT '提现审核人员',
  `verifytime` int(10) DEFAULT NULL COMMENT '提现审核时间',
  `verifyremark` varchar(255) DEFAULT NULL COMMENT '提现审核备注',
  `addtime` int(10) NOT NULL COMMENT '添加时间',
  `addip` varchar(15) NOT NULL COMMENT '添加IP',
  PRIMARY KEY (`cid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='提现记录表';

/*Data for the table `p2p_account_cash` */

/*Table structure for table `p2p_account_log` */

DROP TABLE IF EXISTS `p2p_account_log`;

CREATE TABLE `p2p_account_log` (
  `logid` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '资金记录id',
  `userid` int(10) DEFAULT NULL COMMENT '用户id',
  `logmoney` bigint(15) DEFAULT NULL COMMENT '资金变动金额，单位分',
  `logtype` tinyint(2) DEFAULT NULL COMMENT '变动类型（1，收入；2，支出）',
  `accounttype` varchar(64) DEFAULT NULL COMMENT '资金类型',
  `totalmoney` bigint(15) DEFAULT NULL COMMENT '用户资金总额',
  `usemoney` bigint(15) DEFAULT NULL COMMENT '用户可用资金',
  `frostmoney` bigint(15) DEFAULT NULL COMMENT '用户冻结资金',
  `haveinterest` bigint(15) DEFAULT NULL,
  `waitinterest` bigint(15) DEFAULT NULL,
  `waittotalmoney` bigint(15) DEFAULT NULL,
  `expmoney` int(15) DEFAULT NULL COMMENT '体验金',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `addtime` int(10) DEFAULT NULL COMMENT '变动时间',
  `addip` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`logid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资金日志表';

/*Data for the table `p2p_account_log` */

/*Table structure for table `p2p_account_recharge` */

DROP TABLE IF EXISTS `p2p_account_recharge`;

CREATE TABLE `p2p_account_recharge` (
  `rid` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '充值ID',
  `billno` varchar(128) NOT NULL COMMENT '充值订单流水',
  `userid` int(10) NOT NULL COMMENT '充值用户id',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '充值状态，0申请，1成功，2失败，',
  `money` bigint(15) NOT NULL COMMENT '充值金额',
  `realmoney` bigint(15) NOT NULL COMMENT '实际到账金额',
  `fee` int(15) NOT NULL COMMENT '充值手续费',
  `type` int(11) NOT NULL COMMENT '充值类型1,线上，2，线下',
  `rechargetype` varchar(128) DEFAULT NULL COMMENT '充值类型，对应充值通道id',
  `ret` varchar(1024) DEFAULT NULL COMMENT '线上充值返回参数序列化存储',
  `verifyuser` int(10) DEFAULT NULL COMMENT '审核人',
  `verifytime` int(10) DEFAULT NULL COMMENT '审核时间',
  `verifyremark` varchar(255) DEFAULT NULL COMMENT '审核备注',
  `addtime` int(10) NOT NULL COMMENT '添加时间',
  `addip` varchar(15) NOT NULL COMMENT '添加IP',
  PRIMARY KEY (`rid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `p2p_account_recharge` */

/*Table structure for table `p2p_article` */

DROP TABLE IF EXISTS `p2p_article`;

CREATE TABLE `p2p_article` (
  `articleid` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '文章ID',
  `articlecatid` int(10) DEFAULT NULL COMMENT '文章分类ID',
  `userid` int(10) DEFAULT NULL COMMENT '发布用户ID',
  `showstatus` tinyint(2) NOT NULL DEFAULT '1' COMMENT '显示状态(0,不显示；1，显示)',
  `articletitle` varchar(96) DEFAULT NULL COMMENT '文章标题',
  `articlepic` varchar(96) DEFAULT NULL COMMENT '文章图片',
  `articledesc` varchar(255) DEFAULT NULL COMMENT '文章简介',
  `articlecontent` varchar(20000) DEFAULT NULL COMMENT '文章内容',
  `accnum` int(10) DEFAULT NULL COMMENT '访问次数',
  `sort` int(5) DEFAULT '0' COMMENT '排序权重',
  `addtime` int(10) DEFAULT NULL COMMENT '添加时间',
  `addip` varchar(15) DEFAULT NULL COMMENT '文章添加ip',
  `updatetime` int(10) DEFAULT NULL COMMENT '修改时间',
  `support` tinyint(2) DEFAULT '0' COMMENT '是否推荐（0，默认；1，推荐；）',
  PRIMARY KEY (`articleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章表';

/*Data for the table `p2p_article` */

/*Table structure for table `p2p_articlecat` */

DROP TABLE IF EXISTS `p2p_articlecat`;

CREATE TABLE `p2p_articlecat` (
  `articlecatid` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '文章分类ID',
  `articlecatname` varchar(64) DEFAULT NULL COMMENT '文章分类名称',
  `articlecatalias` varchar(32) DEFAULT NULL COMMENT '类别别名',
  `articlecatdesc` varchar(255) DEFAULT NULL COMMENT '文章分类简介',
  `cattype` tinyint(1) DEFAULT NULL COMMENT '栏目类别（1，列表页；2，单页3，封面页）',
  `showstatus` tinyint(1) DEFAULT NULL COMMENT '是否显示（0，不显示；1，显示）',
  `pid` int(10) DEFAULT NULL COMMENT '父栏目id',
  `tree` varchar(256) DEFAULT NULL COMMENT '子分类等级',
  `listtmppath` varchar(128) DEFAULT NULL COMMENT '列表模板路径',
  `pagetmppath` varchar(128) DEFAULT NULL COMMENT '内容模板路径',
  `specialtmppath` varchar(128) DEFAULT NULL COMMENT '专题模板路径',
  `sort` int(8) DEFAULT NULL COMMENT '排序',
  `pubuserid` int(10) DEFAULT NULL COMMENT '发布用户ID',
  `addtime` int(10) DEFAULT NULL COMMENT '添加时间',
  `updatetime` int(10) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`articlecatid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章目录表';

/*Data for the table `p2p_articlecat` */

/*Table structure for table `p2p_code` */

DROP TABLE IF EXISTS `p2p_code`;

CREATE TABLE `p2p_code` (
  `codeid` int(10) unsigned NOT NULL COMMENT '验证码编号',
  `senceid` tinyint(10) unsigned DEFAULT NULL COMMENT '使用场景',
  `target` varchar(32) NOT NULL COMMENT '用户手机',
  `code` varchar(6) NOT NULL COMMENT '验证码',
  `status` tinyint(1) unsigned NOT NULL COMMENT '状态（0，未使用；1，已使用）',
  `addtime` int(10) unsigned NOT NULL COMMENT '验证码加入时间',
  `exctime` int(10) unsigned DEFAULT NULL COMMENT '过期时间',
  `errornum` tinyint(1) unsigned DEFAULT NULL COMMENT '失败次数',
  `addip` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`codeid`),
  KEY `userphone` (`target`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='验证码表，便于接收不到查询';

/*Data for the table `p2p_code` */

/*Table structure for table `p2p_project` */

DROP TABLE IF EXISTS `p2p_project`;

CREATE TABLE `p2p_project` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `proid` bigint(20) NOT NULL COMMENT '逻辑主键',
  `uid` bigint(20) DEFAULT NULL COMMENT '借款人ID',
  `proname` varchar(256) NOT NULL COMMENT '借款标题',
  `proaccount` int(11) NOT NULL COMMENT '借款金额，单位分',
  `proaccountyes` int(11) DEFAULT NULL COMMENT '已投标金额，单位分',
  `protimelimit` int(11) DEFAULT NULL COMMENT '借款期限',
  `protimeuint` tinyint(4) DEFAULT NULL COMMENT '借款期限类型，1为天，2为月',
  `provalidtime` int(11) DEFAULT NULL COMMENT '借款有效时间',
  `proapr` double DEFAULT NULL COMMENT '借款利率',
  `prostyle` tinyint(4) DEFAULT NULL COMMENT '还款方式',
  `prostatus` tinyint(4) DEFAULT NULL COMMENT '标的状态 0初始1初审成功，投标中2初审失败3复审成功4复审失败5流标6撤销7正常结束',
  `proverifyuser` int(11) DEFAULT NULL COMMENT '审标人',
  `proverifytime` int(11) DEFAULT NULL COMMENT '初审时间',
  `proverifyremark` varchar(256) DEFAULT NULL COMMENT '初审备注',
  `profullverifyuser` int(11) DEFAULT NULL COMMENT '满标审核人',
  `profullverifytime` int(11) DEFAULT NULL COMMENT '满标审核时间',
  `profullverifyremark` varchar(256) DEFAULT NULL COMMENT '满标审核备注',
  `protype` tinyint(4) DEFAULT NULL COMMENT '1信用标2担保标3抵押标4秒标5定向标',
  `prodxb` varchar(32) DEFAULT NULL COMMENT '定向标密码',
  `prodesc` varchar(20000) DEFAULT NULL COMMENT '标的详细描述',
  `proawardtype` tinyint(4) DEFAULT NULL COMMENT '奖励类型 1百分比2固定金额',
  `proaward` double DEFAULT NULL COMMENT '奖励的具体数值',
  `prolowaccount` int(11) DEFAULT NULL COMMENT '最低要求投资金额',
  `promostaccount` int(11) DEFAULT NULL COMMENT '标的最高允许投标金额',
  `successtime` int(11) DEFAULT NULL COMMENT '满标时间',
  `endtime` int(11) DEFAULT NULL COMMENT '根据有效期算出的截止时间',
  `ordernum` mediumint(9) DEFAULT NULL COMMENT '投标次数',
  `autoratio` int(11) DEFAULT NULL COMMENT '允许的自动投标比例，百分比',
  `repayment` int(11) DEFAULT NULL COMMENT '还款金额',
  `repaymentyes` int(11) DEFAULT NULL COMMENT '已还金额',
  `expansion` varchar(512) DEFAULT NULL COMMENT '扩充字段',
  `addtime` int(11) DEFAULT NULL,
  `addip` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `bid` (`proid`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='借款标基本信息';

/*Data for the table `p2p_project` */

insert  into `p2p_project`(`id`,`proid`,`uid`,`proname`,`proaccount`,`proaccountyes`,`protimelimit`,`protimeuint`,`provalidtime`,`proapr`,`prostyle`,`prostatus`,`proverifyuser`,`proverifytime`,`proverifyremark`,`profullverifyuser`,`profullverifytime`,`profullverifyremark`,`protype`,`prodxb`,`prodesc`,`proawardtype`,`proaward`,`prolowaccount`,`promostaccount`,`successtime`,`endtime`,`ordernum`,`autoratio`,`repayment`,`repaymentyes`,`expansion`,`addtime`,`addip`) values (1,1400012001,0,'借钱给我儿子',8000000,50000,3,2,7,15,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1465133455,'127.0.0.1'),(2,1400012002,0,'想买一部苹果笔记本',1200000,0,50,1,5,12,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(3,1400012003,0,'宁波东风日产汽车抵押贷款45000元',4500000,0,3,2,7,15,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(4,1400012004,0,'借钱买车',8000000,0,3,2,7,15,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(5,1400012005,0,'想买一部苹果笔记本',1200000,0,50,1,5,12,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(6,1400012006,0,'宁波东风日产汽车抵押贷款45000元',4500000,0,3,2,7,15,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(7,1400012007,0,'借钱买车',8000000,0,3,2,7,15,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(8,1400012008,0,'想买一部苹果笔记本',1200000,0,50,1,5,12,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(9,1400012009,0,'借钱买车',8000000,0,3,2,7,15,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(10,14000120010,0,'想买一部苹果笔记本',1200000,0,50,1,5,12,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(11,14000120011,0,'宁波东风日产汽车抵押贷款45000元',4500000,0,3,2,7,15,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(12,14000120012,0,'借钱买车',8000000,0,3,2,7,15,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(13,14000120013,0,'想买一部苹果笔记本',1200000,0,50,1,5,12,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(14,14000120014,0,'宁波东风日产汽车抵押贷款45000元',4500000,0,3,2,7,15,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(15,14000120015,0,'借钱买车',8000000,0,3,2,7,15,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(16,14000120016,0,'想买一部苹果笔记本',1200000,0,50,1,5,12,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(17,14000120017,0,'宁波东风日产汽车抵押贷款45000元',4500000,0,3,2,7,15,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(18,14000120018,0,'借钱买车',8000000,0,3,2,7,15,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(19,14000120019,0,'想买一部苹果笔记本',1200000,0,50,1,5,12,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(20,14000120020,0,'宁波东风日产汽车抵押贷款45000元',4500000,0,3,2,7,15,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(21,14000120021,0,'借钱买车',8000000,0,3,2,7,15,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(22,14000120022,0,'想买一部苹果笔记本',1200000,0,50,1,5,12,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(23,14000120023,0,'宁波东风日产汽车抵押贷款45000元',4500000,0,3,2,7,15,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `p2p_project_apply` */

DROP TABLE IF EXISTS `p2p_project_apply`;

CREATE TABLE `p2p_project_apply` (
  `aid` bigint(18) NOT NULL DEFAULT '0',
  `userid` bigint(18) DEFAULT NULL COMMENT '用户id',
  `name` varchar(255) DEFAULT NULL COMMENT '用户名',
  `money` int(11) NOT NULL COMMENT '借款金额',
  `timelimit` int(11) DEFAULT NULL COMMENT '借款时长',
  `phone` varchar(16) DEFAULT NULL COMMENT '联系号码',
  `realname` varchar(32) DEFAULT NULL COMMENT '真实姓名',
  `province` int(11) DEFAULT NULL COMMENT '城市',
  `city` int(11) DEFAULT NULL COMMENT '省份',
  `status` int(2) NOT NULL DEFAULT '0',
  `verifyuser` bigint(18) DEFAULT NULL,
  `verifyremark` varchar(255) DEFAULT NULL,
  `verifytime` varchar(128) DEFAULT NULL,
  `addtime` varchar(128) DEFAULT NULL COMMENT '添加时间',
  `addip` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='借款意向申请表';

/*Data for the table `p2p_project_apply` */

/*Table structure for table `p2p_project_collect` */

DROP TABLE IF EXISTS `p2p_project_collect`;

CREATE TABLE `p2p_project_collect` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `porder` tinyint(4) NOT NULL COMMENT '待收期数',
  `status` tinyint(4) NOT NULL COMMENT '0未收1已还款2待还',
  `projectid` bigint(18) NOT NULL COMMENT '项目id',
  `orderid` int(10) NOT NULL COMMENT '投标id',
  `repaytime` int(11) DEFAULT NULL COMMENT '应收日期',
  `repayyestime` int(11) DEFAULT NULL COMMENT '实收日期',
  `repayaccount` int(11) DEFAULT NULL COMMENT '应收金额',
  `repayyesaccount` int(11) NOT NULL COMMENT '实收金额',
  `interest` int(11) NOT NULL COMMENT '应收利息',
  `capital` int(11) NOT NULL COMMENT '应收本金',
  `latedays` smallint(11) DEFAULT '0' COMMENT '逾期天数',
  `lateinterest` int(11) NOT NULL COMMENT '逾期罚息',
  `addtime` int(11) NOT NULL COMMENT '添加时间',
  `addip` varchar(15) NOT NULL COMMENT '添加IP',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代收表';

/*Data for the table `p2p_project_collect` */

/*Table structure for table `p2p_project_order` */

DROP TABLE IF EXISTS `p2p_project_order`;

CREATE TABLE `p2p_project_order` (
  `oid` int(18) unsigned NOT NULL AUTO_INCREMENT,
  `userid` int(18) DEFAULT NULL,
  `status` int(11) NOT NULL COMMENT '1全部通过 2部分通过',
  `projectid` bigint(18) NOT NULL COMMENT '标id',
  `money` int(11) NOT NULL DEFAULT '0' COMMENT '投标金额',
  `realmoney` int(11) NOT NULL DEFAULT '0' COMMENT '实际投标金额',
  `repayaccount` int(11) NOT NULL DEFAULT '0' COMMENT '应收款总额',
  `repayyesaccount` int(11) NOT NULL DEFAULT '0' COMMENT '已还款总额',
  `waitrepay` int(11) NOT NULL DEFAULT '0' COMMENT '待还总额',
  `interest` int(11) NOT NULL DEFAULT '0' COMMENT '应收利息总额',
  `yesinterest` int(11) NOT NULL DEFAULT '0' COMMENT '已收利息总额',
  `waitinterest` int(11) NOT NULL DEFAULT '0' COMMENT '待还利息',
  `type` int(2) NOT NULL DEFAULT '0' COMMENT '投标类型（0，普通pc投标；1，自动投标；2，手机wap投标）',
  `award` int(11) NOT NULL COMMENT '奖励金额',
  `mold` tinyint(1) NOT NULL DEFAULT '0' COMMENT '1,一马当先,2,一锤定音 ,3,一枝独秀',
  `addtime` int(10) NOT NULL COMMENT '添加时间',
  `addip` varchar(15) NOT NULL COMMENT '添加IP',
  PRIMARY KEY (`oid`),
  KEY `pid` (`projectid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

/*Data for the table `p2p_project_order` */

/*Table structure for table `p2p_project_repay` */

DROP TABLE IF EXISTS `p2p_project_repay`;

CREATE TABLE `p2p_project_repay` (
  `rid` int(18) NOT NULL,
  `status` tinyint(2) NOT NULL COMMENT '0未还1已还2逾期待还',
  `porder` tinyint(11) NOT NULL COMMENT '期数',
  `projectid` bigint(18) NOT NULL COMMENT '标ID',
  `repaytime` int(10) NOT NULL COMMENT '应还款时间',
  `repayyestime` int(10) DEFAULT NULL COMMENT '实际还款时间',
  `repayaccount` int(11) NOT NULL COMMENT '应还款金额',
  `repayyesaccount` int(11) DEFAULT '0' COMMENT '实际还款金额',
  `lateday` smallint(11) DEFAULT '0' COMMENT '逾期天数',
  `lateinterest` int(11) DEFAULT NULL COMMENT '逾期罚息',
  `capital` int(11) NOT NULL COMMENT '还款本金',
  `interest` int(11) NOT NULL COMMENT '还款利息',
  `addtime` int(10) DEFAULT NULL,
  `addip` varchar(15) DEFAULT NULL,
  `advance` tinyint(2) DEFAULT '0' COMMENT '是否提前还款',
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='还款表';

/*Data for the table `p2p_project_repay` */

/*Table structure for table `p2p_system` */

DROP TABLE IF EXISTS `p2p_system`;

CREATE TABLE `p2p_system` (
  `systemid` int(11) NOT NULL AUTO_INCREMENT,
  `systemcatid` int(11) NOT NULL COMMENT '字段类别ID',
  `systemname` varchar(64) NOT NULL COMMENT '字段名称',
  `systemalias` varchar(64) NOT NULL COMMENT '字段别名',
  `systemvalue` varchar(10000) NOT NULL COMMENT '字段值',
  `systemdesc` varchar(64) DEFAULT NULL COMMENT '字段简介',
  `inputtype` int(11) DEFAULT '1' COMMENT '控件类型（1，文本框;2，文本域；3，下拉框；4，单选框;5，复选框;6，上传类型）',
  `addtime` datetime NOT NULL COMMENT '添加时间',
  `updatetime` datetime DEFAULT NULL COMMENT '修改时间',
  `data` varchar(255) DEFAULT NULL COMMENT '数据',
  `isdefault` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否是默认（0，默认；1，用户）',
  PRIMARY KEY (`systemid`),
  KEY `systemcat_id` (`systemcatid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统参数表';

/*Data for the table `p2p_system` */

insert  into `p2p_system`(`systemid`,`systemcatid`,`systemname`,`systemalias`,`systemvalue`,`systemdesc`,`inputtype`,`addtime`,`updatetime`,`data`,`isdefault`) values (1,1,'网站标题','site_name','白菜贷官网','网站的名字',1,'2016-05-07 00:00:00','2016-05-07 00:00:00',NULL,0),(2,1,'网站副标题','site_fname','中国领先的汽车抵押贷款和投资理财互联网金融平台','SEO使用',1,'2016-05-07 23:13:25','2016-05-07 23:13:29',NULL,0),(3,1,'网站URL','site_siteurl','http://www.baicai.me','URL',1,'2016-05-07 23:14:18','2016-05-07 23:14:20',NULL,0),(4,2,'借款手续费','project_fee','0','针对借款人每笔借款收取费用',1,'2016-05-07 23:15:21','2016-05-07 23:15:23',NULL,0);

/*Table structure for table `p2p_system_menu` */

DROP TABLE IF EXISTS `p2p_system_menu`;

CREATE TABLE `p2p_system_menu` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menuname` varchar(64) DEFAULT NULL COMMENT '菜单名',
  `pid` int(11) DEFAULT '0' COMMENT '父节点',
  `url` varchar(256) DEFAULT NULL COMMENT '菜单连接',
  `iconurl` varchar(64) DEFAULT NULL COMMENT 'icon图标地址或样式',
  `desc` varchar(128) DEFAULT NULL COMMENT '简单说明',
  `flag` tinyint(1) DEFAULT '1' COMMENT '使用标记，1正常0禁用',
  `path` varchar(128) DEFAULT NULL COMMENT '路径表',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

/*Data for the table `p2p_system_menu` */

/*Table structure for table `p2p_system_menurole` */

DROP TABLE IF EXISTS `p2p_system_menurole`;

CREATE TABLE `p2p_system_menurole` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `menuid` int(11) DEFAULT '0' COMMENT '菜单',
  `roleid` int(11) DEFAULT '0' COMMENT '角色',
  `uid` int(11) DEFAULT '0' COMMENT '用户，待扩展',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单角色关联表';

/*Data for the table `p2p_system_menurole` */

/*Table structure for table `p2p_system_role` */

DROP TABLE IF EXISTS `p2p_system_role`;

CREATE TABLE `p2p_system_role` (
  `roleid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `rolename` varchar(64) NOT NULL COMMENT '角色名',
  `desc` varchar(128) DEFAULT NULL COMMENT '简单说明',
  `flag` tinyint(1) DEFAULT '1' COMMENT '使用标记，1正常0禁用',
  PRIMARY KEY (`roleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

/*Data for the table `p2p_system_role` */

/*Table structure for table `p2p_systemcat` */

DROP TABLE IF EXISTS `p2p_systemcat`;

CREATE TABLE `p2p_systemcat` (
  `systemcatid` int(11) NOT NULL AUTO_INCREMENT,
  `systemcatname` varchar(64) NOT NULL COMMENT '类别名称',
  `systemcatalias` varchar(32) NOT NULL DEFAULT '' COMMENT '类别别名',
  `systemcatdesc` varchar(128) NOT NULL COMMENT '类别简介',
  `systemcatparent` int(11) NOT NULL DEFAULT '0' COMMENT '父籍id',
  `isdefault` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否是系统默认（0，默认；1用户）',
  PRIMARY KEY (`systemcatid`),
  UNIQUE KEY `systemcat_alias` (`systemcatalias`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统参数分类表';

/*Data for the table `p2p_systemcat` */

insert  into `p2p_systemcat`(`systemcatid`,`systemcatname`,`systemcatalias`,`systemcatdesc`,`systemcatparent`,`isdefault`) values (1,'站点信息','sitename','站点各类信息',0,0),(2,'借款信息','project_info','借款资料信息',0,0),(3,'用户信息','user_info','用户的信息设置',0,1),(4,'资金信息','assets_info','用户资金的控制以及费用百分比',0,1),(10,'积分设置','integral_seting','积分设置',0,0);

/*Table structure for table `p2p_user` */

DROP TABLE IF EXISTS `p2p_user`;

CREATE TABLE `p2p_user` (
  `userid` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(16) NOT NULL COMMENT '用户登录名',
  `loginpass` varchar(54) NOT NULL COMMENT '登陆密码',
  `paypass` varchar(54) DEFAULT NULL COMMENT '支付密码',
  `email` varchar(64) NOT NULL COMMENT '用户邮箱',
  `phone` varchar(11) DEFAULT NULL COMMENT '用户手机',
  `userpic` varchar(64) DEFAULT NULL COMMENT '用户头像',
  `realname` varchar(16) DEFAULT NULL COMMENT '真实姓名',
  `useraddress` varchar(128) DEFAULT NULL COMMENT '用户联系地址',
  `inviteuserid` int(11) DEFAULT NULL COMMENT '父id/推荐人id',
  `usertype` tinyint(2) DEFAULT '0' COMMENT '用户类型（0，普通注册用户；1，手机注册用户2，后台手动添加用户）',
  `emailcheck` tinyint(1) DEFAULT '0' COMMENT '邮箱认证（0，未认证；1已认证）',
  `phonecheck` tinyint(1) DEFAULT '0' COMMENT '手机认证（0，未认证；1已认证）',
  `realnamecheck` tinyint(1) DEFAULT '0' COMMENT '实名认证（0，未认证；1已认证）',
  `safequestioncheck` tinyint(1) DEFAULT '0' COMMENT '密保问题（0，未设置；1，已设置）',
  `vipstoptime` int(10) DEFAULT NULL COMMENT 'VIP到期时间',
  `islock` tinyint(1) DEFAULT '0' COMMENT '账户锁定（0，正常；1，锁定）',
  `registertime` int(10) DEFAULT NULL COMMENT '注册时间',
  `logintime` int(10) DEFAULT NULL COMMENT '登录时间',
  `registerip` varchar(15) DEFAULT NULL,
  `invitenum` int(11) DEFAULT '0' COMMENT '邀请人数',
  PRIMARY KEY (`userid`),
  KEY `i_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `p2p_user` */

insert  into `p2p_user`(`userid`,`username`,`loginpass`,`paypass`,`email`,`phone`,`userpic`,`realname`,`useraddress`,`inviteuserid`,`usertype`,`emailcheck`,`phonecheck`,`realnamecheck`,`safequestioncheck`,`vipstoptime`,`islock`,`registertime`,`logintime`,`registerip`,`invitenum`) values (1,'chenwen','F2FC40C9894585E9836F0093B3BD0755DC266FF8','F2FC40C9894585E9836F0093B3BD0755DC266FF8','chenwen@qq.com','15800000001',NULL,NULL,NULL,NULL,0,0,0,0,0,NULL,0,1464958694,NULL,'192.168.1.101',0),(2,'chenwen1','08E0030C8F4BAC3A574433C0D977BF053971FB12','08E0030C8F4BAC3A574433C0D977BF053971FB12','chenwen1@qq.com','15800000002',NULL,NULL,NULL,NULL,0,0,0,0,0,NULL,0,1464958757,NULL,'192.168.1.101',0),(3,'baicai','3D4EE6160D53810F83D993A353FB0800','3D4EE6160D53810F83D993A353FB0800','baicai@qq.com','15800000003',NULL,NULL,NULL,NULL,0,0,0,0,0,NULL,0,1464959522,NULL,'192.168.1.101',0),(4,'baicai1','BAA225DD9BB007A0D241A59A9B4EDC9B','BAA225DD9BB007A0D241A59A9B4EDC9B','baicai1@qq.com','',NULL,NULL,NULL,NULL,0,0,0,0,0,NULL,0,1464962133,NULL,'192.168.1.101',0),(6,'baicai2','5D38BDBC4519F025992EAA19897BD6D1','5D38BDBC4519F025992EAA19897BD6D1','baicai2@qq.com','',NULL,NULL,NULL,NULL,0,0,0,0,0,NULL,0,1464962906,NULL,'192.168.1.101',0),(7,'baicai3','096E157DCBE59D9771A4E4C25F707A6D','096E157DCBE59D9771A4E4C25F707A6D','baicai3@qq.com','',NULL,NULL,NULL,NULL,0,0,0,0,0,NULL,0,1464963310,NULL,'192.168.1.101',0);

/*Table structure for table `p2p_user_history` */

DROP TABLE IF EXISTS `p2p_user_history`;

CREATE TABLE `p2p_user_history` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL COMMENT '用户ID',
  `action` varchar(20) DEFAULT NULL COMMENT '用户操作',
  `detaill` varchar(512) DEFAULT NULL COMMENT '操作细节',
  `addtime` datetime DEFAULT NULL COMMENT '操作时间',
  `addip` varchar(15) DEFAULT NULL COMMENT '操作IP',
  PRIMARY KEY (`id`),
  KEY `uid` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='用户操作记录表';

/*Data for the table `p2p_user_history` */

insert  into `p2p_user_history`(`id`,`uid`,`action`,`detaill`,`addtime`,`addip`) values (1,11,'logout',NULL,'2016-05-22 02:00:08','192.168.1.101'),(2,11,'login',NULL,'2016-05-22 02:03:59','192.168.1.101'),(3,11,'logout',NULL,'2016-05-22 02:04:05','192.168.1.101'),(4,11,'login',NULL,'2016-05-26 23:40:29','192.168.1.101'),(5,3,'login',NULL,'2016-06-03 21:12:32','192.168.1.101'),(6,6,'login',NULL,'2016-06-03 22:14:24','192.168.1.101'),(7,6,'logout',NULL,'2016-06-03 22:14:27','192.168.1.101'),(8,4,'login',NULL,'2016-06-05 23:47:19','192.168.1.101'),(9,4,'logout',NULL,'2016-06-06 01:06:29','192.168.1.101');

/*Table structure for table `p2p_userinfo` */

DROP TABLE IF EXISTS `p2p_userinfo`;

CREATE TABLE `p2p_userinfo` (
  `userid` int(10) unsigned DEFAULT NULL,
  `hometel` varchar(64) DEFAULT NULL COMMENT '家庭电话',
  `qq` varchar(12) DEFAULT NULL COMMENT 'QQ号码',
  `cardnum` varchar(18) DEFAULT NULL COMMENT '证件号码',
  `gender` tinyint(1) DEFAULT '0' COMMENT '用户性别（0，未知；1，男；2，女）',
  `age` tinyint(3) unsigned DEFAULT NULL COMMENT '年龄',
  `edu` tinyint(1) DEFAULT '0' COMMENT '用户学历（0，未知；1，小学；2，初中；3，高中/高专；4，大专；5，本科；6，硕士/博士/及以上）',
  `birthplace` varchar(196) DEFAULT NULL COMMENT '出生地',
  `liveplace` varchar(196) DEFAULT NULL COMMENT '居住地',
  KEY `uid` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';

/*Data for the table `p2p_userinfo` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
