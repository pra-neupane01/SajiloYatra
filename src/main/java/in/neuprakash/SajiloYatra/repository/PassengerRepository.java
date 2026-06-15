package in.neuprakash.SajiloYatra.repository;

import in.neuprakash.SajiloYatra.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

    boolean existsByCitizenshipNo(String citizenshipNo);

    boolean existsByCitizenshipNoAndIdNot(String citizenshipNo, Long id);

    boolean existsByUserId(Long userId);
}
