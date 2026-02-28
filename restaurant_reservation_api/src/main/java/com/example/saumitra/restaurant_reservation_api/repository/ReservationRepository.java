package com.example.saumitra.restaurant_reservation_api.repository;

import com.example.saumitra.restaurant_reservation_api.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
}
