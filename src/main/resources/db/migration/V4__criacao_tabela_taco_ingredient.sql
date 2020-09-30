create table if not exists taco_ingredient (
	taco_id integer,
	ingredient_id varchar(255),
	
	primary key (taco_id, ingredient_id) 
);

alter table taco_ingredient 
	add constraint taco_ingredient_fk_ingredient
	foreign key (ingredient_id)
	references Ingredient;
	
alter table taco_ingredient 
	add constraint taco_ingredient_fk_taco
	foreign key (taco_id)
	references Taco;