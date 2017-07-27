CREATE DATABASE IF NOT EXISTS `domino`
    CHARACTER SET utf8
    COLLATE utf8_general_ci;
USE `domino`;

CREATE TABLE `domino`.`dominochain` (
	`id_chain` varchar(50) NOT NULL,
	`chain` varchar(255) NOT NULL,
	PRIMARY KEY (`id_chain`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `domino`.`dominochainhistory` (
	`id_chainhistory` varchar(50) NOT NULL,
	`id_chain` varchar(50) NOT NULL,
	`chainhistory` varchar(255) NOT NULL,
	PRIMARY KEY (`id_chainhistory`, `id_chain`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
