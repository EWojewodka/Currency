package wroclaw.webrest.runnable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
@EntityScan(basePackages = "wroclaw.webrest.*")
@ComponentScan(basePackages = "wroclaw.webrest.*")
@EnableJpaRepositories(basePackages = "wroclaw.webrest.repository")
public class ErProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErProjectApplication.class, args);
	}
}
