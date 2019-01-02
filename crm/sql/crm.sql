/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.6.30 : Database - crm
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`crm` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `crm`;

/*Table structure for table `auto_table` */

DROP TABLE IF EXISTS `auto_table`;

CREATE TABLE `auto_table` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `table_name` varchar(50) NOT NULL DEFAULT '' COMMENT '数据库表名',
  `name` varchar(50) NOT NULL COMMENT '表别名',
  `label` varchar(200) DEFAULT NULL COMMENT '表注释',
  `type` int(1) NOT NULL DEFAULT '1' COMMENT '表生成类型1单表 2树行列表结构 3包含关联关系表(扩展以后用)',
  `is_del` int(1) DEFAULT '0' COMMENT '是否有删除功能(真删除)',
  `is_show` int(1) DEFAULT '0' COMMENT '是否有展示隐藏功能(假删除)',
  `is_status` int(1) DEFAULT '0' COMMENT '是否有上下架功能',
  `is_all_del` int(1) DEFAULT '0' COMMENT '批量删除功能(真删除)',
  `is_all_show` int(1) DEFAULT '0' COMMENT '批量展示隐藏功能(假删除)',
  `is_all_status` int(1) DEFAULT '0' COMMENT '批量上下架功能',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代码自动生成 — 已生成数据库表配置';

/*Data for the table `auto_table` */

/*Table structure for table `auto_table_column` */

DROP TABLE IF EXISTS `auto_table_column`;

CREATE TABLE `auto_table_column` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID编号',
  `table_id` bigint(20) DEFAULT NULL COMMENT '表id',
  `column_name` varchar(50) DEFAULT NULL COMMENT '数据库属性名称',
  `name` varchar(50) DEFAULT NULL COMMENT '属性别名',
  `column_type` varchar(50) NOT NULL COMMENT '数据库类型',
  `type` varchar(50) NOT NULL COMMENT 'JAVA类型',
  `length` int(6) DEFAULT NULL COMMENT '长度',
  `label` varchar(255) DEFAULT NULL COMMENT '注释',
  `nullable` int(1) DEFAULT '0' COMMENT '是否为非空',
  `is_list` int(1) NOT NULL DEFAULT '1' COMMENT '是否展示在列表',
  `is_select` int(1) NOT NULL DEFAULT '0' COMMENT '是否为查询条件',
  `is_select_type` int(1) DEFAULT '1' COMMENT '查询种类 1 相等 2 like',
  `order_no` int(4) NOT NULL DEFAULT '0' COMMENT '排序',
  `mdict_title` varchar(50) DEFAULT NULL COMMENT '数据字典title',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代码自动生成 — 数据库表对应的属性列配置';

/*Data for the table `auto_table_column` */

/*Table structure for table `care` */

DROP TABLE IF EXISTS `care`;

CREATE TABLE `care` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '客户关怀',
  `customer` int(11) DEFAULT NULL COMMENT '所属客户',
  `department` int(11) DEFAULT NULL COMMENT '所属部门',
  `care` char(1) DEFAULT NULL COMMENT '关怀类型',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号',
  `content` varchar(100) DEFAULT NULL COMMENT '短信内容',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='客户关怀';

/*Data for the table `care` */

insert  into `care`(`id`,`customer`,`department`,`care`,`phone`,`content`,`createtime`) values (1,1,2,'1','13588889999','123','2018-04-11 14:29:45'),(2,1,2,'1','18353615929','啦啦啦','2018-04-22 00:00:00'),(3,2,2,'1','1855555555','哈哈哈哈哈','2018-04-22 00:00:00'),(4,2,2,'2',NULL,'jjj',NULL);

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `fid` int(32) DEFAULT NULL COMMENT '父类ID',
  `sort` int(32) DEFAULT NULL COMMENT '排序',
  `type` char(1) DEFAULT NULL COMMENT '类型',
  `remarks` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='分类';

/*Data for the table `category` */

/*Table structure for table `customer` */

DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '客户信息',
  `name` varchar(20) DEFAULT NULL COMMENT '客户名称',
  `phone` varchar(11) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(20) DEFAULT NULL COMMENT '电子邮箱',
  `wechat` varchar(11) DEFAULT NULL COMMENT '微信QQ',
  `img_a` varchar(100) DEFAULT NULL COMMENT '身份证正面',
  `img_b` varchar(100) DEFAULT NULL COMMENT '身份证反面',
  `company` char(1) DEFAULT NULL COMMENT '企业类型',
  `department` int(11) DEFAULT NULL COMMENT '所属部门',
  `deal` char(1) DEFAULT NULL COMMENT '成交状态',
  `cash` char(1) DEFAULT NULL COMMENT '兑付状态',
  `agent` int(11) DEFAULT NULL COMMENT '经办人',
  `zmxg` varchar(32) DEFAULT NULL COMMENT '芝麻西瓜',
  `type` char(1) DEFAULT NULL COMMENT '类型个人1机构2',
  `state` char(1) DEFAULT NULL COMMENT '0未审核1审核通过2未通过',
  `seas` char(1) DEFAULT NULL COMMENT '是否公海',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='客户信息';

