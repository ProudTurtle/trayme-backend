CREATE TABLE recommendation
(
    id       INT          NOT NULL AUTO_INCREMENT,
    name     VARCHAR(255) NULL,
    type     VARCHAR(255) NULL,
    who      VARCHAR(255) NULL,
    space_id INT          NOT NULL,
    CONSTRAINT pk_recommendation PRIMARY KEY (id),
    CONSTRAINT fk_recommendation_space
        FOREIGN KEY (space_id)
            REFERENCES space (id)
);