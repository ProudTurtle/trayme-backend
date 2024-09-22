CREATE TABLE note
(
    id        INT          NOT NULL AUTO_INCREMENT,
    title     VARCHAR(255) NULL,
    update_at datetime     NULL,
    content   TEXT         NULL,
    space_id  INT          NOT NULL,
    CONSTRAINT pk_note PRIMARY KEY (id),
    CONSTRAINT fk_note_space
        FOREIGN KEY (space_id)
            REFERENCES space (id)
);