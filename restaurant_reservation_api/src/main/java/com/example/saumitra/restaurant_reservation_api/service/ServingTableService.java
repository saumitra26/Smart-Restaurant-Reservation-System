package com.example.saumitra.restaurant_reservation_api.service;

import com.example.saumitra.restaurant_reservation_api.dto.request.servingTable.CreateServingTableRequest;
import com.example.saumitra.restaurant_reservation_api.dto.response.servingTable.ServingTableResponse;
import com.example.saumitra.restaurant_reservation_api.entity.ServingTable;
import com.example.saumitra.restaurant_reservation_api.repository.ServingTableRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServingTableService {
    private final ServingTableRepository servingTableRepository;

    public ServingTableResponse create(CreateServingTableRequest request) {
        if (servingTableRepository.existsByPosXAndPosY(request.posX(), request.posY())) {
            throw new IllegalArgumentException("A table already exists at this position");
        }
        ServingTable servingTable = ServingTable.builder()
                .tableNumber(request.tableNumber())
                .capacity(request.capacity())
                .posX(request.posX())
                .posY(request.posY())
                .windowSeat(request.windowSeat())
                .nearPlayArea(request.nearPlayArea())
                .privateArea(request.privateArea())
                .accessible(request.accessible())
                .zone(request.zone())
                .build();
        ServingTable saved = servingTableRepository.save(servingTable);
        return ServingTableResponse.of(saved);
    }
    public List<ServingTableResponse> getAllTables(){
        return servingTableRepository.findAll().stream()
                .map(servingTable->ServingTableResponse.of(servingTable))
                .toList();
    }
}
