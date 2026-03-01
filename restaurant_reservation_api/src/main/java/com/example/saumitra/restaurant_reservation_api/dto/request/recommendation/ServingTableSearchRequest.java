package com.example.saumitra.restaurant_reservation_api.dto.request.recommendation;

import com.example.saumitra.restaurant_reservation_api.entity.Zone;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record ServingTableSearchRequest(
        @NotNull LocalDate date,
        @NotNull LocalTime startTime,
        @Min(1) Integer guests,
        Zone zone,
        Boolean windowSeat,
        Boolean privateArea,
        Boolean accessible,
        Boolean nearPlayArea
) {
}
