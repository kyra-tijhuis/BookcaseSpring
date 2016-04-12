drop table if exists book_on_plank;
drop table if exists plank_in_bookcase;
drop table if exists bookcase;
drop table if exists book;
drop table if exists user;


create table user
(
	  user_id			int auto_increment,
    user_name		varchar(20) not null,
    password_hash	varchar(40) not null,
    salt        varchar(36) not null,
    primary key (user_id),
    unique (user_name)
);

create table book
(
	  isbn			varchar(13) unique not null,
    book_title		varchar(80) not null,
    author			varchar(60) not null,
    height			int not null,
    width			int not null,
    thickness		int not null,
    cover			blob,
    primary key (isbn)
);    

create table bookcase
(
	bookcase_name	varchar(60) not null,
    bookcase_id		int auto_increment,
    user_id			int not null,
    width			int not null,
    primary key (bookcase_id),
    foreign key (user_id) references user(user_id) on delete cascade
);
    
create table plank_in_bookcase
(
	  bookcase_id		int not null,
    plank_id		int auto_increment,
    height			int not null,
    primary key (plank_id),
    foreign key	(bookcase_id) references bookcase(bookcase_id) on delete cascade
);    
    
create table book_on_plank
(
	  isbn			varchar(13) not null,
    plank_id		int not null,
    book_index		int not null,
    primary key (isbn, plank_id, book_index),
    foreign key (isbn) references book(isbn) on delete cascade,
    foreign key (plank_id) references plank_in_bookcase(plank_id) on delete cascade
);

insert into user (password_hash, user_name) values ('password', 'user');
insert into book values ('9789045704241', 'Over het water', 'H.M. van den Brink',210,139,15, null);
insert into bookcase (bookcase_name, user_id,width) values ('kast',1,800);
insert into plank_in_bookcase(bookcase_id,height) values (1,275);
insert into book_on_plank values ('9789045704241',1,1);