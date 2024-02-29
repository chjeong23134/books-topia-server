package me.bookstopia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BookstopiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstopiaApplication.class, args);
	}

}