/*Data for the table `customer` */

insert  into `customer`(`id`,`name`,`phone`,`email`,`wechat`,`img_a`,`img_b`,`company`,`department`,`deal`,`cash`,`agent`,`zmxg`,`type`,`state`,`seas`,`createtime`) values (1,'客ddd户1','15088888888','123456qdddq.com','123ddd456','','','1',2,'1','0',3,'','1','1','0','2018-04-11 14:29:45'),(2,'客户2','15098886666','123456qq.com','123456','','','1',2,'0','1',4,'','2','2','0','2018-04-11 14:29:45');

/*Table structure for table `deliver` */

DROP TABLE IF EXISTS `deliver`;

CREATE TABLE `deliver` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '客户转交',
  `department` int(11) DEFAULT NULL COMMENT '所属部门',
  `customer` int(11) DEFAULT NULL COMMENT '所属客户',
  `transfer` int(11) DEFAULT NULL COMMENT '转交人',
  `receive` int(11) DEFAULT NULL COMMENT '接收人',
  `type` char(1) DEFAULT NULL COMMENT '1转交/2分配',
  `createtime` datetime DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='客户转交';

/*Data for the table `deliver` */

insert  into `deliver`(`id`,`department`,`customer`,`transfer`,`receive`,`type`,`createtime`) values (1,2,1,3,4,'1','2018-04-11 14:29:45'),(2,9,1,2,3,'2','2018-04-18 23:56:22'),(3,9,1,2,3,'1','2018-04-18 23:59:19'),(4,9,1,2,4,'1','2018-04-20 13:34:08'),(5,9,1,2,4,'2','2018-04-20 14:04:18'),(6,9,1,2,2,'2','2018-04-20 14:04:51'),(7,9,1,2,3,'1','2018-04-22 11:21:02'),(8,9,1,2,4,'1','2018-04-22 11:21:10'),(9,2,1,3,3,'1','2018-04-22 11:22:21'),(10,2,1,3,3,'1','2018-04-22 11:22:31'),(11,2,2,4,3,'1','2018-04-22 11:23:28'),(12,2,1,4,4,'1','2018-04-22 11:26:24'),(13,2,1,4,3,'1','2018-04-22 11:27:16'),(14,2,2,4,4,'1','2018-04-22 11:27:33'),(15,2,2,4,3,'1','2018-04-22 11:27:45'),(16,9,1,2,3,'1','2018-04-22 17:00:34');

/*Table structure for table `feedback` */

DROP TABLE IF EXISTS `feedback`;

