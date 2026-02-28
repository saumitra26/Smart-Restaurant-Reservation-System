CREATE TABLE serving_tables (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    table_number VARCHAR(20) NOT NULL,
    capacity INT NOT NULL,
    pos_x INT NOT NULL,
    pos_y INT NOT NULL,
    window_seat BOOLEAN NOT NULL,
    near_play_area BOOLEAN NOT NULL,
    private_area BOOLEAN NOT NULL,
    accessible BOOLEAN NOT NULL,
    zone VARCHAR(30) NOT NULL,
    CONSTRAINT serving_table_number UNIQUE (table_number),
    CONSTRAINT serving_table_position UNIQUE (pos_x, pos_y)
);
CREATE TABLE reservations (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    customer_name VARCHAR(100),
    email VARCHAR(150),
    phone VARCHAR(50),
    group_size INT NOT NULL,
    reservation_date DATE NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    serving_table_id BIGINT NOT NULL,
    CONSTRAINT fk_reservation_table
        FOREIGN KEY (serving_table_id)
        REFERENCES serving_tables(id)
);