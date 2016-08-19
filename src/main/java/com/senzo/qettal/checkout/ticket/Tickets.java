package com.senzo.qettal.checkout.tickets;

import java.util.Optional;

public interface Tickets {

	Ticket save(Ticket ticket);

	Optional<Ticket> findByHash(String hash);

}
