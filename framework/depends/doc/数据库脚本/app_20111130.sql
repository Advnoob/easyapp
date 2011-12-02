﻿/*
SQLyog Enterprise - MySQL GUI v6.0
Host - 5.1.31-community : Database - app
*********************************************************************
Server version : 5.1.31-community
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

create database if not exists `app`;

USE `app`;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

/*Table structure for table `app_dict` */

DROP TABLE IF EXISTS `app_dict`;

CREATE TABLE `app_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `key` varchar(100) DEFAULT NULL,
  `value` varchar(100) DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL,
  `pinyin` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8;

/*Data for the table `app_dict` */

insert  into `app_dict`(`id`,`key`,`value`,`type`,`pinyin`) values (1,'article.state.normal','普通','article.state','putong'),(2,'article.state.recommand','推荐','article.state','tuijian'),(3,'article.state.top','置顶','article.state','zhiding'),(4,'ascii','ACSII码','regex','acsii'),(5,'bookcar.state.false','未确认','bookcar.state','weiqueren'),(6,'bookcar.state.true','确认','bookcar.state','queren'),(7,'bookcar.type.bookcar','订车','bookcar.type','dingche'),(8,'bookcar.type.change','置换','bookcar.type','zhihuan'),(9,'bookcar.type.drive','试驾','bookcar.type','shijia'),(10,'boolean.false','否','boolean','fou'),(11,'boolean.true','是','boolean','shi'),(12,'car.dongfenghonda','东风本田','car','dongfengbentian'),(13,'car.dongfenghonda.civic','思域','car.dongfenghonda','siyu'),(14,'car.dongfenghonda.crv','CRV','car.dongfenghonda','crv'),(15,'car.dongfenghonda.crv.1','系列一','car.dongfenghonda.crv','xilieyi'),(16,'car.dongfenghonda.crv.2','系列二','car.dongfenghonda.crv','xilieer'),(17,'car.dongfenghonda.spirior','思铂睿','car.dongfenghonda','siborui'),(18,'car.dongfengnissan','东风尼桑','car','dongfengnisang'),(19,'car.yiqivw','一汽大众','car','yiqidazhong'),(20,'chinese','中文','regex','zhengwen'),(21,'column.type.decimal','decimal','column.type','decimal'),(22,'column.type.int','int','column.type','int'),(23,'column.type.varchar','varchar','column.type','varchar'),(24,'content.mode.more','多项','content.mode','duoxiang'),(25,'content.mode.none','空项','content.mode','kongxiang'),(26,'content.mode.one','单项','content.mode','danxiang'),(28,'decmal','浮点数','regex','fudianshu'),(29,'email','邮箱','regex','youxiang'),(30,'flow.node.approve','回退节点','flow.node','huituijiedian'),(31,'flow.node.create','创建节点','flow.node','chuangjianjiedian'),(32,'flow.node.process','过程节点','flow.node','guochengjiedian'),(33,'flow.node.realize','结束节点','flow.node','jieshujiedian'),(34,'form.type.creater','创建者','form.type','chuangjianzhe'),(35,'form.type.datebox','日期','form.type','riqi'),(36,'form.type.datetime','日期时间','form.type','riqishijian'),(37,'form.type.textarea','文本域','form.type','wenbenyu'),(38,'form.type.combox','单选框','form.type','danxuankuang'),(39,'id','标识','regex','biaoshi'),(40,'idcard','身份证','regex','shenfenzheng'),(41,'integer','整数','regex','zhengshu'),(42,'letter','字母','regex','zimu'),(43,'loginId','登录标识','regex','denglubiaoshi'),(44,'message.state.false','未回复','message.state','weihuifu'),(45,'message.state.true','已回复','message.state','yihuifu'),(46,'node.condition.auth','权限','node.condition.model','qanxian'),(47,'node.condition.auth.department','组织机构','node.condition.auth','bumen'),(48,'node.condition.auth.role','角色','node.condition.auth','juese'),(49,'node.condition.auth.user','用户','node.condition.auth','yonghu'),(50,'node.condition.workflow','工作流','node.condition.model','chuangjianzhe'),(51,'node.condition.creater.department','部门负责人','node.condition.creater','bumenfuzeren'),(52,'node.condition.creater.self','本人','node.condition.creater','zhuangjianzhe'),(53,'notempty','非空','regex','feikong'),(54,'number','数字','regex','shuzi'),(55,'phone','手机电话','regex','shouji'),(56,'picture','图片','regex','tupian'),(57,'qq','QQ号','regex','qq'),(58,'rar','压缩文件','regex','yasuowenjian'),(59,'tel','座机电话','regex','zuoji'),(60,'url','URL','regex','url'),(61,'visible.false','隐藏','visible','yinchang'),(62,'visible.true','显示','visible','xianshi'),(63,'zipcode','邮编','regex','youbian'),(64,'只读','只读','node.state','zhidu'),(65,'必填','必填','node.state','bitian'),(66,'选填','选填','node.state','xuantian'),(67,'隐藏','隐藏','node.state','yincang'),(68,'yes','同意','advise','tongyi'),(69,'no','不同意','advise','butongyi'),(70,'form.type.input','输入框','form.type','shurukuang'),(71,'node.condition.creater.department.people','同部门','node.condition.creater','tongbumen'),(75,'node.condition.creater','创建者','node.condition.workflow','chuangjianzhe'),(86,'申报','申报','sldb.state.type','shenbao'),(87,'初审','初审','sldb.state.type','chushen'),(88,'核对','核对','sldb.state.type','hedui'),(89,'驳回','驳回','sldb.state.type','bohui'),(90,'通过','通过','sldb.state.type','tongguo'),(91,'农村低保收入','农村低保收入','sldb.person.type','nongcundibaoshouru'),(92,'城镇低保收入','城镇低保收入','sldb.person.type','chegnzhendibaoshouru'),(93,'农村低保','农村低保','sldb.person.type','nongcundibao'),(94,'城镇低保','城镇低保','sldb.person.type','chengzhendibao'),(95,'女','女','gender','nv'),(96,'男','男','gender','nan'),(97,'是','是','easyboolean','shi'),(98,'否','否','easyboolean','fou');

/*Table structure for table `app_form_item` */

DROP TABLE IF EXISTS `app_form_item`;

CREATE TABLE `app_form_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `showName` varchar(50) DEFAULT NULL,
  `defaultValue` varchar(500) DEFAULT NULL,
  `tableId` int(11) DEFAULT NULL,
  `required` varchar(50) DEFAULT NULL,
  `requiredName` varchar(50) DEFAULT NULL,
  `readonly` varchar(50) DEFAULT NULL,
  `readonlyName` varchar(50) DEFAULT NULL,
  `hidden` varchar(50) DEFAULT NULL,
  `hiddenName` varchar(50) DEFAULT NULL,
  `min` varchar(50) DEFAULT NULL,
  `max` varchar(50) DEFAULT NULL,
  `regex` varchar(50) DEFAULT NULL,
  `regexName` varchar(50) DEFAULT NULL,
  `focusTip` varchar(200) DEFAULT NULL,
  `func` varchar(2000) DEFAULT NULL,
  `dict` varchar(50) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `typeName` varchar(50) DEFAULT NULL,
  `render` varchar(2000) DEFAULT NULL,
  `formType` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8;

/*Data for the table `app_form_item` */

insert  into `app_form_item`(`id`,`name`,`showName`,`defaultValue`,`tableId`,`required`,`requiredName`,`readonly`,`readonlyName`,`hidden`,`hiddenName`,`min`,`max`,`regex`,`regexName`,`focusTip`,`func`,`dict`,`type`,`typeName`,`render`,`formType`) values (16,'vvv','vvv','',7,'boolean.true','是','boolean.true','是','visible.true','显示','','50','','','请输入内容','function() {this.isvalid=true;}','','form.type.input','输入框','','add'),(17,'name','名称','',7,'boolean.true','是','boolean.true','是','visible.true','显示','','50','','','请输入内容','function() {this.isvalid=true;}','','form.type.input','输入框','','add'),(18,'age','年龄','',7,'boolean.true','是','boolean.true','是','visible.true','显示','','50','','','请输入内容','function() {this.isvalid=true;}','','form.type.input','输入框','','add'),(40,'name','名称','',9,'boolean.true','是','boolean.false','否','visible.true','显示','0','50','chinese','中文','请输入中文','function() {this.isvalid=true;}','','form.type.input','输入框','','add'),(41,'phone','电话','',9,'boolean.true','是','boolean.false','否','visible.true','显示','0','50','phone','手机电话','请输入有效电话号码','function() {this.isvalid=true;}','','form.type.input','输入框','','add'),(42,'email','邮箱','',9,'boolean.true','是','boolean.false','否','visible.true','显示','0','50','email','邮箱','请输入内容','function() {this.isvalid=true;}','','form.type.input','输入框','','add'),(43,'used','有效','boolean.true',9,'boolean.true','是','boolean.false','否','visible.true','显示','0','50','','','请输入内容','function() {this.isvalid=true;}','boolean','form.type.combox','单选框','$(\'#used\').combobox({\r\n	onSelect : function(record){\r\n	       $(\'#usedName\').val(record.text);\r\n        }\r\n});','add'),(44,'usedName','是否有效','是',9,'boolean.false','否','boolean.false','否','visible.false','隐藏','0','50','','','请输入内容','function() {this.isvalid=true;}','','form.type.input','输入框','','add'),(45,'birthday','生日','current',9,'boolean.true','是','boolean.false','否','visible.true','显示','0','50','','','请输入内容','function() {this.isvalid=true;}','','form.type.datebox','日期','','add'),(46,'desc','描述','',9,'boolean.false','否','boolean.false','否','visible.true','显示','0','50','','','请输入内容','function() {this.isvalid=true;}','','form.type.textarea','文本域','','add'),(54,'name','名称','',9,'boolean.true','是','boolean.false','否','visible.true','显示','0','50','chinese','中文','请输入中文','function() {this.isvalid=true;}','','form.type.input','输入框','','edit'),(55,'phone','电话','',9,'boolean.false','否','boolean.false','否','visible.true','显示','0','50','phone','手机电话','请输入有效电话号码','function() {this.isvalid=true;}','','form.type.input','输入框','','edit'),(56,'email','邮箱','',9,'boolean.true','是','boolean.false','否','visible.true','显示','0','50','email','邮箱','请输入内容','function() {this.isvalid=true;}','','form.type.input','输入框','','edit'),(57,'used','有效','boolean.true',9,'boolean.true','是','boolean.false','否','visible.true','显示','0','50','','','请输入内容','function() {this.isvalid=true;}','boolean','form.type.combox','单选框','$(\'#used\').combobox({\r\n	onSelect : function(record){\r\n	       $(\'#usedName\').val(record.text);\r\n        }\r\n});','edit'),(58,'usedName','是否有效','是',9,'boolean.false','否','boolean.false','否','visible.false','隐藏','0','50','','','请输入内容','function() {this.isvalid=true;}','','form.type.input','输入框','','edit'),(59,'birthday','生日','current',9,'boolean.true','是','boolean.false','否','visible.true','显示','0','50','','','请输入内容','function() {this.isvalid=true;}','','form.type.datebox','日期','','edit'),(60,'desc','描述','',9,'boolean.false','否','boolean.false','否','visible.true','显示','0','51','','','请输入内容','function() {this.isvalid=true;}','','form.type.textarea','文本域','','edit'),(61,'name','姓名','张三',10,'boolean.true','是','boolean.false','否','visible.true','显示','0','50','chinese','中文','请输入内容','function() {this.isvalid=true;}','','form.type.input','输入框','','add'),(62,'date','日期','',10,'boolean.true','是','boolean.false','否','visible.true','显示','0','50','data','日期','请输入内容','function() {this.isvalid=true;}','','form.type.datebox','日期','','add'),(63,'phone','电话','',10,'boolean.true','是','boolean.false','否','visible.true','显示','0','50','phone','手机电话','请输入内容','function() {this.isvalid=true;}','','form.type.textarea','文本域','','add'),(68,'name','姓名','张三',10,'boolean.true','是','boolean.false','否','visible.true','显示','0','50','chinese','中文','请输入内容','function() {this.isvalid=true;}','','form.type.input','输入框','','edit'),(69,'date','日期','',10,'boolean.true','是','boolean.false','否','visible.true','显示','0','50','data','日期','请输入内容','function() {this.isvalid=true;}','','form.type.datebox','日期','','edit'),(70,'phone','电话','',10,'boolean.true','是','boolean.false','否','visible.true','显示','0','50','phone','手机电话','请输入内容','function() {this.isvalid=true;}','','form.type.textarea','文本域','','edit');

