CREATE TABLE IF NOT EXISTS `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长主键',
  `name` varchar(255) NOT NULL COMMENT '图书名称',
  `summary` text COMMENT '图书简介',
  `publisher` varchar(255) DEFAULT NULL COMMENT '出版社',
  `author` varchar(255) DEFAULT NULL COMMENT '作者',
  `supplier_id` int(11) DEFAULT NULL COMMENT '供应商id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_flag` int(11) NOT NULL DEFAULT '0' COMMENT '删除标识 1代表删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `supplier` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长主键',
  `name` varchar(255) NOT NULL COMMENT '供应商名称',
  `summary` text COMMENT '供应商简介',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `phone` varchar(255) DEFAULT NULL COMMENT '电话',
  `contacts` varchar(255) DEFAULT NULL COMMENT '联系人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_flag` int(11) NOT NULL DEFAULT '0' COMMENT '删除标识 0代表删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长主键',
  `name` varchar(255) NOT NULL COMMENT '客户名称',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `sex` varchar(1) DEFAULT NULL COMMENT '性别',
  `phone` varchar(255) DEFAULT NULL COMMENT '联系电话',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_flag` int(11) NOT NULL DEFAULT '0' COMMENT '删除标识 1:代表删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `sales` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长主键',
  `customer_id` int(11) DEFAULT NULL COMMENT '客户id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_flag` int(11) NOT NULL DEFAULT '0' COMMENT '删除标识 1:代表删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `purchase` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长主键',
  `book_id` int(11) NOT NULL COMMENT '图书id',
  `order_quantity` int(11) DEFAULT NULL COMMENT '订购数量',
  `order_time` varchar(255) DEFAULT NULL COMMENT '订购时间',
  `order_price` double DEFAULT NULL COMMENT '订购单价',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `book_sales` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长主键',
  `book_id` int(11) NOT NULL COMMENT '图书id',
  `sales_id` int(11) NOT NULL COMMENT '销售id',
  `quantity` int(11) NOT NULL COMMENT '购买数量',
  `prise` double NOT NULL COMMENT '购买单价',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table if not exists `user`(
  id int(11) not null auto_increment primary key comment '自增长主键',
  username varchar(255) not null comment '用户名称',
  password varchar(255) not null comment '用户密码',
  create_time timestamp not null default current_timestamp
)engine=innodb default charset=utf8