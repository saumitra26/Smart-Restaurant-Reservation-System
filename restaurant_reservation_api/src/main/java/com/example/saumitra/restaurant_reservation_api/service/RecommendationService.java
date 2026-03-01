package com.example.saumitra.restaurant_reservation_api.service;

import com.example.saumitra.restaurant_reservation_api.dto.request.recommendation.ServingTableSearchRequest;
import com.example.saumitra.restaurant_reservation_api.dto.response.recommendation.ServingTableRecommendationResponse;
import com.example.saumitra.restaurant_reservation_api.entity.ServingTable;
import com.example.saumitra.restaurant_reservation_api.repository.ReservationRepository;
import com.example.saumitra.restaurant_reservation_api.repository.ServingTableRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Random;
@Service
@AllArgsConstructor
public class RecommendationService {
    private final ServingTableRepository servingTableRepository;
    private  final ReservationRepository reservationRepository;
    private final Random random = new Random();
    private static final int SIZE_WEIGHT =100;
    private static final int ZONE_WEIGHT = 30;
    private static final int PREFERENCE_WEIGHT=20;
    private record TableScore (ServingTable servingTable, int score){}

    public List<ServingTableRecommendationResponse> recommend(ServingTableSearchRequest request){
        LocalTime endTime = request.startTime().plusHours(2);
        List<Long> reservedTablesId= reservationRepository.findReservedTableIds(request.date(),
                request.startTime(),
                endTime);
        List<ServingTable> availableTables=servingTableRepository.findAll().stream()
                .filter(t-> !reservedTablesId.contains(t.getId()))
                .filter(t->t.getCapacity() >= request.guests())
                .toList();
        if(availableTables.isEmpty()){
            // throw new NoTableAvailableException("No suitable table found for your criteria");
        }
        return availableTables.stream()
                .map(t->new TableScore(t, calculateScore(t, request)))
                .sorted((a,b)->Integer.compare(b.score(), a.score()))
                .limit(3)
                .map(ts->ServingTableRecommendationResponse.of(ts.servingTable(),ts.score()))
                .toList();

    }
    private int calculateScore(ServingTable servingTable, ServingTableSearchRequest request) {
        int capacity = servingTable.getCapacity();
        int sizeDifference = capacity - request.guests();
        if (sizeDifference < 0) return 0;
        double wasteRatio = (double) sizeDifference / capacity;
        int efficiencyScore = (int) (SIZE_WEIGHT * (1 - wasteRatio));

        int preferenceScore = 0;
        if (Boolean.TRUE.equals(request.windowSeat())   && servingTable.isWindowSeat())   preferenceScore += PREFERENCE_WEIGHT;
        if (Boolean.TRUE.equals(request.privateArea())  && servingTable.isPrivateArea())  preferenceScore += PREFERENCE_WEIGHT;
        if (Boolean.TRUE.equals(request.accessible())   && servingTable.isAccessible())   preferenceScore += PREFERENCE_WEIGHT;
        if (Boolean.TRUE.equals(request.nearPlayArea())  && servingTable.isNearPlayArea()) preferenceScore += PREFERENCE_WEIGHT;
        if (request.zone() != null && servingTable.getZone() == request.zone())            preferenceScore += ZONE_WEIGHT;

        return efficiencyScore + preferenceScore;
    }
}
