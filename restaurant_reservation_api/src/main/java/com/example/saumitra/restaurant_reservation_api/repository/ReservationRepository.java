package com.example.saumitra.restaurant_reservation_api.repository;

import com.example.saumitra.restaurant_reservation_api.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ReservationRepository  extends JpaRepository<Reservation, Long> {
    @Query("""
       SELECT r.servingTable.id
       FROM Reservation r
       WHERE r.reservationDate = :date
       AND r.startTime < :endTime
       AND r.endTime > :startTime
       """)
    List<Long> findReservedTableIds(
            @Param("date") LocalDate date,
            @Param("startTime") LocalTime startTime,
            @Param("endTime") LocalTime endTime
    );
}
