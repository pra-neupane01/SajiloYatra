package in.neuprakash.SajiloYatra.repository;

import in.neuprakash.SajiloYatra.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    boolean existsByEmail(String email);

}

