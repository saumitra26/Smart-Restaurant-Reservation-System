package com.example.saumitra.restaurant_reservation_api.service;

import com.example.saumitra.restaurant_reservation_api.dto.request.reservation.CreateReservationRequest;
import com.example.saumitra.restaurant_reservation_api.dto.response.reservation.ReservationResponse;
import com.example.saumitra.restaurant_reservation_api.entity.Reservation;
import com.example.saumitra.restaurant_reservation_api.entity.ServingTable;
import com.example.saumitra.restaurant_reservation_api.repository.ReservationRepository;
import com.example.saumitra.restaurant_reservation_api.repository.ServingTableRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Random;

@Service
@AllArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ServingTableRepository servingTableRepository;
    private final Random random = new Random();
    public ReservationResponse create(CreateReservationRequest request){
        ServingTable servingTable= servingTableRepository.findById(request.servingTableId())
                .orElseThrow(()-> new RuntimeException(("Serving table not found with id: " + request.servingTableId())) );
        LocalTime endTime= request.startTime().plusHours(2 ).
                plusMinutes(random.nextInt(60));
        Reservation reservation = Reservation.builder()
                .reservationDate(request.reservationDate())
                .startTime(request.startTime())
                .endTime(endTime)
                .groupSize(request.groupSize())
                .customerName(request.customerName())
                .email(request.email())
                .phone(request.phone())
                .servingTable(servingTable)
                .build();
        Reservation saved = reservationRepository.save(reservation);
        return  ReservationResponse.of(saved);
    }
}
