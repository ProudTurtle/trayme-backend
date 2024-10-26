-- Tabela dla User
CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    password VARCHAR(255) NOT NULL
);

-- Tabela dla Module
CREATE TABLE module (
    id INT AUTO_INCREMENT PRIMARY KEY,
    module VARCHAR(255) NOT NULL,
    title VARCHAR(255) NOT NULL,
    icon VARCHAR(255) NOT NULL
);

-- Tabela dla Space
CREATE TABLE space (
    id INT AUTO_INCREMENT PRIMARY KEY,
    module_id INT NOT NULL,
    user_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    FOREIGN KEY (module_id) REFERENCES module(id),
    FOREIGN KEY (user_id) REFERENCES user(id)
);

-- Tabela bazowa dla Content
CREATE TABLE content (
    id INT AUTO_INCREMENT PRIMARY KEY,
    space_id INT NOT NULL,
    FOREIGN KEY (space_id) REFERENCES space(id)
);

-- Tabela dla Note dziedzicząca po Content
CREATE TABLE note (
    id INT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    update_at TIMESTAMP NOT NULL,
    content TEXT NOT NULL,
    FOREIGN KEY (id) REFERENCES content(id) ON DELETE CASCADE
);

-- Tabela dla Recommendation dziedzicząca po Content
CREATE TABLE recommendation (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    who VARCHAR(255) NOT NULL,
    type VARCHAR(255) NOT NULL,
    FOREIGN KEY (id) REFERENCES content(id) ON DELETE CASCADE
);

INSERT INTO module (id, module, title, icon)
VALUES (1, 'notes', 'Notatki', 'mdi-note-outline'),
       (2, 'shopping-list', 'Do kupienia', 'mdi-cart-outline'),
       (3, 'fees', 'Opłaty', 'mdi-cash-multiple'),
       (5, 'birthdays', 'Urodziny', 'mdi-calendar-heart'),
       (6, 'recommendations', 'Polecajki', 'mdi-thumb-up-outline'),
       (7, 'class-schedule', 'Plan zajęć', 'mdi-school-outline');
