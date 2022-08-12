CREATE TABLE IF NOT EXISTS Book
(
    id INTEGER COMMENT 'Уникальный идентификатор книги' PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL COMMENT 'Имя',
    cost DOUBLE NOT NULL COMMENT 'Идентификатор офиса',
    number_of_pages INTEGER NOT NULL COMMENT 'Колличество страниц',
    release_date DATE NOT NULL COMMENT 'Дата релиза',
    publisher_id INTEGER NOT NULL COMMENT 'Внешний ключ',
    order_id INTEGER COMMENT 'Внешний ключ'
);

CREATE TABLE IF NOT EXISTS Author
(
    id INTEGER COMMENT 'Уникальный идентификатор автора' PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL COMMENT 'Имя' ,
    last_name VARCHAR(50) NOT NULL COMMENT 'Фамилия' ,
    patronymic_name VARCHAR(50) NOT NULL COMMENT 'Отчество',
    date_of_birth DATE NOT NULL COMMENT 'Дата рождения'
);

CREATE TABLE IF NOT EXISTS Publisher
(
    id INTEGER COMMENT 'Уникальный идентификатор издателя' PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL COMMENT 'Имя'
);

CREATE TABLE IF NOT EXISTS Users
(
    id INTEGER COMMENT 'Уникальный идентификатор пользователя' PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL COMMENT 'Логин' ,
    password VARCHAR(250) NOT NULL COMMENT 'Пароль' ,
    role VARCHAR(50) NOT NULL COMMENT 'Роль',
    is_blocked BOOLEAN
);

CREATE TABLE IF NOT EXISTS Genre
(
    id INTEGER COMMENT 'Уникальный идентификатор жанра' PRIMARY KEY AUTO_INCREMENT,
    genre_name VARCHAR(50) NOT NULL COMMENT 'Название жанра'
);

CREATE TABLE IF NOT EXISTS Orders
(
    id INTEGER COMMENT 'Уникальный идентификатор заказа' PRIMARY KEY AUTO_INCREMENT,
    status VARCHAR(50) NOT NULL COMMENT 'Статус заказа',
    create_dt TIMESTAMP NOT NULL COMMENT 'Время заказа'
);

CREATE TABLE IF NOT EXISTS Book_Author (
    book_id   INTEGER  NOT NULL COMMENT 'Уникальный идентификатор книги',
    author_id    INTEGER  NOT NULL COMMENT 'Уникальный идентификатор автора',
    PRIMARY KEY (book_id, author_id)
);

CREATE TABLE IF NOT EXISTS Book_Genre (
    book_id INTEGER NOT NULL COMMENT 'Уникальный идентификатор книги',
    genre_id INTEGER NOT NULL COMMENT 'Уникальный идентификатор заказа',
    PRIMARY KEY (book_id, genre_id)
);

ALTER TABLE Book
    ADD FOREIGN KEY (publisher_id) REFERENCES Publisher (id);
ALTER TABLE Book
    ADD FOREIGN KEY (order_id) REFERENCES Orders (id);

ALTER TABLE Book_Author
    ADD FOREIGN KEY (book_id) REFERENCES Book (id);
ALTER TABLE Book_Author
    ADD FOREIGN KEY (author_id) REFERENCES Author (id);

ALTER TABLE Book_Genre
    ADD FOREIGN KEY (book_id) REFERENCES Book (id);
ALTER TABLE Book_Genre
    ADD FOREIGN KEY (genre_id) REFERENCES Genre (id);


