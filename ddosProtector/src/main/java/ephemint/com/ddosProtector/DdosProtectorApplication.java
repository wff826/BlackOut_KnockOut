package ephemint.com.ddosProtector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableScheduling
public class DdosProtectorApplication {

	public static void main(String[] args) {
		SpringApplication.run(DdosProtectorApplication.class, args);
	}

}
