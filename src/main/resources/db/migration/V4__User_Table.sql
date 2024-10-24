CREATE TABLE user
(
    id     INT          NOT NULL AUTO_INCREMENT,
    password    VARCHAR(255) NOT NULL,

    CONSTRAINT pk_user PRIMARY KEY (id)
);
