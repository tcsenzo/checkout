package com.senzo.qettal.checkout.ticket;

import java.util.Optional;

public interface Tickets {

	Ticket save(Ticket ticket);

	Optional<Ticket> findByHash(String hash);

}
