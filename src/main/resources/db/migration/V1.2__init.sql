DROP TABLE IF EXISTS `umoviles_spots`;
DROP TABLE IF EXISTS `umoviles`;
DROP TABLE IF EXISTS `location`;
DROP TABLE IF EXISTS `spots`;
DROP TABLE IF EXISTS `factor`;
DROP TABLE IF EXISTS `distance`;

CREATE TABLE umoviles (
unitid int NOT NULL AUTO_INCREMENT, 
locationid int, 
name_unit varchar(255), 
status varchar(255), 
PRIMARY KEY (unitid)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE umoviles_spots (
umoviles_unitid int NOT NULL, 
spots_spotid int NOT NULL, 
CONSTRAINT UK_spots
UNIQUE (spots_spotid), 
INDEX FKspots (umoviles_unitid)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE location (
locationid int NOT NULL AUTO_INCREMENT, 
address varchar(255),
place_name varchar(255),
lat varchar(255), 
lng varchar(255), 
PRIMARY KEY (locationid)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE factor (
factorid int NOT NULL AUTO_INCREMENT, 
destination int, distance int, 
origen int, time int, 
PRIMARY KEY (factorid)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE distance (
distanceid int NOT NULL AUTO_INCREMENT,
origenid int,
destinationid int,
distance int, 
time_driving int,
PRIMARY KEY (distanceid)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE spots (
spotid int NOT NULL AUTO_INCREMENT, 
locationid int, 
secuence int, 
PRIMARY KEY (spotid)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;
select * from location

INSERT INTO location (address, place_name, lat, lng) VALUES ('Centro Control','Control', '-11.9622', '-77.08373');
INSERT INTO location (address, place_name, lat, lng) VALUES ('lugar1','Market', '-11.9622', '-77.06986');
INSERT INTO location (address, place_name, lat, lng) VALUES ('lugar4','Train Station', '-11.95859', '-77.05789');
INSERT INTO location (address, place_name, lat, lng) VALUES ('lugar5','Uptown', '-11.96703', '-77.06986');
INSERT INTO location (address, place_name, lat, lng) VALUES ('lugar2', 'School','-11.95116', '-77.0775');
INSERT INTO location (address, place_name, lat, lng) VALUES ('lugar3','Store', '-11.9481', '-77.06248');

INSERT INTO umoviles (locationid, name_unit, status) VALUES (1, 'UI1002', 'HOLD');
INSERT INTO umoviles (locationid, name_unit, status) VALUES (1, 'UI1003', 'HOLD');
INSERT INTO umoviles (locationid, name_unit, status) VALUES (1, 'UI2001', 'HOLD');

