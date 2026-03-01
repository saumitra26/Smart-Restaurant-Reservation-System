package com.example.saumitra.restaurant_reservation_api.dto.response.recommendation;

import com.example.saumitra.restaurant_reservation_api.entity.ServingTable;
import com.example.saumitra.restaurant_reservation_api.entity.Zone;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record ServingTableRecommendationResponse(
        Long id,
        String tableNumber,
        int score
) {
    public static ServingTableRecommendationResponse of(ServingTable table, int score) {
        return new ServingTableRecommendationResponse(
                table.getId(),
                table.getTableNumber(),
                score
        );
    }
}
