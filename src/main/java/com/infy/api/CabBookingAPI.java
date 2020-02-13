package com.infy.api;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.infy.model.CabBooking;
import com.infy.service.BookingService;
@RestController
@RequestMapping(value="/bookings")
public class CabBookingAPI {

	@Autowired
	BookingService bookingService;
	
	@Autowired
	Environment environment;
	
	@PostMapping(value="/")
	public ResponseEntity<String> bookCab(@RequestBody CabBooking cabBooking) throws Exception {
		try{
			Integer bookingId= bookingService.bookCab(cabBooking);
			ResponseEntity<String> response= new ResponseEntity<String>
			(environment.getProperty("API.BOOKING_SUCCESSFUL")+bookingId
					, HttpStatus.CREATED);
			return response;
		}
		catch(Exception e){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					environment.getProperty("BookingService.INVALID_SERVICE_AREA"));
		}
	}

	@GetMapping(value="/{mobileNo}")
	public ResponseEntity<List<CabBooking>> getBookingDetails(@PathVariable Long mobileNo) throws Exception {
		try{
			List<CabBooking> cabs= bookingService.getBookingDetails(mobileNo);
			ResponseEntity<List<CabBooking>> response= new ResponseEntity<List<CabBooking>>(HttpStatus.OK);
			return response;
		}
		catch(Exception e){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					environment.getProperty("BookingService.BOOKINGS_NOT_FOUND"));
		}

	}

	@DeleteMapping(value="/{bookingId}")
	public ResponseEntity<String> cancelBooking(@PathVariable Integer bookingId) throws Exception {
		try{
			Integer bookingIdDeleted= bookingService.cancelBooking(bookingId);
			return new ResponseEntity<String>
			(environment.getProperty("API.BOOKING_CANCELLED")+bookingIdDeleted
					,HttpStatus.OK);
		}
		catch(Exception e){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					environment.getProperty("BookingService.BOOKINGS_NOT_FOUND"));
		}
	}

}