CREATE TABLE `feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '客户反馈',
  `customer` int(11) DEFAULT NULL COMMENT '所属客户',
  `department` int(11) DEFAULT NULL COMMENT '所属部门',
  `type` char(1) DEFAULT NULL COMMENT '机构/客户',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `replytime` datetime DEFAULT NULL COMMENT '回复时间',
  `content` varchar(100) DEFAULT NULL COMMENT '提出内容',
  `answer` varchar(100) DEFAULT NULL COMMENT '回复内容',
  `reply` char(1) DEFAULT NULL COMMENT '是否回复',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='客户反馈';

/*Data for the table `feedback` */

insert  into `feedback`(`id`,`customer`,`department`,`type`,`createtime`,`replytime`,`content`,`answer`,`reply`) values (1,1,2,'1','2018-04-11 14:29:45','2018-04-22 21:40:02','123','dddd','1'),(2,2,2,'1','2018-04-20 17:02:20','2018-04-22 10:02:52','123','hhjj','1'),(3,1,9,'2','2018-04-20 17:03:18',NULL,'啦啦',NULL,'0'),(4,2,9,'1','2018-04-20 17:04:32','2018-04-22 17:32:34','加拉l',NULL,'1'),(5,2,9,'1','2018-04-21 21:55:35','2018-04-22 17:32:47','uuuu',NULL,'1'),(6,2,9,'1','2018-04-22 12:39:42',NULL,'lalalal',NULL,'0'),(7,2,9,'1','2018-04-22 16:43:22',NULL,'乐吧',NULL,'0');

/*Table structure for table `identity` */

DROP TABLE IF EXISTS `identity`;

CREATE TABLE `identity` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '身份信息',
  `user` int(11) DEFAULT NULL COMMENT '所属用户',
  `name` varchar(10) DEFAULT NULL COMMENT '姓名',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号',
  `img_a` varchar(100) DEFAULT NULL COMMENT '身份证正面',
  `img_b` varchar(100) DEFAULT NULL COMMENT '身份证反面',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='身份信息';

/*Data for the table `identity` */

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '产品管理',
  `name` varchar(40) DEFAULT NULL COMMENT '产品名称',
  `custodian` varchar(40) DEFAULT NULL COMMENT '产品管理人',
  `type` varchar(30) DEFAULT NULL COMMENT '产品类型',
  `scale` varchar(30) DEFAULT NULL COMMENT '募集规模',
  `rate` varchar(30) DEFAULT NULL COMMENT '年华收益率',
  `start` datetime DEFAULT NULL COMMENT '开始时间',
  `end` datetime DEFAULT NULL COMMENT '结束时间',
  `partnership` varchar(40) DEFAULT NULL COMMENT '合伙企业',
  `point` varchar(40) DEFAULT NULL COMMENT '认购起点',
  `details` text COMMENT '项目详情',
  `disclosure` text COMMENT '信息披露',
  `halt` char(1) DEFAULT NULL COMMENT '停售状态',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='产品管理';

/*Data for the table `product` */

insert  into `product`(`id`,`name`,`custodian`,`type`,`scale`,`rate`,`start`,`end`,`partnership`,`point`,`details`,`disclosure`,`halt`,`createtime`) values (1,'泰安地下龙宫并购重组股权投资基金','山东金泽霖股权投资管理股份有限公司','有限合伙型','3.15亿元人民币','年华8%（税前）','2018-04-11 14:29:45','2018-07-11 14:29:45','苏州安信盈投资中心（有限合伙）','1000000','本基金资产拟通过认购苏州金泽霖创业投资企业（有限合伙）投资份额参与收购泰安市宝泰隆地下大裂谷旅游开发有限责任公司15%的股权，投资人通过认购本基金的投资份额，实现对项目的投资。','本基金资产拟通过认购苏州金泽霖创业投资企业（有限合伙）投资份额参与收购泰安市宝泰隆地下大裂谷旅游开发有限责任公司15%的股权，投资人通过认购本基金的投资份额，实现对项目的投资。','1','2018-04-11 14:29:45');

/*Table structure for table `profit` */

DROP TABLE IF EXISTS `profit`;

CREATE TABLE `profit` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '签约收益',
  `sum` double DEFAULT NULL COMMENT '收益金额',
  `time` datetime DEFAULT NULL COMMENT '收益时间',
  `type` char(1) DEFAULT NULL COMMENT '收益类型',
  `signing` int(11) DEFAULT NULL COMMENT '所属签约',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='签约收益';

/*Data for the table `profit` */

insert  into `profit`(`id`,`sum`,`time`,`type`,`signing`) values (1,10000,'2018-04-22 00:00:00','1',1),(2,20000,'2018-10-22 00:00:00','2',1);

/*Table structure for table `review` */

DROP TABLE IF EXISTS `review`;

CREATE TABLE `review` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '客户回访',
  `customer` int(11) DEFAULT NULL COMMENT '所属客户',
  `department` int(11) DEFAULT NULL COMMENT '所属部门',
  `product` int(11) DEFAULT NULL COMMENT '所属产品',
  `number` varchar(11) DEFAULT NULL COMMENT '合同编号',
  `visit` char(1) DEFAULT NULL COMMENT '回访状态',
  `result` char(1) DEFAULT NULL COMMENT '回访结果',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='客户回访';

/*Data for the table `review` */

insert  into `review`(`id`,`customer`,`department`,`product`,`number`,`visit`,`result`,`createtime`) values (1,1,2,1,'JBGQBGTZ001','0','0','2018-04-11 00:00:00'),(2,1,2,1,'JBGQBGTZ001','0','0','2018-05-03 00:00:00'),(3,1,2,1,'JBGQBGTZ001','0','0','2018-05-03 00:00:00'),(4,1,2,1,'JBGQBGTZ001','0','0','2018-04-22 00:00:00'),(5,1,2,1,'JBGQBGTZ001','1','0','2018-04-22 00:00:00'),(6,1,2,1,'JBGQBGTZ001','1','1','2018-03-04 00:00:00'),(7,1,2,1,'JBGQBGTZ001','1','0','2018-03-04 00:00:00'),(8,1,2,1,'JBGQBGTZ001','0','0','2012-12-12 00:00:00'),(9,1,2,1,'JBGQBGTZ001','1','1','2014-04-04 00:00:00');

/*Table structure for table `salesallot` */

DROP TABLE IF EXISTS `salesallot`;

CREATE TABLE `salesallot` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '销售分配',
  `target` int(11) DEFAULT NULL COMMENT '所属目标',
  `department` int(11) DEFAULT NULL COMMENT '所属人员',
  `sum` double DEFAULT NULL COMMENT '分配金额',
  `rate` double DEFAULT NULL COMMENT '达成率',
  `level` char(1) DEFAULT NULL COMMENT '级别',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='销售分配';

/*Data for the table `salesallot` */

insert  into `salesallot`(`id`,`target`,`department`,`sum`,`rate`,`level`) values (1,1,2,100000,0,'0'),(2,1,4,10000,0,'1');

/*Table structure for table `salesplan` */

DROP TABLE IF EXISTS `salesplan`;

CREATE TABLE `salesplan` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '销售计划',
  `department` int(11) DEFAULT NULL COMMENT '所属部门',
  `sum` double DEFAULT NULL COMMENT '进款金额',
  `time` datetime DEFAULT NULL COMMENT '进款时间',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='销售计划';

/*Data for the table `salesplan` */

insert  into `salesplan`(`id`,`department`,`sum`,`time`,`createtime`) values (1,2,100000,'2018-04-22 00:00:00','2018-04-22 00:00:00'),(2,9,55555,'2018-04-24 00:00:00','2018-04-24 22:33:56');

/*Table structure for table `salestarget` */

DROP TABLE IF EXISTS `salestarget`;

CREATE TABLE `salestarget` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '销售目标',
  `product` int(11) DEFAULT NULL COMMENT '所属产品',
  `sum` double DEFAULT NULL COMMENT '分配金额',
  `start` datetime DEFAULT NULL COMMENT '开始时间',
  `end` datetime DEFAULT NULL COMMENT '结束时间',
  `rate` double DEFAULT NULL COMMENT '完成率',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='销售目标';

/*Data for the table `salestarget` */

insert  into `salestarget`(`id`,`product`,`sum`,`start`,`end`,`rate`,`createtime`) values (1,1,1000000,'2018-04-11 00:00:00','2019-04-11 00:00:00',0,'2018-04-11 00:00:00');

/*Table structure for table `signing` */

DROP TABLE IF EXISTS `signing`;

CREATE TABLE `signing` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '客户签约',
  `customer` int(11) DEFAULT NULL COMMENT '所属客户',
  `department` int(11) DEFAULT NULL COMMENT '所属部门',
  `product` int(11) DEFAULT NULL COMMENT '所属产品',
  `number` varchar(11) DEFAULT NULL COMMENT '合同编号',
  `time` datetime DEFAULT NULL COMMENT '投资时间',
  `sum` double DEFAULT NULL COMMENT '投资金额',
  `cash` char(1) DEFAULT NULL COMMENT '兑付状态',
  `visit` char(1) DEFAULT NULL COMMENT '回访状态',
  `contract` char(1) DEFAULT NULL COMMENT '合同状态',
  `start` datetime DEFAULT NULL COMMENT '开始时间',
  `end` datetime DEFAULT NULL COMMENT '结束时间',
  `closed` varchar(5) DEFAULT NULL COMMENT '封闭期限',
  `profit` double DEFAULT NULL COMMENT '收益金额',
  `agent` int(11) DEFAULT NULL COMMENT '经办人',
  `state` char(1) DEFAULT NULL COMMENT '审核状态',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `cash_sum` double DEFAULT NULL COMMENT '兑付金额',
  `cash_time` datetime DEFAULT NULL COMMENT '兑付时间',
  `img_a` varchar(100) DEFAULT NULL COMMENT '基础信息表',
  `img_b` varchar(100) DEFAULT NULL COMMENT '风险匹配告知书及确认函',
  `img_c` varchar(100) DEFAULT NULL COMMENT '风险调查问卷',
  `img_d` varchar(100) DEFAULT NULL COMMENT '打款凭证',
  `img_e` varchar(100) DEFAULT NULL COMMENT '银行卡',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='客户签约';

/*Data for the table `signing` */

insert  into `signing`(`id`,`customer`,`department`,`product`,`number`,`time`,`sum`,`cash`,`visit`,`contract`,`start`,`end`,`closed`,`profit`,`agent`,`state`,`createtime`,`cash_sum`,`cash_time`,`img_a`,`img_b`,`img_c`,`img_d`,`img_e`) values (1,1,2,1,'JBGQBGTZ001','2018-04-11 14:29:45',1000000,'0','1','1','2017-04-22 14:29:45','2021-04-11 14:29:45','3年',0,4,'1','2018-04-11 14:29:45',1000000,'2018-04-23 00:00:00','1','2','3','4','5');

/*Table structure for table `sys_department` */

DROP TABLE IF EXISTS `sys_department`;

CREATE TABLE `sys_department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门ID',
  `name` varchar(32) NOT NULL COMMENT '名称',
  `fid` bigint(20) NOT NULL COMMENT '上级ID',
  `sort` int(11) NOT NULL COMMENT '排序',
  `remarks` varchar(200) DEFAULT NULL COMMENT '备注',
  `status` int(2) NOT NULL COMMENT '状态',
  `level` int(4) NOT NULL COMMENT '级别',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='系统部门表';

