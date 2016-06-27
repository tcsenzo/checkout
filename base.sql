set foreign_key_checks = false;

drop table if exists `user`;
create table `user` (
	id bigint(20) not null primary key auto_increment,
	name varchar(255) not null,
	email varchar(255) not null,
	password varchar(255) not null,
	created_at datetime not null
);

set foreign_key_checks = true;