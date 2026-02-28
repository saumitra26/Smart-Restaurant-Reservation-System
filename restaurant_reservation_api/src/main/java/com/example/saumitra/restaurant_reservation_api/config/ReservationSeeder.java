package com.example.saumitra.restaurant_reservation_api.config;

import com.example.saumitra.restaurant_reservation_api.entity.Reservation;
import com.example.saumitra.restaurant_reservation_api.entity.ServingTable;
import com.example.saumitra.restaurant_reservation_api.repository.ReservationRepository;
import com.example.saumitra.restaurant_reservation_api.repository.ServingTableRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class ReservationSeeder {
    private final ReservationRepository reservationRepository;
    private final ServingTableRepository tableRepository;

    private static final String[] FIRST_NAMES = {"John", "Jane", "Mike", "Sarah", "Alex", "Emma", "David", "Lisa"};
    private static final String[] LAST_NAMES = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis"};
    private static final String[] PHONE_PREFIXES = {"98", "97", "96", "95", "94"};

    @PostConstruct
    public void seedRandomReservations() {

        if (reservationRepository.count() > 0) return;

        Random random = new Random();
        List<ServingTable> tables = tableRepository.findAll();

        for (int i = 0; i < 10; i++) {
            ServingTable table = tables.get(random.nextInt(tables.size()));

            String customerName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)] + " "
                    + LAST_NAMES[random.nextInt(LAST_NAMES.length)];
            String email = customerName.toLowerCase().replace(" ", ".") + i + "@mail.com";
            String phone = PHONE_PREFIXES[random.nextInt(PHONE_PREFIXES.length)]
                    + String.format("%08d", random.nextInt(100000000));
            LocalDate reservationDate = LocalDate.now().plusDays(random.nextInt(14) - 7);
            int startHour = 11 + random.nextInt(8);
            int startMinute = random.nextInt(60);

            LocalTime startTime = LocalTime.of(startHour, startMinute);
            LocalTime endTime = startTime.plusHours(2 + random.nextInt(2)); //

            int groupSize = Math.min(2 + random.nextInt(7), table.getCapacity()); //
            Reservation reservation = Reservation.builder()
                    .customerName(customerName)
                    .email(email)
                    .phone(phone)
                    .groupSize(groupSize)
                    .reservationDate(reservationDate)
                    .startTime(startTime)
                    .endTime(endTime)
                    .servingTable(table)
                    .build();

            reservationRepository.save(reservation);
        }
    }
}
