package eu.senla.hotelBooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories
public class Runner extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Runner.class, args);
        System.out.println("sda");
        System.out.println("s");
    }

}

