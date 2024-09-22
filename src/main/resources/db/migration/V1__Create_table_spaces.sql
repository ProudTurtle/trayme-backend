-- V1__Create_space_table.sql
CREATE TABLE space
(
    id     INT          NOT NULL AUTO_INCREMENT,
    module VARCHAR(255) NOT NULL,
    title  VARCHAR(255) NOT NULL,
    icon   VARCHAR(255) NOT NULL,

    CONSTRAINT pk_space PRIMARY KEY (id)
);

INSERT INTO space (id, module, title, icon)
VALUES (1, 'notes', 'Notatki', 'mdi-note-outline'),
       (2, 'shopping-list', 'Do kupienia', 'mdi-cart-outline'),
       (3, 'fees', 'Opłaty', 'mdi-cash-multiple'),
       (5, 'birthdays', 'Urodziny', 'mdi-calendar-heart'),
       (6, 'recommendations', 'Polecajki', 'mdi-thumb-up-outline'),
       (7, 'class-schedule', 'Plan zajęć', 'mdi-school-outline');
