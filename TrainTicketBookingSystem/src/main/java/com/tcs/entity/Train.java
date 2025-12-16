package com.tcs.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Train {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long trainId;
	
	private String source;
	private String destination;
	private double acFare;
	private double nonAcFare;
	private double sleeperFare;
	private int availableSeats = 100;

	public Train() {

	}

	public Train(String source, String destination, double acFare, double nonAcFare, double sleeperFare,
			int availableSeats) {
		super();
		this.source = source;
		this.destination = destination;
		this.acFare = acFare;
		this.nonAcFare = nonAcFare;
		this.sleeperFare = sleeperFare;
		this.availableSeats = availableSeats;
	}

	public long getTrainId() {
		return trainId;
	}

	public void setTrainId(long trainId) {
		this.trainId = trainId;
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

	public double getAcFare() {
		return acFare;
	}

	public void setAcFare(double acFare) {
		this.acFare = acFare;
	}

	public double getNonAcFare() {
		return nonAcFare;
	}

	public void setNonAcFare(double nonAcFare) {
		this.nonAcFare = nonAcFare;
	}

	public double getSleeperFare() {
		return sleeperFare;
	}

	public void setSleeperFare(double sleeperFare) {
		this.sleeperFare = sleeperFare;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

}
