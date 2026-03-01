package com.example.saumitra.restaurant_reservation_api.dto.response.servingTable;

import com.example.saumitra.restaurant_reservation_api.entity.ServingTable;
import com.example.saumitra.restaurant_reservation_api.entity.Zone;

public record ServingTableResponse(
        Long id,
        String tableNumber,
        Integer capacity,
        int posX,
        int posY,
        boolean windowSeat,
        boolean nearPlayArea,
        boolean privateArea,
        boolean accessible,
        Zone zone
) {
    public static ServingTableResponse of(ServingTable table){
        return new ServingTableResponse(
                table.getId(),
                table.getTableNumber(),
                table.getCapacity(),
                table.getPosX(),
                table.getPosY(),
                table.isWindowSeat(),
                table.isNearPlayArea(),
                table.isPrivateArea(),
                table.isAccessible(),
                table.getZone()
        );
    }
}
