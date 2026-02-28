INSERT INTO serving_tables
(table_number, capacity, pos_x, pos_y, window_seat, near_play_area, private_area, accessible, zone)
VALUES
('T1', 2, 1, 1, true,  false, false, true,  'INDOOR'),
('T2', 4, 2, 1, true,  false, false, false, 'INDOOR'),
('T3', 4, 3, 1, false, true,  false, false, 'INDOOR'),
('T4', 6, 1, 2, false, false, true,  false, 'PRIVATE_ROOM'),
('T5', 8, 2, 2, false, false, false, true,  'TERRACE'),
('T6', 2, 3, 2, true,  false, false, false, 'TERRACE'),
('T7', 4, 4, 1, true,  false, false, true,  'TERRACE'),
('T8', 8, 4, 2, false, false, true,  false, 'PRIVATE_ROOM');