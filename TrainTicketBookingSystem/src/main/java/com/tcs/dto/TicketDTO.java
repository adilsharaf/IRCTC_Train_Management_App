package com.tcs.dto;

import java.time.LocalDate;

public class TicketDTO {

    private Long id; 
    private String passengerName; 
    private String email; 
    private String seatType; 
    private String bookingType; 
    private Double fare; 
    private String status; 
    private LocalDate travelDate; 
    private Boolean highFareTicket; 
    private TrainDTO train; 
    
    public TicketDTO(Long id, String passengerName, String email, String seatType, String bookingType,
                     Double fare, String status, LocalDate travelDate, Boolean highFareTicket, TrainDTO train) {
        this.id = id;
        this.passengerName = passengerName;
        this.email = email;
        this.seatType = seatType;
        this.bookingType = bookingType;
        this.fare = fare;
        this.status = status;
        this.travelDate = travelDate;
        this.highFareTicket = highFareTicket;
        this.train = train;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(LocalDate travelDate) {
        this.travelDate = travelDate;
    }

    public Boolean getHighFareTicket() {
        return highFareTicket;
    }

    public void setHighFareTicket(Boolean highFareTicket) {
        this.highFareTicket = highFareTicket;
    }

    public TrainDTO getTrain() {
        return train;
    }

    public void setTrain(TrainDTO train) {
        this.train = train;
    }
}
