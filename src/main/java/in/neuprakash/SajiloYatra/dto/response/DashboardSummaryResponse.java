package in.neuprakash.SajiloYatra.dto.response;


import java.math.BigDecimal;

public record DashboardSummaryResponse(
        Long totalPassengers,
        Long totalBuses,
        Long totalRoutes,
        Long totalTrips,
        Long totalBookings,
        Long pendingBookings,
        Long confirmedBookings,
        Long cancelledBookings,
        BigDecimal totalRevenue
) {
}
