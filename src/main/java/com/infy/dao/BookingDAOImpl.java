package com.infy.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.infy.entity.CabBookingEntity;
import com.infy.model.CabBooking;

@Repository("bookingDao")
public class BookingDAOImpl implements BookingDAO {

	@PersistenceContext
	private EntityManager entityManager;

	
	@Override
	public Float getFare(String from, String to) throws Exception {

		String queryString = "select f.fare from FareEstimationEntity f where f.source=?1 and f.destination=?2";
		Float fare = 0.0f;
		Query query = entityManager.createQuery(queryString);

		query.setParameter(1, from);
		query.setParameter(2, to);

		

		List<Float> result = query.getResultList();
		if(result.size()==1)
			fare = result.get(0);
		
		return fare;

	}

	@Override
	public Integer bookCab(CabBooking booking) throws Exception {
		Integer bookingId = 0;

		CabBookingEntity bookingEntity = new CabBookingEntity();

		bookingEntity.setSource(booking.getSource());
		bookingEntity.setDestination(booking.getDestination());
		bookingEntity.setFare(booking.getFare());
		bookingEntity.setUserMobile(booking.getUserMobile());
		bookingEntity.setTravelDate(booking.getTravelDate());
		bookingEntity.setStatus('B');
		entityManager.persist(bookingEntity);
		bookingId = bookingEntity.getBookingId();
		System.out.println(bookingId);
		return bookingId;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CabBooking> getdetails(Long mobileNo) throws Exception {
		List<CabBooking> bookingList = new ArrayList<CabBooking>();

		String queryString = "select c from CabBookingEntity c where c.userMobile=?1";

		Query query = entityManager.createQuery(queryString);

		query.setParameter(1, mobileNo);

		List<CabBookingEntity> cabBookingEntList = query.getResultList();

		if (!cabBookingEntList.isEmpty()) {

			for (CabBookingEntity bookingEnt : cabBookingEntList) {

				CabBooking booking = new CabBooking();

				booking.setBookingId(bookingEnt.getBookingId());
				booking.setTravelDate(bookingEnt.getTravelDate());
				booking.setFare(bookingEnt.getFare());
				booking.setSource(bookingEnt.getSource());
				booking.setDestination(bookingEnt.getDestination());
				booking.setStatus(bookingEnt.getStatus());
				booking.setUserMobile(bookingEnt.getUserMobile());
				bookingList.add(booking);
			}
		}

		return bookingList;

	}

	@Override
	public Integer cancelBooking(Integer bookingId) throws Exception {
		Integer toRet = null;
		
		CabBookingEntity bookingEntity = entityManager.find(CabBookingEntity.class, bookingId);

		if (bookingEntity != null) {

			toRet = bookingEntity.getBookingId();

			bookingEntity.setStatus('C');

		}

		return toRet;
	}

}