/*Table structure for table `app_queryform_item` */

DROP TABLE IF EXISTS `app_queryform_item`;

CREATE TABLE `app_queryform_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `showName` varchar(50) DEFAULT NULL,
  `tableId` int(11) DEFAULT NULL,
  `condition` varchar(50) DEFAULT NULL,
  `conditionName` varchar(50) DEFAULT NULL,
  `hidden` varchar(50) DEFAULT NULL,
  `hiddenName` varchar(50) DEFAULT NULL,
  `sort` varchar(50) DEFAULT NULL,
  `sortName` varchar(50) DEFAULT NULL,
  `width` varchar(50) DEFAULT NULL,
  `format` varchar(2000) DEFAULT NULL,
  `dict` varchar(50) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `typeName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

/*Data for the table `app_queryform_item` */

insert  into `app_queryform_item`(`id`,`name`,`showName`,`tableId`,`condition`,`conditionName`,`hidden`,`hiddenName`,`sort`,`sortName`,`width`,`format`,`dict`,`type`,`typeName`) values (3,'age','年龄',7,'boolean.true','是','visible.true','显示','boolean.true','是','100','','car','form.type.input','输入框'),(9,'vvv','vvv',7,'boolean.true','是','visible.true','显示','boolean.true','是','50','','','form.type.textarea','文本域'),(10,'name','名称',7,'boolean.true','是','visible.true','显示','boolean.true','是','50','','boolean','form.type.combox','单选框'),(12,'name','名称',9,'boolean.true','是','visible.true','显示','boolean.true','是','80','','','form.type.input','输入框'),(13,'phone','电话',9,'boolean.true','是','visible.true','显示','boolean.true','是','100','','','form.type.input','输入框'),(14,'email','邮箱',9,'boolean.true','是','visible.true','显示','boolean.true','是','150','','','form.type.input','输入框'),(15,'used','有效',9,'boolean.true','是','visible.false','隐藏','boolean.true','是','50','','boolean','form.type.combox','单选框'),(16,'usedName','是否有效',9,'boolean.false','否','visible.true','显示','boolean.true','是','80','','','form.type.input','输入框'),(18,'birthday','生日',9,'boolean.true','是','visible.true','显示','boolean.true','是','150','','','form.type.datebox','日期'),(19,'desc','描述',9,'boolean.false','否','visible.true','显示','boolean.true','是','150','','','form.type.input','输入框'),(22,'name','姓名',10,'boolean.true','是','visible.true','显示','boolean.true','是','101','','','form.type.input','输入框'),(23,'date','日期',10,'boolean.true','是','visible.true','显示','boolean.true','是','152','','','form.type.datebox','日期'),(25,'phone','电话',10,'boolean.true','是','visible.true','显示','boolean.true','是','150','return value;','','form.type.input','输入框'),(26,'test_age','年龄',27,'boolean.true','是','visible.true','显示','boolean.true','是','100','','','input','输入框');

/*Table structure for table `app_resources` */

DROP TABLE IF EXISTS `app_resources`;

