set foreign_key_checks = false;

drop table if exists `user`;
create table `user` (
	id bigint(20) not null primary key auto_increment,
	name varchar(255) not null,
	email varchar(255) not null,
	password varchar(255) not null,
	created_at datetime not null
);

drop table if exists `purchase`;
create table `purchase` (
	id bigint(20) not null primary key auto_increment,
	owner_id bigint(20) not null,
	created_at datetime not null,
	reference_id varchar(255),
	CONSTRAINT owner_id_fk FOREIGN KEY (owner_id) REFERENCES `user`(id)
);

drop table if exists `purchase_item`;
create table `purchase_item` (
	id bigint(20) not null primary key auto_increment,
	purchase_id bigint(20) not null,
	name varchar(255) not null,
	description varchar(255) not null,
	unit_price varchar(30) not null,
	quantity int(11) not null,
	created_at datetime not null,
	CONSTRAINT purchase_id_fk FOREIGN KEY (purchase_id) REFERENCES `purchase`(id)
);

set foreign_key_checks = true;