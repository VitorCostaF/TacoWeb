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
	
	Primary key (id)
);

alter table Taco 
	add constraint taco_fk_tacoorder
	foreign key (order_id)
	references tacoorder;

create table if not exists Ingredient (
	id varchar(255),
	name varchar(255) not null,
	type integer not null,
	 
	Primary key (id)
);

create table if not exists Taco_Ingredient (
	ingredients_id varchar(255),
	tacos_id integer, 
	
	Primary key (ingredients_id, tacos_id)
);

alter table Taco_Ingredient 
	add constraint taco_ingredient_fk_ingredient
	foreign key (ingredients_id)
	references Ingredient;
	
alter table Taco_Ingredient 
	add constraint taco_ingredient_fk_taco
	foreign key (tacos_id)
	references Taco;