/*Data for the table `sys_department` */

insert  into `sys_department`(`id`,`name`,`fid`,`sort`,`remarks`,`status`,`level`) values (1,'金泽霖',0,1,'',2,1),(2,'青岛分公司',1,1,'',2,2),(3,'威海分公司',1,1,'',2,2),(4,'东营分公司',1,1,'',2,2),(5,'临沂分公司',1,1,'',2,2),(6,'烟台分公司',1,1,'',2,2),(7,'济南分公司',1,1,'',2,2),(8,'上海分公司',1,1,'',2,2),(9,'总部办公室',1,1,'',2,2);

/*Table structure for table `sys_logs` */

DROP TABLE IF EXISTS `sys_logs`;

CREATE TABLE `sys_logs` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '日志ID',
  `oper_id` int(32) DEFAULT NULL COMMENT '操作人ID',
  `operator` varchar(32) DEFAULT NULL COMMENT '操作人',
  `createtime` datetime DEFAULT NULL COMMENT '操作时间',
  `remarks` varchar(200) DEFAULT NULL COMMENT '操作备注',
  `type` char(1) DEFAULT NULL COMMENT '操作类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='日志';

/*Data for the table `sys_logs` */

insert  into `sys_logs`(`id`,`oper_id`,`operator`,`createtime`,`remarks`,`type`) values (1,1,'1','0000-00-00 00:00:00','1','1');

