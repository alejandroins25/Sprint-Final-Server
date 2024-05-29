package cat.institutmarianao.gymwebserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GymwebaApplication {
	public static final String DATE_PATTERN = "dd/MM/yyyy HH:mm:ss";

	public static void main(String[] args) {
		SpringApplication.run(GymwebaApplication.class, args);
	}

}
