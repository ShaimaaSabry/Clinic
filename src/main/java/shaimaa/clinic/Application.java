package shaimaa.clinic;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Doctor's Clinic APIs",
				version = "1.0.0",
				description = "APIs for managing a doctor's clinic. The doctor can add available slots for patients to book, view the patients' scheduled appointments, and mark an appointment as complete or cancelled."
		)
)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
