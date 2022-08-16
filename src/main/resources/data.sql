
INSERT INTO Publisher (name) VALUES ( 'Almaty Kitap');

INSERT INTO Book (name, cost, number_of_pages, release_date, publisher_id) VALUES ('Dom', 4, 234,  '2015-06-01', 1);
INSERT INTO Book (name, cost, number_of_pages, release_date, publisher_id) VALUES ('Zabor', 4, 234,  '2015-06-01', 1);
INSERT INTO Book (name, cost, number_of_pages, release_date, publisher_id) VALUES ('Developer', 10001, 234,  '2015-06-01', 1);
INSERT INTO Book (name, cost, number_of_pages, release_date, publisher_id) VALUES ('Sword', 8, 234,  '2015-06-01', 1);
INSERT INTO Book (name, cost, number_of_pages, release_date, publisher_id) VALUES ('Suh', 2, 234,  '2015-06-01', 1);


INSERT INTO Author (last_name, first_name, patronymic_name, date_of_birth) VALUES ( 'Auezov', 'Mukhtar', 'Kairatovich', '2012-02-01');
INSERT INTO Author (last_name, first_name, patronymic_name, date_of_birth) VALUES ( 'Repin', 'Igor', 'Olegovich', '2012-02-01');
INSERT INTO Genre (genre_name) VALUES ('Classic');
INSERT INTO Genre (genre_name) VALUES ('Horror');



INSERT INTO Author_Genre VALUES (2,1);
INSERT INTO Author_Genre VALUES (1,2);

INSERT INTO Book_Author VALUES (1, 2);
INSERT INTO Book_Author VALUES (2, 1);

INSERT INTO Book_Genre VALUES (1, 1);
INSERT INTO Book_Genre VALUES (2, 2);

