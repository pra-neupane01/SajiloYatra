package in.neuprakash.SajiloYatra.service;

import in.neuprakash.SajiloYatra.dto.response.DashboardSummaryResponse;
import in.neuprakash.SajiloYatra.entity.enums.BookingStatusEnum;
import in.neuprakash.SajiloYatra.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class DashboardService {
    private final BookingRepository bookingRepository;
    private final PaymentRepository paymentRepository;
    private final PassengerRepository passengerRepository;
    private final BusRepository busRepository;
    private final RouteRepository routeRepository;
    private final TripRepository tripRepository;

    public DashboardSummaryResponse getDashboardSummary() {
        Long totalPassengers = passengerRepository.count();
        Long totalBuses = busRepository.count();
        Long totalRoutes = routeRepository.count();
        Long totalTrips = tripRepository.count();
        Long totalBookings = bookingRepository.count();

        Long confirmedBooking = bookingRepository.countByBookingStatusEnum(BookingStatusEnum.CONFIRMED);
        Long pendingBooking = bookingRepository.countByBookingStatusEnum(BookingStatusEnum.PENDING);
        Long cancelledBooking = bookingRepository.countByBookingStatusEnum(BookingStatusEnum.CANCELLED);


        BigDecimal totalRevenue = paymentRepository.getTotalRevenue();

        return new DashboardSummaryResponse(totalPassengers,
                totalBuses,
                totalRoutes,
                totalTrips,
                totalBookings,
                confirmedBooking,
                pendingBooking,
                cancelledBooking,
                totalRevenue);
    }
}
