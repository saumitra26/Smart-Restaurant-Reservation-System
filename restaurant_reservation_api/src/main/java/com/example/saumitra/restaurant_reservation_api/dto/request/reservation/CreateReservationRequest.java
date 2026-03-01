package com.example.saumitra.restaurant_reservation_api.dto.request.reservation;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record CreateReservationRequest(
        @NotNull
        LocalDate reservationDate,
        @NotNull
        LocalTime startTime,
        @Min(1)
        Integer groupSize,
        @NotNull
        Long servingTableId,
        @NotBlank
        String customerName,
        @Email
        String email,
        @NotBlank
        String phone
) {
}
