package in.neuprakash.SajiloYatra.entity.enums;

public enum Permission {

    // User
    USER_VIEW,
    ALL_USER_VIEW,
    USER_UPDATE,
    USER_DELETE,
    PROFILE_UPDATE,

    // Bus
    BUS_CREATE,
    BUS_VIEW,
    BUS_UPDATE,
    BUS_DELETE,

    // Route
    ROUTE_CREATE,
    ROUTE_VIEW,
    ROUTE_UPDATE,
    ROUTE_DELETE,

    // Trip
    TRIP_CREATE,
    TRIP_VIEW,
    TRIP_UPDATE,
    TRIP_DELETE,
    TRIP_SEARCH,

    // Booking
    BOOKING_CREATE,
    BOOKING_VIEW,
    BOOKING_UPDATE,
    BOOKING_DELETE,

    // Payment
    PAYMENT_CREATE,
    PAYMENT_VIEW,

    // Ticket
    TICKET_GENERATE,
    TICKET_VIEW,

    // Seat
    SEAT_GENERATE,
    SEAT_VIEW,
    SEAT_ASSIGN,

    // Dashboard
    DASHBOARD_VIEW
}