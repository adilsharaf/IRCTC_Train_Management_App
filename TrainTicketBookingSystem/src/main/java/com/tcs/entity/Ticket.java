package com.tcs.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String passengerName;
	private String email;
	private Long trainId;

	private String source;
	private String destination;
	private String seatType;
	private String bookingType="NORMAL";
	private Double fare;
	private LocalDate travelDate;
	private String status;
	private Boolean isDeleted = false;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private Boolean highFareTicket;

	public Ticket() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
		this.bookingType="NORMAL";
		this.isDeleted=false;
	}

	public Ticket(String passengerName, String email, Long trainId, String source, String destination, String seatType,
			String bookingType, Double fare, LocalDate travelDate, String status, Boolean isDeleted,
			LocalDateTime createdAt, LocalDateTime updatedAt, Boolean highFareTicket) {
		super();
		this.passengerName = passengerName;
		this.email = email;
		this.trainId = trainId;
		this.source = source;
		this.destination = destination;
		this.seatType = seatType;
		this.bookingType = bookingType;
		this.fare = fare;
		this.travelDate = travelDate;
		this.status = status;
		this.isDeleted = isDeleted;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.highFareTicket = highFareTicket;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getSeatType() {
		return seatType;
	}

	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}

	public String getBookingType() {
		return bookingType;
	}

	public void setBookingType(String bookingType) {
		this.bookingType = bookingType;
	}

	public Double getFare() {
		return fare;
	}

	public void setFare(Double fare) {
		this.fare = fare;
	}

	public LocalDate getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(LocalDate travelDate) {
		this.travelDate = travelDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Boolean getHighFareTicket() {
		return highFareTicket;
	}

	public void setHighFareTicket(Boolean highFareTicket) {
		this.highFareTicket = highFareTicket;
	}

	public Long getTrainId() {
		return trainId;
	}

	public void setTrainId(Long trainId) {
		this.trainId = trainId;
	}
}

//id Long Primary key
//passengerName String Passenger full name
//email String Passenger email
//trainId Long Reference to Train table
//source String Boarding station
//destination String Destination station
//seatType String AC / Non-AC / Sleeper
//bookingType String Normal / Tatkal
//fare Double Calculated fare
//travelDate LocalDate Journey date
//status String BOOKED / CANCELLED / COMPLETED
//isDeleted Boolean Soft delete flag
//createdAt Timestamp Record creation time
//updatedAt Timestamp Record update time
//highFareTicket Boolean True if fare > 2000
