package com.senzo.qettal.checkout.tickets;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.senzo.qettal.checkout.purchase.Purchase;

@Component
public class TicketFactory {
	
	@Autowired
	private TicketQRCodeGenerator generator; 
	@Autowired
	private TicketS3Uploader uploader;
	@Autowired
	private Tickets tickets;
	
	public List<Ticket> createFor(Purchase purchase) {
		return purchase.getItems()
				.stream()
				.map(generator::generate)
				.map(uploader::upload)
				.map(qrCode -> new Ticket(qrCode.getSecurityHash(), qrCode.getPurchaseItem()))
				.map(tickets::save)
				.collect(toList());
	}

}
