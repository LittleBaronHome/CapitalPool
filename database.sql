CREATE SCHEMA user_auth;
USE user_auth;
CREATE TABLE IF NOT EXISTS user_auth.user_temp (
	`id` INT NOT NULL AUTO_INCREMENT,
	`username` VARCHAR(200) COMMENT '用户名',
	`name` VARCHAR(200) COMMENT '昵称',
	`email` VARCHAR(200) NOT NULL,
	`phone` VARCHAR(11),
	`password` TEXT,
	`active_code` VARCHAR(20) NOT NULL COMMENT '激活码',
	`create_time` DATETIME NOT NULL DEFAULT NOW(),
	PRIMARY KEY (`id`)
) COMMENT='用户表';

CREATE TABLE IF NOT EXISTS user_auth.user (
	`id` INT NOT NULL AUTO_INCREMENT,
	`uuid` VARCHAR(45) NOT NULL DEFAULT UUID(),
	`username` VARCHAR(200) COMMENT '用户名',
	`name` VARCHAR(200) COMMENT '昵称',
	`email` VARCHAR(200) NOT NULL,
	`phone` VARCHAR(11),
	`password` TEXT,
	`status` TINYINT(4) NOT NULL DEFAULT 1 COMMENT '用户状态 0：无效；1：有效',
	`create_time` DATETIME NOT NULL DEFAULT NOW(),
	PRIMARY KEY (`id`),
	UNIQUE KEY(`uuid`),
	UNIQUE KEY(`email`)
) COMMENT='用户表';

CREATE TABLE IF NOT EXISTS user_auth.account (
	`id` INT NOT NULL AUTO_INCREMENT,
	`uuid` VARCHAR(45) NOT NULL DEFAULT UUID(),
	`name` VARCHAR(200) COMMENT '昵称',
	`user_uuid` VARCHAR(45) NOT NULL COMMENT '所属用户',
	`create_time` DATETIME NOT NULL DEFAULT NOW(),
	`status` TINYINT(4) NOT NULL DEFAULT 1 COMMENT '账户状态 0：无效；1：有效',
	`is_del` TINYINT(4) NOT NULL DEFAULT 0 COMMENT '是否删除 0：未删除；1：已删除',
	`del_time` DATETIME,
	PRIMARY KEY (`id`),
	UNIQUE KEY(`uuid`),
	INDEX user_uuid_index(`user_uuid`)
) COMMENT='账户表';

DELIMITER $$

CREATE EVENT `user_auth`.`FlushExpairedUserTemp`
ON SCHEDULE EVERY 1 MINUTE
DO
	BEGIN
	    DELETE FROM user_auth.user_temp WHERE DATE_ADD(create_time, INTERVAL 15 MINUTE) < NOW();
	END$$

DELIMITER ;
