drop table booktable;

create table booktable
	(bookname		varchar(30),
	 price numeric (5,2),
	 author		varchar(30),
	 year		int,
	 bookid int,
	 primary key (bookid)
	);

delete from booktable;

insert into booktable values ('Harry Potter and the Goblet of Fire', 55.00, ''J.K. Rowling'', 2000, 1);
insert into booktable values ('Le Petit Prince', 22.80, 'Antoine de Saint-Exupéry', 1942, 2);
insert into booktable values ('Les Misérables', 32.50, 'Victor Hugo', 1862, 3);
insert into booktable values ('Sophies World', 25.60, 'Jostein Gaarder', 1991, 4);