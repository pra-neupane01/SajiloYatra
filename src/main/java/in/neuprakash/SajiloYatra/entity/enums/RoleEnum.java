package in.neuprakash.SajiloYatra.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Getter
@RequiredArgsConstructor
public enum RoleEnum {

    ADMIN(Set.of(
            Permission.USER_VIEW,
            Permission.USER_UPDATE,
            Permission.USER_DELETE,
            Permission.PROFILE_UPDATE,

            Permission.BUS_CREATE,
            Permission.BUS_VIEW,
            Permission.BUS_UPDATE,
            Permission.BUS_DELETE,

            Permission.ROUTE_CREATE,
            Permission.ROUTE_VIEW,
            Permission.ROUTE_UPDATE,
            Permission.ROUTE_DELETE,

            Permission.TRIP_CREATE,
            Permission.TRIP_VIEW,
            Permission.TRIP_UPDATE,
            Permission.TRIP_DELETE,
            Permission.TRIP_SEARCH,

            Permission.BOOKING_VIEW,
            Permission.BOOKING_UPDATE,
            Permission.BOOKING_DELETE,

            Permission.PAYMENT_VIEW,

            Permission.TICKET_GENERATE,
            Permission.TICKET_VIEW,

            Permission.SEAT_GENERATE,
            Permission.SEAT_VIEW,
            Permission.SEAT_ASSIGN,

            Permission.DASHBOARD_VIEW
    )),

    PASSENGER(Set.of(
            Permission.PROFILE_UPDATE,

            Permission.TRIP_VIEW,
            Permission.TRIP_SEARCH,

            Permission.BOOKING_CREATE,
            Permission.BOOKING_VIEW,

            Permission.PAYMENT_CREATE,
            Permission.PAYMENT_VIEW,

            Permission.TICKET_VIEW,

            Permission.SEAT_VIEW,
            Permission.SEAT_ASSIGN
    ));

    private final Set<Permission> permissions;
}