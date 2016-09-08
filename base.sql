set foreign_key_checks = false;

drop table if exists `user`;
create table `user` (
	id bigint(20) not null primary key auto_increment,
	name varchar(255) not null,
	email varchar(255) not null UNIQUE,
	auth_id varchar(255) not null UNIQUE,
	created_at datetime not null
);

drop table if exists `purchase`;
create table `purchase` (
	id bigint(20) not null primary key auto_increment,
	owner_id bigint(20) not null,
	event_name varchar(255) not null,
	event_description varchar(255) not null,
	scheduled_date datetime not null,
	theater_id bigint(20) not null,
	theater_name varchar(255) not null,
	street varchar(255) not null,
	number varchar(255) not null,
	complement varchar(255),
	district varchar(255) not null,
	city varchar(255) not null,
	state varchar(255) not null,
	country varchar(255) not null,
	zip_code varchar(255) not null,
	created_at datetime not null,
	reference_id varchar(255),
	unique_id varchar(255),
	CONSTRAINT owner_id_fk FOREIGN KEY (owner_id) REFERENCES `user`(id)
);

drop table if exists `purchase_item`;
create table `purchase_item` (
	id bigint(20) not null primary key auto_increment,
	purchase_id bigint(20) not null,
	price varchar(30) not null,
	ticket_type varchar(255) not null,
	created_at datetime not null,
	CONSTRAINT item_purchase_id_fk FOREIGN KEY (purchase_id) REFERENCES `purchase`(id)
);

drop table if exists `payment`;
create table `payment` (
	id bigint(20) not null primary key auto_increment,
	last_status varchar(255) not null,
	purchase_id bigint(20) not null,
	created_at datetime not null,
	CONSTRAINT payment_purchase_id_fk FOREIGN KEY (purchase_id) REFERENCES `purchase`(id)
);

drop table if exists `payment_status_transition`;
create table `payment_status_transition` (
	id bigint(20) not null primary key auto_increment,
	payment_id bigint(20) not null,
	previous_status varchar(255),
	next_status varchar(255) not null,
	created_at datetime not null,
	CONSTRAINT payment_id_fk FOREIGN KEY (payment_id) REFERENCES `payment`(id)
);

drop table if exists `ticket`;
create table `ticket` (
	id bigint(20) not null primary key auto_increment,
	purchase_item_id bigint(20) not null,
	hash varchar(255) not null,
	created_at datetime not null,
	CONSTRAINT purchase_item_fk FOREIGN KEY (purchase_item_id) REFERENCES `purchase_item`(id)
);

set foreign_key_checks = true;
