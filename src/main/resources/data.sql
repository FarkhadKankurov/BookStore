INSERT INTO Orders (status) VALUES ('CREATED');
INSERT INTO Publisher (name) VALUES ( 'Almaty Kitap');
INSERT INTO Book (name, cost, number_of_pages, release_date, publisher_id, count_books, order_id) VALUES ('Свидетельство', 4, 234,  '2015-06-01', 1, 1, 1);
INSERT INTO Genre (genre_name) VALUES ('Классика');
INSERT INTO Author (last_name, first_name, patronymic_name, date_of_birth) VALUES ( 'Auezov', 'Mukhtar', 'Kairatovich', '2012-02-01');


INSERT INTO Book_Author VALUES (1, 1);
INSERT INTO Book_Genre VALUES (1, 1);