CREATE TABLE `app_resources` (
  `id` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `path` varchar(100) DEFAULT NULL,
  `parentId` varchar(100) DEFAULT NULL,
  `sort` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`sort`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;

/*Data for the table `app_resources` */

insert  into `app_resources`(`id`,`name`,`path`,`parentId`,`sort`) values ('auth','管理权限',NULL,NULL,1),('auth.user','管理用户','/app/auth/user/showUsers.jsp','auth',2),('auth.role','管理角色','/app/auth/role/showRoles.jsp','auth',3),('auth.resource','管理资源','/app/auth/resource/showResources.jsp','auth',4),('auth.organization','管理部门','/app/auth/organization/showOrganizations.jsp','auth',5),('configuration','管理基础配置','',NULL,6),('configuration.dict','管理数据字典','/app/system/dict/showDicts.jsp','configuration',7),('website','管理网站','',NULL,8),('website.content','管理目录','/app/website/content/show.jsp','website',14),('website.article','管理文章','/app/website/article/show.jsp','website',32),('website.message','管理消息','/app/website/message/show.jsp','website',33),('car','管理汽车','',NULL,34),('website.editcontent','管理融展集团','/app/website/admin/main.jsp?cid=rongzhan','website',35),('workstation','管理工作','',NULL,36),('car.newcar','管理新车','/app/car/newcar/show.jsp','car',37),('car.bookcar','管理预约','/app/car/bookcar/show.jsp','car',38),('car.admin.bookcar','管理车辆预约','/app/car/admin/bookcarShow.jsp?cid=rongzhan','car',39),('car.admin.newcar','管理新车信息','/app/car/admin/newcarShow.jsp?cid=rongzhan','car',40),('configuration.table','管理表','/app/system/easyform/table/show.jsp','configuration',41),('workflow','管理工作流','/app/workflow/info/show.jsp','configuration',42),('configuration.workflow.instance','管理工作流实例','/app/workflow/runtime/instance/show.jsp','configuration',43),('sldb.admin','管理双辽低保','',NULL,45),('workstation.tobe','代办事宜','/app/workflow/runtime/user/tobe.jsp','workstation',46),('workstation.done','已办事宜','/app/workflow/runtime/user/done.jsp','workstation',47),('workstation.over','办结事宜','/app/workflow/runtime/user/over.jsp','workstation',48),('sldb.person','双辽低保','',NULL,49),('sldb.admin.importInfo','管理导入表格','/app/sldb/info/show.jsp','sldb.admin',50),('sldb.import','数据导入','',NULL,51),('sldb.person.apply','申请低保','/app/sldb/person/apply.jsp','sldb.person',52),('sldb.person.first','初审低保','/app/sldb/person/first.jsp','sldb.person',53),('sldb.person.check','核对低保','/app/sldb/person/check.jsp','sldb.person',54),('sldb.person.end','决议低保','/app/sldb/person/end.jsp','sldb.person',55),('sldb.admin.import','查看导入数据','/app/sldb/import/show.jsp','sldb.admin',56),('auth.change.password','修改密码','/app/auth/user/changePassword.jsp',NULL,57),('sldb.import.house','房产数据','/app/sldb/import/sub/house.jsp','sldb.import',58);

/*Table structure for table `app_table` */

DROP TABLE IF EXISTS `app_table`;

CREATE TABLE `app_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `showName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `app_table` */

insert  into `app_table`(`id`,`name`,`showName`) values (9,'test_ust','测试用户'),(10,'test_abc','ABC');

/*Table structure for table `app_table_column` */

DROP TABLE IF EXISTS `app_table_column`;

CREATE TABLE `app_table_column` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `showName` varchar(50) DEFAULT NULL,
  `tableId` int(11) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `typeName` varchar(50) DEFAULT NULL,
  `length` varchar(50) DEFAULT NULL,
  `defaultValue` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

/*Data for the table `app_table_column` */

insert  into `app_table_column`(`id`,`name`,`showName`,`tableId`,`type`,`typeName`,`length`,`defaultValue`) values (12,'age','年龄',7,'column.type.varchar','varchar','50','5'),(13,'name','名称',7,'column.type.varchar','varchar','50','11'),(14,'name','名称',8,'column.type.varchar','varchar','50',''),(15,'age','年龄',8,'column.type.varchar','varchar','50',''),(16,'ss','ss',8,'column.type.varchar','varchar','50',''),(17,'vvv','vvv',7,'column.type.varchar','varchar','50','1'),(18,'name','名称',9,'column.type.varchar','varchar','50',''),(19,'phone','电话',9,'column.type.varchar','varchar','50',''),(20,'email','邮箱',9,'column.type.varchar','varchar','50',''),(21,'used','有效',9,'column.type.varchar','varchar','50','boolean.true'),(22,'desc','描述',9,'column.type.varchar','varchar','50',''),(23,'birthday','生日',9,'column.type.varchar','varchar','50',''),(24,'usedName','是否有效',9,'column.type.varchar','varchar','50','是'),(25,'name','姓名',10,'column.type.varchar','varchar','50',''),(26,'date','日期',10,'column.type.varchar','varchar','50',''),(27,'phone','电话',10,'column.type.varchar','varchar','50',''),(44,'testCol','测试列',1,'column.type.varchar','varchar','50','无');

/*Table structure for table `auth_organizations` */

DROP TABLE IF EXISTS `auth_organizations`;

CREATE TABLE `auth_organizations` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  `responsible` varchar(50) DEFAULT '否',
  `parentId` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Data for the table `auth_organizations` */

insert  into `auth_organizations`(`id`,`name`,`description`,`responsible`,`parentId`) values (3,'长春巾航科技有限公司','','否',NULL),(4,'董事长','','否','3'),(5,'总经理','','是','3'),(6,'销售副总','','否','3'),(7,'售后副总','','否','3'),(8,'财务副总','','否','3'),(9,'人事副总','','否','3'),(10,'人事部','','否','3'),(11,'人事部长','','是','10'),(12,'人事部副部长','','否','10'),(13,'人事部助理','','否','10'),(14,'人事部成员','','否','10'),(15,'销售部','','否','3'),(16,'销售部长','','是','15');

/*Table structure for table `auth_re_organization_resource` */

DROP TABLE IF EXISTS `auth_re_organization_resource`;

CREATE TABLE `auth_re_organization_resource` (
  `organizationId` int(100) NOT NULL,
  `resourceId` varchar(100) NOT NULL,
  PRIMARY KEY (`organizationId`,`resourceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `auth_re_organization_resource` */

/*Table structure for table `auth_re_organization_user` */

DROP TABLE IF EXISTS `auth_re_organization_user`;

CREATE TABLE `auth_re_organization_user` (
  `organizationId` int(100) NOT NULL,
  `userId` varchar(100) NOT NULL,
  PRIMARY KEY (`organizationId`,`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `auth_re_organization_user` */

insert  into `auth_re_organization_user`(`organizationId`,`userId`) values (11,'zhangsan'),(12,'admin'),(16,'lisi');

/*Table structure for table `auth_re_role_resource` */

DROP TABLE IF EXISTS `auth_re_role_resource`;

CREATE TABLE `auth_re_role_resource` (
  `roleId` int(100) NOT NULL,
  `resourceId` varchar(100) CHARACTER SET gb2312 NOT NULL DEFAULT '',
  PRIMARY KEY (`roleId`,`resourceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `auth_re_role_resource` */

insert  into `auth_re_role_resource`(`roleId`,`resourceId`) values (1,'auth'),(1,'auth.change.password'),(1,'auth.organization'),(1,'auth.resource'),(1,'auth.role'),(1,'auth.user'),(1,'car'),(1,'car.admin.bookcar'),(1,'car.admin.newcar'),(1,'car.bookcar'),(1,'car.newcar'),(1,'configuration'),(1,'configuration.dict'),(1,'configuration.table'),(1,'configuration.workflow.instance'),(1,'website'),(1,'website.article'),(1,'website.content'),(1,'website.editcontent'),(1,'website.message'),(1,'workflow'),(2,'workstation'),(2,'workstation.done'),(2,'workstation.over'),(2,'workstation.tobe'),(3,'sldb.admin'),(3,'sldb.admin.import'),(3,'sldb.admin.importInfo'),(3,'sldb.import'),(3,'sldb.import.house'),(3,'sldb.person'),(3,'sldb.person.apply'),(3,'sldb.person.check'),(3,'sldb.person.end'),(3,'sldb.person.first');

/*Table structure for table `auth_re_user_resource` */

DROP TABLE IF EXISTS `auth_re_user_resource`;

CREATE TABLE `auth_re_user_resource` (
  `userId` varchar(100) NOT NULL,
  `resourceId` varchar(100) NOT NULL,
  PRIMARY KEY (`userId`,`resourceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `auth_re_user_resource` */

insert  into `auth_re_user_resource`(`userId`,`resourceId`) values ('1','auth'),('1','auth.organization'),('1','auth.reportData'),('1','auth.reportView'),('1','auth.resource'),('1','auth.role'),('1','auth.user'),('1','configuration'),('1','configuration.dict'),('1','test'),('admin','testWorkflow'),('rongzhan','auth.change.password'),('rongzhan','car'),('rongzhan','car.admin.bookcar'),('rongzhan','car.admin.newcar'),('rongzhan','website'),('rongzhan','website.editcontent');

/*Table structure for table `auth_re_user_role` */

DROP TABLE IF EXISTS `auth_re_user_role`;

CREATE TABLE `auth_re_user_role` (
  `userId` varchar(100) CHARACTER SET gb2312 NOT NULL,
  `roleId` int(100) NOT NULL,
  PRIMARY KEY (`userId`,`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `auth_re_user_role` */

insert  into `auth_re_user_role`(`userId`,`roleId`) values ('admin',1),('admin',2),('admin',3);

/*Table structure for table `auth_roles` */

DROP TABLE IF EXISTS `auth_roles`;

CREATE TABLE `auth_roles` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `auth_roles` */

insert  into `auth_roles`(`id`,`name`,`description`) values (1,'超级管理员','拥有所有权限'),(2,'工作流',''),(3,'双辽低保','双辽低保管理员');

/*Table structure for table `auth_users` */

DROP TABLE IF EXISTS `auth_users`;

CREATE TABLE `auth_users` (
  `Id` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `id` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `auth_users` */

insert  into `auth_users`(`Id`,`name`,`password`,`email`,`phone`) values ('1','1','1','1','1'),('2','2','2','2','2'),('3','3','3','3','3'),('4','4','4','4','4'),('5','5','5','5','5'),('6','6','6','6','6'),('7','7','7','7','7'),('8','8','8','8','8'),('9','9','9','9','9'),('admin','管理员','lueSGJZetyySpUndWjMBEg==','guyuzmq@gmail.com','18943630044'),('lisi','李四','lueSGJZetyySpUndWjMBEg==','11@11.11','13511111111'),('rongzhan','融展集团','0N495lkCGCREg87rx9tGew==','11@11.cc','13597878687'),('zhangsan','张三','lueSGJZetyySpUndWjMBEg==','1111@11.11','13599999999');

/*Table structure for table `car_bookcar` */

DROP TABLE IF EXISTS `car_bookcar`;

CREATE TABLE `car_bookcar` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cid` varchar(100) DEFAULT NULL,
  `brand` varchar(100) DEFAULT NULL,
  `brandName` varchar(100) DEFAULT NULL,
  `model` varchar(100) DEFAULT NULL,
  `modelName` varchar(100) DEFAULT NULL,
  `series` varchar(100) DEFAULT NULL,
  `seriesName` varchar(100) DEFAULT NULL,
  `license` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL,
  `typeName` varchar(100) DEFAULT NULL,
  `text` varchar(1000) DEFAULT NULL,
  `bookTime` datetime DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `state` varchar(100) DEFAULT 'bookcar.state.false',
  `stateName` varchar(100) DEFAULT '未确认',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `car_bookcar` */

insert  into `car_bookcar`(`id`,`cid`,`brand`,`brandName`,`model`,`modelName`,`series`,`seriesName`,`license`,`phone`,`type`,`typeName`,`text`,`bookTime`,`createTime`,`state`,`stateName`) values (6,'rongzhan','car.dongfenghonda','东风本田','car.dongfenghonda.crv','CRV','car.dongfenghonda.crv.1','系列一','123123','13596160570','bookcar.type.drive','试驾','123123123','2011-10-02 20:44:46','2011-10-02 12:44:29','bookcar.state.false','未确认'),(7,'rongzhan.about','car.dongfenghonda','东风本田','car.dongfenghonda.crv','CRV','car.dongfenghonda.crv.2','系列二','1231234','13596160504','bookcar.type.bookcar','试驾','adfsfa4','2011-10-24 20:56:51','2011-10-02 12:56:27','bookcar.state.true','确认'),(8,'rongzhan','car.dongfenghonda','东风本田','car.dongfenghonda.crv','CRV','car.dongfenghonda.crv.2','系列二','123','13596160570','bookcar.type.drive','试驾','','2011-11-07 11:50:04','2011-11-06 03:49:16','bookcar.state.false','未确认');

/*Table structure for table `car_newcar` */

DROP TABLE IF EXISTS `car_newcar`;

CREATE TABLE `car_newcar` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cid` varchar(100) DEFAULT NULL,
  `brand` varchar(100) DEFAULT NULL,
  `brandName` varchar(100) DEFAULT NULL,
  `model` varchar(100) DEFAULT NULL,
  `modelName` varchar(100) DEFAULT NULL,
  `series` varchar(100) DEFAULT NULL,
  `seriesName` varchar(100) DEFAULT NULL,
  `image` varchar(100) DEFAULT NULL,
  `price` varchar(100) DEFAULT NULL,
  `realPrice` varchar(100) DEFAULT NULL,
  `gift` varchar(100) DEFAULT NULL,
  `saller` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `car_newcar` */

insert  into `car_newcar`(`id`,`cid`,`brand`,`brandName`,`model`,`modelName`,`series`,`seriesName`,`image`,`price`,`realPrice`,`gift`,`saller`) values (2,'rongzhan','car.dongfenghonda','东风本田','car.dongfenghonda.crv','CRV','car.dongfenghonda.crv.1','系列一','/easyapp/attached/appCar/20111002/20111002035431_773.jpg','22.1','20.2','尴尬',NULL),(3,'rongzhan','car.dongfenghonda','东风本田','car.dongfenghonda.crv','CRV','car.dongfenghonda.crv.1','系列一','/easyapp/attached/appCar/20111002/20111002035431_773.jpg','20','18','尴尬5',NULL),(5,'rongzhan','car.dongfenghonda','东风本田','car.dongfenghonda.crv','CRV','car.dongfenghonda.crv.1','系列一','/easyapp/attached/appCar/20111002/20111002035431_773.jpg','23','21','fffss方法','张三:18943630044'),(8,'rongzhan.about','car.dongfenghonda','东风本田','car.dongfenghonda.crv','CRV','car.dongfenghonda.crv.1','系列一','/easyapp/attached/appCar/20111002/20111002035431_773.jpg','21','10','123213','13123555666');

/*Table structure for table `demo_user` */

DROP TABLE IF EXISTS `demo_user`;

CREATE TABLE `demo_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `birthday` varchar(50) DEFAULT NULL,
  `age` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `demo_user` */

insert  into `demo_user`(`id`,`name`,`birthday`,`age`) values (7,'test_kk2','2000-01-01','10');

/*Table structure for table `sldb_house2011-11` */

DROP TABLE IF EXISTS `sldb_house2011-11`;

CREATE TABLE `sldb_house2011-11` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `identify` varchar(20) DEFAULT NULL,
  `sum` varchar(10) DEFAULT NULL,
  `importDate` varchar(50) DEFAULT NULL,
  `iid` varchar(100) DEFAULT NULL,
  `importType` int(10) DEFAULT NULL,
  `importError` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57601 DEFAULT CHARSET=utf8;

/*Data for the table `sldb_house2011-11` */

/*Table structure for table `sldb_import` */

DROP TABLE IF EXISTS `sldb_import`;

CREATE TABLE `sldb_import` (
  `id` varchar(100) NOT NULL,
  `type` varchar(50) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `creater` varchar(50) DEFAULT NULL,
  `createrName` varchar(50) DEFAULT NULL,
  `sum` varchar(20) DEFAULT NULL,
  `importDate` varchar(10) DEFAULT NULL,
  `filePath` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sldb_import` */

/*Table structure for table `sldb_import_info` */

DROP TABLE IF EXISTS `sldb_import_info`;

CREATE TABLE `sldb_import_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `tableName` varchar(100) DEFAULT NULL,
  `startLine` varchar(50) DEFAULT NULL,
  `desc` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `sldb_import_info` */

insert  into `sldb_import_info`(`id`,`name`,`tableName`,`startLine`,`desc`) values (2,'房产局','sldb_house','1','房产局导入表格信息');

/*Table structure for table `sldb_import_item` */

DROP TABLE IF EXISTS `sldb_import_item`;

CREATE TABLE `sldb_import_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `colume` varchar(50) DEFAULT NULL,
  `length` varchar(20) DEFAULT NULL,
  `showLength` varchar(20) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `infoId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `sldb_import_item` */

insert  into `sldb_import_item`(`id`,`name`,`colume`,`length`,`showLength`,`type`,`infoId`) values (5,'姓名','name','20','70','字符串',2),(6,'身份证','identify','20','150','字符串',2),(7,'有房数','sum','10','70','字符串',2);

/*Table structure for table `sldb_person` */

DROP TABLE IF EXISTS `sldb_person`;

CREATE TABLE `sldb_person` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `identify` varchar(20) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `creater` varchar(50) DEFAULT NULL,
  `createrName` varchar(50) DEFAULT NULL,
  `createrDepartment` varchar(50) DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `age` varchar(10) DEFAULT NULL,
  `homeSum` varchar(10) DEFAULT NULL,
  `ill` varchar(10) DEFAULT NULL,
  `deformity` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `sldb_person` */

insert  into `sldb_person`(`id`,`identify`,`type`,`createTime`,`creater`,`createrName`,`createrDepartment`,`state`,`name`,`gender`,`age`,`homeSum`,`ill`,`deformity`) values (3,'220102198112034411','城镇低保','2011-11-26 17:17:58','admin','管理员','人事部副部长','申报','张三1','女','31','1','是','是'),(4,'220102198112034412','城镇低保','2011-11-26 19:12:50','admin','管理员','人事部副部长','申报','1','男','12','3','否','否'),(5,'220102198112034412','城镇低保','2011-11-26 19:14:43','admin','管理员','人事部副部长','申报','222','男','33','12','否','否'),(6,'220102198112034412','城镇低保','2011-11-26 19:19:26','admin','管理员','人事部副部长','初审','234234','男','33','333','否','否'),(7,'220102198112034412','城镇低保','2011-11-26 20:00:30','admin','管理员','人事部副部长','创建','234234','男','3123','12313','否','否'),(8,'220102198112034412','城镇低保','2011-11-26 20:01:17','admin','管理员','人事部副部长','创建','12313','男','123','13','否','否'),(9,'220102198112034412','城镇低保','2011-11-26 20:02:02','admin','管理员','人事部副部长','核对','1213','男','123','12','否','否');

/*Table structure for table `sldb_person_state` */

DROP TABLE IF EXISTS `sldb_person_state`;

CREATE TABLE `sldb_person_state` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pid` bigint(20) DEFAULT NULL,
  `userId` varchar(50) DEFAULT NULL,
  `userName` varchar(50) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `identify` varchar(20) DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL,
  `note` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Data for the table `sldb_person_state` */

insert  into `sldb_person_state`(`id`,`pid`,`userId`,`userName`,`createTime`,`name`,`identify`,`state`,`note`) values (9,6,'admin','管理员','2011-11-26 19:22:26','234234','220102198112034412','申报',''),(10,7,'admin','管理员','2011-11-26 20:00:58','234234','220102198112034412','申报',''),(11,8,'admin','管理员','2011-11-26 20:01:44','12313','220102198112034412','申报',''),(12,9,'admin','管理员','2011-11-26 20:02:27','1213','220102198112034412','申报',''),(13,9,'admin','管理员','2011-11-26 20:04:41','1213','220102198112034412','申报',''),(14,9,'admin','管理员','2011-11-26 20:17:46','1213','220102198112034412','初审','13123'),(15,6,'admin','管理员','2011-11-26 20:19:20','234234','220102198112034412','初审',''),(16,9,'admin','管理员','2011-11-26 20:29:48','1213','220102198112034412','核对','eee'),(17,-1,'admin','admin','2011-11-26 13:00:00','zhangsan','220102198112034412','申报',''),(18,-1,'admin','admin','2011-11-26 13:00:00','zhangsan','220102198112034412','申报',''),(19,-1,'admin','admin','2011-11-26 13:00:00','zhangsan','220102198112034412','申报','');

/*Table structure for table `test_abc` */

DROP TABLE IF EXISTS `test_abc`;

CREATE TABLE `test_abc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT '',
  `date` varchar(50) DEFAULT '',
  `phone` varchar(50) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `test_abc` */

insert  into `test_abc`(`id`,`name`,`date`,`phone`) values (2,'张三','2011-10-17','13596160570');

/*Table structure for table `test_ust` */

DROP TABLE IF EXISTS `test_ust`;

CREATE TABLE `test_ust` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT '',
  `phone` varchar(50) DEFAULT '',
  `email` varchar(50) DEFAULT '',
  `used` varchar(50) DEFAULT 'boolean.true',
  `desc` varchar(50) DEFAULT '',
  `birthday` varchar(50) DEFAULT '',
  `usedName` varchar(50) DEFAULT '是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `test_ust` */

insert  into `test_ust`(`id`,`name`,`phone`,`email`,`used`,`desc`,`birthday`,`usedName`) values (1,'尴尬','13596160570','gg@oo.com','boolean.true','方法','2011-10-15','是'),(2,'尴尬','13596160570','guyuzmq@gmail.com','boolean.true','sfsdf','2011-10-15 11:24:33','是'),(3,'广告歌六','13555555556','55@66.776','boolean.true','ff6','2011-10-06 11:25:14','是'),(5,'广告歌','13577777777','55@11.com','boolean.true','333','2011-10-17 02:26:22','是'),(6,'方法','13596160570','gg@gg.com','boolean.true','sff','2011-10-16 02:37:47','是'),(7,'方法','13596160570','guyum@gmail.com','boolean.true','1232','2011-10-16','是'),(8,'发发发','13596160570','33@11.11','boolean.true','1','2011-10-16 02:39:32','是'),(9,'通天塔','13596160570','44@11.com','boolean.true','','2011-10-16 02:42:58','是'),(10,'探探','13596160570','gg@qq.com','boolean.true','','2011-10-16','是'),(11,'方法','13599999999','11@11.11','boolean.true','rrr','2011-11-09','是');

/*Table structure for table `web_article` */

DROP TABLE IF EXISTS `web_article`;

CREATE TABLE `web_article` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `cid` varchar(100) DEFAULT NULL,
  `title` varchar(200) DEFAULT NULL,
  `text` varchar(10000) DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL,
  `typeName` varchar(100) DEFAULT NULL,
  `state` varchar(100) DEFAULT NULL,
  `stateName` varchar(100) DEFAULT NULL,
  `count` int(11) DEFAULT '0',
  `createTime` datetime DEFAULT NULL,
  `operater` varchar(100) DEFAULT NULL,
  `operaterName` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8;

/*Data for the table `web_article` */

insert  into `web_article`(`id`,`cid`,`title`,`text`,`type`,`typeName`,`state`,`stateName`,`count`,`createTime`,`operater`,`operaterName`) values (13,'rongzhan.about.speak','董事长致辞','<img alt=\"\" src=\"/easyapp/attached/image/20111005/20111005001743_570.jpg\" />大家吃好喝好，喝好吃好1122333999<img alt=\"\" src=\"/easyapp/attached/image/20111017/20111017091057_261.bmp\" />','article.state.normal','普通','visible.true','显示',0,'2011-09-27 01:38:58','admin','管理员'),(14,'rongzhan.about.introduce','集团介绍','<p>\r\n	这是一个集团介绍，介绍啥呢333333为什么\r\n</p>\r\n<p>\r\n	阿斯多夫是否\r\n</p>\r\n<p>\r\n	asdfsdfasdfsdf\r\n</p>','article.state.normal','普通','visible.true','显示',0,'2011-09-27 01:40:00','admin','管理员'),(15,'rongzhan.about.glory','企业荣誉','<img alt=\"\" src=\"/easyapp/attached/image/20111014/20111014063720_910.bmp\" />企业老多荣誉了！真滴！111eee','article.state.normal','普通','visible.true','显示',0,'2011-09-27 01:41:09','admin','管理员'),(16,'rongzhan.about.structure','组织架构','什么组织呢？','article.state.normal','普通','visible.true','显示',0,'2011-09-27 03:16:34','admin','管理员'),(17,'rongzhan.about.course','发展历程','','article.state.normal','普通','visible.false','隐藏',0,'2011-09-28 08:02:01','admin','管理员'),(21,'rongzhan.insurance.sall','保险销售','4444啊啊啊uuu11','article.state.normal','普通','visible.true','显示',0,'2011-09-28 08:06:17','admin','管理员'),(22,'rongzhan.service.rescue','24小时救援','22','article.state.normal','普通','visible.true','显示',0,'2011-09-28 08:06:24','admin','管理员'),(23,'rongzhan.service.member','会员服务','333','article.state.normal','普通','visible.true','显示',0,'2011-09-28 08:06:49','admin','管理员'),(24,'rongzhan.insurance.indemnify','保险理赔','55555','article.state.normal','普通','visible.true','显示',0,'2011-09-28 08:09:01','admin','管理员'),(25,'rongzhan.service.book','在线预约','<img alt=\"\" src=\"http://maps.googleapis.com/maps/api/staticmap?center=43.88088320219475%2C125.24943320629882&amp;zoom=15&amp;size=558x360&amp;maptype=roadmap&amp;markers=43.88088320219475%2C125.24943320629882&amp;language=zh_CN&amp;sensor=false\" /><img alt=\"\" src=\"/easyapp/attached/image/20111011/20111011023626_650.bmp\" /><embed src=\"/easyapp/attached/media/20111002/20111002035431_773.jpg\" type=\"video/x-ms-asf-plugin\" width=\"550\" height=\"400\" autostart=\"false\" loop=\"true\" /> \r\n<p>\r\n	asfasf\r\n</p>\r\n<p>\r\n	asdfasdf\r\n</p>\r\n<p>\r\n	asfdsadf\r\n</p>\r\n<hr style=\"page-break-after:always;\" class=\"ke-pagebreak\" />\r\n<p>\r\n	ZXZX\r\n</p>\r\n<p>\r\n	asfasfd\r\n</p>\r\n<p>\r\n	asfasf\r\n</p>\r\n<p>\r\n	asdfasdf\r\n</p>','article.state.normal','普通','visible.true','显示',0,'2011-09-28 08:11:07','admin','管理员'),(26,'rongzhan.service.special','特殊服务','','article.state.normal','普通','visible.true','显示',0,'2011-09-28 08:11:20','admin','管理员'),(27,'rongzhan.service.test','验车中心','asdfsfsdfff','article.state.normal','普通','visible.true','显示',0,'2011-09-28 08:12:19','rongzhan','融展集团'),(43,'rongzhan.loan.toyota','丰田金融','<img alt=\"\" src=\"/easyapp/attached/image/20110929/20110929080127_311.bmp\" />ttttthhhiii','article.state.normal','普通','visible.true','显示',0,'2011-09-28 11:40:50','admin','管理员'),(44,'rongzhan.loan.gm','通用金融','aa','article.state.recommand','推荐','visible.true','显示',0,'2011-09-28 11:41:11','admin','管理员'),(47,'rongzhan','融展集团','ggggaaa','article.state.normal','普通','visible.true','显示',0,'2011-09-28 13:32:39','admin','管理员'),(50,'rongzhan.new.enterprise','aaaaaa','aaaaaa','article.state.normal','普通','visible.true','显示',0,'2011-09-28 14:12:45','admin','管理员'),(51,'rongzhan.loan.fw','大众金融','','article.state.normal','普通','visible.true','显示',0,'2011-09-28 14:13:36','admin','管理员'),(53,'rongzhan.hr','人才招聘','&nbsp;\r\n<table border=\"1\" cellspacing=\"0\" bordercolor=\"#000000\" cellpadding=\"0\" align=\"center\">\r\n	<tbody>\r\n		<tr>\r\n			<td valign=\"top\" width=\"114\">\r\n				<p>\r\n					<span style=\"font-family:宋体;\">阿速度法司法</span><span></span> \r\n				</p>\r\n			</td>\r\n			<td valign=\"top\" width=\"114\">\r\n				<p>\r\n					<span>aa</span> \r\n				</p>\r\n			</td>\r\n			<td valign=\"top\" width=\"114\">\r\n				<p>\r\n					<span>aa</span> \r\n				</p>\r\n			</td>\r\n			<td valign=\"top\" width=\"114\">\r\n				<p>\r\n					<span>aa</span> \r\n				</p>\r\n			</td>\r\n			<td valign=\"top\" width=\"114\">\r\n				<p>\r\n					<span>aa</span> \r\n				</p>\r\n			</td>\r\n		</tr>\r\n		<tr>\r\n			<td valign=\"top\" width=\"114\">\r\n				<p>\r\n					<span>sfasfd</span> \r\n				</p>\r\n			</td>\r\n			<td valign=\"top\" width=\"114\">\r\n				<p>\r\n					<span>sdf</span> \r\n				</p>\r\n			</td>\r\n			<td valign=\"top\" width=\"114\">\r\n				<p>\r\n					<span>a</span> \r\n				</p>\r\n			</td>\r\n			<td valign=\"top\" width=\"114\">\r\n				<p>\r\n					<span>sdfsdf</span> \r\n				</p>\r\n			</td>\r\n			<td valign=\"top\" width=\"114\">\r\n				<p>\r\n					<span>df</span> \r\n				</p>\r\n			</td>\r\n		</tr>\r\n		<tr>\r\n			<td valign=\"top\" width=\"114\">\r\n				<p>\r\n					<span>asdfasdf</span> \r\n				</p>\r\n			</td>\r\n			<td valign=\"top\" width=\"114\">\r\n				<p>\r\n					<span>sdf</span> \r\n				</p>\r\n			</td>\r\n			<td valign=\"top\" width=\"114\">\r\n				<p>\r\n					<span>aa</span> \r\n				</p>\r\n			</td>\r\n			<td valign=\"top\" width=\"114\">\r\n				<p>\r\n					<span>f</span> \r\n				</p>\r\n			</td>\r\n			<td valign=\"top\" width=\"114\">\r\n				<p>\r\n					<span>aa</span> \r\n				</p>\r\n			</td>\r\n		</tr>\r\n		<tr>\r\n			<td valign=\"top\" width=\"114\">\r\n				<p>\r\n					<span>aaa</span> \r\n				</p>\r\n			</td>\r\n			<td valign=\"top\" width=\"114\">\r\n				<p>\r\n					<span>asdf</span> \r\n				</p>\r\n			</td>\r\n			<td valign=\"top\" width=\"114\">\r\n				<p>\r\n					<span>aa</span> \r\n				</p>\r\n			</td>\r\n			<td valign=\"top\" width=\"114\">\r\n				<p>\r\n					<span>aa</span> \r\n				</p>\r\n			</td>\r\n			<td valign=\"top\" width=\"114\">\r\n				<p>\r\n					<span>s</span> \r\n				</p>\r\n			</td>\r\n		</tr>\r\n	</tbody>\r\n</table>\r\n<p>\r\n	<input value=\"bb\" name=\"aa\" /></input />&nbsp;\r\n</p>\r\n<hr style=\"page-break-after:always;\" class=\"ke-pagebreak\" />\r\n<p>\r\n	<br />\r\n&nbsp;\r\n</p>\r\n<table border=\"1\" cellspacing=\"0\" bordercolor=\"#000000\" cellpadding=\"0\">\r\n	<tbody>\r\n		<tr>\r\n			<td style=\"background-color:transparent;\" valign=\"top\" width=\"114\">\r\n				<p>\r\n					<span><span style=\"font-family:宋体;\">阿速度法司法</span><span></span></span> \r\n				</p>\r\n			</td>\r\n			<td style=\"background-color:transparent;\" valign=\"top\" width=\"114\">\r\n				<p>\r\n					<span></span>\r\n				</p>\r\n			</td>\r\n			<td style=\"background-color:transparent;\" valign=\"top\" width=\"114\">\r\n				<p>\r\n					<span></span>\r\n				</p>\r\n			</td>\r\n			<td style=\"background-color:transparent;\" valign=\"top\" width=\"114\">\r\n				<p>\r\n					<span></span>\r\n				</p>\r\n			</td>\r\n			<td style=\"background-color:transparent;\" valign=\"top\" width=\"114\">\r\n				<p>\r\n					<span></span>\r\n				</p>\r\n			</td>\r\n		</tr>\r\n		<tr>\r\n			<td style=\"background-color:transparent;\" valign=\"top\" width=\"114\">\r\n				<p>\r\n					<span><span><span>sfasfd</span></span></span> \r\n				</p>\r\n			</td>\r\n			<td style=\"background-color:transparent;\" valign=\"top\" width=\"114\">\r\n				<p>\r\n					<span><span><span>sdf</span></span></span> \r\n				</p>\r\n			</td>\r\n			<td style=\"background-color:transparent;\" valign=\"top\" width=\"114\">\r\n				<p>\r\n					<span></span>\r\n				</p>\r\n			</td>\r\n			<td style=\"background-color:transparent;\" valign=\"top\" width=\"114\">\r\n				<p>\r\n					<span><span><span>sdfsdf</span></span></span> \r\n				</p>\r\n			</td>\r\n			<td style=\"background-color:transparent;\" valign=\"top\" width=\"114\">\r\n				<p>\r\n					<span><span><span>df</span></span></span> \r\n				</p>\r\n			</td>\r\n		</tr>\r\n		<tr>\r\n			<td style=\"background-color:transparent;\" valign=\"top\" width=\"114\">\r\n				<p>\r\n					<span><span><span>asdfasdf</span></span></span> \r\n				</p>\r\n			</td>\r\n			<td style=\"background-color:transparent;\" valign=\"top\" width=\"114\">\r\n				<p>\r\n					<span><span><span>sdf</span></span></span> \r\n				</p>\r\n			</td>\r\n			<td style=\"background-color:transparent;\" valign=\"top\" width=\"114\">\r\n				<p>\r\n					<span></span>\r\n				</p>\r\n			</td>\r\n			<td style=\"background-color:transparent;\" valign=\"top\" width=\"114\">\r\n				<p>\r\n					<span><span><span>f</span></span></span> \r\n				</p>\r\n			</td>\r\n			<td style=\"background-color:transparent;\" valign=\"top\" width=\"114\">\r\n				<p>\r\n					<span></span>\r\n				</p>\r\n			</td>\r\n		</tr>\r\n		<tr>\r\n			<td style=\"background-color:transparent;\" valign=\"top\" width=\"114\">\r\n				<p>\r\n					<span></span>\r\n				</p>\r\n			</td>\r\n			<td style=\"background-color:transparent;\" valign=\"top\" width=\"114\">\r\n				<p>\r\n					<span><span><span>asdf</span></span></span> \r\n				</p>\r\n			</td>\r\n			<td style=\"background-color:transparent;\" valign=\"top\" width=\"114\">\r\n				<p>\r\n					<span></span>\r\n				</p>\r\n			</td>\r\n			<td style=\"background-color:transparent;\" valign=\"top\" width=\"114\">\r\n				<p>\r\n					<span></span>\r\n				</p>\r\n			</td>\r\n			<td style=\"background-color:transparent;\" valign=\"top\" width=\"114\">\r\n				<p>\r\n					<span><span><span>s</span></span></span> \r\n				</p>\r\n			</td>\r\n		</tr>\r\n	</tbody>\r\n</table>\r\n&nbsp;','article.state.normal','普通','visible.true','显示',0,'2011-09-28 14:27:01','admin','管理员'),(54,'rongzhan.contact','联系我们','<p>\r\n	<strong><span style=\"background-color:#ff9900;\">&nbsp;aaaa</span></strong> \r\n</p>\r\n<hr style=\"page-break-after:always;\" class=\"ke-pagebreak\" />','article.state.normal','普通','visible.true','显示',0,'2011-09-28 14:27:06','admin','管理员'),(56,'rongzhan.new.enterprise','hhh','hhhh','article.state.normal','普通','visible.true','显示',0,'2011-09-29 05:03:23','admin','管理员'),(57,'rongzhan.new.enterprise','ttt','ttttt','article.state.normal','普通','visible.true','显示',0,'2011-09-29 05:03:28','admin','管理员'),(58,'rongzhan.new.car','jjjj','jjjj','article.state.normal','普通','visible.true','显示',0,'2011-09-29 05:06:44','admin','管理员'),(59,'rongzhan.new.car','hhhh','hhhh','article.state.normal','普通','visible.false','隐藏',0,'2011-09-29 05:07:07','admin','管理员'),(60,'rongzhan.new.car','111','','article.state.normal','普通','visible.true','显示',0,'2011-09-29 10:44:54','admin','管理员'),(61,'rongzhan.new.car','tttt','','article.state.normal','普通','visible.true','显示',0,'2011-09-29 10:45:22','admin','管理员'),(62,'rongzhan.new.enterprise','aaa','bbb','article.state.normal','普通','visible.true','显示',0,'2011-09-29 10:57:19','admin','管理员'),(63,'rongzhan.new.enterprise','ffffffff','ffffffffffffff','article.state.normal','普通','visible.true','显示',0,'2011-09-29 10:58:55','admin','管理员'),(65,'rongzhan.new.enterprise','bbbbbbb','<strong>bbbbbbbbbbbbbbbbbbbbbvvv</strong>','article.state.normal','普通','visible.true','显示',0,'2011-09-29 11:31:24','admin','管理员'),(66,'rongzhan.new.sall','fff','FFFvv','article.state.normal','普通','visible.true','显示',0,'2011-09-29 11:59:12','admin','管理员'),(68,'rongzhan.new.car','tttt','<strong></strong><span style=\"background-color:#ffe500;\"><strong><em><img alt=\"\" src=\"/easyapp/attached/image/20110929/20110929125415_554.bmp\" /><img alt=\"\" src=\"/easyapp/attached/image/20111005/20111005001743_570.jpg\" />tttt</em></strong></span>','article.state.normal','普通','visible.true','显示',0,'2011-09-29 13:12:12','admin','管理员'),(69,'rongzhan.loan.11','放松放松的','<p>\r\n	asdfasdf\r\n</p>\r\n<p>\r\n	&nbsp;\r\n</p>\r\n<p>\r\n	asdfasfasdf\r\n</p>','article.state.normal','普通','visible.true','显示',0,'2011-10-10 03:36:47','admin','管理员'),(71,'rongzhan.new.enterprise','xzcvxcvxcv','zxcvzxcv<img alt=\"\" src=\"/easyapp/attached/image/20111017/20111017091057_261.bmp\" />','article.state.normal','普通','visible.true','显示',0,'2011-10-17 09:11:34','admin','管理员'),(72,'test.home','测试','一个测试','article.state.recommand','推荐','visible.true','可见',0,'2011-11-17 09:56:58','admin','管理员');

/*Table structure for table `web_content` */

DROP TABLE IF EXISTS `web_content`;

CREATE TABLE `web_content` (
  `id` varchar(100) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `english` varchar(100) DEFAULT NULL,
  `path` varchar(200) DEFAULT NULL,
  `parentId` varchar(100) DEFAULT NULL,
  `sort` int(11) NOT NULL AUTO_INCREMENT,
  `hasShow` varchar(100) DEFAULT NULL,
  `hasShowName` varchar(100) DEFAULT NULL,
  `mode` varchar(100) DEFAULT 'content.mode.one',
  `modeName` varchar(100) DEFAULT '单一',
  PRIMARY KEY (`sort`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;

/*Data for the table `web_content` */

insert  into `web_content`(`id`,`name`,`english`,`path`,`parentId`,`sort`,`hasShow`,`hasShowName`,`mode`,`modeName`) values ('rongzhan','融展集团','','',NULL,1,'visible.true','显示','content.mode.none','空项'),('rongzhan.home','首页','Home','index.jsp','rongzhan',22,'visible.true','显示','content.mode.none','空项'),('rongzhan.about','集团介绍','About','','rongzhan',23,'visible.true','显示','content.mode.none','空项'),('rongzhan.new','新闻中心','New','','rongzhan',29,'visible.true','显示','content.mode.more','多项'),('rongzhan.service','集团服务','Services','','rongzhan',30,'visible.true','显示','content.mode.none','空项'),('rongzhan.insurance','保险中心','Insurance','','rongzhan',31,'visible.true','显示','content.mode.none','空项'),('rongzhan.loan','分期贷款','Loan','','rongzhan',32,'visible.true','显示','content.mode.none','空项'),('rongzhan.hr','人才招聘','HR','','rongzhan',33,'visible.true','显示','content.mode.one','单项'),('rongzhan.contact','联系我们','Contact','','rongzhan',34,'visible.true','显示','content.mode.one','单项'),('rongzhan.about.speak','董事长致辞','Speak','','rongzhan.about',35,'visible.true','显示','content.mode.one','单项'),('rongzhan.about.introduce','集团介绍','Introduce','','rongzhan.about',36,'visible.true','显示','content.mode.one','单项'),('rongzhan.about.course','发展历程','Course','','rongzhan.about',37,'visible.true','显示','content.mode.one','单项'),('rongzhan.about.glory','企业荣誉','Glory','','rongzhan.about',38,'visible.true','显示','content.mode.one','单项'),('rongzhan.about.structure','组织架构','Structur','','rongzhan.about',39,'visible.true','显示','content.mode.one','单项'),('rongzhan.new.enterprise','企业新闻','Enterprise','','rongzhan.new',40,'visible.true','显示','content.mode.more','多项'),('rongzhan.new.car','行业新闻','Car','','rongzhan.new',41,'visible.true','显示','content.mode.more','多项'),('rongzhan.new.sall','促销新闻','Sall','','rongzhan.new',42,'visible.true','显示','content.mode.more','多项'),('rongzhan.service.test','验车中心','Test','','rongzhan.service',43,'visible.true','显示','content.mode.one','单项'),('rongzhan.service.rescue','24小时救援','Rescue','','rongzhan.service',44,'visible.true','显示','content.mode.one','单项'),('rongzhan.service.special','特殊服务','Special','','rongzhan.service',45,'visible.true','显示','content.mode.one','单项'),('rongzhan.service.member','会员服务','Memer','','rongzhan.service',46,'visible.true','显示','content.mode.one','单项'),('rongzhan.service.book','在线预约','Book','','rongzhan.service',47,'visible.true','显示','content.mode.one','单项'),('rongzhan.insurance.sall','保险销售','Sall','','rongzhan.insurance',48,'visible.true','显示','content.mode.one','单项'),('rongzhan.insurance.indemnify','保险理赔','Indemnify','','rongzhan.insurance',49,'visible.true','显示','content.mode.one','单项'),('rongzhan.loan.toyota','丰田金融','Toyota','','rongzhan.loan',50,'visible.true','显示','content.mode.one','单项'),('rongzhan.loan.gm','通用金融','GM','','rongzhan.loan',51,'visible.true','显示','content.mode.one','单项'),('rongzhan.loan.fw','大众金融','FW','','rongzhan.loan',52,'visible.true','显示','content.mode.one','单项'),('rongzhan.loan.11','放松放松的','sdfsf','','rongzhan.loan',53,'visible.true','显示','content.mode.one','单项');

/*Table structure for table `web_message` */

DROP TABLE IF EXISTS `web_message`;

CREATE TABLE `web_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cid` varchar(100) DEFAULT NULL,
  `state` varchar(100) DEFAULT NULL,
  `stateName` varchar(100) DEFAULT NULL,
  `hasShow` varchar(100) DEFAULT NULL,
  `hasShowName` varchar(100) DEFAULT NULL,
  `sendTime` datetime DEFAULT NULL,
  `sendText` varchar(1000) DEFAULT NULL,
  `sendEmail` varchar(100) DEFAULT NULL,
  `sendPhone` varchar(100) DEFAULT NULL,
  `sendName` varchar(100) DEFAULT NULL,
  `receiveTime` datetime DEFAULT NULL,
  `receiveText` varchar(1000) DEFAULT NULL,
  `receiveOperater` varchar(100) DEFAULT NULL,
  `receiveOperaterName` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `web_message` */

insert  into `web_message`(`id`,`cid`,`state`,`stateName`,`hasShow`,`hasShowName`,`sendTime`,`sendText`,`sendEmail`,`sendPhone`,`sendName`,`receiveTime`,`receiveText`,`receiveOperater`,`receiveOperaterName`) values (1,'rongzhan.about','message.state.false','未回复','visible.true','显示','2011-09-29 02:44:23','tttpp','11@11.11pp','13555555550','vvvppp','2011-09-29 02:44:23','vvvffffppp','admin','管理员'),(2,'rongzhan','message.state.true','已回复','visible.false','隐藏','2011-09-29 03:39:08','hhhh','11@11.11','13967895555','123123123','2011-09-29 03:39:08','1412412412','admin','管理员'),(3,'rongzhan','message.state.true','已回复','visible.false','隐藏','2011-09-29 03:44:07','aa\r\nbb\r\ncc','gg@11.com','13596160505','123','2011-09-29 03:44:07','124124\r\n123123\r\n123123123','admin','管理员');

/*Table structure for table `wf_cus_askleave` */

DROP TABLE IF EXISTS `wf_cus_askleave`;

CREATE TABLE `wf_cus_askleave` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `instanceId` int(11) NOT NULL,
  `name` varchar(50) DEFAULT 'creater',
  `start` varchar(50) DEFAULT '',
  `end` varchar(50) DEFAULT '',
  `days` varchar(50) DEFAULT '',
  `departmentAdvise` varchar(50) DEFAULT '',
  `hrAdvise` varchar(50) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `wf_cus_askleave` */

insert  into `wf_cus_askleave`(`id`,`instanceId`,`name`,`start`,`end`,`days`,`departmentAdvise`,`hrAdvise`) values (1,57,'管理员','2011-11-06 09:34:14','2011-11-09 09:34:14','3','yes','yes'),(2,52,'管理员','2011-11-06 09:36:48','2011-11-15 09:36:49','12','',''),(3,42,'管理员','2011-11-06 10:48:14','2011-11-16 00:00:00','5','yes','yes'),(4,44,'管理员','2011-11-06 00:00:00','2011-11-06 00:00:00','5','',''),(5,47,'管理员','null','2011-11-06','5','',''),(6,43,'管理员','2011-11-06','2011-11-08','5','',''),(7,58,'管理员','2011-11-06','2011-11-07','6','no',''),(8,59,'管理员','2011-11-08','2011-11-17','3','',''),(9,60,'管理员','2011-11-06','2011-11-07','1','yes','yes'),(10,61,'管理员','2011-11-06','2011-11-07','1','yes','yes'),(11,62,'管理员','2011-11-09','2011-11-10','1','yes','yes');

/*Table structure for table `wf_cus_test` */

DROP TABLE IF EXISTS `wf_cus_test`;

CREATE TABLE `wf_cus_test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `instanceId` int(11) NOT NULL,
  `name` varchar(50) DEFAULT '',
  `age` varchar(50) DEFAULT '',
  `gender` varchar(50) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `wf_cus_test` */

/*Table structure for table `wf_form_item` */

DROP TABLE IF EXISTS `wf_form_item`;

CREATE TABLE `wf_form_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `showName` varchar(50) DEFAULT NULL,
  `defaultValue` varchar(500) DEFAULT NULL,
  `flowId` int(11) DEFAULT NULL,
  `min` varchar(50) DEFAULT NULL,
  `max` varchar(50) DEFAULT NULL,
  `regex` varchar(50) DEFAULT NULL,
  `regexName` varchar(50) DEFAULT NULL,
  `focusTip` varchar(200) DEFAULT NULL,
  `func` varchar(2000) DEFAULT NULL,
  `dict` varchar(50) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `typeName` varchar(50) DEFAULT NULL,
  `render` varchar(2000) DEFAULT NULL,
  `formType` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `wf_form_item` */

insert  into `wf_form_item`(`id`,`name`,`showName`,`defaultValue`,`flowId`,`min`,`max`,`regex`,`regexName`,`focusTip`,`func`,`dict`,`type`,`typeName`,`render`,`formType`) values (2,'name','姓名','',10,'0','50','','ACSII码','','function() {this.isvalid=true;}','','form.type.input','输入框','',NULL),(3,'age','年龄','',10,'0','50','','','','function() {this.isvalid=true;}','','form.type.input','输入框','',NULL),(4,'gender','性别','',10,'0','50','','','','function() {this.isvalid=true;}','','form.type.input','输入框','',NULL),(5,'name','请假人','creater',11,'1','50','','','','function() {this.isvalid=true;}','','form.type.creater','创建者','',NULL),(6,'start','开始时间','',11,'1','50','','','','function() {this.isvalid=true;}','','form.type.datebox','日期','',NULL),(7,'end','结束时间','',11,'1','50','','','','function() {this.isvalid=true;}','','form.type.datebox','日期','',NULL),(8,'days','总天数','',11,'1','50','integer','整数','','function() {this.isvalid=true;}','','form.type.input','输入框','',NULL),(9,'departmentAdvise','部门领导意见','',11,'1','50','','','','function() {this.isvalid=true;}','advise','form.type.combox','单选框','',NULL),(10,'hrAdvise','人力资源意见','',11,'1','50','','','','function() {this.isvalid=true;}','advise','form.type.combox','单选框','',NULL),(11,'名称','zhangsan','10',3,'1','10','regex.id','标识','请输入名称','','','form.type.input','输入框','',NULL);

/*Table structure for table `wf_line` */

DROP TABLE IF EXISTS `wf_line`;

CREATE TABLE `wf_line` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `flowId` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `startId` int(11) DEFAULT NULL,
  `startName` varchar(50) DEFAULT NULL,
  `endId` int(11) DEFAULT NULL,
  `endName` varchar(50) DEFAULT NULL,
  `reject` varchar(50) DEFAULT NULL,
  `express` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `wf_line` */

insert  into `wf_line`(`id`,`flowId`,`name`,`startId`,`startName`,`endId`,`endName`,`reject`,`express`) values (1,10,'fff',3,'f1',4,'f2','否','fff3'),(2,10,'bbb',4,'f2',5,'f3','否','sfsdf'),(3,11,'l1',6,'第一步：请假申请',7,'第二步：部门意见','否',''),(4,11,'l2',7,'第二步：部门意见',8,'第三步：人力资源意见','否',''),(5,11,'l3',8,'第三步：人力资源意见',9,'结果','否','');

/*Table structure for table `wf_node` */

DROP TABLE IF EXISTS `wf_node`;

CREATE TABLE `wf_node` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `flowId` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `typeName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `wf_node` */

insert  into `wf_node`(`id`,`flowId`,`name`,`type`,`typeName`) values (3,10,'f1','flow.node.create','创建节点'),(4,10,'f2','flow.node.process','过程节点'),(5,10,'f3','flow.node.realize','结束节点'),(6,11,'第一步：请假申请','flow.node.create','创建节点'),(7,11,'第二步：部门意见','flow.node.process','过程节点'),(8,11,'第三步：人力资源意见','flow.node.process','过程节点'),(9,11,'结果','flow.node.realize','结束节点');

/*Table structure for table `wf_node_condition` */

DROP TABLE IF EXISTS `wf_node_condition`;

CREATE TABLE `wf_node_condition` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nodeId` int(11) DEFAULT NULL,
  `model` varchar(50) DEFAULT NULL,
  `modelName` varchar(50) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `typeName` varchar(50) DEFAULT NULL,
  `value` varchar(500) DEFAULT NULL,
  `valueName` varchar(500) DEFAULT NULL,
  `sign` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

/*Data for the table `wf_node_condition` */

insert  into `wf_node_condition`(`id`,`nodeId`,`model`,`modelName`,`type`,`typeName`,`value`,`valueName`,`sign`) values (8,7,'node.condition.workflow','工作流','node.condition.creater','创建者','node.condition.creater.self','本人','否'),(9,8,'node.condition.workflow','工作流','node.condition.creater','创建者','node.condition.creater.self','本人','否'),(10,9,'node.condition.workflow','工作流','node.condition.creater','创建者','node.condition.creater.self','本人','否'),(12,4,'node.condition.auth','权限','node.condition.auth.department','部门','rrr','rrr','否'),(28,6,'node.condition.workflow','工作流','node.condition.creater','创建者','node.condition.creater.self','本人','否'),(42,3,'node.condition.model.auth','权限','node.condition.type.user','用户','admin, rongzhan','管理员, 融展','否'),(43,3,'node.condition.model.auth','权限','node.condition.type.user','用户','admin, rongzhan','管理员, 融展','否'),(44,3,'node.condition.model.auth','权限','node.condition.type.user','用户','admin, rongzhan','管理员, 融展','否'),(45,3,'node.condition.model.auth','权限','node.condition.type.user','用户','admin, rongzhan','管理员, 融展','否');

/*Table structure for table `wf_node_item_state` */

DROP TABLE IF EXISTS `wf_node_item_state`;

CREATE TABLE `wf_node_item_state` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `flowId` int(11) DEFAULT NULL,
  `nodeId` int(11) DEFAULT NULL,
  `itemName` varchar(50) DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=173 DEFAULT CHARSET=utf8;

/*Data for the table `wf_node_item_state` */

insert  into `wf_node_item_state`(`id`,`flowId`,`nodeId`,`itemName`,`state`) values (43,10,3,'name','必填'),(44,10,4,'name','只读'),(45,10,5,'name','只读'),(46,10,3,'age','选填'),(47,10,4,'age','只读'),(48,10,5,'age','只读'),(49,10,3,'gender','只读'),(50,10,4,'gender','只读'),(51,10,5,'gender','只读'),(132,11,6,'name','只读'),(133,11,7,'name','只读'),(134,11,8,'name','只读'),(135,11,9,'name','只读'),(136,11,6,'start','必填'),(137,11,7,'start','只读'),(138,11,8,'start','只读'),(139,11,9,'start','只读'),(140,11,6,'end','必填'),(141,11,7,'end','只读'),(142,11,8,'end','只读'),(143,11,9,'end','只读'),(144,11,6,'days','必填'),(145,11,7,'days','只读'),(146,11,8,'days','只读'),(147,11,9,'days','只读'),(148,11,6,'departmentAdvise','隐藏'),(149,11,7,'departmentAdvise','必填'),(150,11,8,'departmentAdvise','只读'),(151,11,9,'departmentAdvise','只读'),(152,11,6,'hrAdvise','隐藏'),(153,11,7,'hrAdvise','隐藏'),(154,11,8,'hrAdvise','必填'),(155,11,9,'hrAdvise','只读'),(172,-1,2,'name','node.state.normal');

/*Table structure for table `wf_runtime_instance` */

DROP TABLE IF EXISTS `wf_runtime_instance`;

CREATE TABLE `wf_runtime_instance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `flowId` int(11) DEFAULT NULL,
  `flowName` varchar(50) DEFAULT NULL,
  `flowShowName` varchar(50) DEFAULT NULL,
  `creater` varchar(50) DEFAULT NULL,
  `createrName` varchar(50) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8;

/*Data for the table `wf_runtime_instance` */

insert  into `wf_runtime_instance`(`id`,`flowId`,`flowName`,`flowShowName`,`creater`,`createrName`,`createTime`,`endTime`) values (35,11,NULL,'AskLeave','admin','管理员','2011-10-21 13:08:36',NULL),(36,11,NULL,'AskLeave','admin','管理员','2011-10-21 13:09:39',NULL),(37,11,NULL,'AskLeave','admin','管理员','2011-10-21 13:10:48',NULL),(38,11,NULL,'AskLeave','admin','管理员','2011-10-21 13:12:25','2011-10-21 13:12:49'),(39,11,NULL,'AskLeave','admin','管理员','2011-10-24 02:17:45','2011-10-24 02:18:07'),(40,11,NULL,'AskLeave','admin','管理员','2011-10-24 02:20:53','2011-10-24 02:21:12'),(41,11,NULL,'AskLeave','admin','管理员','2011-10-24 03:41:25',NULL),(42,11,NULL,'AskLeave','admin','管理员','2011-10-24 03:41:28','2011-11-06 11:12:13'),(43,11,NULL,'AskLeave','admin','管理员','2011-10-24 03:42:02',NULL),(44,11,NULL,'AskLeave','admin','管理员','2011-10-24 03:42:46',NULL),(45,11,NULL,'AskLeave','admin','管理员','2011-10-24 03:43:15',NULL),(46,11,NULL,'AskLeave','admin','管理员','2011-10-24 03:43:56',NULL),(47,11,NULL,'AskLeave','admin','管理员','2011-10-24 03:45:08',NULL),(48,11,NULL,'AskLeave','admin','管理员','2011-10-24 03:46:10',NULL),(49,11,NULL,'AskLeave','admin','管理员','2011-10-24 03:47:05','2011-10-24 03:47:55'),(50,11,NULL,'AskLeave','admin','管理员','2011-10-24 03:48:54',NULL),(51,11,'AskLeave','请假','admin','管理员','2011-10-24 07:58:37','2011-10-24 07:58:50'),(52,11,'AskLeave','请假','admin','管理员','2011-11-05 04:54:32',NULL),(53,11,'AskLeave','请假','admin','管理员','2011-11-05 04:56:57',NULL),(54,11,'AskLeave','请假','admin','管理员','2011-11-05 04:57:21','2011-11-05 04:57:43'),(55,11,'AskLeave','请假','admin','管理员','2011-11-05 12:56:35',NULL),(56,11,'AskLeave','请假','admin','管理员','2011-11-05 12:58:16','2011-11-05 12:59:14'),(57,11,'AskLeave','请假','admin','管理员','2011-11-06 01:32:09','2011-11-06 01:35:14'),(58,11,'AskLeave','请假','admin','管理员','2011-11-06 03:55:49',NULL),(59,11,'AskLeave','请假','admin','管理员','2011-11-06 05:08:45',NULL),(60,11,'AskLeave','请假','admin','管理员','2011-11-06 10:59:24','2011-11-06 10:59:59'),(61,11,'AskLeave','请假','admin','管理员','2011-11-06 11:10:50','2011-11-06 11:11:23'),(62,11,'AskLeave','请假','admin','管理员','2011-11-09 02:49:12','2011-11-09 02:49:52'),(79,-1,'testFlow','测试工作流','admin','管理员','2011-10-17 20:24:00',NULL);

/*Table structure for table `wf_runtime_process` */

DROP TABLE IF EXISTS `wf_runtime_process`;

CREATE TABLE `wf_runtime_process` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `instanceId` int(11) DEFAULT NULL,
  `flowId` int(11) DEFAULT NULL,
  `nodeId` int(11) DEFAULT NULL,
  `operaterId` varchar(50) DEFAULT NULL,
  `operaterName` varchar(50) DEFAULT NULL,
  `sign` varchar(10) DEFAULT NULL,
  `end` varchar(10) DEFAULT NULL,
  `confirm` varchar(10) DEFAULT NULL,
  `confirmTime` varchar(50) DEFAULT NULL,
  `endTime` varchar(50) DEFAULT NULL,
  `remark` varchar(1000) DEFAULT NULL,
  `createTime` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8;

/*Data for the table `wf_runtime_process` */

insert  into `wf_runtime_process`(`id`,`instanceId`,`flowId`,`nodeId`,`operaterId`,`operaterName`,`sign`,`end`,`confirm`,`confirmTime`,`endTime`,`remark`,`createTime`) values (29,35,11,6,'admin','管理员','否','是','是','2011-10-21 13:08:47','2011-10-21 13:08:47','',NULL),(30,35,11,7,'admin','管理员','否','是','是','2011-10-24 09:46:18','2011-10-24 09:46:18','',NULL),(31,36,11,6,'admin','管理员','否','是','是','2011-10-21 13:09:44','2011-10-21 13:09:44','',NULL),(32,36,11,7,'admin','管理员','否','是','是','2011-10-21 13:10:35','2011-10-21 13:10:35','',NULL),(33,37,11,6,'admin','管理员','否','是','是','2011-10-21 13:10:54','2011-10-21 13:10:54','',NULL),(34,37,11,7,'admin','管理员','否','是','是','2011-10-21 13:10:58','2011-10-21 13:10:58','',NULL),(35,38,11,6,'admin','管理员','否','是','是','2011-10-21 13:12:40','2011-10-21 13:12:40','',NULL),(36,38,11,7,'admin','管理员','否','是','是','2011-10-21 13:12:44','2011-10-21 13:12:44','',NULL),(37,38,11,8,'admin','管理员','否','是','是','2011-10-21 13:12:49','2011-10-21 13:12:49','',NULL),(38,39,11,6,'admin','管理员','否','是','是','2011-10-24 02:17:56','2011-10-24 02:17:56','',NULL),(39,39,11,7,'admin','管理员','否','是','是','2011-10-24 02:18:02','2011-10-24 02:18:02','',NULL),(40,39,11,8,'admin','管理员','否','是','是','2011-10-24 02:18:07','2011-10-24 02:18:07','',NULL),(41,40,11,6,'admin','管理员','否','是','是','2011-10-24 02:21:02','2011-10-24 02:21:02','',NULL),(42,40,11,7,'admin','管理员','否','是','是','2011-10-24 02:21:07','2011-10-24 02:21:07','',NULL),(43,40,11,8,'admin','管理员','否','是','是','2011-10-24 02:21:12','2011-10-24 02:21:12','',NULL),(44,41,11,6,'admin','管理员','否','是','是','2011-10-24 12:34:01','2011-10-24 12:34:01','',NULL),(45,42,11,6,'admin','管理员','否','是','是','2011-11-06 05:36:04','2011-11-06 05:36:04','',NULL),(46,43,11,6,'admin','管理员','否','否','否','','','',NULL),(47,44,11,6,'admin','管理员','否','否','否','','','',NULL),(48,45,11,6,'admin','管理员','否','否','否','','','',NULL),(49,46,11,6,'admin','管理员','否','否','否','','','',NULL),(50,47,11,6,'admin','管理员','否','否','否','','','',NULL),(51,48,11,6,'admin','管理员','否','否','否','','','',NULL),(52,49,11,6,'admin','管理员','否','是','是','2011-10-24 03:47:49','2011-10-24 03:47:49','',NULL),(53,49,11,7,'admin','管理员','否','是','是','2011-10-24 03:47:52','2011-10-24 03:47:52','',NULL),(54,49,11,8,'admin','管理员','否','是','是','2011-10-24 03:47:55','2011-10-24 03:47:55','',NULL),(55,50,11,6,'admin','管理员','否','否','否','','','',NULL),(56,51,11,6,'admin','管理员','否','是','是','2011-10-24 07:58:43','2011-10-24 07:58:43','',NULL),(57,51,11,7,'admin','管理员','否','是','是','2011-10-24 07:58:47','2011-10-24 07:58:47','',NULL),(58,51,11,8,'admin','管理员','否','是','是','2011-10-24 07:58:50','2011-10-24 07:58:50','',NULL),(59,35,11,8,'admin','管理员','否','否','否','','','','2011-10-24 09:46:18'),(60,41,11,7,'admin','管理员','否','否','否','','','','2011-10-24 12:34:01'),(61,52,11,6,'admin','管理员','否','是','是','2011-11-06 01:37:39','2011-11-06 01:37:39','a','2011-11-05 04:54:32'),(62,52,11,6,'1','1','否','是','否','','2011-11-06 01:37:39','','2011-11-05 04:54:32'),(63,52,11,6,'2','2','否','是','否','','2011-11-06 01:37:39','','2011-11-05 04:54:32'),(64,53,11,6,'admin','管理员','否','是','是','2011-11-05 14:25:48','2011-11-05 14:25:48','','2011-11-05 04:56:57'),(65,54,11,6,'admin','管理员','否','是','是','2011-11-05 04:57:28','2011-11-05 04:57:28','','2011-11-05 04:57:21'),(66,54,11,7,'admin','管理员','否','是','是','2011-11-05 04:57:39','2011-11-05 04:57:39','','2011-11-05 04:57:28'),(67,54,11,8,'admin','管理员','否','是','是','2011-11-05 04:57:43','2011-11-05 04:57:43','','2011-11-05 04:57:39'),(68,56,11,6,'admin','管理员','否','是','是','2011-11-05 12:58:51','2011-11-05 12:58:51','','2011-11-05 12:58:40'),(69,56,11,7,'admin','管理员','否','是','是','2011-11-05 12:58:57','2011-11-05 12:58:57','','2011-11-05 12:58:51'),(70,56,11,8,'admin','管理员','否','是','是','2011-11-05 12:59:14','2011-11-05 12:59:14','','2011-11-05 12:58:57'),(71,53,11,7,'admin','管理员','否','是','是','2011-11-05 14:39:03','2011-11-05 14:39:03','','2011-11-05 14:25:48'),(72,53,11,8,'admin','管理员','否','否','否','','','','2011-11-05 14:39:03'),(73,57,11,6,'admin','管理员','否','是','是','2011-11-06 01:34:45','2011-11-06 01:34:45','c','2011-11-06 01:32:09'),(74,57,11,7,'admin','管理员','否','是','是','2011-11-06 01:35:01','2011-11-06 01:35:01','c','2011-11-06 01:34:45'),(75,57,11,8,'admin','管理员','否','是','是','2011-11-06 01:35:14','2011-11-06 01:35:14','c','2011-11-06 01:35:01'),(76,52,11,7,'admin','管理员','否','否','否','','','','2011-11-06 01:37:39'),(77,58,11,6,'admin','管理员','否','是','是','2011-11-06 04:31:15','2011-11-06 04:31:15','77','2011-11-06 03:55:49'),(78,58,11,7,'admin','管理员','否','是','是','2011-11-06 05:11:44','2011-11-06 05:11:44','77','2011-11-06 04:31:15'),(79,59,11,6,'admin','管理员','否','是','是','2011-11-06 05:30:03','2011-11-06 05:30:03','','2011-11-06 05:08:45'),(80,58,11,8,'admin','管理员','否','否','否','','','','2011-11-06 05:11:44'),(81,59,11,7,'admin','管理员','否','否','否','','','','2011-11-06 05:30:03'),(82,42,11,7,'admin','管理员','否','是','是','2011-11-06 05:36:22','2011-11-06 05:36:22','','2011-11-06 05:36:04'),(83,42,11,8,'admin','管理员','否','是','是','2011-11-06 11:12:13','2011-11-06 11:12:13','v','2011-11-06 05:36:22'),(84,60,11,6,'admin','管理员','否','是','是','2011-11-06 10:59:38','2011-11-06 10:59:38','c','2011-11-06 10:59:24'),(85,60,11,7,'admin','管理员','否','是','是','2011-11-06 10:59:46','2011-11-06 10:59:46','c','2011-11-06 10:59:38'),(86,60,11,8,'admin','管理员','否','是','是','2011-11-06 10:59:59','2011-11-06 10:59:59','c','2011-11-06 10:59:46'),(87,61,11,6,'admin','管理员','否','是','是','2011-11-06 11:11:05','2011-11-06 11:11:05','a','2011-11-06 11:10:50'),(88,61,11,7,'admin','管理员','否','是','是','2011-11-06 11:11:15','2011-11-06 11:11:15','b','2011-11-06 11:11:05'),(89,61,11,8,'admin','管理员','否','是','是','2011-11-06 11:11:23','2011-11-06 11:11:23','c','2011-11-06 11:11:15'),(90,62,11,6,'admin','管理员','否','是','是','2011-11-09 02:49:26','2011-11-09 02:49:26','a','2011-11-09 02:49:12'),(91,62,11,7,'admin','管理员','否','是','是','2011-11-09 02:49:44','2011-11-09 02:49:44','fff','2011-11-09 02:49:26'),(92,62,11,8,'admin','管理员','否','是','是','2011-11-09 02:49:52','2011-11-09 02:49:52','','2011-11-09 02:49:44');

/*Table structure for table `wf_workflow` */

DROP TABLE IF EXISTS `wf_workflow`;

CREATE TABLE `wf_workflow` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `showName` varchar(50) DEFAULT NULL,
  `creater` varchar(50) DEFAULT NULL,
  `createrName` varchar(50) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

/*Data for the table `wf_workflow` */

insert  into `wf_workflow`(`id`,`name`,`showName`,`creater`,`createrName`,`createTime`) values (8,'aa','尴尬','admin','管理员','2011-10-17 14:15:50'),(9,'bb_ff_cc','fffff','admin','管理员','2011-10-17 14:16:02'),(10,'test','测试工作流111','admin','管理员','2011-10-17 14:16:50'),(11,'AskLeave','请假','admin','管理员','2011-10-21 02:23:50'),(28,'test_flow','测试工作流','admin','管理员','2011-10-17 20:24:00');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