/*Table structure for table `sys_mdict` */

DROP TABLE IF EXISTS `sys_mdict`;

CREATE TABLE `sys_mdict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID编号',
  `title` varchar(100) NOT NULL COMMENT '标题',
  `name` varchar(100) NOT NULL COMMENT '标题名称',
  `value` varchar(50) NOT NULL COMMENT '值',
  `info` varchar(100) NOT NULL COMMENT '值描述',
  `order_no` int(4) NOT NULL COMMENT '排序',
  PRIMARY KEY (`id`),
  UNIQUE KEY `title` (`title`,`value`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='系统数据字典';

/*Data for the table `sys_mdict` */

insert  into `sys_mdict`(`id`,`title`,`name`,`value`,`info`,`order_no`) values (1,'sys_status','是否禁用','1','禁用',10),(2,'sys_status','是否禁用','2','开启',20),(4,'is_show','是否显示','1','是',10),(5,'is_show','是否显示','2','否',20),(6,'auto_select_type','代码生成查询类型','1','相等',10),(7,'auto_select_type','代码生成查询类型','2','LIKE匹配',20),(8,'auto_select_type','代码生成查询类型','3','大于 >',30),(9,'auto_select_type','代码生成查询类型','4','小于 <',40),(10,'auto_create_type','创建类型','1','前后台代码',10),(11,'auto_create_type','创建类型','2','Service服务(无页面)',20),(12,'auto_create_type','创建类型','3','接口代码(无页面)',30),(13,'cash','是否兑付','1','是',10),(14,'cash','是否兑付','0','否',20),(15,'deal','是否成交','1','是',10),(16,'deal','是否成交','0','否',20),(17,'visit','是否回访','1','是',10),(18,'visit','是否回访','0','否',20),(19,'contract','合同状态','1','已寄出',10),(20,'contract','合同状态','0','未寄出',20),(21,'state','审核状态','1','已审核',10),(22,'state','审核状态','0','未审核',20),(23,'state','审核状态','2','未通过',30),(24,'result','回访结果','1','同意',10),(25,'result','回访结果','0','不同意',20),(26,'care','关怀类型','1','生日祝福',10),(27,'care','关怀类型','2','节日祝福',20),(28,'care','关怀类型','3','派息通知',30),(29,'care','关怀类型','4','公司活动',40),(30,'halt','停售状态','1','已停售',10),(31,'halt','停售状态','0','未停售',20);

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID编号',
  `name` varchar(40) NOT NULL COMMENT '菜单名称',
  `pid` bigint(20) NOT NULL COMMENT '父级ID',
  `level` int(4) NOT NULL DEFAULT '0' COMMENT '当前级别',
  `order_no` int(4) NOT NULL DEFAULT '10' COMMENT '排序号码',
  `path` varchar(255) DEFAULT NULL COMMENT '访问路径',
  `permision` varchar(100) DEFAULT NULL COMMENT '权限标识',
  `img` varchar(100) DEFAULT NULL COMMENT '图标标识',
  `is_show` int(2) NOT NULL DEFAULT '1' COMMENT '是否显示 1是 2否',
  `status` int(2) NOT NULL DEFAULT '2' COMMENT '是否禁用 1是 2否',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `create_by` bigint(20) NOT NULL COMMENT '创建人',
  `update_by` bigint(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=171 DEFAULT CHARSET=utf8 COMMENT='系统菜单表';

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`id`,`name`,`pid`,`level`,`order_no`,`path`,`permision`,`img`,`is_show`,`status`,`create_time`,`update_time`,`create_by`,`update_by`) values (2,'系统设置',0,1,10,'','','',1,2,'2017-06-07 18:48:45','2017-07-01 23:19:30',1,1),(3,'权限管理',2,2,10,'','','',1,2,'2017-06-08 15:12:07','2017-06-11 13:41:13',1,1),(4,'用户管理',3,3,10,'/sysUser/getTreeFrom','','',1,2,'2017-06-08 15:12:07','2018-03-15 08:55:43',1,1),(5,'角色管理',3,3,20,'/sysRole/page','','',1,2,'2017-06-08 21:46:56','2017-06-11 13:42:20',1,1),(6,'菜单管理',3,3,40,'/sysMenu/treeList','','',1,2,'2017-06-08 21:47:49','2017-06-11 13:42:35',1,1),(9,'编辑',4,4,20,'','sys:sysUser:edit','',2,2,'2017-06-09 11:22:38','2017-06-11 09:59:50',1,1),(12,'查看',4,4,10,'','sys:sysUser:view','',2,2,'2017-06-11 09:56:12','2017-06-11 09:57:29',1,1),(13,'查看',5,4,10,'','sys:sysRole:view','',2,2,'2017-06-11 09:58:27','2017-06-11 09:58:27',1,1),(14,'编辑',5,4,20,'','sys:sysRole:edit','',2,2,'2017-06-11 09:58:43','2017-06-11 09:58:43',1,1),(15,'查看',6,4,10,'','sys:sysMenu:view','',2,2,'2017-06-11 09:59:19','2017-06-11 09:59:19',1,1),(16,'编辑',6,4,20,'','sys:sysMenu:edit','',2,2,'2017-06-11 09:59:38','2017-06-11 09:59:38',1,1),(54,'系统设置',2,2,20,'','','',1,2,'2017-07-01 14:54:54','2017-07-01 14:55:29',1,1),(55,'数据字典',54,3,10,'/sysMdict/page','','',1,2,'2017-07-01 14:57:13','2017-07-01 14:57:13',1,1),(56,'查看',55,4,10,'','sys:sysMdict:view','',2,2,'2017-07-01 15:42:14','2017-07-01 15:42:14',1,1),(57,'编辑',55,4,20,'','sys:sysMdict:edit','',2,2,'2017-07-01 15:42:40','2017-07-01 15:42:40',1,1),(58,'业务管理',0,1,20,'','','',1,2,'2017-07-17 21:35:55','2017-07-17 21:35:55',1,1),(59,'基本管理',58,2,10,'','','',1,2,'2017-07-17 21:36:17','2017-07-17 21:36:17',1,1),(62,'代码生成',59,3,5,'/autoTable/showTables','','',1,2,'2017-07-17 23:27:46','2017-07-17 23:27:46',1,1),(99,'部门管理',3,3,30,'/sysDepartment/treeList','','',1,2,'2017-08-02 15:55:21','2017-08-08 10:33:20',1,1),(100,'编辑',99,4,20,'','sys:sysDepartment:edit','',2,2,'2017-08-02 15:55:21','2017-08-02 15:55:21',1,1),(101,'查看',99,4,10,'','sys:sysDepartment:view','',2,2,'2017-08-02 15:55:21','2017-08-02 15:55:21',1,1),(129,'日志管理',59,3,30,'/sysLogs/page',NULL,'&#xe600;',1,2,'2017-09-19 09:07:32','2017-09-19 09:07:32',1,1),(130,'编辑',129,4,20,NULL,'sys:sysLogs:edit',NULL,2,2,'2017-09-19 09:07:32','2017-09-19 09:07:32',1,1),(131,'查看',129,4,10,NULL,'sys:sysLogs:view',NULL,2,2,'2017-09-19 09:07:32','2017-09-19 09:07:32',1,1),(132,'客户信息',59,3,30,'/customer/page',NULL,'&#xe600;',1,2,'2018-04-12 13:06:34','2018-04-12 13:06:34',1,1),(133,'编辑',132,4,20,NULL,'base:customer:edit',NULL,2,2,'2018-04-12 13:06:34','2018-04-12 13:06:34',1,1),(134,'查看',132,4,10,NULL,'base:customer:view',NULL,2,2,'2018-04-12 13:06:34','2018-04-12 13:06:34',1,1),(138,'客户关怀',59,3,30,'/care/page',NULL,'&#xe600;',1,2,'2018-04-16 15:10:51','2018-04-16 15:10:51',1,1),(139,'编辑',138,4,20,NULL,'base:care:edit',NULL,2,2,'2018-04-16 15:10:51','2018-04-16 15:10:51',1,1),(140,'查看',138,4,10,NULL,'base:care:view',NULL,2,2,'2018-04-16 15:10:51','2018-04-16 15:10:51',1,1),(141,'客户回访',59,3,30,'/review/page',NULL,'&#xe600;',1,2,'2018-04-16 15:15:12','2018-04-16 15:15:12',1,1),(142,'编辑',141,4,20,NULL,'base:review:edit',NULL,2,2,'2018-04-16 15:15:12','2018-04-16 15:15:12',1,1),(143,'查看',141,4,10,NULL,'base:review:view',NULL,2,2,'2018-04-16 15:15:12','2018-04-16 15:15:12',1,1),(144,'客户反馈',59,3,30,'/feedback/page',NULL,'&#xe600;',1,2,'2018-04-16 15:21:10','2018-04-16 15:21:10',1,1),(145,'编辑',144,4,20,NULL,'base:feedback:edit',NULL,2,2,'2018-04-16 15:21:10','2018-04-16 15:21:10',1,1),(146,'查看',144,4,10,NULL,'base:feedback:view',NULL,2,2,'2018-04-16 15:21:10','2018-04-16 15:21:10',1,1),(147,'客户转交',59,3,30,'/deliver/page',NULL,'&#xe600;',1,2,'2018-04-16 15:23:39','2018-04-16 15:23:39',1,1),(148,'编辑',147,4,20,NULL,'base:deliver:edit',NULL,2,2,'2018-04-16 15:23:39','2018-04-16 15:23:39',1,1),(149,'查看',147,4,10,NULL,'base:deliver:view',NULL,2,2,'2018-04-16 15:23:39','2018-04-16 15:23:39',1,1),(150,'产品管理',59,3,30,'/product/page',NULL,'&#xe600;',1,2,'2018-04-16 15:28:14','2018-04-16 15:28:14',1,1),(151,'编辑',150,4,20,NULL,'base:product:edit',NULL,2,2,'2018-04-16 15:28:14','2018-04-16 15:28:14',1,1),(152,'查看',150,4,10,NULL,'base:product:view',NULL,2,2,'2018-04-16 15:28:14','2018-04-16 15:28:14',1,1),(153,'身份信息',59,3,30,'/identity/page',NULL,'&#xe600;',1,2,'2018-04-16 15:37:06','2018-04-16 15:37:06',1,1),(154,'编辑',153,4,20,NULL,'base:identity:edit',NULL,2,2,'2018-04-16 15:37:06','2018-04-16 15:37:06',1,1),(155,'查看',153,4,10,NULL,'base:identity:view',NULL,2,2,'2018-04-16 15:37:06','2018-04-16 15:37:06',1,1),(156,'客户签约',59,3,30,'/signing/page',NULL,'&#xe600;',1,2,'2018-04-16 15:53:40','2018-04-16 15:53:40',1,1),(157,'编辑',156,4,20,NULL,'base:signing:edit',NULL,2,2,'2018-04-16 15:53:40','2018-04-16 15:53:40',1,1),(158,'查看',156,4,10,NULL,'base:signing:view',NULL,2,2,'2018-04-16 15:53:40','2018-04-16 15:53:40',1,1),(159,'签约收益',59,3,30,'/profit/page',NULL,'&#xe600;',1,2,'2018-04-16 16:10:25','2018-04-16 16:10:25',1,1),(160,'编辑',159,4,20,NULL,'base:profit:edit',NULL,2,2,'2018-04-16 16:10:25','2018-04-16 16:10:25',1,1),(161,'查看',159,4,10,NULL,'base:profit:view',NULL,2,2,'2018-04-16 16:10:25','2018-04-16 16:10:25',1,1),(162,'销售计划',59,3,30,'/salesplan/page',NULL,'&#xe600;',1,2,'2018-04-16 16:15:37','2018-04-16 16:15:37',1,1),(163,'编辑',162,4,20,NULL,'base:salesplan:edit',NULL,2,2,'2018-04-16 16:15:37','2018-04-16 16:15:37',1,1),(164,'查看',162,4,10,NULL,'base:salesplan:view',NULL,2,2,'2018-04-16 16:15:37','2018-04-16 16:15:37',1,1),(165,'销售目标',59,3,30,'/salestarget/page',NULL,'&#xe600;',1,2,'2018-04-16 16:16:58','2018-04-16 16:16:58',1,1),(166,'编辑',165,4,20,NULL,'base:salestarget:edit',NULL,2,2,'2018-04-16 16:16:58','2018-04-16 16:16:58',1,1),(167,'查看',165,4,10,NULL,'base:salestarget:view',NULL,2,2,'2018-04-16 16:16:58','2018-04-16 16:16:58',1,1),(168,'销售分配',59,3,30,'/salesallot/page',NULL,'&#xe600;',1,2,'2018-04-16 16:18:01','2018-04-16 16:18:01',1,1),(169,'编辑',168,4,20,NULL,'base:salesallot:edit',NULL,2,2,'2018-04-16 16:18:01','2018-04-16 16:18:01',1,1),(170,'查看',168,4,10,NULL,'base:salesallot:view',NULL,2,2,'2018-04-16 16:18:01','2018-04-16 16:18:01',1,1);

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID编号',
  `name` varchar(40) NOT NULL COMMENT '名称',
  `status` int(2) NOT NULL DEFAULT '2' COMMENT '是否禁用 1是 2否',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `create_by` bigint(20) NOT NULL COMMENT '创建人',
  `update_by` bigint(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='系统角色表';

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`name`,`status`,`create_time`,`update_time`,`create_by`,`update_by`) values (1,'超级管理员',2,'2018-04-11 14:13:10','2018-04-11 14:13:10',1,1),(28,'管理员',2,'2018-04-11 14:29:15','2018-04-11 14:29:15',1,1);

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID编号',
  `sys_role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `sys_menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_role_id` (`sys_role_id`,`sys_menu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=567 DEFAULT CHARSET=utf8 COMMENT='系统角色菜单关联表';

/*Data for the table `sys_role_menu` */

insert  into `sys_role_menu`(`id`,`sys_role_id`,`sys_menu_id`) values (562,28,58),(563,28,59),(564,28,129),(566,28,130),(565,28,131);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID编号',
  `username` varchar(60) DEFAULT NULL COMMENT '用户名',
  `password` varchar(60) DEFAULT NULL COMMENT '密码',
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `image` varchar(100) DEFAULT NULL COMMENT '头像',
  `status` int(1) DEFAULT NULL COMMENT '是否禁用 1是 2否',
  `level` int(1) DEFAULT NULL COMMENT '级别',
  `login_time` datetime DEFAULT NULL COMMENT '登录时间',
  `error_date` datetime DEFAULT NULL COMMENT '登录错误时间',
  `error_time` int(3) DEFAULT NULL COMMENT '一段时间内登录出错次数',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_by` bigint(20) DEFAULT NULL COMMENT '修改人',
  `department_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`) USING BTREE,
  KEY `username_2` (`username`) USING BTREE,
  KEY `password` (`password`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='系统用户表';

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`username`,`password`,`name`,`phone`,`image`,`status`,`level`,`login_time`,`error_date`,`error_time`,`create_time`,`update_time`,`create_by`,`update_by`,`department_id`) values (1,'admin','21232f297a57a5a743894a0e4a801fc3','超级管理员','18646888888','/upload/users/d3d6d59f-f52c-42fe-890b-d526917be409.png',2,0,'2018-04-24 18:25:49',NULL,0,'2017-06-07 10:11:35','2017-06-13 10:39:12',1,1,1),(2,'yuying','c33367701511b4f6020ec61ded352059','于影影','18560111162','/upload/image/2018-04-22/24986baa-9492-4137-8d3e-27dcf82f10d1.jpg',2,1,'2018-04-11 14:30:12',NULL,0,'2018-04-04 10:09:42','2018-04-11 14:29:45',1,1,9),(3,'test','e10adc3949ba59abbe56e057f20f883e','机构总','18560111162','/upload/users/d3d6d59f-f52c-42fe-890b-d526917be409.png',2,2,'2018-04-11 14:30:12',NULL,0,'2018-04-11 14:30:12','2018-04-11 14:30:12',1,1,2),(4,'test2','e10adc3949ba59abbe56e057f20f883e','销售','18560111162','/upload/users/d3d6d59f-f52c-42fe-890b-d526917be409.png',2,3,'2018-04-11 14:30:12',NULL,0,'2018-04-11 14:30:12','2018-04-11 14:30:12',1,1,2);

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID编号',
  `sys_user_id` bigint(20) NOT NULL COMMENT '系统用户ID',
  `sys_role_id` bigint(20) NOT NULL COMMENT '系统角色ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_user_id` (`sys_user_id`,`sys_role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=114 DEFAULT CHARSET=utf8 COMMENT='系统用户角色关联表';

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`id`,`sys_user_id`,`sys_role_id`) values (1,1,1),(113,2,28);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
