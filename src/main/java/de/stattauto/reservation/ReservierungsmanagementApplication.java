package de.stattauto.reservation;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import de.stattauto.reservation.entity.Reservation;
import de.stattauto.reservation.repository.ReservationRepository;
import de.stattauto.reservation.restcontroller.ReservationRestController;

@SpringBootApplication
@EnableDiscoveryClient
public class ReservierungsmanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservierungsmanagementApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init (ReservationRepository rr) {
		return args -> {
			rr.save(new Reservation(null,"4711","10011","RESERVATION"));
			rr.save(new Reservation(null,"4712","10012","RESERVATION"));
			rr.save(new Reservation(null,"4713","10013","REPAIR"));
			rr.save(new Reservation(null,"4714","10014","RESERVATION"));
		};
		
	}
}
