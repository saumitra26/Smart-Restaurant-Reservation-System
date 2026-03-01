package com.example.saumitra.restaurant_reservation_api.dto.response.reservation;

import com.example.saumitra.restaurant_reservation_api.dto.response.servingTable.ServingTableResponse;
import com.example.saumitra.restaurant_reservation_api.entity.Reservation;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservationResponse(
        Long id,
        LocalDate reservationDate,
        LocalTime startTime,
        LocalTime endTime,
        Integer groupSize,
        ServingTableResponse servingTable,
        String customerName
) {

    public static ReservationResponse of(Reservation reservation){
        return new ReservationResponse(
                reservation.getId(),
                reservation.getReservationDate(),
                reservation.getStartTime(),
                reservation.getEndTime(),
                reservation.getGroupSize(),
                ServingTableResponse.of(reservation.getServingTable()), // score needed
                reservation.getCustomerName()
        );
    }
}
