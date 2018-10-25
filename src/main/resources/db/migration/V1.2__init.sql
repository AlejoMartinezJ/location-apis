CREATE DATABASE TESIS
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
INSERT INTO location (address, place_name, lat, lng) VALUES ('Las Granadas','Las Granadas','-11.98589','-77.06732');
INSERT INTO location (address, place_name, lat, lng) VALUES ('Salaverry','Salaverry','-11.98992','-77.0653');
INSERT INTO location (address, place_name, lat, lng) VALUES ('Pariahuanca c/ Las Palmeras','Pariahuanca-Las Palmeras','-11.98009','-77.07212');
INSERT INTO location (address, place_name, lat, lng) VALUES ('Hierro','Hierro','-11.96579','-77.06269');
INSERT INTO location (address, place_name, lat, lng) VALUES ('Puente Tres postes','Puente Tres postes','-11.96641','-77.06801');
INSERT INTO location (address, place_name, lat, lng) VALUES ('Naranjal c/ Canta callao','Naranjal-Canta callao','-11.97393','-77.08733');
INSERT INTO location (address, place_name, lat, lng) VALUES ('Naranjal c/ Huandoy','Naranjal-Huandoy','-11.97661','-77.08317');
INSERT INTO location (address, place_name, lat, lng) VALUES ('Alfa c/ Santos Atahualpa','Alfa-Santos Atahualpa','-12.00787','-77.06911');
INSERT INTO location (address, place_name, lat, lng) VALUES ('El Amargon','El Amargon','-11.99143','-77.07459');
INSERT INTO location (address, place_name, lat, lng) VALUES ('Pariahuanca','Pariahuanca','-11.98101','-77.066');
INSERT INTO location (address, place_name, lat, lng) VALUES ('Serpost','Serpost','-12.01004','-77.06873');
INSERT INTO location (address, place_name, lat, lng) VALUES ('Los Chasquis','Los Chasquis','-12.00553','-77.06802');
INSERT INTO location (address, place_name, lat, lng) VALUES ('Antonio Cabo','Antonio Cabo','-12.00865','-77.07102');
INSERT INTO location (address, place_name, lat, lng) VALUES ('Pariahuanca c/ Universitaria','Pariahuanca-Universitaria','-11.97949','-77.07707');
INSERT INTO location (address, place_name, lat, lng) VALUES ('Hugo Espinoza','Hugo Espinoza','-11.98223','-77.06967');
INSERT INTO location (address, place_name, lat, lng) VALUES ('Los Limoncillos','Los Limoncillos','-11.99143','-77.07627');
INSERT INTO location (address, place_name, lat, lng) VALUES ('Campanillas','Campanillas','-11.98811','-77.07068');
INSERT INTO location (address, place_name, lat, lng) VALUES ('Central c/ Canta callao','Central-Canta callao','-11.95507','-77.08507');
INSERT INTO location (address, place_name, lat, lng) VALUES ('Sagrado Corazon c/ Universitaria','Sagrado Corazon-Universitaria','-12.0083','-77.08173');
INSERT INTO location (address, place_name, lat, lng) VALUES ('Hualcan','Hualcan','-11.99432','-77.07993');
INSERT INTO location (address, place_name, lat, lng) VALUES ('Sagrado Corazon de Jesus','Sagrado Corazon de Jesus','-11.97321','-77.07636');
INSERT INTO location (address, place_name, lat, lng) VALUES ('Dalias','Dalias','-11.94731','-77.08561');
INSERT INTO location (address, place_name, lat, lng) VALUES ('Plaza Civica de Pro','Plaza Civica de Pro','-11.93511','-77.07662');
INSERT INTO location (address, place_name, lat, lng) VALUES ('Cordialidad c/ Proceres','Cordialidad-Proceres','-11.93914','-77.07731');
INSERT INTO location (address, place_name, lat, lng) VALUES ('Parque La Huaca','Parque La Huaca','-11.94047','-77.07623');
INSERT INTO location (address, place_name, lat, lng) VALUES ('Pro','Pro','-11.93447','-77.07746');
INSERT INTO location (address, place_name, lat, lng) VALUES ('Laura Caller','Laura Caller','-11.97077','-77.07822');
INSERT INTO location (address, place_name, lat, lng) VALUES ('Sol de Oro','Sol de Oro','-11.99749','-77.06408');


INSERT INTO umoviles (locationid, name_unit, status) VALUES (32, 'UI1002', 'HOLD');
INSERT INTO umoviles (locationid, name_unit, status) VALUES (33, 'UI1003', 'HOLD');
INSERT INTO umoviles (locationid, name_unit, status) VALUES (34, 'UI2001', 'HOLD');

