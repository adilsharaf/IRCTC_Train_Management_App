package com.tcs.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tcs.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

	List<Ticket> findByIsDeleted(boolean value);

	@Query("SELECT t FROM Ticket t WHERE " + "(:passengerName IS NULL OR t.passengerName LIKE %:passengerName%) AND "
			+ "(:email IS NULL OR t.email LIKE %:email%) AND " + "(:trainId IS NULL OR t.trainId = :trainId) AND "
			+ "(:travelDate IS NULL OR t.travelDate = :travelDate) AND " + "t.isDeleted = false")
	List<Ticket> searchTickets(@Param("passengerName") String passengerName, @Param("email") String email,
			@Param("trainId") Long trainId, @Param("travelDate") LocalDate travelDate);

	List<Ticket> findByHighFareTicket(boolean b);

	List<Ticket> findByTravelDate(LocalDate date);

}
