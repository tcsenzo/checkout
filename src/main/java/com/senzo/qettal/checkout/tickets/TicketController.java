package com.senzo.qettal.checkout.tickets;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senzo.qettal.checkout.address.AddressDTO;

@RestController
@RequestMapping("/tickets")
public class TicketController {

	@Autowired
	private Tickets tickets;
	
	@RequestMapping(path = "/{hash}", method = GET)
	public ResponseEntity<TicketDTO> show(@PathVariable("hash") String hash) {
		Optional<Ticket> optionalTicket = tickets.findByHash(hash);
		if(!optionalTicket.isPresent()){
			return new ResponseEntity<>(NOT_FOUND);
		}
		
		Ticket ticket = optionalTicket.get();
		
		TicketTheaterDTO theater = new TicketTheaterDTO(ticket.getTheaterName(), AddressDTO.from(ticket.getTheaterAddress()));
		TicketEventDTO event = new TicketEventDTO(ticket.getEventName(), ticket.getPrice(), ticket.getScheduledDate(), theater);
		TicketOwnerDTO user = new TicketOwnerDTO(ticket.getOwnerName());
		TicketDTO ticketDTO = new TicketDTO(event, user);
		
		return new ResponseEntity<>(ticketDTO, OK);
	}
}
