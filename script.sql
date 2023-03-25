create database if not exists dbEmployes;

use dbEmploys;

create table if not exists T_Employe (
	id int auto_increment primary key,
    nom varchar(50),
    departement varchar(50),
    salaire double
);

insert into T_Employe values (null, 'KHALIL Mariam','RH',23000);
insert into T_Employe values (null, 'ALAG Aziz','Finance',13000);
insert into T_Employe values (null, 'ZHIRI ABDO','Production',3000);
insert into T_Employe values (null, 'APPIE Abdsselam','Production',5000);
insert into T_Employe values (null, 'BEQQAL Amine','Finance',13000);
insert into T_Employe values (null, 'BEKKALI Siham','RH',25000);


