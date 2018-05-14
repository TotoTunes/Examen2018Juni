-- CREATE DATABASE  IF NOT EXISTS `afstandbediening` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `afstandsbediening`;
drop table Bewoners;
create table Bewoners(
ID int Not null auto_increment Primary key,
voornaam varchar(20) not null,
achternaam varchar(30) not null,
frequency float,
acces bool
);

insert into bewoners(voornaam, achternaam, frequency, acces)  values ('Jan','Vanden Bossche', 1, TRUE); 
insert into bewoners(voornaam, achternaam, frequency, acces)  values ('Thomas','Vanden Bossche', 2, false); 
Select * from bewoners;

create user 'idmodule' identified by 'Password0'

grant all privileges on afstandbediening.* to 'idmodule'@'%' with grant option


