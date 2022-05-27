CREATE TABLE books (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    isbn VARCHAR(13) NOT NULL,
    publisher_name VARCHAR NOT NULL,
    author_name VARCHAR NOT NULL,
    `year` int NOT NULL,
    title VARCHAR NOT NULL,
    price DECIMAL(11, 2) NOT NULL,
    CONSTRAINT books_PK PRIMARY KEY (id)
);