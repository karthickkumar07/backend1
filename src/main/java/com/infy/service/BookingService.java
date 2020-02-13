package com.infy.service;

import java.util.List;

import com.infy.model.CabBooking;

public interface BookingService {

	

		
		public Integer bookCab(CabBooking booking) throws Exception;
		public List<CabBooking> getBookingDetails(Long mobileNo) throws Exception;
		public Integer cancelBooking(Integer bookingId) throws Exception;
	
}
