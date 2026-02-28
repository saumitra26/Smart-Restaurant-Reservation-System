package com.example.saumitra.restaurant_reservation_api.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "reservations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "reservation_date")
    private LocalDate reservationDate;
    @Column(name = "start_time")
    private LocalTime startTime;
    @Column(name = "end_time")
    private LocalTime endTime;
    @Column(name = "group_size")
    private int groupSize;
    @Column(name = "customer_name")
    private String customerName;
    private String email;
    private String phone;
    @ManyToOne
    @JoinColumn(name = "serving_table_id")
    private ServingTable servingTable;

}
