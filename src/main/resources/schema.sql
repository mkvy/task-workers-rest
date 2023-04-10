CREATE table if not exists workers(
    id SERIAL PRIMARY KEY,
    name_w VARCHAR(255),
    position_p VARCHAR(255),
    avatar_image bytea
    );
CREATE table if not exists tasks(
    id SERIAL PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    description VARCHAR(200) NOT NULL,
    time_t TIMESTAMP,
    status VARCHAR(50),
    performer_id INTEGER,
    FOREIGN KEY (performer_id) REFERENCES workers(id) ON DELETE SET NULL
);