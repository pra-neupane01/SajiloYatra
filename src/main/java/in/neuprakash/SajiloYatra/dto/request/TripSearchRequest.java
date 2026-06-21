package in.neuprakash.SajiloYatra.dto.request;

import java.time.LocalDate;

public record TripSearchRequest(String source,
                                String destination,
                                LocalDate tripDate) {
}
