/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : ptsite

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2019-02-13 17:05:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_data_sync
-- ----------------------------
DROP TABLE IF EXISTS `tb_data_sync`;
CREATE TABLE `tb_data_sync` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增标识',
  `source` int(2) NOT NULL COMMENT '来源',
  `account_name` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '帐号',
  `empl_name` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '员工姓名',
  `organ1` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '一级部门名',
  `organ2` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '二级部门名',
  `organ3` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '三级部门名',
  `company_name` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '客户公司名',
  `site_url` varchar(256) CHARACTER SET utf8 DEFAULT NULL COMMENT '网址',
  `email` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '员工邮箱',
  `empl_no` varchar(8) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '员工工号',
  `is_delete` int(2) DEFAULT '0' COMMENT '是否删除 0有效',
  `has_change` int(2) DEFAULT '0' COMMENT '变更位 0未变化',
  `note` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '说明',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modify` datetime DEFAULT NULL COMMENT '修订时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16673 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='数据中心';

-- ----------------------------
-- Table structure for tb_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `tb_dictionary`;
CREATE TABLE `tb_dictionary` (
  `dic_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '标识',
  `dic_group` varchar(32) CHARACTER SET utf8 NOT NULL COMMENT '组别',
  `dic_code` varchar(16) CHARACTER SET utf8 NOT NULL COMMENT '代码',
  `dic_value` varchar(128) CHARACTER SET utf8 NOT NULL COMMENT '值',
  `dic_flag` int(2) DEFAULT NULL COMMENT '排序',
  `dic_note` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '说明',
  `gmt_created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modify` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修订时间',
  PRIMARY KEY (`dic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for tb_employee
-- ----------------------------
DROP TABLE IF EXISTS `tb_employee`;
CREATE TABLE `tb_employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '员工标识',
  `did` varchar(8) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'DIS员工标识',
  `EMPL_NO` varchar(8) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '员工工号',
  `REAL_NAME` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '员工姓名',
  `ORG_ID` int(11) DEFAULT NULL COMMENT '组织标识',
  `ORG_NAME` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '组织全称',
  `MOBILE` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '手机号码',
  `EMAIL` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '邮箱号码',
  `ISACTIVE` int(2) DEFAULT '0' COMMENT '状态 0正常',
  `GMT_CREATE` datetime DEFAULT NULL COMMENT '创建时间',
  `GMT_MODIFIED` datetime DEFAULT NULL COMMENT '修订时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=732 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='员工信息';

-- ----------------------------
-- Table structure for tb_employee_site
-- ----------------------------
DROP TABLE IF EXISTS `tb_employee_site`;
CREATE TABLE `tb_employee_site` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `emp_id` int(11) DEFAULT NULL COMMENT '员工标识',
  `site_id` int(11) DEFAULT NULL COMMENT '站点标识',
  `SF_Name` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '十分帐号',
  `other` varchar(2) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  `GMT_CREATE` datetime NOT NULL COMMENT '创建时间',
  `GMT_MODIFIED` datetime DEFAULT NULL COMMENT '修订时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39869 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='员工十分信息';

-- ----------------------------
-- Table structure for tb_mail_history
-- ----------------------------
DROP TABLE IF EXISTS `tb_mail_history`;
CREATE TABLE `tb_mail_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `send_to` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '接收人',
  `titile` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '邮件标题',
  `content` blob COMMENT '邮件内容',
  `error_count` int(11) DEFAULT NULL COMMENT '异常数',
  `GMT_CREATE` datetime DEFAULT NULL COMMENT '发送时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='邮件发送记录';

-- ----------------------------
-- Table structure for tb_self_ip
-- ----------------------------
DROP TABLE IF EXISTS `tb_self_ip`;
CREATE TABLE `tb_self_ip` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `ip` varchar(255) NOT NULL,
  `count` int(11) DEFAULT NULL COMMENT '数量',
  `gmt_created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modify` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修订时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COMMENT='我司IP信息';

-- ----------------------------
-- Table structure for tb_sensitive_record
-- ----------------------------
DROP TABLE IF EXISTS `tb_sensitive_record`;
CREATE TABLE `tb_sensitive_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `site_id` int(11) DEFAULT NULL COMMENT '站点标识',
  `page` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '页面',
  `kw_id` int(11) DEFAULT NULL COMMENT '词标识',
  `key_word` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '词',
  `key_words` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '语境',
  `start` int(11) DEFAULT NULL COMMENT '起点',
  `end` int(11) DEFAULT NULL COMMENT '结束点',
  `status` int(2) DEFAULT '0' COMMENT '状态',
  `gmt_created` datetime DEFAULT NULL COMMENT '触发时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31794 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='敏感词记录';

-- ----------------------------
-- Table structure for tb_sensitive_word
-- ----------------------------
DROP TABLE IF EXISTS `tb_sensitive_word`;
CREATE TABLE `tb_sensitive_word` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `key_word` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `white_words` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` int(2) DEFAULT '0' COMMENT '状态 0正常 1停用',
  `gmt_created` datetime DEFAULT NULL COMMENT '新增时间',
  `gmt_modify` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1317 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='敏感词';

-- ----------------------------
-- Table structure for tb_sensitive_word_bak
-- ----------------------------
DROP TABLE IF EXISTS `tb_sensitive_word_bak`;
CREATE TABLE `tb_sensitive_word_bak` (
  `id` int(11) NOT NULL DEFAULT '0' COMMENT '标识',
  `key_word` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `white_words` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` int(2) DEFAULT '0' COMMENT '状态 0正常 1停用',
  `gmt_created` datetime DEFAULT NULL COMMENT '新增时间',
  `gmt_modify` datetime DEFAULT NULL COMMENT '修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for tb_site_history
-- ----------------------------
DROP TABLE IF EXISTS `tb_site_history`;
CREATE TABLE `tb_site_history` (
  `site_id` int(11) NOT NULL COMMENT '待检测网站标识',
  `page` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `title` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '标题',
  `content` mediumblob COMMENT '内容',
  `status` int(4) DEFAULT '200' COMMENT '响应代码',
  `comment` int(2) DEFAULT NULL COMMENT '评价 3响应慢4报警6安全级别高',
  `ip` varchar(16) CHARACTER SET utf8 DEFAULT NULL COMMENT '网站IP',
  `self_site` int(2) DEFAULT '0' COMMENT '我司网站 1我司',
  `gmt_created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '检测时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='异常网站历史记录';

-- ----------------------------
-- Table structure for tb_site_result
-- ----------------------------
DROP TABLE IF EXISTS `tb_site_result`;
CREATE TABLE `tb_site_result` (
  `site_id` int(11) NOT NULL COMMENT '待检测网站标识',
  `source` int(2) DEFAULT NULL COMMENT '检测源',
  `page` varchar(128) CHARACTER SET utf8 NOT NULL,
  `title` varchar(512) CHARACTER SET utf8 DEFAULT NULL COMMENT '标题',
  `content` mediumblob COMMENT '内容',
  `status` int(4) DEFAULT '200' COMMENT '响应代码',
  `comment` int(2) DEFAULT NULL COMMENT '评价 3响应慢4报警6安全级别高',
  `gmt_created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '检测时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='检测结果';

-- ----------------------------
-- Table structure for tb_site_statistics
-- ----------------------------
DROP TABLE IF EXISTS `tb_site_statistics`;
CREATE TABLE `tb_site_statistics` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '标识',
  `source` int(2) NOT NULL COMMENT '来源 0 IP检测',
  `site_total` int(8) NOT NULL DEFAULT '0' COMMENT '检测总量',
  `site_check` int(4) NOT NULL DEFAULT '0' COMMENT '待查总量',
  `site_error` int(4) DEFAULT '0' COMMENT '异常网站',
  `site_warn` int(4) DEFAULT '0' COMMENT '警报网站',
  `site_slow` int(4) DEFAULT '0' COMMENT '访问慢',
  `begin_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '开始时间',
  `end_time` timestamp NULL DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='检测结果统计';

-- ----------------------------
-- Table structure for tb_site_url
-- ----------------------------
DROP TABLE IF EXISTS `tb_site_url`;
CREATE TABLE `tb_site_url` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `page` varchar(256) NOT NULL COMMENT '待检测网址/落地页',
  `demin` varchar(128) DEFAULT NULL COMMENT '归属主域名',
  `source` int(1) NOT NULL DEFAULT '0' COMMENT '来源类型 0未分类1平台2推广',
  `need_check` int(1) NOT NULL DEFAULT '0' COMMENT '是否要检测 0检测1不检测',
  `charset` varchar(8) DEFAULT NULL COMMENT '文字编码',
  `ip` varchar(32) DEFAULT NULL COMMENT '现IP',
  `old_ip` varchar(32) DEFAULT NULL COMMENT '旧IP',
  `ip_checked` int(1) DEFAULT '0' COMMENT 'IP检测状态 0已检1待检',
  `self_site` int(1) DEFAULT '0' COMMENT '是否我司托管 0非1是',
  `dt` datetime DEFAULT NULL COMMENT 'IP变更时间',
  `customer` varchar(64) DEFAULT NULL COMMENT '客户',
  `error_time` datetime DEFAULT NULL COMMENT '最近异常时间',
  `gmt_created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modify` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修订时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=106289 DEFAULT CHARSET=utf8 COMMENT='待检测网站信息';

-- ----------------------------
-- Table structure for tb_site_url_bak
-- ----------------------------
DROP TABLE IF EXISTS `tb_site_url_bak`;
CREATE TABLE `tb_site_url_bak` (
  `id` int(11) NOT NULL DEFAULT '0' COMMENT '标识',
  `page` varchar(256) CHARACTER SET utf8 NOT NULL COMMENT '待检测网址/落地页',
  `demin` varchar(128) CHARACTER SET utf8 DEFAULT NULL COMMENT '归属主域名',
  `source` int(1) NOT NULL DEFAULT '0' COMMENT '来源类型 0未分类1平台2推广',
  `need_check` int(1) NOT NULL DEFAULT '0' COMMENT '是否要检测 0检测1不检测',
  `charset` varchar(8) CHARACTER SET utf8 DEFAULT NULL COMMENT '文字编码',
  `ip` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '现IP',
  `old_ip` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '旧IP',
  `ip_checked` int(1) DEFAULT '0' COMMENT 'IP检测状态 0已检1待检',
  `self_site` int(1) DEFAULT '0' COMMENT '是否我司托管 0非1是',
  `dt` datetime DEFAULT NULL COMMENT 'IP变更时间',
  `customer` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '客户',
  `error_time` datetime DEFAULT NULL COMMENT '最近异常时间',
  `gmt_created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modify` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修订时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for tf_child_info
-- ----------------------------
DROP TABLE IF EXISTS `tf_child_info`;
CREATE TABLE `tf_child_info` (
  `id` int(11) NOT NULL COMMENT '归属检测',
  `page` varchar(512) COLLATE utf8_unicode_ci NOT NULL COMMENT '检测页面',
  `check_cycle` int(2) NOT NULL COMMENT '检测深度',
  `check_order` int(11) NOT NULL COMMENT '检测序号',
  `code` int(4) NOT NULL COMMENT '检测代码',
  `content` mediumblob COMMENT '页面内容',
  `inner_link` int(11) DEFAULT '0' COMMENT '内链',
  `out_link` int(11) DEFAULT '0' COMMENT '外链',
  `white_word` int(11) DEFAULT '0' COMMENT '白名单敏感词',
  `illegal_word` int(11) DEFAULT '0' COMMENT '疑似敏感词',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for tf_link_item
-- ----------------------------
DROP TABLE IF EXISTS `tf_link_item`;
CREATE TABLE `tf_link_item` (
  `id` int(11) NOT NULL,
  `page` varchar(512) COLLATE utf8_unicode_ci NOT NULL,
  `status` int(2) NOT NULL DEFAULT '0' COMMENT '类型 0内链 1外链 2非法外链',
  `anchor` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `link` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '链接'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='网页链接信息';

-- ----------------------------
-- Table structure for tf_monitor_outline
-- ----------------------------
DROP TABLE IF EXISTS `tf_monitor_outline`;
CREATE TABLE `tf_monitor_outline` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增标识',
  `site_id` int(11) DEFAULT '0' COMMENT '检测网站标识',
  `ip` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'ＩＰ地址',
  `self_site` int(2) DEFAULT '0' COMMENT '我司托管 1是',
  `page` varchar(256) CHARACTER SET utf8 NOT NULL COMMENT '检测网页',
  `accept_mail` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '收件人，多个收件人之间分号分隔',
  `check_level` int(2) DEFAULT '0' COMMENT '检测深度',
  `error_page` int(11) DEFAULT '0' COMMENT '异常页面',
  `inner_page` int(11) DEFAULT '0' COMMENT '检测子页面',
  `outer_page` int(11) DEFAULT '0' COMMENT '外链数量',
  `key_word` int(11) DEFAULT '0' COMMENT '敏感词总量',
  `illegal_word` int(11) DEFAULT '0' COMMENT '疑似非法用词',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modify` datetime DEFAULT NULL COMMENT '修订时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='全站检测概要';

-- ----------------------------
-- Table structure for tf_sensitive_item
-- ----------------------------
DROP TABLE IF EXISTS `tf_sensitive_item`;
CREATE TABLE `tf_sensitive_item` (
  `id` int(11) NOT NULL COMMENT '检测源标识',
  `page` varchar(512) COLLATE utf8_unicode_ci NOT NULL COMMENT '页面',
  `status` int(2) NOT NULL DEFAULT '0' COMMENT '状态 0疑似 1白名单',
  `key_word` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '敏感词',
  `context` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '上下文'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='网页敏感词';
