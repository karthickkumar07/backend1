package com.infy.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Fare")
public class FareEstimationEntity {
	
	
	@Id
	private Integer fareId;
	private String source;
	private String destination;
	private Float fare;
	public Integer getFareId() {
		return fareId;
	}
	public void setFareId(Integer fareId) {
		this.fareId = fareId;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public Float getFare() {
		return fare;
	}
	public void setFare(Float fare) {
		this.fare = fare;
	}
	
}
