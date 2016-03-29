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
  `useId` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `totalMoney` bigint(15) DEFAULT '0' COMMENT '资金总额，单位分',
  `useMoney` bigint(15) DEFAULT '0' COMMENT '可用资金',
  `frostMoney` bigint(15) DEFAULT '0' COMMENT '冻结资金',
  `haveInterest` bigint(15) DEFAULT '0' COMMENT '已收利息',
  `waitInterest` bigint(15) DEFAULT '0' COMMENT '待收利息',
  `waitTotalmoney` bigint(15) DEFAULT '0' COMMENT '待收总额',
  `expMoney` bigint(15) DEFAULT '0' COMMENT '体验金',
  `expUsemoney` bigint(15) DEFAULT '0' COMMENT '已用体验金',
  `sign` varchar(255) DEFAULT NULL COMMENT '验签，备用字段',
  `isWarning` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`useId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户资金表';

/*Data for the table `p2p_account` */

/*Table structure for table `p2p_account_bank` */

DROP TABLE IF EXISTS `p2p_account_bank`;

CREATE TABLE `p2p_account_bank` (
  `bid` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '银行卡ID',
  `userId` int(10) NOT NULL COMMENT '用户ID',
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
  `userId` bigint(18) NOT NULL COMMENT '用户ID',
  `cashStatus` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态:0未审，1通过，2失败',
  `cardno` varchar(24) NOT NULL COMMENT '银行卡号',
  `bank` int(18) DEFAULT NULL COMMENT '银行',
  `branch` varchar(255) DEFAULT NULL COMMENT '银行支行',
  `money` bigint(15) NOT NULL COMMENT '提现金额',
  `realmoney` bigint(15) DEFAULT NULL COMMENT '到账金额',
  `fee` bigint(15) DEFAULT NULL COMMENT '提现手续费',
  `verifyUser` int(10) DEFAULT NULL COMMENT '提现审核人员',
  `verifyTime` int(10) DEFAULT NULL COMMENT '提现审核时间',
  `verifyRemark` varchar(255) DEFAULT NULL COMMENT '提现审核备注',
  `addtime` int(10) NOT NULL COMMENT '添加时间',
  `addip` varchar(15) NOT NULL COMMENT '添加IP',
  PRIMARY KEY (`cid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='提现记录表';

/*Data for the table `p2p_account_cash` */

/*Table structure for table `p2p_account_log` */

DROP TABLE IF EXISTS `p2p_account_log`;

