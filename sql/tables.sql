/*create database port;*/
use port;

/*
drop table ships_history;
drop table priorities;
drop table supplies;
drop table ships_queue;
drop table supplies_history;
*/

create table ships_history(
	ship_id int not null auto_increment,
	all_access_time int not null,
  violations_count int not null,
  primary key(ship_id)
);

create table priorities(
	priority_id int not null auto_increment,
  priority varchar(20) not null,
  primary key(priority_id)
);

create table supplies(
	supply_id int not null auto_increment,
	ship_id int not null,
  download_count int not null,
	unload_count int not null,
  primary key(supply_id),
  foreign key(ship_id) references ships_history(ship_id)
);

create table ships_queue(
	ship_id int not null,
  priority_id int not null,
  supply_id int not null,
  access_time int not null,
  foreign key(ship_id) references ships_history(ship_id),
  foreign key(priority_id) references priorities(priority_id),
  foreign key(supply_id) references supplies(supply_id)
);

create table supplies_history(
	ship_id int not null,
  download_count int not null,
	unload_count int not null,
  foreign key(ship_id) references ships_history(ship_id)
);