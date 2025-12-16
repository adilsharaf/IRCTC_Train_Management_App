package com.tcs.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.dto.TicketDTO;
import com.tcs.entity.Ticket;
import com.tcs.exception.InvalidOperationException;
import com.tcs.exception.ResourceNotFoundException;
import com.tcs.service.TrainService;

@RestController
@RequestMapping("train/tickets")
public class TrainController {

	@Autowired
	TrainService trainService;

	@PostMapping
	public ResponseEntity<TicketDTO> bookTicket(@RequestBody Ticket ticket)
			throws InvalidOperationException, ResourceNotFoundException {

		TicketDTO createdTicket = trainService.bookTicket(ticket);
		return ResponseEntity.status(201).body(createdTicket);

	}

	@GetMapping
	public ResponseEntity<List<TicketDTO>> getAllTickets() throws InvalidOperationException, ResourceNotFoundException {
		List<TicketDTO> tickets = trainService.getAllTickets();
		return ResponseEntity.ok(tickets);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TicketDTO> getTicketById(@PathVariable Long id)
			throws InvalidOperationException, ResourceNotFoundException {
		TicketDTO ticket = trainService.getTicketById(id);
		return ResponseEntity.ok(ticket);

	}

	@PutMapping("/{id}")
	public ResponseEntity<TicketDTO> updateTicket(@PathVariable Long id, @RequestBody Ticket updatedTicket)
			throws InvalidOperationException, ResourceNotFoundException {

		TicketDTO updatedTick = trainService.updateTicket(id, updatedTicket);
		return ResponseEntity.ok(updatedTick);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> cancelTicket(@PathVariable Long id)
			throws InvalidOperationException, ResourceNotFoundException {

		
		return new ResponseEntity<String>(trainService.cancelTicket(id),HttpStatus.OK);

	}

	@PutMapping("/{id}/restore")
	public ResponseEntity<TicketDTO> restoreCancelledTicket(@PathVariable Long id)
			throws InvalidOperationException, ResourceNotFoundException {

		TicketDTO restoredTicket = trainService.restoreCancelledTicket(id);
		return ResponseEntity.ok(restoredTicket);

	}

	@GetMapping("/search")
	public ResponseEntity<List<TicketDTO>> searchTickets(@RequestParam(required = false) String passengerName,
			@RequestParam(required = false) String email, @RequestParam(required = false) Long trainId,
			@RequestParam(required = false) LocalDate travelDate)
			throws InvalidOperationException, ResourceNotFoundException {

		List<TicketDTO> tickets = trainService.searchTickets(passengerName, email, trainId, travelDate);
		return ResponseEntity.ok(tickets);
	}

	@GetMapping("/high-fare")
	public ResponseEntity<List<TicketDTO>> getHighFareTickets()
			throws InvalidOperationException, ResourceNotFoundException {
		List<TicketDTO> highFareTickets = trainService.getHighFareTickets();
		return ResponseEntity.ok(highFareTickets);
	}

	
	@GetMapping("/date")
	public ResponseEntity<List<TicketDTO>> getTicketsByTravelDate(@RequestParam LocalDate travelDate)
			throws InvalidOperationException, ResourceNotFoundException {
		List<TicketDTO> tickets = trainService.getTicketsByTravelDate(travelDate);
		return ResponseEntity.ok(tickets);
	}

}
