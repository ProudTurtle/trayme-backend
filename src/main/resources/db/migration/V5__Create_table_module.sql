DROP TABLE IF EXISTS recommendation;
DROP TABLE IF EXISTS note;
DROP TABLE IF EXISTS space;
DROP TABLE IF EXISTS content;
DROP TABLE IF EXISTS module;
-- V1__Create_space_table.sql
CREATE TABLE module
(
    id     INT          NOT NULL AUTO_INCREMENT,
    module VARCHAR(255) NOT NULL,
    title  VARCHAR(255) NOT NULL,
    icon   VARCHAR(255) NOT NULL,

    CONSTRAINT pk_space PRIMARY KEY (id)
);

INSERT INTO module (id, module, title, icon)
VALUES (1, 'notes', 'Notatki', 'mdi-note-outline'),
       (2, 'shopping-list', 'Do kupienia', 'mdi-cart-outline'),
       (3, 'fees', 'Opłaty', 'mdi-cash-multiple'),
       (5, 'birthdays', 'Urodziny', 'mdi-calendar-heart'),
       (6, 'recommendations', 'Polecajki', 'mdi-thumb-up-outline'),
       (7, 'class-schedule', 'Plan zajęć', 'mdi-school-outline');


CREATE TABLE content (
    id INT NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (id)
);

CREATE TABLE space (
    id INT NOT NULL AUTO_INCREMENT,
    module_id INT NOT NULL,
    user_id INT NOT NULL,
    content_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY (module_id) REFERENCES module(id),
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (content_id) REFERENCES content(id) ON DELETE SET NULL
);


