package com.example.saumitra.restaurant_reservation_api.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="serving_tables")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServingTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "table_number")
    private String tableNumber;

    private int capacity;

    @Column(name = "pos_x")
    private int posX;

    @Column(name = "pos_y")
    private int posY;

    @Column(name = "window_seat")
    private boolean windowSeat;

    @Column(name = "near_play_area")
    private boolean nearPlayArea;

    @Column(name = "private_area")
    private boolean privateArea;
    private boolean accessible;
    @Enumerated(EnumType.STRING)
    private Zone zone;
}
