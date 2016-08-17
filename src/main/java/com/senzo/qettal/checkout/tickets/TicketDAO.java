package com.senzo.qettal.checkout.tickets;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicketDAO implements Tickets{

	@Autowired
	private EntityManager em;
	
	@Override
	@Transactional
	public Ticket save(Ticket ticket) {
		em.persist(ticket);
		return ticket;
	}
	
}
