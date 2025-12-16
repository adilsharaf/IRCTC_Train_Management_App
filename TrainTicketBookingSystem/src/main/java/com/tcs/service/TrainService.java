package com.tcs.service;

import java.time.LocalDate;
import java.util.List;

import com.tcs.dto.TicketDTO;
import com.tcs.entity.Ticket;
import com.tcs.exception.InvalidOperationException;
import com.tcs.exception.ResourceNotFoundException;

public interface TrainService {


	    TicketDTO bookTicket(Ticket ticket) throws InvalidOperationException,ResourceNotFoundException;
	    String cancelTicket(Long bookingId) throws ResourceNotFoundException,InvalidOperationException ;
	    TicketDTO restoreCancelledTicket(Long bookingId) throws ResourceNotFoundException,InvalidOperationException ;
	    List<TicketDTO> getAllTickets() throws ResourceNotFoundException,InvalidOperationException ;
	    TicketDTO getTicketById(Long ticketId) throws ResourceNotFoundException,InvalidOperationException ;
	    List<TicketDTO> searchTickets(String passengerName, String email, Long trainId, LocalDate travelDate)throws ResourceNotFoundException,InvalidOperationException ;
	    List<TicketDTO> getHighFareTickets() throws ResourceNotFoundException,InvalidOperationException ;
	    List<TicketDTO> getTicketsByTravelDate(LocalDate travelDate) throws ResourceNotFoundException,InvalidOperationException ;
	    TicketDTO updateTicket(Long ticketId, Ticket updatedTicket) throws ResourceNotFoundException;

}
