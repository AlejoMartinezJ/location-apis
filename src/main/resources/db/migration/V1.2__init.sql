
DROP TABLE IF EXISTS `address`; 
CREATE TABLE IF NOT EXISTS `address` (

`addressid` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
`address` varchar(255),
`placeName` varchar(50)

)ENGINE=InnoDB DEFAULT CHARSET=UTF8; 

DROP TABLE IF EXISTS `location`;
CREATE TABLE IF NOT EXISTS `location` (
`locationid` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
`addressid` int,
`placeName` varchar(50),
`lat` varchar(20),
`lng` varchar(20)

)ENGINE=InnoDB DEFAULT CHARSET=UTF8; 

DROP TABLE IF EXISTS `umoviles`;
CREATE TABLE IF NOT EXISTS `umoviles` (

`unitid` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
`locationid` int,
`nameUnit` varchar(50),
`status` varchar(10)

)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

DROP TABLE IF EXISTS `uroute`;
CREATE TABLE IF NOT EXISTS `uroute` (

`routeid` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
`unitid` int,
`locationid` int,
`secuence` int

)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

DROP TABLE IF EXISTS `udistance`;
CREATE TABLE IF NOT EXISTS `udistance` (

`distanceid` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
`init` int,
`target` int,
`dtime`  int,
`distance` int

)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

INSERT INTO ADDRESS VALUES(1,'Centro Control', 'Centro Control');
INSERT INTO ADDRESS VALUES(2,'lugar1', 'lugar1');
INSERT INTO ADDRESS VALUES(3,'lugar2', 'lugar2');
INSERT INTO ADDRESS VALUES(4,'lugar3', 'lugar3');
INSERT INTO ADDRESS VALUES(5,'lugar4', 'lugar4');

INSERT INTO LOCATION VALUES (1,1,'Centro Control', '-11.9622', '-77.08373');
INSERT INTO LOCATION VALUES (2,5,'lugar4', '-11.95859', '-77.05789');
INSERT INTO LOCATION VALUES (3,2,'lugar1', '-11.96703', '-77.06986');
INSERT INTO LOCATION VALUES (4,3,'lugar2', '-11.95116', '-77.0775');
INSERT INTO LOCATION VALUES (5,4,'lugar3', '-11.9481', '-77.06248');

INSERT INTO UMOVILES VALUES (1,1,'Centro Control', 'ACTIVE');
