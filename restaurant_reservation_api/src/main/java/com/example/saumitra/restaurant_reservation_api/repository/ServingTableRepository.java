package com.example.saumitra.restaurant_reservation_api.repository;

import com.example.saumitra.restaurant_reservation_api.entity.ServingTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServingTableRepository extends JpaRepository<ServingTable,Long> {
    boolean existsByPosXAndPosY(int posX, int posY);
}
