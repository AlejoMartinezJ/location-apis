DROP TABLE IF EXISTS `address`; 
CREATE TABLE IF NOT EXISTS `address` (

`addressID` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
`address` varchar(255),
`placeName` varchar(50)

)ENGINE=InnoDB DEFAULT CHARSET=UTF8; 

DROP TABLE IF EXISTS `location`;
CREATE TABLE IF NOT EXISTS `location` (
`locationID` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
`addressID` int,
`placeName` varchar(50),
`lat` varchar(20),
`lng` varchar(20)

)ENGINE=InnoDB DEFAULT CHARSET=UTF8; 

DROP TABLE IF EXISTS `umoviles`;
CREATE TABLE IF NOT EXISTS `umoviles` (

`unitID` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
`locationID` int,
`nameUnit` varchar(50),
`status` varchar(10)

)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

DROP TABLE IF EXISTS `uroute`;
CREATE TABLE IF NOT EXISTS `uroute` (

`routeID` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
`unitID` int,
`locationID` int,
`secuence` int

)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

DROP TABLE IF EXISTS `udistance`;
CREATE TABLE IF NOT EXISTS `udistance` (

`distanceID` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
`initID` int,
`targetID` int,
`dtime`  int,
`distance` int

)ENGINE=InnoDB DEFAULT CHARSET=UTF8;