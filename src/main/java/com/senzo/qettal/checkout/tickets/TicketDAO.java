package com.senzo.qettal.checkout.tickets;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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

	@Override
	public Optional<Ticket> findByHash(String hash) {
		String hql = "from "+Ticket.class.getSimpleName()+" t "
				+ "where t.hash = :hash";
		try {
			return Optional.of(em.createQuery(hql, Ticket.class)
				.setParameter("hash", hash)
				.setMaxResults(1)
				.getSingleResult());
		} catch(NoResultException e) {
			return Optional.empty();
		}
	}
	
}
