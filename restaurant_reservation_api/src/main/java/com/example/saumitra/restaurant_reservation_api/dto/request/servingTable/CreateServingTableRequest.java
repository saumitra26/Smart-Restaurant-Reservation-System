package com.example.saumitra.restaurant_reservation_api.dto.request.servingTable;

import com.example.saumitra.restaurant_reservation_api.entity.Zone;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateServingTableRequest(
        @NotBlank
        String tableNumber,

        @NotNull
        @Min(1)
        Integer capacity,

        @Min(0)
        int posX,

        @Min(0)
        int posY,

        boolean windowSeat,
        boolean nearPlayArea,
        boolean privateArea,
        boolean accessible,

        @NotNull
        Zone zone
) {
}
