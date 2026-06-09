package in.neuprakash.SajiloYatra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SajiloYatraApplication {

	public static void main(String[] args) {
		SpringApplication.run(SajiloYatraApplication.class, args);
	}

}
