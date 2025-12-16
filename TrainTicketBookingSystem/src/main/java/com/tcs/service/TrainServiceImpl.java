package com.tcs.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.dto.TicketDTO;
import com.tcs.dto.TrainDTO;
import com.tcs.entity.Ticket;
import com.tcs.entity.Train;
import com.tcs.exception.InvalidOperationException;
import com.tcs.exception.ResourceNotFoundException;
import com.tcs.repository.TicketRepository;
import com.tcs.repository.TrainRepository;

@Service
public class TrainServiceImpl implements TrainService {

	@Autowired
	TicketRepository ticketRepository;

	@Autowired
	TrainRepository trainRepository;

	@Override
	public TicketDTO bookTicket(Ticket ticket) throws InvalidOperationException, ResourceNotFoundException {

		if (ticket.getTravelDate().isBefore(LocalDate.now())) {
			throw new InvalidOperationException("Travel Date is from past");
		}
		Train train = trainRepository.findById(ticket.getTrainId())
				.orElseThrow(() -> new ResourceNotFoundException("No train found"));
		if (!train.getSource().equals(ticket.getSource()) || !train.getDestination().equals(ticket.getDestination())) {
			throw new InvalidOperationException("Source and destination  do not match the selected train route");
		}

		if (!isValidSeatType(ticket.getSeatType())) {
			throw new InvalidOperationException("Invalid seat type. Allowed values are AC, Non-AC, or Sleeper");
		}

		double fare = calculateFare(ticket, train);
		ticket.setStatus("BOOKED");
		ticket.setFare(fare);
		ticket.setIsDeleted(false);
		if(ticket.getBookingType()=="") {
			ticket.setBookingType("NORMAL");
		}

		
		if (train.getAvailableSeats() <= 0) {
			throw new InvalidOperationException("No available seats on the selected train");
		}

		train.setAvailableSeats(train.getAvailableSeats() - 1);
		trainRepository.save(train);

		
		ticketRepository.save(ticket);

		
		if (ticket.getFare() > 2000) {
			ticket.setHighFareTicket(true);
		}

		return createTicketDTO(ticket, train);
	}

	@Override
    public String cancelTicket(Long bookingId) throws ResourceNotFoundException,InvalidOperationException {
        
        Ticket ticket = ticketRepository.findById(bookingId).orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        
        if ("COMPLETED".equals(ticket.getStatus())) {
            throw new InvalidOperationException("Completed tickets cannot be cancelled");
        }

        
        ticket.setStatus("CANCELLED");
        ticket.setIsDeleted(true);
        ticketRepository.save(ticket);

        
        Train train =trainRepository.findByTrainId(ticket.getTrainId());
        train.setAvailableSeats(train.getAvailableSeats() + 1);
        trainRepository.save(train);

        createTicketDTO(ticket, train);
        return "Ticket is cancelled";
    }

    @Override
    public TicketDTO restoreCancelledTicket(Long bookingId) throws ResourceNotFoundException,InvalidOperationException {
        
        Ticket ticket = ticketRepository.findById(bookingId).orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        
        if (!"CANCELLED".equals(ticket.getStatus())) {
            throw new InvalidOperationException("Only cancelled tickets can be restored");
        }

        ticket.setStatus("BOOKED");
        ticket.setIsDeleted(false);
        ticketRepository.save(ticket);

        
        Train train = trainRepository.findByTrainId(ticket.getTrainId());
        train.setAvailableSeats(train.getAvailableSeats() - 1);
        trainRepository.save(train);

       
        return createTicketDTO(ticket, train);
    }

    @Override
    public List<TicketDTO> getAllTickets() throws ResourceNotFoundException,InvalidOperationException  {
        
        List<Ticket> tickets = ticketRepository.findByIsDeleted(false);
        
        
        return tickets.stream()
                .map(ticket -> createTicketDTO(ticket, trainRepository.findByTrainId(ticket.getTrainId())))
                .collect(Collectors.toList());
    }

    @Override
    public TicketDTO getTicketById(Long ticketId) throws ResourceNotFoundException,InvalidOperationException  {
        
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new ResourceNotFoundException("Ticket not found"));;
        
        return createTicketDTO(ticket, trainRepository.findByTrainId(ticket.getTrainId()));
    }

    @Override
    public List<TicketDTO> searchTickets(String passengerName, String email, Long trainId, LocalDate travelDate)throws ResourceNotFoundException,InvalidOperationException  {
        
        List<Ticket> tickets = ticketRepository.searchTickets(passengerName, email, trainId, travelDate);
        
        
        return tickets.stream()
                .map(ticket -> createTicketDTO(ticket, trainRepository.findByTrainId(ticket.getTrainId())))
                .collect(Collectors.toList());
    }

    @Override
    public List<TicketDTO> getHighFareTickets() throws ResourceNotFoundException,InvalidOperationException {
        
        List<Ticket> tickets = ticketRepository.findByHighFareTicket(true);
        
        
        return tickets.stream()
                .map(ticket -> createTicketDTO(ticket, trainRepository.findByTrainId(ticket.getTrainId())))
                .collect(Collectors.toList());
    }

    @SuppressWarnings("deprecation")
	@Override
    public List<TicketDTO> getTicketsByTravelDate(LocalDate travelDate) throws ResourceNotFoundException,InvalidOperationException  {
        
        List<Ticket> tickets = ticketRepository.findByTravelDate(travelDate);
        
        
        return tickets.stream()
                .map(ticket -> createTicketDTO(ticket, trainRepository.getById(ticket.getTrainId())))
                .collect(Collectors.toList());
    }
    @Override
    public TicketDTO updateTicket(Long ticketId, Ticket updatedTicket) throws ResourceNotFoundException {
        
        Ticket existingTicket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found"));

        
        existingTicket.setTravelDate(updatedTicket.getTravelDate());

        
        double fare = calculateFare(existingTicket, trainRepository.findByTrainId(existingTicket.getTrainId()));
        existingTicket.setFare(fare);

        
        ticketRepository.save(existingTicket);

        return createTicketDTO(existingTicket, trainRepository.findByTrainId(existingTicket.getTrainId()));
    }


    private double calculateFare(Ticket ticket, Train train) {
        double fare = 0.0;
        
        switch (ticket.getSeatType()) {
            case "AC":
                fare = train.getAcFare();
                break;
            case "Non-AC":
                fare = train.getNonAcFare();
                break;
            case "Sleeper":
                fare = train.getSleeperFare();
                break;
            default:
                throw new IllegalArgumentException("Invalid seat type");
        }

        if ("Tatkal".equals(ticket.getBookingType())) {
            fare *= 1.25; 
        }
        return fare;
    }

    private boolean isValidSeatType(String seatType) {
        return seatType.equals("AC") || seatType.equals("Non-AC") || seatType.equals("Sleeper");
    }

    private TicketDTO createTicketDTO(Ticket ticket, Train train) {
        
        TrainDTO trainDTO = new TrainDTO(
                train.getTrainId(),
                train.getSource(),
                train.getDestination(),
                train.getAcFare(),
                train.getNonAcFare(),
                train.getSleeperFare()
        );

        
        return new TicketDTO(
                ticket.getId(),
                ticket.getPassengerName(),
                ticket.getEmail(),
                ticket.getSeatType(),
                ticket.getBookingType(),
                ticket.getFare(),
                ticket.getStatus(),
                ticket.getTravelDate(),
                ticket.getHighFareTicket(),
                trainDTO
        );
    }

}
