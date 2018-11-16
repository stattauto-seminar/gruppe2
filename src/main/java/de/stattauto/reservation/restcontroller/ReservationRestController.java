package de.stattauto.reservation.restcontroller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.stattauto.reservation.entity.Reservation;
import de.stattauto.reservation.repository.ReservationRepository;


@RefreshScope
@RestController
public class ReservationRestController {
	
	@Autowired
	private ReservationRepository repo;
	
	@Value("${welcome.message}")
	private String message;
	
	
	@GetMapping("/hello/{name}")
	//http://localhost:8084/hello/Klaus
	// see application.properties
	public String hello(@PathVariable String name) {
		return message + " " + name;
	}
	
	@GetMapping("/hello2")
	//http://localhost:8084/hello2?name=test
	// see application.properties
	public String hello2(@RequestParam String name) {
		return "Hello2 " + name;
	}
	
	@GetMapping("/reservation/list")
	public Collection<Reservation> listReservations()
	{
		return repo.findAll();
	}
	
	@PostMapping("/reservation")
	public void addReservation(@RequestBody Reservation reservation)
	{
		repo.save(reservation);
	}
	
	@GetMapping("/reservation/search/")
	//http://localhost:8084/hello2?name=test
	// see application.properties
	public Reservation searchReservation(@RequestParam Long id) {
		return repo.findById(id).orElse(null);
	}
		
	@GetMapping("/reservation/{reservation}")
	//http://localhost:8084/hello2?name=test
	// see application.properties
   public Reservation searchReservationById(@RequestParam Reservation reservation) {
	   return reservation;

   }

	@GetMapping("/searchReservationByCar")
	// http://localhost:8084/searchReservationByCar?carId=10013
	// see application.properties
   public Reservation searchReservationByCar(@RequestParam String carId) {
	   Collection <Reservation> colres;
	   colres= repo.findByCarId(carId);
	   if ((colres==null) || (colres.size()==0)) {
		   return null;
	   }
	   else {
		  // return (Reservation) colres.toArray()[0];
		   return colres.iterator().next();
	   }
   }

	@GetMapping("/searchReservationByCarLike")
	// http://localhost:8084/searchReservationByCar?carId=10013
	// see application.properties
   public Reservation searchReservationByCarLike(@RequestParam String carId) {
	   Collection <Reservation> colres;
	   colres= repo.findByCarId(carId);
	   if ((colres==null) || (colres.size()==0)) {
		   return null;
	   }
	   else {
		  // return (Reservation) colres.toArray()[0];
		   return colres.iterator().next();
	   }
   }
	
	
}
