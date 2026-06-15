package in.neuprakash.SajiloYatra.repository;

import in.neuprakash.SajiloYatra.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepository extends JpaRepository<Bus, Long> {
//    @Override
//    int deleteById(Long aLong);
//
//    @Query(value = "DELETE FROM Bus WHERE id=:id" , nativeQuery = true)
//    int deleteById(@Param(value = id) Long id);
}