package com.infy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.dao.BookingDAO;
import com.infy.model.CabBooking;

@Service("bookingService")
@Transactional
public class BookingServiceImpl implements BookingService {

	@Autowired
	BookingDAO bookingDao;

	@Override
	public Integer bookCab(CabBooking booking) throws Exception {
		Float fare = 0f;
		fare = bookingDao.getFare(booking.getSource(), booking.getDestination());
		if (fare == 0) {
			throw new Exception("BookingService.INVALID_SERVICE_AREA");
		}
		booking.setFare(fare);
		Integer bookingId = bookingDao.bookCab(booking);
		return bookingId;
	}

	@Override
	public List<CabBooking> getBookingDetails(Long mobileNo) throws Exception {
		List<CabBooking> bookingDetail = null;
		try {
			bookingDetail = bookingDao.getdetails(mobileNo);

			if (bookingDetail.isEmpty())
				throw new Exception("BookingService.BOOKINGS_NOT_FOUND");

		} catch (Exception e) {
			throw e;
		}
		return bookingDetail;
	}

	@Override
	public Integer cancelBooking(Integer bookingId) throws Exception {
		Integer booking_Id = 0;
		try {

			booking_Id = bookingDao.cancelBooking(bookingId);
			if (booking_Id == null)
				throw new Exception("BookingService.BOOKINGS_NOT_FOUND");
		} catch (Exception e) {
			throw e;
		}
		return booking_Id;
	}

}
