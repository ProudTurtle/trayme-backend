CREATE TABLE user_space (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    space_id INT NOT NULL,
    role VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (space_id) REFERENCES space(id)
);

ALTER TABLE space ADD COLUMN share_key VARCHAR(255) NOT NULL;