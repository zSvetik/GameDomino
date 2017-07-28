CREATE DATABASE IF NOT EXISTS `domino`
    CHARACTER SET utf8
    COLLATE utf8_general_ci;
USE `domino`;

CREATE TABLE IF NOT EXISTS `dominochain` (
	`id_chain` varchar(50) NOT NULL,
	`chain` varchar(255) NOT NULL,
	PRIMARY KEY (`id_chain`)
);

CREATE TABLE IF NOT EXISTS `dominochainhistory` (
	`id_chainhistory` varchar(50) NOT NULL,
	`id_chain` varchar(50) NOT NULL,
	`chainhistory` varchar(255) NOT NULL,
	PRIMARY KEY (`id_chainhistory`, `id_chain`)
);

INSERT INTO DominoChain (id_chain, chain) values ('-6040488473586905010', '{[0:0], [0:1], [2:4], [3:3], [3:4], [5:6]}'); 
INSERT INTO DominoChainHistory (id_chain, id_chainhistory, chainhistory) values ('-6040488473586905010', '0', '{[0:0], [0:1]}'), 
('-6040488473586905010', '1', '{[0:1], [0:0]}'), 
('-6040488473586905010', '2', '{[2:4], [3:4], [3:3]}'), 
('-6040488473586905010', '3', '{[3:3], [3:4], [2:4]}'), 
('-6040488473586905010', '4', '{[2:4]}'), 
('-6040488473586905010', '5', '{[3:4], [3:3]}'), 
('-6040488473586905010', '6', '{[5:6]}');
