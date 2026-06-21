package in.neuprakash.SajiloYatra.specification;

import in.neuprakash.SajiloYatra.entity.Trip;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class TripSpecification {

    public static Specification<Trip> hasSource(String source) {
        return (root, query, cb) -> {
            if (source == null || source.isBlank()) return null;

            return cb.like(cb.lower(root.get("route").get("source")),
                    "%" + source.toLowerCase() + "%");
        };
    }

    public static Specification<Trip> hasDestination(String destination) {
        return (root, query, cb) -> {
            if (destination == null || destination.isBlank()) return null;

            return cb.like(cb.lower(root.get("route").get("destination")),
                    "%" + destination.toLowerCase() + "%");
        };
    }

    public static Specification<Trip> hasTripDate(LocalDate date) {
        return (root, query, cb) -> {
            if (date == null) return null;

            return cb.equal(root.get("tripDate"), date);
        };
    }
}