CREATE TABLE `p2p_account_log` (
  `logid` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '资金记录id',
  `userId` int(10) DEFAULT NULL COMMENT '用户id',
  `logMoney` bigint(15) DEFAULT NULL COMMENT '资金变动金额，单位分',
  `logType` tinyint(2) DEFAULT NULL COMMENT '变动类型（1，收入；2，支出）',
  `accountType` varchar(64) DEFAULT NULL COMMENT '资金类型',
  `totalMoney` bigint(15) DEFAULT NULL COMMENT '用户资金总额',
  `useMoney` bigint(15) DEFAULT NULL COMMENT '用户可用资金',
  `frostMoney` bigint(15) DEFAULT NULL COMMENT '用户冻结资金',
  `haveInterest` bigint(15) DEFAULT NULL,
  `waitInterest` bigint(15) DEFAULT NULL,
  `waitTotalmoney` bigint(15) DEFAULT NULL,
  `expMoney` bigint(15) DEFAULT NULL COMMENT '体验金',
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
  `userId` int(10) NOT NULL COMMENT '充值用户id',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '充值状态，0申请，1成功，2失败，',
  `money` bigint(15) NOT NULL COMMENT '充值金额',
  `realMoney` bigint(15) NOT NULL COMMENT '实际到账金额',
  `fee` int(15) NOT NULL COMMENT '充值手续费',
  `type` int(11) NOT NULL COMMENT '充值类型1,线上，2，线下',
  `rechargeType` varchar(128) DEFAULT NULL COMMENT '充值类型，对应充值通道id',
  `ret` varchar(1024) DEFAULT NULL COMMENT '线上充值返回参数序列化存储',
  `verifyUser` int(10) DEFAULT NULL COMMENT '审核人',
  `verifyTime` int(10) DEFAULT NULL COMMENT '审核时间',
  `verifyRemark` varchar(255) DEFAULT NULL COMMENT '审核备注',
  `addtime` int(10) NOT NULL COMMENT '添加时间',
  `addip` varchar(15) NOT NULL COMMENT '添加IP',
  PRIMARY KEY (`rid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `p2p_account_recharge` */

/*Table structure for table `p2p_article` */

DROP TABLE IF EXISTS `p2p_article`;

CREATE TABLE `p2p_article` (
  `articleId` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '文章ID',
  `articleCatId` int(10) DEFAULT NULL COMMENT '文章分类ID',
  `userId` int(10) DEFAULT NULL COMMENT '发布用户ID',
  `showStatus` tinyint(2) NOT NULL DEFAULT '1' COMMENT '显示状态(0,不显示；1，显示)',
  `articleTitle` varchar(96) DEFAULT NULL COMMENT '文章标题',
  `articlePic` varchar(96) DEFAULT NULL COMMENT '文章图片',
  `articleDesc` varchar(255) DEFAULT NULL COMMENT '文章简介',
  `articleContent` varchar(20000) DEFAULT NULL COMMENT '文章内容',
  `accNum` int(10) DEFAULT NULL COMMENT '访问次数',
  `sort` int(5) DEFAULT '0' COMMENT '排序权重',
  `addtime` int(10) DEFAULT NULL COMMENT '添加时间',
  `addip` varchar(15) DEFAULT NULL COMMENT '文章添加ip',
  `updatetime` int(10) DEFAULT NULL COMMENT '修改时间',
  `issupport` tinyint(2) DEFAULT '0' COMMENT '是否推荐（0，默认；1，推荐；）',
  PRIMARY KEY (`articleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章表';

/*Data for the table `p2p_article` */

/*Table structure for table `p2p_articlecat` */

DROP TABLE IF EXISTS `p2p_articlecat`;

CREATE TABLE `p2p_articlecat` (
  `articleCatId` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '文章分类ID',
  `articleCatName` varchar(64) DEFAULT NULL COMMENT '文章分类名称',
  `articleCatAlias` varchar(32) DEFAULT NULL COMMENT '类别别名',
  `articleCatDesc` varchar(255) DEFAULT NULL COMMENT '文章分类简介',
  `catType` tinyint(1) DEFAULT NULL COMMENT '栏目类别（1，列表页；2，单页3，封面页）',
  `showStatus` tinyint(1) DEFAULT NULL COMMENT '是否显示（0，不显示；1，显示）',
  `pid` int(10) DEFAULT NULL COMMENT '父栏目id',
  `tree` varchar(256) DEFAULT NULL COMMENT '子分类等级',
  `listTmpPath` varchar(128) DEFAULT NULL COMMENT '列表模板路径',
  `pageTmpPath` varchar(128) DEFAULT NULL COMMENT '内容模板路径',
  `specialTmpPath` varchar(128) DEFAULT NULL COMMENT '专题模板路径',
  `sort` int(8) DEFAULT NULL COMMENT '排序',
  `pubUserId` int(10) DEFAULT NULL COMMENT '发布用户ID',
  `addtime` int(10) DEFAULT NULL COMMENT '添加时间',
  `updatetime` int(10) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`articleCatId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章目录表';

/*Data for the table `p2p_articlecat` */

/*Table structure for table `p2p_project` */

DROP TABLE IF EXISTS `p2p_project`;

CREATE TABLE `p2p_project` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `proId` bigint(20) NOT NULL COMMENT '逻辑主键',
  `uid` bigint(20) DEFAULT NULL COMMENT '借款人ID',
  `proName` varchar(256) NOT NULL COMMENT '借款标题',
  `proAccount` int(11) NOT NULL COMMENT '借款金额，单位分',
  `proAccountYes` int(11) DEFAULT NULL COMMENT '已投标金额，单位分',
  `proTimeLimit` int(11) DEFAULT NULL COMMENT '借款期限',
  `proTimeUint` tinyint(4) DEFAULT NULL COMMENT '借款期限类型，1为天，2为月',
  `proValidTime` int(11) DEFAULT NULL COMMENT '借款有效时间',
  `proApr` double DEFAULT NULL COMMENT '借款利率',
  `proStyle` tinyint(4) DEFAULT NULL COMMENT '还款方式',
  `proStatus` tinyint(4) DEFAULT NULL COMMENT '标的状态 0初始1初审成功，投标中2初审失败3复审成功4复审失败5流标6撤销7正常结束',
  `proVerifyUser` int(11) DEFAULT NULL COMMENT '审标人',
  `proVerifyTime` int(11) DEFAULT NULL COMMENT '初审时间',
  `proVerifyRemark` varchar(256) DEFAULT NULL COMMENT '初审备注',
  `proFullVerifyUser` int(11) DEFAULT NULL COMMENT '满标审核人',
  `proFullVerifyTime` int(11) DEFAULT NULL COMMENT '满标审核时间',
  `proFullVerifyRemark` varchar(256) DEFAULT NULL COMMENT '满标审核备注',
  `proType` tinyint(4) DEFAULT NULL COMMENT '1信用标2担保标3抵押标4秒标5定向标',
  `proDxb` varchar(32) DEFAULT NULL COMMENT '定向标密码',
  `proDesc` varchar(20000) DEFAULT NULL COMMENT '标的详细描述',
  `proAwardType` tinyint(4) DEFAULT NULL COMMENT '奖励类型 1百分比2固定金额',
  `proAward` double DEFAULT NULL COMMENT '奖励的具体数值',
  `proLowAcount` int(11) DEFAULT NULL COMMENT '最低要求投资金额',
  `proMostAccount` int(11) DEFAULT NULL COMMENT '标的最高允许投标金额',
  `successTime` int(11) DEFAULT NULL COMMENT '满标时间',
  `endTime` int(11) DEFAULT NULL COMMENT '根据有效期算出的截止时间',
  `orderNum` mediumint(9) DEFAULT NULL COMMENT '投标次数',
  `autoRatio` int(11) DEFAULT NULL COMMENT '允许的自动投标比例，百分比',
  `repayment` int(11) DEFAULT NULL COMMENT '还款金额',
  `repaymentYes` int(11) DEFAULT NULL COMMENT '已还金额',
  `expansion` varchar(512) DEFAULT NULL COMMENT '扩充字段',
  `addtime` int(11) DEFAULT NULL,
  `addip` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `bid` (`proId`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='借款标基本信息';

/*Data for the table `p2p_project` */

insert  into `p2p_project`(`id`,`proId`,`uid`,`proName`,`proAccount`,`proAccountYes`,`proTimeLimit`,`proTimeUint`,`proValidTime`,`proApr`,`proStyle`,`proStatus`,`proVerifyUser`,`proVerifyTime`,`proVerifyRemark`,`proFullVerifyUser`,`proFullVerifyTime`,`proFullVerifyRemark`,`proType`,`proDxb`,`proDesc`,`proAwardType`,`proAward`,`proLowAcount`,`proMostAccount`,`successTime`,`endTime`,`orderNum`,`autoRatio`,`repayment`,`repaymentYes`,`expansion`,`addtime`,`addip`) values (1,1400012001,0,'借钱买车',8000000,0,3,2,7,15,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,1400012002,0,'想买一部苹果笔记本',1200000,0,50,1,5,12,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(3,1400012003,0,'宁波东风日产汽车抵押贷款45000元',4500000,0,3,2,7,15,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(4,1400012004,0,'借钱买车',8000000,0,3,2,7,15,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(5,1400012005,0,'想买一部苹果笔记本',1200000,0,50,1,5,12,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(6,1400012006,0,'宁波东风日产汽车抵押贷款45000元',4500000,0,3,2,7,15,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(7,1400012007,0,'借钱买车',8000000,0,3,2,7,15,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(8,1400012008,0,'想买一部苹果笔记本',1200000,0,50,1,5,12,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(9,1400012009,0,'借钱买车',8000000,0,3,2,7,15,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(10,14000120010,0,'想买一部苹果笔记本',1200000,0,50,1,5,12,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(11,14000120011,0,'宁波东风日产汽车抵押贷款45000元',4500000,0,3,2,7,15,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(12,14000120012,0,'借钱买车',8000000,0,3,2,7,15,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(13,14000120013,0,'想买一部苹果笔记本',1200000,0,50,1,5,12,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(14,14000120014,0,'宁波东风日产汽车抵押贷款45000元',4500000,0,3,2,7,15,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(15,14000120015,0,'借钱买车',8000000,0,3,2,7,15,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(16,14000120016,0,'想买一部苹果笔记本',1200000,0,50,1,5,12,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(17,14000120017,0,'宁波东风日产汽车抵押贷款45000元',4500000,0,3,2,7,15,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(18,14000120018,0,'借钱买车',8000000,0,3,2,7,15,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(19,14000120019,0,'想买一部苹果笔记本',1200000,0,50,1,5,12,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(20,14000120020,0,'宁波东风日产汽车抵押贷款45000元',4500000,0,3,2,7,15,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(21,14000120021,0,'借钱买车',8000000,0,3,2,7,15,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(22,14000120022,0,'想买一部苹果笔记本',1200000,0,50,1,5,12,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(23,14000120023,0,'宁波东风日产汽车抵押贷款45000元',4500000,0,3,2,7,15,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `p2p_project_apply` */

DROP TABLE IF EXISTS `p2p_project_apply`;

CREATE TABLE `p2p_project_apply` (
  `aid` bigint(18) NOT NULL DEFAULT '0',
  `userId` bigint(18) DEFAULT NULL COMMENT '用户id',
  `name` varchar(255) DEFAULT NULL COMMENT '用户名',
  `money` int(11) NOT NULL COMMENT '借款金额',
  `timeLimit` int(11) DEFAULT NULL COMMENT '借款时长',
  `phone` varchar(16) DEFAULT NULL COMMENT '联系号码',
  `realname` varchar(32) DEFAULT NULL COMMENT '真实姓名',
  `province` int(11) DEFAULT NULL COMMENT '城市',
  `city` int(11) DEFAULT NULL COMMENT '省份',
  `status` int(2) NOT NULL DEFAULT '0',
  `verifyUser` bigint(18) DEFAULT NULL,
  `verifyRemark` varchar(255) DEFAULT NULL,
  `verifyTime` varchar(128) DEFAULT NULL,
  `addtime` varchar(128) DEFAULT NULL COMMENT '添加时间',
  `addip` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='借款意向申请表';

/*Data for the table `p2p_project_apply` */

/*Table structure for table `p2p_project_order` */

DROP TABLE IF EXISTS `p2p_project_order`;

CREATE TABLE `p2p_project_order` (
  `oid` bigint(18) NOT NULL,
  `userId` bigint(18) DEFAULT NULL,
  `status` int(11) NOT NULL COMMENT '1全部通过 2部分通过',
  `projectId` bigint(18) NOT NULL COMMENT '标id',
  `money` int(11) NOT NULL DEFAULT '0' COMMENT '投标金额',
  `realmoney` int(11) NOT NULL DEFAULT '0' COMMENT '实际投标金额',
  `repayAccount` int(11) NOT NULL DEFAULT '0' COMMENT '应收款总额',
  `repayYesaccount` int(11) NOT NULL DEFAULT '0' COMMENT '已还款总额',
  `waitRepay` int(11) NOT NULL DEFAULT '0' COMMENT '待还总额',
  `interest` int(11) NOT NULL DEFAULT '0' COMMENT '应收利息总额',
  `yesinterest` int(11) NOT NULL DEFAULT '0' COMMENT '已收利息总额',
  `waitinterest` int(11) NOT NULL DEFAULT '0' COMMENT '待还利息',
  `type` int(2) NOT NULL DEFAULT '0' COMMENT '投标类型（0，普通pc投标；1，自动投标；2，手机wap投标）',
  `award` int(11) NOT NULL COMMENT '奖励金额',
  `mold` tinyint(1) NOT NULL DEFAULT '0' COMMENT '1,一马当先,2,一锤定音 ,3,一枝独秀',
  `addtime` int(10) NOT NULL COMMENT '添加时间',
  `addip` varchar(15) NOT NULL COMMENT '添加IP',
  PRIMARY KEY (`oid`),
  KEY `pid` (`projectId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

/*Data for the table `p2p_project_order` */

/*Table structure for table `p2p_user` */

DROP TABLE IF EXISTS `p2p_user`;

CREATE TABLE `p2p_user` (
  `userId` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `userName` varchar(16) NOT NULL COMMENT '用户登录名',
  `loginPass` varchar(54) NOT NULL COMMENT '登陆密码',
  `payPass` varchar(54) DEFAULT NULL COMMENT '支付密码',
  `email` varchar(64) NOT NULL COMMENT '用户邮箱',
  `phone` varchar(11) DEFAULT NULL COMMENT '用户手机',
  `userPic` varchar(64) DEFAULT NULL COMMENT '用户头像',
  `realname` varchar(16) DEFAULT NULL COMMENT '真实姓名',
  `userAddress` varchar(128) DEFAULT NULL COMMENT '用户联系地址',
  `inviteUserId` int(11) DEFAULT NULL COMMENT '父id/推荐人id',
  `userType` tinyint(2) DEFAULT '0' COMMENT '用户类型（0，普通注册用户；1，手机注册用户2，后台手动添加用户）',
  `isEmailCheck` tinyint(1) DEFAULT '0' COMMENT '邮箱认证（0，未认证；1已认证）',
  `isPhoneCheck` tinyint(1) DEFAULT '0' COMMENT '手机认证（0，未认证；1已认证）',
  `isRealnameCheck` tinyint(1) DEFAULT '0' COMMENT '实名认证（0，未认证；1已认证）',
  `isSafequestionCheck` tinyint(1) DEFAULT '0' COMMENT '密保问题（0，未设置；1，已设置）',
  `vipStopTime` int(10) DEFAULT NULL COMMENT 'VIP到期时间',
  `isLock` tinyint(1) DEFAULT '0' COMMENT '账户锁定（0，正常；1，锁定）',
  `registerTime` int(10) DEFAULT NULL COMMENT '注册时间',
  `loginTime` int(10) DEFAULT NULL COMMENT '登录时间',
  `registerip` varchar(15) DEFAULT NULL,
  `invitenum` int(11) DEFAULT '0' COMMENT '邀请人数',
  PRIMARY KEY (`userId`),
  KEY `i_username` (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `p2p_user` */

/*Table structure for table `p2p_userinfo` */

DROP TABLE IF EXISTS `p2p_userinfo`;

CREATE TABLE `p2p_userinfo` (
  `userId` int(10) unsigned DEFAULT NULL,
  `homeTel` varchar(64) DEFAULT NULL COMMENT '家庭电话',
  `qq` varchar(12) DEFAULT NULL COMMENT 'QQ号码',
  `cardnum` varchar(18) DEFAULT NULL COMMENT '证件号码',
  `gender` tinyint(1) DEFAULT '0' COMMENT '用户性别（0，未知；1，男；2，女）',
  `age` tinyint(3) unsigned DEFAULT NULL COMMENT '年龄',
  `edu` tinyint(1) DEFAULT '0' COMMENT '用户学历（0，未知；1，小学；2，初中；3，高中/高专；4，大专；5，本科；6，硕士/博士/及以上）',
  `birthPlace` varchar(196) DEFAULT NULL COMMENT '出生地',
  `livePlace` varchar(196) DEFAULT NULL COMMENT '居住地',
  KEY `uid` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';

/*Data for the table `p2p_userinfo` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
