package com.infy.dao;

import java.util.List;

import com.infy.model.CabBooking;

public interface BookingDAO {

	public Float getFare(String from,String to) throws Exception;
	public Integer bookCab(CabBooking booking) throws Exception;
	public List<CabBooking> getdetails(Long mobileNo) throws Exception;
	public Integer cancelBooking(Integer bookingId) throws Exception;
}
