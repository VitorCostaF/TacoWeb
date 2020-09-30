create table if not exists TacoOrder (
	id integer,
	name varchar(255) not null,
	street varchar(255) not null,
	city varchar(255) not null,
	state varchar(255) not null,
	zip varchar(255) not null,
	ccNumber varchar(255) not null,
	ccExpiration varchar(255) not null,
	ccCVV varchar(255) not null,
	placedAt timestamp not null, 
	
	Primary key (id)
);

create table if not exists Taco (
	id integer,
	name varchar(255) not null,
	createdAt timestamp not null,
	order_id integer,
	
	Primary key (id),
);

alter table Taco 
	add constraint taco_fk_tacoorder
	foreign key (order_id)
	references tacoorder

create table if not exists Ingredient (
	id varchar(255),
	name varchar(255) not null,
	type integer not null,
	 
	Primary key (id)
);

create table if not exists Taco_Ingredient (
	ingredients_id varchar(255),
	tacos_id integer, 
	
	Primary key (ingredients_id, tacos_id),
	Foreign key (ingredients_id) references Ingredient(id),
	Foreign key (tacos_id) references Taco(id)
);

alter table Taco_Ingredient 
	add constraint taco_ingredient_fk_ingredient
	foreign key (ingredients_id)
	references Ingredient
	
alter table Taco_Ingredient 
	add constraint taco_ingredient_fk_taco
	foreign key (tacos_id)
	references Taco

--drop table Taco_Ingredient;
--drop table TacoOrder;
--drop table Taco;
--drop table Ingredient;




--hibernate commands

--the hibernate created the type column with int4 type.
--alter table ingredient alter column type type varchar(255);

--Hibernate: 
--    
--    create table Ingredient (
--       id varchar(255) not null,
--        name varchar(255),
--        type int4,
--        primary key (id)
--    )
--Hibernate: 
--    
--    create table Taco (
--       id int8 not null,
--        createdAt timestamp,
--        name varchar(255) not null,
--        order_id int8,
--        primary key (id)
--    )
--Hibernate: 
--    
--    create table Taco_Ingredient (
--       tacos_id int8 not null,
--        ingredients_id varchar(255) not null
--    )
--Hibernate: 
--    
--    create table TacoOrder (
--       id int8 not null,
--        ccCVV varchar(255),
--        ccExpiration varchar(255),
--        ccNumber varchar(255),
--        city varchar(255),
--        name varchar(255),
--        placedAt timestamp,
--        state varchar(255),
--        street varchar(255),
--        zip varchar(255),
--        primary key (id)
--    )
--Hibernate: create sequence hibernate_sequence start 1 increment 1
--Hibernate: 
--    
--    alter table Taco 
--       add constraint FK5m2or3hufhc1cbr8fdniege2c 
--       foreign key (order_id) 
--       references TacoOrder
--Hibernate: 
--    
--    alter table Taco_Ingredient 
--       add constraint FKihv2m5vvrqgj6macyc2do2ie0 
--       foreign key (ingredients_id) 
--       references Ingredient
--Hibernate: 
--    
--    alter table Taco_Ingredient 
--       add constraint FK1p4uqiuacu5dla8osjmj7qmxj 
--       foreign key (tacos_id) 
--       references Taco